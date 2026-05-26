package com.zoloz.api.sdk.client.protocol;

import com.zoloz.api.sdk.client.exception.ApigwParseException;
import com.zoloz.api.sdk.client.model.OpenApiContext;
import com.zoloz.api.sdk.util.SignatureUtil;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AKSKProtocolTest {

    private static final String CLIENT_ID = "testClient";
    private static final String ACCESS_KEY = "testAccessKey";
    private static final String SECRET_KEY = "testSecretKey";
    private static final String API_NAME = "test.api";
    private static final String REQUEST_BODY = "{\"key\":\"value\"}";
    private static final String SIGNATURE = "testSignature";
    private static final String RESPONSE_BODY = "{\"status\":\"success\"}";
    private static final String RESPONSE_TIME = "2025-05-24T10:15:30+0000";

    private AKSKProtocol protocol;
    private OpenApiContext context;
    private ZonedDateTime reqTime;

    @BeforeMethod
    public void setUp() {
        context = new OpenApiContext();
        context.setApiName(API_NAME);
        context.setRequestHeaders(new HashMap<>());
        reqTime = ZonedDateTime.parse("2025-01-01T00:00:00Z");
    }

    @Test
    public void testBuildRequest() {
        protocol = new AKSKProtocol(CLIENT_ID, ACCESS_KEY, SECRET_KEY, true);

        try (MockedStatic<SignatureUtil> signatureUtil = Mockito.mockStatic(SignatureUtil.class);
             MockedStatic<ZonedDateTime> zonedDateTime = Mockito.mockStatic(ZonedDateTime.class)) {

            zonedDateTime.when(ZonedDateTime::now).thenReturn(reqTime);
            signatureUtil.when(() -> SignatureUtil.sign(anyString(), anyString())).thenReturn(SIGNATURE);

            protocol.buildRequest(context, REQUEST_BODY);

            Map<String, String> headers = context.getRequestHeaders();
            assertEquals(headers.get("Client-Id"), CLIENT_ID);
            assertEquals(headers.get("Request-Time"), reqTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")));
            assertEquals(headers.get("Content-Type"), "application/json; charset=UTF-8");
            assertEquals(headers.get("Auth-Type"), "AKSK");
            assertEquals(headers.get("Access-Key"), ACCESS_KEY);
            assertEquals(headers.get("Signature"), SIGNATURE);
            assertEquals(context.getRequestBody(), REQUEST_BODY);
        }
    }

    @Test
    public void testParseResponse_WithSignature() throws ApigwParseException {
        protocol = new AKSKProtocol(CLIENT_ID, ACCESS_KEY, SECRET_KEY, true);
        
        Map<String, List<String>> responseHeaders = new HashMap<>();
        responseHeaders.put("Signature", new ArrayList<String>(){{add(SIGNATURE);}});
        responseHeaders.put("Response-Time", new ArrayList<String>(){{add(RESPONSE_TIME);}});
        context.setResponseHeaders(responseHeaders);
        context.setResponseBody(RESPONSE_BODY);

        try (MockedStatic<SignatureUtil> signatureUtil = Mockito.mockStatic(SignatureUtil.class)) {
            signatureUtil.when(() -> SignatureUtil.verify(anyString(), anyString(), anyString())).thenReturn(true);

            String result = protocol.parseResponse(context);
            assertEquals(result, RESPONSE_BODY);
        }
    }

    @Test
    public void testParseResponse_SignatureVerificationFailed() {
        protocol = new AKSKProtocol(CLIENT_ID, ACCESS_KEY, SECRET_KEY, true);
        
        Map<String, List<String>> responseHeaders = new HashMap<>();
        responseHeaders.put("Signature", new ArrayList<String>(){{add(SIGNATURE);}});
        responseHeaders.put("Response-Time", new ArrayList<String>(){{add(RESPONSE_TIME);}});
        context.setResponseHeaders(responseHeaders);
        context.setResponseBody(RESPONSE_BODY);

        try (MockedStatic<SignatureUtil> signatureUtil = Mockito.mockStatic(SignatureUtil.class)) {
            signatureUtil.when(() -> SignatureUtil.verify(anyString(), anyString(), anyString())).thenReturn(false);

            assertThrows(ApigwParseException.class, () -> protocol.parseResponse(context));
        }
    }

    @Test
    public void testParseResponse_NoSignatureWhenVerificationRequired() {
        protocol = new AKSKProtocol(CLIENT_ID, ACCESS_KEY, SECRET_KEY, true);
        
        Map<String, List<String>> responseHeaders = new HashMap<>();
        // Don't add Signature header but add Response-Time to trigger verification
        responseHeaders.put("Response-Time", new ArrayList<String>(){{add(RESPONSE_TIME);}});
        context.setResponseHeaders(responseHeaders);
        context.setResponseBody(RESPONSE_BODY);

        // Verify the exception is thrown
        try {
            protocol.parseResponse(context);
            fail("Expected ApigwParseException to be thrown");
        } catch (ApigwParseException e) {
            assertEquals(e.getMessage(), "response signature verification fail.");
        }
    }

    @Test
    public void testParseResponse_NoVerificationWhenDisabled() throws ApigwParseException {
        protocol = new AKSKProtocol(CLIENT_ID, ACCESS_KEY, SECRET_KEY, false);
        
        Map<String, List<String>> responseHeaders = new HashMap<>();
        // No signature header
        context.setResponseHeaders(responseHeaders);
        context.setResponseBody(RESPONSE_BODY);

        // Should not throw exception even without signature
        String result = protocol.parseResponse(context);
        assertEquals(result, RESPONSE_BODY);
    }
}
