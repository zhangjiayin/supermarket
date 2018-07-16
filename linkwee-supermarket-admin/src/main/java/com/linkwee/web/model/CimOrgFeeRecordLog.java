package com.linkwee.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年05月03日 18:30:26
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimOrgFeeRecordLog implements Serializable {
	
	private static final long serialVersionUID = -6129881044697571226L;
	
    /**
     *主键，自增长
     */
	private Integer id;
	
    /**
     *机构编码
     */
	private String orgNumber;
	
    /**
     *规则ID
     */
	private String feeRuleId;
	
    /**
     *收费比例
     */
	private BigDecimal feeRatio;
	
    /**
     *收费额度
     */
	private BigDecimal feeVal;
	
    /**
     *区间最小值
     */
	private BigDecimal intervalMinVal;
	
    /**
     *区间最大值
     */
	private BigDecimal intervalMaxVal;
	
    /**
     *收费区间单位,首投金额元,产品期限天,月销售额万
     */
	private String intervalUnit;
	
    /**
     *
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date creattime;
	
    /**
     *创建人
     */
	private String creator;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updatetime;
	
    /**
     *修改人
     */
	private String updater;
	
    /**
     *备注
     */
	private String remark;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public void setFeeRuleId(String feeRuleId){
		this.feeRuleId = feeRuleId;
	}
	
	public String getFeeRuleId(){
		return feeRuleId;
	}
	
	public void setFeeRatio(BigDecimal feeRatio){
		this.feeRatio = feeRatio;
	}
	
	public BigDecimal getFeeRatio(){
		return feeRatio;
	}
	
	public void setFeeVal(BigDecimal feeVal){
		this.feeVal = feeVal;
	}
	
	public BigDecimal getFeeVal(){
		return feeVal;
	}
	
	public void setIntervalMinVal(BigDecimal intervalMinVal){
		this.intervalMinVal = intervalMinVal;
	}
	
	public BigDecimal getIntervalMinVal(){
		return intervalMinVal;
	}
	
	public void setIntervalMaxVal(BigDecimal intervalMaxVal){
		this.intervalMaxVal = intervalMaxVal;
	}
	
	public BigDecimal getIntervalMaxVal(){
		return intervalMaxVal;
	}
	
	public void setIntervalUnit(String intervalUnit){
		this.intervalUnit = intervalUnit;
	}
	
	public String getIntervalUnit(){
		return intervalUnit;
	}
	
	public void setCreattime(Date creattime){
		this.creattime = creattime;
	}
	
	public Date getCreattime(){
		return creattime;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	
	public String getCreator(){
		return creator;
	}
	
	public void setUpdatetime(Date updatetime){
		this.updatetime = updatetime;
	}
	
	public Date getUpdatetime(){
		return updatetime;
	}
	
	public void setUpdater(String updater){
		this.updater = updater;
	}
	
	public String getUpdater(){
		return updater;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

