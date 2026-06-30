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
import java.util.Map;

/**
 * RealDocSchemaQueryResponse
 * Response containing the current effective RealDoc schema definitions.
 *
 * @author realdoc-biz team
 */
@Data
public class RealDocSchemaQueryResponse extends OpenApiCommonResult {

    /**
     * Schema list returned by the query.
     */
    private List<SchemaItem> schemas;

    /**
     * Schema item with its current effective definition.
     */
    @Data
    public static class SchemaItem {

        /**
         * Schema ID.
         */
        private String schemaId;

        /**
         * Product type of this schema.
         */
        private String productType;

        /**
         * Current effective schema definition data.
         */
        private Map<String, Object> schemaData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("schemas=").append(schemas);
        sb.append(",").append(super.toString());
        return sb.toString();
    }
}
