package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimProduct;
import com.linkwee.web.request.ProductsListRequest;
import com.linkwee.web.request.ProductsSalesStatisticsRequest;
import com.linkwee.web.response.ProductDetailResponse;
import com.linkwee.web.service.CimProductService;
 /**
 * 
 * @描述： CimProductService服务接口
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年08月03日 10:03:21
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductService extends GenericService<CimProduct,Long>{

	/**
	 * 查询CimProduct列表,为data-tables封装
	 * @param productsListRequest 查询条件
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(ProductsListRequest productsListRequest,DataTable dataTable);

	/**
	 * 根据产品id查询产品详情
	 * @param productId
	 * @return
	 */
	ProductDetailResponse queryProductDetail(String productId);

	/**
	 * 查询产品销售统计  为data-tables封装
	 * @param productsSalesStatisticsRequest
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectSalesStatisticsByDatatables(ProductsSalesStatisticsRequest productsSalesStatisticsRequest);
}
