package com.zoloz.api.sdk.model.connectv2;

import lombok.Data;

/**
 * IFAA 更新请求
 *
 * Create by yaomeng on 2020-09-10 20:10
 *
 * @author yaomeng
 */
@Data
public class ConnectV2UpdateIFAARequest {
    // user id
    private String userId;

    // verify id
    private String transactionId;
}
