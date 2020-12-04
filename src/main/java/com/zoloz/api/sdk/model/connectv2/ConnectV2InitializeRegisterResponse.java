/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.zoloz.api.sdk.model.connectv2;

import com.zoloz.api.sdk.model.OpenApiCommonResult;
import lombok.Data;
import lombok.ToString;

/**
 * result bean
 *
 * @author the
 * @version : initializeRegisterResult.java, v 0.1 2020年09月11日 2:11 下午 the Exp $
 */
@Data
@ToString(callSuper = true)
public class ConnectV2InitializeRegisterResponse extends OpenApiCommonResult {
    /**
     * aka transactionId. Unique ID that used to start Connect SDK in client side.
     */
    private String transactionId;

    /**
     * Client config to be used by Zoloz  SDK.
     */
    private String clientCfg;
}