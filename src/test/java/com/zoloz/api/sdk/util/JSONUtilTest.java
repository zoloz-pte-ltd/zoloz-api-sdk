package com.zoloz.api.sdk.util;

import java.util.HashMap;

import static org.testng.Assert.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.zoloz.api.sdk.model.*;

/**
 * JSONUtil tests
 *
 * @author Zhang Fang
 */
public class JSONUtilTest {
    @DataProvider(name = "datamodels")
    public static Object[][] pairsOfObjectAndJsonStr() {
        return new Object[][] {
                new Object[] {
                        new FaceCompareRequest() {{
                            setBizId("test-bizId");
                            setFace1(new FaceImage(){{
                                setContent("dummy-content-for-image1");
                                setRect("1,2,3,4");
                            }});
                            setFace2(new FaceImage(){{
                                setContent("dummy-content-for-image2");
                                setRect("5,6,7,8");
                            }});
                        }}
                },
                new Object[] {
                        new FaceCompareResponse() {{
                            setTransactionId("test-txnId");
                            setResult(new OpenApiResultCode(){{
                                setResultStatus("SUCCESS");
                                setResultCode("OK_SUCCESS");
                                setResultMessage("success");
                            }});
                            setScore(1.0);
                            setSamePerson(true);
                        }}
                },
                new Object[] {
                        new DocRecognitionRequest() {{
                            setBizId("test-bizId");
                            setDocType("test-docType");
                            setFrontPageImage("dummy-content-front-image");
                            setBackPageImage("dummy-content-back-image");
                        }}
                },
                new Object[] {
                        new DocRecognitionResponse() {{
                            setTransactionId("test-txnId");
                            setResult(new OpenApiResultCode(){{
                                setResultStatus("SUCCESS");
                                setResultCode("OK_SUCCESS");
                                setResultMessage("success");
                            }});
                            setRecognitionErrorCode("dummy-rec-errcode");
                            setOcrResult(new HashMap<String, String>(){{
                                put("foo", "bar");
                            }});
                            setSpoofResult(new HashMap<String, String>(){{
                                put("foo", "bar");
                            }});
                        }}
                }

        };
    }

    @Test(dataProvider = "datamodels")
    public void testJSONUtil(Object obj) throws Exception {
        String jsonStr = JSONUtil.toJSONString(obj);
        assertNotNull(jsonStr);
        assertTrue(jsonStr.length() > 1, "unexpected json string: " + jsonStr);

        Object recovered = JSONUtil.parseObject(jsonStr, obj.getClass());
        assertTrue(obj.equals(recovered), String.format(
                "expected: %s, actual: %s",
                obj.toString(),
                recovered.toString()
                )
        );
    }
}