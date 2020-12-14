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

import java.util.List;

/**
 * result bean
 *
 * @author the
 */
public class ConnectV2CheckVerifyResponse extends OpenApiCommonResult {

    /**
     * verification result. Value can be INITIAL, PROCESSING, SUCCESS, FAIL, EXPIRED
     */
    private String verifyStatus;

    /**
     * each product verify result
     */
    private List<List<ProductResult>> validateResult;

    /**
     * Getter method for property <tt>verifyStatus</tt>.
     *
     * @return property value of verifyStatus
     */
    public String getVerifyStatus() {
        return verifyStatus;
    }

    /**
     * Setter method for property <tt>verifyStatus</tt>.
     *
     * @param verifyStatus value to be assigned to property verifyStatus
     */
    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    /**
     * Getter method for property <tt>validateResult</tt>.
     *
     * @return property value of validateResult
     */
    public List<List<ProductResult>> getValidateResult() {
        return validateResult;
    }

    /**
     * Setter method for property <tt>validateResult</tt>.
     *
     * @param validateResult value to be assigned to property validateResult
     */
    public void setValidateResult(List<List<ProductResult>> validateResult) {
        this.validateResult = validateResult;
    }
}
