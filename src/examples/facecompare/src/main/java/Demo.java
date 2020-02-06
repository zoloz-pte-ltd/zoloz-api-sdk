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

import com.zoloz.api.sdk.api.FaceCompareAPI;
import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.FaceCompareRequest;
import com.zoloz.api.sdk.model.FaceCompareResponse;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Demo of Face Compare
 *
 * @Author: jushi
 * @Date: 2020-02-05 14:56
 */
public class Demo {

    public static void main(String[] args) {

        // initialize OpenApiClient
        String clientId = "<Client ID aqcuired in step 2>";
        String zolozPublicKey = "<ZOLOZ transaction public key acquired in step 2>";
        String merchantPrivateKeyPath = "<absolute path of the pem file downloaded in step 2>";
        String merchantPrivateKey = loadPrivateKey(merchantPrivateKeyPath);

        OpenApiClient client = new OpenApiClient();  // construct with signature and encryption by default
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
     * load private key from pem file
     * @param pemPath path of pem file
     * @return base64 encoded content of the private key
     * @throws IOException
     */
    @SneakyThrows(IOException.class)
    protected static String loadPrivateKey(String pemPath) {

        String content = FileUtils.readFileToString(new File(pemPath), "UTF-8");

        String[] lines = content.split("\n");
        String parsed = IntStream.range(1, lines.length - 1)
                .mapToObj(i -> lines[i])
                .collect(Collectors.joining(""));

        return parsed;
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
