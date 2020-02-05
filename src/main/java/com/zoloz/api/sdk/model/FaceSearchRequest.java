package com.zoloz.api.sdk.model;

import lombok.Data;

/**
 * Create by yaomeng on 2020-02-05 12:54
 *
 * @author yaomeng
 */
@Data
public class FaceSearchRequest {
    // group ID，if null server will use "default"
    private String groupId;
    // face type, Please use "image"
    private String faceType;
    // face info
    private FaceInfo face;

    /**
     * comparison score, similar search result should be greater than or equals to this comparison score
     * "85"
     */
    private String score;
    /**
     * respond comparison result will be sorted by similarity score and ordered from top to buttom
     * 5
     */
    private int top;

    // ext info, suggest json format
    private String extInfo;
}
