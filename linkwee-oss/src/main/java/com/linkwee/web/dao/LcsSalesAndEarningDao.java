package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import com.linkwee.web.request.CfpCommonRequest;
import com.linkwee.web.response.*;
import org.apache.ibatis.annotations.Param;

import com.xiaoniu.mybatis.paginator.domain.PageList;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;
import org.apache.ibatis.session.RowBounds;


public interface LcsSalesAndEarningDao {
	
	/**
	 * 查询理财师收益列表数量
	 * @param params
	 * @return
	 */
	public int queryLcsSalesAndEarningDetailListCount(Map<String, Object> params);
	
	/**
	 * 查询理财师收益列表
	 * @param pageRequest
	 * @return
	 */
	public PageList<LcsSalesAndEarningDetailResp> queryLcsSalesAndEarningDetailList(PageRequest pageRequest);
	
	/**
	 * 查询理财师活动奖励列表数量
	 * @param params
	 * @return
	 */
	public int queryLcsActivityDetailCount(Map<String, Object> params);
	
	/**
	 * 查询理财师活动奖励列表
	 * @param pageRequest
	 * @return
	 */
	public List<LcsActivityProfitDetailResp> queryLcsActivityDetail(PageRequest pageRequest,RowBounds rowBounds);

	public List<LcsActivityProfitDetailResp> queryLcsActivityDetail(PageRequest pageRequest);

	/**
	 * 查询活动奖励总计
	 * @param pageRequest
	 * @return
	 */
	public double queryLcsActivityDetailTotalAmount(PageRequest pageRequest);
	
	
	
	/**
	 * 根据理财师手机号码查询理财师客户在投总额
	 * @param mobile
	 * @return
	 */
	public double querylcsCustomerInvestmentListTotalAmount(@Param("mobile") String mobile);
	
	/**
	 * 根据理财师手机号码查询理财师客户在投明细总数
	 * @param mobile
	 * @return
	 */
	public int querylcsCustomerInvestmentListCount(Map<String, Object> params);
	
	/**
	 * 根据理财师手机号码查询理财师客户再投明细列表
	 * @param mobile
	 * @return
	 */
	public PageList<LcsCustomerInvestmentDetailResp> querylcsCustomerInvestmentList(PageRequest pageRequest);
	
	/**
	 * 理财师佣金收益总金额
	 * @param params
	 * @return
	 */
	public double queryLcsCommissionTotalAmount(Map<String, Object> params);
	
	/**
	 * 理财师佣金收益总数
	 * @param params
	 * @return
	 */
	public int queryLcsCommissionListCount(Map<String, Object> params);
	
	/**
	 * 理财师佣金收益列表
	 * @param pageRequest
	 * @return
	 */
	public PageList<LcsCommissionDetailResp> queryLcsCommissionList(PageRequest pageRequest);
	
	
	/**
	 * 查询理财师的推荐收益总额
	 * @Author libin
	 * @return
	 */
	public double queryRecommendedIncomeDetailTotal(PageRequest pageRequest);
	
	/**
	 * 查询理财师的推荐收益总数
	 *
	 * @param params
	 * @return
	 */
	public int queryRecommendedIncomeDetailCount(Map<String, Object> params);
	
	/**
	 * 修改
	 * 查询理财师的推荐收益列表
	 * @param pageRequest
	 *@Author libin
	 * @return
	 */
	 public List<LcsRecommendedIncomeDetailResp> queryRecommendedIncomeDetail(PageRequest pageRequest, RowBounds rowBounds);
	
	/**
	 * 根据手机号码查询理财师编号
	 * @param mobile
	 * @return
	 */
	public String getNumberByMobile(@Param("mobile")String mobile);
	
	
	/**
	 * 导出理财师数据
	 * @param map
	 * @return
	 */
	public List<LcsSalesAndEarningDetailResp> exportLcsSalesAndEarningDetail(Map<String, Object> map);
	
	/**
	 * 导出理财师佣金数据
	 * @param map
	 * @return
	 */
	public List<LcsCommissionDetailResp> exportLcsCommissionDetail(Map<String, Object> map);
	
	/**
	 * 理财师推荐收益
	 * @param map
	 * @return
	 */
	public List<LcsRecommendedIncomeDetailResp> exportLcsRecommendedIncomeDetail(Map<String, Object> map);
	
	/**
	 * 活动奖励
	 * @param map
	 * @return
	 */
	public List<LcsActivityProfitDetailResp> exportLcsActivityProfitDetail(Map<String, Object> map);
	
	/**
	 * 理财师客户当前在投数据
	 * @param map
	 * @return
	 */
	public List<LcsCustomerInvestmentDetailResp> exportLcsCustomerInvestmentDetail(Map<String, Object> map);


	public List<CfpCommissionListResp> queryCfpCommissionList(CfpCommonRequest cfpCommonRequest,RowBounds rowBounds);

	/**
	 * 理财师佣金收益合计
	 * @param cfpCommonRequest
	 * @return
     */
	public Double queryCfpCommissionTotalAmount(CfpCommonRequest cfpCommonRequest);
	
	
	
	
	
	
}
