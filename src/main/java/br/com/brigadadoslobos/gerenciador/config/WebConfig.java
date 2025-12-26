package br.com.brigadadoslobos.gerenciador.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mapeia /uploads/** para a pasta uploads/ no servidor
        registry
                .addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}