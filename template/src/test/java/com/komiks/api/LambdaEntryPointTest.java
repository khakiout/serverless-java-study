package com.komiks.api;

import org.junit.Test;
import org.springframework.http.server.reactive.HttpHandler;

public class LambdaEntryPointTest {

    @Test
    public void lambdaEntryPoint() {
        LambdaEntryPoint lambdaEntryPoint = new LambdaEntryPoint();
        HttpHandler httpHandler = lambdaEntryPoint.createHttpHandler(null);
    }

}
