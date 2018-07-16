package com.linkwee.product.dao;

import java.util.List;

import com.linkwee.core.base.BaseDao;
import com.linkwee.web.model.product.ProductDetailResp;
import com.linkwee.web.model.product.ProductManageRep;
import com.linkwee.web.model.product.ProductManageResp;
import com.linkwee.web.model.product.ProductSaleDtlResp;
import com.linkwee.web.model.product.ProductSaleManageResp;

public interface ProductManageDao extends BaseDao {
	/**
	 * 产品信息分页
	 * @param productManageRep
	 * @return
	 */
	public Integer productListPageCount(ProductManageRep productManageRep);
	public List<ProductManageResp> productListPage(ProductManageRep productManageRep);
	/**
	 * 产品销售分页查询
	 * @param productManageRep
	 * @return
	 */
	public Integer productSalePageCount(ProductManageRep productManageRep);
	public List<ProductSaleManageResp> productSaleListPage(ProductManageRep productManageRep);
	
	/**
	 * 产品销售详细
	 * @param productManageRep
	 * @return
	 */
	public Integer proSaleDtlCount(ProductManageRep productManageRep);
	public List<ProductSaleDtlResp> proSaleDtlPageList(ProductManageRep productManageRep);
	
	/**
	 * 产品详情
	 */
	public ProductDetailResp findProductDetail(String productId);
}