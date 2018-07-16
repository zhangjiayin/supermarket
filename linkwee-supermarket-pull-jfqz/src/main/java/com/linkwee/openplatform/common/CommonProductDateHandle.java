package com.linkwee.openplatform.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.SuccessResponse;
import com.linkwee.openApi.enums.RequestTypeEnums;
import com.linkwee.openApi.response.TokenResponse;
import com.linkwee.openApi.utils.OpenApiCommonUtils;
import com.linkwee.web.model.CimProductAddPull;
import com.linkwee.web.model.JfqzProductAddPull;
import com.linkwee.web.model.SysThirdkeyConfigPull;
import com.linkwee.web.pull.AbstractProductDataHandle;
import com.linkwee.web.request.PullProductRequest;
import com.linkwee.web.response.ProductDataPullReturn;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysThirdkeyConfigPullService;
import com.linkwee.xoss.util.HttpClientUtil;

@Component
@SuppressWarnings("unchecked")
public class CommonProductDateHandle extends AbstractProductDataHandle{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonProductDateHandle.class);

	@Resource
	private SysThirdkeyConfigPullService sysThirdkeyConfigPullService;
	
	@Resource
	private SysConfigService sysConfigService;
	
	@Override
	public List<ProductDataPullReturn> getProductCurrentSale(String orgNumber,String startTime,String endTime) {
		
		List<ProductDataPullReturn> productDataPullReturnList = new ArrayList<ProductDataPullReturn>();
		
		//查询完全遵循文档合作规范合作机构
		SysThirdkeyConfigPull sysThirdkeyConfigPullN = new SysThirdkeyConfigPull();
		sysThirdkeyConfigPullN.setCooperationStandard(0);//完全遵循文档合作规范
		sysThirdkeyConfigPullN.setOrgNumber("OPEN_JIUFUQINGZHOU_WEB");
		sysThirdkeyConfigPullN.setOrgStatus("y");
		List<SysThirdkeyConfigPull> sysThirdkeyConfigPulls = sysThirdkeyConfigPullService.selectListByCondition(sysThirdkeyConfigPullN);
		for (SysThirdkeyConfigPull sysThirdkeyConfigPull : sysThirdkeyConfigPulls) {
			try {
				LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【orgNumber={}】拉取在售产品>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",sysThirdkeyConfigPull.getOrgNumber());
				ProductDataPullReturn productDataPullReturn = new ProductDataPullReturn();
				productDataPullReturn.setSysThirdkeyConfigPull(sysThirdkeyConfigPull);
				
				//发起请求并返回相应的结果
				PullProductRequest pullProductRequest = new PullProductRequest();
				/**
				 * 查询时间范围
				 * 默认空 查询全部在售
				 * 否则根据时间范围获取产品  无论是否在售
				 */
				if(StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)){
					pullProductRequest.setStartTime(startTime);
					pullProductRequest.setEndTime(endTime);
				}
				List<CimProductAddPull> cimProductAddPullList = getCimProductAddPullList(sysThirdkeyConfigPull, pullProductRequest);

				productDataPullReturn.setCimProductAddPullList(cimProductAddPullList);
				productDataPullReturnList.add(productDataPullReturn);
			} catch (Exception e) {
				LOGGER.error("拉取产品 当前在售异常,orgNumber={}",sysThirdkeyConfigPull.getOrgNumber(),e);
				continue;
			}
		}
		return productDataPullReturnList;
	}

	@Override
	public CimProductAddPull getProductById(String orgNumber,String thirdProductId) {
		CimProductAddPull cimProductAddPull =  null;
		try {
			//发起http请求
			LOGGER.info("+++++++++++++++++++++++++++++++++++++【orgNumber={},thirdProductId={}】根据产品id拉取产品+++++++++++++++++++++++++++++++++++++",orgNumber,thirdProductId);
			
			//查询配置
			SysThirdkeyConfigPull sysThirdkeyConfigPull = sysThirdkeyConfigPullService.selectSysThirdkeyConfigPullByOrgNumber(orgNumber);
			
			//请求参数
			PullProductRequest pullProductRequest = new PullProductRequest();
			pullProductRequest.setThirdProductId(thirdProductId);
			List<CimProductAddPull> cimProductAddPullList = getCimProductAddPullList(sysThirdkeyConfigPull, pullProductRequest);
			
			//返回结果转化成可用的产品对象
			if(CollectionUtils.isNotEmpty(cimProductAddPullList)){
				CimProductAddPull cimProductAddPullR = cimProductAddPullList.get(0);
				cimProductAddPullR.setOrgNumber(orgNumber);
				cimProductAddPullR.setCreator(orgNumber);
				return cimProductAddPullR;
			} else {
				return cimProductAddPull;
			}
		} catch (Exception e) {
			LOGGER.error("根据产品id拉取产品异常,orgNumber={},thirdProductId={}",orgNumber,thirdProductId);
			LOGGER.error("根据产品id拉取产品异常",e);
			return cimProductAddPull;
		}
	}

	/**
	 * 发起请求并返回相应的结果
	 * @param sysThirdkeyConfigPull
	 * @param pullProductRequest
	 * @return
	 */
	@SuppressWarnings("static-access")
	private List<CimProductAddPull> getCimProductAddPullList(SysThirdkeyConfigPull sysThirdkeyConfigPull,PullProductRequest pullProductRequest){
		List<CimProductAddPull> cimProductAddPullList = new ArrayList<CimProductAddPull>();
		//玖富轻舟获取token
		String getTokenUrl = sysConfigService.getValuesByKey("getTokenUrl");
		String appId = sysConfigService.getValuesByKey("appId");
		String appSecret = sysConfigService.getValuesByKey("appSecret");
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", appId);
		params.put("appSecret", appSecret);
		params.put("userId", sysThirdkeyConfigPull.getCreateUser());
		String res =null;
		try {
			res = HttpClientUtil.httpPost(getTokenUrl, params);
		} catch (Exception e) {
			LOGGER.error("获取产品列表，获取玖富轻舟token异常！");
		}
		JSONObject json = JSONObject.parseObject(res);
		TokenResponse ver = null;
 		try {
 			ver = json.toJavaObject(json, TokenResponse.class);
		} catch (Exception e) {
		}
		String token = ver.getData().getToken();
		String returnMessage = null;
		returnMessage = OpenApiCommonUtils.httpRequest(sysThirdkeyConfigPull,RequestTypeEnums.POST,sysThirdkeyConfigPull.getOrgPullProductUrl()+token,pullProductRequest);
		List<JfqzProductAddPull> jfqzProductAddPullList = new ArrayList<JfqzProductAddPull>();
		try {
			if(StringUtils.isNotBlank(returnMessage)){
				BaseResponse baseResponse = JSONObject.parseObject(returnMessage, BaseResponse.class);
				if(baseResponse != null && baseResponse.getCode() != null && baseResponse.getCode().equals("SUCCESS")){
					SuccessResponse<CimProductAddPull> succ = JSONObject.parseObject(returnMessage,new SuccessResponse<CimProductAddPull>().getClass());
					String data = JSONObject.toJSONString(succ.getData());
					if(!"".equals(data) && data != null){
						LOGGER.info("==================【orgNumber={}】产品请求结果返回List================== data={}",sysThirdkeyConfigPull.getOrgNumber(),data);
						JSONObject jSONObject = JSONObject.parseObject(returnMessage);
						jfqzProductAddPullList = JSONObject.parseArray(jSONObject.getString("data"), JfqzProductAddPull.class);
						for(JfqzProductAddPull jfqz:jfqzProductAddPullList){
							CimProductAddPull proAdd = new CimProductAddPull();
							proAdd.setOrgNumber("OPEN_JIUFUQINGZHOU_WEB");
							proAdd.setProductName(jfqz.getProductName());
							proAdd.setBuyMaxMoney(jfqz.getMaxInvest());//单笔最大投资金额（元）
							proAdd.setBuyMinMoney(jfqz.getMinInvest());//单笔最小投资金额（元）
							proAdd.setBuyTotalMoney(jfqz.getTotalAmount());//发售总金额
							proAdd.setStatus(jfqz.getSellStatus()==2?1:2);//确认是不是都是在售的产品
							proAdd.setThirdProductId(jfqz.getProductId()+"_"+jfqz.getIssuePeriod());
							proAdd.setIsCollect(1);//是否需要募集开始及截止时间(1=不需要|2=需要)
							proAdd.setSaleStartTime(jfqz.getUpLineTime());//产品上线时间
							proAdd.setSaleEndTime(jfqz.getDownLineTime());//产品下线时间
							proAdd.setCreateTime(new Date().toString());
							proAdd.setInterestWay(2);//起息方式(1=购买当日|2=购买次日
							proAdd.setIsFixedDeadline(1);//是否固定期限(1=固定期限|2=浮动期限)
							proAdd.setDeadLineMinValue(jfqz.getPeriod());//产品最小期限天数
							proAdd.setDeadLineMaxValue(jfqz.getPeriod());//产品最大期限天数
							proAdd.setDeadLineMinSelfDefined(jfqz.getPeriod()+"天");//产品最小期限天数 自定义显示
							proAdd.setDeadLineMaxSelfDefined(jfqz.getPeriod()+"天");//产品最大期限天数 自定义显示
							proAdd.setIsFlow(1);//是否浮动利率(1=固定利率|2=浮动利率)
							proAdd.setFlowMinRate(new BigDecimal(jfqz.getProfit()));//浮动最小利率
							proAdd.setFlowMaxRate(new BigDecimal(jfqz.getProfit()));//浮动最大利率
							proAdd.setRepaymentWay(1);//还本付息方式(1=一次性到期
							proAdd.setProductType(1);//产品类型(1=P2P
							proAdd.setIsRedemption(0);//是否可赎回可转让(0=不支持赎回和转让|1=可赎回|2=可转让|3=可赎回且可转让)
							proAdd.setBuyIncreaseMoney(new BigDecimal(100));//购买递增金额100的整数倍
							
							proAdd.setProductDesc("更新产品信息");//产品描述
							proAdd.setBuyedTotalPeople(0);//产品已投资人数
							proAdd.setBuyedTotalMoney(jfqz.getSoldAmount());//产品已售金额
							cimProductAddPullList.add(proAdd);
						}
					} else {
						LOGGER.error("产品请求结果返回为空  或者产品返回List格式不正确");
					} 
				} else {
					LOGGER.error("产品请求结果返回returnCode不等于0  returnMessage={}",returnMessage);
				}
			} else {
				LOGGER.error("拉取产品列表返回结果为空 orgNumber={} returnMessage={} ",sysThirdkeyConfigPull.getOrgNumber(),returnMessage);
			}		
		} catch (Exception e) {
			LOGGER.error("拉取产品列表异常,orgNumber={},returnMessage={}",sysThirdkeyConfigPull.getOrgNumber(),returnMessage);
			LOGGER.error("拉取产品列表异常",e);
		}
		return cimProductAddPullList;
	}
}
