package com.zoloz.api.sdk.model;

import lombok.Data;

/**
 * Create by yaomeng on 2020-02-05 11:28
 *
 * @author yaomeng
 */
@Data
public class FaceInfo {
    // face image base64
    private String faceContent;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FaceInfo{");
        sb.append("faceContentLength=").append((faceContent == null)?null:faceContent.length());
        sb.append("}");
        return sb.toString();
    }
}
