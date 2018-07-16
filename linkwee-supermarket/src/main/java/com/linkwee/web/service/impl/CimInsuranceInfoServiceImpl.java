package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.ApplicationUtils;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.web.dao.CimInsuranceInfoMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.CimInsuranceInfo;
import com.linkwee.web.model.CimInsuranceNotify;
import com.linkwee.web.model.CimInsurancePolicyInfo;
import com.linkwee.web.model.CimInsuranceProduct;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.service.ActCfpDoubleElevenActivityService;
import com.linkwee.web.service.CimInsuranceFeeService;
import com.linkwee.web.service.CimInsuranceInfoService;
import com.linkwee.web.service.CimInsuranceNotifyService;
import com.linkwee.web.service.CimInsurancePolicyInfoService;
import com.linkwee.web.service.CimInsuranceProductService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.helper.ThreadpoolService;
import com.linkwee.xoss.insurance.qixin.QixinCommonUtils;
import com.linkwee.xoss.util.business.ProfitCalculationUtils;


 /**
 * 
 * @描述：CimInsuranceInfoService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年09月12日 14:37:38
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimInsuranceInfoService")
public class CimInsuranceInfoServiceImpl extends GenericServiceImpl<CimInsuranceInfo, Long> implements CimInsuranceInfoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimInsuranceInfoServiceImpl.class);
	
	@Resource
	private CimInsuranceInfoMapper cimInsuranceInfoMapper;
	@Resource
	private CimInsuranceNotifyService cimInsuranceNotifyService;
	@Resource
	private CimInsurancePolicyInfoService cimInsurancePolicyInfoService;
	@Resource
	private ActCfpDoubleElevenActivityService actCfpDoubleElevenActivityService;
	@Resource
	private CimInsuranceInfoService cimInsuranceInfoService;
	@Resource
	private CimInsuranceProductService cimInsuranceProductService;
	@Resource
	private CimInsuranceFeeService cimInsuranceFeeService;
	@Resource
	private PushMessageHelper pushMessageHelper;
	@Resource
    private CrmCfplannerService crmCfplannerService;
	@Resource
	private CrmInvestorService crmInvestorService;
	
	@Override
    public GenericDao<CimInsuranceInfo, Long> getDao() {
        return cimInsuranceInfoMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimInsuranceInfo -- 排序和模糊查询 ");
		Page<CimInsuranceInfo> page = new Page<CimInsuranceInfo>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimInsuranceInfo> list = this.cimInsuranceInfoMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public CimInsuranceInfo selectByOrgNumber(String orgCode) {
		CimInsuranceInfo cimInsuranceInfo = new CimInsuranceInfo();
		cimInsuranceInfo.setOrgNumber(orgCode);
		cimInsuranceInfo = cimInsuranceInfoMapper.selectOneByCondition(cimInsuranceInfo);
		return cimInsuranceInfo;
	}

	@Override
	public Object notify(String jsonData) {
		
		try {
			JSONObject jsonObject = JSONObject.parseObject(jsonData);
			String orgNumber = "OPEN_QIXIN_WEB";//机构代码
			JSONObject innerDataJSONObject = jsonObject.getJSONObject("data");//data
				
			Integer notifyType = jsonObject.getIntValue("notifyType");//通知类型
			String insureNum = innerDataJSONObject.getString("insureNum");//投保单号
			String caseCode = innerDataJSONObject.getString("caseCode");//保险编号
			
			CimInsuranceProduct cimInsuranceProduct = new CimInsuranceProduct();
			cimInsuranceProduct.setOrgNumber(orgNumber);
			cimInsuranceProduct.setCaseCode(caseCode);
			cimInsuranceProduct = cimInsuranceProductService.selectOne(cimInsuranceProduct);
			if(cimInsuranceProduct == null){
				LOGGER.error("无效的保险平台产品:orgNumber={} ProductId={}",orgNumber,caseCode);
				return null;
			}
			
			CimInsuranceNotify  cimInsuranceNotify = cimInsuranceNotifyService.selectByOrgNumberInsureNum(orgNumber,insureNum);
			
			Date now = new Date();
			if(cimInsuranceNotify == null){
				cimInsuranceNotify = new CimInsuranceNotify();
				cimInsuranceInfoService.initCimInsuranceNotify(cimInsuranceNotify, orgNumber, notifyType, innerDataJSONObject, insureNum,now,null,cimInsuranceProduct);
				if(cimInsuranceNotify != null){
					cimInsuranceNotifyService.insert(cimInsuranceNotify);
				}
			} else {
				cimInsuranceInfoService.initCimInsuranceNotify(cimInsuranceNotify, orgNumber, notifyType, innerDataJSONObject, insureNum,null,now,cimInsuranceProduct);
				if(cimInsuranceNotify != null){					
					cimInsuranceNotifyService.update(cimInsuranceNotify);
				}
			}
		} catch (Exception e) {
			LOGGER.error("领会接收齐欣云服消息通知异常",e);
			return QixinCommonUtils.getNotifyFailed("领会接收齐欣云服消息通知异常");
		}
		LOGGER.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>领会接收齐欣云服消息成功<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		return QixinCommonUtils.getNotifySuccess();
	}
	
	/**
	 * 初始化保险通知表
	 * @param cimInsuranceNotify
	 * @return
	 */
	@Override
	public CimInsuranceNotify initCimInsuranceNotify(CimInsuranceNotify cimInsuranceNotify,String orgNumber,Integer notifyType,JSONObject innerDataJSONObject,String insureNum,Date creatTime,Date updateTime,CimInsuranceProduct cimInsuranceProduct){
		
		cimInsuranceNotify.setOrgNumber(orgNumber);
		if(cimInsuranceNotify.getNotifyType() == null || (notifyType > cimInsuranceNotify.getNotifyType() && cimInsuranceNotify.getNotifyType() != 4 && cimInsuranceNotify.getNotifyType() != 5) || notifyType == 5 || (notifyType == 4 && cimInsuranceNotify.getNotifyType() != 5)){			
			cimInsuranceNotify.setNotifyType(notifyType);
		}
		cimInsuranceNotify.setUserId(innerDataJSONObject.getString("partnerUniqueKey"));
		cimInsuranceNotify.setProductId(innerDataJSONObject.getString("caseCode"));
		cimInsuranceNotify.setInsureNum(insureNum);
		cimInsuranceNotify.setState(innerDataJSONObject.getBooleanValue("state"));
		cimInsuranceNotify.setFailMsg(innerDataJSONObject.getString("failMsg"));
		
		if(creatTime != null){
			cimInsuranceNotify.setCreatTime(creatTime);
		}
		if(updateTime != null){
			cimInsuranceNotify.setUpTime(updateTime);
		}
		if(StringUtils.isBlank(cimInsuranceNotify.getBillId())){
			cimInsuranceNotify.setBillId(ApplicationUtils.randomUUID(true,false));
		}
		if(cimInsuranceNotify.getProductFeeRate() == null){
			cimInsuranceNotify.setProductFeeRate(cimInsuranceProduct.getFeeRatio());//设置产品佣金率
		}
		if(notifyType == 2){//支付通知
			if(innerDataJSONObject.getBooleanValue("state")){//支付成功				
				cimInsuranceNotify.setPayTime(DateUtils.parse(innerDataJSONObject.getString("payTime")));
				cimInsuranceNotify.setPrice(innerDataJSONObject.getBigDecimal("price"));
				cimInsuranceNotify.setOnlinePaymentId(innerDataJSONObject.getString("onlinePaymentId"));
			}
		} else if(notifyType == 3){//出单通知
			String 	policysJsonStr = innerDataJSONObject.getString("policys");
			if(StringUtils.isNotEmpty(policysJsonStr)){
				List<CimInsurancePolicyInfo> cimInsurancePolicyInfoList = cimInsurancePolicyInfoService.selectListByInsureNum(insureNum);//判断该保单信息是否存在
				if(CollectionUtils.isEmpty(cimInsurancePolicyInfoList)){
					cimInsurancePolicyInfoList = JSONObject.parseArray(policysJsonStr, CimInsurancePolicyInfo.class);
					for (CimInsurancePolicyInfo cimInsurancePolicyInfo : cimInsurancePolicyInfoList) {
						cimInsurancePolicyInfo.setOrgNumber(orgNumber);
						cimInsurancePolicyInfo.setUserId(innerDataJSONObject.getString("partnerUniqueKey"));
						cimInsurancePolicyInfo.setProductId(innerDataJSONObject.getString("caseCode"));
						cimInsurancePolicyInfo.setInsureNum(insureNum);
						cimInsurancePolicyInfo.setCreatTime(new Date());
					}
					cimInsurancePolicyInfoService.insertList(cimInsurancePolicyInfoList);
					//消息推送（通知栏+个人消息中心）
					if(innerDataJSONObject.getString("partnerUniqueKey")!=null){
						sendMessage(innerDataJSONObject.getString("partnerUniqueKey"),cimInsuranceNotify.getPrice(),cimInsuranceProduct);
					}
				}
			}
		} else if(notifyType == 4 || notifyType == 5){//4-退保通知 5-退保重出通知
			cimInsuranceNotify.setNewInsureNum(innerDataJSONObject.getString("newInsureNum"));;
			cimInsuranceNotify.setCancelMsg(innerDataJSONObject.getString("cancelMsg"));;
			String 	cancelInsurantsJsonStr = innerDataJSONObject.getString("cancelInsurants");
			if(StringUtils.isNotEmpty(cancelInsurantsJsonStr)){
				List<String> cancelInsurantsNameList = JSONObject.parseArray(cancelInsurantsJsonStr, String.class);
				cimInsuranceNotify.setCancelInsurants(Joiner.on(",").join(cancelInsurantsNameList));
			}
		}
		
		if(notifyType == 3 || notifyType == 9){
			if(cimInsuranceNotify.getAuditStatus() == null || cimInsuranceNotify.getClearingStatus() == null 
					|| (cimInsuranceNotify.getAuditStatus() == 0 && cimInsuranceNotify.getAuditTime() == null) 
					|| (cimInsuranceNotify.getClearingStatus() == 0 && cimInsuranceNotify.getClearingTime() == null) ){			
				cimInsuranceNotify.setAuditStatus(0);//0-待审核  1-系统审核通过 2-系统审核失败 3-管理员审核通过 4-管理员审核失败
				cimInsuranceNotify.setAuditUser("systerm");
				cimInsuranceNotify.setClearingStatus(0);//0-待结算 1-结算成功 2-结算失败
				cimInsuranceNotify.setAuditTime(updateTime);
				cimInsuranceNotify.setClearingTime(updateTime);
				cimInsuranceNotify.setRemark(DateUtils.format(updateTime)+"保险出单成功,初始化审核状态和结算状态");
				
				try {					
					cimInsurancePolicyInfoService.handleInsuranceNotifyProcess(cimInsuranceNotify);
				} catch (Exception e) {
					LOGGER.error("计算保险订单佣金异常   不影响保险订单通知  cimInsuranceNotify ={} ",JSONObject.toJSONString(cimInsuranceNotify),e);
				}
			}
		} else if(notifyType == 4){//退保通知
			if(cimInsuranceNotify.getAuditStatus() == 0 && cimInsuranceNotify.getClearingStatus() == 0){//只有同时是待审核和待结算的状态  才可以进行系统审核失败的操作
				cimInsuranceNotify.setAuditStatus(2);//0-待审核  1-系统审核通过 2-系统审核失败 3-管理员审核通过 4-管理员审核失败
				cimInsuranceNotify.setAuditUser("systerm");
				cimInsuranceNotify.setRemark(DateUtils.format(updateTime)+"接收退保通知,系统审核失败");
				cimInsuranceNotify.setAuditTime(updateTime);
				cimInsuranceNotify.setClearingTime(updateTime);
				cimInsuranceNotify.setClearingStatus(2);//0-待计算 1-计算成功 2-计算失败
				
				try {					
					cimInsurancePolicyInfoService.handleInsuranceNotifyProcess(cimInsuranceNotify);
				} catch (Exception e) {
					LOGGER.error("计算保险订单佣金异常   不影响保险订单通知  cimInsuranceNotify ={} ",JSONObject.toJSONString(cimInsuranceNotify),e);
				}
			}
		}
		return cimInsuranceNotify;
	}

	private void sendMessage(final String userId, final BigDecimal payprice,final CimInsuranceProduct product) {
		ThreadpoolService.execute(new Runnable() {
			@Override
			public void run() {
				//推送给购买用户
				String myContent = "您已成功购买【%s】的保险产品,详情可前往我的-保险查看";
				pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, SmsTypeEnum.INSURANCE_PURCHASE,userId, "晋级通知",  String.format(myContent, product.getProductName()),  null, true);
				
				CrmInvestor inv = crmInvestorService.queryInvestorByUserId(userId);
				String cfpContent = "恭喜您，您的团队成员(%s)已经成功购买金额%s元的保险产品《%s》,佣金率%s,需要待保险犹豫期结束后才会计算佣金";
			    String mobile = inv.getMobile().substring(0, 3)+"****"+inv.getMobile().substring(inv.getMobile().length()-4, inv.getMobile().length());
			    BigDecimal oneHundred = new BigDecimal("100");
			    String price = NumberUtils.getFormat(payprice.divide(oneHundred), "0.00");
				String content = String.format(cfpContent, inv.getUserName()+mobile,price,product.getProductName(),product.getFeeRatio()+"%");
		        
				CrmCfplanner cfp = crmCfplannerService.queryCfplannerByUserId(userId);
				CrmCfplanner cfp1 = null;//上一级
				CrmCfplanner cfp2 = null;//上两级
				CrmCfplanner cfp3 = null;//上三级
				if(cfp.getParentId()!=null){
					cfp1 = crmCfplannerService.queryCfplannerByUserId(cfp.getParentId());
				}
				if(cfp1!=null&&cfp1.getParentId()!=null){
					cfp2 = crmCfplannerService.queryCfplannerByUserId(cfp1.getParentId());
				}
				if(cfp2!=null&&cfp2.getParentId()!=null){
					cfp3 = crmCfplannerService.queryCfplannerByUserId(cfp2.getParentId());
				}
				//发给上三级
				if(ProfitCalculationUtils.paySM3(cfp,cfp1,cfp2,cfp3)){
					String cfp3Content = "恭喜您，您的团队成员(%s)的二级推荐理财师购买金额%s元的保险产品《%s》,佣金率%s,需要待保险犹豫期结束后才会计算佣金";
				    String cfp2mobile = cfp2.getMobile().substring(0, 3)+"****"+cfp2.getMobile().substring(cfp2.getMobile().length()-4, cfp2.getMobile().length());
					String cfp3content = String.format(cfp3Content, cfp2.getUserName()+cfp2mobile,price,product.getProductName(),product.getFeeRatio()+"%");
					pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, SmsTypeEnum.INSURANCE_PURCHASE,cfp3.getUserId(), "出单通知",  cfp3content,  null, true);
				}
				//发给上二级
				if(ProfitCalculationUtils.paySM2(cfp,cfp1,cfp2,cfp3)){
					String cfp2Content = "恭喜您，您的团队成员(%s)的直接推荐理财师购买金额%s元的保险产品《%s》,佣金率%s,需要待保险犹豫期结束后才会计算佣金。";
				    String cfp1mobile = cfp1.getMobile().substring(0, 3)+"****"+cfp1.getMobile().substring(cfp1.getMobile().length()-4, cfp1.getMobile().length());
					String cfp2content = String.format(cfp2Content, cfp1.getUserName()+cfp1mobile,price,product.getProductName(),product.getFeeRatio()+"%");
					pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, SmsTypeEnum.INSURANCE_PURCHASE,cfp2.getUserId(), "出单通知",  cfp2content,  null, true);
				}
				//发给上一级
				if(cfp1!=null){
					pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, SmsTypeEnum.INSURANCE_PURCHASE,cfp1.getUserId(), "出单通知",  content,  null, true);
				}
			 }
			});
	}

}
