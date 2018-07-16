package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import com.linkwee.web.model.CfpCancelValideModel;
import org.apache.ibatis.annotations.Param;

import com.linkwee.web.response.LcsCustomeDetailResp;
import com.linkwee.web.response.LcsDetailResp;
import com.linkwee.web.response.LcsTeamDetailResp;
import com.xiaoniu.mybatis.paginator.domain.PageList;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;
import org.apache.ibatis.session.RowBounds;

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
	
public interface LcsListDao  {
	/**
	 * 是否理财师
	 * @param mobile
	 * @return
	 */
	public int isCfp(@Param("mobile")String mobile);
	
	/**
	 * 查询理财师数量
	 * @param params
	 * @return
	 */
	public int queryLcsListCount(Map<String, Object> params);
	
	
	/**
	 * 查询理财师列表
	 * @param request
	 * @return
	 */
	public PageList<LcsDetailResp> queryLcsList(PageRequest pageRequest,RowBounds page);
	
	/**
	 * 根据手机号码 查询理财师详情
	 * @param mobile
	 * @return
	 */
	public LcsDetailResp queryLcsDetail(@Param("mobile")String mobile);


	
	/**
	 * 查询理财师团队数量
	 * @param mobile
	 * @return
	 */
	public int queryLcsTeamCount(Map<String, Object> params);
	
	/**
	 * 查询理财师团队列表
	 * @param request
	 * @return
	 */
	public PageList<LcsTeamDetailResp> queryLcsTeamList(PageRequest pageRequest);
	
	// 退出理财师 start
	/**
	 * 记录操作
	 * @param mobile
	 */
	public void operationRecords(@Param("mobile")String mobile,@Param("remark")String remark);
	
	/**
	 * 备份
	 * @param mobile
	 */
	public void bacRecords(@Param("mobile")String mobile);
	
	/**
	 * 删除信息
	 * @param mobile
	 */
	public void deleteSaleUser(@Param("mobile")String mobile);
	
	/**
	 * 更新为自由客户
	 * @param mobile
	 */
	public void changeFreedomCustomer(@Param("mobile")String mobile);

	/**
	 * 更改为上级理财师客户
	 * @param mobile
	 * @return
     */
	public int changeFreedomCustomerNew(@Param("mobile")String mobile);


	//退出理财师 end
	
	/**
	 * 查询理财师的客户数量
	 * @param params
	 * @return
	 */
	public int queryLcsCustomerCount(Map<String, Object> params);
	
	/**
	 * 查询理财师的客户
	 * @param pageRequest
	 * @return
	 */
	public PageList<LcsCustomeDetailResp> queryLcsCustomerList(PageRequest pageRequest,RowBounds rowBounds);
	
	
	
	/**
	 * 解绑客户与理财师的关系
	 * @param customerMobile
	 * @param lcsNumber
	 */
	public void unbindByCustomer(@Param("customerMobile")String customerMobile, @Param("lcsNumber")String lcsNumber);
	
	/**
	 * 更换理财师组织机构 
	 * @param lcsNumber
	 * @param department
	 */
	public void unbindByOrganization(@Param("lcsNumber")String lcsNumber,@Param("department")String department);
	
	/**
	 * 导出理财师数据
	 * @param map
	 * @return
	 */
	public List<LcsDetailResp> exportLcsList(Map<String, Object> map);
	
	/**
	 * 导出理财师团队
	 * @param map
	 * @return
	 */
	public List<LcsTeamDetailResp> exportLcsTeamList(Map<String, Object> map);
	
	/**
	 * 导出理财师客户
	 * @param map
	 * @return
	 */
	public List<LcsCustomeDetailResp> exportLcsCustomerList(Map<String, Object> map);

	/**
	 * 获得可否取消理财师的条件
	 * @param mobile
	 * @return
     */
	public CfpCancelValideModel queryValidCancelCFP(@Param("mobile") String mobile);

	/**
	 * 删除理财师头像
	 * @param mobile
	 * @return
     */
	public int removeSaleUserHeadImage(@Param("mobile") String mobile);
}
