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
 * @创建人： Hxb
 * 
 * @创建时间：2018年04月24日 16:51:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class ActLaborDayChangeFaceRecord implements Serializable {
	
	private static final long serialVersionUID = 2029309152848900961L;
	
    /**
     *自增ID
     */
	private Integer id;
	
    /**
     *头像图片
     */
	private String headImage;
	
    /**
     *微信昵称
     */
	private String weixinNickname;
	
    /**
     *openid
     */
	private String openid;
	
    /**
     *创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	
    /**
     *最后修改时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date lastUpdateTime;
	


	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setHeadImage(String headImage){
		this.headImage = headImage;
	}
	
	public String getHeadImage(){
		return headImage;
	}
	
	public void setWeixinNickname(String weixinNickname){
		this.weixinNickname = weixinNickname;
	}
	
	public String getWeixinNickname(){
		return weixinNickname;
	}
	
	public void setOpenid(String openid){
		this.openid = openid;
	}
	
	public String getOpenid(){
		return openid;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setLastUpdateTime(Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public Date getLastUpdateTime(){
		return lastUpdateTime;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

