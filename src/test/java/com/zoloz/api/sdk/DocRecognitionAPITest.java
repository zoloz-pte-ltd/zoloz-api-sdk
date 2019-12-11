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
import com.zoloz.api.sdk.model.DocRecognitionRequest;
import com.zoloz.api.sdk.model.DocRecognitionResponse;
import com.zoloz.api.sdk.api.DocRecognitionAPI;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author chenzc
 * @version $Id: DocRecognitionAPITest.java, v 0.1 2019年11月26日 20:46 chenzc Exp $
 */
public class DocRecognitionAPITest extends ApiBaseTest{

    private String passport="/images/doc/passport1.jpg";

    @Test
    public void testPassportPicture(){
        DocRecognitionRequest request=new DocRecognitionRequest();
        request.setBizId("test-biz-id");
        request.setDocType("00000001003");
        request.setFrontPageImage(getBase64ImageContent(passport));
        DocRecognitionAPI docRecognitionAPI=new DocRecognitionAPI(getClient());
        DocRecognitionResponse response=docRecognitionAPI.recognition(request);
        System.out.println("response :" + JSON.toJSONString(response));
        Assert.assertTrue(response.getOcrResult() != null && response.getOcrResult().size() > 0);
    }


}