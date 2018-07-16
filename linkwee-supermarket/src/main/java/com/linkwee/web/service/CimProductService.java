package com.linkwee.web.service;

import java.math.BigDecimal;
import java.util.List;

import com.linkwee.api.request.cim.HotProductRequest;
import com.linkwee.api.request.cim.ProductClassifyPageListRequest;
import com.linkwee.api.request.cim.ProductDetailRequest;
import com.linkwee.api.request.cim.ProductInvestRequest;
import com.linkwee.api.request.cim.ProductPageList4Request;
import com.linkwee.api.request.cim.ProductPageListRecommendRequest;
import com.linkwee.api.request.cim.ProductPageListRequest;
import com.linkwee.api.request.cim.ProductRecommendByChooseRequest;
import com.linkwee.api.request.cim.ProductRecommendChooseRequest;
import com.linkwee.api.request.cim.ProductStatisticsRequest;
import com.linkwee.api.request.cim.ScreenProductPageListRequest;
import com.linkwee.api.request.cim.SelectedProductsListRequest;
import com.linkwee.api.response.cim.ProductDetailResponse;
import com.linkwee.api.response.cim.ProductInvestResponse;
import com.linkwee.api.response.cim.ProductPageList4Response;
import com.linkwee.api.response.cim.ProductPageListResponse;
import com.linkwee.api.response.cim.ProductRecommendChooseResponse;
import com.linkwee.api.response.cim.ProductStatistics460Response;
import com.linkwee.api.response.cim.ProductStatisticsPreferenceResponse;
import com.linkwee.api.response.cim.ProductStatisticsResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.openapi.request.OmsProductRequest;
import com.linkwee.openapi.request.ProductPushRequest;
import com.linkwee.openapi.response.OmsProductResponse;
import com.linkwee.web.model.CimProduct;
import com.linkwee.web.request.ProductListDataRequest;
import com.linkwee.web.request.ProductSaleDetailRequest;
import com.linkwee.web.request.ProductSaleListRequest;
import com.linkwee.web.request.ProductsSalesStatisticsRequest;
import com.linkwee.web.response.ProductDetailForManageResponse;
import com.linkwee.web.response.ProductSaleDetailResponse;
import com.linkwee.web.response.ProductsSalesStatisticsResponse;
import com.linkwee.web.response.act.ProductPageResponse;
import com.linkwee.web.response.orgInfo.OrgSaleProductResponse;
import com.linkwee.xoss.api.AppRequestHead;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月14日 18:23:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductService extends GenericService<CimProduct,Long>{

	/**
	 * 查询CimProduct列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 查询热门产品分页信息
	 * @param userId
	 * @param page
	 * @return
	 */
	PaginatorResponse<ProductPageListResponse> queryHotProduct(HotProductRequest hotProductRequest,Page<ProductPageListResponse> page);
		
	/**
	 * 理财-产品列表 带排序
	 * @param userId
	 * @param page
	 * @return
	 */
	PaginatorResponse<ProductPageListResponse> queryProductPageList(ProductPageListRequest productPageListRequest,Page<ProductPageListResponse> page);
	
	/**
	 * 理财-理财师推荐产品列表 带排序
	 * @param productPageListRequest
	 * @param page
	 * @return
	 */
	PaginatorResponse<ProductPageListResponse> queryRecdProductPageList(ProductPageListRecommendRequest productPageListRecommendRequest,Page<ProductPageListResponse> page);
	
	/**
	 * 查询机构在售产品列表
	 * @param orgNumber 机构编码
	 * @param page 分页信息
	 * @return
	 */
	PaginatorResponse<OrgSaleProductResponse> queryOrgSaleProducts(String orgNumber,Page<OrgSaleProductResponse> page);
	
	/**
	 * 查询产品详情
	 * @param productDetailRequest
	 * @return
	 */
	ProductDetailResponse queryProductDetail(ProductDetailRequest productDetailRequest);
	
	/**
	 * 根据产品id查询产品详情	   无理财师是否推荐信息  有产品标签
	 * @param productId
	 * @param appKind
	 * @return
	 */
	ProductDetailResponse queryProductDetail(String productId,String appKind);
	
	/**
	 * 根据产品id查询产品详情	   无理财师是否推荐信息   无产品标签
	 * @param productId
	 * @param appKind
	 * @return
	 */
	ProductDetailResponse queryProductDetail(String productId);
	
	/**
	 * 获取浮动产品
	 * @return
	 */
	List<CimProduct> getFlowProducts();
	
	/**
	 * 产品筛选查询
	 * @param productPageListRequest
	 * @param page
	 * @return
	 */
	PaginatorResponse<ProductPageListResponse> queryProductScreenPageList(ScreenProductPageListRequest productPageListRequest, Page<ProductPageListResponse> page);
	
	/**
	 * 根据条件查询产品
	 * @param orgNumber
	 * @param proName
	 * @param page
	 * @return
	 */
	
	List<ProductPageResponse> queryProductByProductName(String orgNumber,String proName, Page<ProductPageResponse> page);
	
	/**
	 * 根据条件查询产品
	 * @param pids
	 * @return
	 */
	List<ProductPageResponse> queryProductByProductIds(String[] pids);
	

	/**
	 * 后台管理系统查询产品列表
	 * @param productListDataRequest
	 * @return
	 */
	DataTableReturn selectProductListForManage(ProductListDataRequest productListDataRequest);
	
	/**
	 * 根据产品id查询产品详情  管理后台
	 * @param productId
	 * @return
	 */
	ProductDetailForManageResponse queryProductDetailForManerge(String productId);

   /**
    * 产品审核
    * @param auditType  审核类型  partAudit-部分审核  allAudit-全部审核
    * @param auditCode  审核code  0-审核通过  1-审核未通过
    * @param productTableIdList  待审核的产品表主键id列   格式 1,2,3,4
    * @return
    */
	void productAudit(String auditType, Integer auditCode,String productTableIdList);

	/**
	 * 后台管理系统-查询产品销售列表
	 * @param productSaleListRequest
	 * @return
	 */
    DataTableReturn selectProductSaleListForManage(ProductSaleListRequest productSaleListRequest);

    /**
     * 后台管理系统-查询产品销售详情
     * @param productSaleDetailRequest
     * @return
     */
	List<ProductSaleDetailResponse> selectProductSaleDetail(ProductSaleDetailRequest productSaleDetailRequest);
	/**
	 * 后台管理系统-查询产品销售统计  为data-tables封装
	 * @param productsSalesStatisticsRequest
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectSalesStatisticsByDatatables(ProductsSalesStatisticsRequest productsSalesStatisticsRequest);
	
	/**
	 * 查询产品销售统计
	 * @param productsSalesStatisticsRequest
	 * @param page
	 * @return
	 */
	List<ProductsSalesStatisticsResponse> selectSalesStatistics(ProductsSalesStatisticsRequest productsSalesStatisticsRequest);
	
	/**
     * PC-查询产品销售详情 分页
     * @param getProductInvestList
     * @return
     */
	PaginatorResponse<ProductInvestResponse> getProductInvestList(ProductInvestRequest productInvestRequest,Page<ProductInvestResponse> page);

	/**
	 * 查询产品分类统计(根据产品标签分类)
	 * @param productStatisticsRequest
	 * @return
	 */
	List<ProductStatisticsResponse> productClassifyStatistics(AppRequestHead appRequestHead,ProductStatisticsRequest productStatisticsRequest);
	
	/**
	 * 理财-产品分类列表 带排序
	 * @param head
	 * @param productCfyPgListRequest
	 * @return
	 */
	PaginatorResponse<ProductPageListResponse> queryProductClassifyPageList(AppRequestHead head,ProductClassifyPageListRequest productCfyPgListRequest);
	
	/**
	 * 根据推送的产品信息对该产品自动进行产品分类
	 * @param cimProduct
	 */
	void autoHandleProductCateForPush(String productId,ProductPushRequest productPushRequest);
	
	/**
	 * 根据机构代码更改产品的佣金
	 * @param orgNumber 机构代码
	 * @param feeRatio 佣金
	 * @return
	 */
    int updateFeeRatioByOrgNumber(String orgNumber,BigDecimal feeRatio);

    /**
     * 产品推荐选择列表
     * @param head
     * @param productRecommendChooseRequest
     * @return
     */
	ProductRecommendChooseResponse recommendChooseList(AppRequestHead head,ProductRecommendChooseRequest productRecommendChooseRequest);

	/**
	 * 产品选择推荐
	 * @param head
	 * @param productRecommendByChooseRequest
	 */
	void recommendByChoose(AppRequestHead head,ProductRecommendByChooseRequest productRecommendByChooseRequest);

	/**
	 * 查询产品分类统计2.0.1(根据产品标签分类)
	 * @param productStatisticsRequest
	 * @return
	 */
	List<ProductStatisticsResponse> productClassifyStatistics201(AppRequestHead appRequestHead,ProductStatisticsRequest productStatisticsRequest);
	
	/**
	 * 查询产品分类统计4.6.0
	 * @param productStatisticsRequest
	 * @return
	 */
	List<ProductStatistics460Response> productClassifyStatistics460(AppRequestHead appRequestHead,ProductStatisticsRequest productStatisticsRequest);

	/**
	 * 理财-产品分类列表 带排序
	 * @param head
	 * @param productCfyPgListRequest
	 * @return
	 */
	PaginatorResponse<ProductPageListResponse> queryProductClassifyPageList201(AppRequestHead appRequestHead,ProductClassifyPageListRequest productCfyPgListRequest);

	/**
	 * 产品分类优选
	 * @param appRequestHead
	 * @param productStatisticsRequest
	 * @return
	 */
	List<ProductStatisticsPreferenceResponse> productClassifyPreference(AppRequestHead appRequestHead,ProductStatisticsRequest productStatisticsRequest);

	/**
	 * 全部标的列表
	 * @param appRequestHead
	 * @param productStatisticsRequest
	 * @return
	 */
	List<ProductStatisticsResponse> productTypeList(AppRequestHead appRequestHead,ProductStatisticsRequest productStatisticsRequest);

	/**
	 * 自动设置管理员进行产品编辑信息
	 * @param cimProductNew
	 */
	void autoProductEdit(CimProduct cimProductNew);

	/**
	 *  热推产品列表TOP10
	 * @param appRequestHead
	 * @param productCfyPgListRequest
	 * @return
	 */
	List<ProductPageListResponse> queryProductClassifyPageListTop(AppRequestHead appRequestHead);

	/**
	 * 产品列表4.0
	 * @param appRequestHead
	 * @param productPageList4Request
	 * @return
	 */
	PaginatorResponse<ProductPageList4Response> queryProductPageList4(AppRequestHead appRequestHead,ProductPageList4Request productPageList4Request);

	/**
	 * 产品列表4.6.0
	 * @param appRequestHead
	 * @param productPageList4Request
	 * @return
	 */
	PaginatorResponse<ProductPageList4Response> queryProductPageList460(AppRequestHead appRequestHead,ProductPageList4Request productPageList4Request);

	
	/**
	 * 产品列表统计4.0
	 * @param appRequestHead
	 * @param productPageList4Request
	 * @return
	 */
	Integer productPageListStatistics4(AppRequestHead appRequestHead,ProductPageList4Request productPageList4Request);

	/**
	 * 猎财大师首页精选产品
	 * @param productPageListRequest 
	 * @return
	 */
	List<ProductPageList4Response> querySelectedProducts(AppRequestHead head,ProductPageListRequest productPageListRequest);

	/**
	 * 精选产品列表
	 * @param selectedProductsListRequest
	 * @return
	 */
	PaginatorResponse<ProductPageList4Response> querySelectedProductsList(AppRequestHead head,SelectedProductsListRequest selectedProductsListRequest);

	/**
	 * 开放平台接入领会理财产品列表
	 * @param omsProductRequest
	 * @return
	 */
	PaginatorResponse<OmsProductResponse> queryOmsProductList(OmsProductRequest omsProductRequest);
}
