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

package com.zoloz.example.clientmode.controller;

import javax.annotation.PostConstruct;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.example.clientmode.autoconfig.RealIdConfig;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * client mode controller
 *
 * @Author: Zhongyang MA
 * @Date: 2020-01-02 15:38
 */
@CrossOrigin
@RestController
@RequestMapping(value = {"/api", "/webapi"})
public class NativeClientModeController {

    private static final Logger logger = LoggerFactory.getLogger(NativeClientModeController.class);

    @Autowired
    private OpenApiClient openApiClient;

    @Autowired
    private RealIdConfig realIdConfig;

    @PostConstruct
    public void init(){
        logger.info("docType={},serviceLevel={}",realIdConfig.getDocType(),realIdConfig.getServiceLevel());
    }

    @RequestMapping(value = {"/realid/initialize","/realIdDemoService/initialize"}, method = RequestMethod.POST)
    public JSONObject realIdInit(@RequestBody JSONObject request) {

        logger.info("request=" + request);

        String metaInfo = request.getString("metaInfo");

        String docType = request.getString("docType");
        if (StringUtils.isBlank(docType)) {
            docType = realIdConfig.getDocType();
        }
        String serviceLevel = request.getString("serviceLevel");
        if (StringUtils.isBlank(serviceLevel)) {
            serviceLevel = realIdConfig.getServiceLevel();
        }

        String businessId = "dummy_bizid_" + System.currentTimeMillis();
        String userId = "dummy_userid_" + System.currentTimeMillis();

        JSONObject apiReq = new JSONObject();
        apiReq.put("bizId", businessId);
        apiReq.put("flowType", "REALIDLITE_KYC");
        apiReq.put("docType", docType);
        //apiReq.put("pages", "1");
        apiReq.put("metaInfo", metaInfo);
        apiReq.put("userId", userId);
        if(StringUtils.isNotBlank(serviceLevel)){
            apiReq.put("serviceLevel",serviceLevel);
        }

        String apiRespStr = openApiClient.callOpenApi(
                "v1.zoloz.realid.initialize",
                JSON.toJSONString(apiReq)
        );

        JSONObject apiResp = JSON.parseObject(apiRespStr);

        JSONObject response = new JSONObject(apiResp);
        response.put("rsaPubKey", openApiClient.getOpenApiPublicKey());
        response.put("transactionId", apiResp.getString("transactionId"));
        response.put("clientCfg", apiResp.getString("clientCfg"));
        logger.info("response=" + apiRespStr);

        return response;
    }


    @RequestMapping(value = {"/facecapture/initialize"}, method = RequestMethod.POST)
    public JSONObject facecaptureInit(@RequestBody JSONObject request) {

        logger.info("request=" + request);

        String metaInfo = request.getString("metaInfo");

        String businessId = "dummy_bizid_" + System.currentTimeMillis();
        String userId = "dummy_userid_" + System.currentTimeMillis();

        JSONObject apiReq = new JSONObject();
        apiReq.put("bizId", businessId);
        apiReq.put("metaInfo", metaInfo);
        apiReq.put("merchantUserId", userId);

        String apiRespStr = openApiClient.callOpenApi(
                "v1.zoloz.facecapture.initialize",
                JSON.toJSONString(apiReq)
        );

        JSONObject apiResp = JSON.parseObject(apiRespStr);

        JSONObject response = new JSONObject(apiResp);
        response.put("rsaPubKey", openApiClient.getOpenApiPublicKey());
        response.put("transactionId", apiResp.getString("transactionId"));
        response.put("clientCfg", apiResp.getString("clientCfg"));
        logger.info("response=" + apiRespStr);

        return response;
    }


    @RequestMapping(value = {"/idrecognition/initialize"}, method = RequestMethod.POST)
    public JSONObject docInit(@RequestBody JSONObject request) {

        logger.info("request=" + request);

        String metaInfo = request.getString("metaInfo");

        String docType = request.getString("docType");
        if (StringUtils.isBlank(docType)) {
            docType = realIdConfig.getDocType();
        }

        String businessId = "dummy_bizid_" + System.currentTimeMillis();
        String userId = "dummy_userid_" + System.currentTimeMillis();
        if (docType == null) {
            docType = realIdConfig.getDocType();
        }

        JSONObject apiReq = new JSONObject();
        apiReq.put("bizId", businessId);
        apiReq.put("metaInfo", metaInfo);
        apiReq.put("merchantUserId", userId);
        apiReq.put("docType", docType);
        apiReq.put("pages","1");
        String apiRespStr = openApiClient.callOpenApi(
                "v1.zoloz.idrecognition.initialize",
                JSON.toJSONString(apiReq)
        );

        JSONObject apiResp = JSON.parseObject(apiRespStr);

        JSONObject response = new JSONObject(apiResp);
        response.put("transactionId", apiResp.getString("transactionId"));
        response.put("clientCfg", apiResp.getString("clientCfg"));
        logger.info("response=" + apiRespStr);

        return response;
    }


    @RequestMapping(value = "/realid/checkresult", method = RequestMethod.POST)
    public JSONObject realIdCheck(@RequestBody JSONObject request) {

        logger.info("request=" + request);

        String businessId = "dummy_bizid_" + System.currentTimeMillis();
        String transactionId = request.getString("transactionId");
        String isReturnImage = request.getString("isReturnImage");

        JSONObject apiReq = new JSONObject();
        apiReq.put("bizId", businessId);
        apiReq.put("transactionId", transactionId);
        apiReq.put("isReturnImage", isReturnImage);

        String apiRespStr = openApiClient.callOpenApi(
                "v1.zoloz.realid.checkresult",
                JSON.toJSONString(apiReq)
        );

        JSONObject apiResp = JSON.parseObject(apiRespStr);

        JSONObject response = new JSONObject(apiResp);
        return response;
    }


    @RequestMapping(value = "/facecapture/checkresult", method = RequestMethod.POST)
    public JSONObject facecaptureCheck(@RequestBody JSONObject request) {

        logger.info("request=" + request);

        String businessId = "dummy_bizid_" + System.currentTimeMillis();
        String transactionId = request.getString("transactionId");

        JSONObject apiReq = new JSONObject();
        apiReq.put("bizId", businessId);
        apiReq.put("transactionId", transactionId);

        String apiRespStr = openApiClient.callOpenApi(
                "v1.zoloz.facecapture.checkresult",
                JSON.toJSONString(apiReq)
        );

        JSONObject apiResp = JSON.parseObject(apiRespStr);

        JSONObject response = new JSONObject(apiResp);
        return response;
    }


    @RequestMapping(value = "/idrecognition/checkresult", method = RequestMethod.POST)
    public JSONObject docCheck(@RequestBody JSONObject request) {

        logger.info("request=" + request);

        String businessId = "dummy_bizid_" + System.currentTimeMillis();
        String transactionId = request.getString("transactionId");

        JSONObject apiReq = new JSONObject();
        apiReq.put("bizId", businessId);
        apiReq.put("transactionId", transactionId);

        String apiRespStr = openApiClient.callOpenApi(
                "v1.zoloz.idrecognition.checkresult",
                JSON.toJSONString(apiReq)
        );

        JSONObject apiResp = JSON.parseObject(apiRespStr);

        JSONObject response = new JSONObject(apiResp);
        return response;
    }


    @RequestMapping(value = "/privacyinfo/delete", method = RequestMethod.POST)
    public JSONObject privacyInfoDelete(@RequestBody JSONObject request) {

        logger.info("request=" + request);

        String businessId = "dummy_bizid_" + System.currentTimeMillis();
        String transactionId = request.getString("transactionId");

        JSONObject apiReq = new JSONObject();
        apiReq.put("bizId", businessId);
        apiReq.put("transactionId", transactionId);

        String apiRespStr = openApiClient.callOpenApi(
                "v1.zoloz.privacyinfo.delete",
                JSON.toJSONString(apiReq)
        );

        JSONObject apiResp = JSON.parseObject(apiRespStr);

        JSONObject response = new JSONObject(apiResp);
        return response;
    }
}
