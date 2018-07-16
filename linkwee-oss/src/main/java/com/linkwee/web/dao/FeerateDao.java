package com.linkwee.web.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.Feerate;


 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年10月28日 16:33:54
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface FeerateDao extends BasePageDao<Feerate>{
	
	/**
	 * 查询产品佣金利率
	 * @param productNumber 产品编码
	 * @return
	 */
	public Double queryProductFeeRate(@Param("productNumber") String productNumber);

	/**
	 * 查所有上级组织里面最低级的级别的佣金率
	 * @Auther ZhongLing
	 * @Date 2016年1月19日
	 * @param map
	 * @return
	 */
	public Double queryFeeRateByProductAnFancestor(HashMap<String, String> map);

	/**
	 * 根据理财师编号查理财师上级组织
	 * @Auther ZhongLing
	 * @Date 2016年1月19日
	 * @param saleNumber
	 * @return
	 */
	public HashMap<String, String> queryFancestorBySaleNumber(String saleNumber);
	/**
	 * 设置产品佣金率
	 * @param productId
	 * @param feeratio
	 */
	public void orgFeeRatio(@Param("productId")String productId,@Param("feeratio") Double feeratio,@Param("collectRatio") Double collectRatio);
	public String isExistByProId(String productId);
}
