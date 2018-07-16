package com.linkwee.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.web.model.product.ProductInfo;
import com.linkwee.web.model.product.ProductInfoResp;

 /**
 * 	
 * 描述： Dao接口
 * 
 * @创建人： chenchy
 * 
 * @创建时间：2016年05月26日 17:01:46
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface ProductInfoDao extends BasePageDao<ProductInfo>{
	public ProductInfoResp getByProductId(String productId);
	public Integer findByProName(String productName);
	public void updateIndexRecomended();
	public Integer updateProProtocal(ProductInfo productInfo);
	
	List<ProductInfo> queryDataTableProductList(@Param("dt")DataTable dt,RowBounds page);
	Double queryBuyTotalByProId(String productId);
	public Integer setOver(@Param("productId")String productId,@Param("userName")String userName,@Param("showIndex")int showIndex);
	Integer queryMinShowIndexByProId(@Param("productId")String productId); 
	List<ProductInfo> queryProductStatusList();
	public void updateStatusInfo(ProductInfo productInfo);
}
