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

import lombok.Data;

import java.util.List;

/**
 * RealDocCrossMatchingResponse
 * RealDoc Cross Matching Response.
 *
 * @author realdoc-biz team
 */
@Data
public class RealDocCrossMatchingResponse extends OpenApiCommonResult {

    /**
     * Transaction ID
     */
    private String transactionId;

    /**
     * Overall match result, for example True, False, or None.
     */
    private String overallMatchResult;

    /**
     * Overall match score, range 0-100.
     */
    private Double overallMatchScore;

    /**
     * Executive summary.
     */
    private String executiveSummary;

    /**
     * Matching details.
     */
    private List<MatchInfo> matchingInfo;

    @Data
    public static class MatchInfo {
        /**
         * Matching field name.
         */
        private String field;

        /**
         * First value.
         */
        private String value1;

        /**
         * Second value.
         */
        private String value2;

        /**
         * Explanation.
         */
        private String explanation;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("transactionId=").append(transactionId);
        sb.append(",overallMatchResult=").append(overallMatchResult);
        sb.append(",overallMatchScore=").append(overallMatchScore);
        sb.append(",executiveSummary=").append(executiveSummary);
        sb.append(",matchingInfo=").append(matchingInfo);
        sb.append(",").append(super.toString());
        return sb.toString();
    }
}
