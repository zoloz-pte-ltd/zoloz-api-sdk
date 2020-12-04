package com.zoloz.api.sdk.model.connectv2;

import com.zoloz.api.sdk.model.OpenApiCommonResult;
import lombok.Data;

@Data
public class ConnectV2VerifyResponse extends OpenApiCommonResult {

    /**
     * validate result
     */
    private ProductResult validateResult;

}
