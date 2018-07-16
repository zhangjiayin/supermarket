package com.linkwee.web.model.acc;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 import java.lang.Long;
 import java.lang.String;
 import java.util.Date;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月26日 17:59:48
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class AcCityList implements Serializable {
	
	private static final long serialVersionUID = 2686603814836994277L;
	
    /**
     *自增ID
     */
	private Long id;
	
    /**
     *省份缩写
     */
	private String provinceId;
	
    /**
     *省份名称
     */
	private String provinceName;
	
    /**
     *城市代码表
     */
	private String cityId;
	
    /**
     *城市名字
     */
	private String cityName;
	
    /**
     *备注
     */
	private String remark;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createDate;
	
    /**
     *更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date updateDate;
	


	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setProvinceId(String provinceId){
		this.provinceId = provinceId;
	}
	
	public String getProvinceId(){
		return provinceId;
	}
	
	public void setProvinceName(String provinceName){
		this.provinceName = provinceName;
	}
	
	public String getProvinceName(){
		return provinceName;
	}
	
	public void setCityId(String cityId){
		this.cityId = cityId;
	}
	
	public String getCityId(){
		return cityId;
	}
	
	public void setCityName(String cityName){
		this.cityName = cityName;
	}
	
	public String getCityName(){
		return cityName;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	public Date getCreateDate(){
		return createDate;
	}
	
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	
	public Date getUpdateDate(){
		return updateDate;
	}
	
}

