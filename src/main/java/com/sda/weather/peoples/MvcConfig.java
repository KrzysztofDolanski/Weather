package com.sda.weather.peoples;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class  MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/weather").setViewName("weather");
        registry.addViewController("localise").setViewName("localise");
        registry.addViewController("localiseCity").setViewName("localisecity");
        registry.addViewController("localise/{id}/forecast").setViewName("ForecastById");
        registry.addViewController("/login").setViewName("login");
    }
}
