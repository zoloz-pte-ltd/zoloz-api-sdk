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
 * product result bean
 *
 * @author yaomeng
 */
public class ProductResult {
    /**
     * Product Code
     */
    private String productCode;

    /**
     * Number of verifications performed
     */
    private int validateTimes;

    /**
     * Product verification result. value can list: INITIAL, PASS, PROCESS, FAIL
     */
    private String prodStatus;

    /**
     * Validation time
     */
    private String validateTime;

    /**
     * Additional validation information
     */
    private Map<String, Object> extInfo;

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
     * Getter method for property <tt>validateTimes</tt>.
     *
     * @return property value of validateTimes
     */
    public int getValidateTimes() {
        return validateTimes;
    }

    /**
     * Setter method for property <tt>validateTimes</tt>.
     *
     * @param validateTimes value to be assigned to property validateTimes
     */
    public void setValidateTimes(int validateTimes) {
        this.validateTimes = validateTimes;
    }

    /**
     * Getter method for property <tt>prodStatus</tt>.
     *
     * @return property value of prodStatus
     */
    public String getProdStatus() {
        return prodStatus;
    }

    /**
     * Setter method for property <tt>prodStatus</tt>.
     *
     * @param prodStatus value to be assigned to property prodStatus
     */
    public void setProdStatus(String prodStatus) {
        this.prodStatus = prodStatus;
    }

    /**
     * Getter method for property <tt>validateTime</tt>.
     *
     * @return property value of validateTime
     */
    public String getValidateTime() {
        return validateTime;
    }

    /**
     * Setter method for property <tt>validateTime</tt>.
     *
     * @param validateTime value to be assigned to property validateTime
     */
    public void setValidateTime(String validateTime) {
        this.validateTime = validateTime;
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
}
