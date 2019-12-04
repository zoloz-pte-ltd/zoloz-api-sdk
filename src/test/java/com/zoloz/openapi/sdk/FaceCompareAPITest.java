package com.zoloz.openapi.sdk;

import com.alibaba.fastjson.JSON;
import com.zoloz.openapi.sdk.model.FaceCompareRequest;
import com.zoloz.openapi.sdk.model.FaceCompareResponse;
import com.zoloz.openapi.sdk.api.FaceCompareAPI;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Zhongyang MA
 * Date: 2019-11-18
 * Time: 20:24
 */
public class FaceCompareAPITest extends ApiBaseTest {

    private String face1Url = "/images/face/alive_moxi.jpg";
    private String face1RealFace = "/images/face/ladyGaga.jpg";
    private String face2Url = "/images/face/alive_moxi.jpg";
    private String face2ScreenRetaken = "/images/face/alive_attack.jpg";
    private String face2DifferentPerson = "/images/face/shilin.jpg";
    private String face2Dog = "/images/face/dog.jpg";
    private String face2Desk = "/images/face/table.jpg";
    private String face2Big = "/images/face/taylorSwift_highQuality.jpg";
    private String face2Void = "/images/face/void.jpg";
    private String face2BlackNWhite = "/images/face/taylorSwift_bNw.jpg";
    private String face2FakeFace = "/images/face/ladyGaga_mask.jpg";
    private String face2HalfWEye = "/images/face/shilin_half_eye.jpg";
    private String face2HalfWOEye = "/images/face/shilin_half.jpg";

    // compare 2 same people
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

    // compare a person vs screen retaken image

    @Test
    public void testFaceRetakenCompare() {
        // initialize FaceCompareApi
        FaceCompareAPI faceCompareApi = new FaceCompareAPI(getClient());
        // prepare request body
        FaceCompareRequest request = new FaceCompareRequest();
        request.setBizId("test-biz-id");
        request.getFace1().setContent(getBase64ImageContent(face1Url));
        request.getFace1().setRect("112,234,455,89");
        request.getFace2().setContent(getBase64ImageContent(face2ScreenRetaken));
        request.getFace2().setRect("543,544,112,56");
        FaceCompareResponse response = faceCompareApi.compare(request);
        System.out.println(JSON.toJSONString(response));
    }

    // compare 2 different people
    @Test
    public void testFaceDifferentCompare() {
        // initialize FaceCompareApi
        FaceCompareAPI faceCompareApi = new FaceCompareAPI(getClient());
        // prepare request body
        FaceCompareRequest request = new FaceCompareRequest();
        request.setBizId("test-biz-id");
        request.getFace1().setContent(getBase64ImageContent(face1Url));
        request.getFace1().setRect("112,234,455,89");
        request.getFace2().setContent(getBase64ImageContent(face2DifferentPerson));
        request.getFace2().setRect("543,544,112,56");
        FaceCompareResponse response = faceCompareApi.compare(request);
        System.out.println(JSON.toJSONString(response));
    }

    // compare a person vs a dog
    @Test
    public void testFaceVSDogCompare() {
        // initialize FaceCompareApi
        FaceCompareAPI faceCompareApi = new FaceCompareAPI(getClient());
        // prepare request body
        FaceCompareRequest request = new FaceCompareRequest();
        request.setBizId("test-biz-id");
        request.getFace1().setContent(getBase64ImageContent(face1Url));
        request.getFace1().setRect("112,234,455,89");
        request.getFace2().setContent(getBase64ImageContent(face2Dog));
        request.getFace2().setRect("543,544,112,56");
        FaceCompareResponse response = faceCompareApi.compare(request);
        System.out.println(JSON.toJSONString(response));
    }
    // compare a person vs a desk
    @Test
    public void testFaceVSDeskCompare() {
        // initialize FaceCompareApi
        FaceCompareAPI faceCompareApi = new FaceCompareAPI(getClient());
        // prepare request body
        FaceCompareRequest request = new FaceCompareRequest();
        request.setBizId("test-biz-id");
        request.getFace1().setContent(getBase64ImageContent(face1Url));
        request.getFace1().setRect("112,234,455,89");
        request.getFace2().setContent(getBase64ImageContent(face2Desk));
        request.getFace2().setRect("543,544,112,56");
        FaceCompareResponse response = faceCompareApi.compare(request);
        System.out.println(JSON.toJSONString(response));
    }
    // image size exceeds the upper limit 200M
    @Test
    public void testFaceBigCompare() {
        // initialize FaceCompareApi
        FaceCompareAPI faceCompareApi = new FaceCompareAPI(getClient());
        // prepare request body
        FaceCompareRequest request = new FaceCompareRequest();
        request.setBizId("test-biz-id");
        request.getFace1().setContent(getBase64ImageContent(face1Url));
        request.getFace1().setRect("112,234,455,89");
        request.getFace2().setContent(getBase64ImageContent(face2Big));
        request.getFace2().setRect("543,544,112,56");
        FaceCompareResponse response = faceCompareApi.compare(request);
        System.out.println(JSON.toJSONString(response));
    }
    // compare a person vs a void image
    @Test
    public void testCompareVoid() {
        // initialize FaceCompareApi
        FaceCompareAPI faceCompareApi = new FaceCompareAPI(getClient());
        // prepare request body
        FaceCompareRequest request = new FaceCompareRequest();
        request.setBizId("test-biz-id");
        request.getFace1().setContent(getBase64ImageContent(face1Url));
        request.getFace1().setRect("112,234,455,89");
        request.getFace2().setContent(getBase64ImageContent(face2Void));
        request.getFace2().setRect("543,544,112,56");
        FaceCompareResponse response = faceCompareApi.compare(request);
        System.out.println(JSON.toJSONString(response));
    }
    // compare a person image with color vs black and white imge
    @Test
    public void testCompareBlackNWhite() {
        // initialize FaceCompareApi
        FaceCompareAPI faceCompareApi = new FaceCompareAPI(getClient());
        // prepare request body
        FaceCompareRequest request = new FaceCompareRequest();
        request.setBizId("test-biz-id");
        request.getFace1().setContent(getBase64ImageContent(face1Url));
        request.getFace1().setRect("112,234,455,89");
        request.getFace2().setContent(getBase64ImageContent(face2BlackNWhite));
        request.getFace2().setRect("543,544,112,56");
        FaceCompareResponse response = faceCompareApi.compare(request);
        System.out.println(JSON.toJSONString(response));
    }
    // compare twins( not impletement yet)

    // compare a person image vs a fake face image
    @Test
    public void testCompareFake() {
        // initialize FaceCompareApi
        FaceCompareAPI faceCompareApi = new FaceCompareAPI(getClient());
        // prepare request body
        FaceCompareRequest request = new FaceCompareRequest();
        request.setBizId("test-biz-id");
        request.getFace1().setContent(getBase64ImageContent(face1RealFace));
        request.getFace1().setRect("112,234,455,89");
        request.getFace2().setContent(getBase64ImageContent(face2FakeFace));
        request.getFace2().setRect("543,544,112,56");
        FaceCompareResponse response = faceCompareApi.compare(request);
        System.out.println(JSON.toJSONString(response));
    }

    // compare a complete image vs a half image with eyes
    @Test
    public void testCompareHalfEye() {
        // initialize FaceCompareApi
        FaceCompareAPI faceCompareApi = new FaceCompareAPI(getClient());
        // prepare request body
        FaceCompareRequest request = new FaceCompareRequest();
        request.setBizId("test-biz-id");
        request.getFace1().setContent(getBase64ImageContent(face2DifferentPerson));
        request.getFace1().setRect("112,234,455,89");
        request.getFace2().setContent(getBase64ImageContent(face2HalfWEye));
        request.getFace2().setRect("543,544,112,56");
        FaceCompareResponse response = faceCompareApi.compare(request);
        System.out.println(JSON.toJSONString(response));
    }

    // compare a complete image vs a half image without eyse
    @Test
    public void testCompareHalf() {
        // initialize FaceCompareApi
        FaceCompareAPI faceCompareApi = new FaceCompareAPI(getClient());
        // prepare request body
        FaceCompareRequest request = new FaceCompareRequest();
        request.setBizId("test-biz-id");
        request.getFace1().setContent(getBase64ImageContent(face2DifferentPerson));
        request.getFace1().setRect("112,234,455,89");
        request.getFace2().setContent(getBase64ImageContent(face2HalfWOEye));
        request.getFace2().setRect("543,544,112,56");
        FaceCompareResponse response = faceCompareApi.compare(request);
        System.out.println(JSON.toJSONString(response));
    }

}
