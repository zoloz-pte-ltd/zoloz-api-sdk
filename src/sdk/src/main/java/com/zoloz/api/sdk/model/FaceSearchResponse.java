package com.zoloz.api.sdk.model;

import lombok.Data;

import java.util.List;

/**
 * Create by yaomeng on 2020-02-05 12:58
 *
 * @author yaomeng
 */
@Data
public class FaceSearchResponse  extends OpenApiCommonResult {
    /**
     * transaction id
     */
    private String transactionId;

    /**
     * matched user info
     */
    private List<FaceSearchUserInfo> uidList;
}
