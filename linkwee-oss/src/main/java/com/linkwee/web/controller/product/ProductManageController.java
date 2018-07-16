package com.linkwee.web.controller.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.util.StringUtils;
import com.linkwee.product.service.ProductInfoService;
import com.linkwee.product.service.ProductManageService;
import com.linkwee.web.api.BaseController;
import com.linkwee.web.model.InvestorUserInfo;
import com.linkwee.web.model.product.ProductCate;
import com.linkwee.web.model.product.ProductDetailResp;
import com.linkwee.web.model.product.ProductInfoResp;
import com.linkwee.web.model.product.ProductManageRep;
import com.linkwee.web.model.product.ProductManageResp;
import com.linkwee.web.model.product.ProductProtocal;
import com.linkwee.web.model.product.ProductSaleDtlResp;
import com.linkwee.web.model.product.ProductSaleManageResp;
import com.linkwee.web.model.product.ProductType;
import com.linkwee.web.response.PageResponse;
import com.linkwee.web.service.CustomerCftRelFixWebService;
import com.linkwee.web.service.FeerateService;
import com.linkwee.web.service.InvestorUserInfoService;
import com.linkwee.web.util.ErrorCode;
import com.linkwee.web.util.RequestLogging;

@Controller
@RequestMapping(value = "product")
@RequestLogging("产品")
public class ProductManageController extends BaseController{
	
	@Resource
	private CustomerCftRelFixWebService customerCftRelFixService;
	@Resource
	private ProductManageService productManageService;
	@Resource
	private InvestorUserInfoService investorUserInfoService;
	@Resource
	private FeerateService feerateService;
	@Resource
	private ProductInfoService productInfoService;
	

	
	/**
	 * 产品列表
	 */
	@RequestMapping("toprolist")
	public String toinvestorlist(Model model){
		model.addAttribute("proCateList", productInfoService.queryAllPublicCates());
		return "product/productList";
	}
	@RequestMapping("prolist")
	@ResponseBody
	@RequestLogging("产品列表")
	public Object producPagetList(ProductManageRep productManageRep){

		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		PageResponse<ProductManageResp> response = new PageResponse<ProductManageResp>();
		try {
			
			PaginatorSevResp<ProductManageResp> resp = productManageService.queryProductPage(productManageRep);
			List<ProductManageResp> proList = resp.getDatas();
			Map<String,Double> proRateMap = feerateService.queryProRatio("99999999");
			//产品佣金
			if(proRateMap != null){
				for(ProductManageResp item : proList){
					item.setFeeRatio(proRateMap.get(item.getProductId()));
					//设置产品期限
					if(item.getDeadLineValue() ==null){
						StringBuffer proDays = new StringBuffer();
						proDays.append(item.getCollectLineMinValue() == null? "" : item.getCollectLineMinValue());
						proDays.append(item.getCollectLineMaxValue() == null ? "" : "~");
						proDays.append(item.getCollectLineMaxValue() == null ? "" : item.getCollectLineMaxValue());
						item.setProDays(proDays.toString());
					}else{
						switch(item.getDeadLineType())
						{
						case 1:item.setProDays(item.getDeadLineValue().toString());break;
						case 2:item.setProDays(String.valueOf(item.getDeadLineValue() * 30));break;
						case 3:item.setProDays(String.valueOf(item.getDeadLineValue() *360));break;
						default:item.setProDays(item.getDeadLineValue().toString()); break;
						}
					}
					//产品推荐
					StringBuffer proReBuffer = new StringBuffer();
					if(item.getIsRecommended() == 1){
						proReBuffer.append("首页");
					}
					if(StringUtils.isNotBlank(item.getListRecommended())){
						proReBuffer.append(" ").append(item.getListRecommended()).append(" ").append(item.getListSort());
					}
					item.setProRecomend(proReBuffer.toString());
					//产品销售状态--'1-预售、2-在售、3-售罄、4-下架、5-定时发售' 6-募集中、7-募集失败、8-募集成功
					switch(item.getSaleStatus())
					{
					case 1: item.setSaleStatusName("预售"); break;
					case 2:item.setSaleStatusName("在售");break;
					case 3:item.setSaleStatusName("售罄");break;
					case 4:item.setSaleStatusName("下架");break;
					case 5:item.setSaleStatusName("定时发售");break;
					case 6:item.setSaleStatusName("募集中");break;
					case 7:item.setSaleStatusName("募集失败");break;
					case 8:item.setSaleStatusName("募集成功");break;
					default:break;
					}
				}
			}
				response.setRows(proList);
				response.setTotal(resp.getTotalCount());
		} catch (Exception e) {
			response.setErrorcode(ErrorCode.SYSTEM_ERROR);
			e.printStackTrace();
			logsb.append("producPagetList|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("producPagetList|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return response;
	}
	
	/**
	 * 产品销售列表
	 */
	@RequestMapping("toprosalelist")
	public String toProSaleList(Model model){
		model.addAttribute("proCateList", productInfoService.queryAllPublicCates());
		return "product/productSaleList";
	}
	@RequestMapping("prosalelist")
	@ResponseBody
	@RequestLogging("产品销售列表")
	public Object producSalePagetList(ProductManageRep productManageRep){

		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		PageResponse<ProductSaleManageResp> response = new PageResponse<ProductSaleManageResp>();
		try {
			
			PaginatorSevResp<ProductSaleManageResp> resp = productManageService.queryProductSalePage(productManageRep);
			List<ProductSaleManageResp> list = resp.getDatas();
			List<Map<String,Object>> footer = new ArrayList<Map<String,Object>>();
			Map<String,Object> map = new HashMap<String,Object>();
			BigDecimal buyedTotalMoney = new BigDecimal(0);
			BigDecimal saleReward = new BigDecimal(0);
			for(ProductSaleManageResp item :list){
				if(item.getBuyedTotalMoney() != null){
				buyedTotalMoney = buyedTotalMoney.add(new BigDecimal(item.getBuyedTotalMoney()));
				}
				if(item.getSaleReward() != null){
				saleReward = saleReward.add(new BigDecimal(item.getSaleReward()));
				}
				int saleStatus = 0;
				if(item.getSaleStatus()!=null){
					saleStatus = item.getSaleStatus();
				}
				switch(saleStatus)
				{
				case 1: item.setSaleStatusName("预售"); break;
				case 2:item.setSaleStatusName("在售");break;
				case 3:item.setSaleStatusName("售罄");break;
				case 4:item.setSaleStatusName("下架");break;
				case 5:item.setSaleStatusName("定时发售");break;
				default:break;
				}
				
			}
			map.put("produtName", "<font color='red'>合计：</font>");
			map.put("buyedTotalMoney", buyedTotalMoney);
			map.put("saleReward", saleReward);
			footer.add(map);
			response.setRows(list);
			response.setTotal(resp.getTotalCount());
			response.setFooter(footer);

		} catch (Exception e) {
			e.printStackTrace();
			logsb.append("producSalePagetList|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("producSalePagetList|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return response;
	}
	
	/**
	 * 产品销售明细
	 */
	@RequestMapping("toprosaledtllist")
	public String toProSaleDtlList(String productId,Model model){
		
		//获取产品名称
		ProductInfoResp proInfoResp = productInfoService.getByProductId(productId);
		if(proInfoResp!=null){
			model.addAttribute("productName", proInfoResp.getProductName());
		}
		model.addAttribute("productId", productId);
		
		return "product/productSaleDtlList";
	}
	@RequestMapping("prosaledtllist")
	@ResponseBody
	@RequestLogging("产品销售明细列表")
	public Object proSaleDtlPageList(ProductManageRep productManageRep){

		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		PageResponse<ProductSaleDtlResp> response = new PageResponse<ProductSaleDtlResp>();
		if(StringUtils.isNotBlank(productManageRep.getBuyUserId())){
			//根据手机号和名称查找对应userId
			String buyUserId = investorUserInfoService.findUserIdByNameOrMobile(productManageRep.getBuyUserId());
			productManageRep.setBuyUserId(buyUserId);
		}
		if(StringUtils.isBlank(productManageRep.getSort())){
			productManageRep.setSort("buyedTime");
			productManageRep.setOrder("DESC");
		}
		try {
			
			PaginatorSevResp<ProductSaleDtlResp> resp = productManageService.queryProductSaleDtlPage(productManageRep);
			
			List<ProductSaleDtlResp> listProSaleDtl = resp.getDatas();
			List<String> userIds = new ArrayList<String>();
			for(ProductSaleDtlResp item:listProSaleDtl){
				userIds.add(item.getBuyUserId());
			}
			if(userIds!=null && userIds.size()>0){
			Map<String,InvestorUserInfo> userInfoMap = investorUserInfoService.getInvestUserInfoMap(userIds);
			for(ProductSaleDtlResp item:listProSaleDtl){
				InvestorUserInfo userInfo = userInfoMap.get(item.getBuyUserId());
				if(userInfo!=null){
				item.setBuyUserName(userInfo.getUserName());
				item.setBuyUserMobile(userInfo.getMobile());
				}
			}
			}
			response.setRows(resp.getDatas());
			response.setTotal(resp.getTotalCount());
			
		} catch (Exception e) {
			response.setErrorcode(ErrorCode.SYSTEM_ERROR);
			e.printStackTrace();
			logsb.append("producPagetList|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("producPagetList|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return response;
	}
	/**
	 * 产品详情 
	 */
	@RequestMapping("prodtl")
	@RequestLogging("产品详情")
	public String productDtl( String productId,Model model){
		
		Map<String,Double> proRateMap = feerateService.queryProRatio("99999999");
		ProductDetailResp productDetailResp = productManageService.findProductDetail(productId);
		//设置产品佣金
		if( proRateMap!= null){
		productDetailResp.setFeeRatio(proRateMap.get(productId));
		}
		model.addAttribute("product", productDetailResp);
		return "product/productDtl";
	}
	/**
	 * 添加产品
	 */
	@RequestMapping("productAdd")
	public String productAdd(Model model,String productCate){
		List<ProductCate> proCateList = productInfoService.queryAllPublicCates();
		model.addAttribute("proCateList", proCateList);
		queryAllHashNamesProtalcals(model);
		
		String view ="product/productAdd";
		if(StringUtils.isNotBlank(productCate)&& "1002".equals(productCate)){//浮动收益
			List<ProductType> proTypeList = productInfoService.queryAllProTypes();
			model.addAttribute("proTypeList", proTypeList);
			view = "product/productFloatAdd";
		}
		return view;
	}
	public void queryAllHashNamesProtalcals(Model model) {
		List<ProductProtocal> proProtocalList = productInfoService.queryAllHasNamesProtocals();
		model.addAttribute("proProtocalList", proProtocalList);
	}
	
/*	*//**
	 * 添加产品进行保存
	 *//*
	@RequestMapping("productAddSave")
	@ResponseBody
	public String productAddSave(ProductInfoResp productInfoResp,HttpServletRequest request){
		try {
			request.setCharacterEncoding("UTF-8");
			productInfoResp.setCreator("admin");
			productInfoResp.setCreateTime(new Date());
			productInfoResp.setUpdater("admin");
			productInfoResp.setUpdateTime(new Date());
			productInfoResp.setRemark("后台管理员添加新产品");
			productInfoResp.setProductId(UUID.randomUUID().toString());
			productService.addProduct(productInfoResp);
		} catch (Exception e) {
			logger.info("添加产品进行保存异常{}",e);
			return "error";
		}
		return "succeed";
	}*/
}
