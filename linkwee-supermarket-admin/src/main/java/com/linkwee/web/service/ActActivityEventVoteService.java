package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActActivityEventVote;
import com.linkwee.web.request.VoteRequest;

import java.math.BigDecimal;

/**
*
* @描述： ActActivityEventVoteService服务接口
*
* @创建人： Hxb
*
* @创建时间：2018年05月23日 10:56:23
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
public interface ActActivityEventVoteService extends GenericService<ActActivityEventVote,Long>{

   /**
    * 查询ActActivityEventVote列表,为data-tables封装
    * @param dataTable
    * @return
    */
   DataTableReturn selectByDatatables(DataTable dataTable);

    /**
     * 活动已投票数
     * @param activityCode
     * @param userId
     * @return
     */
   int hadVotedNum(String activityCode, String userId);

    /**
     * 支持A方票数
     * @param guessingId
     * @param userId
     * @return
     */
   int hadVotedNumA(Integer guessingId, String userId);

    /**
     * 支持B方票数
     * @param id
     * @param userId
     * @return
     */
   int hadVotedNumB(Integer id, String userId);

    /**
     * 竞猜奖励
     * @param guessingId
     * @param userId
     * @param supportVote
     * @return
     */
   BigDecimal queryEventGuessingPrize(Integer guessingId, String userId, String supportVote);

    /**
     * 投票
     * @param request
     * @return
     */
   int vote(VoteRequest request);

    /**
     * 真实投票总数
     * @param guessingId
     * @param supportVote
     * @return
     */
   int hadVotedTotal(Integer guessingId,String supportVote);

    /**
     * 比赛结束生成奖项
     * @param guessingId
     * @param supportVote
     * @param perVotePrize
     * @return
     */
   int generatePrize(Integer guessingId,String supportVote, BigDecimal perVotePrize);

    /**
     * 竞猜实际奖励
     * @param guessingId
     * @param supportVote
     * @return
     */
   BigDecimal queryEventGuessingPrizeAmount(Integer guessingId,String supportVote);

    /**
     * 发放赛事奖励
     * @param guessId
     */
   void sendPrize(Long guessId,String supportVote,String operator) throws Exception;
}
