/*
 * Copyright (c) 2020 ZOLOZ PTE.LTD.
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

package com.zoloz.api.sdk.model.connectv2;

import java.util.Map;

/**
 * product info bean
 *
 * @author yaomeng
 */
public class ProductInfo {
    /**
     * Value could be:
     * <p>
     * REMOTE_FACE
     * <p>
     * IIFAA_BIO
     */
    private String productCode;

    /**
     * extra product params
     */
    private Map<String, Object> productParams;

    /**
     * Getter method for property <tt>productCode</tt>.
     *
     * @return property value of productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Setter method for property <tt>productCode</tt>.
     *
     * @param productCode value to be assigned to property productCode
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * Getter method for property <tt>productParams</tt>.
     *
     * @return property value of productParams
     */
    public Map<String, Object> getProductParams() {
        return productParams;
    }

    /**
     * Setter method for property <tt>productParams</tt>.
     *
     * @param productParams value to be assigned to property productParams
     */
    public void setProductParams(Map<String, Object> productParams) {
        this.productParams = productParams;
    }

    public static class ExtKeys {

        /**
         * Only available in remote face product. Values can be:
         * <p>
         * the value can be
         * <p>
         * CONNECT0001
         * <p>
         * CONNECT0002 default
         * <p>
         * CONNECT0003
         * <p>
         * CONNECT0004
         */
        public static final String SERVICE_LEVEL = "serviceLevel";

        /**
         * operationMode
         */
        public static final String OPERATION_MODE = "operationMode";
        /**
         * Only applicable for remote face product. used in ekyc user
         * <p>
         * When passed in, ZOLOZ (at site layer) will compare it with current enrolling face, and ensure it's the same person.
         */
        public static final String IMG_CONTENT    = "base64ImageContent";
        /**
         * Only applicable for remote face product. used in ekyc user. The Doc Type, available if base64ImageContent is a doc image (with
         * face). Values can be:
         * <p>
         * 00600000001 (for MyKad)
         * <p>
         * 00600000002: (for Passport)
         * <p>
         * 00600000003: (for MyPolice)
         * <p>
         * 00600000004: (for MyArmy)
         */
        public static final String DOC_TYPE       = "docType";
    }
}
