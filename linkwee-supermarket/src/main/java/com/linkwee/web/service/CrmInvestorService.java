package com.linkwee.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.linkwee.api.request.cim.ProductRecommendChooseRequest;
import com.linkwee.api.request.crm.InvotationRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.CrmInvestorRecommend;
import com.linkwee.web.model.crm.InvestorBindPlatformDatable;
import com.linkwee.web.model.crm.InvotateUserListResp;
import com.linkwee.web.request.orgInfo.OrgRecommendChooseRequest;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月12日 10:11:15
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CrmInvestorService extends GenericService<CrmInvestor,Long>{

	/**
	 * 查询CrmInvestor列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 投资用户是否存在
	 * @param mobile
	 * @return
	 */
	boolean isExistsInvestor(String mobile);

	/**
	 * 根据电话号码查投资用户
	 * @param recommendCode
	 * @return
	 */
	CrmInvestor queryInvestorByMobile(String mobile);

	/**
	 * 投资者注册
	 * @param mobile
	 * @param password
	 * @param refUserId
	 * @return
	 */
	String registerInvestor(String mobile, String password, String refUserId, String fromUrl, String accessUrl);
	
	/**
	 * 保存投资者表数据
	 * @param mobile
	 * @param password
	 * @param refUserId
	 * @param type
	 */
	void saveInvestor(String mobile, String userId, String refUserId, AppTypeEnum type);
	
	/**
	 * 查投资用户信息
	 * @param userId
	 * @return
	 */
	public CrmInvestor queryInvestorByUserId(String userId);
    
	/**
	 * @author hxb
	 * 更新投资用户二维码
	 * @param userId
	 * @param qrcode
	 */
    public void updateInvQrByUserId(String userId, String qrcode);
    
    /**
	 * @author hxb
	 * 根据用户userId更新投资用户信息
	 * @param userId
	 * @param qrcode
	 */
    int updateByUserId(CrmInvestor crmInvestor);
    
    /**
     * 查询手机号码已注册的投资用户userId
     * @param mobileArray
     * @return
     */
    public List<String> selectRegInvestors(String[] mobileArray);
    
    /**
     * 在投资用户表中查询被该用户推荐的用户userId
     * @param userId
     * @return
     */
    List<String> refRegInvestors(String userId);

    /**
	 * 根据环信帐号查投资用户信息
	 * @param userId
	 * @return
	 */
	List<CrmInvestor> queryInvestorByEasemob(List<String> easemobAcctList);

	/**
	 * 查环信token
	 * @return
	 */
	Map<String, Object> queryEaseToken();

	/**
	 * 更新环信token
	 * @param token
	 */
	void updateEaseToken(String token);

	/**
	 * 首次投资时间
	 * @param userId
	 * @return
	 */
	Date queryFirstRcpDate(String userId);

	/**
	 * 删除归属理财师
	 * @param userId
	 */
	void removeCfplanner(String userId);

	/**
	 * 用户是否锁定
	 * @param mobile
	 * @return
	 */
	boolean isLockedInventor(String mobile);
	
	/**
	 * 分配理财师
	 */
	public void allotCfplanner(String userId, String mobile);
	
	/**
	 * 查询投资人的绑定的机构信息
	 * @author yalin 
	 * @date 2016年9月29日 下午2:32:32  
	 * @param dt
	 * @param page
	 * @return
	 */
	public DataTableReturn queryInvestorBindPlatformList(InvestorBindPlatformDatable dt);
    /**
     * 查询多个对象
     *
     * @return 对象集合
     * 其中电话号码，姓名可以进行模糊查询
     */
    List<CrmInvestor> selectListByConditionNew(CrmInvestor t);
    
    /**
	 * 通过投资用户id查询用户的当前理财师
	 * @author yalin 
	 * @date 2016年10月17日 下午4:58:16  
	 * @param investUserId
	 * @return
	 */
	public CrmInvestor queryPlannerByInvestUserId(String investUserId);

	/**
	 * 根据客户条件筛选理财师所有客户 含是否推荐该产品信息
	 * @param productRecommendChooseRequest
	 * @return
	 */
	List<CrmInvestorRecommend> selectCrmInvestorRecommend(ProductRecommendChooseRequest productRecommendChooseRequest);

	/**
	 * 根据客户条件筛选理财师所有客户 含是否推荐该机构信息
	 * @param orgRecommendChooseRequest
	 * @return
	 */
	List<CrmInvestorRecommend> selectCrmInvestorRecommendOrg(OrgRecommendChooseRequest orgRecommendChooseRequest);
	
	/**
	 * 生成投资用户
	 * @param mobile 投资用户电话
	 * @param cfplaner 归属理财师userId
	 * @return
	 */
	CrmInvestor generateInvestor(String mobile, String cfplanerId) throws Exception ;

	/**
	 * 客户邀请记录列表
	 * @param req
	 * @param page
	 * @return
	 */
	PaginatorResponse<InvotateUserListResp> queryInvitationCustomerRecord(InvotationRequest req,
			Page<InvotateUserListResp> page);

	/**
	 * 客户邀请统计
	 * @param userId
	 * @return
	 */
	Map<String, Integer> queryInvitationCustomerRecordStatistics(String userId);

	/**
	 * 邀请注册成功人数
	 * @param userId
	 * @return
	 */
	int queryInvitationCount(String userId);
	
	
}
