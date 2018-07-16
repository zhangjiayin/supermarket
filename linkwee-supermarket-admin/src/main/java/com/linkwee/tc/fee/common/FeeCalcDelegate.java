package com.linkwee.tc.fee.common;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.linkwee.tc.fee.common.profitCalculation.ProfitCalculation;
import com.linkwee.tc.fee.model.vo.FeedetailWrapper;
import com.linkwee.tc.fee.service.TCFeeDetailService;
import com.linkwee.web.model.CimFeedetail;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.service.CimFeedetailService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.xoss.util.RejectedExecuteRetry;
/**
 * 佣金计算委托
 * @author ch
 *
 */
@Component
public class FeeCalcDelegate {
	
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(FeeCalcDelegate.class);
	
	@Autowired
	protected TCFeeDetailService feeDetailService;
	
	@Autowired
	protected CrmCfplannerService cfplannerService;
	
	@Autowired
	protected CrmInvestorService investorService;
	
	@Autowired
	private CimFeedetailService cimFeedetailService;
	
	@Autowired
	private List<ProfitCalculation> profitCalculations;
	
	@RejectedExecuteRetry
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void everyDayFeeCalc(InvestRecordWrapper investRecord) throws Exception{
		feeCalcEveryDay(investRecord);
	}
	
	public void feeCalc(InvestRecordWrapper investRecord) throws Exception{
		
		//查询用户
		CrmInvestor investor = investorService.queryInvestorByUserId(investRecord.getUserId());
		
		if(investor==null){
			LOGGER.warn("calculate fee investor do not exist investor id ={}", investRecord.getUserId());
			return;
		}
		//查询理财师
		CrmCfplanner cfplanner = getCfplanner(investor.getCfplanner());
		
		if(cfplanner==null){
			LOGGER.warn("calculate fee cfplanner do not exist cfplanner id ={}", investor.getCfplanner());
			return;
		}
		
		investRecord.setYearPurAmount(CalculateTools.yearpurAmountCompute(investRecord.getInvestAmt(), investRecord.getProductDays()));
		List<FeedetailWrapper> feedetailWrappers = Lists.newLinkedList();
		for (ProfitCalculation profitCalculation : profitCalculations) {
			List<FeedetailWrapper> results = profitCalculation.calculate(investor, cfplanner, investRecord);
			if(CollectionUtils.isEmpty(results)) continue;
			feedetailWrappers.addAll(results);
		}
		savaFeedetails(feedetailWrappers.toArray(new FeedetailWrapper[0]));
	}
	
	
	public void feeCalcEveryDay(InvestRecordWrapper investRecord) throws Exception{
		
		try {
			//查询用户
			CrmInvestor investor = investorService.queryInvestorByUserId(investRecord.getUserId());
			
			if(investor==null){
				LOGGER.warn("calculate fee everyDay investor do not exist investor id ={}", investRecord.getUserId());
				return;
			}
			//查询理财师
			CrmCfplanner cfplanner = getCfplanner(investor.getCfplanner());
			
			if(cfplanner==null){
				LOGGER.warn("calculate fee everyDay cfplanner do not exist cfplanner id ={}", investor.getCfplanner());
				return;
			}
			
			/**
			 * 根据投资记录编号查询最初佣金,推荐津贴,直接管理津贴,团队管理津贴,计算明细 用于浮动期产品过了最小日期 每天津贴计算
			 */
			List<FeedetailWrapper> feedetailWrappers = Lists.newLinkedList();
			List<CimFeedetail>  cimFeedetailList= cimFeedetailService.queryEveryDayCalcFeeDetailMapByBillId(investRecord.getBizId());
			
			if(CollectionUtils.isNotEmpty(cimFeedetailList)){
				for (CimFeedetail cimFeedetail : cimFeedetailList) {
					FeedetailWrapper feedetailWrapper = creatFeedetailWrapperForFeeCalcEveryDay(cimFeedetail,investRecord);
					if(feedetailWrapper != null){
						feedetailWrappers.add(feedetailWrapper);
					}
				}
			}
			
			//保存到佣金明细表和跟新佣金表
			if(CollectionUtils.isNotEmpty(feedetailWrappers)){		
				savaFeedetails(feedetailWrappers.toArray(new FeedetailWrapper[0]));
			}		
		} catch (Exception e) {
			LOGGER.error("calculate fee everyDay cfplanner Exception", e);
			throw e;
		}
	}
	
	/**
	 * 封装佣金明细包装类
	 * @param cimFeedetail
	 * @param investRecord
	 * @return
	 */
	private FeedetailWrapper creatFeedetailWrapperForFeeCalcEveryDay(CimFeedetail cimFeedetail, InvestRecordWrapper investRecord) {
		
		FeedetailWrapper feedetailWrapper = null;
		
		String remark = investRecord.getRemark();
		if(cimFeedetail.getFeeType().equals("1001")){
			remark += "佣金";
		} else if(cimFeedetail.getFeeType().equals("1002")){
			remark += "推荐奖励";
		} else if(cimFeedetail.getFeeType().equals("1005")){
			remark += "直接管理津贴";
		} else if(cimFeedetail.getFeeType().equals("1006")){
			remark += "团队管理津贴";
		} else {
			return feedetailWrapper;
		}
		
		//拼装佣金封装类
		if(investRecord.getProductDays() > 0 && investRecord.getInvestAmt().compareTo(new BigDecimal(0)) > 0){//避免某些第三方数据错误
			
			feedetailWrapper = new FeedetailWrapper();

			//新旧佣金率换算比率
			BigDecimal rate = new BigDecimal("0.00");
			if(investRecord.getFeeRatio().compareTo(BigDecimal.ZERO) != 0){
				rate = investRecord.getNewFeeRatio().divide(investRecord.getFeeRatio(), 4, BigDecimal.ROUND_DOWN);
			}		
			//提前赎回部分本金    最终 年化=未赎回金额/总金额  * 最初年化/产品最小期限 
			feedetailWrapper.setYearPurAmount(investRecord.getStockInvestAmt().divide(investRecord.getInvestAmt(), 4, BigDecimal.ROUND_DOWN).multiply(cimFeedetail.getYearpurAmount()).divide(new BigDecimal(investRecord.getProductDays()),4,BigDecimal.ROUND_DOWN));
			feedetailWrapper.setRemark(remark);
			
			feedetailWrapper.setBillId(investRecord.getBizId());
			feedetailWrapper.setInvestorId(investRecord.getUserId());
			feedetailWrapper.setProductOrgId(investRecord.getProductOrgId());
			feedetailWrapper.setProductId(investRecord.getProductId());
			feedetailWrapper.setProductName(investRecord.getProductName());
			feedetailWrapper.setInvestmentAmount(investRecord.getInvestAmt());
			feedetailWrapper.setInvestDate(investRecord.getInvestTime());
			
			feedetailWrapper.setProfitCfplannerId(cimFeedetail.getProfitCfplannerId());
			feedetailWrapper.setOriginCfplannerId(cimFeedetail.getOriginCfplannerId());
			if(cimFeedetail.getFeeType().equals("1001")){
				feedetailWrapper.setRatio(investRecord.getNewFeeRatio().doubleValue());
			}else {
				feedetailWrapper.setRatio(cimFeedetail.getFeeRate().doubleValue());
			}
	
			feedetailWrapper.setFeetype(cimFeedetail.getFeeType());
			
			////提前赎回部分本金    最终收益=未赎回金额/总金额  * 最初收益/产品最小期限  * 新旧佣金率换算比率
			feedetailWrapper.setFeeamount(investRecord.getStockInvestAmt().divide(investRecord.getInvestAmt(), 4, BigDecimal.ROUND_DOWN).multiply(cimFeedetail.getFeeAmount()).divide(new BigDecimal(investRecord.getProductDays()),4,BigDecimal.ROUND_DOWN).multiply(rate));
		}
		
		return feedetailWrapper;
	}



	/**
	 * 设置祖先路径
	 * @param feedetailWrapper 明细包装类
	 * @param cfplannerId 理财师编号
	 */
	private CrmCfplanner getCfplanner(String cfplannerId){
		if(StringUtils.isBlank(cfplannerId))return null;
		return cfplannerService.queryCfplannerByUserId(cfplannerId);
	}
	
	/***
	 * 保存佣金明细
	 * @param feedetailWrappers
	 */
	private void savaFeedetails(FeedetailWrapper... feedetailWrappers){
		
		try {
			feeDetailService.insertFeedetail(feedetailWrappers);
		} catch (Exception e) {
			LOGGER.warn("SavaFeedetail Exception feedetailWrapper = {}", feedetailWrappers);
		} 
	}
}
