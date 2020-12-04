package com.zoloz.api.sdk.model.connectv2;

import lombok.Data;

import java.util.Map;

/**
 * Create by yaomeng on 2020-09-10 19:21
 *
 * @author yaomeng
 */
@Data
public class ConnectV2ProductResult {
    // 已校验次数
    private int validateTimes;

    // 核身产品状态
    private String prodStatus;

    // 核身时间
    private String validateTime;

    // 核身结果扩展信息
    private Map<String, Object> extInfo;
}
