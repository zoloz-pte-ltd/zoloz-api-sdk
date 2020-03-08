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
import com.zoloz.api.sdk.client.OpenApiClient;
import lombok.SneakyThrows;
import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

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
public class WebSdkController {

    private static final Logger logger = LoggerFactory.getLogger(WebSdkController.class);

    @Autowired
    private OpenApiClient openApiClient;

    @SneakyThrows({UnsupportedEncodingException.class, URISyntaxException.class})
    @GetMapping(value = "/doc")
    public RedirectView doc(
            @RequestParam(name = "doc_type", required = false) String docType,
            HttpServletRequest request
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

        if("S".equals(apiResp.getJSONObject("result").getString("resultStatus"))) {
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

            StringBuilder sb = new StringBuilder("https://zasia.oss-cn-beijing.aliyuncs.com/dev/zoloz-saas-doc-demo/index.html?");
            sb.append("state=");
            sb.append(URLEncoder.encode(transactionId, "UTF-8"));
            sb.append("&");
            sb.append("clientcfg=");
            sb.append(URLEncoder.encode(clientCfg, "UTF-8"));
            sb.append("&");
            sb.append("callbackurl=");
            sb.append(URLEncoder.encode(callbackUri.toString(), "UTF-8"));

            RedirectView response = new RedirectView();
            response.setUrl(sb.toString());

            return response;
        }
        else {
            throw new NotImplementedException("");
        }
    }

    @GetMapping(value = "/doc_result.html")
    public String docResult(@RequestParam(name = "state") String state) {
        String bizId = String.valueOf(System.currentTimeMillis());
        String transactionId = state;

        JSONObject apiReq = new JSONObject();
        apiReq.put("bizId", bizId);
        apiReq.put("transactionId", transactionId);

        String apiRespStr = openApiClient.callOpenApi(
                "v1.zoloz.idrecognition.checkresult", JSON.toJSONString(apiReq));

        return apiRespStr;
    }
}
