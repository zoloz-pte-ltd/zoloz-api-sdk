package com.zoloz.api.sdk.model.connectv2;

import com.zoloz.api.sdk.model.OpenApiCommonResult;
import lombok.Data;

import java.util.List;

@Data
public class ConnectV2InitializeVerifyResponse extends OpenApiCommonResult {

    /**
     * Unique ID of the connect application. It will be provided when result.resultStatus is S
     */
    private String transactionId;

    /**
     * Client config to be used by Zoloz SDK
     */
    private String clientCfg;

    /**
     * available authentication product
     */
    private List<List<String>> availableProducts;
}
