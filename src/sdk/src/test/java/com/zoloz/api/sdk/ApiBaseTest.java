/*
 * Copyright (c) 2019 ZOLOZ PTE.LTD.
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
package com.zoloz.api.sdk;

import com.zoloz.api.sdk.client.OpenApiClient;
import lombok.Getter;
import org.junit.Before;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;

/**
 * ApiBaseTest
 *
 * @Author: moxi
 * @Date: 2019-12-11 21:13
 */
public class ApiBaseTest {

    @Getter
    private OpenApiClient client;

    @Before
    public void init(){
        String clientId = "";
        String merchantPrivateKey = "";
        String openApiPublicKey = "";
        String hostUrl = "";

        // initialize OpenApiClient
        client= new OpenApiClient();  // construct with signature and encryption by default
        client.setHostUrl(hostUrl);
        client.setClientId(clientId);
        client.setMerchantPrivateKey(merchantPrivateKey);
        client.setOpenApiPublicKey(openApiPublicKey);
        //client.setSigned(false);     // signature can be turned off
        //client.setEncrypted(false);  // encryption can be turned off
    }

    protected static String getBase64ImageContent(String imagePath) {
        String base64string = null;
        try {
            FileInputStream inputStream = new FileInputStream(imagePath);
            ByteArrayOutputStream baos=readInputStream(inputStream);
            base64string = Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
        return base64string;
    }

    private static ByteArrayOutputStream readInputStream(InputStream input) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
