package com.linkwee.tc.fee.common.profitCalculation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linkwee.core.util.NumberUtils;
import com.linkwee.tc.fee.common.CalculateTools;
import com.linkwee.tc.fee.model.vo.InsuranceFeedetailWrapper;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.vo.InsuranceInvestRecordWrapper;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CrmCfpLevelRewardRateService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.xoss.util.RandomTextCreator;
import com.linkwee.xoss.util.business.ProfitCalculationUtils;

/**
 * 团队管理津贴计算
 * @author liqimoon
 *
 */

@Component
public class InsuranceLeaderProfitCalculation implements InsuranceProfitCalculation{
	
	private static final String TYPE = "1006";
	
    @Resource
    private CrmCfplannerService crmCfplannerService;
	@Autowired
	private CrmCfpLevelRewardRateService levelRewardRateService;
	@Autowired
	private CimOrginfoService orginfoService;

	@Override
	public List<InsuranceFeedetailWrapper> calculate(CrmInvestor crmInvestor,CrmCfplanner crmCfplanner,InsuranceInvestRecordWrapper insuranceInvestRecordWrapper) {
		
		if(crmInvestor==null||crmCfplanner==null||insuranceInvestRecordWrapper==null){return null;}
		List<InsuranceFeedetailWrapper> insuranceFeedetailWrapperList = new ArrayList<InsuranceFeedetailWrapper>();
		CrmCfplanner cfp1 = null;//上一级
		CrmCfplanner cfp2 = null;//上两级
		CrmCfplanner cfp3 = null;//上三级
		if(crmCfplanner.getParentId()!=null){
			cfp1 = crmCfplannerService.queryCfplannerByUserId(crmCfplanner.getParentId());
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
		BigDecimal baseFeeAmount = null;
		if(insuranceInvestRecordWrapper.getFeeRate().compareTo(BigDecimal.ZERO) > 0 && insuranceInvestRecordWrapper.getYearpurAmount().compareTo(BigDecimal.ZERO) > 0){
			baseFeeAmount = CalculateTools.feeAmountCompute(insuranceInvestRecordWrapper.getYearpurAmount(),insuranceInvestRecordWrapper.getFeeRate().doubleValue());
		} else {
			baseFeeAmount = BigDecimal.ZERO;
		}
		
		//发给上三级情况
		if(ProfitCalculationUtils.paySM3(crmCfplanner,cfp1,cfp2,cfp3)){
			/**
			 * 创建保险明细封装类
			 */
			InsuranceFeedetailWrapper insuranceFeedetailWrapper = ProfitCalculationUtils.getBaseFeedetailWrapper(insuranceInvestRecordWrapper,crmCfplanner);
			insuranceFeedetailWrapper.setFeeAmount(CalculateTools.feeAmountCompute(baseFeeAmount, levelRewardRateService.selectOneByLevelCode(cfp3.getJobGrade()).getTeamAllowanceRate().doubleValue()));
			insuranceFeedetailWrapper.setProfitCfplannerId(cfp3.getUserId());
			insuranceFeedetailWrapper.setProfitCfplannerIdLowType("3");//0=默认|1=上1级|2=上2级|上3级
			insuranceFeedetailWrapper.setOriginCfplannerParent1Id(cfp1.getUserId());
			insuranceFeedetailWrapper.setOriginCfplannerParent2Id(cfp2.getUserId());
			insuranceFeedetailWrapper.setOriginCfplannerParent3Id(cfp3.getUserId());
			insuranceFeedetailWrapper.setLowUserId(cfp2.getUserId());
			insuranceFeedetailWrapper.setLowType("2");
			String remark = cfp2.getUserName()+"("+RandomTextCreator.encrypTion(cfp2.getMobile())+")的下下级理财师销售"+insuranceInvestRecordWrapper.getProductAmount()+"元《"+insuranceInvestRecordWrapper.getOrgName()+"-"+insuranceInvestRecordWrapper.getProductName()+"》";
			if(StringUtils.isBlank(insuranceInvestRecordWrapper.getRemark())){		
				insuranceFeedetailWrapper.setRemark(remark);
			}
			insuranceFeedetailWrapper.setSucceedRemark(remark);
			insuranceFeedetailWrapper.setFeeType(TYPE);
			insuranceFeedetailWrapper.setFeeRate(new BigDecimal(levelRewardRateService.selectOneByLevelCode(cfp3.getJobGrade()).getTeamAllowanceRate()));
			insuranceFeedetailWrapperList.add(insuranceFeedetailWrapper);
		}
		//发给上两级情况
		if(ProfitCalculationUtils.paySM2(crmCfplanner,cfp1,cfp2,cfp3)){
			/**
			 * 创建保险明细封装类
			 */
			InsuranceFeedetailWrapper insuranceFeedetailWrapper = ProfitCalculationUtils.getBaseFeedetailWrapper(insuranceInvestRecordWrapper,crmCfplanner);
			insuranceFeedetailWrapper.setFeeAmount(CalculateTools.feeAmountCompute(baseFeeAmount, levelRewardRateService.selectOneByLevelCode(cfp2.getJobGrade()).getTeamAllowanceRate().doubleValue()));
			insuranceFeedetailWrapper.setProfitCfplannerId(cfp2.getUserId());
			insuranceFeedetailWrapper.setProfitCfplannerIdLowType("2");//0=默认|1=上1级|2=上2级|上3级
			insuranceFeedetailWrapper.setOriginCfplannerParent1Id(cfp1.getUserId());
			insuranceFeedetailWrapper.setOriginCfplannerParent2Id(cfp2.getUserId());
			insuranceFeedetailWrapper.setLowUserId(cfp1.getUserId());
			insuranceFeedetailWrapper.setLowType("1");
			String remark = cfp1.getUserName()+"("+RandomTextCreator.encrypTion(cfp1.getMobile())+")的下级理财师销售"+insuranceInvestRecordWrapper.getProductAmount()+"元《"+insuranceInvestRecordWrapper.getOrgName()+"-"+insuranceInvestRecordWrapper.getProductName()+"》";
			if(StringUtils.isBlank(insuranceInvestRecordWrapper.getRemark())){			
				insuranceFeedetailWrapper.setRemark(remark);
			}
			insuranceFeedetailWrapper.setSucceedRemark(remark);
			insuranceFeedetailWrapper.setFeeType(TYPE);
			insuranceFeedetailWrapper.setFeeRate(new BigDecimal(levelRewardRateService.selectOneByLevelCode(cfp2.getJobGrade()).getTeamAllowanceRate().doubleValue()));
			insuranceFeedetailWrapperList.add(insuranceFeedetailWrapper);
		}
		//发给上一级情况
		if(ProfitCalculationUtils.paySM1(crmCfplanner,cfp1,cfp2,cfp3)){
			/**
			 * 创建保险明细封装类
			 */
			InsuranceFeedetailWrapper insuranceFeedetailWrapper = ProfitCalculationUtils.getBaseFeedetailWrapper(insuranceInvestRecordWrapper,crmCfplanner);
			insuranceFeedetailWrapper.setFeeAmount(CalculateTools.feeAmountCompute(baseFeeAmount, levelRewardRateService.selectOneByLevelCode(cfp1.getJobGrade()).getTeamAllowanceRate().doubleValue()));
			insuranceFeedetailWrapper.setProfitCfplannerId(cfp1.getUserId());
			insuranceFeedetailWrapper.setProfitCfplannerIdLowType("1");//0=默认|1=上1级|2=上2级|上3级
			insuranceFeedetailWrapper.setOriginCfplannerParent1Id(cfp1.getUserId());
			insuranceFeedetailWrapper.setLowUserId(crmCfplanner.getUserId());
			insuranceFeedetailWrapper.setLowType("0");
			String remark = crmCfplanner.getUserName()+"("+RandomTextCreator.encrypTion(crmCfplanner.getMobile())+")销售"+NumberUtils.getFormat(insuranceInvestRecordWrapper.getProductAmount(), "0.00")+"元《"+insuranceInvestRecordWrapper.getOrgName()+"-"+insuranceInvestRecordWrapper.getProductName()+"》";
			if(StringUtils.isBlank(insuranceInvestRecordWrapper.getRemark())){				
				insuranceFeedetailWrapper.setRemark(remark);
			}
			insuranceFeedetailWrapper.setSucceedRemark(remark);
			insuranceFeedetailWrapper.setFeeType(TYPE);
			insuranceFeedetailWrapper.setFeeRate(new BigDecimal(levelRewardRateService.selectOneByLevelCode(cfp1.getJobGrade()).getTeamAllowanceRate().doubleValue()));
			insuranceFeedetailWrapperList.add(insuranceFeedetailWrapper);
		}
		
		return insuranceFeedetailWrapperList;
	}
}
