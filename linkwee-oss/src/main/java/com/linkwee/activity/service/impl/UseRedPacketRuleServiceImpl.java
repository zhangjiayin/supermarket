package com.linkwee.activity.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.linkwee.activity.dao.UseRedPacketRuleMapper;
import com.linkwee.activity.model.Activity;
import com.linkwee.activity.model.RedpacketType;
import com.linkwee.activity.model.UseRedPacketRule;
import com.linkwee.activity.model.UseRuleContext;
import com.linkwee.activity.model.UseRuleDetail;
import com.linkwee.activity.service.UseRedPacketRuleService;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.product.service.ProductInfoService;
import com.linkwee.web.model.product.ProductInfoResp;
import com.linkwee.web.request.RedPacketInfoRequest;
import com.linkwee.web.util.GenerateNumberUtils;

@Service
public class UseRedPacketRuleServiceImpl extends GenericServiceImpl<UseRedPacketRule, Long>
		implements UseRedPacketRuleService {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(UseRedPacketRuleServiceImpl.class);
	
	@Autowired
	private UseRedPacketRuleMapper useRedPacketRuleMapper;
	
	
	@Autowired
	private ProductInfoService productInfoService;
	
	@Override
	public GenericDao<UseRedPacketRule, Long> getDao() {
		return useRedPacketRuleMapper;
	}


	
	/**
	 * 插入红包使用规则
	 * @param useRuleContext
	 * @return
	 * @throws Exception
	 */
	private boolean insertUseRule(UseRuleContext useRuleContext) throws Exception{
		Activity activity = useRuleContext.getActivity();
		Date date = useRuleContext.getDate();
		UseRedPacketRule useRedPacketRule = new UseRedPacketRule();
		useRedPacketRule.setActivityId(activity.getFid());
		useRedPacketRule.setActivityName(activity.getName());
		useRedPacketRule.setInitDate(date);
		useRedPacketRule.setUpdateDate(date);
		
		RedPacketInfoRequest req =useRuleContext.getReq();
		
		Integer investProductLimitCode = req.getLimitInvestProduct();
		Validate.notNull(investProductLimitCode,"投资产品限制不能为空");
		if(investProductLimitCode==1 && req.getOperator()!=null){
			investProductLimitCode = Integer.parseInt(investProductLimitCode+""+req.getOperator());
		}
		
		InvestProductLimit investProductLimit = InvestProductLimit.getInvestProductLimit(investProductLimitCode);
		Validate.notNull(investProductLimit,"投资产品限制有误");
		useRedPacketRule.setUseCondition(investProductLimit.getUseCondition());
		if(investProductLimit == InvestProductLimit.unLimit){
			useRedPacketRule.setFid(GenerateNumberUtils.generateKey());
			useRedPacketRule.setRedPaperRuleJson(getUseRuleJson(useRuleContext,new ArrayList<UseRuleDetail>(1)));
			return insert(useRedPacketRule)>0;
		}else if(investProductLimit==InvestProductLimit.eqProductDeadlineLimit||investProductLimit==InvestProductLimit.geProductDeadlineLimit){
			Validate.notNull(req.getDeadline(),"投资产品期限不能为空");
			useRedPacketRule.setFid(GenerateNumberUtils.generateKey());
			useRedPacketRule.setProductDeadline(req.getDeadline());
			useRedPacketRule.setRedPaperRuleJson(getUseRuleJson(useRuleContext,new ArrayList<UseRuleDetail>(1)));
			return insert(useRedPacketRule)>0;
		}else if(investProductLimit==InvestProductLimit.productIdLimit){
			Validate.isTrue(StringUtils.isNotBlank(req.getPids()),"产品编号不能为空");
			String[] productIds = StringUtils.split(req.getPids(), ',');
			Validate.notEmpty(productIds,"产品不能为空");
			String useRuleJson = getUseRuleJson(useRuleContext,new ArrayList<UseRuleDetail>(1));
			useRedPacketRule.setRedPaperRuleJson(useRuleJson);
			List<UseRedPacketRule> useRedPacketRules = new LinkedList<UseRedPacketRule>();
			ProductInfoResp productInfo = null;
			for (String productId : productIds) {
				UseRedPacketRule pUseRedPacketRule = new UseRedPacketRule();
				BeanUtils.copyProperties(pUseRedPacketRule, useRedPacketRule);
				productInfo = productInfoService.getByProductId(productId);
				Validate.notNull(productInfo, "编号为: "+productId+" 的产品不存在 ");
				pUseRedPacketRule.setFid(GenerateNumberUtils.generateKey());
				pUseRedPacketRule.setProductId(productInfo.getProductId());
				pUseRedPacketRule.setProductName(productInfo.getProductName());
				pUseRedPacketRule.setProductDeadline(productInfo.getDeadLineValue());
				useRedPacketRules.add(pUseRedPacketRule);
			}
			if(!useRedPacketRules.isEmpty()){
				useRedPacketRuleMapper.inserts(useRedPacketRules);
				return true;
			}
			
		}
		
		return false;
	}
	
	/**
	 * 根据信息生成红包使用规则
	 * @param useRuleContext
	 * @param useRuleDetails
	 * @return
	 */
	private String getUseRuleJson(UseRuleContext useRuleContext,List<UseRuleDetail> useRuleDetails){
		UseRuleDetail useRuleDetail = new UseRuleDetail();
		RedPacketInfoRequest req =useRuleContext.getReq();
		useRuleDetail.setInvestUser(req.getLimitInvestUser());
		Validate.notNull(req.getLimitMoney(),"投资产品金额限制不能为空");
		InvestMoneyLimit investMoneyLimit = InvestMoneyLimit.getInvestMoneyLimit(req.getLimitMoney());
		Validate.notNull(investMoneyLimit,"投资产品金额限制有误");
		investMoneyLimit.setUseRuleInvestMoneyLimit(useRuleDetail, req.getMinMoney(), req.getMaxMoney());
		RedpacketType redpacketType = useRuleContext.getRedpacketType();
		useRuleDetail.setBusType(req.getRtype());
		useRuleDetail.setRedPaperTypeId(redpacketType.getFid());;
		useRuleDetail.setRedPapers(redpacketType.getMoney());
		useRuleDetails.add(useRuleDetail);
		return JSON.toJSONString(useRuleDetails);
		
	}
	




	@Override
	public boolean insertUseRedPacketRule(UseRuleContext useRuleContext) throws Exception {
		try{
			return insertUseRule(useRuleContext);
		}catch(Exception e){
			LOGGER.error("addUseRedPacketRule Exception useRuleContext={},exception={}", useRuleContext,e);
			throw e;
		}
	}


	@Override
	public void getRedPacketUseRule(RedPacketInfoRequest redPacketInfo,String activityId) {
		
		UseRedPacketRule useRedPacketRule = useRedPacketRuleMapper.getUseRuleByActivityId(activityId);
		if(null == useRedPacketRule)return;
		//设置产品条件
		InvestProductLimit investProductLimit = InvestProductLimit.getUseConditionLimit(useRedPacketRule.getUseCondition());
		int limitCode = investProductLimit.getLimitCode();
		
		redPacketInfo.setLimitInvestProduct(limitCode>=10?limitCode/10:limitCode);
		
		if(InvestProductLimit.productIdLimit==investProductLimit){
			redPacketInfo.setLimitInvestProduct(investProductLimit.getLimitCode());
			List<String>  pid = useRedPacketRuleMapper.queryAllProductIdByActivityId(activityId);
			redPacketInfo.setPids(StringUtils.join(pid, ","));
		}else{
			if(InvestProductLimit.eqProductDeadlineLimit==investProductLimit||InvestProductLimit.geProductDeadlineLimit==investProductLimit){
				redPacketInfo.setOperator(limitCode%10);
				redPacketInfo.setDeadline(useRedPacketRule.getProductDeadline());
			}
		}
		//获取使用规则
		String useRuleJson = useRedPacketRule.getRedPaperRuleJson();
		StringUtils.isNotBlank(useRuleJson);
		List<UseRuleDetail> useRuleDetails = JSON.parseArray(useRuleJson, UseRuleDetail.class);
		Validate.notEmpty(useRuleDetails,"红包使用规则不存在");
		UseRuleDetail useRuleDetail = useRuleDetails.get(0);
		redPacketInfo.setRtype(useRuleDetail.getBusType());
		//设置金额条件
		if(ObjectUtils.equals(useRuleDetail.getMin(), 0d)&&ObjectUtils.equals(useRuleDetail.getMax(), 0d)){
			redPacketInfo.setLimitMoney(0);
		}else{
			redPacketInfo.setLimitMoney(1);
			redPacketInfo.setMaxMoney(useRuleDetail.getMax());
			redPacketInfo.setMinMoney(useRuleDetail.getMin());
		}
		//设置投资用户条件
		redPacketInfo.setLimitInvestUser(useRuleDetail.getInvestUser());
	}

	@Override
	public List<UseRedPacketRule> queryAllUseRuleByActivityId(String activityId) {
		return useRedPacketRuleMapper.queryAllUseRuleByActivityId(activityId);
	}



	@Override
	public boolean updateUseRedPacketRule(String activityId, String productIdStr) throws Exception {
		try{
			Validate.isTrue(StringUtils.isNotBlank(productIdStr),"产品编号不能为空");
			UseRedPacketRule useRedPacketRule = useRedPacketRuleMapper.getUseRuleByActivityId(activityId);
			Validate.notNull(useRedPacketRule,"无红包规则");
			String[] productIds = StringUtils.split(productIdStr, ',');
			Validate.notEmpty(productIds,"产品不能为空");
			List<UseRedPacketRule> useRedPacketRules = new LinkedList<UseRedPacketRule>();
			ProductInfoResp productInfo = null;
			Date date = new Date();
			for (String productId : productIds) {
				UseRedPacketRule pUseRedPacketRule = new UseRedPacketRule();
				pUseRedPacketRule.setActivityId(useRedPacketRule.getActivityId());
				pUseRedPacketRule.setActivityName(useRedPacketRule.getActivityName());
				pUseRedPacketRule.setRedPaperRuleJson(useRedPacketRule.getRedPaperRuleJson());
				productInfo = productInfoService.getByProductId(productId);
				Validate.notNull(productInfo, "编号为: "+productId+" 的产品不存在 ");
				pUseRedPacketRule.setFid(GenerateNumberUtils.generateKey());
				pUseRedPacketRule.setProductId(productInfo.getProductId());
				pUseRedPacketRule.setProductName(productInfo.getProductName());
				pUseRedPacketRule.setProductDeadline(productInfo.getDeadLineValue());
				pUseRedPacketRule.setInitDate(date);
				pUseRedPacketRule.setUpdateDate(date);
				useRedPacketRules.add(pUseRedPacketRule);
			}
			if(!useRedPacketRules.isEmpty()){
				useRedPacketRuleMapper.inserts(useRedPacketRules);
				return true;
			}
			return false;
		}catch(Exception e){
			LOGGER.error("updateUseRedPacketRule Exception activityId={},productIdStr={},exception={}", new Object[]{activityId,productIdStr,e});
			throw e;
		}
	}



	@Override
	public boolean deleteUseRedPacketRule(String  activityId)
			throws Exception {
		return useRedPacketRuleMapper.deleteUseRedPacketRule(activityId)>0;
	}
	







}
