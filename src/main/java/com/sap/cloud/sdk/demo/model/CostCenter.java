package com.sap.cloud.sdk.demo.model;

import org.springframework.stereotype.Component;

import com.sap.cloud.sdk.result.ElementName;

import lombok.Data;

@Data
@Component
public class CostCenter{
	
	@ElementName( "CostCenter" )
	private String costCenter;
	
	@ElementName( "CostCenterID" )
	private String costCenterId;
	
	@ElementName( "Status" )
	private String status;
	
	@ElementName( "CompanyCode" )
	private String companyCode;
	
	@ElementName( "CostCenterCategory" )
	private String category;
	
	@ElementName( "CostCenterDescription" )
	private String costCenterDescription;
	

}

