package com.linkwee.api.response.crm;

import java.util.List;

import com.linkwee.core.base.BaseEntity;

/**
 * @描述：切换背景
 */
public class ChooseBackgroundResponse extends BaseEntity {
	private static final long serialVersionUID = 3405963387464644389L;

	public ChooseBackgroundResponse() {

	}


	/**
	 * 类型
	 */
	private String type;
	
	private List<String> backgroundImg;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getBackgroundImg() {
		return backgroundImg;
	}

	public void setBackgroundImg(List<String> backgroundImg) {
		this.backgroundImg = backgroundImg;
	}
	
}
