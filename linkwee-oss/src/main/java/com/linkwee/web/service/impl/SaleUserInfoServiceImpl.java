package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.web.dao.ChangeParentRecordDao;
import com.linkwee.web.dao.SaleUserInfoDao;
import com.linkwee.web.dao.UsercustomerrelDao;
import com.linkwee.web.enums.YesOrNotEnum;
import com.linkwee.web.model.ChangeParentRecord;
import com.linkwee.web.model.InvestorUserInfo;
import com.linkwee.web.model.SaleUserInfo;
import com.linkwee.web.model.Usercustomerrel;
import com.linkwee.web.service.InvestorUserInfoService;
import com.linkwee.web.service.SaleUserInfoService;

 /**
 * 
 * @描述：销售人员服务类
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年07月31日 11:49:53
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
@Service("saleUserInfoService")
public class SaleUserInfoServiceImpl implements SaleUserInfoService{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SaleUserInfoDao saleUserInfoDao;
	
	@Autowired
	private UsercustomerrelDao usercustomerrelDao;
	@Autowired
	private InvestorUserInfoService investorUserInfoService; 
	
	@Autowired
	private ChangeParentRecordDao changeParentRecordDao;
	

	/**
	 * 根据手机号码查询 用户信息
	 * 
	 * @param mobile 手机号码
	 * @return 理财师用户信息
	 */
	@Override
	public SaleUserInfo getSaleUserInfoByMobile(String mobile) {
		SaleUserInfo saleUserInfo = new SaleUserInfo();
		saleUserInfo.setMobile(mobile);
		saleUserInfo.setDelStatus(Byte.parseByte(YesOrNotEnum.NOT.getCode()+""));
		List<SaleUserInfo> list = saleUserInfoDao.list(saleUserInfo);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 根据用户id查询 用户信息
	 * 
	 * @param mobile 手机号码
	 * @return 理财师用户信息
	 */
	public SaleUserInfo getSaleUserInfoByCustomerId(String customerId) {
		SaleUserInfo saleUserInfo = new SaleUserInfo();
		saleUserInfo.setCustomerId(customerId);
		saleUserInfo.setDelStatus(Byte.parseByte(YesOrNotEnum.NOT.getCode()+""));
		List<SaleUserInfo> list = saleUserInfoDao.list(saleUserInfo);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据用户编码查询 用户信息
	 * 
	 * @param number 用户编码
	 * @return 理财师用户信息
	 */
	@Override
	public SaleUserInfo getSaleUserInfoByNumber(String number) {
		SaleUserInfo saleUserInfo = new SaleUserInfo();
		saleUserInfo.setNumber(number);
		saleUserInfo.setDelStatus(Byte.parseByte(YesOrNotEnum.NOT.getCode()+""));
		List<SaleUserInfo> list = saleUserInfoDao.list(saleUserInfo);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 新增
	 * @param saleUserInfo
	 */
	@Override
	public void add(SaleUserInfo saleUserInfo) {
		saleUserInfoDao.add(saleUserInfo);
	}

	/**
	 * 修改
	 * @param saleUserInfo
	 */
	@Override
	public void update(SaleUserInfo saleUserInfo) {
		saleUserInfoDao.update(saleUserInfo);
	}
	
	/**
	 * 判断 是否新财富账号
	 * @param number 用户编码
	 * @return true是，false 否
	 */
	public boolean isNewWealthUser(String number){
		SaleUserInfo saleUserInfo = new SaleUserInfo();
		saleUserInfo.setNumber(number);
		List<SaleUserInfo> list = saleUserInfoDao.findNewWealthUser(saleUserInfo);
		return list!=null&&list.size()>0;
	}

	/**
	 * 根据投资用户id查理财师信息
	 * @Auther ZhongLing
	 * @Date 2016年1月4日
	 * @param customerId
	 * @return
	 */
	@Override
	public SaleUserInfo getSaleUserInfoByInvester(String customerId) {
		Usercustomerrel bo = new Usercustomerrel();
		bo.setCustomerid(customerId);
		bo = usercustomerrelDao.query(bo);
		if(bo != null) {
			if(StringUtils.isNotBlank(bo.getCurrsaleuser())){
				return this.getSaleUserInfoByNumber(bo.getCurrsaleuser());
			}else{
				return null;
			}
		}else{
			InvestorUserInfo investorUserInfo = investorUserInfoService.queryInvestorUserInfo(customerId);
			if(null != investorUserInfo && StringUtils.isNotBlank(investorUserInfo.getRefUser())){
//				return this.getSaleUserInfoByCustomerId(investorUserInfo.getRefUser());
				bo = new Usercustomerrel();
				bo.setCustomerid(investorUserInfo.getRefUser());
				bo = usercustomerrelDao.query(bo);
				if(bo != null) {
					if(StringUtils.isNotBlank(bo.getCurrsaleuser())){
						return this.getSaleUserInfoByNumber(bo.getCurrsaleuser());
					}else{
						return null;
					}
				}
			}
		}
		return null;
	}

	@Override
	public boolean isLevelOne(String cfpMobile, String eventCfpmobile) {
		//查出理财师的上级
		SaleUserInfo parent = this.queryParentByMobile(eventCfpmobile);
		
		if(cfpMobile !=null && parent != null && cfpMobile.equals(parent.getMobile())) {
			return true;
		}
		return false;
	}

	@Override
	public SaleUserInfo queryParentByMobile(String eventCfpmobile) {
		return saleUserInfoDao.queryParentByMobile(eventCfpmobile);
	}

	@Override
	public boolean isLocked(String mobile) {
		if(saleUserInfoDao.queryIsLocked(mobile) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public SaleUserInfo queryLscByInvestMobile(String moible) {
		return saleUserInfoDao.queryLscByInvestMobile(moible);
	}

	@Override
	public void updateRealName(String mobile, String name) {
		saleUserInfoDao.updateRealName(mobile, name);
	}

	@Override
	public List<SaleUserInfo> listUserList() {
		return saleUserInfoDao.listUserList();
	}
	
	@Override
	public void updateByImage(SaleUserInfo saleUserInfo){
		saleUserInfoDao.updateByImage(saleUserInfo);
	}

	/**
	 * 更换上级
	 * @param mobile
	 * @param parentMobile
	 * @param changeType
	 */
	@Override
	public void changeParent(String mobile, String parentMobile, String changeType, SaleUserInfo saleUserInfo) {
		SaleUserInfo saleUserForUpdate = new SaleUserInfo();
		if("1".equals(changeType)) {
			//更改新上级
			SaleUserInfo parentNew = this.getSaleUserInfoByMobile(parentMobile);
			if(parentNew == null){
				return;
			}
			saleUserForUpdate.setNumber(saleUserInfo.getNumber());
			saleUserForUpdate.setParentId(parentNew.getNumber());
			if(parentNew.getAncestor() != null && !"".equals(parentNew.getAncestor())) {
				saleUserForUpdate.setAncestor(parentNew.getAncestor() + "!" + parentNew.getNumber());
			} else {
				saleUserForUpdate.setAncestor(parentNew.getNumber());
			}
			this.update(saleUserForUpdate);
			//操作记录
			ChangeParentRecord record = new ChangeParentRecord();
			record.setNumber(saleUserInfo.getNumber());
			record.setParentId(parentNew.getNumber());
			record.setType(1);
			changeParentRecordDao.add(record);
			
		} else if ("2".equals(changeType)) {
			//变为一级理财师
			saleUserForUpdate.setNumber(saleUserInfo.getNumber());
			saleUserForUpdate.setParentId("");
			saleUserForUpdate.setAncestor("");
			this.update(saleUserForUpdate);
			//操作记录
			ChangeParentRecord record = new ChangeParentRecord();
			record.setNumber(saleUserInfo.getNumber());
			record.setParentId(saleUserInfo.getParentId());
			record.setType(2);
			changeParentRecordDao.add(record);
		} else {
			return;
		}
		//更新下级的先祖路径，更新下两级
		List<SaleUserInfo> children1 = querySaleUserByParent(saleUserInfo.getNumber());
		for(SaleUserInfo child1 : children1) {
			//一级下级
			SaleUserInfo saleUserForUpdate1 = new SaleUserInfo();
			saleUserForUpdate1.setNumber(child1.getNumber());
			if(saleUserForUpdate.getAncestor() != null && !"".equals(saleUserForUpdate.getAncestor())) {
				saleUserForUpdate1.setAncestor(saleUserForUpdate.getAncestor() + "!" + saleUserForUpdate.getNumber());
			} else {
				saleUserForUpdate1.setAncestor(saleUserForUpdate.getNumber());
			}
			this.update(saleUserForUpdate1);
			
			List<SaleUserInfo> children2 = querySaleUserByParent(child1.getNumber());
			for(SaleUserInfo child2 : children2) {
				//二级下级
				SaleUserInfo saleUserForUpdate2 = new SaleUserInfo();
				saleUserForUpdate2.setNumber(child2.getNumber());
				if(saleUserForUpdate1.getAncestor() != null && !"".equals(saleUserForUpdate1.getAncestor())) {
					saleUserForUpdate2.setAncestor(saleUserForUpdate1.getAncestor() + "!" + saleUserForUpdate1.getNumber());
				} else {
					saleUserForUpdate2.setAncestor(saleUserForUpdate1.getNumber());
				}
				this.update(saleUserForUpdate2);
			}
		}
	}
	
	/**
	 * 根据parentId查理财师
	 */
	public List<SaleUserInfo> querySaleUserByParent(String parentId){
		SaleUserInfo saleUserInfo = new SaleUserInfo();
		saleUserInfo.setParentId(parentId);
		List<SaleUserInfo> list = saleUserInfoDao.list(saleUserInfo);
		return list;
	}

	/**
	 * 禁止登录90天
	 * @param mobile
	 */
	@Override
	public void disabledLogin90days(String mobile) {
		saleUserInfoDao.disabledLogin90days(mobile);
	}

	@Override
	public Date queryDisabledLoginTime(String mobile) {
		return saleUserInfoDao.queryDisabledLoginTime(mobile);
	}

	@Override
	public List<SaleUserInfo> getAllSaleInfo(Map<String, Object> map) {
		return saleUserInfoDao.getAllSaleInfo(map);
	}

	@Override
	public int getAllSaleCounts(Map<String, Object> map) {
		return saleUserInfoDao.getAllSaleCounts(map);
	}
	

}
