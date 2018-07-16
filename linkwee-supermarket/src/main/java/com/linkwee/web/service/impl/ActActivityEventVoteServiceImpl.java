package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.lang.Long;

import javax.annotation.Resource;

import com.linkwee.api.request.activity.VoteRequest;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.service.ActActivityEventGuessingService;
import com.linkwee.web.service.CrmUserInfoService;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActActivityEventVote;
import com.linkwee.web.dao.ActActivityEventVoteMapper;
import com.linkwee.web.service.ActActivityEventVoteService;
import com.linkwee.web.service.impl.ActActivityEventVoteServiceImpl;


/**
*
* @描述：ActActivityEventVoteService 服务实现类
*
* @创建人： Hxb
*
* @创建时间：2018年05月23日 10:56:23
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
@Service("actActivityEventVoteService")
public class ActActivityEventVoteServiceImpl extends GenericServiceImpl<ActActivityEventVote, Long> implements ActActivityEventVoteService{

   private static final Logger LOGGER = LoggerFactory.getLogger(ActActivityEventVoteServiceImpl.class);

   @Resource
   private CrmUserInfoService userInfoService;
   
   @Resource
   private ActActivityEventGuessingService eventGuessingService;

   @Resource
   private ActActivityEventVoteMapper actActivityEventVoteMapper;

   @Override
   public GenericDao<ActActivityEventVote, Long> getDao() {
       return actActivityEventVoteMapper;
   }

   @Override
   public DataTableReturn selectByDatatables(DataTable dt) {
       DataTableReturn tableReturn = new DataTableReturn();
       tableReturn.setDraw(dt.getDraw()+1);
       LOGGER.debug(" -- ActActivityEventVote -- 排序和模糊查询 ");
       Page<ActActivityEventVote> page = new Page<ActActivityEventVote>(dt.getStart()/dt.getLength()+1,dt.getLength());
       List<ActActivityEventVote> list = this.actActivityEventVoteMapper.selectBySearchInfo(dt,page);
       tableReturn.setData(list);
       tableReturn.setRecordsFiltered(page.getTotalCount());
       tableReturn.setRecordsTotal(page.getTotalCount());
       return tableReturn;
   }

    @Override
    public int hadVotedNum(String activityCode, String userId) {
       return actActivityEventVoteMapper.hadVotedNum(activityCode,userId);
    }

    @Override
    public int hadVotedNumA(Integer guessingId, String userId) {
        return actActivityEventVoteMapper.hadVotedGuessNum(guessingId,userId,"a");
    }

    @Override
    public int hadVotedNumB(Integer guessingId, String userId) {
        return actActivityEventVoteMapper.hadVotedGuessNum(guessingId,userId,"b");
    }

    @Override
    public BigDecimal queryEventGuessingPrize(Integer guessingId, String userId, String supportVote) {
        return actActivityEventVoteMapper.queryEventGuessingPrize(guessingId,userId,supportVote);
    }

    @Override
    public int vote(VoteRequest request) {
       ActActivityEventVote eventVote = new ActActivityEventVote();
       eventVote.setVoteId(StringUtils.getUUID());
       eventVote.setGuessId(request.getGuessId());
       eventVote.setUserId(request.getUserId());
       CrmUserInfo userInfo = userInfoService.queryUserInfoByUserId(request.getUserId());
       eventVote.setMobile(userInfo.getMobile());
       eventVote.setSupportVote(request.getSupportVote());
       eventVote.setConsumeTimes(request.getVoteNumber());
       eventVote.setIsVirtual(0);
       eventVote.setVoteNumber(request.getVoteNumber());
       eventVote.setVoteTime(new Date());
       eventGuessingService.updateVotes(request);
       return insert(eventVote);
    }

}
