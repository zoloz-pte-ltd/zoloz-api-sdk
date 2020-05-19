package com.zoloz.api.sdk.api;

import com.alibaba.fastjson.JSON;

import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.PrivacyInfoDeleteRequest;
import com.zoloz.api.sdk.model.PrivacyInfoDeleteResponse;

public class PrivacyInfoDeleteApi {

    private static final String API_NAME = "v1.zoloz.privacyinfo.delete";

    private OpenApiClient openApiClient;

    public PrivacyInfoDeleteApi(OpenApiClient openApiClient) {
        this.openApiClient = openApiClient;
    }

    /**
     * the recognize method of PrivacyInfoDelete API
     * @param request the PrivacyInfoDelete request
     * @see PrivacyInfoDeleteRequest
     * @return the PrivacyInfoDelete response
     * @see PrivacyInfoDeleteResponse
     */
    public PrivacyInfoDeleteResponse delete(PrivacyInfoDeleteRequest request) {
        String response = openApiClient.callOpenApi(API_NAME, JSON.toJSONString(request));
        return JSON.parseObject(response, PrivacyInfoDeleteResponse.class);
    }
}
