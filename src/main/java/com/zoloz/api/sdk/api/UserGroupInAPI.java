package com.zoloz.api.sdk.api;

import com.alibaba.fastjson.JSON;
import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.UserGroupInRequest;
import com.zoloz.api.sdk.model.UserGroupInResponse;

/**
 * Create by yaomeng on 2020-02-05 11:39
 *
 * @author yaomeng
 */
public class UserGroupInAPI {
    private static final String API_NAME = "v1.zoloz.usergroupin.in";

    private OpenApiClient openApiClient;

    public UserGroupInAPI(OpenApiClient openApiClient) {
        this.openApiClient = openApiClient;
    }

    /**
     * Add user face into group
     * @param request the face group in request
     * @see UserGroupInRequest
     * @return the face group in response
     * @see UserGroupInResponse
     */
    public UserGroupInResponse in(UserGroupInRequest request) {
        String response = openApiClient.callOpenApi(API_NAME, JSON.toJSONString(request));
        return JSON.parseObject(response, UserGroupInResponse.class);
    }
}
