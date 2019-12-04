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
        //dev
        /*
        String clientId = "2189400000000204";
        String merchantPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCWXW4J5Q11/f8TBb0/zDz/FQDMVx5Y4qNxzQR3BAUXzejkDhueeuOsP0NF1B5l3/H55ALAyuVE5SORHudYkL5ITkVnQGLYnvTTKLnSinHNW5A4iLZWWe/0VP/4L+y8wtlTeTakrpLBnvwm/ipk2/GEGjdI68MYRNfKdLljENP5fRbjNMT7Ys8i/SnoPjkGavcZXyYTFqrLqPqCdL2w/ZmNRzLw1BTtqgL90bX83ZPfL4uWjCtgpfpQcsdxr4XOFi1g6iHE6Gi2y5PzascHoMxOKbFc2itC0NIaOuY4LMFHpBSD+D80uXugLpqG/bx3yAbfOM19E7II1r+WxzCNPpkPAgMBAAECggEAHhhTFt3mZNBShj8m0qcRKkjM4RkqtMWfyv4qv5tpXYtT6pk8Q+r6uJXs5AQBlYhOCSLuviGd470s8jXKPfqyawxnbbQAVLTz8XN8Rbx57I0//2C1hfD4SuHcXZNSAbHKB6ACJNHgA6rLcw5587flzfflSs4NPUVCH35fQIm0s4FcGbjVdvTo05+bsWi+xcexs7pjlzIvr8L5QoURpK9yYddXHi5cq6D5JkkpvmoFCMRR0kV1i2r+3SHWWfyZp5Aj1U0N/x13FJkEpNKiOGXR9Te6XYNeo8JvjNzgKfa+Ag0NwQQre9RljrsH6Ti5XtfTMMz8ukxqD5plKlDAlyxJuQKBgQDTiZVTsDeuheXpdxZOsRNk7ma5iAxKXjKTSdtMQxRpXishRaTAnX1H14MvLX4xXuBd8Bgu6IVEellOQgQVmWzpeB7yCHPieQ+LWSiFCUzU842kSg3KqrILAeX6GIQFNfQZXPvmVhcZZ5yV517IpjLOTE8FzJlDXFkdFyJuT6IP/QKBgQC1+EZnZxvonCVCLbyg9cKISMBEsr3kp2oCfGnRurUJ1ALksS/H+BVfuoKYshAta4likV6eeruCzDrbYMFI0WJLgyI0l61BZ5iHMNMNEE1S2OhyiSwLhMIbSLmHu61XVAD6n7ydkXy/fRT2WhcV3TsDq+kbLRHf47vHwvVJTCJc+wKBgQCLlZTIlVYYvlgYtzEsGeKPTlTQhjp99CxxTmH7r6PPZ4kUOm1dgE6D0mzI+77yewWYVu7OPTZ7GjTF0//39LaOVGovEW7OeU3NiLaZGqrtNg035Hm3Su2TH9yOLBEpkxGQju/VbOdvJxSHQhYkVq7dvDLEw946Oby/2l0o5zksTQKBgGhRFfs9LtCggvN3SMV1XbnHCwHW/elQ2ALo07j5scMamTYFJYEbhRVF1Iw4t/FxzmaFM5rifRA0iCEvTF60OgdT+43uzdHK07Pcl4DLsagm1MqkPG22A5ikjkdznaGMdKs4W2Zw+vTDffrkFovgMW6fZJjBs5eH9CRGbVtBnAl1AoGAd4JJ/VHvZUoL9g7iumbcPRn0ihqnaFXl1qe6418wKLgq8yfqD3xJARmTgelBRIUsJYvf4YOKFokheNqx5S0QSZ2zIWFKhjZOOhoVQXVsfxWDUxnrOS3cEKrmkL1ldoZ5tSH3VcMa+9ZLcCSBaCSjvyjOLzSGA7BGLgYbe1Yc1ho=";
        String openApiPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAid3Qr831XYq55LdO4NBI9l1N2j8h8NaPFwISl2vKlH6ftvTIdbPQ4/Zmb2Bjm7CP986tPZq92oWnzkLkVy0Tmkc/YPRF+kMdP/EUMKAb/nKjTUzXwQSNADG0dSyNAojiyaxUneNrG7hONnuQOANfgKV0qGkEYOpr/5SnQMvvnsV4E3JTkvfGfYTc6Y1vr4c/PEHcz5I/uIyv8sV52aLzxdOf8G7K219llS6wRWYa02H9xIl3d6nnRx4VJTvA9eZVemunuG4OHzTdhZ+RdMIDpMCeuhb2pGlcy/uP8UPr4aiQ0tkxfwD8FgcmqwCtHmEZWESMCPiPtSEV/ztyM+vQNQIDAQAB";
        String hostUrl = "http://zhubglobal-eu95-0.sggz00b.stable.alipay.net";
        */

        //pre
        String clientId = "2188488535727396";
        String merchantPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCWXW4J5Q11/f8TBb0/zDz/FQDMVx5Y4qNxzQR3BAUXzejkDhueeuOsP0NF1B5l3/H55ALAyuVE5SORHudYkL5ITkVnQGLYnvTTKLnSinHNW5A4iLZWWe/0VP/4L+y8wtlTeTakrpLBnvwm/ipk2/GEGjdI68MYRNfKdLljENP5fRbjNMT7Ys8i/SnoPjkGavcZXyYTFqrLqPqCdL2w/ZmNRzLw1BTtqgL90bX83ZPfL4uWjCtgpfpQcsdxr4XOFi1g6iHE6Gi2y5PzascHoMxOKbFc2itC0NIaOuY4LMFHpBSD+D80uXugLpqG/bx3yAbfOM19E7II1r+WxzCNPpkPAgMBAAECggEAHhhTFt3mZNBShj8m0qcRKkjM4RkqtMWfyv4qv5tpXYtT6pk8Q+r6uJXs5AQBlYhOCSLuviGd470s8jXKPfqyawxnbbQAVLTz8XN8Rbx57I0//2C1hfD4SuHcXZNSAbHKB6ACJNHgA6rLcw5587flzfflSs4NPUVCH35fQIm0s4FcGbjVdvTo05+bsWi+xcexs7pjlzIvr8L5QoURpK9yYddXHi5cq6D5JkkpvmoFCMRR0kV1i2r+3SHWWfyZp5Aj1U0N/x13FJkEpNKiOGXR9Te6XYNeo8JvjNzgKfa+Ag0NwQQre9RljrsH6Ti5XtfTMMz8ukxqD5plKlDAlyxJuQKBgQDTiZVTsDeuheXpdxZOsRNk7ma5iAxKXjKTSdtMQxRpXishRaTAnX1H14MvLX4xXuBd8Bgu6IVEellOQgQVmWzpeB7yCHPieQ+LWSiFCUzU842kSg3KqrILAeX6GIQFNfQZXPvmVhcZZ5yV517IpjLOTE8FzJlDXFkdFyJuT6IP/QKBgQC1+EZnZxvonCVCLbyg9cKISMBEsr3kp2oCfGnRurUJ1ALksS/H+BVfuoKYshAta4likV6eeruCzDrbYMFI0WJLgyI0l61BZ5iHMNMNEE1S2OhyiSwLhMIbSLmHu61XVAD6n7ydkXy/fRT2WhcV3TsDq+kbLRHf47vHwvVJTCJc+wKBgQCLlZTIlVYYvlgYtzEsGeKPTlTQhjp99CxxTmH7r6PPZ4kUOm1dgE6D0mzI+77yewWYVu7OPTZ7GjTF0//39LaOVGovEW7OeU3NiLaZGqrtNg035Hm3Su2TH9yOLBEpkxGQju/VbOdvJxSHQhYkVq7dvDLEw946Oby/2l0o5zksTQKBgGhRFfs9LtCggvN3SMV1XbnHCwHW/elQ2ALo07j5scMamTYFJYEbhRVF1Iw4t/FxzmaFM5rifRA0iCEvTF60OgdT+43uzdHK07Pcl4DLsagm1MqkPG22A5ikjkdznaGMdKs4W2Zw+vTDffrkFovgMW6fZJjBs5eH9CRGbVtBnAl1AoGAd4JJ/VHvZUoL9g7iumbcPRn0ihqnaFXl1qe6418wKLgq8yfqD3xJARmTgelBRIUsJYvf4YOKFokheNqx5S0QSZ2zIWFKhjZOOhoVQXVsfxWDUxnrOS3cEKrmkL1ldoZ5tSH3VcMa+9ZLcCSBaCSjvyjOLzSGA7BGLgYbe1Yc1ho=";
        String openApiPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhFkEaT2orLpL+lbDdDp1I7b9iG7E+D7DqZWsmy84e7Tm33kUOXYjOuZhnASevVowDyDbumJq4++d1FnA/jUy+Dmi8hkL10qztDaLod4opfzFXAfq3p3PsjOr0qN7wvX/nu+Ko2A0xO/2elnih3iIXMxU2K988+TspQLQZXN+ZyaJTF4uB1BgczQgDTHaC7eVPbS0AZWs61/b3PfhIqvT/DkYUva3yHciAthQTOVv6P4Yxjoid1huthFYob6MwsCrD62m4GamYACsQTSZgmvU49XbcGxnnvnHmwb1B/KHqA78U8YXL2MHoZZODLzd51JUUmdVZAv1Cr6J2NHiRbN3/QIDAQAB";
        String hostUrl = "https://open-sea-pre.zoloz.com";



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
