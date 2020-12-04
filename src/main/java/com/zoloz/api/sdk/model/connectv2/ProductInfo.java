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
public class ProductInfo {
    // product name
    private ProductCodeEnum productCode;

    // product parameters
    private Map<String, Object> productParams;

    public static class ExtKeys {

        public static final String UI_TYPE = "uiType";

        /**
         * serviceLevel
         */
        public static final String SERVICE_LEVEL = "serviceLevel";

        /**
         * operationMode
         */
        public static final String OPERATION_MODE = "operationMode";
    }
}
