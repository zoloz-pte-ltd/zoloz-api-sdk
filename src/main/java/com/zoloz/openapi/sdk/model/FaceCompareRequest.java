package com.zoloz.openapi.sdk.model;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Zhongyang MA
 * Date: 2019-11-15
 * Time: 15:42
 */
@Data
public class FaceCompareRequest {
    /**
     * business unique ID
     */
    private String bizId;

    /**
     * liveness detect switch
     */
    // private String livenessDetectMode;

    /**
     * face1
     */
    private FaceImage face1;

    /**
     * face2
     */
    private FaceImage face2;

    public FaceCompareRequest() {
        face1 = new FaceImage();
        face2 = new FaceImage();
    }

    @Data
    public static class FaceImage {
        /**
         * face content base64 string
         */
        private String content;

        /**
         * face rect string
         */
        private String rect;
    }

}
