package com.sap.cloud.sdk.demo;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;
import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.s4hana.connectivity.ErpConfigContext;

import com.sap.cloud.sdk.demo.model.Employee;

@RestController
public class NorthwindController
{
    private static final long serialVersionUID = 1L;
    private static final Logger logger = CloudLoggerFactory.getLogger(NorthwindController.class);
    
    private ErpConfigContext getErpConfigContext(){
        final ErpConfigContext config = new ErpConfigContext("northwind");
        return config;
    }
    
    @RequestMapping("/northwind/employees")
    public ResponseEntity<Map<String, Object>> getNorthWindEmployees( final HttpServletRequest request, final HttpServletResponse response )
        throws IOException, ODataException
    {
        logger.info("Fetching Employees from Northwind");
        
        final Map<String, Object> employees = new GetNorthwindEmployeesCommand(getErpConfigContext()).execute();
        
        
        logger.info("Response Receieved : " + employees);
		return ResponseEntity.ok(employees);
    }
}
