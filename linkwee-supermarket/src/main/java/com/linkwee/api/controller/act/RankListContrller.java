package com.linkwee.api.controller.act;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.linkwee.act.rankList.service.ActRankListService;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.RequestLogging;

@Controller
@RequestMapping(value = "api/act/rankList")
@RequestLogging("排行榜")
public class RankListContrller {
	
	@Autowired
	private ActRankListService rankListService;
	
	@RequestMapping(value="{rankListId}/myRank")
	@ResponseBody
	public Object getMyRank(AppRequestHead head,@PathVariable("rankListId")String rankListId,HttpServletRequest request,HttpServletResponse response){
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Map<String, String> parameters = getParameterMap(request);
		parameters.put("rank.userId", userId);
		return rankListService.getMyRankData(rankListId,parameters);
	}
	
	@RequestMapping(value="{rankListId}/rank")
	@ResponseBody
	public Object rankListdata(@PathVariable("rankListId")String rankListId,PaginatorRequest page,HttpServletRequest request){
		return rankListService.getRankListData(rankListId, getParameterMap(request),page);
	}
	
	private Map<String, String> getParameterMap(HttpServletRequest request){
		Map<String, String> parameterMap = Maps.newHashMap();
		@SuppressWarnings("rawtypes")
		Enumeration em = request.getParameterNames();
		while (em.hasMoreElements()) {
		    String name = (String) em.nextElement();
		    if(StringUtils.isNotBlank(name) && name.startsWith("rank.")){
		    	parameterMap.put(name, (String)request.getParameter(name));
		    }
		}
		//添加上一个月份的月份值  用于查询理财师上个月排行榜数据
		parameterMap.put("feeMonth", new DateTime().minusMonths(1).monthOfYear().getAsString());
		return parameterMap;
	}

}
