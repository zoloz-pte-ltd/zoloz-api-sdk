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

package com.zoloz.api.sdk;

import com.alibaba.fastjson.JSON;
import com.zoloz.api.sdk.api.FaceCompareAPI;
import com.zoloz.api.sdk.model.FaceCompareRequest;
import com.zoloz.api.sdk.model.FaceCompareResponse;
import org.junit.Test;

/**
 * FaceCompareAPITest
 *
 * @Author: Zhongyang MA
 * @Date: 2019-12-11 21:13
 */
public class FaceCompareAPITest extends ApiBaseTest {

    private String face1Url = "/images/face/face1.jpg";
    private String face2Url = "/images/face/face2.jpg";

    @Test
    public void testFaceCompare() {
        // initialize FaceCompareApi
        FaceCompareAPI faceCompareApi = new FaceCompareAPI(getClient());

        // prepare request body
        FaceCompareRequest request = new FaceCompareRequest();
        request.setBizId("test-biz-id");  // for tracing purpose, it is recommended to use a global unique id
        request.getFace1().setContent(getBase64ImageContent(face1Url));
        request.getFace1().setRect("112,234,455,89");
        request.getFace2().setContent(getBase64ImageContent(face2Url));
        request.getFace2().setRect("543,544,112,56");
        FaceCompareResponse response = faceCompareApi.compare(request);
        System.out.println(JSON.toJSONString(response));
    }

}
