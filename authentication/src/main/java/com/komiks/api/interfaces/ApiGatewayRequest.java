package com.komiks.api.interfaces;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * API Gateway Request model.
 */
public class ApiGatewayRequest {

    private Logger logger = LogManager.getLogger(this.getClass());

    static final String HEADER_VALUE_SEPARATOR = ";";
    static final String HEADER_KEY_VALUE_SEPARATOR = "=";

    private String resource;
    private String path;
    private String httpMethod;
    private Map<String, String> headers;
    private Map<String, String> queryStringParameters;
    private Map<String, String> pathParameters;
    private Map<String, String> stageVariables;
    private Map<String, Object> requestContext;
    private String body;
    private boolean isBase64Encoded;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    /**
     * Return the headers of the request.
     *
     * @return the headers.
     */
    public Map<String, String> getHeaders() {
        if (Objects.nonNull(headers)) {
            return headers;
        }
        return Collections.emptyMap();
    }

    /**
     * Retrieve the headers based on the name.
     *
     * @param name the header name.
     * @return the header.
     */
    public List<Map.Entry<String, String>> getHeaders(String name) {
        String headerValue = getHeaders().getOrDefault(name, null);
        if (Objects.nonNull(headerValue)) {
            return parseHeaderValue(headerValue);
        }
        return Collections.emptyList();
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getQueryStringParameters() {
        return queryStringParameters;
    }

    public void setQueryStringParameters(Map<String, String> queryStringParameters) {
        this.queryStringParameters = queryStringParameters;
    }

    public Map<String, String> getPathParameters() {
        return pathParameters;
    }

    public void setPathParameters(Map<String, String> pathParameters) {
        this.pathParameters = pathParameters;
    }

    public Map<String, String> getStageVariables() {
        return stageVariables;
    }

    public void setStageVariables(Map<String, String> stageVariables) {
        this.stageVariables = stageVariables;
    }

    public Map<String, Object> getRequestContext() {
        return requestContext;
    }

    public void setRequestContext(Map<String, Object> requestContext) {
        this.requestContext = requestContext;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isBase64Encoded() {
        return isBase64Encoded;
    }

    public void setBase64Encoded(boolean base64Encoded) {
        isBase64Encoded = base64Encoded;
    }

    /**
     * Retrieve the parameters in query string format.
     *
     * @return the query string.
     */
    public String getQueryString() {
        StringBuilder queryString = new StringBuilder();
        if (!queryStringParameters.isEmpty()) {
            for (Map.Entry<String, String> entry : queryStringParameters.entrySet()) {
                String separator = queryString.length() == 0 ? "?" : "&";
                queryString.append(separator).append(entry.getKey()).append("=")
                    .append(entry.getValue());
                logger.debug(entry.getKey() + "/" + entry.getValue());
            }
        }
        return queryString.toString();
    }

    private List<Map.Entry<String, String>> parseHeaderValue(String headerValue) {
        List<Map.Entry<String, String>> values = new ArrayList<>();
        if (headerValue == null) {
            return values;
        }

        for (String kv : headerValue.split(HEADER_VALUE_SEPARATOR)) {
            String[] kvSplit = kv.split(HEADER_KEY_VALUE_SEPARATOR);

            if (kvSplit.length != 2) {
                values.add(new AbstractMap.SimpleEntry<>(null, kv.trim()));
            } else {
                values.add(new AbstractMap.SimpleEntry<>(kvSplit[0].trim(), kvSplit[1].trim()));
            }
        }
        return values;
    }

    @Override
    public String toString() {
        return "APIGatewayProxyRequest {"
            + "resource='" + resource + '\''
            + ", path='" + path + '\''
            + ", httpMethod='" + httpMethod + '\''
            + ", headers=" + headers
            + ", queryStringParameters=" + queryStringParameters
            + ", pathParameters=" + pathParameters
            + ", stageVariables=" + stageVariables
            + ", requestContext=" + requestContext
            + ", body='" + body + '\''
            + ", isBase64Encoded=" + isBase64Encoded
            + '}';
    }

}
