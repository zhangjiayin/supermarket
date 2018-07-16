package com.linkwee.web.dao;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.ActActivityEventGuessing;
import com.linkwee.web.request.VoteRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
*
* @描述： Dao接口
*
* @创建人： Hxb
*
* @创建时间：2018年05月23日 10:56:23
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public interface ActActivityEventGuessingMapper extends GenericDao<ActActivityEventGuessing,Long>{

    /**
    * 封装DataTable对象查询
    * @param dt
    * @param page
    * @return
    */
   List<ActActivityEventGuessing> selectBySearchInfo(@Param("dt") DataTable dt, RowBounds page);

    /**
     * 按活动编码查询竞猜项
     * @param activityCode
     * @return
     */
   ActActivityEventGuessing queryEventGuessingByActivityCode(@Param("activityCode") String activityCode);

    /**
     *
     * @param startTime
     * @param endTime
     * @param userId
     * @return
     */
   int userNBAGuessingVotes(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("userId") String userId);

   /**
    * 投票后更新票数
    * @param request
    * @return
    */
   int updateVotes(VoteRequest request);
}
