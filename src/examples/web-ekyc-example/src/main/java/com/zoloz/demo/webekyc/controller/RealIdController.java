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

package com.zoloz.demo.webekyc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zoloz.api.sdk.client.OpenApiClient;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * realid api controller
 *
 * @Author: jushi
 * @Date: 2020-02-28 22:05
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class RealIdController {

    private static final Logger logger = LoggerFactory.getLogger(RealIdController.class);

    @Data
    private static class RealIdContext {
        private String bizId;
        private String docTxId;
        private String faceTxId;
        private String faceCompareTxId;
        private String merchantUserId;

        private String docImg;
        private String docImgRect;

        private String faceImg;
        private String faceImgRect;

        private Boolean samePerson;
        private double score;
        private Boolean risk;
    }

    private Map<String, RealIdContext> contextMap = new HashMap<>();

    @Autowired
    private OpenApiClient openApiClient;

    @RequestMapping(value = "/initdoc", method = RequestMethod.POST)
    public JSONObject initDoc(@RequestBody JSONObject request) {

        logger.info("request=" + request);

        String docType = request.getString("docType");
        String bizId = String.valueOf(System.currentTimeMillis());

        JSONObject apiReq = new JSONObject();
        apiReq.put("bizId", bizId);
        apiReq.put("docType", docType);
        apiReq.put("pages", "1");
        apiReq.put("metaInfo", "MOB_H5");
        apiReq.put("merchantUserId", "fixed-test-id");

        String apiRespStr = openApiClient.callOpenApi(
                "v1.zoloz.idrecognition.initialize",
                JSON.toJSONString(apiReq)
        );
        JSONObject apiResp = JSON.parseObject(apiRespStr);

        String transactionId = apiResp.getString("transactionId");
        String clientCfg = apiResp.getString("clientCfg");

        // save context
        RealIdContext context = new RealIdContext();
        context.setBizId(bizId);
        context.setDocTxId(transactionId);
        contextMap.put(bizId, context);

        // prepare api response
        JSONObject response = new JSONObject();
        response.put("bizId", bizId);
        response.put("clientCfg", clientCfg);

        if("S".equals(apiResp.getJSONObject("result").getString("resultStatus"))) {
            response.put("success", true);
        } else {
            response.put("success", false);
        }

        logger.info("init doc response=" + response);
        return response;
    }

    @RequestMapping(value = "/checkdoc", method = RequestMethod.POST)
    public JSONObject checkDoc(@RequestBody JSONObject request) {

        logger.info("request=" + request);

        String bizId = request.getString("bizId");
        RealIdContext context = contextMap.get(bizId);
        String transactionId = context.getDocTxId();

        JSONObject apiReq = new JSONObject();
        apiReq.put("bizId", bizId);
        apiReq.put("transactionId", transactionId);

        String apiRespStr = openApiClient.callOpenApi(
                "v1.zoloz.idrecognition.checkresult", JSON.toJSONString(apiReq));
        JSONObject apiResp = JSON.parseObject(apiRespStr);

        JSONObject response = new JSONObject();
        if("S".equals(apiResp.getJSONObject("result").getString("resultStatus"))) {
            String docImage = apiResp.getJSONObject("extInfo").getString("imageContent");
            JSONObject rect = apiResp.getJSONObject("extInfo").getJSONObject("rect");

            // update context
            context.setDocImg(docImage);
//            context.setDocImgRect(rect.getString("left") + ",");
            response.put("success", true);
        } else {
            response.put("success", false);
        }

        logger.info("check response=" + response);
        return response;
    }

    @RequestMapping(value = "/initface", method = RequestMethod.POST)
    public JSONObject initFace(@RequestBody JSONObject request) {

        logger.info("request=" + request);

        String bizId = request.getString("bizId");
        RealIdContext context = contextMap.get(bizId);

        JSONObject apiReq = new JSONObject();
        apiReq.put("bizId", bizId);
        apiReq.put("metaInfo", "MOB_H5");
        apiReq.put("merchantUserId", context.getMerchantUserId());

        String apiRespStr = openApiClient.callOpenApi(
                "v1.zoloz.facecapture.initialize", JSON.toJSONString(apiReq));
        JSONObject apiResp = JSON.parseObject(apiRespStr);

        String transactionId = apiResp.getString("transactionId");
        String clientCfg = apiResp.getString("clientCfg");
        // save context
        context.setFaceTxId(transactionId);

        // prepare api response
        JSONObject response = new JSONObject();
        response.put("bizId", bizId);
        response.put("clientCfg", clientCfg);

        if("S".equals(apiResp.getJSONObject("result").getString("resultStatus"))) {
            response.put("success", true);
        } else {
            response.put("success", false);
        }

        logger.info("init face response=" + response);
        return response;
    }

    @RequestMapping(value = "/checkfinal", method = RequestMethod.POST)
    public JSONObject checkFinal(@RequestBody JSONObject request) {

        logger.info("request=" + request);

        String bizId = request.getString("bizId");
        RealIdContext context = contextMap.get(bizId);

        // check face capture result
        {
            JSONObject apiReq = new JSONObject();
            apiReq.put("bizId", bizId);
            apiReq.put("transactionId", context.getFaceTxId());

            String apiRespStr = openApiClient.callOpenApi(
                    "v1.zoloz.facecapture.checkresult", JSON.toJSONString(apiReq));
            JSONObject apiResp = JSON.parseObject(apiRespStr);

            if("S".equals(apiResp.getJSONObject("result").getString("resultStatus"))) {
                String faceImage = apiResp.getJSONObject("extInfo").getString("imageContent");
                context.setFaceImg(faceImage);
            }
        }

        // face compare
        {
            JSONObject apiReq = new JSONObject();
            apiReq.put("bizId", bizId);
            apiReq.put("face1", new JSONObject() {{
                put("content", context.getFaceImg());
            }});
            apiReq.put("face2", new JSONObject() {{
                put("content", context.getDocImg());
            }});

            String apiRespStr = openApiClient.callOpenApi(
                    "v1.zoloz.facecompare.compare", JSON.toJSONString(apiReq));
            JSONObject apiResp = JSON.parseObject(apiRespStr);

            // update context
            if("S".equals(apiResp.getJSONObject("result").getString("resultStatus"))) {
                context.setSamePerson(apiResp.getBoolean("samePerson"));
                context.setScore(apiResp.getDouble("score"));
                context.setFaceCompareTxId(apiResp.getString("transactionId"));
            }
        }

        // prepare api response
        JSONObject response = new JSONObject();
        response.put("samePerson", context.samePerson);
        response.put("score", context.score);
        response.put("success", true);
        response.put("risk", context.risk);
        response.put("faceImg", context.faceImg);
        response.put("docImg", context.docImg);

        logger.info("check final response=" + response);
        return response;
    }
}

