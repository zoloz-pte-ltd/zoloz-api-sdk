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
public class ConnectV2FaceEnrollRequest {
    /**
     * unique business ID
     */
    private String bizId;

    /**
     * Merchant user id
     */
    private String userId;

    /**
     * Image content using base64 encode
     */
    private String base64ImageContent;

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
     * Getter method for property <tt>base64ImageContent</tt>.
     *
     * @return property value of base64ImageContent
     */
    public String getBase64ImageContent() {
        return base64ImageContent;
    }

    /**
     * Setter method for property <tt>base64ImageContent</tt>.
     *
     * @param base64ImageContent value to be assigned to property base64ImageContent
     */
    public void setBase64ImageContent(String base64ImageContent) {
        this.base64ImageContent = base64ImageContent;
    }
}