package com.zoloz.api.sdk.client.protocol;

import com.zoloz.api.sdk.client.exception.ApigwParseException;
import com.zoloz.api.sdk.client.model.OpenApiContext;

/**
 * interface for API gateway protocol
 *
 * @author Zhang Fang
 */
public interface IApigwProtocol {
    /**
     * build http request from business request
     * @param context api gateway context
     * @param rawReq raw business request
     */
    void buildRequest(OpenApiContext context, String rawReq);


    /**
     * parse business response from http response
     * @param context api gateway context
     * @return business response
     * @throws ApigwParseException cannot parse http response
     */
    String parseResponse(OpenApiContext context) throws ApigwParseException;
}
