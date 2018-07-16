package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CfpLevelNode;
import com.linkwee.web.model.CrmCfpLevelRecord;
import com.linkwee.web.model.crm.CfpManagerDetailResp;
import com.linkwee.web.model.crm.CfplannerListNewResp;
import com.linkwee.web.model.crm.LcsHongbaoListResp;
import com.linkwee.web.model.crm.TeamAllowanceListResp;
import com.linkwee.web.request.LcsListRequest;
import com.linkwee.web.request.ListDetailRequest;
import com.linkwee.web.response.CfpCustomerProfitListResp;
import com.linkwee.web.response.CfpTeamListResp;
import com.xiaoniu.mybatis.paginator.domain.PageList;

/**
 * 
 * @描述： 理财师相关查询
 * 
 * @创建人：ch
 * 
 * @创建时间：2016年04月13日 17:27:15
 * 
 *  Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
	
public interface CfplannerManagerDao  {
	
	
	/**
	 * 查询理财师列表
	 * @param request
	 * @return
	 */
	public PageList<CfpManagerDetailResp> queryLcsList(@Param("query")LcsListRequest pageRequest,RowBounds page);
	
	/**
	 * 查询理财师列表
	 * @param request
	 * @return
	 */
	public PageList<CfplannerListNewResp> queryLcsListNew(@Param("query")LcsListRequest pageRequest,RowBounds page);
	
	/**
	 * 查询理财师详情
	 * @param mobile
	 * @return
	 */
	public CfpManagerDetailResp queryLcsDetail(@Param("mobile")String mobile);
	
	/**
	 * 查询理财师详情
	 * @param mobile
	 * @return
	 */
	public CfpManagerDetailResp queryLcsDetailNew(@Param("mobile")String mobile);

	/**
	 * 删除理财师头像
	 * @param mobile
	 * @return
     */
	public int removeCfplannerHeadImage(@Param("mobile") String mobile);
	
	/**
     * 查询理财师客户列表
     */
    public List<CfpCustomerProfitListResp> queryCfpCustomerProfitList(@Param("query") CfpManagerDetailResp detailResp,RowBounds rowBounds);

    /**
     * 查询理财师团队列表
     */
    public List<CfpTeamListResp> queryCfpTeamList(@Param("query") CfpManagerDetailResp lcsDetailResp,RowBounds rowBounds);
    
    /**
     * 查询理财师团队列表
     */
    public List<CfpTeamListResp> queryLcsTeamDetail(@Param("query") CfpManagerDetailResp lcsDetailResp,RowBounds rowBounds);
    
    /**
     * 查询理财师团队列表(条件查询)
     */
    public List<CfpTeamListResp> queryLcsTeamDetailCondition(@Param("query") CfpManagerDetailResp lcsDetailResp,RowBounds rowBounds); 
	
    /**
     * 奖励、津贴
     */
    public List<CfpTeamListResp> queryRewardAllowanceCondition(@Param("query") CfpManagerDetailResp lcsDetailResp,RowBounds rowBounds); 
    
    /**
     * 团队关系
     */
    public String queryTeamRela(@Param("userId")String userId,@Param("parentId")String parentId);
	/**
	 * 查询理财师数据
	 * @return
	 */
	public Map<String, Object> getLcsDateStaticCount();
	
	/**
	 * 根据日期查询理财师数据
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Map<String, Object>> getLcsDateStatic(Map<String, Object> map);
	
	/**
	 * 查询有效理财师数据
	 * @return
	 */
	public Map<String, Object> getValidLcsDateStaticCount();
	
	/**
	 * 根据日期查询有效理财师数据
	 * @param start
	 * @param end
	 * @return
	 */
	public  List<Map<String, Object>> getValidLcsDateStatic(Map<String, Object> map);

	/**
	 * 查询无父无子的理财师
	 * @return
	 */
	public List<CfpLevelNode> getCfpWithoutChildrenAndParent(@Param("startDate")String startDate, @Param("endDate")String endDate);

	/**
	 * 查询有父无子的理财师
	 * @return
	 */
	public List<CfpLevelNode> getCfpWithoutChildrenButParent(@Param("startDate")String startDate, @Param("endDate")String endDate);

	/**
	 * 查询有父有子的理财师
	 * @return
	 */
	public List<CfpLevelNode> getCfpWithChildrenAndParent(@Param("startDate")String startDate, @Param("endDate")String endDate);

	/**
	 * 查询无父有子的理财师
	 * @return
	 */
	public List<CfpLevelNode> getCfpWithChildrenButParent(@Param("startDate")String startDate, @Param("endDate")String endDate);

	public Double queryActivityReward(String userId);

	public Double queryAllowance(String userId);

	public Double queryCurrInvestAmount(String userId);

	public Double queryFee(String userId);

	public List<LcsHongbaoListResp> queryHongbaoList(String userId);

	public Double queryLeaderReward(String userId);

	public Double queryTotalSaleAmount(String userId);

	public int queryTotalSaleCount(String userId);

	/**
	 * 理财师职级变更历史
	 * @param lcsDetailResp
	 * @param page
	 * @return
	 */
	public List<CrmCfpLevelRecord> queryCfpLevelList(@Param("query")CfpManagerDetailResp lcsDetailResp, RowBounds rowBounds);
	
	
	/**
	 * 团队理财师收益贡献明细
	 */
	public List<TeamAllowanceListResp> queryTeamAllowanceList(@Param("query")ListDetailRequest req, Page<TeamAllowanceListResp> page);

}
