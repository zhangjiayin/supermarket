package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.ActChristmasSocksDetail;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月20日 13:54:37
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActChristmasSocksDetailMapper extends GenericDao<ActChristmasSocksDetail,Long>{

	/**
	 * 助力记录
	 * @param userId
	 * @return
	 */
	List<ActChristmasSocksDetail> queryHelpRecord(@Param("userId")String userId);
}
