/*package com.linkwee.act.rankList.component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.linkwee.act.rankList.model.ActTDJLRanklistVirtualDetail;
import com.linkwee.act.rankList.model.MyRank;
import com.linkwee.act.rankList.model.Ranklist;
import com.linkwee.act.rankList.model.TDJLRanklist;
import com.linkwee.core.orm.paging.Page;


public class LeaderRewardRankList extends AbstractRankList{
	
	public static final String CODE = "leaderRewardRankList";
	
	public static final String VIRTUAL_DATA_PREFIX = "VIRTUAL_DATA";
	
	public static final String RANKLIST_ID = "tdjl";
	
	public static final String FORMAT_MONTH = "yyyyMM";
	
	public static final String UPDATE_VIRTUAL_DATA_DAY_KEY = "UPDATE_VIRTUAL_DATA_DAY";
	
	
	
	
	
	private static final AtomicInteger virtualDataStatus = new AtomicInteger(0); 
	
	
	private static final AtomicInteger preUpdateVirtualDataOfDay = new AtomicInteger(0);
	
	private static final Map<Integer,List<ActTDJLRanklistVirtualDetail>> DAY_VIRTUAL_DATA_MAP = Maps.newHashMap();
	
	private  volatile List<TDJLRanklist> cur_ranklist_virtual_data = null;
	
	private volatile  Set<Integer> indexs = null;
	
	@PostConstruct
	private void init(){
		List<Integer> updateVirtualDataOfDays = getUpdateVirtualDataOfDays(RANKLIST_ID);
		for (Integer day : updateVirtualDataOfDays) {
			String tdjlRanklistVirtualDetailJson = ranklistCustomMapper.getRanklistCustomValueByKey(RANKLIST_ID , StringUtils.join(new Object[]{day,VIRTUAL_DATA_PREFIX},"_"));
			DAY_VIRTUAL_DATA_MAP.put(day, JSON.parseArray(tdjlRanklistVirtualDetailJson, ActTDJLRanklistVirtualDetail.class));
		}
		
	}
	

	@Override
	public String code() {
		return CODE;
	}
	

	@Override
	protected void getMyRankDataBefo(String rankListId,Map<String, String> params) {
		params.put("month", DateTime.now().toString(FORMAT_MONTH));
	}



	@Override
	protected void getMyRankDataAfter(String rankListId,Map<String, String> params, MyRank myRank) {
		
		super.getMyRankDataAfter(rankListId, params, myRank);
	}



	@Override
	protected void getRankListDataBefo(String rankListId,Map<String, String> params, Page<Ranklist> page) {
		DateTime now =	DateTime.now();
		params.put("month", now.toString(FORMAT_MONTH));
	}
	
	private List<Integer>  getUpdateVirtualDataOfDays(String rankListId ){
		String updateVirtualDataOfDayStr = ranklistCustomMapper.getRanklistCustomValueByKey(rankListId , UPDATE_VIRTUAL_DATA_DAY_KEY);
		List<Integer> updateVirtualDataOfDays = null;
		boolean isEmpty = StringUtils.isEmpty(updateVirtualDataOfDayStr);
		if(!isEmpty){
			String[] updateVirtualDataOfDayStrs = StringUtils.split(updateVirtualDataOfDayStr, ",");
			if(ArrayUtils.isNotEmpty(updateVirtualDataOfDayStrs)){
				List<Integer> days = Lists.newArrayListWithCapacity(updateVirtualDataOfDayStrs.length);
				for (String day : updateVirtualDataOfDayStrs) {
					days.add(Integer.valueOf(day));
				}
			}
		}
		if(isEmpty || updateVirtualDataOfDays==null){
			updateVirtualDataOfDays = Lists.newArrayList(1,6,10,16,20,26);
		}
		return updateVirtualDataOfDays;
	}

	@Override
	protected void getRankListDataAfter(String rankListId,	Map<String, String> params, Page<Ranklist> page,List<Ranklist> ranklists) {
		boolean isNotEmpty = CollectionUtils.isNotEmpty(ranklists);
		if(isNotEmpty && ranklists.size() == 30) {
			
		}else{
			if(ranklists==null)ranklists = Lists.newArrayListWithCapacity(30);
			
			
			List<Integer> updateVirtualDataOfDays = getUpdateVirtualDataOfDays(rankListId);
			Integer curDay = DateTime.now().getDayOfMonth();
			int size = updateVirtualDataOfDays.size();
			Integer updateVirtualDataOfDay =Integer.valueOf(0);
			*//**
			 * 确定排行榜虚拟数据更新日
			 *//*
			for (int i = 0; i < size; i++) {
				//获取更新日
				updateVirtualDataOfDay = updateVirtualDataOfDays.get(i);
				
				//当前日期大于更新日
				if(curDay > updateVirtualDataOfDay ){
					//大于 取下一个下标值 比较
					int nexIndex = i+1;
					int lastIndex = size-1;
					
					//是否最后一个值
					if( nexIndex < lastIndex  ){
						//不是最后一个值 并且 当前日期小于下一个更新日 取当前更新日 否则继续循环
						if(curDay < updateVirtualDataOfDays.get(nexIndex)){
							break;
						}
					}else{//最后一个值 直接结束
						break;
					}
				}else if(curDay.equals(updateVirtualDataOfDay) ){
					if(preUpdateVirtualDataOfDay.get()!= updateVirtualDataOfDay){
						int day =preUpdateVirtualDataOfDay.get();
						if(preUpdateVirtualDataOfDay.compareAndSet(day, updateVirtualDataOfDay)){
							if(virtualDataStatus.get()==0){
								int virtualSize = !isNotEmpty ? 30 : 30 - ranklists.size();
								generatorVirtualData(updateVirtualDataOfDay, virtualSize);
								virtualDataStatus.set(1);
							}
							
						}
						
					}
					
					break;
				}
			}
		
			if(virtualDataStatus.get()==0){
				for (;;) {
					if(virtualDataStatus.get()==1)break;
				}
			}
			
		
		}
	}
	
	private void fillTDJLRanklistVirtualData(List<Ranklist> ranklists){
		
	}
	
	private void generatorVirtualData(Integer updateVirtualDataOfDay ,int virtualSize){
		List<ActTDJLRanklistVirtualDetail> tdjlRanklistVirtualDetails = DAY_VIRTUAL_DATA_MAP.get(updateVirtualDataOfDay);
		int index = 0;
		List<TDJLRanklist> temp_ranklist_virtual_data = Lists.newArrayListWithCapacity(virtualSize);
		Set<Integer> temp_indexs = Sets.newHashSetWithExpectedSize(virtualSize);
		TDJLRanklist tdjlRanklist = null;
		ActTDJLRanklistVirtualDetail tdjlRanklistVirtualDetail = null;
		for (int i = 0; i < virtualSize; i++) {
			do {
				index =	RandomUtils.nextInt(virtualSize);
			} while (temp_indexs.contains(index));
			temp_indexs.add(index);
			tdjlRanklistVirtualDetails.get(index);
			BigDecimal totalProfit = new BigDecimal(RandomUtils.nextInt(tdjlRanklistVirtualDetail.getMaxProfit()) % (tdjlRanklistVirtualDetail.getMaxProfit()-tdjlRanklistVirtualDetail.getMinProfit()+1) + tdjlRanklistVirtualDetail.getMinProfit());
			tdjlRanklist = new TDJLRanklist(tdjlRanklistVirtualDetail.getHeadImg(),tdjlRanklistVirtualDetail.getUserMobile(),tdjlRanklistVirtualDetail.getUserName(),totalProfit);
			temp_ranklist_virtual_data.add(tdjlRanklist);
			//ranklists.add(tdjlRanklist);
		}
		indexs = temp_indexs;
		cur_ranklist_virtual_data = temp_ranklist_virtual_data;
	}
	
	
	
	
	public static volatile AtomicBoolean a = new AtomicBoolean(false); 
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			System.out.println(RandomUtils.nextInt(1));
		}
		
		final CountDownLatch downLatch = new CountDownLatch(1);
		final String updateVirtualDataOfDayStr = "1,6,10,16,20,26";
		Runnable run = new Runnable() {
			public void run() {
				downLatch.countDown();
				while (!a.get()) {
					
				}
				List<Integer> updateVirtualDataOfDays = null;
				boolean isEmpty = StringUtils.isEmpty(updateVirtualDataOfDayStr);
				if(!isEmpty){
					String[] updateVirtualDataOfDayStrs = StringUtils.split(updateVirtualDataOfDayStr, ",");
					if(ArrayUtils.isNotEmpty(updateVirtualDataOfDayStrs)){
						List<Integer> days = Lists.newArrayListWithCapacity(updateVirtualDataOfDayStrs.length);
						for (String day : updateVirtualDataOfDayStrs) {
							days.add(Integer.valueOf(day));
						}
					}
				}
				if(isEmpty || updateVirtualDataOfDays==null){
					updateVirtualDataOfDays = Lists.newArrayList(1,6,10,16,20,26);
				}
				Integer curDay = DateTime.now().getDayOfMonth();
				int size = updateVirtualDataOfDays.size();
				Integer updateVirtualDataOfDay =Integer.valueOf(0);
				*//**
				 * 确定排行榜虚拟数据更新日
				 *//*
				for (int i = 0; i < size; i++) {
					//获取更新日
					updateVirtualDataOfDay = updateVirtualDataOfDays.get(i);
					
					//当前日期大于更新日
					if(curDay > updateVirtualDataOfDay ){
						//大于 取下一个下标值 比较
						int nexIndex = i+1;
						int lastIndex = size-1;
						
						//是否最后一个值
						if( nexIndex < lastIndex  ){
							//不是最后一个值 并且 当前日期小于下一个更新日 取当前更新日 否则继续循环
							if(curDay < updateVirtualDataOfDays.get(nexIndex)){
								break;
							}
						}else{//最后一个值 直接结束
							break;
						}
					}else if(curDay.equals(updateVirtualDataOfDay) ){
						if(preUpdateVirtualDataOfDay.get()!= updateVirtualDataOfDay){
							int day =preUpdateVirtualDataOfDay.get();
							if(preUpdateVirtualDataOfDay.compareAndSet(day, updateVirtualDataOfDay)){
								System.out.println(preUpdateVirtualDataOfDay.get());
								
							}
						}else{
							System.out.println(0);
						}
						
						break;
					}
				}
				System.out.println("end........");
			}
		};
		
		ExecutorService service = Executors.newFixedThreadPool(20);
		for (int i = 0; i < 1; i++) {
			service.execute(run);
		}
		downLatch.await();
		a.set(true);
		System.out.println("start.............");
		service.shutdown();
		
	}



	@Override
	protected MyRank getInternalMyRankData(String rankListId,Map<String, String> params) {
		
		return null;
	}

	@Override
	protected List<Ranklist> getInternalRankListData(String rankListId,Map<String, String> params, Page<Ranklist> page) {
		return null;
	}
	
	


	

}
*/