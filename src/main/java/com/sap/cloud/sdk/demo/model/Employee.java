package com.sap.cloud.sdk.demo.model;

import org.springframework.stereotype.Component;

import com.sap.cloud.sdk.result.ElementName;

import lombok.Data;

@Data
@Component
public class Employee{
	
	@ElementName( "Title" )
	private String title;
	
	public String getTitle() {
		return title;
	}

}

