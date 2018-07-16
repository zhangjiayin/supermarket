package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.AcAccountBindMapper;
import com.linkwee.web.dao.AcBalanceRecordMapper;
import com.linkwee.web.dao.CimProductInvestRecordMapper;
import com.linkwee.web.dao.CimProductUnrecordInvestMapper;
import com.linkwee.web.dao.TCFeeMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.CimProductUnrecordInvest;
import com.linkwee.web.model.acc.AcAccountBind;
import com.linkwee.web.model.acc.AcBalanceRecord;
import com.linkwee.web.model.cim.CimProductDataTable;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.request.tc.AuditUnrecordInvestRequest;
import com.linkwee.web.request.tc.UnrecordInvestRequest;
import com.linkwee.web.response.tc.UnrecordInvestListResponse;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.ActSignRecordService;
import com.linkwee.web.service.CimProductUnrecordInvestService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.InvestRecordAware;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.web.service.WeiXinMsgService;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.helper.ThreadpoolService;


/**
 * 
 * @描述：CimProductUnrecordInvestService 服务实现类
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年09月09日 14:27:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimProductUnrecordInvestService")
public class CimProductUnrecordInvestServiceImpl extends GenericServiceImpl<CimProductUnrecordInvest, Long> implements CimProductUnrecordInvestService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductUnrecordInvestServiceImpl.class);
	
	@Resource
	private CimProductUnrecordInvestMapper unrecordInvestMapper;
	@Resource
	private TCFeeMapper feeMapper;
	
	@Resource
	private CrmInvestorService  investorService;
	
	@Resource
	private PushMessageHelper pushMessageHelper;

    @Autowired
    private ActSignRecordService signRecordService;

	@Resource
	private CimProductInvestRecordMapper investRecordMapper;
	
	@Resource
	private ConfigHelper configHelper;

    @Autowired
	private SysConfigService sysConfigService;
	
	private List<InvestRecordAware> investRecordAwares;
	
	@Autowired
	private SmMessageQueueService messageQueueService;
	
/*	@Autowired
	private CimOrginfoService orginfoService;*/
	
	@Resource(name="crmCfgLevelService")
	private InvestRecordAware levelService;
	
	@Resource(name="feeCalcService")
	private InvestRecordAware feeCalcService;
	
	
	@Resource(name="actRedpacketService")
	private InvestRecordAware redpacketService;
	
	@Resource
	private WeiXinMsgService weiXinMsgService;
	
	@Resource
	private CimProductUnrecordInvestService cimProductUnrecordInvestService;
	
	@Resource
	private AcAccountBindService accountBindService;
	
	@Resource
	private AcBalanceRecordMapper acBalanceRecordMapper;
	
	@Resource
	private AcAccountBindMapper accountbindMapper;
	
	@Resource
	private SysMsgService sysMsgService;
	
	@Resource
	private SmMessageQueueService smMessageQueueService;
	
	@PostConstruct
	private void init(){
		investRecordAwares = Lists.newArrayList(redpacketService,levelService,(InvestRecordAware)investorService,feeCalcService);
	}
	
	
	@Override
    public GenericDao<CimProductUnrecordInvest, Long> getDao() {
        return unrecordInvestMapper;
    }



	@Override
	public DataTableReturn getUnrecordInvestList(UnrecordInvestRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(req.getDraw()+1);
		Page<UnrecordInvestListResponse> page = new Page<UnrecordInvestListResponse>(req.getStart()/req.getLength()+1,req.getLength());
		String imgServerUrl = configHelper.getValue(2,"img_server_url");
		List<UnrecordInvestListResponse> unrecordInvestListResponseLists = unrecordInvestMapper.getUnrecordInvestList(req.getInvestorsUserName(),req.getInvestorsMobiel(),req.getStartTime(),req.getEndTime(),req.getStatus(),page);
		for(UnrecordInvestListResponse unrecordInvestListResponseTemp : unrecordInvestListResponseLists){
			if(StringUtils.isNotBlank(unrecordInvestListResponseTemp.getImg())){
				String images = "";
				String [] imgs = unrecordInvestListResponseTemp.getImg().split(",");
				for(int i = 0; i < imgs.length; i++){
					if(i == imgs.length - 1){
						images += (imgServerUrl + imgs[i]);
					}else{
						images += (imgServerUrl + imgs[i] + ",");
					}
				}
				unrecordInvestListResponseTemp.setImg(images);
			}
		}
		tableReturn.setData(unrecordInvestListResponseLists);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	
	private InvestRecordWrapper createInvestRecordWrapper(CimProductUnrecordInvest  req){
		InvestRecordWrapper investRecordWrapper = new InvestRecordWrapper();
		investRecordWrapper.setBizId(req.getInvestId());
		investRecordWrapper.setInvestId(req.getInvestId());
		investRecordWrapper.setUserId(req.getUserId());
		investRecordWrapper.setProductName(req.getProductName());
		investRecordWrapper.setFeeRatio(req.getFeeRate());
		investRecordWrapper.setRemark(req.getPlatfromName()+"-"+req.getProductName() +" 报单佣金");
		investRecordWrapper.setProductOrgId(req.getPlatfrom());
		investRecordWrapper.setProductDays(req.getProductDeadLineValue());
		investRecordWrapper.setInvestAmt(req.getInvestAmt());
		investRecordWrapper.setInvestTime(req.getInvestTime());
		investRecordWrapper.setPlatfromFirstInvest(true);
		investRecordWrapper.setFirstInvest(ObjectUtils.equals(investRecordMapper.queryUserInvestCount(req.getUserId()), 0));
		return investRecordWrapper;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public ResponseResult audit(AuditUnrecordInvestRequest req, String userName) throws Exception{
		 try{
			  LOGGER.debug("开始更新审核状态 req={},userName={}",req,userName);
			  boolean audit ;
			  switch (req.getStatus()) {
				case 1:
					audit = true;
					break;
				case 2:
					audit = false;		
					break;
				default:
					return new ResponseResult(false,"更新审核状态失败,请重试");
				}
			   CimProductUnrecordInvest updateUnrecordInvest= null;
			   CimProductUnrecordInvest unrecordInvest  = new CimProductUnrecordInvest();
			   unrecordInvest.setId(req.getId());
			   unrecordInvest = selectOne(unrecordInvest);
			   //审核通过,如果已经同意晒单则发放奖励金
			   if(audit){
				   updateUnrecordInvest = auditThrough(req, unrecordInvest, userName);
				   //如果已经同意晒单则发放奖励金
                   if(unrecordInvest.getShareStatus() == 1){
                       int bountyType = 4;
                       String bounty = sysConfigService.getValuesByKey("share_unrecord_invest_bounty");
                       signRecordService.sendBounty(unrecordInvest.getUserId(),1,new BigDecimal(bounty),bountyType);
                   }
			   }else{
				   updateUnrecordInvest = auditNotThrough(req, userName);
			   }
			   if(updateUnrecordInvest==null) return new ResponseResult(false, "更新审核状态失败,请重试");
			   boolean isUpdate = update(updateUnrecordInvest) > 0 ;
			   if(isUpdate){
				   unrecordInvest.setRemark(req.getRemark());
				   pushNoticeThread(unrecordInvest, audit, req.getRemark());
			   }
			   return isUpdate ? new ResponseResult(true, "更新审核状态成功"): new ResponseResult(false, "更新审核状态失败,请重试");
		   }catch(Exception e){
			   LOGGER.error("更新审核状态异常",e);
			   throw e;
		   }
	}
	
	/**
	 * 审核通过
	 * @param req
	 * @param unAuditrecord
	 * @param userName
	 * @return
	 */
	private CimProductUnrecordInvest auditThrough(AuditUnrecordInvestRequest req,CimProductUnrecordInvest unAuditrecord, String userName){
		CimProductUnrecordInvest unrecordInvest = new CimProductUnrecordInvest();
		unrecordInvest.setId(req.getId());
		unrecordInvest.setUserId(unAuditrecord.getUserId());
		unrecordInvest.setStatus(req.getStatus());
		unrecordInvest.setUpdateTime(new Date());
		unrecordInvest.setOperator(userName);
		unrecordInvest.setRemark(req.getRemark());
		unrecordInvest.setCheckTime(new Date());
		return unrecordInvest;
	}
	
	
	/**
	 * 审核不通过
	 * @param req
	 * @param userName
	 * @return
	 */
	private CimProductUnrecordInvest auditNotThrough(AuditUnrecordInvestRequest req, String userName){
		if (StringUtils.isBlank(req.getRemark())){
			LOGGER.debug("审核不通过审核失败原因不能为空，req={}",req);
			return null;
		}
		CimProductUnrecordInvest unrecordInvest = new CimProductUnrecordInvest();
		unrecordInvest.setId(req.getId());
		unrecordInvest.setStatus(req.getStatus());
		unrecordInvest.setRemark(req.getRemark());
		unrecordInvest.setUpdateTime(new Date());
		unrecordInvest.setOperator(userName);
		unrecordInvest.setCheckTime(new Date());
		return  unrecordInvest;
	}
	
	/**
	 * 发送消息
	 * @param
	 * @param audit
	 * @param reason
	 */
	 private void pushNoticeThread(final CimProductUnrecordInvest formInfo,final boolean audit,final String reason) {
	   	ThreadpoolService.execute(new Runnable() {
				@Override
				public void run() {
					try {
					 String msg = "";
					 String createTime = DateUtils.format(formInfo.getCreateTime(),DateUtils.FORMAT_LONG);
					 if(audit){//审核通过
						 msg = String.format(configHelper.getValue(SysConfigConstant.DECLARATIONFORM_PASS_NOTICE),createTime,formInfo.getFeeAmt().setScale(2, BigDecimal.ROUND_DOWN),formInfo.getProductName());
						 messageQueueService.sendSingleMessage(formInfo.getUserMobile(), AppTypeEnum.CHANNEL, MsgModuleEnum.UNRECORDINVESTSUCCESS,createTime,formInfo.getFeeAmt().setScale(2, BigDecimal.ROUND_DOWN));
					 }else { //审核不通过
						 msg = String.format(configHelper.getValue(SysConfigConstant.DECLARATIONFORM_FAIL_NOTICE),createTime,formInfo.getRemark());
						 messageQueueService.sendSingleMessage(formInfo.getUserMobile(), AppTypeEnum.CHANNEL, MsgModuleEnum.UNRECORDINVESTFAILURE,createTime);
					 }
					 pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, SmsTypeEnum.LCSDECLARATIONFORMAUDITNOTICE, formInfo.getCfplannerId(), "报单审核通知", msg, null, true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	 
	 /**
	  * 相关计算
	  * @param investRecordWrapper
	  */
	 private void dispatchRecords(InvestRecordWrapper investRecordWrapper ) throws Exception{
		try {
			for (InvestRecordAware investRecordAware : investRecordAwares) {
				investRecordAware.investRecordProcess(investRecordWrapper);
			}
		} catch (Exception e) {
			LOGGER.warn(" investRecordProcess()  param={} Exception={} ",
					new Object[] { investRecordWrapper, e });
			throw e;
		}
	 }
	 

		@Override
		public DataTableReturn selectByDatatables(DataTable dt) {
			DataTableReturn tableReturn = new DataTableReturn();
			tableReturn.setDraw(dt.getDraw()+1);
			Page<CimProductUnrecordInvest> page = new Page<CimProductUnrecordInvest>(dt.getStart()/dt.getLength()+1,dt.getLength());
			List<CimProductUnrecordInvest> list = this.unrecordInvestMapper.selectBySearchInfo(dt,page);
			tableReturn.setData(list);
			tableReturn.setRecordsFiltered(page.getTotalCount());
			tableReturn.setRecordsTotal(page.getTotalCount());
			return tableReturn;
		}
		
		@Override
		public void payById(String dataId,String userName) {
			CimProductUnrecordInvest in = cimProductUnrecordInvestService.selectById(Long.parseLong(dataId));
			if(in!=null&&in.getPayStatus()==2){
			return;
			}
			if(in!=null&&in.getPayStatus()!=null&&in.getPayStatus()!=2){
				AcAccountBind ac = accountBindService.selectAccountByUserId(in.getUserId());
				AcBalanceRecord re = new AcBalanceRecord();
				re.setBankCardId(ac.getBankCardId());
				re.setTransAmount(in.getFeeAmt()!=null?in.getFeeAmt():null);
				re.setMobile(in.getUserMobile());
				re.setCreateTime(new Date());
				re.setCreatePerson(userName);
				re.setOrderId(StringUtils.getUUID());
				re.setTransDate(new Date());
				re.setTransType(14);
				re.setUserId(in.getUserId());
				re.setUserType(1);//理财师
				String content = "您通过超级返投资【 "+in.getPlatfromName()+" "+NumberUtils.getFormat(in.getInvestAmt(), "0.00")+"元  "+in.getProductDeadLineValue()+"天标的产品】，获得猎财返现+"+NumberUtils.getFormat(in.getFeeAmt(), "0.00")+"元.";
				re.setRemark(content);
				re.setCreateType(0);
				re.setSerialNumber(StringUtils.getUUID());
				//插入交易明细
				acBalanceRecordMapper.insertSelective(re);
			    //添加发放金额
				accountbindMapper.acGrantProfit(in.getFeeAmt().toString(),ac.getUserId());
				CimProductUnrecordInvest update = new CimProductUnrecordInvest();
				update.setId(in.getId());
				update.setPayTime(new Date());
				update.setUpdateTime(new Date());
				update.setPayStatus(2);
				update.setUploadOperator(userName);
				cimProductUnrecordInvestService.update(update);
			}

		}


		@Override
		public void payAudit(List<String> listStr, String userName) {
			for(String tableId:listStr){
				payById(tableId,userName);
			}
		}


		@Override
		public void batchInsert(List<CimProductUnrecordInvest> insertList) {
			for(CimProductUnrecordInvest invest:insertList){
				unrecordInvestMapper.insertSelective(invest);
				//发放站内信和短信
				sendMessage(invest);
			}
		}


		private void sendMessage(final CimProductUnrecordInvest in) {
			ThreadpoolService.execute(new Runnable() {			
				@Override
				public void run() {
					String msgcontent ="尊敬的用户，您于"+DateUtils.format(in.getInvestTime(),DateUtils.FORMAT_LONG)+"投资"+in.getPlatfromName()+"平台"+in.getProductDeadLineValue()+
							"天标的"+NumberUtils.getFormat(in.getInvestAmt(), "0.00")+"元，获得猎财返现金额"+NumberUtils.getFormat(in.getFeeAmt(), "0.00")+"元，将在3个工作日内发放至猎财余额。";
					SysMsg msg = new SysMsg();
					msg.setContent(msgcontent);
					msg.setStatus(0);// 发布
					msg.setUserNumber(in.getUserId());
					msg.setReadStatus(0);// 未读
					msg.setAppType(1);
					msg.setTypeName("投资返现发放");
					sysMsgService.addMsg(msg);
		
					try {
						messageQueueService.sendSingleMessage(in.getUserMobile(), AppTypeEnum.CHANNEL, MsgModuleEnum.PAYSUCCESS,DateUtils.format(in.getInvestTime(),DateUtils.FORMAT_LONG)
								,in.getPlatfromName(),in.getProductDeadLineValue(),NumberUtils.getFormat(in.getInvestAmt(), "0.00"),NumberUtils.getFormat(in.getFeeAmt(), "0.00"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}


		@Override
		public DataTableReturn selectByDatatablesLogs(CimProductDataTable dt) {
			DataTableReturn tableReturn = new DataTableReturn();
			tableReturn.setDraw(dt.getDraw()+1);
			Page<CimProductUnrecordInvest> page = new Page<CimProductUnrecordInvest>(dt.getStart()/dt.getLength()+1,dt.getLength());
			List<CimProductUnrecordInvest> list = this.unrecordInvestMapper.selectBySearchInfoLogs(dt,page);
			tableReturn.setData(list);
			tableReturn.setRecordsFiltered(page.getTotalCount());
			tableReturn.setRecordsTotal(page.getTotalCount());
			return tableReturn;
		}


		@Override
		public void sendMessage() {
			List<CimProductUnrecordInvest> list = this.unrecordInvestMapper.selectByHuiKuan();
			for(CimProductUnrecordInvest invest:list){
				 //发短信
				try {
					smMessageQueueService.sendSingleMessage(invest.getUserMobile(), AppTypeEnum.CHANNEL, MsgModuleEnum.HUIKUAN,invest.getPlatfromName(),invest.getProductDeadLineValue(),NumberUtils.getFormat(invest.getInvestAmt(), "0.00"));
				} catch (Exception e) {
					e.printStackTrace();
				}  
			}
		}
}
