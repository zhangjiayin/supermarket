package com.xiaoniu.account.service;

import java.util.List;
import java.util.Map;

import com.xiaoniu.account.domain.AuthReq;
import com.xiaoniu.account.domain.AuthSendSmsReq;
import com.xiaoniu.account.domain.BankListReq;
import com.xiaoniu.account.domain.SearchCardInfoReq;
import com.xiaoniu.account.domain.UserBindCardReq;
import com.xiaoniu.account.domain.result.CityInfoRlt;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.EmptyObject;
import com.xiaoniu.account.domain.result.ProvinceInfoRlt;
import com.xiaoniu.account.domain.result.RecordRlt;
import com.xiaoniu.account.domain.result.UserBindCardRlt;
import com.xiaoniu.account.domain.result.UserSettleInfo;


/**
 * @Description: 支付前的准备工作接口
 * @author 周锋恒
 * @date 2015年7月23日
 *
 */
public interface IPrepare2PayService {
	
	/**
	 * 查询用户绑卡信息
	 * @param req
	 * @return
	 */
	public CommonRlt<Map<String, Object>> getUserBindCard(UserBindCardReq req);
	
	
	/**
	 * 查询用户绑卡信息
	 * @param req
	 * @return
	 */
	public CommonRlt<UserBindCardRlt> getUserBindCardNew(UserBindCardReq req);	
	
	/**
	 * 查询用户绑卡信息
	 * @param req
	 * @return
	 */
	public CommonRlt<Map<String, Object>> getUserBindCardNew2(UserBindCardReq req);

	/**
	 * 查询用户绑卡信息
	 * @param req
	 * @return
	 */
	public CommonRlt<Map<String, Object>> getUserBindCardWithWX(UserBindCardReq req);
	
	
	/**
	 * 查询用户打款信息
	 * @param req
	 * @return
	 */
	public CommonRlt<UserSettleInfo> queryUserSettleInfo(UserBindCardReq req);	
	
	/**
	 * 查询银行列表
	 * @param req
	 * @return
	 */
	public CommonRlt<Object> queryBankList(BankListReq req);
	
	/**
	 * 查找卡bin信息
	 * @param req
	 * @return
	 */
	public CommonRlt<Map<String, Object>> searchCardInfo(SearchCardInfoReq req);
	
	/**
	 * 查询省份信息
	 * @return
	 */
	public CommonRlt<List<ProvinceInfoRlt>>  queryAllProvince();
	
	
	/**
	 * 查询城市信息
	 * @return
	 */
	public CommonRlt<List<CityInfoRlt>>  queryCityByProvince(String provinceCode);

	/**
	 * 独立授权发短信
	 * @param req
	 * @return
	 */
	public CommonRlt<Map<String, Object>> authSendSms(AuthSendSmsReq req);

	/**
	 * 独立授权
	 * @param req
	 * @return
	 */
	public CommonRlt<EmptyObject> auth(AuthReq req);
	
}
