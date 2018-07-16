package com.linkwee.web.service.fee.impl;
//package com.linkwee.fee.service.impl;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//
//import cn.xn.user.domain.CommonRlt;
//import cn.xn.user.domain.CustomerInfoReq2;
//import cn.xn.user.domain.CustomerInfoRlt;
//import cn.xn.user.enums.SystemType;
//import cn.xn.user.service.ICustomerInfoService;
//import cn.xn.user.utils.RequestSignUtils;
//
//import com.linkwee.web.model.fee.FeePay;
//import com.linkwee.web.model.fee.FeePayReq;
//import com.linkwee.web.remote.SmsCenterHandler;
//import com.linkwee.web.service.SystemConfigService;
//import com.xiaoniu.account.domain.SystemInRecordReq;
//import com.xiaoniu.account.service.IInRecordAndPayService;
//import com.xiaoniu.account.utils.SignUtils;
//
///**
// * 佣金发放线程
// * 
// * @author xiaojinhe
// *
// */
//public class FeePayThread extends Thread {
//	private static Logger logger = Logger.getLogger(FeePayThread.class);
//	private FeePayReq req;
//	private ICustomerInfoService p2pCustomerInfoService;
//	private IInRecordAndPayService p2pInRecordAndPayService;
//	private SystemConfigService systemConfigService;
//	private SmsCenterHandler smsCenterHandler;
//
//	public FeePayThread(FeePayReq req,
//			ICustomerInfoService p2pCustomerInfoService,
//			IInRecordAndPayService p2pInRecordAndPayService,
//			SystemConfigService systemConfigService,
//			SmsCenterHandler smsCenterHandler) {
//		this.req = req;
//		this.p2pCustomerInfoService = p2pCustomerInfoService;
//		this.p2pInRecordAndPayService = p2pInRecordAndPayService;
//		this.systemConfigService = systemConfigService;
//		this.smsCenterHandler = smsCenterHandler;
//		String name = "FeePayThread" + System.currentTimeMillis();
//		setName(name);
//	}
//
//	public void run() {
//		long start = System.currentTimeMillis();
//		StringBuilder logsb = new StringBuilder();
//		logsb.append("FeePayThread run start");
//		try {
//			List<FeePay> entitylist = FeePayUtil.getFeePayEntityList(req);
//			while (entitylist != null && entitylist.size() > 0) {
//				int size = entitylist.size();
//				FeePay entity = null;
//				for (int i = 0; i < size; i++) {
//					entity = entitylist.get(i);
//					feePayExecute(entity);
//				}
//				entitylist = FeePayUtil.getFeePayEntityList(req);
//			}
//		} catch (Throwable e) {
//			e.printStackTrace();
//			logsb.append("|Throwable e=" + e.getMessage());
//		} finally {
//			long end = System.currentTimeMillis();
//			logsb.append("|totaltime=").append(end - start);
//			logger.info(logsb.toString());
//		}
//	}
//
//	private void feePayExecute(FeePay entity) {
//		long start = System.currentTimeMillis();
//		StringBuilder logsb = new StringBuilder();
//		int id = entity.getId();
//		String mobile = entity.getMobile();
//		String billnumber = entity.getBillnumber();
//		String thread_id = String.valueOf(Thread.currentThread().getId());
//		String thread_name = Thread.currentThread().getName();
//		entity.setThread_id(thread_id);
//		entity.setThread_name(thread_name);
//		try {
//			CustomerInfoReq2 req = new CustomerInfoReq2();
//			// req.setAppVersion("1.0");
//			// req.setSystemType("QGZ");
//			// req.setSourceType("Channel");
//			// req.setLoginName(mobile);
//
//			// 用户中心接口签名调整
//			req.setAppVersion("1.0");
//			req.setSystemType(SystemType.CHANNEL.getText());
//			req.setSourceType("Channel");
//			req.setLoginName(mobile);
//			String sign = RequestSignUtils.addSign(req,getUserCenterKey());
//			req.setSign(sign);
//			logsb.append("feePayExecute|id=").append(id).append("|mobile=")
//			.append(mobile).append("|billnumber=").append(billnumber).append("|sign=")
//			.append(sign);
//			CommonRlt<CustomerInfoRlt> result = p2pCustomerInfoService
//					.getUserInfoByLoginName(req);
//			if (result.getReturnCode().intValue() != 0) {
//				// 失败重试一次
//				result = p2pCustomerInfoService.getUserInfoByLoginName(req);
//			}
//			logsb.append(
//					"|p2pCustomerInfoService.getUserInfoByLoginName[retcode=")
//					.append(result.getReturnCode().intValue())
//					.append("|retmsg=").append(result.getReturnMsg())
//					.append("]");
//			if (result.getReturnCode().intValue() != 0) {
//				// 查询客户ID失败
//				int resultcode = Integer
//						.parseInt(ResultBean.GET_CUSTOMERID_FAIL.getCode());
//				String resultmsg = new StringBuilder(
//						ResultBean.GET_CUSTOMERID_FAIL.getDesc())
//						.append("|invoke result=")
//						.append(result.getReturnCode().intValue()).append(",")
//						.append(result.getReturnMsg()).toString();
//				entity.setResultcode(resultcode);
//				entity.setResultmsg(resultmsg);
//				return;
//			}
//			CustomerInfoRlt customerInfo = result.getData();
//			String customerid = customerInfo.getMemberNo();
//			String customerName = customerInfo.getMemberName();
//			entity.setCustomerid(customerid);
//
//			// 调用交易中心系统充值接口
//			invokeTransPayPlatform(entity, customerid, customerName, logsb);
//			// 推送个人和通知栏消息
//			Map<String, Object> paramsMap = new HashMap<String, Object>();
//			paramsMap.put("userId", customerid);
//			paramsMap.put("moduleId", "COMMISION");
//			paramsMap.put("month", entity.getMonth());
//			paramsMap.put("sysConfigKey", "pushmessage_lfeereceived");
//			paramsMap.put("sysConfigappType", 1);
//			smsCenterHandler.pushMessage(paramsMap);
//		} catch (Throwable e) {
//			e.printStackTrace();
//			logsb.append("|Throwable e=").append(e.getMessage());
//			int resultcode = Integer
//					.parseInt(ResultBean.SYSTEM_ERROR.getCode());
//			String resultmsg = new StringBuilder(
//					ResultBean.SYSTEM_ERROR.getDesc()).append("|e=")
//					.append(e.getMessage()).toString();
//			entity.setResultcode(resultcode);
//			entity.setResultmsg(resultmsg);
//		} finally {
//			long totaltime = System.currentTimeMillis() - start;
//			entity.setTotaltime(totaltime);
//			feePayLog(entity); // 写佣金发放日志
//			updateFeePayStatus(entity); // 更新佣金发放记录状态
//			long end = System.currentTimeMillis();
//			logsb.append("|totaltime=").append(end - start);
//			logger.info(logsb.toString());
//		}
//	}
//
//	/**
//	 * 调用交易中心系统充值接口实现佣金发放
//	 * 
//	 * @param entity
//	 * @param customerid
//	 * @param customerName
//	 * @param logsb
//	 */
//	private void invokeTransPayPlatform(FeePay entity, String customerid,
//			String customerName, StringBuilder logsb) {
//		SystemInRecordReq req = new SystemInRecordReq();
//		String partnerId = getPartnerId();
//		req.setPartnerId(partnerId);
//		req.setPartnerTradeNo(entity.getBillnumber());
//		req.setUserId(customerid);
//		req.setUserName(customerName);
//		req.setBisType(9); // 活动佣金
//		req.setBisName("渠道佣金发放");
//		java.text.DecimalFormat df = new java.text.DecimalFormat("#0");
//		Long amount = Long.parseLong(df.format(entity.getFeeamount() * 10000));
//		req.setAmount(amount);
//		req.setRemark("渠道佣金发放");
//		req.setCharset("UTF-8");
//		String signKey = getTransMd5Key();
//		logsb.append("|signKey=").append(signKey);
//		req.setSign(SignUtils.addSign(req, req.getCharset(), signKey));
//		try {
//			com.xiaoniu.account.domain.result.CommonRlt<Map<String, String>> result = p2pInRecordAndPayService
//					.systemInRecord(req);
//			if (result.getReturnCode().intValue() != 0) {
//				// 调用失败，重试一次
//				result = p2pInRecordAndPayService.systemInRecord(req);
//			}
//			logsb.append("|p2pInRecordAndPayService.systemInRecord[retcode=")
//					.append(result.getReturnCode().intValue())
//					.append("|retmsg=").append(result.getReturnMsg())
//					.append("]");
//			if (result.getReturnCode().intValue() == 0) {
//				// 充值成功
//				entity.setResultcode(0);
//				entity.setResultmsg("佣金发放成功");
//			} else {
//				// 充值失败
//				entity.setResultcode(result.getReturnCode().intValue());
//				entity.setResultmsg(result.getReturnMsg());
//			}
//		} catch (Throwable e) {
//			e.printStackTrace();
//			logsb.append("|invokeTransPayPlatform,Throwable e=").append(
//					e.getMessage());
//			int resultcode = Integer
//					.parseInt(ResultBean.SYSTEM_ERROR.getCode());
//			String resultmsg = new StringBuilder(
//					ResultBean.SYSTEM_ERROR.getDesc()).append("|e=")
//					.append(e.getMessage()).toString();
//			entity.setResultcode(resultcode);
//			entity.setResultmsg(resultmsg);
//		}
//	}
//
//	/**
//	 * 写佣金发放日志
//	 * 
//	 * @param entity
//	 */
//	private void feePayLog(FeePay entity) {
//		StringBuilder logsb = new StringBuilder();
//		int id = entity.getId();
//		String mobile = entity.getMobile();
//		String billnumber = entity.getBillnumber();
//		logsb.append("feePayLog|id=").append(id).append("|mobile=")
//				.append(mobile).append("|billnumber=").append(billnumber);
//		StringBuilder sqlsb = new StringBuilder();
//		sqlsb.append("insert into t_fee_pay_log (");
//		sqlsb.append("f_department, f_emp_no, f_mobile, f_name, ");
//		sqlsb.append("f_fee_amount, f_bill_number, f_month, f_sale_user_no, ");
//		sqlsb.append("f_customer_id, f_result_code, f_result_msg, f_total_time, ");
//		sqlsb.append("f_thread_id, f_thread_name) ");
//		sqlsb.append("values (?, ?, ?, ?, ");
//		sqlsb.append("?, ?, ?, ?, ");
//		sqlsb.append("?, ?, ?, ?, ");
//		sqlsb.append("?, ?)");
//		logger.debug(logsb.append("|sql=").append(sqlsb).toString());
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = DBUtil.getConnection();
//			pstmt = conn.prepareStatement(sqlsb.toString());
//			pstmt.setString(1, entity.getDepartment());
//			pstmt.setString(2, entity.getEmpno());
//			pstmt.setString(3, entity.getMobile());
//			pstmt.setString(4, entity.getName());
//			pstmt.setDouble(5, entity.getFeeamount());
//			pstmt.setString(6, entity.getBillnumber());
//			pstmt.setString(7, entity.getMonth());
//			pstmt.setString(8, entity.getSaleuserno());
//			pstmt.setString(9, entity.getCustomerid());
//			pstmt.setInt(10, entity.getResultcode());
//			pstmt.setString(11, entity.getResultmsg());
//			pstmt.setInt(12, (int) entity.getTotaltime());
//			pstmt.setString(13, entity.getThread_id());
//			pstmt.setString(14, entity.getThread_name());
//			pstmt.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			logger.error("feePayLog|SQLException e=" + e.getMessage());
//		} finally {
//			DBUtil.close(pstmt, conn);
//		}
//	}
//
//	/**
//	 * 更新佣金发放记录状态
//	 * 
//	 * @param entity
//	 */
//	private void updateFeePayStatus(FeePay entity) {
//		StringBuilder logsb = new StringBuilder();
//		int id = entity.getId();
//		String mobile = entity.getMobile();
//		String billnumber = entity.getBillnumber();
//		logsb.append("feePayLog|id=").append(id).append("|mobile=")
//				.append(mobile).append("|billnumber=").append(billnumber);
//		StringBuilder sqlsb = new StringBuilder();
//		sqlsb.append("update t_fee_pay set ");
//		if (entity.getResultcode() == 0) {
//			// 发放成功
//			sqlsb.append("f_status = 2, f_result_msg = '")
//					.append(entity.getResultmsg()).append("' ");
//		} else {
//			// 发放失败
//			sqlsb.append("f_status = 3, f_result_msg = '")
//					.append(entity.getResultmsg()).append("' ");
//		}
//		sqlsb.append("where f_status = 1 and fid = ?");
//		logger.debug(logsb.append("|sql=").append(sqlsb).toString());
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = DBUtil.getConnection();
//			pstmt = conn.prepareStatement(sqlsb.toString());
//			pstmt.setInt(1, id);
//			pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			logger.error("updateFeePayStatus|SQLException e=" + e.getMessage());
//		} finally {
//			DBUtil.close(pstmt, conn);
//		}
//	}
//
//	/**
//	 * 获取交易中心分配的签名Key
//	 * 
//	 * @return
//	 */
//	private String getTransMd5Key() {
//		String md5key = systemConfigService
//				.getValue("TRANS_MD5_SIGN_KEY_FEEPAY");
//		return md5key;
//	}
//
//	private String getPartnerId() {
//		String partnerId = systemConfigService
//				.getValue("account_partnerId_feepay");
//		return partnerId;
//	}
//
//	private String getUserCenterKey() {
//		String key = systemConfigService.getValue("lh_userCenter_signKey");
//		return key;
//	}
//}