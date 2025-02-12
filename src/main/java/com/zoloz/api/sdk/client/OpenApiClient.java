/*
 * Copyright (c) 2019 ZOLOZ PTE.LTD.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.zoloz.api.sdk.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.zoloz.api.sdk.util.AESUtil;
import com.zoloz.api.sdk.util.GenSignUtil;
import com.zoloz.api.sdk.util.OpenApiData;
import com.zoloz.api.sdk.util.RSAUtil;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OpenApiClient
 *
 * @author Zhongyang MA
 */
@Data
public class OpenApiClient {
    private static final Logger logger = LoggerFactory.getLogger(OpenApiClient.class);

    /**
     * default HTTP connection timeout in milliseconds
     */
    private static final Integer DEFAULT_CONN_TIMEOUT = 10000;

    /**
     * default HTTP read timeout in milliseconds
     */
    private static final Integer DEFAULT_READ_TIMEOUT = 10000;

    /**
     * HTTP connection timeout in milliseconds
     */
    private Integer connTimeout = DEFAULT_CONN_TIMEOUT;

    /**
     * HTTP read timeout in milliseconds
     */
    private Integer readTimeout = DEFAULT_READ_TIMEOUT;

    private String hostUrl;

    private String clientId;

    private String merchantPrivateKey;

    private String openApiPublicKey;

    private boolean signed;

    private boolean encrypted;

    private boolean isLoadTest;
    
    private Integer aesLength = 256;

    /**
     * default constructor with signature and encryption
     */
    public OpenApiClient() {
        this.signed = true;
        this.encrypted = true;
    }

    /**
     * invoke API gateway with the optional signature and encryption processes
     * @param apiName the name of API
     * @param request the request content in json string format
     * @return the response content in json string format
     */
    public String callOpenApi(String apiName, String request) {
        String encryptKey = null;
        byte[] key = null;
        try {
            if (encrypted) {
                // Generate aes key
                key = AESUtil.generateKey(aesLength);
                // encrypt content
                request = AESUtil.encrypt(key, request);
                // encrypt aes key
                encryptKey = RSAUtil.encrypt(openApiPublicKey, key);
            }
        } catch (Exception e) {
            logger.error("encrypt key fail.", e);
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
            if (logger.isInfoEnabled()) {
                logger.info("API URL = " + url);
            }
            OpenApiData data = post(url, encryptKey, clientId, reqTime, signature, request, new HashMap<>());
            resultContent = afterPost(apiName, data);
        } catch (Exception e) {
            logger.error("failed to get response.", e);
        }
        return resultContent;
    }

    /**
     * overload the callOpenApi method
     * invoke API gateway with the optional signature and encryption processes
     * @param apiName the name of API
     * @param request the request content in json string format
     * @param headers the map of header properties
     * @return the response content in json string format
     */
    public String callOpenApi(String apiName, String request, Map<String, String> headers) {
        String encryptKey = null;
        byte[] key = null;
        try {
            if (encrypted) {
                // Generate aes key
                key = AESUtil.generateKey(aesLength);
                // encrypt content
                request = AESUtil.encrypt(key, request);
                // encrypt aes key
                encryptKey = RSAUtil.encrypt(openApiPublicKey, key);
            }
        } catch (Exception e) {
            logger.error("encrypt key fail.", e);
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
            if (logger.isInfoEnabled()) {
                logger.info("API URL = " + url);
            }
            OpenApiData data = post(url, encryptKey, clientId, reqTime, signature, request, headers);
            resultContent = afterPost(apiName, data);
        } catch (Exception e) {
            logger.error("failed to get response.", e);
        }
        return resultContent;
    }

    private String afterPost(String apiName, OpenApiData data) throws Exception {
        String resultContent = null;
        for (String k : data.getHeader().keySet()) {
            if (logger.isInfoEnabled()) {
                if (k == null) {
                    logger.info(data.getHeader().get(k).get(0));
                } else {
                    logger.info(k + "=" + data.getHeader().get(k).get(0));
                }
            }
        }
        // 3. Check Signature
        if (data.getHeader().get("Signature") != null) {
            Map<String, String> responseSign = splitEncryptOrSignature(data.getHeader().get("Signature").get(0));
            String toSignContent = buildResponseSignatureContent(apiName, clientId, data.getHeader().get("Response-Time").get(0),
                    data.getContent());
            boolean checkSignResult = GenSignUtil.verify(openApiPublicKey, toSignContent,
                    URLDecoder.decode(responseSign.get("signature"), "UTF-8"));
            if (logger.isInfoEnabled()) {
                logger.info("check response signature " + checkSignResult);
            }
        }

        resultContent = data.getContent();
        // 4. decrypt
        if (encrypted) {
            if (data.getHeader().get("Encrypt") != null) {
                Map<String, String> encrypt = splitEncryptOrSignature(data.getHeader().get("Encrypt").get(0));
                if (encrypt != null && encrypt.get("symmetricKey") != null) {
                    byte[] decryptedAESKey = RSAUtil.decrypt(merchantPrivateKey,
                            URLDecoder.decode(encrypt.get("symmetricKey"), StandardCharsets.UTF_8.name()));
                    resultContent = AESUtil.decrypt(decryptedAESKey, resultContent);
                }
            }
        }
        return resultContent;
    }

    private String sign(String merchantPrivateKey, String api, String clientId, String reqTime, String request) throws Exception {
        StringBuffer sb = new StringBuffer(request.length() + 256);
        sb.append("POST ").append("/api/").append(api.replaceAll("\\.", "/")).append("\n");
        sb.append(clientId).append(".").append(reqTime).append(".").append(request);
        String str = sb.toString();
        return GenSignUtil.sign(merchantPrivateKey, str);
    }

    private OpenApiData post(String baseUrl, String encryptKey, String clientId, String reqTime, String signature, String request, Map<String,String> headers) throws IOException {
        OpenApiData data = new OpenApiData();
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer(1024 * 20);

        try {
            URL realUrl = new URL(baseUrl);
            URLConnection conn = realUrl.openConnection();

            conn.setConnectTimeout(connTimeout);
            conn.setReadTimeout(readTimeout);

            if (encryptKey != null) {
                conn.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
            } else {
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            }
            conn.setRequestProperty("Client-Id", clientId);
            conn.setRequestProperty("Request-Time", reqTime);
            if (isLoadTest) {
                conn.setRequestProperty("loadTestMode", "true");
            }
            if (signature != null) {
                conn.setRequestProperty("Signature",
                        "algorithm=RSA256, signature=" + URLEncoder.encode(signature, StandardCharsets.UTF_8.name()));
            }
            if (encryptKey != null) {
                conn.setRequestProperty("Encrypt",
                        "algorithm=RSA_AES, symmetricKey=" + URLEncoder.encode(encryptKey, StandardCharsets.UTF_8.name()));
            }
            // Add header properties to HttpURLConnection
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            if (logger.isInfoEnabled()) {
                for (String key : conn.getRequestProperties().keySet()) {
                    logger.info(key + "=" + conn.getRequestProperties().get(key).get(0));
                }
            }
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8.name());
            out.write(request);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8.name()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            data.setContent(result.toString());
            data.setHeader(conn.getHeaderFields());
        } catch (IOException e) {
            logger.error("failed to do request:{}.", request);
            throw e;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                logger.error("close io fail.", ex);
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
