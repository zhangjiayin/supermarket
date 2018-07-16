package com.linkwee.web.model;

public class CimInsuranceRecommendName extends CimInsuranceRecommend{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     *推荐种类名称
     */
	private String recommendTypeName;

	public String getRecommendTypeName() {
		return recommendTypeName;
	}

	public void setRecommendTypeName(String recommendTypeName) {
		this.recommendTypeName = recommendTypeName;
	}
}
