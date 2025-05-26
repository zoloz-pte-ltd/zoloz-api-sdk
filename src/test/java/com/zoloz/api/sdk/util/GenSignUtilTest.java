package com.zoloz.api.sdk.util;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

public class GenSignUtilTest {

    private static final String TEST_CONTENT = "Test message to sign";
    
    private KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    @Test
    public void testSignAndVerify() throws Exception {
        KeyPair keyPair = generateKeyPair();
        String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        
        String signature = GenSignUtil.sign(privateKey, TEST_CONTENT);
        assertNotNull(signature);
        
        boolean isValid = GenSignUtil.verify(publicKey, TEST_CONTENT, signature);
        assertTrue(isValid);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSignWithInvalidPrivateKey() {
        GenSignUtil.sign("invalid_key", TEST_CONTENT);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testVerifyWithInvalidPublicKey() {
        GenSignUtil.verify("invalid_key", TEST_CONTENT, "signature");
    }

    @Test
    public void testVerifyFailsWithWrongContent() throws Exception {
        KeyPair keyPair = generateKeyPair();
        String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        
        String signature = GenSignUtil.sign(privateKey, TEST_CONTENT);
        boolean isValid = GenSignUtil.verify(publicKey, "Different content", signature);
        assertFalse(isValid);
    }

    @Test
    public void testSignEmptyContent() throws Exception {
        KeyPair keyPair = generateKeyPair();
        String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        
        String signature = GenSignUtil.sign(privateKey, "");
        assertNotNull(signature);
        
        boolean isValid = GenSignUtil.verify(publicKey, "", signature);
        assertTrue(isValid);
    }
}
