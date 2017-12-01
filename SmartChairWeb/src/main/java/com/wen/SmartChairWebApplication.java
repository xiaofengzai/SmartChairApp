package com.wen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SmartChairWebApplication {

	public static void main(String[] args) {
		SpringApplication springApplication =new SpringApplication(SmartChairWebApplication.class);
		springApplication.addListeners(new ApplicationStartup());
		SpringApplication.run(SmartChairWebApplication.class, args);
	}
}
