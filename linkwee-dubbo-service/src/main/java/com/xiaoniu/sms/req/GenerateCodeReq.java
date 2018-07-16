package com.xiaoniu.sms.req;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * 生成验证码 参数
 * @author 颜彩云
 *
 */
public class GenerateCodeReq extends BaseReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3143446461951029234L;
	private int type = 0;// 0：数字验证码，其他值为  混合验证码
	private int count = 4;// 验证码位数默认为4位
	private String mobile;// 手机号
	private int send = 0;// 默认为发送（0），其他值为不发送
	private int expire = 15;//默认为15分钟
	private int updateCache = 0;//0为默认不更新，其它值为强制更新
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getSend() {
		return send;
	}
	public void setSend(int send) {
		this.send = send;
	}
	public int getExpire() {
		return expire;
	}
	public void setExpire(int expire) {
		this.expire = expire;
	}
	public int getUpdateCache() {
		return updateCache;
	}
	public void setUpdateCache(int updateCache) {
		this.updateCache = updateCache;
	}
	
	public boolean check(){
		if (StringUtils.isBlank(mobile)) {
			return false;
		}
		if (StringUtils.isBlank(super.getPartnerId())) {
			return false;
		}
		if (StringUtils.isBlank(super.getModuleId())) {
			return false;
		}
		return true;
	}
	
}
