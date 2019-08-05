package com.komiks.api.spring;

import com.komiks.api.interfaces.http.handler.UserHandler;
import com.komiks.api.interfaces.http.router.SampleRouter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;


@Configuration
public class MainConfiguration {

    /**
     * Bean for SampleHandler.
     *
     * @return the handler
     */
    @Bean
    public UserHandler sampleHandler() {
        return new UserHandler();
    }

    /**
     * Bean for SampleRouter.
     * @param userHandler the related handler.
     *
     * @return the router
     */
    @Bean
    public RouterFunction sampleRouter(UserHandler userHandler) {
        return new SampleRouter().sampleRouter(userHandler);
    }

}
