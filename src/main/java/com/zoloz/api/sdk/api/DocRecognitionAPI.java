package com.zoloz.api.sdk.api;

import com.alibaba.fastjson.JSON;
import com.zoloz.api.sdk.model.DocRecognitionRequest;
import com.zoloz.api.sdk.model.DocRecognitionResponse;
import com.zoloz.api.sdk.client.OpenApiClient;

/**
 * chenzc
 */
public class DocRecognitionAPI {

    private static final String API_NAME = "v1.zoloz.idrecognition.recognize";

    private OpenApiClient openApiClient;

    public DocRecognitionAPI(OpenApiClient openApiClient) {
        this.openApiClient = openApiClient;
    }

    public DocRecognitionResponse recognition(DocRecognitionRequest request) {
        String response = openApiClient.callOpenApi(API_NAME, JSON.toJSONString(request));
        return JSON.parseObject(response, DocRecognitionResponse.class);
    }

}
