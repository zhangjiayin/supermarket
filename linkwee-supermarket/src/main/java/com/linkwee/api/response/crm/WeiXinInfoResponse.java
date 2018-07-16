package com.linkwee.api.response.crm;

import java.util.ArrayList;
import java.util.List;

import com.linkwee.core.base.BaseEntity;

public class WeiXinInfoResponse extends BaseEntity {

	private static final long serialVersionUID = -1816148675364562127L;
	/**
	 * 用户微信openId
	 */
	private String openid;
	/**
	 * nickname
	 */
	private String nickname;

	/**
	 * sex
	 */
	private String sex;

	/**
	 * province
	 */
	private String province;

	/**
	 * city
	 */
	private String city;

	/**
	 * country
	 */
	private String country;

	/**
	 * headimgurl
	 */
	private String headimgurl;
	/**
	 * privilege
	 */
	private List<String> privilege = new ArrayList<String>();
	/**
	 * unionid
	 */
	private String unionid;


	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public List<String> getPrivilege() {
		return privilege;
	}

	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
