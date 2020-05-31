package com.javawomen.errorcenter.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
//import io.jsonwebtoken.lang.Arrays;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//@EnableSwagger2
@Configuration
public class SwaggerConfigurations {
 
	@Bean
	public Docket centralErrosApi() {		
		  return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.javawomen.errorcenter"))
	                .paths(PathSelectors.ant("/**"))
	                .build()
	                .useDefaultResponseMessages(true).apiInfo(metaInfo())
	                .globalOperationParameters( 
	                		Arrays.asList(
	                                new ParameterBuilder()
	                                    .name("Authorization")
	                                    .description("Header para Token JWT")
	                                    .modelRef(new ModelRef("string"))
	                                    .parameterType("header")
	                                    .required(false)
	                                    .build()));
	                                                  
	}
	
	private ApiInfo metaInfo() {
        return new ApiInfoBuilder()
                .title("Central de Erros - REST API")
                .description("Projeto desenvolvido no programa AceleraDev Java Women oferecido pela Codenation com o apoio da Conta Azul."
                		+ " O objetivo desta API é centralizar em um único local os registros de erros que surgem de outras camadas de aplicação.")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("HelloSquad ", " https://github.com/codenation-dev/squad-2-ad-java-contaazul-1/", null))
                .build();
    }

}
