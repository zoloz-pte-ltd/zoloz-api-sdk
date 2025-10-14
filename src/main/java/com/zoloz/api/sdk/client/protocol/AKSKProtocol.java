package com.zoloz.api.sdk.client.protocol;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import com.zoloz.api.sdk.client.exception.ApigwParseException;
import com.zoloz.api.sdk.client.model.OpenApiContext;
import com.zoloz.api.sdk.util.SignatureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AKSK based protocol
 *
 * @author Zhang Fang
 */
public class AKSKProtocol implements IApigwProtocol {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(AKSKProtocol.class);

    /**
     * accessKey
     */
    private String accessKey;

    /**
     * secretKey
     */
    private String secretKey;

    /**
     * clientId
     */
    private String clientId;

    /**
     * whether to verify response signature
     */
    private boolean verifyResp;

    /**
     * ctor
     * @param clientId client id
     * @param accessKey access key
     * @param secretKey secret key
     * @param verifyResp whether to verify response signature
     */
    public AKSKProtocol(String clientId, String accessKey, String secretKey, boolean verifyResp) {
        this.clientId = clientId;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.verifyResp = verifyResp;
    }

    /**
     * generate signature
     * @param secretKey secret key
     * @param apiName api name
     * @param clientId client Id
     * @param reqTime request time
     * @param reqBody request body
     * @return request signature
     */
    private String generateSignature(String secretKey, String apiName, String clientId, String reqTime, String reqBody) {
        StringBuffer sb = new StringBuffer(reqBody.length() + 256);
        sb.append("POST ").append("/api/").append(apiName.replaceAll("\\.", "/")).append("\n");
        sb.append(clientId).append(".").append(reqTime).append(".").append(reqBody);
        String str = sb.toString();
        return SignatureUtil.sign(str, secretKey);
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
    public void buildRequest(OpenApiContext context, String rawReq) {

        String apiName = context.getApiName();
        String reqTime = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"));

        // 1. sign request
        String signature = generateSignature(secretKey, apiName, clientId, reqTime, rawReq);

        // 2. add header
        Map<String, String> headers = context.getRequestHeaders();
        headers.put("Content-Type", "application/json; charset=UTF-8");
        headers.put("Client-Id", clientId);
        headers.put("Request-Time", reqTime);
        headers.put("Auth-Type", "AKSK");
        headers.put("Access-Key", accessKey);
        headers.put("Signature",signature);

        context.setRequestBody(rawReq);
    }

    /**
     * parse business response from http response
     * @param context api gateway context
     * @return business response
     * @throws ApigwParseException cannot parse http response
     */
    @Override
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

        // verify response signature
        String apiName = context.getApiName();
        boolean verified = false;
        if (headers.get("Signature") != null) {
            String signature = headers.get("Signature").get(0);
            String verisignContent = buildVerisignContent(
                    apiName,
                    clientId,
                    headers.get("Response-Time").get(0),
                    context.getResponseBody()
            );

            verified = SignatureUtil.verify(verisignContent, secretKey, signature);
        }

        if(!verified){
            if (logger.isErrorEnabled()) {
                logger.error("response signature verification failed.");
            }

            if (verifyResp) {
                throw new ApigwParseException("response signature verification fail.");
            }
        }

        // parse response body
        String respBody = context.getResponseBody();
        return respBody;
    }
}

