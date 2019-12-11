package com.zoloz.api.sdk.client;

import com.zoloz.api.sdk.util.AESUtil;
import com.zoloz.api.sdk.util.GenSignUtil;
import com.zoloz.api.sdk.util.OpenApiData;
import com.zoloz.api.sdk.util.RSAUtil;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhongyang MA
 * Date: 2019-11-18
 * Time: 20:34
 */
@Data
public class OpenApiClient {
    private static final Logger logger = LoggerFactory.getLogger(OpenApiClient.class);

    private String hostUrl;
    private String clientId;
    private String merchantPrivateKey;
    private String openApiPublicKey;
    private boolean signed;
    private boolean encrypted;

    // default constructor with signature and encryption
    public OpenApiClient() {
        this.signed = true;
        this.encrypted = true;
    }

    public String callOpenApi(String apiName, String request) {

        String encryptKey = null;
        byte[] key = null;
        try {
            if (encrypted) {
                // encrypt
                // Generate aes key
                key = AESUtil.generateKey(128);
                // encrypt content
                request = AESUtil.encrypt(key, request);
                // encrypt aes key
                encryptKey = RSAUtil.encrypt(openApiPublicKey, key);
            }
        } catch (Exception e) {
            logger.info("error: " + e);
        }
        String resultContent = null;
        try {
            // 1. sign the signature
            String reqTime = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"));
            String signature = null;
            if (signed) {
                signature = sign(merchantPrivateKey, apiName, clientId, reqTime, request);
            }
            // 2. Send data and receive response
            String url = hostUrl + "/api/" + apiName.replaceAll("\\.", "/");
            logger.info("API URL = " + url);
            OpenApiData data = post(url, encryptKey, clientId, reqTime, signature, request);
            for (String k : data.getHeader().keySet()) {
                if (k == null) {
                    logger.info(data.getHeader().get(k).get(0));
                } else {
                    logger.info(k + "=" + data.getHeader().get(k).get(0));
                }
            }
            //logger.info(data.getContent());

            // 3. Check Signature
            if (data.getHeader().get("Signature") != null) {
                Map<String, String> responseSign = splitEncryptOrSignature(data.getHeader().get("Signature").get(0));
                String toSignContent = buildResponseSignatureContent(apiName, clientId, data.getHeader().get("Response-Time").get(0), data.getContent());
                boolean checkSignResult = GenSignUtil.verify(openApiPublicKey, toSignContent, URLDecoder.decode(responseSign.get("signature"), "UTF-8"));
                logger.info("check response signature " + checkSignResult);
            }

            resultContent = data.getContent();
            // 4. decrypt
            if (encrypted) {
                if (data.getHeader().get("Encrypt") != null) {
                    Map<String, String> encrypt = splitEncryptOrSignature(data.getHeader().get("Encrypt").get(0));
                    if (encrypt != null && encrypt.get("symmetricKey") != null) {
                        resultContent = AESUtil.decrypt(key, resultContent);
                    }
                }
            }
        } catch (Exception e) {
            logger.info("error: " + e);
        }
        //logger.info(resultContent);
        return resultContent;
    }


    private String sign(String merchantPrivateKey, String api, String clientId, String reqTime, String request) throws Exception {
        StringBuffer sb = new StringBuffer(request.length() + 256);
        sb.append("POST ").append("/api/").append(api.replaceAll("\\.", "/")).append("\n");
        sb.append(clientId).append(".").append(reqTime).append(".").append(request);
        String str = sb.toString();
        return GenSignUtil.sign(merchantPrivateKey, str);
    }

    private OpenApiData post(String baseUrl, String encryptKey, String clientId, String reqTime, String signature, String request) {
        OpenApiData data = new OpenApiData();
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer(1024 * 20);

        try {
            URL realUrl = new URL(baseUrl);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            if (encryptKey != null) {
                conn.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
            } else {
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            }
            conn.setRequestProperty("Client-Id", clientId);
            conn.setRequestProperty("Request-Time", reqTime);
            if (signature != null) {
                conn.setRequestProperty("Signature", "algorithm=RSA256, signature=" + URLEncoder.encode(signature, "UTF-8"));
            }
            if (encryptKey != null) {
                conn.setRequestProperty("Encrypt", "algorithm=RSA_AES, symmetricKey=" + URLEncoder.encode(encryptKey, "UTF-8"));
            }

            for (String key : conn.getRequestProperties().keySet()) {
                logger.info(key + "=" + conn.getRequestProperties().get(key).get(0));
            }

            //logger.info(request);

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 中文有乱码的需要将PrintWriter改为如下
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            out.write(request);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            data.setContent(result.toString());
            data.setHeader(conn.getHeaderFields());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {  // 使用finally块来关闭输出流、输入流
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return data;
    }

    private Map<String, String> splitEncryptOrSignature(String value) {
        if (value == null) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        String[] pairs = value.split(",");
        if (pairs == null) {
            return map;
        }
        for (String pair : pairs) {
            if (pair == null) {
                continue;
            }
            String[] kv = pair.trim().split("=");
            if (kv != null && kv.length == 2 && kv[0] != null) {
                map.put(kv[0].trim(), kv[1].trim());
            }
        }
        return map;
    }

    private String buildResponseSignatureContent(String apiName, String clientId, String responseTime, String response) {
        StringBuffer sb = new StringBuffer(response.length() + 256);
        sb.append("POST ").append("/api").append("/").append(apiName.replaceAll("\\.", "/")).append("\n");
        sb.append(clientId).append(".").append(responseTime).append(".").append(response);
        return sb.toString();
    }

}
