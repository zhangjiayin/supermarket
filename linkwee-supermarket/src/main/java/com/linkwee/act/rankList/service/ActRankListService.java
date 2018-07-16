package com.linkwee.act.rankList.service;

import java.util.Map;

import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;

/**
 * 排行榜
 * @author ch
 * @date 2017-02-10
 *
 */
public interface ActRankListService {
	
	
	String queryPriftRankListNo1(Integer feeMonth);
	BaseResponse getMyRankData(String rankListId,Map<String, String> params);
	BaseResponse getRankListData(String rankListId,Map<String, String> params,PaginatorRequest page);

}
