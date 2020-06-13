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

package com.zoloz.example.clientmode.controller.connect;

import com.alibaba.fastjson.JSONObject;

import com.zoloz.api.sdk.api.ConnectAPI;
import com.zoloz.api.sdk.client.OpenApiClient;
import com.zoloz.api.sdk.model.ConnectCheckResultRequest;
import com.zoloz.api.sdk.model.ConnectCheckResultResponse;
import com.zoloz.api.sdk.model.ConnectEnrollRequest;
import com.zoloz.api.sdk.model.ConnectEnrollResponse;
import com.zoloz.api.sdk.model.ConnectInitializeRequest;
import com.zoloz.api.sdk.model.ConnectInitializeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Connect controller.
 */
@CrossOrigin
@RestController
@RequestMapping(value = {"/api", "/webapi"})
public class ConnectController {

    private static final Logger logger = LoggerFactory.getLogger(ConnectController.class);

    @Autowired
    private OpenApiClient openApiClient;

    /**
     * Initialize connect initialize response.
     *
     * @param request the request
     * @return the connect initialize response
     */
    @RequestMapping(value = {"/connect/initialize","/connectDemoService/initialize"}, method = RequestMethod.POST)
    public ConnectInitializeResponse initialize(@RequestBody JSONObject request) {
        logger.info("request=" + request);
        ConnectInitializeRequest connectInitializeRequest = request.toJavaObject(ConnectInitializeRequest.class);
        ConnectInitializeResponse connectInitializeResponse = new ConnectAPI(openApiClient).initialize(connectInitializeRequest);
        logger.info("response=" + connectInitializeResponse);
        return connectInitializeResponse;
    }

    /**
     * Check result connect check result response.
     *
     * @param request the request
     * @return the connect check result response
     */
    @RequestMapping(value = "/connect/checkresult", method = RequestMethod.POST)
    public ConnectCheckResultResponse checkResult(@RequestBody JSONObject request) {
        logger.info("request=" + request);
        ConnectCheckResultRequest connectCheckResultRequest = request.toJavaObject(ConnectCheckResultRequest.class);
        ConnectCheckResultResponse connectCheckResultResponse = new ConnectAPI(openApiClient).checkResult(connectCheckResultRequest);
        logger.info("response=" + connectCheckResultResponse);
        return connectCheckResultResponse;

    }

    /**
     * Enroll connect enroll response.
     *
     * @param request the request
     * @return the connect enroll response
     */
    @RequestMapping(value = "/connect/enroll", method = RequestMethod.POST)
    public ConnectEnrollResponse enroll(@RequestBody JSONObject request) {
        logger.info("request=" + request);
        ConnectEnrollRequest connectEnrollRequest = request.toJavaObject(ConnectEnrollRequest.class);
        ConnectEnrollResponse connectEnrollResponse = new ConnectAPI(openApiClient).enroll(connectEnrollRequest);
        logger.info("response=" + connectEnrollResponse);
        return connectEnrollResponse;

    }
}
