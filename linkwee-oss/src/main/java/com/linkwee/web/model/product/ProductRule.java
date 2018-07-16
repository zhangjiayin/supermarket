package com.linkwee.web.model.product;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年06月20日 10:18:50
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class ProductRule extends BaseEntity {
	
	private static final long serialVersionUID = -3334560383826680440L;
	
    /**
     *产品规则ID
     */
	private Integer tRuleId;
	
    /**
     *产品规则别名
     */
	private String tRuleName;
	
    /**
     *产品收益率
     */
	private BigDecimal fixRate;
	
    /**
     *产品期限起始天数或月数值或年数值
     */
	private Integer lineMinValue;
	
    /**
     *产品期限结束天数或月数值或年数值
     */
	private Integer lineMaxValue;
	
    /**
     *最近修改时间
     */
	private Date modifyTime;
	
    /**
     *规则描述
     */
	private String ruleDescription;
	
    /**
     *是否可用：0-可用 1-不可用
     */
	private String ruleDisabled;
	
    /**
     *佣金率
     */
	private BigDecimal feeRate;
	


	public void setTRuleId(Integer tRuleId){
		this.tRuleId = tRuleId;
	}
	
	public Integer getTRuleId(){
		return tRuleId;
	}
	
	public void setTRuleName(String tRuleName){
		this.tRuleName = tRuleName;
	}
	
	public String getTRuleName(){
		return tRuleName;
	}
	
	public void setFixRate(BigDecimal fixRate){
		this.fixRate = fixRate;
	}
	
	public BigDecimal getFixRate(){
		return fixRate;
	}
	
	public void setLineMinValue(Integer lineMinValue){
		this.lineMinValue = lineMinValue;
	}
	
	public Integer getLineMinValue(){
		return lineMinValue;
	}
	
	public void setLineMaxValue(Integer lineMaxValue){
		this.lineMaxValue = lineMaxValue;
	}
	
	public Integer getLineMaxValue(){
		return lineMaxValue;
	}
	
	public void setModifyTime(Date modifyTime){
		this.modifyTime = modifyTime;
	}
	
	public Date getModifyTime(){
		return modifyTime;
	}
	
	public void setRuleDescription(String ruleDescription){
		this.ruleDescription = ruleDescription;
	}
	
	public String getRuleDescription(){
		return ruleDescription;
	}
	
	public void setRuleDisabled(String ruleDisabled){
		this.ruleDisabled = ruleDisabled;
	}
	
	public String getRuleDisabled(){
		return ruleDisabled;
	}
	
	public void setFeeRate(BigDecimal feeRate){
		this.feeRate = feeRate;
	}
	
	public BigDecimal getFeeRate(){
		return feeRate;
	}
	

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

