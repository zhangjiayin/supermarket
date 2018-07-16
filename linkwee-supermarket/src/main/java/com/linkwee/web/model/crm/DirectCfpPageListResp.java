package com.linkwee.web.model.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;

/**
 * 直属理财师团队
 * 
 * @Author chenjl
 * @Date 2017年02月27日 下午4:20:57
 */
public class DirectCfpPageListResp extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 头像
	 */
	private String headImage;
	/**
	 * 名字
	 */
	private String userName;
	/**
	 * 手机号
	 */
	private String mobile;

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


}
