package com.zoloz.api.sdk.model;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Zhongyang MA
 * Date: 2019-11-19
 * Time: 19:19
 */
@Data
public class OpenApiCommonResult {

    private OpenApiResultCode result;

    public OpenApiCommonResult() {
        result = new OpenApiResultCode();
    }

    @Data
    public static class OpenApiResultCode {
        private String resultCode;
        private String resultStatus;
        private String resultMessage;
        private String transactionId;
    }

}
