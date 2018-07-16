package com.linkwee.tc.fee.common.profitCalculation;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.linkwee.core.util.NumberUtils;
import com.linkwee.tc.fee.common.CalculateTools;
import com.linkwee.tc.fee.model.vo.FeedetailWrapper;
import com.linkwee.web.model.CrmCfpLevelRewardRate;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.vo.InvestRecordWrapper;
/**
 * 基础佣金计算
 * @author ch
 *
 */
@Component
public class BaseFeedProfitCalculation extends AbstractProfitCalculation {

	private static final String TYPE = "1001";
	

	@Override
	protected Boolean preCalculate(CrmInvestor investor, CrmCfplanner cfplanner,InvestRecordWrapper investRecord, FeedetailWrapper feedetailWrapper) {
		feedetailWrapper.setProfitCfplannerId(cfplanner.getUserId());
		feedetailWrapper.setOriginCfplannerId(cfplanner.getUserId());
		feedetailWrapper.setRatio(investRecord.getFeeRatio().doubleValue());
		feedetailWrapper.setFeetype(TYPE);
		String remark = investRecord.getRemark();
		if(StringUtils.isBlank(remark)){
			String productName= investRecord.getProductName();
			BigDecimal amt = investRecord.getInvestAmt();
			String mobile=null,name=null;
			//理财师描述
			 mobile = investor.getMobile();
			 mobile = StringUtils.join(new Object[]{mobile.substring(0,3),mobile.substring(mobile.length()-4)},"***");	
			 name = StringUtils.join(new Object[]{investor.getUserName(),mobile},' ');
			 remark = String.format("客户%s购买 %s，金额%s元",name,productName,NumberUtils.getFormat(amt, "0.00"));
		}
		
		feedetailWrapper.setRemark(remark);
		return Boolean.TRUE;
	}

	@Override
	protected void internalCalculate(CrmInvestor investor,CrmCfplanner cfplanner, InvestRecordWrapper investRecord,FeedetailWrapper feedetailWrapper) {
		
		CrmCfpLevelRewardRate levelRewardRate = getLevelRewardRate(cfplanner.getJobGrade());
		//基础佣金 = 平台基础佣金率 * 年化业绩
		BigDecimal baseFeeAmount = CalculateTools.feeAmountCompute(feedetailWrapper.getYearPurAmount(),feedetailWrapper.getRatio());
		//理财师佣金 = 基础佣金  * 职级基础佣金百分比
		BigDecimal feeAmount = CalculateTools.feeAmountCompute(baseFeeAmount,levelRewardRate.getBaseFeeRate().doubleValue());
		feedetailWrapper.setFeeamount(feeAmount);
	}


}
