package com.linkwee.api.response;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.web.model.SmGrowthHandbook;

public class GrowthHandbookClassifyListResponse extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6597102153991387810L;

	/**
	 * banner图片
	 */
	private String bannerImg;
	
	/**
	 * 成长手册列表
	 */
	private PaginatorResponse<SmGrowthHandbook> growthHandbookList;

	public String getBannerImg() {
		return bannerImg;
	}

	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}

	public PaginatorResponse<SmGrowthHandbook> getGrowthHandbookList() {
		return growthHandbookList;
	}

	public void setGrowthHandbookList(
			PaginatorResponse<SmGrowthHandbook> growthHandbookList) {
		this.growthHandbookList = growthHandbookList;
	}
	
}
