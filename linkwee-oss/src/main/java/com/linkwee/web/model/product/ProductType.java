package com.linkwee.web.model.product;

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
 * @创建时间：2016年06月20日 10:24:49
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class ProductType extends BaseEntity {
	
	private static final long serialVersionUID = -3028385363972254974L;
	
    /**
     *自增长主键
     */
	private Integer id;
	
    /**
     *产品类型名称
     */
	private String typeName;
	
    /**
     *产品类型描述
     */
	private String typeDesc;
	
    /**
     *产品类型业务方式(1=并购式|2=产业式 )
     */
	private Byte businessType;
	
    /**
     *管理模式(1=资管|2=公募|3=私募(有限合伙/契约性基金)|4=信托) 
     */
	private Byte manageWay;
	
    /**
     *组成内容(1=股权|2=债权|3=债转股|4=股转债|5=其它) 
     */
	private Byte compriseContentWay;
	
    /**
     *是否自动派息(1=自动|2=手动|3=用户赎回) 
     */
	private Byte isAuto;
	
    /**
     *产品类型(1=天添牛|2=指数牛|3=活期宝 | 4= 惠房宝|5=日益宝)
     */
	private Integer typeValue;
	
    /**
     *1=正常|2=已删除
     */
	private Integer delStatus;
	
    /**
     *创建者
     */
	private String creator;
	
    /**
     *创建时间
     */
	private Date createTime;
	
    /**
     *最后一次更新者
     */
	private String updater;
	
    /**
     *最后一次更新时间
     */
	private Date updateTime;
	
    /**
     *更新操作说明
     */
	private String remark;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setTypeName(String typeName){
		this.typeName = typeName;
	}
	
	public String getTypeName(){
		return typeName;
	}
	
	public void setTypeDesc(String typeDesc){
		this.typeDesc = typeDesc;
	}
	
	public String getTypeDesc(){
		return typeDesc;
	}
	
	public void setBusinessType(Byte businessType){
		this.businessType = businessType;
	}
	
	public Byte getBusinessType(){
		return businessType;
	}
	
	public void setManageWay(Byte manageWay){
		this.manageWay = manageWay;
	}
	
	public Byte getManageWay(){
		return manageWay;
	}
	
	public void setCompriseContentWay(Byte compriseContentWay){
		this.compriseContentWay = compriseContentWay;
	}
	
	public Byte getCompriseContentWay(){
		return compriseContentWay;
	}
	
	public void setIsAuto(Byte isAuto){
		this.isAuto = isAuto;
	}
	
	public Byte getIsAuto(){
		return isAuto;
	}
	
	public void setTypeValue(Integer typeValue){
		this.typeValue = typeValue;
	}
	
	public Integer getTypeValue(){
		return typeValue;
	}
	
	public void setDelStatus(Integer delStatus){
		this.delStatus = delStatus;
	}
	
	public Integer getDelStatus(){
		return delStatus;
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
	
	public void setUpdater(String updater){
		this.updater = updater;
	}
	
	public String getUpdater(){
		return updater;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
	

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

