package com.carwash.washerservice;

import com.carwash.washerservice.service.SequenceGeneratorService;
import com.carwash.washerservice.service.WasherServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableEurekaClient
@OpenAPIDefinition

public class WasherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WasherServiceApplication.class, args);
	}

//	@Bean
//	public WasherServiceImpl washerService(){
//		return new WasherServiceImpl();
//	}

	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
			}
		};
	}
}
