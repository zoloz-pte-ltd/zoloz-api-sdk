package com.zoloz.openapi.sdk.util;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhongyang MA
 * Date: 2019-11-18
 * Time: 21:17
 */
@Data
public class OpenApiData {

    // header
    private Map<String,List<String>> header;

    // content
    private String content;

}
