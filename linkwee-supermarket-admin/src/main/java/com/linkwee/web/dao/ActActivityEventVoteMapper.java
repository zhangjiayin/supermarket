package com.linkwee.web.dao;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.ActActivityEventVote;
import com.linkwee.web.response.UserEventGuessingPrizeResponse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.math.BigDecimal;
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
public interface ActActivityEventVoteMapper extends GenericDao<ActActivityEventVote,Long>{

    /**
    * 封装DataTable对象查询
    * @param dt
    * @param page
    * @return
    */
   List<ActActivityEventVote> selectBySearchInfo(@Param("dt") DataTable dt, RowBounds page);

    /**
     * 已投票数
     * @param activityCode
     * @param userId
     * @return
     */
   int hadVotedNum(@Param("activityCode") String activityCode, @Param("userId") String userId);

    /**
     * 竞猜已投A方票数
     * @param guessingId
     * @param userId
     * @return
     */
   int hadVotedGuessNum(@Param("guessingId") Integer guessingId, @Param("userId") String userId, @Param("supportVote") String supportVote);

    /**
     * 竞猜奖励
     * @param guessingId
     * @param userId
     * @param supportVote
     * @return
     */
   BigDecimal queryEventGuessingPrize(@Param("guessingId") Integer guessingId, @Param("userId") String userId, @Param("supportVote") String supportVote);

    /**
     * 真实投票总数
     * @param supportVote
     * @return
     */
   int hadVotedTotal(@Param("guessingId") Integer guessingId,@Param("supportVote")String supportVote);

    /**
     * 比赛结束生成奖项
     * @param guessingId
     * @param supportVote
     * @param perVotePrize
     * @return
     */
   int generatePrize(@Param("guessingId")Integer guessingId, @Param("supportVote")String supportVote, @Param("perVotePrize")BigDecimal perVotePrize);

    /**
     * 竞猜实际奖励
     * @param guessingId
     * @param supportVote
     * @return
     */
   BigDecimal queryEventGuessingPrizeAmount(@Param("guessingId")Integer guessingId, @Param("supportVote")String supportVote);

    /**
     * 赛事结算 用户奖励
     * @param guessingId
     * @return
     */
   List<UserEventGuessingPrizeResponse> queryEventGuessingPrizeTotal(@Param("guessingId")Long guessingId, @Param("supportVote")String supportVote);

    /**
     * 赛事结算 更新奖励发放状态
     * @param guessingId
     * @param supportVote
     * @param operator
     * @return
     */
   int updateEventGuessingStatus(@Param("guessingId")Long guessingId,@Param("supportVote") String supportVote,@Param("operator") String operator);
}
