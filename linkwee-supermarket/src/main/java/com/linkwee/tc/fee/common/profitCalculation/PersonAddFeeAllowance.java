package com.linkwee.tc.fee.common.profitCalculation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.linkwee.core.util.NumberUtils;
import com.linkwee.tc.fee.common.CalculateTools;
import com.linkwee.tc.fee.model.vo.FeedetailWrapper;
import com.linkwee.web.model.ActPersonAddfeeTicketSenduseDetail;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.acc.ActPersonAddfeeTicketExtends;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.service.ActPersonAddfeeTicketSenduseDetailService;
import com.linkwee.web.service.ActPersonAddfeeTicketService;

/**
 * 个人加佣（个人加佣券）
 * @author liqimoon
 *
 */
@Component
public class PersonAddFeeAllowance extends AbstractProfitCalculation{

	private static final String TYPE = "1021";
	
	@Resource
	private ActPersonAddfeeTicketService  actPersonAddfeeTicketService;
	@Resource
	private ActPersonAddfeeTicketSenduseDetailService actPersonAddfeeTicketSenduseDetailService;
	
	@Override
	protected Boolean preCalculate(CrmInvestor investor, CrmCfplanner cfplanner,InvestRecordWrapper investRecord, FeedetailWrapper feedetailWrapper) {
		
		/**
		 * 1:非募集期产品 
		 * 2: 募集期产品需募集成功后才计算加佣
		 */
		
		if(investRecord.getIsCollect() == 1 || (investRecord.getIsCollect() == 2 && investRecord.getStatus() == 2)){
			/**
			 * 个人加佣券需自己使用
			 */
			if(investor.getUserId().equals(cfplanner.getUserId())){
				//查询个人持有的加佣券
				List<ActPersonAddfeeTicketExtends> actPersonAddfeeTicketExtendsList = actPersonAddfeeTicketService.queryPersonAddfeeTicket(cfplanner.getUserId(),1);
				ActPersonAddfeeTicketExtends actPersonAddfeeTicketExtends = actPersonAddfeeTicketService.matchePersonAddfeeTicket(actPersonAddfeeTicketExtendsList,investRecord);
				
				if(actPersonAddfeeTicketExtends != null){
					feedetailWrapper.setProfitCfplannerId(cfplanner.getUserId());
					feedetailWrapper.setOriginCfplannerId(cfplanner.getUserId());
					feedetailWrapper.setRatio(actPersonAddfeeTicketExtends.getRate().doubleValue());
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
					
					//加佣天数
					BigDecimal feeAmount = BigDecimal.ZERO;
					Integer addFeeDay = 0;
					if(actPersonAddfeeTicketExtends.getAddFeeLimit() == 1 && actPersonAddfeeTicketExtends.getAddFeeLimitDay() != null 
							&&  actPersonAddfeeTicketExtends.getAddFeeLimitDay().compareTo(investRecord.getProductDays()) < 0){//加佣佣金 = 加佣天数 * 加佣佣金费率
						addFeeDay = actPersonAddfeeTicketExtends.getAddFeeLimitDay();
					} else {
						addFeeDay = investRecord.getProductDays();
					}
					feedetailWrapper.setYearPurAmount(CalculateTools.yearpurAmountCompute(investRecord.getInvestAmt(),addFeeDay));//根据实际天数重新计算产品的年化
					feeAmount = CalculateTools.feeAmountCompute(feedetailWrapper.getYearPurAmount(),feedetailWrapper.getRatio());
					feedetailWrapper.setFeeamount(feeAmount);
					
					//更新个人加拥券发放使用明细
					ActPersonAddfeeTicketSenduseDetail actPersonAddfeeTicketSenduseDetail = new ActPersonAddfeeTicketSenduseDetail();
					actPersonAddfeeTicketSenduseDetail.setId(actPersonAddfeeTicketExtends.getTicketDetailId());
					actPersonAddfeeTicketSenduseDetail.setInvestId(investRecord.getBizId());
					actPersonAddfeeTicketSenduseDetail.setFeeAmount(feeAmount);
					actPersonAddfeeTicketSenduseDetail.setAddFeeDay(addFeeDay);
					actPersonAddfeeTicketSenduseDetail.setInvestUseTime(new Date());
					actPersonAddfeeTicketSenduseDetailService.update(actPersonAddfeeTicketSenduseDetail);
					
					return Boolean.TRUE;			
				} else {
					return Boolean.FALSE;
				}
			} else {
				return Boolean.FALSE;
			}
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	protected void internalCalculate(CrmInvestor investor,CrmCfplanner cfplanner, InvestRecordWrapper investRecord,FeedetailWrapper feedetailWrapper) {
		// TODO Auto-generated method stub
		
	}
}
