package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.mc.SysNotice;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月20日 15:59:52
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SysNoticeMapper extends GenericDao<SysNotice,Long>{
	
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param pageni
     * @return
     */
	List<SysNotice> selectBySearchInfo(@Param("dt")DataTable dt,@Param("appType")Integer appType,@Param("platform")Integer platform,RowBounds page);
	
	
	/**
	 * 统计未读消息数
	 * @param sysMsgDate 上次查看系统消息的时间
	 * @param app类别
	 * @return
	 */
	public int queryUnReadSysMsgCount(Map<String,Object> map);

	/**
	 * 批量新增
	 * @param 
	 * @return
	 */
	public Integer addBatch(List<SysNotice> list);
}
