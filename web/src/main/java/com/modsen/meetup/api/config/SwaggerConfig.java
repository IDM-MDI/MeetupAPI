package com.modsen.meetup.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
public class SwaggerConfig {
    public static final String EVENT_TAG = "Event API";
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage("com.modsen.meetup.api.controller"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag(EVENT_TAG, "Event API controller with CRUD operation"));
    }
}
