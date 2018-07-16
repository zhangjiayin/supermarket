package com.linkwee.api.request.funds.ifast;

public class BatchGetFundRequest {

	//基金代码(如果指定基金代码（可多个，基金代码直接以逗号分隔，如fundCodes=482002,219003），则返回指定的基金的信息；如为空值，则返回全部。在【基金信息类API】——【批量获取基金信息】接口有返回给商户) 是否必须-否
	private String	fundCodes;							
											
	//是否可以购买(如果isBuyEnable=0，则返回所有可以购买的基金；如果isBuyEnable=1，则返回所有不可以购买的基金；如果为空值，则返回全部基金) 是否必须-否
	private String	isBuyEnable = "0";							
											
	//是否是QDII基金(如果isQDII=0，则返回所有QDII型基金；如果isQDII=1，则返回所有非QDII型基金；如果为空值，则返回全部基金) 是否必须-否
	private String	isQDII;							
											
	//是否是货币基金(如果isMMFund=0，则返回所有货币型基金；如果isMMFund=1，则返回所有非货币型基金；如果为空值，则返回全部基金) 是否必须-否
	private String	isMMFund;							
											
	//是否是推荐基金(如果isRecommended=0，则返回所有奕丰推荐的基金；如果isRecommended=1，则返回所有非奕丰推荐的基金；如果为空值，则返回全部基金) 是否必须-否
	private String	isRecommended;							
											
	//排序方式(DESC:降序，默认；ASC:升序) 是否必须-否
	private String	sort;
																		
	//排序字段（earningsPer10000：万份收益，nav：基金净值，month3：三月收益率，year1：1年收益率，sinceLaunch：成立以来收益率） 是否必须-否
	private String	period;							
											
	//地理分类搜索（支持多搜索，格式：geographicalSector=[china.onshore,sh.hk.connect...]） 是否必须-否
	private String	geographicalSector;							
											
	//行业分类搜索（支持多搜索，格式：specializeSector=[一般,金融...]） 是否必须-否
	private String	specializeSector;							
											
	//基金类型搜索（支持多搜索，格式：fundType=[MM,BOND...]） 是否必须-否
	private String	fundType;							
											
	//基金简称搜索（模糊搜索） 是否必须-否
	private String	shortName;
																		
	//基金公司代码（可以在【基金信息类api】-【find-fund-house-list】获取相关的信息） 是否必须-否
	private String	fundHouseCode;							
											
	//每页记录数 是否必须-否
	private String	pageSize = "10";
																		
	//页码 是否必须-否
	private String	pageIndex = "1";
	
	//基金检索
	private String search;

	public String getFundCodes() {
		return fundCodes;
	}

	public void setFundCodes(String fundCodes) {
		this.fundCodes = fundCodes;
	}

	public String getIsBuyEnable() {
		return isBuyEnable;
	}

	public void setIsBuyEnable(String isBuyEnable) {
		this.isBuyEnable = isBuyEnable;
	}

	public String getIsQDII() {
		return isQDII;
	}

	public void setIsQDII(String isQDII) {
		this.isQDII = isQDII;
	}

	public String getIsMMFund() {
		return isMMFund;
	}

	public void setIsMMFund(String isMMFund) {
		this.isMMFund = isMMFund;
	}

	public String getIsRecommended() {
		return isRecommended;
	}

	public void setIsRecommended(String isRecommended) {
		this.isRecommended = isRecommended;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getGeographicalSector() {
		return geographicalSector;
	}

	public void setGeographicalSector(String geographicalSector) {
		this.geographicalSector = geographicalSector;
	}

	public String getSpecializeSector() {
		return specializeSector;
	}

	public void setSpecializeSector(String specializeSector) {
		this.specializeSector = specializeSector;
	}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFundHouseCode() {
		return fundHouseCode;
	}

	public void setFundHouseCode(String fundHouseCode) {
		this.fundHouseCode = fundHouseCode;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
}
