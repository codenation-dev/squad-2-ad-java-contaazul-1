package com.javawomen.errorcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication//vem com tomcat para rodar a api
@EnableSpringDataWebSupport
@EnableSwagger2
public class ErrorCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErrorCenterApplication.class, args);
	}

}
