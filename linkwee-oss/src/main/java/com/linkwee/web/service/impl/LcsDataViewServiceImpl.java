package com.linkwee.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.core.util.DateUtils;
import com.linkwee.web.dao.LcsDataViewDao;
import com.linkwee.web.service.LcsDataViewService;


@Service("lcsDataViewService")
public class LcsDataViewServiceImpl implements LcsDataViewService {
	
	@Autowired
	private LcsDataViewDao lcsDataViewDao;
	

	@Override
	public Map<String, Object> getLcsDateStaticCount() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("lcs", lcsDataViewDao.getLcsDateStaticCount());
		map.put("lcsValid", lcsDataViewDao.getValidLcsDateStaticCount());
		return map;
	}

	@Override
	public Map<String, Object> getLcsDataStatic(Map<String, Object> map) {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
		Date start = (Date)map.get("startDate");
		Date end = (Date)map.get("endDate");
		int searchDay = DateUtils.countDays(start,end)+1;
		
		Map<String, Object> lcs= new LinkedHashMap<String, Object>();
		//lcs.put("total", lcsDataViewDao.getLcsDateStaticTotal(map));
		List<Map<String, Object>> lcsData = lcsDataViewDao.getLcsDateStatic(map);
		
		List<Object> list =null;
		
		if(lcsData.size() < searchDay){
			Map<String,Object> dataMap = new LinkedHashMap<String, Object>();
			for(Map<String,Object> item:lcsData){
				dataMap.put(item.get("date").toString(),item);
			}
			list = new ArrayList<Object>();
			List<String> dates = DateUtils.getBetweeenTime(start, end, "yyyy-MM-dd");
			for(String date:dates){
				if(!dataMap.containsKey(date)){
					Map<String,Object> temp = new HashMap<String,Object>();
					
					temp.put("date", date);
					temp.put("count", 0);
					list.add(temp);
				}else{
					list.add(dataMap.get(date));
				}
			}
		}
		
		lcs.put("data",list==null?lcsData:list);
		
		
		data.put("lcs", lcs);
		
		lcs= new LinkedHashMap<String, Object>();
		
		//lcs.put("total", lcsDataViewDao.getValidLcsDateStaticTotal(map));
		List<Map<String, Object>> validLcsDate = lcsDataViewDao.getValidLcsDateStatic(map);
		
		
	
		list =null;
		
		if(validLcsDate.size() < searchDay){
			
			Map<String,Object> dataMap = new LinkedHashMap<String, Object>();
			for(Map<String,Object> item:validLcsDate){
				dataMap.put(item.get("date").toString(),item);
			}
			list = new ArrayList<Object>();
			List<String> dates = DateUtils.getBetweeenTime(start, end, "yyyy-MM-dd");
			for(String date:dates){
				if(!dataMap.containsKey(date)){
					Map<String,Object> temp = new HashMap<String,Object>();
					
					temp.put("date", date);
					temp.put("count", 0);
					list.add(temp);
				}else{
					list.add(dataMap.get(date));
				}
			}
		}
		
		lcs.put("data",list==null?validLcsDate:list);
		data.put("lcsValid", lcs);
		return data;
	}
	

	
}
