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

package com.zoloz.demo.clientmode.autoconfig;

import com.zoloz.api.sdk.client.OpenApiClient;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * auto configuraiton
 *
 * @Author: jushi
 * @Date: 2020-02-19 16:46
 */
@Configuration
public class BizAutoConfig {

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

    /**
     * load private key from pem file
     * @param keyPath path of pem file
     * @return base64 encoded content of the private key
     * @throws IOException
     */
    @SneakyThrows(IOException.class)
    protected static String loadKeyContent(String keyPath) {

        String content = FileUtils.readFileToString(new File(keyPath), "UTF-8");

        String[] lines = content.split("\n");
        String parsed = Stream.of(lines)
                .filter(line -> !line.startsWith("--"))
                .collect(Collectors.joining(""));

        return parsed;
    }

    @Bean
    public OpenApiClient client() {

        if (zolozPubKey == null || zolozPubKey.isEmpty()) {
            zolozPubKey = loadKeyContent(zolozPubKeyPath);
        }
        String merchantPrivateKey = loadKeyContent(merchantPrivKeyPath);

        OpenApiClient client = new OpenApiClient();
        client.setHostUrl(hostUrl);
        client.setClientId(clientId);
        client.setOpenApiPublicKey(zolozPubKey);
        client.setMerchantPrivateKey(merchantPrivateKey);

        return client;
    }
}
