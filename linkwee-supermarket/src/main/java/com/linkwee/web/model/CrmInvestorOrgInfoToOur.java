package com.linkwee.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年03月26日 16:21:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmInvestorOrgInfoToOur implements Serializable {
	
	private static final long serialVersionUID = -6441434277226834446L;
	
    /**
     *主键，自增长
     */
	private Integer id;
	
    /**
     *用户id
     */
	private String userId;
	
    /**
     *机构编码
     */
	private String orgNumber;
	
    /**
     *是否领会老用户 0-不是 1-是
     */
	private Integer ifOldAccount;
	
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
	
    /**
     *创建人
     */
	private String orgCreator;
	
    /**
     *修改人
     */
	private String orgUpdater;
	
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
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setOrgNumber(String orgNumber){
		this.orgNumber = orgNumber;
	}
	
	public String getOrgNumber(){
		return orgNumber;
	}
	
	public void setIfOldAccount(Integer ifOldAccount){
		this.ifOldAccount = ifOldAccount;
	}
	
	public Integer getIfOldAccount(){
		return ifOldAccount;
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
	
	public void setOrgCreator(String orgCreator){
		this.orgCreator = orgCreator;
	}
	
	public String getOrgCreator(){
		return orgCreator;
	}
	
	public void setOrgUpdater(String orgUpdater){
		this.orgUpdater = orgUpdater;
	}
	
	public String getOrgUpdater(){
		return orgUpdater;
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

