package com.linkwee.tc.fee.common;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.linkwee.tc.fee.common.profitCalculation.InsuranceProfitCalculation;
import com.linkwee.tc.fee.model.vo.InsuranceFeedetailWrapper;
import com.linkwee.web.model.CimInsuranceFeedetailExtends;
import com.linkwee.web.model.CimInsuranceNotify;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.vo.InsuranceInvestRecordWrapper;
import com.linkwee.web.service.CimInsuranceFeedetailService;
import com.linkwee.web.service.CimInsuranceNotifyService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.xoss.util.business.ProfitCalculationUtils;

@Component
public class InsuranceFeeCalcDelegate {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(InsuranceFeeCalcDelegate.class);
	
	@Autowired
	private CrmInvestorService investorService;
	@Autowired
	private CrmCfplannerService cfplannerService;
	@Autowired
	private CimInsuranceFeedetailService cimInsuranceFeedetailService;
	@Autowired
	private CimInsuranceNotifyService cimInsuranceNotifyService;
	@Autowired
	private List<InsuranceProfitCalculation> profitInsuranceCalculationList;

	/**
	 * 获取理财师信息
	 * @param cfplannerId 理财师编号
	 */
	protected CrmCfplanner getCfplanner(String cfplannerId){
		if(StringUtils.isBlank(cfplannerId))return null;
		return cfplannerService.queryCfplannerByUserId(cfplannerId);
	}
	
	/**
	 * 计算保险佣金
	 * @param insuranceInvestRecordWrapper
	 * @throws Exception
	 */
	public void feeCalc(InsuranceInvestRecordWrapper insuranceInvestRecordWrapper) throws Exception{
		
		try {
			//查询用户
			CrmInvestor investor = investorService.queryInvestorByUserId(insuranceInvestRecordWrapper.getInvestorId());		
			if(investor == null){
				LOGGER.warn("calculate insurance fee investor do not exist investor id ={}", insuranceInvestRecordWrapper.getInvestorId());
				return;
			}
			//查询理财师
			CrmCfplanner cfplanner = getCfplanner(investor.getCfplanner());
			
			if(cfplanner==null){
				LOGGER.warn("calculate  insurance fee cfplanner do not exist cfplanner id ={}", investor.getCfplanner());
				return;
			}
			
			List<InsuranceFeedetailWrapper> insuranceFeedetailWrapperList = Lists.newLinkedList();
			for (InsuranceProfitCalculation insuranceProfitCalculation : profitInsuranceCalculationList) {
				List<InsuranceFeedetailWrapper> results = insuranceProfitCalculation.calculate(investor, cfplanner, insuranceInvestRecordWrapper);
				if(CollectionUtils.isEmpty(results)) continue;
				insuranceFeedetailWrapperList.addAll(results);
			}
			savaInsuranceFeedetails(insuranceFeedetailWrapperList.toArray(new InsuranceFeedetailWrapper[0]));
			
			LOGGER.info("保险订单佣金计算完成  InsuranceInvestRecordWrapper ={} ",JSONObject.toJSONString(insuranceInvestRecordWrapper));
		} catch (Exception e) {
			LOGGER.error("计算保险佣金异常", e);
			throw e;
		}
	}
	
	/**
	 * 计算保险佣金-已存在佣金明细
	 * @param insuranceInvestRecordWrapper
	 * @throws Exception
	 */
	public void feeCalcExist(CimInsuranceNotify  cimInsuranceNotify) throws Exception{
		try {
			/**
			 * 根据投资记录编号查询最初佣金,推荐津贴,直接管理津贴,团队管理津贴,计算明细 
			 */		
			List<InsuranceFeedetailWrapper> insuranceFeedetailWrapperList = Lists.newLinkedList();
			List<CimInsuranceFeedetailExtends>  cimInsuranceFeedetailExtendsList= cimInsuranceFeedetailService.queryInitInsuranceFeeDetailByBillId(cimInsuranceNotify.getBillId());
			if(CollectionUtils.isNotEmpty(cimInsuranceFeedetailExtendsList)){
				for (CimInsuranceFeedetailExtends cimInsuranceFeedetailExtends : cimInsuranceFeedetailExtendsList) {
					InsuranceFeedetailWrapper insuranceFeedetailWrapper = ProfitCalculationUtils.creatInsuranceFeedetailWrapperForFeeCalcExist(cimInsuranceFeedetailExtends,cimInsuranceNotify);
					if(insuranceFeedetailWrapper != null){
						insuranceFeedetailWrapperList.add(insuranceFeedetailWrapper);
					}
				}
			}
			
			//保存到佣金明细表和跟新佣金表
			if(CollectionUtils.isNotEmpty(insuranceFeedetailWrapperList)){		
				savaInsuranceFeedetails(insuranceFeedetailWrapperList.toArray(new InsuranceFeedetailWrapper[0]));
			}
		} catch (Exception e) {
			LOGGER.error("计算保险佣金【已存在佣金明细】异常", e);
			throw e;
		}
	}

	/***
	 * 保存佣金明细
	 * @param feedetailWrappers
	 */
	private void savaInsuranceFeedetails(InsuranceFeedetailWrapper... insuranceFeedetailWrappers) throws Exception{
		
		try {
			cimInsuranceFeedetailService.insertFeedetail(insuranceFeedetailWrappers);
		} catch (Exception e) {
			LOGGER.warn("SavaFeedetail Exception insuranceFeedetailWrappers = {}", insuranceFeedetailWrappers);
			throw e;
		} 
	}
}
