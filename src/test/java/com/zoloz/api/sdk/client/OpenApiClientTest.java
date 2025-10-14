package com.zoloz.api.sdk.client;

import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.zoloz.api.sdk.client.exception.ApigwParseException;
import com.zoloz.api.sdk.client.model.OpenApiContext;
import com.zoloz.api.sdk.client.protocol.IApigwProtocol;
import com.zoloz.api.sdk.client.protocol.TwoWayAuthProtocol;
import com.zoloz.api.sdk.client.protocol.AKSKProtocol;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OpenApiClientTest {

    private OpenApiClient client;

    @Captor
    ArgumentCaptor<OpenApiContext> context;

    @BeforeMethod
    public void setUp() {
        client = new OpenApiClient();
        client.setHostUrl("http://test.com");
        client.setClientId("testClient");
        context = ArgumentCaptor.forClass(OpenApiContext.class);
    }

    @Test
    public void testCallOpenApi() throws Exception {

        // Setup mock protocol
        IApigwProtocol mockProto = mock(IApigwProtocol.class);

        // Mock protocol behavior
        when(mockProto.parseResponse(any(OpenApiContext.class))).thenReturn("final-result");
        doAnswer(invocationOnMock -> {
            OpenApiContext context = invocationOnMock.getArgument(0, OpenApiContext.class);
            context.setRequestBody("proto-request-body");
            Map<String, String> headers = context.getRequestHeaders();
            headers.put("proto-request-header-key", "proto-request-header-val");
            return null;
        }).when(mockProto).buildRequest(any(OpenApiContext.class), anyString());

        // Setup mock connection
        HttpURLConnection mockConn = mock(HttpURLConnection.class);

        // Mock response stream
        ByteArrayInputStream inputStream = new ByteArrayInputStream("proto-response-body".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        when(mockConn.getOutputStream()).thenReturn(outputStream);
        when(mockConn.getInputStream()).thenReturn(inputStream);
        ArrayList<String> strList = new ArrayList<String>(){{add("proto-response-header-val");}};
        Map<String, List<String>> headerMap = new HashMap<String, List<String>>() {{
            put("proto-response-header-key", strList);
        }};
        when(mockConn.getHeaderFields()).thenReturn(headerMap);

        // Mock createConnection to return our mock
        OpenApiClient clientSpy = spy(client);
        doReturn(mockConn).when(clientSpy).createConnection(anyString(), anyString());
        doReturn(mockProto).when(clientSpy).createProtocol();

        // Execute
        String result = clientSpy.callOpenApi(
                "test-api",
                "raw-request",
                new HashMap<String, String>(){{
                    put("biz-request-header-key", "biz-request-header-val");
                }}
        );

        // Verify
        // 1. build request
        verify(mockProto).buildRequest(context.capture(), eq("raw-request"));
        // 2. post data
        verify(mockConn).setConnectTimeout(anyInt());
        verify(mockConn).setReadTimeout(anyInt());
        verify(mockConn).setDoOutput(true);
        verify(mockConn).setDoInput(true);
        HashMap<String, String> expectedRequestHeaders = new HashMap<String, String>() {{
            put("biz-request-header-key", "biz-request-header-val");
            put("proto-request-header-key", "proto-request-header-val");
        }};
        for (Entry<String, String> entry: expectedRequestHeaders.entrySet()) {
            verify(mockConn).setRequestProperty(eq(entry.getKey()), eq(entry.getValue()));
        }
        verify(mockConn, times(2)).setRequestProperty(endsWith("-request-header-key"), endsWith("-request-header-val"));
        assertEquals(outputStream.toString(), "proto-request-body");
        // 3. parse response
        verify(mockProto).parseResponse(context.capture());
        // 4. final request
        assertEquals(result, "final-result");
        // 5. internal context
        assertEquals(context.getValue().getApiHost(), "http://test.com");
        assertEquals(context.getValue().getApiName(), "test-api");
        assertEquals(context.getValue().getRequestHeaders().size(), 2);
        assertEquals(context.getValue().getRequestHeaders().get("biz-request-header-key"), "biz-request-header-val");
        assertEquals(context.getValue().getRequestHeaders().get("proto-request-header-key"), "proto-request-header-val");
        assertEquals(context.getValue().getRequestBody(), "proto-request-body");
        assertEquals(context.getValue().getResponseHeaders().size(), 1);
        assertEquals(context.getValue().getResponseHeaders().get("proto-response-header-key").get(0), "proto-response-header-val");
        assertEquals(context.getValue().getResponseBody(), "proto-response-body");
    }

    @Test(expectedExceptions = IOException.class)
    public void testCallOpenApiWithNetworkFailure() throws Exception {

        // Setup mock protocol
        IApigwProtocol mockProto = mock(IApigwProtocol.class);

        // Mock createConnection to return our mock
        OpenApiClient clientSpy = spy(client);
        doReturn(mockProto).when(clientSpy).createProtocol();
        doThrow(IOException.class).when(clientSpy).postData(any(OpenApiContext.class));

        // Execute with no throw
        String result = clientSpy.callOpenApi(
                "test-api",
                "raw-request"
        );
        assertNull(result);

        // Excecute with throw
        clientSpy.setThrowOnFailure(true);
        clientSpy.callOpenApi(
                "test-api",
                "raw-request"
        );

        // Expect to throw IOException
    }

    @Test(expectedExceptions = ApigwParseException.class)
    public void testCallOpenApiWithParseFailure() throws Exception{
        // Setup mock protocol
        IApigwProtocol mockProto = mock(IApigwProtocol.class);

        // Mock protocol behavior
        when(mockProto.parseResponse(any(OpenApiContext.class))).thenThrow(ApigwParseException.class);

        // Mock createConnection to return our mock
        OpenApiClient clientSpy = spy(client);
        doReturn(mockProto).when(clientSpy).createProtocol();
        doAnswer(invocationOnMock -> null).when(clientSpy).postData(any(OpenApiContext.class));

        // Execute with no throw
        String result = clientSpy.callOpenApi(
                "test-api",
                "raw-request"
        );
        assertNull(result);

        // Execute with throw
        clientSpy.setThrowOnFailure(true);
        clientSpy.callOpenApi(
                "test-api",
                "raw-request"
        );

        // expect to throw ApigwParseException
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCallOpenApiWithInvalidProtocol() {
        client.setProtoName("invalid");
        client.callOpenApi("test.api", "{}");
    }

    @Test
    public void testDefaultConstructor() {
        OpenApiClient defaultClient = new OpenApiClient();
        assertTrue(defaultClient.isSigned());
        assertTrue(defaultClient.isEncrypted());
    }

    @Test
    public void testConfigurationSetters() {
        client.setConnTimeout(5000);
        client.setReadTimeout(5000);
        client.setEncrypted(false);
        client.setSigned(false);
        client.setAesLength(128);
        client.setLoadTest(true);
        client.setThrowOnFailure(true);
        
        assertEquals(client.getConnTimeout(), Integer.valueOf(5000));
        assertEquals(client.getReadTimeout(), Integer.valueOf(5000));
        assertFalse(client.isEncrypted());
        assertFalse(client.isSigned());
        assertEquals(client.getAesLength(), Integer.valueOf(128));
        assertTrue(client.isLoadTest());
        assertTrue(client.isThrowOnFailure());
    }

    @Test
    public void testCreateTwoWayProtocol() {
        client.setProtoName("2way");
        client.setMerchantPrivateKey("privateKey");
        client.setOpenApiPublicKey("publicKey");
        client.setEncrypted(true);
        client.setSigned(true);
        client.setAesLength(256);

        IApigwProtocol protocol = client.createProtocol();
        assertTrue(protocol instanceof TwoWayAuthProtocol);
    }

    @Test
    public void testCreateAKSKProtocol() {
        client.setProtoName("aksk");
        client.setAccessKey("accessKey");
        client.setSecretKey("secretKey");
        client.setSigned(true);

        IApigwProtocol protocol = client.createProtocol();
        assertTrue(protocol instanceof AKSKProtocol);
    }

    @Test
    public void testInitContextBasic() {
        OpenApiContext context = client.initContext("test.api", null);
        
        assertEquals(context.getApiName(), "test.api");
        assertEquals(context.getApiHost(), "http://test.com");
        assertTrue(context.getRequestHeaders().isEmpty());
    }

    @Test
    public void testInitContextWithHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Header1", "Value1");
        headers.put("Header2", "Value2");
        
        OpenApiContext context = client.initContext("test.api", headers);
        
        assertEquals(context.getRequestHeaders().size(), 2);
        assertEquals(context.getRequestHeaders().get("Header1"), "Value1");
        assertEquals(context.getRequestHeaders().get("Header2"), "Value2");
    }

    @Test
    public void testInitContextWithLoadTest() {
        client.setLoadTest(true);
        OpenApiContext context = client.initContext("test.api", null);
        
        assertEquals(context.getRequestHeaders().size(), 1);
        assertEquals(context.getRequestHeaders().get("loadTestMode"), "true");
    }

    @Test
    public void testInitContextWithNullHeaders() {
        OpenApiContext context = client.initContext("test.api", null);
        
        assertNotNull(context.getRequestHeaders());
        assertTrue(context.getRequestHeaders().isEmpty());
    }

    @Test
    public void testPostDataSuccess() throws Exception {

        // Setup mock connection
        HttpURLConnection mockConn = mock(HttpURLConnection.class);
        
        // Mock response stream
        ByteArrayInputStream inputStream = new ByteArrayInputStream("test response".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        when(mockConn.getOutputStream()).thenReturn(outputStream);
        when(mockConn.getInputStream()).thenReturn(inputStream);
        
        // Create test context
        OpenApiContext context = new OpenApiContext();
        context.setApiName("test.api");
        context.setRequestBody("test request");
        context.setRequestHeaders(new HashMap<String, String>(){{put("test header key", "test header value");}});
        
        // Mock createConnection to return our mock
        OpenApiClient clientSpy = spy(client);
        doReturn(mockConn).when(clientSpy).createConnection(anyString(), anyString());

        // Execute
        clientSpy.postData(context);

        // Verify
        verify(mockConn).setRequestProperty("test header key", "test header value");
        verify(mockConn).setDoOutput(true);
        verify(mockConn).setDoInput(true);
        assertEquals(outputStream.toString(), "test request");
        assertEquals(context.getResponseBody(), "test response");
    }

    @Test(expectedExceptions = IOException.class)
    public void testPostDataIOException() throws Exception {
        // Setup mock connection
        HttpURLConnection mockConn = mock(HttpURLConnection.class);
        
        // Mock error
        when(mockConn.getOutputStream()).thenThrow(new IOException("test error"));
        
        // Create test context
        OpenApiContext context = new OpenApiContext();
        context.setApiName("test.api");
        context.setRequestBody("test request");
        
        // Use reflection to inject mock conn
        OpenApiClient clientSpy = spy(client);
        doReturn(mockConn).when(clientSpy).createConnection(anyString(), anyString());
        
        // Execute - should throw
        clientSpy.postData(context);
    }

    @Test
    public void testCreateConnection() throws Exception {
        // Setup
        String host = "http://test.com";
        String apiName = "test.api";
        
        // Execute
        URLConnection conn = client.createConnection(host, apiName);
        
        // Verify
        assertNotNull(conn);
        assertEquals(conn.getURL().toString(), "http://test.com/api/test/api");
    }

    @Test(expectedExceptions = IOException.class)
    public void testCreateConnectionInvalidUrl() throws Exception {
        // Setup invalid host
        String host = "invalid://test.com";
        String apiName = "test.api";
        
        // Execute - should throw
        client.createConnection(host, apiName);
    }

}
