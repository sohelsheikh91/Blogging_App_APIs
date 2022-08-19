package com.springboot.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(getInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    public ApiInfo getInfo(){
         return new ApiInfo("Blogging Application",
                 "This project is Developed By Sohel Sheikh",
                 "1.0",
                 "Terms of Service",
                  new Contact("Sohel Sheikh","https://www.linkedin.com/in/sohel-sheikh-9b5124139/","sohelsheikh91@gmail.com"),
                 "Development of API's",
                 "API License URL",
                 Collections.emptyList());
    }
}
