package com.zoloz.api.sdk.util;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

import javax.crypto.KeyGenerator;
import java.util.Base64;

public class SignatureUtilTest {

    private static final String TEST_CONTENT = "Test message to sign";
    
    private String generateSecretKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        return Base64.getUrlEncoder().withoutPadding().encodeToString(keyGen.generateKey().getEncoded());
    }

    @Test
    public void testSignAndVerify() throws Exception {
        String secretKey = generateSecretKey();
        
        String signature = SignatureUtil.sign(TEST_CONTENT, secretKey);
        assertNotNull(signature);
        
        boolean isValid = SignatureUtil.verify(TEST_CONTENT, secretKey, signature);
        assertTrue(isValid);
    }

    @Test
    public void testVerifyFailsWithWrongContent() throws Exception {
        String secretKey = generateSecretKey();
        String signature = SignatureUtil.sign(TEST_CONTENT, secretKey);
        
        boolean isValid = SignatureUtil.verify("Different content", secretKey, signature);
        assertFalse(isValid);
    }

    @Test
    public void testVerifyFailsWithWrongKey() throws Exception {
        String secretKey1 = generateSecretKey();
        String secretKey2 = generateSecretKey();
        String signature = SignatureUtil.sign(TEST_CONTENT, secretKey1);
        
        boolean isValid = SignatureUtil.verify(TEST_CONTENT, secretKey2, signature);
        assertFalse(isValid);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSignWithInvalidKey() {
        // construct an invalid key that doesn't conform base64 schema
        String invalidKey = "abcdefg123$";
        // expect to throw IllegalArgumentException
        SignatureUtil.sign(TEST_CONTENT, invalidKey);
    }

    @Test
    public void testSignEmptyContent() throws Exception {
        String secretKey = generateSecretKey();
        String signature = SignatureUtil.sign("", secretKey);
        assertNotNull(signature);
        
        boolean isValid = SignatureUtil.verify("", secretKey, signature);
        assertTrue(isValid);
    }
}
