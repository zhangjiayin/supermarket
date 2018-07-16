package com.linkwee.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.web.dao.InvestorUserInfoDao;
import com.linkwee.web.dao.UsercustomerrelDao;
import com.linkwee.web.enums.YesOrNotEnum;
import com.linkwee.web.model.InvestorUserInfo;
import com.linkwee.web.model.Usercustomerrel;
import com.linkwee.web.service.UsercustomerrelService;



 /**
 * 
 * @描述： 客户信息服务类
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年08月12日 09:37:08
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
@Service("usercustomerrelService")
public class UsercustomerrelServiceImpl implements UsercustomerrelService{
	
	@Autowired
	private UsercustomerrelDao usercustomerrelDao;
	@Autowired
	private InvestorUserInfoDao investorUserInfoDao;
	
	/**
	 * 根据手机号查询客户信息
	 * @param mobile 手机号
	 * @return
	 */
	public Usercustomerrel queryByMobile(String mobile){
		Usercustomerrel usercustomerrel = new Usercustomerrel();
		usercustomerrel.setDelstatus(Byte.parseByte(YesOrNotEnum.NOT.getCode()+""));
		usercustomerrel.setCustomermobile(mobile);
		return usercustomerrelDao.query(usercustomerrel);
	}
	/**
	 * 根据客户id查询客户信息
	 * @param mobile 手机号
	 * @return
	 */
	public List<Usercustomerrel> queryByIds(String[] ids){
		if(ids==null||ids.length==0){
			return null;
		}
		return usercustomerrelDao.queryByIds(ids);
	}
	
	public void update(Usercustomerrel usercustomerrel){
		usercustomerrelDao.update(usercustomerrel);
	}
	
	public void removeImportantCust(String saleNumber,String customerId){
		Usercustomerrel usercustomerrel = new Usercustomerrel();
		usercustomerrel.setCustomerid(customerId);
		//usercustomerrel.setCurrsaleuser(saleNumber);
		usercustomerrel.setImportant(Byte.parseByte(YesOrNotEnum.YES.getCode()+""));
		List<Usercustomerrel> list = usercustomerrelDao.list(usercustomerrel);
		if(list!=null&&list.size()>0){
			for(Usercustomerrel obj:list){
				obj.setImportant(Byte.parseByte(YesOrNotEnum.NOT.getCode()+""));
			}
			usercustomerrelDao.updateBatch(list);
		}
	}
	
	public void addImportantCust(String saleNumber,String customerId){
		Usercustomerrel usercustomerrel = new Usercustomerrel();
		usercustomerrel.setCustomerid(customerId);
		//usercustomerrel.setCurrsaleuser(saleNumber);
		usercustomerrel.setImportant(Byte.parseByte(YesOrNotEnum.NOT.getCode()+""));
		List<Usercustomerrel> list = usercustomerrelDao.list(usercustomerrel);
		if(list!=null&&list.size()>0){
			for(Usercustomerrel obj:list){
				obj.setImportant(Byte.parseByte(YesOrNotEnum.YES.getCode()+""));
			}
			usercustomerrelDao.updateBatch(list);
		}
	}
	
	@Override
	public Usercustomerrel queryUserInfo(String userId) {
		return usercustomerrelDao.getByPrimaryKey(userId);
	}
	@Override
	public List<Usercustomerrel> getUserInfoByEasemob(List<String> easemobAcctList) {
		return usercustomerrelDao.getUserInfoByEasemob(easemobAcctList);
	}
	/**
	 * 查询新人用户
	 * @Auther xuzhao
	 * @Date 2016年1月20日 下午2:03:53
	 * @param userId
	 * @return
	 */
	@Override
	public Usercustomerrel queryNewCustmers(String userId) {
		return usercustomerrelDao.queryNewCustmers(userId);
	}
	
	/**
	 * 根据客户id查询用户信息
	 */
	@Override
	public Usercustomerrel queryByCustomerId(String customerId) {
		Usercustomerrel usercustomerrel = new Usercustomerrel();
		usercustomerrel.setCustomerid(customerId);
		return usercustomerrelDao.query(usercustomerrel);
	}
	/**
	 * 更新用户业务员关联关系表
	 */
	@Override
	public int updateRegreInfo(int i, String number, String userId) {
		return usercustomerrelDao.updateRegreInfo(i,number,userId);
	}
	@Override
	public void add(Usercustomerrel usercustomerrel) {
		usercustomerrelDao.add(usercustomerrel);
	}
	
	/**
	 * 根据手机号查询客户信息(包含理财师信息)
	 * @param mobile 手机号
	 * @return
	 */
	public Usercustomerrel findSaleInfoByMobile(String mobile){
		return usercustomerrelDao.findSaleInfoByMobile(mobile);
	}
	
	@Override
	public String findUserIdByMobile(String mobile) {
		String userId = "";
		InvestorUserInfo  condit = new InvestorUserInfo();
		condit.setMobile(mobile);
		InvestorUserInfo result = investorUserInfoDao.query(condit);
		if(result != null){
			userId = result.getUserId();
		}
		return userId;
	}
	
	@Override
	public void updateRealName(String mobile, String name) {
		usercustomerrelDao.updateRealName(mobile, name);
	}
	
}
