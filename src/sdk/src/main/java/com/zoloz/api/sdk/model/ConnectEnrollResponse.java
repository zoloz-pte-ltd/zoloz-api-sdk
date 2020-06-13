package com.zoloz.api.sdk.model;

import lombok.Data;

/**
 * @author lan.qi
 * @date 24/2/20
 */
@Data
public class ConnectEnrollResponse extends OpenApiCommonResult {

    private String transactionId;
    private Long   referenceSourceSimpleId;

}
