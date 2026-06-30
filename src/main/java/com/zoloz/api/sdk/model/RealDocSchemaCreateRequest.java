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

import java.util.Map;

/**
 * RealDocSchemaCreateRequest
 * Request for creating a RealDoc document extraction schema.
 *
 * @author realdoc-biz team
 */
@Data
public class RealDocSchemaCreateRequest {

    /**
     * Business ID used to distinguish merchant requests or tasks.
     */
    private String bizId;

    /**
     * Schema ID, unique under the same merchant.
     * Supported characters are letters, numbers, underscores and hyphens.
     */
    private String schemaId;

    /**
     * Product type of this schema.
     * Currently only REALDOC_DOCUMENT_EXTRACTION is supported.
     *
     * @see RealDocProductType
     */
    private String productType;

    /**
     * Schema definition data.
     * The value must be a JSON Schema Draft 7 object with the supported field whitelist.
     */
    private Map<String, Object> schemaData;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("bizId=").append(bizId);
        sb.append(",schemaId=").append(schemaId);
        sb.append(",productType=").append(productType);
        sb.append(",schemaData=").append(schemaData);
        return sb.toString();
    }
}
