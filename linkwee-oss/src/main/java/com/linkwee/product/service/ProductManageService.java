package com.linkwee.product.service;

import com.linkwee.core.base.ErrorCode;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.web.model.product.ProductDetailResp;
import com.linkwee.web.model.product.ProductManageRep;
import com.linkwee.web.model.product.ProductManageResp;
import com.linkwee.web.model.product.ProductSaleDtlResp;
import com.linkwee.web.model.product.ProductSaleManageResp;

/**
 * 理财产品
 * @Author ZhongLing
 * @Date 2015年12月29日 下午2:49:42
 */
public interface ProductManageService {
	public static enum Error implements ErrorCode{
	    INVEST_FAIL(145001, "购买失败，请重试"),
		REPAY_FAIL(145002, "回款失败");
		Error(int code, String message){
			this.code = code;
			this.message = message;
		}
		private int code = 0;
		private String message = "";
		public int getCode() {
			return code;
		}
		public String getMessage() {
			return message;
		}
	}
	
	/**
	 * 分页查询产品
	 * @Auther ZhongLing
	 * @Date 2015年12月25日
	 * @return
	 */
	public PaginatorSevResp<ProductManageResp> queryProductPage(ProductManageRep request);
	/**
	 * 产品销售分页查询
	 * @param request
	 * @return
	 */
	public PaginatorSevResp<ProductSaleManageResp> queryProductSalePage(ProductManageRep request);
	
	/**
	 * 产品销售详细分页查询
	 * @param request
	 * @return
	 */
	public PaginatorSevResp<ProductSaleDtlResp> queryProductSaleDtlPage(ProductManageRep request);
	/**
	 * 产品详情
	 * @param productId
	 * @return
	 */
	public ProductDetailResp findProductDetail(String productId);

	
}
