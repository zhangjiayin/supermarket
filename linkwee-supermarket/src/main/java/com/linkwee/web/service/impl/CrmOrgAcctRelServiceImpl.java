package com.linkwee.web.service.impl;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linkwee.api.request.funds.ifast.InvestorHoldingsRequest;
import com.linkwee.api.request.funds.ifast.VerifyInvestorAccountNumberRequest;
import com.linkwee.api.response.funds.ifast.InvestorholdingsResponse;
import com.linkwee.api.response.funds.ifast.VerifyInvestorAccountNumberResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CrmOrgAcctRelMapper;
import com.linkwee.web.model.CrmOrgAcctRel;
import com.linkwee.web.service.CimFundInfoService;
import com.linkwee.web.service.CrmOrgAcctRelService;
import com.linkwee.xoss.funds.sdk.ifast.Client;
import com.linkwee.xoss.funds.sdk.ifast.Request;
import com.linkwee.xoss.funds.sdk.ifast.base.IfastBaseRespons;
import com.linkwee.xoss.funds.sdk.ifast.constant.RequestPath;
import com.linkwee.xoss.funds.sdk.ifast.enums.Method;


 /**
 * 
 * @描述：CrmOrgAcctRelService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年08月14日 10:15:42
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmOrgAcctRelService")
public class CrmOrgAcctRelServiceImpl extends GenericServiceImpl<CrmOrgAcctRel, Long> implements CrmOrgAcctRelService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmOrgAcctRelServiceImpl.class);
	
	@Resource
	private CrmOrgAcctRelMapper crmOrgAcctRelMapper;
	@Resource
	private CimFundInfoService cimFundInfoService;
	
	@Override
    public GenericDao<CrmOrgAcctRel, Long> getDao() {
        return crmOrgAcctRelMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmOrgAcctRel -- 排序和模糊查询 ");
		Page<CrmOrgAcctRel> page = new Page<CrmOrgAcctRel>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmOrgAcctRel> list = this.crmOrgAcctRelMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public boolean ifOrgAccountExist(String userId, String orgNumber) {
		CrmOrgAcctRel crmOrgAcctRelq = new CrmOrgAcctRel();
		crmOrgAcctRelq.setUserId(userId);
		crmOrgAcctRelq.setOrgNumber(orgNumber);
		crmOrgAcctRelq = selectOne(crmOrgAcctRelq);
		if(crmOrgAcctRelq == null){
			//如果不存在  则调用益丰基金的接口判断是否已经注册
			
			//请求奕丰接口
			Gson gson = new Gson();
			Request request = new Request(Method.GET, RequestPath.VERIFY_INVESTOR_ACCOUNT_NUMBER,cimFundInfoService.selectOneByOrgNumber(orgNumber));
			request.setQuerys(ImmutableMap.of("accountNumber", userId));
			String responseBody = Client.execute(request).getBody();
			LOGGER.debug("请求奕丰获取账户持有资产接口返回    responseBody={} ",responseBody);
			Type quickType = new TypeToken<IfastBaseRespons<VerifyInvestorAccountNumberRequest,VerifyInvestorAccountNumberResponse>>(){}.getType();
			IfastBaseRespons<InvestorHoldingsRequest, InvestorholdingsResponse>  ifastBaseRespons = gson.fromJson(responseBody,quickType);
			
			//此账户号码已被占用
			if("126".equals(ifastBaseRespons.getCode())){
				CrmOrgAcctRel crmOrgAcctRel = new CrmOrgAcctRel();
				crmOrgAcctRel.setUserId(userId);
				crmOrgAcctRel.setOrgAccountType(1);
				crmOrgAcctRel.setOrgAccount(userId);
				crmOrgAcctRel.setOrgNumber(orgNumber);
				crmOrgAcctRel.setCreatTime(new Date());
				crmOrgAcctRel.setOrgType(1);
				insert(crmOrgAcctRel);
			}
		}
		
		return true;
	}

	@Override
	public String getOrgAccount(String userId, String orgNumber) {
		CrmOrgAcctRel crmOrgAcctRelq = new CrmOrgAcctRel();
		crmOrgAcctRelq.setUserId(userId);
		crmOrgAcctRelq.setOrgNumber(orgNumber);
		crmOrgAcctRelq = selectOne(crmOrgAcctRelq);
		if(crmOrgAcctRelq != null){
			return crmOrgAcctRelq.getOrgAccount();
		} else {
			return null;
		}
	}

	@Override
	public boolean hasRegFund(String userId) {
		return crmOrgAcctRelMapper.hasRegFund(userId);
	}
}
