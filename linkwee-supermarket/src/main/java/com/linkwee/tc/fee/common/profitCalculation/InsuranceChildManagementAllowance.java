package com.linkwee.tc.fee.common.profitCalculation;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linkwee.core.util.NumberUtils;
import com.linkwee.tc.fee.common.CalculateTools;
import com.linkwee.tc.fee.model.vo.InsuranceFeedetailWrapper;
import com.linkwee.web.model.CrmCfpLevelRewardRate;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.vo.InsuranceInvestRecordWrapper;
import com.linkwee.web.service.CimOrginfoService;

/**
 * 直接管理津贴计算-保险
 * @author liqimoon
 *
 */
@Component
public class InsuranceChildManagementAllowance extends AbstractInsuranceProfitCalculation{
	
	private static final String TYPE = "1005";
	
	@Autowired
	private CimOrginfoService orginfoService;

	@Override
	protected Boolean preCalculate(CrmInvestor crmInvestor,CrmCfplanner crmCfplanner,InsuranceInvestRecordWrapper insuranceInvestRecordWrapper,InsuranceFeedetailWrapper insuranceFeedetailWrapper) {
		// TODO Auto-generated method stub
		//获取推荐理财师
		CrmCfplanner pCfplanner = getCfplanner(crmCfplanner.getParentId());
		if(pCfplanner == null) return Boolean.FALSE;
		//职级要大于经理，即权重大于30才发放
		if( pCfplanner.getJobGradeWeight()<30) return Boolean.FALSE;
		//职级要大于级理财师
		if( pCfplanner.getJobGradeWeight() <= crmCfplanner.getJobGradeWeight()) return Boolean.FALSE;
		
		CrmCfpLevelRewardRate pCfplannerLevelRewardRate = getLevelRewardRate(pCfplanner.getJobGrade());
		
		insuranceFeedetailWrapper.setProfitCfplannerId(pCfplanner.getUserId());
		insuranceFeedetailWrapper.setProfitCfplannerIdLowType("1");//0=默认|1=上1级|2=上2级|上3级
		insuranceFeedetailWrapper.setOriginCfplannerParent1Id(pCfplanner.getUserId());
		insuranceFeedetailWrapper.setOriginCfplannerId(crmCfplanner.getUserId());
		insuranceFeedetailWrapper.setFeeRate(new BigDecimal(pCfplannerLevelRewardRate.getChildAllowanceRate()));
		insuranceFeedetailWrapper.setFeeType(TYPE);
		
		//备注信息
		String productName= insuranceInvestRecordWrapper.getProductName();
		BigDecimal amt = insuranceInvestRecordWrapper.getProductAmount();
		String mobile=null,name=null;
		//理财师描述
		mobile = crmCfplanner.getMobile();
		mobile = StringUtils.join(new Object[]{mobile.substring(0,3),mobile.substring(mobile.length()-4)},"***");	
		name =  StringUtils.join(new Object[]{crmCfplanner.getUserName(),mobile},' ');
		String remark =  String.format("团队成员%s销售 %s，金额%s元", name,productName,NumberUtils.getFormat(amt, "0.00"));
		insuranceFeedetailWrapper.setSucceedRemark(remark);
		if(StringUtils.isBlank(insuranceInvestRecordWrapper.getRemark())){
			insuranceFeedetailWrapper.setRemark(remark);
		}
		
		return Boolean.TRUE;
	}

	@Override
	protected void internalCalculate(CrmInvestor crmInvestor,CrmCfplanner crmCfplanner,InsuranceInvestRecordWrapper insuranceInvestRecordWrapper,InsuranceFeedetailWrapper insuranceFeedetailWrapper) {
		// TODO Auto-generated method stub
		
		/**
		 * 基础佣金 = 平台基础佣金率 * 年化业绩
		 */
		BigDecimal baseFeeAmount = null;
		if(insuranceInvestRecordWrapper.getFeeRate().compareTo(BigDecimal.ZERO) > 0 && insuranceFeedetailWrapper.getYearpurAmount().compareTo(BigDecimal.ZERO) > 0){
			baseFeeAmount = CalculateTools.feeAmountCompute(insuranceFeedetailWrapper.getYearpurAmount(),insuranceFeedetailWrapper.getProductFeeRate().doubleValue());
		} else {
			baseFeeAmount = BigDecimal.ZERO;
		}
		
		/**
		 * 理财师佣金 = 基础佣金  * 职级基础佣金百分比
		 */
		BigDecimal feeAmount = CalculateTools.feeAmountCompute(baseFeeAmount, insuranceFeedetailWrapper.getFeeRate().doubleValue());
		insuranceFeedetailWrapper.setFeeAmount(feeAmount);
	}
}
