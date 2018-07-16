package com.linkwee.web.service.fee.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.xn.user.domain.CommonRlt;
import cn.xn.user.domain.CustomerInfoReq2;
import cn.xn.user.domain.CustomerInfoRlt;
import cn.xn.user.enums.SystemType;
import cn.xn.user.service.ICustomerInfoService;
import cn.xn.user.utils.RequestSignUtils;

import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.base.SuccessCode;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.FeePayDao;
import com.linkwee.web.dao.FeePayLogDao;
import com.linkwee.web.model.fee.FeeDetail;
import com.linkwee.web.model.fee.FeePay;
import com.linkwee.web.model.fee.FeePayLog;
import com.linkwee.web.model.fee.StatusSetReq;
import com.linkwee.web.remote.SmsCenterHandler;
import com.linkwee.web.request.FeeRequest;
import com.linkwee.web.service.FeePayService;
import com.linkwee.web.service.SystemConfigService;
import com.xiaoniu.account.domain.SystemInRecordReq;
import com.xiaoniu.account.service.IInRecordAndPayService;
import com.xiaoniu.account.utils.SignUtils;

@Service("feePayService")
public class FeePayServiceImpl implements FeePayService {
	private static Logger logger = Logger.getLogger(FeePayServiceImpl.class);

	@Autowired
	private FeePayDao feePayDao;

	@Autowired
	private FeePayLogDao feePayLogDao;

	@Resource
	private ICustomerInfoService p2pCustomerInfoService;
	@Resource
	private IInRecordAndPayService p2pInRecordAndPayService;
	@Resource
	private SystemConfigService systemConfigService;
	@Resource
	private SmsCenterHandler smsCenterHandler;

	@Override
	public ReturnCode feeDataImport(List<FeePay> datalist) {
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		if (null != datalist && !datalist.isEmpty()) {
			feePayDao.addBatch(datalist);
		}
		long end = System.currentTimeMillis();
		logsb.append("|totaltime=").append(end - start);
		return new SuccessCode();
	}

	@Override
	public ReturnCode feePay(List<FeePay> entitylist) {
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		logsb.append("FeePayThread run start");
		try {
			while (entitylist != null && entitylist.size() > 0) {
				int size = entitylist.size();
				FeePay entity = null;
				for (int i = 0; i < size; i++) {
					entity = entitylist.get(i);
					if (entity.getStatus() == 0) {// 更改t_fee_pay状态为正在发放中
						entity.setStatus(1);
						feePayDao.update(entity);
					}
					feePayExecute(entity);
					if (entity.getStatus() != 0) {
						entitylist.remove(i);
						i--;
					}
					if (entitylist.size() == 0)
						break;
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
			logsb.append("|Throwable e=" + e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return new SuccessCode();
	}

	@Override
	public DataTableReturn feePayResultQuery(FeeRequest req) {
		String month = req.getMonth();
		List<String> saleuserlist = new ArrayList<String>();
		if (!StringUtils.isEmpty(req.getSaleUser())) {
			saleuserlist.add(req.getSaleUser());
		}
		int qtype = req.getQuerytype();
		Page<FeeDetail> page = new Page<FeeDetail>(req.getPageIndex(),
				req.getPageSize());
		List<FeePay> entitylist = feePayDao.selectByCondition(month,
				saleuserlist, qtype, page);
		DataTableReturn dataTableReturn = new DataTableReturn();
		dataTableReturn.setData(entitylist);
		dataTableReturn.setDraw(0);
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		return dataTableReturn;
	}

	@Override
	public ReturnCode statusReset(StatusSetReq req) {
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		String month = req.getMonth();
		logsb.append("statusReset|month=").append(month);
		feePayDao.updateByMonthSaleUser(month, req.getSaleuserlist());
		long end = System.currentTimeMillis();
		logsb.append("|totaltime=").append(end - start);
		logger.info(logsb.toString());
		return new SuccessCode();
	}

	private void executeFeePay(String month, List<String> saleUserNo) {
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		logsb.append("FeePayThread run start");
		try {
			List<FeePay> entitylist = getUnPayList(month, saleUserNo);
			while (entitylist != null && entitylist.size() > 0) {
				int size = entitylist.size();
				FeePay entity = null;
				for (int i = 0; i < size; i++) {
					entity = entitylist.get(i);
					if (entity.getStatus() == 0) {// 更改t_fee_pay状态为正在发放中
						entity.setStatus(1);
						feePayDao.update(entity);
					}
					feePayExecute(entity);
					if (entity.getStatus() != 0) {
						entitylist.remove(i);
						i--;
					}
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
			logsb.append("|Throwable e=" + e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
	}

	public List<FeePay> getUnPayList(String month, List<String> saleUserNo) {
		List<FeePay> list = feePayDao
				.selectByMonthSaleuserNo(month, saleUserNo);
		return list;
	}

	private void feePayExecute(FeePay entity) {
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		int id = entity.getId();
		String mobile = entity.getMobile();
		String billnumber = entity.getBillnumber();
		String thread_id = String.valueOf(Thread.currentThread().getId());
		String thread_name = Thread.currentThread().getName();
		entity.setThread_id(thread_id);
		entity.setThread_name(thread_name);
		try {
			CustomerInfoReq2 req = new CustomerInfoReq2();
			// 用户中心接口签名调整
			req.setAppVersion("1.0");
			req.setSystemType(SystemType.CHANNEL.getText());
			req.setSourceType("Channel");
			req.setLoginName(mobile);
			String sign = RequestSignUtils.addSign(req, getUserCenterKey());
			req.setSign(sign);
			logsb.append("feePayExecute|id=").append(id).append("|mobile=")
					.append(mobile).append("|billnumber=").append(billnumber)
					.append("|sign=").append(sign);
			CommonRlt<CustomerInfoRlt> result = p2pCustomerInfoService
					.getUserInfoByLoginName(req);
			if (result.getReturnCode().intValue() != 0) {
				// 失败重试一次
				result = p2pCustomerInfoService.getUserInfoByLoginName(req);
			}
			logsb.append(
					"|p2pCustomerInfoService.getUserInfoByLoginName[retcode=")
					.append(result.getReturnCode().intValue())
					.append("|retmsg=").append(result.getReturnMsg())
					.append("]");
			if (result.getReturnCode().intValue() != 0) {
				// 查询客户ID失败
				int resultcode = Integer
						.parseInt(ResultBean.GET_CUSTOMERID_FAIL.getCode());
				String resultmsg = new StringBuilder(
						ResultBean.GET_CUSTOMERID_FAIL.getDesc())
						.append("|invoke result=")
						.append(result.getReturnCode().intValue()).append(",")
						.append(result.getReturnMsg()).toString();
				entity.setResultcode(resultcode);
				entity.setResultmsg(resultmsg);
				return;
			}
			CustomerInfoRlt customerInfo = result.getData();
			String customerid = customerInfo.getMemberNo();
			String customerName = customerInfo.getMemberName();
			entity.setCustomerid(customerid);

			// 调用交易中心系统充值接口
			invokeTransPayPlatform(entity, customerid, customerName, logsb);
			// 推送个人和通知栏消息
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("userId", customerid);
			paramsMap.put("moduleId", "COMMISION");
			paramsMap.put("month", entity.getMonth());
			paramsMap.put("sysConfigKey", "pushmessage_lfeereceived");
			paramsMap.put("sysConfigappType", 1);
			smsCenterHandler.pushMessage(paramsMap);
		} catch (Throwable e) {
			e.printStackTrace();
			logsb.append("|Throwable e=").append(e.getMessage());
			int resultcode = Integer
					.parseInt(ResultBean.SYSTEM_ERROR.getCode());
			String resultmsg = new StringBuilder(
					ResultBean.SYSTEM_ERROR.getDesc()).append("|e=")
					.append(e.getMessage()).toString();
			entity.setResultcode(resultcode);
			entity.setResultmsg(resultmsg);
		} finally {
			long totaltime = System.currentTimeMillis() - start;
			entity.setTotaltime(totaltime);
			feePayLog(entity); // 写佣金发放日志
			updateFeePayStatus(entity); // 更新佣金发放记录状态
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
	}

	/**
	 * 调用交易中心系统充值接口实现佣金发放
	 * 
	 * @param entity
	 * @param customerid
	 * @param customerName
	 * @param logsb
	 */
	private void invokeTransPayPlatform(FeePay entity, String customerid,
			String customerName, StringBuilder logsb) {
		SystemInRecordReq req = new SystemInRecordReq();
		String partnerId = getPartnerId();
		req.setPartnerId(partnerId);
		req.setPartnerTradeNo(entity.getBillnumber());
		req.setUserId(customerid);
		req.setUserName(customerName);
		req.setBisType(9); // 活动佣金
		req.setBisName("渠道佣金发放");
		java.text.DecimalFormat df = new java.text.DecimalFormat("#0");
		Long amount = Long.parseLong(df.format(entity.getFeeamount() * 10000));
		req.setAmount(amount);
		req.setRemark("渠道佣金发放");
		req.setCharset("UTF-8");
		String signKey = getTransMd5Key();
		logsb.append("|signKey=").append(signKey);
		req.setSign(SignUtils.addSign(req, req.getCharset(), signKey));
		try {
			com.xiaoniu.account.domain.result.CommonRlt<Map<String, String>> result = p2pInRecordAndPayService
					.systemInRecord(req);
			if (result.getReturnCode().intValue() != 0) {
				// 调用失败，重试一次
				result = p2pInRecordAndPayService.systemInRecord(req);
			}
			logsb.append("|p2pInRecordAndPayService.systemInRecord[retcode=")
					.append(result.getReturnCode().intValue())
					.append("|retmsg=").append(result.getReturnMsg())
					.append("]");
			if (result.getReturnCode().intValue() == 0) {
				// 充值成功
				entity.setResultcode(0);
				entity.setResultmsg("佣金发放成功");
			} else {
				// 充值失败
				entity.setResultcode(result.getReturnCode().intValue());
				entity.setResultmsg(result.getReturnMsg());
			}
		} catch (Throwable e) {
			e.printStackTrace();
			logsb.append("|invokeTransPayPlatform,Throwable e=").append(
					e.getMessage());
			int resultcode = Integer
					.parseInt(ResultBean.SYSTEM_ERROR.getCode());
			String resultmsg = new StringBuilder(
					ResultBean.SYSTEM_ERROR.getDesc()).append("|e=")
					.append(e.getMessage()).toString();
			entity.setResultcode(resultcode);
			entity.setResultmsg(resultmsg);
		}
	}

	/**
	 * 写佣金发放日志
	 * 
	 * @param entity
	 */
	private void feePayLog(FeePay entity) {
		FeePayLog feePayLog = new FeePayLog(entity);
		feePayLogDao.add(feePayLog);
	}

	/**
	 * 更新佣金发放记录状态
	 * 
	 * @param entity
	 */
	private void updateFeePayStatus(FeePay entity) {
		StringBuilder logsb = new StringBuilder();
		int id = entity.getId();
		String mobile = entity.getMobile();
		String billnumber = entity.getBillnumber();
		logsb.append("feePayLog|id=").append(id).append("|mobile=")
				.append(mobile).append("|billnumber=").append(billnumber);
		if (entity.getStatus() == 1) {
			if (entity.getResultcode() == 0) { // 发放成功
				entity.setStatus(2);
			} else {// 发放失败
				entity.setStatus(3);
			}
			feePayDao.update(entity);
		}
	}

	/**
	 * 获取交易中心分配的签名Key
	 * 
	 * @return
	 */
	private String getTransMd5Key() {
		//把t_system_cofnig中的TRANS_MD5_SIGN_KEY_FEEPAY改为t_system_config_new中的lhlc_trans_md5_sign_key
		String md5key = systemConfigService
				.getValuesByKey("lhlc_trans_md5_sign_key");
		return md5key;
	}

	private String getPartnerId() {
		//把t_system_cofnig中的account_partnerId_feepay改为t_system_config_new中的lhlc_account_partnerId
		String partnerId = systemConfigService
				.getValuesByKey("lhlc_account_partnerId");
		return partnerId;
	}

	private String getUserCenterKey() {
		//把t_system_cofnig中的lh_userCenter_signKey改为t_system_config_new中的lh_userCenter_signKey
		String key = systemConfigService
				.getValuesByKey("lh_userCenter_signKey");
		return key;
	}

	@Override
	public void updateFeePayStatus(List<FeePay> list) {
		if(null!=list && !list.isEmpty()){
			List<Integer> ids=new ArrayList<Integer>();
			for (FeePay feePay : list) {
				ids.add(feePay.getId());
			}
			feePayDao.updateBatchStatus(ids, 1);
		}
	}
}
