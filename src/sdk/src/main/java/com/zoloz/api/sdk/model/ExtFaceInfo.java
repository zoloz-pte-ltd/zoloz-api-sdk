/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.zoloz.api.sdk.model;

import lombok.Data;

/**
 *
 * @author weijie.xwj
 * @version $Id: ExtFaceInfo.java, v 0.1 2020年01月14日 4:39 PM weijie.xwj Exp $
 */
@Data
public class ExtFaceInfo {

    /**
     * Alive face image, only presents when retCode equals SUCCESS or NOT_SAME_PERSON.
     */
    String aliveImage;

    /**
     * Referenced face image, only presents in a verification flow where retCode equals SUCCESS or NOT_SAME_PERSON.
     */
    String refImage;

    /**
     * Face matching similarity, only presents in a verification flow where retCode equals SUCCESS or NOT_SAME_PERSON.
     */
    Integer faceScore;

    /**
     * Same face threshold, only presents in a verification flow where retCode equals SUCCESS or NOT_SAME_PERSON.
     */
    Integer sameFaceThreshold;

    /**
     * Different face threshold, only presents in a verification flow where retCode equals SUCCESS or NOT_SAME_PERSON.
     */
    Integer notSameFaceThreshold;

    /**
     * Attack detected, only presents when retCode equals SUCCESS or NOT_SAME_PERSON. Possible values will be: true/ false.
     */
    boolean faceAttack;
}