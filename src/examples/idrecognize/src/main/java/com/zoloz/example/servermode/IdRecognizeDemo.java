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

import com.zoloz.api.sdk.api.DocRecognitionAPI;
import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.DocRecognitionRequest;
import com.zoloz.api.sdk.model.DocRecognitionResponse;
import com.zoloz.example.util.KeyUtil;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Demo of ID Recognize
 *
 * @Author: jushi
 * @Date: 2020-02-05 14:56
 */
public class IdRecognizeDemo {

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

        // initialize DocRecognitionAPI
        DocRecognitionAPI docRecognitionAPI = new DocRecognitionAPI(client);

        // prepare api request
        String imagePath = "/path/to/passport_image.jpg";

        DocRecognitionRequest request=new DocRecognitionRequest();
        request.setBizId("biz-id-12345");  // for tracing purpose, it is recommended to use a global unique id
        request.setDocType("00000001003");
        request.setFrontPageImage(getBase64ImageContent(imagePath));

        // call api
        DocRecognitionResponse response = docRecognitionAPI.recognition(request);
        if ("S".equals(response.getResult().getResultStatus())) {
            if ("Y".equals(response.getRecognitionResult())) {
                System.out.println("ID detected.\n");

                if (response.getSpoofResult() != null && !response.getSpoofResult().isEmpty()) {
                    System.out.println("Spoofing Detection:");
                    response.getOcrResult().forEach((key, value) -> {
                        System.out.println(String.format(" -%s: %s", key, value));
                    });

                }

                System.out.println("OCR Result:");
                response.getOcrResult().forEach((key, value) -> {
                    System.out.println(String.format(" -%s: %s", key, value));
                });
                System.out.println(String.format(
                        "[Error] %s: %s",
                        response.getResult().getResultCode(),
                        response.getResult().getResultMessage()
                ));
            }
            else {
                System.out.println(String.format(
                        "Cannot recognize image: %s",
                        response.getRecognitionErrorCode()
                ));
            }
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
