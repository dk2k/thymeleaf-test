package ru.outofrange.config;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends /*WebMvcConfigurerAdapter*/ WebMvcAutoConfigurationAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/alluser").setViewName("alluser");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/search").setViewName("search");
        registry.addViewController("/edit-user").setViewName("edit-user");
        
    }

}
