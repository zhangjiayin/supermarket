package com.linkwee.web.controller.customer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.base.PaginatorSevReq;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.base.ServiceResponse;
import com.linkwee.web.api.BaseController;
import com.linkwee.web.constant.ResponseConstant;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.enums.UnconventionalTypeEnum;
import com.linkwee.web.model.SaleUserInfo;
import com.linkwee.web.model.UnconventionalRecord;
import com.linkwee.web.model.Usercustomerrel;
import com.linkwee.web.request.UnConventionalRecordPageListRequest;
import com.linkwee.web.response.PageResponse;
import com.linkwee.web.service.CustomerCftRelFixWebService;
import com.linkwee.web.service.PushMsgService;
import com.linkwee.web.service.SaleUserInfoService;
import com.linkwee.web.service.UsercustomerrelService;
import com.linkwee.web.util.RequestLogging;


@Controller
@RequestMapping(value = "customerfix")
@RequestLogging("投资客户")
public class CustomerFixController extends BaseController {
	@Resource
	private CustomerCftRelFixWebService customerCftRelFixService;
	@Resource
	private UsercustomerrelService usercustomerrelService;
	@Resource
	private SaleUserInfoService saleUserInfoService;
	@Resource
	private PushMsgService pushMsgService;
	
	@RequestMapping(value = "tobound")
	public String toBound(String customerMobile,Model model) {
		Usercustomerrel usercustomerrel = usercustomerrelService.findSaleInfoByMobile(customerMobile);
		model.addAttribute("usercustomerrel", usercustomerrel);
		return "investor/boundCfp";
	}

	
	@RequestMapping(value = "toBeXcfCfp")
	public String toBeXcfCfp(String mobile,Model model) {
		SaleUserInfo saleUserInfo = customerCftRelFixService.findSaleUserInfoByMobile(mobile);
		model.addAttribute("saleUserInfo", saleUserInfo);
		return "customer/beNewCfp";
	}
	
	@RequestMapping(value = "bound")
	@ResponseBody
	@RequestLogging("绑定理财师")
	public Object bound(HttpServletRequest reqeust,Usercustomerrel usercustomerrel) {
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		logsb.append("list|cfpMobile=").append(usercustomerrel.getCurrsaleuser()).append("|customerMobile=").append(usercustomerrel.getCustomermobile());
		ResponseResult result = null;
		try {
			
			UnconventionalRecord ur = new UnconventionalRecord();
			ur.setOptType(UnconventionalTypeEnum.CHANGE.getCode());
			ur.setEffectiveTime(new Date());
			ur.setCrtTime(new Date());
			ur.setModifyTime(new Date());
			ur.setOptUserName("admin");
			ur.setOptUserNumber("admin");
			ur.setRemark(UnconventionalTypeEnum.CHANGE.getMessage());
			ur.setExtended(ur.toString());
			
			ServiceResponse<String> srvResponse = customerCftRelFixService.boundCfpForCustomer(usercustomerrel, ur);
			if (srvResponse.getHead().getCode() == 0) {
				result = new ResponseResult(ResponseConstant.SUCCESS, "操作成功");
				
				//推送消息  在绑定之后查询相关的逻辑
				Usercustomerrel usercustomerrelDel = usercustomerrelService.findSaleInfoByMobile(usercustomerrel.getCustomermobile());
				
				//理财师
				if(StringUtils.isEmpty(usercustomerrelDel.getCustomername())){
					usercustomerrelDel.setCustomername("");
				}
				String nameStrlcs = fixphoneNmber(StringUtils.trim( "(" + usercustomerrelDel.getCustomername() + usercustomerrel.getCustomermobile() + ")" ));
				String lcsContent = String.format("恭喜您，%s成为你的客户，快去为Ta推荐理财产品吧",nameStrlcs);
				boolean lcsbdlcsBL = pushMsgService.pushMessage(AppTypeEnum.CHANNEL.getKey(), SmsTypeEnum.BDLCSLCSSUCCESS.getKey(), usercustomerrelDel.getCurrsaleuserUUID(), nameStrlcs, 0, lcsContent, 0, null);
				logger.info("绑定理财师推送理财师消息：{}-------------------{}", lcsContent,lcsbdlcsBL==true?"推送成功":"推送失败");
				//金服
				String nameStrjf = fixphoneNmber(StringUtils.trim(usercustomerrelDel.getCurrSaleUserName()));
				String jfContent = String.format("您已成为理财师%s的客户，有理财方面的问题都可以问他", nameStrjf);
				boolean lcsbdjfBL = pushMsgService.pushMessage(AppTypeEnum.INVESTOR.getKey(), SmsTypeEnum.BDLCSJFSUCCESS.getKey(), usercustomerrelDel.getCustomerid(), nameStrjf, 0, jfContent, 0, null);
				logger.info("绑定理财师推送金服消息：{}-------------------{}", jfContent,lcsbdjfBL==true?"推送成功":"推送失败");
				
			} else {
				result = new ResponseResult(ResponseConstant.FAILURE, srvResponse.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logsb.append("|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return result;
	}
	
	/**
	 * 用户解除绑和理财师归属关系
	 * @param reqeust
	 * @param customerMobile
	 * @return
	 */
	@RequestMapping(value = "unwrap")
	@ResponseBody
	@RequestLogging("客户解绑")
	public Object unwrap(HttpServletRequest reqeust,@RequestParam String customerMobile ) {
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		logsb.append("list|customerMobile=").append(customerMobile);
		ResponseResult result = null;
		try {
			
			String[] objects = new String[]{customerMobile};
			//TsUser user = Utils.getSessionUser(reqeust);
			//推送消息  要在解绑之前查询相关的信息
			Usercustomerrel usercustomerrelKH = usercustomerrelService.findSaleInfoByMobile(customerMobile);
			
			ServiceResponse<Integer> srvResponse = customerCftRelFixService.cleanRelForCustomer(objects,"admin","admin");
			if (srvResponse.getHead().getCode() == 0) {
				result = new ResponseResult(ResponseConstant.SUCCESS, "操作成功");	
				
				//理财师
				if(StringUtils.isEmpty(usercustomerrelKH.getCustomername())){
					usercustomerrelKH.setCustomername("");
				}
				String nameStrlcs = fixphoneNmber(StringUtils.trim( "(" + usercustomerrelKH.getCustomername() + customerMobile + ")" ));
				String lcsContent = String.format("有点遗憾,客户%s已与您解除关系,后续注意维护客户关系哦",nameStrlcs);
				boolean khjblcsBL = pushMsgService.pushMessage(AppTypeEnum.CHANNEL.getKey(), SmsTypeEnum.JBLCSLCSSUCCESS.getKey(), usercustomerrelKH.getCurrsaleuserUUID(), nameStrlcs, 0, lcsContent, 0, null);
				logger.info("客户解绑推送理财师消息：{}-------------------{}", lcsContent,khjblcsBL==true?"推送成功":"推送失败");
				//金服
				String nameStrjf = fixphoneNmber(StringUtils.trim(usercustomerrelKH.getCurrSaleUserName()));
				String jfContent = String.format("您已与理财师%s解除客户关系", nameStrjf);
				boolean khjbjfBL = pushMsgService.pushMessage(AppTypeEnum.INVESTOR.getKey(), SmsTypeEnum.JBLCSJFSUCCESS.getKey(), usercustomerrelKH.getCustomerid(), nameStrjf, 0, jfContent, 0, null);
				logger.info("客户解绑推送金服消息：{}-------------------{}", jfContent,khjbjfBL==true?"推送成功":"推送失败");
				
			} else {
				result = new ResponseResult(ResponseConstant.FAILURE, srvResponse.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logsb.append("|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return result;
	}
	//exitFromCfp
	@RequestMapping(value = "exitFromCfp")
	@ResponseBody
	public Object exitFromCfp(HttpServletRequest reqeust,String mobile) {
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		logsb.append("list|customerMobile=").append(mobile);
		ResponseResult result = null;
		try {
			
			//TsUser user = Utils.getSessionUser(reqeust);
			ReturnCode returnCode = customerCftRelFixService.exitFromCfp(mobile,"admin","admin");
			if (returnCode.getCode() == 0) {
				result = new ResponseResult(ResponseConstant.SUCCESS, "操作成功");
			} else {
				result = new ResponseResult(ResponseConstant.FAILURE, returnCode.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logsb.append("|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return result;
	}
	//exitFromCfp
		@RequestMapping(value = "beFreeCfp")
		@ResponseBody
		public Object beFreeCfp(HttpServletRequest reqeust,@RequestParam String mobile) {
			long start = System.currentTimeMillis();
			StringBuilder logsb = new StringBuilder();
			String[] mobiles = new String[]{mobile};
			logsb.append("list|numbers=").append(mobiles);
			ResponseResult result = null;
			try {
				
				//TsUser user = Utils.getSessionUser(reqeust);
				
				ReturnCode returnCode = customerCftRelFixService.beFreeCfp(mobiles,"admin","admin");
				if (returnCode.getCode() == 0) {
					result = new ResponseResult(ResponseConstant.SUCCESS, "操作成功");
				} else {
					result = new ResponseResult(ResponseConstant.FAILURE, returnCode.getMessage());
				}
			} catch (Exception e) {
				e.printStackTrace();
				logsb.append("|Exception e=").append(e.getMessage());
			} finally {
				long end = System.currentTimeMillis();
				logsb.append("|totaltime=").append(end - start);
				logger.info(logsb.toString());
			}
			return result;
		}
		
		@RequestMapping(value = "beXcfCfp")
		@ResponseBody
		public Object beXcfCfp(HttpServletRequest reqeust,@RequestParam String mobile,@RequestParam String department) {
			long start = System.currentTimeMillis();
			StringBuilder logsb = new StringBuilder();
			String[] mobiles = new String[]{mobile};
			logsb.append("list|numbers=").append(mobiles);
			ResponseResult result = null;
			try {
				
				//TsUser user = Utils.getSessionUser(reqeust);
				ReturnCode returnCode = customerCftRelFixService.beXcfCfp(department,mobile,"admin","admin");
				if (returnCode.getCode() == 0) {
					result = new ResponseResult(ResponseConstant.SUCCESS, "操作成功");
				} else {
					result = new ResponseResult(ResponseConstant.FAILURE, returnCode.getMessage());
				}
			} catch (Exception e) {
				e.printStackTrace();
				logsb.append("|Exception e=").append(e.getMessage());
			} finally {
				long end = System.currentTimeMillis();
				logsb.append("|totaltime=").append(end - start);
				logger.info(logsb.toString());
			}
			return result;
		}
		
		
		@RequestMapping(value = "hisrec")
		@ResponseBody
		@RequestLogging("理财师变更历史")
		public Object cfpHisRec(HttpServletRequest reqeust,UnConventionalRecordPageListRequest req) {
			long start = System.currentTimeMillis();
			StringBuilder logsb = new StringBuilder();
			logsb.append("cfpHisRec|UnconventionalRecordReq=").append(req.toString());
			PageResponse<UnconventionalRecord> response = new PageResponse<UnconventionalRecord>();
			try {
				
				PaginatorSevReq pageRequest = req.toPaginatorSevReq();
				pageRequest.getQueryConditions().put("customerMobile",req.getCustomerMobile());
				PaginatorSevResp<UnconventionalRecord> unRecPageList = customerCftRelFixService.queryUnRecList(pageRequest);
				response.setTotal(unRecPageList.getTotalCount());
				response.setRows(unRecPageList.getDatas());
				
			} catch (Exception e) {
				e.printStackTrace();
				logsb.append("|Exception e=").append(e.getMessage());
			} finally {
				long end = System.currentTimeMillis();
				logsb.append("|totaltime=").append(end - start);
				logger.info(logsb.toString());
			}
			return response;
		}
		
		/**
		 * 重新绑定理财师界面
		 */
		@RequestMapping("toChangeLcs")
		public String toChangeLcs(String mobile,Model model){
			String customerName = "";
			String lcsName = "";
			Usercustomerrel usercustomerrel = usercustomerrelService.queryByMobile(mobile);
			if(usercustomerrel != null) {
				customerName = usercustomerrel.getCustomermobile();
				if(usercustomerrel.getCurrsaleuser() != null && !"".equals(usercustomerrel.getCurrsaleuser())){
					SaleUserInfo saleUserInfo = saleUserInfoService.getSaleUserInfoByNumber(usercustomerrel.getCurrsaleuser());
					if(saleUserInfo != null) {
						lcsName = saleUserInfo.getMobile();
					}
				}
			}
			model.addAttribute("customerName",customerName);
			model.addAttribute("lcsName",lcsName);
			return "investor/toChangeLcs";
		}

		/**
		 * 重新绑定理财师
		 */
		@RequestMapping(value = "changeLcs")
		@ResponseBody
		@RequestLogging("重新绑定理财师")
		public Object changeLcs(HttpServletRequest reqeust,@RequestParam String mobile,@RequestParam String lcsMobile,@RequestParam String changeType) {
			long start = System.currentTimeMillis();
			StringBuilder logsb = new StringBuilder();
			logsb.append("list|cfpMobile=").append(lcsMobile).append("|customerMobile=").append(mobile);
			ResponseResult result =  new ResponseResult();
			try {
				//推送消息  要在解绑之前查询相关的信息 重新绑定前关系数据
				Usercustomerrel usercustomerrelKH = usercustomerrelService.findSaleInfoByMobile(mobile);
				
				ServiceResponse<String> srvResponse = customerCftRelFixService.changeLcs(mobile, lcsMobile, changeType);
				
				if (srvResponse.getHead().getCode() == 0) {
					
					try {
						
						if(usercustomerrelKH != null){
							//理财师
							if(StringUtils.isEmpty(usercustomerrelKH.getCustomername())){
								usercustomerrelKH.setCustomername("");
							}
							
							//旧理财师消息
							String nameStrlcs = fixphoneNmber(StringUtils.trim( "(" + usercustomerrelKH.getCustomername() + mobile + ")" ));
							String lcsContent = String.format("有点遗憾,客户%s已与您解除关系,后续注意维护客户关系哦",nameStrlcs);
							Map<String, Object> map1 = new HashMap<String, Object>();
							map1.put("customUrlKey", SmsTypeEnum.JBLCSLCSSUCCESS.getMsg());
							boolean khjblcsBL = pushMsgService.pushMessage(AppTypeEnum.CHANNEL.getKey(), SmsTypeEnum.JBLCSLCSSUCCESS.getKey(), usercustomerrelKH.getCurrsaleuserUUID(), nameStrlcs, 0, lcsContent, 0, map1);
							logger.info("客户解绑推送理财师消息：{}-------------------{}", lcsContent,khjblcsBL==true?"推送成功":"推送失败");
							
							//解除旧理财师金服消息
							String nameStrjf = fixphoneNmber(StringUtils.trim(usercustomerrelKH.getCurrSaleUserName()));
							String jfContent = String.format("您已与理财师%s解除客户关系", nameStrjf);
							Map<String, Object> map2 = new HashMap<String, Object>();
							map2.put("customUrlKey", SmsTypeEnum.JBLCSJFSUCCESS.getMsg());
							boolean khjbjfBL = pushMsgService.pushMessage(AppTypeEnum.INVESTOR.getKey(), SmsTypeEnum.JBLCSJFSUCCESS.getKey(), usercustomerrelKH.getCustomerid(), nameStrjf, 0, jfContent, 0, map2);
							logger.info("客户解绑推送金服消息：{}-------------------{}", jfContent,khjbjfBL==true?"推送成功":"推送失败");	
						}

						//重新绑定理财师
						if(changeType != null && "1".equals(changeType)){	
							//新理财师消息
							Usercustomerrel usercustomerrelOfAfter = usercustomerrelService.findSaleInfoByMobile(mobile);
							if(StringUtils.isEmpty(usercustomerrelOfAfter.getCustomername())){
								usercustomerrelOfAfter.setCustomername("");
							}
							String nameStrlcs = fixphoneNmber(StringUtils.trim( "(" + usercustomerrelOfAfter.getCustomername() + usercustomerrelOfAfter.getCustomermobile() + ")" ));
							String lcsContent = String.format("恭喜您，%s成为你的客户，快去为Ta推荐理财产品吧",nameStrlcs);
							Map<String, Object> map1 = new HashMap<String, Object>();
							map1.put("customUrlKey", SmsTypeEnum.BDLCSLCSSUCCESS.getMsg());
							boolean lcsbdlcsBL = pushMsgService.pushMessage(AppTypeEnum.CHANNEL.getKey(), SmsTypeEnum.BDLCSLCSSUCCESS.getKey(), usercustomerrelOfAfter.getCurrsaleuserUUID(), nameStrlcs, 0, lcsContent, 0, map1);
							logger.info("绑定理财师推送理财师消息：{}-------------------{}", lcsContent,lcsbdlcsBL==true?"推送成功":"推送失败");
							
							//金服用户消息
							String nameStrjf = fixphoneNmber(StringUtils.trim(usercustomerrelOfAfter.getCurrSaleUserName()));
							String jfContent = String.format("您已成为理财师%s的客户，有理财方面的问题都可以问他", nameStrjf);
							Map<String, Object> map2 = new HashMap<String, Object>();
							map2.put("customUrlKey", SmsTypeEnum.BDLCSJFSUCCESS.getMsg());
							boolean lcsbdjfBL = pushMsgService.pushMessage(AppTypeEnum.INVESTOR.getKey(), SmsTypeEnum.BDLCSJFSUCCESS.getKey(), usercustomerrelOfAfter.getCustomerid(), nameStrjf, 0, jfContent, 0, map2);
							logger.info("绑定理财师推送金服消息：{}-------------------{}", jfContent,lcsbdjfBL==true?"推送成功":"推送失败");
						}
					} catch (Exception e) {
						logger.warn("重新绑定理财师消息推送失败 " + e);
					}
					
					result.setIsFlag(true);
				} else {
					result.setIsFlag(false);
					result.setMsg(srvResponse.getMessage());
				}
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

}