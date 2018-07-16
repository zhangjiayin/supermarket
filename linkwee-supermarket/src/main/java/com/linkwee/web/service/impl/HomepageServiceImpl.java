package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.linkwee.api.response.CradListResponse;
import com.linkwee.api.response.HomePageInvestResponse;
import com.linkwee.core.base.api.ErrorResponse;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.CimOrginfoMapper;
import com.linkwee.web.dao.CimProductInvestRecordMapper;
import com.linkwee.web.model.acc.AcAccountBind;
import com.linkwee.web.service.*;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linkwee.api.response.tc.CfpOrderResponse;
import com.linkwee.api.response.tc.HotInvestResponse;
import com.linkwee.web.model.SysHomepageCommission;

@Service("homepageService")
public class HomepageServiceImpl implements HomepageService {
	
	@Resource
	private SysHomepageCommissionService sysHomepageCommissionService;
	@Resource
    private CimProductInvestRecordService investRecordService;
	@Autowired
	private CimProductInvestRecordMapper investRecordMapper;
	@Resource
	private CimOrginfoMapper cimOrginfoMapper;
	@Resource
	private SysGrayReleaseService sysGrayReleaseService; //灰度服务
	@Resource
	private AcAccountBindService accountbindService;

	@Override
	@Cacheable(value="sysInfo400",key="'sysInfo400'")
	public Map<String, Object> querySysCfpHomepageInfo() {
		
		Map<String,Object> resp = new HashMap<String,Object>();
		SysHomepageCommission sysHomepageCommission = sysHomepageCommissionService.selectNewest();
		List<CfpOrderResponse> orderList = investRecordService.selectNewestTop200();
		List<HotInvestResponse> hotInvestList = investRecordService.queryHotInvestList();
		resp.put("newcomerTaskStatus", 0);		
		resp.put("commissionAmount", sysHomepageCommission.getAmount().toString());
		resp.put("orderList", orderList);
		resp.put("hotInvestList", hotInvestList);
		return resp;
	}

    @Override
    public List<CradListResponse> cfpCardList(AppRequestHead head) {
		List<CradListResponse> resultList = new ArrayList<CradListResponse>();
		/*String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if(!StringUtils.isBlank(userId) && !userId.equals("undefined")){
			int investCount = investRecordMapper.queryUserInvestCount(userId);
			if(investCount > 0){
				//已投资(加入对应的图片和链接) TODO
			}
		}*/
		CradListResponse aboutLieCai = new CradListResponse();
		aboutLieCai.setImgUrl("4bf667209bb3261e2f1a93355d51b0da");
		aboutLieCai.setLinkUrl("https://liecai.toobei.com/pages/richText/detail.html?type=2&id=193");
		resultList.add(aboutLieCai);
		return resultList;
    }

	@Override
	public Map<String, Object> cfpInvestStatistic(AppRequestHead head) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("investStatus",0);
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		if(!StringUtils.isBlank(userId) && !userId.equals("undefined")){
			int investCount = investRecordMapper.queryUserInvestCount(userId);
			if(investCount > 0){
				resultMap.put("investStatus",1);
			}
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			String lastMonth = new SimpleDateFormat("yyyy-MM").format(c.getTime());
			String monthProfit = NumberUtils.getFormat(investRecordService.queryMonthIncome(lastMonth,userId), "0.00");

			AcAccountBind bind = accountbindService.selectAccountByUserId(userId);
			String amount = bind!=null?NumberUtils.getFormat(Double.parseDouble(bind.getTotalAmount()), "0.00"):"0.00";//余额
			Boolean isGrayUser = sysGrayReleaseService.ifHaveGrayPermission(userId, "0,2");
			Map<String, String> investMap = cimOrginfoMapper.investStatistics(userId,isGrayUser);
			BigDecimal totalAsset = new BigDecimal(investMap.get("investAmt")).add(new BigDecimal(investMap.get("investingProfit"))).add(new BigDecimal(amount));
			resultMap.put("totalAsset",totalAsset.setScale(2, BigDecimal.ROUND_DOWN).toString());
			int investingOrgCount = investRecordService.investingOrgCount(userId,isGrayUser);
			resultMap.put("investingOrgCount",investingOrgCount);
			resultMap.put("monthProfit",monthProfit);
		}

		return resultMap;
	}

	@Override
	public List<HomePageInvestResponse> homepageInvestList() {
		return investRecordService.homepageInvestList();
	}

}
