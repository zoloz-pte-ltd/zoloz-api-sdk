/*
 * Copyright (c) 2019 ZOLOZ PTE.LTD.
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

import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.RealDocParseRequest;
import com.zoloz.api.sdk.model.RealDocParseResponse;
import com.zoloz.api.sdk.model.RealDocAsyncUploadRequest;
import com.zoloz.api.sdk.model.RealDocAsyncUploadResponse;
import com.zoloz.api.sdk.model.RealDocAsyncStatusRequest;
import com.zoloz.api.sdk.model.RealDocAsyncStatusResponse;
import com.zoloz.api.sdk.model.RealDocAsyncParseResultRequest;
import com.zoloz.api.sdk.model.RealDocAsyncParseResultResponse;
import com.zoloz.api.sdk.model.RealDocAsyncFraudCheckResultRequest;
import com.zoloz.api.sdk.model.RealDocAsyncFraudCheckResultResponse;
import com.zoloz.api.sdk.util.JSONUtil;

/**
 * RealDocAPI
 * Real Document API
 *
 * @author yirong
 */
public class RealDocAPI {
    /**
     * parse api name
     */
    private static final String PARSE_API_NAME = "v1.zoloz.realdoc.parse";

    /**
     * async upload api name
     */
    private static final String ASYNC_UPLOAD_API_NAME = "v1.zoloz.realdoc.async.upload";

    /**
     * async status api name
     */
    private static final String ASYNC_STATUS_API_NAME = "v1.zoloz.realdoc.async.status";

    /**
     * async document parse result api name
     */
    private static final String ASYNC_DOCUMENT_PARSE_RESULT_API_NAME = "v1.zoloz.realdoc.async.documentparse.result";

    /**
     * async fraud check result api name
     */
    private static final String ASYNC_FRAUD_CHECK_RESULT_API_NAME = "v1.zoloz.realdoc.async.fraudcheck.result";

    private OpenApiClient openApiClient;

    public RealDocAPI(OpenApiClient openApiClient) {
        this.openApiClient = openApiClient;
    }

    /**
     * Document parsing method
     * @param request document parsing request
     * @see RealDocParseRequest
     * @return document parsing response
     * @see RealDocParseResponse
     */
    public RealDocParseResponse parse(RealDocParseRequest request) {
        String response = openApiClient.callOpenApi(PARSE_API_NAME, JSONUtil.toJSONString(request));
        return JSONUtil.parseObject(response, RealDocParseResponse.class);
    }

    /**
     * Asynchronous document upload method
     * @param request async upload request
     * @see RealDocAsyncUploadRequest
     * @return async upload response
     * @see RealDocAsyncUploadResponse
     */
    public RealDocAsyncUploadResponse asyncUpload(RealDocAsyncUploadRequest request) {
        String response = openApiClient.callOpenApi(ASYNC_UPLOAD_API_NAME, JSONUtil.toJSONString(request));
        return JSONUtil.parseObject(response, RealDocAsyncUploadResponse.class);
    }

    /**
     * Task status query method
     * @param request async status request
     * @see RealDocAsyncStatusRequest
     * @return async status response
     * @see RealDocAsyncStatusResponse
     */
    public RealDocAsyncStatusResponse asyncStatus(RealDocAsyncStatusRequest request) {
        String response = openApiClient.callOpenApi(ASYNC_STATUS_API_NAME, JSONUtil.toJSONString(request));
        return JSONUtil.parseObject(response, RealDocAsyncStatusResponse.class);
    }

    /**
     * Document parsing async result query method
     * @param request async parse result request
     * @see RealDocAsyncParseResultRequest
     * @return async parse result response
     * @see RealDocAsyncParseResultResponse
     */
    public RealDocAsyncParseResultResponse asyncParseResult(RealDocAsyncParseResultRequest request) {
        String response = openApiClient.callOpenApi(ASYNC_DOCUMENT_PARSE_RESULT_API_NAME, JSONUtil.toJSONString(request));
        return JSONUtil.parseObject(response, RealDocAsyncParseResultResponse.class);
    }

    /**
     * Document fraud check async result query method
     * @param request async fraud check result request
     * @see RealDocAsyncFraudCheckResultRequest
     * @return async fraud check result response
     * @see RealDocAsyncFraudCheckResultResponse
     */
    public RealDocAsyncFraudCheckResultResponse asyncFraudCheckResult(RealDocAsyncFraudCheckResultRequest request) {
        String response = openApiClient.callOpenApi(ASYNC_FRAUD_CHECK_RESULT_API_NAME, JSONUtil.toJSONString(request));
        return JSONUtil.parseObject(response, RealDocAsyncFraudCheckResultResponse.class);
    }

}
