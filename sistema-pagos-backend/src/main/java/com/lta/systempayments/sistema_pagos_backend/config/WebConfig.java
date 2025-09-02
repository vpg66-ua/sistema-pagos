package com.lta.systempayments.sistema_pagos_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println(">>> Configuración CORS cargada <<<");
        registry.addMapping("/**") // Aplica la configuración a todos los endpoints
                .allowedOrigins("*") // Origen permitido (ej. tu frontend)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*") // Todos los encabezados permitidos
                .maxAge(3600); // Tiempo en segundos que el navegador puede almacenar la respuesta de la pre-flight
    }
}