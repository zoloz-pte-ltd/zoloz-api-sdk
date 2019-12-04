package com.zoloz.openapi.sdk;

import com.alibaba.fastjson.JSON;
import com.zoloz.openapi.sdk.client.OpenApiClient;
import com.zoloz.openapi.sdk.model.FaceCompareRequest;
import com.zoloz.openapi.sdk.model.FaceCompareResponse;
import com.zoloz.openapi.sdk.api.FaceCompareAPI;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;

/**
 * Created with IntelliJ IDEA.
 * User: Zhongyang MA
 * Date: 2019-11-18
 * Time: 20:24
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
        request.setBizId("test-biz-id");
        request.getFace1().setContent(getBase64ImageContent(face1Url));
        request.getFace1().setRect("112,234,455,89");
        request.getFace2().setContent(getBase64ImageContent(face2Url));
        request.getFace2().setRect("543,544,112,56");
        FaceCompareResponse response = faceCompareApi.compare(request);
        System.out.println(JSON.toJSONString(response));
    }

}
