package com.linkwee.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.linkwee.api.request.act.ActPersonAddfeeTicketRequest;
import com.linkwee.api.request.cim.ProductAddfeeTicketRequest;
import com.linkwee.api.response.act.ActPersonAddfeeTicketExtendsResponse;
import com.linkwee.api.response.cim.ProductDetailResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.ActPersonAddfeeTicketMapper;
import com.linkwee.web.model.ActPersonAddfeeTicket;
import com.linkwee.web.model.acc.ActPersonAddfeeTicketExtends;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.service.ActPersonAddfeeTicketService;
import com.linkwee.web.service.CimProductInvestRecordService;
import com.linkwee.web.service.CimProductService;
import com.linkwee.web.service.CrmInvestorService;


 /**
 * 
 * @描述：ActPersonAddfeeTicketService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年04月12日 16:02:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actPersonAddfeeTicketService")
public class ActPersonAddfeeTicketServiceImpl extends GenericServiceImpl<ActPersonAddfeeTicket, Long> implements ActPersonAddfeeTicketService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActPersonAddfeeTicketServiceImpl.class);
	
	@Autowired
	private ActPersonAddfeeTicketMapper actPersonAddfeeTicketMapper;
	@Autowired
	private CimProductService cimProductService;
	@Autowired
	private CrmInvestorService crmInvestorService;
	@Autowired
	private CimProductInvestRecordService cimProductInvestRecordService;
	
	@Override
    public GenericDao<ActPersonAddfeeTicket, Long> getDao() {
        return actPersonAddfeeTicketMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActPersonAddfeeTicket -- 排序和模糊查询 ");
		Page<ActPersonAddfeeTicket> page = new Page<ActPersonAddfeeTicket>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActPersonAddfeeTicket> list = this.actPersonAddfeeTicketMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public PaginatorResponse<ActPersonAddfeeTicketExtendsResponse> myAddFeeTicket(ActPersonAddfeeTicketRequest actPersonAddfeeTicketRequest) {
		PaginatorResponse<ActPersonAddfeeTicketExtendsResponse> paginatorResponse = new PaginatorResponse<ActPersonAddfeeTicketExtendsResponse>();
		Page<ActPersonAddfeeTicketExtendsResponse> page  = new Page<ActPersonAddfeeTicketExtendsResponse>(actPersonAddfeeTicketRequest.getPageIndex(),actPersonAddfeeTicketRequest.getPageSize()); //默认每页10条
		List<ActPersonAddfeeTicketExtendsResponse> actPersonAddfeeTicketExtendsResponseList = actPersonAddfeeTicketMapper.myAddFeeTicket(actPersonAddfeeTicketRequest, page);	
		paginatorResponse.setDatas(actPersonAddfeeTicketExtendsResponseList);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public List<ActPersonAddfeeTicketExtends> queryPersonAddfeeTicket(String userId, Integer queryType) {
		// TODO Auto-generated method stub
		return actPersonAddfeeTicketMapper.queryPersonAddfeeTicket(userId,queryType);
	}

	@Override
	public ActPersonAddfeeTicketExtends matchePersonAddfeeTicket(List<ActPersonAddfeeTicketExtends> actPersonAddfeeTicketExtendsList,InvestRecordWrapper investRecord) {
		ActPersonAddfeeTicketExtends actPersonAddfeeTicketExtends = null;
		List<ActPersonAddfeeTicketExtends> matcheActPersonAddfeeTicketExtendsList = matchePersonAddfeeTicketList(actPersonAddfeeTicketExtendsList,investRecord);
		if(CollectionUtils.isNotEmpty(matcheActPersonAddfeeTicketExtendsList)){
			actPersonAddfeeTicketExtends = matcheActPersonAddfeeTicketExtendsList.get(0);
			for (ActPersonAddfeeTicketExtends matcheActPersonAddfeeTicketExtends : matcheActPersonAddfeeTicketExtendsList) {
				if(matcheActPersonAddfeeTicketExtends.getRate().compareTo(actPersonAddfeeTicketExtends.getRate()) > 0 ){
					actPersonAddfeeTicketExtends = matcheActPersonAddfeeTicketExtends;
				}
			}
		}
		return actPersonAddfeeTicketExtends;
	}

	@Override
	public List<ActPersonAddfeeTicketExtends> matchePersonAddfeeTicketList(List<ActPersonAddfeeTicketExtends> actPersonAddfeeTicketExtendsList,InvestRecordWrapper investRecordWrapper) {
		List<ActPersonAddfeeTicketExtends> matcheActPersonAddfeeTicketExtendsList = new ArrayList<ActPersonAddfeeTicketExtends>();
		if(CollectionUtils.isNotEmpty(actPersonAddfeeTicketExtendsList)){
			for (ActPersonAddfeeTicketExtends actPersonAddfeeTicketExtends : actPersonAddfeeTicketExtendsList) {
				//平台限制
				if(actPersonAddfeeTicketExtends.getPlatformLimit() == 1 && !actPersonAddfeeTicketExtends.getPlatformLimitOrgNumber().equals(investRecordWrapper.getProductOrgId())){
					continue;
				}
				//产品限制-产品编号
				if(actPersonAddfeeTicketExtends.getProductLimit() == 1001 && !Sets.newHashSet(actPersonAddfeeTicketExtends.getProductLimitId().split(",")).contains(investRecordWrapper.getProductId())){
					continue;
				}
				//产品限制-等于产品期限
				if(actPersonAddfeeTicketExtends.getProductLimit() == 1002 && actPersonAddfeeTicketExtends.getProductLimitDeadline() != investRecordWrapper.getDeadLineMinValue()){
					continue;
				}
				//产品限制-大于等于产品期限
				if(actPersonAddfeeTicketExtends.getProductLimit() == 1003 && investRecordWrapper.getDeadLineMinValue() < actPersonAddfeeTicketExtends.getProductLimitDeadline()){
					continue;
				}
				//用户投资限制-用户首投
				if(actPersonAddfeeTicketExtends.getInvestLimit() == 1 && !investRecordWrapper.isFirstInvest()){
					continue;
				}
				//用户投资限制-平台首投
				if(actPersonAddfeeTicketExtends.getInvestLimit() == 2 && !investRecordWrapper.isPlatfromFirstInvest()){
					continue;
				}
				//金额限制-大于
				if(actPersonAddfeeTicketExtends.getAmountLimit() == 1 && investRecordWrapper.getInvestAmt().compareTo(actPersonAddfeeTicketExtends.getAmount()) <= 0){
					continue;
				}
				//金额限制-大于等于
				if(actPersonAddfeeTicketExtends.getAmountLimit() == 2 && investRecordWrapper.getInvestAmt().compareTo(actPersonAddfeeTicketExtends.getAmount()) < 0){
					continue;
				}
				matcheActPersonAddfeeTicketExtendsList.add(actPersonAddfeeTicketExtends);
			}
		}
		return matcheActPersonAddfeeTicketExtendsList;
	}

	@Override
	public List<ActPersonAddfeeTicketExtends> productPersonAddfeeTicket(ProductAddfeeTicketRequest productAddfeeTicketRequest) {
		List<ActPersonAddfeeTicketExtends> actPersonAddfeeTicketExtendsList = queryPersonAddfeeTicket(productAddfeeTicketRequest.getUserId(),1);
		//产品详情
		ProductDetailResponse productDetailResponse = cimProductService.queryProductDetail(productAddfeeTicketRequest.getProductId(),null);
		InvestRecordWrapper investRecordWrapper = new InvestRecordWrapper();
		investRecordWrapper.setProductOrgId(productDetailResponse.getOrgNumber());
		investRecordWrapper.setProductId(productDetailResponse.getProductId());
		investRecordWrapper.setDeadLineMinValue(productAddfeeTicketRequest.getDeadLineValue() == null?productDetailResponse.getDeadLineMinValue():productAddfeeTicketRequest.getDeadLineValue());
		investRecordWrapper.setInvestAmt(productAddfeeTicketRequest.getBuyTotal());
		
		investRecordWrapper.setFirstInvest(ObjectUtils.equals(cimProductInvestRecordService.getInvestRecordCounts(productAddfeeTicketRequest.getUserId()), 0));
		investRecordWrapper.setPlatfromFirstInvest(ObjectUtils.equals(cimProductInvestRecordService.queryUserPlatfromInvestCount(productAddfeeTicketRequest.getUserId(),productDetailResponse.getOrgNumber()), 0));
		
		return matchePersonAddfeeTicketList(actPersonAddfeeTicketExtendsList, investRecordWrapper);
	}

	@Override
	public Integer productPersonAddfeeTicketCount(ProductAddfeeTicketRequest productAddfeeTicketRequest) {
		return productPersonAddfeeTicket(productAddfeeTicketRequest).size();
	}

	@Override
	public int queryPersonAddfeeTicketCount(String userId) {
		// TODO Auto-generated method stub
		return actPersonAddfeeTicketMapper.queryPersonAddfeeTicket(userId,null).size();
	}

}
