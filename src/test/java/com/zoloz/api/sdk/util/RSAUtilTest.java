package com.zoloz.api.sdk.util;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

public class RSAUtilTest {

    private static final String TEST_CONTENT = "Test message to encrypt";
    
    private KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    @Test
    public void testEncryptDecryptRoundtrip() throws Exception {
        KeyPair keyPair = generateKeyPair();
        String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        
        String encrypted = RSAUtil.encrypt(publicKey, TEST_CONTENT.getBytes());
        assertNotNull(encrypted);
        assertNotEquals(TEST_CONTENT, encrypted);
        
        byte[] decrypted = RSAUtil.decrypt(privateKey, encrypted);
        assertEquals(TEST_CONTENT, new String(decrypted));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEncryptWithInvalidPublicKey() {
        RSAUtil.encrypt("invalid_key", TEST_CONTENT.getBytes());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDecryptWithInvalidPrivateKey() {
        RSAUtil.decrypt("invalid_key", "encrypted_content");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDecryptInvalidContent() throws Exception {
        KeyPair keyPair = generateKeyPair();
        String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        RSAUtil.decrypt(privateKey, "not_a_valid_encrypted_string");
    }

    @Test
    public void testEncryptEmptyContent() throws Exception {
        KeyPair keyPair = generateKeyPair();
        String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        
        String encrypted = RSAUtil.encrypt(publicKey, "".getBytes());
        assertNotNull(encrypted);
        
        byte[] decrypted = RSAUtil.decrypt(privateKey, encrypted);
        assertEquals("", new String(decrypted));
    }
}
