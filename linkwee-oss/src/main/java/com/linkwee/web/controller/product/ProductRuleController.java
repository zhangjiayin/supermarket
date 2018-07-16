package com.linkwee.web.controller.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.base.ServiceResponse;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.product.service.ProductInfoService;
import com.linkwee.product.service.ProductRuleService;
import com.linkwee.web.api.BaseController;
import com.linkwee.web.constant.ResponseConstant;
import com.linkwee.web.model.product.ProductRule;
import com.linkwee.web.model.product.ProductType;
import com.linkwee.web.util.RequestLogging;

@Controller
@RequestMapping(value = "prorule")
@RequestLogging("浮动产品收益规则")
public class ProductRuleController extends BaseController{
	
	@Resource
	private ProductRuleService  productRuleService;
	@Resource
	private ProductInfoService productInfoService;

	/**
	 * 新增收益模板
	 * @param infoResp
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	@RequestLogging("新增产品")
	public Object saveProfitModel(String jsonStr,String productTypeName){
		ServiceResponse<String> srvResponse = null;
		
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult responseResult = null;
		try {
			if(StringUtils.isBlank(jsonStr) || StringUtils.isBlank(productTypeName)){
				responseResult = new ResponseResult(ResponseConstant.FAILURE, "模板名称和规则不能为空");
			}
			List<ProductRule> list = new ArrayList<ProductRule>();
			list = JsonUtils.fromJsonToObject(jsonStr, List.class, ProductRule.class); 
			srvResponse = productRuleService.addProfitModel(list, productTypeName);
			 if (srvResponse.getHead().getCode() == 0) {
					responseResult = new ResponseResult(ResponseConstant.SUCCESS, "操作成功");
				} else {
					responseResult = new ResponseResult(ResponseConstant.FAILURE, srvResponse.getMessage());
				}
		} catch (Exception e) {
			responseResult = new ResponseResult(ResponseConstant.FAILURE, "操作失败");
			e.printStackTrace();
			logsb.append("saveProfitModel|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("saveProfitModel|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return responseResult;
	}
	
	@RequestMapping("loadtype")
	@ResponseBody
	@RequestLogging("加载模板")
	public Object loadType(){
		List<ProductType> proTypeList = productInfoService.queryAllProTypes();
		return proTypeList;
	}
	
	/**
	 * 根据选择的收益模板 返回产品对应期限天数
	 * @return
	 */
	@RequestMapping("loaddays")
	@ResponseBody
	@RequestLogging("获取模板对应期限")
	public Object loadDays(String typeId){
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("linMinValue", 0);
		retMap.put("linMaxValue", 0);
		if(StringUtils.isNotBlank(typeId)){
			Map<String,Object> mapTemp = productInfoService.queryProDaysByTypeId(Integer.parseInt(typeId));
			if(mapTemp!=null){
				retMap.put("linMinValue", mapTemp.get("linMinValue"));
				retMap.put("linMaxValue", mapTemp.get("linMaxValue"));
			}
			
		}
		return new ResponseResult(true, "成功返回", retMap);
	}
	/**
	 * 根据类型ID 返回详细浮动规则信息
	 * @return
	 */
	@RequestMapping("loadruledtl")
	@ResponseBody
	@RequestLogging("加载模板")
	public Object loadTypeRuleDtl(String typeId){
		if(StringUtils.isNotBlank(typeId)){
			List<ProductRule> proRuleList = productInfoService.queryProRuleDteByTypeId(Integer.parseInt(typeId));
			return proRuleList;
		}else{
			return null;
		}
	}
	

}
