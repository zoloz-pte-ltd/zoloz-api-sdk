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

package com.zoloz.api.sdk.client.protocol;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zoloz.api.sdk.client.exception.ApigwParseException;
import com.zoloz.api.sdk.util.AESUtil;
import com.zoloz.api.sdk.util.GenSignUtil;
import com.zoloz.api.sdk.client.model.OpenApiContext;
import com.zoloz.api.sdk.util.RSAUtil;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2-way authentication protocol
 *
 * @author Zhang Fang
 */
public class TwoWayAuthProtocol implements IApigwProtocol {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(TwoWayAuthProtocol.class);

    /**
     * client id
     */
    private String clientId;

    /**
     * merchant private key
     */
    private String merchantPrivateKey;

    /**
     * provider public key
     */
    private String openApiPublicKey;

    /**
     * whether to verify signature of the response
     */
    private boolean verifySign;

    /**
     * whether to encrypt request/response body
     */
    private boolean encrypt;

    /**
     * length of AES key
     */
    private Integer aesLength = 256;

    /**
     * ctor
     * @param clientId client id
     * @param merchantPrivateKey merchant private key
     * @param openApiPublicKey provider public key
     * @param encrypt whether to encrypt request/response body
     * @param validateSign whether to verify signature of the response
     */
    public TwoWayAuthProtocol(
            String clientId,
            String merchantPrivateKey,
            String openApiPublicKey,
            boolean encrypt,
            boolean validateSign,
            Integer aesLength
    )
    {
        this.clientId = clientId;
        this.merchantPrivateKey = merchantPrivateKey;
        this.openApiPublicKey = openApiPublicKey;
        this.verifySign = validateSign;
        this.encrypt = encrypt;
        this.aesLength = aesLength;
    }

    /**
     * generate signature
     * @param merchantPrivateKey merchant private key
     * @param apiName api name
     * @param clientId client Id
     * @param reqTime request time
     * @param reqBody request body
     * @return request signature
     */
    private String generateSignature(String merchantPrivateKey, String apiName, String clientId, String reqTime, String reqBody) {
        StringBuffer sb = new StringBuffer(reqBody.length() + 256);
        sb.append("POST ").append("/api/").append(apiName.replaceAll("\\.", "/")).append("\n");
        sb.append(clientId).append(".").append(reqTime).append(".").append(reqBody);
        String str = sb.toString();
        return GenSignUtil.sign(merchantPrivateKey, str);
    }

    /**
     * parse composed header (like Encrypt or Signature) value to entries
     * @param value header value
     * @return entries in header value
     */
    private Map<String, String> parseHeaderValue(String value) {
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

    /**
     * build content for signature validation
     * @param apiName api name
     * @param clientId client id
     * @param respTime response time
     * @param respBody response body
     * @return content to be verified
     */
    private String buildVerisignContent(String apiName, String clientId, String respTime, String respBody) {
        StringBuffer sb = new StringBuffer(respBody.length() + 256);
        sb.append("POST ").append("/api").append("/").append(apiName.replaceAll("\\.", "/")).append("\n");
        sb.append(clientId).append(".").append(respTime).append(".").append(respBody);
        return sb.toString();
    }

    /**
     * build http request from business request
     * @param context api gateway context
     * @param rawReq raw business request
     */
    @Override
    @SneakyThrows(UnsupportedEncodingException.class)
    public void buildRequest(OpenApiContext context, String rawReq) {

        Map<String, String> headers = context.getRequestHeaders();
        headers.put("Client-Id", clientId);

        // encrypt request
        String reqBody;
        if (encrypt) {
            // set content-type header
            headers.put("Content-Type", "text/plain; charset=UTF-8");

            // generate aes key
            byte[] key = AESUtil.generateKey(aesLength);
            // encrypt content
            reqBody = AESUtil.encrypt(key, rawReq);
            // encrypt aes key
            String encryptKey = RSAUtil.encrypt(openApiPublicKey, key);
            // construct header value
            String encryptHeadVal = "algorithm=RSA_AES, symmetricKey=" + URLEncoder.encode(encryptKey, StandardCharsets.UTF_8.name());
            // set encrypt header
            headers.put("Encrypt", encryptHeadVal);
        }
        else {
            reqBody = rawReq;
            // set content-type header
            headers.put("Content-Type", "application/json; charset=UTF-8");
        }

        // sign request
        String apiName = context.getApiName();
        String reqTime = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"));
        String signature = generateSignature(merchantPrivateKey, apiName, clientId, reqTime, reqBody);
        String signatureHeadVal = "algorithm=RSA256, signature=" + URLEncoder.encode(signature, StandardCharsets.UTF_8.name());

        headers.put("Signature", signatureHeadVal);
        headers.put("Request-Time", reqTime);

        // update request body
        context.setRequestBody(reqBody);
    }

    /**
     * parse business response from http response
     * @param context api gateway context
     * @return business response
     * @throws ApigwParseException cannot parse http response
     */
    @Override
    @SneakyThrows(UnsupportedEncodingException.class)
    public String parseResponse(OpenApiContext context) throws ApigwParseException {
        Map<String, List<String>> headers = context.getResponseHeaders();

        if (logger.isInfoEnabled()) {
            for (String k : headers.keySet()) {
                if (k == null) {
                    logger.info(headers.get(k).get(0));
                } else {
                    logger.info(k + "=" + headers.get(k).get(0));
                }
            }
        }

        // verify signature
        if (verifySign) {
            String apiName = context.getApiName();
            String response = context.getResponseBody();
            boolean verified = false;
            if (headers.get("Signature") != null) {

                Map<String, String> respSignature = parseHeaderValue(headers.get("Signature").get(0));

                String contentToVerify = buildVerisignContent(
                        apiName,
                        clientId,
                        headers.get("Response-Time").get(0),
                        response
                );

                verified = GenSignUtil.verify(
                        openApiPublicKey,
                        contentToVerify,
                        URLDecoder.decode(respSignature.get("signature"), "UTF-8")
                );
            }

            if (!verified) {
                throw new ApigwParseException("signature validation failed.");
            }
        }

        // decrypt response body
        String resultContent = context.getResponseBody();
        if (encrypt) {
            if (headers.get("Encrypt") != null) {
                Map<String, String> encrypt = parseHeaderValue(headers.get("Encrypt").get(0));
                if (encrypt != null && encrypt.get("symmetricKey") != null) {
                    byte[] decryptedAESKey = RSAUtil.decrypt(
                            merchantPrivateKey,
                            URLDecoder.decode(encrypt.get("symmetricKey"), StandardCharsets.UTF_8.name())
                    );
                    resultContent = AESUtil.decrypt(decryptedAESKey, resultContent);
                }
            }
        }

        return resultContent;
    }
}
