package com.zoloz.api.sdk.client.protocol;

import com.zoloz.api.sdk.client.exception.ApigwParseException;
import com.zoloz.api.sdk.client.model.OpenApiContext;
import com.zoloz.api.sdk.util.AESUtil;
import com.zoloz.api.sdk.util.GenSignUtil;
import com.zoloz.api.sdk.util.RSAUtil;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TwoWayAuthProtocolTest {

    private static final String CLIENT_ID = "testClient";
    private static final String MERCHANT_PRIVATE_KEY = "merchantPrivateKey";
    private static final String OPEN_API_PUBLIC_KEY = "openApiPublicKey";
    private static final String API_NAME = "test.api";
    private static final String REQUEST_BODY = "{\"key\":\"value\"}";
    private static final String ENCRYPTED_BODY = "encryptedBody";
    private static final String ENCRYPTED_KEY = "encryptedKey";
    private static final String SIGNATURE = "testSignature";
    private static final String RESPONSE_BODY = "{\"status\":\"success\"}";
    private static final String RESPONSE_TIME = "2025-05-24T10:15:30+0000";

    private TwoWayAuthProtocol protocol;
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
    public void testBuildRequest_WithoutEncryption() {
        protocol = new TwoWayAuthProtocol(CLIENT_ID, MERCHANT_PRIVATE_KEY, OPEN_API_PUBLIC_KEY, false, true, 256);

        try (MockedStatic<GenSignUtil> genSignUtil = Mockito.mockStatic(GenSignUtil.class);
             MockedStatic<ZonedDateTime> zonedDateTime = Mockito.mockStatic(ZonedDateTime.class)) {

            zonedDateTime.when(ZonedDateTime::now).thenReturn(reqTime);
            genSignUtil.when(() -> GenSignUtil.sign(anyString(), anyString())).thenReturn(SIGNATURE);

            protocol.buildRequest(context, REQUEST_BODY);

            Map<String, String> headers = context.getRequestHeaders();
            assertEquals(headers.get("Client-Id"), CLIENT_ID);
            assertEquals(headers.get("Request-Time"), reqTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")));
            assertEquals(headers.get("Content-Type"), "application/json; charset=UTF-8");
            assertTrue(headers.get("Signature").contains(SIGNATURE));
            assertEquals(context.getRequestBody(), REQUEST_BODY);
        }
    }

    @Test
    public void testBuildRequest_WithEncryption() {
        protocol = new TwoWayAuthProtocol(CLIENT_ID, MERCHANT_PRIVATE_KEY, OPEN_API_PUBLIC_KEY, true, true, 256);

        try (MockedStatic<GenSignUtil> genSignUtil = Mockito.mockStatic(GenSignUtil.class);
             MockedStatic<ZonedDateTime> zonedDateTime = Mockito.mockStatic(ZonedDateTime.class);
             MockedStatic<AESUtil> aesUtil = Mockito.mockStatic(AESUtil.class);
             MockedStatic<RSAUtil> rsaUtil = Mockito.mockStatic(RSAUtil.class)) {

            zonedDateTime.when(ZonedDateTime::now).thenReturn(reqTime);
            genSignUtil.when(() -> GenSignUtil.sign(anyString(), anyString())).thenReturn(SIGNATURE);
            aesUtil.when(() -> AESUtil.generateKey(anyInt())).thenReturn(new byte[]{1, 2, 3});
            aesUtil.when(() -> AESUtil.encrypt(any(), anyString())).thenReturn(ENCRYPTED_BODY);
            rsaUtil.when(() -> RSAUtil.encrypt(anyString(), any())).thenReturn(ENCRYPTED_KEY);

            protocol.buildRequest(context, REQUEST_BODY);

            Map<String, String> headers = context.getRequestHeaders();
            assertEquals(headers.get("Client-Id"), CLIENT_ID);
            assertEquals(headers.get("Request-Time"), reqTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")));
            assertEquals(headers.get("Content-Type"), "text/plain; charset=UTF-8");
            assertTrue(headers.get("Signature").contains(SIGNATURE));
            assertTrue(headers.get("Encrypt").contains(ENCRYPTED_KEY));
            assertEquals(context.getRequestBody(), ENCRYPTED_BODY);
        }
    }

    @Test
    public void testParseResponse_WithoutEncryption_WithSignature() throws ApigwParseException {
        protocol = new TwoWayAuthProtocol(CLIENT_ID, MERCHANT_PRIVATE_KEY, OPEN_API_PUBLIC_KEY, false, true, 256);
        
        Map<String, List<String>> responseHeaders = new HashMap<>();
        responseHeaders.put("Signature", new ArrayList<String>(){{add("algorithm=RSA256, signature=" + SIGNATURE);}});
        responseHeaders.put("Response-Time", new ArrayList<String>(){{add(RESPONSE_TIME);}});
        context.setResponseHeaders(responseHeaders);
        context.setResponseBody(RESPONSE_BODY);

        try (MockedStatic<GenSignUtil> genSignUtil = Mockito.mockStatic(GenSignUtil.class)) {
            genSignUtil.when(() -> GenSignUtil.verify(anyString(), anyString(), anyString())).thenReturn(true);

            String result = protocol.parseResponse(context);
            assertEquals(result, RESPONSE_BODY);
        }
    }

    @Test
    public void testParseResponse_WithEncryption_WithSignature() throws ApigwParseException {
        protocol = new TwoWayAuthProtocol(CLIENT_ID, MERCHANT_PRIVATE_KEY, OPEN_API_PUBLIC_KEY, true, true, 256);
        
        Map<String, List<String>> responseHeaders = new HashMap<>();
        responseHeaders.put("Signature", new ArrayList<String>(){{add("algorithm=RSA256, signature=" + SIGNATURE);}});
        responseHeaders.put("Response-Time", new ArrayList<String>(){{add(RESPONSE_TIME);}});
        responseHeaders.put("Encrypt", new ArrayList<String>(){{add("algorithm=RSA_AES, symmetricKey=" + ENCRYPTED_KEY);}});
        context.setResponseHeaders(responseHeaders);
        context.setResponseBody(ENCRYPTED_BODY);

        try (MockedStatic<GenSignUtil> genSignUtil = Mockito.mockStatic(GenSignUtil.class);
             MockedStatic<RSAUtil> rsaUtil = Mockito.mockStatic(RSAUtil.class);
             MockedStatic<AESUtil> aesUtil = Mockito.mockStatic(AESUtil.class)) {

            genSignUtil.when(() -> GenSignUtil.verify(anyString(), anyString(), anyString())).thenReturn(true);
            rsaUtil.when(() -> RSAUtil.decrypt(anyString(), anyString())).thenReturn(new byte[]{1, 2, 3});
            aesUtil.when(() -> AESUtil.decrypt(any(), anyString())).thenReturn(RESPONSE_BODY);

            String result = protocol.parseResponse(context);
            assertEquals(result, RESPONSE_BODY);
        }
    }

    @Test
    public void testParseResponse_SignatureVerificationFailed() {
        protocol = new TwoWayAuthProtocol(CLIENT_ID, MERCHANT_PRIVATE_KEY, OPEN_API_PUBLIC_KEY, false, true, 256);
        
        Map<String, List<String>> responseHeaders = new HashMap<>();
        responseHeaders.put("Signature", new ArrayList<String>(){{add("algorithm=RSA256, signature=" + SIGNATURE);}});
        responseHeaders.put("Response-Time", new ArrayList<String>(){{add(RESPONSE_TIME);}});
        context.setResponseHeaders(responseHeaders);
        context.setResponseBody(RESPONSE_BODY);

        try (MockedStatic<GenSignUtil> genSignUtil = Mockito.mockStatic(GenSignUtil.class)) {
            genSignUtil.when(() -> GenSignUtil.verify(anyString(), anyString(), anyString())).thenReturn(false);

            assertThrows(ApigwParseException.class, () -> protocol.parseResponse(context));
        }
    }

    @Test
    public void testParseResponse_NoSignatureWhenVerificationRequired() {
        protocol = new TwoWayAuthProtocol(CLIENT_ID, MERCHANT_PRIVATE_KEY, OPEN_API_PUBLIC_KEY, false, true, 256);
        
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
            assertEquals(e.getMessage(), "signature validation failed.");
        }
    }
}
