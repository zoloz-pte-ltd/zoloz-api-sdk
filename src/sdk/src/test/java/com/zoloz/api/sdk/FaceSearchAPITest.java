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

import com.zoloz.api.sdk.api.FaceSearchAPI;
import com.zoloz.api.sdk.model.FaceInfo;
import com.zoloz.api.sdk.model.FaceSearchRequest;
import com.zoloz.api.sdk.model.FaceSearchResponse;
import org.junit.Test;

/**
 * Create by yaomeng on 2020-02-05 13:44
 *
 * @author yaomeng
 */
public class FaceSearchAPITest extends ApiBaseTest {
    // image
    private final static String FACE_URL = "/images/face/face1.jpg";

    @Test
    public void search() {
        FaceSearchAPI faceSearchAPI = new FaceSearchAPI(getClient());

        FaceSearchRequest request = new FaceSearchRequest();
        request.setGroupId("default");
        request.setFaceType("image");
        FaceInfo face = new FaceInfo();
        face.setFaceContent(getBase64ImageContent(FACE_URL));
        request.setFace(face);
        request.setScore("85");
        request.setTop(5);

        FaceSearchResponse response = faceSearchAPI.search(request);
        System.out.println(response);
    }
}
