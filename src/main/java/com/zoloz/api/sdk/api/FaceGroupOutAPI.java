package com.zoloz.api.sdk.api;

import com.alibaba.fastjson.JSON;
import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.FaceGroupOutRequest;
import com.zoloz.api.sdk.model.FaceGroupOutResponse;

/**
 * Create by yaomeng on 2020-02-05 11:49
 *
 * @author yaomeng
 */
public class FaceGroupOutAPI {
    private static final String API_NAME = "v1.zoloz.usergroupout.out";

    private OpenApiClient openApiClient;

    public FaceGroupOutAPI(OpenApiClient openApiClient) {
        this.openApiClient = openApiClient;
    }

    /**
     * Delete user face from group
     * @param request the face group out request
     * @see FaceGroupOutRequest
     * @return the face group out response
     * @see FaceGroupOutResponse
     */
    public FaceGroupOutResponse out(FaceGroupOutRequest request) {
        String response = openApiClient.callOpenApi(API_NAME, JSON.toJSONString(request));
        return JSON.parseObject(response, FaceGroupOutResponse.class);
    }
}
