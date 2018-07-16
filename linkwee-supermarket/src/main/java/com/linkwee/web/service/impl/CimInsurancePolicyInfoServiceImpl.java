package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.tc.fee.common.InsuranceFeeCalcDelegate;
import com.linkwee.web.dao.CimInsurancePolicyInfoMapper;
import com.linkwee.web.model.CimInsuranceFee;
import com.linkwee.web.model.CimInsuranceInfo;
import com.linkwee.web.model.CimInsuranceNotify;
import com.linkwee.web.model.CimInsurancePolicyInfo;
import com.linkwee.web.model.CimInsuranceProduct;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.vo.InsuranceInvestRecordWrapper;
import com.linkwee.web.service.CimInsuranceFeeService;
import com.linkwee.web.service.CimInsuranceInfoService;
import com.linkwee.web.service.CimInsuranceNotifyService;
import com.linkwee.web.service.CimInsurancePolicyInfoService;
import com.linkwee.web.service.CimInsuranceProductService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.InsuranceInvestRecordAware;
import com.linkwee.xoss.helper.DisruptorHelper;
import com.linkwee.xoss.helper.DisruptorHelper.AbstractEventHandler;
import com.linkwee.xoss.util.business.BusinessUtils;


 /**
 * 
 * @描述：CimInsurancePolicyInfoService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年09月14日 16:54:47
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimInsurancePolicyInfoService")
public class CimInsurancePolicyInfoServiceImpl extends GenericServiceImpl<CimInsurancePolicyInfo, Long> implements CimInsurancePolicyInfoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimInsurancePolicyInfoServiceImpl.class);
	
	private static final String KEY = CimInsurancePolicyInfoServiceImpl.class.getName()+".investRecordProcess";
	
	@Autowired
	private DisruptorHelper disruptorHelper;
	@Autowired
	private List<InsuranceInvestRecordAware> insuranceInvestRecordAwareList;
	@Resource
	private CimInsuranceProductService cimInsuranceProductService;
	@Resource
	private CimInsuranceInfoService cimInsuranceInfoService;
	@Resource
	private CimInsuranceNotifyService cimInsuranceNotifyService;
	@Resource
	private CimInsuranceFeeService cimInsuranceFeeService;
	@Resource
	private InsuranceFeeCalcDelegate insuranceFeeCalcDelegate;
	@Autowired
	private CrmInvestorService crmInvestorService;
	
	@Resource
	private CimInsurancePolicyInfoMapper cimInsurancePolicyInfoMapper;
	
	@Override
    public GenericDao<CimInsurancePolicyInfo, Long> getDao() {
        return cimInsurancePolicyInfoMapper;
    }
	
	@PostConstruct
	private void init() throws Exception{
		registeredDispatcherEventHandler();
	}
	
	@SuppressWarnings("unchecked")
	private void registeredDispatcherEventHandler() throws Exception{
		try {
			disruptorHelper.createRingBuffer(KEY);
			LOGGER.info("registeredDispatcherEventHandler_insuranceInvestRecordAwareList : {}",insuranceInvestRecordAwareList);
			List<AbstractEventHandler<InsuranceInvestRecordWrapper>> eventHandlers = Lists.newArrayList();
			
			for (final InsuranceInvestRecordAware insuranceInvestRecordAware : insuranceInvestRecordAwareList) {
				eventHandlers.add(new AbstractEventHandler<InsuranceInvestRecordWrapper>() {
					@Override
					protected void onEvent(InsuranceInvestRecordWrapper insuranceInvestRecordWrapper) throws Exception {
						try{
							insuranceInvestRecordAware.insuranceInvestRecordProcess(insuranceInvestRecordWrapper);
							LOGGER.debug("DispatcherEventHandler service = {} InsuranceInvestRecordWrapper={}",AopUtils.getTargetClass(insuranceInvestRecordAware).getAnnotation(Service.class).value(),insuranceInvestRecordWrapper);
						}catch(Exception e){
							LOGGER.error("DispatcherEventHandler Exception service = {} investRecordProcess()  param={} Exception={} ", new Object[]{AopUtils.getTargetClass(insuranceInvestRecordAware).getAnnotation(Service.class).value(),insuranceInvestRecordWrapper,e});							
						}
					}
				});	
			}
			disruptorHelper.addEventProcessor(KEY, eventHandlers.toArray(new AbstractEventHandler[0]));
		} catch (Exception e) {
			LOGGER.error("registeredDispatcherEventHandler Exception", e);
			throw e;
		}
	}
	
	
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimInsurancePolicyInfo -- 排序和模糊查询 ");
		Page<CimInsurancePolicyInfo> page = new Page<CimInsurancePolicyInfo>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimInsurancePolicyInfo> list = this.cimInsurancePolicyInfoMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public void insertList(List<CimInsurancePolicyInfo> cimInsurancePolicyInfoList) {
		// TODO Auto-generated method stub
		cimInsurancePolicyInfoMapper.insertList(cimInsurancePolicyInfoList);
	}

	@Override
	public List<CimInsurancePolicyInfo> selectListByInsureNum(String insureNum) {
		CimInsurancePolicyInfo cimInsurancePolicyInfo = new CimInsurancePolicyInfo();
		cimInsurancePolicyInfo.setInsureNum(insureNum);
		return cimInsurancePolicyInfoMapper.selectByCondition(cimInsurancePolicyInfo);
	}

	@Override
	public void deleteByInsureNum(String insureNum) {
		// TODO Auto-generated method stub
		cimInsurancePolicyInfoMapper.deleteByInsureNum(insureNum);
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void handleInsuranceNotifyProcess(CimInsuranceNotify  cimInsuranceNotify) throws Exception{
		// TODO Auto-generated method stub
		try {
			LOGGER.info("开始保险订单佣金计算  cimInsuranceNotify ={} ",JSONObject.toJSONString(cimInsuranceNotify));
			
			CimInsuranceProduct cimInsuranceProduct = new CimInsuranceProduct();
			cimInsuranceProduct.setOrgNumber(cimInsuranceNotify.getOrgNumber());
			cimInsuranceProduct.setCaseCode(cimInsuranceNotify.getProductId());
			cimInsuranceProduct = cimInsuranceProductService.selectOne(cimInsuranceProduct);
			if(cimInsuranceProduct == null){
				LOGGER.error("无效的保险平台产品:orgNumber={} ProductId={}",cimInsuranceNotify.getOrgNumber(),cimInsuranceNotify.getProductId());
				return;
			}
			
			CimInsuranceNotify  cimInsuranceNotifyNew = cimInsuranceNotifyService.selectByOrgNumberInsureNum(cimInsuranceNotify.getOrgNumber(), cimInsuranceNotify.getInsureNum());
			if((cimInsuranceNotifyNew != null && cimInsuranceNotifyNew.getAuditStatus() != 0) || (cimInsuranceNotifyNew != null && cimInsuranceNotifyNew.getClearingStatus() != 0)){
				LOGGER.error("该笔保险订单已通过审核处理或者已结算处理,佣金无需再次计算:orgNumber={} ProductId={}",cimInsuranceNotify.getOrgNumber(),cimInsuranceNotify.getProductId());
				return;
			}
			
			//判断是否存在佣金记录
			List<CimInsuranceFee>  cimInsuranceFeeList = cimInsuranceFeeService.selectByBillId(cimInsuranceNotify.getBillId());
			if(CollectionUtils.isEmpty(cimInsuranceFeeList)){
				InsuranceInvestRecordWrapper insuranceInvestRecordWrapper = createInsuranceInvestRecordWrapper(cimInsuranceProduct,cimInsuranceNotify);
				disruptorHelper.publish(KEY, insuranceInvestRecordWrapper);
			} else {
				/**
				 * 系统审核通过 或 系统审核失败
				 * AuditStatus = 0 不做任何处理 因为保险会存在notifyType == 3和notifyType == 9的状态通知  这种通知可能会有多次 顺序也是不固定的
				 */
				if(cimInsuranceNotify.getAuditStatus() != 0){ 
					try {
						insuranceFeeCalcDelegate.feeCalcExist(cimInsuranceNotify);
					} catch (Exception e) {
						cimInsuranceNotify.setClearingStatus(2);
						LOGGER.error("计算保险佣金-已存在佣金明细异常  cimInsuranceNotify ={} ",JSONObject.toJSONString(cimInsuranceNotify),e);
						throw e;
					}
				} else {
					LOGGER.info("保险订单佣金计算结束  保险佣金已计算  AuditStatus = 0 不做任何处理  cimInsuranceNotify ={} ",JSONObject.toJSONString(cimInsuranceNotify));
				}
			}
			
		} catch (Exception e) {
			LOGGER.error("计算保险订单佣金异常  cimInsuranceNotify ={} ",JSONObject.toJSONString(cimInsuranceNotify),e);
			throw e;
		}
	}


	/**
	 * 创建保险订单佣金计算封装类
	 * @param cimInsuranceProduct
	 * @param cimInsuranceNotify
	 * @return
	 */
	private InsuranceInvestRecordWrapper createInsuranceInvestRecordWrapper(CimInsuranceProduct cimInsuranceProduct,CimInsuranceNotify cimInsuranceNotify) {
		// TODO Auto-generated method stub
		InsuranceInvestRecordWrapper insuranceInvestRecordWrapper = new InsuranceInvestRecordWrapper();
		insuranceInvestRecordWrapper.setFeeRate(cimInsuranceProduct.getFeeRatio());
		insuranceInvestRecordWrapper.setBillId(cimInsuranceNotify.getBillId());
		insuranceInvestRecordWrapper.setInvestorId(cimInsuranceNotify.getUserId());
		
		//保险机构名称
		CimInsuranceInfo cimInsuranceInfo =  cimInsuranceInfoService.selectByOrgNumber(cimInsuranceProduct.getOrgNumber());
		insuranceInvestRecordWrapper.setOrgName(cimInsuranceInfo.getName());
		
		//购买人信息
		CrmInvestor investor = crmInvestorService.queryInvestorByUserId(cimInsuranceNotify.getUserId());		
		insuranceInvestRecordWrapper.setOrgName(cimInsuranceInfo.getName());
		/**
		 * 齐欣保险通知价格单位为 分   需转化为元
		 */
		insuranceInvestRecordWrapper.setProductAmount(cimInsuranceNotify.getPrice().divide(new BigDecimal(100)));
		insuranceInvestRecordWrapper.setProductId(cimInsuranceNotify.getProductId());
		insuranceInvestRecordWrapper.setProductName(cimInsuranceProduct.getProductName());
		insuranceInvestRecordWrapper.setProductOrgId(cimInsuranceProduct.getOrgNumber());
		
		//0-待审核   生成相关佣金为0的 佣金记录 便于前端页面显示
		String remark = null;
		if(cimInsuranceNotify.getAuditStatus() == 0){
			remark = String.format("%s购买保险产品《%s》，将会在15天—45天内结算（受保险机构结算方式的影响）", BusinessUtils.getUserNameMobile(investor.getUserName(), investor.getMobile()),cimInsuranceProduct.getProductName());
			insuranceInvestRecordWrapper.setRemark(remark);
			insuranceInvestRecordWrapper.setYearpurAmount(BigDecimal.ZERO);
		} else if(cimInsuranceNotify.getAuditStatus() == 1 || cimInsuranceNotify.getAuditStatus() == 3){ //系统审核通过
			insuranceInvestRecordWrapper.setRemark(null);//需具体计算展示备注
			insuranceInvestRecordWrapper.setYearpurAmount(cimInsuranceNotify.getPrice().divide(new BigDecimal(100)));//保险年化额就等于保险购买保险产品的金额
		} else if(cimInsuranceNotify.getAuditStatus() == 2 || cimInsuranceNotify.getAuditStatus() == 4){ //系统审核失败
			remark = String.format("%s购买保险产品《%s》，由于在犹豫期退保，佣金结算失败", BusinessUtils.getUserNameMobile(investor.getUserName(), investor.getMobile()),cimInsuranceProduct.getProductName());
			insuranceInvestRecordWrapper.setRemark(remark);
			insuranceInvestRecordWrapper.setYearpurAmount(BigDecimal.ZERO);
		}
		
		return insuranceInvestRecordWrapper;
	}

}
