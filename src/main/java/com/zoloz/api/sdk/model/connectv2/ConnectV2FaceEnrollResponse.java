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
 * @version : FaceEnrollResult.java, v 0.1 2020年09月11日 2:27 下午 the Exp $
 */
@Data
@ToString(callSuper = true)
public class ConnectV2FaceEnrollResponse extends OpenApiCommonResult {
    /**
     * optional parameter
     */
    private String transactionId;
}