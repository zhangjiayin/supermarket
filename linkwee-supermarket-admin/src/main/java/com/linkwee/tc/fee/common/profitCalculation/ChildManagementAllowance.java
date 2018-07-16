package com.linkwee.tc.fee.common.profitCalculation;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.linkwee.tc.fee.common.CalculateTools;
import com.linkwee.tc.fee.model.vo.FeedetailWrapper;
import com.linkwee.web.model.CrmCfpLevelRewardRate;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.service.CimOrginfoService;
/**
 * 直接管理津贴计算
 * @author ch
 *
 */
/*@Component*/
public class ChildManagementAllowance extends AbstractProfitCalculation{
	
	private static final String TYPE = "1005";
	
	@Autowired
	private CimOrginfoService orginfoService;
	
	@Override
	protected Boolean preCalculate(CrmInvestor investor, CrmCfplanner cfplanner,InvestRecordWrapper investRecord, FeedetailWrapper feedetailWrapper) {
		
		//获取推荐理财师
		CrmCfplanner pCfplanner = getCfplanner(cfplanner.getParentId());
		if(pCfplanner == null) return Boolean.FALSE;
		
		if( pCfplanner.getJobGradeWeight() <= cfplanner.getJobGradeWeight()) return Boolean.FALSE;
		
		CrmCfpLevelRewardRate pCfplannerLevelRewardRate = getLevelRewardRate(pCfplanner.getJobGrade());
		
		feedetailWrapper.setProfitCfplannerId(pCfplanner.getUserId());
		feedetailWrapper.setOriginCfplannerId(cfplanner.getUserId());
		feedetailWrapper.setRatio(pCfplannerLevelRewardRate.getChildAllowanceRate().doubleValue());
		feedetailWrapper.setFeetype(TYPE);
		String remark = investRecord.getRemark();
		
		if(StringUtils.isBlank(remark)){
			String productName= investRecord.getProductName();
			BigDecimal amt = investRecord.getInvestAmt();
			String mobile=null,name=null;
			//理财师描述
			mobile = cfplanner.getMobile();
			mobile = StringUtils.join(new Object[]{mobile.substring(0,3),mobile.substring(mobile.length()-4)},"***");	
			name =  StringUtils.join(new Object[]{cfplanner.getUserName(),mobile},' ');
			remark = String.format("团队成员%s销售 %s，金额%s元", name,productName,amt);
		} else {
			remark = StringUtils.join(new Object[]{remark,"直接管理津贴"}, "-");
		}
		feedetailWrapper.setRemark(remark);
		
		return Boolean.TRUE;
	}

	@Override
	protected void internalCalculate(CrmInvestor investor,CrmCfplanner cfplanner, InvestRecordWrapper investRecord,FeedetailWrapper feedetailWrapper) {
		/**
		 * 基础佣金 = 平台基础佣金率 * 年化业绩
		 */
		BigDecimal baseFeeAmount = BigDecimal.ZERO;
		if(investRecord.getFeeRatio().compareTo(BigDecimal.ZERO) > 0 && investRecord.getYearPurAmount().compareTo(BigDecimal.ZERO) > 0){
			baseFeeAmount = CalculateTools.feeAmountCompute(investRecord.getYearPurAmount(),investRecord.getFeeRatio().doubleValue());
		}
		
		BigDecimal feeAmount = CalculateTools.feeAmountCompute(baseFeeAmount, feedetailWrapper.getRatio());
		feedetailWrapper.setFeeamount(feeAmount);
	}
}
