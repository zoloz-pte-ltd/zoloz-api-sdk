/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.zoloz.api.sdk.model.connectv2;

import lombok.Data;

/**
 * request bean
 *
 * @author the
 * @version : FaceEnrollRequest.java, v 0.1 2020年09月11日 2:27 下午 the Exp $
 */
@Data
public class ConnectV2FaceEnrollRequest {
    /**
     * unique business ID
     */
    private String bizId;

    /**
     * Merchant user id
     */
    private String userId;

    /**
     * Image content using base64 encode
     */
    private String base64ImageContent;
}