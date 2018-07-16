package com.linkwee.openplatform.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.SuccessResponse;
import com.linkwee.core.util.DateUtils;
import com.linkwee.openApi.enums.RequestTypeEnums;
import com.linkwee.openApi.utils.OpenApiCommonUtils;
import com.linkwee.openplatform.common.vo.CommonInvestRecordVO;
import com.linkwee.openplatform.common.vo.CommonRepaymentRecordVO;
import com.linkwee.openplatform.xiaoying.XiaoyingkeJiHelper;
import com.linkwee.web.model.CimProductInvestRecordPull;
import com.linkwee.web.model.CimProductRepaymentRecordPull;
import com.linkwee.web.model.SysThirdkeyConfigPull;
import com.linkwee.web.pull.AbstractPlatformDataHandle;
import com.linkwee.web.request.PullInvestRecordRequest;
import com.linkwee.web.request.PullRepaymentRecordRequest;
import com.linkwee.web.response.InvestRecordPullReturn;
import com.linkwee.web.response.RepaymentRecordPullReturn;
import com.linkwee.web.service.CimProductInvestRecordPullService;
import com.linkwee.web.service.SysThirdkeyConfigPullService;

@Component
@SuppressWarnings("unchecked")
public class CommonPlatformDataHandle extends AbstractPlatformDataHandle<InvestRecordPullReturn,RepaymentRecordPullReturn>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonPlatformDataHandle.class);

	@Resource
	private SysThirdkeyConfigPullService sysThirdkeyConfigPullService;
	
	@Resource
	private CimProductInvestRecordPullService investRecordPullService;
	
	@Resource
	private XiaoyingkeJiHelper xiaoyingkeJiHelper;

	@Override
	public List<CimProductInvestRecordPull> investRecordAdapter(InvestRecordPullReturn investRecord) {
		
		String orgNumber = investRecord.getSysThirdkeyConfigPull().getOrgNumber();
		
		List<CimProductInvestRecordPull> cimProductInvestRecordPullList = new ArrayList<CimProductInvestRecordPull>();
		
		if(CollectionUtils.isNotEmpty(investRecord.getCommonInvestRecordVOList())){
			for(CommonInvestRecordVO commonInvestRecordVO: investRecord.getCommonInvestRecordVOList()){			
				CimProductInvestRecordPull cimProductInvestRecordPull =  new CimProductInvestRecordPull();
				BeanUtils.copyProperties(commonInvestRecordVO, cimProductInvestRecordPull);
				cimProductInvestRecordPull.setPlatfrom(orgNumber);
				cimProductInvestRecordPull.setInvestAmt(commonInvestRecordVO.getInvestAmount());
				cimProductInvestRecordPull.setInvestId(Joiner.on('.').join(new Object[]{orgNumber,commonInvestRecordVO.getInvestId()}));
				cimProductInvestRecordPull.setInvestStartTime(DateUtils.parse(commonInvestRecordVO.getInvestStartTime(), "yyyy-MM-dd HH:mm:ss"));
				cimProductInvestRecordPull.setInvestEndTime(DateUtils.parse(commonInvestRecordVO.getInvestEndTime(), "yyyy-MM-dd HH:mm:ss"));			
				cimProductInvestRecordPullList.add(cimProductInvestRecordPull);
			}
		}
		
		return cimProductInvestRecordPullList;
	}

	@Override
	public List<CimProductRepaymentRecordPull> repaymentRecordAdapter(RepaymentRecordPullReturn repaymentRecord) {
		
		String orgNumber = repaymentRecord.getSysThirdkeyConfigPull().getOrgNumber();
		
		List<CimProductRepaymentRecordPull> cimProductRepaymentRecordPullList = new ArrayList<CimProductRepaymentRecordPull>();
		
		if(CollectionUtils.isNotEmpty(repaymentRecord.getCommonRepaymentRecordVOList())){
			for(CommonRepaymentRecordVO commonRepaymentRecordVO: repaymentRecord.getCommonRepaymentRecordVOList()){			
				CimProductRepaymentRecordPull cimProductRepaymentRecordPull =  new CimProductRepaymentRecordPull();
				BeanUtils.copyProperties(commonRepaymentRecordVO, cimProductRepaymentRecordPull);
				cimProductRepaymentRecordPull.setRepaymentId(Joiner.on('.').join(new Object[]{orgNumber,commonRepaymentRecordVO.getRepaymentId()}));
				cimProductRepaymentRecordPull.setInvestId(Joiner.on('.').join(new Object[]{orgNumber,commonRepaymentRecordVO.getInvestId()}));
				cimProductRepaymentRecordPull.setRepaymentTime(DateUtils.parse(commonRepaymentRecordVO.getRepaymentTime(), "yyyy-MM-dd HH:mm:ss"));

				BigDecimal stockInvestAmt = investRecordPullService.getStockInvestAmt(cimProductRepaymentRecordPull.getInvestId());
				
				if (stockInvestAmt==null || stockInvestAmt.compareTo(BigDecimal.ZERO) <= 0){
					continue;
				}
				
				BigDecimal amt =commonRepaymentRecordVO.getRepaymentAmount();
				
				/**
				 * 小赢理财  回款状态优先  其他平台  根据锁定金额优先
				 */
				if(orgNumber.equals("OPEN_XIAOYINGLICAI_WEB")){
					if(commonRepaymentRecordVO.getStatus() == 3){
						cimProductRepaymentRecordPull.setStatus(3);
					} else if(commonRepaymentRecordVO.getStatus() == 4){
						cimProductRepaymentRecordPull.setStatus(2);
					} else {
						if(amt.compareTo(new BigDecimal(0)) == 0 ){
							cimProductRepaymentRecordPull.setStatus(2);
						}else if(amt.compareTo(new BigDecimal(0)) > 0 ){							
							int compare = stockInvestAmt.compareTo(cimProductRepaymentRecordPull.getRepaymentAmount());
							cimProductRepaymentRecordPull.setStatus(compare>0?2:3);
						}
					}			
				} else {
					if(amt.compareTo(new BigDecimal(0)) == 0 ){
						cimProductRepaymentRecordPull.setStatus(2);
					}else if(amt.compareTo(new BigDecimal(0)) > 0 ){
						int compare = stockInvestAmt.compareTo(cimProductRepaymentRecordPull.getRepaymentAmount());
						cimProductRepaymentRecordPull.setStatus(compare>0?4:3);
					}
				}
				
				cimProductRepaymentRecordPullList.add(cimProductRepaymentRecordPull);
			}
		}
		
		return cimProductRepaymentRecordPullList;
	}

	@Override
	protected List<InvestRecordPullReturn> getInvestRecord(String orgNumber,String startTime,String endTime) throws Throwable {
		List<InvestRecordPullReturn> investRecordPullReturnList = new ArrayList<InvestRecordPullReturn>();
		//查询完全遵循文档合作规范合作机构
		SysThirdkeyConfigPull sysThirdkeyConfigPullN = new SysThirdkeyConfigPull();
		if(StringUtils.isNotBlank(orgNumber)){
			sysThirdkeyConfigPullN.setOrgNumber(orgNumber);
		}
		sysThirdkeyConfigPullN.setCooperationStandard(0);//完全遵循文档合作规范
		sysThirdkeyConfigPullN.setOrgStatus("y");
		sysThirdkeyConfigPullN.setInvestStatus("y");
		List<SysThirdkeyConfigPull> sysThirdkeyConfigPulls = sysThirdkeyConfigPullService.selectListByCondition(sysThirdkeyConfigPullN);
		for (SysThirdkeyConfigPull sysThirdkeyConfigPull : sysThirdkeyConfigPulls) {
			String returnMessage = null;
			try {
				InvestRecordPullReturn investRecordPullReturn = new InvestRecordPullReturn();
				
				LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【orgNumber={}】拉取投资记录>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",sysThirdkeyConfigPull.getOrgNumber());
				List<CommonInvestRecordVO> commonInvestRecordVOList = new ArrayList<CommonInvestRecordVO>();
				PullInvestRecordRequest pullInvestRecordRequest = new PullInvestRecordRequest();
				/**
				 * 查询时间范围
				 * 默认为一天
				 */
				pullInvestRecordRequest.setStartTime(StringUtils.isNotBlank(startTime)?startTime:DateUtils.format(DateUtils.subDay(new Date(), 1)));
				pullInvestRecordRequest.setEndTime(StringUtils.isNotBlank(endTime)?endTime:DateUtils.getNow());
				
				/**
				 * 发送请求
				 * 1：小赢科技使用小赢证书key方式校验   需单独处理
				 */
				if("OPEN_XIAOYINGLICAI_WEB".equals(sysThirdkeyConfigPull.getOrgNumber())){
					returnMessage = xiaoyingkeJiHelper.getRequestResult(sysThirdkeyConfigPull.getOrgPullInvestRecordUrl(), pullInvestRecordRequest);
				} else {		
					returnMessage = OpenApiCommonUtils.httpRequest(sysThirdkeyConfigPull,RequestTypeEnums.POST,sysThirdkeyConfigPull.getOrgPullInvestRecordUrl(),pullInvestRecordRequest);//发送请求
				}

				if(StringUtils.isNotBlank(returnMessage)){			
					BaseResponse baseResponse = JSONObject.parseObject(returnMessage, BaseResponse.class);
					if(baseResponse != null && baseResponse.getCode().equals("0")){
						SuccessResponse<CommonInvestRecordVO> succ = JSONObject.parseObject(returnMessage,new SuccessResponse<CommonInvestRecordVO>().getClass());
						String data = JSONObject.toJSONString(succ.getData());
						if(!"".equals(data) && data.indexOf("datas") != -1){
							LOGGER.info("==================【orgNumber={}】投资记录请求结果返回List================== data={}",sysThirdkeyConfigPull.getOrgNumber(),data);
							JSONObject jSONObject = JSONObject.parseObject(data);
							commonInvestRecordVOList = JSONObject.parseArray(jSONObject.getString("datas"), CommonInvestRecordVO.class);
						}					
					} else {
						LOGGER.error("投资记录请求结果返回returnCode不等于0  returnMessage={}",returnMessage);
					}
					investRecordPullReturn.setSysThirdkeyConfigPull(sysThirdkeyConfigPull);
					
					//根据投资时间排序
					Collections.sort(commonInvestRecordVOList, new Comparator<CommonInvestRecordVO>() {
						@Override
						public int compare(CommonInvestRecordVO o1,CommonInvestRecordVO o2) {
							return o1.getInvestStartTime().compareTo(o2.getInvestStartTime());
						}
					});
					
					investRecordPullReturn.setCommonInvestRecordVOList(commonInvestRecordVOList);
					investRecordPullReturnList.add(investRecordPullReturn);
				} else {
					LOGGER.error("拉取投资记录返回结果为空 returnMessage={} orgNumber={}",returnMessage,sysThirdkeyConfigPull.getOrgNumber());
				}
			} catch (Exception e) {
				LOGGER.error("拉取投资记录异常,returnMessage={} orgNumber={}",returnMessage,sysThirdkeyConfigPull.getOrgNumber());
				LOGGER.error("拉取投资记录异常,orgNumber={}",sysThirdkeyConfigPull.getOrgNumber(),e);
				continue;
			}
		}
		return investRecordPullReturnList;
	}

	@Override
	protected List<RepaymentRecordPullReturn> getRepaymentRecord(String orgNumber,String startTime,String endTime)throws Throwable {
		List<RepaymentRecordPullReturn> repaymentRecordPullReturnList = new ArrayList<RepaymentRecordPullReturn>();
		//查询完全遵循文档合作规范合作机构
		SysThirdkeyConfigPull sysThirdkeyConfigPullN = new SysThirdkeyConfigPull();
		if(StringUtils.isNotBlank(orgNumber)){
			sysThirdkeyConfigPullN.setOrgNumber(orgNumber);
		}		
		sysThirdkeyConfigPullN.setCooperationStandard(0);//完全遵循文档合作规范
		sysThirdkeyConfigPullN.setOrgStatus("y");
		sysThirdkeyConfigPullN.setRepaymentStatus("y");
		List<SysThirdkeyConfigPull> sysThirdkeyConfigPulls = sysThirdkeyConfigPullService.selectListByCondition(sysThirdkeyConfigPullN);
		for (SysThirdkeyConfigPull sysThirdkeyConfigPull : sysThirdkeyConfigPulls) {
			try {
				RepaymentRecordPullReturn repaymentRecordPullReturn = new RepaymentRecordPullReturn();
				LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【orgNumber={}】拉取回款记录>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",sysThirdkeyConfigPull.getOrgNumber());
				List<CommonRepaymentRecordVO> commonRepaymentRecordVOList = new ArrayList<CommonRepaymentRecordVO>();
				PullRepaymentRecordRequest pullRepaymentRecordRequest = new PullRepaymentRecordRequest();
				//查询时间范围为一天
				pullRepaymentRecordRequest.setStartTime(StringUtils.isNotBlank(startTime)?startTime:DateUtils.format(DateUtils.subDay(new Date(), 1)));
				pullRepaymentRecordRequest.setEndTime(StringUtils.isNotBlank(endTime)?endTime:DateUtils.getNow());
				
				/**
				 * 发送请求
				 * 1：小赢科技使用小赢证书key方式校验   需单独处理
				 */
				String returnMessage = null;
				if("OPEN_XIAOYINGLICAI_WEB".equals(sysThirdkeyConfigPull.getOrgNumber())){
					returnMessage = xiaoyingkeJiHelper.getRequestResult(sysThirdkeyConfigPull.getOrgPullRepaymentRecordUrl(), pullRepaymentRecordRequest);
				} else {		
					returnMessage = OpenApiCommonUtils.httpRequest(sysThirdkeyConfigPull,RequestTypeEnums.POST,sysThirdkeyConfigPull.getOrgPullRepaymentRecordUrl(),pullRepaymentRecordRequest);//发送请求
				}
				
				if(StringUtils.isNotBlank(returnMessage)){				
					BaseResponse baseResponse = JSONObject.parseObject(returnMessage, BaseResponse.class);
					if(baseResponse.getCode().equals("0")){
						SuccessResponse<CommonRepaymentRecordVO> succ = JSONObject.parseObject(returnMessage,new SuccessResponse<CommonRepaymentRecordVO>().getClass());
						String data = JSONObject.toJSONString(succ.getData());
						if(!"".equals(data) && data.indexOf("datas") != -1){
							LOGGER.info("==================【orgNumber={}】回款记录请求结果返回List================== data={}",sysThirdkeyConfigPull.getOrgNumber(),data);
							JSONObject jSONObject = JSONObject.parseObject(data);
							commonRepaymentRecordVOList = JSONObject.parseArray(jSONObject.getString("datas"), CommonRepaymentRecordVO.class);
						}
					} else {
						LOGGER.error("回款记录请求结果返回returnCode不等于0  returnMessage={}",returnMessage);
					}
					repaymentRecordPullReturn.setSysThirdkeyConfigPull(sysThirdkeyConfigPull);
					repaymentRecordPullReturn.setCommonRepaymentRecordVOList(commonRepaymentRecordVOList);
					repaymentRecordPullReturnList.add(repaymentRecordPullReturn);
				} else {
					LOGGER.error("拉取回款记录返回结果为空 returnMessage={} orgNumber={}",returnMessage,sysThirdkeyConfigPull.getOrgNumber());
				}
			} catch (Exception e) {
				LOGGER.error("拉取回款记录异常,orgNumber={}",sysThirdkeyConfigPull.getOrgNumber(),e);
				continue;
			}
		}
		return repaymentRecordPullReturnList;
	}
}
