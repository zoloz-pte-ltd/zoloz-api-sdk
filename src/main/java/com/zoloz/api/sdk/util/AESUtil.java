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

package com.zoloz.api.sdk.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import lombok.SneakyThrows;

/**
 * AESUtil
 *
 * @author: Zhang Fang
 */
public class AESUtil {

    /**
     * GCM 模式:NoPadding(AEAD,无填充)
     */
    private static final String GCM_AES_MODE = "AES/GCM/NoPadding";

    /**
     * GCM nonce 长度(12 字节,NIST 推荐)
     */
    private static final int GCM_NONCE_LENGTH = 12;

    /**
     * GCM 认证 tag 长度(128 bit = 16 字节)
     */
    private static final int GCM_TAG_LENGTH_BITS = 128;
    private static final int GCM_TAG_LENGTH_BYTES = GCM_TAG_LENGTH_BITS / 8;

    /**
     * Base64URL 无 padding 编码器(iv/tag 用,规避 header 按 "=" 拆分的解析坑)
     */
    private static final Base64.Encoder URL_ENCODER_NO_PADDING = Base64.getUrlEncoder().withoutPadding();
    private static final Base64.Decoder URL_DECODER = Base64.getUrlDecoder();

    /**
     * 加密级随机源(GCM nonce 必须用加密级随机)
     */
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    /**
     * encrypt data with AES key
     * @param key the AES key
     * @param content to be encrypted
     * @return encrypted content in base64 format
     */
    @SneakyThrows({GeneralSecurityException.class, UnsupportedEncodingException.class})
    public static String encrypt(byte[] key, String content) {

        try {
            SecretKeySpec spec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(1, spec);
            byte[] byteEnc = cipher.doFinal(content.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(byteEnc);
        }
        catch (InvalidKeyException ex) {
            throw new IllegalArgumentException("invalid key", ex);
        }
        catch (IllegalBlockSizeException ex) {
            throw new IllegalArgumentException("invalid key or content", ex);
        }
        catch (BadPaddingException ex) {
            throw new IllegalArgumentException("invalid key or content", ex);
        }
    }

    /**
     * decrypt data with AES key
     * @param key the AES key
     * @param content to be decrypted
     * @return the original content
     */
    @SneakyThrows({GeneralSecurityException.class, UnsupportedEncodingException.class})
    public static String decrypt(byte[] key, String content) {
        try {
            SecretKeySpec spec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, spec);
            byte[] encryptByte = Base64.getDecoder().decode(content);
            byte[] original = cipher.doFinal(encryptByte);
            return new String(original, "UTF-8");
        }
        catch (InvalidKeyException ex) {
            throw new IllegalArgumentException("invalid key", ex);
        }
        catch (IllegalBlockSizeException ex) {
            throw new IllegalArgumentException("invalid key or content", ex);
        }
        catch (BadPaddingException ex) {
            throw new IllegalArgumentException("invalid key or content", ex);
        }
    }

    /**
     * AES key generation
     * @param length key length
     * @return key in byte array
     */
    @SneakyThrows(NoSuchAlgorithmException.class)
    public static byte[] generateKey(int length) {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(length);
        SecretKey secretKey = keyGen.generateKey();
        byte[] key = secretKey.getEncoded();
        return key;
    }

    // ==================== GCM 模式(AEAD,加密+认证一体)====================

    /**
     * 用 AES-GCM 加密数据
     * <p>
     * 生成 12 字节随机 nonce,加密后密文与 tag 拆分:
     * - cipher: 密文,标准 Base64(作为 HTTP body)
     * - iv:     12 字节 nonce,Base64URL 无 padding(进入 Encrypt 头)
     * - tag:    16 字节认证 tag,Base64URL 无 padding(进入 Encrypt 头)
     *
     * @param key     AES 密钥
     * @param content 待加密明文
     * @return GcmCipher(cipher/iv/tag)
     */
    @SneakyThrows(GeneralSecurityException.class)
    public static GcmCipher encryptGCM(byte[] key, String content) {
        byte[] nonce = new byte[GCM_NONCE_LENGTH];
        SECURE_RANDOM.nextBytes(nonce);

        Cipher cipher = Cipher.getInstance(GCM_AES_MODE);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"),
                new GCMParameterSpec(GCM_TAG_LENGTH_BITS, nonce));
        // JDK GCM: doFinal 输出 = 密文 + tag(tag 在末尾,16 字节)
        byte[] ctAndTag = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));

        int ctLen = ctAndTag.length - GCM_TAG_LENGTH_BYTES;
        byte[] ct = Arrays.copyOfRange(ctAndTag, 0, ctLen);
        byte[] tag = Arrays.copyOfRange(ctAndTag, ctLen, ctAndTag.length);

        return new GcmCipher(
                Base64.getEncoder().encodeToString(ct),       // body 用标准 Base64
                URL_ENCODER_NO_PADDING.encodeToString(nonce), // iv 用 Base64URL 无 padding
                URL_ENCODER_NO_PADDING.encodeToString(tag)    // tag 用 Base64URL 无 padding
        );
    }

    /**
     * 用 AES-GCM 解密数据
     * <p>
     * tag 校验失败抛 AEADBadTagException(不返回明文)。
     *
     * @param key      AES 密钥
     * @param content  密文(标准 Base64,HTTP body)
     * @param ivB64    12 字节 nonce(Base64URL 无 padding,Encrypt 头 iv)
     * @param tagB64   16 字节 tag(Base64URL 无 padding,Encrypt 头 tag)
     * @return 明文
     */
    @SneakyThrows(GeneralSecurityException.class)
    public static String decryptGCM(byte[] key, String content, String ivB64, String tagB64) {
        byte[] nonce = URL_DECODER.decode(ivB64);
        byte[] ct = Base64.getDecoder().decode(content);
        byte[] tag = URL_DECODER.decode(tagB64);

        // 拼回 ct + tag(JDK GCM 解密要求 tag 拼在密文末尾)
        byte[] ctAndTag = Arrays.copyOf(ct, ct.length + tag.length);
        System.arraycopy(tag, 0, ctAndTag, ct.length, tag.length);

        Cipher cipher = Cipher.getInstance(GCM_AES_MODE);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"),
                new GCMParameterSpec(GCM_TAG_LENGTH_BITS, nonce));
        // tag 校验失败:抛 AEADBadTagException
        byte[] original = cipher.doFinal(ctAndTag);
        return new String(original, StandardCharsets.UTF_8);
    }

}
