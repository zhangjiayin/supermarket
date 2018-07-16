package com.linkwee.tc.fee.common.profitCalculation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linkwee.core.util.NumberUtils;
import com.linkwee.tc.fee.common.CalculateTools;
import com.linkwee.tc.fee.model.vo.FeedetailWrapper;
import com.linkwee.web.model.CrmCfpLevelRewardRate;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CrmCfpLevelRewardRateService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.xoss.util.RandomTextCreator;
import com.linkwee.xoss.util.business.ProfitCalculationUtils;

/**
 * 团队管理津贴
 * @author liqimoon
 *
 */
@Component
public class LeaderProfitCalculation implements ProfitCalculation{
	
    @Resource
    private CrmCfplannerService crmCfplannerService;
    
	@Autowired
	private CrmCfpLevelRewardRateService levelRewardRateService;
	
	@Autowired
	private CimOrginfoService orginfoService;
    
	public CrmCfpLevelRewardRate getLevelRewardRate(String levelCode){
		CrmCfpLevelRewardRate levelRewardRate = new CrmCfpLevelRewardRate();
		levelRewardRate.setLevelCode(levelCode);
		return levelRewardRateService.selectOne(levelRewardRate);
	}
	
	protected FeedetailWrapper getBaseFeedetailWrapper(InvestRecordWrapper investRecord){	
		FeedetailWrapper feedetailWrapper = new FeedetailWrapper();
		feedetailWrapper.setBillId(investRecord.getBizId());
		
		feedetailWrapper.setInvestorId(investRecord.getUserId());
	
		feedetailWrapper.setProductOrgId(investRecord.getProductOrgId());
		
		feedetailWrapper.setProductId(investRecord.getProductId());
		feedetailWrapper.setProductName(investRecord.getProductName());
		
		feedetailWrapper.setInvestmentAmount(investRecord.getInvestAmt());
		feedetailWrapper.setYearPurAmount(investRecord.getYearPurAmount());
		
		feedetailWrapper.setInvestDate(investRecord.getInvestTime());
		feedetailWrapper.setEndDate(investRecord.getEndTime());
		
		return feedetailWrapper;
	}

	@Override
	public List<FeedetailWrapper> calculate(CrmInvestor investor, CrmCfplanner cfplanner,
			InvestRecordWrapper investRecord) {
		if(investor==null||cfplanner==null||investRecord==null){return null;}
		List<FeedetailWrapper> wrappers = new ArrayList<FeedetailWrapper>();
		CrmCfplanner cfp1 = null;//上一级
		CrmCfplanner cfp2 = null;//上两级
		CrmCfplanner cfp3 = null;//上三级
		if(cfplanner.getParentId()!=null){
			cfp1 = crmCfplannerService.queryCfplannerByUserId(cfplanner.getParentId());
		}
		if(cfp1!=null&&cfp1.getParentId()!=null){
			cfp2 = crmCfplannerService.queryCfplannerByUserId(cfp1.getParentId());
		}
		if(cfp2!=null&&cfp2.getParentId()!=null){
			cfp3 = crmCfplannerService.queryCfplannerByUserId(cfp2.getParentId());
		}
		
		/**
		 * 基础佣金 = 平台基础佣金率 * 年化业绩
		 */
		BigDecimal feeAmount = BigDecimal.ZERO;
		if(investRecord.getFeeRatio().compareTo(BigDecimal.ZERO) > 0 && investRecord.getYearPurAmount().compareTo(BigDecimal.ZERO) > 0){
			feeAmount = CalculateTools.feeAmountCompute(investRecord.getYearPurAmount(),investRecord.getFeeRatio().doubleValue());;
		}
		
//		BigDecimal feeAmount = CalculateTools.feeAmountCompute(investRecord.getYearPurAmount(),investRecord.getFeeRatio().doubleValue());
		//发给上三级情况
		if(ProfitCalculationUtils.paySM3(cfplanner,cfp1,cfp2,cfp3)){
			FeedetailWrapper wrapper = getBaseFeedetailWrapper(investRecord);
			wrapper.setFeeamount(CalculateTools.feeAmountCompute(feeAmount, getLevelRewardRate(cfp3.getJobGrade()).getTeamAllowanceRate().doubleValue()));
			wrapper.setProfitCfplannerId(cfp3.getUserId());
			wrapper.setOriginCfplannerId(cfplanner.getUserId());
			wrapper.setLowUserId(cfp2.getUserId());
			wrapper.setLowType("2");
			wrapper.setRemark(cfp2.getUserName()+"("+RandomTextCreator.encrypTion(cfp2.getMobile())+")的下下级理财师销售"+investRecord.getInvestAmt()+"元《"+investRecord.getOrgName()+"-"+investRecord.getProductName()+"》");
			wrapper.setFeetype("1006");
			wrapper.setRatio(getLevelRewardRate(cfp3.getJobGrade()).getTeamAllowanceRate().doubleValue());
			wrappers.add(wrapper);
		}
		//发给上两级情况
		if(ProfitCalculationUtils.paySM2(cfplanner,cfp1,cfp2,cfp3)){
			FeedetailWrapper wrapper = getBaseFeedetailWrapper(investRecord);
			wrapper.setFeeamount(CalculateTools.feeAmountCompute(feeAmount, getLevelRewardRate(cfp2.getJobGrade()).getTeamAllowanceRate().doubleValue()));
			wrapper.setProfitCfplannerId(cfp2.getUserId());
			wrapper.setOriginCfplannerId(cfplanner.getUserId());
			wrapper.setLowUserId(cfp1.getUserId());
			wrapper.setLowType("1");
			wrapper.setRemark(cfp1.getUserName()+"("+RandomTextCreator.encrypTion(cfp1.getMobile())+")的下级理财师销售"+investRecord.getInvestAmt()+"元《"+investRecord.getOrgName()+"-"+investRecord.getProductName()+"》");
			wrapper.setFeetype("1006");
			wrapper.setRatio(getLevelRewardRate(cfp2.getJobGrade()).getTeamAllowanceRate().doubleValue());
			wrappers.add(wrapper);
		}
		//发给上一级情况
		if(ProfitCalculationUtils.paySM1(cfplanner,cfp1,cfp2,cfp3)){
			FeedetailWrapper wrapper = getBaseFeedetailWrapper(investRecord);
			wrapper.setFeeamount(CalculateTools.feeAmountCompute(feeAmount, getLevelRewardRate(cfp1.getJobGrade()).getTeamAllowanceRate().doubleValue()));
			wrapper.setProfitCfplannerId(cfp1.getUserId());
			wrapper.setOriginCfplannerId(cfplanner.getUserId());
			wrapper.setLowUserId(cfplanner.getUserId());
			wrapper.setLowType("0");
			wrapper.setRemark(cfplanner.getUserName()+"("+RandomTextCreator.encrypTion(cfplanner.getMobile())+")销售"+NumberUtils.getFormat(investRecord.getInvestAmt(), "0.00")+"元《"+investRecord.getOrgName()+"-"+investRecord.getProductName()+"》");
			wrapper.setFeetype("1006");
			wrapper.setRatio(getLevelRewardRate(cfp1.getJobGrade()).getTeamAllowanceRate().doubleValue());
			wrappers.add(wrapper);
		}
		
		return wrappers;
	}
	


}
