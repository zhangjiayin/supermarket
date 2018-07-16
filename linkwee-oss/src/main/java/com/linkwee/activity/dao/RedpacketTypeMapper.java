package com.linkwee.activity.dao;

import org.apache.ibatis.annotations.Param;

import com.linkwee.activity.model.RedpacketType;
import com.linkwee.core.generic.GenericDao;

public interface RedpacketTypeMapper extends GenericDao<RedpacketType, Long>{

	/**
	 * 根据金额查询红包类型
	 * @param money
	 * @return
	 */
	public RedpacketType getRedPaperTypeByMoney(@Param("money") Double money);
	
	/**
	 * 根据根据红包类型id 查询红包类型
	 * @param money
	 * @return
	 */
	public RedpacketType getRedPaperTypeById(@Param("redPacketTypeId")String redPacketTypeId);

}
