package com.zoloz.api.sdk.model.connectv2;

import lombok.Data;

import java.util.List;

@Data
public class ConnectV2InitializeVerifyRequest {

    /**
     * Uniquely generated, globally unique business ID, for tracing.
     * e.g. Sequence ID from DB
     */
    private String bizId;

    /**
     * Meta info from client
     */
    private String clientData;

    /**
     * Merchant user id
     */
    private String userId;

    /**
     * Business scene for data analysis purpose, no real business impact.
     * If you want to distinguish the data performance of different scenarios,
     * we suggest setting sceneCode to different value.
     * It is recommended to name sceneCode's value according to business purpose.
     * For example:registration,withdraw,topUp,transfer,changePassword.
     */
    private String sceneCode;

    /**
     * It may has one or more group of methods combinations, and each combination may has one or more authentication methods.
     */
    private List<List<ConnectV2ProductInfo>> validateFlows;

    /**
     * Wireless config group name. This field is used for looking up wireless configs. Wireless config includes mobile gateway url, h5 pages url, etc. This field is required if there exists multiple environments and wireless config differs in each environment.
     */
    private String wirelessConfigGroup;
}
