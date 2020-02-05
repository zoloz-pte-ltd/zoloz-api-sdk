package com.zoloz.api.sdk.api;

import com.alibaba.fastjson.JSON;
import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.FaceGroupInRequest;
import com.zoloz.api.sdk.model.FaceGroupInResponse;

/**
 * Create by yaomeng on 2020-02-05 11:39
 *
 * @author yaomeng
 */
public class FaceGroupInAPI {
    private static final String API_NAME = "v1.zoloz.usergroupin.in";

    private OpenApiClient openApiClient;

    public FaceGroupInAPI(OpenApiClient openApiClient) {
        this.openApiClient = openApiClient;
    }

    /**
     * Add user face into group
     * @param request the face group in request
     * @see FaceGroupInRequest
     * @return the face group in response
     * @see FaceGroupInResponse
     */
    public FaceGroupInResponse in(FaceGroupInRequest request) {
        String response = openApiClient.callOpenApi(API_NAME, JSON.toJSONString(request));
        return JSON.parseObject(response, FaceGroupInResponse.class);
    }
}
