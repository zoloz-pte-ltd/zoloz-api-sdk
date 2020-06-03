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

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import com.zoloz.api.sdk.api.DocRecognitionAPI;
import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.DocRecognitionRequest;
import com.zoloz.api.sdk.model.DocRecognitionResponse;
import com.zoloz.example.util.KeyUtil;
import lombok.SneakyThrows;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;

/**
 * Example of ID Recognize
 *
 * @Author: jushi
 * @Date: 2020-02-05 14:56
 */
public class IdRecognizeExample {

    public static void main(String[] args) {

        // create Options object
        Options options = new Options();
        options.addOption("c", true, "The client id");
        options.addOption("p", true, "The base64 content of the zoloz public key");
        options.addOption("k", true, "The path of the merchant private key");
        options.addOption("f", true, "The path of passport image to be recognized");
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
            formatter.printHelp( "idrecognize " +
                            "-c <client_id> " +
                            "-p <zoloz_public_key_content> " +
                            "-k <merchant_private_key_path> " +
                            "-f <passport_image_path> " +
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

        // initialize DocRecognitionAPI
        DocRecognitionAPI docRecognitionAPI = new DocRecognitionAPI(client);

        // prepare api request
        String imagePath = cmd.getOptionValue("f");

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
                    response.getSpoofResult().forEach((key, value) -> {
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
