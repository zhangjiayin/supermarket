package com.linkwee.web.controller.product;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.base.ServiceResponse;
import com.linkwee.core.util.StringUtils;
import com.linkwee.product.service.ProductInfoService;
import com.linkwee.web.api.BaseController;
import com.linkwee.web.constant.ResponseConstant;
import com.linkwee.web.model.product.ProductCate;
import com.linkwee.web.model.product.ProductInfoResp;
import com.linkwee.web.model.product.ProductProtocal;
import com.linkwee.web.model.product.ProductType;
import com.linkwee.web.util.RequestLogging;

@Controller
@RequestMapping(value = "productos")
@RequestLogging("产品上下架")
public class ProductInfoController extends BaseController{
	
	@Resource
	private ProductInfoService productInfoService;

	@RequestMapping("allpubliccate")
	@ResponseBody
	@RequestLogging("产品分类")
	public Object queryAllPublicCates(){

		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		List<ProductCate> proCateList = new ArrayList<ProductCate>();
		try {
			proCateList = productInfoService.queryAllPublicCates();
		} catch (Exception e) {
			e.printStackTrace();
			logsb.append("queryAllPublicCates|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("queryAllPublicCates|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return proCateList;
	}
	/**
	 * 新增产品
	 * @param infoResp
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	@RequestLogging("新增产品")
	public Object producPagetList(@Valid ProductInfoResp infoResp,BindingResult result){
		ServiceResponse<String> srvResponse = null;
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult responseResult = null;
		if (result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            for(ObjectError error : errorList){
                System.out.println(error.getDefaultMessage());
            }
           return new ResponseResult(ResponseConstant.FAILURE, "输入参数错误");
        }
		
		try {
			
			srvResponse = productInfoService.addProduct(infoResp);
			 if (srvResponse.getHead().getCode() == 0) {
					responseResult = new ResponseResult(ResponseConstant.SUCCESS, "操作成功");
				} else {
					responseResult = new ResponseResult(ResponseConstant.FAILURE, srvResponse.getMessage());
				}
		} catch (Exception e) {
			
			e.printStackTrace();
			logsb.append("producPagetList|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("producPagetList|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return responseResult;
	}
	
	/**
	 * 发布
	 * @param productId
	 * @return
	 */
	@RequestMapping("release")
	@ResponseBody
	@RequestLogging("发布产品")
	public Object releasePro(@RequestParam String productId){

		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ServiceResponse<String> srvResponse = null;
		try {
			
			 srvResponse = productInfoService.releasePro(productId);
		} catch (Exception e) {
			
			e.printStackTrace();
			logsb.append("producPagetList|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("producPagetList|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return srvResponse;
	}
	/**
	 * 上下架
	 * @param productId
	 * @return
	 */
	@RequestMapping("onoffpro")
	@ResponseBody
	@RequestLogging("上下架产品")
	public Object onOffPro(@RequestParam String productId,@RequestParam int opType){

		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ServiceResponse<String> srvResponse = null;
		try {
			
			 srvResponse = productInfoService.onOrOffPro(productId, opType);
		} catch (Exception e) {
			
			e.printStackTrace();
			logsb.append("producPagetList|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("onOffPro|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return srvResponse;
	}
	
	/**
	 * 产品详情
	 */
	@RequestMapping("dtl")
	public String toProDtl(@RequestParam String productId,@RequestParam String opType,Model model){
		List<ProductCate> proCateList = productInfoService.queryAllPublicCates();
		model.addAttribute("proCateList", proCateList);
		queryAllHashNamesProtalcals(model);
		ProductInfoResp infoResp = productInfoService.findProDetail(productId);
		model.addAttribute("dtl", infoResp);
		String viewName = "";
		boolean isFloatPro = false;
		if(infoResp.getFixRate()==null){//浮动收益产品
			isFloatPro = true;
			List<ProductType> proTypeList = productInfoService.queryAllProTypes();
			model.addAttribute("proTypeList", proTypeList);
		}
		if("edit".equals(opType)){
			viewName = "product/productEdit";
			if(isFloatPro){
				viewName = "product/productFloatEdit";
			}
		}else if("update".equals(opType)){
			viewName = "product/productUpdate";
			if(isFloatPro){
				viewName = "product/productFloatUpdate";
			}
		}
		return viewName;
	}
	
	/**
	 * 修改产品
	 * @param infoResp
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	@RequestLogging("修改产品")
	public Object updatePor(@Valid ProductInfoResp infoResp,@Valid String opType, BindingResult result){
		if (result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            for(ObjectError error : errorList){
                System.out.println(error.getDefaultMessage());
            }
            return new ResponseResult(ResponseConstant.FAILURE, "输入参数错误");
        }
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult responseResult = null;
		try {
			
			ServiceResponse<String> srvResponse = productInfoService.updateProduct(infoResp,opType);
			if (srvResponse.getHead().getCode() == 0) {
				responseResult = new ResponseResult(ResponseConstant.SUCCESS, "操作成功");
			} else {
				responseResult = new ResponseResult(ResponseConstant.FAILURE, srvResponse.getMessage());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			logsb.append("producPagetList|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("producPagetList|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return responseResult;
	}
	/**
	 * 编辑产品
	 * @param infoResp
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	@RequestLogging("编辑产品")
	public Object editPor(ProductInfoResp infoResp,@Valid String opType,BindingResult result){
		/*if (result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            for(ObjectError error : errorList){
                System.out.println(error.getDefaultMessage());
            }
            return new ResponseResult(ResponseConstant.FAILURE, "输入参数错误");
        }*/
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult responseResult = null;
		try {
			
			ServiceResponse<String> srvResponse  = productInfoService.updateProduct(infoResp,opType);
			if (srvResponse.getHead().getCode() == 0) {
				responseResult = new ResponseResult(ResponseConstant.SUCCESS, "操作成功");
			} else {
				responseResult = new ResponseResult(ResponseConstant.FAILURE, srvResponse.getMessage());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			logsb.append("producPagetList|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("producPagetList|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return responseResult;
		
	}
	
	/**
	 * 添加产品
	 */
	@RequestMapping("productAdd")
	public String productAdd(Model model,String productCate){
		List<ProductCate> proCateList = productInfoService.queryAllPublicCates();
		model.addAttribute("proCateList", proCateList);
		String view ="product/productAdd";
		if(StringUtils.isNotBlank(productCate)&& "1002".equals(productCate)){//浮动收益
			List<ProductType> proTypeList = productInfoService.queryAllProTypes();
			model.addAttribute("proTypeList", proTypeList);
			view = "product/productFloatAdd";
		}
		System.out.println(view);
		return view;
	}
	/**
	 * 校验产品名称是否已经存在 validname
	 */
	@RequestMapping("validname")
	@RequestLogging("校验产品名称是否已经存在")
	@ResponseBody
	public Object isExistProName(String proName){
	  Boolean rlt = productInfoService.isExistProName(proName);
	  return rlt;
	}
	
	/**
	 * 手动设置为满标
	 * @param model
	 */
	@RequestMapping("over")
	@ResponseBody
	@RequestLogging("手动设置为满标")
	public Object setOver(@RequestParam String productId){

		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ServiceResponse<String> srvResponse = null;
		try {
			
			 srvResponse = productInfoService.setOver(productId);
		} catch (Exception e) {
			
			e.printStackTrace();
			logsb.append("setOver|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("setOver|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return srvResponse;
	}
	
	public void queryAllHashNamesProtalcals(Model model) {
		List<ProductProtocal> proProtocalList = productInfoService.queryAllHasNamesProtocals();
		model.addAttribute("proProtocalList", proProtocalList);
	}

}
