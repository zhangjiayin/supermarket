package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;
import java.lang.Long;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.api.request.crm.InvestCalendarDetailRequest;
import com.linkwee.api.request.crm.InvestCalendarRequest;
import com.linkwee.api.request.crm.InvestCalendarStatisticsRequest;
import com.linkwee.api.response.crm.CalendarStatisticsResponse;
import com.linkwee.api.response.crm.InsuranceInvestCalendarDetailResponse;
import com.linkwee.api.response.crm.InsuranceInvestCalendarResponse;
import com.linkwee.api.response.crm.InsuranceInvestCalendarStatisticsResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.model.CimInsuranceNotify;
import com.linkwee.web.dao.CimInsuranceNotifyMapper;
import com.linkwee.web.service.CimInsuranceNotifyService;
import com.linkwee.web.service.impl.CimInsuranceNotifyServiceImpl;


 /**
 * 
 * @描述：CimInsuranceNotifyService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年09月14日 16:54:47
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimInsuranceNotifyService")
public class CimInsuranceNotifyServiceImpl extends GenericServiceImpl<CimInsuranceNotify, Long> implements CimInsuranceNotifyService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimInsuranceNotifyServiceImpl.class);
	
	@Resource
	private CimInsuranceNotifyMapper cimInsuranceNotifyMapper;
	
	@Override
    public GenericDao<CimInsuranceNotify, Long> getDao() {
        return cimInsuranceNotifyMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimInsuranceNotify -- 排序和模糊查询 ");
		Page<CimInsuranceNotify> page = new Page<CimInsuranceNotify>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimInsuranceNotify> list = this.cimInsuranceNotifyMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public CimInsuranceNotify selectByOrgNumberInsureNum(String orgNumber,String insureNum) {
		CimInsuranceNotify cimInsuranceNotify = new CimInsuranceNotify();
		cimInsuranceNotify.setOrgNumber(orgNumber);
		cimInsuranceNotify.setInsureNum(insureNum);
		return cimInsuranceNotifyMapper.selectOneByCondition(cimInsuranceNotify);
	}

	@Override
	public void updateStatus(CimInsuranceNotify cimInsuranceNotify) {
		// TODO Auto-generated method stub
		Date now = new Date();
		CimInsuranceNotify cimInsuranceNotifyUpdate = new CimInsuranceNotify();
		cimInsuranceNotifyUpdate.setId(cimInsuranceNotify.getId());
		cimInsuranceNotifyUpdate.setAuditStatus(cimInsuranceNotify.getAuditStatus());
		cimInsuranceNotifyUpdate.setAuditUser(cimInsuranceNotify.getAuditUser());
		cimInsuranceNotifyUpdate.setAuditTime(now);
		cimInsuranceNotifyUpdate.setClearingTime(now);
		
		/**
		 * 0-待审核  1-系统审核通过 2-系统审核失败 3-管理员审核通过 4-管理员审核失败
		 */
		if(cimInsuranceNotify.getAuditStatus() == 0){
			cimInsuranceNotifyUpdate.setClearingStatus(0);
			cimInsuranceNotifyUpdate.setRemark("待审核");
		} else if(cimInsuranceNotify.getAuditStatus() == 1){
			cimInsuranceNotifyUpdate.setClearingStatus(1);
			cimInsuranceNotifyUpdate.setRemark(DateUtils.format(now)+"系统审核通过");
		} else if(cimInsuranceNotify.getAuditStatus() == 2){
			cimInsuranceNotifyUpdate.setClearingStatus(2);
			cimInsuranceNotifyUpdate.setRemark(DateUtils.format(now)+"系统审核失败");
		} else if(cimInsuranceNotify.getAuditStatus() == 3){
			cimInsuranceNotifyUpdate.setClearingStatus(1);
			cimInsuranceNotifyUpdate.setRemark(DateUtils.format(now)+"管理员审核通过");
		} else if(cimInsuranceNotify.getAuditStatus() == 4){
			cimInsuranceNotifyUpdate.setClearingStatus(2);
			cimInsuranceNotifyUpdate.setRemark(DateUtils.format(now)+"管理员审核失败");
		}
		cimInsuranceNotifyMapper.updateByPrimaryKeySelective(cimInsuranceNotifyUpdate);
	}

	@Override
	public List<InsuranceInvestCalendarResponse> queryInsuranceInvestCalendarPageList(InvestCalendarRequest investCalendarRequest,Page<InsuranceInvestCalendarResponse> page) {
		// TODO Auto-generated method stub
		return cimInsuranceNotifyMapper.queryInsuranceInvestCalendarPageList(investCalendarRequest,page);
	}

	@Override
	public InsuranceInvestCalendarStatisticsResponse investCalendarStatisticsTotal(InvestCalendarStatisticsRequest investCalendarStatisticsRequest) {
		// TODO Auto-generated method stub
		return cimInsuranceNotifyMapper.investCalendarStatisticsTotal(investCalendarStatisticsRequest);
	}

	@Override
	public List<CalendarStatisticsResponse> investCalendarStatisticsNumber(InvestCalendarStatisticsRequest investCalendarStatisticsRequest) {
		// TODO Auto-generated method stub
		return cimInsuranceNotifyMapper.investCalendarStatisticsNumber(investCalendarStatisticsRequest);
	}

	@Override
	public InsuranceInvestCalendarDetailResponse queryInvestCalendarDetail(InvestCalendarDetailRequest investCalendarDetailRequest) {
		// TODO Auto-generated method stub
		return cimInsuranceNotifyMapper.queryInvestCalendarDetail(investCalendarDetailRequest);
	}

}
