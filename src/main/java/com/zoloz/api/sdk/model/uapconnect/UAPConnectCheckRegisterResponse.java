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

package com.zoloz.api.sdk.model.uapconnect;

import java.util.HashMap;
import java.util.Map;

import com.zoloz.api.sdk.model.OpenApiCommonResult;
import lombok.Data;

@Data
public class UAPConnectCheckRegisterResponse extends OpenApiCommonResult {

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