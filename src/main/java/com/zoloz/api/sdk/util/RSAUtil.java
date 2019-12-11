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

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Created with IntelliJ IDEA.
 * User: Zhongyang MA
 * Date: 2019-11-18
 * Time: 22:01
 */
public class RSAUtil {
    /**
     * 加密
     * @param publicKey 公钥
     * @param content 待加密内容，原文
     * @return 加密后的密文Base64格式
     * @throws Exception
     */
    public static String encrypt(String publicKey, byte[] content) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        return Base64.getEncoder().encodeToString(cipher.doFinal(content));
    }

    /**
     * 解密
     * @param privateKey 私钥
     * @param content 待解密的内容，Base64格式
     * @return 原文
     * @throws Exception
     */
    public static byte[] decrypt(String privateKey, String content) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
        return cipher.doFinal(Base64.getDecoder().decode(content));
    }

    /**
     * 组装公钥
     * @param base64PublicKey
     * @return
     * @throws Exception
     */
    private static PublicKey getPublicKey(String base64PublicKey) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 组装私钥
     * @param base64PrivateKey
     * @return
     * @throws Exception
     */
    private static PrivateKey getPrivateKey(String base64PrivateKey) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey));
        return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    }

}
