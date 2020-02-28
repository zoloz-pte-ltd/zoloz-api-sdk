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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * auto configuraiton
 *
 * @Author: jushi
 * @Date: 2020-02-19 16:46
 */
@Configuration
public class BizAutoConfig {

    @Bean
    public OpenApiClient client() {

//        String clientId = "2188424678027236";
//        String openApiPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr+xWyrMYViLrdV8VAh4BrHbFyKbY8rcaSZA5C7eajHH/3oYZQUexvwTFd3LmoeATfNlZS4xxVWhKZXxSIeH7MogrDB//vDIWviHT3/5cW/dHqqK2SU6hpsDiOyxxXLhqRhyKMxs7gLfg1WvMUlhOoJuVtyFQC4/501cf2Z/4PIVaHh6xq7v9Ot5RFmOP2n+H2NaJyDl1vtRU5wdJZM+X1iP/hEA+Ms2riRCU+vf3020BNWNsw09qYvJIqTS1IE3wle6z/H+5teN0alBEJlNVqcGXvyzw2hqhbQLW+G+eONW99S+8nNqub7V11TwSLzmgVDm9IQZ1P8mARzDhlqfpVwIDAQAB";
//        String merchantPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCCMPwcXN94dRph1rXa/poPaMGkQzUwH6lpsGCM49WjXEjGJU8k/gPqeEaPUwy7MuLFfbhNZZ4SF7mXysYfuDfY2dpojl3khtlDl74ZHkd3xKIYjHccwuVT6qNTXogZahodYSkTNt0x+MBiLXBdmKjH23IoB5ZXnkVGjV7uhVwl4rkhMMlf9zpPz2Hz53oBrofMrJAX5QplH6/uXTKrTZ6PpvjXIOaCKsCnHmlF6C1FGoRuKPwpdtRbEq2/EHSzU+UwrEFbzDhtC3yNLXhyR624GpUEA95Bk1jEI48YODoEQj7/W1huAherElslvFS3DCK4iWicn4YpDGxhFrVPS079AgMBAAECggEAGv85aFBB7uKpo9w4Pb9KByP786oUH3SRVt9CRjUAmi2K0bvu24bpHPfv+cyI+VCmAbn+O950lt/gln9v8ifrPMaAKepLwhXMe929h8vlmgpmapzGHmqzz2D6mVEi+9IqtCjvsZJQ8AnbKwCtKlt7tGXnGDHqlSS2zNiH9QImh5xc5mfbatNQ3jVMO7YNDsSc90UheodKcAXV+19XAbk/K6sC2qePqEmGF/gS8DFPtJrUCAn93WATbenRcl2WRagEsND268bTo6RYUIwAG5oCqoCYA2viZt8+YeEPNpENzI7x7/Zpk6Y6N7BgYcf2yQ4vFZJpZCjY2jZETLwTJv+PqQKBgQDf8xE9K8OjVCO2Fp7MxpOKAzfijySLZt9E5c+sqrvZlY6zM/wPml3vpqJlL7YuPnDSAw9+SaCJWT2lcN7e4iqNfCG8iSWrVfZpUv4a2XD4O9hoo8MtyVpeRbvpUajJZwlw1sYM1Vc9Q6wEyhtB88ZeLH7SGWe8XHs0xrGyyvZnAwKBgQCU0txW1/W7zOAbNkQtzKY0YT7dPKJp1Tf6ltNT3/A0Bl+UnCDqWTdtxrAtrLOGIRm6zbGpcDU33ltRPYWgJ3gJ5CF8juDh9mspqiYCi2ycnEIgPpLJNSsNiTphLUtazsmxbupV/lFNgHreZgVaU1w4I0cAB2Bh8o9ciw3LfdaR/wKBgGUYpFKZ/BIyPIDJy1wj1Jy8ATFsbxHjGa1PlSWcWXrK8gVh1Urx46/46FyHYlWT6HhV21umPythttuFjYbOUGlYfR5QR0oGX1uClKVoPYBB+9qY+tYjjID8N+0G6QwqUG+49OT8Ngq5DuGGnzFxq739li6gO10j3pafj75nsz4jAoGBAIMdnwhX7eSOJKd00O50zb16K5No10JcmpwJA5hyxEJhlxXr08gD4CS0FwW2kNfME8oTbMH13L0jBtr1U+P8wfIc7i+6BYCuxE0J3cECKydzN4MG/fWNBShAzZFOAYS4iNPs/0HR9LlOR3Hm+jJRHP9MfUIWPaEtvYZv8DBm5oLrAoGBAJgeMajagRWsK6bMnk2DxIbtugEsKQP4mbVE41ouwXREp0+0O86Cz3SNwud1dm+fe6p6jWy5gIMxGgAz8uJg7GJhu18NAlDa8+uhbkAPbSwGBxPD8jto87GyHv+iUPpkOwRME7hzPKbg8+gd9df7GRM4WGLjFxh0kUsZfGTOwKwl";
//        String hostUrl = "https://sg-dev-api.zoloz.net";

//        String clientId="2188486771412389";
//        String openApiPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAll40lJjo2o4QQbSzchaTLKkdZiBNI4+j2XL4GFDOC7rz4Px5Qxjsp7kvfJ4FMSqA+rJlMuRTvkQjrq6zd0DverjOJy7/oLq9xqRKgHnB/MPkDgOoYzYjVBUextkrI5fEhuVfw8u6Zcus0SWcwUK4837/1C3qv+wollE39LrfQ3puJDyR0Mb31fw+TVHwfquDD18/8ck4/r45Uv8FwQBBZHhMf7JAdjayU6ut4F0oGp1h165uBNbG+9HkVWnzxCyVW52MpmXenYl97Ejwp61lmxgw2pl3qW2mvulUEaFNve2NyyxH/hKqAdQBRUvIKCMHgsodr3k8CyG8LwUPnE2xlQIDAQAB";
//        String merchantPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCwrn7iUsYwBqMNdGEIZo0qL+N/djGdeo9u+dp5x0TsChB2Yh72uopaPCr51FM+h/g9J2uNbg+8wiY7JRT0KFzIImt5m7fEleNjfAGpHtUC5MaGVJDEfoelT4BFTU7Q2rBSjT2sAe0PiQc99+5+ddznq048aXX9X653K9zWVK7E9+fbBZ4bv9pIn9Y8gafJ3SkWqGvIBHXozhyqANBleGgPZMf7IYHRRFTIb2fHT6s11L3izqXpCWPpdwkb6n+em4MlPFhw8q0Jk0NuJIcEohHVTUpvLLK6SlLUBqAiE9kb6dRoFJP4/7nQkLz9ORXDMBr3QQwskGK6/I8JlefYnONPAgMBAAECggEAeWpg3c0+TxCwinV4nRpEVkJ0l0o1DGCGIyBmiBttfLvp1kDVwH4ux5Xjfq3vyuALspLdcWK6Q+Az/58bCDKYmBw3OWnOXrUWf25fgcYjYgqvdu60UaQWOX8pyfY3fIVceE6BnCvyqiNjq60u4w7CER6C9DcRE17meRTWfR/Svp3r+PBwnWB65b0isHi5vOkSnHKiI+up5Lo2e78qJNvAtdq80x1kTeRK4yzOS3/LTypDNB6fJfS58QLv7NVY92z3165CAc9NYXMuqaz069swG1NKnSXBlcSOVDStFhjNOqKYOqS8bHMkCiLD5KodPYoON0Ztbmb4Q8GQx7sEtQRlGQKBgQDvy4MmnXexVbVTgUbCPGPew/+V5eQmAww4vJg4krNg6Jv7nJlwzNYBo8T+27vU42+J0txEToGFn5MiVJcrfhItyXUSTumhsw7jbOvSj1ZhcoknsvJFVnOoxTroFve6wyGjHqvqLt0QvPCVzuly7x3tMAnYsu3PBadzos9j2RDpjQKBgQC8nx0BjXPXcobIzBe//8RzIfHELH/mH5aGNkJslsvLaxbLEvhSWClnqRwQJHV8DRK0zUn8IDCpYxsp7XQjmLfbcgmH6/LBYc2AdmL/balAW75vTJUb7qVxscjxgDAxfpPNSHLKyRBE7MZsuY6oUM3CYmntvf28lFM+yLW+pF8TSwKBgQCUwstVHGFRyVsZN9z+yNooK62Wf6z/C+r8E7vXdOMFXqjN+kOSn/Cvybp/ZS/sjT/337E4Sv+9NtbfeFzj5W0/rHgNaJk18FBvNHZEBRpQj+6Yi0ITU+OisuCtdPowibeHa5K5UGNia+RQ+7EF1WPabm/dWHuBb/+HFFzWk6YTTQKBgHSY5xVOm41S/n6hqwzEzmtdUFY0K9vVKvdME0OM8+2LBUwDLf25Ad9SakaQQw+sBSQ+wr1YKLyMxv3kCyb2ALFmjP24nRdtndsLU6cR4s4l6FJkxMe/fgYKIG0pEKGH3VXIsy0nnSb+Nqp/CfCCAq9mppSQHYm1JsBo0fBHK36BAoGBAInVn3OxThDPrxZyGHf4AeT2VHPpPIkzQOWiq4xhTQYqMp1KvNDs9VTtG3ykc1lFE8BLGP1owlg8diQbiZz/NNCdquw62r8U4gKgMXRWQk0kuQ9wS84Y0+Yb4+h8IZQ8+U9To/fYSme2FlQHIf6Z0D3nPNrmzJKK/84XrKxqe6W/";
//        String hostUrl = "https://sg-sit-api.zoloz.net";

        String clientId="2188423368528034";
        String openApiPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjzAACKJIjY0ttXiG2mfiAZjMXlGXj88L2XEJxhkvxnp7VGEFKP3azxBYTGXM56+0G56r5fcMUma6biQbF6PalHIDP3r9a4Z64h3KGgSIuIc8qRVhS9yuJYY1cJaEvNliFFck26awoPu0EEUNS0KzMJ3tVJxOa1KlhbL3W2gJk/yhsl1zD06qqd2I1rpzlwyQEik8+TuhKPegP9ypB9lVcJxBYVtFKSM6+VwryUD+Bb3M5ut0dwr6nbSMNu4t7S12Pe7XxA5h+z/81GAYpYgYq5VXPX6Nalp+yFbZ926YusIQsgPXp3wzRPJjHmzGejjTn0PHw5iDLanpyNjw1qhX6QIDAQAB";
        String merchantPrivateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCG2bF1VQxflDOBJm1R1H3qni8qZj+ujcQbcdxzFk/tXM5A3b7xN2aIREIwAh640Wr/rdwpEbJwvLdw8B9UnW5emRnaqFT52fQndOh7tzDRMsaGqWMv3xxYaWxa6xXIJdO4MaBoqYU0yFpHjscMKSTqoUbSRjOANSuyEKYzahrbB+ly2EPbaCpP/gkN9FM95qUaozhEHHuSaaFrEkAFSf21str6pJNiObyFrXJpYCMkvoKiDec9MyAt9aUg+f1Wi52RdeZfhzwFMc2WaVfll2L4NN6dfkgduim2X4THgrFAEKXZlXqtbv88ErCHkIi5T8ir+1mqjhTcxBviFK9E8oT/AgMBAAECggEAWYRPkjUIBrbJzfUlYsntYH7rjnjb01MWmKVoExn/qvENle/d3iDJtrGhQl9V55hVMC9I1BaEXuRX3ef41GHBr5hwmj9eUZHGyx6HagD8rhLHyQLO7itslKA/Jseh6QB7YG3JL1FBNP2/OHtmVQNHF15CNjoV3Ajv3b/BpKXN+BF7jazzYoGKOPwWH2LrvpUhYHzw+7EQkXG9iPs8VDLWUm576GoTsGa52UBDfIjsIF1PnA8z9n3nlhiAUJjqCWW0dRW43a6BLuMA4WIGY2QY2lov69tX3SuCoN3uS0PV8QJ3zb9UtrlzENg0fTexe6msvcz0nEm1TR39ZmhwXvj+wQKBgQDNig6SqNsmMNqpT3hTL5cXqyz0u2gxDpf6SG2vrc4SssUcgOSCxA9+D2B72akx0guNmI1KMZvfR+dKK0cA6RezqOlA9QikOxrXLCHn+RUjmmOw3Ng1eHcQORSVN9b9IF+EDRu/wfqh3JOQx0mkfY6xKN+sFvZXUDYy+a9NQpPtnwKBgQCn9OgetYCXAqZrJS2noNsqYUXZqJLjyczSaSOZk+Ffml1Hzk2SaCTaOMjfQSlfUW5sBGJTkBudxxkLeRQRl4KODfANIbhd1/bGv9gqNwychn1dqbQ28vyYFvRDh7nduK1zEpHIZQfhq3rhIgVz/h1MNzsWIPgQdIJQhIP3HHVsoQKBgD9McBZ8iBkn3H9d6Ql9U4PHhK3N9VV+UBGVMqpy1U7u3UNc8oLPDJJe+/DIZJHiRQqTW5fo1T8bLsCpuVEvd8wtjcAidknVpft0LPD6xabUSjVSngdBlTFa0trtenkz+hZ5zvzsKVqlypBAv4432LT4iIHABJd5LWffezxI77OzAoGAabJTHXhFi0MMreMAvzwYkKwUhCx0/ErzzfqCf9SzgTGowFaRNLC7eGVB7FWajrS1U97SbhoDdyBjn3e77HI3o+QbjHgLCWuujlcO10o7comkfXwdwHLcCW5wm8rfaiwdY4xtF/qUoi0DMIjze5KQp3UehweKdPiNRdSPI8TSFOECgYAZZaeMP/is4FHK/E4V+PuXLJDJZGoPelCwrL0HWLz370QrJhIl7cpKfpqKKsFf7XDBtPBg4c5QgvrYHDD7tp0EVsofNWpxvHxrRrqNy0s3qT7/a65T40RWLwQnLg3CnwOpYRk7m97CQQxMoDj8XHsW2dq8DvUJ7ttCi5Jg4kiq5Q==";
        //String hostUrl = "https://sg-staging-api.zoloz.com";
        String hostUrl = "https://sg-production-api.zoloz.com";

        OpenApiClient client = new OpenApiClient();
        client.setHostUrl(hostUrl);
        client.setClientId(clientId);
        client.setOpenApiPublicKey(openApiPublicKey);
        client.setMerchantPrivateKey(merchantPrivateKey);

        return client;
    }
}
