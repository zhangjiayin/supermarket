package com.xiaoniu.account.service;

import java.util.Map;

import com.xiaoniu.account.comm.page.PageResult;
import com.xiaoniu.account.domain.CancelInvestOrderReq;
import com.xiaoniu.account.domain.ConfirmInvestOrderReq;
import com.xiaoniu.account.domain.InvestOrderReq;
import com.xiaoniu.account.domain.InvestRecordReq;
import com.xiaoniu.account.domain.QueryInvestRecordReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.EmptyObject;
import com.xiaoniu.account.domain.result.InvestRecordRlt;
import com.xiaoniu.account.domain.result.RecordRlt;

/**
 * 
 * @Description: 投资相关接口
 * @author 周锋恒
 * @date 2015年7月24日
 *
 */
public interface IInvestRecordSOAService {	
	
	

	/**
	 * 用户投资操作，记录投资记录
	 * @param req
	 * @return Map {investRecordNo:投资流水号}
	 */
	public CommonRlt<Map<String, Object>> userInvestRecord(InvestRecordReq req);
	
	/**
	 * 用户预投资下单
	 * @param req
	 * @return
	 */
	public CommonRlt<RecordRlt> investOrder(InvestOrderReq req);
	
	/**
	 * 投资入口
	 * @param req
	 * @return
	 */
	public CommonRlt<RecordRlt> confirmInvestOrder(ConfirmInvestOrderReq req);
	
	/**
	 * 取消认筹，单笔
	 * @param req
	 * @return
	 */
	public CommonRlt<EmptyObject> cancelInvestOrder(CancelInvestOrderReq req);
	
	
	/**
	 * 查询投资记录
	 * @param req
	 * @return  分页对象
	 */
	public CommonRlt<PageResult<InvestRecordRlt>> investRecordListPage(QueryInvestRecordReq req);
	
}
