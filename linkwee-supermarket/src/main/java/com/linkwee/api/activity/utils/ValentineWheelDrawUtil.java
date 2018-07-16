package com.linkwee.api.activity.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.linkwee.api.activity.BaseLottery;

public class ValentineWheelDrawUtil {
	/**
     * 给转盘的每个奖项赋初始值
     * @return
     */
    private final static List<BaseLottery> initDrawList = new ArrayList<BaseLottery>() {{
        add(new BaseLottery(771, "28元投资红包", 400, 2, null));
        add(new BaseLottery(772, "100元投资红包", 350, 2, null));
        add(new BaseLottery(773, "250元投资红包", 98, 2, null));
        add(new BaseLottery(774, "8元现金", 100, 1, new BigDecimal(8)));
        add(new BaseLottery(775, "18元现金", 50, 1, new BigDecimal(18)));
        add(new BaseLottery(776, "小米VR眼镜", 1, 3, null));
        add(new BaseLottery(777, "米家小白智能摄像机", 1, 3, null));
    }};
    
    private final static List<BaseLottery> firstDrawList = new ArrayList<BaseLottery>() {{
        add(new BaseLottery(771, "28元投资红包", 420, 2, null));
        add(new BaseLottery(772, "100元投资红包", 410, 2, null));
        add(new BaseLottery(773, "250元投资红包", 170, 2, null));
    }};

    /**
     * 生成奖项
     * @return
     */
    public static BaseLottery generateAward() {
        long result = randomnum(1, 1000);
        int line = 0;
        int temp = 0;
        BaseLottery returnobj = null;
        for (int i = 0; i < initDrawList.size(); i++) {
        	BaseLottery obj2 = initDrawList.get(i);
            int c = obj2.getVariable();
            temp = temp + c;
            line = 1000 - temp;
            if (c != 0) {
                if (result > line && result <= (line + c)) {
                    returnobj = obj2;
                    break;
                }
            }
        }
        return returnobj;
    }
    
    /**
     * 首次抽奖生成奖项
     * @return
     */
    public static BaseLottery generateFirstAward() {
        long result = randomnum(1, 1000);
        int line = 0;
        int temp = 0;
        BaseLottery returnobj = null;
        for (int i = 0; i < firstDrawList.size(); i++) {
        	BaseLottery obj2 = firstDrawList.get(i);
            int c = obj2.getVariable();
            temp = temp + c;
            line = 1000 - temp;
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

}
