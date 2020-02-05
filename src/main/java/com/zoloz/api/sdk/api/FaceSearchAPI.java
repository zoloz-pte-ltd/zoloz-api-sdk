package com.zoloz.api.sdk.api;

import com.alibaba.fastjson.JSON;
import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.FaceSearchRequest;
import com.zoloz.api.sdk.model.FaceSearchResponse;

/**
 * Create by yaomeng on 2020-02-05 13:01
 *
 * @author yaomeng
 */
public class FaceSearchAPI {
    private static final String API_NAME = "v1.zoloz.facesearch.search";

    private OpenApiClient openApiClient;

    public FaceSearchAPI(OpenApiClient openApiClient) {
        this.openApiClient = openApiClient;
    }

    /**
     * Search user from group by face
     * @param request the face search request
     * @see FaceSearchRequest
     * @return the face search response
     * @see FaceSearchResponse
     */
    public FaceSearchResponse out(FaceSearchRequest request) {
        String response = openApiClient.callOpenApi(API_NAME, JSON.toJSONString(request));
        return JSON.parseObject(response, FaceSearchResponse.class);
    }
}
