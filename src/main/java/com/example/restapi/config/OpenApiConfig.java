package com.example.restapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        // Swagger Setting
        return new OpenAPI().info(new Info().title("User management API").version("1.0").description("RESTful API + JPA"));
    }
}
