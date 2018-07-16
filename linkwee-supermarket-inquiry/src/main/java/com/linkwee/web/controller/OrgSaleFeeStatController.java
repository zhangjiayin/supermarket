package com.linkwee.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.request.OrgSaleFeeListRequest;
import com.linkwee.web.service.CimOrgFeeGatherService;


@Controller
@RequestMapping("orgsalefee")
public class OrgSaleFeeStatController {
	@Resource
	private CimOrgFeeGatherService cimOrgFeedetailService; 

	

	@RequestMapping("init")
	public String advListPage(){
		return "orgSaleFee/cimorgfeedetail-list";
	}

	@RequestMapping("/list_ajax")
	@ResponseBody
	public DataTableReturn newsListAjax(OrgSaleFeeListRequest req, DataTable dataTable) throws Exception{
		DataTableReturn dataTableReturn = new DataTableReturn();
		Map<String,Object> params = new HashMap<String,Object>();
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CimOrginfo orgUser = (CimOrginfo)session.getAttribute("userInfo");
		params.put("orgNumber", orgUser.getOrgNumber());
		if(StringUtils.isNotBlank(req.getStartDate())){
			params.put("startDate", req.getStartDate());
		}
		if(StringUtils.isNotBlank(req.getEndDate())){
			params.put("endDate", req.getEndDate());
		}
		dataTableReturn = cimOrgFeedetailService.queryOrgSaleFee(params, dataTable);
		return dataTableReturn;
	}
	
	
}
