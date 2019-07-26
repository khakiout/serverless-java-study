package com.khakiout.api.spring;

import com.khakiout.api.interfaces.http.handler.SampleHandler;
import com.khakiout.api.interfaces.http.router.SampleRouter;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class MainConfiguration {

    /**
     * Bean for SampleHandler.
     *
     * @return the handler
     */
    @Bean
    public SampleHandler sampleHandler() {
        return new SampleHandler();
    }

    /**
     * Bean for SampleRouter.
     * @param sampleHandler the related handler.
     *
     * @return the router
     */
    @Bean
    public RouterFunction sampleRouter(SampleHandler sampleHandler) {
        return new SampleRouter().sampleRouter(sampleHandler);
    }

}
