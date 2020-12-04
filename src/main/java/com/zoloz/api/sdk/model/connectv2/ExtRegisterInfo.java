/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.zoloz.api.sdk.model.connectv2;

import lombok.Data;

import java.util.Map;

/**
 * @author the
 * @version : ExtRegisterInfo.java, v 0.1 2020年09月11日 2:33 下午 the Exp $
 */
@Data
public class ExtRegisterInfo {
    /**
     * a string of numbers and letters that identifies every individual smartphone or tablet in the world. (required)
     */
    private String deviceId;

    private String  imageContent;
    private Boolean faceAttack;
    private Map     rect;
    private String  quality;
    private int     retryCount;

}