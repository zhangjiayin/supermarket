package com.linkwee.web.service.impl;

import java.util.List;
import java.lang.Long;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.api.request.activity.VoteRequest;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActActivityEventGuessing;
import com.linkwee.web.dao.ActActivityEventGuessingMapper;
import com.linkwee.web.service.ActActivityEventGuessingService;
import com.linkwee.web.service.impl.ActActivityEventGuessingServiceImpl;


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

}
