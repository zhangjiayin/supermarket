package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.act.redpacket.service.RedPacketService;
import com.linkwee.api.activity.response.NewYearRewardResponse;
import com.linkwee.api.request.crm.CfplannerDataRequest;
import com.linkwee.api.request.crm.InvotationRequest;
import com.linkwee.api.request.crm.RegisterSevReq;
import com.linkwee.api.request.crm.UserTypeRequest;
import com.linkwee.api.response.crm.CfplannerDataResponse;
import com.linkwee.api.response.crm.CfplannerMemberDetailResponse;
import com.linkwee.api.response.crm.CfplannerMemberResponse;
import com.linkwee.api.response.crm.CustomerCfpmember;
import com.linkwee.api.response.crm.CustomerMemberInvestRecordResponse;
import com.linkwee.core.base.ServiceResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.CrmCfplannerMapper;
import com.linkwee.web.dao.SequenceDao;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.CfpJobGradeEnum;
import com.linkwee.web.enums.CfpLevelEnum;
import com.linkwee.web.enums.CfpNewcomerTaskEnum;
import com.linkwee.web.enums.YesOrNotEnum;
import com.linkwee.web.model.CrmCfpNewcomerWelfareTask;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.acc.AcAccountRecharge;
import com.linkwee.web.model.crm.CfplannerInvestorPersonResp;
import com.linkwee.web.model.crm.InvotateUserListResp;
import com.linkwee.web.service.AcAccountBindService;
import com.linkwee.web.service.CrmCfgLevelService;
import com.linkwee.web.service.CrmCfpLevelRecordService;
import com.linkwee.web.service.CrmCfpLevelRecordTempService;
import com.linkwee.web.service.CrmCfpNewcomerTaskService;
import com.linkwee.web.service.CrmCfpNewcomerWelfareTaskService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.util.MD5;
import com.linkwee.xoss.util.PwdUtil;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:25:55
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmCfplannerService")
public class CrmCfplannerServiceImpl extends GenericServiceImpl<CrmCfplanner, Long> implements CrmCfplannerService{
	
	private static final Logger logger = LoggerFactory.getLogger(CrmCfplannerServiceImpl.class);
	
	@Resource
	private CrmCfplannerMapper crmCfplannerMapper;
	
	@Resource
    private CrmInvestorService crmInvestorService;
	
	@Resource
    private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private SequenceDao sequenceDao;
	
	@Resource
	private CrmCfgLevelService crmCfgLevelService;
	
	@Resource
	private AcAccountBindService accountbindService;

	@Resource
	private SysConfigService sysConfigService;
	
	@Resource
	private RedPacketService redPacketService;
	
	@Resource
	private CrmCfpNewcomerTaskService crmCfpNewcomerTaskService;
	
	@Resource
	private CrmCfpLevelRecordService crmCfpLevelRecordService;
	
	@Resource
	private CrmCfpNewcomerWelfareTaskService crmCfpNewcomerWelfareTaskService;
	
    @Resource
    private CrmCfplannerService crmCfplannerService;
    
    @Resource
    private CrmCfpLevelRecordTempService crmCfpLevelRecordTempService;
	
	@Override
    public GenericDao<CrmCfplanner, Long> getDao() {
        return crmCfplannerMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		logger.debug(" -- CrmCfplanner -- 排序和模糊查询 ");
		Page<CrmCfplanner> page = new Page<CrmCfplanner>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmCfplanner> list = this.crmCfplannerMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

    /**
	 * 号码是否已注册
	 * @param mobile
	 * @return
	 */
	@Override
	public boolean isExistsCfplanner(String mobile) {
		CrmCfplanner bo = new CrmCfplanner();
		bo.setMobile(mobile);
		bo = this.selectOne(bo);
		if(bo != null){
			return true;
		}
		return false;
	}
	
	public boolean isDisabledLogin90days(String mobile) {
		CrmCfplanner crmCfplanner = this.queryCfplannerByMobile(mobile);
		Date disabledLoginTime = crmCfplanner.getDisabledLoginTime();
		if(disabledLoginTime == null || "".equals(disabledLoginTime)) {
			return false;
		}
		Date newDate = DateUtils.addDay(disabledLoginTime, 90); 
		logger.debug("解禁登录日期: " + DateUtils.format(newDate,DateUtils.FORMAT_SHORT));
		Date newDateFormat = null;
		try {
			newDateFormat = DateUtils.parse(newDate, DateUtils.FORMAT_SHORT);
		} catch (Exception e) {
			logger.error("格式化时间错误 " + e);
		}
		int rlt = DateUtils.compareDate(new Date(), newDateFormat);
		if(rlt == -1) {
			//今天小于解禁登录日期
			return true;
		}
		return false;
	}
	
	@Override
	public String queryDisabledLoginTime(String mobile) {
		CrmCfplanner crmCfplanner = this.queryCfplannerByMobile(mobile);
		Date disabledLoginTime = crmCfplanner.getDisabledLoginTime();
		if(disabledLoginTime == null || "".equals(disabledLoginTime)) {
			return null;
		} else {
			String disabledLoginTimeStr = DateUtils.format(disabledLoginTime,DateUtils.FORMAT_SHORT);
			return disabledLoginTimeStr;
		}
	}

	/**
	 * 根据电话号码获取用户
	 * @param mobile
	 * @return
	 */
	@Override
	public CrmCfplanner queryCfplannerByMobile(String mobile) {
		CrmCfplanner bo = new CrmCfplanner();
		bo.setMobile(mobile);
		return crmCfplannerMapper.selectOneByCondition(bo);
	}

	/**
	 * 注册理财师
	 * @param registerSevReq
	 * @return
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public ServiceResponse<Boolean> registerLcs(final RegisterSevReq registerSevReq) throws Exception {
		CrmCfplanner  crmCfplanner = this.queryCfplannerByMobile(registerSevReq.getMobile());
		if(crmCfplanner!=null){
			 return new ServiceResponse<Boolean>(CrmUserInfoService.Error.REGISTER_ISEXISTS);
		}
		
		String userId = null;
		//基础用户表数据
		CrmUserInfo crmUserInfo = crmUserInfoService.selectCrmUserInfoByMobile(registerSevReq.getMobile());
		if(crmUserInfo == null) {
			userId =  StringUtils.getUUID();
			crmUserInfo = new CrmUserInfo();
			crmUserInfo.setCreateTime(new Date());
			crmUserInfo.setMobile(registerSevReq.getMobile());
			crmUserInfo.setPassword(MD5.crypt(registerSevReq.getPassword()));
			crmUserInfo.setUpdateTime(new Date());
			crmUserInfo.setUserId(userId);
			crmUserInfoService.insert(crmUserInfo);
		}else {
			userId = crmUserInfo.getUserId();
		}
		
		//账户表信息
		accountbindService.initAccountBind(userId, AppTypeEnum.CHANNEL.getKey());
		
		//理财师表数据
		crmCfplanner = new CrmCfplanner();
		if(StringUtils.isNotBlank(registerSevReq.getParentId())){//有推荐人
			crmCfplanner.setParentId(registerSevReq.getParentId());
			crmCfplanner.setSalesOrgId(registerSevReq.getSalesOrgId());
			crmCfplanner.setSalesOrgDepth(registerSevReq.getSalesOrgDepth());
		} else {
			if(StringUtils.isNotBlank(registerSevReq.getSalesOrgId())) {
				if(this.salesOrgIsExist(registerSevReq.getSalesOrgId())) {
					crmCfplanner.setSalesOrgId(registerSevReq.getSalesOrgId());
				}
			}
		}
		crmCfplanner.setCfpLevel(CfpLevelEnum.REGISTERED.getValue());
		crmCfplanner.setMobile(registerSevReq.getMobile());
		crmCfplanner.setCfpRegTime(new Date());
		crmCfplanner.setDelStatus(Byte.parseByte(YesOrNotEnum.NOT.getCode()+""));
		crmCfplanner.setDepartment("99999999");
		crmCfplanner.setUserId(userId);
		crmCfplanner.setEasemobAcct("cfp" + userId);//环信帐号
		crmCfplanner.setEasemobPassword(PwdUtil.SHA1ToBase64(userId));//环信密码
		crmCfplanner.setCreateTime(new Date());
		crmCfplanner.setUpdateTime(new Date());
		crmCfplanner.setRectVisitTime(new Date());
		crmCfplanner.setHeadImage("7187525842a640ca36e48a5ce366894d");//4.5.0版本增加默认头像
		if(registerSevReq.getFromUrl() != null && !"".equals(registerSevReq.getFromUrl()) && registerSevReq.getFromUrl().length() > 512) {
			crmCfplanner.setRegisterFromUrl(registerSevReq.getFromUrl().substring(0, 512));
		} else {
			crmCfplanner.setRegisterFromUrl(registerSevReq.getFromUrl());
		}
		if(registerSevReq.getAccessUrl() != null && !"".equals(registerSevReq.getAccessUrl()) && registerSevReq.getAccessUrl().length() > 512) {
			crmCfplanner.setRegisterAccessUrl(registerSevReq.getAccessUrl().substring(0, 512));
		} else {
			crmCfplanner.setRegisterAccessUrl(registerSevReq.getAccessUrl());
		}
		this.insert(crmCfplanner);
		
		//初始化理财师的月职级和天职级
		crmCfpLevelRecordService.initCfpLevel(userId,null);
		crmCfpLevelRecordService.initCfpLevelTemp(userId,null);
		
		//理财师新手任务
		/*CrmCfpNewcomerTask crmCfpNewcomerTask = new CrmCfpNewcomerTask();
		crmCfpNewcomerTask.setUserId(userId);
		crmCfpNewcomerTask.setCreateTime(new Date());
		crmCfpNewcomerTask.setLastUpdateTime(new Date());
		crmCfpNewcomerTaskService.insert(crmCfpNewcomerTask);*/
		//新手福利六连送 记录新注册用户的任务完成状态
		CrmCfpNewcomerWelfareTask crmCfpNewcomerWelfareTask = new CrmCfpNewcomerWelfareTask();
		crmCfpNewcomerWelfareTask.setUserId(userId);
		crmCfpNewcomerWelfareTask.setCreateTime(new Date());
		crmCfpNewcomerWelfareTask.setLastUpdateTime(new Date());
		crmCfpNewcomerWelfareTaskService.insert(crmCfpNewcomerWelfareTask);
		//新手福利六连送--注册（红包在配置表中配置后才发放。与之前新手任务重合，故不需配置）
		crmCfpNewcomerWelfareTaskService.sendTaskReward(userId, CfpNewcomerTaskEnum.CFP_NEWCOMMER_WELFARE_REGISTER);
		
		//投资用户信息表
		CrmInvestor crmInvestor = crmInvestorService.queryInvestorByMobile(registerSevReq.getMobile());
		if(crmInvestor == null) {
			crmInvestor = new CrmInvestor();
			CrmInvestor refUserCustomer = null;//邀请人
			if(StringUtils.isNotBlank(registerSevReq.getParentId())){
				refUserCustomer = crmInvestorService.queryInvestorByUserId(registerSevReq.getParentId());
			}
			if(null != refUserCustomer){
				crmInvestor.setRefUser(registerSevReq.getParentId());
				//判断邀请人是否为理财师
				CrmCfplanner refCrmCfplanner = this.queryCfplannerByUserId(registerSevReq.getParentId());
				if(null != refCrmCfplanner ){
					crmInvestor.setRefType(new Byte("1"));//理财师邀请
				}else{
					crmInvestor.setRefType(new Byte("2"));//客户邀请
				}
			}
			crmInvestor.setCfplanner(userId);//理财师的理财师是自己
			crmInvestor.setIsFreeCustomer(new Byte("0"));
			crmInvestor.setUserId(userId);
			crmInvestor.setMobile(registerSevReq.getMobile());
			crmInvestor.setCreateTime(new Date());
			crmInvestor.setUpdateTime(new Date());
			crmInvestor.setRectVisitTime(new Date());
			crmInvestor.setEasemobAcct("inv" + userId);//环信帐号
			crmInvestor.setEasemobPassword(PwdUtil.SHA1ToBase64(userId));//环信密码
			crmInvestor.setRegisterFromUrl(registerSevReq.getFromUrl());
			crmInvestor.setRegisterAccessUrl(registerSevReq.getAccessUrl());
			crmInvestor.setHeadImage("7187525842a640ca36e48a5ce366894d");//4.5.0版本增加默认头像
			crmInvestorService.insert(crmInvestor);
			
			//注册送5元现金
			String swicth = sysConfigService.getValuesByKey(SysConfigConstant.REGISTER_SEND_CASH_SWICTH);
			if("ON".equals(swicth)){
				try {
					AcAccountRecharge recharge = new AcAccountRecharge();
					recharge.setRedpacketId(StringUtils.getUUID());
					recharge.setRemark("注册送5元现金");
					recharge.setTransAmount(BigDecimal.valueOf(5));
					recharge.setTransType(3);//活动奖励
					recharge.setUserId(userId);
					recharge.setUserType(2);
					accountbindService.accountRecharge(recharge);
				} catch (Exception e) {
					logger.error("注册送5元现金失败" , e);
				}
			}
			
		}else {
			CrmInvestor crmInvestorUpdate = new CrmInvestor();
			crmInvestorUpdate.setCfplanner(userId);
			crmInvestorUpdate.setIsFreeCustomer(new Byte("0"));
			crmInvestorUpdate.setId(crmInvestor.getId());
			crmInvestorService.update(crmInvestorUpdate);
		}
		
		//计算注册用户上级理财师的职级
		/*if(StringUtils.isNotBlank(registerSevReq.getParentId())){
			crmCfgLevelService.rankCalculation(userId);
		}*/
		return new ServiceResponse<Boolean>(true);
			
	}

	/**
	 * 销售机构是否存在
	 * @param salesOrgId
	 * @return
	 */
	private boolean salesOrgIsExist(String salesOrgId) {
		int cot = crmCfplannerMapper.querySalesOrgCount(salesOrgId);
		if(cot > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 根据理财师编码号码获取用户
	 * @param number 理财师编码
	 * @return
	 */
	public CrmCfplanner queryCfplannerByNumber(String number) {
		CrmCfplanner bo = new CrmCfplanner();
		bo.setNumber(number);
		return crmCfplannerMapper.selectOneByCondition(bo);
	}
	
	/**
	 * 创建理财师编码
	 */
	public String getNumber(){
		String number = sequenceDao.getSequence("tcrm_cfplanner");
		CrmCfplanner oldsaleuserInfo = this.queryCfplannerByNumber(number);
		if(null == oldsaleuserInfo){
			return number;
		}else{
			return getNumber();
		}
	}

	/**
	 * 检测是否能被推荐
	 */
	@Override
	public ServiceResponse<Boolean> checkCfgRecommend(String recommendUserNumber, String mobile) {
		if(isExistsCfplanner(mobile)){
			return new ServiceResponse<Boolean>(CrmUserInfoService.Error.CHECK_CFGRECOMMEND_USER_ISREG);
		}
		CrmInvestor crmInvestor = crmInvestorService.queryInvestorByMobile(mobile);
			if(null != crmInvestor && crmInvestor.getIsFreeCustomer() == 1 ){
				//自由用户不保护专属关系
				return new ServiceResponse<Boolean>(true);
			}
			Date curr = DateUtils.addDay(new Date(), -180);
			//处于保护时间内
			if(crmInvestor != null && crmInvestor.getCfplanner() != null &&
					crmInvestor.getRectVisitTime() != null && curr.before(crmInvestor.getRectVisitTime())){
				//是当前理财师客户
				if(recommendUserNumber != null && recommendUserNumber.equals(crmInvestor.getCfplanner())){
					return new ServiceResponse<Boolean>(true);
				}else{
					return new ServiceResponse<Boolean>(CrmUserInfoService.Error.CHECK_CFGRECOMMEND_USER_PROTECTED);
				}
			}
		return new ServiceResponse<Boolean>(true);
	}
	
	/**
	 * 检测用户是否必须需要推荐才能注册
	 * @param mobile 被检测手机号
	 * @return
	 */
	@Override
	public boolean checkCfgNeedRc(String mobile) {
		CrmInvestor customer = crmInvestorService.queryInvestorByMobile(mobile);
		if(null != customer && customer.getIsFreeCustomer() == 1){
			//自由用户不保护专属关系
			return false;
		}
		Date curr = DateUtils.addDay(new Date(), -180);
		//处于保护时间内
		if(customer != null&&StringUtils.isNotBlank(customer.getCfplanner()) &&
			customer.getRectVisitTime() != null && curr.before(customer.getRectVisitTime())){
			return true;
		}
		return false;
	}

	/**
	 * 查投资用户的理财师
	 * @param mobile
	 * @return
	 */
	@Override
	public CrmCfplanner queryCfplannerByInvestMobile(String investorMobile) {
		return crmCfplannerMapper.queryCfplannerByInvestMobile(investorMobile);
	}

	/**
	 * 查询手机号码已注册的理财师userId
	 */
	@Override
	public List<String> selectRegCfplanners(String[] mobileArray) {
		return crmCfplannerMapper.selectRegCfplanners(mobileArray);
	}

	/**
	 * 根据userId查理财师信息
	 * @param mobile
	 * @return
	 */
	@Override
	public CrmCfplanner queryCfplannerByUserId(String userId) {
		if(StringUtils.isBlank(userId)) {
			return null;
		}
		CrmCfplanner bo = new CrmCfplanner();
		bo.setUserId(userId);
		return crmCfplannerMapper.selectOneByCondition(bo);
	}

	/**
	 * 更新理财师二维码字段
	 */
	@Override
	public void updateCfpQrByUserId(String userId, String qrcode) {
		CrmCfplanner crmCfplanner = new CrmCfplanner();
		crmCfplanner.setUserId(userId);
		crmCfplanner.setQrcode(qrcode);
		crmCfplannerMapper.updateCfpQrByUserId(crmCfplanner);
	}

	/**
	 * 根据环信帐号查理财师信息
	 * @param easemobAcctList
	 * @return
	 */
	@Override
	public List<CrmCfplanner> queryCfplannerByEasemob(List<String> easemobAcctList) {
		return crmCfplannerMapper.queryCfplannerByEasemob(easemobAcctList);
	}

	/**
	 * 查投资者的理财师
	 * @param userId
	 * @return
	 */
	@Override
	public CrmCfplanner queryCfplannerByInvestor(String investorUserId) {
		return crmCfplannerMapper.queryCfplannerByInvestor(investorUserId);
	}

	/**
	 * 查理财师的团队人数
	 * @param userId 理财师userId
	 * @return  团队人数
	 */
	@Override
	public int queryTeamMemberCount(String userId) {
		return crmCfplannerMapper.queryTeamMemberCount(userId);
	}

	/**
	 * 更新理财师等级与经验
	 * @param userId 理财师用户编号
	 * @param level 理财师等级
	 * @param experience 理财师增加经验
	 */
	@Override
	public void updateCfplannerRankExperience(String userId, String level,Integer experience) {
		crmCfplannerMapper.updateCfplannerRankExperience(userId, level, experience);
	}

	/**
	 * 修改理财师信息
	 * @param bo
	 * @return
	 */
	@Override
	public int updateByUserId(CrmCfplanner crmCfplanner) {
		return crmCfplannerMapper.updateByUserId(crmCfplanner);
	}

	/**
	 * 查理财师的所有团队成员
	 * @param userId
	 * @return
	 */
	@Override
	public List<CrmCfplanner> queryTeamAllMember(String userId) {
		return crmCfplannerMapper.queryTeamAllMember(userId);
	}
	
	/**
	 * 查理财师的一级下级成员
	 * @param userId
	 * @return
	 */
	@Override
	public List<CrmCfplanner> queryLowerLevelOne(String userId) {
		return crmCfplannerMapper.queryLowerLevelOne(userId);
	}

	/**
	 * 符合分配规则的理财师
	 * @return
	 */
	@Override
	public List<String> queryConformAllotRuleCfps() {
		List<String> list = crmCfplannerMapper.queryConformAllotRuleCfps();
		if(list == null || list.size() == 0) {
			list = crmCfplannerMapper.queryLoginInSevenDaysCfp();
		}
		return list;
	}

	@Override
	public CrmCfplanner queryParentByUserId(String userId) {
		CrmCfplanner cfp = this.queryCfplannerByUserId(userId);
		CrmCfplanner parent = null;
		if(cfp != null && cfp.getParentId() != null && !"".equals(cfp.getParentId())) {
			parent = this.queryCfplannerByUserId(cfp.getParentId());
		}
		return parent;
	}

	@Override
	public int queryCustomerCount(String userId) {
		return crmCfplannerMapper.queryCustomerCount(userId);
	}

	@Override
	public boolean isLockedCfplanner(String mobile) {
		CrmCfplanner crmCfplanner = this.queryCfplannerByMobile(mobile);
		if(crmCfplanner != null && crmCfplanner.getIsLocked() != null &&  crmCfplanner.getIsLocked() == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<String> querySpecifiedCfps() {
		return crmCfplannerMapper.querySpecifiedCfps();
	}

	@Override
	public PaginatorResponse<InvotateUserListResp> queryInvitationCfplannerRecord(InvotationRequest req,
			Page<InvotateUserListResp> page) {
		PaginatorResponse<InvotateUserListResp> paginatorResponse = new PaginatorResponse<InvotateUserListResp>();
		paginatorResponse.setDatas(crmCfplannerMapper.queryInvitationCfplannerRecord(req,page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public int queryInvitationCfplannerRecordStatistics(String userId) {
		return crmCfplannerMapper.queryInvitationCfplannerRecordStatistics(userId);
	}

	@Override
	public boolean isNew(String userId) {
		return crmCfplannerMapper.isNew(userId)==0;
	}

	@Override
	public CrmCfplanner personalCenter(String userId) {
		return crmCfplannerMapper.personalCenter(userId);
	}

	@Override
	public CrmCfplanner queryCfpUserInfo(String userId) {
		return crmCfplannerMapper.queryCfpUserInfo(userId);
	}

	@Override
	public PaginatorResponse<CrmCfplanner> invitationCfpList(Page<CrmCfplanner> page, Map<String, Object> conditions) {
		PaginatorResponse<CrmCfplanner> cfpresponse = new PaginatorResponse<CrmCfplanner>();
		List<CrmCfplanner> cfpList = crmCfplannerMapper.invitationCfpList(page,conditions);
		cfpresponse.setDatas(cfpList);
		cfpresponse.setValuesByPage(page);
		return cfpresponse;
	}

	@Override
	public PaginatorResponse<CrmCfplanner> invitationInvestorList(Page<CrmCfplanner> page,
			Map<String, Object> conditions) {
		PaginatorResponse<CrmCfplanner> cfpresponse = new PaginatorResponse<CrmCfplanner>();
		List<CrmCfplanner> cfpList = crmCfplannerMapper.invitationInvestorList(page,conditions);
		cfpresponse.setDatas(cfpList);
		cfpresponse.setValuesByPage(page);
		return cfpresponse;
	}

	@Override
	public int queryNewCfpNumber(String fromTime) {
		return crmCfplannerMapper.queryNewCfpNumber(fromTime);
	}

	@Override
	public CrmCfplanner queryCfplannerInfo(String userId) {
		return crmCfplannerMapper.queryCfplannerInfo(userId);
	}

	@Override
	public PaginatorResponse<CustomerCfpmember> queryCfpmemberPage(Page<CustomerCfpmember> page,
			UserTypeRequest req) {
		PaginatorResponse<CustomerCfpmember> customerCfpmemberResponse = new PaginatorResponse<CustomerCfpmember>();
		List<CustomerCfpmember> memberList = new ArrayList<CustomerCfpmember>();
		if(req!=null&&req.getAttenInvestType()==3){
			memberList = crmCfplannerMapper.queryCfpmemberPage(page,req);
		}else{
			memberList = crmCfplannerMapper.queryCfpmemberPageOther(page,req);
		}
		customerCfpmemberResponse.setDatas(memberList);
		customerCfpmemberResponse.setValuesByPage(page);
		return customerCfpmemberResponse;
	}

	@Override
	public PaginatorResponse<CustomerMemberInvestRecordResponse> cfpInvestRecordPage(
			Page<CustomerMemberInvestRecordResponse> page, Map<String, Object> conditions) {
		CrmCfplanner cfp = crmCfplannerService.queryCfplannerByUserId(conditions.get("cfpUserId").toString());
		PaginatorResponse<CustomerMemberInvestRecordResponse> response = new PaginatorResponse<CustomerMemberInvestRecordResponse>();
		List<CustomerMemberInvestRecordResponse> memberList = crmCfplannerMapper.cfpInvestRecordPage(page,conditions);
		for(CustomerMemberInvestRecordResponse cu:memberList){
			cu.setHeadImage(cfp.getHeadImage());
			cu.setUserName(cfp.getUserName());
			cu.setFeeAmount(crmCfplannerMapper.queryFeeAmountTotal(cu.getInvestId(),conditions.get("userId").toString()));
		}
		response.setDatas(memberList);
		response.setValuesByPage(page);
		return response;
	}

	@Override
	public int queryCustomerMember(String userId) {
		return crmCfplannerMapper.queryCustomerMember(userId);
	}

	@Override
	public List<CrmInvestor> queryInvestorList(String userId) {
		return crmCfplannerMapper.queryInvestorList(userId);
	}

	@Override
	public PaginatorResponse<CfplannerMemberResponse> cfplannerMemberPage(Page<CfplannerMemberResponse> page,
			Map<String, Object> conditions) {
		PaginatorResponse<CfplannerMemberResponse> response = new PaginatorResponse<CfplannerMemberResponse>();
		List<CfplannerMemberResponse> memberList =crmCfplannerMapper.cfplannerMemberPage(page,conditions);
		for(CfplannerMemberResponse cfp:memberList){
			cfp.setTeamMemberCount(crmCfplannerMapper.queryLowerLevelOne(cfp.getUserId()).size()+"");
		}
		response.setDatas(memberList);
		response.setValuesByPage(page);
		return response;
	}

	@Override
	public CfplannerInvestorPersonResp queryCfplannerMemberNum(String userId) {
		return crmCfplannerMapper.queryCfplannerMemberNum(userId);
	}

	@Override
	public CfplannerMemberDetailResponse queryCfplannerDetail(String userId, String profitUserId, String thisMonth) {
		return crmCfplannerMapper.queryCfplannerDetail(userId,profitUserId,thisMonth);
	}

	@Override
	public int query2017NewCfpNumber(String fromTime, String endTime) {
		return crmCfplannerMapper.query2017NewCfpNumber(fromTime,endTime);
	}

	@Override
	public List<NewYearRewardResponse> queryForgeRewardData() {
		return crmCfplannerMapper.queryForgeRewardData();
	}

	@Override
	public int lcsNumber() {
		return crmCfplannerMapper.lcsNumber();
	}

	@Override
	public List<String> queryNewRegTop() {
		return crmCfplannerMapper.queryNewRegTop();
	}

	@Override
	public CfplannerDataResponse queryCfplannerData(CfplannerDataRequest cfplannerDataRequest) {
		CfplannerDataResponse cfplannerDataResponse = new CfplannerDataResponse();
		if(cfplannerDataRequest.getQueryDate() == null){
			cfplannerDataRequest.setQueryDate(new Date());
		}
		cfplannerDataResponse.setQueryDate(cfplannerDataRequest.getQueryDate());
		cfplannerDataResponse.setMonthIncome(crmCfplannerMapper.queryCfplannerMonthIncome(cfplannerDataRequest));
		cfplannerDataResponse.setMonthInvestAmt(crmCfplannerMapper.queryCfplannerMonthInvestAmt(cfplannerDataRequest));
				
		CrmCfplanner cfp = queryCfplannerByUserId(cfplannerDataRequest.getUserId());
		cfplannerDataResponse.setGrade(CfpJobGradeEnum.getCfpJobGradeEnumByKey(cfp.getJobGrade()).getMsg());
		cfplannerDataResponse.setCustomerMember(crmCfplannerService.queryCustomerMember(cfplannerDataRequest.getUserId()));
		cfplannerDataResponse.setTeamMember(crmCfplannerService.queryLowerLevelOne(cfplannerDataRequest.getUserId()).size());
		
		return cfplannerDataResponse;
	}

}
