package com.linkwee.web.controller;

import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.*;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActActivityEventGuessing;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.User;
import com.linkwee.web.model.acc.AcAccountRecharge;
import com.linkwee.web.request.ChangeScoreRequest;
import com.linkwee.web.response.UserEventGuessingPrizeResponse;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.ActActivityEventGuessingService;
import com.linkwee.web.service.ActActivityEventVoteService;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.*;
import java.math.BigDecimal;
import java.util.*;

/**
*
* @描述： ActActivityEventGuessingController控制器
*
* @创建人： Hxb
*
* @创建时间：2018年05月23日 11:32:44
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
@Controller
@RequestMapping(value = "act/actactivityeventguessing")
@RequestLogging("ActActivityEventGuessingController控制器")
public class ActActivityEventGuessingController {

   private static final Logger LOGGER = LoggerFactory.getLogger(ActActivityEventGuessingController.class);

   @Resource
   private ActActivityEventGuessingService actActivityEventGuessingService;

   @Resource
   private ActActivityEventVoteService eventVoteService;

   @Resource
   private ActivityListService activityListService;

   /**
    * 转换器
    *
    * @param binder
    */
   @InitBinder
   public void initBinder(WebDataBinder binder) {
       binder.registerCustomEditor(Date.class, new DateConvertEditor());
       binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
   }

   /**
    * 查看列表
    */
   @RequestMapping(value="/list",   method=RequestMethod.GET)
   @RequestLogging("查看列表页面")
   public String actActivityEventGuessing(Model model) {
       return "actactivityeventguessing/actactivityeventguessing-list";
   }

   /**
    * datatables<br>
    * @return
    */
   @RequestMapping(value="/list", method = RequestMethod.POST)
   @ResponseBody
   @RequestLogging("查看列表")
   public DataTableReturn getActActivityEventGuessings(@RequestParam String  _dt_json) {
       LOGGER.debug("ActActivityEventGuessing list _dt_json={}", _dt_json);
       DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
       dataTable.initOrders();
       DataTableReturn tableReturn = actActivityEventGuessingService.selectByDatatables(dataTable);
       return tableReturn;
   }


   @RequestMapping(value = "/save", method = RequestMethod.POST)
   @ResponseBody
   @RequestLogging("CUD操作")
   public DataResult save(@RequestParam String rows) {
       DataInfo df = JsonUtils.fromJsonToObject(rows, DataInfo.class);
       @SuppressWarnings("unchecked")
       Map<String,ActActivityEventGuessing> map =  (Map<String, ActActivityEventGuessing>) df.getData();
       DataResult dr = new DataResult();
       List<ActActivityEventGuessing> datas = new ArrayList<ActActivityEventGuessing>();
       List<ErrorField> errors = new ArrayList<ErrorField>();
       ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
       Validator validator = factory.getValidator();
       //下面用到bean属性copy，需要对日期进行转换
       DateConverter dateConverter = new DateConverter();
       dateConverter.setPattern("yyyy-MM-dd HH:mm:ss");
       ConvertUtils.register(dateConverter, Date.class);
       try {
           if(DataInfo.ACTION_CREATE.equals(df.getAction())){
               for (String key : map.keySet()) {
                   ActActivityEventGuessing r = new ActActivityEventGuessing();
                   BeanUtils.copyProperties(r, map.get(key));
                   datas.add(r);
                   Set<ConstraintViolation<ActActivityEventGuessing>> constraintViolations = validator.validate(r);
                   for (ConstraintViolation<ActActivityEventGuessing> constraintViolation : constraintViolations) {
                       errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
                       dr.setFieldErrors(errors);
                       return dr;
                   }
                   this.actActivityEventGuessingService.insert(r);
               }
           }
           if(DataInfo.ACTION_EDIT.equals(df.getAction())){
               for (String key : map.keySet()) {
                   ActActivityEventGuessing r = new ActActivityEventGuessing();
                   BeanUtils.copyProperties(r, map.get(key));
                   datas.add(r);
                   Set<ConstraintViolation<ActActivityEventGuessing>> constraintViolations = validator.validate(r);
                   for (ConstraintViolation<ActActivityEventGuessing> constraintViolation : constraintViolations) {
                       errors.add(new ErrorField(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage()));
                       dr.setFieldErrors(errors);
                       return dr;
                   }
                   this.actActivityEventGuessingService.update(r);
               }
           }
           if(DataInfo.ACTION_REMOVE.equals(df.getAction())){
               for (String key : map.keySet()) {
                   this.actActivityEventGuessingService.delete(Long.parseLong(key));
               }
           }
       } catch (Exception e) {
           dr.setError(e.getMessage());
       }
       dr.setData(datas);
       return dr;
   }

    @RequestMapping(value="/changeScore",   method=RequestMethod.GET)
    @RequestLogging("修改比分页面")
    public String changeScorePage(@RequestParam String guessId,Model model) {
        if(StringUtils.isNotBlank(guessId)){
            model.addAttribute("eventGuess", actActivityEventGuessingService.selectById(Long.valueOf(guessId)));
        }
        return "actactivityeventguessing/change-score";
    }

    @RequestMapping(value="/changeScore",   method=RequestMethod.POST)
    @RequestLogging("修改比分")
    @ResponseBody
    public Object changeScore(@Valid ChangeScoreRequest request, BindingResult bindResult) {
        if(ResponseUtil.existsParamsError(bindResult)) {
            return ResponseUtil.getErrorParams(bindResult);
        }

        ResponseResult result = null;
        ActActivityEventGuessing eventGuessingBefore = actActivityEventGuessingService.selectById(request.getGuessId());
        if(request.getScoreA() < eventGuessingBefore.getScoreA() || request.getScoreB() < eventGuessingBefore.getScoreB()){
            result = new ResponseResult(false, "比分不能下调");
            return result;
        }
        ActActivityEventGuessing eventGuessing = new ActActivityEventGuessing();
        eventGuessing.setId(request.getGuessId().intValue());
        eventGuessing.setScoreA(request.getScoreA());
        eventGuessing.setScoreB(request.getScoreB());

        if(request.getScoreA().compareTo(eventGuessingBefore.getCouldGrantScore()) == 0 || request.getScoreB().compareTo(eventGuessingBefore.getCouldGrantScore()) == 0 ){
            //比赛结束，生成奖励，等待发放
            int voteTotal = 0;
            if(request.getScoreA().compareTo(eventGuessingBefore.getCouldGrantScore()) == 0){
                voteTotal = eventGuessingBefore.getSupportVotesA();
                if(voteTotal > 0){
                    BigDecimal perVotePrize = eventGuessingBefore.getJackpot().divide(new BigDecimal(voteTotal),2);
                    eventVoteService.generatePrize(eventGuessingBefore.getId(),"a",perVotePrize);
                }
            }else if(request.getScoreB().compareTo(eventGuessingBefore.getCouldGrantScore()) == 0){
                voteTotal = eventGuessingBefore.getSupportVotesB();
                if(voteTotal > 0){
                    BigDecimal perVotePrize = eventGuessingBefore.getJackpot().divide(new BigDecimal(voteTotal),2);
                    eventVoteService.generatePrize(eventGuessingBefore.getId(),"b",perVotePrize);
                }
            }
            eventGuessing.setGrowthRateA(0);
            eventGuessing.setGrowthRateB(0);

            //更新活动结束时间
            ActivityList selectCondition = new ActivityList();
            selectCondition.setActivityCode(eventGuessingBefore.getActivityCode());
            selectCondition = activityListService.selectOne(selectCondition);
            ActivityList activity = new ActivityList();
            activity.setId(selectCondition.getId());
            activity.setEndDate(new Date());
            activityListService.update(activity);
        }else {
            eventGuessing.setNextStartTime(DateUtils.parse(request.getNextStartTime(), DateUtils.FORMAT_LONG));
            if (request.getScoreA() == eventGuessingBefore.getCouldGrantScore() - 1 || request.getScoreB() == eventGuessingBefore.getCouldGrantScore() - 1) {
                //赛点局开赛时间为投票暂停时间
                eventGuessing.setVoteEndTime(DateUtils.parse(request.getNextStartTime(), DateUtils.FORMAT_LONG));
            }

            if (request.getScoreA() == 0) {
                eventGuessing.setSupportVotesTargetA(eventGuessingBefore.getSupportVotesA());
            } else if (request.getScoreA() == 1) {
                if (eventGuessingBefore.getSupportVotesA() < 15235) {
                    eventGuessing.setSupportVotesTargetA(15235);
                } else {
                    eventGuessing.setSupportVotesTargetA(eventGuessingBefore.getSupportVotesA());
                }
            } else if (request.getScoreA() == 2 ) {
                if (eventGuessingBefore.getSupportVotesA() < 32156) {
                    eventGuessing.setSupportVotesTargetA(32156);
                } else {
                    eventGuessing.setSupportVotesTargetA(eventGuessingBefore.getSupportVotesA());
                }
            } else if (request.getScoreA() == 3 ) {
                if (eventGuessingBefore.getSupportVotesA() < 50834) {
                    eventGuessing.setSupportVotesTargetA(50834);
                } else {
                    eventGuessing.setSupportVotesTargetA(eventGuessingBefore.getSupportVotesA());
                }
            }
            if (request.getScoreB() == 0) {
                eventGuessing.setSupportVotesTargetB(eventGuessingBefore.getSupportVotesB());
            } else if (request.getScoreB() == 1) {
                if (eventGuessingBefore.getSupportVotesB() < 13983) {
                    eventGuessing.setSupportVotesTargetB(13983);
                } else {
                    eventGuessing.setSupportVotesTargetB(eventGuessingBefore.getSupportVotesB());
                }
            } else if (request.getScoreB() == 2) {
                if (eventGuessingBefore.getSupportVotesB() < 29568) {
                    eventGuessing.setSupportVotesTargetB(29568);
                } else {
                    eventGuessing.setSupportVotesTargetB(eventGuessingBefore.getSupportVotesB());
                }
            } else if (request.getScoreB() == 3) {
                if (eventGuessingBefore.getSupportVotesB() < 49863) {
                    eventGuessing.setSupportVotesTargetB(49863);
                } else {
                    eventGuessing.setSupportVotesTargetB(eventGuessingBefore.getSupportVotesB());
                }
            }

            int leftMinutes = DateUtils.countMinutes(DateUtils.parse(request.getNextStartTime(), DateUtils.FORMAT_LONG));
            if (leftMinutes < 5) {
                leftMinutes = 5;
            }
            int rateA = (eventGuessing.getSupportVotesTargetA() - eventGuessingBefore.getSupportVotesA()) / (leftMinutes / 5);
            if (rateA < 1) {
                rateA = 1;
            }
            eventGuessing.setGrowthRateA(rateA);
            int rateB = (eventGuessing.getSupportVotesTargetB() - eventGuessingBefore.getSupportVotesB()) / (leftMinutes / 5);
            if (rateB < 1) {
                rateB = 1;
            }
            eventGuessing.setGrowthRateB(rateB);
        }

        int change = actActivityEventGuessingService.update(eventGuessing);
        if (change > 0) {
            result = new ResponseResult(true, "比分调整成功");
        } else {
            result = new ResponseResult(false, "比分调整失败");
        }
        return result;
    }

    @RequestMapping(value="/sendPrize",   method=RequestMethod.GET)
    @RequestLogging("发放奖励")
    public String sendPrizePage(@RequestParam String guessId,Model model) {
        if(StringUtils.isNotBlank(guessId)){
            ActActivityEventGuessing eventGuessingBefore = actActivityEventGuessingService.selectById(Long.valueOf(guessId));
            if(eventGuessingBefore.getScoreA().compareTo(eventGuessingBefore.getCouldGrantScore()) == 0 || eventGuessingBefore.getScoreB().compareTo(eventGuessingBefore.getCouldGrantScore()) == 0 ){
                int voteTotal = 0;
                BigDecimal prizeAmount = new BigDecimal("0");
                String winner = "";
                if(eventGuessingBefore.getScoreA().compareTo(eventGuessingBefore.getCouldGrantScore()) == 0){
                    winner = eventGuessingBefore.getCompetitionPartyA();
                    voteTotal = eventVoteService.hadVotedTotal(eventGuessingBefore.getId(),"a");
                    prizeAmount = eventVoteService.queryEventGuessingPrizeAmount(eventGuessingBefore.getId(),"a");
                }else if(eventGuessingBefore.getScoreB().compareTo(eventGuessingBefore.getCouldGrantScore()) == 0){
                    winner = eventGuessingBefore.getCompetitionPartyB();
                    voteTotal = eventVoteService.hadVotedTotal(eventGuessingBefore.getId(),"b");
                    prizeAmount = eventVoteService.queryEventGuessingPrizeAmount(eventGuessingBefore.getId(),"b");
                }
                model.addAttribute("guessId", eventGuessingBefore.getId());
                model.addAttribute("winner", winner);
                model.addAttribute("voteTotal", voteTotal);
                model.addAttribute("prizeAmount", prizeAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            }
        }
        return "actactivityeventguessing/send-prize";
    }

    @RequestMapping(value="/sendPrize", method=RequestMethod.POST)
    @RequestLogging("发放奖励")
    @ResponseBody
    public Object sendPrize(@RequestParam String guessId,HttpSession session) {

        User user = (User) session.getAttribute("userInfo");
        ResponseResult result;
        ActActivityEventGuessing eventGuessingBefore = actActivityEventGuessingService.selectById(Long.valueOf(guessId));

        if(eventGuessingBefore.getScoreA().compareTo(eventGuessingBefore.getCouldGrantScore()) == 0 || eventGuessingBefore.getScoreB().compareTo(eventGuessingBefore.getCouldGrantScore()) == 0 ){
            //发放奖励
            try {
                if(eventGuessingBefore.getScoreA().compareTo(eventGuessingBefore.getCouldGrantScore()) == 0){
                    eventVoteService.sendPrize(Long.valueOf(guessId),"a",user.getUsername());
                }else if(eventGuessingBefore.getScoreB().compareTo(eventGuessingBefore.getCouldGrantScore()) == 0 ){
                    eventVoteService.sendPrize(Long.valueOf(guessId),"b",user.getUsername());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            result = new ResponseResult(true, "发放成功！");
        }else {
            result = new ResponseResult(false, "比分未达要求，发放失败！");
        }
        return result;
    }

}
