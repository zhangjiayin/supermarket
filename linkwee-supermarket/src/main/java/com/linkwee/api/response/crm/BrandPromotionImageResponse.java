package com.linkwee.api.response.crm;

import com.linkwee.core.base.BaseEntity;

/**
 * @描述：v4.0个人品牌推广
 */
public class BrandPromotionImageResponse extends BaseEntity {
	private static final long serialVersionUID = 3405963387464644389L;

	public BrandPromotionImageResponse() {

	}
	
	/**
	 * 图片
	 */
	private String image;
	
	
	/**
     *缩略图 
     */
	private String smallImage;
	
	
	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
}
