package com.zoloz.api.sdk.api;

import com.alibaba.fastjson.JSON;
import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.UserGroupOutRequest;
import com.zoloz.api.sdk.model.UserGroupOutResponse;

/**
 * Create by yaomeng on 2020-02-05 11:49
 *
 * @author yaomeng
 */
public class UserGroupOutAPI {
    private static final String API_NAME = "v1.zoloz.usergroupout.out";

    private OpenApiClient openApiClient;

    public UserGroupOutAPI(OpenApiClient openApiClient) {
        this.openApiClient = openApiClient;
    }

    /**
     * Delete user face from group
     * @param request the face group out request
     * @see UserGroupOutRequest
     * @return the face group out response
     * @see UserGroupOutResponse
     */
    public UserGroupOutResponse out(UserGroupOutRequest request) {
        String response = openApiClient.callOpenApi(API_NAME, JSON.toJSONString(request));
        return JSON.parseObject(response, UserGroupOutResponse.class);
    }
}
