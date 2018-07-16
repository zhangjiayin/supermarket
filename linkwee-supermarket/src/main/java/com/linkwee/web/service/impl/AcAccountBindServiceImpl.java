package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.api.controller.acc.AcAccountBindController;
import com.linkwee.api.request.acc.AddBankCardRequest;
import com.linkwee.api.request.acc.ResetPayPwdRequest;
import com.linkwee.api.request.acc.TwoPwdRequest;
import com.linkwee.api.request.acc.UserWithdrawRequest;
import com.linkwee.api.request.crm.WeiXinMsgRequest;
import com.linkwee.api.response.acc.AcAccountTypeReponse;
import com.linkwee.api.response.acc.AcBankCodeResponse;
import com.linkwee.api.response.acc.AcBankImageResponse;
import com.linkwee.api.response.acc.CityInfoResponse;
import com.linkwee.api.response.acc.ProvinceInfoResponse;
import com.linkwee.api.response.acc.WithdrawBankCardResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.openapi.request.OmsAccountBindRequest;
import com.linkwee.openapi.request.OmsAccountExistRequest;
import com.linkwee.openapi.request.OmsExistAccountRequest;
import com.linkwee.openapi.response.AccountExistResponse;
import com.linkwee.openapi.response.OmsAccountBindResponse;
import com.linkwee.web.dao.AcAccountBindMapper;
import com.linkwee.web.dao.AcAccountTypeMapper;
import com.linkwee.web.dao.AcBalanceRecordMapper;
import com.linkwee.web.dao.AcBankCodeMappingMapper;
import com.linkwee.web.dao.AcCityListMapper;
import com.linkwee.web.dao.AcHolidayDataMapper;
import com.linkwee.web.dao.AcProvinceListMapper;
import com.linkwee.web.dao.AcWithdrawApplyMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.model.CimOrgInfoToOur;
import com.linkwee.web.model.CrmInvestorOrgInfoToOur;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.acc.AcAccountBind;
import com.linkwee.web.model.acc.AcAccountDeduct;
import com.linkwee.web.model.acc.AcAccountRecharge;
import com.linkwee.web.model.acc.AcBalanceRecord;
import com.linkwee.web.model.acc.AcBankCardInfo;
import com.linkwee.web.model.acc.AcCityList;
import com.linkwee.web.model.acc.AcHolidayData;
import com.linkwee.web.model.acc.AcWithdrawApply;
import com.linkwee.web.model.acc.AcWithdrawTimes;
import com.linkwee.web.response.CommonTCSResult;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.AcBankCardInfoService;
import com.linkwee.web.service.CimOrgInfoToOurService;
import com.linkwee.web.service.CrmInvestorOrgInfoToOurService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.EasemobService;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.WeiXinMsgService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.OpenRequestHead;
import com.linkwee.xoss.constant.TimeSetConstants;
import com.linkwee.xoss.helper.DateUtils;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.helper.MessageHelper;
import com.linkwee.xoss.helper.ThreadpoolService;
import com.linkwee.xoss.util.AcAccountUtils;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.MD5;
import com.linkwee.xoss.util.OpenResponseUtil;
import com.linkwee.xoss.util.VerifyCodeUtils;

import redis.clients.jedis.JedisCluster;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月12日 19:10:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("accountbindService")
public class AcAccountBindServiceImpl extends GenericServiceImpl<AcAccountBind, Long> implements AcAccountBindService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AcAccountBindServiceImpl.class);
	//跟机构约定的手续费
	private static final Integer FEE = 1;
	
	@Resource
	private AcAccountBindMapper accountbindMapper;
	@Resource
	private AcCityListMapper acCityListMapper;
	@Resource
	private AcProvinceListMapper acProvinceListMapper;
	@Resource
	private AcBankCodeMappingMapper acBankCodeMappingMapper;
	@Resource
	private AcWithdrawApplyMapper acWithdrawApplyMapper;
	@Resource
	private AcAccountTypeMapper acAccountTypeMapper;
	@Resource
	private MessageHelper messageHelper;
	@Resource
	private  AcHolidayDataMapper acHolidayDataMapper;
	@Resource
	private AcBankCardInfoService acBankCardInfoService;
	@Resource
	private AcBalanceRecordMapper acBalanceRecordMapper;
	@Resource
    private CrmUserInfoService crmUserInfoService;
	@Resource
	private WeiXinMsgService weiXinMsgService;
	@Resource
	private CrmInvestorService crmInvestorService;
	@Resource
	private EasemobService easemobService;
	@Resource
	private CrmInvestorOrgInfoToOurService crmInvestorOrgInfoToOurService;
	@Resource
	private CimOrgInfoToOurService cimOrgInfoToOurService;
	@Resource
	private SmMessageQueueService smMessageQueueService;
	@Resource
	private JedisCluster redisManager;
	
	@Override
    public GenericDao<AcAccountBind, Long> getDao() {
        return accountbindMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- Bind -- 排序和模糊查询 ");
		Page<AcAccountBind> page = new Page<AcAccountBind>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<AcAccountBind> list = this.accountbindMapper.selectBySearchInfo(dt,page);
		//对银行预留手机号码、银行卡号、身份证进行加密
		for(AcAccountBind ac: list){
			ac.setReserveMobile(encrypTion(ac.getReserveMobile()));
			ac.setBankCard(encrypTion(ac.getBankCard()));
			ac.setIdCard(encrypTion(ac.getIdCard()));
		}
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	public String encrypTion(String str) {
		String  copy = "**************************************";
		StringBuilder  restr = new StringBuilder();
		if(str!=null&&str.length()>7){
			int i = str.length()-7;
			restr = restr.append(str.substring(0, 3)).append(copy.substring(0, i)).append(str.substring(str.length()-4, str.length()));
			return restr.toString();
		}else{
			return str;
		}
	}
	
	@Override
	public boolean doVerifyPayPwdState(AppRequestHead head) {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		AcAccountBind ab = accountbindMapper.selectAccountByUserId(userId);
		if(ab!=null&&ab.getTranPwd()!=null&&ab.getTranPwd().length()>0){
			return true;//密码
		}else{
			return false;//密码空
		}
	}

	@Override
	public boolean doVerifyPayPwdSame(TwoPwdRequest req,AppRequestHead head) {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		AcAccountBind ab = accountbindMapper.selectAccountByUserId(userId);
		if(ab!=null&&req.getOldPwd()!=null&&MD5.crypt(req.getOldPwd()).equals(ab.getTranPwd())){
			return true;//源码正确
		}else{
			return false;//密码空
		}
	}

	@Override
	public boolean queryAuthentication(String userId) {
		AcAccountBind acbind = accountbindMapper.selectAccountByUserId(userId);
		if(acbind!=null&&"1".equals(acbind.getStatus().toString())){
			return true;//已绑定
		}else{
			return false;//未绑定
		}
	}

	@Override
	public List<ProvinceInfoResponse> queryAllProvince() {
		return acProvinceListMapper.queryAllProvince();
	}

	@Override
	public List<CityInfoResponse> queryCityByProvince(String  provinceId) {
		return acCityListMapper.selectByProvinceCode( provinceId);
	}

	@Override
	public List<AcBankCodeResponse> queryAllBank() {
		return acBankCodeMappingMapper.queryAllBank();
	}

	@Override
	public void initAccountBind(String userId,int userType) {
		AcAccountBind bind = new AcAccountBind();
		bind.setUserId(userId);
		AcAccountBind acbind = accountbindMapper.selectAccountByUserId(userId);
		if(acbind==null){
			bind.setUserType(userType);
			accountbindMapper.insertSelective(bind);
		}
	} 

	@Override
	public WithdrawBankCardResponse queryWithdrawBankCard(String userId) {
		WithdrawBankCardResponse with = new WithdrawBankCardResponse();
		AcAccountBind ac = accountbindMapper.selectAccountByUserId(userId);
		if(ac!=null){
			with.setBankCard(ac.getBankCard());
			with.setBankName(ac.getBankName());
			with.setUserName(ac.getUserName());
			with.setTotalAmount(NumberUtils.getFormat(Double.parseDouble(ac.getTotalAmount()), "0.00"));
			with.setMobile(ac.getReserveMobile());
			with.setCity(ac.getCity());
			with.setKaiHuHang(ac.getKaiHuHang());
			//根据城市获取省份
			if(ac.getCity()!=null){
				AcCityList accity = new AcCityList();
				accity.setCityName(ac.getCity());
				AcCityList ci = acCityListMapper.selectOneByCondition(accity);
				with.setProvince(ci!=null?ci.getProvinceName():null);
			}
			with.setNeedkaiHuHang(ac.getKaiHuHang()==null?true:false);
			//只有提现有成功的情况下才不需要开户行
/*			int in = accountbindMapper.isNeedkaiHuHang(ac.getBankCardId());
			with.setNeedkaiHuHang(in>0?false:true);*/
		}
		//判断用户是否需要手续费
		with.setFee(needFee(userId)==true?"1.00":"0");
		with.setHasFee(needFee(userId));
		Integer times = queryLimitTimes(userId);
		with.setLimitTimes(times);
		//预计到账时间
		//先查询当日是否节假日
		String isHoliday = isHoliday(); 
		with.setPaymentDate("预计"+isHoliday +" 24点前");
		with.setWithdrawRemark(withdrawRemark(isHoliday));
		return with;
	}
	
	public String withdrawRemark(String time){
		String today = DateUtils.getNow(DateUtils.FORMAT_SHORT);
		String nextDay =DateUtils.format(DateUtils.addDay(new Date(), 1),DateUtils.FORMAT_SHORT);
		if(today.equals(time)){
			return "当天到账";
		}else if(nextDay.equals(time)){
			return "次日到账";
		}
		return "下个工作日到账";
		
	}
	
	public String isHoliday(){
		SimpleDateFormat sf1 = new SimpleDateFormat(DateUtils.FORMAT_SHORT);
		String nowDay = sf1.format(new Date());
		AcHolidayData ho = new AcHolidayData();
		ho.setNoWorkDay(nowDay);
		AcHolidayData reho = acHolidayDataMapper.selectOneByCondition(ho);
		if(reho==null){
			int hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			if(hours<14){
				return DateUtils.getNow(DateUtils.FORMAT_SHORT);
			}else{
				//获取下一个工作日
				return nextWorkDay(1);
			}
		}else{//是节假日,获取下一个工作日
			return nextWorkDay(1);
		}
	}
	
	public String nextWorkDay(int in){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.FORMAT_SHORT);
		calendar.add(Calendar.DAY_OF_YEAR, in);
		Date date = calendar.getTime();
		String nextDay = sdf.format(date);
		AcHolidayData ho = new AcHolidayData();
		ho.setNoWorkDay(nextDay);
		AcHolidayData reho = acHolidayDataMapper.selectOneByCondition(ho);
		if(reho==null){
			return nextDay;
		}else{
			nextDay = nextWorkDay(in+1);
		}
		return nextDay;
	}

	@Override
	@Transactional
	public Map<String,String> userWithdrawApply(UserWithdrawRequest req) {
		Map<String,String> returnStr = new HashMap<String, String>();
		BigDecimal amount = new BigDecimal(req.getAmount().replaceAll(" ", "")); 

		//运营人员禁止提现
		int i = accountbindMapper.isForbiWithdrawUser(req.getUserId());
		
		if(i>0){
			returnStr.put("code","运营人员不能提现");
        	return returnStr;
		}
		
		AcAccountBind acBind = accountbindMapper.selectAccountByUserId(req.getUserId());
		//查询用户注册信息
		CrmUserInfo crm = crmUserInfoService.queryUserInfoByUserId(req.getUserId());
		//增加冻结操作
		AcWithdrawApply apply = new AcWithdrawApply();
		apply.setAmount(amount);
		apply.setBisName("提现申请");
		apply.setBisTime(new Date());
		
		
		BigDecimal fee = new BigDecimal(0);
		//是否需要手续费
		if(needFee(req.getUserId())){
			fee = new BigDecimal(FEE);
		}
		apply.setOrderId(AcAccountBindController.getRandomString(5)+new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date()));
		apply.setUserType(req.getUserType());
		apply.setStatus("1");//提现中，资金已冻结，待审核
		apply.setCreatedDate(new Date());
		apply.setUserId(req.getUserId());
		apply.setUserName(acBind.getUserName());
		apply.setFee(fee);
		apply.setTotalAmount(amount.add(fee));
		apply.setBankCardId(acBind.getBankCardId());
		apply.setMobile(crm.getMobile());
		apply.setPaymentDate("预计"+isHoliday() +" 24点前到账");
		apply.setBankName(req.getBankName());
		apply.setCity(req.getCity());
		apply.setKaiHuHang(req.getKaihuhang());
		apply.setBankCode(req.getBankCode());
		
		BigDecimal oldFreezeAmount = new BigDecimal(acBind.getFreezeAmount());
        BigDecimal oldWithdrawAmount = new BigDecimal(acBind.getWithdrawAmount());
        BigDecimal oldTotalAmount = new BigDecimal(acBind.getTotalAmount());
        
        BigDecimal totalAmount = amount.add(fee);
        
        //判断账号余额是否满足提现  结果:-1小于 | 0等于| 1大于
        if(oldTotalAmount.compareTo(totalAmount)==-1){
        	returnStr.put("code","账号余额不满足提现");
        	return returnStr;
        };
        //保存提现信息
        acWithdrawApplyMapper.insertSelective(apply);
		//更新开户行、城市到绑卡信息表
		AcAccountBind ac = new AcAccountBind();
		ac.setUserId(req.getUserId());
		ac.setCity(req.getCity());
		ac.setKaiHuHang(req.getKaihuhang());
		//判断银行卡名称是否变动
//		ac.setBankName(req.getBankName());
//		ac.setBankCode(req.getBankCode());
		//处理冻结字段 交易金额  总金额
		ac.setWithdrawAmount(oldFreezeAmount.add(totalAmount).toString());
		ac.setFreezeAmount(oldWithdrawAmount.add(totalAmount).toString());
		ac.setTotalAmount(oldTotalAmount.subtract(totalAmount).toString());
		accountbindMapper.updateByPrimaryKeySelective(ac);
		//更新开户行、城市到银行卡信息表
		if(req.getCity()!=null&&req.getKaihuhang()!=null){
			AcBankCardInfo model = new AcBankCardInfo();
			model.setCity(req.getCity());
			model.setKaiHuHang(req.getKaihuhang());
			model.setUserId(req.getUserId());
			model.setBankCard(req.getBankCard());
//			model.setBankName(req.getBankName());
//			model.setBankCode(req.getBankCode());
			acBankCardInfoService.update(model);
		}
		//微信消息推送 提现申请通知
		final WeiXinMsgRequest wxreq = new WeiXinMsgRequest();
		wxreq.setUseId(req.getUserId());
		wxreq.setTemkey(SysConfigConstant.APPLY_WITHDRAWALS_ACCOUNT);//提现申请通知
		wxreq.setNickName(crm.getUserName());
		wxreq.setWithdrawTime(DateUtils.format(apply.getCreatedDate(),DateUtils.FORMAT_LONG));
		wxreq.setWithdrawAmount(amount.toString()+"元");
		String bankCardNo = acBind.getBankCard().substring(acBind.getBankCard().length()-4, acBind.getBankCard().length());
		wxreq.setWithdrawType(req.getBankName()+" (尾号"+bankCardNo+")");
		wxreq.setUseType(req.getUserType());
		
		ThreadpoolService.execute(new Runnable() {
			@Override
			public void run() {
				weiXinMsgService.sendWeiXinMsgCommon(wxreq);
			}
		});
		
		returnStr.put("code","00");
    	return returnStr;
		
	}
	
	//判断本次提现是否需要手续费
	public boolean needFee(String userId){
		String startTime = getMonthFirstDay();
		String endTime = getMonthEndDay();
		AcWithdrawTimes time = acWithdrawApplyMapper.queryWithdrawTimes(userId,startTime,endTime);
		if(time!=null&&time.getTimes()>=1){
			if(!(time.getFee()>(time.getTimes()-1)*FEE)){
				return true;
			}
		}
		return false;
	}
	
	//免费剩余次数
    public Integer queryLimitTimes(String userId){
    	String startTime = getMonthFirstDay();
		String endTime = getMonthEndDay();
    	AcWithdrawTimes time = acWithdrawApplyMapper.queryWithdrawTimes(userId,startTime,endTime);
    	if(time!=null&&time.getTimes()<=1){
    		return 1-time.getTimes();
		}else if(time!=null&&(time.getFee()>(time.getTimes()-1)*FEE)){
			return 1;
		}
		return 0;
    	
    } 
	
	public String getMonthFirstDay(){
		Calendar curCal = Calendar.getInstance();
        SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
        curCal.set(Calendar.DAY_OF_MONTH, 1);
        Date beginTime = curCal.getTime();
		return datef.format(beginTime) + " 00:00:00";
	};
	
	public String getMonthEndDay(){
		Calendar curCal = Calendar.getInstance();
        SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
        curCal.set(Calendar.DATE, 1);
        curCal.roll(Calendar.DATE, -1);
        Date endTime = curCal.getTime();
		return datef.format(endTime) + " 23:59:59";
	};

	@Override
	public double queryAccountBalance(String userId) {
		AcAccountBind ac = accountbindMapper.selectAccountByUserId(userId);
		return Double.parseDouble(ac.getTotalAmount());
	}

	@Override
	public double queryWithdrawingAmount(String userId) {
		AcAccountBind ac = accountbindMapper.selectAccountByUserId(userId);
		return Double.parseDouble(ac.getWithdrawAmount());
	}

	@Override
	public void resetPayPwd(ResetPayPwdRequest req) {
        //重置密码token  校验
		AcAccountBind bind = new AcAccountBind();
		bind.setTranPwd(MD5.crypt(req.getPwd()));
		bind.setUserId(req.getUserId());
		accountbindMapper.updateByPrimaryKeySelective(bind);
	}

	@Override
	public AcAccountBind selectAccountByUserId(String userId) {
		return accountbindMapper.selectAccountByUserId(userId);
	}

	@Override
	public List<AcAccountTypeReponse> queryAllAccountType(String userType) {
		return acAccountTypeMapper.queryAllAccountType(userType);
	}

	@Override
	public boolean sendVcode(String mobile,AppRequestHead head) {
		//发送手机验证码
		boolean boo = messageHelper.sendVerifyCode(mobile, AppUtils.getAppType(head.getOrgNumber()), MsgModuleEnum.UPDATETRADEPWD);
	    return boo;
	}

	@Override
	public boolean checkVerifyCode(String mobile,String verifyCode) {
		boolean boo = messageHelper.checkVerifyCode(mobile, MsgModuleEnum.UPDATETRADEPWD,verifyCode);
	    return boo;
	}
	
	@Override
	public boolean checkVerifyCode2(String mobile,String verifyCode) {
		boolean boo = messageHelper.checkVerifyCode(mobile, MsgModuleEnum.BINDCARD,verifyCode);
	    return boo;
	}
	@Override
	public boolean checkVerifyCode3(String mobile,String verifyCode) {
		boolean boo = messageHelper.checkVerifyCode(mobile, MsgModuleEnum.XIAOSHANGCHENG,verifyCode);
	    return boo;
	}
	
	

	@Override
	public Double queryWithdrawSummary(String userId) {
		return acWithdrawApplyMapper.queryWithdrawSummary(userId);
		
	}

	@Override
	public boolean isbindBankcard(String userId) {
		AcAccountBind bind =  accountbindMapper.selectAccountByUserId(userId);
		if(bind!=null&&"1".equals(bind.getStatus().toString())){
			return true;
		}else{
			return false;
		}
	}

	@Override
	@Transactional
	public String accountRecharge(AcAccountRecharge recharge) throws Exception{
		LOGGER.debug(">>>>>>>>>>>充值starting>>>>>>>>>");
		//先更改账号金额
		AcAccountBind bind =  accountbindMapper.selectAccountByUserId(recharge.getUserId());
		BigDecimal totalAmount = new BigDecimal(bind.getTotalAmount());
		bind.setTotalAmount(totalAmount.add(recharge.getTransAmount()).toString());
		accountbindMapper.updateByPrimaryKeySelective(bind);
		//再插入银行流水记录
		AcBalanceRecord record = new AcBalanceRecord();
		String serialNumber = StringUtils.getUUID();
		record.setTransAmount(recharge.getTransAmount());
		record.setOrderId(recharge.getRedpacketId());
		record.setUserId(recharge.getUserId());
		record.setBankCardId(bind.getBankCardId());
		record.setCreateTime(new Date());
		record.setTransDate(new Date());
		record.setUserType(recharge.getUserType());
		record.setTransType(recharge.getTransType());
		record.setRemark(recharge.getRemark());
		record.setSerialNumber(serialNumber);
		acBalanceRecordMapper.insertSelective(record);
		LOGGER.debug(">>>>>>>>>>>充值完成>>>>>>>>>");
		return serialNumber;
	}

	@Override
	public void acAccountUnbund(String userId) {
		accountbindMapper.acAccountUnbund(userId);
	}

	@Override
	public AcBankCodeResponse queryBankByBankId(int bankId) {
		return acBankCodeMappingMapper.queryBankById(bankId);
	}

	@Override
	public AcBankCodeResponse queryBankByBankName(String bankname) {
		return acBankCodeMappingMapper.queryBankByName(bankname);
	}

	@Override
	public void updateBindTimes(AcAccountBind upbind) {
		accountbindMapper.updateBindTimes(upbind);
	}

	@Override
	public List<AcAccountBind> checkIdCardOnly(String idCard) {
		return accountbindMapper.checkIdCardOnly(idCard);
	}

	@Override
	public Double queryCfpActivityReward(String userId, String month) {
		return accountbindMapper.queryCfpActivityReward(userId, month);
	}
	
	@Override
	public Double queryCfpActivityRewardNew(String userId, String month) {
		return accountbindMapper.queryCfpActivityRewardNew(userId, month);
	}
	
	@Override
	public Double queryHongbaoProfit(String userId, String month) {
		return accountbindMapper.queryHongbaoProfit(userId, month);
	}
	
	@Override
	public Double queryGrantedAmount(String userId, String month) {
		return accountbindMapper.queryGrantedAmount(userId, month);
	}

	@Override
	public Double queryWaitGrantAmount(String userId, String month) {
		return accountbindMapper.queryWaitGrantAmount(userId, month);
	}

	@Override
	public List<AcAccountBind> queryCfpOfNotSetImage() {
		return accountbindMapper.queryCfpOfNotSetImage();
	}

	@Override
	public BaseResponse queryOmsaccountExist(OpenRequestHead openRequestHead,OmsAccountExistRequest omsAccountExistRequest) {
		// TODO Auto-generated method stub
		AccountExistResponse accountExistResponse = new AccountExistResponse();
		//判断身份证的唯一性
//		List<AcAccountBind> idCardList = checkIdCardOnly(omsAccountExistRequest.getIdCard());
		//判断手机号码的唯一性
		CrmUserInfo crmUserInfo = crmUserInfoService.selectCrmUserInfoByMobile(omsAccountExistRequest.getMobile());
		
		if(crmUserInfo == null){	
			accountExistResponse.setIsExist("N");
		} else {
			accountExistResponse.setIsExist("Y");
			
			//第三方平台是否愿意绑定老用户
			if(omsAccountExistRequest.getIfNeedOldBind() == 1){
				
				boolean ifExistBind = crmInvestorOrgInfoToOurService.ifExistBindAccount(openRequestHead.getOrgNumber(),crmUserInfo.getUserId());
				
				if(!ifExistBind){
					CimOrgInfoToOur cimOrgInfoToOur = cimOrgInfoToOurService.selectOneByOrgNumber(openRequestHead.getOrgNumber());
					/**
					 * 生成6位数字的验证码
					 */
					String verifyCode = VerifyCodeUtils.generatePasswordCode(6);	
					String toSaveKey = omsAccountExistRequest.getMobile()+"#"+MsgModuleEnum.INV_OLD_ACCOUNT_BIND+"#"+verifyCode;
					redisManager.setex(toSaveKey,(int)TimeSetConstants.MSGVERIFYCODE_VALID_DATE/1000 ,toSaveKey);
					LOGGER.info(">>>>>>>>>>>>>>>>>");
					LOGGER.info(">>>>>>>>>>>>>>>>> 发送短信验证码 : phone = {} | code = {}" ,omsAccountExistRequest.getMobile(), verifyCode);
					LOGGER.info(">>>>>>>>>>>>>>>>>");
					CommonTCSResult retCode = smMessageQueueService.sendSingleMessage(omsAccountExistRequest.getMobile(), AppTypeEnum.INVESTOR, MsgModuleEnum.INV_OLD_ACCOUNT_BIND, cimOrgInfoToOur.getName(),verifyCode);
					if(retCode.getCode() != 0){		
						LOGGER.error("第三方平台绑定老用户发送短信异常,mobile={}",omsAccountExistRequest.getMobile());
						return OpenResponseUtil.getErrorBusi("bind_old_account_msg_error", "第三方平台绑定领会老用户时【领会】发送短信异常");//发送验证码失败
					}
				} else {
					LOGGER.info("第三方平台绑定老用户异常,mobile={}已绑定",omsAccountExistRequest.getMobile());
					return OpenResponseUtil.getErrorBusi("exist_old_account_bind", "已经存在的领会老账户绑定");//发送验证码失败
				}
			}
		}
		return OpenResponseUtil.getSuccessResponse(accountExistResponse);
	}

	@Override
	public BaseResponse omsAccountBind(OmsAccountBindRequest omsAccountBindRequest) {
		
		OmsAccountBindResponse omsAccountBindResponse = new OmsAccountBindResponse();	
		Date now = new Date();

		//检验手机号码
		CrmUserInfo crmUserInfo = crmUserInfoService.selectCrmUserInfoByMobile(omsAccountBindRequest.getMobile());
		if(crmUserInfo != null){
			return OpenResponseUtil.getErrorBusi("mobile_exists","手机号已注册");
		}
		//检验身份证
		List<AcAccountBind> idCardList = checkIdCardOnly(omsAccountBindRequest.getIdCard());
		if(CollectionUtils.isNotEmpty(idCardList)){
			return OpenResponseUtil.getErrorBusi("idcard_exists","身份证已注册");
		}
		//生成8位数字的账号密码
		String password = VerifyCodeUtils.generatePasswordCode(8);
		
		/**
		 * 投资账号注册及绑卡
		 */
		try {
			//注册
			String userId = crmInvestorService.registerInvestor(omsAccountBindRequest.getMobile(),password, null,null,null);
			
			//写入关联关系
			CrmInvestorOrgInfoToOur crmInvestorOrgInfoToOur = new CrmInvestorOrgInfoToOur();
			crmInvestorOrgInfoToOur.setUserId(userId);
			crmInvestorOrgInfoToOur.setOrgNumber(omsAccountBindRequest.getOrgNumber());
			crmInvestorOrgInfoToOur.setIfOldAccount(0);
			crmInvestorOrgInfoToOur.setCreateTime(now);
			crmInvestorOrgInfoToOur.setRemark("第三方对接领会系统自动注册");
			crmInvestorOrgInfoToOurService.insert(crmInvestorOrgInfoToOur);
			
			//生成环信帐号
			easemobService.generateEasemobThread(userId);
			
			//绑卡
			AddBankCardRequest addBankCardRequest = new AddBankCardRequest();
			addBankCardRequest.setBankCard(omsAccountBindRequest.getBankCard());
			addBankCardRequest.setBankName(omsAccountBindRequest.getBankName());
			addBankCardRequest.setIdCard(omsAccountBindRequest.getIdCard());
			addBankCardRequest.setMobile(omsAccountBindRequest.getMobile());
			addBankCardRequest.setReserveMobile(omsAccountBindRequest.getMobile());
			addBankCardRequest.setUserId(userId);
			addBankCardRequest.setUserName(omsAccountBindRequest.getUserName());
			addBankCardRequest.setUserType(2);
			
			String bankname = AcAccountUtils.matchbankName(omsAccountBindRequest.getBankName());
			addBankCardRequest.setBankName(bankname);
			AcBankCodeResponse bank = queryBankByBankName(bankname);
			if(bank!=null){
				addBankCardRequest.setBankCode(bank.getBankCode());
				addBankCardRequest.setBankId(bank.getBankId());
			} else {
				addBankCardRequest.setBankCode(omsAccountBindRequest.getBankCode());
			}
			acBankCardInfoService.insertBankCard(addBankCardRequest);
			
			omsAccountBindResponse.setUserId(userId);
			omsAccountBindResponse.setIsNewUser("Y");
			omsAccountBindResponse.setBindTime(now);
		} catch (Exception e) {
			LOGGER.error("第三方机构绑定领会账号-投资者注册异常 " , e);
			return OpenResponseUtil.getErrorBusi(new BaseResponse("reg_error","绑定领会平台账户-注册失败"));
		}
		return OpenResponseUtil.getSuccessResponse(omsAccountBindResponse);
	}
	
	@Override
	public AcAccountBind selectBindAcctByUserId(String userId) {
		return accountbindMapper.selectBindAcctByUserId(userId);
	}

	@Override
	public BaseResponse existAccountBind(OpenRequestHead openRequestHead,OmsExistAccountRequest omsExistAccountRequest) {
		OmsAccountBindResponse omsAccountBindResponse = new OmsAccountBindResponse();
		if(messageHelper.checkVerifyCode(omsExistAccountRequest.getMobile(), MsgModuleEnum.INV_OLD_ACCOUNT_BIND, omsExistAccountRequest.getMsgCode())){	
			Date now = new Date();
			//获取投资用户的信息
			CrmUserInfo crmUserInfo = crmUserInfoService.selectCrmUserInfoByMobile(omsExistAccountRequest.getMobile());
			
			//写入关联关系
			CrmInvestorOrgInfoToOur crmInvestorOrgInfoToOur = new CrmInvestorOrgInfoToOur();
			crmInvestorOrgInfoToOur.setUserId(crmUserInfo.getUserId());
			crmInvestorOrgInfoToOur.setOrgNumber(openRequestHead.getOrgNumber());
			crmInvestorOrgInfoToOur.setIfOldAccount(1);
			crmInvestorOrgInfoToOur.setCreateTime(now);
			crmInvestorOrgInfoToOur.setRemark("第三方对接领会系统老用户短信认证绑定");
			crmInvestorOrgInfoToOurService.insert(crmInvestorOrgInfoToOur);
			
			omsAccountBindResponse.setUserId(crmUserInfo.getUserId());
			omsAccountBindResponse.setIsNewUser("N");
			omsAccountBindResponse.setBindTime(now);
		} else {
			return OpenResponseUtil.getErrorBusi("msg_code_error", "短信验证码错误");//短信验证码错误
		}
		return OpenResponseUtil.getSuccessResponse(omsAccountBindResponse);
	}

	@Override
	public void accountDeduct(AcAccountDeduct deduct) {
		LOGGER.debug(">>>>>>>>>>>扣款starting>>>>>>>>>");
		//先更改账号金额
		AcAccountBind bind =  accountbindMapper.selectAccountByUserId(deduct.getUserId());
		BigDecimal totalAmount = new BigDecimal(bind.getTotalAmount());
		//判断账号余额是否满足提现  结果:-1小于 | 0等于| 1大于
        if(totalAmount.compareTo(deduct.getTransAmount())==-1){
        	throw new ServiceException("金额不足,扣款失败!");
        };
		bind.setTotalAmount(totalAmount.subtract(deduct.getTransAmount()).toString());
		accountbindMapper.updateByPrimaryKeySelective(bind);
		//再插入银行流水记录
		AcBalanceRecord record = new AcBalanceRecord();
		String serialNumber = StringUtils.getUUID();
		record.setTransAmount(deduct.getTransAmount());
		record.setUserId(deduct.getUserId());
		record.setBankCardId(bind.getBankCardId());
		record.setCreateTime(new Date());
		record.setTransDate(new Date());
		record.setUserType(1);
		record.setTransType(25);
		record.setRemark(deduct.getRemark());
		record.setSerialNumber(serialNumber);
		acBalanceRecordMapper.insertSelective(record);
		LOGGER.debug(">>>>>>>>>>>扣款完成>>>>>>>>>");
	}

	@Override
	public AcBankImageResponse selectBankImage(String bankCode) {
		return accountbindMapper.selectBankImage(bankCode);
	}
}
