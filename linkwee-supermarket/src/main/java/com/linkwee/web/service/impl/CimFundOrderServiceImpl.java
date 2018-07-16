package com.linkwee.web.service.impl;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linkwee.api.request.funds.ifast.HoldingsStatisticRequest;
import com.linkwee.api.request.funds.ifast.IfastBaseRequest;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CimFundOrderMapper;
import com.linkwee.web.model.CimFundOrder;
import com.linkwee.web.service.CimFundInfoService;
import com.linkwee.web.service.CimFundOrderService;
import com.linkwee.web.service.CrmOrgAcctRelService;
import com.linkwee.xoss.funds.sdk.ifast.Client;
import com.linkwee.xoss.funds.sdk.ifast.Request;
import com.linkwee.xoss.funds.sdk.ifast.base.IfastBaseRespons;
import com.linkwee.xoss.funds.sdk.ifast.constant.RequestPath;
import com.linkwee.xoss.funds.sdk.ifast.enums.Method;
import com.linkwee.xoss.funds.sdk.ifast.model.HoldingsStatistic;
import com.linkwee.xoss.util.OpenHttpUtils;


 /**
 * 
 * @描述：CimFundOrderService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年10月24日 17:36:45
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimFundOrderService")
public class CimFundOrderServiceImpl extends GenericServiceImpl<CimFundOrder, Long> implements CimFundOrderService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimFundOrderServiceImpl.class);
	
	@Resource
	private CimFundOrderMapper cimFundOrderMapper;
	@Resource
	private CrmOrgAcctRelService crmOrgAcctRelService;
	@Resource
	private CimFundInfoService cimFundInfoService;
	
	@Override
    public GenericDao<CimFundOrder, Long> getDao() {
        return cimFundOrderMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimFundOrder -- 排序和模糊查询 ");
		Page<CimFundOrder> page = new Page<CimFundOrder>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimFundOrder> list = this.cimFundOrderMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public BigDecimal getTransactionAmountByUserId(String userId) {
		// TODO Auto-generated method stub
		return cimFundOrderMapper.getTransactionAmountByUserId(userId);
	}

	@Override
	public HoldingsStatistic getOnTransactionAmountByUserId(String userId) {
		
		HoldingsStatistic holdingsStatistic = null;
		
		HoldingsStatisticRequest holdingsStatisticRequest = new HoldingsStatisticRequest();
		holdingsStatisticRequest.setUserId(userId);
		try {
			IfastBaseRequest ifastBaseRequest = new IfastBaseRequest();
			BeanUtils.copyProperties(holdingsStatisticRequest, ifastBaseRequest);
			ifastBaseRequest.setAccountNumber(crmOrgAcctRelService.getOrgAccount(holdingsStatisticRequest.getUserId(), holdingsStatisticRequest.getOrgCode()));
			
			//请求奕丰接口
			Gson gson = new Gson();
			Request request = new Request(Method.GET, RequestPath.GET_HOLDINGS_STATISTIC,cimFundInfoService.selectOneByOrgNumber(holdingsStatisticRequest.getOrgCode()));
			request.setQuerys(OpenHttpUtils.obj2Map(ifastBaseRequest));
			String responseBody = Client.execute(request).getBody();
			LOGGER.debug("[个人中心]请求奕丰获取账户持有总资产接口返回    responseBody={} ",responseBody);
			Type quickType = new TypeToken<IfastBaseRespons<IfastBaseRequest, HoldingsStatistic>>(){}.getType();
			IfastBaseRespons<IfastBaseRequest, HoldingsStatistic>  ifastBaseRespons = gson.fromJson(responseBody,quickType);
			
			if("0000".equals(ifastBaseRespons.getCode())){		
				if(ifastBaseRespons.getData() != null){
					holdingsStatistic = ifastBaseRespons.getData();
				}
			} else {
				LOGGER.info("[个人中心]奕丰获取账户持有总资产错误	 ifastBaseResponsMessage={}","【奕丰金融】"+ifastBaseRespons.getMessage());
			}
		} catch (Exception e) {
			LOGGER.error("[个人中心]奕丰获取账户持有总资产异常	 HoldingsStatisticRequest={}",JSONObject.toJSONString(holdingsStatisticRequest),e);
		}
		
		return holdingsStatistic;
	}

}
