package com.komiks.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.komiks.api.commons.JsonUtils;
import com.komiks.api.domain.Session;
import com.komiks.api.interfaces.http.models.response.GenericServerErrorResponse;
import com.komiks.api.interfaces.http.models.response.NotFoundResponse;
import com.komiks.api.model.request.LoginRequest;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginHandler implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse> {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest request, Context context) {

        try {
            // get the 'pathParameters' from input
            LoginRequest loginRequest = JsonUtils.parseJson(request.getBody(), LoginRequest.class);

            logger.info("Logging in {}", loginRequest.username);

            Session session = new Session();
            session.setToken("1234");
            session.setUsername(loginRequest.username);

            // send the response back
            if (loginRequest.password != null) {
                return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(session)
                    .build();
            } else {
                return ApiGatewayResponse.builder()
                    .setStatusCode(404)
                    .setObjectBody(new NotFoundResponse())
                    .build();
            }
        } catch (IOException ex) {
            logger.error("Error in retrieving product: " + ex);

            // send the error response back
            return ApiGatewayResponse.builder()
                .setStatusCode(500)
                .setObjectBody(new GenericServerErrorResponse())
                .build();
        }
    }
}
