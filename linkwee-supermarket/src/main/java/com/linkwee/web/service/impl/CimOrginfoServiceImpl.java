package com.linkwee.web.service.impl;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linkwee.act.redpacket.service.RedPacketService;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.base.api.SuccessResponse;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.ApplicationUtils;
import com.linkwee.core.util.SignUtils;
import com.linkwee.web.dao.CimOrginfoMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.PersonalMsgTypeEnum;
import com.linkwee.web.enums.PlatformEnum;
import com.linkwee.web.enums.RequestTypeEnums;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.ActivityList;
import com.linkwee.web.model.CimOrgMemberInfo;
import com.linkwee.web.model.CimOrgRef;
import com.linkwee.web.model.CimOrgUrl;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.CrmInvestorRecommend;
import com.linkwee.web.model.CrmOrgAcctRel;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.OrgSkipData;
import com.linkwee.web.model.SysThirdkeyConfig;
import com.linkwee.web.model.cim.CimOrgAssertData;
import com.linkwee.web.model.cim.CimOrginfoBindSelect;
import com.linkwee.web.model.cim.CimOrginfoWeb;
import com.linkwee.web.model.cim.CimProductInvestRecord;
import com.linkwee.web.model.cim.OrgInfo;
import com.linkwee.web.model.cim.PcOrgInfo;
import com.linkwee.web.model.crm.PlatformAcctManagerListResp;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.request.orgInfo.OrgPageList4Request;
import com.linkwee.web.request.orgInfo.OrgRecommendByChooseRequest;
import com.linkwee.web.request.orgInfo.OrgRecommendChooseRequest;
import com.linkwee.web.request.orgInfo.OrgThirdDataDetailRequest;
import com.linkwee.web.request.orgInfo.OrgUrlSkipParameterRequest;
import com.linkwee.web.response.InvestProductStatisticsResponse;
import com.linkwee.web.response.InvestStatisticsResponse;
import com.linkwee.web.response.orgInfo.InvestmentStrategyResponse;
import com.linkwee.web.response.orgInfo.OrgPageListResponse;
import com.linkwee.web.response.orgInfo.OrgRecommendChooseResponse;
import com.linkwee.web.response.orgInfo.OrgThirdDataDetailResponse;
import com.linkwee.web.service.ActivityListService;
import com.linkwee.web.service.CimOrgMemberinfoService;
import com.linkwee.web.service.CimOrgRefService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CimProductInvestRecordService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmOrgAcctRelService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.web.service.SysThirdkeyConfigService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.helper.ThreadpoolService;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.OpenHttpUtils;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月11日 16:27:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrginfoService")
public class CimOrginfoServiceImpl extends GenericServiceImpl<CimOrginfo, Integer> implements CimOrginfoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrginfoServiceImpl.class);
	
	@Resource
	private CimOrginfoMapper cimOrginfoMapper;
	@Resource
	private CimOrgMemberinfoService cimOrgMemberinfoService; //机构团队详情
	@Resource
	private SysThirdkeyConfigService SysThirdkeyConfigService; //第三方api接口配置
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private CrmInvestorService crmInvestorService;
	@Resource
	private CrmOrgAcctRelService crmOrgAcctRelService;
	@Resource
	private CimProductInvestRecordService cimProductInvestRecordService;
	@Resource
	private CimOrgRefService cimOrgRefService;
	@Resource
	private ActivityListService activityListService;
	@Resource
	private SysMsgService sysMsgService;
    @Resource
    private CrmCfplannerService crmCfplannerService;
    @Resource
	private RedPacketService redPacketService; //红包服务
    @Resource
    private PushMessageHelper pushMessageHelper;
    @Resource
    private SysThirdkeyConfigService sysThirdkeyConfigService;
    @Resource
    private CrmUserInfoService crmUserInfoService;
    
    @Resource
	private ConfigHelper configHelper;
    
	@Override
    public GenericDao<CimOrginfo, Integer> getDao() {
        return cimOrginfoMapper;
    }
	
	@Override
    public int insert(CimOrginfo model) {
        return cimOrginfoMapper.insertSelective(model);
    }

    @Override
    public int update(CimOrginfo model) {
        return cimOrginfoMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public int delete(Integer id) {
    	//roleMapper.deleteUserRolesByUserid(id);
        return cimOrginfoMapper.deleteByPrimaryKey(id);
    }

 

    @Override
    public CimOrginfo selectById(Integer id) {
        return cimOrginfoMapper.selectByPrimaryKey(id);
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrginfo -- 排序和模糊查询 ");
		Page<CimOrginfoWeb> page = new Page<CimOrginfoWeb>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrginfoWeb> list = this.cimOrginfoMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CimOrginfo> findRecommendOrg(Boolean isGrayUser) {
		CimOrginfo orginfo = new CimOrginfo();
		//orginfo.setRecommend(1); //仅查询优质平台
		if(isGrayUser){
			orginfo.setOrgGrayStatus(1); //灰度用户
		}
		return cimOrginfoMapper.findRecommendOrg(orginfo);
	}
	
	
	@Override
	public PaginatorResponse<CimOrginfo> queryOrgList(Page<CimOrginfo> page,Map<String,Object> conditions) {
		PaginatorResponse<CimOrginfo> paginatorResponse = new PaginatorResponse<CimOrginfo>();
		List<CimOrginfo> queryCimOrginfoList = cimOrginfoMapper.queryCimOrginfoList(page,conditions);
		String appType = (String) conditions.get("appType");
		redPacketService.patformRedPacketTag(queryCimOrginfoList, conditions.get("userId").toString()); //查询机构是否有红包
		conditions.clear();
		if(queryCimOrginfoList != null){
			for(CimOrginfo org : queryCimOrginfoList){
				org.setUsableProductNums(cimOrginfoMapper.queryOrgUseProductNums(org.getOrgNumber()) == null ? 0 : cimOrginfoMapper.queryOrgUseProductNums(org.getOrgNumber()));
				if(AppUtils.isChannelApp(appType)){
					conditions.put("appType", 1); //猎财
				}else{
					conditions.put("appType", 2); //T呗
				}
	    		conditions.put("activityPlatform", org.getName()); //平台名称
	    		List<ActivityList> orgActivityList = activityListService.queryPlatformActivities(conditions); //[] 机构活动宣传图
	    		if(orgActivityList != null){
	    			org.setOrgActivitys(orgActivityList);
	    		}
			}
		}
		
		paginatorResponse.setDatas(queryCimOrginfoList);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}
	
	
	@Override
	public OrgInfo findOrgInfo(String orgNo) {
		return cimOrginfoMapper.findOrgInfo(orgNo);
	}
	
	@Override
	public PcOrgInfo findPcOrgInfo(String orgNumber) {
		return cimOrginfoMapper.findPcOrgInfo(orgNumber);
	}

	@Override
	public PaginatorSevResp<PlatformAcctManagerListResp> queryPlatformAcctManagerPageList(Map<String, Object> query,
			Page<PlatformAcctManagerListResp> page) {
		PaginatorSevResp<PlatformAcctManagerListResp> paginatorResponse = new PaginatorSevResp<PlatformAcctManagerListResp>();
		List<PlatformAcctManagerListResp> list = cimOrginfoMapper.queryPlatformAcctManagerPageList(query, page);
		
		List<PlatformAcctManagerListResp> removeList = new ArrayList<PlatformAcctManagerListResp>();
		
		if(list != null && list.size() > 0){
			for(PlatformAcctManagerListResp bo : list) {
				if(bo != null && bo.getPlatformListIco() != null && !"".equals(bo.getPlatformListIco())){
					bo.setPlatformListIco(sysConfigService.getImageUrl(bo.getPlatformListIco()));
					bo.setOrgAdvertisement(sysConfigService.getImageUrl(bo.getOrgAdvertisement()));
				}
				//查看合作结束的平台并且用户在平台的投资记录数大于0的平台
				if((bo.getStatus() == null || bo.getStatus() == 0) && bo.getInvestCount() == 0){
					removeList.add(bo);
				}
			}
		}
		list.removeAll(removeList); //集中移除合作结束并且投资记录数为0的平台
		paginatorResponse.setDatas(list);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public int queryOrgAccountCount(String userId) {
		return cimOrginfoMapper.queryOrgAccountCount(userId);
	}

	@Override
	public void bindOrgAcct(CrmOrgAcctRel  bo) {
		cimOrginfoMapper.insertOrgAcctRel(bo);
	}

	@Override
	public CimOrgUrl selectOrgUrlByOrgNumber(String orgNumber) {
		return cimOrginfoMapper.selectOrgUrlByOrgNumber(orgNumber);
	}

	@Override
	public boolean isBindOrgAcct(String userId, String platFromNumber) {
		if(cimOrginfoMapper.queryIsBindOrgAcct(userId, platFromNumber) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public String queryThirdOrgAccountByUserId(String userId, String platFromNumber) {
		return cimOrginfoMapper.queryThirdOrgAccountByUserId(userId, platFromNumber);
	}

	@Override
	public int queryOrgCount() {
		return cimOrginfoMapper.queryOrgCount();
	}

	@Override
	public CimOrginfoWeb selectOrgInfoByOrgNumber(String orgNumber) {
		return cimOrginfoMapper.selectOrgInfoByOrgNumber(orgNumber);
	}

	@Override
	public void insertOrgFullInfo(CimOrginfoWeb cimOrginfo, String createUser) {
		LOGGER.debug("新增机构时 插入数据到机构表:cimOrginfo = {}", JSON.toJSONString(cimOrginfo));
		cimOrginfoMapper.insertOrginfo(cimOrginfo);
		LOGGER.debug("插入数据到机构表成功！ ");
		if(!cimOrginfo.getTeams().isEmpty() && cimOrginfo.getTeams().size() > 0){
			for(CimOrgMemberInfo team :cimOrginfo.getTeams()){
				team.setOrgId(cimOrginfo.getId()); //机构主键
			}
			LOGGER.debug("新增机构时 批量插入团队信息到团队信息表:teams = {}", JSON.toJSONString(cimOrginfo.getTeams()));
			cimOrgMemberinfoService.insertBatch(cimOrginfo.getTeams());
			LOGGER.debug("插入数据到团队信息表成功！ ");
		}
		
		SysThirdkeyConfig thirdkeyConfig = new SysThirdkeyConfig();
		//第三方api接口配置表生成公钥和私钥
		thirdkeyConfig.setOrgNumber(cimOrginfo.getOrgNumber()); //机构编码
		thirdkeyConfig.setOrgKey(ApplicationUtils.randomUUID(true, true)); //生成公钥
		thirdkeyConfig.setOrgSecret(ApplicationUtils.randomUUID(true, true)); //生成私钥
		thirdkeyConfig.setOrgStatus("y"); //y：开启，n：关闭
		thirdkeyConfig.setCreateTime(new Date());
		thirdkeyConfig.setArchive(0); //'逻辑删除：0:可用，1：删除'
		thirdkeyConfig.setCreateUser(createUser); //创建人
		LOGGER.debug("新增机构时 插入数据到第三方api接口配置表:thirdkeyConfig = {} ", JSON.toJSONString(thirdkeyConfig));
		SysThirdkeyConfigService.insert(thirdkeyConfig); //插入
		LOGGER.debug("插入数据到第三方api接口配置表成功！ ");
	}
	
	@Override
	public void updateOrgFullInfo(CimOrginfoWeb cimOrginfo) {
		LOGGER.debug("更新机构 更新数据到机构表:cimOrginfo = {}", JSON.toJSONString(cimOrginfo));
		cimOrginfoMapper.updateByOrgNumber(cimOrginfo);
		LOGGER.debug("更新机构表数据成功！ ");
		List<CimOrgMemberInfo> teams = cimOrginfo.getTeams(); //获取团队信息
		//判断团队id是否null
		List<CimOrgMemberInfo> newTeams = new ArrayList<CimOrgMemberInfo>(); //新增加的团队成员信息
		
		if(teams != null){
			for(CimOrgMemberInfo newTeam : teams){
				if(newTeam.getTid() == null){
					newTeams.add(newTeam); //保存id为null的成员信息 执行批量新增
				}
			}
			
			if(newTeams.size() > 0){
				teams.removeAll(newTeams); //从集合中删除id为null的团队成员
			}
			
			/**
			 * 执行批量更新操作
			 */
			if(!teams.isEmpty() && teams.size() > 0){
				LOGGER.debug("更新机构 批量更新团队信息到团队信息表:teams = {}", JSON.toJSONString(teams));
				cimOrgMemberinfoService.updateBatchTeam(teams);
				LOGGER.debug("更新机构 批量更新数据到团队信息表成功！ ");
			}
			
		}
		
		/**
		 * 执行批量插入操作
		 */
		if(!newTeams.isEmpty() && newTeams.size() > 0){
			for(CimOrgMemberInfo team : newTeams){
				team.setOrgId(cimOrginfo.getId()); //设置机构主键到团队信息表
			}
			LOGGER.debug("更新机构 批量新增团队信息到团队信息表:newTeams = {}", JSON.toJSONString(newTeams));
			cimOrgMemberinfoService.insertBatch(newTeams);
			LOGGER.debug("更新机构 批量新增数据到团队信息表成功！ ");
		}
	}

	@Override
	public String queryInvestorLoginId(String userId) {
		return cimOrginfoMapper.queryInvestorLoginId(userId);
	}

	@Override
	public String queryUserIdByThirdOrgAccount(String thirdOrgAccount,String platFromNumber) {
		return cimOrginfoMapper.queryUserIdByThirdOrgAccount(thirdOrgAccount, platFromNumber);
	}

	@Override
	public int updateByOrgNumber(CimOrginfoWeb o) {
		return cimOrginfoMapper.updateByOrgNumber(o);
	}

	@Override
	public int insertOrginfo(CimOrginfoWeb o) {
		return cimOrginfoMapper.insertOrginfo(o);
	}

	@Override
	public List<CimOrginfoBindSelect> queryOrgByStatus(int status) {
		return cimOrginfoMapper.queryOrgByStatus(status);
	}

	@Override
	public void updateOrgAcctRelInvested(String userId, String productOrgId) {
		cimOrginfoMapper.updateOrgAcctRelInvested(userId,  productOrgId);
		
	}

	@Override
	public CimOrginfoWeb findWebOrgInfo(String orgNumber) {
		return cimOrginfoMapper.findWebOrgInfo(orgNumber);
	}

	@Override
	public boolean isOrgNewUser(String userId, String orgNumber) {
		return cimOrginfoMapper.isOrgNewUser(userId, orgNumber);
	}

	@Override
	public PaginatorResponse<CimOrginfo> findPcRecommendOrg(Page<CimOrginfo> page,Boolean isGrayUser) {
		PaginatorResponse<CimOrginfo> paginatorResponse = new PaginatorResponse<CimOrginfo>();
		List<CimOrginfo> queryCimOrginfoList = cimOrginfoMapper.findPcRecommendOrg(page,isGrayUser);
		paginatorResponse.setDatas(queryCimOrginfoList);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public List<CimOrginfo> queryLatestOrg(Boolean isGrayUser) {
		return cimOrginfoMapper.queryLatestOrg(isGrayUser);
	}

	@Override
	public CimOrginfoWeb queryOrgFeeInfo(String orgNumber) {
		return cimOrginfoMapper.queryOrgFeeInfo(orgNumber);
	}

	@Override
	public CimOrginfo queryCimOrginfoByProductid(String productId) {
		return cimOrginfoMapper.queryCimOrginfoByProductid(productId);
	}
	
	@Override
	public OrgRecommendChooseResponse recommendChooseList(AppRequestHead head,OrgRecommendChooseRequest orgRecommendChooseRequest) {
		
		OrgRecommendChooseResponse orgRecommendChooseResponse = new OrgRecommendChooseResponse();
		List<CrmInvestorRecommend> allFeeList = new ArrayList<CrmInvestorRecommend>();
		List<CrmInvestorRecommend> haveFeeList = new ArrayList<CrmInvestorRecommend>();
		List<CrmInvestorRecommend> notHaveFeeList = new ArrayList<CrmInvestorRecommend>();
		
		/**
		 * 获取理财师id
		 */
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		orgRecommendChooseRequest.setUserId(userId);
		/**
		 * 根据客户条件筛选理财师所有客户 含是否推荐该机构信息
		 */
		allFeeList = crmInvestorService.selectCrmInvestorRecommendOrg(orgRecommendChooseRequest);
		/**
		 * 查询机构详情 
		 */
		OrgInfo orgInfoResponse = findOrgInfo(orgRecommendChooseRequest.getOrgCode());
		orgRecommendChooseResponse.setOrgIsstaticproduct(orgInfoResponse.getOrgIsstaticproduct());//是否对接机构
		/**
		 * 推荐
		 * 1：未进行对接    直接列出所有用户
		 * 2：已技术对接
		 * 2.1 有佣金的用户   2.11 CPS  我们带过去的新用户            2.12  CPA 我们带过去的新用户未投资
		 * 2.2 无佣金的用户   2.21 CPS  不是我们带过去的用户       2.22  CPA 不是我们带过去用户 或者是我们带过去的用户（已投资）
		 */
			
		if(orgInfoResponse.getOrgIsstaticproduct() == 0){//已技术对接
			
			/** 收费类型   1:cpa-按投资人数量进行收费	2:cps-按投资金额进行收费 */
			Integer orgFeeType = orgInfoResponse.getOrgFeeType();
			
			/**
			 * 遍历所有客户进行是否拥有佣金分类
			 */
			for(CrmInvestorRecommend crmInvestorRecommend:allFeeList){
				CrmOrgAcctRel crmOrgAcctRel =  new CrmOrgAcctRel();
				crmOrgAcctRel.setOrgNumber(orgRecommendChooseRequest.getOrgCode());
				crmOrgAcctRel.setUserId(crmInvestorRecommend.getUserId());
				crmOrgAcctRel = crmOrgAcctRelService.selectOne(crmOrgAcctRel);
				if(crmOrgAcctRel == null){//未绑定平台用户
					haveFeeList.add(crmInvestorRecommend);
				} else if(crmOrgAcctRel.getIsNewUser() == 1){//平台新用户
					if(orgFeeType == 1){//CPA
						/**
						 * 判断该用户是否投资过该机构,若投资过,则没有佣金，否则有佣金
						 */
						CimProductInvestRecord cimProductInvestRecord = new CimProductInvestRecord();
						cimProductInvestRecord.setUserId(crmInvestorRecommend.getUserId());
						cimProductInvestRecord.setPlatfrom(orgRecommendChooseRequest.getOrgCode());
						List<CimProductInvestRecord>  cimProductInvestRecordList= cimProductInvestRecordService.selectListByCondition(cimProductInvestRecord);
						if(cimProductInvestRecordList != null && cimProductInvestRecordList.size() > 0){
							notHaveFeeList.add(crmInvestorRecommend);
						} else {
							haveFeeList.add(crmInvestorRecommend);
						}
					} else if(orgFeeType == 2){//CPS
						haveFeeList.add(crmInvestorRecommend);
					}
				} else {//平台老用户
					notHaveFeeList.add(crmInvestorRecommend);
				}
			}
		}
		orgRecommendChooseResponse.setAllFeeList(allFeeList);
		orgRecommendChooseResponse.setHaveFeeList(haveFeeList);
		orgRecommendChooseResponse.setNotHaveFeeList(notHaveFeeList);
		
		return orgRecommendChooseResponse;
	}
	@Override
	public PaginatorResponse<CimOrginfo> queryPlannerRecommendPlatfrom(Page<CimOrginfo> page, String investUserId, String saleUserId) {
		PaginatorResponse<CimOrginfo> paginatorResponse = new PaginatorResponse<CimOrginfo>();
		List<CimOrginfo> queryCimOrginfoList = cimOrginfoMapper.queryPlannerRecommendPlatfrom(page,investUserId,saleUserId);
		redPacketService.patformRedPacketTag(queryCimOrginfoList,investUserId); //查询机构是否有红包
		paginatorResponse.setDatas(queryCimOrginfoList);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public void recommendByChoose(AppRequestHead head,OrgRecommendByChooseRequest orgRecommendByChooseRequest) {
		/**
		 * 获取理财师id
		 */
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		final String orgCode = orgRecommendByChooseRequest.getOrgCode();
		String userIdString = orgRecommendByChooseRequest.getUserIdString();
		
		//批量删除原有推荐信息  
		CimOrgRef cimOrgRefNew = new CimOrgRef();
		cimOrgRefNew.setOrgNumber(orgRecommendByChooseRequest.getOrgCode());
		cimOrgRefNew.setSaleUserId(userId);
		cimOrgRefService.deleteByCondition(cimOrgRefNew);
		
		
		//推送个人消息列表
		final  List<SysMsg> msgList = Lists.newArrayList();
		final  List<String> userIds = Lists.newArrayList();
		//理财师信息
		CrmCfplanner crmCfplanner =  crmCfplannerService.queryCfplannerByInvestor(userId);
		OrgInfo orgInfo = findOrgInfo(orgRecommendByChooseRequest.getOrgCode());

		String contentTemp = configHelper.getValue(SysConfigConstant.PUSHMESSAGE_RECOMEND_PLATFORM_INV);
		final String content = contentTemp == null ? null :String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_RECOMEND_PLATFORM_INV),crmCfplanner == null ? "" : crmCfplanner.getUserName() == null ? "" :crmCfplanner.getUserName() +crmCfplanner.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"),orgInfo == null ? "" :orgInfo.getOrgName());

		//插入机构推荐信息
		if(StringUtils.isNotBlank(userIdString)){
			String[] investorArray = orgRecommendByChooseRequest.getUserIdString().split(",");
			for(String investorUserId : investorArray){			
				//如果该投资人不存在  直接跳出当前循环进入下一次循环
				CrmInvestor crmInvestor = new CrmInvestor();
				crmInvestor.setUserId(investorUserId);
				crmInvestor = crmInvestorService.selectOne(crmInvestor);
				if(crmInvestor == null) continue;
				
				CimOrgRef cimOrgRef = new CimOrgRef();
				cimOrgRef.setOrgNumber(orgCode);
				cimOrgRef.setSaleUserId(userId);
				cimOrgRef.setInvestorUserId(investorUserId);
				cimOrgRef.setRecommendTime(new Date());
				cimOrgRef.setRemarks("理财师选择推荐机构");
				cimOrgRefService.insert(cimOrgRef);
				//构建个人消息对象
				SysMsg msg = new SysMsg();				
				msg.setContent(content);
				msg.setStatus(0);// 发布
				msg.setUserNumber(investorUserId);
				msg.setReadStatus(0);// 未读
				msg.setAppType(AppTypeEnum.INVESTOR.getKey());
				msg.setTypeName(PersonalMsgTypeEnum.PROJECTINVEST_INV.getValue());
				msg.setStartTime(new Date());
				msg.setCrtTime(new Date());
				msg.setModifyTime(new Date());
				msg.setLinkBtnTxt("立即查看");
				msg.setLinkUrlKey(PersonalMsgTypeEnum.RECOMMEND_PLATFORM.getMsg());
				msgList.add(msg);
				//
				userIds.add(investorUserId);
			}	
		}
		//给推荐成功的投资客户发个人消息
		if(content != null && msgList.size() > 0){
			ThreadpoolService.execute(new Runnable() {
				@Override
				public void run() {
					sysMsgService.addMsgs(msgList);
					Map<String,Object> urlparam = Maps.newHashMap();
					urlparam.put("orgNo",orgCode);
					pushMessageHelper.BatchSinglePush(AppTypeEnum.INVESTOR, SmsTypeEnum.PLATFORMDTL_INC, userIds, "平台推荐", content, urlparam, false, PersonalMsgTypeEnum.RECOMMEND_PLATFORM);
				}
			});
		}
	}

	@Override
	public PaginatorResponse<CimOrginfo> queryPcPlannerRecommendPlatfrom(Page<CimOrginfo> page, String investUserId, String saleUserId) {
		PaginatorResponse<CimOrginfo> paginatorResponse = new PaginatorResponse<CimOrginfo>();
		List<CimOrginfo> queryPcCimOrginfoList = cimOrginfoMapper.queryPcPlannerRecommendPlatfrom(page,investUserId,saleUserId);
		redPacketService.patformRedPacketTag(queryPcCimOrginfoList,investUserId); //查询机构是否有红包
		paginatorResponse.setDatas(queryPcCimOrginfoList);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public BigDecimal queryOrgFeeRatio(String orgNumber) {
		return cimOrginfoMapper.queryOrgFeeRatio(orgNumber);
	}

	@Override
	public InvestmentStrategyResponse queryInvestmentStrategy(String orgCode) {
		return cimOrginfoMapper.queryInvestmentStrategy(orgCode);
	}

	@Override
	public String queryOrgSecurity(String orgNumber) {
		return cimOrginfoMapper.queryOrgSecurity(orgNumber);
	}

	@Override
	public List<CimOrginfo> selectListByGrade(String securityLevel,Boolean ifHaveGray) {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("securityLevel", securityLevel);
		conditions.put("ifHaveGray", ifHaveGray);
		return cimOrginfoMapper.selectListByGrade(conditions);
	}

	@Override
	public BigDecimal queryOrgDiffFeeRatio(String orgNumber) {
		return cimOrginfoMapper.queryOrgDiffFeeRatio(orgNumber);
	}

	@Override
	public CimOrginfo queryOrgInfoByOrgNumber(String orgNumber) {
		CimOrginfo cimOrginfo = new CimOrginfo();
		cimOrginfo.setOrgNumber(orgNumber);
		cimOrginfo = cimOrginfoMapper.selectOneByCondition(cimOrginfo);
		return cimOrginfo;
	}

	@Override
	public Integer queryOrgUseProductNums(String orgNumber) {
		return cimOrginfoMapper.queryOrgUseProductNums(orgNumber);
	}

	@Override
	public List<PlatformAcctManagerListResp> bindOrgAccountCount(Map<String, Object> map) {
		return cimOrginfoMapper.bindOrgAccountCount(map);
	}

	@Override
	public int unBindOrgAccountCount(Map<String, Object> map) {
		return cimOrginfoMapper.unBindOrgAccountCount(map);
	}

	@Override
	public List<CimOrginfo> queryChoicenessPlatfrom(Boolean isGrayUser,String userId,String appType) {
		Set<CimOrginfo> set = new LinkedHashSet<CimOrginfo>();
		List<CimOrginfo> shortProductMajorPlatfrom = cimOrginfoMapper.queryShortProductMajorPlatfrom(isGrayUser);
		List<CimOrginfo> mediumAndLongProductMajorPlatfrom = cimOrginfoMapper.queryMediumAndLongProductMajorPlatfrom(isGrayUser);
		List<CimOrginfo> highYieldProductPlatfrom = cimOrginfoMapper.queryHighYieldProductPlatfrom(isGrayUser);
		
		//shortProductMajorPlatfrom.removeAll(mediumAndLongProductMajorPlatfrom);
		if(shortProductMajorPlatfrom != null){
			set.addAll(shortProductMajorPlatfrom);
		}
		if(mediumAndLongProductMajorPlatfrom != null){
			set.addAll(mediumAndLongProductMajorPlatfrom);
		}
		if(highYieldProductPlatfrom != null){
			set.addAll(highYieldProductPlatfrom);
		}
		
		List<CimOrginfo> newList = new ArrayList<CimOrginfo>(set); //去重后的机构
		
		redPacketService.patformRedPacketTag(newList,userId); //查询机构是否有红包
		
		if(newList != null){
			Map<String, Object> map = new HashMap<String, Object>();
			for(CimOrginfo org : newList){
				org.setUsableProductNums(cimOrginfoMapper.queryOrgUseProductNums(org.getOrgNumber()) == null ? 0 : cimOrginfoMapper.queryOrgUseProductNums(org.getOrgNumber()));
				if(AppUtils.isChannelApp(appType)){
					map.put("appType", 1); //猎财
				}else{
					map.put("appType", 2); //T呗
				}
				map.put("activityPlatform", org.getName()); //平台名称
	    		List<ActivityList> orgActivityList = activityListService.queryPlatformActivities(map); //[] 机构活动宣传图
	    		if(orgActivityList != null){
	    			org.setOrgActivitys(orgActivityList);
	    		}
			}
		}
		return newList;
	}

	@Override
	public List<String> selectFeeCalFeeType() {
		return cimOrginfoMapper.selectFeeCalFeeType();
	}

	@Override
	public PaginatorResponse<OrgPageListResponse> queryOrgPageList4(AppRequestHead head,OrgPageList4Request orgPageList4Request) {
		PaginatorResponse<OrgPageListResponse> paginatorResponse = new PaginatorResponse<OrgPageListResponse>();
		Page<OrgPageListResponse> page = new Page<OrgPageListResponse>(orgPageList4Request.getPageIndex(), orgPageList4Request.getPageSize());
		List<OrgPageListResponse> orgPageListResponses = cimOrginfoMapper.queryOrgPageList4(orgPageList4Request,page);
		paginatorResponse.setDatas(orgPageListResponses);
		paginatorResponse.setValuesByPage(page);
		//针对玖富轻舟产品对于4.5.4版本以下用户都不显示
		String ver = head.getAppVersion();
		if("android".equals(head.getAppClient())||"ios".equals(head.getAppClient())){
			if((ver!=null&&ver.length()>=5&&Integer.parseInt(ver.substring(0,1))<=4
					&&Integer.parseInt(ver.substring(2,3))<=5
					&&Integer.parseInt(ver.substring(4,5))<=4)||head.getAppKind().indexOf("investor")!=-1){
				List<OrgPageListResponse> list = new ArrayList<OrgPageListResponse>();
				for (OrgPageListResponse pro : orgPageListResponses) {
					if(!"OPEN_JIUFUQINGZHOU_WEB".equals(pro.getOrgNumber())){
						list.add(pro);
					}
				}
				paginatorResponse.setDatas(list);
			}
		}
		return paginatorResponse;
	}

	@Override
	public Map<String, String> investStatistics(String userId, Boolean isGrayUser) {
		String totalProfit = cimOrginfoMapper.totalProfit(userId,isGrayUser);
		Map<String, String> resultMap = cimOrginfoMapper.investStatistics(userId,isGrayUser);
		resultMap.put("totalProfit", totalProfit);
		return resultMap;
	}

	@Override
	public List<InvestStatisticsResponse> platformInvestStatistics(String userId,Boolean isGrayUser) {
		return cimOrginfoMapper.platformInvestStatistics(userId,isGrayUser);
	}

	@Override
	public List<InvestProductStatisticsResponse> investProductStatistics(String userId, Boolean isGrayUser) {
		return cimOrginfoMapper.investProductStatistics(userId,isGrayUser);
	}

	@Override
	public OrgThirdDataDetailResponse selectOrgThirdDataDetail(OrgThirdDataDetailRequest orgThirdDataDetailRequest) {
		
		OrgThirdDataDetailResponse orgThirdDataDetailResponse = new OrgThirdDataDetailResponse();
		Gson gson = new Gson();
		
		//查询用户信息
		CrmUserInfo user = crmUserInfoService.queryUserInfoByUserId(orgThirdDataDetailRequest.getUserId());
		SysThirdkeyConfig sysThirdkeyConfig = sysThirdkeyConfigService.selectOneByOrgNumber(orgThirdDataDetailRequest.getOrgNo());
		
		CimOrginfo cimOrginfo = selectCimOrginfoByOrgNumber(orgThirdDataDetailRequest.getOrgNo());
		orgThirdDataDetailResponse.setCimOrginfo(cimOrginfo);
		orgThirdDataDetailResponse.setProductCount(cimOrginfoMapper.queryOrgUseProductNums(orgThirdDataDetailRequest.getOrgNo()));
		
		//必需选项
		List<OrgSkipData> needList = new ArrayList<OrgSkipData>();
		if(StringUtils.isNotBlank(cimOrginfo.getOrgUserbalanceUrl())){		
			Map<String , String> param = new HashMap<String , String>();
			param.put("orgAccount", crmOrgAcctRelService.getOrgAccount(user.getUserId(), orgThirdDataDetailRequest.getOrgNo()));
			param.put("mobile", user.getMobile());
			String returnMsg = OpenHttpUtils.httpRequest(sysThirdkeyConfig, RequestTypeEnums.POST, cimOrginfo.getOrgUserbalanceUrl(), param);
			LOGGER.info("查询客户在第三方账户信息返回   returnMsg={} param={}",returnMsg,JSONObject.toJSONString(param));
			try {
				Type quickType = new TypeToken<SuccessResponse<CimOrgAssertData>>(){}.getType();
				SuccessResponse<CimOrgAssertData> successResponse = gson.fromJson(returnMsg,quickType);
				CimOrgAssertData cimOrgAssertData = successResponse.getData();
				if("0".equals(successResponse.getCode()) && cimOrgAssertData != null){
					
					//总金额
					orgThirdDataDetailResponse.setTotalAssets(cimOrgAssertData.getTotalAssets());
					
					OrgSkipData orgSkipData1 =  new OrgSkipData();
					orgSkipData1.setName("可用余额");
					orgSkipData1.setData(cimOrgAssertData.getTotalAmount());
					orgSkipData1.setUnit("元");
					orgSkipData1.setSkipUrl(cimOrginfo.getOrgUsercenterUrl());
					needList.add(orgSkipData1);
					
					OrgSkipData orgSkipData5 =  new OrgSkipData();
					orgSkipData5.setName("待收本息");
					orgSkipData5.setData(cimOrgAssertData.getOnPrincipalAndinterest());
					orgSkipData5.setUnit("元");
					orgSkipData5.setSkipUrl(cimOrginfo.getOrgUsercenterUrl());
					needList.add(orgSkipData5);
					
					OrgSkipData orgSkipData2 =  new OrgSkipData();
					orgSkipData2.setName("在途金额");
					orgSkipData2.setData(cimOrgAssertData.getInInvestmentAmount());
					orgSkipData2.setUnit("元");
					orgSkipData2.setSkipUrl(cimOrginfo.getOrgUsercenterUrl());
					needList.add(orgSkipData2);
					
					OrgSkipData orgSkipData4 =  new OrgSkipData();
					orgSkipData4.setName("累计收益");
					orgSkipData4.setData(cimOrgAssertData.getAccumulatedEarnings());
					orgSkipData4.setUnit("元");
					orgSkipData4.setSkipUrl(cimOrginfo.getOrgUsercenterUrl());
					needList.add(orgSkipData4);
					
				}
			} catch (Exception e) {
				LOGGER.error("查询客户在第三方账户信息异常 ",e);
			}
		}
		orgThirdDataDetailResponse.setNeedList(needList);
		
		//非必需项
		List<OrgSkipData> chooseList = new ArrayList<OrgSkipData>();
		if(StringUtils.isNotBlank(cimOrginfo.getOrgUrlSkipCoupon())){		
			OrgSkipData orgSkipData =  new OrgSkipData();
			orgSkipData.setName("优惠券");
			orgSkipData.setSkipUrl(cimOrginfo.getOrgUrlSkipCoupon());
			chooseList.add(orgSkipData);
		}
		if(StringUtils.isNotBlank(cimOrginfo.getOrgUrlSkipRedpackage())){		
			OrgSkipData orgSkipData =  new OrgSkipData();
			orgSkipData.setName("红包");
			orgSkipData.setSkipUrl(cimOrginfo.getOrgUrlSkipRedpackage());
			chooseList.add(orgSkipData);
		}
		if(StringUtils.isNotBlank(cimOrginfo.getOrgUrlSkipConcessionScore())){		
			OrgSkipData orgSkipData =  new OrgSkipData();
			orgSkipData.setName("积分");
			orgSkipData.setSkipUrl(cimOrginfo.getOrgUrlSkipConcessionScore());
			chooseList.add(orgSkipData);
		}
		if(StringUtils.isNotBlank(cimOrginfo.getOrgUrlSkipRechargeRecord())){		
			OrgSkipData orgSkipData =  new OrgSkipData();
			orgSkipData.setName("充值记录");
			orgSkipData.setSkipUrl(cimOrginfo.getOrgUrlSkipRechargeRecord());
			chooseList.add(orgSkipData);
		}
		if(StringUtils.isNotBlank(cimOrginfo.getOrgUrlSkipWithdrawDepositRecord())){		
			OrgSkipData orgSkipData =  new OrgSkipData();
			orgSkipData.setName("提现记录");
			orgSkipData.setSkipUrl(cimOrginfo.getOrgUrlSkipWithdrawDepositRecord());
			chooseList.add(orgSkipData);
		}
		if(StringUtils.isNotBlank(cimOrginfo.getOrgUrlSkipSafeCenter())){		
			OrgSkipData orgSkipData =  new OrgSkipData();
			orgSkipData.setName("安全中心详情");
			orgSkipData.setSkipUrl(cimOrginfo.getOrgUrlSkipSafeCenter());
			chooseList.add(orgSkipData);
		}
		if(StringUtils.isNotBlank(cimOrginfo.getOrgUrlSkipActivity())){		
			OrgSkipData orgSkipData =  new OrgSkipData();
			orgSkipData.setName("活动列表");
			orgSkipData.setSkipUrl(cimOrginfo.getOrgUrlSkipActivity());
			chooseList.add(orgSkipData);
		}
		if(StringUtils.isNotBlank(cimOrginfo.getOrgUrlSkipBankcardRechargeLimit())){		
			OrgSkipData orgSkipData =  new OrgSkipData();
			orgSkipData.setName("平台银行充值");
			orgSkipData.setSkipUrl(cimOrginfo.getOrgUrlSkipBankcardRechargeLimit());
			chooseList.add(orgSkipData);
		}
		if(StringUtils.isNotBlank(cimOrginfo.getOrgUrlSkipOfficialNotice())){		
			OrgSkipData orgSkipData =  new OrgSkipData();
			orgSkipData.setName("官方公告");
			orgSkipData.setSkipUrl(cimOrginfo.getOrgUrlSkipOfficialNotice());
			chooseList.add(orgSkipData);
		}
		if(StringUtils.isNotBlank(cimOrginfo.getOrgUrlSkipProductBuyCompact())){		
			OrgSkipData orgSkipData =  new OrgSkipData();
			orgSkipData.setName("购买后电子合同");
			orgSkipData.setSkipUrl(cimOrginfo.getOrgUrlSkipProductBuyCompact());
			chooseList.add(orgSkipData);
		}
		
		orgThirdDataDetailResponse.setChooseList(chooseList);
		return orgThirdDataDetailResponse;
	}

	@Override
	public Map<String, String> getOrgUrlSkipParameter(OrgUrlSkipParameterRequest orgUrlSkipParameterRequest,AppRequestHead head) {
		
		//查询第三方api接口配置
		SysThirdkeyConfig sysThirdkeyConfig = new SysThirdkeyConfig();
		sysThirdkeyConfig.setOrgNumber(orgUrlSkipParameterRequest.getOrgNo());
		sysThirdkeyConfig = sysThirdkeyConfigService.selectOne(sysThirdkeyConfig);
		
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		String thirdOrgAccount = queryThirdOrgAccountByUserId(userId,orgUrlSkipParameterRequest.getOrgNo());//通过userid和机构编码 查询用户的 第三方机构用户账号
		
		Map<String,String> paramsMap = new HashMap<String,String>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		paramsMap.put("orgNumber", sysThirdkeyConfig.getOrgNumber());//机构编码
		paramsMap.put("orgKey",sysThirdkeyConfig.getOrgKey());//机构公钥
		paramsMap.put("timestamp",simpleDateFormat.format(new Date()));
		paramsMap.put("orgAccount",thirdOrgAccount);//第三方机构用户账号
		//判断是否PC端
    	if(PlatformEnum.WEB == AppUtils.getPlatform(head.getOrgNumber())){
    		paramsMap.put("requestFrom","web");//PC端
    	}else{
    		paramsMap.put("requestFrom","wap");//移动端
    	}
    	
    	//产品跳转
    	if(orgUrlSkipParameterRequest.getUrlSkipType() == 1 && StringUtils.isNotBlank(orgUrlSkipParameterRequest.getThirdProductId())){
			//获取投资者最后一次登录id(交易流水号)
			String txId = queryInvestorLoginId(userId);
			if(txId == null){
				txId = "NONE";
			}
			paramsMap.put("thirdProductId",orgUrlSkipParameterRequest.getThirdProductId());//第三方机构产品id
			paramsMap.put("txId",txId);//投资者最后一次登录id(交易流水号)
    	}
    	
        String sign = SignUtils.sign(paramsMap,sysThirdkeyConfig.getOrgSecret());//机构私钥
        paramsMap.put("sign", sign); //生成签名
        
		/**
		 * 小赢科技 账户单独处理 urlecode编码
		 */
		if("OPEN_XIAOYINGLICAI_WEB".equals(sysThirdkeyConfig.getOrgNumber())){
			try {		
				paramsMap.put("orgAccount",URLEncoder.encode(thirdOrgAccount, "UTF-8"));//第三方机构用户账号
			} catch (Exception e) {
				LOGGER.error("小赢科技 账户单独处理 urlecode编码异常 paramsMap={}",JSONObject.toJSONString(paramsMap));
			}
		}
		
		/**
		 * 兼容老版本  返回请求的跳转URL
		 */
		if(orgUrlSkipParameterRequest.getUrlSkipType() != 0){
			CimOrgUrl orgurl = selectOrgUrlByOrgNumber(orgUrlSkipParameterRequest.getOrgNo());
			if(orgUrlSkipParameterRequest.getUrlSkipType() == 1 && StringUtils.isNotBlank(orgUrlSkipParameterRequest.getThirdProductId())){//产品跳转
				paramsMap.put("orgProductUrl", orgurl.getOrgProductUrl());
			} else if(orgUrlSkipParameterRequest.getUrlSkipType() == 2){//个人中心跳转
				paramsMap.put("orgUsercenterUrl", orgurl.getOrgUsercenterUrl());
			}
		}
		return paramsMap;
	}

	@Override
	public CimOrginfo selectCimOrginfoByOrgNumber(String orgNumber) {
		CimOrginfo cimOrginfo = new CimOrginfo();
		cimOrginfo.setOrgNumber(orgNumber);
		return cimOrginfoMapper.selectOneByCondition(cimOrginfo);
	}
}
