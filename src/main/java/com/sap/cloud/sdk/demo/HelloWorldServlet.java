package com.sap.cloud.sdk.demo;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;
import com.sap.cloud.sdk.demo.model.CostCenter;
import com.sap.cloud.sdk.odatav2.connectivity.ODataCreateRequestBuilder;
import com.sap.cloud.sdk.odatav2.connectivity.ODataCreateResult;
import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQuery;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQueryBuilder;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQueryResult;
import com.sap.cloud.sdk.s4hana.connectivity.ErpConfigContext;

@RestController
public class HelloWorldServlet
{
    private static final long serialVersionUID = 1L;
    private static final Logger logger = CloudLoggerFactory.getLogger(HelloWorldServlet.class);

    @RequestMapping("/hello")
    protected void sayHello( final HttpServletRequest request, final HttpServletResponse response )
        throws IOException
    {
        logger.info("I am running!");
        response.getWriter().write("Hello World!!!!!");
    }
    
    @RequestMapping("/s4cloud/costcenters")
    protected List<CostCenter> getS4CostCenters( final HttpServletRequest request, final HttpServletResponse response )
        throws IOException, ODataException
    {
        logger.info("Fetching costcenters from S4HANA Cloud - API Hub");
        String destinationName = "ErpQueryEndpoint";
		final ErpConfigContext configContext = new ErpConfigContext(destinationName); 
		

		ODataQuery query= ODataQueryBuilder
		        .withEntity("/s4hanacloud/sap/opu/odata/sap/API_COSTCENTER_SRV",
		                "A_CostCenter")
		        .withHeader("apikey", "7PGAIoLeZfJzcTX1Dyt9ZpEu6bHxm0Ch", true)
		        .select("CostCenter")
		        .top(10)
		        .build();
		
		logger.info("Metadata URL: " + query.getMetadataQuery());
		
		ODataQueryResult result = query.execute(configContext);
		
		return result.asList(CostCenter.class);
    }
    
}
