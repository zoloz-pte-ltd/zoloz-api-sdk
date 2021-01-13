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

import java.util.HashMap;
import java.util.Map;

/**
 * product status
 *
 * @author the
 */
public class ProductStatus {
    /**
     * Product Code
     */
    private String productCode;

    /**
     * Whether the device supports
     */
    private boolean support;

    /**
     * Additional available information
     */
    private Map<String, Object> extInfo = new HashMap<>(10);

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
     * Getter method for property <tt>support</tt>.
     *
     * @return property value of support
     */
    public boolean isSupport() {
        return support;
    }

    /**
     * Setter method for property <tt>support</tt>.
     *
     * @param support value to be assigned to property support
     */
    public void setSupport(boolean support) {
        this.support = support;
    }

    /**
     * Getter method for property <tt>extInfo</tt>.
     *
     * @return property value of extInfo
     */
    public Map<String, Object> getExtInfo() {
        return extInfo;
    }

    /**
     * Setter method for property <tt>extInfo</tt>.
     *
     * @param extInfo value to be assigned to property extInfo
     */
    public void setExtInfo(Map<String, Object> extInfo) {
        this.extInfo = extInfo;
    }

    public static class ExtKeys {
        /**
         * Only available in iifaa product. a string of numbers and letters that identifies every individual smartphone or tablet in the
         * world.
         */
        public static final String DEVICE_ID = "deviceId";

        /**
         * Only available in iifaa product.
         */
        public static final String SPECIFIC_PRODUCT = "specificProduct";
    }
}