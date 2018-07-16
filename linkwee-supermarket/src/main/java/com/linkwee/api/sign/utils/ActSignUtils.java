package com.linkwee.api.sign.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.linkwee.api.activity.BaseLottery;
import com.linkwee.api.response.act.SignResponse;

public class ActSignUtils {
	
	/**
     * 给转盘的每个奖项赋初始值
     * @return
     */
    private final static List<BaseLottery> initDrawList = new ArrayList<BaseLottery>() {{
        add(new BaseLottery(21, "50元投资返现红包", 30));
        add(new BaseLottery(22, "30元投资返现红包", 30));
        add(new BaseLottery(23, "30元投资返现红包", 40));
    }};

    /**
     * 生成奖项
     * @return
     */
    public static BaseLottery generateAward() {
        long result = randomnum(1, 100);
        int line = 0;
        int temp = 0;
        BaseLottery returnobj = null;
        for (int i = 0; i < initDrawList.size(); i++) {
        	BaseLottery obj2 = initDrawList.get(i);
            int c = obj2.getVariable();
            temp = temp + c;
            line = 100 - temp;
            if (c != 0) {
                if (result > line && result <= (line + c)) {
                    returnobj = obj2;
                    break;
                }
            }
        }
        return returnobj;
    }

	public static SignResponse generateBonus(int consecutiveDays) {
		long bonus = 0;
		int times = 0;
		if(consecutiveDays >= 1 && consecutiveDays <= 7){
			bonus = randomnum(10,40);
		}else if(consecutiveDays >= 8 && consecutiveDays <= 15){
			bonus = randomnum(30,60);
		}else if(consecutiveDays >= 16){
			bonus = randomnum(50,80);
		}
		if(consecutiveDays == 7){
			times = 1;
		}else if(consecutiveDays == 30){
			times = 2;
		}
		SignResponse signResponse = new SignResponse();
		signResponse.setBonus(new BigDecimal(bonus).divide(new BigDecimal(100)));
		signResponse.setTimes(times);
		//配合前端解决显示问题 下个版本修改为 signResponse.setTimesBonus(new BigDecimal(bonus).divide(new BigDecimal(100)).multiply(new BigDecimal(times)));
		if(times == 0){
			signResponse.setTimesBonus(new BigDecimal(bonus).divide(new BigDecimal(100)).multiply(new BigDecimal(times)));
		}else{
			signResponse.setTimesBonus(new BigDecimal(bonus).divide(new BigDecimal(100)).multiply(new BigDecimal(1)));
		}		
		return signResponse;
	}
	
	// 获取2个值之间的随机数
    private static long randomnum(int smin, int smax){
        int range = smax - smin;
        double rand = Math.random();
        return (smin + Math.round(rand * range));
    }
    
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    
    public static int doublePrize() {
    	int rand = (int) randomnum(1,100);
    	if(rand <= 40){
    		return 1;
    	}
        return 0;
    }
    
}
