package com.xiaoniu.account.service;

import com.xiaoniu.account.domain.CheckPayPwdReq;
import com.xiaoniu.account.domain.CheckPayPwdStatusReq;
import com.xiaoniu.account.domain.IsSetPayPwdReq;
import com.xiaoniu.account.domain.ResetPayPwdReq;
import com.xiaoniu.account.domain.SetPayPwdReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.EmptyObject;

/**
 * 
 * @Description: 用户资产扩展信息相关操作接口。
 * @author 颜彩云
 * @date 2015年12月2日
 *
 */
public interface IPayPasswordSOAService {

	/**
	 * 验证支付密码操作
	 * @param model
	 * @return true:密码正确,false:密码错误
	 * @throws Exception
	 */
	CommonRlt<Boolean> checkPayPassword(CheckPayPwdReq req);
	
	/**
	 * 验证支付密码状态  提供给支付系统使用
	 * @param model
	 * @return true:存在,false:不存在
	 * @throws Exception
	 */
	CommonRlt<Boolean> checkPayStatus(CheckPayPwdStatusReq req);
	
	/**
	 * 设置支付密码操作
	 * @param model
	 * @return
	 * @throws Exception
	 */
	CommonRlt<EmptyObject> setPayPassword(SetPayPwdReq req);
	
	/**
	 * 设置支付密码操作
	 * @param model
	 * @return
	 * @throws Exception
	 */
	CommonRlt<EmptyObject> resetPayPassword(ResetPayPwdReq req);
	
	/**
	 * 是否设置了支付密码
	 * @param model
	 * @return true:已经设置,false:未设置支付密码
	 * @throws Exception
	 */
	CommonRlt<Boolean> isSetPayPassword(IsSetPayPwdReq req);
}
