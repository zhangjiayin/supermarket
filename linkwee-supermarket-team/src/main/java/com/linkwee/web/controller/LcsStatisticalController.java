package com.linkwee.web.controller;




import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.model.CrmSalesOrg;
import com.linkwee.web.request.CustomerStatisticalRequest;
import com.linkwee.web.request.DataStatisticsRequest;
import com.linkwee.web.request.LcsDetailRequest;
import com.linkwee.web.request.LcsFouseRequest;
import com.linkwee.web.request.LcsSaleRequest;
import com.linkwee.web.request.LcsSearchRequest;
import com.linkwee.web.request.LcsStatisticalRequest;
import com.linkwee.web.request.PlatformSaleRequest;
import com.linkwee.web.request.TeamSaleRequest;
import com.linkwee.web.request.TeamStatisticalRequest;
import com.linkwee.web.response.LcsDetailResponse;
import com.linkwee.web.response.LcsSaleInfoResponse;
import com.linkwee.web.response.LcsStatisticalResponse;
import com.linkwee.web.response.TotalStatisticResponse;
import com.linkwee.web.service.CrmSalesOrgService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.RequestLogging;

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
	public String teamPage(Model model,String mobile){
		DateTime  now = DateTime.now();
		String month = now.toString("yyyy-MM");
		if(!StringUtils.isBlank(mobile)){
			model.addAttribute("startDate", "2017-01-01");
		}else{
			model.addAttribute("startDate", month + "-01");
		}
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
    	if(orgUser == null){
    		return new DataTableReturn();
    	}
		return salesOrgService.getLcsStatisticalList(orgUser.getSalesOrgId(), req);
	}
	
	@RequestMapping("lcs/customer/lists")
	@ResponseBody
	public Object queryCustomerStatisticals(CustomerStatisticalRequest req){
		if(StringUtils.isBlank(req.getMobile())){
			return new DataTableReturn();
		}
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		return new DataTableReturn();
    	}
    
		return salesOrgService.getCustomerStatisticalList(orgUser.getSalesOrgId(), req);
	}
	
	@RequestMapping("lcs/team/total")
	@ResponseBody
	public Object queryTeamTotal(TeamStatisticalRequest req){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		return new DataTableReturn();
    	}
		return salesOrgService.getTeamStatistical(orgUser.getSalesOrgId(), req);
	}
	
	@RequestMapping("lcs/team/lists")
	@ResponseBody
	public Object queryTeamStatisticals(TeamStatisticalRequest req){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		return new DataTableReturn();
    	}
		return salesOrgService.getTeamStatisticalList(orgUser.getSalesOrgId(), req);
	}
	
	@RequestMapping("lcs/team/platforms")
	public String investmentDistributionStatisticsPage(Model model){
		model.addAttribute("selects", salesOrgService.getPlatfroms());
		return "lcs/platforms";
	}
	
	@ResponseBody
	@RequestLogging("合伙人管理后台-投资平台分布统计")
	@RequestMapping("lcs/team/platforms/investmentDistributionStatistics")
	public Object investmentDistributionStatistics(Map<String,String> req){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	Map<String, Object> map = Maps.newHashMap();
    	if(orgUser == null){
    		return map;
    	}
		map.put("total", salesOrgService.getInvestmentStatisticsTotal(orgUser.getSalesOrgId(),req.get("platfrom"), req.get("startTime"), req.get("endTime")));
		map.put("datas", salesOrgService.getInvestmentStatisticsList(orgUser.getSalesOrgId(),req.get("platfrom"), req.get("startTime"), req.get("endTime")));
		return map;
	}

	@RequestMapping("lcs/dataStatistic")
	public String lcsDataStatistic(Model model){
    	return "lcs/dataStatistic";
	}
	
	/**
	 * 总体数据
	 * @param request
	 * @return
	 */
	@RequestMapping("lcs/dataStatistic/statistics")
	@RequestLogging("总体数据")
	@ResponseBody
	public BaseResponse dataStatistics(DataStatisticsRequest request){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		return new BaseResponse();
    	}
    	request.setSalesOrgId(orgUser.getSalesOrgId());
		TotalStatisticResponse result = salesOrgService.queryTotalStatisticData(request);
		return AppResponseUtil.getSuccessResponse(result);
	}
	
	@RequestMapping("lcs/dataStatistic/platformSale")
	@ResponseBody
	public Object platformSale(PlatformSaleRequest req){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		return new DataTableReturn();
    	}
    	req.setSalesOrgId(orgUser.getSalesOrgId());
		return salesOrgService.getPlatformSale(req);
	}
	
	@RequestMapping("lcs/dataStatistic/levelSale")
	@ResponseBody
	public Object levelSale(PlatformSaleRequest req){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		return new DataTableReturn();
    	}
    	req.setSalesOrgId(orgUser.getSalesOrgId());
		return salesOrgService.getLevelSale(req);
	}
	
	@RequestMapping("lcs/teamSaleRecord")
	public String lcsTeamSaleRecord(Model model){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		model.addAttribute("error", "未登陆,请先登录");
			return "login";
    	}	
    	int teamMaxDepth =salesOrgService.getTeamMaxDepth(orgUser.getSalesOrgId());
    	model.addAttribute("teamMaxDepth", teamMaxDepth);
    	return "lcs/teamSaleRecord";
	}
	
	/**
	 * 团队出单记录
	 * @param req
	 * @return
	 */
	@RequestMapping("lcs/teamSaleRecord/list")
	@ResponseBody
	public Object lcsTeamSaleRecordList(TeamSaleRequest req){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		return new DataTableReturn();
    	}
    	req.setSalesOrgId(orgUser.getSalesOrgId());
		return salesOrgService.lcsTeamSaleRecordList(req);
	}
	
	@RequestMapping("lcs/detail")
	public String lcsDetail(Model model,LcsDetailRequest request){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		model.addAttribute("error", "未登陆,请先登录");
			return "login";
    	}	
    	request.setSalesOrgId(orgUser.getSalesOrgId());
    	LcsDetailResponse detailResp = salesOrgService.lcsDetail(request);
    	model.addAttribute("detailResp", detailResp);
    	return "lcs/lcsDetail";
	}
	
	@RequestMapping("lcs/detail/saleInfo")
	@RequestLogging("出单信息")
	@ResponseBody
	public BaseResponse lcsSaleInfo(LcsSaleRequest request){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		return new BaseResponse();
    	}
    	request.setSalesOrgId(orgUser.getSalesOrgId());
		LcsSaleInfoResponse result = salesOrgService.lcsSaleInfo(request);
		return AppResponseUtil.getSuccessResponse(result);
	}
	
	@RequestMapping("lcs/detail/saleList")
	@RequestLogging("出单列表")
	@ResponseBody
	public Object lcsSaleList(LcsSaleRequest request){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		return new DataTableReturn();
    	}
    	request.setSalesOrgId(orgUser.getSalesOrgId());
		return salesOrgService.lcsSaleList(request);
	}
	
	@RequestMapping("lcs/search")
	public String lcsSearch(Model model){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		model.addAttribute("error", "未登陆,请先登录");
			return "login";
    	}
    	return "lcs/lcsSearch";
	}
	
	@RequestMapping("lcs/searchInfoList")
	@RequestLogging("理财师信息列表")
	@ResponseBody
	public Object lcsSearchInfoList(LcsSearchRequest request){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		return new DataTableReturn();
    	}
    	request.setSalesOrgId(orgUser.getSalesOrgId());
		return salesOrgService.lcsSearchInfoList(request);
	}
	
	@RequestMapping("lcs/search/record")
	@RequestLogging("记录搜索信息")
	@ResponseBody
	public BaseResponse lcsSearchRecord(LcsSearchRequest request){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		return new BaseResponse();
    	}
    	request.setSalesOrgId(orgUser.getSalesOrgId());
		return salesOrgService.lcsSearchRecord(request);
	}
	
	@RequestMapping("lcs/searchHistory")
	@RequestLogging("记录搜索信息")
	@ResponseBody
	public BaseResponse lcsSearchHistory(LcsSearchRequest request){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		return new BaseResponse();
    	}
    	request.setSalesOrgId(orgUser.getSalesOrgId());
		return salesOrgService.lcsSearchHistory(request);
	}
	
	@RequestMapping("/lcs/fouse/record")
	@RequestLogging("记录关注信息")
	@ResponseBody
	public BaseResponse lcsFouseRecord(LcsFouseRequest request){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		return new BaseResponse();
    	}
    	request.setSalesOrgId(orgUser.getSalesOrgId());
		return salesOrgService.lcsFouseRecord(request);
	}
	
	@RequestMapping("lcs/fouse")
	public String lcsFouse(Model model){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		model.addAttribute("error", "未登陆,请先登录");
			return "login";
    	}
    	return "lcs/lcsFoused";
	}
	
	@RequestMapping("lcs/fouseList")
	@RequestLogging("理财师信息列表")
	@ResponseBody
	public Object lcsFouseList(LcsSearchRequest request){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		return new DataTableReturn();
    	}
    	request.setSalesOrgId(orgUser.getSalesOrgId());
		return salesOrgService.lcsFouseList(request);
	}
	
	@RequestMapping("lcs/unsale")
	public String lcsUnSale(Model model){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		model.addAttribute("error", "未登陆,请先登录");
			return "login";
    	}
    	return "lcs/lcsNotSale";
	}
	
	@RequestMapping("lcs/unsaleList")
	@RequestLogging("理财师信息列表")
	@ResponseBody
	public Object lcsUnSaleList(LcsSearchRequest request){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		return new DataTableReturn();
    	}
    	request.setSalesOrgId(orgUser.getSalesOrgId());
		return salesOrgService.lcsUnSaleList(request);
	}
		
	@RequestMapping("lcs/unRepaymentSaleRecord")
	public String lcsUnRepaymentSaleRecord(Model model){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		model.addAttribute("error", "未登陆,请先登录");
			return "login";
    	}
    	return "lcs/lcsUnRepaymentSaleRecord";
	}
	
	/**
	 * 浮动期未回款出单记录
	 * @param req
	 * @return
	 */
	@RequestMapping("lcs/flowDeadLine/unRepaymentList")
	@ResponseBody
	public Object lcsFlowDeadLineUnRepaymentList(TeamSaleRequest req){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		return new DataTableReturn();
    	}
    	req.setSalesOrgId(orgUser.getSalesOrgId());
		return salesOrgService.lcsFlowDeadLineUnRepaymentList(req);
	}
	
	/**
	 * 浮动期未回款出单记录汇总
	 * @param req
	 * @return
	 */
	@RequestMapping("lcs/flowDeadLine/unRepaymentList/total")
	@ResponseBody
	public Object unRepaymentListTotal(TeamSaleRequest req){
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg orgUser = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(orgUser == null){
    		return new DataTableReturn();
    	}
    	req.setSalesOrgId(orgUser.getSalesOrgId());
		return salesOrgService.unRepaymentListTotal(req);
	}
	
	/**
	 * 转换器
	 *
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}
