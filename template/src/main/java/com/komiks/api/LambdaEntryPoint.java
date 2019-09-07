package com.komiks.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.komiks.api.interfaces.serverless.aws.ApiGatewayProxyHandler;
import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.web.reactive.context.GenericReactiveWebApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;

public class LambdaEntryPoint extends ApiGatewayProxyHandler {

    private static ApplicationContext applicationContext;

    static {
        SpringApplication application = new SpringApplication();
        application
            .addPrimarySources(Collections.singleton(TemplateApiServiceApplication.class));
        application.setWebApplicationType(WebApplicationType.REACTIVE);
        application.setApplicationContextClass(GenericReactiveWebApplicationContext.class);

        applicationContext = application.run();
    }

    @Override
    protected HttpHandler createHttpHandler(Context context) {
        return applicationContext.getBean(HttpHandler.class);
    }

}
