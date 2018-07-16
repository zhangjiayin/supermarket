package com.linkwee.tc.fee.common.strategy;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linkwee.tc.fee.common.FeeCalcDelegate;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.vo.InvestRecordWrapper;

@Component
public class CPSPatternStrategy implements OrgFeeCalcPatternStrategy{
	
	@Autowired
	private FeeCalcDelegate delegate;

	@Override
	public boolean matchPattern(CimOrginfo orginfo,InvestRecordWrapper investRecordWrapper) {
		return  ObjectUtils.equals(orginfo.getOrgFeeType(), 2);
	}

	@Override
	public void orgFeeCalc(CimOrginfo orginfo,InvestRecordWrapper investRecordWrapper) throws Exception{
		
		if(investRecordWrapper.getIsCollect() == 2 && investRecordWrapper.getStatus() == 1){//募集中
			String remark = "该产品仍在募集中,募集成功后将会显示佣金";
			investRecordWrapper.setRemark(remark);
			//募集期年化为0 否则募集成功计算佣金时年化叠加
			//募集期间理财师佣金为零, 募集成功后更新佣金
			investRecordWrapper.setProductDays(0);
		} else if(investRecordWrapper.getIsCollect() == 2 && investRecordWrapper.getStatus() == 3) {//募集失败
			String remark = "该产品募集失败,无佣金";
			investRecordWrapper.setRemark(remark);
			//募集失败 佣金为0
			investRecordWrapper.setProductDays(0);
		}
		//交给委托类计算
		delegate.feeCalc(investRecordWrapper);
	}
}

