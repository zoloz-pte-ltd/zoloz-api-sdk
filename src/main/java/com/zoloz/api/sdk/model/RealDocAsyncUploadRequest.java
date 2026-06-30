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

import java.util.List;

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
     * Product type for the async RealDoc task.
     * Supported values:
     * REALDOC_FORGERY_DETECTION: document forgery detection.
     * REALDOC_DOCUMENT_EXTRACTION: document information extraction.
     * REALDOC_DOCUMENT_INSIGHT: document insight analysis.
     * REALDOC_CROSS_MATCHING: cross matching for two documents. The documents field is required.
     * REALDOC_DOCUMENT_PARSING: document parsing with markdown output.
     *
     * @see RealDocProductType
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
     * Whether to include extended extraction information in the async extraction result.
     * Supported values:
     * Y: include confidence score, bbox coordinates, and page number.
     * N: do not include extended information. This is the default server behavior.
     *
     * This parameter is only effective when productType is REALDOC_DOCUMENT_EXTRACTION.
     * It is not supported for REALDOC_FORGERY_DETECTION, REALDOC_DOCUMENT_INSIGHT,
     * REALDOC_CROSS_MATCHING, or REALDOC_DOCUMENT_PARSING.
     *
     * @see RealDocProductType#REALDOC_DOCUMENT_EXTRACTION
     */
    private String includeExtInfo;

    /**
     * File content (Base64 encoded), max 10MB
     */
    private String fileContent;

    /**
     * File URL address
     */
    private String fileUrl;

    /**
     * Documents used by cross matching
     */
    private List<RealDocDocument> documents;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("bizId=").append(bizId);
        sb.append(",productType=").append(productType);
        sb.append(",fileType=").append(fileType);
        sb.append(",schemaId=").append(schemaId);
        sb.append(",includeExtInfo=").append(includeExtInfo);
        sb.append(",fileContentLength=").append(fileContent == null ? 0 : fileContent.length());
        sb.append(",fileUrl=").append(fileUrl);
        sb.append(",documents=").append(documents);
        return sb.toString();
    }
}
