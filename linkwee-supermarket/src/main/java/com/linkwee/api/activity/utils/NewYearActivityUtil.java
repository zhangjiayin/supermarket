package com.linkwee.api.activity.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.linkwee.api.activity.response.NewYearRankingListResponse;
import com.linkwee.api.sign.utils.ActSignUtils;
import com.linkwee.core.util.DateUtils;

public class NewYearActivityUtil {
	
	private static Map<String, List<String>> dayUsers = new HashMap<String, List<String>>() {{ 
		put("周日", Arrays.asList("187****8721","180****6506","189****6889")); 
		put("周一", Arrays.asList("150****3297","131****2245","186****3783")); 
		put("周二", Arrays.asList("136****4753","130****6678","139****2697")); 
		put("周三", Arrays.asList("159****3364","150****6115","139****3651")); 
		put("周四", Arrays.asList("132****5103","136****7749","152****5913")); 
		put("周五", Arrays.asList("136****1018","189****5526","159****5346"));
		put("周六", Arrays.asList("130****6519","180****6872","189****7766"));
	}}; 

	public static List<NewYearRankingListResponse> generateRankingList(BigDecimal newYearMaxSaleAmount, Date startDate, Date endDate) {
		List<NewYearRankingListResponse> responses = new ArrayList<NewYearRankingListResponse>();
		List<String> users = dayUsers.get(ActSignUtils.getWeekOfDate(new Date()));
		int startDays = DateUtils.countDays(DateUtils.format(startDate, DateUtils.FORMAT_SHORT)+" 00:00:00");
		int endDays = DateUtils.countDays(DateUtils.format(endDate, DateUtils.FORMAT_SHORT)+" 00:00:00");
		if(endDays > 0){
			startDays = DateUtils.countDays(endDate,startDate);
			users = dayUsers.get(ActSignUtils.getWeekOfDate(endDate));
		}
		responses.add(new NewYearRankingListResponse(1,users.get(0),newYearMaxSaleAmount.add(new BigDecimal("3406.28").multiply(new BigDecimal(10+startDays)))));
		responses.add(new NewYearRankingListResponse(2,users.get(1),newYearMaxSaleAmount.add(new BigDecimal("2345.67").multiply(new BigDecimal(9+startDays)))));
		responses.add(new NewYearRankingListResponse(3,users.get(2),newYearMaxSaleAmount.add(new BigDecimal("1234.56").multiply(new BigDecimal(8+startDays)))));
		return responses;
	}

}
