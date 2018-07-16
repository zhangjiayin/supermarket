package com.linkwee.act.rankList.component;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.linkwee.act.rankList.model.MyRank;
import com.linkwee.act.rankList.model.Ranklist;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.ActRanklistCustomMapper;
import com.linkwee.web.dao.ActRanklistMapper;

public abstract class AbstractRankList implements IRankList{
	
	@Autowired
	protected ActRanklistCustomMapper ranklistCustomMapper;
	
	@Autowired
	protected ActRanklistMapper ranklistMapper;

	
	protected void getMyRankDataBefo(String rankListId, Map<String, String> params){
		
	};
	

	protected void getMyRankDataAfter(String rankListId, Map<String, String> params,MyRank myRank){
		
	};
	
	protected void getRankListDataBefo(String rankListId,Map<String, String> params, Page<Ranklist> page){
		
	};
	

	protected void getRankListDataAfter(String rankListId, Map<String, String> params,Page<Ranklist> page,List<Ranklist> ranklists){
		
	}
	
	protected abstract MyRank getInternalMyRankData(String rankListId, Map<String, String> params);
	
	protected abstract List<Ranklist> getInternalRankListData(String rankListId,Map<String, String> params, Page<Ranklist> page);


	@Override
	public MyRank getMyRankData(String rankListId, Map<String, String> params) {
		getMyRankDataBefo(rankListId, params);
		MyRank myRank = getInternalMyRankData(rankListId, params);
		getMyRankDataAfter(rankListId, params, myRank);
		return myRank;
	}


	@Override
	public List<Ranklist> getRankListData(String rankListId,Map<String, String> params, Page<Ranklist> page) {
		getRankListDataBefo(rankListId, params, page);
		List<Ranklist> ranklistDatas = getInternalRankListData(rankListId, params, page);
		getRankListDataAfter(rankListId, params, page, ranklistDatas);
		return ranklistDatas;
	};
	
	
	
	



}
