package com.linkwee.api.response.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.xoss.util.RandomTextCreator;
import com.linkwee.xoss.util.WebUtil;

/**
 * 团队成员销售记录
 * 
 * @Author chenjl
 * @Date 2017年2月27日 下午4:20:57
 */
public class DirectCfpPageListResponse extends BaseEntity {
	private static final long serialVersionUID = -5418637411733755341L;

	public DirectCfpPageListResponse() {

	}

	public DirectCfpPageListResponse(CrmCfplanner obj) {
		WebUtil.initObj(this, obj);
		this.setHeadImage(obj.getHeadImage());
		this.setUserName(obj.getUserName());
		this.setMobile(RandomTextCreator.encrypTion(obj.getMobile()));
	}

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
	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
