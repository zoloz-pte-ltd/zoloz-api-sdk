package com.zoloz.openapi.sdk.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zoloz.openapi.sdk.client.OpenApiClient;
import com.zoloz.openapi.sdk.model.FaceCompareRequest;
import com.zoloz.openapi.sdk.model.FaceCompareResponse;
import com.zoloz.openapi.sdk.model.OpenApiCommonResult;

/**
 * Created with IntelliJ IDEA.
 * User: Zhongyang MA
 * Date: 2019-11-19
 * Time: 18:02
 */
public class FaceCompareAPI {

    private static final String API_NAME = "v1.zoloz.facecompare.compare";

    private OpenApiClient openApiClient;

    public FaceCompareAPI(OpenApiClient openApiClient) {
        this.openApiClient = openApiClient;
    }

    public FaceCompareResponse compare(FaceCompareRequest request) {
        String response = openApiClient.callOpenApi(API_NAME, JSON.toJSONString(request));
        return JSON.parseObject(response, FaceCompareResponse.class);
    }

}
