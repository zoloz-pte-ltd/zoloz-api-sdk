package com.zoloz.api.sdk.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Created with IntelliJ IDEA.
 * User: Zhongyang MA
 * Date: 2019-11-18
 * Time: 21:49
 */
public class AESUtil {
    /**
     * 用AES密钥加密数据
     * @param key 密钥
     * @param content 待加密数据内容原文
     * @return 输出的密文Base64格式
     */
    public static String encrypt(byte[] key, String content) throws Exception {
        SecretKeySpec spec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, spec);
        byte[] byteEnc = cipher.doFinal(content.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(byteEnc);
    }

    /**
     * 用AES进行解密
     * @param key 密钥
     * @param content 待解密数据内容Base64格式
     * @return 原文
     */
    public static String decrypt(byte[] key, String content) throws Exception {
        SecretKeySpec spec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, spec);
        byte[] encryptByte = Base64.getDecoder().decode(content);
        byte[] original = cipher.doFinal(encryptByte);
        return new String(original, "UTF-8");
    }

    /**
     * 生成密钥
     * @param length 长度
     * @return
     * @throws Exception
     */
    public static byte[] generateKey(int length) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");//密钥生成器
        keyGen.init(length); //默认128，获得无政策权限后可为192或256
        SecretKey secretKey = keyGen.generateKey();//生成密钥
        byte[] key = secretKey.getEncoded();//密钥字节数组
        return key;
    }

}