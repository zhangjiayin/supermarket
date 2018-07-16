package com.linkwee.web.model.crm;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Integer;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年03月06日 16:38:51
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CimLeaderTree implements Serializable {
	
	private static final long serialVersionUID = 338192445420672474L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *根节点用户
     */
	private String rootId;
	
    /**
     *当前用户ID
     */
	private String userId;
	
    /**
     *当前用户理财师user_id
     */
	private String cfplanner;
	
    /**
     *是否有独立核算0=否1=是
     */
	private Integer isOnlypay;
	
    /**
     *是否直接下级理财师0=否1=是
     */
	private Integer isDirect;
	
    /**
     *树是否成立0=否1=是
     */
	private Integer isTree;
	
    /**
     *用户ID下面的数据是否核算到此树0=否1=是
     */
	private Integer isCalc;
	
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
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setRootId(String rootId){
		this.rootId = rootId;
	}
	
	public String getRootId(){
		return rootId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setCfplanner(String cfplanner){
		this.cfplanner = cfplanner;
	}
	
	public String getCfplanner(){
		return cfplanner;
	}
	
	public void setIsOnlypay(Integer isOnlypay){
		this.isOnlypay = isOnlypay;
	}
	
	public Integer getIsOnlypay(){
		return isOnlypay;
	}
	
	public void setIsDirect(Integer isDirect){
		this.isDirect = isDirect;
	}
	
	public Integer getIsDirect(){
		return isDirect;
	}
	
	public void setIsTree(Integer isTree){
		this.isTree = isTree;
	}
	
	public Integer getIsTree(){
		return isTree;
	}
	
	public void setIsCalc(Integer isCalc){
		this.isCalc = isCalc;
	}
	
	public Integer getIsCalc(){
		return isCalc;
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
	
}

