package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.CimJfqzPushRecord;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年03月21日 18:23:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimJfqzPushRecordMapper extends GenericDao<CimJfqzPushRecord,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimJfqzPushRecord> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
}
