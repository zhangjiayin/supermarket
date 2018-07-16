package com.linkwee.xoss.util.business;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import com.linkwee.tc.fee.model.vo.InsuranceFeedetailWrapper;
import com.linkwee.web.model.CimInsuranceFeedetailExtends;
import com.linkwee.web.model.CimInsuranceNotify;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.vo.InsuranceInvestRecordWrapper;

public class ProfitCalculationUtils {

	/**
	 * 发给上三级情况
	 * @param cfp
	 * @param cfp1
	 * @param cfp2
	 * @param cfp3
	 * @return
	 */
	public static boolean paySM1(CrmCfplanner cfp,CrmCfplanner cfp1,CrmCfplanner cfp2,CrmCfplanner cfp3){
		if(cfp!=null&&cfp1!=null){
			Integer cfpW = cfp.getJobGradeWeight();
			Integer cfp1W = cfp1.getJobGradeWeight();
//			Integer cfp2W = cfp2!=null?cfp2.getJobGradeWeight():0;
//			Integer cfp3W = cfp3!=null?cfp3.getJobGradeWeight():0;
			if(cfp1W>=30&&(cfp1W>cfpW)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 发给上两级情况
	 * @param cfp
	 * @param cfp1
	 * @param cfp2
	 * @param cfp3
	 * @return
	 */
	public static boolean paySM2(CrmCfplanner cfp,CrmCfplanner cfp1,CrmCfplanner cfp2,CrmCfplanner cfp3){
		if(cfp!=null&&cfp1!=null&&cfp2!=null){
			Integer cfpW = cfp.getJobGradeWeight();
			Integer cfp1W = cfp1.getJobGradeWeight();
			Integer cfp2W = cfp2.getJobGradeWeight();
//			Integer cfp3W = cfp3!=null?cfp3.getJobGradeWeight():0;
			if(cfp2W>=30&&(cfp2W>cfpW)&&(cfp2W>cfp1W)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 发给上一级情况
	 * @param cfp
	 * @param cfp1
	 * @param cfp2
	 * @param cfp3
	 * @return
	 */
	public static boolean paySM3(CrmCfplanner cfp,CrmCfplanner cfp1,CrmCfplanner cfp2,CrmCfplanner cfp3){
		if(cfp!=null&&cfp1!=null&&cfp2!=null&&cfp3!=null){
			Integer cfpW = cfp.getJobGradeWeight();
			Integer cfp1W = cfp1.getJobGradeWeight();
			Integer cfp2W = cfp2.getJobGradeWeight();
			Integer cfp3W = cfp3.getJobGradeWeight();
			if(cfp3W>=30&&(cfp3W>cfpW)&&(cfp3W>cfp1W)&&(cfp3W>cfp2W)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 封装保险佣金明细包装类
	 * @param insuranceInvestRecordWrapper
	 * @param cfplanner
	 * @return
	 */
	public static InsuranceFeedetailWrapper getBaseFeedetailWrapper(InsuranceInvestRecordWrapper insuranceInvestRecordWrapper,CrmCfplanner cfplanner){	
		InsuranceFeedetailWrapper insuranceFeedetailWrapper = new InsuranceFeedetailWrapper();
		
		insuranceFeedetailWrapper.setBillId(insuranceInvestRecordWrapper.getBillId());
		insuranceFeedetailWrapper.setInvestorId(insuranceInvestRecordWrapper.getInvestorId());
		insuranceFeedetailWrapper.setProductAmount(insuranceInvestRecordWrapper.getProductAmount());
		insuranceFeedetailWrapper.setProductId(insuranceInvestRecordWrapper.getProductId());
		insuranceFeedetailWrapper.setProductFeeRate(insuranceInvestRecordWrapper.getFeeRate());
		insuranceFeedetailWrapper.setProductOrgId(insuranceInvestRecordWrapper.getProductOrgId());
		insuranceFeedetailWrapper.setYearpurAmount(insuranceInvestRecordWrapper.getYearpurAmount());
		insuranceFeedetailWrapper.setOriginCfplannerId(cfplanner.getUserId());
		insuranceFeedetailWrapper.setRemark(insuranceInvestRecordWrapper.getRemark());
		
		
/*		以下参数需 具体计算
 * 		insuranceFeedetailWrapper.setFeeAmount(feeAmount);
		insuranceFeedetailWrapper.setFeeRate(feeRate);
		insuranceFeedetailWrapper.setFeeType(feeType);
		
		insuranceFeedetailWrapper.setProfitCfplannerId(profitCfplannerId);
		insuranceFeedetailWrapper.setProfitCfplannerIdLowType(profitCfplannerIdLowType);
		
		insuranceFeedetailWrapper.setLowType(lowType);
		insuranceFeedetailWrapper.setLowUserId(lowUserId);
		insuranceFeedetailWrapper.setOriginCfplannerParent1Id(originCfplannerParent1Id);
		insuranceFeedetailWrapper.setOriginCfplannerParent2Id(originCfplannerParent2Id);
		insuranceFeedetailWrapper.setOriginCfplannerParent3Id(originCfplannerParent3Id);*/
		
		return insuranceFeedetailWrapper;
	}
	
	/**
	 * 封装保险佣金明细包装类
	 * @param cimInsuranceFeedetail
	 * @param cimInsuranceNotify
	 * @return
	 */
	public static InsuranceFeedetailWrapper creatInsuranceFeedetailWrapperForFeeCalcExist(CimInsuranceFeedetailExtends cimInsuranceFeedetailExtends,CimInsuranceNotify cimInsuranceNotify) {
		
		InsuranceFeedetailWrapper insuranceFeedetailWrapper = new InsuranceFeedetailWrapper();
		BeanUtils.copyProperties(cimInsuranceFeedetailExtends, insuranceFeedetailWrapper);
		
		//0-待审核   生成相关佣金为0的 佣金记录 便于前端页面显示
		String remark = null;
		if(cimInsuranceNotify.getAuditStatus() == 0){
			remark = String.format("%s购买保险产品《%s》，将会在15天—45天内结算（受保险机构结算方式的影响）", cimInsuranceFeedetailExtends.getUserNameMobile(),cimInsuranceFeedetailExtends.getProductName());
			insuranceFeedetailWrapper.setRemark(remark);
			insuranceFeedetailWrapper.setYearpurAmount(BigDecimal.ZERO);
			insuranceFeedetailWrapper.setFeeAmount(BigDecimal.ZERO);
		} else if(cimInsuranceNotify.getAuditStatus() == 1 || cimInsuranceNotify.getAuditStatus() == 3){ //系统审核通过
			insuranceFeedetailWrapper.setRemark(cimInsuranceFeedetailExtends.getSucceedRemark());//需具体计算展示备注
			insuranceFeedetailWrapper.setSucceedRemark("");//清空SucceedRemark
			insuranceFeedetailWrapper.setYearpurAmount(cimInsuranceFeedetailExtends.getProductAmount());
			insuranceFeedetailWrapper.setFeeAmount(cimInsuranceFeedetailExtends.getProductAmount().multiply(cimInsuranceFeedetailExtends.getProductFeeRate()).divide(new BigDecimal(100)).multiply(cimInsuranceFeedetailExtends.getFeeRate()).divide(new BigDecimal(100)));
		} else if(cimInsuranceNotify.getAuditStatus() == 2 || cimInsuranceNotify.getAuditStatus() == 4){ //系统审核失败
			remark = String.format("%s购买保险产品《%s》，由于在犹豫期退保，佣金结算失败", cimInsuranceFeedetailExtends.getUserNameMobile(),cimInsuranceFeedetailExtends.getProductName());
			insuranceFeedetailWrapper.setRemark(remark);
			insuranceFeedetailWrapper.setYearpurAmount(BigDecimal.ZERO);
			insuranceFeedetailWrapper.setFeeAmount(BigDecimal.ZERO);
		}
		
		return insuranceFeedetailWrapper;
	}
}
