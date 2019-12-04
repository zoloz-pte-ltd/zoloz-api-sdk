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

    private String passport="/images/doc/passport.jpg";
    private String bd1="/images/doc/bd1.jpg";
    private String bd2="/images/doc/bd2.jpeg";
    private String hk = "/images/doc/HKID1.jpg";
    private String newHk="/images/doc/newHKID.jpg";
    private String umid = "/images/doc/umid_xuan_new.jpg";
    private String tin = "/images/doc/tin1_xuan_p1.jpg";
    private String diverLicense = "/images/doc/driver1_xuan.jpg";
    private String philHealth = "/images/doc/health1_xuan.jpg";
    private String sss = "/images/doc/sss.jpeg";
    private String myKad = "/images/doc/mykad_xuan.jpeg";
    private String eKTP = "/images/doc/id-KTP.jpg";
    private String macao = "/images/doc/mo1.jpeg";

    private String hkblur = "/images/doc/hkblur.jpg";
    private String myKadNoFace = "/images/doc/myKad_no_face.jpg";
    private String bigImg = "/images/doc/big.jpg";


    //happyPath case begin

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

    //NID Bengal
    @Test
    public void testBDPicture(){
        DocRecognitionRequest request=new DocRecognitionRequest();
        request.setBizId("test-biz-id");
        request.setDocType("08800000001");
        request.setFrontPageImage(getBase64ImageContent(bd1));
        request.setBackPageImage(getBase64ImageContent(bd2));
        DocRecognitionAPI docRecognitionAPI=new DocRecognitionAPI(getClient());
        DocRecognitionResponse response=docRecognitionAPI.recognition(request);
        System.out.println("response :" + JSON.toJSONString(response));
        Assert.assertTrue(response.getOcrResult() != null && response.getOcrResult().size() > 0);
    }

    //HKID
    @Test
    public void testHKIDPicture(){
        DocRecognitionRequest request=new DocRecognitionRequest();
        request.setBizId("test-biz-id");
        request.setDocType("08520000001");
        request.setFrontPageImage(getBase64ImageContent(hk));
        DocRecognitionAPI docRecognitionAPI=new DocRecognitionAPI(getClient());
        DocRecognitionResponse response=docRecognitionAPI.recognition(request);
        System.out.println("response :" + JSON.toJSONString(response));
        Assert.assertTrue(response.getOcrResult() != null && response.getOcrResult().size() > 0);
    }


    //NewHKID
    @Test
    public void testNewHKIDPicture(){
        DocRecognitionRequest request=new DocRecognitionRequest();
        request.setBizId("test-biz-id");
        request.setDocType("08520000002");
        request.setFrontPageImage(getBase64ImageContent(newHk));
        DocRecognitionAPI docRecognitionAPI=new DocRecognitionAPI(getClient());
        DocRecognitionResponse response=docRecognitionAPI.recognition(request);
        System.out.println("response :" + JSON.toJSONString(response));
        Assert.assertTrue(response.getOcrResult() != null && response.getOcrResult().size() > 0);
    }


    //UMID
    @Test
    public void testUMIDPicture(){
        DocRecognitionRequest request=new DocRecognitionRequest();
        request.setBizId("test-biz-id");
        request.setDocType("00630000001");
        request.setFrontPageImage(getBase64ImageContent(umid));
        DocRecognitionAPI docRecognitionAPI=new DocRecognitionAPI(getClient());
        DocRecognitionResponse response=docRecognitionAPI.recognition(request);
        System.out.println("response :" + JSON.toJSONString(response));
        Assert.assertTrue(response.getOcrResult() != null && response.getOcrResult().size() > 0);
    }
    //TIN
    @Test
    public void testTINPicture() {
        DocRecognitionRequest request = new DocRecognitionRequest();
        request.setBizId("test-biz-id");
        request.setDocType("00630000002");
        request.setFrontPageImage(getBase64ImageContent(tin));
        DocRecognitionAPI docRecognitionAPI = new DocRecognitionAPI(getClient());
        DocRecognitionResponse response = docRecognitionAPI.recognition(request);
        System.out.println("response :" + JSON.toJSONString(response));
        Assert.assertTrue(response.getOcrResult() != null && response.getOcrResult().size() > 0);
    }

    // Driver’s License
    @Test
    public void testDriverLicensePicture() {
        DocRecognitionRequest request = new DocRecognitionRequest();
        request.setBizId("test-biz-id");
        request.setDocType("00630000004");
        request.setFrontPageImage(getBase64ImageContent(diverLicense));
        DocRecognitionAPI docRecognitionAPI = new DocRecognitionAPI(getClient());
        DocRecognitionResponse response = docRecognitionAPI.recognition(request);
        System.out.println("response :" + JSON.toJSONString(response));
        Assert.assertTrue(response.getOcrResult() != null && response.getOcrResult().size() > 0);
    }


    //PHILHEALTH
    @Test
    public void testPhilHealthPicture() {
        DocRecognitionRequest request = new DocRecognitionRequest();
        request.setBizId("test-biz-id");
        request.setDocType("00630000024");
        request.setFrontPageImage(getBase64ImageContent(philHealth));
        DocRecognitionAPI docRecognitionAPI = new DocRecognitionAPI(getClient());
        DocRecognitionResponse response = docRecognitionAPI.recognition(request);
        System.out.println("response :" + JSON.toJSONString(response));
        Assert.assertTrue(response.getOcrResult() != null && response.getOcrResult().size() > 0);
    }

    //SSS
    @Test
    public void testSSSPicture() {
        DocRecognitionRequest request = new DocRecognitionRequest();
        request.setBizId("test-biz-id");
        request.setDocType("00630000020");
        request.setFrontPageImage(getBase64ImageContent(sss));
        DocRecognitionAPI docRecognitionAPI = new DocRecognitionAPI(getClient());
        DocRecognitionResponse response = docRecognitionAPI.recognition(request);
        System.out.println("response :" + JSON.toJSONString(response));
        Assert.assertTrue(response.getOcrResult() != null && response.getOcrResult().size() > 0);
    }

    //MyKad
    @Test
    public void testMyKadPicture() {
        DocRecognitionRequest request = new DocRecognitionRequest();
        request.setBizId("test-biz-id");
        request.setDocType("00600000001");
        request.setFrontPageImage(getBase64ImageContent(myKad));
        DocRecognitionAPI docRecognitionAPI = new DocRecognitionAPI(getClient());
        DocRecognitionResponse response = docRecognitionAPI.recognition(request);
        System.out.println("response :" + JSON.toJSONString(response));
        Assert.assertTrue(response.getOcrResult() != null && response.getOcrResult().size() > 0);
    }

    //eKTP
    @Test
    public void testeKTPPicture() {
        DocRecognitionRequest request = new DocRecognitionRequest();
        request.setBizId("test-biz-id");
        request.setDocType("00620000001");
        request.setFrontPageImage(getBase64ImageContent(eKTP));
        DocRecognitionAPI docRecognitionAPI = new DocRecognitionAPI(getClient());
        DocRecognitionResponse response = docRecognitionAPI.recognition(request);
        System.out.println("response :" + JSON.toJSONString(response));
        Assert.assertTrue(response.getOcrResult() != null && response.getOcrResult().size() > 0);
    }
    // Macao
    @Test
    public void testMacaoIDPicture() {
        DocRecognitionRequest request = new DocRecognitionRequest();
        request.setBizId("test-biz-id");
        request.setDocType("08530000001");
        request.setFrontPageImage(getBase64ImageContent(macao));
        DocRecognitionAPI docRecognitionAPI = new DocRecognitionAPI(getClient());
        DocRecognitionResponse response = docRecognitionAPI.recognition(request);
        System.out.println("response :" + JSON.toJSONString(response));
        Assert.assertTrue(response.getOcrResult() != null && response.getOcrResult().size() > 0);
    }

    //happyPath case end


    //badPath case begin
    @Test
    public void testWrongDocType(){
        DocRecognitionRequest request=new DocRecognitionRequest();
        request.setBizId("test-biz-id");
        request.setDocType("00000001111");
        request.setFrontPageImage(getBase64ImageContent(umid));
        DocRecognitionAPI docRecognitionAPI=new DocRecognitionAPI(getClient());
        DocRecognitionResponse response=docRecognitionAPI.recognition(request);
        System.out.println("response :" + JSON.toJSONString(response));
        Assert.assertTrue(response.getResult().getResultCode().equals("INVALID_ARGUMENT"));
    }

    @Test
    public void testImageSizeLarge(){
        DocRecognitionRequest request=new DocRecognitionRequest();
        request.setBizId("test-biz-id");
        request.setDocType("00000001003");
        request.setFrontPageImage(getBase64ImageContent(bigImg));
        DocRecognitionAPI docRecognitionAPI=new DocRecognitionAPI(getClient());
        DocRecognitionResponse response=docRecognitionAPI.recognition(request);
        System.out.println("response :" + JSON.toJSONString(response));
        Assert.assertTrue(response.getResult().getResultCode().equals("INVALID_ARGUMENT"));
    }



    @Test
    public void testNoRequiredId(){
        DocRecognitionRequest request=new DocRecognitionRequest();
        request.setBizId("test-biz-id");
        request.setDocType("00000001003");
        request.setFrontPageImage(getBase64ImageContent(umid));
        DocRecognitionAPI docRecognitionAPI=new DocRecognitionAPI(getClient());
        DocRecognitionResponse response=docRecognitionAPI.recognition(request);
        System.out.println("response :" + JSON.toJSONString(response));
        Assert.assertTrue(response.getRecognitionResult().equals("N"));
        Assert.assertTrue(response.getRecognitionErrorCode().equals("NO_REQUIRED_ID"));
    }

    @Test
    public void testBlur(){
        // attach a blur image
        DocRecognitionRequest request=new DocRecognitionRequest();
        request.setBizId("test-biz-id");
        request.setDocType("08520000001");
        request.setFrontPageImage(getBase64ImageContent(hkblur));
        DocRecognitionAPI docRecognitionAPI=new DocRecognitionAPI(getClient());
        DocRecognitionResponse response=docRecognitionAPI.recognition(request);
        System.out.println("response :" + JSON.toJSONString(response));
        Assert.assertTrue(response.getOcrResult() != null && response.getOcrResult().size() > 0);
    }

    @Test
    public void testNoFaceDetected(){
        DocRecognitionRequest request=new DocRecognitionRequest();
        request.setBizId("test-biz-id");
        request.setDocType("00600000001");
        request.setFrontPageImage(getBase64ImageContent(myKadNoFace));
        DocRecognitionAPI docRecognitionAPI=new DocRecognitionAPI(getClient());
        DocRecognitionResponse response=docRecognitionAPI.recognition(request);
        System.out.println("response :" + JSON.toJSONString(response));
        Assert.assertTrue(response.getOcrResult() != null && response.getOcrResult().size() > 0);

    }

    public void testNotRealDoc(){
        // all not realdoc, but doc picture
    }

    public void testExposure(){

    }



    //badPath caes end














}