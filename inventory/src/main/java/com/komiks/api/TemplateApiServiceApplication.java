package com.komiks.api;

import com.komiks.api.spring.MainConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({MainConfiguration.class})
public class TemplateApiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemplateApiServiceApplication.class, args);
    }

}

