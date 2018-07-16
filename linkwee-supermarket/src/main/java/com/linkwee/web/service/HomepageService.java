package com.linkwee.web.service;

import com.linkwee.api.response.CradListResponse;
import com.linkwee.api.response.HomePageInvestResponse;
import com.linkwee.xoss.api.AppRequestHead;

import java.util.List;
import java.util.Map;

public interface HomepageService {

	/**
	 * 首页理财师发放佣金累计和出单
	 * @return
	 */
	Map<String,Object> querySysCfpHomepageInfo();

	/**
	 * 首页卡片列表
	 * @param head
	 * @return
	 */
	List<CradListResponse> cfpCardList(AppRequestHead head);

	/**
	 * 用户投资统计
	 * @param head
	 * @return
	 */
	Map<String,Object> cfpInvestStatistic(AppRequestHead head);

	/**
	 * 首页投资列表
	 * @return
	 */
	List<HomePageInvestResponse> homepageInvestList();

}
