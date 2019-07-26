package com.khakiout.api.interfaces.serverless.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.khakiout.api.interfaces.serverless.aws.model.ApiGatewayProxyRequest;
import com.khakiout.api.interfaces.serverless.aws.model.ApiGatewayProxyResponse;
import com.khakiout.api.interfaces.serverless.aws.reactive.SimpleApiGatewayProxyServerHttpRequest;
import com.khakiout.api.interfaces.serverless.aws.reactive.SimpleApiGatewayProxyServerHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

public abstract class ApiGatewayProxyHandler implements
    RequestHandler<ApiGatewayProxyRequest, ApiGatewayProxyResponse> {

    private Logger logger = LoggerFactory.getLogger(ApiGatewayProxyHandler.class);

    @Override
    public ApiGatewayProxyResponse handleRequest(ApiGatewayProxyRequest proxyRequest,
        Context context) {
        logger.info("proxyRequest: {}, context: {}", proxyRequest, context);

        ApiGatewayProxyResponse proxyResponse = new ApiGatewayProxyResponse();

        HttpHandler httpHandler = createHttpHandler(context);
        logger.info("HttpHandler created: {}", httpHandler);

        ServerHttpRequest request = SimpleApiGatewayProxyServerHttpRequest.of(proxyRequest);
        ServerHttpResponse response = SimpleApiGatewayProxyServerHttpResponse.of(proxyResponse);

        httpHandler.handle(request, response).block();
        logger.info("proxyResponse: {}", proxyResponse);

        return proxyResponse;
    }


    protected abstract HttpHandler createHttpHandler(Context context);

}
