package com.example.demo;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class StaticResourceHandler implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		String path = this.getClass().getResource("/indexImage/").getFile();
		
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");

		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");

		registry.addResourceHandler("/style/**").addResourceLocations("classpath:/static/style/");

		registry.addResourceHandler("/indexImage/**").addResourceLocations("file:"+ path);
		
	}
}