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

package com.zoloz.demo.clientmode.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zoloz.api.sdk.client.OpenApiClient;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

/**
 * web sdk controller
 *
 * @Author: jushi
 * @Date: 2020-03-08 14:55
 */
@Controller
public class H5ClientModeController {

    private static final Logger logger = LoggerFactory.getLogger(H5ClientModeController.class);

    @Autowired
    private OpenApiClient openApiClient;

    @SneakyThrows({UnsupportedEncodingException.class, URISyntaxException.class})
    @GetMapping(value = "/doc")
    public String doc(
            @RequestParam(name = "doc_type", required = false) String docType,
            HttpServletRequest request,
            Model model
    ) {

        String bizId = String.valueOf(System.currentTimeMillis());

        if (docType == null) {
            docType = "00000001003";
        }

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

        if ("S".equals(apiResp.getJSONObject("result").getString("resultStatus"))) {
            URI requestUri = new URI(request.getRequestURL().toString());
            URI callbackUri = new URI(
                    requestUri.getScheme(),
                    null,
                    requestUri.getHost(),
                    requestUri.getPort(),
                    "/doc_result.html",
                    null,
                    null
            );

            StringBuilder sb = new StringBuilder("https://zasia.oss-cn-beijing.aliyuncs.com/prod/zoloz-saas-doc-demo/index.html?");
            sb.append("state=");
            sb.append(URLEncoder.encode(transactionId, "UTF-8"));
            sb.append("&");
            sb.append("clientcfg=");
            sb.append(URLEncoder.encode(clientCfg, "UTF-8"));
            sb.append("&");
            sb.append("callbackurl=");
            sb.append(URLEncoder.encode(callbackUri.toString(), "UTF-8"));

            return "redirect:" + sb.toString();
        } else {
            model.addAttribute("result", apiRespStr);
            model.addAttribute("title", "doc initialization error");

            return "result";
        }
    }


    @SneakyThrows({UnsupportedEncodingException.class, URISyntaxException.class})
    @GetMapping(value = "/face")
    public String face(HttpServletRequest request, Model model) {

        String bizId = String.valueOf(System.currentTimeMillis());

        JSONObject apiReq = new JSONObject();
        apiReq.put("bizId", bizId);
        apiReq.put("metaInfo", "MOB_H5");
        apiReq.put("merchantUserId", "fixed-test-id");

        String apiRespStr = openApiClient.callOpenApi(
                "v1.zoloz.facecapture.initialize",
                JSON.toJSONString(apiReq)
        );
        JSONObject apiResp = JSON.parseObject(apiRespStr);

        String transactionId = apiResp.getString("transactionId");
        String clientCfg = apiResp.getString("clientCfg");

        if ("S".equals(apiResp.getJSONObject("result").getString("resultStatus"))) {
            URI requestUri = new URI(request.getRequestURL().toString());
            URI callbackUri = new URI(
                    requestUri.getScheme(),
                    null,
                    requestUri.getHost(),
                    requestUri.getPort(),
                    "/face_result.html",
                    null,
                    null
            );

            StringBuilder sb = new StringBuilder("https://zasia.oss-cn-beijing.aliyuncs.com/prod/zoloz-saas-face-demo/index.html?");
            sb.append("state=");
            sb.append(URLEncoder.encode(transactionId, "UTF-8"));
            sb.append("&");
            sb.append("clientcfg=");
            sb.append(URLEncoder.encode(clientCfg, "UTF-8"));
            sb.append("&");
            sb.append("callbackurl=");
            sb.append(URLEncoder.encode(callbackUri.toString(), "UTF-8"));

            return "redirect:" + sb.toString();
        } else {
            model.addAttribute("result", apiRespStr);
            model.addAttribute("title", "face initialization error");

            return "result";
        }
    }

    @GetMapping(value = "/face_result.html")
    public String faceResult(@RequestParam(name = "state") String state, Model model) {
        String bizId = String.valueOf(System.currentTimeMillis());
        String transactionId = state;

        JSONObject apiReq = new JSONObject();
        apiReq.put("bizId", bizId);
        apiReq.put("transactionId", transactionId);

        String apiRespStr = openApiClient.callOpenApi(
                "v1.zoloz.facecapture.checkresult", JSON.toJSONString(apiReq));

        JSONObject apiResp = JSON.parseObject(apiRespStr);
        String imageContent = apiResp.getJSONObject("extInfo").getString("imageContent");
        if (imageContent != null) {
            model.addAttribute("image", "data:image/jpg;base64," + imageContent);
            apiResp.getJSONObject("extInfo").remove("imageContent");
        }
        model.addAttribute("title", "face capturing done.");
        model.addAttribute("result", "\n" + JSON.toJSONString(
                apiResp,
                SerializerFeature.PrettyFormat
        ));

        return "result";
    }

    @GetMapping(value = "/doc_result.html")
    public String docResult(@RequestParam(name = "state") String state, Model model) {
        String bizId = String.valueOf(System.currentTimeMillis());
        String transactionId = state;

        JSONObject apiReq = new JSONObject();
        apiReq.put("bizId", bizId);
        apiReq.put("transactionId", transactionId);

        String apiRespStr = openApiClient.callOpenApi(
                "v1.zoloz.idrecognition.checkresult", JSON.toJSONString(apiReq));

        JSONObject apiResp = JSON.parseObject(apiRespStr);
        String imageContent = apiResp.getJSONObject("extInfo").getString("imageContent");
        if (imageContent != null) {
            model.addAttribute("image", "data:image/jpg;base64," + imageContent);
            apiResp.getJSONObject("extInfo").remove("imageContent");
        }
        model.addAttribute("title", "doc recognizing done.");
        model.addAttribute("result", "\n" + JSON.toJSONString(
                apiResp,
                SerializerFeature.PrettyFormat
        ));

        return "result";
    }

    @GetMapping(value = "/index.html")
    public String index(Model model) {
        model.addAttribute("title", "Welcome to WebSDK Demo");

        return "result";
    }
}