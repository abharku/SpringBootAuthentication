package com.bluenimbus.spring.auth.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket api() {
      return new Docket(DocumentationType.SWAGGER_2)//
          .select()//
          .apis(RequestHandlerSelectors.basePackage("com.bluenimbus.spring.auth.controller"))//
          .paths(PathSelectors.any())
          .paths(Predicates.not(PathSelectors.regex("/error")))//
          .paths(Predicates.not(PathSelectors.regex("/swagger")))
          .build()//
          .apiInfo(metadata())//
          .useDefaultResponseMessages(false)//
          .securitySchemes(new ArrayList<>(Arrays.asList(new ApiKey("authHeader", "Authorization", "Header"))))//
          .genericModelSubstitutes(Optional.class);

    }

    private ApiInfo metadata() {
      return new ApiInfoBuilder()//
          .title("Spring Auth demo REST APIs with GCP")//
          .description(
              "This project contains Spring and Rest auth examlple. It also demostrates how we can create Swagger documentation. There are falvours of GCP App engine and Cloud sql")//
          .version("1.0.0")//
          .license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")//
          .contact(new Contact("BlueNimbus Support", "www.blue-nimbus.com", "support@blue-nimbus.com"))//
          .build();
    }

	
}
