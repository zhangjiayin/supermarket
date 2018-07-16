package com.linkwee.web.controller;




import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.model.CrmSalesOrg;
import com.linkwee.web.request.CustomerStatisticalRequest;
import com.linkwee.web.request.LcsStatisticalRequest;
import com.linkwee.web.request.TeamStatisticalRequest;
import com.linkwee.web.response.LcsStatisticalResponse;
import com.linkwee.web.service.CrmSalesOrgService;

@Controller  
@RequestMapping("statistics")
public class LcsStatisticalController {

	@Autowired
	private CrmSalesOrgService salesOrgService;
	
	@RequestMapping("lcsPage")
	public String lcspage(Model model){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		model.addAttribute("error", "未登陆,请先登录");
			return "login";
    	}	
    	LcsStatisticalResponse lcsStatisticalResponse =salesOrgService.getLcsStatistical(orgUser.getSalesOrgId());
    	model.addAttribute("totalCount", lcsStatisticalResponse.getTotalCount());
    	model.addAttribute("totalAmount", lcsStatisticalResponse.getTotalAmount());
		return "lcs/lcspage";
	}
	
	@RequestMapping("lcs/customerPage")
	public String customerPage(){
		return "lcs/customerPage";
	}
	
	@RequestMapping("lcs/teamPage")
	public String teamPage(Model model){
		DateTime  now = DateTime.now();
		String month = now.toString("yyyy-MM");
		model.addAttribute("startDate", month + "-01");
		model.addAttribute("endDate", now.toString("yyyy-MM-dd"));
		model.addAttribute("platfroms", salesOrgService.getPlatfroms());
		return "lcs/teamPage";
	}

	@RequestMapping("lcs/lists")
	@ResponseBody
	public Object queryLcsStatisticals(LcsStatisticalRequest req){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null) return new DataTableReturn();
		return salesOrgService.getLcsStatisticalList(orgUser.getSalesOrgId(), req);
	}
	
	@RequestMapping("lcs/customer/lists")
	@ResponseBody
	public Object queryCustomerStatisticals(CustomerStatisticalRequest req){
		if(StringUtils.isBlank(req.getMobile())) return new DataTableReturn();
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null) return new DataTableReturn();
    
		return salesOrgService.getCustomerStatisticalList(orgUser.getSalesOrgId(), req);
	}
	
	@RequestMapping("lcs/team/total")
	@ResponseBody
	public Object queryTeamTotal(TeamStatisticalRequest req){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null) return new DataTableReturn();
		return salesOrgService.getTeamStatistical(orgUser.getSalesOrgId(), req);
	}
	
	@RequestMapping("lcs/team/lists")
	@ResponseBody
	public Object queryTeamStatisticals(TeamStatisticalRequest req){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null) return new DataTableReturn();
		return salesOrgService.getTeamStatisticalList(orgUser.getSalesOrgId(), req);
	}
}
