package com.sap.cloud.sdk.demo.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.sap.cloud.sdk.result.ElementName;

import lombok.Data;

@Data
@Component
public class SalesOrderLocal{
	
	@ElementName( "SalesOrder" )
	private String salesOrder;
	
	@ElementName( "SalesOrderType" )
	private String salesOrderType;
	
	@ElementName( "SalesOrganization" )
	private String salesOrganization;
	
	@ElementName( "TotalNetAmount" )
	private String totalNetAmount;
	
	@ElementName( "RequestedDeliveryDate" )
	private Date requestDeliveryDate;

}

