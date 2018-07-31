package com.sap.cloud.sdk.demo.command;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;
import com.sap.cloud.sdk.demo.model.CostCenterLocal;
import com.sap.cloud.sdk.demo.model.Employee;
import com.sap.cloud.sdk.demo.model.SalesOrderLocal;
import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQuery;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQueryBuilder;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQueryResult;
import com.sap.cloud.sdk.s4hana.connectivity.ErpCommand;
import com.sap.cloud.sdk.s4hana.connectivity.ErpConfigContext;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.businesspartner.BusinessPartner;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.salesorder.SalesOrder;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.BusinessPartnerService;

public class GetSalesOrderCommand extends ErpCommand<List<SalesOrderLocal>>{
	
	private static final Logger logger = CloudLoggerFactory.getLogger(GetSalesOrderCommand.class);

	
	public GetSalesOrderCommand(ErpConfigContext erpConfigContext) {
		super(GetCostCenterCommand.class, erpConfigContext);
	}
	

	@Override
	protected List<SalesOrderLocal> run() throws Exception {
		// TODO Auto-generated method stub
		try {

			String destinationName = "ErpQueryEndpoint";
			final ErpConfigContext configContext = new ErpConfigContext(destinationName); 

			ODataQuery query= ODataQueryBuilder
			        .withEntity("/s4hanacloud/sap/opu/odata/sap/API_SALES_ORDER_SRV",
			                "A_SalesOrder")
			        .withHeader("apikey", "7PGAIoLeZfJzcTX1Dyt9ZpEu6bHxm0Ch", true)
			        .select("SalesOrder", "SalesOrderType", "SalesOrganization","TotalNetAmount", "RequestedDeliveryDate")
			        .top(10)
			        .build();
			
			ODataQueryResult result = query.execute(configContext);
	         
			return result.asList(SalesOrderLocal.class);
			

        } catch (final ODataException e) {
            throw new HystrixBadRequestException(e.getMessage(), e);
        }
	}
	
	@Override
    protected List<SalesOrderLocal> getFallback() {
        logger.warn("Fallback called because of exception:", getExecutionException());
        return Collections.emptyList();
    }
}