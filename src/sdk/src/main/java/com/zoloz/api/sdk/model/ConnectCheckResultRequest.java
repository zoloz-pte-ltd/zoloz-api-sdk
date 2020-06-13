package com.zoloz.api.sdk.model;

import lombok.Data;

@Data
public class ConnectCheckResultRequest {

    /**
     * Uniquely generated, globally unique business ID, for tracing.
     * e.g. Sequence ID from DB
     */
    private String bizId;

    /**
     * unique ID that used to start ekyc SDK in client side.
     * Server side need to pass this to calling client for further processing
     */
    private String transactionId;

    /**
     * A flag indicates whether image data should be returned in the API.
     * Default value is N.
     *
     * "Y": yes
     * "N": no
     */
    private String isReturnImage;

}
