package com.sap.cloud.sdk.demo.controllers;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;
import com.sap.cloud.sdk.demo.command.GetCostCenterCommand;
import com.sap.cloud.sdk.demo.model.CostCenterLocal;
import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQuery;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQueryBuilder;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQueryResult;
import com.sap.cloud.sdk.s4hana.connectivity.ErpConfigContext;
import com.sap.cloud.sdk.s4hana.datamodel.odata.helper.Order;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.businesspartner.BusinessPartner;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.costcenter.CostCenter;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.purchaseorder.PurchaseOrder;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultCostCenterService;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultPurchaseOrderService;

@RestController
public class CostCenterController{

    private static final long serialVersionUID = 1L;
    private static final Logger logger = CloudLoggerFactory.getLogger(CostCenterController.class);

    private static final String CATEGORY_PERSON = "1";
    
    
    @RequestMapping("/s4cloud/costcenters")
    protected List<CostCenterLocal> getS4CostCenters( final HttpServletRequest request, final HttpServletResponse response )
        throws IOException, ODataException
    {
        logger.info("Fetching costcenters from S4HANA Cloud - API Hub");
        String destinationName = "ErpQueryEndpoint";
		final ErpConfigContext configContext = new ErpConfigContext(destinationName); 
		

		ODataQuery query= ODataQueryBuilder
		        .withEntity("/s4hanacloud/sap/opu/odata/sap/API_COSTCENTER_SRV",
		                "A_CostCenter")
		        .withHeader("apikey", "7PGAIoLeZfJzcTX1Dyt9ZpEu6bHxm0Ch", true)
		        .select("CostCenter", "CompanyCode", "CostCenterCategory")
		        .top(10)
		        .build();
		
		logger.info("Metadata URL: " + query.getMetadataQuery());
		
		ODataQueryResult result = query.execute(configContext);
		
		return result.asList(CostCenterLocal.class);
    }


    @RequestMapping("/s4cloud/vdm/costcenters")
    protected List<BusinessPartner> getCostCenters(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException, ODataException {
        final ErpConfigContext config = new ErpConfigContext("s4cloud");
		final List<BusinessPartner> costcenters = new GetCostCenterCommand(config,null).execute();
		
		return costcenters;
    }
}