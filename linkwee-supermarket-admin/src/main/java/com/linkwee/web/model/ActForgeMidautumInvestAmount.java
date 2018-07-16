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
 * @创建人： Mignet
 * 
 * @创建时间：2017年09月20日 17:25:13
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActForgeMidautumInvestAmount implements Serializable {
	
	private static final long serialVersionUID = -4765936615064758439L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *用户姓名
     */
	private String userName;
	
    /**
     *头像图片
     */
	private String headImage;
	
    /**
     *手机号码
     */
	private String mobile;
	
    /**
     *投资额度
     */
	private BigDecimal investAmt;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *更新日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateTime;
	
    /**
     *修改人
     */
	private String updater;
	
    /**
     *扩展字段1
     */
	private String extend1;
	
    /**
     *扩展字段2
     */
	private String extend2;
	
    /**
     *扩展字段3
     */
	private String extend3;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setHeadImage(String headImage){
		this.headImage = headImage;
	}
	
	public String getHeadImage(){
		return headImage;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setInvestAmt(BigDecimal investAmt){
		this.investAmt = investAmt;
	}
	
	public BigDecimal getInvestAmt(){
		return investAmt;
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
	
	public void setUpdater(String updater){
		this.updater = updater;
	}
	
	public String getUpdater(){
		return updater;
	}
	
	public void setExtend1(String extend1){
		this.extend1 = extend1;
	}
	
	public String getExtend1(){
		return extend1;
	}
	
	public void setExtend2(String extend2){
		this.extend2 = extend2;
	}
	
	public String getExtend2(){
		return extend2;
	}
	
	public void setExtend3(String extend3){
		this.extend3 = extend3;
	}
	
	public String getExtend3(){
		return extend3;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

