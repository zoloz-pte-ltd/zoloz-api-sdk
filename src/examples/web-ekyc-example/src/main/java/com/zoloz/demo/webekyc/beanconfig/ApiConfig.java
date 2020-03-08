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

package com.zoloz.demo.webekyc.beanconfig;

import com.zoloz.api.sdk.client.OpenApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * auto configuraiton
 *
 * @Author: jushi
 * @Date: 2020-02-19 16:46
 */
@Configuration
public class ApiConfig {

    @Bean
    public OpenApiClient client() {

//        // citi
//        String clientId = "default";
//        String openApiPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr+xWyrMYViLrdV8VAh4BrHbFyKbY8rcaSZA5C7eajHH/3oYZQUexvwTFd3LmoeATfNlZS4xxVWhKZXxSIeH7MogrDB//vDIWviHT3/5cW/dHqqK2SU6hpsDiOyxxXLhqRhyKMxs7gLfg1WvMUlhOoJuVtyFQC4/501cf2Z/4PIVaHh6xq7v9Ot5RFmOP2n+H2NaJyDl1vtRU5wdJZM+X1iP/hEA+Ms2riRCU+vf3020BNWNsw09qYvJIqTS1IE3wle6z/H+5teN0alBEJlNVqcGXvyzw2hqhbQLW+G+eONW99S+8nNqub7V11TwSLzmgVDm9IQZ1P8mARzDhlqfpVwIDAQAB";
//        String merchantPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCCMPwcXN94dRph1rXa/poPaMGkQzUwH6lpsGCM49WjXEjGJU8k/gPqeEaPUwy7MuLFfbhNZZ4SF7mXysYfuDfY2dpojl3khtlDl74ZHkd3xKIYjHccwuVT6qNTXogZahodYSkTNt0x+MBiLXBdmKjH23IoB5ZXnkVGjV7uhVwl4rkhMMlf9zpPz2Hz53oBrofMrJAX5QplH6/uXTKrTZ6PpvjXIOaCKsCnHmlF6C1FGoRuKPwpdtRbEq2/EHSzU+UwrEFbzDhtC3yNLXhyR624GpUEA95Bk1jEI48YODoEQj7/W1huAherElslvFS3DCK4iWicn4YpDGxhFrVPS079AgMBAAECggEAGv85aFBB7uKpo9w4Pb9KByP786oUH3SRVt9CRjUAmi2K0bvu24bpHPfv+cyI+VCmAbn+O950lt/gln9v8ifrPMaAKepLwhXMe929h8vlmgpmapzGHmqzz2D6mVEi+9IqtCjvsZJQ8AnbKwCtKlt7tGXnGDHqlSS2zNiH9QImh5xc5mfbatNQ3jVMO7YNDsSc90UheodKcAXV+19XAbk/K6sC2qePqEmGF/gS8DFPtJrUCAn93WATbenRcl2WRagEsND268bTo6RYUIwAG5oCqoCYA2viZt8+YeEPNpENzI7x7/Zpk6Y6N7BgYcf2yQ4vFZJpZCjY2jZETLwTJv+PqQKBgQDf8xE9K8OjVCO2Fp7MxpOKAzfijySLZt9E5c+sqrvZlY6zM/wPml3vpqJlL7YuPnDSAw9+SaCJWT2lcN7e4iqNfCG8iSWrVfZpUv4a2XD4O9hoo8MtyVpeRbvpUajJZwlw1sYM1Vc9Q6wEyhtB88ZeLH7SGWe8XHs0xrGyyvZnAwKBgQCU0txW1/W7zOAbNkQtzKY0YT7dPKJp1Tf6ltNT3/A0Bl+UnCDqWTdtxrAtrLOGIRm6zbGpcDU33ltRPYWgJ3gJ5CF8juDh9mspqiYCi2ycnEIgPpLJNSsNiTphLUtazsmxbupV/lFNgHreZgVaU1w4I0cAB2Bh8o9ciw3LfdaR/wKBgGUYpFKZ/BIyPIDJy1wj1Jy8ATFsbxHjGa1PlSWcWXrK8gVh1Urx46/46FyHYlWT6HhV21umPythttuFjYbOUGlYfR5QR0oGX1uClKVoPYBB+9qY+tYjjID8N+0G6QwqUG+49OT8Ngq5DuGGnzFxq739li6gO10j3pafj75nsz4jAoGBAIMdnwhX7eSOJKd00O50zb16K5No10JcmpwJA5hyxEJhlxXr08gD4CS0FwW2kNfME8oTbMH13L0jBtr1U+P8wfIc7i+6BYCuxE0J3cECKydzN4MG/fWNBShAzZFOAYS4iNPs/0HR9LlOR3Hm+jJRHP9MfUIWPaEtvYZv8DBm5oLrAoGBAJgeMajagRWsK6bMnk2DxIbtugEsKQP4mbVE41ouwXREp0+0O86Cz3SNwud1dm+fe6p6jWy5gIMxGgAz8uJg7GJhu18NAlDa8+uhbkAPbSwGBxPD8jto87GyHv+iUPpkOwRME7hzPKbg8+gd9df7GRM4WGLjFxh0kUsZfGTOwKwl";
//        String hostUrl = "http://13.250.24.139:8341";

        // saas dev
        String clientId = "2188424678027236";
        String openApiPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr+xWyrMYViLrdV8VAh4BrHbFyKbY8rcaSZA5C7eajHH/3oYZQUexvwTFd3LmoeATfNlZS4xxVWhKZXxSIeH7MogrDB//vDIWviHT3/5cW/dHqqK2SU6hpsDiOyxxXLhqRhyKMxs7gLfg1WvMUlhOoJuVtyFQC4/501cf2Z/4PIVaHh6xq7v9Ot5RFmOP2n+H2NaJyDl1vtRU5wdJZM+X1iP/hEA+Ms2riRCU+vf3020BNWNsw09qYvJIqTS1IE3wle6z/H+5teN0alBEJlNVqcGXvyzw2hqhbQLW+G+eONW99S+8nNqub7V11TwSLzmgVDm9IQZ1P8mARzDhlqfpVwIDAQAB";
        String merchantPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCCMPwcXN94dRph1rXa/poPaMGkQzUwH6lpsGCM49WjXEjGJU8k/gPqeEaPUwy7MuLFfbhNZZ4SF7mXysYfuDfY2dpojl3khtlDl74ZHkd3xKIYjHccwuVT6qNTXogZahodYSkTNt0x+MBiLXBdmKjH23IoB5ZXnkVGjV7uhVwl4rkhMMlf9zpPz2Hz53oBrofMrJAX5QplH6/uXTKrTZ6PpvjXIOaCKsCnHmlF6C1FGoRuKPwpdtRbEq2/EHSzU+UwrEFbzDhtC3yNLXhyR624GpUEA95Bk1jEI48YODoEQj7/W1huAherElslvFS3DCK4iWicn4YpDGxhFrVPS079AgMBAAECggEAGv85aFBB7uKpo9w4Pb9KByP786oUH3SRVt9CRjUAmi2K0bvu24bpHPfv+cyI+VCmAbn+O950lt/gln9v8ifrPMaAKepLwhXMe929h8vlmgpmapzGHmqzz2D6mVEi+9IqtCjvsZJQ8AnbKwCtKlt7tGXnGDHqlSS2zNiH9QImh5xc5mfbatNQ3jVMO7YNDsSc90UheodKcAXV+19XAbk/K6sC2qePqEmGF/gS8DFPtJrUCAn93WATbenRcl2WRagEsND268bTo6RYUIwAG5oCqoCYA2viZt8+YeEPNpENzI7x7/Zpk6Y6N7BgYcf2yQ4vFZJpZCjY2jZETLwTJv+PqQKBgQDf8xE9K8OjVCO2Fp7MxpOKAzfijySLZt9E5c+sqrvZlY6zM/wPml3vpqJlL7YuPnDSAw9+SaCJWT2lcN7e4iqNfCG8iSWrVfZpUv4a2XD4O9hoo8MtyVpeRbvpUajJZwlw1sYM1Vc9Q6wEyhtB88ZeLH7SGWe8XHs0xrGyyvZnAwKBgQCU0txW1/W7zOAbNkQtzKY0YT7dPKJp1Tf6ltNT3/A0Bl+UnCDqWTdtxrAtrLOGIRm6zbGpcDU33ltRPYWgJ3gJ5CF8juDh9mspqiYCi2ycnEIgPpLJNSsNiTphLUtazsmxbupV/lFNgHreZgVaU1w4I0cAB2Bh8o9ciw3LfdaR/wKBgGUYpFKZ/BIyPIDJy1wj1Jy8ATFsbxHjGa1PlSWcWXrK8gVh1Urx46/46FyHYlWT6HhV21umPythttuFjYbOUGlYfR5QR0oGX1uClKVoPYBB+9qY+tYjjID8N+0G6QwqUG+49OT8Ngq5DuGGnzFxq739li6gO10j3pafj75nsz4jAoGBAIMdnwhX7eSOJKd00O50zb16K5No10JcmpwJA5hyxEJhlxXr08gD4CS0FwW2kNfME8oTbMH13L0jBtr1U+P8wfIc7i+6BYCuxE0J3cECKydzN4MG/fWNBShAzZFOAYS4iNPs/0HR9LlOR3Hm+jJRHP9MfUIWPaEtvYZv8DBm5oLrAoGBAJgeMajagRWsK6bMnk2DxIbtugEsKQP4mbVE41ouwXREp0+0O86Cz3SNwud1dm+fe6p6jWy5gIMxGgAz8uJg7GJhu18NAlDa8+uhbkAPbSwGBxPD8jto87GyHv+iUPpkOwRME7hzPKbg8+gd9df7GRM4WGLjFxh0kUsZfGTOwKwl";
        String hostUrl = "https://sg-dev-api.zoloz.net";

        OpenApiClient client = new OpenApiClient();
        client.setSigned(true);
        client.setEncrypted(false);
        client.setHostUrl(hostUrl);
        client.setClientId(clientId);
        client.setOpenApiPublicKey(openApiPublicKey);
        client.setMerchantPrivateKey(merchantPrivateKey);

        return client;
    }
}
