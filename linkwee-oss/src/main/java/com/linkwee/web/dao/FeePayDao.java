package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.web.model.fee.FeePay;

/**
 * Title: 佣金发放Dao 
 * Description: 
 * Company: Copyright (c) 深圳市前海领会科技有限公司-版权所有
 * 
 * @author jinbo.fu
 * @date 2016年6月20日下午5:12:37
 */
public interface FeePayDao extends BasePageDao<FeePay> {
	
	/**
	 * 根据月份查询
	 * @param month
	 * @param saleUserList
	 * @param status
	 * @return
	 */
	public List<FeePay> selectByCondition(@Param("month")String month,@Param("list")List<String> saleUserList,@Param("status")int status,RowBounds rowBounds);
	
	/**
	 * 根据月份查询有多少条数
	 * @param month
	 * @param saleUserList
	 * @return
	 */
	public Integer countByMonth(@Param("month")String month,@Param("list")List<String> saleUserList);
	
	/**
	 * 根据月份，销售用户更改佣金发放状态
	 * @param month
	 * @param saleUserList
	 * @return
	 */
	public Integer updateByMonthSaleUser(@Param("month")String month,@Param("list")List<String> saleUserList);
	
	/**
	 * 根据月份和业务员编码来查询
	 * @param month
	 * @param saleUserNo
	 * @return
	 */
	public List<FeePay> selectByMonthSaleuserNo(@Param("month")String month,@Param("list")List<String> saleUserNo);
	
	/**
	 * 批量修改状态
	 * @param ids
	 */
	public void updateBatchStatus(@Param("list")List<Integer> ids,@Param("status")int status);
	
}
