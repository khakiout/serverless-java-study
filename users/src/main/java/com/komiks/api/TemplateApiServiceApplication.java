package com.komiks.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.ReactiveWebServerFactoryAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@Import({
    ServerlessWebApplicationConfiguration.class,
    ReactiveWebServerFactoryAutoConfiguration.class
})
public class TemplateApiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemplateApiServiceApplication.class, args);
    }

}

