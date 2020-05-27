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

package com.zoloz.example;

import com.zoloz.api.sdk.api.PrivacyInfoDeleteApi;
import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.PrivacyInfoDeleteRequest;
import com.zoloz.api.sdk.model.PrivacyInfoDeleteResponse;
import com.zoloz.example.util.KeyUtil;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Example of Privacy Information Delete
 *
 * @Author: jushi
 * @Date: 2020-05-27 19:05
 */
public class PrivacyInfoDeleteExample {

    public static void main(String[] args) {


        // create Options object
        Options options = new Options();
        options.addOption("c", true, "The client id");
        options.addOption("p", true, "The base64 content of the zoloz public key");
        options.addOption("k", true, "The path of the merchant private key");
        options.addOption("t", true, "The transaction id");
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
                            "-t <transaction_id> " +
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
        //client.setSigned(false);     // signature (of response) validation can be turned off
        //client.setEncrypted(false);  // encryption can be turned off

        // initialize PrivacyInfoDeleteApi
        PrivacyInfoDeleteApi deleteApi = new PrivacyInfoDeleteApi(client);

        // get transaction id
        String transactionId = cmd.getOptionValue("t");

        PrivacyInfoDeleteRequest request = new PrivacyInfoDeleteRequest();
        request.setTransactionId(transactionId);

        // call api
        PrivacyInfoDeleteResponse response = deleteApi.delete(request);

        if ("S".equals(response.getResult().getResultStatus())) {
            System.out.println(String.format("delete transactionId=%s success",request.getTransactionId()));
        }
        else {
            System.out.println(String.format(
                    "[Error] %s: %s",
                    response.getResult().getResultCode(),
                    response.getResult().getResultMessage()
            ));
        }
    }

}
