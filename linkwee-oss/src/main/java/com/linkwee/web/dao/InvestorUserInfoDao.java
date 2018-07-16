package com.linkwee.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.base.BasePageDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.InvestorDtlResp;
import com.linkwee.web.model.InvestorUserInfo;


 /**
 * 
 * 描述： Dao接口
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年12月25日 15:06:26
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface InvestorUserInfoDao extends BasePageDao<InvestorUserInfo>{

	/**
	 * 是否为理财师
	 * @param userId
	 */
	Integer isCfp(String userId);

	/**
	 * 查没有用户id的错误数据
	 * @return
	 */
	InvestorUserInfo queryErrInvestorUserInfo();

	/**
	 * 查没有关联关系数据的用户
	 */
	InvestorUserInfo queryErrInvestorUserInfoOfRefIsNull();
	
	/**
	 * 根据ID查Name,批量
	 */
	public List<InvestorUserInfo> findUserNameById(List<String> userIds);
	/**
	 * 查是否锁定字段
	 * @param mobile
	 * @return
	 */
	int queryIsLocked(String mobile);

	/**
	 * 更新用户实名
	 * @param mobile
	 * @param name
	 */
	void updateRealName(@Param("mobile")String mobile, @Param("name")String name);
	
	public String findUserIdByNameOrMobile(@Param("buyUserName")String buyUserName);

	/**
	 * 查用户详情
	 * @param mobile
	 * @return
	 */
	InvestorDtlResp queryInvestorDetail(String mobile);

	/**
	 * 删除头像
	 * @param mobile
	 * @return
	 */
	int removeInvestorHeadImage(String mobile);
	
	
	/**
	 * 分页
	 * @param request
	 * @return
	 */
	public List<InvestorUserInfo> queryInvestorUserInfo(Page<InvestorUserInfo> page, Map<String,Object> map);

}
