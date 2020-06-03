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
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

/**
 * Example of ID Recognize
 *
 * @Author: jushi
 * @Date: 2020-02-05 14:56
 */
public class IdRecognizeExample {

    public static void main(String[] args) {

        //// create Options object
        //Options options = new Options();
        //options.addOption("c", true, "The client id");
        //options.addOption("p", true, "The base64 content of the zoloz public key");
        //options.addOption("k", true, "The path of the merchant private key");
        //options.addOption("f", true, "The path of passport image to be recognized");
        //options.addOption(new Option("e", true, "The endpoint of the zoloz service"){{
        //    setRequired(false);
        //}});
        //
        //CommandLine cmd = null;
        //try {
        //    cmd = new DefaultParser().parse(options, args);
        //}
        //catch (ParseException ex) {
        //    // automatically generate the help statement
        //    HelpFormatter formatter = new HelpFormatter();
        //    formatter.printHelp( "idrecognize " +
        //                    "-c <client_id> " +
        //                    "-p <zoloz_public_key_content> " +
        //                    "-k <merchant_private_key_path> " +
        //                    "-f <passport_image_path> " +
        //                    "[-e <zoloz_service_endpoint>]",
        //            options );
        //    System.exit(-1);
        //}

        //// initialize OpenApiClient
        //String clientId = cmd.getOptionValue("c");
        //String zolozPublicKey = cmd.getOptionValue("p");
        //String merchantPrivateKeyPath = cmd.getOptionValue("k");
        //String merchantPrivateKey = KeyUtil.loadKeyContent(merchantPrivateKeyPath);
        //String endpointUrl = cmd.getOptionValue("e", "https://sg-production-api.zoloz.com");


        // initialize OpenApiClient
        String clientId = "2188418269881563";
        String zolozPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAid3Qr831XYq55LdO4NBI9l1N2j8h8NaPFwISl2vKlH6ftvTIdbPQ4/Zmb2Bjm7CP986tPZq92oWnzkLkVy0Tmkc/YPRF+kMdP/EUMKAb/nKjTUzXwQSNADG0dSyNAojiyaxUneNrG7hONnuQOANfgKV0qGkEYOpr/5SnQMvvnsV4E3JTkvfGfYTc6Y1vr4c/PEHcz5I/uIyv8sV52aLzxdOf8G7K219llS6wRWYa02H9xIl3d6nnRx4VJTvA9eZVemunuG4OHzTdhZ+RdMIDpMCeuhb2pGlcy/uP8UPr4aiQ0tkxfwD8FgcmqwCtHmEZWESMCPiPtSEV/ztyM+vQNQIDAQAB";
        //String merchantPrivateKeyPath = cmd.getOptionValue("k");
        String merchantPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDdcEJ5jwevZXSpBRrkZzDh2BlEheJ8sww7KAE6EtPA4QyVdsuF6RdNVbTYOVqlC8AYtucFzBDFk6f2v0TAzRT1Nt/VjB2v6VDcaroLQrym/2Yu0K5UrHL8VGJypf6m6WHzPraAckh45+M0a/Ih4fphLAvHvD/gjOKWotg+jTce2E1Er08YtWUI3gSpsd1rmLjlydStWA/mEcdA/B0hLf5WZhFlWg651pjHtXI5767TNRP0qhaqUnydp+NBiuriU099zSILoMg8rpSP8KuDCCu55UHSPOjj3kX70jLqrmhkcQAZfCuGBIasGFlq0TYjpV+JNY0Gvp1iCjVRSazzTUcPAgMBAAECggEBAMbns45lP835IBVClKldV7dF2UyHxq2kVvQ8Nv3nM3YpsJgBIUej3F6HAe7HdfiS2XZZtKKsbtRKkbE+lY9pp82sANtdBL2iohObeonq5HJNL4gVwsIkNRUa52N8X4WmVWmdh2lRPm/8O0Y3QEPXLIRkgCBlpCsb4dzVYdCuIDj+/fz9/CV2sdL8eDwjO6VJgxy2rKsCSU9w8much2nc+aUJy1pnRR4NoiyfMHKdN4H5UEqGnPZNyytB26Lgv9zlLCPLaT03qNvf4Q0Sa8tNRlA+d9CBCSDOwuAbuK9ngyL8KH5zzytfVXX6JMUfIBlKXDi9WW31C/uFlIEgWjR5rUECgYEA+pr/0GL4WGsW7d5K0wgiPFxz9C81gzPg8Bt074gfyz+R4dWeUagfj9VUrcn4hmz2QIRRi+C4iFUhJxGEWbH1mAzAIsuu8QHVZVOB2JOHTbv+OmB3AdqOeSokcMgnSXLCRLcLz5Q+CgXAKwLAY4g+vhe94OETP2tKRgOmO8Y0ZBkCgYEA4jSIBWaP6amqcW+75MLeWXD+BuSqLQRRxzXiPpCZkbnNuwhSCDoBwyGmuYpULR3uc65Po2E82cBoOrbryNhlVL0wonaCKCKmIGlZRJp5A9qR81ubuvMmsopUx71kr3eObpehi3QNR8OEcHDH7tZKBuTXNoJAXOZ6kGKc54/ZKWcCgYEA0npKLrhhAsn2uRPBXlrvfijcTpMz650v6Bn3bZKzuV4L5VQoI0cEMyamd0wxyp1LecZoQwYblo2BSbHhqf/YWLskyFSv+sVieDbZ0I+fzT7+eAM7ELiCfutRLdQ9gue6mEHwzoggTwSjcLxf33i30apZFL2VD+fJuuInxP5kVEkCgYASbYsjPX6932eWRC7/bOymRrE7KIfAkRiEbRxYUnlgSM28zem459ja57Poa5996XFSlUHBoptNdgJHvj0pKI/gvmTm51alJGV6QHrYPula6gR32d+u9D4Tz3cmqoQ4UzoUQmATt0mZrhDtBBMVnvl65EuwEh5iMxiLCK2Vo79iQwKBgDPtJ1VEAvwv8rFxZEfrLhruY29eUnzqBRqevOFXYOSdgzmUFfVMc68lPUUNT4uTSbHs2yyuetCL+7TSewJ5t1XxsA8jNZ1PlnSvlyc2slVxDYwtRTECxnenzBM2ixsS0SDTj/+eTDoA58biNmK5PdFhVI4em9RujvZpHamHpLDj";
        String endpointUrl = "http://zhubglobal-eu126-4.sggz00b.dev.alipay.net";

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
        String imagePath = "/Users/lzcc2003/Desktop/vm_front.jpg";

        DocRecognitionRequest request=new DocRecognitionRequest();
        request.setBizId("biz-id-12345");  // for tracing purpose, it is recommended to use a global unique id
        request.setDocType("00840000001");
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
