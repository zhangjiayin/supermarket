package com.linkwee.web.response;

public class CfpLevelDataStatisticsListResp {
	
	/**
	 * 月份
	 */
	private Integer month;

    /**
     *获销售佣金（人数）
     */
	private Integer xsyjStaticCount = 0;
	
    /**
     *获推荐奖励（人数）
     */
	private Integer tjjlStaticCount = 0;
	
    /**
     *获直接管理津贴（人数）
     */
	private Integer zjgljtStaticCount = 0;
	
    /**
     *获团队管理津贴（人数）
     */
	private Integer tdgljtStaticCount = 0;
	
    /**
     *见习人数
     */
	private Integer taStaticCount = 0;
	
    /**
     *顾问人数
     */
	private Integer sm1StaticCount = 0;
	
    /**
     *经理人数
     */
	private Integer sm2StaticCount = 0;
	
    /**
     *总监人数
     */
	private Integer sm3StaticCount = 0;

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getXsyjStaticCount() {
		return xsyjStaticCount;
	}

	public void setXsyjStaticCount(Integer xsyjStaticCount) {
		this.xsyjStaticCount = xsyjStaticCount;
	}

	public Integer getTjjlStaticCount() {
		return tjjlStaticCount;
	}

	public void setTjjlStaticCount(Integer tjjlStaticCount) {
		this.tjjlStaticCount = tjjlStaticCount;
	}

	public Integer getZjgljtStaticCount() {
		return zjgljtStaticCount;
	}

	public void setZjgljtStaticCount(Integer zjgljtStaticCount) {
		this.zjgljtStaticCount = zjgljtStaticCount;
	}

	public Integer getTdgljtStaticCount() {
		return tdgljtStaticCount;
	}

	public void setTdgljtStaticCount(Integer tdgljtStaticCount) {
		this.tdgljtStaticCount = tdgljtStaticCount;
	}

	public Integer getTaStaticCount() {
		return taStaticCount;
	}

	public void setTaStaticCount(Integer taStaticCount) {
		this.taStaticCount = taStaticCount;
	}

	public Integer getSm1StaticCount() {
		return sm1StaticCount;
	}

	public void setSm1StaticCount(Integer sm1StaticCount) {
		this.sm1StaticCount = sm1StaticCount;
	}

	public Integer getSm2StaticCount() {
		return sm2StaticCount;
	}

	public void setSm2StaticCount(Integer sm2StaticCount) {
		this.sm2StaticCount = sm2StaticCount;
	}

	public Integer getSm3StaticCount() {
		return sm3StaticCount;
	}

	public void setSm3StaticCount(Integer sm3StaticCount) {
		this.sm3StaticCount = sm3StaticCount;
	}
}
