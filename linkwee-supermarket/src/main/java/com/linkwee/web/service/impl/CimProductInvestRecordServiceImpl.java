package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.linkwee.api.response.HomePageInvestResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.linkwee.api.request.cim.MyInvestrecordRequest;
import com.linkwee.api.request.cim.PlatformBounsRequest;
import com.linkwee.api.request.tc.CfplannerCustomerInvestRecordRequest;
import com.linkwee.api.response.cim.MyInvestrecordResponse;
import com.linkwee.api.response.tc.CfpOrderResponse;
import com.linkwee.api.response.tc.CustomerInvestProfitResponse;
import com.linkwee.api.response.tc.CustomerInvestRecordResponse;
import com.linkwee.api.response.tc.CustomerInvestRecordStatisticResponse;
import com.linkwee.api.response.tc.CustomerTradeMsgResponse;
import com.linkwee.api.response.tc.HotInvestResponse;
import com.linkwee.api.response.tc.InvestRecordResponse;
import com.linkwee.api.response.tc.RepaymentResponse;
import com.linkwee.api.response.tc.TradeNewlyDynamicResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.constant.ActivityConstant;
import com.linkwee.core.constant.ApiInvokeLogConstant;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.openapi.request.InvestRecordReq;
import com.linkwee.web.dao.AcBalanceRecordMapper;
import com.linkwee.web.dao.CimProductInvestRecordMapper;
import com.linkwee.web.dao.SmMessageTemplateMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.CfpNewcomerTaskEnum;
import com.linkwee.web.enums.MidAutumnTaskEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.CimProduct;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.SmMessageTemplate;
import com.linkwee.web.model.SysApiInvokeLog;
import com.linkwee.web.model.acc.AcAccountRecharge;
import com.linkwee.web.model.acc.AcBalanceRecord;
import com.linkwee.web.model.cim.CimProductInvestRecord;
import com.linkwee.web.model.cim.OrgInfo;
import com.linkwee.web.model.crm.GoodTransResp;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.ActCfpDoubleElevenActivityService;
import com.linkwee.web.service.ActMidautumnTaskService;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CimProductInvestRecordService;
import com.linkwee.web.service.CimProductService;
import com.linkwee.web.service.CrmCfpNewcomerWelfareTaskService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.InvestRecordAware;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.SysApiInvokeLogService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.DisruptorHelper;
import com.linkwee.xoss.helper.DisruptorHelper.AbstractEventHandler;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.helper.ThreadpoolService;
import com.linkwee.xoss.util.RejectedExecuteRetry;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： hxb
 * 
 * @创建时间：2016年07月14日 18:04:53
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimProductInvestRecordService")
public class CimProductInvestRecordServiceImpl extends GenericServiceImpl<CimProductInvestRecord, Long> implements CimProductInvestRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductInvestRecordServiceImpl.class);
	
	private static final String KEY = CimProductInvestRecordServiceImpl.class.getName()+".investRecordProcess";
	
	@Autowired
	private CimProductInvestRecordMapper investRecordMapper;
	
	@Autowired
	private CrmInvestorService investorService;
	
	@Autowired
	private CimProductService productService;
	
	@Autowired
	private List<InvestRecordAware> investRecordAwares;
	
	@Autowired
	private SysApiInvokeLogService sysApiInvokeLogService;
	
	@Autowired
	private CimOrginfoService orginfoService;
	
	@Autowired
	private DisruptorHelper disruptorHelper;
	
	@Resource
	private ActivityListService activityListService;
	
	@Resource
	private AcAccountBindService accountbindService;
	
	@Resource
	private AcBalanceRecordMapper acBalanceRecordMapper;
	
	@Resource
	private SmMessageQueueService messageQueueService;
	
	@Resource
	private SmMessageTemplateMapper smMessageTemplateMapper;
	
	@Resource
	private SysMsgService sysMsgService;
	
	@Resource
	private CrmCfplannerService crmCfplannerService;
	
	@Resource
	private CrmCfpNewcomerWelfareTaskService crmCfpNewcomerWelfareTaskService;
	
	@Resource
	private ActMidautumnTaskService actMidautumnTaskService;
	
	@Resource
	private ActCfpDoubleElevenActivityService doubleElevenActivityService;
	
	@Override
    public GenericDao<CimProductInvestRecord, Long> getDao() {
        return investRecordMapper;
    }
	
	public DisruptorHelper getDisruptorHelper() {
		return disruptorHelper;
	}

	
	@PostConstruct
	private void init() throws Exception{
		registeredDispatcherEventHandler();
	}
	
	@SuppressWarnings("unchecked")
	private void registeredDispatcherEventHandler() throws Exception{
		try {
			getDisruptorHelper().createRingBuffer(KEY);
			LOGGER.info("registeredDispatcherEventHandler_investRecordAwares : {}",investRecordAwares);
			List<AbstractEventHandler<InvestRecordWrapper>> eventHandlers = Lists.newArrayList();
			
			for (final InvestRecordAware investRecordAware : investRecordAwares) {
				eventHandlers.add(new AbstractEventHandler<InvestRecordWrapper>() {
					@Override
					protected void onEvent(InvestRecordWrapper investRecordWrapper) throws Exception {
						try{
							InvestRecordWrapper wrapper = copy(investRecordWrapper);
							investRecordAware.investRecordProcess(wrapper);
							LOGGER.debug("DispatcherEventHandler service = {} InvestRecordWrapper={}",AopUtils.getTargetClass(investRecordAware).getAnnotation(Service.class).value(),wrapper);
						}catch(Exception e){
							LOGGER.error("DispatcherEventHandler Exception service = {} investRecordProcess()  param={} Exception={} ", new Object[]{AopUtils.getTargetClass(investRecordAware).getAnnotation(Service.class).value(),investRecordWrapper,e});							
						}
					}
				});	
			}
			getDisruptorHelper().addEventProcessor(KEY, eventHandlers.toArray(new AbstractEventHandler[0]));
		} catch (Exception e) {
			LOGGER.error("registeredDispatcherEventHandler Exception", e);
			throw e;
		}
	}
	private InvestRecordWrapper copy(InvestRecordWrapper investRecordWrapper) throws Exception{
		InvestRecordWrapper wrapper =  new InvestRecordWrapper();
		BeanUtils.copyProperties(wrapper,investRecordWrapper);
		return wrapper;
	}

	/**
	 * 查询被推荐已投资用户的userId
	 */
	@Override
	public List<CimProductInvestRecord> selectRefInvestRecord(List<String> refRegCustomers) {
		return investRecordMapper.selectRefInvestRecord(refRegCustomers);
	}
	
	@Override
	public void insertInvestRecordProcess(InvestRecordReq investRecordReq)throws Exception {
		try {
				
				CimProductInvestRecord record = new CimProductInvestRecord();
				record.setInvestRecordNo(investRecordReq.getInvestId());
				if(selectOne(record)!=null)throw new ServiceException("已经存在的投资记录: "+investRecordReq.getInvestId());
				if(investorService.queryInvestorByUserId(investRecordReq.getUserId())==null)throw new ServiceException("无效的用户编号: "+investRecordReq.getUserId());
				
				CimProduct product = new CimProduct();
				product.setThirdProductId(investRecordReq.getProductId());
				product.setOrgNumber(investRecordReq.getPlatfromId());
				product = productService.selectOne(product);
				if(product==null) throw new ServiceException("无效的平台产品: "+investRecordReq.getPlatfromId()+","+investRecordReq.getProductId());
				
				Date time = Calendar.getInstance().getTime();
				//保存投资记录
				CimProductInvestRecord investRecord = insertInvestRecord(investRecordReq, product, time);
				
				//创建投资记录包装类
				InvestRecordWrapper investRecordWrapper = createInvestRecordWrapper(product, investRecord);
				
				getDisruptorHelper().publish(KEY, investRecordWrapper);
				
			    //王者荣耀活动
				if(investRecord!=null&&investRecord.getIsFirstInvest()==1&&investRecordReq.getInvestAmount().compareTo(new BigDecimal(3000))!=-1&&product.getDeadLineMaxValue()!=null&&product.getDeadLineMaxValue()>=30){
					payAugustActivity(investRecord);
				}
				
				boolean isExistUser = crmCfpNewcomerWelfareTaskService.isExistUser(investRecordReq.getUserId());
				if(isExistUser && investRecord.getIsFirstInvest() == 1){
					//新手福利六连送--自投
					crmCfpNewcomerWelfareTaskService.sendTaskReward(investRecordReq.getUserId(), CfpNewcomerTaskEnum.CFP_NEWCOMMER_WELFARE_INVEST);
					CrmCfplanner crmCfplanner = crmCfplannerService.queryParentByUserId(investRecordReq.getUserId());
					if(crmCfplanner !=  null){					
						//新手福利六连送--给上级理财师发放（邀请理财师投资奖励）
						crmCfpNewcomerWelfareTaskService.sendTaskReward(crmCfplanner.getUserId(), CfpNewcomerTaskEnum.CFP_NEWCOMMER_WELFARE_INVITECFP_INVEST);	
					}
				}
				
				//月饼节活动
				ActivityList selectCondition = new ActivityList();
				selectCondition.setActivityCode("midautumn_rank");
				ActivityList activity = activityListService.selectActiveOne(selectCondition);
				if(activity != null){
					CrmCfplanner crmCfplanner = crmCfplannerService.queryCfplannerByInvestor(investRecordReq.getUserId());
					boolean hasFinishInvestStatus = actMidautumnTaskService.hasFinishInvestStatus(crmCfplanner.getUserId(),DateUtils.format(activity.getStartDate(), DateUtils.FORMAT_LONG),DateUtils.format(activity.getEndDate(), DateUtils.FORMAT_LONG),null);
					if(hasFinishInvestStatus){
						actMidautumnTaskService.finishTask(crmCfplanner.getUserId(), MidAutumnTaskEnum.INVEST_STATUS);
					}
				}
				
		} catch (Exception e) {
			LOGGER.error("insertInvestRecord exception investRecordReq={},exception={}", investRecordReq,e);
			throw e;
		}
		
	}
	
	/**
	 * 王者荣耀活动
	 * 1.满足条件的给其上级理财师发送28.8元现金红包(条件：客户首次购买，理财师获得现金红包次数少于十次)
	 * 2.发送短信 站内信 
	 * @throws Exception 
	 * */
	public void payAugustActivity(final CimProductInvestRecord investRecord) throws Exception{
		ThreadpoolService.execute(new Runnable() {
			@Override
			public void run() {
				    ActivityList act = activityListService.queryByCode(ActivityConstant.KING_OF_GLORY_CODE);
				    if(act==null) throw new ServiceException("王者荣耀活动【KING_OF_GLORY_CODE】没有配置 ");
				    int startTime = DateUtils.compareDate(investRecord.getStartTime(), act.getStartDate());
				    int endTime = DateUtils.compareDate(investRecord.getStartTime(), act.getEndDate());
				    CrmCfplanner crmCfp = crmCfplannerService.queryCfplannerByUserId(investRecord.getUserId());
				    if(crmCfp!=null&&startTime==1&&endTime==-1){
				    	String cfpUserId = crmCfp.getParentId();
				    	AcBalanceRecord record = new AcBalanceRecord();
				    	record.setRemark("王者荣耀活动奖励");
				    	record.setUserId(cfpUserId);
				    	record.setTransAmount(null);
				    	record.setFee(null);
				    	List<AcBalanceRecord> recordList = acBalanceRecordMapper.selectByCondition(record);
				    	//理财师获得红包的次数少于十次
				    	if(cfpUserId!=null&&recordList.size()<20){
				    		//给该用户账号充值
							AcAccountRecharge recharge = new AcAccountRecharge();
							recharge.setRedpacketId(StringUtils.getUUID());
							recharge.setTransAmount(new BigDecimal(50));
							recharge.setUserId(cfpUserId);
							recharge.setUserType(1);
							recharge.setTransType(14);
							recharge.setRemark("王者荣耀活动奖励");
							try {
								accountbindService.accountRecharge(recharge);
							} catch (Exception e1) {
								LOGGER.error("王者荣耀活动奖励发放50红包异常exception={}",e1);
							}
							
							CrmInvestor cfp = investorService.queryInvestorByUserId(cfpUserId);
							
							SmMessageTemplate condit = new SmMessageTemplate();
							condit.setModuleId(MsgModuleEnum.LCS_KING_OF_GLORY.getValue());
							condit.setAppType(AppTypeEnum.CHANNEL.getKey());
							SmMessageTemplate tmp = smMessageTemplateMapper.selectOneByCondition(condit);
							SysMsg msg = new SysMsg();
							msg.setContent(tmp.getContent());
							msg.setStatus(0);// 发布
							msg.setUserNumber(cfpUserId);
							msg.setReadStatus(0);// 未读
							msg.setAppType(1);
							msg.setTypeName("现金红包");
							msg.setStartTime(new Date());
							msg.setCrtTime(new Date());
							msg.setModifyTime(new Date());
							sysMsgService.addMsg(msg);
							
							//发送短信
							try {
								messageQueueService.sendSingleMessage(cfp.getMobile(), AppTypeEnum.CHANNEL, MsgModuleEnum.LCS_KING_OF_GLORY);
							} catch (Exception e) {
								LOGGER.warn("【王者荣耀活动】发送短信异常,mobile={},appType={},msgModuleEnum={},exception={}",new Object[]{cfp.getMobile(),JSON.toJSONString(1),JSON.toJSONString(MsgModuleEnum.LCS_KING_OF_GLORY),e});
							}
							
							
				    	}
				    	
				    }
				}
			});
	}
	
	@Override
	public void updateRepaymentStatus(String investRecordNo,Integer status,Date repaymentTime,BigDecimal repaymentAmount,BigDecimal accurateProfit) {
		investRecordMapper.updateRepaymentStatus(investRecordNo,status,repaymentTime,repaymentAmount, accurateProfit);
	}
	
	
	@Override
	public Double queryCustomerInvestTotalAmount(String userId) {
		return investRecordMapper.queryCustomerInvestTotalAmount(userId).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
	}
	
	@Override
	public Double queryCustomerInvestTotalProfit(String userId) {
		return investRecordMapper.queryCustomerInvestTotalProfit(userId).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
	}
	

	@Override
	public PaginatorResponse<InvestRecordResponse> queryCustomerInvestRecord(String userId,	Integer status, Page<InvestRecordResponse> page) {
		PaginatorResponse<InvestRecordResponse> paginatorResponse = new PaginatorResponse<InvestRecordResponse>();
		paginatorResponse.setDatas(investRecordMapper.queryCustomerInvestRecord(userId, status, page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}
	
	/**
	 * 根据产品编号查询在投投资记录 
	 * @param product 产品
	 * @return
	 */
	@Override
	public List<InvestRecordWrapper> getInvestRecordByProduct(CimProduct product){
		//根据产品编号查询投资记录
		List<CimProductInvestRecord> investRecords = investRecordMapper.getInvestRecordByProductIds(Sets.newHashSet(product.getProductId()));
		if(investRecords==null|| investRecords.isEmpty())return Lists.newArrayListWithCapacity(0);
		//投资记录包装类
		List<InvestRecordWrapper> investRecordWrappers = Lists.newArrayListWithCapacity(investRecords.size());
		for (CimProductInvestRecord investRecord : investRecords) {
			investRecordWrappers.add(createInvestRecordWrapper(product, investRecord));
		}
		return investRecordWrappers;
	}
	
	/**
	 * 根据产品编号查询在投投资记录 
	 * @param products 产品集合
	 * @return
	 */
	@Override
	public List<InvestRecordWrapper> getInvestRecordByProducts(List<CimProduct> products){
		//映射产品 方便遍历投资记录时根据产品编号获取具体产品信息
		Map<String, CimProduct> productMaps = Maps.newHashMap();
		for (CimProduct cimProduct : products) {
			productMaps.put(cimProduct.getProductId(), cimProduct);
		}
		
		//根据产品编号查询投资记录
		List<CimProductInvestRecord> investRecords = investRecordMapper.getInvestRecordByProductIds(productMaps.keySet());
		if(investRecords==null|| investRecords.isEmpty())return Lists.newArrayListWithCapacity(0);
		//格式化 当前日期 格式 yyyy-MM-dd
		SimpleDateFormat format = new SimpleDateFormat(DateUtils.FORMAT_SHORT);
		String curTime = format.format(DateUtils.getCurrentDate());
		//投资日期 与 解除日期 映射 以便减少解锁日期计算次数
		Map<String, String> timeMaps = Maps.newLinkedHashMap();
		
		//投资记录包装类
		List<InvestRecordWrapper> investRecordWrappers = Lists.newArrayListWithCapacity(investRecords.size());
		
		for (CimProductInvestRecord investRecord : investRecords) {
			//根据投资记录中的产品编号获取具体产品信息
			CimProduct p = productMaps.get(investRecord.getProductId());
			if(p==null) continue;
			//不需要募集 取投资时间,否则取募集成功时间
			Date investSuccessTime = ObjectUtils.equals(p.getIsCollect(), 1) ? investRecord.getStartTime() : p.getSaleEndTime();
			
			if(investSuccessTime==null) continue;
			//缓存key = 产品编号与日期
			String key = org.apache.commons.lang.StringUtils.join(new Object[]{investRecord.getProductId(),format.format(investSuccessTime)});
			//根据 key 获取  该笔投资的 的 解除日期 没有则计算 
			String lockTime = timeMaps.get(key);
			if(StringUtils.isBlank(lockTime)){
				
				//起息日  = 投资成功时间或者募集成功时间 + 1 
				lockTime =format.format(org.apache.commons.lang.time.DateUtils.addDays(investSuccessTime, p.getDeadLineMinValue()+1));
				timeMaps.put(key, lockTime);	
			}
			
			//当前日期大于解锁日期 创建投资记录包装类
			if(curTime.compareTo(lockTime)>0){
				investRecordWrappers.add(createInvestRecordWrapper(p, investRecord));
			}
			
		}
		return investRecordWrappers;
	}

	/**
	 * 保存投资记录
	 * @param investRecordReq
	 * @param product
	 * @param time
	 * @return
	 */
	private CimProductInvestRecord insertInvestRecord(InvestRecordReq investRecordReq, CimProduct product, Date time) {
		
		CimProductInvestRecord investRecord = new CimProductInvestRecord();
		investRecord.setInvestId(StringUtils.getUUID());
		investRecord.setInvestRecordNo(investRecordReq.getInvestId());
		investRecord.setTxId(investRecordReq.getTxId());
		investRecord.setBizTime(time);
		investRecord.setUserId(investRecordReq.getUserId());
		investRecord.setProductId(product.getProductId());
		investRecord.setThirdProductId(investRecordReq.getProductId());
		investRecord.setProductFeeRate(product.getFeeRatio());
		investRecord.setStartTime(investRecordReq.getInvestStartTime());
		investRecord.setEndTime(investRecordReq.getInvestEndTime());
		investRecord.setInvestAmt(investRecordReq.getInvestAmount());
		investRecord.setProfit(investRecordReq.getProfit());
		investRecord.setPlatfrom(investRecordReq.getPlatfromId());
		boolean isPlatfromFirstInvest = ObjectUtils.equals(investRecordMapper.queryUserPlatfromInvestCount(investRecordReq.getUserId(),investRecordReq.getPlatfromId()), 0);
		investRecord.setIsPlatfromFirstInvest(isPlatfromFirstInvest?1:0);
		boolean isFirstInvest = ObjectUtils.equals(investRecordMapper.queryUserInvestCount(investRecordReq.getUserId()), 0);
		investRecord.setIsFirstInvest(isFirstInvest?1:0);
		investRecord.setStatus((byte)1);
		investRecord.setCreateTime(time);
		investRecord.setUpdateTime(time);
		
		//插入投资记录
		insert(investRecord);
		return investRecord;
	}
	/**
	 * 创建投资记录包装类
	 * @param investRecordReq
	 * @param product
	 * @param investRecord
	 * @return
	 */
	private InvestRecordWrapper createInvestRecordWrapper(CimProduct product,CimProductInvestRecord investRecord) {
		InvestRecordWrapper investRecordWrapper = new InvestRecordWrapper();
		investRecordWrapper.setBizId(investRecord.getInvestId());
		investRecordWrapper.setInvestId(investRecord.getInvestRecordNo());
		investRecordWrapper.setUserId(investRecord.getUserId());
		investRecordWrapper.setProductId(product.getProductId());
		investRecordWrapper.setIsFixedDeadline(product.getIsFixedDeadline());
		investRecordWrapper.setIsCollect(product.getIsCollect());
		investRecordWrapper.setProductName(product.getProductName());
		//如果是平台老用户不计算佣金
		boolean isOrgNewUser = true;
		//玖富轻舟不需要开通账户，默认已开通返回true
		if(!"OPEN_JIUFUQINGZHOU_WEB".equals(product.getOrgNumber())){
			isOrgNewUser = orginfoService.isOrgNewUser(investRecord.getUserId(), investRecord.getPlatfrom());
		}
		investRecordWrapper.setFeeRatio(isOrgNewUser ? investRecord.getProductFeeRate():BigDecimal.ZERO);
		OrgInfo orgInfoResponse =  orginfoService.findOrgInfo(investRecord.getPlatfrom());
		//平台老用户
		if(!isOrgNewUser){
			if(orgInfoResponse!=null)
				investRecordWrapper.setRemark(isOrgNewUser?null:orgInfoResponse.getOrgName() + "平台老用户,不计算佣金");
		}
		investRecordWrapper.setIsPlatfromNewUser(isOrgNewUser);
		investRecordWrapper.setProductOrgId(investRecord.getPlatfrom());
		investRecordWrapper.setOrgName(orgInfoResponse.getOrgName());
		investRecordWrapper.setProductDays(product.getDeadLineMinValue());
		investRecordWrapper.setIsRedemption(product.getIsRedemption());
		investRecordWrapper.setDeadLineMinValue(product.getDeadLineMinValue());
		investRecordWrapper.setDeadLineMaxValue(product.getDeadLineMaxValue());
		investRecordWrapper.setInvestAmt(investRecord.getInvestAmt());
		investRecordWrapper.setInvestTime(investRecord.getStartTime());
		investRecordWrapper.setFirstInvest(ObjectUtils.equals(investRecord.getIsFirstInvest(), 1));
		investRecordWrapper.setPlatfromFirstInvest(ObjectUtils.equals(investRecord.getIsPlatfromFirstInvest(), 1));
		investRecordWrapper.setEndTime(investRecord.getEndTime());
		investRecordWrapper.setStatus(product.getStatus());
		investRecordWrapper.setIsCpaAndCps(orgInfoResponse.getIsCpaAndCps());
		return investRecordWrapper;
	}
	
	@Override
	public CustomerInvestRecordStatisticResponse queryCfplannerCustomerInvestRecordStatistic(CfplannerCustomerInvestRecordRequest req) {
		return investRecordMapper.queryCfplannerCustomerInvestRecordStatistic(req.getUserId(), req.getDateType(), DateUtils.parse(req.getDate(),DateUtils.FORMAT_SHORT));
	}

	@Override
	public PaginatorResponse<CustomerInvestRecordResponse> queryCfplannerCustomerInvestRecord(CfplannerCustomerInvestRecordRequest req) {
		Page<CustomerInvestRecordResponse> page  = new Page<CustomerInvestRecordResponse>(req.getPageIndex(),req.getPageSize()>10?10:req.getPageSize()); //默认每页10条
		PaginatorResponse<CustomerInvestRecordResponse> paginatorResponse = new PaginatorResponse<CustomerInvestRecordResponse>();
		paginatorResponse.setDatas(investRecordMapper.queryCfplannerCustomerInvestRecord(req.getUserId(), req.getDateType(), DateUtils.parse(req.getDate(),DateUtils.FORMAT_SHORT), page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public PaginatorResponse<CustomerInvestRecordResponse> queryCfplannerInvestCustomerDetail(CfplannerCustomerInvestRecordRequest req) {
		Page<CustomerInvestRecordResponse> page  = new Page<CustomerInvestRecordResponse>(req.getPageIndex(),req.getPageSize()>10?10:req.getPageSize()); //默认每页10条
		PaginatorResponse<CustomerInvestRecordResponse> paginatorResponse = new PaginatorResponse<CustomerInvestRecordResponse>();
		paginatorResponse.setDatas(investRecordMapper.queryCfplannerInvestCustomerDetail(req.getUserId(), req.getDateType(), DateUtils.parse(req.getDate(),DateUtils.FORMAT_SHORT), page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}
	
	

	@Override
	public PaginatorResponse<RepaymentResponse> queryCustomerRepayment(String userId,String customerId,Page<RepaymentResponse> page) {
		PaginatorResponse<RepaymentResponse> paginatorResponse = new PaginatorResponse<RepaymentResponse>();
		if(StringUtils.isBlank(customerId)) customerId = null;
		//DateTime now = DateTime.now();
		paginatorResponse.setDatas(investRecordMapper.queryCustomerRepayment(userId,customerId,page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public int queryCustomerInvestTradeMsgCount(String userId) {
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_CUSTOMER_TRADELIST_SQ, userId, AppTypeEnum.CHANNEL.getKey());
		Date date = apiInvokeLog!=null ? apiInvokeLog.getChgTime(): DateUtils.parse("1990-01-01 00:00:00");
		return investRecordMapper.queryCustomerInvestTradeMsgCount(date, userId);
	}

	@Override
	public PaginatorResponse<CustomerTradeMsgResponse> queryCustomerInvestTradeMsg(String userId,String customerId, Page<CustomerTradeMsgResponse> page) {
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_CUSTOMER_TRADELIST_SQ, userId, AppTypeEnum.CHANNEL.getKey());
		String date = apiInvokeLog!=null ?  DateUtils.format(apiInvokeLog.getChgTime()): DateUtils.format(DateUtils.parse("1990-01-01 00:00:00"));
		PaginatorResponse<CustomerTradeMsgResponse> paginatorResponse = new PaginatorResponse<CustomerTradeMsgResponse>();
		paginatorResponse.setDatas(investRecordMapper.queryCustomerInvestTradeMsg(date,customerId,userId, page));
		paginatorResponse.setValuesByPage(page);
		sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.NAME_CUSTOMER_TRADELIST_SQ, userId,AppTypeEnum.CHANNEL.getKey());
		return paginatorResponse;
	}
	

	@Override
	public int queryCustomerRepaymentTradeMsgCount(String userId) {
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_CUSTOMER_TRADELIST_SH, userId, AppTypeEnum.CHANNEL.getKey());
		Date date = apiInvokeLog!=null ? apiInvokeLog.getChgTime(): DateUtils.parse("1990-01-01 00:00:00");
		return investRecordMapper.queryCustomerRepaymentTradeMsgCount(date, userId);
	}
	
	@Override
	public PaginatorResponse<CustomerTradeMsgResponse> queryCustomerRepaymentTradeMsg(String userId,String customerId, Page<CustomerTradeMsgResponse> page) {
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_CUSTOMER_TRADELIST_SH, userId, AppTypeEnum.CHANNEL.getKey());
		String date = apiInvokeLog!=null ?  DateUtils.format(apiInvokeLog.getChgTime()): DateUtils.format(DateUtils.parse("1990-01-01 00:00:00"));
		PaginatorResponse<CustomerTradeMsgResponse> paginatorResponse = new PaginatorResponse<CustomerTradeMsgResponse>();
		paginatorResponse.setDatas(investRecordMapper.queryCustomerRepaymentTradeMsg( date,customerId,userId,page));
		paginatorResponse.setValuesByPage(page);
		sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.NAME_CUSTOMER_TRADELIST_SH, userId,AppTypeEnum.CHANNEL.getKey());
		return paginatorResponse;
	}

	@Override
	public PaginatorResponse<TradeNewlyDynamicResponse> queryCfpNewlyDynamic(Page<TradeNewlyDynamicResponse> page, String userId) {
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.INVESTRECORD_CFP_DYNAMIC_PAGELIST, userId, AppTypeEnum.CHANNEL.getKey());
		String date = apiInvokeLog!=null ?  DateUtils.format(apiInvokeLog.getChgTime()): DateUtils.format(DateUtils.parse("1990-01-01 00:00:00"));
		PaginatorResponse<TradeNewlyDynamicResponse> paginatorResponse = new PaginatorResponse<TradeNewlyDynamicResponse>();
		List<TradeNewlyDynamicResponse> dynamicList = investRecordMapper.queryCfpNewlyDynamic(userId,date, page);
		paginatorResponse.setDatas(dynamicList);
		paginatorResponse.setValuesByPage(page);
		sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.INVESTRECORD_CFP_DYNAMIC_PAGELIST, userId,AppTypeEnum.CHANNEL.getKey());
		return paginatorResponse;
	}

	@Override
	public Double queryCurrInvestAmount(String userId) {
		return investRecordMapper.queryCurrInvestAmount(userId).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
	}

	@Override
	public BigDecimal queryCurrInvestAmount2(String userId) {
		return investRecordMapper.queryCurrInvestAmount(userId);
	}

	@Override
	public int queryCfpNewlyDynamicUnReadCount(String userId) {
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.INVESTRECORD_CFP_DYNAMIC_PAGELIST, userId, AppTypeEnum.CHANNEL.getKey());
		String date = apiInvokeLog!=null ?  DateUtils.format(apiInvokeLog.getChgTime()): DateUtils.format(DateUtils.parse("1990-01-01 00:00:00"));
		return investRecordMapper.queryCfpNewlyDynamicUnReadCount(userId,date);
	}

	@Override
	public PaginatorResponse<CustomerTradeMsgResponse> queryCustomerTradeMsg(String customerId, String userId, Page<CustomerTradeMsgResponse> page) {
		PaginatorResponse<CustomerTradeMsgResponse> paginatorResponse = new PaginatorResponse<CustomerTradeMsgResponse>();
		paginatorResponse.setDatas(investRecordMapper.queryCustomerTradeMsg(customerId, userId, page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@RejectedExecuteRetry
	@Override
	public boolean updateInvestRecordEndTimeByProductId(CimProductInvestRecord investRecord)throws Exception {
		return investRecordMapper.updateInvestRecordEndTimeByProductId(investRecord)>0;
	}

	
	@Override
	public Map<String, String> getInvestRecordCounts(String userId) {
		return investRecordMapper.getInvestRecordCounts(userId);
	}

	@Override
	public int queryUserPlatfromInvestCount(String userId, String platfromId) {
		return investRecordMapper.queryUserPlatfromInvestCount(userId, platfromId);
	}

	@Override
	public CustomerInvestProfitResponse getInvestProfit(String userId) {
		return investRecordMapper.getInvestProfit(userId);
	}

	@Override
	public void updateBorrowRefuseInvestProfit(String investId) {
		investRecordMapper.updateBorrowRefuseInvestProfit(investId);
	}

	@Override
	public List<CfpOrderResponse> selectNewestTop200() {
		return investRecordMapper.selectNewestTop200();
	}

	@Override
	public PaginatorResponse<MyInvestrecordResponse> myInvestrecord(AppRequestHead appRequestHead,MyInvestrecordRequest myInvestrecordRequest) {
		
		//获取  0-在投产品 或者  1-已到期产品 最后一次调用的时间
		String name_investrecord_myinvestrecord = null;
		if(myInvestrecordRequest.getInvestType() == 0){
			name_investrecord_myinvestrecord = ApiInvokeLogConstant.NAME_INVESTRECORD_MYINVESTRECORD_0;
		} else {
			name_investrecord_myinvestrecord = ApiInvokeLogConstant.NAME_INVESTRECORD_MYINVESTRECORD_1;
		}
		
		String userId =  JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());//获取当前用户
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(name_investrecord_myinvestrecord, userId, AppTypeEnum.CHANNEL.getKey());//获取用户最后一次调取接口的时间
		String lastReaddate = apiInvokeLog!=null ?  DateUtils.format(apiInvokeLog.getChgTime()): DateUtils.format(DateUtils.parse("1990-01-01 00:00:00"));
		myInvestrecordRequest.setUserId(userId);
		myInvestrecordRequest.setLastReaddate(lastReaddate);
		PaginatorResponse<MyInvestrecordResponse> paginatorResponse = new PaginatorResponse<MyInvestrecordResponse>();
		Page<MyInvestrecordResponse> page = new Page<MyInvestrecordResponse>(myInvestrecordRequest.getPageIndex(), myInvestrecordRequest.getPageSize());
		List<MyInvestrecordResponse> myInvestrecordResponses = investRecordMapper.myInvestrecord(myInvestrecordRequest,page);
		paginatorResponse.setDatas(myInvestrecordResponses);
		paginatorResponse.setValuesByPage(page);
		sysApiInvokeLogService.updateApiInvokeLog(name_investrecord_myinvestrecord, userId,AppTypeEnum.CHANNEL.getKey());//更新用户最后一次调取接口的时间
		return paginatorResponse;
	}

	@Override
	public Integer queryNotReadRecord(String userId, int investType) {
		//获取  0-在投产品 或者  1-已到期产品 最后一次调用的时间
		String name_investrecord_myinvestrecord = null;
		if(investType == 0){
			name_investrecord_myinvestrecord = ApiInvokeLogConstant.NAME_INVESTRECORD_MYINVESTRECORD_0;
		} else {
			name_investrecord_myinvestrecord = ApiInvokeLogConstant.NAME_INVESTRECORD_MYINVESTRECORD_1;
		}
		SysApiInvokeLog apiInvokeLog = sysApiInvokeLogService.queryApiInvokeLog(name_investrecord_myinvestrecord, userId, AppTypeEnum.CHANNEL.getKey());
		String lastReaddate = apiInvokeLog!=null ?  DateUtils.format(apiInvokeLog.getChgTime()): DateUtils.format(DateUtils.parse("1990-01-01 00:00:00"));
		
		return investRecordMapper.queryNotReadRecord(userId,investType,lastReaddate);
	}

	@Override
	public GoodTransResp getGoodTrans(String userId) {
		return investRecordMapper.getGoodTrans(userId);
	}

	@Override
	public PaginatorResponse<GoodTransResp> queryOldGoodTransList(Page<GoodTransResp> page,
			Map<String, Object> conditions) {
		PaginatorResponse<GoodTransResp> goodTransResponse = new PaginatorResponse<GoodTransResp>();
		List<GoodTransResp> goodList = investRecordMapper.queryOldGoodTransList(page,conditions);
		goodTransResponse.setDatas(goodList);
		goodTransResponse.setValuesByPage(page);
		return goodTransResponse;
	}

	@Override
	public GoodTransResp getGoodTransByInvestId(String billId) {
		return investRecordMapper.getGoodTransByInvestId(billId);
	}

	@Override
	public Date selectTranRecordDate(String userId) {
		return investRecordMapper.selectTranRecordDate(userId);
	}

	@Override
	public Date selectPaymentDate(String userId) {
		return investRecordMapper.selectPaymentDate(userId);
	}

	@Override
	public BigDecimal queryMonthIncome(String thisMonth, String userId) {
		return investRecordMapper.queryMonthIncome(thisMonth,userId);
	}

	@Override
	public Date newTranRecordDate(String userId) {
		return investRecordMapper.newTranRecordDate(userId);
	}

	@Override
	public BigDecimal queryAllTotalIncome(String userId) {
		// TODO Auto-generated method stub
		return investRecordMapper.queryAllTotalIncome(userId);
	}

	@Override
	public BigDecimal querySignRecordAmount(String userId,Date signTime) {
		String startTime = DateUtils.format(signTime);
		return investRecordMapper.querySignRecordAmount(userId,startTime);
	}

	@Override
	public void updatePlatformBouns(PlatformBounsRequest platformBounsRequest) {
		investRecordMapper.updatePlatformBouns(platformBounsRequest);
	}

	@Override
	public BigDecimal querySignRecordAmount460(String userId, Date signTime) {
		String startTime = DateUtils.format(signTime);
		return investRecordMapper.querySignRecordAmount460(userId,startTime);
	}

	@Override
	public List<HotInvestResponse> queryHotInvestList() {
		return investRecordMapper.queryHotInvestList();
	}

	@Override
	public int queryUserInvestCount(String userId) {
		// TODO Auto-generated method stub
		return investRecordMapper.queryUserInvestCount(userId);
	}

     @Override
     public int investingOrgCount(String userId, Boolean isGrayUser) {
		 return investRecordMapper.investingOrgCount(userId,isGrayUser);
     }

     @Override
     public List<HomePageInvestResponse> homepageInvestList() {
		return investRecordMapper.homepageInvestList();
     }

 }
