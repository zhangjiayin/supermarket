package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.InvestRecord;
import com.linkwee.web.model.InvestRecordReq;
import com.linkwee.web.model.InvestorProfitResp;
import com.linkwee.web.model.InvestorReq;
import com.linkwee.web.model.InvestorResp;
import com.linkwee.web.model.MyInvestedCustomerResp;
import com.linkwee.web.model.Usercustomerrel;


 /**
 * 
 * @描述：客户 Dao接口
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年08月12日 09:37:08
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface InvestorProfitStatisticDao extends BasePageDao<Usercustomerrel>{
	/**
	 * 　投资记录-分页
	 */
	public Integer investRecordCount(InvestRecordReq investRecordReq);
	public List<InvestRecord> investRecordPageList(InvestRecordReq investRecordReq);
	
	/**
	 * 客户列表
	 */
	
	public Integer investorCount(InvestorReq investorReq);
	public List<InvestorResp> investorList(InvestorReq investorReq);
	/**
	 * 我邀请的客户列表
	 */
	
	public List<MyInvestedCustomerResp> MyInvestedCustomer(InvestorReq investorReq);
	/**
	 * 投资收益
	 */
	public Integer investorProfitCount(InvestorReq investorReq);
	public List<InvestorProfitResp> investorProfitList(InvestorReq investorReq);
	/**
	 * 数据概览统计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Map<String,Object>> queryInvestorNumByTime(@Param("startDate") String startDate,@Param("endDate")String endDate);
	public List<Map<String,Object>> queryInvestMoneyByTime(@Param("startDate")String startDate,@Param("endDate") String endDate);
	
	public Map<String,Object> personAmoutStat();
	public Map<String,Object> investMoneyStat();
	
	/**
	 * 总投资人数和投资额
	 * @return
	 */
	public Map<String,Object> investorTotal();
	public Map<String,Object> investMoneyTotal();
	
	
	/**
	 * 统计用户投资总额
	 * @return
	 */
	public Double investTotalSumByCustomerId(String userId);
	/**
	 * 用户使用红包总额
	 * @return
	 */
	public List<Map<String,Object>> usedTotalRedPaper();
	
}
