package com.javawomen.errorcenter.config.swagger;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javawomen.errorcenter.model.User;

//import io.jsonwebtoken.lang.Arrays;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import io.swagger.models.auth.In;

//@EnableSwagger2
@Configuration
public class SwaggerConfigurations {
 
	//definir um bean para o spring e devolver o obj tipo docket do swagger
	@Bean
	public Docket centralErrosApi() {
		//estanciar o obj docket e settar as infos que o springfox precisa p config o nosso projeto
		
		  return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.javawomen.errorcenter"))
	                .paths(PathSelectors.ant("/**"))
	                .build()
	                .ignoredParameterTypes(User.class)
	                .useDefaultResponseMessages(false)                                   
	                //.globalResponseMessage(RequestMethod.GET, responseMessageForGET())//new ApiKey("JWT", "Authorization", "header");
	                //.securitySchemes(Arrays.asList(new ApiKey("JWT Token Access", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
	                //.securityContexts(Arrays.asList(securityContext()))
	                .apiInfo(apiInfo())
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
	
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Central de Erros - REST API")
                .description("Projeto desenvolvido no programa AceleraDev Java Women oferecido pela Codenation com o apoio da Conta Azul."
                		+ "/n"
                		+ " O objetivo desta API é centralizar em um único local os registros de erros que surgem de outras camadas de aplicação.")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("HelloSquad", "https://github.com/codenation-dev/squad-2-ad-java-contaazul-1/", "hellosquad@gmail.com"))
                .build();
    }
	
	//nao se aplica
	
	
    private List<ResponseMessage> responseMessageForGET()
    {
        return new ArrayList<ResponseMessage>() {
            private static final long serialVersionUID = 1L;

            {
            add(new ResponseMessageBuilder()   
                .code(500)
                .message("500 message")
                .responseModel(new ModelRef("Error"))
                .build());
            add(new ResponseMessageBuilder() 
                .code(403)
                .message("Forbidden!")
                .build());
        }};
    }
    
    private SecurityContext securityContext() {
        return SecurityContext.builder()
            //.securityReferences(defaultAuth())
            //.forPaths(PathSelectors.ant("/environment/**"))
            //.forPaths(PathSelectors.ant("/environment"))
            //.forPaths((PathSelectors.ant("/**")))
            .build();
    }
    
    List<SecurityReference> defaultAuth() {
    	
        AuthorizationScope authorizationScope = new AuthorizationScope("ADMIN", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        
        authorizationScopes[0] = authorizationScope;
        
        return Arrays.asList(new SecurityReference("Token Access", authorizationScopes));
    }
}
