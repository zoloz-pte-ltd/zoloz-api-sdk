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

package com.zoloz.example.ekyc.serviceimpl;

import com.zoloz.api.sdk.api.FaceCompareAPI;
import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.FaceCompareRequest;
import com.zoloz.api.sdk.model.FaceCompareResponse;
import com.zoloz.example.ekyc.autoconfig.ApiClientConfig;
import com.zoloz.example.ekyc.service.FaceCompareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *implement the faceCompare service interface
 *
 * @author youcha
 * @date 2020/8/18 10:37
 */

@Service
public class FaceCompareServiceImp implements FaceCompareService {
    private OpenApiClient openApiClient;

    @Autowired
    public FaceCompareServiceImp(ApiClientConfig apiClientConfig){
        this.openApiClient = apiClientConfig.client();
    }

    @Override
    public String[] getFaceMatchScore(String docImageContent, String faceImageContent) {
        FaceCompareRequest request = new FaceCompareRequest();
        request.setBizId("biz-id-12345");
        request.getFace1().setContent(docImageContent);
        request.getFace2().setContent(faceImageContent);
        FaceCompareAPI faceCompareAPI = new FaceCompareAPI(openApiClient);
        FaceCompareResponse response = faceCompareAPI.compare(request);
        String result[] = new String[2];
        result[0]=response.getScore().toString();
        result[1]=response.getSamePerson().toString();
        return result;
    }
}
