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
 * RealDocAsyncUploadRequest
 * Real Document Async Upload Request
 *
 * @author yirong
 */
@Data
public class RealDocAsyncUploadRequest {

    /**
     * Business ID
     */
    private String bizId;

    /**
     * Product type
     */
    private String productType;

    /**
     * File type (pdf, jpg, png, etc.)
     */
    private String fileType;

    /**
     * Schema ID for document validation rules
     */
    private String schemaId;

    /**
     * File content (Base64 encoded), max 10MB
     */
    private String fileContent;

    /**
     * File URL address
     */
    private String fileUrl;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("bizId=").append(bizId);
        sb.append(",productType=").append(productType);
        sb.append(",fileType=").append(fileType);
        sb.append(",schemaId=").append(schemaId);
        sb.append(",fileContentLength=").append(fileContent == null ? 0 : fileContent.length());
        sb.append(",fileUrl=").append(fileUrl);
        return sb.toString();
    }
}

