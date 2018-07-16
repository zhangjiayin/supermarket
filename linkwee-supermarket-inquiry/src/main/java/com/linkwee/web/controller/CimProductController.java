package com.linkwee.web.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.request.ProductsListRequest;
import com.linkwee.web.request.ProductsSalesStatisticsRequest;
import com.linkwee.web.response.ProductDetailResponse;
import com.linkwee.web.service.CimProductService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： CimProductController控制器
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年08月03日 10:03:21
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/cim/cimproduct")
@RequestLogging("CimProductController控制器")
public class CimProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductController.class);
	
	@Resource
	private CimProductService cimProductService;

    /**
     * 查看列表
     */
    @RequestMapping(value="/list",   method=RequestMethod.GET)
    @RequestLogging("查看列表页面")
    public String cimProduct(Model model) {
    	return "cimProduct/cimproduct-list";
    }

    /**
     * 产品列表datatables<br>
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("产品列表")
	public DataTableReturn getCimProductsList(ProductsListRequest productsListRequest, DataTable dataTable) {
    	//设置机构编码
    	Session session =  SecurityUtils.getSubject().getSession();
    	CimOrginfo cimOrginfo = (CimOrginfo)session.getAttribute("userInfo");
    	productsListRequest.setOrgNumber(cimOrginfo.getOrgNumber());
		DataTableReturn tableReturn = cimProductService.selectByDatatables(productsListRequest,dataTable);
		return tableReturn;
	}
    
    @RequestMapping(value="/detail", method = RequestMethod.GET)
    @RequestLogging("产品详情")
	public String getCimProductsDetail(String productId,Model model) {
		LOGGER.info("根据产品id查询产品详情, productId={}", productId);
		ProductDetailResponse productDetailResponse = new ProductDetailResponse();
		productDetailResponse = cimProductService.queryProductDetail(productId);
		model.addAttribute("productDT", productDetailResponse);
		return "cimProduct/cimproduct-detail";
	}
    
    /**
     * 产品销售
     */
    @RequestMapping(value="/salesStatistics",   method=RequestMethod.GET)
    @RequestLogging("查看产品销售列表")
    public String cimProductSalesStatistics(Model model) {
    	return "cimProduct/cimproduct-salesStatistics";
    }
    
    /**
     * 产品销售页面 datatables<br>
     * @return
     */
    @RequestMapping(value="/salesStatistics", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("产品销售页面数据")
    public DataTableReturn getSalesStatistics(@RequestBody ProductsSalesStatisticsRequest ProductsSalesStatisticsRequest){
    	//设置机构编码
    	Session session =  SecurityUtils.getSubject().getSession();
    	CimOrginfo cimOrginfo = (CimOrginfo)session.getAttribute("userInfo");
    	ProductsSalesStatisticsRequest.setOrgNumber(cimOrginfo.getOrgNumber());
    	ProductsSalesStatisticsRequest.initOrdersOriginal();
		DataTableReturn tableReturn = cimProductService.selectSalesStatisticsByDatatables(ProductsSalesStatisticsRequest);
		return tableReturn;
    }
    
	/**
	 * 转换器
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}
