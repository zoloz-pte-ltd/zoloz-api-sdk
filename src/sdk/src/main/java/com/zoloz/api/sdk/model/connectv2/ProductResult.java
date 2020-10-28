package com.zoloz.api.sdk.model.connectv2;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by yaomeng on 2020-09-10 19:21
 *
 * @author yaomeng
 */
@Data
public class ProductResult {
    // 已校验次数
    private int validateTimes;

    // 核身产品状态
    private ProductVerifyStatusEnum prodStatus;

    // 核身时间
    private Date validateTime;

    // 核身结果扩展信息
    private Map<String, Object> extInfo = new HashMap<>();

    public static class ExtKeys {
        public static final String DEVICE_ID = "deviceId";
    }

}
