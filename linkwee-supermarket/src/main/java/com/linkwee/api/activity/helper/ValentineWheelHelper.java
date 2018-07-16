package com.linkwee.api.activity.helper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.linkwee.api.activity.BaseLottery;
import com.linkwee.api.activity.utils.ValentineWheelDrawUtil;

@Component
public class ValentineWheelHelper {
		
	public BaseLottery firstAwardWithOutSomePrize(List<Integer> prizeLevelList){
		BaseLottery tempResult = ValentineWheelDrawUtil.generateAward();
		for(Integer level : prizeLevelList){
			if(tempResult.getId().intValue() == level.intValue()){
				tempResult = firstAwardWithOutSomePrize(prizeLevelList);
			}
		}
		return tempResult;
	}
}
