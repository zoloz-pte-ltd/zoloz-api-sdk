/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.zoloz.openapi.sdk;

import com.alibaba.fastjson.JSON;
import com.zoloz.openapi.sdk.api.DocRecognitionAPI;
import com.zoloz.openapi.sdk.model.DocRecognitionRequest;
import com.zoloz.openapi.sdk.model.DocRecognitionResponse;
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