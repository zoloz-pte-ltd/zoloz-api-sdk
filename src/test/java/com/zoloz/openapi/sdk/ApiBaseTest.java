/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.zoloz.openapi.sdk;

import com.zoloz.openapi.sdk.client.OpenApiClient;
import lombok.Getter;
import org.junit.Before;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;

/**
 *
 * @author chenzc
 * @version $Id: BaseTest.java, v 0.1 2019年11月26日 20:43 chenzc Exp $
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
        client= new OpenApiClient();  // 构造函数默认加签、加密
        client.setHostUrl(hostUrl);
        client.setClientId(clientId);
        client.setMerchantPrivateKey(merchantPrivateKey);
        client.setOpenApiPublicKey(openApiPublicKey);
        //client.setSigned(false);     // 可以设置不加签
        //client.setEncrypted(false);  // 可以设置不加密
    }

    protected String getBase64ImageContent(String imageUrl) {
        String base64string = null;
        try {
            InputStream inputStream= this.getClass().getResourceAsStream(imageUrl);
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
