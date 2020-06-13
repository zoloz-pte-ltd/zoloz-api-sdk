package com.zoloz.api.sdk.model;

import lombok.Data;

/**
 * @author lan.qi
 * @date 24/2/20
 */
@Data
public class ConnectEnrollRequest {

    /**
     * Uniquely generated, globally unique business ID, for tracing.
     * e.g. Sequence ID from DB
     */
    private String bizId;

    /**
     * Merchant user id
     */
    private String userId;

    /**
     * base64ImageContent ...
     */
    private String base64ImageContent;
}
