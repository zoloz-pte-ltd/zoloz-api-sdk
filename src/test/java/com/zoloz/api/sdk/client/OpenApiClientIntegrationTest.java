package com.zoloz.api.sdk.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.zoloz.api.sdk.util.JSONUtil;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class OpenApiClientIntegrationTest {

    private OpenApiClient client;

    @BeforeMethod
    public void setUp() {
        String[] requiredKeys = new String[] {
                "ZOLOZ_IT_HOST_URL",
                "ZOLOZ_IT_CLIENT_ID",
                "ZOLOZ_IT_MERCHANT_PRIVATE_KEY",
                "ZOLOZ_IT_OPENAPI_PUBLIC_KEY",
                "ZOLOZ_IT_AK",
                "ZOLOZ_IT_SK",
        };
        for (String key : requiredKeys) {
            if (System.getenv(key) == null) {
                throw new SkipException("Skipping all tests - cannot find required env key " + key);
            }
        }

        client = new OpenApiClient();
        client.setHostUrl(System.getenv("ZOLOZ_IT_HOST_URL"));
        client.setClientId(System.getenv("ZOLOZ_IT_CLIENT_ID"));
        client.setMerchantPrivateKey(System.getenv("ZOLOZ_IT_MERCHANT_PRIVATE_KEY"));
        client.setOpenApiPublicKey(System.getenv("ZOLOZ_IT_OPENAPI_PUBLIC_KEY"));
        client.setAccessKey(System.getenv("ZOLOZ_IT_AK"));
        client.setSecretKey(System.getenv("ZOLOZ_IT_SK"));

        client.setThrowOnFailure(true);
    }

    @Test
    public void testTwoWayAuth() throws Exception {

        client.setProtoName("2way");

        String result = client.callOpenApi("v1.zoloz.authentication.test", "{\"foo\":\"bar\"}");
        assertNotNull(result);

        JSONObject json = JSONUtil.parseObject(result, JSONObject.class);
        JSONObject innerResult = (JSONObject)json.get("result");
        assertEquals(innerResult.getString("resultStatus"), "S");
        assertEquals(innerResult.getString("resultCode"), "SUCCESS");

        JSONObject bizResult = JSON.parseObject(innerResult.getString("resultMessage"));
        assertEquals(bizResult.getString("foo"), "bar");
    }

    @Test
    public void testAKSK() throws Exception {

        client.setProtoName("aksk");

        String result = client.callOpenApi("v1.zoloz.authentication.test", "{\"foo\":\"bar\"}");
        assertNotNull(result);

        JSONObject json = JSONUtil.parseObject(result, JSONObject.class);
        JSONObject innerResult = (JSONObject)json.get("result");
        assertEquals(innerResult.getString("resultStatus"), "S");
        assertEquals(innerResult.getString("resultCode"), "SUCCESS");

        JSONObject bizResult = JSON.parseObject(innerResult.getString("resultMessage"));
        assertEquals(bizResult.getString("foo"), "bar");
    }
}
