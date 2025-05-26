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
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import lombok.SneakyThrows;

/**
 * AESUtil
 *
 * @author: Zhang Fang
 */
public class AESUtil {
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

}
