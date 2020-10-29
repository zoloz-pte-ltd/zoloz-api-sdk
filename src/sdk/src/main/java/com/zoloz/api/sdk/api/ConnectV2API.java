package com.zoloz.api.sdk.api;

import com.alibaba.fastjson.JSON;
import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.connectv2.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConnectV2API {

    private static final String API_NAME_CHECK_AVAILABLE = "v2.zoloz.connect.checkavailable";
    private static final String API_NAME_CHECK_VERIFY    = "v2.zoloz.connect.checkverify";
    private static final String API_NAME_CLOSE           = "v2.zoloz.connect.close";
    private static final String API_NAME_FACE_ENROLL     = "v2.zoloz.connect.faceenroll";
    private static final String API_NAME_IFAA_UPDATE     = "v2.zoloz.connect.ifaaupdate";
    private static final String API_NAME_INIT_REGISTER   = "v2.zoloz.connect.initregister";
    private static final String API_NAME_INIT_VERIFY     = "v2.zoloz.connect.initverify";
    private static final String API_NAME_CHECK_REGISTER  = "v2.zoloz.connect.checkRegister";

    private OpenApiClient openApiClient;

    public ConnectV2CheckAvailableResponse checkAvailable(ConnectV2CheckAvailableRequest request) {
        String response = openApiClient.callOpenApi(API_NAME_CHECK_AVAILABLE, JSON.toJSONString(request));
        return JSON.parseObject(response, ConnectV2CheckAvailableResponse.class);
    }

    public ConnectV2InitializeRegisterResponse initVerify(ConnectV2InitializeVerifyRequest request) {
        String response = openApiClient.callOpenApi(API_NAME_INIT_VERIFY, JSON.toJSONString(request));
        return JSON.parseObject(response, ConnectV2InitializeRegisterResponse.class);
    }

    public ConnectV2CheckVerifyResponse checkVerify(ConnectV2CheckVerifyRequest request) {
        String response = openApiClient.callOpenApi(API_NAME_CHECK_VERIFY, JSON.toJSONString(request));
        return JSON.parseObject(response, ConnectV2CheckVerifyResponse.class);

    }

    public ConnectV2UpdateIFAAResponse ifaaUpdate(ConnectV2UpdateIFAARequest request) {
        String response = openApiClient.callOpenApi(API_NAME_IFAA_UPDATE, JSON.toJSONString(request));
        return JSON.parseObject(response, ConnectV2UpdateIFAAResponse.class);
    }

    public ConnectV2InitializeRegisterResponse initRegister(ConnectV2InitializeRegisterRequest request) {
        String response = openApiClient.callOpenApi(API_NAME_INIT_REGISTER, JSON.toJSONString(request));
        return JSON.parseObject(response, ConnectV2InitializeRegisterResponse.class);
    }

    public ConnectV2CheckRegisterResponse checkRegister(ConnectV2CheckRegisterRequest request) {
        String response = openApiClient.callOpenApi(API_NAME_CHECK_REGISTER, JSON.toJSONString(request));
        return JSON.parseObject(response, ConnectV2CheckRegisterResponse.class);
    }

    public ConnectV2CloseResponse close(ConnectV2CloseRequest request) {
        String response = openApiClient.callOpenApi(API_NAME_CLOSE, JSON.toJSONString(request));
        return JSON.parseObject(response, ConnectV2CloseResponse.class);
    }

}
