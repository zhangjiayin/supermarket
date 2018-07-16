package com.linkwee.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CimProductMapper;
import com.linkwee.web.model.CimProduct;
import com.linkwee.web.request.ProductsListRequest;
import com.linkwee.web.request.ProductsSalesStatisticsRequest;
import com.linkwee.web.response.ProductDetailResponse;
import com.linkwee.web.response.ProductsListResponse;
import com.linkwee.web.response.ProductsSalesStatisticsResponse;
import com.linkwee.web.service.CimProductService;


 /**
 * 
 * @描述：CimProductService 服务实现类
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年08月03日 10:03:21
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimProductService")
public class CimProductServiceImpl extends GenericServiceImpl<CimProduct, Long> implements CimProductService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductServiceImpl.class);
	
	@Resource
	private CimProductMapper cimProductMapper;
	
	@Override
    public GenericDao<CimProduct, Long> getDao() {
        return cimProductMapper;
    }

	@Override
	public DataTableReturn selectByDatatables(ProductsListRequest productsListRequest, DataTable dataTable) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dataTable.getDraw()+1);
		LOGGER.debug(" -- CimProduct -- 排序和模糊查询 ");
		Page<ProductsListResponse> page = new Page<ProductsListResponse>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<ProductsListResponse> list = this.cimProductMapper.selectBySearchInfo(productsListRequest,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public ProductDetailResponse queryProductDetail(String productId) {
		ProductDetailResponse productDetailResponse = new ProductDetailResponse();
		//查询产品信息
		productDetailResponse = cimProductMapper.queryProductDetail(productId);
		LOGGER.debug("根据产品id查询产品详情:productDetailResponse={}",JSONObject.toJSONString(productDetailResponse));
		return productDetailResponse;
	}

	@Override
	public DataTableReturn selectSalesStatisticsByDatatables(ProductsSalesStatisticsRequest prodSSRequest) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(prodSSRequest.getDraw()+1);
		LOGGER.debug(" -- 查询产品销售统计 -- 排序和模糊查询 ");
		Page<ProductsSalesStatisticsResponse> page = new Page<ProductsSalesStatisticsResponse>(prodSSRequest.getStart()/prodSSRequest.getLength()+1,prodSSRequest.getLength());
		List<ProductsSalesStatisticsResponse> list = this.cimProductMapper.selectSalesStatisticsByDatatables(prodSSRequest,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
