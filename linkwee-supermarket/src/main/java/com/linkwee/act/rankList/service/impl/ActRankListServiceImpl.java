package com.linkwee.act.rankList.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.linkwee.act.rankList.component.IRankList;
import com.linkwee.act.rankList.model.ActRanklist;
import com.linkwee.act.rankList.model.MyRank;
import com.linkwee.act.rankList.model.Ranklist;
import com.linkwee.act.rankList.service.ActRankListService;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.ActRanklistMapper;
import com.linkwee.xoss.util.AppResponseUtil;

@Service("actRankListServiceImpl")
public class ActRankListServiceImpl implements ActRankListService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActRankListServiceImpl.class);
	
	@Autowired
	private ActRanklistMapper ranklistMapper;
	
	
	private Map<String, IRankList> rankListMap;
	
	
	
	@Autowired
	public void setRankLists(List<IRankList> rankLists) {
		rankListMap =  Maps.newHashMap();
		if(CollectionUtils.isEmpty(rankLists))return;
		for (IRankList rankList : rankLists) {
			rankListMap.put(rankList.code(), rankList);
		}
	}

	@Override
	public BaseResponse getRankListData(String rankListId,Map<String, String> params,PaginatorRequest pageReq) {
		try{
			Page<Ranklist> page  = new Page<Ranklist>(pageReq.getPageIndex()>2?2:pageReq.getPageIndex(),pageReq.getPageSize()>10?10:pageReq.getPageSize());
			PaginatorResponse<Ranklist> paginatorResponse = new PaginatorResponse<Ranklist>();
			
			List<Ranklist> ranklistdatas = null;
			
			if(StringUtils.isEmpty(rankListId)){
				
				ranklistdatas =	Collections.emptyList();
				
			}else{
				ActRanklist ranklist =new ActRanklist();
				ranklist.setRanklistId(rankListId);
				ranklist = ranklistMapper.selectOneByCondition(ranklist);
				
				if(ranklist==null){
					ranklistdatas =	Collections.emptyList();
				}else{
					IRankList iRankList = rankListMap.get(ranklist.getRanklistCode());
					if(iRankList==null){
						ranklistdatas =	Collections.emptyList();
					}else{
						ranklistdatas = iRankList.getRankListData(rankListId, params, page);
						page.setTotalPages(page.getTotalPages()>2?2:page.getTotalPages());
						page.setTotalCount(page.getTotalCount()>20?20:page.getTotalCount());
					}
				}
			}
			paginatorResponse.setValuesByPage(page);
			paginatorResponse.setDatas(ranklistdatas);
			return  AppResponseUtil.getSuccessResponse(paginatorResponse);
		}catch(Exception e){
			LOGGER.error("getRankListData exception rankListId={},params={},pageReq={},exception={}", new Object[]{rankListId,params,pageReq,e});
		}
		
		return  AppResponseUtil.getErrorServ();
	}

	@Override
	public BaseResponse getMyRankData(String rankListId,Map<String, String> params) {
		try{
			MyRank myRank=null;
			if(StringUtils.isEmpty(rankListId)){
				
				return AppResponseUtil.getSuccessResponse(myRank);
				
			}else{
				ActRanklist ranklist =new ActRanklist();
				ranklist.setRanklistId(rankListId);
				ranklist = ranklistMapper.selectOneByCondition(ranklist);
				
				if(ranklist==null){
					return AppResponseUtil.getSuccessResponse(myRank);
				}else{
					IRankList iRankList = rankListMap.get(ranklist.getRanklistCode());
					if(iRankList==null){
						return AppResponseUtil.getSuccessResponse(myRank);
					}else{
						return  AppResponseUtil.getSuccessResponse(iRankList.getMyRankData(rankListId, params));
					}
				}
			}
		}catch(Exception e){
			LOGGER.error("getRankListData exception rankListId={},params={},exception={}", new Object[]{rankListId,params,e});
		}
		return  AppResponseUtil.getErrorServ();
	}

	@Override
	public String queryPriftRankListNo1(Integer feeMonth) {
		return ranklistMapper.queryPriftRankListNo1(feeMonth);
	}


}
