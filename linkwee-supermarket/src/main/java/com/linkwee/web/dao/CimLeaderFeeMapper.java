package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.cim.CimLeaderFee;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年02月28日 10:46:49
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimLeaderFeeMapper extends GenericDao<CimLeaderFee,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<CimLeaderFee> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
	
	/**
     * 其中1个直属理财师年化业绩达到100元
     */
	Double queryUnderCfpYearInvestAmount(@Param("userId")String userId);
	
	/**
	 * 直属理财师贡献leader奖励明细-分页
	 * */
	List<CimLeaderFee> querycontribuPageList(Map<String, Object> query, Page<CimLeaderFee> page);

	/**
	 * 当月leader奖励
	 * */
	Double queryMonthProfit(@Param("userId")String userId, @Param("month")String month);
	/**
	 * 查下级理财师有没有独立核算
	 */
	int haveUnderCfpIndependent(@Param("userId")String userId);
	/**
	 * 间接理财师贡献奖励(统计所有数据)
	 * */
	Double queryContrProfit(@Param("userId")String userId);

	/**
	 * 间接理财师人数
	 */
	int queryIndirectCfpNumbers(@Param("userId")String userId);
	
	String queryLeaderPriftRankListNo1();

	/**
	 * 新建树后将rootID下面的数据重新设置ownerId
	 */
	void updateLeaderProfitOwnerId(@Param("userId")String userId);



}
