package com.linkwee.activity.service;

import java.util.Date;

import com.linkwee.activity.model.RedpacketType;
import com.linkwee.core.generic.GenericService;

public interface RedpacketTypeService extends GenericService<RedpacketType, Long>{
	/**
	 * 添加红包类型
	 * @param money
	 * @param name
	 * @param date
	 * @return
	 * @throws Exception
	 */
	RedpacketType insertRedpacketType(Double money,String name,Date date,String operator) throws Exception;
	
	
	
	boolean updateRedpacketType(String typeId,Double money,String name,Date date,String operator)throws Exception;
	
	
	/**
	 * 根据根据红包类型id 查询红包类型
	 * @param money
	 * @return
	 */
	public RedpacketType getRedPaperTypeById(String redPacketTypeId);

}
