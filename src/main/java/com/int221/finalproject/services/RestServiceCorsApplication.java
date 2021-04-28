package com.int221.finalproject.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RestServiceCorsApplication implements WebMvcConfigurer {

    public static final String HOST_URL = "http://"+System.getenv("ENV_HOST") == null ? "localhost:8081" : System.getenv("ENV_HOST");

    @Value("#{'GET, PUT, HEAD, POST, DELETE, OPTION'.split(', ')}")
    private String[] methodList;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(HOST_URL).allowedMethods(methodList);
    }

}
