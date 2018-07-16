package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.ChangeLcsRecord;
import com.linkwee.web.model.UnconventionalRecord;
import com.xiaoniu.mybatis.paginator.domain.PageList;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;


/**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年11月24日 14:15:40
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface UnconventionalRecordDao extends BasePageDao<UnconventionalRecord>{
	
	/**
	 * 
	 * @param lcsNumber 理财师编码
	 * @return
	 */
	public int queryUnconventionalRecord(@Param("cfpMobile")String cfpMobile);

	public PageList<UnconventionalRecord> queryUnreCordPage(PageRequest req);

	/**
	 * 获取理财师职级更变记录
	 * @param unconventionalRecord
	 * @return
     */
	 public List<UnconventionalRecord> queryCfpLevelOptRecord(UnconventionalRecord unconventionalRecord);
	 
	 /**
	  * 更换理财师操作记录
	  * @param customerMobile
	  * @return
	  */
	 public List<ChangeLcsRecord> queryChangeLcsRecord(String customerMobile);




}
