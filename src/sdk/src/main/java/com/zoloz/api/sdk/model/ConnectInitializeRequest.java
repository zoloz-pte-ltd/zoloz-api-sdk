package com.zoloz.api.sdk.model;

import java.util.Map;

import lombok.Data;

@Data
public class ConnectInitializeRequest {

    /**
     * Uniquely generated, globally unique business ID, for tracing.
     * e.g. Sequence ID from DB
     */
    private String bizId;

    /**
     * Meta info from client
     */
    private String metaInfo;

    /**
     * Merchant user id
     */
    private String userId;

    /**
     * Doc information after manual review. Must be the same as enrolled information.
     * Either userId or certInfo, one of them must be in the request.
     */
    // we do not support realId for connect now
    // private CertInfo certInfo;


    /**
     * This is a Map(Key value pairs) for additional information to be passed in.
     * It shouldn’t support nested map elements
     */
    private Map<String, Object> envData;

    /**
     * Business scene for data analysis purpose, no real business impact.
     * If you want to distinguish the data performance of different scenarios,
     * we suggest setting sceneCode to different value.
     * It is recommended to name sceneCode's value according to business purpose.
     * For example:registration,withdraw,topUp,transfer,changePassword.
     */
    private String sceneCode;

    /**
     * Service level is used to select different combination of our algorithm from security perspective
     */
    private String serviceLevel;

    /**
     * Risk operation mode is used to control the threshold of our algorithm
     */
    private String operationMode;

    /**
     * Wireless config group name. This field is used for looking up wireless configs.
     * Wireless config includes mobile gateway url, h5 pages url, etc.
     * This field is required if there exists multiple environments and wireless config differs in each environment.
     */
    private String wirelessConfigGroup;


    /**
     * UI type
     */
    private String uiType;

}
