package com.linkwee.web.service.impl;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.ActActivityEventGuessingMapper;
import com.linkwee.web.model.ActActivityEventGuessing;
import com.linkwee.web.request.VoteRequest;
import com.linkwee.web.service.ActActivityEventGuessingService;
import com.linkwee.xoss.helper.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
*
* @描述：ActActivityEventGuessingService 服务实现类
*
* @创建人： Hxb
*
* @创建时间：2018年05月23日 10:56:23
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
@Service("actActivityEventGuessingService")
public class ActActivityEventGuessingServiceImpl extends GenericServiceImpl<ActActivityEventGuessing, Long> implements ActActivityEventGuessingService{

   private static final Logger LOGGER = LoggerFactory.getLogger(ActActivityEventGuessingServiceImpl.class);

   @Resource
   private ActActivityEventGuessingMapper actActivityEventGuessingMapper;

   @Override
   public GenericDao<ActActivityEventGuessing, Long> getDao() {
       return actActivityEventGuessingMapper;
   }

   @Override
   public DataTableReturn selectByDatatables(DataTable dt) {
       DataTableReturn tableReturn = new DataTableReturn();
       tableReturn.setDraw(dt.getDraw()+1);
       LOGGER.debug(" -- ActActivityEventGuessing -- 排序和模糊查询 ");
       Page<ActActivityEventGuessing> page = new Page<ActActivityEventGuessing>(dt.getStart()/dt.getLength()+1,dt.getLength());
       List<ActActivityEventGuessing> list = this.actActivityEventGuessingMapper.selectBySearchInfo(dt,page);
       tableReturn.setData(list);
       tableReturn.setRecordsFiltered(page.getTotalCount());
       tableReturn.setRecordsTotal(page.getTotalCount());
       return tableReturn;
   }

    @Override
    public ActActivityEventGuessing queryEventGuessingByActivityCode(String activityCode) {
        return actActivityEventGuessingMapper.queryEventGuessingByActivityCode(activityCode);
    }

    @Override
    public int userActivityGuessingVotes(String activityCode, String startTime, String endTime, String userId) {
        return actActivityEventGuessingMapper.userNBAGuessingVotes(startTime,endTime,userId);
    }

	@Override
	public int updateVotes(VoteRequest request) {
		return actActivityEventGuessingMapper.updateVotes(request);
	}

    @Override
    public int updateEventGuessingVotes() {
        List<ActActivityEventGuessing> eventGuessingList = selectListByCondition(null);
        if(eventGuessingList != null && eventGuessingList.size() > 0){
            for(ActActivityEventGuessing eventGuessing : eventGuessingList){
                if (DateUtils.compareDate(new Date(), eventGuessing.getVoteStartTime()) > 0) {
                    if (eventGuessing.getVoteEndTime() == null || DateUtils.compareDate(new Date(), eventGuessing.getVoteEndTime()) < 0) {
                        VoteRequest request = new VoteRequest();
                        request.setGuessId(eventGuessing.getId());

                        if (eventGuessing.getGrowthRateA() > 0) {
                            request.setSupportVote("a");
                            request.setVoteNumber(eventGuessing.getGrowthRateA());
                            actActivityEventGuessingMapper.updateVotes(request);
                        }

                        if (eventGuessing.getGrowthRateB() > 0) {
                            request.setSupportVote("b");
                            request.setVoteNumber(eventGuessing.getGrowthRateB());
                            actActivityEventGuessingMapper.updateVotes(request);
                        }
                    }
                }
            }
        }
        return 1;
    }

    @Override
    public int updateEventGuessingStatus(Long guessId, String supportVote) {
        ActActivityEventGuessing guessing = new ActActivityEventGuessing();
        guessing.setId(guessId.intValue());
        guessing.setGrantStatus(1);
        return update(guessing);
    }

}
