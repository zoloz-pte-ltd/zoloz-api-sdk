/*
 * Copyright (c) 2019 ZOLOZ PTE.LTD.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.zoloz.api.sdk.model;

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
