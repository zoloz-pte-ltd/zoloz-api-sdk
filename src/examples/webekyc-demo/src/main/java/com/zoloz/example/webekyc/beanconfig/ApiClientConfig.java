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

package com.zoloz.example.webekyc.beanconfig;

import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.example.util.KeyUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * auto configuraiton
 *
 * @Author: jushi
 * @Date: 2020-02-19 16:46
 */
@Configuration
public class ApiClientConfig {

    @Value("${host.url:https://sg-production-api.zoloz.com}")
    private String hostUrl = "https://sg-production-api.zoloz.com";

    @Value("${client.id}")
    private String clientId;

    @Value("${merchant.privkey.path}")
    private String merchantPrivKeyPath;

    @Value("${zoloz.pubkey.path:}")
    private String zolozPubKeyPath = null;

    @Value("${zoloz.pubkey:}")
    private String zolozPubKey = null;


    @Bean
    public OpenApiClient client() {

        if (zolozPubKey == null || zolozPubKey.isEmpty()) {
            zolozPubKey = KeyUtil.loadKeyContent(zolozPubKeyPath);
        }
        String merchantPrivateKey = KeyUtil.loadKeyContent(merchantPrivKeyPath);

        OpenApiClient client = new OpenApiClient();
        client.setHostUrl(hostUrl);
        client.setClientId(clientId);
        client.setOpenApiPublicKey(zolozPubKey);
        client.setMerchantPrivateKey(merchantPrivateKey);

        return client;
    }
}
