package com.zoloz.api.sdk.model.connectv2;

/**
 * 核身产品状态
 *
 * Create by yaomeng on 2020-09-10 19:23
 *
 * @author yaomeng
 */
public enum ProductVerifyStatusEnum {
    /**
     * 初始化
     */
    INITIAL("INITIAL"),

    /**
     * 通过校验
     */
    PASS("PASS"),

    /**
     * 处理中
     */
    PROCESS("PROCESS"),

    /**
     * 未通过校验
     */
    FAIL("FAIL");

    public String getCode() {
        return code;
    }

    private String code;

    ProductVerifyStatusEnum(String code) {
        this.code = code;
    }
}
