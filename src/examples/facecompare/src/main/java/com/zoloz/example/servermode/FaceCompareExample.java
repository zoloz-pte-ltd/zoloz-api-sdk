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
import org.apache.commons.cli.*;
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

        // create Options object
        Options options = new Options();
        options.addOption("c", true, "The client id");
        options.addOption("p", true, "The base64 content of the zoloz public key");
        options.addOption("k", true, "The path of the merchant private key");
        options.addOption("a", true, "The path of 1st face image to be compared");
        options.addOption("b", true, "The path of 2nd face image to be compared");
        options.addOption(new Option("e", true, "The endpoint of the zoloz service"){{
            setRequired(false);
        }});

        CommandLine cmd = null;
        try {
            cmd = new DefaultParser().parse(options, args);
        }
        catch (ParseException ex) {
            // automatically generate the help statement
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "facecompare " +
                    "-c <client_id> " +
                    "-p <zoloz_public_key_content> " +
                    "-k <merchant_private_key_path> " +
                    "-a <face1_image_path> " +
                    "-b <face2_image_path> " +
                    "[-e <zoloz_service_endpoint>]",
                    options );
            System.exit(-1);
        }

        // initialize OpenApiClient
        String clientId = cmd.getOptionValue("c");
        String zolozPublicKey = cmd.getOptionValue("p");
        String merchantPrivateKeyPath = cmd.getOptionValue("k");
        String merchantPrivateKey = KeyUtil.loadKeyContent(merchantPrivateKeyPath);
        String endpointUrl = cmd.getOptionValue("e", "https://sg-production-api.zoloz.com");

        // construct with signature and encryption by default
        OpenApiClient client = new OpenApiClient();
        client.setHostUrl(endpointUrl);
        client.setClientId(clientId);
        client.setMerchantPrivateKey(merchantPrivateKey);
        client.setOpenApiPublicKey(zolozPublicKey);
        client.setSigned(true);     // signature (of response) validation can be turned off
        //client.setEncrypted(false);  // encryption can be turned off

        // initialize FaceCompareApi
        FaceCompareAPI faceCompareApi = new FaceCompareAPI(client);

        // prepare api request
        String face1ImgPath = cmd.getOptionValue("a");
        String face2ImgPath = cmd.getOptionValue("b");

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
