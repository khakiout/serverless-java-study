package com.komiks.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.komiks.api.commons.JwtService;
import com.komiks.api.model.AuthPolicy;
import com.komiks.api.model.PolicyMetadata;
import com.komiks.api.model.TokenAuthorizerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handles IO for a Java Lambda function as a custom authorizer for API Gateway.
 *
 * @author Jack Kohn
 */
public class ApiGatewayAuthorizerHandler implements
    RequestHandler<TokenAuthorizerContext, AuthPolicy> {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public AuthPolicy handleRequest(TokenAuthorizerContext input, Context context) {

        String token = input.getAuthorizationToken();
        logger.info("Token is {}", token);

        // validate the incoming token
        // and produce the principal user identifier associated with the token

        // this could be accomplished in a number of ways:
        // 1. Call out to OAuth provider
        // 2. Decode a JWT token in-line
        // 3. Lookup in a self-managed DB
//        String principalId = "xxxx";

        // if the client token is not recognized or invalid
        // you can send a 401 Unauthorized response to the client by failing like so:
        // throw new RuntimeException("Unauthorized");

        // if the token is valid, a policy should be generated which will allow or
        // deny access to the client

        // if access is denied, the client will receive a 403 Access Denied response
        // if access is allowed, API Gateway will proceed with the back-end integration
        // configured on the method that was called

        String methodArn = input.getMethodArn();
        logger.info("ARN is {}", methodArn);
        PolicyMetadata metadata = new PolicyMetadata(methodArn);

        // this function must generate a policy that is associated with the recognized
        // principal user identifier.
        // depending on your use case, you might store policies in a DB, or generate them on the fly

        // keep in mind, the policy is cached for 5 minutes by default
        // (TTL is configurable in the authorizer)
        // and will apply to subsequent calls to any method/resource in the RestApi
        // made with the same token

        AuthPolicy.PolicyDocument policyDocument = null;
        String jwtSecret = System.getenv("JWT_SECRET");
        JwtService jwtService = new JwtService(jwtSecret);
        String username = jwtService.getUsernameFromToken(token);

        if (username == null) {
            logger.warn("No user");
            policyDocument = AuthPolicy.PolicyDocument
                .getDenyAllPolicy(metadata);
        } else {
            logger.info("User has valid rights.");
            policyDocument = AuthPolicy.PolicyDocument.getAllowAllPolicy(metadata);
        }

        String principalId = username;
        return new AuthPolicy(principalId, policyDocument);
    }

}