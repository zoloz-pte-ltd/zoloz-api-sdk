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

package com.zoloz.example.servermode;

import com.zoloz.api.sdk.api.FaceCompareAPI;
import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.FaceCompareRequest;
import com.zoloz.api.sdk.model.FaceCompareResponse;

import com.zoloz.example.util.KeyUtil;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

/**
 * Example of Face Compare
 *
 * @Author: jushi
 * @Date: 2020-02-05 14:56
 */
public class FaceCompareExample {

    public static void main(String[] args) {

        // initialize OpenApiClient
        String clientId = "<your client ID>";
        String zolozPublicKey = "<ZOLOZ transaction public key>";
        String merchantPrivateKeyPath = "<absolute path of your private key file>";
        String merchantPrivateKey = KeyUtil.loadKeyContent(merchantPrivateKeyPath);

        // construct with signature and encryption by default
        OpenApiClient client = new OpenApiClient();
        client.setHostUrl("https://sg-production-api.zoloz.com");
        client.setClientId(clientId);
        client.setMerchantPrivateKey(merchantPrivateKey);
        client.setOpenApiPublicKey(zolozPublicKey);
        //client.setSigned(false);     // signature (of response) validation can be turned off
        //client.setEncrypted(false);  // encryption can be turned off

        // initialize FaceCompareApi
        FaceCompareAPI faceCompareApi = new FaceCompareAPI(client);

        // prepare api request
        String face1ImgPath = "/path/to/face1.jpg";
        String face2ImgPath = "/path/to/face2.jpg";

        FaceCompareRequest request = new FaceCompareRequest();
        request.setBizId("biz-id-12345");  // for tracing purpose, it is recommended to use a global unique id
        request.getFace1().setContent(getBase64ImageContent(face1ImgPath));
        request.getFace2().setContent(getBase64ImageContent(face2ImgPath));

        // call api
        FaceCompareResponse response = faceCompareApi.compare(request);

        if ("S".equals(response.getResult().getResultStatus())) {
            System.out.println(String.format(
                    "Two faces are from %s, the similarity score is %2f",
                    response.getSamePerson() ? "same person" : "different persons",
                    response.getScore()
            ));
        }
        else {
            System.out.println(String.format(
                    "[Error] %s: %s",
                    response.getResult().getResultCode(),
                    response.getResult().getResultMessage()
            ));
        }
    }

    /**
     * get content of the image file
     * @param imagePath path of the image file
     * @return base64 encoded content of the image file
     * @throws IOException
     */
    @SneakyThrows(IOException.class)
    protected static String getBase64ImageContent(String imagePath) {
        byte[] content = FileUtils.readFileToByteArray(new File(imagePath));
        return Base64.getEncoder().encodeToString(content);
    }
}
