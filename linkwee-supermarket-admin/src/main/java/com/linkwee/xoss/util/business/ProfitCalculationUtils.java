package com.linkwee.xoss.util.business;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import com.linkwee.tc.fee.model.vo.InsuranceFeedetailWrapper;
import com.linkwee.web.model.CimInsuranceFeedetailExtends;
import com.linkwee.web.model.CimInsuranceNotify;

public class ProfitCalculationUtils {
	
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
