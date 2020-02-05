package com.zoloz.api.sdk;

import com.zoloz.api.sdk.api.UserGroupInAPI;
import com.zoloz.api.sdk.model.UserGroupInRequest;
import com.zoloz.api.sdk.model.UserGroupInResponse;
import com.zoloz.api.sdk.model.FaceInfo;
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
        FaceInfo face = new FaceInfo();
        face.setFaceContent(getBase64ImageContent(FACE_URL));
        request.setFace(face);
        request.setExtInfo("{\"phone\":\"1234567890\"}");

        UserGroupInResponse response = userGroupInAPI.in(request);
        System.out.println(response);
    }
}
