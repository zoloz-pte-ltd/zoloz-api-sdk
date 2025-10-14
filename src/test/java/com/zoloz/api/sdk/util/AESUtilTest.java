package com.zoloz.api.sdk.util;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.security.GeneralSecurityException;

public class AESUtilTest {

    @Test
    public void testGenerateKey() {
        byte[] key = AESUtil.generateKey(128);
        assertNotNull(key);
        assertEquals(16, key.length); // 128 bits = 16 bytes
    }

    @Test
    public void testEncryptDecryptRoundtrip() {
        byte[] key = AESUtil.generateKey(128);
        String original = "Test message 123";
        
        String encrypted = AESUtil.encrypt(key, original);
        assertNotNull(encrypted);
        assertNotEquals(original, encrypted);
        
        String decrypted = AESUtil.decrypt(key, encrypted);
        assertEquals(original, decrypted);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEncryptWithInvalidKey() {
        byte[] invalidKey = new byte[8]; // Too short
        AESUtil.encrypt(invalidKey, "test");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDecryptWithInvalidKey() {
        byte[] key1 = AESUtil.generateKey(128);
        byte[] key2 = AESUtil.generateKey(128); // Different key
        String original = "Test message";
        
        String encrypted = AESUtil.encrypt(key1, original);
        AESUtil.decrypt(key2, encrypted); // Should fail
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDecryptInvalidContent() {
        byte[] key = AESUtil.generateKey(128);
        AESUtil.decrypt(key, "not a valid encrypted string");
    }

    @Test
    public void testEncryptEmptyString() {
        byte[] key = AESUtil.generateKey(128);
        String encrypted = AESUtil.encrypt(key, "");
        assertNotNull(encrypted);
        String decrypted = AESUtil.decrypt(key, encrypted);
        assertEquals("", decrypted);
    }
}
