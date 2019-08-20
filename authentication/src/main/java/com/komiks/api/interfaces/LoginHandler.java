package com.komiks.api.interfaces;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.komiks.api.application.AuthenticationApplication;
import com.komiks.api.commons.JsonUtils;
import com.komiks.api.commons.JwtService;
import com.komiks.api.domain.Session;
import com.komiks.api.infrastructure.db.repository.UserRepository;
import com.komiks.api.infrastructure.db.repository.UserRepositoryImpl;
import com.komiks.api.interfaces.http.models.response.GenericServerErrorResponse;
import com.komiks.api.interfaces.http.models.response.GenericUnauthorizedResponse;
import com.komiks.api.model.request.LoginRequest;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginHandler implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse> {

    private Logger logger = LogManager.getLogger(this.getClass());

    private AuthenticationApplication authenticationApplication;

    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest request, Context context) {
        LoginRequest loginRequest;
        try {
            loginRequest = JsonUtils.parseJson(request.getBody(), LoginRequest.class);
        } catch (IOException ex) {
            logger.error("Error in retrieving product: " + ex);

            // send the error response back
            return ApiGatewayResponse.builder()
                .setStatusCode(500)
                .setObjectBody(new GenericServerErrorResponse())
                .build();
        }

        initializeServices();
        logger.info("Logging in {}", loginRequest.username);
        Session session = authenticationApplication.authenticate(loginRequest);

        if (session != null) {
            return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(session)
                .build();
        } else {
            return ApiGatewayResponse.builder()
                .setStatusCode(401)
                .setObjectBody(new GenericUnauthorizedResponse())
                .build();
        }

    }

    private void initializeServices() {
        String jwtSecret = System.getenv("JWT_SECRET");
        JwtService jwtService = new JwtService(jwtSecret);
        UserRepository userRepository = new UserRepositoryImpl();
        authenticationApplication = new AuthenticationApplication(jwtService, userRepository);
    }
}
