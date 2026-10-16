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

    // ==================== GCM(AEAD)====================

    @Test
    public void testGcmRoundTrip() {
        byte[] key = AESUtil.generateKey(256);
        String plain = "{\"bizId\":\"b1\",\"docType\":\"ID_CARD\"}";
        GcmCipher g = AESUtil.encryptGCM(key, plain);
        assertNotNull(g.getIv());
        assertNotNull(g.getTag());
        // iv/tag 用 base64url 无 padding(不含 '=')
        assertFalse(g.getIv().contains("="), "iv should be base64url without padding");
        assertFalse(g.getTag().contains("="), "tag should be base64url without padding");
        assertEquals(plain, AESUtil.decryptGCM(key, g.getCipher(), g.getIv(), g.getTag()));
    }

    @Test
    public void testGcmNonceUniqueness() {
        byte[] key = AESUtil.generateKey(256);
        String plain = "same-content";
        GcmCipher g1 = AESUtil.encryptGCM(key, plain);
        GcmCipher g2 = AESUtil.encryptGCM(key, plain);
        assertNotEquals(g1.getIv(), g2.getIv(), "nonce must differ per encryption");
        assertNotEquals(g1.getCipher(), g2.getCipher(), "ciphertext must differ with different nonce");
    }

    @Test
    public void testGcmEmptyAndLargeContent() {
        byte[] key = AESUtil.generateKey(256);
        // 空明文
        GcmCipher gEmpty = AESUtil.encryptGCM(key, "");
        assertEquals("", AESUtil.decryptGCM(key, gEmpty.getCipher(), gEmpty.getIv(), gEmpty.getTag()));
        // 超长
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("x");
        }
        GcmCipher gLarge = AESUtil.encryptGCM(key, sb.toString());
        assertEquals(sb.toString(), AESUtil.decryptGCM(key, gLarge.getCipher(), gLarge.getIv(), gLarge.getTag()));
    }

    @Test(expectedExceptions = Exception.class)
    public void testGcmTagTamperThrows() {
        byte[] key = AESUtil.generateKey(256);
        GcmCipher g = AESUtil.encryptGCM(key, "secret");
        // 翻转 tag 末位 → tag 校验必须失败
        byte[] tagBytes = java.util.Base64.getUrlDecoder().decode(g.getTag());
        tagBytes[tagBytes.length - 1] ^= 0x01;
        String tamperedTag = java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(tagBytes);
        AESUtil.decryptGCM(key, g.getCipher(), g.getIv(), tamperedTag);
    }

    @Test(expectedExceptions = Exception.class)
    public void testGcmIvTamperThrows() {
        byte[] key = AESUtil.generateKey(256);
        GcmCipher g = AESUtil.encryptGCM(key, "secret");
        // 篡改 iv → GCM 认证失败(iv 在 AEAD 认证范围)
        byte[] ivBytes = java.util.Base64.getUrlDecoder().decode(g.getIv());
        ivBytes[0] ^= 0x01;
        String tamperedIv = java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(ivBytes);
        AESUtil.decryptGCM(key, g.getCipher(), tamperedIv, g.getTag());
    }
}
