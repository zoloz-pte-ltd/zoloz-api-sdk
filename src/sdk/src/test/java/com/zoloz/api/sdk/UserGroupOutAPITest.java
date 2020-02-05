package com.zoloz.api.sdk;

import com.zoloz.api.sdk.api.UserGroupOutAPI;
import com.zoloz.api.sdk.model.UserGroupOutRequest;
import com.zoloz.api.sdk.model.UserGroupOutResponse;
import org.junit.Test;

/**
 * Create by yaomeng on 2020-02-05 13:37
 *
 * @author yaomeng
 */
public class UserGroupOutAPITest extends ApiBaseTest {
    @Test
    public void groupOut() {
        UserGroupOutAPI userGroupOutAPI = new UserGroupOutAPI(getClient());

        UserGroupOutRequest request = new UserGroupOutRequest();
        request.setGroupId("default");
        request.setUid("28302310022");

        UserGroupOutResponse response = userGroupOutAPI.out(request);
        System.out.println(response);
    }
}
