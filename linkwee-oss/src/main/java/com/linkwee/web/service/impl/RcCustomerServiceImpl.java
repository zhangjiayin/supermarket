package com.linkwee.web.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.core.base.PaginatorSevReq;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.web.dao.RcCustomerDao;
import com.linkwee.web.dao.StatisticCustomerDao;
import com.linkwee.web.enums.OrderEnum;
import com.linkwee.web.model.customer.RcCustomersResp;
import com.linkwee.web.model.customer.RcLcsCustomersResp;
import com.linkwee.web.model.customer.StatisticCustomerReq;
import com.linkwee.web.model.customer.UnRcLcsCustomersResp;
import com.linkwee.web.service.RcCustomerService;
import com.linkwee.web.util.PaginatorUtil;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;


/**
 * 
 * @描述： 邀请理财师
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年08月08日 17:27:15
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
@Service("rcCustomerService")
public class RcCustomerServiceImpl implements RcCustomerService {

	@Autowired
	private RcCustomerDao rcCustomerDao;
	
	@Autowired
	private StatisticCustomerDao statisticCustomerDao;
	
	/**
	 * 统计邀请人数
	 * @param userNumber 理财师编号
	 * @return
	 */
	public Map<String,String> statisticRcCustCount(String userNumber,String[] types){
		return rcCustomerDao.statisticRcCustCount(userNumber, types);
	}

	/**
	 * 查询未被邀请客户
	 * @return @see UnRcPageListResp
	 */
	@Override
	public PaginatorSevResp<UnRcLcsCustomersResp> queryUnRcLcsCustomers(
			PaginatorSevReq request) {
		PageRequest req = PaginatorUtil.toPageRequest(request);
		int totalCount = rcCustomerDao.queryUnRcLcsCustomersCount(req.getQuery());
		if(totalCount==0){
			return PaginatorUtil.getEmptyResp(req);
		}else{
			req.setContainsTotalCount(false);
			if(request.getSort()!=null&&request.getOrder()!=null){
				req.setSort(EnumUtils.getValueByKey(request.getSort(), QurplSortFieldEnum.values()));
				req.setOrder(EnumUtils.getValueByKey(request.getOrder(), OrderEnum.values()));
			}
			return PaginatorUtil.getPaginatorSevResp(req,rcCustomerDao.queryUnRcLcsCustomers(req),totalCount);
		}
	}

	/**
	 * 查询客户推荐情况
	 * @return @see RcListPageListResp
	 */
	@Override
	public PaginatorSevResp<RcLcsCustomersResp> queryRcLcsCustomers(PaginatorSevReq request) {
		PageRequest req = PaginatorUtil.toPageRequest(request);
		if(request.getSort()!=null&&request.getOrder()!=null){
			req.setSort(EnumUtils.getValueByKey(request.getSort(), QrplSortFieldEnum.values()));
			req.setOrder(EnumUtils.getValueByKey(request.getOrder(), OrderEnum.values()));
		}
		return  PaginatorUtil.toPaginatorSevResp(rcCustomerDao.queryRcLcsCustomers(req));
	}

	/**
	 * 统计 注册客户、投资客户
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public Map<String, String> statisticRegCustCount(String userMobile) {
		StatisticCustomerReq statisticCustomerReq = new StatisticCustomerReq();
		statisticCustomerReq.setUserMobile(userMobile);
		//累计注册客户
		Integer totalCustomer = statisticCustomerDao.queryRegCustomerCount(statisticCustomerReq);
		//累计投资客户
		Integer totalInvestCustomer = statisticCustomerDao.queryInvestCustomerCount(statisticCustomerReq);
		Map<String,String> ret = new HashMap<String,String>();
		ret.put("regPersons", totalCustomer==null?"0":totalCustomer.toString());
		ret.put("investPersons", totalInvestCustomer==null?"0":totalInvestCustomer.toString());
		return ret;
	}
	
	
	/**
	 * 邀请客户-客户列表
	 * @param request
	 * @return
	 */
	public PaginatorSevResp<RcCustomersResp> queryRcCustomers(PaginatorSevReq request){
		PageRequest req = PaginatorUtil.toPageRequest(request);
		if(request.getSort()!=null&&request.getOrder()!=null){
			req.setSort(EnumUtils.getValueByKey(request.getSort(), QurplSortFieldEnum.values()));
			req.setOrder(EnumUtils.getValueByKey(request.getOrder(), OrderEnum.values()));
		}else{
			req.setSort("registerDate");
			req.setOrder(OrderEnum.DESC.getValue());
		}
		return  PaginatorUtil.toPaginatorSevResp(rcCustomerDao.queryRcCustomers(req));
	}
}
