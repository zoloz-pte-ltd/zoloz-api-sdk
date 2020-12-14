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

import com.zoloz.api.sdk.model.OpenApiCommonResult;

import java.util.HashMap;
import java.util.Map;

/**
 * result bean
 *
 * @author the
 * @version : CloseResult.java, v 0.1 2020年09月11日 2:26 下午 the Exp $
 */
public class ConnectV2CloseResponse extends OpenApiCommonResult {

    /**
     * Additional close data
     */
    private Map<String, Object> extCloseInfo = new HashMap<>(10);

    /**
     * Getter method for property <tt>extCloseInfo</tt>.
     *
     * @return property value of extCloseInfo
     */
    public Map<String, Object> getExtCloseInfo() {
        return extCloseInfo;
    }

    /**
     * Setter method for property <tt>extCloseInfo</tt>.
     *
     * @param extCloseInfo value to be assigned to property extCloseInfo
     */
    public void setExtCloseInfo(Map<String, Object> extCloseInfo) {
        this.extCloseInfo = extCloseInfo;
    }

    public static class ExtKeys {
        public static final String DEVICE_ID = "deviceId";
    }
}