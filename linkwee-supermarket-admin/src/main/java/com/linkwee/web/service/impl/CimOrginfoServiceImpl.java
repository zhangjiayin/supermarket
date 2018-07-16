package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisCluster;

import com.alibaba.fastjson.JSON;
import com.linkwee.act.redpacket.service.ActRepaymentRedpacketPoolService;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.ApplicationUtils;
import com.linkwee.core.util.EnumUtils;
import com.linkwee.web.dao.CimOrginfoMapper;
import com.linkwee.web.enums.cim.OrgGradeEnum;
import com.linkwee.web.model.CimOrgFeeTimetask;
import com.linkwee.web.model.CimOrgMemberInfo;
import com.linkwee.web.model.CimOrgRiskFresGrade;
import com.linkwee.web.model.CimOrgRiskManageSynthesize;
import com.linkwee.web.model.CimOrgUrl;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.CimProduct;
import com.linkwee.web.model.CrmOrgAcctRel;
import com.linkwee.web.model.SysThirdkeyConfig;
import com.linkwee.web.model.cim.CimOrginfoBindSelect;
import com.linkwee.web.model.cim.CimOrginfoWeb;
import com.linkwee.web.model.cim.RiskHandleSave;
import com.linkwee.web.model.crm.PlatformAcctManagerListResp;
import com.linkwee.web.response.orgInfo.OrgInfoResponse;
import com.linkwee.web.service.CimOrgFeeTimetaskService;
import com.linkwee.web.service.CimOrgFresGradeTypeService;
import com.linkwee.web.service.CimOrgMemberinfoService;
import com.linkwee.web.service.CimOrgPictureService;
import com.linkwee.web.service.CimOrgRiskFresGradeService;
import com.linkwee.web.service.CimOrgRiskManageSynthesizeService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CimProductService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysThirdkeyConfigService;
import com.linkwee.xoss.rbac.PermissionSign;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @author： Mignet
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
	private SysThirdkeyConfigService sysThirdkeyConfigService; //第三方api接口配置
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private CimProductService cimProductService; //产品服务
	@Resource
	private CimOrgFeeTimetaskService cimOrgFeeTimetaskService;  //机构佣金定时设置服务
	@Resource
	private CimOrgPictureService cimOrgPictureService; //图片服务
	@Resource
	private JedisCluster jedisCluster;
	@Resource
	private ActRepaymentRedpacketPoolService  actRepaymentRedpacketPoolService;
	@Resource
	private CimOrgRiskManageSynthesizeService cimOrgRiskManageSynthesizeService;
	@Resource
	private CimOrgFresGradeTypeService cimOrgFresGradeTypeService;
	@Resource
	private CimOrgRiskFresGradeService cimOrgRiskFresGradeService;
	
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
		
		Subject currentUser = SecurityUtils.getSubject();
		String detailEditPermission = "0";
		String feeModelEditPermission = "0";
		String feeEditPermission = "0";
		if(currentUser.isPermitted(PermissionSign.ORG_DETAIL_EDIT)) {
			detailEditPermission = "1";
		}
		if(currentUser.isPermitted(PermissionSign.ORG_FEE_MODEL_EDIT)) {
			feeModelEditPermission = "1";
		}
		if(currentUser.isPermitted(PermissionSign.ORG_FEE_EDIT)) {
			feeEditPermission = "1";
		}
		for(CimOrginfoWeb temp : list){			
			temp.setDetailEditPermission(detailEditPermission);			
			temp.setFeeModelEditPermission(feeModelEditPermission);
			temp.setFeeEditPermission(feeEditPermission);			
		}
			
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CimOrginfo> findRecommendOrg() {
		CimOrginfo condit = new CimOrginfo();
		condit.setRecommend(1); //仅查询优质平台
		return cimOrginfoMapper.findRecommendOrg(condit);
	}
	
	
	@Override
	public PaginatorResponse<CimOrginfo> queryOrgList(Page<CimOrginfo> page,Map<String,Object> conditions) {
		PaginatorResponse<CimOrginfo> paginatorResponse = new PaginatorResponse<CimOrginfo>();
		List<CimOrginfo> queryCimOrginfoList = cimOrginfoMapper.queryCimOrginfoList(page,conditions);
		paginatorResponse.setDatas(queryCimOrginfoList);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}
	
	
	@Override
	public OrgInfoResponse findOrgInfo(String orgNo) {
		return cimOrginfoMapper.findOrgInfo(orgNo);
	}


	@Override
	public PaginatorSevResp<PlatformAcctManagerListResp> queryPlatformAcctManagerPageList(Map<String, Object> query,
			Page<PlatformAcctManagerListResp> page) {
		PaginatorSevResp<PlatformAcctManagerListResp> paginatorResponse = new PaginatorSevResp<PlatformAcctManagerListResp>();
		List<PlatformAcctManagerListResp> list = cimOrginfoMapper.queryPlatformAcctManagerPageList(query, page);
		for(PlatformAcctManagerListResp bo : list) {
			if(bo != null && bo.getPlatformListIco() != null && !"".equals(bo.getPlatformListIco())){
				bo.setPlatformListIco(sysConfigService.getImageUrl(bo.getPlatformListIco()));
			}
		}
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
		if(cimOrginfo.getTeams() != null && !cimOrginfo.getTeams().isEmpty() && cimOrginfo.getTeams().size() > 0){
			for(CimOrgMemberInfo team :cimOrginfo.getTeams()){
				team.setOrgId(cimOrginfo.getId()); //机构主键
			}
			LOGGER.debug("新增机构时 批量插入团队信息到团队信息表:teams = {}", JSON.toJSONString(cimOrginfo.getTeams()));
			cimOrgMemberinfoService.insertBatch(cimOrginfo.getTeams());
			LOGGER.debug("插入数据到团队信息表成功！ ");
		}
		
		if(cimOrginfo.getOrgPictures() != null && cimOrginfo.getOrgPictures().size() > 0){
			cimOrgPictureService.insertBatchPicture(cimOrginfo.getOrgPictures());//图片批量插入
		}
		
		SysThirdkeyConfig thirdkeyConfig = new SysThirdkeyConfig();
		//第三方api接口配置表生成公钥和私钥
		thirdkeyConfig.setOrgNumber(cimOrginfo.getOrgNumber()); //机构编码
		thirdkeyConfig.setOrgKey(ApplicationUtils.randomUUID(true, true)); //生成公钥
		thirdkeyConfig.setOrgSecret(ApplicationUtils.randomUUID(true, true)); //生成私钥
		thirdkeyConfig.setOrgStatus("n"); //默认非合作状态 n, y：开启，n：关闭
		thirdkeyConfig.setCreateTime(new Date());
		thirdkeyConfig.setArchive(0); //'逻辑删除：0:可用，1：删除'
		thirdkeyConfig.setCreateUser(createUser); //创建人
		LOGGER.debug("新增机构时 插入数据到第三方api接口配置表:thirdkeyConfig = {} ", JSON.toJSONString(thirdkeyConfig));
		sysThirdkeyConfigService.insert(thirdkeyConfig); //插入
		LOGGER.debug("插入数据到第三方api接口配置表成功！ ");
	}
	
	@Override
	public void updateOrgFullInfo(CimOrginfoWeb cimOrginfo) {
		LOGGER.debug("更新机构 更新数据到机构表:cimOrginfo = {}", JSON.toJSONString(cimOrginfo));
		cimOrginfoMapper.updateByOrgNumber(cimOrginfo);
		if(jedisCluster != null){//若redis存在
			String redisOrgKey = cimOrginfo.getOrgNumber()+"_platform_detail";
			if(jedisCluster.exists(redisOrgKey)){
				jedisCluster.del(redisOrgKey);//移除key
			}
		} 
		SysThirdkeyConfig thirdkeyConfig = new SysThirdkeyConfig();
		thirdkeyConfig.setOrgNumber(cimOrginfo.getOrgNumber()); //机构编码
		thirdkeyConfig = sysThirdkeyConfigService.selectOne(thirdkeyConfig);
		if(cimOrginfo.getStatus() == null || cimOrginfo.getStatus() == 0){ //合作状态.0-合作结束，1-合作中' 2-待上线
			if(jedisCluster != null){//若redis存在
				String redisOrgKey = cimOrginfo.getOrgNumber()+thirdkeyConfig.getOrgKey();
				if(jedisCluster.exists(redisOrgKey)){
					jedisCluster.del(redisOrgKey);//移除key
				}
			} 
			thirdkeyConfig.setOrgStatus("n");
		}else if(cimOrginfo.getStatus() == 1){
			thirdkeyConfig.setOrgStatus("y");
		}
		
		thirdkeyConfig.setUpdateTime(new Date());
		thirdkeyConfig.setCreateUser(cimOrginfo.getOrgUpdater());
		sysThirdkeyConfigService.updateThirdkeyByOrgNumber(thirdkeyConfig);
		actRepaymentRedpacketPoolService.updateStatus(cimOrginfo.getOrgNumber(), cimOrginfo.getOrgFeeType(), cimOrginfo.getStatus(),cimOrginfo.getOrgGrayStatus(), cimOrginfo.getOrgUpdater());
		if(cimOrginfo.getOrgPictures() != null && cimOrginfo.getOrgPictures().size() > 0){
			cimOrgPictureService.insertBatchPicture(cimOrginfo.getOrgPictures());//图片批量插入
			if(jedisCluster != null){//若redis存在
				String redisOrgPaperKey = cimOrginfo.getOrgNumber() + "_platform_detail_org_paper";
				if(jedisCluster.exists(redisOrgPaperKey)){
					jedisCluster.del(redisOrgPaperKey);//移除key
				}
				String redisOrgEnviKey = cimOrginfo.getOrgNumber() + "_platform_detail_org_envi";
				if(jedisCluster.exists(redisOrgEnviKey)){
					jedisCluster.del(redisOrgEnviKey);//移除key
				}
				String redisOrgHonorKey = cimOrginfo.getOrgNumber() + "_platform_detail_org_honor";
				if(jedisCluster.exists(redisOrgHonorKey)){
					jedisCluster.del(redisOrgHonorKey);//移除key
				}
			}
		}
		//更新产品表下所有产品的佣金
		//cimProductService.updateFeeRatioByOrgNumber(cimOrginfo.getOrgNumber(), cimOrginfo.getOrgFeeRatio());
		LOGGER.debug("更新机构表和产品表数据成功！ ");
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
		if(jedisCluster != null){//若redis存在
			String redisOrgKey = o.getOrgNumber()+"_platform_detail";
			if(jedisCluster.exists(redisOrgKey)){
				jedisCluster.del(redisOrgKey);//移除key
			}
		} 
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
	public List<CimOrginfoBindSelect> queryOrgOfCooperation() {
		return cimOrginfoMapper.queryOrgOfCooperation();
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
	public List<CimOrginfo> findPcRecommendOrg() {
		return cimOrginfoMapper.findPcRecommendOrg();
	}

	@Override
	public List<CimOrginfo> queryLatestOrg() {
		return cimOrginfoMapper.queryLatestOrg();
	}

	@Override
	public CimOrginfoWeb queryOrgFeeInfo(String orgNumber) {
		return cimOrginfoMapper.queryOrgFeeInfo(orgNumber);
	}

	@Override
	public void updateCimOrgFeeRatio(CimOrgFeeTimetask orgFeeTask) {
		//更新机构佣金信息
		cimOrginfoMapper.updateOrgFeeRatio(orgFeeTask.getOrgNumber(), orgFeeTask.getOrgFeeRatio()); 
		//查询机构所有的产品
		List<CimProduct> orgAllOnSellProducts = cimProductService.queryOrgAllProducts(orgFeeTask.getOrgNumber());
		if(orgAllOnSellProducts != null && orgAllOnSellProducts.size() > 0){
			//更新产品表下所有在售产品的佣金
			cimProductService.updateFeeRatioByOrgNumber(orgFeeTask.getOrgNumber(), orgFeeTask.getOrgFeeRatio());
		}
		
		if(jedisCluster != null){//若redis存在
			String redisOrgKey = orgFeeTask.getOrgNumber()+"_platform_detail";
			if(jedisCluster.exists(redisOrgKey)){
				jedisCluster.del(redisOrgKey);//移除key
			}
		} 
		
		orgFeeTask.setTaskStatus(2); //2,定时任务已经执行
		orgFeeTask.setUpdateTime(new Date());
		orgFeeTask.setRemark("定时任务执行完毕");
		cimOrgFeeTimetaskService.update(orgFeeTask); //更新定时任务执行状态
		   
	}

	@Override
	public List<CimOrginfoBindSelect> queryAllOrgByStatus(int status) {
		return cimOrginfoMapper.queryAllOrgByStatus(status);
	}

	@Override
	public BigDecimal queryOrgDiffFeeRatio(String orgNumber) {
		return cimOrginfoMapper.queryOrgDiffFeeRatio(orgNumber);
	}

	@Override
	public void updateOrgRecommendInfo(CimOrginfoWeb cimOrginfo) {
		LOGGER.debug("更新机构-推荐信息:cimOrginfo = {}", JSON.toJSONString(cimOrginfo));
		cimOrginfoMapper.updateOrgRecommendInfo(cimOrginfo);
		if(jedisCluster != null){//若redis存在
			String redisOrgKey = cimOrginfo.getOrgNumber()+"_platform_detail";
			if(jedisCluster.exists(redisOrgKey)){
				//orgSecret = jedisCluster.get(redisOrgKey);
				jedisCluster.del(redisOrgKey);//移除key
			}
		}
	}

	@Override
	public int queryOrgListSort(int top) {
		return cimOrginfoMapper.queryOrgListSort(top);
	}

	@Override
	public int queryOrgHomePageSort(int homepageSort) {
		return cimOrginfoMapper.queryOrgHomePageSort(homepageSort);
	}

	@Override
	public void riskHandleSave(RiskHandleSave riskHandleSave) {
		// TODO Auto-generated method stub
		//更新风控总分  合规进度
		CimOrgRiskManageSynthesize cimOrgRiskManageSynthesize = cimOrgRiskManageSynthesizeService.selectOneByOrgNumber(riskHandleSave.getOrgNumber());
		if(cimOrgRiskManageSynthesize == null){
			cimOrgRiskManageSynthesize = new CimOrgRiskManageSynthesize();
			cimOrgRiskManageSynthesize.setComplianceProgress(riskHandleSave.getComplianceProgress());
			cimOrgRiskManageSynthesize.setTotalScore(riskHandleSave.getTotalScore());
			cimOrgRiskManageSynthesize.setOrgNumber(riskHandleSave.getOrgNumber());
			cimOrgRiskManageSynthesize.setCreateTime(new Date());
			cimOrgRiskManageSynthesizeService.insert(cimOrgRiskManageSynthesize);
		} else {
			cimOrgRiskManageSynthesize = new CimOrgRiskManageSynthesize();
			cimOrgRiskManageSynthesize.setComplianceProgress(riskHandleSave.getComplianceProgress());
			cimOrgRiskManageSynthesize.setTotalScore(riskHandleSave.getTotalScore());
			cimOrgRiskManageSynthesize.setOrgNumber(riskHandleSave.getOrgNumber());
			cimOrgRiskManageSynthesize.setUpdateTime(new Date());
			cimOrgRiskManageSynthesizeService.updateByOrgNumber(cimOrgRiskManageSynthesize);
		}
		
		//更新机构安全评级
		CimOrginfoWeb cimOrginfoWeb = new CimOrginfoWeb();
		cimOrginfoWeb.setOrgNumber(riskHandleSave.getOrgNumber());
		cimOrginfoWeb.setGrade(EnumUtils.getKvmEnumByMsg(riskHandleSave.getGradeStr(), OrgGradeEnum.values()).getValue());
		updateByOrgNumber(cimOrginfoWeb);
		
		Date now = new Date();
		//更新机构风控FRES评分
		if(CollectionUtils.isNotEmpty(riskHandleSave.getGradeTypes())){
			cimOrgRiskFresGradeService.deleteByOrgNumber(riskHandleSave.getOrgNumber());
			for (CimOrgRiskFresGrade cimOrgRiskFresGrade : riskHandleSave.getGradeTypes()) {
				cimOrgRiskFresGrade.setOrgNumber(riskHandleSave.getOrgNumber());
				cimOrgRiskFresGrade.setCreateTime(now);
				cimOrgRiskFresGrade.setUpdateTime(now);
				cimOrgRiskFresGrade.setRemark(riskHandleSave.getUser().getUsername());
				cimOrgRiskFresGradeService.insert(cimOrgRiskFresGrade);
			}
		}
		
	}

	@Override
	public CimOrginfoWeb selectOrgInfoByOrgName(String orgName) {
		return cimOrginfoMapper.selectOrgInfoByOrgName(orgName);
	}
}
