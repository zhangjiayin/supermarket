package com.linkwee.api.response.crm;

import java.util.ArrayList;
import java.util.List;

import com.linkwee.core.base.BaseEntity;

/**
 * @描述：v4.0个人品牌推广
 */
public class BrandPromotionResponse extends BaseEntity {
	private static final long serialVersionUID = 3405963387464644389L;

	public BrandPromotionResponse() {

	}

	/**
	 * 二维码
	 */
	private String qrcode;
	
	/**
	 * 热点海报
	 */
	private String hotContent;
	
	/**
	 * 精品推荐内容
	 */
	private String recomContent;
	
	/**
	 * 热点海报图片 
	 */
	private List<BrandPromotionImageResponse> hotPosterList = new ArrayList<BrandPromotionImageResponse>();
	
	/**
	 * 精品推荐图片
	 */
	private List<BrandPromotionImageResponse> recommenList = new ArrayList<BrandPromotionImageResponse>();

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public List<BrandPromotionImageResponse> getHotPosterList() {
		return hotPosterList;
	}

	public void setHotPosterList(List<BrandPromotionImageResponse> hotPosterList) {
		this.hotPosterList = hotPosterList;
	}

	public List<BrandPromotionImageResponse> getRecommenList() {
		return recommenList;
	}

	public void setRecommenList(List<BrandPromotionImageResponse> recommenList) {
		this.recommenList = recommenList;
	}

	public String getHotContent() {
		return hotContent;
	}

	public void setHotContent(String hotContent) {
		this.hotContent = hotContent;
	}

	public String getRecomContent() {
		return recomContent;
	}

	public void setRecomContent(String recomContent) {
		this.recomContent = recomContent;
	}

}
