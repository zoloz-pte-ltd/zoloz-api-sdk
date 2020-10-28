package com.zoloz.api.sdk.model.connectv2;

import lombok.Data;

import java.util.Map;

/**
 * 产品信息
 * <p>
 * Create by yaomeng on 2020-09-10 17:17
 *
 * @author yaomeng
 */
@Data
public class ConnectV2ProductInfo {
    // product name
    private String productCode;

    // product parameters
    private Map<String, Object> productParams;
}
