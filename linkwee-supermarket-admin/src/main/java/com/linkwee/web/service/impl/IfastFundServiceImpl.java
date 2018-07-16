package com.linkwee.web.service.impl;


import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.dao.CimFundOrderMapper;
import com.linkwee.web.model.CimFundOrder;
import com.linkwee.web.request.funds.ifast.GetOrderListRequest;
import com.linkwee.web.request.funds.ifast.OrderListRequest;
import com.linkwee.web.response.funds.ifast.OrderListExtendsResponse;
import com.linkwee.web.service.CimFundInfoService;
import com.linkwee.web.service.CimFundOrderService;
import com.linkwee.web.service.IfastFundService;
import com.linkwee.xoss.funds.sdk.ifast.Client;
import com.linkwee.xoss.funds.sdk.ifast.Request;
import com.linkwee.xoss.funds.sdk.ifast.base.IfastBaseRespons;
import com.linkwee.xoss.funds.sdk.ifast.constant.RequestPath;
import com.linkwee.xoss.funds.sdk.ifast.enums.Method;
import com.linkwee.xoss.funds.sdk.ifast.model.OrderListExtends;
import com.linkwee.xoss.util.OpenHttpUtils;


@Service("IfastFundService")
public class IfastFundServiceImpl implements IfastFundService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(IfastFundServiceImpl.class);
	
	@Resource
	private CimFundOrderMapper cimFundOrderMapper;
	@Resource
	private CimFundInfoService cimFundInfoService;
	@Resource
	private CimFundOrderService  cimFundOrderService;

	@Override
	public void pullOrderList(GetOrderListRequest getOrderListRequest) {
		
		try {	
			//请求奕丰接口
			Gson gson = new Gson();
			OrderListRequest orderListRequest = new OrderListRequest();
			BeanUtils.copyProperties(getOrderListRequest, orderListRequest);
			
			Request request = new Request(Method.GET, RequestPath.GET_ORDER_LIST,cimFundInfoService.selectOneByOrgNumber(getOrderListRequest.getOrgCode()));
			request.setQuerys(OpenHttpUtils.obj2Map(orderListRequest));
			String responseBody = Client.execute(request).getBody();
			LOGGER.debug("拉取基金订单列表接口返回    responseBody={} ",responseBody);
			Type quickType = new TypeToken<IfastBaseRespons<OrderListRequest, OrderListExtendsResponse>>(){}.getType();
			IfastBaseRespons<OrderListRequest, OrderListExtendsResponse>  ifastBaseRespons = gson.fromJson(responseBody,quickType);
			
			if("0000".equals(ifastBaseRespons.getCode())){
				//返回数据处理
				List<OrderListExtends> orderListExtendsList= ifastBaseRespons.getData().getData();
				
				if(CollectionUtils.isNotEmpty(orderListExtendsList)){
					for (OrderListExtends orderListExtends : orderListExtendsList) {
						//判断当前订单是否存在
						CimFundOrder  cimFundOrder =  new CimFundOrder();
						cimFundOrder.setFundCode(orderListExtends.getFundCode());
						cimFundOrder.setMerchantNumber(orderListExtends.getMerchantNumber());
						cimFundOrder = cimFundOrderService.selectOne(cimFundOrder);
						if(cimFundOrder == null){//不存在 才进行插入
							CimFundOrder  cimFundOrderNew =  new CimFundOrder();
							BeanUtils.copyProperties(orderListExtends, cimFundOrderNew);
							cimFundOrderNew.setOrderDate(DateUtils.parseTimestampStr(orderListExtends.getOrderDate()));
							cimFundOrderNew.setTransactionAmount(new BigDecimal(orderListExtends.getTransactionAmount()));
							cimFundOrderNew.setTransactionCharge(new BigDecimal(orderListExtends.getTransactionCharge()));
							cimFundOrderNew.setTransactionDate(DateUtils.parse(orderListExtends.getTransactionDate()));
							cimFundOrderNew.setTransactionRate(new BigDecimal(orderListExtends.getTransactionRate()));
							cimFundOrderNew.setBizTime(new Date());
							cimFundOrderService.insert(cimFundOrderNew);
						}
					}
				}
			} else {
				LOGGER.error("拉取基金订单列表异常【奕丰金融】返回    code={} ,message={}",ifastBaseRespons.getCode(),ifastBaseRespons.getMessage());
			}
		} catch (Exception e) {
			LOGGER.error("拉取基金订单列表异常    getOrderListRequest={}",JSONObject.toJSONString(getOrderListRequest), e);
		}
	}

	@Override
	public void updateOrderList() {
		try {
			List<CimFundOrder>	cimFundOrderListList = cimFundOrderService.selectUpdateOrderList();
			if(CollectionUtils.isNotEmpty(cimFundOrderListList)){
				for (CimFundOrder cimFundOrder : cimFundOrderListList) {
					//请求奕丰接口
					Gson gson = new Gson();
					OrderListRequest orderListRequest = new OrderListRequest();
					orderListRequest.setAccountNumber(cimFundOrder.getAccountNumber());
					orderListRequest.setMerchantNumber(cimFundOrder.getMerchantNumber());
					orderListRequest.setFundCodes(cimFundOrder.getFundCode());
					
					Request request = new Request(Method.GET, RequestPath.GET_ORDER_LIST,cimFundInfoService.selectOneByOrgNumber("OPEN_IFAST_WEB"));
					request.setQuerys(OpenHttpUtils.obj2Map(orderListRequest));
					String responseBody = Client.execute(request).getBody();
					LOGGER.debug("拉取基金订单列表接口返回    responseBody={} ",responseBody);
					Type quickType = new TypeToken<IfastBaseRespons<OrderListRequest, OrderListExtendsResponse>>(){}.getType();
					IfastBaseRespons<OrderListRequest, OrderListExtendsResponse>  ifastBaseRespons = gson.fromJson(responseBody,quickType);
					
					if("0000".equals(ifastBaseRespons.getCode())){
						//返回数据处理
						List<OrderListExtends> orderListExtendsList= ifastBaseRespons.getData().getData();
						
						if(CollectionUtils.isNotEmpty(orderListExtendsList)){	
							OrderListExtends orderListExtends = orderListExtendsList.get(0);
							CimFundOrder  cimFundOrderUpdate =  new CimFundOrder();
							BeanUtils.copyProperties(orderListExtends, cimFundOrderUpdate);
							cimFundOrderUpdate.setId(cimFundOrder.getId());
							cimFundOrderUpdate.setUpdateTime(new Date());
							cimFundOrderService.update(cimFundOrderUpdate);
						}
					} else {
						LOGGER.error("拉取基金订单列表异常【奕丰金融】返回    code={} ,message={}",ifastBaseRespons.getCode(),ifastBaseRespons.getMessage());
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("拉取更新基金订单列表异常", e);
		}
	}

}