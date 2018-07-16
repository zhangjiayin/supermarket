package com.linkwee.test.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.linkwee.act.redpacket.model.ActRedpacketBriefDetailAndRule;
import com.linkwee.api.request.cim.ProductAddfeeTicketRequest;
import com.linkwee.api.request.cim.ProductClassifyPageListRequest;
import com.linkwee.api.request.cim.ProductInvestRequest;
import com.linkwee.api.request.cim.ProductPageList4Request;
import com.linkwee.api.request.cim.ProductRecommendByChooseRequest;
import com.linkwee.api.request.cim.ProductRedPacketRequest;
import com.linkwee.api.request.cim.ProductScreenRequest;
import com.linkwee.api.request.cim.ProductStatisticsRequest;
import com.linkwee.api.request.cim.SelectedProductsListRequest;
import com.linkwee.api.request.tc.ProfitCalculateRequest;
import com.linkwee.api.response.cim.ProductDetailResponse;
import com.linkwee.api.response.cim.ProductPageList4Response;
import com.linkwee.api.response.cim.ProductPageListResponse;
import com.linkwee.api.response.cim.ProductRecommendChooseResponse;
import com.linkwee.api.response.cim.ProductStatisticsPreferenceResponse;
import com.linkwee.api.response.cim.ProductStatisticsResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.model.CimProductExtends;
import com.linkwee.web.model.acc.ActPersonAddfeeTicketExtends;

public class ProductControllerTest extends BaseTest{
	
	/**
	 * 首页-热门产品
	 * @throws Exception
	 */
	@Test
	public void testHotProduct() throws Exception {
		PaginatorRequest pageRequest = new PaginatorRequest();
		pageRequest.setPageIndex(1);
		pageRequest.setPageSize(100);
		Map<String, String> parameterMap = new HashMap<String, String>();
//		parameterMap.put("orgCode", "7777777");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/hotProduct",this.getToken(),ProductPageListResponse.class,pageRequest,parameterMap);
		LOGGER.debug(baseResponse.toString());
	}
		
	@Test
	public void profitCalculate() throws Exception {
		ProfitCalculateRequest calculateRequest =  new ProfitCalculateRequest();
		calculateRequest.setProductId("6D677262A92B4AE7A03049148EFA146E");
		calculateRequest.setAmount(50000d);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/profitCalculate",this.getToken(),BaseResponse.class,calculateRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 理财-产品列表
	 * @throws Exception
	 */
	@Test
	public void testProductPageList() throws Exception {
		PaginatorRequest pageRequest = new PaginatorRequest();
		pageRequest.setPageIndex(1);
		pageRequest.setPageSize(20);
		pageRequest.setOrder(0);
		pageRequest.setSort(4);
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("orgCode", "OPEN_YINCHENGPAI_WEB");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productPageList",this.getToken(),ProductPageListResponse.class,pageRequest,parameterMap);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 理财-产品分类列表
	 * cateId 801-理财师推荐产品  901-首投标  902-复投标      剩下按照产品分类表（tcim_product_cate）对应   如：1-热门产品  2-新手产品
	 * 投呗:  		产品类型有 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品  801-理财师推荐产品 
	 * 猎才大师:		产品类型有 2-新手产品 901-首投标  902-复投标
	 * @throws Exception
	 */
	@Test
	public void testproductClassifyPageList() throws Exception {
		ProductClassifyPageListRequest productCfyPgListRequest = new ProductClassifyPageListRequest();
		productCfyPgListRequest.setPageIndex(1);
		productCfyPgListRequest.setPageSize(20);
		productCfyPgListRequest.setOrder(0);
		productCfyPgListRequest.setSort(4);
//		productCfyPgListRequest.setOrgCode("OPEN_XIAONIUZAIXIAN_WEB");
		productCfyPgListRequest.setCateId(2);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/product/productClassifyPageList",this.getToken(),ProductPageListResponse.class,productCfyPgListRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testproductClassifyPageList460() throws Exception {
		ProductClassifyPageListRequest productCfyPgListRequest = new ProductClassifyPageListRequest();
		productCfyPgListRequest.setPageIndex(1);
		productCfyPgListRequest.setPageSize(10);
		productCfyPgListRequest.setOrder(0);//0-升序 1-降序
		productCfyPgListRequest.setSort(2);//1-年化收益 2-产品期限
//		productCfyPgListRequest.setCateId(3);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productClassifyPageList/4.6.0",this.getToken(),ProductPageListResponse.class,productCfyPgListRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 产品分类统计（根据产品分类表分类 ）
	 * cateId 801-理财师推荐产品  901-首投标  902-复投标      剩下按照产品分类表（tcim_product_cate）对应   如：1-热门产品  2-新手产品
	 * 投呗:  		产品类型有 2-新手产品  3-短期产品  4-高收益产品  5-稳健收益产品  801-理财师推荐产品 
	 * 猎才大师:		产品类型有 2-新手产品 901-首投标  902-复投标
	 * @throws Exception
	 */
	@Test
	public void testproductClassifyStatistics() throws Exception {
		ProductStatisticsRequest productStatisticsRequest = new ProductStatisticsRequest();
//		productStatisticsRequest.setCateIdList("801");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WEB,this.getUrl(PathEnum.LOCALHOST),"/api/product/productClassifyStatistics",this.getToken(),ProductStatisticsResponse.class,productStatisticsRequest);
		LOGGER.info(baseResponse.toString());
	}
	
	/**
	 * 理财-产品详情
	 * @throws Exception
	 */
	@Test
	public void testProductDetail() throws Exception {
		Map<String, String> parameterMap =  new HashMap<String, String>();
		parameterMap.put("productId", "1DEFEF114C004B829CAF55A1BD41BCA9");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productDetail",this.getToken(),ProductDetailResponse.class,parameterMap);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 理财-产品分享
	 * @throws Exception
	 */
	@Test
	public void testProductShare() throws Exception {
		Map<String, String> parameterMap =  new HashMap<String, String>();
		parameterMap.put("productId", "6D677262A92B4AE7A03049148EFA146E");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/share",this.getToken(),CimProductExtends.class,parameterMap);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 理财-产品推荐
	 * @throws Exception
	 */
	@Test
	public void testProductRecommend() throws Exception {
		Map<String, String> parameterMap =  new HashMap<String, String>();
		parameterMap.put("productId", "6D677262A92B4AE7A03049148EFA146E");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/recommend",this.getToken(),null,parameterMap);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 理财-产品推荐取消
	 * @throws Exception
	 */
	@Test
	public void testProductCancelRecommend() throws Exception {
		Map<String, String> parameterMap =  new HashMap<String, String>();
		parameterMap.put("productId", "6D677262A92B4AE7A03049148EFA146E");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/cancelRecommend",this.getToken(),null,parameterMap);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 理财-产品推荐选择列表
	 * @throws Exception
	 */
	@Test
	public void testrecommendChooseList() throws Exception {
		Map<String, String> parameterMap =  new HashMap<String, String>();
		parameterMap.put("productId", "6D677262A92B4AE7A03049148EFA146E");
//		parameterMap.put("searchValue", "成国峰");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/recommendChooseList",this.getToken(),ProductRecommendChooseResponse.class,parameterMap);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 理财-产品选择推荐
	 * @throws Exception
	 */
	@Test
	public void testrecommendByChoose() throws Exception {
		ProductRecommendByChooseRequest productRecommendByChooseRequest =  new ProductRecommendByChooseRequest();
		productRecommendByChooseRequest.setProductId("6D677262A92B4AE7A03049148EFA146E");
		productRecommendByChooseRequest.setUserIdString("822b71784d6f497cb891626fac538a14,5c32ce9e27ef4750b5828ef45c5a8d64");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/recommendByChoose",this.getToken(),null,productRecommendByChooseRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 理财师推荐的产品列表
	 * @throws Exception
	 */
	@Test
	public void testRecdProductPageList() throws Exception {
		PaginatorRequest pageRequest = new PaginatorRequest();
		pageRequest.setPageIndex(1);
		pageRequest.setPageSize(5);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/recdProductPageList",this.getToken(),ProductPageListResponse.class,pageRequest);
		LOGGER.info(baseResponse.toString());
	}
	
	/**
	 * 产品筛选条件
	 * @throws Exception
	 */
	@Test
	public void testproductHead() throws Exception {
		ProductScreenRequest productScreenRequest = new ProductScreenRequest();
		//productScreenRequest.setSecurityLevel(">=4");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productHead",this.getToken(),Map.class,productScreenRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 查询筛选产品
	 * @throws Exception
	 */
	@Test
	public void testProductScreenPageList() throws Exception {
		ProductScreenRequest pageRequest = new ProductScreenRequest();
		/*pageRequest.setBackground("民营");
		pageRequest.setPlatform("OPEN_XIAONIUZAIXIAN_WEB");
		pageRequest.setPageIndex(1);
		pageRequest.setPageSize(20);
		pageRequest.setProductDeadLine("30,60");
		pageRequest.setYearProfit("3,9");*/
		//pageRequest.setSecurityLevel(">=4");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/screenPageList",this.getToken(),ProductPageListResponse.class,pageRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 产品投资列表
	 * @throws Exception
	 */
	@Test
	public void testProductInvestList() throws Exception {
		ProductInvestRequest pageRequest =new ProductInvestRequest();
		pageRequest.setProductId("6D677262A92B4AE7A03049148EFA146E");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productInvestList",this.getToken(),ProductPageListResponse.class,pageRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	/**
	 * 全部标的
	 * @throws Exception
	 */
	@Test
	public void testProductTypeList() throws Exception {
		ProductClassifyPageListRequest productCfyPgListRequest = new ProductClassifyPageListRequest();
		productCfyPgListRequest.setPageIndex(1);
		productCfyPgListRequest.setPageSize(20);
		productCfyPgListRequest.setOrder(0);
		productCfyPgListRequest.setSort(4);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productTypeList/2.0.1",this.getToken(),ProductStatisticsResponse.class,productCfyPgListRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testProductClassifyStatistics201() throws Exception {
		ProductClassifyPageListRequest productCfyPgListRequest = new ProductClassifyPageListRequest();
		productCfyPgListRequest.setPageIndex(1);
		productCfyPgListRequest.setPageSize(20);
		productCfyPgListRequest.setOrder(0);
		productCfyPgListRequest.setSort(4);
//		productCfyPgListRequest.setOrgCode("OPEN_XIAONIUZAIXIAN_WEB");
		productCfyPgListRequest.setCateId(2);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productClassifyStatistics/2.0.1",this.getToken(),ProductStatisticsResponse.class,productCfyPgListRequest);
		LOGGER.debug(baseResponse.toString());
	}
	/**
	 * 产品分类统计(根据产品表进行产品分类统计)
	 * cateId 1-短期(3个月以内) 2-中期(4-6个月) 3-长期(7个月以上)
	 * @param productStatisticsRequest
	 * @return
	 */
	@Test
	public void testProductClassifyStatistics460() throws Exception {
		ProductClassifyPageListRequest productCfyPgListRequest = new ProductClassifyPageListRequest();
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.PRERELEASE),"/api/product/productClassifyStatistics/4.6.0",this.getToken(),ProductStatisticsResponse.class,productCfyPgListRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testProductClassifyPageList201() throws Exception {
		ProductClassifyPageListRequest productCfyPgListRequest = new ProductClassifyPageListRequest();
		productCfyPgListRequest.setPageIndex(1);
		productCfyPgListRequest.setPageSize(20);
		productCfyPgListRequest.setOrder(0);
		productCfyPgListRequest.setSort(4);
//		productCfyPgListRequest.setOrgCode("OPEN_XIAONIUZAIXIAN_WEB");
		productCfyPgListRequest.setCateId(3);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productClassifyPageList/2.0.1",this.getToken(),ProductPageListResponse.class,productCfyPgListRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testProductClassifyPreference() throws Exception {
		ProductStatisticsRequest productStatisticsRequest = new ProductStatisticsRequest();
		//productStatisticsRequest.setCateIdList("3,4,5");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productClassifyPreference/2.0.1",this.getToken(),ProductStatisticsPreferenceResponse.class,productStatisticsRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testHotRecommendProductListTop() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/hotRecommendProductListTop/2.0.1",this.getToken(),ProductPageListResponse.class);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testProductPageList4() throws Exception {
		ProductPageList4Request productPageList4Request = new ProductPageList4Request();
//		productPageList4Request.setDeadlineValue("dLa");
//		productPageList4Request.setFlowRate("fRa");
//		productPageList4Request.setIfRookie(0);
//		productPageList4Request.setOrder(0);
//		productPageList4Request.setOrgCode("OPEN_JIUFUQINGZHOU_WEB");
		productPageList4Request.setPageIndex(1);
		productPageList4Request.setPageSize(40);
		productPageList4Request.setSort(1);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_WECHAT,this.getUrl(PathEnum.LOCALHOST),"/api/product/productPageList/4.0",this.getToken(),ProductPageList4Response.class,productPageList4Request);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void testProductPageListStatistics4() throws Exception {
		ProductPageList4Request productPageList4Request = new ProductPageList4Request();
		productPageList4Request.setDeadlineValue("dLb");
		productPageList4Request.setFlowRate("fRa");
		productPageList4Request.setIfRookie(1);
		//productPageList4Request.setOrgCode("OPEN_DONGFANGHUI_WEB");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productPageListStatistics/4.0",this.getToken(),Integer.class,productPageList4Request);
		LOGGER.info(baseResponse.toString());
	}
	
	@Test
	public void testSelectedProducts() throws Exception {
		Map<String, String> parameterMap =  new HashMap<String, String>();
		parameterMap.put("limitNumber", "10");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/selectedProducts/4.0.0",this.getToken(),ProductPageList4Response.class,parameterMap);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void testSelectedProductsList() throws Exception {
		SelectedProductsListRequest pageRequest = new SelectedProductsListRequest();
		pageRequest.setPageIndex(1);
		pageRequest.setPageSize(100);
		pageRequest.setCateId(1);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/selectedProductsList/4.1.1",this.getToken(),ProductPageList4Response.class,pageRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	@Test
	public void productRedPacketTest() throws Exception {
		ProductRedPacketRequest productRedPacketRequest = new ProductRedPacketRequest();
		productRedPacketRequest.setProductId("64F162FBA3DF48C7B8401DA8EF303B3A");
		//productRedPacketRequest.setBuyTotal(new BigDecimal(20000));
		//productRedPacketRequest.setDeadLineValue(20);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productRedPacket",this.getToken(),ActRedpacketBriefDetailAndRule.class,productRedPacketRequest);
		LOGGER.debug(baseResponse.toString());
	}
	
	
	@Test
	public void productAddfeeTicketTest() throws Exception {
		ProductAddfeeTicketRequest productAddfeeTicketRequest = new ProductAddfeeTicketRequest();
		productAddfeeTicketRequest.setProductId("64F162FBA3DF48C7B8401DA8EF303B3A");
		//productRedPacketRequest.setBuyTotal(new BigDecimal(20000));
		//productRedPacketRequest.setDeadLineValue(20);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/product/productAddfeeTicket",this.getToken(),ActPersonAddfeeTicketExtends.class,productAddfeeTicketRequest);
		LOGGER.debug(baseResponse.toString());
	}
}
