package com.zoloz.api.sdk.model;

import lombok.Data;

/**
 * Create by yaomeng on 2020-02-05 11:23
 *
 * @author yaomeng
 */
@Data
public class UserGroupInRequest {
    // group ID，if null server will use "default"
    private String groupId;
    // business user ID
    private String uid;
    // face type, Please use "image"
    private String faceType;
    // face info
    private FaceInfo face;
    // ext info, suggest json format
    private String extInfo;
}
