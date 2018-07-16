/*
 * 文件名: ICustomerInfoService.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package cn.xn.user.service;

import java.util.List;

import cn.xn.user.domain.BindInfoRlt;
import cn.xn.user.domain.CertReq;
import cn.xn.user.domain.CheckUserRlt;
import cn.xn.user.domain.CommonRlt;
import cn.xn.user.domain.CustomerInfoBaseRlt;
import cn.xn.user.domain.CustomerInfoByCertNoReq;
import cn.xn.user.domain.CustomerInfoReq;
import cn.xn.user.domain.CustomerInfoReq2;
import cn.xn.user.domain.CustomerInfoRlt;
import cn.xn.user.domain.CustomerListReq;
import cn.xn.user.domain.CustomerListRlt;
import cn.xn.user.domain.DeviceInfoReq;
import cn.xn.user.domain.DeviceInfoRlt;
import cn.xn.user.domain.EmptyObject;
import cn.xn.user.domain.LevelFlagUpdateReq;
import cn.xn.user.domain.LevelUpdateReq;
import cn.xn.user.domain.LevelUpdateRlt;
import cn.xn.user.domain.MemberLevelInfoRlt;
import cn.xn.user.domain.NameFlagReq;
import cn.xn.user.domain.PageCommonRlt;
import cn.xn.user.domain.StatisticReq;
import cn.xn.user.domain.StatisticRlt;
import cn.xn.user.domain.UpdateCustomerInoReq;
import cn.xn.user.domain.UpdateMemberNameReq;
import cn.xn.user.domain.UpdateRefereeReq;
import cn.xn.user.domain.UpdateScanFlagReq;

/**
 *
 * @类描述：用户信息服务
 *
 * @创建人：luohao
 *
 * @创建时间：2016年8月3日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public interface ICustomerInfoService {
    
    /**
     * 插入设备信息
     * @param uid
     * @return
     * @throws Exception
     */
    public CommonRlt<EmptyObject> updateDeviceInfo(DeviceInfoReq model);
    
    /**
     * 插入设备信息
     * @param uid
     * @return
     * @throws Exception
     */
    public CommonRlt<EmptyObject> createDeviceInfo(DeviceInfoReq model);        
    
    /**
     * 根据用户编码查询设备信息
     * @param uid
     * @return
     * @throws Exception
     */
    public CommonRlt<DeviceInfoRlt> getDeviceInfo(CustomerInfoReq model);   

    /**
     * 查询用户信息,提供给业务系统调用
     * @param uid
     * @return
     * @throws Exception
     */
    public CommonRlt<CustomerInfoRlt> getUserInfo(CustomerInfoReq model);
    
    /**
     * 根据身份证号查询用户信息,提供给业务系统调用
     * @param uid
     * @return
     * @throws Exception
     */
    public CommonRlt<List<CustomerInfoRlt>> getUserInfoByCertNo(CustomerInfoByCertNoReq model);
    
    /**
     * 根据登录名查询用户信息,提供给渠道系统调用
     * @param 
     * @return
     * @throws Exception
     */
    public CommonRlt<CustomerInfoRlt> getUserInfoByLoginName(CustomerInfoReq2 model);

    /**
     * 更新身份信息
     * @param model
     * @return
     * @throws Exception
     */
    public CommonRlt<EmptyObject> updateCertInfo(CertReq model);
    
    
    /**
     * 更新用户实名标识
     * @param model
     * @return
     * @throws Exception
     */
    public CommonRlt<EmptyObject> updateNameFlag(NameFlagReq model);
    
    
    
    /**
     * 检查用户是否实名认证，是否设置交易密码，获取实名认证信息
     * @param model
     * @return
     * @throws Exception
     */
    public CommonRlt<CheckUserRlt> checkUser(CustomerInfoReq model);    
    
    /**
     * 根据用户id获取手机号
     * @param model
     * @return
     * @
     */
    public CommonRlt<String> findMobileById(CustomerInfoReq model); 
    
    /**
     * 更新用户信息 提供给支付服务系统调用
     * @param model
     * @return
     * @
     */
    public CommonRlt<EmptyObject> updateCustomerInfo(UpdateCustomerInoReq model);
    
    /**
     * 获取用户基本信息 提供给支付服务系统调用
     * @param model
     * @return
     * @
     */
    public CommonRlt<CustomerInfoBaseRlt> getCustomerInfo(CustomerInfoReq model);
    
    /**
     * 根据用户投资金额更新用户会员等级
     * @param model
     * @return
     * @
     */
    public CommonRlt<LevelUpdateRlt> updateMemberLevel(LevelUpdateReq model);   
    
    
    /**
     * 根据用户投资金额更新用户会员等级
     * @param model
     * @return
     * @
     */
    public CommonRlt<MemberLevelInfoRlt> doGetMemberLevelInfo(CustomerInfoReq model);   
    
    /**
     * 根据用户投资金额更新用户会员等级
     * @param model
     * @return
     * @
     */
    public CommonRlt<EmptyObject> updateMemberLevelFlag(LevelFlagUpdateReq model);
    
    /**
     * 获取用户绑定信息
     * @param model
     * @return
     */
    public CommonRlt<BindInfoRlt> getBindInfo(CustomerInfoReq model);
    /**
     * 更新用户信息（姓名和身份证）
     * @param req
     * @return
     */
    public CommonRlt<EmptyObject> updateMemberName(UpdateMemberNameReq req);
    
	/**
	 * 获取部分统计数据，如 注册人数
	 * @return
	 */
	public CommonRlt<StatisticRlt> getMemberCount(StatisticReq req);
	/**
	 * 获取用户信息列表
	 * @param req
	 * @return
	 */
	public PageCommonRlt<List<CustomerListRlt>> getCustomerList(CustomerListReq req);
	
	public CommonRlt<EmptyObject> updateScanFlag(UpdateScanFlagReq req) ;
	
	/**
	 * 更新推荐人信息
	 * @param req
	 * @return
	 */
	public CommonRlt<Boolean> updateRefereeInfo(UpdateRefereeReq req);
}
