package com.bcp.reto.tipodecambio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan({
		"com.bcp.reto.tipodecambio"})
public class TipodecambioApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TipodecambioApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TipodecambioApplication.class);
	}
}
