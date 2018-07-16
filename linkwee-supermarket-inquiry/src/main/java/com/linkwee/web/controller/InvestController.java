package com.linkwee.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.request.CustomerInvestRequest;
import com.linkwee.web.service.TcInvestService;

@Controller
@RequestMapping("invest")
public class InvestController {

	@Autowired
	private TcInvestService investService;
	
	
	@RequestMapping("init")
	public String init(){
		return "investor/investList";
	}
	
	@RequestMapping("queryCustomerInvestStatistics")
	@ResponseBody
	public Object queryCustomerInvestStatistics(CustomerInvestRequest req){
		DataTableReturn dataTableReturn = new DataTableReturn();
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CimOrginfo orgUser = (CimOrginfo)session.getAttribute("userInfo");
    	if(orgUser == null) return dataTableReturn;
		req.setPlatfrom(orgUser.getOrgNumber());
		return investService.queryCustomerInvestStatistics(req);
	}
	
	
	@RequestMapping("getCustomerInvestDetailPage")
	public String getCustomerInvestDetailPage(){
		return "investor/investorDetail";
	}
	
	@RequestMapping("queryCustomerInvestDetail")
	@ResponseBody
	public Object queryCustomerInvestDetail(CustomerInvestRequest req){
		DataTableReturn dataTableReturn = new DataTableReturn();
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CimOrginfo orgUser = (CimOrginfo)session.getAttribute("userInfo");
    	if(orgUser == null) return dataTableReturn;
		req.setPlatfrom(orgUser.getOrgNumber());
		return investService.queryCustomerInvestDetail(req);
	}
	
}
