package com.linkwee.web.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.web.dao.FeerateDao;
import com.linkwee.web.model.Feerate;
import com.linkwee.web.service.FeerateService;



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
@Service("feerateService")
public class FeerateServiceImpl implements FeerateService{
	
	@Autowired
	private FeerateDao feerateDao;
	
	/**
	 * 查询产品佣金利率
	 * @param productNumber 产品编码
	 * @return
	 */
	public Double queryProductFeeRate( String productNumber){
		return feerateDao.queryProductFeeRate(productNumber);
	}

	/**
	 * 根据产品id和理财师编码查佣金率
	 * @Auther ZhongLing
	 * @Date 2016年1月19日
	 * @param productId
	 * @param userId
	 * @return
	 */
	@Override
	public Double queryFeeRateByProductAndSale(String productId, String saleNumber) {
		//根据理财师number查所在组织和所有上级组织
		HashMap<String, String> fancestorMap = feerateDao.queryFancestorBySaleNumber(saleNumber);
		List<String> fancestorList = new ArrayList<String>();
		String fancestor = fancestorMap.get("fancestor");
		if(fancestor != null ) {
			fancestorList = Arrays.asList(fancestor.split("!"));
		}
		fancestorList.add(fancestorMap.get("department"));
		String fancestorStr = "";
		for(int i=0; i<fancestorList.size(); i++){
		fancestorStr = fancestorStr + "'" + fancestorList.get(i) + "',";
		}
		fancestorStr = fancestorStr.substring(0,fancestorStr.length());
		
		//查所有上级组织里面最低级的级别的佣金率
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("productId", productId);
		map.put("fancestorStr", fancestorStr);
		return feerateDao.queryFeeRateByProductAnFancestor(map);
		
	}

	@Override
	public Map<String, Double> queryProRatio(String orgNumber) {
		Map<String,Double> proRate = new HashMap<String,Double>();
		Feerate condit = new Feerate();
		condit.setOrgnumber(orgNumber);
		List<Feerate> rates = feerateDao.list(condit);
		for(Feerate item :rates){
			proRate.put(item.getProductnumber(), item.getFeeratio());
		}
		return proRate;
	}

}
