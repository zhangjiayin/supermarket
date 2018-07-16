package com.xiaoniu.account.service;

import com.xiaoniu.account.comm.page.PageResult;
import com.xiaoniu.account.domain.AuditOutRecordReq;
import com.xiaoniu.account.domain.BatchAuditOutRecordReq;
import com.xiaoniu.account.domain.DealOutRecordReq;
import com.xiaoniu.account.domain.QueryOutExpectReachTimeReq;
import com.xiaoniu.account.domain.SearchInRecordPageReq;
import com.xiaoniu.account.domain.SearchInvestRecordPageReq;
import com.xiaoniu.account.domain.SearchOutRecordPageReq;
import com.xiaoniu.account.domain.SearchOutRecordReq;
import com.xiaoniu.account.domain.SearchReturnRecordPageReq;
import com.xiaoniu.account.domain.SystemInRecordReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.EmptyObject;
import com.xiaoniu.account.domain.result.InRecordBackRlt;
import com.xiaoniu.account.domain.result.InvestRecordRlt;
import com.xiaoniu.account.domain.result.OutExpectReachTimeBackRlt;
import com.xiaoniu.account.domain.result.OutRecordRlt;
import com.xiaoniu.account.domain.result.ReturnRecordRlt;

import java.util.Map;

/**
 * 
 * @Description: 后台管理系统调用接口
 * @author 周锋恒
 * @date 2015年8月4日
 *
 */
public interface IRecordBackgroundService {

	
	/**
	 * 后台查询提现记录
	 * @param req
	 * @return  分页对象
	 */
	public CommonRlt<PageResult<OutRecordRlt>> outRecordListPage(SearchOutRecordPageReq req);

	/**
	 * 后台查询单条提现记录
	 * @param req
	 * @return
	 */
	public CommonRlt<OutRecordRlt> singleOutRecord(SearchOutRecordReq req);
	
	
	/**
	 * 后台审核提现操作
	 * @param req
	 * @return
	 */
	public CommonRlt<EmptyObject> auditOutRecord(AuditOutRecordReq req);

	/**
	 * 后台审核提现操作
	 * @param req
	 * @return
	 */
	public CommonRlt<EmptyObject> batchAuditOutRecord(BatchAuditOutRecordReq req);

	/**
	 * 接收提现通知
	 * @param map
	 * @return
	 */
	public CommonRlt<EmptyObject> acceptOutNotice(Map<String, String[]> map);
	
	/**
	 * 自动审核
	 * @param req
	 * @return
	 */
	public CommonRlt<EmptyObject> autoAuditOutRecord(AuditOutRecordReq req);

	/**
	 * 后台手动解冻提现操作
	 * @param outRecordNo
	 * @return
	 */
	public CommonRlt<EmptyObject> unFrozenOut(Long outRecordNo);
	
	/**
	 * 查询充值记录
	 * @param req
	 * @return  分页对象
	 */
	public CommonRlt<PageResult<InRecordBackRlt>> inRecordListPage(SearchInRecordPageReq req);
	
	/**
	 * 查询投资记录
	 * @param req
	 * @return  分页对象
	 */
	public CommonRlt<PageResult<InvestRecordRlt>> investRecordListPage(SearchInvestRecordPageReq req);

	/**
	 * 查询回款记录
	 * @param req
	 * @return  分页对象
	 */
	public CommonRlt<PageResult<ReturnRecordRlt>> returnRecordListPage(SearchReturnRecordPageReq req);
	
	/**
	 * 非常特殊的操作
	 * @param req
	 * @return
	 */
	public CommonRlt<EmptyObject> dealOutRecord(DealOutRecordReq req);

	/**
	 * 系统的各类型充值
	 * @param req
	 * @return Map {inRecordNo:充值流水号}
	 */
	public CommonRlt<Map<String, String>> systemInRecord(SystemInRecordReq req);

	/**
	 * 查询提现预计到账时间
	 * @param req
	 * @return
	 */
	public CommonRlt<OutExpectReachTimeBackRlt> queryOutRecordExpectedArrivalTime(QueryOutExpectReachTimeReq req);
	
}
