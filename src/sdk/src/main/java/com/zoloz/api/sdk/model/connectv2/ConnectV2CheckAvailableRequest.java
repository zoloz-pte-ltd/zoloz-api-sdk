/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.zoloz.api.sdk.model.connectv2;

import lombok.Data;

/**
 * request bean
 *
 * @author the
 * @version : CheckAvailableRequest.java, v 0.1 2020年09月11日 2:27 下午 the Exp $
 */
@Data
public class ConnectV2CheckAvailableRequest {
    /**
     * Merchant user id.
     */
    private String userId;
    /**
     * Client environment data from client, including device model, language, OS type, etc,.
     */
    private String clientData;

    /**
     * unique business ID
     */
    private String bizId;

    /**
     * optional, if null, query all product
     */
    private String productCode;
}