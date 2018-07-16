package com.linkwee.act.rankList.component;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.linkwee.act.rankList.model.MyRank;
import com.linkwee.act.rankList.model.Ranklist;
import com.linkwee.core.orm.paging.Page;


@Component
public class CommonRankList extends AbstractRankList{
	
	public static final String CODE = "commonRankList";
	
	private static final String RANK_SQL_KEY = "SHOW_DATA_SQL";
	
	private static final String MY_RANK_SQL_KEY = "SHOW_MY_DATA_SQL";
	

	@Override
	public String code() {
		return CODE;
	}
	
	@Override
	protected MyRank getInternalMyRankData(String rankListId, Map<String, String> params) {
		String show_my_data_sql = ranklistCustomMapper.getRanklistCustomValueByKey(rankListId, MY_RANK_SQL_KEY);
		show_my_data_sql = replaceSql(show_my_data_sql, params);
		return ranklistMapper.getCommonRankListBySql(show_my_data_sql);
	}


	@Override
	protected List<Ranklist> getInternalRankListData(String rankListId,Map<String, String> params, Page<Ranklist> page) {
		String show_data_sql = ranklistCustomMapper.getRanklistCustomValueByKey(rankListId, RANK_SQL_KEY);
		if(StringUtils.isEmpty(show_data_sql)){}
		show_data_sql = replaceSql(show_data_sql, params);
		return ranklistMapper.getCommonRankListsBySql(show_data_sql,page);
	}
	
	private String replaceSql(String sql , Map<String, String> params){
		for (Entry<String, String> element : params.entrySet()) {
			sql = sql.replace(StringUtils.join(new Object[]{"#{",element.getKey(),"}"}), element.getValue());
		}
		return sql;
	}
	

}
