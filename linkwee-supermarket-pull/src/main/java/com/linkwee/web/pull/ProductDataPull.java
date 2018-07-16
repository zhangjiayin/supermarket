package com.linkwee.web.pull;

import com.linkwee.web.model.CimProductAddPull;

public interface ProductDataPull{
	/**
	 * 拉取产品 在售
	 * @param orgNumber  机构编码   为null时查询所有机构
	 * @param startTime  开始时间   为null时为当前时间减1天
	 * @param endTime    结束时间  为null时为当前时间
	 */
	void pullProductCurrentSale(String orgNumber,String startTime,String endTime);
	
	/**
	 * 根据第三方产品id  拉取产品   无论产品当前是否在售
	 */
	CimProductAddPull pullProductById(String orgNumber,String thirdProductId);
}
