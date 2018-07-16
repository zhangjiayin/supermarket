package com.linkwee.web.service;

import java.util.Map;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年10月28日 16:33:54
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface FeerateService{

	
	/**
	 * 查询产品佣金利率
	 * @param productNumber 产品编码
	 * @return
	 */
	public Double queryProductFeeRate( String productNumber);
	
	/**
	 * 根据产品id和理财师编号查佣金率
	 * @Auther ZhongLing
	 * @Date 2016年1月19日
	 * @param productId
	 * @param userId
	 * @return
	 */
	public Double queryFeeRateByProductAndSale( String productId, String saleNumber);
	
	public Map<String,Double> queryProRatio(String orgNumber);

}
