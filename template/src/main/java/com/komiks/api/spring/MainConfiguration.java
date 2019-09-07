package com.komiks.api.spring;

import com.komiks.api.interfaces.http.handler.SampleHandler;
import com.komiks.api.interfaces.http.router.SampleRouter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.accept.RequestedContentTypeResolverBuilder;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;


@Configuration
public class MainConfiguration {

    /**
     * Set the default response to 'application/json'.
     *
     * @return the webflux configuration.
     */
    @Bean
    public WebFluxConfigurer webFluxConfigurer() {
        return new WebFluxConfigurer() {
            @Override
            public void configureContentTypeResolver(RequestedContentTypeResolverBuilder builder) {
                builder.fixedResolver(MediaType.APPLICATION_JSON);
            }
        };
    }

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
     *
     * @param sampleHandler the related handler.
     * @return the router
     */
    @Bean
    public RouterFunction sampleRouter(SampleHandler sampleHandler) {
        return new SampleRouter().sampleRouter(sampleHandler);
    }

}
