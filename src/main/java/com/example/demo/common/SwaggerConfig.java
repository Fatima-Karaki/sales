package com.example.demo.common;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfig
{
	@Bean
	OpenAPI api()
	{
		return new OpenAPI().info(new Info().title("Sales Management Service").description("APIs related for managing sales"));
	}
}
