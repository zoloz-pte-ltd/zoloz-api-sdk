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
import javax.crypto.NoSuchPaddingException;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import lombok.SneakyThrows;

/**
 * RSAUtil
 *
 * @author: Zhang Fang
 */
public class RSAUtil {
    /**
     * encrypt with RSA public key
     * @param publicKey the RSA public key
     * @param content original content to be encrypted
     * @return the encrypted content in base64 format
     */
    @SneakyThrows({NoSuchAlgorithmException.class, NoSuchPaddingException.class, IllegalBlockSizeException.class, BadPaddingException.class})
    public static String encrypt(String publicKey, byte[] content) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
            return Base64.getEncoder().encodeToString(cipher.doFinal(content));
        }
        catch (InvalidKeySpecException ex) {
            throw new IllegalArgumentException("invalid key", ex);
        }
        catch (InvalidKeyException ex) {
            throw new IllegalArgumentException("invalid key", ex);
        }
    }

    /**
     * decrypt with RSA private key
     * @param privateKey the RSA private key
     * @param content content to be decrypted in base64 format
     * @return original content
     */
    @SneakyThrows({NoSuchAlgorithmException.class, NoSuchPaddingException.class})
    public static byte[] decrypt(String privateKey, String content) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
            return cipher.doFinal(Base64.getDecoder().decode(content));
        }
        catch (InvalidKeySpecException ex) {
            throw new IllegalArgumentException("invalid key", ex);
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
     * construct public key
     * @param base64PublicKey public key in base64 format
     * @return the public key
     */
    @SneakyThrows(NoSuchAlgorithmException.class)
    private static PublicKey getPublicKey(String base64PublicKey) throws InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * construct private key
     * @param base64PrivateKey private key in base64 format
     * @return the private key
     */
    @SneakyThrows(NoSuchAlgorithmException.class)
    private static PrivateKey getPrivateKey(String base64PrivateKey) throws InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey));
        return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    }

}
