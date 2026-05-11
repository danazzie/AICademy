package com.aicademy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class CorsConfig {
    @Value("${app.frontend-url:http://localhost:5173,http://127.0.0.1:5173}")
    private String frontendUrls;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        String[] allowedOrigins = Arrays.stream(frontendUrls.split(","))
                .map(String::trim)
                .flatMap(origin -> Arrays.stream(new String[]{origin, origin.replaceAll("/+$", "")}))
                .distinct()
                .toArray(String[]::new);

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins(allowedOrigins)
                        .allowedMethods("GET", "POST", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}
