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

import com.zoloz.api.sdk.api.DocRecognitionAPI;
import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.DocRecognitionRequest;
import com.zoloz.api.sdk.model.DocRecognitionResponse;
import com.zoloz.example.ekyc.autoconfig.ApiClientConfig;
import com.zoloz.example.ekyc.service.OCRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * implement the OCR service interface
 *
 * @author youcha
 * @date 2020/8/18 10:39
 */

@Service
public class OCRServiceImp implements OCRService {
    private OpenApiClient openApiClient;

    @Autowired
    public OCRServiceImp(ApiClientConfig apiClientConfig) {
        this.openApiClient = apiClientConfig.client();
    }

    @Override
    public String[] getOcrResult(String docType, String docImageContent) {
        DocRecognitionRequest request = new DocRecognitionRequest();
        request.setBizId("biz-id-12345");  // for tracing purpose, it is recommended to use a global unique id
        request.setDocType(docType);
        request.setFrontPageImage(docImageContent);
        DocRecognitionAPI docRecognitionAPI = new DocRecognitionAPI(openApiClient);
        DocRecognitionResponse response =docRecognitionAPI.recognition(request);
        String ocrResult="";
        ocrResult+="Name: ";
        ocrResult+=response.getOcrResult().get("NAME_CN")+"    ";
        ocrResult+="Birthday： ";
        ocrResult+=response.getOcrResult().get("DATE_OF_BIRTH")+"    ";
        ocrResult+="ID Number: ";
        ocrResult+=response.getOcrResult().get("ID_NUMBER");
        String result[] = new String[2];
        result[0]=ocrResult;
        result[1]=response.getRecognitionResult();
        return result;
    }
}
