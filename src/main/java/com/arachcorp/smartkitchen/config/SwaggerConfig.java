package com.arachcorp.smartkitchen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Set;

@Configuration
@EnableSwagger2
@Profile(value = {"dev", "test"})
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes( Set.of("application/json"))
                .produces( Set.of("application/json"))
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.arachcorp.smartkitchen.rest"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("SmartKitchen")
                .description("RestService SmartKitchen | Arach")
                .version("1.0")
                .contact(contact())
                .build();
    }

    private Contact contact(){
        return new Contact(
                "Arach Corp",
                "https://github.com/Arach-Corp",
                "tec.arach@gmail.com");
    }

}
