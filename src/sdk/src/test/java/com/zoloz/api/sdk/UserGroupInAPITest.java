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

package com.zoloz.api.sdk;

import com.zoloz.api.sdk.api.UserGroupInAPI;
import com.zoloz.api.sdk.model.UserGroupInRequest;
import com.zoloz.api.sdk.model.UserGroupInResponse;
import org.junit.Test;

/**
 * Create by yaomeng on 2020-02-05 13:31
 *
 * @author yaomeng
 */
public class UserGroupInAPITest extends ApiBaseTest {
    // image
    private final static String FACE_URL = "/images/face/face1.jpg";

    @Test
    public void groupIn() {
        UserGroupInAPI userGroupInAPI = new UserGroupInAPI(getClient());

        UserGroupInRequest request = new UserGroupInRequest();
        request.setGroupId("default");
        request.setUid("28302310022");
        request.setFaceType("image");
        request.setFace(getBase64ImageContent(FACE_URL));
        request.setExtInfo("{\"phone\":\"1234567890\"}");

        UserGroupInResponse response = userGroupInAPI.in(request);
        System.out.println(response);
    }
}
