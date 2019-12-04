package com.zoloz.api.sdk.model;

import lombok.Data;

/**
 * Create by yaomeng on 2020-02-05 11:47
 *
 * @author yaomeng
 */
@Data
public class UserGroupOutRequest {
    // group ID，if null server will use "default"
    private String groupId;
    // business user ID
    private String uid;
}
