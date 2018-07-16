package com.linkwee.act.rankList.component;

import java.util.List;
import java.util.Map;

import com.linkwee.act.rankList.model.MyRank;
import com.linkwee.act.rankList.model.Ranklist;
import com.linkwee.core.orm.paging.Page;

/**
 * 排行榜接口
 * @author ch  
 *
 */
public interface IRankList {
	/**
	 * 排行榜code
	 * @return
	 */
	String code();
	
	/**
	 * 我的排名
	 * @param rankListId 
	 * @param params
	 * @return
	 */
	MyRank getMyRankData(String rankListId,Map<String, String> params);
	
	
	/**
	 *	排行榜列表 
	 * @param rankListId
	 * @param params
	 * @param page
	 * @return
	 */
	List<Ranklist> getRankListData(String rankListId,Map<String, String> params,Page<Ranklist> page);
}
