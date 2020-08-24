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

package com.zoloz.example.servermode;

import com.zoloz.api.sdk.api.FaceSearchAPI;
import com.zoloz.api.sdk.api.UserGroupInAPI;
import com.zoloz.api.sdk.api.UserGroupOutAPI;
import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.*;
import com.zoloz.example.util.KeyUtil;
import lombok.SneakyThrows;
import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

/**
 * Example of Face Search
 * In this demo, users can add face images into a specific group.
 * Then, users can search face images similar to an uploaded image.
 * After that, the program will automatically remove searched face images from the specific group.
 * Finally, the program will search the face image again in the group.
 * @author youcha
 * @date 2020/7/31 13:51
 */

public class FaceSearchExample {

    static Logger logger = LoggerFactory.getLogger(FaceSearchExample.class);
    static OpenApiClient client=new OpenApiClient();

    public static void main(String[] args) {

        // create Options object
        Options options = new Options();
        options.addOption("c", true, "The client id");
        options.addOption("p", true, "The base64 content of the zoloz public key");
        options.addOption("k", true, "The path of the merchant private key");
        options.addOption("u",true,"the path of face images to be uploaded (Separated by commas)");
        options.addOption("s",true,"the path of face image to be searched");
        options.addOption(new Option("e", true, "The endpoint of the zoloz service"){{
            setRequired(false);
        }});
        CommandLine cmd = null;
        try {
            cmd = new DefaultParser().parse(options, args);
        }
        catch (ParseException ex) {
            // automatically generate the help statement
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "facesearch " +
                            "-c <client_id> " +
                            "-p <zoloz_public_key_content> " +
                            "-k <merchant_private_key_path> " +
                            "-u <upload_face_images_path> " +
                            "-s <search_face_image_path> " +
                            "[-e <zoloz_service_endpoint>]",
                    options );
            System.exit(-1);
        }

        // initialize OpenApiClient
        String clientId =cmd.getOptionValue("c");
        String zolozPublicKey=cmd.getOptionValue("p");
        String merchantPrivateKeyPath = cmd.getOptionValue("k");
        String merchantPrivateKey = KeyUtil.loadKeyContent(merchantPrivateKeyPath);
        String endpointUrl = cmd.getOptionValue("e", "https://sg-production-api.zoloz.com");
        String uploadImagePaths[] = cmd.getOptionValue("u").split(",");
        String searchImagePath = cmd.getOptionValue("s");
        client.setHostUrl(endpointUrl);
        client.setClientId(clientId);
        client.setMerchantPrivateKey(merchantPrivateKey);
        client.setOpenApiPublicKey(zolozPublicKey);
        client.setSigned(true);     // signature (of response) validation can be turned off
        //client.setEncrypted(false);  // encryption can be turned off

        //userGroupIn
        System.out.println("Add the face image in the specific group.");
        for(int i = 0; i < uploadImagePaths.length; i++){
            String uid = "test_userId_"+String.valueOf(i+1);
            String groupId_in = "default";
            String uploadImagePath = uploadImagePaths[i];
            String faceType = "image";
            userGroupIn(uid, groupId_in, uploadImagePath, faceType);
            System.out.println("Successfully upload the image ( " + "uid: "+uid + ", group: "+groupId_in + ", facetype: "+faceType + " )");
        }

        //faceSearch
        System.out.println("Search the face image in the specific group.");
        String faceType = "image";
        String groupId_search = "default";
        String score = "70";
        List<FaceSearchUserInfo>faceSearchUserInfoList = faceSearch(searchImagePath,faceType,groupId_search,score);
        for(int i = 0;i < faceSearchUserInfoList.size();i++){
            System.out.println("the uid of searched image:  "+faceSearchUserInfoList.get(i).getUid());
            System.out.println("the comparison score of searched image: "+faceSearchUserInfoList.get(i).getScore());
        }

        //faceSearch after userGroupOut
        System.out.println("Remove searched face images");
        String groupId_out = "default";
        for(int i = 0;i < faceSearchUserInfoList.size();i++){
            String uid = faceSearchUserInfoList.get(i).getUid();
            userGroupOut(groupId_out, uid);
            System.out.println("Successfully remove the face image("+"uid: "+uid+", group: "+groupId_out+")");
        }
        List<FaceSearchUserInfo>userInfoAfterOut = faceSearch(searchImagePath,faceType,groupId_search,score);
        System.out.println("the size of the searched uidList after out:"+userInfoAfterOut.size());
    }

    /**
     * add user's face into a specific group.
     * @param uid unique user id
     * @param groupId face group
     * @param uploadImagePath face image path
     * @param faceType face type
     */
    public static void userGroupIn(String uid, String groupId, String uploadImagePath, String faceType){
        //initialize UserGroupAPI
        UserGroupInAPI userGroupInAPI = new UserGroupInAPI(client);

        //prepare api request
        UserGroupInRequest userGroupInRequest = new UserGroupInRequest();
        userGroupInRequest.setUid(uid);
        userGroupInRequest.setGroupId(groupId);
        userGroupInRequest.setFace(getBase64ImageContent(uploadImagePath));
        userGroupInRequest.setFaceType(faceType);

        //call api
        UserGroupInResponse response=userGroupInAPI.in(userGroupInRequest);
        logger.info(response.toString());
    }

    /**
     * delete user's face from a specific group.
     * @param groupId face group
     * @param uid unique user id
     */
    public static void userGroupOut(String groupId, String uid)
    {
        //initialize UserGroupOut API
        UserGroupOutAPI userGroupOutAPI = new UserGroupOutAPI(client);

        //prepare api request
        UserGroupOutRequest userGroupOutRequest = new UserGroupOutRequest();
        userGroupOutRequest.setGroupId(groupId);
        userGroupOutRequest.setUid(uid);
        //call api
        UserGroupOutResponse response = userGroupOutAPI.out(userGroupOutRequest);
        logger.info(response.toString());
    }

    /**
     * search user's face into a specific group.
     * @param imagepath face image path
     * @param faceType face type
     * @param groupId face group
     * @param score comparison score, similar search result should be greater than or equals to this comparison score
     * @return the information of searched group users.
     */
    public static List<FaceSearchUserInfo> faceSearch(String imagepath, String faceType, String groupId, String score)
    {
        //initialize FaceSearchAPI
        FaceSearchAPI faceSearchAPI = new FaceSearchAPI(client);
        //prepare api request
        FaceSearchRequest faceSearchRequest = new FaceSearchRequest();
        faceSearchRequest.setFace(getBase64ImageContent(imagepath));
        faceSearchRequest.setFaceType(faceType);
        faceSearchRequest.setGroupId(groupId);
        faceSearchRequest.setScore(score);

        //call on faceSearch api
        FaceSearchResponse response = faceSearchAPI.search(faceSearchRequest);
        logger.info(response.toString());
        return response.getUidList();
    }

    /**
     * get content of the image file
     * @param imagePath path of the image file
     * @return base64 encoded content of the image file
     * @throws IOException
     */
    @SneakyThrows(IOException.class)
    protected static String getBase64ImageContent(String imagePath) {
        byte[] content = FileUtils.readFileToByteArray(new File(imagePath));
        return Base64.getEncoder().encodeToString(content);
    }
}
