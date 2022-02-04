package com.bcp.reto.tipodecambio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Arrays;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig  {


    private final ResponseMessage m404 = codeStatus(404, "dato no encontrado");
    private final ResponseMessage m500 = codeStatus(500, "Error interno");

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("tipodecambio")
                .globalResponseMessage(RequestMethod.POST, Arrays.asList( m500))
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(m404, m500))
                .apiInfo(apiInfo()).select()
                .paths(regex("/api.*")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("API tipo de cambio").description("API tipo de cambio BCP")
                .termsOfServiceUrl("Terminos de Servicio").license("bcp").version("1.0.0").build();
    }
    private ResponseMessage codeStatus(int code, String msg) {
        return new ResponseMessageBuilder().code(code).message(msg).build();
    }
}