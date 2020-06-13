package com.zoloz.api.sdk.model;

import lombok.Data;

@Data
public class ConnectInitializeResponse extends OpenApiCommonResult{

    /**
     * Unique ID of the connect application. It will be provided when result.resultStatus is S
     */
    private String transactionId;

    /**
     * Client config to be used by Zoloz SDK
     */
    private String clientCfg;

}
