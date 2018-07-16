package com.linkwee.web.service.impl;

import java.util.*;

import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.request.CfpCommonRequest;
import com.linkwee.web.response.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.core.base.PaginatorSevReq;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.web.dao.LcsSalesAndEarningDao;
import com.linkwee.web.model.SaleUserInfo;
import com.linkwee.web.service.LcsSalesAndEarningService;
import com.linkwee.web.service.SaleUserInfoService;
import com.linkwee.web.util.PaginatorUtil;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;


@Service("lcsSalesAndEarningService")
public class LcsSalesAndEarningServiceImpl implements LcsSalesAndEarningService{

	@Autowired
	private SaleUserInfoService saleUserInfoService;
	
	@Autowired
	private LcsSalesAndEarningDao lcsSalesAndEarningDao;
	
	
	@Override
	public PaginatorSevResp<LcsSalesAndEarningDetailResp> queryLcsSalesAndEarningDetailList(PaginatorSevReq request) {
		PageRequest req = PaginatorUtil.toPageRequest(request);
		int totalCount = lcsSalesAndEarningDao.queryLcsSalesAndEarningDetailListCount(req.getQuery());
		if(totalCount==0){
			return PaginatorUtil.getEmptyResp(req);
		}
		req.setContainsTotalCount(false);
		return PaginatorUtil.getPaginatorSevResp(req,lcsSalesAndEarningDao.queryLcsSalesAndEarningDetailList(req),totalCount);
	}

	
	
	@Override
	public PaginatorSevResp<LcsCommissionDetailResp> queryLcsCommissionList(PaginatorSevReq request) {
		PageRequest req = PaginatorUtil.toPageRequest(request);
		int totalCount = lcsSalesAndEarningDao.queryLcsCommissionListCount(req.getQuery());
		if(totalCount==0){
			return PaginatorUtil.getEmptyResp(req);
		}
		req.setContainsTotalCount(false);
		return PaginatorUtil.getPaginatorSevResp(req,lcsSalesAndEarningDao.queryLcsCommissionList(req),totalCount);
		
	}

	@Override
	public PaginatorSevResp<LcsCustomerInvestmentDetailResp> querylcsCustomerInvestmentList(PaginatorSevReq request) {
		PageRequest req = PaginatorUtil.toPageRequest(request);
		int totalCount = lcsSalesAndEarningDao.querylcsCustomerInvestmentListCount(req.getQuery());
		if(totalCount==0){
			return PaginatorUtil.getEmptyResp(req);
		}
		req.setContainsTotalCount(false);
		return PaginatorUtil.getPaginatorSevResp(req,lcsSalesAndEarningDao.querylcsCustomerInvestmentList(req),totalCount);
	}

	@Override
	public DataTableReturn queryLcsActivityDetail(PaginatorSevReq request) {
		PageRequest req = PaginatorUtil.toPageRequest(request);
		Page<LcsActivityProfitDetailResp> page = new Page<LcsActivityProfitDetailResp>(request.getPageIndex(),request.getPageSize());
		List<LcsActivityProfitDetailResp> lcsActivityProfitDetailRespList = lcsSalesAndEarningDao.queryLcsActivityDetail(req,page);
		DataTableReturn dataTableReturn = new DataTableReturn();
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		dataTableReturn.setData(lcsActivityProfitDetailRespList);
		dataTableReturn.setDraw(0);
		return  dataTableReturn;
	}



	@Override
	public double queryLcsCommissionTotalAmount(Map<String, Object> params) {
		return lcsSalesAndEarningDao.queryLcsCommissionTotalAmount(params);
	}



	@Override
	public double querylcsCustomerInvestmentListTotalAmount(String mobile) {
		return lcsSalesAndEarningDao.querylcsCustomerInvestmentListTotalAmount(mobile);
	}



	@Override
	public double queryLcsActivityDetailTotalAmount(PaginatorSevReq request) {
		PageRequest req = PaginatorUtil.toPageRequest(request);
		return lcsSalesAndEarningDao.queryLcsActivityDetailTotalAmount(req);
	}



	@Override
	public double queryRecommendedIncomeDetailTotal(Map<String, Object> params) {
		
		SaleUserInfo saleUserInfo = saleUserInfoService.getSaleUserInfoByMobile(params.get("mobile").toString());
		if(saleUserInfo==null)return 0d;
		params.put("number", saleUserInfo.getNumber());
		return lcsSalesAndEarningDao.queryRecommendedIncomeDetailCount(params);
	}


	@Override
	public double queryRecommendedIncomeDetailTotal(PaginatorSevReq request) {
		PageRequest req = PaginatorUtil.toPageRequest(request);
		return lcsSalesAndEarningDao.queryRecommendedIncomeDetailTotal(req);
	}

	/**
	 * @Author libin
	 * @param request
	 * @return
     */
	@Override
	public DataTableReturn queryRecommendedIncomeDetail(PaginatorSevReq request) {
		PageRequest req = PaginatorUtil.toPageRequest(request);
		Page<LcsRecommendedIncomeDetailResp> lcsRecommendedIncomeDetailRespPage = new Page<LcsRecommendedIncomeDetailResp>(request.getPageIndex(),request.getPageSize());
		List<LcsRecommendedIncomeDetailResp> respList = lcsSalesAndEarningDao.queryRecommendedIncomeDetail(req,lcsRecommendedIncomeDetailRespPage);
//		double total= lcsSalesAndEarningDao.queryRecommendedIncomeDetailTotal(req);
		DataTableReturn dataTableReturn = new DataTableReturn();
		dataTableReturn.setDraw(0);
		dataTableReturn.setData(respList);
		dataTableReturn.setRecordsFiltered(lcsRecommendedIncomeDetailRespPage.getTotalCount());
		dataTableReturn.setRecordsTotal(lcsRecommendedIncomeDetailRespPage.getTotalCount());
		return  dataTableReturn;
	}



	@Override
	public Map<String, Object> exportLcsSalesAndEarningDetail(Map<String, Object> map) {
		Map<String, Object> datas = new LinkedHashMap<String, Object>();
		List<LcsSalesAndEarningDetailResp> list = lcsSalesAndEarningDao.exportLcsSalesAndEarningDetail(map);
		datas.put("list", list==null?Collections.emptyList():list);
		datas.put("size", list==null?0l:Long.valueOf(list.size()));
		return datas;
	}



	@Override
	public Map<String, Object> exportLcsCommissionDetail(Map<String, Object> map) {
		Map<String, Object> datas = new LinkedHashMap<String, Object>();
		List<LcsCommissionDetailResp> list = lcsSalesAndEarningDao.exportLcsCommissionDetail(map);
		datas.put("list", list==null?Collections.emptyList():list);
		datas.put("size", list==null?0l:Long.valueOf(list.size()));
		return datas;
	}



	@Override
	public Map<String, Object> exportLcsRecommendedIncomeDetail(Map<String, Object> map) {
		Map<String, Object> datas = new LinkedHashMap<String, Object>();
		List<LcsRecommendedIncomeDetailResp> list = null;
		String number = lcsSalesAndEarningDao.getNumberByMobile(map.get("mobile").toString());
		if(!StringUtils.isBlank(number)){
			map.put("number", number);
			list = lcsSalesAndEarningDao.exportLcsRecommendedIncomeDetail(map);
		}
		datas.put("list", list==null?Collections.emptyList():list);
		datas.put("size", list==null?0l:Long.valueOf(list.size()));
		return datas;
	}



	@Override
	public Map<String, Object> exportLcsActivityProfitDetail(Map<String, Object> map) {
		Map<String, Object> datas = new LinkedHashMap<String, Object>();
		List<LcsActivityProfitDetailResp> list = lcsSalesAndEarningDao.exportLcsActivityProfitDetail(map);
		datas.put("list", list==null?Collections.emptyList():list);
		datas.put("size", list==null?0l:Long.valueOf(list.size()));
		return datas;
	}



	@Override
	public Map<String, Object> exportLcsCustomerInvestmentDetail(Map<String, Object> map) {
		Map<String, Object> datas = new LinkedHashMap<String, Object>();
		List<LcsCustomerInvestmentDetailResp> list = lcsSalesAndEarningDao.exportLcsCustomerInvestmentDetail(map);
		datas.put("list", list==null?Collections.emptyList():list);
		datas.put("size", list==null?0l:Long.valueOf(list.size()));
		return datas;
	}

	@Override
	public DataTableReturn findCfpCommissionDetailList(CfpCommonRequest cfpCommonRequest) throws Exception {
		if(cfpCommonRequest == null || StringUtils.isBlank(cfpCommonRequest.getSearchValue())){
			DataTableReturn dataTableReturn = new DataTableReturn();
			dataTableReturn.setData(new ArrayList<Object>());
			dataTableReturn.setDraw(0);
			dataTableReturn.setRecordsFiltered(0);
			dataTableReturn.setRecordsFiltered(0);
			return  dataTableReturn;
		}
		Page<CfpCommissionListResp> page = new Page<CfpCommissionListResp>(cfpCommonRequest.getPageIndex(),cfpCommonRequest.getLength());
		List<CfpCommissionListResp> listResps = lcsSalesAndEarningDao.queryCfpCommissionList(cfpCommonRequest,page);
		DataTableReturn dataTableReturn = new DataTableReturn();
		dataTableReturn.setDraw(0);
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		dataTableReturn.setData(listResps);
		return dataTableReturn;
	}

	/**
	 * 理财师佣金收益合计
	 *
	 * @param cfpCommonRequest
	 * @return
	 * @throws Exception
	 */
	@Override
	public Double findCfpCommissionDetailTotalAmount(CfpCommonRequest cfpCommonRequest) throws Exception {
		if(cfpCommonRequest == null || StringUtils.isBlank(cfpCommonRequest.getSearchValue())){
			return  0.00;
		}
		return lcsSalesAndEarningDao.queryCfpCommissionTotalAmount(cfpCommonRequest);
	}
}
