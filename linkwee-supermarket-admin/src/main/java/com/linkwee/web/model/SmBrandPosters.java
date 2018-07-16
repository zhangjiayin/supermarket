package com.linkwee.web.model;

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
 * @创建时间：2018年01月06日 15:13:33
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class SmBrandPosters implements Serializable {
	
	private static final long serialVersionUID = 1917579460938622177L;
	
    /**
     *自增长ID
     */
	private Integer id;
	
    /**
     *海报类型:1:推荐2:正能量3:理念4:节日
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
     *缩略图
     */
	private String smallImage;
	
    /**
     *显示排序
     */
	private Integer showInx;
	
    /**
     *创建者
     */
	private String creator;
	
    /**
     *生效时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8") 
	private Date validBegin;
	
    /**
     *结束时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date validEnd;
	
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
     *备注
     */
	private String remark;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setTypeValue(String typeValue){
		this.typeValue = typeValue;
	}
	
	public String getTypeValue(){
		return typeValue;
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
	
	public void setSmallImage(String smallImage){
		this.smallImage = smallImage;
	}
	
	public String getSmallImage(){
		return smallImage;
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
	
	public void setValidBegin(Date validBegin){
		this.validBegin = validBegin;
	}
	
	public Date getValidBegin(){
		return validBegin;
	}
	
	public void setValidEnd(Date validEnd){
		this.validEnd = validEnd;
	}
	
	public Date getValidEnd(){
		return validEnd;
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
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
}

