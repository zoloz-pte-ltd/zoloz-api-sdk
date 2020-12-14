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

/**
 * request bean
 *
 * @author the
 */
public class ConnectV2InitializeRegisterRequest {
    /**
     * Merchant user id
     */
    private String userId;

    /**
     * ready to register product name
     */
    private ProductInfo productInfo;

    /**
     * unique business ID
     */
    private String bizId;

    /**
     * Client environment data from client, including device model, language, OS type, etc,.
     */
    private String clientData;

    /**
     * Wireless config group name. This field is used for looking up wireless configs. Wireless config includes mobile gateway url, h5 pages
     * url, etc. This field is required if there exists multiple environments and wireless config differs in each environment.
     */
    private String wirelessConfigGroup;

    /**
     * Getter method for property <tt>userId</tt>.
     *
     * @return property value of userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Setter method for property <tt>userId</tt>.
     *
     * @param userId value to be assigned to property userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Getter method for property <tt>productInfo</tt>.
     *
     * @return property value of productInfo
     */
    public ProductInfo getProductInfo() {
        return productInfo;
    }

    /**
     * Setter method for property <tt>productInfo</tt>.
     *
     * @param productInfo value to be assigned to property productInfo
     */
    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

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
     * Getter method for property <tt>clientData</tt>.
     *
     * @return property value of clientData
     */
    public String getClientData() {
        return clientData;
    }

    /**
     * Setter method for property <tt>clientData</tt>.
     *
     * @param clientData value to be assigned to property clientData
     */
    public void setClientData(String clientData) {
        this.clientData = clientData;
    }

    /**
     * Getter method for property <tt>wirelessConfigGroup</tt>.
     *
     * @return property value of wirelessConfigGroup
     */
    public String getWirelessConfigGroup() {
        return wirelessConfigGroup;
    }

    /**
     * Setter method for property <tt>wirelessConfigGroup</tt>.
     *
     * @param wirelessConfigGroup value to be assigned to property wirelessConfigGroup
     */
    public void setWirelessConfigGroup(String wirelessConfigGroup) {
        this.wirelessConfigGroup = wirelessConfigGroup;
    }
}