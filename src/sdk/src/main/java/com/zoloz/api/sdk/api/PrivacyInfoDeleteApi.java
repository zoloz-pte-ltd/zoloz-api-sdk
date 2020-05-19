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

import com.alibaba.fastjson.JSON;

import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.PrivacyInfoDeleteRequest;
import com.zoloz.api.sdk.model.PrivacyInfoDeleteResponse;

/**
 * PrivacyInfoDeleteApi
 * @author lzcc2003
 * @date 2020年5月19日 下午11:45:26
 */
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
