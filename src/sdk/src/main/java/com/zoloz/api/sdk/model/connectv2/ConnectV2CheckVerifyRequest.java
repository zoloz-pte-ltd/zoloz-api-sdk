package com.zoloz.api.sdk.model.connectv2;

import lombok.Data;

import java.util.Map;

@Data
public class ConnectV2CheckVerifyRequest {

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
    private Map<String, String> extInfo;

}
