package com.zoloz.api.sdk.model.connectv2;

/**
 * 核身验证状态枚举
 *
 * Create by yaomeng on 2020-09-10 19:17
 *
 * @author yaomeng
 */
public enum VerifyStatusEnum {

    /**
     * 核身初始化
     */
    INITIAL("INITIAL"),

    /**
     * 处理中
     */
    PROCESSING("PROCESSING"),

    /**
     * 通过核身
     */
    SUCCESS("SUCCESS"),


    /**
     * 过期
     */
    EXPIRED("EXPIRED"),

    /**
     * 拒绝
     */
    REJECT("REJECT"),

    /**
     * 未通过核身
     */
    FAIL("FAIL");

    public String getStatus() {
        return status;
    }

    private String status;

    VerifyStatusEnum(String status) {
        this.status = status;
    }
}
