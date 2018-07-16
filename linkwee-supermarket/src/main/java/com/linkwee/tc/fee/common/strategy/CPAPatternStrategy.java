package com.linkwee.tc.fee.common.strategy;

import java.math.BigDecimal;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linkwee.tc.fee.common.FeeCalcDelegate;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.vo.InvestRecordWrapper;
@Component
public class CPAPatternStrategy implements OrgFeeCalcPatternStrategy{
	
	@Autowired
	private FeeCalcDelegate delegate;

	@Override
	public boolean matchPattern(CimOrginfo orginfo,InvestRecordWrapper investRecordWrapper) {
		return ObjectUtils.equals(orginfo.getOrgFeeType(), 1);
	}

	@Override
	public void orgFeeCalc(CimOrginfo orginfo,InvestRecordWrapper investRecordWrapper) throws Exception{
		
		if(investRecordWrapper.getIsCollect() == 2 && investRecordWrapper.getStatus() == 1){//募集中
			String remark = "该产品仍在募集中,募集成功后将会显示佣金";
			investRecordWrapper.setRemark(remark);
			//募集期年化为0 否则募集成功计算佣金时年化叠加
			//募集期间理财师佣金为零, 募集成功后更新佣金
			investRecordWrapper.setProductDays(0);
		} else if((investRecordWrapper.getIsCollect() == 2 && investRecordWrapper.getStatus() == 2) || investRecordWrapper.getIsCollect() == 1) {//募集成功或者非募集产品   按照最小期限当固定期产品处理
			if(!investRecordWrapper.isPlatfromFirstInvest()){
				String remark = StringUtils.join(new Object[]{orginfo.getName(),"产品为首投,该客户之前已经投资过"});
				investRecordWrapper.setRemark(remark);
				investRecordWrapper.setFeeRatio(BigDecimal.ZERO);//cpa模式不是首次投资理财师佣金为零
			}else{
				if(investRecordWrapper.getInvestAmt().compareTo(orginfo.getOrgAmountLimit())==1){
					investRecordWrapper.setInvestAmt(orginfo.getOrgAmountLimit());
				}
				if(investRecordWrapper.getProductDays().compareTo(orginfo.getOrgInvestdeadlineLimit())==1){
					investRecordWrapper.setProductDays(orginfo.getOrgInvestdeadlineLimit());
				}	
			}
		} else if(investRecordWrapper.getIsCollect() == 2 && investRecordWrapper.getStatus() == 3) {//募集失败
			String remark = "该产品募集失败,无佣金";
			investRecordWrapper.setRemark(remark);
			investRecordWrapper.setProductDays(0);//募集失败 佣金为0
		}
		//交给委托类计算
		delegate.feeCalc(investRecordWrapper);			
	}

}
