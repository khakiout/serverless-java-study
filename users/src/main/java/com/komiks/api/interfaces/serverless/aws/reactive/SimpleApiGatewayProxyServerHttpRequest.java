package com.komiks.api.interfaces.serverless.aws.reactive;

import com.komiks.api.interfaces.serverless.aws.model.ApiGatewayProxyRequest;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.AbstractServerHttpRequest;
import org.springframework.http.server.reactive.SslInfo;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Flux;

public class SimpleApiGatewayProxyServerHttpRequest extends AbstractServerHttpRequest {

    private final ApiGatewayProxyRequest proxyRequest;
    private final DataBufferFactory dataBufferFactory;

    private SimpleApiGatewayProxyServerHttpRequest(URI uri, String contextPath, HttpHeaders headers,
        ApiGatewayProxyRequest proxyRequest, DataBufferFactory dataBufferFactory) {
        super(uri, contextPath, headers);
        this.proxyRequest = proxyRequest;
        this.dataBufferFactory = dataBufferFactory;
    }

    @Override
    protected MultiValueMap<String, String> initQueryParams() {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();

        Map<String, String> requestQueryParams = proxyRequest.getQueryStringParameters();
        if (!CollectionUtils.isEmpty(requestQueryParams)) {
            proxyRequest.getQueryStringParameters().forEach(queryParams::add);
        }

        return queryParams;
    }

    @Override
    protected MultiValueMap<String, HttpCookie> initCookies() {
        MultiValueMap<String, HttpCookie> httpCookies = new LinkedMultiValueMap<>();

        proxyRequest.getHeaders(HttpHeaders.COOKIE)
            .stream()
            .map(cookie -> new HttpCookie(cookie.getKey(), cookie.getValue()))
            .forEach(httpCookie -> httpCookies.add(httpCookie.getName(), httpCookie));

        return httpCookies;
    }

    @Override
    protected SslInfo initSslInfo() {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getNativeRequest() {
        return (T) proxyRequest;
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
        return null;
    }

    @Override
    public String getMethodValue() {
        return proxyRequest.getHttpMethod();
    }

    @Override
    public Flux<DataBuffer> getBody() {
        return Flux
            .just(dataBufferFactory.wrap(proxyRequest.getBody().getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * Create a SimpleApiGatewayProxyServerHttpRequest instance.
     *
     * @param proxyRequest the proxyRequest.
     * @return the new instance of SimpleApiGatewayProxyServerHttpRequest
     */
    public static SimpleApiGatewayProxyServerHttpRequest of(ApiGatewayProxyRequest proxyRequest) {
        try {
            URI uri = new URI(proxyRequest.getPath());
            String contextPath = "/";
            HttpHeaders headers = new HttpHeaders();
            proxyRequest.getHeaders()
                .entrySet()
                .stream()
                .filter(entry -> !HttpHeaders.COOKIE.equals(entry.getKey()))
                .filter(entry -> !HttpHeaders.ACCEPT_LANGUAGE.equals(entry.getKey()))
                .forEach(entry -> {
                    List<Map.Entry<String, String>> entries = proxyRequest
                        .getHeaders(entry.getKey());
                    entries.stream().map(Map.Entry::getValue)
                        .forEach(headerValue -> headers.add(entry.getKey(), headerValue));
                });

            return new SimpleApiGatewayProxyServerHttpRequest(uri, contextPath, headers,
                proxyRequest, new DefaultDataBufferFactory());
        } catch (URISyntaxException error) {
            throw new UriSyntaxExceptionWrapper(error);
        }
    }

    public static class UriSyntaxExceptionWrapper extends RuntimeException {

        UriSyntaxExceptionWrapper(Throwable cause) {
            super(cause);
        }
    }

}
