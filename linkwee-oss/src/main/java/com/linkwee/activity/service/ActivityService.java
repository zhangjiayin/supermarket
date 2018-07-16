package com.linkwee.activity.service;

import java.util.Date;

import com.linkwee.activity.model.Activity;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;

/**
 *  业务接口
 * 
 * @author Mignet
 * @since 2014年6月10日 下午4:15:01
 **/
public interface ActivityService extends GenericService<Activity, Integer> {

	/**
	 * 查询列表,为data tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);
	
	/**
	 * 是否有效活动
	 * @param activityId 活动编号
	 * @return Activity
	 */
	Activity getActivity(String activityId);
	
	/**
	 * 添加活动
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	Activity insertActivity(String name,Date startDate,Date endDate) throws Exception;

	boolean updateActivity(String activity,String name,Date endDate,Date updateDate)throws Exception;
}
