package com.komiks.api.spring;

import com.komiks.api.application.LocationsApplication;
import com.komiks.api.interfaces.http.handler.LocationHandler;
import com.komiks.api.interfaces.http.handler.SampleHandler;
import com.komiks.api.interfaces.http.router.LocationsRouter;
import com.komiks.api.interfaces.http.router.SampleRouter;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.accept.RequestedContentTypeResolverBuilder;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;


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
     * The location application.
     */
    @Bean
    public LocationsApplication locationsApplication() {
        return new LocationsApplication();
    }

    /**
     * Bean for Location Handler.
     */
    @Bean
    public LocationHandler locationHandler(LocationsApplication locationsApplication) {
        return new LocationHandler(locationsApplication);
    }

    /**
     * The location routers.
     */
    @Bean
    public RouterFunction locationRouter(LocationHandler locationHandler) {
        return new LocationsRouter().sampleRouter(locationHandler);
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
     * @param sampleHandler the related handler.
     *
     * @return the router
     */
    @Bean
    public RouterFunction sampleRouter(SampleHandler sampleHandler) {
        return new SampleRouter().sampleRouter(sampleHandler);
    }

}
