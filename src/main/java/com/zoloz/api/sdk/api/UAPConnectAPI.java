/*
 * Copyright (c) 2020 ZOLOZ PTE.LTD.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.zoloz.api.sdk.api;

import com.zoloz.api.sdk.util.JSONUtil;
import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.uapconnect.UAPConnectCheckAvailableRequest;
import com.zoloz.api.sdk.model.uapconnect.UAPConnectCheckAvailableResponse;
import com.zoloz.api.sdk.model.uapconnect.UAPConnectCheckRegisterRequest;
import com.zoloz.api.sdk.model.uapconnect.UAPConnectCheckRegisterResponse;
import com.zoloz.api.sdk.model.uapconnect.UAPConnectCheckVerifyRequest;
import com.zoloz.api.sdk.model.uapconnect.UAPConnectCheckVerifyResponse;
import com.zoloz.api.sdk.model.uapconnect.UAPConnectCloseRequest;
import com.zoloz.api.sdk.model.uapconnect.UAPConnectCloseResponse;
import com.zoloz.api.sdk.model.uapconnect.UAPConnectFaceEnrollRequest;
import com.zoloz.api.sdk.model.uapconnect.UAPConnectFaceEnrollResponse;
import com.zoloz.api.sdk.model.uapconnect.UAPConnectInitializeRegisterRequest;
import com.zoloz.api.sdk.model.uapconnect.UAPConnectInitializeRegisterResponse;
import com.zoloz.api.sdk.model.uapconnect.UAPConnectInitializeVerifyRequest;
import com.zoloz.api.sdk.model.uapconnect.UAPConnectInitializeVerifyResponse;
import com.zoloz.api.sdk.model.uapconnect.UAPConnectUpdateIFAARequest;
import com.zoloz.api.sdk.model.uapconnect.UAPConnectUpdateIFAAResponse;
import com.zoloz.api.sdk.model.uapconnect.UAPConnectVerifyRequest;
import com.zoloz.api.sdk.model.uapconnect.UAPConnectVerifyResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <h1>UAPConnectAPI</h1>
 * <p>
 * Biometrics continue to overtake passwords and other obsolete means of authentication. Mobile app authentication, enabled by Zoloz's
 * connect 2.0 platform, builds on the prevalence of high-quality biometric sensors in mobile devices to deliver fast, secure ways for your
 * organization to onboard users, combat fraud, and increase time spent on your native mobile app.
 * <p>
 *
 * @author: the
 */
@Data
@AllArgsConstructor
public class UAPConnectAPI {

    private static final String API_NAME_CHECK_AVAILABLE = "v1.zoloz.uapconnect.checkavailable";
    private static final String API_NAME_VERIFY          = "v1.zoloz.uapconnect.verify";
    private static final String API_NAME_CHECK_VERIFY    = "v1.zoloz.uapconnect.checkverify";
    private static final String API_NAME_CLOSE           = "v1.zoloz.uapconnect.close";
    private static final String API_NAME_FACE_ENROLL     = "v1.zoloz.uapconnect.faceenroll";
    private static final String API_NAME_IFAA_UPDATE     = "v1.zoloz.uapconnect.ifaaupdate";
    private static final String API_NAME_INIT_REGISTER   = "v1.zoloz.uapconnect.initregister";
    private static final String API_NAME_INIT_VERIFY     = "v1.zoloz.uapconnect.initverify";
    private static final String API_NAME_CHECK_REGISTER  = "v1.zoloz.uapconnect.checkregister";

    private OpenApiClient openApiClient;

    /**
     * Represents an entry point for checking whether each biometric authentication of Zoloz can be used, including checking whether the
     * operating system version support, whether hardware support, and whether the authentication template has been registered.
     *
     * @param request request bean
     * @return result bean
     */
    public UAPConnectCheckAvailableResponse checkAvailable(UAPConnectCheckAvailableRequest request) {
        String response = openApiClient.callOpenApi(API_NAME_CHECK_AVAILABLE, JSONUtil.toJSONString(request));
        return JSONUtil.parseObject(response, UAPConnectCheckAvailableResponse.class);
    }

    /**
     * Enroll API is used for enroll one face image related to one user for subsequent usage of connect.
     *
     * @param request request bean
     * @return result bean
     */
    public UAPConnectFaceEnrollResponse faceEnroll(UAPConnectFaceEnrollRequest request) {
        String response = openApiClient.callOpenApi(API_NAME_FACE_ENROLL, JSONUtil.toJSONString(request));
        return JSONUtil.parseObject(response, UAPConnectFaceEnrollResponse.class);
    }

    /**
     * Initial Verify API  is the first API you need to call from your server side, when a user starts the authentication process. It will
     * initialize an authentication process in Zoloz with a unique transaction ID, which shall be used in all the subsequent interactions
     * with Zoloz for the same application.
     *
     * @param request request bean
     * @return result bean
     */
    public UAPConnectInitializeVerifyResponse initVerify(UAPConnectInitializeVerifyRequest request) {
        String response = openApiClient.callOpenApi(API_NAME_INIT_VERIFY, JSONUtil.toJSONString(request));
        return JSONUtil.parseObject(response, UAPConnectInitializeVerifyResponse.class);
    }

    /**
     * Verify API is designed for your server side to verify the request. Check Verify API will send back to your sever side with
     * authentication status suggested by Zoloz and other corresponding results.
     *
     * @param request request bean
     * @return result bean
     */
    public UAPConnectVerifyResponse verify(UAPConnectVerifyRequest request) {
        String response = openApiClient.callOpenApi(API_NAME_VERIFY, JSONUtil.toJSONString(request));
        return JSONUtil.parseObject(response, UAPConnectVerifyResponse.class);
    }

    /**
     * This is for your server side to retrieve the verification result no matter the verification process is completed successfully or
     * not.
     *
     * @param request request bean
     * @return result bean
     */
    public UAPConnectCheckVerifyResponse checkVerify(UAPConnectCheckVerifyRequest request) {
        String response = openApiClient.callOpenApi(API_NAME_CHECK_VERIFY, JSONUtil.toJSONString(request));
        return JSONUtil.parseObject(response, UAPConnectCheckVerifyResponse.class);
    }

    /**
     * This API changes the template of a specific user-registered ifaa product stored in persistent storage. This API just changes the
     * user-registered biometric authentication information to bear a new template in the persistent storage so it can be used for biometric
     * authentication.
     *
     * @param request request bean
     * @return result bean
     */
    public UAPConnectUpdateIFAAResponse ifaaUpdate(UAPConnectUpdateIFAARequest request) {
        String response = openApiClient.callOpenApi(API_NAME_IFAA_UPDATE, JSONUtil.toJSONString(request));
        return JSONUtil.parseObject(response, UAPConnectUpdateIFAAResponse.class);
    }

    /**
     * Step 1 for biometric authentication registration. This API generates and returns a unique transaction ID and client sdk configuration
     * to the caller. This data is needed to evoke client registration.
     *
     * @param request request bean
     * @return result bean
     */
    public UAPConnectInitializeRegisterResponse initRegister(UAPConnectInitializeRegisterRequest request) {
        String response = openApiClient.callOpenApi(API_NAME_INIT_REGISTER, JSONUtil.toJSONString(request));
        return JSONUtil.parseObject(response, UAPConnectInitializeRegisterResponse.class);
    }

    /**
     * Step 2 for biometric authentication registration. After receiving the response from the client,  this API allows the caller to
     * confirm registration via transactionId.
     *
     * @param request request bean
     * @return result bean
     */
    public UAPConnectCheckRegisterResponse checkRegister(UAPConnectCheckRegisterRequest request) {
        String response = openApiClient.callOpenApi(API_NAME_CHECK_REGISTER, JSONUtil.toJSONString(request));
        return JSONUtil.parseObject(response, UAPConnectCheckRegisterResponse.class);
    }

    /**
     * The process of de-registering an already-registered biometric authentication.
     *
     * @param request request bean
     * @return result bean
     */
    public UAPConnectCloseResponse close(UAPConnectCloseRequest request) {
        String response = openApiClient.callOpenApi(API_NAME_CLOSE, JSONUtil.toJSONString(request));
        return JSONUtil.parseObject(response, UAPConnectCloseResponse.class);
    }

}
