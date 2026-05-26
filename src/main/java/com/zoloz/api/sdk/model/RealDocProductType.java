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

/**
 * RealDocProductType
 * RealDoc product type values accepted by async upload.
 *
 * @author realdoc-biz team
 */
public enum RealDocProductType {

    /**
     * RealDoc Forgery Detection
     */
    REALDOC_FORGERY_DETECTION("REALDOC_FORGERY_DETECTION", "RealDoc Forgery Detection"),

    /**
     * RealDoc Document Extraction
     */
    REALDOC_DOCUMENT_EXTRACTION("REALDOC_DOCUMENT_EXTRACTION", "RealDoc Document Extraction"),

    /**
     * RealDoc Cross Matching
     */
    REALDOC_CROSS_MATCHING("REALDOC_CROSS_MATCHING", "RealDoc Cross Matching"),

    /**
     * RealDoc Document Insight
     */
    REALDOC_DOCUMENT_INSIGHT("REALDOC_DOCUMENT_INSIGHT", "RealDoc Document Insight"),

    /**
     * RealDoc Document Parsing (Markdown)
     */
    REALDOC_DOCUMENT_PARSING("REALDOC_DOCUMENT_PARSING", "RealDoc Document Parsing (Markdown)");

    private final String code;

    private final String description;

    RealDocProductType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static RealDocProductType fromCode(String code) {
        for (RealDocProductType productType : RealDocProductType.values()) {
            if (productType.getCode().equals(code)) {
                return productType;
            }
        }
        return null;
    }

    public static String getNameByCode(String code) {
        RealDocProductType productType = fromCode(code);
        return productType != null ? productType.name() : null;
    }

    public static String getCodeByName(String name) {
        if (name == null || name.length() == 0) {
            return null;
        }
        try {
            return RealDocProductType.valueOf(name).getCode();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static RealDocProductType valueOfOrNull(String name) {
        if (name == null || name.length() == 0) {
            return null;
        }
        try {
            return RealDocProductType.valueOf(name);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static boolean isValidProductType(String productType) {
        return valueOfOrNull(productType) != null;
    }
}
