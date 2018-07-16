package com.linkwee.openplatform.common;

import java.util.ArrayList;
import java.util.List;

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
import com.linkwee.openApi.utils.OpenApiCommonUtils;
import com.linkwee.openplatform.xiaoying.XiaoyingkeJiHelper;
import com.linkwee.web.model.CimProductAddPull;
import com.linkwee.web.model.SysThirdkeyConfigPull;
import com.linkwee.web.pull.AbstractProductDataHandle;
import com.linkwee.web.request.PullProductRequest;
import com.linkwee.web.response.ProductDataPullReturn;
import com.linkwee.web.service.SysThirdkeyConfigPullService;

@Component
@SuppressWarnings("unchecked")
public class CommonProductDateHandle extends AbstractProductDataHandle{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonProductDateHandle.class);

	@Resource
	private SysThirdkeyConfigPullService sysThirdkeyConfigPullService;
	@Resource
	private XiaoyingkeJiHelper xiaoyingkeJiHelper;
	
	@Override
	public List<ProductDataPullReturn> getProductCurrentSale(String orgNumber,String startTime,String endTime) {
		
		List<ProductDataPullReturn> productDataPullReturnList = new ArrayList<ProductDataPullReturn>();
		
		//查询完全遵循文档合作规范合作机构
		SysThirdkeyConfigPull sysThirdkeyConfigPullN = new SysThirdkeyConfigPull();
		sysThirdkeyConfigPullN.setCooperationStandard(0);//完全遵循文档合作规范
		if(StringUtils.isNotBlank(orgNumber)){
			sysThirdkeyConfigPullN.setOrgNumber(orgNumber);
		}		
		sysThirdkeyConfigPullN.setOrgStatus("y");
		sysThirdkeyConfigPullN.setProductStatus("y");
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
	private List<CimProductAddPull> getCimProductAddPullList(SysThirdkeyConfigPull sysThirdkeyConfigPull,PullProductRequest pullProductRequest){
		List<CimProductAddPull> cimProductAddPullList = new ArrayList<CimProductAddPull>();
		
		/**
		 * 发送请求
		 * 1：小赢科技使用小赢证书key方式校验   需单独处理
		 */
		String returnMessage = null;
		if("OPEN_XIAOYINGLICAI_WEB".equals(sysThirdkeyConfigPull.getOrgNumber())){
			returnMessage = xiaoyingkeJiHelper.getRequestResult(sysThirdkeyConfigPull.getOrgPullProductUrl(), pullProductRequest);
		} else {		
			returnMessage = OpenApiCommonUtils.httpRequest(sysThirdkeyConfigPull,RequestTypeEnums.POST,sysThirdkeyConfigPull.getOrgPullProductUrl(),pullProductRequest);
		}
		try {
			if(StringUtils.isNotBlank(returnMessage)){
				BaseResponse baseResponse = JSONObject.parseObject(returnMessage, BaseResponse.class);
				if(baseResponse != null && baseResponse.getCode() != null && baseResponse.getCode().equals("0")){
					SuccessResponse<CimProductAddPull> succ = JSONObject.parseObject(returnMessage,new SuccessResponse<CimProductAddPull>().getClass());
					String data = JSONObject.toJSONString(succ.getData());
					if(!"".equals(data) && data.indexOf("datas") != -1){
						LOGGER.info("==================【orgNumber={}】产品请求结果返回List================== data={}",sysThirdkeyConfigPull.getOrgNumber(),data);
						JSONObject jSONObject = JSONObject.parseObject(data);
						cimProductAddPullList = JSONObject.parseArray(jSONObject.getString("datas"), CimProductAddPull.class);
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
