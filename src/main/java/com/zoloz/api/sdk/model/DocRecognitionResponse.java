/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.zoloz.api.sdk.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chenzc
 * @version $Id: DocVerificationResponse.java, v 0.1 2019年11月12日 11:41 chenzc Exp $
 */
@Data
public class DocRecognitionResponse extends OpenApiCommonResult{

    private String transactionId;

    private Map<String,String> ocrResult = new HashMap<>();

    private String  recognitionResult;

    private String  recognitionErrorCode;

    private Map<String,String> spoofResult = new HashMap<>();

}