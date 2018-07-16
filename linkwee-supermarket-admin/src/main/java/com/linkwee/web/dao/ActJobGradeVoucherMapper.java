package com.linkwee.web.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.crm.ActJobGradeVoucher;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年10月27日 18:00:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActJobGradeVoucherMapper extends GenericDao<ActJobGradeVoucher,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActJobGradeVoucher> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
     * 查询今天生效的职级体验券
     */
	List<ActJobGradeVoucher> synActJobGradeVoucher(@Param("today")String today);
	
	/**
     * 更新过期职级体验券
     */
	List<ActJobGradeVoucher> synExpirseActJobGradeVoucher(@Param("today")String today);

	/**
     * 将正在使用的职级体验券置为已失效
     */
	void updateByUserIdJobGradeWeight(@Param("userId")String userId, @Param("jobGradeWeight")int jobGradeWeight);
	
	/**
     * 将未使用的设为已失效(针对下个月先发总监后发经理体验券)
     */
	void updateByMonthUserIdJobGradeWeight(@Param("userId")String userId, @Param("jobGradeWeight")int levelWeight, @Param("month")String month);
}
