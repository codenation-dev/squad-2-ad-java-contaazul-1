package com.javawomen.errorcenter.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.javawomen.errorcenter.model.User;

//import io.jsonwebtoken.lang.Arrays;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//@EnableSwagger2
@Configuration
public class SwaggerConfigurations {

	//definir um bean para o spring e devolver o obj tipo docket do swagger
	@Bean
	public Docket centralErrosApi() {
		//estanciar o obj docket e settar as infos que o springfox precisa p config o nosso projeto
		
		  return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.example.app"))
	                .paths(PathSelectors.ant("/**"))
	                .build()
	                .ignoredParameterTypes(User.class)
	                .globalOperationParameters( //desabilita, testa, depois habilita e testa novamente
	                        Arrays.asList(
	                                new ParameterBuilder()
	                                    .name("Authorization")
	                                    .description("Header para Token JWT")
	                                    .modelRef(new ModelRef("string"))
	                                    .parameterType("header")
	                                    .required(false)
	                                    .build()));
	                                    
	}
}
