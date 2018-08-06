package com.sap.cloud.sdk.demo.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.sap.cloud.sdk.result.ElementName;

import lombok.Data;

@Data
@Component
public class SalesOrderLocal{
	
	public SalesOrderLocal(String so, String stype, String sorg, String tn, Date rd){
		this.salesOrder = so;
		this.salesOrderType = stype;
		this.salesOrganization = sorg;
		this.totalNetAmount = tn;
		this.requestDeliveryDate = rd;
	}
	
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

