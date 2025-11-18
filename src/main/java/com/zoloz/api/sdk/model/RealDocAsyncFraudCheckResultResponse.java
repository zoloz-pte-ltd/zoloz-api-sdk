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

import java.util.List;

import lombok.Data;

/**
 * RealDocAsyncFraudCheckResultResponse
 * Real Document Async Fraud Check Result Response
 *
 * @author yirong
 */
@Data
public class RealDocAsyncFraudCheckResultResponse extends OpenApiCommonResult {

    /**
     * Transaction ID
     */
    private String transactionId;

    /**
     * Fraud check result summary
     */
    private Summary summary;

    /**
     * Tamper information
     */
    private List<TamperInfo> tamperInfo;

    @Data
    public static class Summary {
        /**
         * Overall risk score (0-100)
         */
        private Double overallRiskScore;

        /**
         * Executive summary of results
         */
        private String executiveSummary;
    }

    @Data
    public static class TamperInfo {
        /**
         * Page number
         */
        private Integer page;
        /**
         * Content
         */
        private String content;

        /**
         * Location coordinates [x1, y1, x2, y2]
         * Represents the bounding box of the tampered content
         */
        private Double[] location;

        /**
         * Explanation
         */
        private String explanation;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("transactionId=").append(transactionId);
        sb.append(",summary=").append(summary);
        sb.append(",tamperInfo=").append(tamperInfo);
        // 添加父类的toString内容
        sb.append(",").append(super.toString());
        return sb.toString();
    }
}

