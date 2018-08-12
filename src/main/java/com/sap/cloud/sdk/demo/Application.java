package com.sap.cloud.sdk.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//public class Application extends SpringBootServletInitializer{

@ServletComponentScan({"com.sap.cloud.sdk"})
@SpringBootApplication

public class Application{
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}