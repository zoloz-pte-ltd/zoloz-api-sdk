package com.zoloz.api.sdk.model;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Zhongyang MA
 * Date: 2019-11-15
 * Time: 15:50
 */
@Data
public class FaceCompareResponse extends OpenApiCommonResult {

    private String transactionId;

    /**
     * same person
     */
    private Boolean samePerson;

    /**
     * comparison score
     */
    private Double score;

    /**
     * face1 rect
     */
    //private FaceImage face1;

    /**
     * face2 rect
     */
    //private FaceImage face2;

    public FaceCompareResponse() {
        //face1 = new FaceImage();
        //face2 = new FaceImage();
    }

    @Data
    public static class FaceImage {
        /**
         * face liveness
         */
        private Boolean liveness;
    }

}
