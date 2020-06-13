package com.zoloz.api.sdk.api;

import com.alibaba.fastjson.JSON;

import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.ConnectCheckResultRequest;
import com.zoloz.api.sdk.model.ConnectCheckResultResponse;
import com.zoloz.api.sdk.model.ConnectEnrollRequest;
import com.zoloz.api.sdk.model.ConnectEnrollResponse;
import com.zoloz.api.sdk.model.ConnectInitializeRequest;
import com.zoloz.api.sdk.model.ConnectInitializeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConnectAPI {

    private static final String API_NAME_INITIALIZE   = "v1.zoloz.connect.initialize";
    private static final String API_NAME_CHECK_RESULT = "v1.zoloz.connect.checkresult";
    private static final String API_NAME_ENROLL       = "v1.zoloz.connect.enroll";

    private OpenApiClient openApiClient;

    public ConnectInitializeResponse initialize(ConnectInitializeRequest request) {
        String response = openApiClient.callOpenApi(API_NAME_INITIALIZE, JSON.toJSONString(request));
        return JSON.parseObject(response, ConnectInitializeResponse.class);
    }

    public ConnectCheckResultResponse checkResult(ConnectCheckResultRequest request) {
        String response = openApiClient.callOpenApi(API_NAME_CHECK_RESULT, JSON.toJSONString(request));
        return JSON.parseObject(response, ConnectCheckResultResponse.class);
    }

    public ConnectEnrollResponse enroll(ConnectEnrollRequest request) {
        String response = openApiClient.callOpenApi(API_NAME_ENROLL, JSON.toJSONString(request));
        return JSON.parseObject(response, ConnectEnrollResponse.class);

    }


}
