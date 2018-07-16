package com.linkwee.web.service;

import java.util.Map;

import com.linkwee.core.base.PaginatorSevReq;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.request.CfpCommonRequest;
import com.linkwee.web.response.*;


/**
 * 
 * @描述：理财师销售于收益
 *
 * @author ch
 * @时间  2016年4月8日下午5:43:30
 *
 */
public interface LcsSalesAndEarningService {

	
	/**
	 * 查询理财师销售与收益列表
	 * @param request
	 * @return
	 */
	public PaginatorSevResp<LcsSalesAndEarningDetailResp> queryLcsSalesAndEarningDetailList(PaginatorSevReq request);
	
	/**
	 * 理财师佣金收益总金额
	 * @param params
	 * @return
	 */
	public double queryLcsCommissionTotalAmount(Map<String, Object> params);
	
	/**
	 * 查询理财师佣金收益明细列表
	 * @param request
	 * @return
	 */
	public PaginatorSevResp<LcsCommissionDetailResp> queryLcsCommissionList(PaginatorSevReq request);
	
	
	
	/**
	 * 根据理财师手机号码查询理财师客户在投总额
	 * @param mobile
	 * @return
	 */
	public double querylcsCustomerInvestmentListTotalAmount(String mobile);
	
	/**
	 * 查询理财师客户在投记录列表
	 * @param request
	 * @return
	 */
	public PaginatorSevResp<LcsCustomerInvestmentDetailResp> querylcsCustomerInvestmentList(PaginatorSevReq request);
	
	
	/**
	 * 查询活动奖励总计
	 * @param request
	 * @return
	 */
	public double queryLcsActivityDetailTotalAmount(PaginatorSevReq request);
	
	/**
	 * 查询理财师活动记录奖励列表
	 * @param request
	 * @return
	 */
	public DataTableReturn queryLcsActivityDetail(PaginatorSevReq request);
	
	
	
	/**
	 * 查询理财师的推荐收益总数
	 * @param params
	 * @return
	 */
	public double queryRecommendedIncomeDetailTotal(Map<String, Object> params);

	public double queryRecommendedIncomeDetailTotal(PaginatorSevReq req);

	/**
	 * 查询理财师的推荐收益列表
	 * @param request
	 * @return
	 */
	public DataTableReturn queryRecommendedIncomeDetail(PaginatorSevReq request);
	
	
	
	/**
	 * 导出理财师数据
	 * @param map
	 * @return
	 */
	public Map<String, Object> exportLcsSalesAndEarningDetail(Map<String, Object> map);
	
	/**
	 * 导出理财师佣金数据
	 * @param map
	 * @return
	 */
	public Map<String, Object> exportLcsCommissionDetail(Map<String, Object> map);
	
	/**
	 * 理财师推荐收益
	 * @param map
	 * @return
	 */
	public Map<String, Object> exportLcsRecommendedIncomeDetail(Map<String, Object> map);
	
	/**
	 * 活动奖励
	 * @param map
	 * @return
	 */
	public Map<String, Object> exportLcsActivityProfitDetail(Map<String, Object> map);
	
	/**
	 * 理财师客户当前在投数据
	 * @param map
	 * @return
	 */
	public Map<String, Object> exportLcsCustomerInvestmentDetail(Map<String, Object> map);

	public DataTableReturn findCfpCommissionDetailList(CfpCommonRequest cfpCommonRequest) throws Exception;

	/**
	 * 理财师佣金收益合计
	 * @param cfpCommonRequest
	 * @return
	 * @throws Exception
     */
	public Double findCfpCommissionDetailTotalAmount(CfpCommonRequest cfpCommonRequest) throws Exception;
	
}
