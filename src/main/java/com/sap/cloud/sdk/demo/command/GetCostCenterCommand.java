package com.sap.cloud.sdk.demo.command;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;
import com.sap.cloud.sdk.demo.model.CostCenterLocal;
import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.s4hana.connectivity.ErpCommand;
import com.sap.cloud.sdk.s4hana.connectivity.ErpConfigContext;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.businesspartner.BusinessPartner;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.BusinessPartnerService;

public class GetCostCenterCommand extends ErpCommand<List<BusinessPartner>>{
	
	private static final Logger logger = CloudLoggerFactory.getLogger(GetCostCenterCommand.class);

	
	private BusinessPartnerService businessPartnerService;
	
	public GetCostCenterCommand(ErpConfigContext erpConfigContext, BusinessPartnerService businessPartnerService) {
		super(GetCostCenterCommand.class, erpConfigContext);
		this.businessPartnerService = businessPartnerService;
	}
	

	@Override
	protected List<BusinessPartner> run() throws Exception {
		// TODO Auto-generated method stub
		try {

            return businessPartnerService.getAllBusinessPartner()
                        .filter(BusinessPartner.IS_NATURAL_PERSON.eq("X"))
                        .select(BusinessPartner.FIRST_NAME,
                                BusinessPartner.LAST_NAME)
                        .execute(getConfigContext());

        } catch (final ODataException e) {
            throw new HystrixBadRequestException(e.getMessage(), e);
        }
	}
	
	@Override
    protected List<BusinessPartner> getFallback() {
        logger.warn("Fallback called because of exception:", getExecutionException());
        return Collections.emptyList();
    }
}