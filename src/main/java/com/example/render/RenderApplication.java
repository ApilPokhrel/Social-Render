package com.example.render;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;


import com.example.render.uploads.ComparisionFileUpload;

@SpringBootApplication
@ComponentScan({"com.example.render", "com.example.render.controller.api"})
public class RenderApplication {


	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {
		SpringApplication.run(RenderApplication.class, args);
	}
	
	@Bean
	public ComparisionFileUpload cfu() {
		return new ComparisionFileUpload();
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	

	
	
}
