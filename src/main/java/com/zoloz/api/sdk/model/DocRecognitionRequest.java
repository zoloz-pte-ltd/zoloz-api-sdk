/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.zoloz.api.sdk.model;

import lombok.Data;

/**
 * @author chenzc
 * @version $Id: DocVerificationRequest.java, v 0.1 2019年11月12日 11:40 chenzc Exp $
 */
@Data
public class DocRecognitionRequest {
    private String bizId;
    private String docType;
    private String frontPageImage;
    private String backPageImage;
    private String productLevel;
    private String operationMode;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("bizId=").append(bizId);
        sb.append(",docType=").append(docType);
        sb.append(",frontPageImageLength=").append(frontPageImage == null ? 0 : frontPageImage.length());
        sb.append(",backPageImageLength=").append(backPageImage == null ? 0 : backPageImage.length());
        sb.append(",productLevel=").append(productLevel);
        sb.append(",operationMode=").append(operationMode);
        return sb.toString();
    }
}