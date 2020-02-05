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
