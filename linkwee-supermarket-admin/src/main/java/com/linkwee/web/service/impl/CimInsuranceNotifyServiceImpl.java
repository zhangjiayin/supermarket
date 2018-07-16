package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.tc.fee.common.InsuranceFeeCalcDelegate;
import com.linkwee.web.dao.CimInsuranceNotifyMapper;
import com.linkwee.web.model.CimInsuranceFee;
import com.linkwee.web.model.CimInsuranceNotify;
import com.linkwee.web.model.CimInsuranceProduct;
import com.linkwee.web.request.insurance.qixin.InsuranceAuditRequest;
import com.linkwee.web.request.insurance.qixin.InsuranceNotifyAuditRequest;
import com.linkwee.web.response.insurance.qixin.InsuranceNotifyAuditResponse;
import com.linkwee.web.service.CimInsuranceFeeService;
import com.linkwee.web.service.CimInsuranceNotifyService;
import com.linkwee.web.service.CimInsuranceProductService;


 /**
 * 
 * @描述：CimInsuranceNotifyService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月30日 11:21:50
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimInsuranceNotifyService")
public class CimInsuranceNotifyServiceImpl extends GenericServiceImpl<CimInsuranceNotify, Long> implements CimInsuranceNotifyService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimInsuranceNotifyServiceImpl.class);
	
	@Resource
	private CimInsuranceNotifyMapper cimInsuranceNotifyMapper;
	@Resource
	private InsuranceFeeCalcDelegate InsuranceFeeCalcDelegate;
	@Resource
	private CimInsuranceProductService cimInsuranceProductService;
	@Resource
	private CimInsuranceFeeService cimInsuranceFeeService;
	
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
	public DataTableReturn getInsuranceNotify(InsuranceNotifyAuditRequest insuranceNotifyAuditRequest) {
		
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(insuranceNotifyAuditRequest.getDraw()+1);
		Page<InsuranceNotifyAuditResponse> page = new Page<InsuranceNotifyAuditResponse>(insuranceNotifyAuditRequest.getStart()/insuranceNotifyAuditRequest.getLength()+1,insuranceNotifyAuditRequest.getLength());
		List<InsuranceNotifyAuditResponse> list = cimInsuranceNotifyMapper.getInsuranceNotify(insuranceNotifyAuditRequest,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public String auditInsuranceNotify(InsuranceAuditRequest insuranceAuditRequest) throws Exception{
		
		Date now = new Date();
		CimInsuranceNotify cimInsuranceNotify = new CimInsuranceNotify();
		cimInsuranceNotify.setId(insuranceAuditRequest.getId());
		cimInsuranceNotify = selectOne(cimInsuranceNotify);
		cimInsuranceNotify.setAuditStatus(insuranceAuditRequest.getAuditStatus());
		cimInsuranceNotify.setAuditTime(now);
		cimInsuranceNotify.setAuditUser(insuranceAuditRequest.getUserName());
		cimInsuranceNotify.setClearingTime(now);
		
		LOGGER.info("开始保险订单审核佣金计算  cimInsuranceNotify ={} ",JSONObject.toJSONString(cimInsuranceNotify));
		
		CimInsuranceProduct cimInsuranceProduct = new CimInsuranceProduct();
		cimInsuranceProduct.setOrgNumber(cimInsuranceNotify.getOrgNumber());
		cimInsuranceProduct.setCaseCode(cimInsuranceNotify.getProductId());
		cimInsuranceProduct = cimInsuranceProductService.selectOne(cimInsuranceProduct);
		if(cimInsuranceProduct == null){
			LOGGER.error("无效的保险平台产品:orgNumber={} ProductId={}",cimInsuranceNotify.getOrgNumber(),cimInsuranceNotify.getProductId());
			return "无效的保险平台产品";
		}
		
		CimInsuranceNotify  cimInsuranceNotifyNew = selectByOrgNumberInsureNum(cimInsuranceNotify.getOrgNumber(), cimInsuranceNotify.getInsureNum());
		if((cimInsuranceNotifyNew != null && cimInsuranceNotifyNew.getAuditStatus() != 0) || (cimInsuranceNotifyNew != null && cimInsuranceNotifyNew.getClearingStatus() != 0)){
			LOGGER.error("该笔保险订单已通过审核处理或者已结算处理,佣金无需再次计算:orgNumber={} ProductId={}",cimInsuranceNotify.getOrgNumber(),cimInsuranceNotify.getProductId());
			return "该笔保险订单已通过审核处理或者已结算处理,佣金无需再次审核计算";
		}
		
		//判断是否存在佣金记录
		List<CimInsuranceFee>  cimInsuranceFeeList = cimInsuranceFeeService.selectByBillId(cimInsuranceNotify.getBillId());
		if(CollectionUtils.isEmpty(cimInsuranceFeeList)){	
			LOGGER.error("该笔保险订单无佣金明细记录,请联系管理员",cimInsuranceNotify.getOrgNumber(),cimInsuranceNotify.getProductId());
			return "该笔保险订单无佣金明细记录,请联系管理员";
		} else {
			InsuranceFeeCalcDelegate.feeCalcExist(cimInsuranceNotify);
			
			//更新订单审核状态
			updateStatus(cimInsuranceNotify);
			return "success";
		}
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
			cimInsuranceNotifyUpdate.setRemark("待审核");
			cimInsuranceNotifyUpdate.setClearingStatus(0);
		} else if(cimInsuranceNotify.getAuditStatus() == 1){
			cimInsuranceNotifyUpdate.setRemark(DateUtils.format(now)+"系统审核通过");
			cimInsuranceNotifyUpdate.setClearingStatus(1);
		} else if(cimInsuranceNotify.getAuditStatus() == 2){
			cimInsuranceNotifyUpdate.setRemark(DateUtils.format(now)+"系统审核失败");
			cimInsuranceNotifyUpdate.setClearingStatus(2);
		} else if(cimInsuranceNotify.getAuditStatus() == 3){
			cimInsuranceNotifyUpdate.setRemark(DateUtils.format(now)+"管理员审核通过");
			cimInsuranceNotifyUpdate.setClearingStatus(1);
		} else if(cimInsuranceNotify.getAuditStatus() == 4){
			cimInsuranceNotifyUpdate.setRemark(DateUtils.format(now)+"管理员审核失败");
			cimInsuranceNotifyUpdate.setClearingStatus(2);
		}
		cimInsuranceNotifyMapper.updateByPrimaryKeySelective(cimInsuranceNotifyUpdate);
	}

}
