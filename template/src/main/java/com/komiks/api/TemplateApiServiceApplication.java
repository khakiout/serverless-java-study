package com.komiks.api;

import com.komiks.api.spring.MainConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
// We use direct @Import instead of @ComponentScan to speed up cold starts
@Import({MainConfiguration.class})
public class TemplateApiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemplateApiServiceApplication.class, args);
    }

}

