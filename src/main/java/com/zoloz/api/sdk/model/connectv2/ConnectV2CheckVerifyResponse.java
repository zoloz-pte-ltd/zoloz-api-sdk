package com.zoloz.api.sdk.model.connectv2;

import com.zoloz.api.sdk.model.OpenApiCommonResult;
import lombok.Data;

import java.util.List;

@Data
public class ConnectV2CheckVerifyResponse extends OpenApiCommonResult {

    /**
     * verify status 内部模块间facade采用枚举方式，对外许愿转换为String
     */
    private VerifyStatusEnum verifyStatus;

    /**
     * validate result
     */
    private List<List<ProductResult>> validateResult;

}
