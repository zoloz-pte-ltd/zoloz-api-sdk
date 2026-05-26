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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.zoloz.api.sdk.client.protocol.AKSKProtocol;
import com.zoloz.api.sdk.client.exception.ApigwParseException;
import com.zoloz.api.sdk.client.protocol.IApigwProtocol;
import com.zoloz.api.sdk.client.protocol.TwoWayAuthProtocol;
import com.zoloz.api.sdk.client.model.OpenApiContext;
import lombok.Data;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OpenAPI client
 *
 * @author Zhang Fang
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

    /**
     * host url
     * mandatory
     */
    private String hostUrl;

    /**
     * client Id
     * mandatory
     */
    private String clientId;


    /**
     * load test or not
     */
    private boolean isLoadTest;

    /**
     * whether throw exception on failure or simply return null
     */
    private boolean throwOnFailure = false;

    /**
     * protocol name
     * candidate values: 2way / aksk
     */
    private String protoName = "2way";
    /**
     * merchant private key
     * mandatory for 2way auth protocol
     */
    private String merchantPrivateKey;

    /**
     * provider public key
     * mandatory for 2way auth protocol
     */
    private String openApiPublicKey;

    /**
     * verify response signature or not
     * optional
     */
    private boolean signed = true;

    /**
     * encrypt request/response body or not
     * optional for 2way auth protocol
     */
    private boolean encrypted;

    /**
     * length AES encryption key
     * optional for 2way auth protocol
     */
    private Integer aesLength = 256;

    /**
     * access key
     * mandatory for aksk protocol
     */
    private String accessKey;

    /**
     * access secret
     * mandatory for aksk protocol
     */
    private String secretKey;

    /**
     * default constructor with signature and encryption
     */
    public OpenApiClient() {
        this.signed = true;
        this.encrypted = true;
    }
    
   /**
     * overload the callOpenApi method
     * invoke API gateway with the optional signature and encryption processes
     * @param apiName the name of API
     * @param request the request content in json string format
     * @return the response content in json string format
     */
    public String callOpenApi(String apiName, String request) {
        return callOpenApi(apiName, request, null);
    }

    /**
     * invoke API gateway with the optional signature and encryption processes
     * @param apiName the name of API
     * @param request the request content in json string format
     * @param headers the map of header properties
     * @return the response content in json string format
     */
    @SneakyThrows({ApigwParseException.class, IOException.class})
    public String callOpenApi(String apiName, String request, Map<String, String> headers) {
        // 1. choose protocol
        IApigwProtocol protocol = createProtocol();

        // 2. initialize context
        OpenApiContext context = initContext(apiName, headers);
        if (logger.isInfoEnabled()) {
            logger.info("context initialized.");
        }

        // 3. build request
        protocol.buildRequest(context, request);
        if (logger.isInfoEnabled()) {
            logger.info("request built.");
        }

        // 4. post data
        try {
            postData(context);
        }
        catch (IOException ex) {
            if (logger.isErrorEnabled()) {
                logger.error("response signature validation failed");
            }

            if (throwOnFailure) {
                throw ex;
            }
            else {
                return null;
            }
        }
        if (logger.isInfoEnabled()) {
            logger.info("data posted.");
        }

        // 5. parse response
        try {
            String result = protocol.parseResponse(context);
            return result;
        }
        catch (ApigwParseException ex) {
            if (logger.isErrorEnabled()) {
                logger.error("response signature validation failed");
            }

            if (throwOnFailure) {
                throw ex;
            }
            else {
                return null;
            }
        }
    }

    /**
     * Create protocol instance based on current configuration
     * @return protocol instance
     */
    protected IApigwProtocol createProtocol() {
        if (protoName.equals("2way")) {
            if (logger.isInfoEnabled()){
                logger.info("use 2way auth protocol");
            }
            return new TwoWayAuthProtocol(
                    clientId,
                    merchantPrivateKey,
                    openApiPublicKey,
                    encrypted,
                    signed,
                    aesLength
            );
        }
        else if (protoName.equals("aksk")) {
            if (logger.isInfoEnabled()){
                logger.info("use aksk protocol");
            }
            return new AKSKProtocol(
                    clientId,
                    accessKey,
                    secretKey,
                    signed
            );
        }
        else {
            throw new IllegalArgumentException("unknown protocol: " + protoName);
        }
    }

    /**
     * initialize api context
     * @param apiName api name
     * @param headers headers
     * @return context object
     */
    protected OpenApiContext initContext(String apiName, Map<String, String> headers) {
        OpenApiContext context = new OpenApiContext();
        context.setApiHost(hostUrl);
        context.setApiName(apiName);
        context.setRequestHeaders(new HashMap<>());
        if (isLoadTest) {
            context.getRequestHeaders().put("loadTestMode", "true");
        }
        if (headers != null) {
            for (Entry<String, String> entry: headers.entrySet()) {
                context.getRequestHeaders().put(entry.getKey(), entry.getValue());
            }
        }
        return context;
    }

    /**
     * Creates URL from string
     * @param host the host
     * @param apiName the api name
     * @return URL object
     * @throws MalformedURLException host or apiName is invalid
     */
    protected URLConnection createConnection(String host, String apiName) throws IOException {
        String apiUrl = host + "/api/" + apiName.replaceAll("\\.", "/");
        if (logger.isInfoEnabled()) {
            logger.info("API URL = " + apiUrl);
        }
        URL url = new URL(apiUrl);
        return url.openConnection();
    }

     /**
     * post data via network
     * @param context api context
     * @throws IOException network error
     */
    protected void postData(OpenApiContext context) throws IOException {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer(1024 * 20);

        try {
            String apiName = context.getApiName();
            URLConnection conn = createConnection(hostUrl, apiName);

            conn.setConnectTimeout(connTimeout);
            conn.setReadTimeout(readTimeout);

            if (context.getRequestHeaders() != null) {
                for (Entry<String, String> entry: context.getRequestHeaders().entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());

                    if (logger.isInfoEnabled()) {
                        logger.info(entry.getKey() + "=" + entry.getValue());
                    }
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
            out.write(context.getRequestBody());
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8.name()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            context.setResponseBody(result.toString());
            context.setResponseHeaders(conn.getHeaderFields());
        } catch (IOException e) {
            logger.error("failed to do request:{}.", context.getRequestBody());
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
    }
}
