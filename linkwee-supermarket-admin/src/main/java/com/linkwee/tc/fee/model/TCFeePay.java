package com.linkwee.tc.fee.model;

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
 * @创建时间：2017年12月12日 17:16:22
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class TCFeePay implements Serializable {
	
	private static final long serialVersionUID = -4935394006108405409L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *佣金发放单据编号（理财师编码+年月）
     */
	private String billId;
	
    /**
     *部门
     */
	private String department;
	
    /**
     *理财师编码
     */
	private String userId;
	
    /**
     *理财师手机号码
     */
	private String userMobile;
	
    /**
     *理财师姓名
     */
	private String userName;
	
    /**
     *佣金
     */
	private BigDecimal feeAmount;
	
    /**
     *佣金类型 0=老数据|1001=佣金|1002=推荐奖励|1005=直接管理津贴|1006=团队管理津贴
     */
	private String type;
	
    /**
     *年月
     */
	private String month;
	
    /**
     *产品类型  0-网贷 1-保险
     */
	private Integer productClassify;
	
    /**
     *发放状态 0:未发放; 1:发放中 2:发放成功; 3:发放失败
     */
	private Integer status;
	
    /**
     *
     */
	private String resultCode;
	
    /**
     *发放结果描述
     */
	private String resultMsg;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setBillId(String billId){
		this.billId = billId;
	}
	
	public String getBillId(){
		return billId;
	}
	
	public void setDepartment(String department){
		this.department = department;
	}
	
	public String getDepartment(){
		return department;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setUserMobile(String userMobile){
		this.userMobile = userMobile;
	}
	
	public String getUserMobile(){
		return userMobile;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setFeeAmount(BigDecimal feeAmount){
		this.feeAmount = feeAmount;
	}
	
	public BigDecimal getFeeAmount(){
		return feeAmount;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return type;
	}
	
	public void setMonth(String month){
		this.month = month;
	}
	
	public String getMonth(){
		return month;
	}
	
	public void setProductClassify(Integer productClassify){
		this.productClassify = productClassify;
	}
	
	public Integer getProductClassify(){
		return productClassify;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setResultCode(String resultCode){
		this.resultCode = resultCode;
	}
	
	public String getResultCode(){
		return resultCode;
	}
	
	public void setResultMsg(String resultMsg){
		this.resultMsg = resultMsg;
	}
	
	public String getResultMsg(){
		return resultMsg;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

