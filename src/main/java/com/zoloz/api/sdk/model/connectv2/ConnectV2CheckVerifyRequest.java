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
 * request bean
 *
 * @author the
 */
public class ConnectV2CheckVerifyRequest {

    /**
     * Uniquely generated, globally unique business ID, for tracing. e.g. Sequence ID from DB
     */
    private String bizId;

    /**
     * unique ID that used to start ekyc SDK in client side. Server side need to pass this to calling client for further processing
     */
    private String transactionId;

    /**
     * extra infos
     */
    private Map<String, String> extInfo;

    /**
     * Getter method for property <tt>bizId</tt>.
     *
     * @return property value of bizId
     */
    public String getBizId() {
        return bizId;
    }

    /**
     * Setter method for property <tt>bizId</tt>.
     *
     * @param bizId value to be assigned to property bizId
     */
    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    /**
     * Getter method for property <tt>transactionId</tt>.
     *
     * @return property value of transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Setter method for property <tt>transactionId</tt>.
     *
     * @param transactionId value to be assigned to property transactionId
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Getter method for property <tt>extInfo</tt>.
     *
     * @return property value of extInfo
     */
    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    /**
     * Setter method for property <tt>extInfo</tt>.
     *
     * @param extInfo value to be assigned to property extInfo
     */
    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}
