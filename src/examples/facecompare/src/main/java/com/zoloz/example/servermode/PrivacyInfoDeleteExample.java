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

import com.zoloz.api.sdk.api.PrivacyInfoDeleteApi;
import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.PrivacyInfoDeleteRequest;
import com.zoloz.api.sdk.model.PrivacyInfoDeleteResponse;
import com.zoloz.example.util.KeyUtil;

/**
 * PrivacyInfoDelete example
 */
public class PrivacyInfoDeleteExample {

    public static void main(String[] args) {

        // initialize OpenApiClient
        String clientId = "<your client ID>";
        String zolozPublicKey = "<ZOLOZ transaction public key>";
        String merchantPrivateKeyPath = "<absolute path of your private key file>";
        String merchantPrivateKey = KeyUtil.loadKeyContent(merchantPrivateKeyPath);

        // construct with signature and encryption by default
        OpenApiClient client = new OpenApiClient();
        client.setHostUrl("https://sg-dev-api.zoloz.net");
        client.setClientId(clientId);
        client.setMerchantPrivateKey(merchantPrivateKey);
        client.setOpenApiPublicKey(zolozPublicKey);
        //client.setSigned(false);     // signature (of response) validation can be turned off
        //client.setEncrypted(false);  // encryption can be turned off

        // initialize PrivacyInfoDeleteApi
        PrivacyInfoDeleteApi deleteApi = new PrivacyInfoDeleteApi(client);

        PrivacyInfoDeleteRequest request = new PrivacyInfoDeleteRequest();
        request.setTransactionId("G000000001FRL20200517000000000021000895");  // set transactionId

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
