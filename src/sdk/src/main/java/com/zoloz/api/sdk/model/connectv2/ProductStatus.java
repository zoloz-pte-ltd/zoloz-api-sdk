/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.zoloz.api.sdk.model.connectv2;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author the
 * @version : ProductStatus.java, v 0.1 2020年09月11日 5:37 下午 the Exp $
 */
@Data
public class ProductStatus {
    /**
     * Product Code
     */
    private ProductCodeEnum productCode;

    /**
     * Whether the device supports
     */
    private boolean support;

    /**
     * Additional Product Parameters
     */
    private ProductAvailabilityStatusEnum productAvailabilityStatus;

    /**
     * Additional available information
     */
    private Map<String, Object> extAvailabilityInfo = new HashMap<>(10);

    public static class ExtKeys {

        public static final String DEVICE_ID = "deviceId";
    }
}