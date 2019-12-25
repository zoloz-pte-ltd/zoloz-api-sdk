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
        String clientId = "2189400000000143";
        String merchantPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDcTm+BhyhynoFnHFcllxWp2LmFZqmK9mRfjrMs4Wfk0P0CwpW6h6CuJWZwHwd5iIL3ZT5yOM3yjE4J3035E3b+tGUJm64/NgfHC7u+9Teo8L/MJ+htmQDuq1fPBx0Mevhs7ObzI86olcMIM2eN3dFiI555evjGsXm9JXD5d1IGsgLX3lIgbj0BfFWxIMs1mCC5Pb4imExhDbDQQaWwLCZ0fdklO9o2jpB8He9MqCQWqF5PHe9s6Du1IWurTA6f6VCNWJwE+GiFnhVoMPOzJOSSeS9HvoKgRkQADJ1RlzrOKqTfJIcxOfkEoJI2pmkWMTGUVgyuuGt9UNQuqeLeVsVvAgMBAAECggEARMcH8LwtWHs7kuE6I7YjQT8zy/3c+FErzNlT/rLwKHNGSdJNxEoLU9LBREerobmIiYvGDwrE4PAEfPiF8ziscaAH2xQLNhi8HNwA5QDRO6DNgAMFgRG/RMOhSjTCUN8jJ35eK5IGNoNqu+iwb43MjILqOqq1d/J4OyCxQHCL3yKnkBx8k74B1qE+dtDnrQ9AMRgkkrV4JmWs1+f9n0/6zFfn2nnPXLh4O3umVW8lDxZjJ/tXsTgXSENuOCuSMdonNywODv9zBeKYI4n6M3n5sAQUKR0uoZQJff7r1xdbvMcSSp8TB8bGTNlaj1xsm/Lmc3texYa2q3wHAf8eyqcXQQKBgQDuRmGOhkbPQh7GMZhkiT2cJdlkNbx6ru/RJA3QQHCjEgBpgpcSp2T1IDOGCXyYb18DM30fdHzuDmsYNRY8bkdNCDZvDwezmOvzTJM9/pl/0coUu0p5QRv5zqUKPkvZ7zGJcyax2wB9gPahqhf1tuD8a9ykbhf7DKzqfHqbocfgqQKBgQDssd5M+8RcuRIficAspFv0IWBgZVnOcbsjBMdPA46K/PUAGLz67YG7JAM2cv+SSDNbOnog8UPO+RvYGKWAJV3VdWCSOK/En4zfHaQ+khvR7zUVFBGkyfx06MdLh8SpVw4jqKI5Jg4QahfauAL2/waBc+zXf/hSlVeoQb+PL0WMVwKBgQDJpgiT3LF6X4O5yWkl1DuutR1oQE3VKc1NGo0SLCbm6qosoNcHc2DuTiUsj0TBoKcxB1ch0qyTruIVZ3/8/nsbBxNCI98psAYnLiwCMEBpuvqhVuus+FwbqjVkGNqKgnJS13/szuF4VTbkFBYY3X9EQGhHQ+DJlq2T+Gqu4VhNmQKBgBDIf/HHfGHywYU4+EZyFQ8XesT7CsYnrFRMV3GT1/yk+R5borLMOoGxiQTIXLTbp8CVQemqEdvQMBg/jySqAaBMJV/Y4yW3Bn8fk/DX3V8ibgojlSixJdSIWPFTHC/Bu3/tyirxNtgVHcHlgFhRNv3g4W9aZJo+equ0lceqW20bAoGBAOG6ySw9zn1BsHGSv40+w2fO4YmTv/P5GazVmrf3UWoTz6g5r6ts8NOtka8m4cZxOm6DJu3AgyE7yRx/5cwI8+IFFrp/mK5iXodWE+DdR1lQDVvq+4WgtlretrHF8W9zIRLG2zSakZ9sXQQycqXF+YBly6n/zDwE905754RH4P60";
        String openApiPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhFkEaT2orLpL+lbDdDp1I7b9iG7E+D7DqZWsmy84e7Tm33kUOXYjOuZhnASevVowDyDbumJq4++d1FnA/jUy+Dmi8hkL10qztDaLod4opfzFXAfq3p3PsjOr0qN7wvX/nu+Ko2A0xO/2elnih3iIXMxU2K988+TspQLQZXN+ZyaJTF4uB1BgczQgDTHaC7eVPbS0AZWs61/b3PfhIqvT/DkYUva3yHciAthQTOVv6P4Yxjoid1huthFYob6MwsCrD62m4GamYACsQTSZgmvU49XbcGxnnvnHmwb1B/KHqA78U8YXL2MHoZZODLzd51JUUmdVZAv1Cr6J2NHiRbN3/QIDAQAB";
        String hostUrl = "https://open-sea.zoloz.com";

        // initialize OpenApiClient
        client= new OpenApiClient();  // construct with signature and encryption by default
        client.setHostUrl(hostUrl);
        client.setClientId(clientId);
        client.setMerchantPrivateKey(merchantPrivateKey);
        client.setOpenApiPublicKey(openApiPublicKey);
        //client.setSigned(false);     // signature can be turned off
        //client.setEncrypted(false);  // encryption can be turned off
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
