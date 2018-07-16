package com.linkwee.web.controller.cfplanner;

import com.linkwee.core.base.PaginatorSevReq;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.export.ExportSupport;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.request.LcsListRequest;
import com.linkwee.web.response.LcsCommissionDetailResp;
import com.linkwee.web.response.LcsCustomerInvestmentDetailResp;
import com.linkwee.web.response.LcsSalesAndEarningDetailResp;
import com.linkwee.web.service.LcsSalesAndEarningService;
import com.linkwee.web.util.RequestLogging;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("lcsSalesAndEarningList")
@Controller
@RequestLogging("理财师销售与收益")
public class LcsSalesAndEarningContrller {
	
	@Resource
	private LcsSalesAndEarningService lcsSalesAndEarningService;
	
	@Resource
	private ExportSupport exportSupport;
	
	@RequestMapping("lcsSalesAndEarningPage")
	public String lcsSalesAndEarningPage(){
		return "cfplanner/lcsSalesAndEarningPage";
	}
	
	@RequestMapping("lcsCommissionDetailPage")
	public String lcsCommissionDetailPage(String mobile,Model m){
		m.addAttribute("mobile", mobile);
		return "cfplanner/lcsCommissionDetailPage";
	}
	
	/**
	 * 推荐收益
	 * @return
	 */
	@RequestMapping("lcsRecommendedIncomePage")
	public String lcsRecommendedIncomePage(String mobile,Model m){
		m.addAttribute("mobile", mobile);
		return "cfplanner/lcsRecommendedIncomePage";
	}
	
	/**
	 * 活动奖励
	 * @return
	 */
	@RequestMapping("LcsActivityProfitDetailPage")
	public String LcsActivityProfitDetailPage(String mobile,Model m){
		m.addAttribute("mobile", mobile);
		return "cfplanner/lcsActivityProfitDetailPage";
	}
	
	@RequestMapping("lcsCustomerInvestmentPage")
	public String lcsCustomerInvestmentPage(String mobile,Model m){
		m.addAttribute("mobile", mobile);
		return "cfplanner/lcsCustomerInvestmentPage";
	}
	

	/**
	 * 获取理财师列表
	 * @param lcsListRequest
	 * @return
	 */
	@RequestMapping("getLcsSalesAndEarningList")
	@ResponseBody
	@RequestLogging("销售与收益列表")
	public Object getLcsSalesAndEarningList(LcsListRequest lcsListRequest){
		PaginatorSevReq request = lcsListRequest.toPaginatorSevReq();
		if(StringUtils.isNotBlank(lcsListRequest.getNameOrmobile())){
			request.getQueryConditions().put("nameOrmobile", lcsListRequest.getNameOrmobile());
		}
		if(StringUtils.isNotBlank(lcsListRequest.getStartDate())){
			request.getQueryConditions().put("startDate", DateUtils.parse(lcsListRequest.getStartDate(), DateUtils.FORMAT_SHORT));
		}
		if(StringUtils.isNotBlank(lcsListRequest.getEndDate())){
			request.getQueryConditions().put("endDate",DateUtils.parse(lcsListRequest.getEndDate(), DateUtils.FORMAT_SHORT));
		}
		
		PaginatorSevResp<LcsSalesAndEarningDetailResp> datas= lcsSalesAndEarningService.queryLcsSalesAndEarningDetailList(request);
		return datas;
	}
	
	@RequestMapping("getLcsCommissionDetailList")
	@ResponseBody
	@RequestLogging("佣金明细列表")
	public Object getLcsCommissionDetailList(LcsListRequest lcsListRequest){
		PaginatorSevReq request = lcsListRequest.toPaginatorSevReq();
		if(StringUtils.isNotBlank(lcsListRequest.getMobile())){
			request.getQueryConditions().put("mobile", lcsListRequest.getMobile());
		}
		if(StringUtils.isNotBlank(lcsListRequest.getStartDate())){
			request.getQueryConditions().put("startDate", DateUtils.parse(lcsListRequest.getStartDate(), DateUtils.FORMAT_SHORT));
		}
		if(StringUtils.isNotBlank(lcsListRequest.getEndDate())){
			request.getQueryConditions().put("endDate",DateUtils.parse(lcsListRequest.getEndDate(), DateUtils.FORMAT_SHORT));
		}
		
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		List<Map<String,Object>> footer = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		PaginatorSevResp<LcsCommissionDetailResp> datas= lcsSalesAndEarningService.queryLcsCommissionList(request);
		DataTableReturn dataTableReturn = new DataTableReturn();
		dataTableReturn.setDraw(0);
		dataTableReturn.setData(datas.getDatas());
		dataTableReturn.setRecordsFiltered(datas.getTotalCount());
		dataTableReturn.setRecordsTotal(datas.getTotalCount());
		return  dataTableReturn;
	}
	
	@RequestMapping("getLcsCommissionDetailTotal")
	@ResponseBody
	public Object getLcsCommissionDetailTotal(LcsListRequest lcsListRequest){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if(StringUtils.isNotBlank(lcsListRequest.getMobile())){
			map.put("mobile", lcsListRequest.getMobile());
		}
		if(StringUtils.isNotBlank(lcsListRequest.getStartDate())){
			map.put("startDate", DateUtils.parse(lcsListRequest.getStartDate(), DateUtils.FORMAT_SHORT));
		}
		if(StringUtils.isNotBlank(lcsListRequest.getEndDate())){
			map.put("endDate",DateUtils.parse(lcsListRequest.getEndDate(), DateUtils.FORMAT_SHORT));
		}
		return lcsSalesAndEarningService.queryLcsCommissionTotalAmount(map);
	}
	
	@RequestMapping("getLcsRecommendedIncomeTotal")
	@ResponseBody
	public Object getLcsRecommendedIncomeTotal(LcsListRequest lcsListRequest){
		PaginatorSevReq request = lcsListRequest.toPaginatorSevReq();
		if(StringUtils.isNotBlank(lcsListRequest.getMobile())){
			request.getQueryConditions().put("mobile", lcsListRequest.getMobile());
		}

		if(StringUtils.isNotBlank(lcsListRequest.getNameOrmobile())){
			request.getQueryConditions().put("nameOrmobile", lcsListRequest.getNameOrmobile());
		}

		if(StringUtils.isNotBlank(lcsListRequest.getStartDate())){
			request.getQueryConditions().put("startDate", DateUtils.parse(lcsListRequest.getStartDate(), DateUtils.FORMAT_SHORT));
		}
		if(StringUtils.isNotBlank(lcsListRequest.getEndDate())){
			request.getQueryConditions().put("endDate",DateUtils.parse(lcsListRequest.getEndDate(), DateUtils.FORMAT_SHORT));
		}
		return lcsSalesAndEarningService.queryRecommendedIncomeDetailTotal(request);
	}
	
	@RequestMapping("getLcsActivityProfitDetailTotal")
	@ResponseBody
	public Object getLcsActivityProfitDetailTotal(LcsListRequest lcsListRequest){
		PaginatorSevReq request = lcsListRequest.toPaginatorSevReq();
		if(StringUtils.isNotBlank(lcsListRequest.getMobile())){
			request.getQueryConditions().put("mobile", lcsListRequest.getMobile());
		}
		if(StringUtils.isNotBlank(lcsListRequest.getStartDate())){
			request.getQueryConditions().put("startDate", DateUtils.parse(lcsListRequest.getStartDate(), DateUtils.FORMAT_SHORT));
		}
		if(StringUtils.isNotBlank(lcsListRequest.getEndDate())){
			request.getQueryConditions().put("endDate",DateUtils.parse(lcsListRequest.getEndDate(), DateUtils.FORMAT_SHORT));
		}
		return lcsSalesAndEarningService.queryLcsActivityDetailTotalAmount(request);
	}
	
	@RequestMapping("getLcsCustomerInvestmentDetailTotal")
	@ResponseBody
	public Object getLcsCustomerInvestmentDetailTotal(LcsListRequest lcsListRequest){
		if(StringUtils.isNotBlank(lcsListRequest.getMobile())){
			return lcsSalesAndEarningService.querylcsCustomerInvestmentListTotalAmount( lcsListRequest.getMobile());
		}
		return 0;
	}

	
	
	@RequestMapping("getLcsActivityProfitDetailList")
	@ResponseBody
	@RequestLogging("活动奖励列表")
	public Object getLcsActivityProfitDetailList(LcsListRequest lcsListRequest){
		PaginatorSevReq request = lcsListRequest.toPaginatorSevReq();
		if(StringUtils.isNotBlank(lcsListRequest.getMobile())){
			request.getQueryConditions().put("mobile", lcsListRequest.getMobile());
		}

		if(StringUtils.isNotBlank(lcsListRequest.getNameOrmobile())){
			request.getQueryConditions().put("nameOrmobile", lcsListRequest.getNameOrmobile());
		}

		if(StringUtils.isNotBlank(lcsListRequest.getStartDate())){
			request.getQueryConditions().put("startDate", DateUtils.parse(lcsListRequest.getStartDate(), DateUtils.FORMAT_SHORT));
		}
		if(StringUtils.isNotBlank(lcsListRequest.getEndDate())){
			request.getQueryConditions().put("endDate",DateUtils.parse(lcsListRequest.getEndDate(), DateUtils.FORMAT_SHORT));
		}
		DataTableReturn  dataTableReturn = lcsSalesAndEarningService.queryLcsActivityDetail(request);
		return  dataTableReturn;
		
	}
	
	
	@RequestMapping("getLcsCustomerInvestmentList")
	@ResponseBody
	@RequestLogging("当前客户在投列表")
	public Object getLcsCustomerInvestmentList(LcsListRequest lcsListRequest){
		PaginatorSevReq request = lcsListRequest.toPaginatorSevReq();
		if(StringUtils.isNotBlank(lcsListRequest.getMobile())){
			request.getQueryConditions().put("mobile", lcsListRequest.getMobile());
		}
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		List<Map<String,Object>> footer = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		PaginatorSevResp<LcsCustomerInvestmentDetailResp> datas= lcsSalesAndEarningService.querylcsCustomerInvestmentList(request);
		maps.put("totalCount", datas.getTotalCount());
		maps.put("datas", datas.getDatas());
		map.put("endDate","<font color='red'>合计：</font>");
		Object v = getLcsCustomerInvestmentDetailTotal(lcsListRequest);
		map.put("deadLine",v==null?0:v);
		footer.add(map);
		maps.put("footer", footer);
		return maps;
	}



	
	@RequestMapping("getLcsRecommendedIncomeList")
	@ResponseBody
	@RequestLogging("推荐收益列表")
	public Object getLcsRecommendedIncomeList(LcsListRequest lcsListRequest){
		PaginatorSevReq request = lcsListRequest.toPaginatorSevReq();
		if(StringUtils.isNotBlank(lcsListRequest.getMobile())){
			request.getQueryConditions().put("mobile", lcsListRequest.getMobile());
		}

		if(StringUtils.isNotBlank(lcsListRequest.getNameOrmobile())){
			request.getQueryConditions().put("nameOrmobile", lcsListRequest.getNameOrmobile());
		}

		if(StringUtils.isNotBlank(lcsListRequest.getStartDate())){
			request.getQueryConditions().put("startDate", DateUtils.parse(lcsListRequest.getStartDate(), DateUtils.FORMAT_SHORT));
		}
		if(StringUtils.isNotBlank(lcsListRequest.getEndDate())){
			request.getQueryConditions().put("endDate",DateUtils.parse(lcsListRequest.getEndDate(), DateUtils.FORMAT_SHORT));
		}
		DataTableReturn dataTableReturn = lcsSalesAndEarningService.queryRecommendedIncomeDetail(request);
		return  dataTableReturn;
	}
	
	
	
	@RequestMapping("exportLcsSalesAndEarningDetail")
	@RequestLogging("理财师销售与收益导出")
	public void exportLcsSalesAndEarningDetail(HttpServletRequest request, HttpServletResponse response,LcsListRequest lcsListRequest){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if(StringUtils.isNotBlank(lcsListRequest.getNameOrmobile())){
			map.put("nameOrmobile", lcsListRequest.getNameOrmobile());
		}
		if(StringUtils.isNotBlank(lcsListRequest.getStartDate())){
			map.put("startDate", DateUtils.parse(lcsListRequest.getStartDate(), DateUtils.FORMAT_SHORT));
		}
		if(StringUtils.isNotBlank(lcsListRequest.getEndDate())){
			map.put("endDate",DateUtils.parse(lcsListRequest.getEndDate(), DateUtils.FORMAT_SHORT));
		}
		exportSupport.export(request, response, "lcs/LcsSalesAndEarning/lcsSalesAndEarning.xls", lcsSalesAndEarningService.exportLcsSalesAndEarningDetail(map));
	}
	
	

	@RequestMapping("exportLcsCommissionDetail")
	@RequestLogging("佣金明细导出")
	public void exportLcsCommissionDetail(HttpServletRequest request, HttpServletResponse response,LcsListRequest lcsListRequest){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if(StringUtils.isNotBlank(lcsListRequest.getMobile())){
			map.put("mobile", lcsListRequest.getMobile());
		}
		if(StringUtils.isNotBlank(lcsListRequest.getStartDate())){
			map.put("startDate", DateUtils.parse(lcsListRequest.getStartDate(), DateUtils.FORMAT_SHORT));
		}
		if(StringUtils.isNotBlank(lcsListRequest.getEndDate())){
			map.put("endDate",DateUtils.parse(lcsListRequest.getEndDate(), DateUtils.FORMAT_SHORT));
		}
		exportSupport.export(request, response, "lcs/LcsSalesAndEarning/lcsCommissionDetail.xls", lcsSalesAndEarningService.exportLcsCommissionDetail(map));
	}
	
	@RequestMapping("exportLcsRecommendedIncomeDetail")
	@RequestLogging("推荐收益导出")
	public void exportLcsRecommendedIncomeDetail(HttpServletRequest request, HttpServletResponse response,LcsListRequest lcsListRequest){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if(StringUtils.isNotBlank(lcsListRequest.getMobile())){
			map.put("mobile", lcsListRequest.getMobile());
		}
		if(StringUtils.isNotBlank(lcsListRequest.getStartDate())){
			map.put("startDate", DateUtils.parse(lcsListRequest.getStartDate(), DateUtils.FORMAT_SHORT));
		}
		if(StringUtils.isNotBlank(lcsListRequest.getEndDate())){
			map.put("endDate",DateUtils.parse(lcsListRequest.getEndDate(), DateUtils.FORMAT_SHORT));
		}
		exportSupport.export(request, response, "lcs/LcsSalesAndEarning/lcsRecommendedIncomeDetail.xls", lcsSalesAndEarningService.exportLcsRecommendedIncomeDetail(map));
	}
	
	@RequestMapping("exportLcsActivityProfitDetail")
	@RequestLogging("活动导出")
	public void exportLcsActivityProfitDetail(HttpServletRequest request, HttpServletResponse response,LcsListRequest lcsListRequest){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if(StringUtils.isNotBlank(lcsListRequest.getMobile())){
			map.put("mobile", lcsListRequest.getMobile());
		}
		if(StringUtils.isNotBlank(lcsListRequest.getStartDate())){
			map.put("startDate", DateUtils.parse(lcsListRequest.getStartDate(), DateUtils.FORMAT_SHORT));
		}
		if(StringUtils.isNotBlank(lcsListRequest.getEndDate())){
			map.put("endDate",DateUtils.parse(lcsListRequest.getEndDate(), DateUtils.FORMAT_SHORT));
		}
		exportSupport.export(request, response, "lcs/LcsSalesAndEarning/lcsActivityProfitDetail.xls", lcsSalesAndEarningService.exportLcsActivityProfitDetail(map));
	}
	
	@RequestMapping("exportLcsCustomerInvestmentDetail")
	@RequestLogging("当前客户在投")
	public void exportLcsCustomerInvestmentDetail(HttpServletRequest request, HttpServletResponse response,LcsListRequest lcsListRequest){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if(StringUtils.isNotBlank(lcsListRequest.getMobile())){
			map.put("mobile", lcsListRequest.getMobile());
		}
		exportSupport.export(request, response, "lcs/LcsSalesAndEarning/lcsCustomerInvestmentDetail.xls", lcsSalesAndEarningService.exportLcsCustomerInvestmentDetail(map));
	}
	

}
