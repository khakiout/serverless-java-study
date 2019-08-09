package com.komiks.api.spring;

import com.komiks.api.application.UserApplication;
import com.komiks.api.interfaces.http.handler.UserHandler;
import com.komiks.api.interfaces.http.router.UserRouter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

@Configuration
public class MainConfiguration {

    /**
     * Bean for the User Application.
     *
     * @return the user application
     */
    @Bean
    public UserApplication userApplication() {
        return new UserApplication();
    }

    /**
     * Bean for SampleHandler.
     * @param userApplication the user application
     * @return the handler
     */
    @Bean
    public UserHandler userHandler(UserApplication userApplication) {
        return new UserHandler(userApplication);
    }

    /**
     * Bean for SampleRouter.
     * @param userHandler the related handler.
     *
     * @return the router
     */
    @Bean
    public RouterFunction userRouter(UserHandler userHandler) {
        return new UserRouter(userHandler).getMapping();
    }

}
