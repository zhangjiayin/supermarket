package com.linkwee.act.rankList.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.linkwee.act.rankList.model.ActRanklistVirtualData;
import com.linkwee.act.rankList.model.ActRanklistVirtualDetail;
import com.linkwee.act.rankList.service.RankListService;
import com.linkwee.web.dao.ActRanklistCustomMapper;
import com.linkwee.web.dao.ActRanklistMapper;
import com.linkwee.web.service.SysConfigService;
@Service("rankListServiceImpl")
public class RankListServiceImpl implements RankListService{
	
	public static final String VIRTUAL_DATA_PREFIX = "VIRTUAL_DATA";
	
	public static final String[] RANKLIST_IDS = {"zyb"};
	
	public static final String VIRTUAL_DATA_MULTIPLE = "VIRTUAL_DATA_MULTIPLE";

	@Autowired
	private ActRanklistCustomMapper ranklistCustomMapper;
	
	@Autowired
	private SysConfigService configService;
	
	@Autowired
	private ActRanklistMapper ranklistMapper;
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void updateRanklistVirtualData() {
		for (String ranklist_id : RANKLIST_IDS) {
			List<ActRanklistVirtualDetail> virtualDetails = getVirtualDetail(ranklist_id);
			if(CollectionUtils.isEmpty(virtualDetails)) continue ;
			List<ActRanklistVirtualData> ranklistVirtualDatas = generatorRanklistVirtualData(ranklist_id,virtualDetails);
			ranklistMapper.updateRankListVirtualDataStatus(ranklist_id);
			ranklistMapper.insetsVirtualData(ranklistVirtualDatas);
		}
	}
	
	private List<ActRanklistVirtualData> generatorRanklistVirtualData(String ranklist_id,List<ActRanklistVirtualDetail> virtualDetails ){
		List<ActRanklistVirtualData> ranklistDatas = Lists.newArrayListWithCapacity(virtualDetails.size());
		ActRanklistVirtualData ranklistVirtualData = null;
		ActRanklistVirtualDetail ranklistVirtualDetail = null;
		
		String vitdataMult = ranklistCustomMapper.getRanklistCustomValueByKey(ranklist_id, VIRTUAL_DATA_MULTIPLE);
		if(StringUtils.isBlank(vitdataMult)){
			vitdataMult = "1";
		}
		
		for (int i = 0; i < virtualDetails.size(); i++) {
			
			ranklistVirtualDetail = virtualDetails.get(i);
			BigDecimal randomValue = new BigDecimal(RandomUtils.nextFloat()*10).setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal totalProfit = new BigDecimal(RandomUtils.nextInt(ranklistVirtualDetail.getMaxProfit()) % (ranklistVirtualDetail.getMaxProfit()-ranklistVirtualDetail.getMinProfit()+1) + ranklistVirtualDetail.getMinProfit()).multiply(new BigDecimal(vitdataMult)).add(randomValue);
			
			ranklistVirtualData = new ActRanklistVirtualData(ranklist_id,"",ranklistVirtualDetail.getHeadImg(),ranklistVirtualDetail.getUserMobile(),ranklistVirtualDetail.getUserName(),totalProfit);
			ranklistDatas.add(ranklistVirtualData);
		}
		 
		Collections.sort(ranklistDatas,new Comparator<ActRanklistVirtualData>() {
			@Override
			public int compare(ActRanklistVirtualData o1,ActRanklistVirtualData o2) {
				
				int compare = o1.getTotalProfit().compareTo(o2.getTotalProfit());
				if(compare!=0){
					return compare==1?-1:1;
				}
				return 0; 
				
			}
		});
		
		
		int top30 = 30;
		String topLevelName = null;
		String topLevelSecondName = null;
		String otherLevelName = null;
		int d = DateTime.now().getMonthOfYear()%2;
		if(d==1){
			topLevelName="经理";
			topLevelSecondName="顾问";
			otherLevelName="见习";
		}else{
			topLevelName="总监";
			topLevelSecondName="经理";
			otherLevelName="顾问";
		}
		for (ActRanklistVirtualData actRanklistVirtualData : ranklistDatas) {
			if(top30 >= 22){
				--top30;
				actRanklistVirtualData.setLevelName(topLevelName);
			}else if(top30 >= 10){
				--top30;
				actRanklistVirtualData.setLevelName(topLevelSecondName);
			} else{
				actRanklistVirtualData.setLevelName(otherLevelName);
			}
		}
		
		return ranklistDatas;
	}
	
	private List<ActRanklistVirtualDetail> getVirtualDetail(String ranklist_id){
		String virtual_datas = ranklistCustomMapper.getRanklistCustomValueByKey(ranklist_id, StringUtils.join(new Object[]{String.valueOf(DateTime.now().getDayOfMonth()),VIRTUAL_DATA_PREFIX},"_"));
		if(StringUtils.isEmpty(virtual_datas))return null;
		return JSON.parseArray(virtual_datas, ActRanklistVirtualDetail.class);
	}

}
