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
        String response = openApiClient.callOpenApi(API_NAME, JSONUtil.toJSONString(request));
        return JSONUtil.parseObject(response, UserGroupInResponse.class);
    }
}
