package com.int221.finalproject.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfigurer implements WebMvcConfigurer {

    public static final String HOST_URL = (System.getenv("ENV_HOST") == null ? "http://localhost" : System.getenv("ENV_HOST"));

    @Value("#{'GET, PUT, HEAD, POST, DELETE, OPTION'.split(', ')}")
    private String[] methodList;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(HOST_URL).allowedMethods(methodList);
    }

}
