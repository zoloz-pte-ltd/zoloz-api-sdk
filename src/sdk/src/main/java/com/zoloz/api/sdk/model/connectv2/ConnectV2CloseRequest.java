/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.zoloz.api.sdk.model.connectv2;

import lombok.Data;

/**
 * request bean
 *
 * @author the
 * @version : CloseRequest.java, v 0.1 2020年09月11日 2:26 下午 the Exp $
 */
@Data
public class ConnectV2CloseRequest {
    /**
     * unique business ID
     */
    private String bizId;

    /**
     * Client environment data from client, including device model, language, OS type, etc,.
     */
    private String clientData;

    /**
     * ready to register product name
     */
    private String productCode;

    /**
     * Merchant user id
     */
    private String userId;

}