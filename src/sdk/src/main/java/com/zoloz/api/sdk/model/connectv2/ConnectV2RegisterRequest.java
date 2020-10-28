/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.zoloz.api.sdk.model.connectv2;

import lombok.Data;

/**
 * request bean
 *
 * @author the
 * @version : RegisterRequest.java, v 0.1 2020年09月11日 2:13 下午 the Exp $
 */

@Data
public class ConnectV2RegisterRequest {

    /**
     * Unique ID that used to start Connect SDK in client side.
     */
    private String transactionId;

    /**
     * iifaa needed, remote face is optional
     */
    private String authData;
}