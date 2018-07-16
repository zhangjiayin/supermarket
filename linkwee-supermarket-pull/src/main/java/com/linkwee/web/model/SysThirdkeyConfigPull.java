package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Integer;
 import java.lang.Long;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年02月24日 14:58:15
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SysThirdkeyConfigPull implements Serializable {
	
	private static final long serialVersionUID = -8498621456329581502L;
	
    /**
     *主键
     */
	private Long orgId;
	
    /**
     *机构编码
     */
	private String orgNumber;
	
    /**
     *机构公钥
     */
	private String orgKey;
	
    /**
     *机构私钥
     */
	private String orgSecret;
	
    /**
     *y：开启，n：关闭
     */
	private String orgStatus;
	
    /**
     *y：开启，n：关闭
     */
	private String productStatus;
	
    /**
     *y：开启，n：关闭
     */
	private String investStatus;
	
    /**
     *y：开启，n：关闭
     */
	private String repaymentStatus;
	
    /**
     *
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
    /**
     *创建人
     */
	private String createUser;
	
    /**
     *产品拉取接口地址
     */
	private String orgPullProductUrl;
	
    /**
     *募集类型产品状态查询接口地址
     */
	private String orgQueryCollectProductUrl;
	
    /**
     *投资记录拉取接口地址
     */
	private String orgPullInvestRecordUrl;
	
    /**
     *投资回款拉取接口地址
     */
	private String orgPullRepaymentRecordUrl;
	
    /**
     *合作规范  0-完全遵循文档合作  1-非文档标准合作
     */
	private Integer cooperationStandard;
	


	public void setOrgId(Long orgId){
		this.orgId = orgId;
	}
	
	public Long getOrgId(){
		return orgId;
	}
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public void setOrgKey(String orgKey){
		this.orgKey = orgKey;
	}
	
	public String getOrgKey(){
		return orgKey;
	}
	
	public void setOrgSecret(String orgSecret){
		this.orgSecret = orgSecret;
	}
	
	public String getOrgSecret(){
		return orgSecret;
	}
	
	public void setOrgStatus(String orgStatus){
		this.orgStatus = orgStatus;
	}
	
	public String getOrgStatus(){
		return orgStatus;
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
	
	public void setCreateUser(String createUser){
		this.createUser = createUser;
	}
	
	public String getCreateUser(){
		return createUser;
	}
	
	public void setOrgPullProductUrl(String orgPullProductUrl){
		this.orgPullProductUrl = orgPullProductUrl;
	}
	
	public String getOrgPullProductUrl(){
		return orgPullProductUrl;
	}
	
	public void setOrgQueryCollectProductUrl(String orgQueryCollectProductUrl){
		this.orgQueryCollectProductUrl = orgQueryCollectProductUrl;
	}
	
	public String getOrgQueryCollectProductUrl(){
		return orgQueryCollectProductUrl;
	}
	
	public void setOrgPullInvestRecordUrl(String orgPullInvestRecordUrl){
		this.orgPullInvestRecordUrl = orgPullInvestRecordUrl;
	}
	
	public String getOrgPullInvestRecordUrl(){
		return orgPullInvestRecordUrl;
	}
	
	public void setOrgPullRepaymentRecordUrl(String orgPullRepaymentRecordUrl){
		this.orgPullRepaymentRecordUrl = orgPullRepaymentRecordUrl;
	}
	
	public String getOrgPullRepaymentRecordUrl(){
		return orgPullRepaymentRecordUrl;
	}
	
	public void setCooperationStandard(Integer cooperationStandard){
		this.cooperationStandard = cooperationStandard;
	}
	
	public Integer getCooperationStandard(){
		return cooperationStandard;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getInvestStatus() {
		return investStatus;
	}

	public void setInvestStatus(String investStatus) {
		this.investStatus = investStatus;
	}

	public String getRepaymentStatus() {
		return repaymentStatus;
	}

	public void setRepaymentStatus(String repaymentStatus) {
		this.repaymentStatus = repaymentStatus;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

