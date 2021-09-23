package ru.geekbrains.shop.buisness.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("welcome");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirName = "upload";
        String staticPath = Paths.get(System.getProperty("user.dir"), dirName).toFile().getAbsolutePath();

        registry.addResourceHandler("/" + dirName + "/**")
                .addResourceLocations("file://" + staticPath + "/");

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

    }
}
