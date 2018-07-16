package com.linkwee.api.controller.activity;

import com.linkwee.api.request.activity.VoteRequest;
import com.linkwee.api.response.activity.NBAEventGuessingResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActActivityEventGuessing;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.service.ActActivityEventGuessingService;
import com.linkwee.web.service.ActActivityEventVoteService;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.DateUtils;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.RequestLogging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;

/**
*
* @描述： ActActivityEventGuessingController控制器
*
* @创建人： Hxb
*
* @创建时间：2018年05月23日 10:56:23
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
@Controller
@RequestMapping(value = "/api/activity/eventguessing")
@RequestLogging("赛事竞猜控制器")
public class EventGuessingActivityController {

   private static final Logger LOGGER = LoggerFactory.getLogger(EventGuessingActivityController.class);

   @Resource
   private ActActivityEventGuessingService actActivityEventGuessingService;

   @Resource
   private ActActivityEventVoteService actActivityEventVoteService;

   @Resource
   private ActivityListService activityListService;

    @RequestLogging("竞猜信息")
    @RequestMapping("info")
    @ResponseBody
    public BaseResponse guessingInfo(AppRequestHead head, String userId) {
        if(StringUtils.isBlank(userId) || userId.equals("undefined")){
            userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
        }
        NBAEventGuessingResponse resp = new NBAEventGuessingResponse();
        String activityCode = "nba_event_guessing";

        ActivityList selectCondition = new ActivityList();
        selectCondition.setActivityCode(activityCode);
        ActivityList activity = activityListService.selectOne(selectCondition);
        if(activity != null) {
            ActActivityEventGuessing eventGuessing = actActivityEventGuessingService.queryEventGuessingByActivityCode(activityCode);
            if (eventGuessing != null) {
                BeanUtils.copyProperties(eventGuessing,resp);
                resp.setGuessId(eventGuessing.getId());
                if(eventGuessing.getScoreA().compareTo(eventGuessing.getCouldGrantScore()) == 0){
                    resp.setWinningParty("a");
                }else if(eventGuessing.getScoreB().compareTo(eventGuessing.getCouldGrantScore()) == 0){
                    resp.setWinningParty("b");
                }
                resp.setPrize(new BigDecimal("0"));
                if (!StringUtils.isBlank(userId) && !userId.equals("undefined")) {
                    int totalVotes = actActivityEventGuessingService.userActivityGuessingVotes(activityCode,DateUtils.format(activity.getStartDate(), DateUtils.FORMAT_LONG), DateUtils.format(activity.getEndDate(), DateUtils.FORMAT_LONG),userId);
                    int hadVotedNum = actActivityEventVoteService.hadVotedNum(activityCode,userId);
                    int hadVotedNumA = actActivityEventVoteService.hadVotedNumA(eventGuessing.getId(),userId);
                    int hadVotedNumB = actActivityEventVoteService.hadVotedNumB(eventGuessing.getId(),userId);
                    int leftVotes = totalVotes - hadVotedNum;
                    resp.setLeftVotes(leftVotes);
                    resp.setHadVotedNumA(hadVotedNumA);
                    resp.setHadVotedNumB(hadVotedNumB);
                    if(eventGuessing.getScoreA().compareTo(eventGuessing.getCouldGrantScore()) == 0){
                        BigDecimal prize = actActivityEventVoteService.queryEventGuessingPrize(eventGuessing.getId(),userId,"a");
                        resp.setPrize(prize);
                    }else if(eventGuessing.getScoreB().compareTo(eventGuessing.getCouldGrantScore()) == 0){
                        BigDecimal prize = actActivityEventVoteService.queryEventGuessingPrize(eventGuessing.getId(),userId,"b");
                        resp.setPrize(prize);
                    }
                }
                if (DateUtils.compareDate(new Date(), eventGuessing.getVoteStartTime()) > 0) {
                    if (eventGuessing.getVoteEndTime() == null || DateUtils.compareDate(new Date(), eventGuessing.getVoteEndTime()) < 0) {
                        resp.setGuessingStatus(1);
                    } else if (eventGuessing.getScoreA().compareTo(eventGuessing.getCouldGrantScore()) == 0 || eventGuessing.getScoreB().compareTo(eventGuessing.getCouldGrantScore()) == 0) {
                        resp.setGuessingStatus(4);
                    } else {
                        resp.setGuessingStatus(3);
                    }
                } else {
                    resp.setGuessingStatus(2);
                }
            } else {
                return AppResponseUtil.getErrorBusi("10091", "竞猜活动不存在");
            }
        }else {
            return AppResponseUtil.getErrorBusi("10086", "活动不存在");
        }
        return AppResponseUtil.getSuccessResponse(resp);
    }

    @RequestLogging("竞猜投票")
    @RequestMapping("vote")
    @ResponseBody
    public BaseResponse vote(AppRequestHead head, @Valid VoteRequest request) {
        String userId = request.getUserId();
        if(StringUtils.isBlank(userId) || userId.equals("undefined")){
            userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
            if(StringUtils.isBlank(userId) || userId.equals("undefined")){
                return AppResponseUtil.getErrorToken();
            }
            request.setUserId(userId);
        }

        String activityCode = "nba_event_guessing";

        ActivityList selectCondition = new ActivityList();
        selectCondition.setActivityCode(activityCode);
        ActivityList activity = activityListService.selectOne(selectCondition);
        if(activity != null) {
            ActActivityEventGuessing eventGuessing = actActivityEventGuessingService.queryEventGuessingByActivityCode(activityCode);
            if (eventGuessing != null) {
                if (DateUtils.compareDate(new Date(), eventGuessing.getVoteStartTime()) > 0) {
                    if (eventGuessing.getVoteEndTime() == null || DateUtils.compareDate(new Date(), eventGuessing.getVoteEndTime()) < 0) {
                        int totalVotes = actActivityEventGuessingService.userActivityGuessingVotes(activityCode,DateUtils.format(activity.getStartDate(), DateUtils.FORMAT_LONG), DateUtils.format(activity.getEndDate(), DateUtils.FORMAT_LONG),userId);
                        int hadVotedNum = actActivityEventVoteService.hadVotedNum(activityCode,userId);
                        int leftVotes = totalVotes - hadVotedNum;
                        if(leftVotes >= request.getVoteNumber()){
                            actActivityEventVoteService.vote(request);
                        }else{
                            return AppResponseUtil.getErrorBusi("10095", "拥有的票数不足本次投票");
                        }
                    } else if (eventGuessing.getScoreA().compareTo(eventGuessing.getCouldGrantScore()) == 0 || eventGuessing.getScoreB().compareTo(eventGuessing.getCouldGrantScore()) == 0) {
                        return AppResponseUtil.getErrorBusi("10094", "竞猜结束");
                    } else {
                        return AppResponseUtil.getErrorBusi("10093", "不在竞猜活动投票时间内");
                    }
                } else {
                    return AppResponseUtil.getErrorBusi("10092", "竞猜活动尚未开放投票");
                }
            } else {
                return AppResponseUtil.getErrorBusi("10091", "竞猜活动不存在");
            }
        }else {
            return AppResponseUtil.getErrorBusi("10086", "活动不存在");
        }
        return AppResponseUtil.getSuccessResponse();
    }

}
