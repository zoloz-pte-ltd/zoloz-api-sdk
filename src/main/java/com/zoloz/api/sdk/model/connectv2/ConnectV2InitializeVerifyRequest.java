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

import java.util.List;

/**
 * request bean
 *
 * @author the
 */
public class ConnectV2InitializeVerifyRequest {

    /**
     * Uniquely generated, globally unique business ID, for tracing. e.g. Sequence ID from DB
     */
    private String bizId;

    /**
     * Meta info from client
     */
    private String clientData;

    /**
     * Merchant user id
     */
    private String userId;

    /**
     * Business scene for data analysis purpose, no real business impact. If you want to distinguish the data performance of different
     * scenarios, we suggest setting sceneCode to different value. It is recommended to name sceneCode's value according to business
     * purpose. For example:registration,withdraw,topUp,transfer,changePassword.
     */
    private String sceneCode;

    /**
     * It may has one or more group of methods combinations, and each combination may has one or more authentication methods.
     */
    private List<List<ProductInfo>> validateFlows;

    /**
     * Wireless config group name. This field is used for looking up wireless configs. Wireless config includes mobile gateway url, h5 pages
     * url, etc. This field is required if there exists multiple environments and wireless config differs in each environment.
     */
    private String wirelessConfigGroup;

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
     * Getter method for property <tt>sceneCode</tt>.
     *
     * @return property value of sceneCode
     */
    public String getSceneCode() {
        return sceneCode;
    }

    /**
     * Setter method for property <tt>sceneCode</tt>.
     *
     * @param sceneCode value to be assigned to property sceneCode
     */
    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    /**
     * Getter method for property <tt>validateFlows</tt>.
     *
     * @return property value of validateFlows
     */
    public List<List<ProductInfo>> getValidateFlows() {
        return validateFlows;
    }

    /**
     * Setter method for property <tt>validateFlows</tt>.
     *
     * @param validateFlows value to be assigned to property validateFlows
     */
    public void setValidateFlows(List<List<ProductInfo>> validateFlows) {
        this.validateFlows = validateFlows;
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
