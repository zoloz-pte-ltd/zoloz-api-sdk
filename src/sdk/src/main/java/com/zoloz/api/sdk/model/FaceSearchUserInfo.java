package com.zoloz.api.sdk.model;

import lombok.Data;

/**
 * Create by yaomeng on 2020-02-05 13:00
 *
 * @author yaomeng
 */
@Data
public class FaceSearchUserInfo {
    // id
    private String uid;
    // 比对分数
    private String score;
    // 扩展数据json
    private String extInfo;
    // 创建时间
    private String gmtCreate;
    // 修改时间
    private String gmtModified;
}