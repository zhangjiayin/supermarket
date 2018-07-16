package com.linkwee.web.service;

import com.linkwee.api.request.activity.VoteRequest;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActActivityEventGuessing;

/**
*
* @描述： ActActivityEventGuessingService服务接口
*
* @创建人： Hxb
*
* @创建时间：2018年05月23日 10:56:23
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public interface ActActivityEventGuessingService extends GenericService<ActActivityEventGuessing,Long>{

   /**
    * 查询ActActivityEventGuessing列表,为data-tables封装
    * @param dataTable
    * @return
    */
   DataTableReturn selectByDatatables(DataTable dataTable);

    /**
     * 根据活动编码查询竞猜项
     * @param activityCode
     * @return
     */
   ActActivityEventGuessing queryEventGuessingByActivityCode(String activityCode);

    /**
     * 用户竞猜活动票数
     * @param activityCode
     * @param startTime
     * @param endTime
     * @param userId
     * @return
     */
   int userActivityGuessingVotes(String activityCode,String startTime,String endTime,String userId);

   /**
    * 投票后更新票数
    * @param request
    * @return
    */
   int updateVotes(VoteRequest request);
}
