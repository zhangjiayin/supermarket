package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.response.acc.AcBankImageResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.acc.AcAccountBind;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月12日 19:10:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface AcAccountBindMapper extends GenericDao<AcAccountBind,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<AcAccountBind> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 根据用户ID查询用户的绑卡信息
	 * */
	AcAccountBind selectAccountByUserId(@Param("userId")String userId);

	/**
	 * 用户解绑银行卡
	 * */
	void acAccountUnbund(@Param("userId")String userId);
	
	/**
	 * 运营人员禁止提现
	 * */
	int isForbiWithdrawUser(@Param("userId")String userId);

	/**
	 * 发放奖励-添加金额
	 * */
	void acGrantProfit(@Param("transAmount")String transAmount, @Param("userId")String userId);

	/**
	 * 是否需要开户行
	 * */
	int isNeedkaiHuHang(@Param("bankCardId")String bankCardId);

	/**
	 * 测试绑卡用
	 * */
	List<AcAccountBind> selectBankByBankName();

	/**
	 * 修改绑卡次数
	 * */
	void updateBindTimes(AcAccountBind upbind);

	/**
	 * 判断身份证的唯一性 
	 * */
	List<AcAccountBind> checkIdCardOnly(@Param("idCard")String idCard);

	/**
	 * 理财师活动奖励
	 * @param userId
	 * @return
	 */
	Double queryCfpActivityReward(@Param("userId")String userId, @Param("month")String month);
	
	/**
	 * 理财师活动奖励v2.1
	 * @param userId
	 * @return
	 */
	Double queryCfpActivityRewardNew(@Param("userId")String userId, @Param("month")String month);
	
	/**
	 * 红包收益v2.1
	 * @param userId
	 * @return
	 */
	Double queryHongbaoProfit(@Param("userId")String userId, @Param("month")String month);
	
	/**
	 * 已发放收益v2.1
	 * @param userId
	 * @return
	 */
	Double queryGrantedAmount(@Param("userId")String userId, @Param("month")String month);
	
	/**
	 * 待发放收益v2.1
	 * @param userId
	 * @return
	 */
	Double queryWaitGrantAmount(@Param("userId")String userId, @Param("month")String month);

	/**
	 * 查已实名未设置头像的理财师用户
	 * @return
	 */
	List<AcAccountBind> queryCfpOfNotSetImage();

	/**
	 * 查询用户的绑卡信息 
	 * @return
	 */
	AcAccountBind selectBindAcctByUserId(@Param("userId")String userId);

	AcBankImageResponse selectBankImage(@Param("bankCode")String bankCode);
	

}
