package com.linkwee.test.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.test.TestSupport;
import com.linkwee.web.service.CimOrgFeeGatherService;

public class CimOrgFeedetailServiceTest extends TestSupport{
	
	@Resource
	private CimOrgFeeGatherService cimOrgFeedetailService; 
	
    @Test
    public void testSysMsgPage() throws Exception {
    	start();
		
    	DataTableReturn dataTableReturn = new DataTableReturn();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orgNumber", "10000003");
		DataTable dataTable = new DataTable();
		dataTable.setStart(0);
		dataTable.setLength(10);
		dataTableReturn = cimOrgFeedetailService.queryOrgSaleFee(params, dataTable);
    	System.out.println(dataTableReturn);
        end();
    }
}
