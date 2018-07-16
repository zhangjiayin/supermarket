package com.linkwee.activity.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.linkwee.activity.dao.GenerateRedPacketRuleMapper;
import com.linkwee.activity.model.GenerateRedPacketRule;
import com.linkwee.activity.model.GenerateRuleContext;
import com.linkwee.activity.model.GenerateRuleDetail;
import com.linkwee.activity.model.RedpacketRule;
import com.linkwee.activity.service.GenerateRedPacketRuleService;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.web.util.GenerateNumberUtils;

@Service
public class GenerateRedPacketRuleServiceImpl extends GenericServiceImpl<GenerateRedPacketRule, Long> implements GenerateRedPacketRuleService{
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GenerateRedPacketRuleServiceImpl.class);
	
	@Autowired
	private GenerateRedPacketRuleMapper generateRedPacketRuleMapper;

	@Override
	public GenericDao<GenerateRedPacketRule, Long> getDao() {
		return generateRedPacketRuleMapper;
	}

	@Override
	public GenerateRedPacketRule getGenerateRedPacketRuleByActivityId(String activityId) {
		return generateRedPacketRuleMapper.getGenerateRedPacketRuleByActivityId(activityId);
	}

	private void addRedpacketRule(GenerateRuleContext generateRuleContext,List<RedpacketRule> redpackets){
		redpackets.add(new RedpacketRule(generateRuleContext.getRedpacketType().getMoney(),generateRuleContext.getRedpacketType().getFid(),generateRuleContext.getValidDate(),generateRuleContext.getRtype()));
	}

	/**
	 * 添加新生成规则
	 * @param generateRuleContext
	 * @return
	 * @throws Exception
	 */
	private boolean insertGenerateRule(GenerateRuleContext generateRuleContext) throws Exception{
		try{
			List<RedpacketRule> redpackets= new ArrayList<RedpacketRule>(1);
			addRedpacketRule(generateRuleContext, redpackets);
			GenerateRuleDetail detail = new GenerateRuleDetail();
			detail.setMax(0d);
			detail.setMin(0d);
			
			detail.setRedPaperType(redpackets);
			
			GenerateRedPacketRule generateRedPacketRule = new GenerateRedPacketRule();
			generateRedPacketRule.setFid(GenerateNumberUtils.generateKey());
			generateRedPacketRule.setActivityId(generateRuleContext.getActivity().getFid());
			generateRedPacketRule.setActivityName(generateRuleContext.getActivity().getName());
			
			//不限 生成条件
			generateRedPacketRule.setCreateCondition(1000);
			generateRedPacketRule.setRedPaperRuleJson(JSON.toJSONString(detail));
			generateRedPacketRule.setInitDate(generateRuleContext.getDate());
			generateRedPacketRule.setUpdateDate(generateRuleContext.getDate());
			return insert(generateRedPacketRule)>0;
		}catch(Exception e){
			LOGGER.error("添加生成规则异常 generateRuleContext={},errorinfo={}", generateRuleContext,e );
			throw e;
		}
	}
	
	/*	
	private boolean updateGenerateRule(GenerateRedPacketRule generateRedPacketRule,GenerateRuleContext generateRuleContext) throws Exception{
		try{
			String redpacketRule = generateRedPacketRule.getRedPaperRuleJson();
			GenerateRuleDetail generateRuleDetail = JSON.parseObject(redpacketRule, GenerateRuleDetail.class);
			addRedpacketRule(generateRuleContext, generateRuleDetail.getRedpackets());
			generateRedPacketRule.setRedPaperRuleJson(JSON.toJSONString(generateRuleDetail));
			generateRedPacketRule.setUpdateDate(generateRuleContext.getDate());
			return update(generateRedPacketRule)>0;
		}catch(Exception e){
			LOGGER.error("更新生成规则异常 generateRedPacketRule={},generateRuleContext={},errorinfo={}" , new Object[]{generateRedPacketRule,generateRuleContext,e});
			throw e;
		}
		
	}*/

	@Override
	public boolean insertActivityGenerateRule(GenerateRuleContext generateRuleContext)  throws Exception{
		GenerateRedPacketRule generateRedPacketRule = getGenerateRedPacketRuleByActivityId(generateRuleContext.getActivity().getFid());
		return generateRedPacketRule==null?insertGenerateRule(generateRuleContext):false;
		/*return generateRedPacketRule==null ? insertGenerateRule(generateRuleContext): updateGenerateRule(generateRedPacketRule,generateRuleContext);*/
		
	}

	@Override
	public boolean deleteActivityGenerateRule(String activityId) {
		return generateRedPacketRuleMapper.deleteActivityGenerateRule(activityId)>0;
	}

}
