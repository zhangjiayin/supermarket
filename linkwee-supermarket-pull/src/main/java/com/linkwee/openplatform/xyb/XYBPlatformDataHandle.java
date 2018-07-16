package com.linkwee.openplatform.xyb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.linkwee.core.util.DateUtils;
import com.linkwee.openplatform.xyb.helper.HttpRequestClientXyb;
import com.linkwee.openplatform.xyb.vo.XYBInvestRecordVO;
import com.linkwee.openplatform.xyb.vo.XYBRepaymentRecordVO;
import com.linkwee.web.enums.OrgEnum;
import com.linkwee.web.model.CimProductInvestRecordPull;
import com.linkwee.web.model.CimProductRepaymentRecordPull;
import com.linkwee.web.model.SysThirdkeyConfigPull;
import com.linkwee.web.pull.AbstractPlatformDataHandle;
import com.linkwee.web.service.CimProductInvestRecordPullService;
import com.linkwee.web.service.SysThirdkeyConfigPullService;
import com.openpltsdk.xyb.entity.Index;
import com.openpltsdk.xyb.entity.QueryReq;
import com.openpltsdk.xyb.entity.ServiceData;
import com.openpltsdk.xyb.entity.ServiceEnums;
import com.openpltsdk.xyb.entity.TimeRange;

/**
 * 信用宝数据拉取
 * @author ch
 *
 */
@Component("xybPlatformDataPull")
@SuppressWarnings("serial")
public class XYBPlatformDataHandle extends AbstractPlatformDataHandle<XYBInvestRecordVO,XYBRepaymentRecordVO>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(XYBPlatformDataHandle.class);
	
	@Resource
	private SysThirdkeyConfigPullService sysThirdkeyConfigPullService;
	
	@Resource
	private HttpRequestClientXyb httpRequestClientXyb;
	
	@Resource
	private CimProductInvestRecordPullService investRecordPullService;
	
	private OrgEnum orgEnum = OrgEnum.OPEN_XINYONGBAO_WEB;
	
	@Override
	protected List<XYBInvestRecordVO> getInvestRecord(String orgNumber,String startTime,String endTime) throws Throwable  {
		Gson gson =  new GsonBuilder()  
		  .setDateFormat("yyyy-MM-dd HH:mm:ss")  
		  .create(); 
		QueryReq qr = new QueryReq();
		TimeRange timeRange = new TimeRange();
		
		timeRange.setStartTime(StringUtils.isNotBlank(startTime)?startTime:DateUtils.format(DateUtils.subDay(new Date(), 1)));
		timeRange.setEndTime(StringUtils.isNotBlank(endTime)?endTime:DateUtils.getNow());
		qr.setDateRange(timeRange);
		Index index = new Index();
		qr.setIndex(index);
		
		/**
		 * 发起http请求
		 */
		List<XYBInvestRecordVO> xYBInvestRecordVOList = new ArrayList<XYBInvestRecordVO>();
		
		SysThirdkeyConfigPull sysThirdkeyConfigPull = new SysThirdkeyConfigPull();
		sysThirdkeyConfigPull.setCooperationStandard(1);//没有遵循文档合作规范
		if(StringUtils.isBlank(orgNumber) || orgNumber.endsWith(orgEnum.getOrgNumber())){
			sysThirdkeyConfigPull.setOrgNumber(orgEnum.getOrgNumber());
		} else {			
			return xYBInvestRecordVOList;
		}
		sysThirdkeyConfigPull.setOrgStatus("y");
		sysThirdkeyConfigPull.setInvestStatus("y");
		sysThirdkeyConfigPull = sysThirdkeyConfigPullService.selectOne(sysThirdkeyConfigPull);
		
		if(sysThirdkeyConfigPull != null){		
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【orgNumber={}】拉取投资记录>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",orgEnum.getOrgNumber());
			ServiceData requestServiceData = new ServiceData(ServiceEnums.queryInvests.getServiceName(), gson.toJsonTree(qr));
			ServiceData returnServiceData = httpRequestClientXyb.invokePostHttp(sysThirdkeyConfigPull.getOrgPullInvestRecordUrl(),requestServiceData,null);
			xYBInvestRecordVOList = gson.fromJson(returnServiceData.getBody(), new TypeToken<List<XYBInvestRecordVO>>(){}.getType());
			LOGGER.info("==================【orgNumber={}】投资记录请求结果返回List================== data={}",orgEnum.getOrgNumber(),xYBInvestRecordVOList);
		}
		return xYBInvestRecordVOList;
	}


	@Override
	protected List<XYBRepaymentRecordVO> getRepaymentRecord(String orgNumber,String startTime,String endTime)throws Throwable {
		Gson gson =  new GsonBuilder()  
		  .setDateFormat("yyyy-MM-dd HH:mm:ss")  
		  .create(); 
		QueryReq qr = new QueryReq();
		TimeRange timeRange = new TimeRange();		
		timeRange.setStartTime(StringUtils.isNotBlank(startTime)?startTime:DateUtils.format(DateUtils.subDay(new Date(), 1)));
		timeRange.setEndTime(StringUtils.isNotBlank(endTime)?endTime:DateUtils.getNow());
		
		qr.setDateRange(timeRange);
		Index index = new Index();
		qr.setIndex(index);
		ServiceData requestServiceData = new ServiceData(ServiceEnums.queryRepays.getServiceName(), gson.toJsonTree(qr));
		
		/**
		 * 发起http请求
		 */
		List<XYBRepaymentRecordVO> xYBRepaymentRecordVOList = new ArrayList<XYBRepaymentRecordVO>();
		SysThirdkeyConfigPull sysThirdkeyConfigPull = new SysThirdkeyConfigPull();
		if(StringUtils.isBlank(orgNumber) || orgNumber.endsWith(orgEnum.getOrgNumber())){
			sysThirdkeyConfigPull.setOrgNumber(orgEnum.getOrgNumber());
		} else {			
			return xYBRepaymentRecordVOList;
		}
		sysThirdkeyConfigPull.setOrgStatus("y");
		sysThirdkeyConfigPull.setRepaymentStatus("y");
		sysThirdkeyConfigPull = sysThirdkeyConfigPullService.selectOne(sysThirdkeyConfigPull);
		
		if(sysThirdkeyConfigPull != null){
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【orgNumber={}】拉取回款记录>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",orgEnum.getOrgNumber());
			ServiceData returnServiceData = httpRequestClientXyb.invokePostHttp(sysThirdkeyConfigPull.getOrgPullRepaymentRecordUrl(),requestServiceData,null);
			xYBRepaymentRecordVOList = gson.fromJson(returnServiceData.getBody(), new TypeToken<List<XYBRepaymentRecordVO>>(){}.getType());
			LOGGER.info("==================【orgNumber={}】回款记录请求结果返回List================== data={}",orgEnum.getOrgNumber(),JSONObject.toJSONString(xYBRepaymentRecordVOList));
		}
		return xYBRepaymentRecordVOList;
	}

	@Override
	public List<CimProductInvestRecordPull> investRecordAdapter(XYBInvestRecordVO xybInvestRecord) {
		List<CimProductInvestRecordPull> cimProductInvestRecordPullList = new ArrayList<CimProductInvestRecordPull>();
		
		CimProductInvestRecordPull investRecord =  new CimProductInvestRecordPull();
		investRecord.setPlatfrom(orgEnum.getOrgNumber());
		investRecord.setInvestId(Joiner.on('.').join(new Object[]{investRecord.getPlatfrom(),xybInvestRecord.getId()}));
		investRecord.setTxId(xybInvestRecord.getTxId());
		investRecord.setUserId(xybInvestRecord.getUserId());
		investRecord.setProductId(xybInvestRecord.getBid());
		investRecord.setInvestAmt(new BigDecimal(xybInvestRecord.getAmount()));
		investRecord.setProfit(new BigDecimal(xybInvestRecord.getIncome()));
		investRecord.setInvestStartTime(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(xybInvestRecord.getInvestAt()).toDate());
		investRecord.setInvestEndTime(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(xybInvestRecord.getInvestEndTime()).toDate());
		cimProductInvestRecordPullList.add(investRecord);
		return cimProductInvestRecordPullList;
	}


	@Override
	public List<CimProductRepaymentRecordPull> repaymentRecordAdapter(XYBRepaymentRecordVO xybRepaymentRecord) {
		try{
			List<CimProductRepaymentRecordPull> cimProductRepaymentRecordPullList = new ArrayList<CimProductRepaymentRecordPull>();
			
			CimProductRepaymentRecordPull repaymentRecord = new CimProductRepaymentRecordPull();
			repaymentRecord.setRepaymentId(Joiner.on('.').join(new Object[]{orgEnum.getOrgNumber(),xybRepaymentRecord.getId()}));
			repaymentRecord.setInvestId(Joiner.on('.').join(new Object[]{orgEnum.getOrgNumber(),xybRepaymentRecord.getInvestId()}));
			repaymentRecord.setUserId(xybRepaymentRecord.getUserId());
			repaymentRecord.setProductId(xybRepaymentRecord.getBid());
			repaymentRecord.setRepaymentTime(xybRepaymentRecord.getRepayAt());
			repaymentRecord.setRepaymentAmount(new BigDecimal(xybRepaymentRecord.getAmount()));
			repaymentRecord.setProfit(new BigDecimal(xybRepaymentRecord.getIncome()));
			BigDecimal stockInvestAmt = investRecordPullService.getStockInvestAmt(repaymentRecord.getInvestId());
			if(stockInvestAmt==null)return null;
			float amt =xybRepaymentRecord.getAmount();
			if(amt==0f ){
				repaymentRecord.setStatus(2);
			}
			else if(amt>0f ){
				int compare = stockInvestAmt.compareTo(repaymentRecord.getRepaymentAmount());
				repaymentRecord.setStatus(compare>0?4:3);
			}
			cimProductRepaymentRecordPullList.add(repaymentRecord);
			return cimProductRepaymentRecordPullList;
		}catch(Exception e){
			LOGGER.error("repaymentRecordAdapter exception xybRepaymentRecord={} ", xybRepaymentRecord,e);
		}
		return null;
	}

}
