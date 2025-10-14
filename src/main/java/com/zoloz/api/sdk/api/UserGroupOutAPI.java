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
import com.zoloz.api.sdk.model.UserGroupOutRequest;
import com.zoloz.api.sdk.model.UserGroupOutResponse;

/**
 * Create by yaomeng on 2020-02-05 11:49
 *
 * @author yaomeng
 */
public class UserGroupOutAPI {
    private static final String API_NAME = "v1.zoloz.usergroupout.out";

    private OpenApiClient openApiClient;

    public UserGroupOutAPI(OpenApiClient openApiClient) {
        this.openApiClient = openApiClient;
    }

    /**
     * Delete user face from group
     * @param request the face group out request
     * @see UserGroupOutRequest
     * @return the face group out response
     * @see UserGroupOutResponse
     */
    public UserGroupOutResponse out(UserGroupOutRequest request) {
        String response = openApiClient.callOpenApi(API_NAME, JSONUtil.toJSONString(request));
        return JSONUtil.parseObject(response, UserGroupOutResponse.class);
    }
}
