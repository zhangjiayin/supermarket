package com.linkwee.openplatform.xyb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linkwee.core.util.DateUtils;
import com.linkwee.openplatform.xyb.helper.HttpRequestClientXyb;
import com.linkwee.openplatform.xyb.model.Productxyb;
import com.linkwee.web.enums.OrgEnum;
import com.linkwee.web.model.CimProductAddPull;
import com.linkwee.web.model.SysThirdkeyConfigPull;
import com.linkwee.web.pull.AbstractProductDataHandle;
import com.linkwee.web.response.ProductDataPullReturn;
import com.linkwee.web.service.SysThirdkeyConfigPullService;
import com.openpltsdk.xyb.entity.Index;
import com.openpltsdk.xyb.entity.QueryReq;
import com.openpltsdk.xyb.entity.ServiceData;
import com.openpltsdk.xyb.entity.ServiceEnums;
import com.openpltsdk.xyb.entity.TimeRange;

@Component
public class XYBProductDateHandle extends AbstractProductDataHandle{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(XYBProductDateHandle.class);
	
	@Resource
	private HttpRequestClientXyb httpRequestClientXyb;
	
	@Resource
	private SysThirdkeyConfigPullService sysThirdkeyConfigPullService;
	
	private OrgEnum orgEnum = OrgEnum.OPEN_XINYONGBAO_WEB;
	
	public List<ProductDataPullReturn> getProductCurrentSale(String orgNumber,String startTime,String endTime){
		
		List<ProductDataPullReturn> productDataPullReturnList = new ArrayList<ProductDataPullReturn>();
		
		/* 加密 */
		Gson gson = new Gson();
		QueryReq qr = new QueryReq();
		TimeRange timeRange = new TimeRange();
		timeRange.setStartTime(StringUtils.isNotBlank(startTime)?startTime:DateUtils.format(DateUtils.subDay(new Date(), 15)));//查询15天内就行，超过15天，直接当售罄处理  已和信用宝沟通
		timeRange.setEndTime(StringUtils.isNotBlank(endTime)?endTime:DateUtils.format(new Date()));
		qr.setDateRange(timeRange);
		Index index = new Index();
		qr.setIndex(index);
		ServiceData requestServiceData = new ServiceData(ServiceEnums.queryBids.getServiceName(), gson.toJsonTree(qr));
		
		List<Productxyb> productxybList = new ArrayList<Productxyb>();
		List<CimProductAddPull> cimProductAddPullList = new ArrayList<CimProductAddPull>();
		
		//发起http请求
		SysThirdkeyConfigPull sysThirdkeyConfigPull = new SysThirdkeyConfigPull();
		sysThirdkeyConfigPull.setCooperationStandard(1);//没有遵循文档合作规范
		if(StringUtils.isBlank(orgNumber) || orgNumber.endsWith(orgEnum.getOrgNumber())){
			sysThirdkeyConfigPull.setOrgNumber(orgEnum.getOrgNumber());
		} else {			
			return productDataPullReturnList;
		}		
		sysThirdkeyConfigPull.setOrgStatus("y");
		sysThirdkeyConfigPull.setProductStatus("y");
		sysThirdkeyConfigPull = sysThirdkeyConfigPullService.selectOne(sysThirdkeyConfigPull);
		if(sysThirdkeyConfigPull != null){
			ServiceData returnServiceData = null;
			try {
				LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【信用宝】拉取在售产品>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				returnServiceData = httpRequestClientXyb.invokePostHttp(sysThirdkeyConfigPull.getOrgPullProductUrl(), requestServiceData, null);
				productxybList = gson.fromJson(returnServiceData.getBody(), new TypeToken<List<Productxyb>>(){}.getType());
				LOGGER.info("==================【orgNumber={}】产品请求结果返回List================== data={}",orgEnum.getOrgNumber(),JSONObject.toJSONString(productxybList));
				cimProductAddPullList = productDatadAdapter(productxybList);
			} catch (Throwable e) {
				LOGGER.error("【信用宝】拉取在售产品异常", e);
			}
			ProductDataPullReturn productxybRDPR = new ProductDataPullReturn();
			productxybRDPR.setSysThirdkeyConfigPull(sysThirdkeyConfigPull);
			productxybRDPR.setCimProductAddPullList(cimProductAddPullList);
			productDataPullReturnList.add(productxybRDPR);
		}
		
		return productDataPullReturnList;
	}
	
	public CimProductAddPull getProductById(String orgNumber,String thirdProductId){
		CimProductAddPull cimProductAddPull =  null;
		try {	
			/*加密*/
			Gson gson = new Gson();
			QueryReq qr = new QueryReq();
			//设置查询时间范围
			TimeRange timeRange = new TimeRange();
			qr.setDateRange(timeRange);
			//设置查询条件
			Index index = new Index();
			index.setName("id");
			index.setVals(new String[]{thirdProductId});
			qr.setIndex(index);
			ServiceData requestServiceData = new ServiceData(ServiceEnums.queryBids.getServiceName(), gson.toJsonTree(qr));
			
			//发起http请求
			LOGGER.info("+++++++++++++++++++++++++++++++++++++【信用宝,thirdProductId={}】根据产品id拉取产品+++++++++++++++++++++++++++++++++++++",thirdProductId);
			SysThirdkeyConfigPull sysThirdkeyConfigPull = new SysThirdkeyConfigPull();
			sysThirdkeyConfigPull.setOrgNumber(orgNumber);
			sysThirdkeyConfigPull.setOrgStatus("y");
			sysThirdkeyConfigPull = sysThirdkeyConfigPullService.selectOne(sysThirdkeyConfigPull);
			
			List<Productxyb> productxybList = new ArrayList<Productxyb>();
			if(sysThirdkeyConfigPull != null){		
				ServiceData returnServiceData= httpRequestClientXyb.invokePostHttp(sysThirdkeyConfigPull.getOrgPullProductUrl(),requestServiceData,null);
				productxybList = gson.fromJson(returnServiceData.getBody(), new TypeToken<List<Productxyb>>(){}.getType());
				LOGGER.info("+++++++++++++++++++++++++++++++++++++【信用宝,thirdProductId={}】根据产品id拉取产品请求结果返回List+++++++++++++++++++++++++++++++++++++ data={}",thirdProductId,JSONObject.toJSONString(productxybList));
			}
			
			List<CimProductAddPull> cimProductAddPullList = productDatadAdapter(productxybList);
			
			//返回结果转化成可用的产品对象
			if(CollectionUtils.isNotEmpty(cimProductAddPullList)){
				return cimProductAddPullList.get(0);
			} else {
				return cimProductAddPull;
			}
		} catch (Throwable e) {
			LOGGER.info("[信用宝]根据产品id查询产品结果转化为CimProductAddPull异常,thirdProductId="+thirdProductId,e);
			return cimProductAddPull;
		}
	}
	
	/**
	 * 产品对象转换器
	 */
	private List<CimProductAddPull> productDatadAdapter(List<Productxyb> productxybList) {
		List<CimProductAddPull> cimProductAddPullList = new ArrayList<CimProductAddPull>();
		if(CollectionUtils.isNotEmpty(productxybList)){
			for(Productxyb productxyb : productxybList){
				try {
					CimProductAddPull cimProductAddPull = new CimProductAddPull();
					
					BeanUtils.copyProperties(productxyb, cimProductAddPull);
					
					//标期限转换  以大写M D 结尾的   M代表月 D代表天
					String period = productxyb.getPeriod();
					Integer preNumber = Integer.parseInt(period.substring(0,period.length()-1));
					
					//步步高产品单独处理
					if(productxyb.getTitle().indexOf("步步高") != -1){
						//利率-固定利率
						cimProductAddPull.setIsFlow(2);
						cimProductAddPull.setFlowMinRate(new BigDecimal(productxyb.getOriginalRate()));
						cimProductAddPull.setFlowMaxRate(new BigDecimal(12));
						
						//期限
						cimProductAddPull.setIsFixedDeadline(2);
						if(period.endsWith("M") || period.endsWith("m")){
							cimProductAddPull.setDeadLineMinValue(preNumber*30);
							cimProductAddPull.setDeadLineMinSelfDefined(preNumber+"个月");
							cimProductAddPull.setDeadLineMaxValue(12*30);
							cimProductAddPull.setDeadLineMaxSelfDefined(12+"个月");
						}else if(period.endsWith("D") || period.endsWith("d")){
							cimProductAddPull.setDeadLineMinValue(preNumber);
							cimProductAddPull.setDeadLineMinSelfDefined(preNumber+"天");
							cimProductAddPull.setDeadLineMaxValue(360);
							cimProductAddPull.setDeadLineMaxSelfDefined(360+"天");
						}
					} else {
						//利率-固定利率
						cimProductAddPull.setIsFlow(1);
						cimProductAddPull.setFlowMinRate(new BigDecimal(productxyb.getOriginalRate()));
						cimProductAddPull.setFlowMaxRate(new BigDecimal(productxyb.getOriginalRate()));
						
						//期限
						cimProductAddPull.setIsFixedDeadline(1);
						if(period.endsWith("M") || period.endsWith("m")){
							cimProductAddPull.setDeadLineMinValue(preNumber*30);
							cimProductAddPull.setDeadLineMinSelfDefined(preNumber+"个月");
							cimProductAddPull.setDeadLineMaxValue(preNumber*30);
							cimProductAddPull.setDeadLineMaxSelfDefined(preNumber+"个月");
						}else if(period.endsWith("D") || period.endsWith("d")){
							cimProductAddPull.setDeadLineMinValue(preNumber);
							cimProductAddPull.setDeadLineMinSelfDefined(preNumber+"天");
							cimProductAddPull.setDeadLineMaxValue(preNumber);
							cimProductAddPull.setDeadLineMaxSelfDefined(preNumber+"天");
						}
					}
					
					//总额  剩余金额
					cimProductAddPull.setBuyMinMoney(new BigDecimal(productxyb.getMinInvestAmount()));
					cimProductAddPull.setBuyTotalMoney(new BigDecimal(productxyb.getBorrowAmount()));
					cimProductAddPull.setBuyedTotalMoney(new BigDecimal(productxyb.getBorrowAmount()).subtract(new BigDecimal(productxyb.getRemainAmount())));
					
					//产品描述
					cimProductAddPull.setProductDesc(productxyb.getDesc());
					cimProductAddPull.setProductName(productxyb.getTitle());
					cimProductAddPull.setRepaymentWay(1);
					cimProductAddPull.setSaleStartTime(productxyb.getPublishAt());
					cimProductAddPull.setThirdProductId(productxyb.getId());
					
					//机构编码
					cimProductAddPull.setOrgNumber(orgEnum.getOrgNumber());
					
					/**
					 * 产品状态
					 * 信用宝启动了  0还款中，1已还清，3投标中， 9等待开始
					 * 其中0 1对应  2-售罄  3 9对应1-在售
					 * 其他  全部当成售罄处理
					 */
					if(productxyb.getStatus() == 3 || productxyb.getStatus() == 9){
						cimProductAddPull.setStatus(1);
					} else {
						cimProductAddPull.setStatus(2);
					}
					//产品销售结束时间
					cimProductAddPull.setSaleEndTime(productxyb.getFullAt());
					
					cimProductAddPullList.add(cimProductAddPull);
				} catch (Exception e) {
					LOGGER.info("[信用宝]将productxyb转化为CimProductAddPull异常,Productxyb="+JSONObject.toJSONString(productxyb),e);
					continue;
				}
			}
		}
		return cimProductAddPullList;		
	}
}
