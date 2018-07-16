package com.linkwee.product.service;

import java.util.List;
import java.util.Map;

import com.linkwee.core.base.ServiceResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.model.product.ProductCate;
import com.linkwee.web.model.product.ProductInfoResp;
import com.linkwee.web.model.product.ProductProtocal;
import com.linkwee.web.model.product.ProductRule;
import com.linkwee.web.model.product.ProductType;

/**
 * 用户 业务 接口
 * 
 * @author Mignet
 * @since 2014年7月5日 上午11:53:33
 **/
public interface ProductInfoService {
	
	public static final String open_linkUrl ="http://minvestor.xiaoniuapp.com/pages/manage_finances/product_detail.html";
	public static final String buy_succeed_returnUrl ="http://minvestor.xiaoniuapp.com/pages/manage_finances/proBuySucc.html";
	public static final String open_lcs_linkUrl ="http://mchannel.xiaoniuapp.com/pages/manage_finances/product_detail.html";

	
	public static final String open_linkUrl_collect ="http://minvestor.xiaoniuapp.com/pages/manage_finances/increasingly_treasure.html";
	public static final String open_lcs_linkUrl_collect ="http://mchannel.xiaoniuapp.com/pages/manage_finances/increasingly_treasure.html"; 
	
	
	public List<ProductCate> queryAllPublicCates();
	public List<ProductType> queryAllProTypes();
	public ServiceResponse<String>  addProduct(ProductInfoResp infoResp);
	public ServiceResponse<String> releasePro(String productId);
	public ServiceResponse<String> onOrOffPro(String productId,int opType);
	public ServiceResponse<String>  updateProduct(ProductInfoResp infoResp,String opType);
	public ServiceResponse<String>  editProduct(ProductInfoResp infoResp);
	public ProductInfoResp findProDetail(String fid);
	public Boolean isExistProName(String proName);
	public ProductInfoResp getByProductId(String productId);
	public Map<String,Object> queryProDaysByTypeId(Integer productTypeId);
	public List<ProductRule> queryProRuleDteByTypeId(Integer productTypeId);
	public List<ProductProtocal> queryAllHasNamesProtocals();
	
	public DataTableReturn queryDataTableProductList(DataTable dt);
	public ServiceResponse<String> setOver(String productId);
	//产品定时下架
	public ServiceResponse<Boolean> proAutoOff();
	
}
