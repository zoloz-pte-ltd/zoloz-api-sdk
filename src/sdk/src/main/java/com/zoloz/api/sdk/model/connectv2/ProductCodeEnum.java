/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.zoloz.api.sdk.model.connectv2;

/**
 * @author the
 * @version : ProductName.java, v 0.1 2020年09月11日 2:52 下午 the Exp $
 */
public enum ProductCodeEnum {

    /**
     * Device fingerprint authentication, original IFAA fingerprint authentication
     */
    FINGERID,

    /**
     * Device 3D facial authentication, original IFAA FaceId authentication
     */
    FACEID,

    /**
     * Remote 2D facial authentication, original zoloz 2D face compare
     */
    REMOTE_FACE;
}