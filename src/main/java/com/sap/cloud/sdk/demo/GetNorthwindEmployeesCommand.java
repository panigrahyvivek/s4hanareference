package com.sap.cloud.sdk.demo;

import com.sap.cloud.sdk.demo.model.Employee;
import com.sap.cloud.sdk.frameworks.hystrix.HystrixUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQueryBuilder;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQueryResult;
import com.sap.cloud.sdk.s4hana.connectivity.ErpCommand;
import com.sap.cloud.sdk.s4hana.connectivity.ErpConfigContext;

public class GetNorthwindEmployeesCommand extends ErpCommand<Map<String, Object>>{

	private static final Logger logger = CloudLoggerFactory.getLogger(GetNorthwindEmployeesCommand.class);

	
	public GetNorthwindEmployeesCommand(final ErpConfigContext configContext) {
        super(GetNorthwindEmployeesCommand.class, configContext);
    }


	@Override
	protected Map<String, Object> run() throws Exception {
		// TODO Auto-generated method stub
		logger.info("Fetching Employees from Northwind");
        String destinationName = "northwind";
		final ErpConfigContext configContext = new ErpConfigContext(destinationName); 

		ODataQueryResult result = ODataQueryBuilder
		        .withEntity("/",
		                "Employees")
		        .select("Title")
		        .top(10)
		        .build()
		        .execute(configContext);
         
		logger.info(result.toString());
		
		List<Employee> testdata = result.collect("value").asList(Employee.class);
		logger.info(testdata.toString());
		
		return result.asMap();

	}
	
	@Override
    protected Map<String, Object> getFallback() {
        logger.warn("Fallback called because of exception:", getExecutionException());
        return Collections.emptyMap();
    }
	
}