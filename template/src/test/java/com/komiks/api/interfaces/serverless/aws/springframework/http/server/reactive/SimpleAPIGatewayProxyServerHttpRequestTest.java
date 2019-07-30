package com.komiks.api.interfaces.serverless.aws.springframework.http.server.reactive;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.is;

import com.komiks.api.interfaces.serverless.aws.model.ApiGatewayProxyRequest;
import com.komiks.api.interfaces.serverless.aws.reactive.SimpleApiGatewayProxyServerHttpRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;

public class SimpleAPIGatewayProxyServerHttpRequestTest {

    @Test
    public void body() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");

        ApiGatewayProxyRequest proxyRequest = new ApiGatewayProxyRequest();
        proxyRequest.setPath("/");
        proxyRequest.setHeaders(headers);

        SimpleApiGatewayProxyServerHttpRequest requestAdapter = SimpleApiGatewayProxyServerHttpRequest.of(proxyRequest);
        Assert.assertThat(requestAdapter.getHeaders().getContentType(), is(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreationFromEmptyRequest() throws IOException {
        ApiGatewayProxyRequest proxyRequest = new ApiGatewayProxyRequest();
        proxyRequest.setPath("/");

        assertNotNull(proxyRequest);

        SimpleApiGatewayProxyServerHttpRequest requestAdapter = SimpleApiGatewayProxyServerHttpRequest.of(proxyRequest);
        assertNotNull(requestAdapter);
    }


}
