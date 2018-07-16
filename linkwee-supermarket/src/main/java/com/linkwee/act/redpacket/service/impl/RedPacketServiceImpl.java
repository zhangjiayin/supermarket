package com.linkwee.act.redpacket.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.linkwee.act.redpacket.model.ActRedpacket;
import com.linkwee.act.redpacket.model.ActRedpacketBriefDetailAndRule;
import com.linkwee.act.redpacket.model.ActRedpacketDetail;
import com.linkwee.act.redpacket.model.ActRedpacketRuleDetail;
import com.linkwee.act.redpacket.model.SendContext;
import com.linkwee.act.redpacket.service.ActRedpacketRuleService;
import com.linkwee.act.redpacket.service.ActRedpacketSendRecordService;
import com.linkwee.act.redpacket.service.ActRedpacketService;
import com.linkwee.act.redpacket.service.RedPacketService;
import com.linkwee.api.request.act.RedpacketRequest;
import com.linkwee.api.request.act.SendRedPacketRequest;
import com.linkwee.api.request.cim.ProductRedPacketRequest;
import com.linkwee.api.response.act.RedpacketResponse;
import com.linkwee.api.response.cim.ProductDetailResponse;
import com.linkwee.api.response.cim.ProductPageList4Response;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.web.dao.ActRedpacketDetailMapper;
import com.linkwee.web.dao.CimProductInvestRecordMapper;
import com.linkwee.web.enums.ActicityRedPacketEnum;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.enums.PersonalMsgTypeEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.service.CimProductService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.InvestRecordAware;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.helper.ThreadpoolService;
import com.linkwee.xoss.util.AppResponseUtil;

@Service("redPacketService")
public class RedPacketServiceImpl implements RedPacketService,InvestRecordAware{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RedPacketServiceImpl.class);
	
	@Autowired
	private ActRedpacketDetailMapper  redpacketDetailMapper;
	
	
	@Autowired 
	private ActRedpacketService redpacketService;
	
	@Autowired
	private CrmInvestorService investorService;
	
	@Autowired
	private CrmUserInfoService userInfoService;
	
	@Autowired
	private CimProductService productService;
	
	@Autowired
	private ActRedpacketRuleService redpacketRuleService;
	
	@Autowired
	private ActRedpacketSendRecordService redpacketSendRecordService ;
	
	@Autowired
	private SmMessageQueueService messageQueueService;
	
	@Autowired
	private SysMsgService sysMsgService;
	
	@Autowired
	private CimProductInvestRecordMapper productInvestRecordMapper;

	@Autowired
	private PushMessageHelper pushMessageHelper;

	@Autowired
	private SysConfigService sysConfigService;
	
	@Autowired
	private CrmCfplannerService cfplannerService;

	
	@Override
	public Integer queryInvestorRedPacketCount(String userId) {
		return redpacketDetailMapper.queryInvestorRedPacketCount(userId);
	}

	@Override
	public Integer queryCfplannerRedPacketCount(String userId) {
		return redpacketDetailMapper.queryCfplannerRedPacketCount(userId);
	}
	
	
	@Override
	public PaginatorResponse<RedpacketResponse> queryInvestorRedPacket(String userId, RedpacketRequest req) {
		Page<RedpacketResponse> page  = new Page<RedpacketResponse>(req.getPageIndex(),req.getPageSize()>10?10:req.getPageSize()); //默认每页10条
		PaginatorResponse<RedpacketResponse> paginatorResponse = new PaginatorResponse<RedpacketResponse>();
		List<RedpacketResponse> redpackets = redpacketDetailMapper.queryInvestorRedPacket(userId, req.getType(), page);
		redpackets = redpacketUnionRule(redpackets,null);
		paginatorResponse.setDatas(redpackets);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}


	
	@Override
	public PaginatorResponse<RedpacketResponse> queryCfplannerRedPacket(String userId, RedpacketRequest req) {		
		Page<RedpacketResponse> page  = new Page<RedpacketResponse>(req.getPageIndex(),req.getPageSize()>10?10:req.getPageSize()); //默认每页10条
		PaginatorResponse<RedpacketResponse> paginatorResponse = new PaginatorResponse<RedpacketResponse>();
		List<RedpacketResponse> redpackets = redpacketDetailMapper.queryCfplannerRedPacket(userId, req.getType(), page);
		redpackets = redpacketUnionRule(redpackets,null);
		paginatorResponse.setDatas(redpackets);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}
	
	private List<RedpacketResponse> redpacketUnionRule(List<RedpacketResponse> redpackets,List<ActRedpacketRuleDetail> redpacketRules){
		
		if(redpackets == null || redpackets.isEmpty() ) return Collections.emptyList();
		
		Map<String, List<RedpacketResponse>> redpacketMap = Maps.newLinkedHashMap();
		
		for (RedpacketResponse redpacket : redpackets) {
			List<RedpacketResponse> values =redpacketMap.get(redpacket.getRid());
			if(values==null){
				values =  Lists.newArrayList(redpacket);
				redpacketMap.put(redpacket.getRid(), values);
			}else{
				values.add(redpacket);
			}
		}
		if(CollectionUtils.isEmpty(redpacketRules))redpacketRules = redpacketDetailMapper.getRedpacketRulesByRid(redpacketMap.keySet());
		
		Map<String, String[]> pnames = Maps.newLinkedHashMap();
		
		Set<String> pids = Sets.newLinkedHashSet();
		
		
		for (ActRedpacketRuleDetail redpacketRule : redpacketRules) {
			
			List<RedpacketResponse> values = redpacketMap.get(redpacketRule.getRedpacketId());
			if(CollectionUtils.isEmpty(values)) continue;
			for (RedpacketResponse redpacket : values) {
				redpacket.setInvestLimit(redpacketRule.getInvestLlimit());
				redpacket.setPlatformLimit(redpacketRule.getPlatformLimit());
				redpacket.setPlatform(redpacketRule.getPlatformName());
				redpacket.setProductLimit(redpacketRule.getProductLimit());
				redpacket.setDeadline(redpacketRule.getProductDeadline());
				redpacket.setAmountLimit(redpacketRule.getAmountLimit());
				redpacket.setAmount(NumberUtils.getFormat(redpacketRule.getAmount(), ""));
			}
			
			if(ObjectUtils.equals(redpacketRule.getProductLimit(),1001)){
				String[] pidStr = StringUtils.split(redpacketRule.getProductId(), ",");
				pnames.put(redpacketRule.getRedpacketId(),pidStr);
				pids.addAll(Arrays.asList(pidStr));
			}
		}
		
		if(pnames.isEmpty()) return redpackets;
		
		List<Map<String, String>> pmap = redpacketDetailMapper.getProductNames(pids);
		Map<String, String> p= Maps.newLinkedHashMap();
		
		for (Map<String, String> map : pmap) {
			p.put(map.get("pid"), map.get("pname"));
		}
		
		for (Entry<String,  String[]> entry : pnames.entrySet()) {
			
			List<String> names = Lists.newArrayList();
			for (String pid : entry.getValue()) {
				String value = p.get(pid);
				if(StringUtils.isBlank(value))continue;
				names.add(value);
			}
			if(names.isEmpty())continue;
			
			
			List<RedpacketResponse> values = redpacketMap.get(entry.getKey());
			
			for (RedpacketResponse redpacket : values) {
				redpacket.setProductName(StringUtils.join(names, ","));
			}
			
		}
		
		return redpackets;
	}
	
	private List<RedpacketResponse> result(Map<String, List<RedpacketResponse>> redpacketMap){
		List<RedpacketResponse> redpacketResponses = Lists.newArrayList();
		for (List<RedpacketResponse> values: redpacketMap.values()) {
			redpacketResponses.addAll(values);
		}
		return redpacketResponses;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public BaseResponse sendRedPacket(String userId,SendRedPacketRequest sendRedPacketRequest) throws Exception {
		final ActRedpacket redpacket =  redpacketService.getRedpacket(sendRedPacketRequest.getRid());
		if(redpacket==null){
			return AppResponseUtil.getErrorBusi(new BaseResponse("100002", "红包不存在"));
		}	
		
		String[] userMobiles =StringUtils.split(sendRedPacketRequest.getUserMobiles(), ",");
		if(userMobiles == null || userMobiles.length <=0)return AppResponseUtil.getErrorBusi(new BaseResponse("100002", "用户手机号不能为空")); 
		
		List<CrmInvestor> existUserMobile = Lists.newArrayList();
		List<String> unExistUserMobile = Lists.newArrayList();
		for (String userMobile : userMobiles) {
			CrmInvestor investor = investorService.queryInvestorByMobile(userMobile);
			if(investor!=null &&  ObjectUtils.equals(userId,investor.getCfplanner())){
				existUserMobile.add(investor);
			}else{
				unExistUserMobile.add(userMobile);
			}
		}
		if(!existUserMobile.isEmpty()){
			try{
				List<String> rids = redpacketDetailMapper.getSendRedpacketIds(userId, sendRedPacketRequest.getRid(), existUserMobile.size());
				if(rids==null || rids.isEmpty())return AppResponseUtil.getErrorBusi(new BaseResponse("100002", "红包数量为零"));  
				if(rids.size() < existUserMobile.size())return AppResponseUtil.getErrorBusi(new BaseResponse("100002", "红包数量小于发放人数")); 
				
				ActRedpacketDetail redpacketDetail = null;
				final List<String> mobiles =  Lists.newArrayListWithCapacity(existUserMobile.size());
				final List<String> userIds =  Lists.newArrayListWithCapacity(existUserMobile.size());
				for (int i = 0; i < existUserMobile.size(); i++) {
					CrmInvestor investor = existUserMobile.get(i);
					redpacketDetail = new ActRedpacketDetail();
					redpacketDetail.setRedpacketDetailId(rids.get(i));
					redpacketDetail.setUserId(investor.getUserId());
					redpacketDetail.setUserMobile(investor.getMobile());
					redpacketDetail.setUserName(investor.getUserName());
					redpacketDetailMapper.sendRedpacket(redpacketDetail);
					mobiles.add(investor.getMobile());
					userIds.add(investor.getUserId());
				}
				ThreadpoolService.execute(new Runnable() {
					@Override
					public void run() {
						messageQueueService.batchSendSmMessageAndPersonalMsg(mobiles, AppTypeEnum.INVESTOR, MsgModuleEnum.RECIVEREDPAPERBYLCS,true,userIds, PersonalMsgTypeEnum.REDPACKET_INV,redpacket.getMoney().setScale(2, BigDecimal.ROUND_DOWN));
						pushMessageHelper.BatchSinglePush(AppTypeEnum.INVESTOR, SmsTypeEnum.MYREDPACKET_INC, userIds, "理财师派发红包", messageQueueService.queryMessageTemplate(MsgModuleEnum.RECIVEREDPAPERBYLCS, AppTypeEnum.INVESTOR, redpacket.getMoney().setScale(2, BigDecimal.ROUND_DOWN)), null, false, null);
					}
				});
				return AppResponseUtil.getSuccessResponse();
			}catch(Exception e){
				LOGGER.error("sendRedPacket exception userId={},sendRedPacketRequest={},exception", new Object[]{userId,sendRedPacketRequest,e});
				throw e;
			}
		}
		if(!unExistUserMobile.isEmpty())return AppResponseUtil.getErrorBusi (new BaseResponse("100002", "用户不存在或用户不是理财师的客户 "+StringUtils.join(unExistUserMobile.toArray(new String[0]), ','))); 
		
		return AppResponseUtil.getErrorBusi(new BaseResponse("100002", "未知错误"));
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void investRecordProcess(InvestRecordWrapper investRecord)throws Exception {
		useRedPacket(investRecord);
	}

	
	private void useRedPacket(InvestRecordWrapper investRecord)throws Exception{
		try{
			LOGGER.info("start use redpacket investRecordId={}",investRecord.getInvestId());
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
			LOGGER.debug("matcheRedPacket optimalRedpacket = {}",optimalRedpacket);
			//红包使用时如果UserID为空，红包的拥有者为CfplannerId（派发红包可以直接使用）
			if(optimalRedpacket.getUserId() == null){
				optimalRedpacket.setUserId(optimalRedpacket.getCfplannerId());
				optimalRedpacket.setUserMobile(optimalRedpacket.getCfplannerMobile());
				optimalRedpacket.setUserName(optimalRedpacket.getCfplannerName());
			}
			//红包使用成功 发送消息
			if(redpacketService.useRedpacket(investRecord,optimalRedpacket)){
				try{
					LOGGER.debug("matcheRedPacket optimalRedpacket use suceess investRecord={} optimalRedpacket= {}",optimalRedpacket);
					messageQueueService.sendSingleMessage(optimalRedpacket.getUserMobile(), AppTypeEnum.INVESTOR, MsgModuleEnum.INVESTUSEREDPAPER, investRecord.getProductName());
					List<String> userIds = Lists.newArrayList();
					userIds.add(optimalRedpacket.getUserId());	
					//typeValue
					Map<String,Object> urlparam = Maps.newHashMap();
					urlparam.put("typeValue", 1);
					pushMessageHelper.BatchSinglePush(AppTypeEnum.INVESTOR, SmsTypeEnum.REWARDBALANCE_INC, userIds, "投资使用红包", messageQueueService.queryMessageTemplate(MsgModuleEnum.INVESTUSEREDPAPER, AppTypeEnum.INVESTOR, investRecord.getProductName()), urlparam, true, PersonalMsgTypeEnum.REDPACKET_INV);
				}catch(Exception e){
					LOGGER.warn("sendSingleMessage exception userMobile={},money={},exception={}",new Object[]{optimalRedpacket.getUserMobile(),optimalRedpacket.getMoney(),e.getMessage()} );
				}
				return;
			}
			useRedPacket(investRecord);
		}catch(Exception e){
			LOGGER.error("useRedPacket exception InvestRecordWrapper={}", investRecord,e);
			throw e;
		}
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void customerRegisterRedPacekt(CrmUserInfo userInfo)throws Exception {
		redpacketService.customerRegisterRedPacekt(userInfo);
	}
	
	@Override
	public <T extends ProductPageList4Response> void productRedPacketTag(List<T> products,String userId) {
		
		if(CollectionUtils.isEmpty(products) || StringUtils.isEmpty(userId)) return;
		
		CrmInvestor investor = investorService.queryInvestorByUserId(userId);
		if(investor==null)return;
		List<ActRedpacketRuleDetail> ruleDetails = redpacketDetailMapper.getRedpacketRulesByUid(userId);
		if(CollectionUtils.isEmpty(ruleDetails)) return;
		boolean isInvestLlimit,isPatformLimit,isProductLimit;
		
		isInvestLlimit=isPatformLimit=isProductLimit=false;
		
		Set<String> orgNumber =	Sets.newLinkedHashSet();
		for (ProductPageList4Response product  : products) {
			orgNumber.add(product.getOrgNumber());
		}
		List<String> lists = productInvestRecordMapper.queryPlatfromInvestCount(userId, orgNumber);
		Set<String> platfroms = CollectionUtils.isEmpty(ruleDetails) ? Sets.<String>newHashSet():Sets.<String>newHashSet(lists);
	
		
		for (ProductPageList4Response product : products) {
			for (ActRedpacketRuleDetail ruleDetail : ruleDetails) {
				
				if(ruleDetail.getPlatformLimit()) isPatformLimit = ObjectUtils.equals(ruleDetail.getPlatformId(), product.getOrgNumber());
				else isPatformLimit=true;
				
				if(!isPatformLimit)continue;
				
				int investLlimit = ruleDetail.getInvestLlimit();
				switch (investLlimit) {
				case 0:
					isInvestLlimit = true;
					break;
				case 1:
					isInvestLlimit = (null == investor.getFirstInvestTime());
					break;
				case 2:
					isInvestLlimit = !platfroms.contains(product.getOrgNumber());
					break;
				}
				if(!isInvestLlimit)continue;
				
				int productLimit = ruleDetail.getProductLimit();
				if(ObjectUtils.equals(productLimit, 1000))isProductLimit = true;
			    //产品匹配
			    else if(ObjectUtils.equals(productLimit, 1001))isProductLimit = Sets.newHashSet(org.apache.commons.lang.StringUtils.split(ruleDetail.getProductId(), ",")).contains(product.getProductId());
			    else if(ObjectUtils.equals(productLimit, 1002))isProductLimit = ObjectUtils.equals(ruleDetail.getProductDeadline(), product.getDeadLineMinValue());
			    else if(ObjectUtils.equals(productLimit, 1003))isProductLimit = product.getDeadLineMinValue().compareTo(ruleDetail.getProductDeadline())>=0;
				
			    if(!isProductLimit)continue;
			    
			    product.setHasRedPacket(true);
		    	break;
			}
		}
	}

	@Override
	public void patformRedPacketTag(List<CimOrginfo> orgInfos, String userId) {
		if(CollectionUtils.isEmpty(orgInfos) || StringUtils.isEmpty(userId)) return;
		CrmInvestor investor = investorService.queryInvestorByUserId(userId);
		if(investor==null)return;
		List<ActRedpacketRuleDetail> ruleDetails = redpacketDetailMapper.getRedpacketRulesByUid(userId);
		if(CollectionUtils.isEmpty(ruleDetails)) return;
		boolean isInvestLlimit,isPatformLimit;
		
		isInvestLlimit=isPatformLimit=false;
		
		Set<String> orgNumber =	Sets.newLinkedHashSet();
		for (CimOrginfo orgInfo  : orgInfos) {
			orgNumber.add(orgInfo.getOrgNumber());
		}
		List<String> lists = productInvestRecordMapper.queryPlatfromInvestCount(userId, orgNumber);
		Set<String> platfroms = CollectionUtils.isEmpty(ruleDetails) ? Sets.<String>newHashSet():Sets.<String>newHashSet(lists);
	
		
		for (CimOrginfo orgInfo  : orgInfos) {
			for (ActRedpacketRuleDetail ruleDetail : ruleDetails) {
				
				if(ruleDetail.getPlatformLimit()) isPatformLimit = ObjectUtils.equals(ruleDetail.getPlatformId(), orgInfo.getOrgNumber());
				else isPatformLimit=true;
				
				if(!isPatformLimit)continue;
				
				int investLlimit = ruleDetail.getInvestLlimit();
				switch (investLlimit) {
				case 0:
					isInvestLlimit = true;
					break;
				case 1:
					isInvestLlimit = (null == investor.getFirstInvestTime());
					break;
				case 2:
					isInvestLlimit = !platfroms.contains(orgInfo.getOrgNumber());
					break;
				}
				if(!isInvestLlimit)continue;
				
				orgInfo.setHashRedpacket(true);
		    	break;
			}
		}
	}

	@Override
	public int productRedPacketCount(ProductDetailResponse productDetail ,String userId) {
		ProductRedPacketRequest productRedPacketRequest = new ProductRedPacketRequest();
		productRedPacketRequest.setProductId(productDetail.getProductId());
		productRedPacketRequest.setUserId(userId);
		productRedPacketRequest.setDeadLineValue(productDetail.getDeadLineMinValue());
		return productRedPacket(productRedPacketRequest).size();
	}
	
	public List<RedpacketResponse> productRedPacket(String userId,RedpacketRequest req,Page<RedpacketResponse> page){
		if(StringUtils.isEmpty(req.getPatform()) || StringUtils.isEmpty(req.getProductId()) || req.getDeadline()==null || req.getDeadline() <= 0 || StringUtils.isEmpty(userId)) return Collections.emptyList();
		
		CrmInvestor investor = investorService.queryInvestorByUserId(userId); 
		
		if(investor==null)return Collections.emptyList();
		
		List<ActRedpacketRuleDetail> ruleDetails = redpacketDetailMapper.getRedpacketRulesByUid(userId);
		if(CollectionUtils.isEmpty(ruleDetails)) return Collections.emptyList();
		
		boolean isInvestLlimit,isPatformLimit,isProductLimit;
		
		isInvestLlimit=isPatformLimit=isProductLimit=false;
		
		int investCount = productInvestRecordMapper.queryUserPlatfromInvestCount(userId,req.getPatform());
		Set<String> rids = Sets.newHashSetWithExpectedSize(ruleDetails.size());
		for (ActRedpacketRuleDetail ruleDetail : ruleDetails) {
			if(ruleDetail.getPlatformLimit()) isPatformLimit = ObjectUtils.equals(ruleDetail.getPlatformId(), req.getPatform());
			else isPatformLimit=true;
			if(!isPatformLimit)continue;
			int investLlimit = ruleDetail.getInvestLlimit();
			switch (investLlimit) {
			case 0:
				isInvestLlimit = true;
				break;
			case 1:
				isInvestLlimit = (null == investor.getFirstInvestTime());
				break;
			case 2:
				isInvestLlimit = investCount==0;
				break;
			}
			if(!isInvestLlimit)continue;
			
			int productLimit = ruleDetail.getProductLimit();
			if(ObjectUtils.equals(productLimit, 1000))isProductLimit = true;
		    //产品匹配
		    else if(ObjectUtils.equals(productLimit, 1001))isProductLimit = Sets.newHashSet(org.apache.commons.lang.StringUtils.split(ruleDetail.getProductId(), ",")).contains(req.getProductId());
		    else if(ObjectUtils.equals(productLimit, 1002))isProductLimit = ObjectUtils.equals(ruleDetail.getProductDeadline(),req.getDeadline());
		    else if(ObjectUtils.equals(productLimit, 1003))isProductLimit = req.getDeadline().compareTo(ruleDetail.getProductDeadline())>=0;
			
		    if(!isProductLimit)continue;
		    rids.add(ruleDetail.getRedpacketId());
		}
		if(rids.isEmpty()) return Collections.emptyList();
		List<RedpacketResponse> redpackets = redpacketDetailMapper.getUserRedpacketByRid(userId, rids, page);
		redpackets = redpacketUnionRule(redpackets,ruleDetails);
		return redpackets;
	}
	
	

	@Override
	public int patformRedPacketCount(String patform,int model,String userId) {
		
		int count = 0;
		if(StringUtils.isEmpty(patform) || StringUtils.isEmpty(userId)) return count;
		
		CrmInvestor investor = investorService.queryInvestorByUserId(userId); 
		
		if(investor==null)return count;
		
		List<ActRedpacketRuleDetail> ruleDetails = redpacketDetailMapper.getRedpacketRulesByUid(userId);
		
		if(CollectionUtils.isEmpty(ruleDetails)) return count;
		
		boolean isInvestLlimit,isPatformLimit;
		
		isInvestLlimit=isPatformLimit=false;
		
		int investCount = productInvestRecordMapper.queryUserPlatfromInvestCount(userId,patform);
		Set<String> rids = Sets.newHashSetWithExpectedSize(ruleDetails.size());
		for (ActRedpacketRuleDetail ruleDetail : ruleDetails) {
			
			if(ruleDetail.getPlatformLimit()) isPatformLimit = ObjectUtils.equals(ruleDetail.getPlatformId(), patform);
			else isPatformLimit=true;
			
			if(!isPatformLimit)continue;
			
			int investLlimit = ruleDetail.getInvestLlimit();
			switch (investLlimit) {
			case 0:
				isInvestLlimit = true;
				break;
			case 1:
				isInvestLlimit = (null == investor.getFirstInvestTime());
				break;
			case 2:
				isInvestLlimit = investCount==0;
				break;
			}
			if(!isInvestLlimit)continue;
			rids.add(ruleDetail.getRedpacketId());
			
		}
		return !rids.isEmpty()? redpacketDetailMapper.getUserRedpacketCountByRid(userId, rids) : count;
	}
	
	public List<RedpacketResponse> patformRedPacket(String userId,RedpacketRequest req,Page<RedpacketResponse> page){
		if(StringUtils.isEmpty(req.getPatform()) || StringUtils.isEmpty(userId)) return Collections.emptyList();
		
		CrmInvestor investor = investorService.queryInvestorByUserId(userId); 
		
		if(investor==null)return Collections.emptyList();
		
		List<ActRedpacketRuleDetail> ruleDetails = redpacketDetailMapper.getRedpacketRulesByUid(userId);
		if(CollectionUtils.isEmpty(ruleDetails)) return Collections.emptyList();
		
		boolean isInvestLlimit,isPatformLimit;
		
		isInvestLlimit=isPatformLimit=false;
		
		int investCount = productInvestRecordMapper.queryUserPlatfromInvestCount(userId,req.getPatform());
		Set<String> rids = Sets.newHashSetWithExpectedSize(ruleDetails.size());
		for (ActRedpacketRuleDetail ruleDetail : ruleDetails) {
			if(ruleDetail.getPlatformLimit())isPatformLimit = ObjectUtils.equals(ruleDetail.getPlatformId(), req.getPatform());
			else isPatformLimit=true;
			
			if(!isPatformLimit)continue;
			
			int investLlimit = ruleDetail.getInvestLlimit();
			switch (investLlimit) {
			case 0:
				isInvestLlimit = true;
				break;
			case 1:
				isInvestLlimit = (null == investor.getFirstInvestTime());
				break;
			case 2:
				isInvestLlimit = investCount==0;
				break;
			}
			if(!isInvestLlimit)continue;
			rids.add(ruleDetail.getRedpacketId());
		}
		if(rids.isEmpty()) return Collections.emptyList();
		List<RedpacketResponse> redpackets = redpacketDetailMapper.getUserRedpacketByRid(userId, rids, page);
		redpackets = redpacketUnionRule(redpackets,ruleDetails);
		return redpackets;
	}
	
	
	@Override
	public void lcsActicityRedPacket(String userId,ActicityRedPacketEnum acticityRedPacketEnum) throws Exception {
		CrmUserInfo userInfo = userInfoService.queryUserInfoByUserId(userId);
		if(userInfo == null){
			LOGGER.info("send lcsActicityRedPacket The user does not exist.  userId={},acticity code={}",userId,acticityRedPacketEnum.getValue());
			return;
		}
		String vlueStr = sysConfigService.getValuesByKey(acticityRedPacketEnum.getValue());
		if(StringUtils.isBlank(vlueStr)){
			LOGGER.info("send lcsActicityRedPacket The acticity code value does not exist.  userId={},acticity code={}",userId,acticityRedPacketEnum.getValue());
			return;
		}
		String[] values= StringUtils.split(vlueStr, ",");
		redpacketService.lcsSystemRedpacekt(new SendContext(userInfo,new String[]{values[0]},new String[]{values[1]}));
	}

	@Override
	public PaginatorResponse<RedpacketResponse> queryCfplannerRedPacket4(RedpacketRequest redpacketRequest) {
		PaginatorResponse<RedpacketResponse> paginatorResponse = new PaginatorResponse<RedpacketResponse>();
		Page<RedpacketResponse> page  = new Page<RedpacketResponse>(redpacketRequest.getPageIndex(),redpacketRequest.getPageSize()); //默认每页10条
		List<RedpacketResponse> redpackets = redpacketDetailMapper.queryCfplannerRedPacket4(redpacketRequest, page);
		redpackets = redpacketUnionRule(redpackets,null);
		paginatorResponse.setDatas(redpackets);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public Integer queryRedPacketCount4(RedpacketRequest redpacketRequest) {
		return redpacketDetailMapper.queryRedPacketCount4(redpacketRequest);
	}	
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public BaseResponse sendRedPacketToCfp(String userId,SendRedPacketRequest sendRedPacketRequest) throws Exception {
		final ActRedpacket redpacket =  redpacketService.getRedpacket(sendRedPacketRequest.getRid());
		if(redpacket==null){
			return AppResponseUtil.getErrorBusi(new BaseResponse("100002", "红包不存在"));
		}	
		
		String[] userMobiles =StringUtils.split(sendRedPacketRequest.getUserMobiles(), ",");
		if(userMobiles == null || userMobiles.length <=0)return AppResponseUtil.getErrorBusi(new BaseResponse("100002", "用户手机号不能为空")); 
		
		List<CrmCfplanner> existUserMobile = Lists.newArrayList();
		List<String> unExistUserMobile = Lists.newArrayList();
		for (String userMobile : userMobiles) {
			CrmCfplanner cfplanner = cfplannerService.queryCfplannerByMobile(userMobile);
			if(cfplanner!=null &&  ObjectUtils.equals(userId,cfplanner.getParentId())){
				existUserMobile.add(cfplanner);
			}else{
				unExistUserMobile.add(userMobile);
			}
		}
		if(!existUserMobile.isEmpty()){
			try{
				List<String> rids = redpacketDetailMapper.getSendRedpacketIds(userId, sendRedPacketRequest.getRid(), existUserMobile.size());
				if(rids==null || rids.isEmpty())return AppResponseUtil.getErrorBusi(new BaseResponse("100002", "红包数量为零"));  
				if(rids.size() < existUserMobile.size())return AppResponseUtil.getErrorBusi(new BaseResponse("100002", "红包数量小于发放人数")); 
				
				ActRedpacketDetail redpacketDetail = null;
				final List<String> mobiles =  Lists.newArrayListWithCapacity(existUserMobile.size());
				final List<String> userIds =  Lists.newArrayListWithCapacity(existUserMobile.size());
				for (int i = 0; i < existUserMobile.size(); i++) {
					CrmCfplanner cfplanner = existUserMobile.get(i);
					redpacketDetail = new ActRedpacketDetail();
					redpacketDetail.setRedpacketDetailId(rids.get(i));
					redpacketDetail.setCfplannerId(cfplanner.getUserId());
					redpacketDetail.setCfplannerMobile(cfplanner.getMobile());
					redpacketDetail.setCfplannerName(cfplanner.getUserName());
					redpacketDetail.setSenderUserId(userId);
					redpacketDetailMapper.sendRedpacketToCfp(redpacketDetail);
					mobiles.add(cfplanner.getMobile());
					userIds.add(cfplanner.getUserId());
				}
				ThreadpoolService.execute(new Runnable() {
					@Override
					public void run() {
						messageQueueService.batchSendSmMessageAndPersonalMsg(mobiles, AppTypeEnum.INVESTOR, MsgModuleEnum.RECIVEREDPAPERBYLCS,true,userIds, PersonalMsgTypeEnum.REDPACKET_INV,redpacket.getMoney().setScale(2, BigDecimal.ROUND_DOWN));
						pushMessageHelper.BatchSinglePush(AppTypeEnum.INVESTOR, SmsTypeEnum.MYREDPACKET_INC, userIds, "理财师派发红包", messageQueueService.queryMessageTemplate(MsgModuleEnum.RECIVEREDPAPERBYLCS, AppTypeEnum.INVESTOR, redpacket.getMoney().setScale(2, BigDecimal.ROUND_DOWN)), null, false, null);
					}
				});
				return AppResponseUtil.getSuccessResponse();
			}catch(Exception e){
				LOGGER.error("sendRedPacket exception userId={},sendRedPacketRequest={},exception", new Object[]{userId,sendRedPacketRequest,e});
				throw e;
			}
		}
		if(!unExistUserMobile.isEmpty())return AppResponseUtil.getErrorBusi (new BaseResponse("100002", "用户不存在或用户不是理财师的客户 "+StringUtils.join(unExistUserMobile.toArray(new String[0]), ','))); 
		
		return AppResponseUtil.getErrorBusi(new BaseResponse("100002", "未知错误"));
	}

	@Override
	public PaginatorResponse<RedpacketResponse> queryCfplannerRedPacket5(PaginatorRequest request,String userId) {
		PaginatorResponse<RedpacketResponse> paginatorResponse = new PaginatorResponse<RedpacketResponse>();
		Page<RedpacketResponse> page  = new Page<RedpacketResponse>(request.getPageIndex(),request.getPageSize()); //默认每页10条
		List<RedpacketResponse> redpackets = redpacketDetailMapper.queryCfplannerRedPacket5(userId, page);
		redpackets = redpacketUnionRule(redpackets,null);
		paginatorResponse.setDatas(redpackets);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public int queryRedPacketCount5(String userId) {
		return redpacketDetailMapper.queryRedPacketCount5(userId);
	}

	@Override
	public ActRedpacketDetail queryNewestRedPacket(String userId) {
		return redpacketDetailMapper.queryNewestRedPacket(userId);
	}

	@Override
	public void sendDoubleElevenRedpacket(String userId,ActicityRedPacketEnum acticityRedPacketEnum) throws Exception {
		CrmUserInfo userInfo = userInfoService.queryUserInfoByUserId(userId);
		if(userInfo == null){
			LOGGER.info("send lcsActicityRedPacket The user does not exist.  userId={},acticity code={}",userId,acticityRedPacketEnum.getValue());
			return;
		}
		if(acticityRedPacketEnum == ActicityRedPacketEnum.DOUBLE_ELEVEN_11 || acticityRedPacketEnum == ActicityRedPacketEnum.DOUBLE_ELEVEN_111){
			String vlueStr = sysConfigService.getValuesByKey(acticityRedPacketEnum.getValue());
			if(StringUtils.isBlank(vlueStr)){
				LOGGER.info("send lcsActicityRedPacket The acticity code value does not exist.  userId={},acticity code={}",userId,acticityRedPacketEnum.getValue());
				return;
			}
			String[] values= StringUtils.split(vlueStr, ",");
			redpacketService.lcsSystemRedpacekt(new SendContext(userInfo,SysConfigConstant.DOUBLE_ELEVEN_REDPACEKT_SWITCH, new String[]{values[0]},new String[]{values[1]}));
		}else if(acticityRedPacketEnum == ActicityRedPacketEnum.DOUBLE_ELEVEN_1111){
			SendContext sendContext =new SendContext(userInfo, SysConfigConstant.DOUBLE_ELEVEN_REDPACEKT_SWITCH,true, SysConfigConstant.DOUBLE_ELEVEN_REDPACEKT_IDS, SysConfigConstant.DOUBLE_ELEVEN_REDPACEKT_SEND_IDS);
			redpacketService.lcsSystemRedpacekt(sendContext);
		}	
	}

	@Override
	public RedpacketResponse redPacketDetail(String userId,String redpacketId) {
		Set<String> rids = Sets.newHashSet();
		rids.add(redpacketId);
		Page<RedpacketResponse> page  = new Page<RedpacketResponse>(1,10);
		List<RedpacketResponse> redpackets = redpacketDetailMapper.getUserRedpacketByRid(userId, rids, page);
		redpackets = redpacketUnionRule(redpackets,null);
		if(redpackets != null && redpackets.size() > 0){
			return redpackets.get(0);
		}
		return null;
	}

	@Override
	public List<ActRedpacketBriefDetailAndRule> productRedPacket(ProductRedPacketRequest productRedPacketRequest) {
		
		List<ActRedpacketBriefDetailAndRule>  actRedpacketBriefDetailAndRuleListReturen = new ArrayList<ActRedpacketBriefDetailAndRule>();
		
		//产品详情
		ProductDetailResponse productDetailResponse = productService.queryProductDetail(productRedPacketRequest.getProductId(),null);
		if(productRedPacketRequest.getDeadLineValue() == null){//如果没有设置期限 则取产品最小期限
			productRedPacketRequest.setDeadLineValue(productDetailResponse.getDeadLineMinValue());
		}
		if( productDetailResponse != null && StringUtils.isNotEmpty(productRedPacketRequest.getUserId())){
			//投资人详情
			CrmInvestor investor = investorService.queryInvestorByUserId(productRedPacketRequest.getUserId());
			if(null !=  investor){
				
				//查询客户平台投资次数
				int platfromInvestCount = productInvestRecordMapper.queryUserPlatfromInvestCount(productRedPacketRequest.getUserId(),productDetailResponse.getOrgNumber());
				
				/**
				 * 查询所有可使用的红包及规则
				 */
				List<ActRedpacketBriefDetailAndRule> actRedpacketBriefDetailAndRuleList = redpacketDetailMapper.getRedpacketBriefDetailAndRule(productRedPacketRequest.getUserId());
				if(CollectionUtils.isNotEmpty(actRedpacketBriefDetailAndRuleList)){
					
					//排序规则:大金额排序在前，小金额排序在后，金额相等比较过期日期，快过期红包的排序在前
					Collections.sort(actRedpacketBriefDetailAndRuleList);
					for (ActRedpacketBriefDetailAndRule actRedpacketBriefDetailAndRule : actRedpacketBriefDetailAndRuleList) {
						
						/**
						 * 平台匹配
						 * 1=限制|0=不限制
						 */
						if(actRedpacketBriefDetailAndRule.getPlatformLimit() && !actRedpacketBriefDetailAndRule.getPlatformId().equals(productDetailResponse.getOrgNumber())){
							continue;
						}
						
						/**
						 * 用户投资限制匹配
						 * 0=不限|1=用户首投|2=平台首投
						 */
						if((actRedpacketBriefDetailAndRule.getInvestLlimit() == 1 && null != investor.getFirstInvestTime()) || (actRedpacketBriefDetailAndRule.getInvestLlimit() == 2 && platfromInvestCount != 0)){
							continue;
						}
						
						/**
						 * 产品匹配
						 * 1000=不限|1001=限制产品编号|1002=等于产品期限|1003=大于等于产品期限
						 */
						if((actRedpacketBriefDetailAndRule.getProductLimit() == 1001 && !Sets.newHashSet(actRedpacketBriefDetailAndRule.getProductId().split(",")).contains(productRedPacketRequest.getProductId())) 
								|| (actRedpacketBriefDetailAndRule.getProductLimit() == 1002 && productRedPacketRequest.getDeadLineValue() != productDetailResponse.getDeadLineMinValue())
								|| (actRedpacketBriefDetailAndRule.getProductLimit() == 1003 && productRedPacketRequest.getDeadLineValue().compareTo(actRedpacketBriefDetailAndRule.getProductDeadline())< 0)){
							continue;
						}
						
						/**
						 * 金额匹配
						 * 0=不限|1=大于|2=大于等于
						 */
						if((actRedpacketBriefDetailAndRule.getAmountLimit() == 1 && productRedPacketRequest.getBuyTotal().compareTo(actRedpacketBriefDetailAndRule.getAmount()) <= 0) 
								|| (actRedpacketBriefDetailAndRule.getAmountLimit() == 2 && productRedPacketRequest.getBuyTotal().compareTo(actRedpacketBriefDetailAndRule.getAmount()) < 0)){
							continue;
						}
						
						actRedpacketBriefDetailAndRuleListReturen.add(actRedpacketBriefDetailAndRule);
					}
				}
			}
		}
		
		return actRedpacketBriefDetailAndRuleListReturen;		
	}
	
}
