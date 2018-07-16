package com.linkwee.act.redpacket.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.Validate;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.linkwee.act.redpacket.model.ActRedpacket;
import com.linkwee.act.redpacket.model.ActRedpacketDetail;
import com.linkwee.act.redpacket.model.ActRedpacketSendRecord;
import com.linkwee.act.redpacket.model.RedpacketImportModel;
import com.linkwee.act.redpacket.service.ActRedpacketRuleService;
import com.linkwee.act.redpacket.service.ActRedpacketSendRecordService;
import com.linkwee.act.redpacket.service.ActRedpacketService;
import com.linkwee.act.redpacket.service.ActRedpacketUseRecordService;
import com.linkwee.core.Import.PoiImport;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.AcAccountBindMapper;
import com.linkwee.web.dao.ActRedpacketDetailMapper;
import com.linkwee.web.dao.ActRedpacketMapper;
import com.linkwee.web.dao.ActRedpacketSendRecordMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.enums.PersonalMsgTypeEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.acc.AcAccountBind;
import com.linkwee.web.model.acc.AcAccountRecharge;
import com.linkwee.web.model.vo.InvestInfo;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.model.weixin.WeiXinMsgRequest;
import com.linkwee.web.request.act.RedPacketInfoRequest;
import com.linkwee.web.response.act.RedpacketListResponse;
import com.linkwee.web.response.act.RedpacketStatisticsResponse;
import com.linkwee.web.response.orgInfo.OrgInfoResponse;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.InvestRecordAware;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.WeiXinMsgService;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.helper.DateUtils;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.helper.ThreadpoolService;
import com.linkwee.xoss.rbac.PermissionSign;
import com.linkwee.xoss.util.InterceptUtility;
import com.linkwee.xoss.util.RandomTextCreator;


 /**
 * 
 * @描述：ActRedpacketService 服务实现类
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年07月31日 13:13:55
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actRedpacketService")
public class ActRedpacketServiceImpl extends GenericServiceImpl<ActRedpacket, Long> implements ActRedpacketService,InvestRecordAware{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActRedpacketServiceImpl.class);
	

	@Autowired
	private ActRedpacketMapper actRedpacketMapper;
	
	@Autowired
	private ActRedpacketDetailMapper  redpacketDetailMapper;
	
	@Autowired
	private ActRedpacketRuleService redpacketRuleService;
	
	@Autowired
	private ActRedpacketUseRecordService redpacketUseRecordService;
	
	@Autowired
	private AcAccountBindService accountBindService;
	
	@Autowired
	private CrmUserInfoService userInfoService;
	
	@Autowired
	private SmMessageQueueService messageQueueService;
	
	@Autowired
	private PushMessageHelper pushMessageHelper;
	
	@Autowired
	private SysConfigService sysConfigService;
	
	@Resource
	private ConfigHelper configHelper;

	@Autowired
	private ActRedpacketSendRecordService redpacketSendRecordService;
	
	@Resource
	private AcAccountBindMapper accountbindMapper;
	
	@Value("${REDPACKET_INSERT_NUMBER}") 
	private int redpacket_insert_numer;
	
	@Value("${REDPACKET_NUMBER_THAN_USE_SUBSECTION}") 
	private int redpacket_number_than_use_subsection;
	
	@Resource
	private CrmInvestorService crmInvestorService;
	
	@Resource
	private WeiXinMsgService weiXinMsgService;
	
	@Resource
	private CimOrginfoService cimOrginfoService;
	
	@Resource
    private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private ActRedpacketService actRedpacketService;
	
	@Autowired
	private ActRedpacketSendRecordMapper redpacketSendRecordMapper;
	
	private SendRedpacketCallback customerSendRedpacketCallback = new SendRedpacketCallback(){
		public void setRedpacketAttr(CrmUserInfo userInfo,ActRedpacketDetail redpacket,Set<String> mobileOrUserIds,Set<String> userIds) throws Exception{
			redpacket.setUserId(userInfo.getUserId());
			redpacket.setUserMobile(userInfo.getMobile());
			redpacket.setUserName(userInfo.getUserName());
			redpacket.setStatus(2);
			mobileOrUserIds.add(userInfo.getMobile());
			if(userIds != null){
			userIds.add(userInfo.getUserId());
			}
		}

		@Override
		public void sendMsgs(Set<String> mobiles,Set<String> userIds,String content) {
			try{
				messageQueueService.batchSendMessage(mobiles,AppTypeEnum.INVESTOR, MsgModuleEnum.RECIVEREDPAPERBYSYS,content);
				if(userIds != null && userIds.size() > 0) {
					pushMessageHelper.BatchSinglePush(AppTypeEnum.INVESTOR, SmsTypeEnum.MYREDPACKET_INC, userIds, "发红包啦", messageQueueService.queryMessageTemplate(MsgModuleEnum.RECIVEREDPAPERBYSYS, AppTypeEnum.INVESTOR, content), null, true, PersonalMsgTypeEnum.REDPACKET_INV);
				}
			}catch(Exception e){
				LOGGER.warn("Send customer Redpacket Msg Exception ", e);
			}
		}

		@Override
		public String getMsgContent(ActRedpacket redpacket, int sendNum) {
			return redpacket.getMoney().setScale(2, BigDecimal.ROUND_DOWN).toString();
		}

		@Override
		public void sendMsgs(Set<String> mobileOrUserIds, String content,
				Map<String, Object> urlParm) {
			
		}
		
	};
	
	private SendRedpacketCallback lcsSendRedpacketCallback = new SendRedpacketCallback(){
		@Override
		public void setRedpacketAttr(CrmUserInfo userInfo,ActRedpacketDetail redpacket,Set<String> mobileOrUserIds,Set<String> userIds) throws Exception{
			redpacket.setCfplannerId(userInfo.getUserId());
			redpacket.setCfplannerMobile(userInfo.getMobile());
			redpacket.setCfplannerName(userInfo.getUserName());
			redpacket.setStatus(1);
			mobileOrUserIds.add(userInfo.getUserId());
			if(userIds != null){
				userIds.add(userInfo.getUserId());
			}
		}

		@Override
		public void sendMsgs(Set<String> mobileOrUserIds,Set<String> userIds,String content) {
			pushMessageHelper.BatchSinglePush(AppTypeEnum.CHANNEL,SmsTypeEnum.LCSRECEIVESYSREDPAPER,mobileOrUserIds, "系统消息",content,null, true,PersonalMsgTypeEnum.REDPACKET_INV);
		}

		@Override
		public String getMsgContent(ActRedpacket redpacket, int sendNum) {
			return String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_LCSRECEIVESYSREDPAPER),sendNum,redpacket.getMoney().setScale(2, BigDecimal.ROUND_DOWN));
		}

		@Override
		public void sendMsgs(Set<String> mobileOrUserIds, String content,
				Map<String, Object> urlParm) {
			pushMessageHelper.BatchSinglePush(AppTypeEnum.CHANNEL,SmsTypeEnum.LCSRECEIVESYSREDPAPER,mobileOrUserIds, "系统消息",content,urlParm, true,PersonalMsgTypeEnum.REDPACKET_INV);
			
		}
		
	};
	private SendRedpacketCallback lcsPaybackSendRedpacketCallback = new SendRedpacketCallback(){
		@Override
		public void setRedpacketAttr(CrmUserInfo userInfo,ActRedpacketDetail redpacket,Set<String> mobileOrUserIds,Set<String> userIds) throws Exception{
			redpacket.setCfplannerId(userInfo.getUserId());
			redpacket.setCfplannerMobile(userInfo.getMobile());
			redpacket.setCfplannerName(userInfo.getUserName());
			redpacket.setStatus(1);
			mobileOrUserIds.add(userInfo.getUserId());
			if(userIds != null){
				userIds.add(userInfo.getUserId());
			}
		}

		@Override
		public void sendMsgs(Set<String> mobileOrUserIds,Set<String> userIds,String content) {
			pushMessageHelper.BatchSinglePush(AppTypeEnum.CHANNEL, SmsTypeEnum.LCUSTOMERBIGAMOUNTRETURN,mobileOrUserIds, "系统消息",content,null, true,PersonalMsgTypeEnum.PAYMENTRETURN);
			
		}
		@Override
		public void sendMsgs(Set<String> mobileOrUserIds,String content,Map<String,Object> urlParm) {
			pushMessageHelper.BatchSinglePush(AppTypeEnum.CHANNEL, SmsTypeEnum.LCUSTOMERBIGAMOUNTRETURN,mobileOrUserIds, "系统消息",content,urlParm, true,PersonalMsgTypeEnum.PAYMENTRETURN);
			
		}

		@Override
		public String getMsgContent(ActRedpacket redpacket, int sendNum) {
			return String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_LCSRECEIVESYSREDPAPER),sendNum,redpacket.getMoney().setScale(2, BigDecimal.ROUND_DOWN));
		}
		
	};
	
	@Override
    public GenericDao<ActRedpacket, Long> getDao() {
        return actRedpacketMapper;
    }

	@Override
	public boolean isExistRedpacket(String redpacketId) {
		return actRedpacketMapper.isExistRedpacket(redpacketId);
	}
	
	

	@Override
	public boolean useRedpacket(InvestRecordWrapper investRecord,ActRedpacketDetail redpacket) throws Exception {
		LOGGER.debug("start use redpacket investRecordId={},redpacketId ={}",investRecord.getInvestId(), redpacket.getRedpacketDetailId());
		boolean success = ObjectUtils.equals(redpacketDetailMapper.useRedpacket(redpacket.getRedpacketDetailId(),redpacket.getUpdateTime()), 1);
		if(success){
			LOGGER.debug("use redpacket succes investRecordId={},redpacketId ={}",investRecord.getInvestId(), redpacket.getRedpacketDetailId());
			String rechargeId = recharge(redpacket);
			LOGGER.debug("redpacket recharge  success rechargeId={}", rechargeId);
			redpacketUseRecordService.insertRedpacketUseRecord(rechargeId, investRecord, redpacket);
		}
		return success;
	}
    
	private String recharge(ActRedpacketDetail redpacketDetail) throws Exception{
		AcAccountRecharge recharge = new AcAccountRecharge();
		recharge.setRedpacketId(redpacketDetail.getRedpacketDetailId());
		recharge.setTransAmount(redpacketDetail.getMoney());
		recharge.setUserId(redpacketDetail.getUserId());
		recharge.setUserType(2);
		recharge.setTransType(4);
		recharge.setRemark("投资返现红包到账");
		return accountBindService.accountRecharge(recharge);
	}

	@Override
	public DataTableReturn getRedpacketList(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		Page<RedpacketListResponse> page = new Page<RedpacketListResponse>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<RedpacketListResponse> list = actRedpacketMapper.getRedpacketList(dt,page);
		
		Subject currentUser = SecurityUtils.getSubject();
		String redpacketSendPermission = "0";
		String redpacketEditPermission = "0";
		if(currentUser.isPermitted(PermissionSign.REDPACKET_SEND)) {
			redpacketSendPermission = "1";
		}
		if(currentUser.isPermitted(PermissionSign.REDPACKET_EDIT)) {
			redpacketEditPermission = "1";
		}
		for(RedpacketListResponse temp : list){			
			temp.setRedpacketSendPermission(redpacketSendPermission);
			temp.setRedpacketEditPermission(redpacketEditPermission);
		}
		
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void insertRedpacket(RedPacketInfoRequest redPacketInfo)throws Exception {
		try{
			Date date = new Date();
			boolean result =  insert(redPacketInfo, date);
			if(result){
				result = redpacketRuleService.insertRedpacketRule(redPacketInfo, date);
			}
			if(!result)throw new ServiceException("新增失败");
		}catch(Exception e){
			LOGGER.error("insertRedpacket exception redPacketInfo={}",redPacketInfo, e);
			throw e;
		}
	}

	private boolean insert(RedPacketInfoRequest redPacketInfo,Date date){
		ActRedpacket redpacket = createRedpacket(StringUtils.getUUID(), redPacketInfo, date);
		redpacket.setCreateTime(date);
		boolean result = insert(redpacket)>0;
		if(result)redPacketInfo.setRedpacketId(redpacket.getRedpacketId());
		return result;
	}
	
	private ActRedpacket createRedpacket(String redpacketId,RedPacketInfoRequest redPacketInfo,Date date){
		ActRedpacket redpacket = new ActRedpacket();
		redpacket.setRedpacketId(redpacketId);
		redpacket.setName(redPacketInfo.getName());
		redpacket.setMoney(new BigDecimal(redPacketInfo.getMoney()));
		redpacket.setRemark(redPacketInfo.getRemark());
		redpacket.setIsActivity(StringUtils.isNotBlank(redPacketInfo.getActivityId()) ? 1 : 0);
		redpacket.setActivityCode(redPacketInfo.getActivityId());
		redpacket.setActivityName(redPacketInfo.getActivityName());
		redpacket.setUpdateTime(date);
		redpacket.setOperator(redPacketInfo.getOperator());
		return redpacket;
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void updateRedpacket(RedPacketInfoRequest redPacketInfo)throws Exception {
		String redpacketId = redPacketInfo.getRedpacketId();
		if(StringUtils.isBlank(redpacketId) || !isExistRedpacket(redpacketId))throw new ServiceException("红包不存在!");
		
		Date date= new Date();
		ActRedpacket redpacket = createRedpacket(redPacketInfo.getRedpacketId(), redPacketInfo,date);
		boolean result = actRedpacketMapper.updateRedpacket(redpacket)>0;
		if(result){
			result = redpacketRuleService.updateRedpacketRule(redPacketInfo, date);
		}
		if(!result)throw new ServiceException("更新失败");
	}
	
	/**
	 * 根据编号查询红包
	 * @param redpacketId
	 * @return
	 */
	@Override
	public ActRedpacket getRedpacket(String redpacketId){
		List<ActRedpacket> redpackets = getRedpackets(redpacketId);
		return redpackets==null||redpackets.isEmpty()?null:redpackets.get(0);
	}
	
	
	@Override
	public List<ActRedpacket> getRedpackets(String... redpacketId) {
		return actRedpacketMapper.getRedpackets(Lists.newArrayList(redpacketId));
	}
	

	@Override
	public boolean isSendRedpacket(String redpacketId) throws Exception{
		ActRedpacket redpacket = getRedpacket(redpacketId);
		if(redpacket==null)throw new ServiceException("红包不存在");
		return redpacket.getSendCount()>0;
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Set<String> sendCustomerRedPacket(MultipartFile file, String redpacketId,Integer sendNum, Date expiresDate,String operator) throws Exception {
		return sendRedpacekt(file, redpacketId, sendNum, expiresDate,operator,customerSendRedpacketCallback);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Set<String> sendLcsRedPacket(MultipartFile file, String redpacketId,Integer sendNum, Date expiresDate,String operator)throws Exception {
		return sendRedpacekt(file, redpacketId, sendNum, expiresDate,operator,lcsSendRedpacketCallback);
	}
	
	private List<RedpacketImportModel> getRedpacketImportModels(MultipartFile file)throws Exception{
		 InputStream inputStream = file.getInputStream();
		 return PoiImport.dataImport(inputStream, RedpacketImportModel.class);
	}
	
	private Set<String> sendRedpacekt(MultipartFile file, String redpacketId,final Integer sendNum, final Date expiresDate,final String operator,final SendRedpacketCallback callback)throws Exception{
		long s = System.currentTimeMillis();
		LOGGER.debug("start Redpacket");
		Validate.notEmpty(redpacketId,"红包编号不能为空");
		final ActRedpacket redpacket = getRedpacket(redpacketId);
		Validate.notNull(redpacket,"红包不存在");
		//解析导入模板
		final List<RedpacketImportModel>  redpacketImportModels = getRedpacketImportModels(file);
		Validate.notEmpty(redpacketImportModels,"导入用户为空");
		final String sendId = StringUtils.getUUID();
		final Date time = new Date();
		
		final Set<ActRedpacketDetail> redpacketDetails = Sets.newConcurrentHashSet();
		final Set<String> msg  = Sets.newConcurrentHashSet();
		final Set<String> mobileOrUserIds  = Sets.newConcurrentHashSet();
		final Set<String> userIds  = Sets.newConcurrentHashSet();
		List<List<RedpacketImportModel>>  redpacketImportModelss = Lists.newArrayList();
		// 按 redpacket_insert_numer的4倍进行 用户数据  分段
		InterceptUtility.subsection(redpacketImportModels, redpacketImportModelss,redpacket_insert_numer*4);
		
		
		final CountDownLatch countDownLatch = new CountDownLatch(redpacketImportModelss.size());
		/**
		 * 分批次 进行多线程创建红包
		 */
		for (final List<RedpacketImportModel> list : redpacketImportModelss) {
			ThreadpoolService.execute(new Runnable() {
				@Override
				public void run() {
					try{
						List<String> sendRedpacketUserMobiles = Lists.newArrayListWithCapacity(list.size());
						for (RedpacketImportModel redpacketImportModel : list) {
							sendRedpacketUserMobiles.add(redpacketImportModel.getMobile());
						}
						//查询本批次发放的所有用户信息 避免单次慢查询
						List<CrmUserInfo> userInfos =  userInfoService.queryUserListByMobileList(sendRedpacketUserMobiles);
						if(userInfos==null )userInfos =  Lists.newArrayListWithCapacity(1);
						
						Map<String, CrmUserInfo> userMaps = Maps.newHashMapWithExpectedSize(userInfos.size());
						//映射用户手机号码与用户信息 方便查询用户信息
						for (CrmUserInfo crmUserInfo : userInfos) {
							userMaps.put(crmUserInfo.getMobile(), crmUserInfo);
						}
						//清空无用数据 
						sendRedpacketUserMobiles=null;
						userInfos = null;
						
						for (RedpacketImportModel redpacketImportModel : list) {
							CrmUserInfo crmUserInfo = userMaps.get(redpacketImportModel.getMobile());
							if(crmUserInfo ==null){
								msg.add("用户不存在_"+redpacketImportModel.getMobile());
								continue;
							}
							//创建红包
							redpacketDetails.add(createRedpacketDetail(crmUserInfo, redpacket, sendId, expiresDate, time, callback,mobileOrUserIds,userIds));
						}
					}catch(Exception e){
						LOGGER.warn("创建红包",e);
					}finally{
						countDownLatch.countDown();
					}
				}
			});
		}
		countDownLatch.await();
		
		//可发放红包为空 返回
		if(redpacketDetails.isEmpty()) return msg;
		
		//一次红包 * 发放数量 = 发放总红包数据
		int size = redpacketDetails.size() * sendNum;
		
		List<List<ActRedpacketDetail>> redpacketss = Lists.newArrayList();
		//红包总数据大于  redpacket_number_than_use_subsection 
		//true : 分批插入  | false :  一次插入
		if(size > redpacket_number_than_use_subsection ){
			InterceptUtility.subsection(new ArrayList<ActRedpacketDetail>(redpacketDetails),redpacketss,redpacket_insert_numer);
		}else{
			redpacketss.add(new ArrayList<ActRedpacketDetail>(redpacketDetails));
		}
		//批量插入红包
		for (int i = 0; i < sendNum; i++) {
			for (List<ActRedpacketDetail> redpacketList : redpacketss) {
				for (ActRedpacketDetail redpacketDetail : redpacketList) {
					redpacketDetail.setRedpacketDetailId(StringUtils.getUUID());
				}
				redpacketDetailMapper.inserts(redpacketList);
			}
		}
		redpacketss = null;
		//保存发放信息
		saveRedpacketSendRecord(redpacket, sendId, redpacket.getMoney().multiply(new BigDecimal(size)), size,mobileOrUserIds.size(), expiresDate, time,operator);
		updateSendRedpacket(redpacketId, operator, time);
		LOGGER.debug("send Redpacket end time = {}",  System.currentTimeMillis()-s);
		//异步发送消息
		ThreadpoolService.execute(new Runnable(){
			@Override
			public void run() {
				try{
					callback.sendMsgs(mobileOrUserIds,userIds,callback.getMsgContent(redpacket, sendNum));
				}catch(Exception e){
					LOGGER.warn("sendMsgs exception",e);
				}
			}});
		
		return msg;
	}

	private void updateSendRedpacket(String redpacketId, final String operator, final Date time) {
		//更新红包信息
		ActRedpacket redpacketNew = new ActRedpacket();
		redpacketNew.setRedpacketId(redpacketId);
		redpacketNew.setUpdateTime(time);
		redpacketNew.setOperator(operator);
		actRedpacketMapper.updateSendRedpacket(redpacketNew);
	}
	
	

	
	/**
	 * 
	 * @param redpacket
	 * @param sendId 发放编号
	 * @param money 发放金额
	 * @param count 发放数量
	 * @param numer 发放人数
	 * @param expiresDate 过期时间
	 * @param time 时间
	 * @param operator 
	 * @return
	 */
	private void saveRedpacketSendRecord(ActRedpacket redpacket, String sendId,BigDecimal money,Integer count,Integer numer,Date expiresDate,Date time,String operator){
		ActRedpacketSendRecord sendRecord = new ActRedpacketSendRecord();
		sendRecord.setRedpacketId(redpacket.getRedpacketId());
		sendRecord.setSendId(sendId);
		sendRecord.setExpiresTime(expiresDate);
		sendRecord.setExpiresStatus(0);
		sendRecord.setSendMoney(money);
		sendRecord.setSendCount(count);
		sendRecord.setSendNumber(numer);
		sendRecord.setSendTime(time);
		sendRecord.setOperator(operator);
		redpacketSendRecordService.insert(sendRecord);
	}
	
	/**
	 * 创建红包明细
	 * @param mobile
	 * @param redpacket
	 * @param expiresDate
	 * @param time
	 * @param callback
	 * @return
	 * @throws Exception
	 */
	private ActRedpacketDetail createRedpacketDetail(CrmUserInfo userInfo,ActRedpacket redpacket,String sendId, Date expiresDate,Date time,SendRedpacketCallback callback,Set<String> mobileOrUserIds,Set<String> userIds) throws Exception{
		ActRedpacketDetail redpacketDetail = new ActRedpacketDetail();
		redpacketDetail.setRedpacketId(redpacket.getRedpacketId());
		redpacketDetail.setSendId(sendId);
		redpacketDetail.setName(redpacket.getName());
		redpacketDetail.setMoney(redpacket.getMoney());
		redpacketDetail.setRemark(redpacket.getRemark());
		redpacketDetail.setType(redpacket.getType());
		redpacketDetail.setExpiresDate(expiresDate);
		callback.setRedpacketAttr(userInfo, redpacketDetail,mobileOrUserIds,userIds);
		redpacketDetail.setCreateTime(time);
		redpacketDetail.setUpdateTime(time);
		return redpacketDetail;
	}

	
	@Override
	public RedPacketInfoRequest getRedPacketInfo(String redpacketId) {
		RedPacketInfoRequest redPacketInfo = new RedPacketInfoRequest();
		ActRedpacket redpacket = getRedpacket(redpacketId);
		if(redpacket==null)throw new ServiceException("红包不存在");
		setRedPacketInfo(redPacketInfo, redpacket);
		redpacketRuleService.getRedPacketRuleInfo(redPacketInfo, redpacketId);
		return redPacketInfo;
	}
	
	private void setRedPacketInfo(RedPacketInfoRequest redPacketInfo,ActRedpacket redpacket){
		redPacketInfo.setName(redpacket.getName());
		redPacketInfo.setMoney(redpacket.getMoney().doubleValue());
		redPacketInfo.setRemark(redpacket.getRemark());
	}

	@Override
	public RedpacketStatisticsResponse getRedpacketStatistics(String date) {
		if(StringUtils.isBlank(date)){
			date = DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
		}
		if(StringUtils.isBlank(date)) return null;
		Date start = DateUtils.parse(Joiner.on(" ").join(new Object[]{date,"00:00:00"}));
		Date end =DateUtils.parse(Joiner.on(" ").join(new Object[]{date,"23:59:59"}));
		return actRedpacketMapper.getRedpacketStatistics(start, end);
	}
	
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void  customerRegisterRedPacekt(CrmUserInfo userInfo)throws Exception {
		String msg = sysConfigService.getValuesByKey(SysConfigConstant.REGISTER_CUSTOMER_REDPACEKT_MSG);
		registerRedPacekt(userInfo, customerSendRedpacketCallback,StringUtils.isBlank(msg)?"一组580.00":msg);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void  lcsRegisterRedPacekt(CrmUserInfo userInfo)throws Exception {
		String msg = sysConfigService.getValuesByKey(SysConfigConstant.REGISTER_LCS_REDPACEKT_MSG);
		registerRedPacekt(userInfo, lcsSendRedpacketCallback,StringUtils.isBlank(msg)?"亲，猎财大师给您发了一组580.00元的投资返现红包，快去分给您的客户吧":msg);
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	private void registerRedPacekt(CrmUserInfo userInfo,SendRedpacketCallback sendRedpacketCallback,String msg) throws Exception {
		LOGGER.info("registerRedPacekt userInfo={}",userInfo);
		String switchStr = sysConfigService.getValuesByKey(SysConfigConstant.REGISTER_REDPACEKT_SWITCH);
		if(StringUtils.isBlank(switchStr))return;	
		boolean isSwitch = Boolean.valueOf(switchStr);
		if(!isSwitch)return;
		//获取红包配置
		String  registerRedpacektIds = configHelper.getValue(SysConfigConstant.REGISTER_REDPACEKT_IDS);
		String  registerRedpacektSendIds = configHelper.getValue(SysConfigConstant.REGISTER_REDPACEKT_SEND_IDS);
		if(StringUtils.isBlank(registerRedpacektIds)||StringUtils.isBlank(registerRedpacektSendIds))return;	
		
		String[] registerRedpacektIdArray = org.apache.commons.lang.StringUtils.split(registerRedpacektIds, ",");
		String[] registerRedpacektSendIdArray = org.apache.commons.lang.StringUtils.split(registerRedpacektSendIds, ",");
		//查询注册红包
		List<ActRedpacket> redpackets =  getRedpackets(registerRedpacektIdArray);
		if(redpackets==null || redpackets.isEmpty())return;
		//发送记录
		List<ActRedpacketSendRecord> redpacketSends = redpacketSendRecordService.getRedpacketSendRecords(registerRedpacektSendIdArray);
		if(redpacketSends==null || redpacketSends.isEmpty())return;
		Map<String, ActRedpacketSendRecord> redpacketSendRecordMaps =  Maps.newHashMapWithExpectedSize(redpacketSends.size());
		for (ActRedpacketSendRecord redpacketSendRecord : redpacketSends) {
			redpacketSendRecordMaps.put(redpacketSendRecord.getRedpacketId(), redpacketSendRecord);
		}
		List<ActRedpacketDetail> redpacketDetails = Lists.newArrayListWithCapacity(redpackets.size());
		Set<String> mobiles = Sets.newHashSet();
		Date time = new Date();
		//创建红包
		for (ActRedpacket redpacket : redpackets) {
			ActRedpacketSendRecord redpacketSendRecord = redpacketSendRecordMaps.get(redpacket.getRedpacketId());
			if(redpacketSendRecord==null) continue;
			
			Date expiresTime = redpacketSendRecord.getExpiresTime();
			if( expiresTime == null ){
				expiresTime = DateTime.now().plusDays(redpacketSendRecord.getExpiresDay()).toDate();
			}
			
			ActRedpacketDetail redpacketDetail =createRedpacketDetail(userInfo, redpacket, redpacketSendRecord.getSendId(), expiresTime, time, sendRedpacketCallback, mobiles,null);
			redpacketDetail.setRedpacketDetailId(StringUtils.getUUID());
			redpacketDetails.add(redpacketDetail);
		}
		//发送红包
		if(!redpacketDetails.isEmpty()){
			redpacketDetailMapper.inserts(redpacketDetails);
			redpacketSendRecordService.updateSendRedpackets(redpacketDetails);
			actRedpacketMapper.updateSendRedpackets(redpacketSendRecordMaps.keySet());
			try{
				sendRedpacketCallback.sendMsgs(mobiles,null,msg);
			}catch(Throwable e){
				LOGGER.warn("Send  Redpacket Msg Exception ", e);
			}
			
		};
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void  customerRedPacekt(CrmUserInfo userInfo,String redpacektId,String sendRedpacektId,InvestInfo investInfo,int type)throws Exception {
		sendRedPacekt(userInfo, redpacektId,sendRedpacektId,customerSendRedpacketCallback,investInfo,type);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void  lcsRedPacekt(CrmUserInfo userInfo,String redpacektId,String sendRedpacektId,InvestInfo investInfo,int type)throws Exception {
		sendRedPacekt(userInfo, redpacektId,sendRedpacektId,lcsPaybackSendRedpacketCallback, investInfo,type);
	}
	
	
	private void sendRedPacekt(CrmUserInfo userInfo,String redpacektId,String sendRedpacektId,SendRedpacketCallback sendRedpacketCallback,InvestInfo investInfo,int type) throws Exception {
		LOGGER.info("sendRedPacekt userInfo={}",userInfo);
	
		if(StringUtils.isBlank(redpacektId)||StringUtils.isBlank(sendRedpacektId))return;	
		
		//查询注册红包
		List<ActRedpacket> redpackets =  getRedpackets(new String[]{redpacektId});
		if(redpackets==null || redpackets.isEmpty())return;
		ActRedpacket redpacket = redpackets.get(0);
		//发送记录
		List<ActRedpacketSendRecord> redpacketSends = redpacketSendRecordService.getRedpacketSendRecords(new String[]{sendRedpacektId});
		if(redpacketSends==null || redpacketSends.isEmpty())return;
		ActRedpacketSendRecord redpacketSendRecord  = redpacketSends.get(0);
		
		List<ActRedpacketDetail> redpacketDetails = Lists.newArrayListWithCapacity(redpackets.size());
		Set<String> mobiles = Sets.newHashSet();
		Date time = new Date();
		//创建红包
		Date expiresTime = redpacketSendRecord.getExpiresTime();
		if( expiresTime == null ){
			expiresTime = DateTime.now().plusDays(redpacketSendRecord.getExpiresDay()).toDate();
		}
		
		ActRedpacketDetail redpacketDetail =createRedpacketDetail(userInfo, redpacket, redpacketSendRecord.getSendId(), expiresTime, time, sendRedpacketCallback, mobiles,null);
		redpacketDetail.setRedpacketDetailId(StringUtils.getUUID());
		redpacketDetails.add(redpacketDetail);
		
		//发送红包
		if(!redpacketDetails.isEmpty()){
			redpacketDetailMapper.inserts(redpacketDetails);
			redpacketSendRecordService.updateSendRedpackets(redpacketDetails);
			actRedpacketMapper.updateSendRedpackets(Sets.newHashSet(redpacket.getRedpacketId()));
			try{
				String msg = "";
				boolean isLcsSendRedpackage = type == 1 ? true : false;
				if(isLcsSendRedpackage){//给理财师发红包				
					 msg = sysConfigService.getValuesByKey(SysConfigConstant.PUSHMESSAGE_LCUSTOMERBIGAMOUNTRETURN_FIX);
					 msg = StringUtils.isBlank(msg)? "亲，猎财大师给您发了一个投资返现红包，快去分给您的客户吧" : 
						String.format(msg,investInfo.getUname()+investInfo.getUmobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"),investInfo.getProductName(),investInfo.getInvestAmt() == null ? "" : investInfo.getInvestAmt().setScale(2, BigDecimal.ROUND_DOWN),redpacket.getMoney().setScale(2, BigDecimal.ROUND_DOWN));
	
					 //给客户及其理财师推送微信消息：即将回款提醒
					 /*OrgInfoResponse org = cimOrginfoService.findOrgInfo(investInfo.getPlatfrom());
					 String platformName = org!=null?org.getOrgName():"";//平台名称
					 String investAmt = NumberUtils.getFormat(investInfo.getInvestAmt(), "0.00")+"元";//回款金额
					 String paymentDate = DateUtils.format(investInfo.getEndTime(),DateUtils.FORMAT_LONG);//回款时间
					 final List<WeiXinMsgRequest> wxList = new ArrayList<WeiXinMsgRequest>();
					 WeiXinMsgRequest wxInvest = new WeiXinMsgRequest();
					 wxInvest.setUseId(investInfo.getUid());
					 wxInvest.setUseType("2");
					 wxInvest.setTemkey(SysConfigConstant.SOON_PAYMENT_REMINDER);//回款提醒(产品剩余3天到期回款)
					 wxInvest.setPlatformName(platformName);//平台名称
					 wxInvest.setProductName(investInfo.getProductName());//产品名称
					 wxInvest.setPaymentDate(paymentDate);//回款时间
					 wxInvest.setAmount(investAmt);//回款金额
					 wxList.add(wxInvest);
					 WeiXinMsgRequest wxCfp = new WeiXinMsgRequest();
					 wxCfp.setUseId(investInfo.getCid());
					 wxCfp.setUseType("1");
					 wxCfp.setTemkey(SysConfigConstant.SOON_PAYMENT_REMINDER);//回款提醒(产品剩余3天到期回款)
					 wxCfp.setPlatformName(platformName);//平台名称
					 wxCfp.setProductName(investInfo.getProductName());//产品名称
					 wxCfp.setPaymentDate(paymentDate);//回款时间
					 wxCfp.setAmount(investAmt);//回款金额
					 wxCfp.setRedPacketAmt(redpacket.getMoney().setScale(2, BigDecimal.ROUND_DOWN).toString());//备注字段:红包金额
					 wxCfp.setUserName(investInfo.getUname()+RandomTextCreator.encrypTion(investInfo.getUmobile()));
					 wxList.add(wxCfp);
					 ThreadpoolService.execute(new Runnable() {
						 @Override
						 public void run() {
							 weiXinMsgService.sendWeiXinMsgListCommon(wxList);
						 }
					 });*/
				}else{ //给投资客户发红包
					msg = redpacket.getMoney().setScale(2, BigDecimal.ROUND_DOWN).toString();
				}
				if(StringUtils.isBlank(msg)) return;
				if(isLcsSendRedpackage){
					Map<String,Object> urlparam = Maps.newHashMap();
					urlparam.put("customerId", investInfo.getUid());
					sendRedpacketCallback.sendMsgs(mobiles,msg,urlparam);
				}else{
					sendRedpacketCallback.sendMsgs(mobiles,null,msg);
				}
				
			}catch(Throwable e){
				LOGGER.warn("Send  Redpacket Msg Exception ", e);
			}
			
		};
	}
	

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void investRecordProcess(InvestRecordWrapper investRecord)throws Exception {
		useRedPacket(investRecord);
	}

	
	private void useRedPacket(InvestRecordWrapper investRecord)throws Exception{
		try{
			LOGGER.debug("start use redpacket investRecordId={}",investRecord.getInvestId());
			List<ActRedpacketDetail> redpackets =	redpacketDetailMapper.getUserUsableRedpackets(investRecord.getUserId());
			if(redpackets==null || redpackets.isEmpty())return;
			Map<String, ActRedpacketDetail> redpacketMpas = Maps.newHashMap();
			//去除相同红包,相同红包以快过期红包为最优
			for (ActRedpacketDetail redpacket : redpackets) {
				if(redpacketMpas.containsKey(redpacket.getRedpacketId())){
					ActRedpacketDetail	existRedpacket =   redpacketMpas.get(redpacket.getRedpacketId());
					if(existRedpacket.getExpiresDate().compareTo(redpacket.getExpiresDate())==-1){
						redpacket = existRedpacket;
					}
				}
				redpacketMpas.put(redpacket.getRedpacketId(), redpacket);
			}
			ActRedpacketDetail optimalRedpacket = redpacketRuleService.matcheRedPacket(investRecord, redpacketMpas);
			if(optimalRedpacket == null) return;
			//红包使用成功 发送消息
			if(useRedpacket(investRecord,optimalRedpacket)){
				try{
					messageQueueService.sendSingleMessage(optimalRedpacket.getUserMobile(), AppTypeEnum.INVESTOR, MsgModuleEnum.INVESTUSEREDPAPER, investRecord.getProductName());
				}catch(Exception e){
					LOGGER.warn("sendSingleMessage exception userMobile={},money={},exception={}",new Object[]{optimalRedpacket.getUserMobile(),optimalRedpacket.getMoney(),e.getMessage()} );
				}
				return;
			}
			
			useRedPacket(investRecord);
		}catch(Exception e){
			LOGGER.error("useRedPacket exception InvestRecordWrapper={}", investRecord,e.getMessage());
			throw e;
		}
		
	}
	
	public void testClass(){
		
		
	}

	@Override
	public void synActBirthdayRedpacketJob() {
		String birthday = new SimpleDateFormat("MMdd").format(new Date());
		List<AcAccountBind> acBindList = accountbindMapper.synActBirthdayReminder(birthday);
		String redpacektIdAndSendId = sysConfigService.getValuesByKey("BIRTHDAYREDPACKET"); 
		String values[] =  org.apache.commons.lang.StringUtils.split(redpacektIdAndSendId,"_");
		List<ActRedpacketDetail> redpacketDetails = Lists.newArrayList();
		String sendId = StringUtils.getUUID();
		for(AcAccountBind bind: acBindList){
			CrmUserInfo userInfo = crmUserInfoService.queryUserInfoByUserId(bind.getUserId());
			if(userInfo==null) continue;
			ActRedpacketDetail redpacketDetail = new ActRedpacketDetail();
			redpacketDetail.setRedpacketDetailId(StringUtils.getUUID());
			redpacketDetail.setRedpacketId(values[0]);
			redpacketDetail.setSendId(sendId);
			redpacketDetail.setName("生日红包");
			redpacketDetail.setMoney(new BigDecimal(18));
			redpacketDetail.setRemark("不限平台,10000元起");
			redpacketDetail.setType(1);
			redpacketDetail.setUserId(userInfo.getUserId());
			redpacketDetail.setUserMobile(userInfo.getMobile());
			redpacketDetail.setUserName(userInfo.getUserName());
			redpacketDetail.setExpiresDate(DateUtils.addDay(new Date(), 30));//有效期为30天
			redpacketDetail.setCreateTime(new Date());
			redpacketDetail.setUpdateTime(new Date());
			redpacketDetail.setStatus(2);//未使用
			redpacketDetails.add(redpacketDetail);
		}
		
		//发送红包
		if(!redpacketDetails.isEmpty()){
			redpacketDetailMapper.inserts(redpacketDetails);
			updateSendRedpacket(values[0], "system", new Date());
			ActRedpacketSendRecord sendRecord = new ActRedpacketSendRecord();
			sendRecord.setRedpacketId(values[0]);
			sendRecord.setSendId(sendId);
			sendRecord.setExpiresTime(DateUtils.addDay(new Date(), 30));
			sendRecord.setExpiresStatus(0);
			sendRecord.setSendMoney(new BigDecimal(18*redpacketDetails.size()));
			sendRecord.setSendCount(redpacketDetails.size());
			sendRecord.setSendNumber(redpacketDetails.size());
			sendRecord.setSendTime(new Date());
			sendRecord.setOperator("system");
			redpacketSendRecordService.insert(sendRecord);
		}
	}

}
