package net.movielot.movielot.configuration;

import net.movielot.movielot.configuration.converter.EmotionConstantConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${client.front.host}")
    private String frontHost;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins(frontHost);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new EmotionConstantConverter());
    }
}
