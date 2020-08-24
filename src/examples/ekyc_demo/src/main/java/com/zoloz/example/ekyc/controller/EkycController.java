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

package com.zoloz.example.ekyc.controller;

import com.zoloz.example.ekyc.service.FaceCompareService;
import com.zoloz.example.ekyc.service.OCRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * ekyc controller
 *
 * @author youcha
 * @date 2020/8/18 10:46
 */

@Controller
public class EkycController {
    private final OCRService OCRService;
    private final FaceCompareService faceCompareService;

    @Autowired
    public EkycController(OCRService OCRService, FaceCompareService faceCompareService){
        this.OCRService = OCRService;
        this.faceCompareService = faceCompareService;
    }

    @PostMapping("/ekyc")
    @ResponseBody
    public Map handleEkycRequest(@RequestBody Map request){
        String docType = request.get("docType").toString();
        String docImageContent = request.get("docImageContent").toString();
        String faceImageContent = request.get("faceImageContent").toString();
        Map<String,String>map = new HashMap<>();
        String ocrResult[] = OCRService.getOcrResult(docType,docImageContent);
        String information = ocrResult[0];
        String recognitionResult = ocrResult[1];
        String faceCompareResult[] = faceCompareService.getFaceMatchScore(docImageContent,faceImageContent);
        String faceMatchScore = faceCompareResult[0];
        String samePerson = faceCompareResult[1];
        map.put("ocrResult",information);
        map.put("faceScore", faceMatchScore);
        if(recognitionResult.equals("Y") && samePerson.equals("true"))
            map.put("ekycResult", "true");
        else
            map.put("ekycResult", "false");
        return map;
    }

}
