package com.linkwee.tc.fee.common;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.linkwee.tc.fee.model.vo.InsuranceFeedetailWrapper;
import com.linkwee.web.model.CimInsuranceFeedetailExtends;
import com.linkwee.web.model.CimInsuranceNotify;
import com.linkwee.web.model.CrmCfplanner;
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

	/**
	 * 获取理财师信息
	 * @param cfplannerId 理财师编号
	 */
	protected CrmCfplanner getCfplanner(String cfplannerId){
		if(StringUtils.isBlank(cfplannerId))return null;
		return cfplannerService.queryCfplannerByUserId(cfplannerId);
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
			List<CimInsuranceFeedetailExtends>  cimInsuranceFeedetailExtendslList= cimInsuranceFeedetailService.queryInitInsuranceFeeDetailByBillId(cimInsuranceNotify.getBillId());
			if(CollectionUtils.isNotEmpty(cimInsuranceFeedetailExtendslList)){
				for (CimInsuranceFeedetailExtends cimInsuranceFeedetailExtends : cimInsuranceFeedetailExtendslList) {
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
