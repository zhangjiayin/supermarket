package com.linkwee.tc.fee.common.profitCalculation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linkwee.core.util.NumberUtils;
import com.linkwee.tc.fee.common.CalculateTools;
import com.linkwee.tc.fee.model.vo.FeedetailWrapper;
import com.linkwee.web.dao.CimProductInvestRecordMapper;
import com.linkwee.web.model.ActAddFeeCoupon;
import com.linkwee.web.model.ActAddFeeCouponUseDetail;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.service.ActAddFeeCouponService;
import com.linkwee.web.service.ActAddFeeCouponUseDetailService;

/**
 * 平台加佣券-基础加佣
 * @author liqimoon
 *
 */
@Component
public class BaseAddFeeProfitCalculation implements ProfitCalculation{
	
	private static final String TYPE = "1011";
	
	@Resource
	private ActAddFeeCouponService addFeeCouponService;
	
	@Resource
	private ActAddFeeCouponUseDetailService useDetailService;
	
	@Autowired
	private CimProductInvestRecordMapper productInvestRecordMapper;
	
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
	public List<FeedetailWrapper> calculate(CrmInvestor investor, CrmCfplanner cfplanner,InvestRecordWrapper investRecord) {
		
		if(investor==null||cfplanner==null||investRecord==null || investRecord.getYearPurAmount().compareTo(new BigDecimal(0)) == 0 ){return null;}//
		
		List<FeedetailWrapper> wrappers = new ArrayList<FeedetailWrapper>();
		List<ActAddFeeCoupon> couponList = addFeeCouponService.queryUseableAddFeeCoupon(investRecord.getInvestTime(),1);
		for(ActAddFeeCoupon addFeeCoupon : couponList){
			
			boolean isInvestLlimit,isPatformLimit;			
			isInvestLlimit=isPatformLimit=false;
			//平台投资次数
			int investCount = productInvestRecordMapper.queryUserPlatfromInvestCount(investor.getUserId(),investRecord.getProductOrgId());
			
			if(addFeeCoupon.isPlatformLimit()){
				isPatformLimit = ObjectUtils.equals(addFeeCoupon.getPlatformId(), investRecord.getProductOrgId());
			} else {
				isPatformLimit=true;
			}
			if(!isPatformLimit)continue;
			
			int investLlimit = addFeeCoupon.getInvestLimit();
			switch (investLlimit) {
				case 0:
					isInvestLlimit = true;
					break;
				case 1:
					isInvestLlimit = (null == investor.getFirstInvestTime());
					break;
				case 2:
					isInvestLlimit = investCount==0;
					break;
			}
			if(!isInvestLlimit)continue;
			
			FeedetailWrapper feedetailWrapper = getBaseFeedetailWrapper(investRecord);
			feedetailWrapper.setProfitCfplannerId(cfplanner.getUserId());
			feedetailWrapper.setOriginCfplannerId(cfplanner.getUserId());
			feedetailWrapper.setRatio(addFeeCoupon.getRate().doubleValue());
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
			//基础加拥佣金 = 加拥比率 * 年化业绩
			BigDecimal baseAddFeeAmount = CalculateTools.feeAmountCompute(feedetailWrapper.getYearPurAmount(),feedetailWrapper.getRatio());
			feedetailWrapper.setFeeamount(baseAddFeeAmount);
			wrappers.add(feedetailWrapper);
			ActAddFeeCouponUseDetail useDetail = new ActAddFeeCouponUseDetail();
			useDetail.setCouponId(addFeeCoupon.getCouponId());
			useDetail.setCouponType(addFeeCoupon.getType());
			useDetail.setCreateTime(new Date());
			useDetail.setFeeAmount(baseAddFeeAmount);
			useDetail.setFeeRate(addFeeCoupon.getRate());
			useDetail.setInvestId(investRecord.getBizId());
			useDetail.setUserId(cfplanner.getUserId());
			useDetailService.insert(useDetail);
		}	
		return wrappers;
	}

}
