package com.linkwee.api.activity.utils;

import com.alibaba.fastjson.JSON;
import com.linkwee.api.activity.BaseLottery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OneYuanDrawUtil {

	/**
     * 给转盘的每个奖项赋初始值
     * @return
     */
    private final static List<BaseLottery> initDrawList = new ArrayList<BaseLottery>() {{
        add(new BaseLottery(20181001, "[稀有] 小米 5X", 1, 3, null, 1));
        add(new BaseLottery(20181002, "小米小钢炮蓝牙音箱", 200, 3, null, 1));
        add(new BaseLottery(20181003, "九阳（Joyoung）榨汁机", 200, 3, null, 1));
        add(new BaseLottery(20181004, "酷我（KUWO）K1 无线蓝牙运动耳机", 200, 3, null, 1));
        add(new BaseLottery(20181005, "爱奇艺VIP会员", 500, 3, null, 2));
        add(new BaseLottery(20181006, "10元现金", 400, 1, new BigDecimal(10), 0));
        //add(new BaseLottery(20181007, "3元投资红包", 180000, 2, null, 0));
        add(new BaseLottery(20181009, "1元奖励金", 200000, 5, new BigDecimal(1), 0));
        add(new BaseLottery(20181010, "0.5元现金", 356899, 1, new BigDecimal(0.5), 0));
        //add(new BaseLottery(20181011, "16元投资红包", 180000, 2, null, 0));
        add(new BaseLottery(20181012, "加佣券1%", 81600, 6, null, 0));
        add(new BaseLottery(20181013, "1元投资红包", 180000, 2, null, 0));
        add(new BaseLottery(20181014, "3元投资红包", 180000, 2, null, 0));
    }};

    /**
     * 生成奖项
     * @return
     */
    public static BaseLottery generateAward() {
        long result = randomnum(1, 1000000);
        int line = 0;
        int temp = 0;
        BaseLottery returnobj = null;
        for (int i = 0; i < initDrawList.size(); i++) {
        	BaseLottery obj2 = initDrawList.get(i);
            int c = obj2.getVariable();
            temp = temp + c;
            line = 1000000 - temp;
            if (c != 0) {
                if (result > line && result <= (line + c)) {
                    returnobj = obj2;
                    break;
                }
            }
        }
        return returnobj;
    }

    // 获取2个值之间的随机数
    private static long randomnum(int smin, int smax){
        int range = smax - smin;
        double rand = Math.random();
        return (smin + Math.round(rand * range));
    }

    public static void main(String[] args) {
    	int count = 0;
    	for(int i = 0; i < 1000; i++){
    		BaseLottery temp = generateAward();
    		if(temp.getId() == 2 || temp.getId() == 3 || temp.getId() == 4 || temp.getId() == 5){
    			System.out.println(JSON.toJSONString(temp));
    		}
    		if(temp.getId() == 1){
    			count++;
    		}
    	}
        System.out.println(count);
    }

	public static List<BaseLottery> generateAwards(int num) {
		List<BaseLottery> lotteries = new ArrayList<BaseLottery>();
		for(int i = 0; i < num; i++){
			BaseLottery temp = generateAward();
			lotteries.add(temp);
		}
		return lotteries;
	}

}
