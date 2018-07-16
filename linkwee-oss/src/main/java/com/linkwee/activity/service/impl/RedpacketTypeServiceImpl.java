package com.linkwee.activity.service.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.activity.dao.RedpacketTypeMapper;
import com.linkwee.activity.model.RedpacketType;
import com.linkwee.activity.service.RedpacketTypeService;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.web.util.GenerateNumberUtils;
@Service
public class RedpacketTypeServiceImpl extends GenericServiceImpl<RedpacketType, Long> implements RedpacketTypeService{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(RedpacketTypeServiceImpl.class);
	
	@Autowired
	private RedpacketTypeMapper redpacketTypeMapper;

	@Override
	public GenericDao<RedpacketType, Long> getDao() {
		return redpacketTypeMapper;
	}

	@Override
	public RedpacketType insertRedpacketType(Double money,String name, Date date,String operator) throws Exception {
		try{
			RedpacketType redpacketType = new RedpacketType();
			String redpacketTypeFId = GenerateNumberUtils.generateKey();
			redpacketType.setFid(redpacketTypeFId);
			redpacketType.setName(name);
			redpacketType.setMoney(money);
			redpacketType.setOperator(operator);
			redpacketType.setInitDate(date);
			redpacketType.setUpdateDate(date);
			insert(redpacketType);
			return redpacketType;
		}catch(Exception e){
			LOGGER.error("insertRedpacketType Exception money={},name={},date={},exception={}", new Object[]{money,name,date,e});
			throw e;
		}
	}

	@Override
	public RedpacketType getRedPaperTypeById(String redPacketTypeId) {
		return redpacketTypeMapper.getRedPaperTypeById(redPacketTypeId);
	}

	@Override
	public boolean updateRedpacketType(String typeId, Double money, String name,Date date, String operator) throws Exception {
		try{
			
			RedpacketType redpacketType = new RedpacketType();
			Validate.notNull(money,"红包金额不能为空");
			Validate.isTrue(StringUtils.isNotBlank(name),"红包名称不能为空");
			Validate.isTrue(money>0,"红包金额必须大于0");
			redpacketType.setFid(typeId);
			redpacketType.setMoney(money);
			redpacketType.setName(name);
			redpacketType.setUpdateDate(date);
			redpacketType.setOperator(operator);
			return update(redpacketType) > 0;
		}catch(Exception e){
			LOGGER.error("updateRedpacketType Exception typeId ={},money={},name={},date={},exception={}", new Object[]{typeId,money,name,date,e});
			throw e;
		}
	}




}
