package com.linkwee.web.controller.customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.export.ExportSupport;
import com.linkwee.core.util.DateUtils;
import com.linkwee.product.service.ProductManageService;
import com.linkwee.web.api.BaseController;
import com.linkwee.web.model.InvestorProfitResp;
import com.linkwee.web.model.InvestorReq;
import com.linkwee.web.model.InvestorResp;
import com.linkwee.web.model.InvestorUserInfo;
import com.linkwee.web.model.MyInvestedCustomerResp;
import com.linkwee.web.model.product.ProductManageRep;
import com.linkwee.web.model.product.ProductSaleDtlResp;
import com.linkwee.web.model.product.ProductSaleManageResp;
import com.linkwee.web.service.CustomerCftRelFixWebService;
import com.linkwee.web.service.InvestorUserInfoService;
import com.linkwee.web.service.UsercustomerrelService;
import com.linkwee.web.util.RequestLogging;


@Controller
@RequestMapping(value = "export")
@RequestLogging("数据导出")
public class ExportController extends BaseController {
	@Resource
	private CustomerCftRelFixWebService customerCftRelFixService;
	@Resource
	private UsercustomerrelService usercustomerrelService;
	@Resource
	private ExportSupport exportSupport;
	@Resource
	private ProductManageService productManageService;
	@Resource
	private InvestorUserInfoService investorUserInfoService;
	
	@RequestMapping(value = "investor")
	@RequestLogging("投资客户导出")
	public void export(HttpServletRequest request, HttpServletResponse response,InvestorReq investorReq) {
		//导出默认时间段数据
		if(StringUtils.isBlank(investorReq.getRegTimeStart())|| StringUtils.isBlank(investorReq.getRegTimeEnd())){
			investorReq.setRegTimeStart(DateUtils.format(DateUtils.subDay(new Date(), 30),"yyyy-MM-dd"));
			investorReq.setRegTimeEnd(DateUtils.getNow());
		}
		String tempFileName = "investor/investorList.xls";
		investorReq.setPaging(false);
		PaginatorResponse<InvestorResp> resp = customerCftRelFixService.queryInvestorList(investorReq);
		 Map<String, Object> params = new HashMap<String,Object>();
		 params.put("size", Long.valueOf(resp.getTotalCount()));
		 params.put("list", resp.getDatas());
		 exportSupport.export( request,  response,  tempFileName,  params);
	}


	
	@RequestMapping(value = "visited")
	@RequestLogging("投资记录")
	public void vistedExport(HttpServletRequest request, HttpServletResponse response,InvestorReq investorReq) {
		//导出默认时间段数据
		if(StringUtils.isBlank(investorReq.getCustomer())){
			logger.error("邀请列表导出参数错误");
		}
		String tempFileName = "investor/visitedList.xls";
		investorReq.setPaging(false);
		List<MyInvestedCustomerResp> list = customerCftRelFixService.MyInvestedCustomer(investorReq);
		 Map<String, Object> params = new HashMap<String,Object>();
		 params.put("size", Long.valueOf(list.size()));
		 params.put("list", list);
		 exportSupport.export( request,  response,  tempFileName,  params);
	}
	
	@RequestMapping(value = "investprofit")
	@RequestLogging("投资收益导出")
	public void exportInvestProfit(HttpServletRequest request, HttpServletResponse response,InvestorReq investorReq) {
		//导出默认时间段数据
		if(StringUtils.isBlank(investorReq.getRegTimeStart())|| StringUtils.isBlank(investorReq.getRegTimeEnd())){
			investorReq.setRegTimeStart(DateUtils.format(DateUtils.subDay(new Date(), 30),"yyyy-MM-dd"));
			investorReq.setRegTimeEnd(DateUtils.getNow());
		}
		String tempFileName = "investor/investProfitList.xls";
		investorReq.setPaging(false);
		PaginatorResponse<InvestorProfitResp> resp = customerCftRelFixService.queryInvestProfitList(investorReq);
		 Map<String, Object> params = new HashMap<String,Object>();
		 params.put("size", Long.valueOf(resp.getTotalCount()));
		 params.put("list", resp.getDatas());
		 exportSupport.export( request,  response,  tempFileName,  params);
	}
	/**
	 * 产品销售导出
	 * @param request
	 * @param response
	 * @param investorReq
	 */
	@RequestMapping(value = "prosale")
	@RequestLogging("产品销售导出")
	public void exportProSale(HttpServletRequest request, HttpServletResponse response,ProductManageRep productManageRep) {
		String tempFileName = "investor/proSaleList.xls";
		productManageRep.setPaging(false);
		PaginatorSevResp<ProductSaleManageResp> resp = productManageService.queryProductSalePage(productManageRep);
		 Map<String, Object> params = new HashMap<String,Object>();
		 params.put("size", Long.valueOf(resp.getTotalCount()));
		 params.put("list", resp.getDatas());
		 exportSupport.export( request,  response,  tempFileName,  params);
	}
	
	/**
	 * 产品销售明细
	 * @param request
	 * @param response
	 * @param investorReq
	 */
	@RequestMapping(value = "prosaledtl")
	@RequestLogging("产品销售明细导出")
	public void exportProSaleDtl(HttpServletRequest request, HttpServletResponse response,ProductManageRep productManageRep) {
		
		String tempFileName = "investor/proSaleDtlList.xls";
		productManageRep.setPaging(false);
		productManageRep.setCollectBeginTimeStart(DateUtils.format(DateUtils.subDay(new Date(), 30),"yyyy-MM-dd"));
		productManageRep.setCollectBeginTimeEnd(DateUtils.getNow());

		PaginatorSevResp<ProductSaleDtlResp> resp = productManageService.queryProductSaleDtlPage(productManageRep);
		List<ProductSaleDtlResp> listProSaleDtl = resp.getDatas();
		List<String> userIds = new ArrayList<String>();
		for(ProductSaleDtlResp item:listProSaleDtl){
			userIds.add(item.getBuyUserId());
		}
		Map<String,InvestorUserInfo> userInfoMap = investorUserInfoService.getInvestUserInfoMap(userIds);
		for(ProductSaleDtlResp item:listProSaleDtl){
			InvestorUserInfo userInfo = userInfoMap.get(item.getBuyUserId());
			if(userInfo!=null){
				item.setBuyUserName(userInfo.getUserName());
				item.setBuyUserMobile(userInfo.getMobile());
				}
		}
		 Map<String, Object> params = new HashMap<String,Object>();
		 params.put("size", Long.valueOf(resp.getTotalCount()));
		 params.put("list", listProSaleDtl);
		 exportSupport.export( request,  response,  tempFileName,  params);
	}
	
	

}