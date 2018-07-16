package com.linkwee.web.controller.crm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.PersonalMsgTypeEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.crm.InvestorBindPlatformDatable;
import com.linkwee.web.model.crm.InvestorManagerDetailResp;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.model.weixin.WeiXinMsgRequest;
import com.linkwee.web.request.LcsListRequest;
import com.linkwee.web.request.ListDetailRequest;
import com.linkwee.web.response.CommonTCSResult;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.InvestorManagerService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.SysMsgService;
import com.linkwee.web.service.WeiXinMsgService;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.constant.ResponseConstant;
import com.linkwee.xoss.helper.ConfigHelper;
import com.linkwee.xoss.helper.PushMessageHelper;
import com.linkwee.xoss.helper.ThreadpoolService;
import com.linkwee.xoss.util.MD5;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： 实体控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月28日 16:08:06
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "investor")
@RequestLogging("投资用户管理")
public class InvestorManagerController extends BaseController {

	@Resource
	private SysMsgService msgService;

	@Resource
	private SysConfigService sysConfigService;

	@Resource
	private CrmCfplannerService crmCfplannerService;

	@Resource
	private CrmInvestorService crmInvestorService;

	@Resource
	private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private InvestorManagerService investorManagerService;
	
	@Resource
	private PushMessageHelper pushMessageHelper;
	
	@Resource
	private WeiXinMsgService weiXinMsgService;
	@Resource
	private ConfigHelper configHelper;
	
    /**
	 * 客户列表
	 */
	@RequestMapping("investorListPage")
	public String lcsListPage() {
		return "investor/investor-list";
	}	
	
	/**
	 * 获取投资者列表
	 * @param lcsListRequest
	 * @return
	 */
	@RequestMapping("getInvestorList")
	@ResponseBody
	@RequestLogging("获取投资者列表")
	public Object getInvestorList(LcsListRequest lcsListRequest, DataTable dataTable) {
		DataTableReturn dataTableReturn = investorManagerService.queryInvestorList(dataTable, lcsListRequest);
		return dataTableReturn;
	}
	
	/**
	 * 客户列表
	 */
	@RequestMapping("investorListPageNew")
	public String lcsListPageNew() {
		return "investor/investor-listNew";
	}	
	
	/**
	 * 获取投资者列表
	 * @param lcsListRequest
	 * @return
	 */
	@RequestMapping("getInvestorListNew")
	@ResponseBody
	@RequestLogging("获取投资者列表")
	public Object getInvestorListNew(LcsListRequest lcsListRequest, DataTable dataTable) {
		DataTableReturn dataTableReturn = investorManagerService.queryInvestorListNew(dataTable, lcsListRequest);
		return dataTableReturn;
	}
	
	/**
	 * 获取理财师明细
	 * 
	 * @param mobile
	 * @return
	 */
	@RequestMapping("getInvestorDetail")
	@RequestLogging("获取理财师明细")
	public String getInvestorDetail(String mobile, Model model) {
		if (StringUtils.isNotBlank(mobile)) {
			InvestorManagerDetailResp lcsDetailResp = investorManagerService.queryInvestorDetail(mobile);
			if(lcsDetailResp != null && lcsDetailResp.getHeadImage() != null) {
				lcsDetailResp.setHeadImage(sysConfigService.getImageUrl(lcsDetailResp.getHeadImage()));
			}
			model.addAttribute("dtl", lcsDetailResp);
		}
		return "investor/investorDetail";
	}
	
	
	/**
	 * 重新绑定理财师
	 */
	@RequestMapping(value = "changeCfplanner")
	@ResponseBody
	@RequestLogging("重新绑定理财师")
	public Object changeCfplanner(HttpServletRequest reqeust,@RequestParam String mobile,@RequestParam String lcsMobile,@RequestParam String changeType) {
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		logsb.append("list|cfpMobile=").append(lcsMobile).append("|customerMobile=").append(mobile);
		ResponseResult result =  new ResponseResult();
		if (StringUtils.isBlank(mobile) || StringUtils.isBlank(changeType)) {
			return new ResponseResult(ResponseConstant.FAILURE, "参数错误");
		}
		CrmInvestor customer = crmInvestorService.queryInvestorByMobile(mobile);
		if (customer == null) {
			return new ResponseResult(ResponseConstant.FAILURE, "查询客户信息错误");
		}
		CrmCfplanner self = crmCfplannerService.queryCfplannerByMobile(mobile);
		if (self != null) {
			return new ResponseResult(ResponseConstant.FAILURE, "自己是理财师，不能更换");
		}
		CrmCfplanner cfplanner = null;
		//推送消息  要在解绑之前查询相关的信息 重新绑定前关系数据
		CrmCfplanner oldCfplanner = crmCfplannerService.queryCfplannerByUserId(customer.getCfplanner());
		if (StringUtils.isNotBlank(changeType) && "1".equals(changeType)) {
			if (StringUtils.isBlank(lcsMobile)) {
				return new ResponseResult(ResponseConstant.FAILURE, "理财师号码不正常");
			}
			cfplanner = crmCfplannerService.queryCfplannerByMobile(lcsMobile);
			if (cfplanner == null) {
				return new ResponseResult(ResponseConstant.FAILURE, "理财师不存在");
			}
		} else {
		}
		
		try {
			investorManagerService.changeCfplanner(customer, cfplanner, changeType);
			try {
				final List<WeiXinMsgRequest> wxList = new ArrayList<WeiXinMsgRequest>();
				if(oldCfplanner != null) {
					if(StringUtils.isBlank(customer.getUserName())){
						customer.setUserName("");
					}
					//旧理财师消息
					String nameStrlcs = customer.getUserName()+mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
					//"客户%s已与您解除关系,后续注意维护客户关系哦"
					String lcsContent = String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_LCS_CUSTOMER_UNBUNDLING), nameStrlcs);
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("customUrlKey", SmsTypeEnum.JBLCSLCSSUCCESS.getMsg());
					CommonTCSResult khjblcsBL = pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, SmsTypeEnum.JBLCSLCSSUCCESS, oldCfplanner.getUserId(), "客户关系解除",  lcsContent,  map1, true);
					logger.info("客户解绑推送理财师消息：{} , {}", lcsContent,khjblcsBL.getCode() == 0 ? "推送成功" : "推送失败");
					
					//解除旧理财师投资端消息
					String nameStrjf = StringUtils.isBlank(oldCfplanner.getUserName()) ? "" : oldCfplanner.getUserName() + oldCfplanner.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
					String jfContent = String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_INV_CUSTOMER_UNBUNDLING), nameStrjf);
					//Map<String, Object> map2 = new HashMap<String, Object>();
					//map2.put("customUrlKey", SmsTypeEnum.JBLCSJFSUCCESS.getMsg());
					CommonTCSResult khjbjfBL = pushMessageHelper.pushMessage(AppTypeEnum.INVESTOR, SmsTypeEnum.PERSONALMSGCT_INC, customer.getUserId(), "客户关系解除", jfContent, null, true,PersonalMsgTypeEnum.UNBUNDINGLCS_INV);
					logger.info("客户解绑推送投资端消息：{} , {}", jfContent,khjbjfBL.getCode() == 0 ? "推送成功" : "推送失败");
				
					//微信消息推送  推给原理财师(客户解绑) 有1名客户与您解除绑定关系，后续注意维护客户哦！具体客户信息如下：
					WeiXinMsgRequest wxreq1 = new WeiXinMsgRequest();
					wxreq1.setUseId(oldCfplanner.getUserId());
					wxreq1.setTemkey(SysConfigConstant.CUSTOMER_RELEASE_RELATIONSHIP);//绑定成功通知bind_success
					wxreq1.setUserName(customer.getUserName()==null?"":customer.getUserName());//用户名
					wxreq1.setMobile(customer.getMobile());//手机号
					wxreq1.setBindTime(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));
					wxreq1.setUseType("1");
					wxList.add(wxreq1);
					//微信消息推送  解绑通知 推给客户
					WeiXinMsgRequest wxreq2 = new WeiXinMsgRequest();
					wxreq2.setUseId(customer.getUserId());
					wxreq2.setTemkey(SysConfigConstant.RELEASE_RELATIONSHIP);//绑定成功通知bind_success
					wxreq2.setUserName(oldCfplanner.getUserName()==null?"":oldCfplanner.getUserName());//用户名
					wxreq2.setMobile(oldCfplanner.getMobile());//用户类型s
					wxreq2.setBindTime(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));//解绑时间
					wxreq2.setUseType("2");
					wxList.add(wxreq2);
				}

				//重新绑定理财师
				if(changeType != null && "1".equals(changeType)){	
					//新理财师消息
					CrmInvestor investorOfAfterUpdate = crmInvestorService.queryInvestorByMobile(mobile);
					if(StringUtils.isBlank(investorOfAfterUpdate.getUserName())){
						investorOfAfterUpdate.setUserName("");
					}
					String nameStrlcsAfter =  investorOfAfterUpdate.getUserName() + investorOfAfterUpdate.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
					String lcsContentAfter = String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_LCS_CUSTOMER_BUNDLING),nameStrlcsAfter);
					Map<String, Object> map11 = new HashMap<String, Object>();
					map11.put("customUrlKey", SmsTypeEnum.BDLCSLCSSUCCESS.getMsg());
					CommonTCSResult lcsbdlcsBL = pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, SmsTypeEnum.BDLCSLCSSUCCESS, cfplanner.getUserId(), "客户关系绑定",  lcsContentAfter,  map11, true);
					logger.info("绑定理财师推送理财师消息：{}, {}", lcsContentAfter, lcsbdlcsBL.getCode() == 0 ? "推送成功" : "推送失败");
					
					//投资端用户消息
					String nameStrjfAfter = StringUtils.isBlank(cfplanner.getUserName()) ? "" : cfplanner.getUserName() + cfplanner.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
					String jfContentAfter = String.format(configHelper.getValue(SysConfigConstant.PUSHMESSAGE_INV_CUSTOMER_BUNDLING), nameStrjfAfter);
					Map<String, Object> map22 = new HashMap<String, Object>();
					map22.put("customUrlKey", SmsTypeEnum.BDLCSJFSUCCESS.getMsg());
					CommonTCSResult lcsbdjfBL = pushMessageHelper.pushMessage(AppTypeEnum.INVESTOR, SmsTypeEnum.PERSONALMSGCT_INC, customer.getUserId(), "客户关系绑定",  jfContentAfter,  null, true,PersonalMsgTypeEnum.REBUNDINGLCS_INV);
					logger.info("绑定理财师推送投资端消息：{}, {}", jfContentAfter, lcsbdjfBL.getCode() == 0 ? "推送成功" : "推送失败");
				
					//微信消息推送 绑定成功通知  推给客户
					WeiXinMsgRequest wxreq3 = new WeiXinMsgRequest();
					wxreq3.setUseId(customer.getUserId());
					wxreq3.setTemkey(SysConfigConstant.BIND_SUCCESS);//绑定成功通知bind_success
					wxreq3.setUserName(cfplanner.getUserName()==null?"":cfplanner.getUserName());//绑定姓名
					wxreq3.setMobile(cfplanner.getMobile());//绑定账户
					wxreq3.setBindTime(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));
					wxreq3.setUseType("2");
					wxList.add(wxreq3);
					
					//微信消息推送 绑定成功通知  推给理财师
					WeiXinMsgRequest wxreq2 = new WeiXinMsgRequest();
					wxreq2.setUseId(cfplanner.getUserId());
					wxreq2.setTemkey(SysConfigConstant.CUSTOMER_BIND_SUCCESS);//绑定成功通知bind_success
					wxreq2.setUserName(cfplanner.getUserName()==null?"":cfplanner.getUserName());//绑定姓名
					wxreq2.setMobile(cfplanner.getMobile());//绑定账户
					wxreq2.setBindTime(DateUtils.format(new Date(),DateUtils.FORMAT_LONG));
					wxreq2.setUseType("1");
					wxList.add(wxreq2);
				}
				
				ThreadpoolService.execute(new Runnable() {
					 @Override
					 public void run() {
						 weiXinMsgService.sendWeiXinMsgListCommon(wxList);
					 }
				 });
			} catch (Exception e) {
				logger.error("重新绑定理财师消息推送失败 " + e);
			}
			
			result.setIsFlag(true);
		} catch (Exception e) {
			logger.error("重新绑定理财师异常" + e);
			result.setMsg("系统异常");
			logsb.append("|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return result;
	}
	
	public String fixphoneNmber(String msg){
		return msg.substring(0,msg.length()-12)+msg.substring(msg.length()-5);
	}
	
	/**
	 * 登录密码重置
	 * @param reqeust
	 * @param mobile
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value = "resetpwd")
	@ResponseBody
	@RequestLogging("登录密码重置")
	public Object resetPwd(HttpServletRequest reqeust,@RequestParam String mobile,@RequestParam String newPwd) {
		if (StringUtils.isBlank(mobile) || StringUtils.isBlank(newPwd)) {
			return new ResponseResult(ResponseConstant.FAILURE, "参数错误");
		}
		CrmInvestor crmInvestor = crmInvestorService.queryInvestorByMobile(mobile);
		if (crmInvestor == null) {
			return new ResponseResult(ResponseConstant.FAILURE, "查询客户信息错误");
		}
		ResponseResult result = new ResponseResult();
		try {
			CrmUserInfo crmUserInfo = new CrmUserInfo();
			crmUserInfo.setPassword(MD5.crypt(newPwd));
			crmUserInfo.setUserId(crmInvestor.getUserId());
			crmUserInfoService.updateByUserId(crmUserInfo);
			//站内信
			String content = String.format("亲爱的%s,您的密码信息已于%s由管理员更新,敬请留意",
					StringUtils.isNotBlank(crmInvestor.getUserName()) ? crmInvestor.getUserName() : crmInvestor.getMobile(),
							DateUtils.getNow(DateUtils.FORMAT_LONG));
			SysMsg msg = new SysMsg();
			msg.setAppType(AppTypeEnum.INVESTOR.getKey());
			msg.setStatus(0);
			msg.setUserNumber(crmInvestor.getUserId());
			msg.setContent(content);
			msgService.addMsg(msg);
			result.setIsFlag(true);
		} catch (Exception e) {
			result.setIsFlag(false);
		}
		return result;
		
	}
	
	
	/**
     * 邀请的好友列表
     */
    @RequestMapping("freindsListPage")
    @RequestLogging("客户投资记录列表")
    public String freindsListPage(Model model, @RequestParam String userId) {
    	CrmCfplanner cfp = crmCfplannerService.queryCfplannerByUserId(userId);
    	model.addAttribute("cfp", cfp);
    	model.addAttribute("userId", userId);
    	return "investor/freindsList";
    }

    /**
     * 获取邀请好友列表数据
     */
    @RequestMapping("getFreindsList")
    @ResponseBody
	public DataTableReturn getFreindsList(ListDetailRequest req, DataTable dataTable) {
		DataTableReturn tableReturn = investorManagerService.selectFreindsList(dataTable,req);
		return tableReturn;
	}
    
    /**
     * 投资用户图表
     */
    @RequestMapping("statdata")
	@ResponseBody
	@RequestLogging("投资客户数据概览-报表图")
	public Object dataViewInvestMoney(String startDate,String endDate){
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		logsb.append("dataViewInvestorAmount|startTime=").append(startDate).append("endTime=").append(endDate);
		if(StringUtils.isBlank(startDate)  || StringUtils.isBlank(endDate)){
			startDate = DateUtils.format(DateUtils.subDay(new Date(), 7),"yyyy-MM-dd");
			endDate = DateUtils.format(DateUtils.subDay(new Date(), 1),"yyyy-MM-dd");
		}
		Map<String, Object> mapData = null;
		String msg = "";
		 try {
			 mapData = investorManagerService.queryInvestorAndMoneyByDate(startDate, endDate);
			msg = "查询数据成功";
			logsb.append(msg);
			logger.info(logsb.toString());
		} catch (Exception e) {
			msg = "查询数据错误";
			logsb.append(msg);
			logger.info(logsb.toString());
		}finally{
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		 
		return mapData;
	}
	
	/**
	 * 数据概览
	 * @param model
	 * @return
	 */
	@RequestMapping("dataview")
	@RequestLogging("投资数据概览-页面")
	public String dataView(Model model){
		String startDate = DateUtils.format(DateUtils.subDay(new Date(), 7),"yyyy-MM-dd");
		String endDate = DateUtils.format(DateUtils.subDay(new Date(), 1),"yyyy-MM-dd");
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);
		long startTime = System.currentTimeMillis();
		Map<String,Object> mapRlt = investorManagerService.queryInvestorAndMoney();
		logger.debug("customerCftRelFixService.queryInvestorAndMoney():"+(System.currentTimeMillis()-startTime));
		model.addAttribute("data",mapRlt);
		logger.debug("queryTotalData():"+(System.currentTimeMillis()-startTime));
		return "investor/investorDateView";
	}
	
	/**
	 * 总投资人数和年化投资额 异步加载
	 */
	@RequestMapping("totaldata")
	@ResponseBody
	public Map<String, Object> queryTotalData(){
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		Map<String, Object> mapData = null;
		String msg = "";
		 try {
			 mapData = investorManagerService.queryTotalInvestorAndMoney();
			msg = "查询数据成功";
			logsb.append(msg);
			logger.info(logsb.toString());
		} catch (Exception e) {
			msg = "查询数据错误";
			logsb.append(msg);
			logger.info(logsb.toString());
		}finally{
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		 
		return mapData;
	}
	
	/**
     * 投资人绑定的机构信息列表页面
     */
    @RequestMapping(value="/queryInvestorBindPlatformList",   method=RequestMethod.GET)
    @RequestLogging("跳到投资人绑定的机构信息列表页面")
    public String toInvestorBindPlatformListView(Model model) {
    	return "investor/investorBindPlatform-list";
    }

    /**
     * 投资人绑定的机构信息列表
     * @return
     */
    @RequestMapping(value="/queryInvestorBindPlatformList", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看投资人绑定的机构信息列表")
	public DataTableReturn investorBindPlatformList(@RequestParam String  _dt_json) {
    	logger.debug("InvestorBindPlat list _dt_json={}", _dt_json);
    	InvestorBindPlatformDatable dataTable = JsonUtils.fromJsonToObject(_dt_json, InvestorBindPlatformDatable.class);
		dataTable.initOrders();
		DataTableReturn tableReturn = crmInvestorService.queryInvestorBindPlatformList(dataTable);
		return tableReturn;
	}
		
}
