package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.linkwee.core.base.PaginatorSevReq;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.base.ServiceResponse;
import com.linkwee.core.base.SuccessCode;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.web.dao.FeerateDao;
import com.linkwee.web.dao.InvestorProfitStatisticDao;
import com.linkwee.web.dao.SaleUserInfoDao;
import com.linkwee.web.dao.StatisticCustomerDao;
import com.linkwee.web.dao.UnconventionalRecordDao;
import com.linkwee.web.dao.UsercustomerrelDao;
import com.linkwee.web.enums.UnconventionalTypeEnum;
import com.linkwee.web.model.ChangeLcsRecord;
import com.linkwee.web.model.Feerate;
import com.linkwee.web.model.InvestRecord;
import com.linkwee.web.model.InvestRecordReq;
import com.linkwee.web.model.InvestorProfitResp;
import com.linkwee.web.model.InvestorReq;
import com.linkwee.web.model.InvestorResp;
import com.linkwee.web.model.MyInvestedCustomerResp;
import com.linkwee.web.model.SaleUserInfo;
import com.linkwee.web.model.UnconventionalRecord;
import com.linkwee.web.model.Usercustomerrel;
import com.linkwee.web.service.CustomerCftRelFixWebService;
import com.linkwee.web.service.SaleUserInfoService;
import com.linkwee.web.util.PaginatorUtil;
import com.xiaoniu.mybatis.paginator.domain.PageList;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;


/**
* 
* @描述： 客户服务类
* 
* @创建人： chenchy
* 
* @创建时间：2015年08月08日 17:27:15
* 
* Copyright (c) 深圳市小牛科技有限公司-版权所有
*/
@Service("customerCftRelFixService")
public class CustomerCftRelFixServiceImpl implements CustomerCftRelFixWebService{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UnconventionalRecordDao unconventionalRecordDao;
	
	@Autowired
	private UsercustomerrelDao usercustomerrelDao;
	
	@Autowired
	private SaleUserInfoDao saleUserInfoDao;
	@Resource
	private SaleUserInfoService saleUserInfoService;
	
	@Autowired
	private FeerateDao feerateDao;
	
	
	@Autowired
	private InvestorProfitStatisticDao investorProfitStatisticDao;
	@Autowired
	private StatisticCustomerDao statisticCustomerDao;

	

	@Override
	public ServiceResponse<String> boundCfpForCustomer(Usercustomerrel usercustomerrel,UnconventionalRecord ur) {
		StringBuffer logsMsg = new StringBuffer();
		logsMsg.append("客户：[").append(usercustomerrel.getCustomermobile()).append("]绑定理财师[").append(usercustomerrel.getCurrsaleuser());
		//确定输入的理财师手机号是已经存在的理财师
		SaleUserInfo condit = new SaleUserInfo();
		condit.setMobile(usercustomerrel.getCurrsaleuser());
		SaleUserInfo saleUser =  saleUserInfoDao.query(condit);
		if(saleUser ==null ){
			logsMsg.append("]找不到对于理财师信息");
			logger.error(logsMsg.toString());
			return new ServiceResponse<String>(Error.SALE_USER_NOT_EXIST);
		}
		//检查客户信息是否存在
		Usercustomerrel rcrCondit = new Usercustomerrel();
		rcrCondit.setCustomermobile(usercustomerrel.getCustomermobile());
		Usercustomerrel customer = usercustomerrelDao.query(rcrCondit);
		if(customer == null){
			logsMsg.append("]找不到对于客户信息");
			logger.error(logsMsg.toString());
			return new ServiceResponse<String>(Error.CUSTOMER_NOT_EXIST);
		}
		if(customer != null && !StringUtils.isEmpty(customer.getCurrsaleuser())){
			logsMsg.append("]客户为非自由客户");
			logger.error(logsMsg.toString());
			return new ServiceResponse<String>(Error.CUSTOMER_NOT_FREE);
		}
		
		ur.setLcsNumber(saleUser.getNumber());
		ur.setCfpMobile(usercustomerrel.getCurrsaleuser());
		ur.setCustomerMobile(usercustomerrel.getCustomermobile());
		unconventionalRecordDao.add(ur);
		usercustomerrelDao.updateCftForCustomer(usercustomerrel);
		return new ServiceResponse<String>("绑定成功");
	}

	@Override
	public ServiceResponse<Integer> cleanRelForCustomer(Object[] objects,String opName,String opNumber) {
		StringBuffer logsMsg = new StringBuffer();
		//客户对于理财师信息
		String customerMobile = String.valueOf(objects[0]);
		Usercustomerrel relCondit = new Usercustomerrel();
		relCondit.setCustomermobile(customerMobile);
		List<Usercustomerrel> customerList = usercustomerrelDao.list(relCondit);
		SaleUserInfo saleUserCfp = null;
		if(customerList!=null && customerList.size()>0){
			
			saleUserCfp = saleUserInfoDao.getByPrimaryKey(customerList.get(0).getCurrsaleuser());
			if(saleUserCfp ==null || (saleUserCfp !=null && StringUtils.isEmpty(saleUserCfp.getNumber()))){
				logsMsg.append("客户：[").append(customerMobile).append("]对应的理财师:").append(customerList.get(0).getCurrsaleuser()).append("不存在");
				logger.error(logsMsg.toString());
				return new ServiceResponse<Integer>(Error.SALE_USER_NOT_EXIST);
			}
		}else{
			logsMsg.append("客户：[").append(customerMobile).append("]解绑时找不到对于的理财师");
			logger.error(logsMsg.toString());
			return new ServiceResponse<Integer>(Error.CUSTOMER_NOT_EXIST);
		}
		 
		//记录操作日记
		List<UnconventionalRecord> urList = new ArrayList<UnconventionalRecord>();
		for(int i=0;i<objects.length;i++){
		UnconventionalRecord ur = new UnconventionalRecord();
		ur.setLcsNumber(saleUserCfp.getNumber());
		ur.setOptType(UnconventionalTypeEnum.BEFREECUSTOMER.getCode());
		ur.setEffectiveTime(new Date());
		ur.setCrtTime(new Date());
		ur.setModifyTime(new Date());
		ur.setOptUserName(StringUtils.isEmpty(opName) ? "admin" :opName);
		ur.setOptUserNumber(StringUtils.isEmpty(opNumber) ? "admin" :opNumber);
		ur.setRemark(UnconventionalTypeEnum.BEFREECUSTOMER.getMessage());
		ur.setCfpMobile(saleUserCfp.getMobile());
		ur.setCustomerMobile(String.valueOf(objects[i]));
		ur.setExtended(JSON.toJSONString(ur));
		urList.add(ur);
		}
		unconventionalRecordDao.addBatch(urList);
		int record = usercustomerrelDao.cleanRelForCustomer(objects);
		//关系表解除绑定
		if(record < 1){
			logsMsg.append("客户：[").append(customerMobile).append("]执行解绑时出错");
			logger.error(logsMsg.toString());
			return new ServiceResponse<Integer>(Error.DB_ERROR);
		}
		return new ServiceResponse<Integer>(record);
	}

	@Override
	public ReturnCode exitFromCfp(String mobile,String opName,String opNumber) {
		
		StringBuffer logsMsg = new StringBuffer();
		logsMsg.append("理财师：[").append(mobile).append("]退出时");
		/**
		 * 1：记录操作(理财师变更为客户)
          2记录用户变更理财师
		   3更改理财师下面的客户的理财师
		   4删除理财师信息
		 */
		SaleUserInfo condit = new SaleUserInfo();
		condit.setMobile(mobile);
		SaleUserInfo saleUser = saleUserInfoDao.query(condit);
		if(saleUser == null){
			logsMsg.append("]找不到对于理财师信息");
			logger.error(logsMsg.toString());
			return Error.CUSTOMER_NOT_EXIST; 
		}
		List<UnconventionalRecord> urList = new ArrayList<UnconventionalRecord>();
		/**
		 * 记录操作：理财师变更为客户
		 */
		UnconventionalRecord ur = new UnconventionalRecord();
		ur.setLcsNumber(saleUser.getNumber());
		ur.setOptType(UnconventionalTypeEnum.REJECT.getCode());
		ur.setEffectiveTime(new Date());
		ur.setCrtTime(new Date());
		ur.setOptUserName(StringUtils.isEmpty(opName) ? "admin" :opName);
		ur.setOptUserNumber(StringUtils.isEmpty(opNumber) ? "admin" :opNumber);
		ur.setRemark(UnconventionalTypeEnum.REJECT.getMessage());
		ur.setCfpMobile(saleUser.getMobile());
		ur.setExtended(JSON.toJSONString(ur));
		urList.add(ur);
		
		unconventionalRecordDao.addBatch(urList);
		
		/**
		 * 更改理财师下面的客户为自由客户
		 */
		usercustomerrelDao.beFreeCustomer(saleUser.getNumber());
		/**
		 * 物理删除先备份
		 */
		saleUserInfoDao.backSaleUserByNumber(saleUser.getNumber());
		/**
		 * 删除理财师信息
		 */
		saleUserInfoDao.phDeleteByNumber(saleUser.getNumber());
		return new SuccessCode();
	}

	@Override
	public ReturnCode beFreeCfp(Object[] objects,String opName,String opNumber) {
		StringBuffer logsMsg = new StringBuffer();
		logsMsg.append("理财师：[").append(String.valueOf(objects[0])).append("]转为自由理财师时");
		//理财师信息
		SaleUserInfo condit = new SaleUserInfo();
		condit.setMobile(String.valueOf(objects[0]));
		SaleUserInfo saleUser = saleUserInfoDao.query(condit);
		if(saleUser == null){
			logsMsg.append("]找不到对于理财师信息");
			logger.error(logsMsg.toString());
			return Error.CUSTOMER_NOT_EXIST;
		}
		//记录操作日记
		UnconventionalRecord ur = new UnconventionalRecord();
		ur.setLcsNumber(saleUser.getNumber());
		ur.setOptType(UnconventionalTypeEnum.BEFREECFP.getCode());
		ur.setEffectiveTime(new Date());
		ur.setCrtTime(new Date());
		ur.setOptUserName(StringUtils.isEmpty(opName) ? "Admin" :opName);
		ur.setOptUserNumber(StringUtils.isEmpty(opNumber) ? "Admin" :opNumber);
		ur.setRemark(UnconventionalTypeEnum.BEFREECFP.getMessage());
		ur.setExtended(JSON.toJSONString(ur));
		ur.setCfpMobile(saleUser.getMobile());
		unconventionalRecordDao.add(ur);
		int record = saleUserInfoDao.beFreeCfp(objects);
		if(record < 1){
			logsMsg.append("]理财师关系处理失败");
			logger.error(logsMsg.toString());
			return Error.DB_ERROR;
		}
		return new SuccessCode();
	}

	@Override
	public SaleUserInfo findSaleUserInfoByMobile(String mobile) {
		SaleUserInfo condit = new SaleUserInfo();
		condit.setMobile(mobile);
		SaleUserInfo saleUserInfo = new SaleUserInfo();
		List<SaleUserInfo> saleUserList = saleUserInfoDao.list(condit);
		if(saleUserList!=null && saleUserList.size()>0){
			saleUserInfo = saleUserList.get(0);
		}
		return saleUserInfo;
		
	}

	@Override
	public ReturnCode beXcfCfp(String department,String mobile,String opName,String opNumber) {
		StringBuffer logsMsg = new StringBuffer();
		logsMsg.append("理财师：[").append(mobile).append("]转为新财富理财师（部门：").append(department);
		//理财师信息
		SaleUserInfo condit = new SaleUserInfo();
		condit.setMobile(mobile);
		SaleUserInfo saleUser = saleUserInfoDao.query(condit);
		if(saleUser == null){
			logsMsg.append(")理财师信息不存在");
			logger.error(logsMsg.toString());
			return Error.CUSTOMER_NOT_EXIST;
		}
		//记录操作日记
		UnconventionalRecord ur = new UnconventionalRecord();
		ur.setLcsNumber(saleUser.getNumber());
		ur.setOptType(UnconventionalTypeEnum.BEXCFCFP.getCode());
		ur.setEffectiveTime(new Date());
		ur.setCrtTime(new Date());
		ur.setOptUserName(StringUtils.isEmpty(opName) ? "Admin" :opName);
		ur.setOptUserNumber(StringUtils.isEmpty(opNumber) ? "Admin" :opNumber);
		ur.setRemark(UnconventionalTypeEnum.BEXCFCFP.getMessage());
		ur.setCfpMobile(saleUser.getMobile());
		ur.setExtended(JSON.toJSONString(ur));
		unconventionalRecordDao.add(ur);
		int record = saleUserInfoDao.beXcfCfp(department, mobile);
		if(record < 1){
			logsMsg.append(")修改理财师所属机构失败");
			logger.error(logsMsg.toString());
			return Error.DB_ERROR;
		}
		return new SuccessCode();
	}
	public PaginatorSevResp<UnconventionalRecord> queryUnRecList(PaginatorSevReq pageRequest) {
		PageRequest req = PaginatorUtil.toPageRequest(pageRequest);
		PageList<UnconventionalRecord> datas = unconventionalRecordDao.queryUnreCordPage(req);
		return  PaginatorUtil.toPaginatorSevResp(datas);
	}

	/**
	 * 投资明细
	 */
	public PaginatorResponse<InvestRecord> queryInvestRecordList(InvestRecordReq investRecordReq) {
		
		PaginatorResponse<InvestRecord> pageResponse= new PaginatorResponse<InvestRecord>();
		pageResponse.setPageSize(investRecordReq.getRows());
		pageResponse.setPageIndex(investRecordReq.getPage());
		pageResponse.setTotalCount(investorProfitStatisticDao.investRecordCount(investRecordReq));
		pageResponse.setDatas(investorProfitStatisticDao.investRecordPageList(investRecordReq));
		return pageResponse;
	}

	@Override
	public Integer queryRegCustomerCount(String mobile) {
		return statisticCustomerDao.queryInvitedCustomerNum(mobile);
	}
	
	/**
	 * 客户列表
	 */
	public PaginatorResponse<InvestorResp> queryInvestorList(InvestorReq investorReq) {
		
		PaginatorResponse<InvestorResp> pageResponse= new PaginatorResponse<InvestorResp>();
		pageResponse.setPageSize(investorReq.getRows());
		pageResponse.setPageIndex(investorReq.getPage());
		pageResponse.setTotalCount(investorProfitStatisticDao.investorCount(investorReq));
		pageResponse.setDatas(investorProfitStatisticDao.investorList(investorReq));
		return pageResponse;
	}

	@Override
	public List<MyInvestedCustomerResp> MyInvestedCustomer(InvestorReq investorReq) {
		return investorProfitStatisticDao.MyInvestedCustomer(investorReq);
	}
	/**
	 * 投资收益列表
	 */
	public PaginatorResponse<InvestorProfitResp> queryInvestProfitList(InvestorReq investorReq) {
		
		PaginatorResponse<InvestorProfitResp> pageResponse= new PaginatorResponse<InvestorProfitResp>();
		pageResponse.setPageSize(investorReq.getRows());
		pageResponse.setPageIndex(investorReq.getPage());
		pageResponse.setTotalCount(investorProfitStatisticDao.investorProfitCount(investorReq));
		List<InvestorProfitResp> respList = investorProfitStatisticDao.investorProfitList(investorReq);
		pageResponse.setDatas(respList);
		return pageResponse;
	}

	@Override
	public ServiceResponse<List<Feerate>>  productFeeRate(){
		Feerate feeRate = new Feerate();
		feeRate.setOrgnumber("99999999");
		List<Feerate> feerateList = new ArrayList<Feerate>();
		try {
			 feerateList = feerateDao.list(feeRate);
			 return  new ServiceResponse<List<Feerate>>(feerateList);
		} catch (Exception e) {
			return  new ServiceResponse<List<Feerate>>(Error.DB_ERROR);
		}
	}

	@Override
	public Map<String, Object> queryInvestorAndMoney() {
	
		Map<String,Object> mapRet = new HashMap<String,Object>();
		Map<String,Object> personAmountMap = investorProfitStatisticDao.personAmoutStat();
		Map<String,Object> investMoneyMap = investorProfitStatisticDao.investMoneyStat();
		for(Map.Entry<String, Object> item :personAmountMap.entrySet()){
			personAmountMap.put(item.getKey(), NumberUtils.getFormat(new BigDecimal(String.valueOf(item.getValue())), "0"));
		}
		for(Map.Entry<String, Object> item :investMoneyMap.entrySet()){
			investMoneyMap.put(item.getKey(), NumberUtils.getFormat(new BigDecimal(String.valueOf(item.getValue())), "0.00"));
		}
		
		mapRet.put("personAmout", personAmountMap);
		mapRet.put("investMoney", investMoneyMap);
		return mapRet;
	}

	@Override
	public Map<String, Object> queryInvestorAndMoneyByDate(String startDate, String endDate) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> investor= new LinkedHashMap<String, Object>();
		Date start = DateUtils.parse(startDate,"yyyy-MM-dd");
		Date end = DateUtils.parse(endDate,"yyyy-MM-dd");
		int searchDay = DateUtils.countDays(start,end)+1;
		List<Map<String, Object>> listInvestorNum = investorProfitStatisticDao.queryInvestorNumByTime(startDate, endDate);
		List<Map<String, Object>> listInvestMoney = investorProfitStatisticDao.queryInvestMoneyByTime(startDate, endDate);
		List<Object> list =null;
		if(listInvestorNum.size() < searchDay){
			list =  new ArrayList<Object>();
			List<String> strList = DateUtils.getBetweeenTime(start, end, "yyyy-MM-dd");
			/**
			 * 手动补全投资人数
			 */
			for(String str:strList){
				boolean isExist = false;
				for(Map<String,Object> item:listInvestorNum){
					if(item.get("investDate") !=null && item.get("investDate").equals(str)){
						isExist = true;
						list.add(item);
						continue;
					}
					
				}
				if(!isExist){//没有查询数据手动补0
					Map<String,Object> temp = new HashMap<String,Object>();
					temp.put("investDate", str);
					temp.put("personNum", 0);
					list.add(temp);
				}else{
					
				}
			}
			investor.put("person", list);
			/**
			 * 手动补全投资额
			 */
			list =  new ArrayList<Object>();
			for(String str:strList){
				boolean isExist = false;
				for(Map<String,Object> item:listInvestMoney){
					//if(item.containsKey(str)){
					if(item.get("investDate") !=null && item.get("investDate").equals(str)){
						isExist = true;
						list.add(item);
						continue;
					}
					
				}
				if(!isExist){//没有查询数据手动补0
					Map<String,Object> temp = new HashMap<String,Object>();
					temp.put("investDate", str);
					temp.put("investTotal", 0);
					list.add(temp);
				}
			}
			investor.put("money", list);
			
		}else{
			investor.put("person", listInvestorNum);
			investor.put("money", listInvestMoney);
		}
		
		
		map.put("data", investor);
		return map;
	}

	@Override
	public Double queryInvestSum(String userId) {
		return investorProfitStatisticDao.investTotalSumByCustomerId(userId);
	}

	@Override
	public Map<String, Object> queryTotalInvestorAndMoney() {
		Map<String,Object> mapRet = new HashMap<String,Object>();
		Map<String,Object> investorTotal = investorProfitStatisticDao.investorTotal();
		Map<String,Object> investMoneyTotal = investorProfitStatisticDao.investMoneyTotal();
		
		mapRet.put("totalperson", NumberUtils.getFormat(new BigDecimal(String.valueOf(investorTotal.get("totalperson"))), "0"));
		mapRet.put("totalmoney", NumberUtils.getFormat(new BigDecimal(String.valueOf(investMoneyTotal.get("totalmoney"))), "0"));
		return mapRet;
	}

	@Override
	public Map<String,Object> usedTotalRedPaper() {
		List<Map<String,Object>> mapList = investorProfitStatisticDao.usedTotalRedPaper();
		Map<String,Object> retMap = new HashMap<String,Object>();
		for(Map<String,Object> item :mapList){
			retMap.put(item.get("f_customerid").toString(), item.get("total"));
		}
		return retMap;
		
	}

	/**
	 * 重新绑定理财师
	 * @param mobile
	 * @param lcsMobile
	 * @param changeType
	 * @return
	 */
	@Override
	public ServiceResponse<String> changeLcs(String mobile, String lcsMobile, String changeType) {
		StringBuffer logsMsg = new StringBuffer();
		logsMsg.append("客户：[").append(mobile).append("]绑定理财师[").append(lcsMobile);
		//检查客户信息是否存在
		Usercustomerrel rcrCondit = new Usercustomerrel();
		rcrCondit.setCustomermobile(mobile);
		Usercustomerrel customer = usercustomerrelDao.query(rcrCondit);
		if(customer == null){
			logsMsg.append("]找不到对于客户信息");
			logger.error(logsMsg.toString());
			return new ServiceResponse<String>(Error.CUSTOMER_NOT_EXIST);
		}
		SaleUserInfo self = saleUserInfoService.getSaleUserInfoByMobile(mobile);
		if(self != null) {
			logsMsg.append("]用户自己是理财师");
			logger.error(logsMsg.toString());
			return new ServiceResponse<String>(Error.SELF_IS_CFP);
		}
		if(changeType != null && "1".equals(changeType)){
			//重新绑定理财师
			if(lcsMobile == null || "".equals(lcsMobile)) {
				return new ServiceResponse<String>(Error.CFP_MOBILE_ERROR);
			}
			//确定输入的理财师手机号是已经存在的理财师
			SaleUserInfo condit = new SaleUserInfo();
			condit.setMobile(lcsMobile);
			SaleUserInfo saleUser =  saleUserInfoDao.query(condit);
			if(saleUser ==null ){
				logsMsg.append("]找不到对于理财师信息");
				logger.error(logsMsg.toString());
				return new ServiceResponse<String>(Error.SALE_USER_NOT_EXIST);
			}
			UnconventionalRecord ur = new UnconventionalRecord();
			ur.setOptType(UnconventionalTypeEnum.CHANGE.getCode());
			ur.setEffectiveTime(new Date());
			ur.setCrtTime(new Date());
			ur.setModifyTime(new Date());
			ur.setOptUserName("admin");
			ur.setOptUserNumber("admin");
			ur.setRemark(UnconventionalTypeEnum.CHANGE.getMessage());
			ur.setExtended(ur.toString());
			ur.setLcsNumber(saleUser.getNumber());
			ur.setCfpMobile(lcsMobile);
			ur.setCustomerMobile(mobile);
			//操作记录
			unconventionalRecordDao.add(ur);
			Usercustomerrel usercustomerrel = new Usercustomerrel();
			usercustomerrel.setCustomermobile(mobile);
			usercustomerrel.setCurrsaleuser(lcsMobile);
			usercustomerrelDao.updateCftForCustomer(usercustomerrel);
			return new ServiceResponse<String>("绑定成功");
		} else if("2".equals(changeType)) {
			//变更为自由客户
			//记录操作日记
			if(customer.getCurrsaleuser() == null) {
				return new ServiceResponse<String>(Error.PARAM_ERROR);
			}
			SaleUserInfo infoForQuery = new SaleUserInfo();
			infoForQuery.setNumber(customer.getCurrsaleuser());
			SaleUserInfo info = saleUserInfoDao.query(infoForQuery);
			UnconventionalRecord ur = new UnconventionalRecord();
			ur.setLcsNumber(customer.getCurrsaleuser());
			ur.setOptType(UnconventionalTypeEnum.BEFREECUSTOMER.getCode());
			ur.setEffectiveTime(new Date());
			ur.setCrtTime(new Date());
			ur.setModifyTime(new Date());
			ur.setOptUserName("admin");
			ur.setOptUserNumber("admin");
			ur.setRemark(UnconventionalTypeEnum.BEFREECUSTOMER.getMessage());
			if(info != null ){
				ur.setCfpMobile(info.getMobile());
			}
			ur.setCustomerMobile(mobile);
			ur.setExtended(JSON.toJSONString(ur));
			unconventionalRecordDao.add(ur);
			usercustomerrelDao.deleteRelForCustomer(mobile);
			return new ServiceResponse<String>("成功变更为自由客户");
		}else {
			return new ServiceResponse<String>(Error.PARAM_ERROR);
		}
	}

	@Override
	public List<ChangeLcsRecord> queryChangeLcsRecord(String customerMobile) {
		return unconventionalRecordDao.queryChangeLcsRecord(customerMobile);
	}

	
}
