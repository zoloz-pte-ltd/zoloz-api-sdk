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
 * DocRecognitionRequest
 *
 * @Author: moxi
 * @Date: 2019-12-11 21:13
 */
@Data
public class DocRecognitionRequest {

    /**
     * business unique ID
     */
    private String bizId;

    /**
     * doc type
     */
    private String docType;

    /**
     * image of front page
     */
    private String frontPageImage;

    /**
     * image of back page
     */
    private String backPageImage;

    /**
     * product level
     */
    private String productLevel;

    /**
     * operation mode
     */
    private String operationMode;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("bizId=").append(bizId);
        sb.append(",docType=").append(docType);
        sb.append(",frontPageImageLength=").append(frontPageImage == null ? 0 : frontPageImage.length());
        sb.append(",backPageImageLength=").append(backPageImage == null ? 0 : backPageImage.length());
        sb.append(",productLevel=").append(productLevel);
        sb.append(",operationMode=").append(operationMode);
        return sb.toString();
    }
}