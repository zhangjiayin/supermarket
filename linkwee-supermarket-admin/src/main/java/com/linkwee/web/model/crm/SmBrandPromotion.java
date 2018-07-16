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
 * @创建时间：2017年06月28日 18:47:51
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SmBrandPromotion implements Serializable {
	
	private static final long serialVersionUID = 2236883319163881492L;
	
    /**
     *自增长ID
     */
	private Integer id;
	
    /**
     *1:推荐理财师2:邀请客户
     */
	private String appType;
	
    /**
     *1:个人名片2:热点海报3:精品推荐
     */
	private String typeValue;
	
    /**
     *状态:0发布,1删除
     */
	private Integer status;
	
    /**
     *图片
     */
	private String image;
	
    /**
     *显示排序
     */
	private Integer showInx;
	
    /**
     *创建者
     */
	private String creator;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date modifiyTime;
	
    /**
     *生效时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private String validBegin;
	
    /**
     *结束时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private String validEnd;
	
    /**
     *备注
     */
	private String remark;
	
	 /**
     *1是海报2是名片
     */
	private String useType;
	
	/**
     *缩略图 
     */
	private String smallImage;


	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void setImage(String image){
		this.image = image;
	}
	
	public String getImage(){
		return image;
	}
	
	public void setShowInx(Integer showInx){
		this.showInx = showInx;
	}
	
	public Integer getShowInx(){
		return showInx;
	}
	
	public void setCreator(String creator){
		this.creator = creator;
	}
	
	public String getCreator(){
		return creator;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setModifiyTime(Date modifiyTime){
		this.modifiyTime = modifiyTime;
	}
	
	public Date getModifiyTime(){
		return modifiyTime;
	}
	
	public String getValidBegin() {
		return validBegin;
	}

	public void setValidBegin(String validBegin) {
		this.validBegin = validBegin;
	}

	public String getValidEnd() {
		return validEnd;
	}

	public void setValidEnd(String validEnd) {
		this.validEnd = validEnd;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
}

