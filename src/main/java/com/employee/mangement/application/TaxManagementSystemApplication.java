package com.employee.mangement.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.employee"})
public class TaxManagementSystemApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		
		// set register error page filter false
		setRegisterErrorPageFilter(false);

		builder.sources(TaxManagementSystemApplication.class);
		return builder;
	}

	public static void main(String[] args) {
		SpringApplication.run(TaxManagementSystemApplication.class, args);
	}

}
