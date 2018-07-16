package com.linkwee.web.push;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.linkwee.openplatform.common.vo.CommonInvestRecordVO;
import com.linkwee.web.model.CimProductInvestRecordPull;
import com.linkwee.web.model.CimProductRepaymentRecordPull;
import com.linkwee.web.model.SysThirdkeyConfigPull;
import com.linkwee.web.push.vo.ResultVO;
import com.linkwee.web.service.CimProductInvestRecordPullService;
import com.linkwee.web.service.CimProductRepaymentRecordPullService;
import com.linkwee.web.service.SysThirdkeyConfigPullService;
import com.linkwee.xoss.helper.OpenApiHelper;

@Component
public class DefaultPlatformDataPush implements PlatformDataPush{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPlatformDataPush.class);
	
	@Autowired
	private CimProductInvestRecordPullService investRecordPullService;
	
	@Autowired
	private CimProductRepaymentRecordPullService repaymentRecordPullService;
	
	@Autowired
	private SysThirdkeyConfigPullService thirdkeyConfigPullService;
	
	@Autowired
	private OpenApiHelper openApiHelper;

	@Override
	public void pushInvestRecord() throws Throwable {
		
		Map<String, SysThirdkeyConfigPull> orgCache = Maps.newHashMapWithExpectedSize(64);
		//investRecordPullService.updateInvestRecordInStatus(new Integer{}, afterStatus, msg)
		List<CimProductInvestRecordPull> datas = investRecordPullService.getInvestRecord();
		if(CollectionUtils.isEmpty(datas))return;
		investRecordPullService.updateInvestRecordInStatus(new Integer[]{1,2}, 3,DateTime.now().toString("yyyy-MM-dd HH:mm:ss") + " 推送执行中");
		Map<Integer, List<ResultVO>> resultMaps = Maps.newLinkedHashMap();
		//推送失败记录
		resultMaps.put(1,Lists.<ResultVO>newLinkedList());
		//推送成功记录
		resultMaps.put(0,Lists.<ResultVO>newLinkedList());
		LOGGER.info("###############################################调用领会投资记录推送接口###############################################");
		for (CimProductInvestRecordPull investRecordPull : datas) {
			SysThirdkeyConfigPull thirdkeyConfig = orgCache.get(investRecordPull.getPlatfrom());
			if(thirdkeyConfig==null){
				thirdkeyConfig = new SysThirdkeyConfigPull();
				thirdkeyConfig.setOrgNumber(investRecordPull.getPlatfrom());
				thirdkeyConfig = thirdkeyConfigPullService.selectOne(thirdkeyConfig);
				orgCache.put(investRecordPull.getPlatfrom(), thirdkeyConfig);
				if(thirdkeyConfig==null)continue;
			}
			ResultVO result = openApiHelper.pushInvestRecord(thirdkeyConfig, investRecordPull);
			resultMaps.get(result.getCode()).add(result);
		}
		List<ResultVO> fail =resultMaps.get(1);
		if(CollectionUtils.isNotEmpty(fail)){
			try{

				//更新推送失败记录状态
				investRecordPullService.updatePushInvestRecordStatus(fail, 2);
			}catch(Exception e){
			}
		}
		List<ResultVO> success =resultMaps.get(0);
		if(CollectionUtils.isNotEmpty(success)){
			try{
				//更新推送成功记录状态
				investRecordPullService.updatePushInvestRecordStatus(success,0);
			}catch(Exception e){
			}
		}
	}

	@Override
	public void pushRepaymentRecord() throws Throwable {
		Map<String, SysThirdkeyConfigPull> orgCache = Maps.newHashMapWithExpectedSize(64);
		//investRecordPullService.updateInvestRecordInStatus(new Integer{}, afterStatus, msg)
		List<CimProductRepaymentRecordPull> datas = repaymentRecordPullService.getRepaymentRecord();
		if(CollectionUtils.isEmpty(datas))return;
		
		/**
		 * 回款4的状态变成回款2
		 * 根据回款状态排序  回款成功放在最后
		 */
		Collections.sort(datas, new Comparator<CimProductRepaymentRecordPull>() {
			@Override
			public int compare(CimProductRepaymentRecordPull o1,CimProductRepaymentRecordPull o2) {
				return o1.getStatus().compareTo(o2.getStatus());
			}
		});
		
		repaymentRecordPullService.updateRepaymentRecordInStatus(new Integer[]{1,2}, 3,DateTime.now().toString("yyyy-MM-dd HH:mm:ss") + " 推送执行中");
		Map<Integer, List<ResultVO>> resultMaps = Maps.newLinkedHashMap();
		//推送失败记录
		resultMaps.put(1,Lists.<ResultVO>newLinkedList());
		//推送成功记录
		resultMaps.put(0,Lists.<ResultVO>newLinkedList());
		LOGGER.info("###############################################调用领会投资回款推送接口###############################################");
		for (CimProductRepaymentRecordPull repaymentRecordPull : datas) {
			String platfrom = StringUtils.split(repaymentRecordPull.getRepaymentId(),'.')[0];
			SysThirdkeyConfigPull thirdkeyConfig = orgCache.get(platfrom);
			if(thirdkeyConfig==null){
				thirdkeyConfig = new SysThirdkeyConfigPull();
				thirdkeyConfig.setOrgNumber(platfrom);
				thirdkeyConfig = thirdkeyConfigPullService.selectOne(thirdkeyConfig);
				orgCache.put(platfrom, thirdkeyConfig);
				if(thirdkeyConfig==null)continue;
			}
			ResultVO result = openApiHelper.pushRepaymentRecord(thirdkeyConfig, repaymentRecordPull);
			resultMaps.get(result.getCode()).add(result);
		}
		List<ResultVO> fail =resultMaps.get(1);
		if(CollectionUtils.isNotEmpty(fail)){
			try{
				//更新推送失败记录状态
				repaymentRecordPullService.updatePushRepaymentRecordStatus(fail, 2);
			}catch(Exception e){
			}
		}
		List<ResultVO> success =resultMaps.get(0);
		if(CollectionUtils.isNotEmpty(success)){
			try{
				//更新推送成功记录状态
				repaymentRecordPullService.updatePushRepaymentRecordStatus(success, 0);
			}catch(Exception e){
			}
		}
	}

}
