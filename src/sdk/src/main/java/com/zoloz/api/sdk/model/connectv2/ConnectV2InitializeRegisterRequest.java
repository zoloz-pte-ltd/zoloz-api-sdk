/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.zoloz.api.sdk.model.connectv2;

import lombok.Data;

/**
 * request bean
 *
 * @author the
 * @version : InitializeRegisterRequest.java, v 0.1 2020年09月11日 2:12 下午 the Exp $
 */
@Data
public class ConnectV2InitializeRegisterRequest {
    /**
     * Merchant user id
     */
    private String userId;

    /**
     * ready to register product name
     */
    private String productCode;

    /**
     * unique business ID
     */
    private String bizId;

    /**
     * Client environment data from client, including device model, language, OS type, etc,.
     */
    private String clientData;

}