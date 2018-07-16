package com.linkwee.api.response.crm;

import java.util.ArrayList;
import java.util.List;

import com.linkwee.core.base.BaseEntity;

/**
 * @描述：v4.5.3推广海报
 */
public class BrandPostersResponse extends BaseEntity {
	private static final long serialVersionUID = 3405963387464644389L;

	public BrandPostersResponse() {

	}

	/**
	 * 二维码
	 */
	private String qrcode;
	
	/**
	 * 热点海报图片 
	 */
	private List<BrandPromotionImageResponse> posterList = new ArrayList<BrandPromotionImageResponse>();
	

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public List<BrandPromotionImageResponse> getPosterList() {
		return posterList;
	}

	public void setPosterList(List<BrandPromotionImageResponse> posterList) {
		this.posterList = posterList;
	}


}
