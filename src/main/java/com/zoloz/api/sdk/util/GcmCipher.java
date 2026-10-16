package com.zoloz.api.sdk.util;

import lombok.Data;

/**
 * GCM 加密结果载体
 *
 * cipher: 密文(标准 Base64,作为 HTTP body)
 * iv:     12 字节 nonce(Base64URL 无 padding,放入 Encrypt 头)
 * tag:    16 字节认证 tag(Base64URL 无 padding,放入 Encrypt 头)
 *
 * iv/tag 使用 Base64URL 无 padding,规避与服务端 header 按 "=" 拆分时
 * 遇标准 Base64 padding 的解析坑。
 *
 * @author xiangyun
 */
@Data
public class GcmCipher {

    /**
     * 密文,标准 Base64(进入 HTTP body)
     */
    private String cipher;

    /**
     * 12 字节 GCM nonce,Base64URL 无 padding(进入 Encrypt 头 iv 字段)
     */
    private String iv;

    /**
     * 16 字节 GCM 认证 tag,Base64URL 无 padding(进入 Encrypt 头 tag 字段)
     */
    private String tag;

    public GcmCipher() {
    }

    public GcmCipher(String cipher, String iv, String tag) {
        this.cipher = cipher;
        this.iv = iv;
        this.tag = tag;
    }
}