package com.komiks.api;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.komiks.api.spring.MainConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.reactor.core.ReactorCoreAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.accept.RequestedContentTypeResolverBuilder;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@Import({
    JacksonAutoConfiguration.class,
    HttpMessageConvertersAutoConfiguration.class,
    CodecsAutoConfiguration.class,
    ReactorCoreAutoConfiguration.class,
    WebFluxAutoConfiguration.class,
    HttpHandlerAutoConfiguration.class,
    MainConfiguration.class
})
public class ServerlessWebApplicationConfiguration {

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

}
