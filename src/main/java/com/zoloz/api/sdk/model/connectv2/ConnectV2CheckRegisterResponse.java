/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.zoloz.api.sdk.model.connectv2;

import com.zoloz.api.sdk.model.OpenApiCommonResult;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * result bean
 *
 * @author the
 * @version : RegisterResult.java, v 0.1 2020年09月11日 2:13 下午 the Exp $
 */
@Data
@ToString(callSuper = true)
public class ConnectV2CheckRegisterResponse extends OpenApiCommonResult {

    /**
     * Additional register data
     */
    private Map<String, Object> extRegisterInfo = new HashMap<>(10);

    public static class ExtKeys {

        public static final String IMAGE_CONTENT = "imageContent";
        public static final String FACE_ATTACK   = "faceAttack";
        public static final String RECT          = "rect";
        public static final String QUALITY       = "quality";
        public static final String RETRY_COUNT   = "retryCount";

        public static final String DEVICE_ID = "deviceId";
    }
}