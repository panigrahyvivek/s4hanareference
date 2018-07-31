package com.sap.cloud.sdk.demo.controllers;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;
import com.sap.cloud.sdk.demo.command.GetSalesOrderCommand;
import com.sap.cloud.sdk.demo.model.SalesOrderLocal;
import com.sap.cloud.sdk.odatav2.connectivity.ODataException;

import com.sap.cloud.sdk.s4hana.connectivity.ErpConfigContext;


@RestController
public class SalesOrderController{

    private static final long serialVersionUID = 1L;
    private static final Logger logger = CloudLoggerFactory.getLogger(CostCenterController.class);

    private static final String CATEGORY_PERSON = "1";
    
    private ErpConfigContext getErpConfigContext(){
        final ErpConfigContext config = new ErpConfigContext("ErpQueryEndpoint");
        return config;
    }
    
    @RequestMapping("/s4cloud/salesorders")
    protected List<SalesOrderLocal> getS4SalesOrders( final HttpServletRequest request, final HttpServletResponse response )
        throws IOException, ODataException
    {
    	final List<SalesOrderLocal> salesorders = new GetSalesOrderCommand(getErpConfigContext()).execute();
    	return salesorders;
		
    }


}