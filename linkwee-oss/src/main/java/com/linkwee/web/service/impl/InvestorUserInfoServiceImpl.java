package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.InvestorUserInfoDao;
import com.linkwee.web.dao.SaleUserInfoDao;
import com.linkwee.web.dao.UsercustomerrelDao;
import com.linkwee.web.enums.RegSourceEnum;
import com.linkwee.web.enums.YesOrNotEnum;
import com.linkwee.web.model.InvestorDtlResp;
import com.linkwee.web.model.InvestorUserInfo;
import com.linkwee.web.model.SaleUserInfo;
import com.linkwee.web.model.Usercustomerrel;
import com.linkwee.web.service.InvestorUserInfoService;



 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年12月25日 15:06:26
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
@Service("investorUserInfoService")
public class InvestorUserInfoServiceImpl implements InvestorUserInfoService{
	
	@Autowired
	private InvestorUserInfoDao investorUserInfoDao;
	
	@Autowired
	private UsercustomerrelDao usercustomerrelDao;
	
	@Autowired
	private SaleUserInfoDao saleUserInfoDao;
	
	
	/**
	 * 更新投资者信息
	 * @param investorUserInfo
	 */
	public void update(InvestorUserInfo investorUserInfo){
		investorUserInfoDao.update(investorUserInfo);
	}
	
	
	/**
	 * 预注册
	 * @param mobile 手机号
	 * @param refUser 推荐人
	 * @param userSource 用户来源
	 */
	public int preRegister(String mobile,String userName,String refUser,Integer userSource){
		InvestorUserInfo investorUserInfo = new InvestorUserInfo();
		investorUserInfo.setMobile(mobile);
		investorUserInfo.setUserName(userName);
		investorUserInfo.setRefUser(refUser);
		investorUserInfo.setUserSource(userSource);
		investorUserInfo.setStatus(YesOrNotEnum.YES.getCode());
	    investorUserInfoDao.add(investorUserInfo);
	    return investorUserInfo.getId();
	}
	
	/**
	 * 完成注册
	 * @param id 
	 * @param userId 用户id
	 */
	public void completeRegister(int id,String userId,String recommendUserId){
		InvestorUserInfo investorUserInfo = investorUserInfoDao.getByPrimaryKey(id);
		//初始化关联关系表数据
		initusercustomerrel(userId,investorUserInfo.getMobile(),investorUserInfo.getUserName(),recommendUserId);
		if(investorUserInfo!=null){
			investorUserInfo.setUserId(userId);
			investorUserInfo.setStatus(YesOrNotEnum.NOT.getCode());
			investorUserInfoDao.update(investorUserInfo);
		}
	}
	
	/**
	 * 直接注册
	 * @param mobile 手机号
	 * @param refUser 推荐人
	 * @param userSource 用户来源
	 */
	public void register(String userId, String mobile, String userName, String refUser, Integer userSource) {
		InvestorUserInfo investorUserInfo = new InvestorUserInfo();
		investorUserInfo.setMobile(mobile);
		investorUserInfo = investorUserInfoDao.query(investorUserInfo);
		//初始化关联关系表数据
		initusercustomerrel(userId,mobile,userName,refUser);
		if (null == investorUserInfo) {
			investorUserInfo = new InvestorUserInfo();
			investorUserInfo.setUserId(userId);
			investorUserInfo.setUserName(userName);
			investorUserInfo.setMobile(mobile);
			investorUserInfo.setRefUser(refUser);
			investorUserInfo.setUserSource(userSource);
			investorUserInfo.setStatus(YesOrNotEnum.NOT.getCode());
			investorUserInfoDao.add(investorUserInfo);
			
		}
	}
	
	/**
	 * 初始化关联关系表数据
	 */
	public void initusercustomerrel(String userId, String mobile, String userName, String recommendUserId){
		Usercustomerrel usercustomerrel = new Usercustomerrel();
		usercustomerrel.setCustomermobile(mobile);
		//当前注册用户
		usercustomerrel =  usercustomerrelDao.query(usercustomerrel);
		if(null == usercustomerrel){
			//如果用户关联数据不存在
			usercustomerrel = new Usercustomerrel();
			Usercustomerrel refusercustomer = null;//邀请人
			if(StringUtils.isNotBlank(recommendUserId)){
				refusercustomer = new Usercustomerrel();
				refusercustomer.setCustomerid(recommendUserId);
				refusercustomer = usercustomerrelDao.query(refusercustomer);
			}
			if(null != refusercustomer){
				//如果邀请人不为空
				usercustomerrel.setCurrsaleuser(refusercustomer.getCurrsaleuser());
				SaleUserInfo saleUserInfo2 = new SaleUserInfo();
				saleUserInfo2.setCustomerId(recommendUserId);
				saleUserInfo2 = saleUserInfoDao.query(saleUserInfo2);
				if(null != saleUserInfo2 && saleUserInfo2.getIsCfp() == 1){
					//如果邀请人是理财师
					usercustomerrel.setRegrefereetype(new Byte("1"));
					usercustomerrel.setRegrefuser(saleUserInfo2.getNumber());
				}else{
					usercustomerrel.setRegrefereetype(new Byte("2"));
					usercustomerrel.setRegrefcustomer(refusercustomer.getCustomermobile());
				}
				usercustomerrel.setFreecustomer(refusercustomer.getFreecustomer());//根据邀请人是否自由客户身份来设置注册人身份
			} else {
				usercustomerrel.setFreecustomer(1);//自由客户
			}
			SaleUserInfo saleUserInfo = new SaleUserInfo();
			saleUserInfo.setCustomerId(userId);
			saleUserInfo = saleUserInfoDao.query(saleUserInfo);
			if(null != saleUserInfo && saleUserInfo.getIsCfp() == 1 ){
				//如果注册用户是理财师
				usercustomerrel.setCurrsaleuser(saleUserInfo.getNumber());
				usercustomerrel.setFreecustomer(0);
			}
			usercustomerrel.setCustomerid(userId);
			usercustomerrel.setCustomername(userName);
			usercustomerrel.setCustomermobile(mobile);
			usercustomerrel.setRegbizfrom(RegSourceEnum.INVESTOR.getValue());
			usercustomerrel.setRegtime(new Date());
			usercustomerrel.setCreatetime(new Date());
			usercustomerrel.setUpdatetime(new Date());
			usercustomerrelDao.add(usercustomerrel);
		} else {
			//如果存在用户关联表数据
			InvestorUserInfo investorUserInfo = this.queryInvestorUserInfoByMobile(mobile);
			if(investorUserInfo == null || investorUserInfo.getUserId() == null) {
				//如果是钱罐子用户激活
				Usercustomerrel usercustomerrelUpdate = new Usercustomerrel();
				Usercustomerrel refusercustomer = null;//邀请人
				if(StringUtils.isNotBlank(recommendUserId)){
					refusercustomer = new Usercustomerrel();
					refusercustomer.setCustomerid(recommendUserId);
					refusercustomer = usercustomerrelDao.query(refusercustomer);
				}
				if(null != refusercustomer){
					//如果邀请人不为空
					usercustomerrelUpdate.setCurrsaleuser(refusercustomer.getCurrsaleuser());
					SaleUserInfo saleUserInfo2 = new SaleUserInfo();
					saleUserInfo2.setCustomerId(recommendUserId);
					saleUserInfo2 = saleUserInfoDao.query(saleUserInfo2);
					if(null != saleUserInfo2  && saleUserInfo2.getIsCfp() == 1){
						//如果邀请人是理财师
						usercustomerrelUpdate.setRegrefereetype(new Byte("1"));
						usercustomerrelUpdate.setRegrefuser(saleUserInfo2.getNumber());
					}else{
						usercustomerrelUpdate.setRegrefereetype(new Byte("2"));
						usercustomerrelUpdate.setRegrefcustomer(refusercustomer.getCustomermobile());
					}
					usercustomerrelUpdate.setFreecustomer(refusercustomer.getFreecustomer());//根据邀请人是否自由客户身份来设置注册人身份
				} else {
					usercustomerrelUpdate.setFreecustomer(1);//自由客户
					usercustomerrelUpdate.setCurrsaleuser("");
					usercustomerrelUpdate.setRegrefuser("");
					usercustomerrelUpdate.setRegrefcustomer("");
				}
				SaleUserInfo saleUserInfo = new SaleUserInfo();
				saleUserInfo.setCustomerId(userId);
				saleUserInfo = saleUserInfoDao.query(saleUserInfo);
				if(null != saleUserInfo && saleUserInfo.getIsCfp() == 1){
					//如果注册用户是理财师
					usercustomerrelUpdate.setCurrsaleuser(saleUserInfo.getNumber());
					usercustomerrelUpdate.setFreecustomer(0);
				}
				usercustomerrelUpdate.setCustomerid(userId);
				usercustomerrelUpdate.setCustomername(userName);
				usercustomerrelUpdate.setCustomermobile(mobile);
				usercustomerrelUpdate.setRegbizfrom(RegSourceEnum.INVESTOR.getValue());
				usercustomerrelUpdate.setRegtime(new Date());
				usercustomerrelUpdate.setCreatetime(new Date());
				usercustomerrelUpdate.setUpdatetime(new Date());
				
				usercustomerrelUpdate.setId(usercustomerrel.getId());
				//修改数据
				usercustomerrelDao.update(usercustomerrelUpdate);
			} else {
				SaleUserInfo saleUserInfo = new SaleUserInfo();
				saleUserInfo.setCustomerId(userId);
				saleUserInfo = saleUserInfoDao.query(saleUserInfo);
				if(null != saleUserInfo && saleUserInfo.getIsCfp() == 1){
					//如果注册用户是理财师
					Usercustomerrel relUpdate = new Usercustomerrel();
					relUpdate.setCurrsaleuser(saleUserInfo.getNumber());
					relUpdate.setId(usercustomerrel.getId());
					relUpdate.setFreecustomer(0);
					usercustomerrelDao.update(relUpdate);
				}
			}
		}
	}
	
	public InvestorUserInfo queryInvestorUserInfo(String userId) {
		InvestorUserInfo investorUserInfo = new InvestorUserInfo();
		investorUserInfo.setUserId(userId);
		return investorUserInfoDao.query(investorUserInfo);
	}
	
	public InvestorUserInfo queryInvestorUserInfoByMobile(String mobile) {
		InvestorUserInfo investorUserInfo = new InvestorUserInfo();
		investorUserInfo.setMobile(mobile);
		investorUserInfo.setStatus(YesOrNotEnum.NOT.getCode());
		return investorUserInfoDao.query(investorUserInfo);
	}
	
	/**
	 * 投资者用户是否存在
	 * @param mobile 手机号码
	 * @return
	 */
	public boolean isExistsInvestor(String mobile) {
		InvestorUserInfo investorUserInfo = new InvestorUserInfo();
		investorUserInfo.setStatus(YesOrNotEnum.NOT.getCode());
		investorUserInfo.setMobile(mobile);
		List<InvestorUserInfo> list = investorUserInfoDao.list(investorUserInfo);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}


	/**
	 * 是否为理财师用户
	 * @Auther ZhongLing
	 * @Date 2016年1月6日
	 * @param userId
	 */
	@Override
	public boolean isCfp(String userId) {
		return investorUserInfoDao.isCfp(userId) > 0;
	}


	@Override
	public InvestorUserInfo queryErrInvestorUserInfo() {
		return investorUserInfoDao.queryErrInvestorUserInfo();
	}


	@Override
	public InvestorUserInfo queryErrInvestorUserInfoOfRefIsNull() {
		return investorUserInfoDao.queryErrInvestorUserInfoOfRefIsNull();
	}

	/**
	 * 根据userIds 返回userId,Username map
	 */
	@Override
	public Map<String,InvestorUserInfo> getInvestUserInfoMap(List<String> userIds) {
		Map<String,InvestorUserInfo> nameMap = new HashMap<String,InvestorUserInfo>();
		List<InvestorUserInfo> nameList = investorUserInfoDao.findUserNameById(userIds);
		for(InvestorUserInfo item: nameList){
			nameMap.put(item.getUserId(),item);
		}
		return nameMap;
	}


	@Override
	public boolean isLocked(String mobile) {
		if(investorUserInfoDao.queryIsLocked(mobile) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public void updateRealName(String mobile, String name) {
		investorUserInfoDao.updateRealName(mobile, name);
	}

	@Override
	public void updateByImage(InvestorUserInfo invest) {
		investorUserInfoDao.update(invest);
	}


	@Override
	public String findUserIdByNameOrMobile(String buyUserName) {
		return investorUserInfoDao.findUserIdByNameOrMobile(buyUserName);
	}

	@Override
	public InvestorDtlResp queryInvestorDetail(String mobile) {
		return investorUserInfoDao.queryInvestorDetail(mobile);
	}


	@Override
	public boolean removeInvestorHeadImage(String mobile) {
		return investorUserInfoDao.removeInvestorHeadImage(mobile)>0;
	}
	
	@Override
    public PaginatorResponse<InvestorUserInfo> queryInvestorUserInfo(Page<InvestorUserInfo> page,Map<String,Object> conditions){
   	 PaginatorResponse<InvestorUserInfo> paginatorResponse = new PaginatorResponse<InvestorUserInfo>();
   	 List<InvestorUserInfo> investorList = investorUserInfoDao.queryInvestorUserInfo(page, conditions);
	 paginatorResponse.setDatas(investorList);
	 paginatorResponse.setValuesByPage(page);
		return  paginatorResponse;
   }

}
