package com.linkwee.activity.service;

import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.Validate;

import com.linkwee.activity.model.UseRedPacketRule;
import com.linkwee.activity.model.UseRuleContext;
import com.linkwee.activity.model.UseRuleDetail;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.request.RedPacketInfoRequest;

public interface UseRedPacketRuleService extends GenericService<UseRedPacketRule, Long>{

	
	public enum InvestProductLimit{
		unLimit(0,1000),
		eqProductDeadlineLimit(10,1002),
		geProductDeadlineLimit(11,1003),
		productIdLimit(2,1001);
		
		private int limitCode;
		private int useCondition;
		
		private InvestProductLimit(int limitCode,int useCondition) {
			this.limitCode=limitCode;
			this.useCondition=useCondition;
		}

		public int getLimitCode() {
			return limitCode;
		}

		public int getUseCondition() {
			return useCondition;
		}
		
		public static InvestProductLimit getInvestProductLimit(int limitCode){
			for (InvestProductLimit investProductLimit : values()) {
				if(ObjectUtils.equals(investProductLimit.limitCode,limitCode)){
					return investProductLimit;
				}
			}
			return null;
		}
		
		public static InvestProductLimit getUseConditionLimit(int useCondition){
			for (InvestProductLimit investProductLimit : values()) {
				if(ObjectUtils.equals(investProductLimit.useCondition,useCondition)){
					return investProductLimit;
				}
			}
			return null;
		}
		
	}
	
	public enum InvestMoneyLimit{
		
		unLimit{

			@Override
			public void setUseRuleInvestMoneyLimit(UseRuleDetail useRuleDetail, Double min,Double max) {
				useRuleDetail.setMin(0d);
				useRuleDetail.setMax(0d);
			}

			@Override
			int getInvestMoneyLimitCode() {
				return 0;
			}

			
		},
		Limit{

			@Override
			public void setUseRuleInvestMoneyLimit(UseRuleDetail useRuleDetail,Double min,Double max) {
				Validate.notNull(min,"购买产品最小金额不能为空");
				Validate.notNull(max,"购买产品最大金额不能为空");
				Validate.isTrue(min.compareTo(0d)>-1,"购买产品最小金额必须为大于等于0的数值");
				Validate.isTrue(max.compareTo(0d)>-1,"购买产品最大金额必须为大于等于0的数值");
				if(!ObjectUtils.equals(max, 0d)){
					Validate.isTrue(max.compareTo(min)==1,"购买产品最小金额不能大于等于最大金额");
				}
				useRuleDetail.setMin(min);
				useRuleDetail.setMax(max);
			}

			@Override
			int getInvestMoneyLimitCode() {
				return 1;
			}
			
		};
	
		abstract int getInvestMoneyLimitCode();
		
		public abstract void setUseRuleInvestMoneyLimit(UseRuleDetail useRuleDetail,Double min,Double max);
		
		public static InvestMoneyLimit getInvestMoneyLimit(int limitCode){
			for (InvestMoneyLimit investMoneyLimit : values()) {
				if(ObjectUtils.equals(investMoneyLimit.getInvestMoneyLimitCode(),limitCode)){
					return investMoneyLimit;
				}
			}
			return null;
		}
	}
	
	
	/**
	 * 添加使用规则
	 * @param useRuleContext
	 * @return
	 * @throws Exception
	 */
	boolean insertUseRedPacketRule(UseRuleContext useRuleContext) throws Exception;
	
	boolean deleteUseRedPacketRule(String  activityId) throws Exception;
	
	boolean updateUseRedPacketRule(String activityId,String pids) throws Exception;
	
	/**
	 * 查询活动使用规则
	 * @param activityId
	 * @return
	 */
	void getRedPacketUseRule(RedPacketInfoRequest redPacketInfo,String  activityId);
	
	/**
	 * 通过活动id查询红包使用规则
	 * @author yalin 
	 * @date 2016年6月30日 下午5:00:09  
	 * @param activityId
	 * @return
	 */
	List<UseRedPacketRule> queryAllUseRuleByActivityId(String  activityId);
}
