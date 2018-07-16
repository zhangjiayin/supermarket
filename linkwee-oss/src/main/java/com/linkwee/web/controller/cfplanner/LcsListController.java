package com.linkwee.web.controller.cfplanner;

import cn.xn.user.domain.ResetLoginPwdReq;
import cn.xn.user.service.IPwdService;
import cn.xn.user.utils.RequestSignUtils;

import com.linkwee.core.base.PaginatorSevReq;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.export.ExportSupport;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.constant.ResponseConstant;
import com.linkwee.web.constant.WebConstants;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.model.*;
import com.linkwee.web.request.LcsCustomerRequest;
import com.linkwee.web.request.LcsListRequest;
import com.linkwee.web.response.LcsDetailResp;
import com.linkwee.web.response.LcsTeamDetailResp;
import com.linkwee.web.response.PageResponse;
import com.linkwee.web.service.*;
import com.linkwee.web.util.PaginatorUtil;
import com.linkwee.web.util.RequestLogging;
import com.xiaoniu.account.domain.result.UserBindCardRlt;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("lcsList")
@RequestLogging("理财师列表")
public class LcsListController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LcsListController.class);
	@Resource
	private LcsListService lcsListService;
	
	@Resource
	private ExportSupport exportSupport;
	
	@Resource
	private MsgService msgService;
	
	@Resource
	private SystemConfigService systemConfigService;

	@Resource
	private SaleUserInfoService saleUserInfoService;

	@Resource
	private IPwdService p2pPwdService;
	
	@Resource
	private CustomerCftRelFixWebService customerCftRelFixService;

	@Resource
	private UsercustomerrelService usercustomerrelService;

	@Resource
	private UserInfoTCService userInfoTCService;
	
	@Resource
	private PushMsgService pushMsgService;


	/**
	 * 理财师列表页面
	 * @return
     */
	@RequestMapping("lcsListPage")
	public String lcsListPage(){
		return "cfplanner/lcsList";
	}
	
	
	@RequestMapping("lcsTeamListPage")
	public String lcsTeamListPage(String lcsNumber,Model m){
		m.addAttribute("lcsNumber", lcsNumber);
		return "cfplanner/lcsTeamListPage";
	}
	
	@RequestMapping("lcsCustomerListPage")
	public String lcsCustomerListPage(String lcsNumber,String mobile,Model m){
		m.addAttribute("lcsNumber", lcsNumber);
		m.addAttribute("lcsMobile", mobile);
		return "cfplanner/lcsCustomerListPage";
	}
	
	
	@RequestMapping("orgPage")
	public String orgPage(){
		return "lcsOrg";
	}
	
	@RequestMapping("exportLcsList")
	@RequestLogging("导出理财师")
	public void exportLcsList(HttpServletRequest request, HttpServletResponse response,LcsListRequest lcsListRequest){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if(StringUtils.isNotBlank(lcsListRequest.getNameOrmobile())){
			map.put("nameOrmobile", lcsListRequest.getNameOrmobile());
		}
		if(StringUtils.isNotBlank(lcsListRequest.getStartDate())){
			map.put("startDate", DateUtils.parse(lcsListRequest.getStartDate(), DateUtils.FORMAT_SHORT));
		}
		if(StringUtils.isNotBlank(lcsListRequest.getEndDate())){
			map.put("endDate",DateUtils.parse(lcsListRequest.getEndDate(), DateUtils.FORMAT_SHORT));
		}
		exportSupport.export(request, response, "lcs/lcsList/lcsDetail.xls", lcsListService.exportLcsList(map));
	}
	
	@RequestMapping("exportLcsTeamList")
	@RequestLogging("导出理财师团队")
	public void exportLcsTeamList(HttpServletRequest request, HttpServletResponse response,LcsCustomerRequest lcsCustomerRequest){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if(StringUtils.isNotBlank(lcsCustomerRequest.getLcsNumber())){
			map.put("number", lcsCustomerRequest.getLcsNumber());
		}
		if(StringUtils.isNotBlank(lcsCustomerRequest.getStartDate())){
			map.put("startDate", DateUtils.parse(lcsCustomerRequest.getStartDate(), DateUtils.FORMAT_SHORT));
		}
		if(StringUtils.isNotBlank(lcsCustomerRequest.getEndDate())){
			map.put("endDate",DateUtils.parse(lcsCustomerRequest.getEndDate(), DateUtils.FORMAT_SHORT));
		}
		exportSupport.export(request, response, "lcs/lcsList/lcsTeam.xls", lcsListService.exportLcsTeamList(map));
	}
	
	@RequestMapping("exportLcsCustomerList")
	@RequestLogging("导出理财师客户")
	public void exportLcsCustomerList(HttpServletRequest request, HttpServletResponse response,LcsCustomerRequest lcsCustomerRequest){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if(StringUtils.isNotBlank(lcsCustomerRequest.getLcsNumber())){
			map.put("number", lcsCustomerRequest.getLcsNumber());
		}
		if(StringUtils.isNotBlank(lcsCustomerRequest.getStartDate())){
			map.put("startDate", DateUtils.parse(lcsCustomerRequest.getStartDate(), DateUtils.FORMAT_SHORT));
		}
		if(StringUtils.isNotBlank(lcsCustomerRequest.getEndDate())){
			map.put("endDate",DateUtils.parse(lcsCustomerRequest.getEndDate(), DateUtils.FORMAT_SHORT));
		}
		exportSupport.export(request, response, "lcs/lcsList/lcsCustomer.xls", lcsListService.exportLcsCustomerList(map));
	}
	
	/**
	 * 获取理财师列表
	 * @param lcsListRequest
	 * @return
	 */
	@RequestMapping("getLcsList")
	@ResponseBody
	@RequestLogging("获取理财师列表")
	public Object getLcsList(LcsListRequest lcsListRequest){
		DataTableReturn dataTableReturn = new DataTableReturn();
		PaginatorSevReq request = lcsListRequest.toPaginatorSevReq();
		if(StringUtils.isBlank(lcsListRequest.getNameOrmobile()) && (StringUtils.isBlank(lcsListRequest.getStartDate()) || StringUtils.isBlank(lcsListRequest.getEndDate())) ){
			PaginatorSevResp<LcsDetailResp> datas = PaginatorUtil.getEmptyResp(PaginatorUtil.toPageRequest(request));
			dataTableReturn.setDraw(lcsListRequest.getPageIndex());
			dataTableReturn.setData(datas.getDatas());
			dataTableReturn.setRecordsTotal(datas.getTotalCount());
			return dataTableReturn;
		}
		else{
			request.getQueryConditions().put("nameOrmobile", lcsListRequest.getNameOrmobile());
		}

		if(StringUtils.isNotBlank(lcsListRequest.getStartDate())){
			request.getQueryConditions().put("startDate", DateUtils.parse(lcsListRequest.getStartDate(), DateUtils.FORMAT_SHORT));
		}
		if(StringUtils.isNotBlank(lcsListRequest.getEndDate())){
			request.getQueryConditions().put("endDate",DateUtils.parse(lcsListRequest.getEndDate(), DateUtils.FORMAT_SHORT));
		}
		dataTableReturn = lcsListService.queryLcsList(request);
		dataTableReturn.setDraw(0);
		return dataTableReturn;
	}
	
	/**
	 * 获取理财师明细
	 * @param mobile
	 * @return
	 */
	@RequestMapping("getLcsDetail")
	@RequestLogging("获取理财师明细")
	public String getLcsDetail(String mobile, Model model){
		try {
			if(StringUtils.isNotBlank(mobile)){
//				mobile = new String(mobile.getBytes("iso-8859-1"),"UTF-8");
				TorginfoModel torginfoModel = new TorginfoModel();
				List<TorginfoModel> torginfoModelList = lcsListService.findTorgginNodeListByParentId(torginfoModel);
				LcsDetailResp lcsDetailResp = lcsListService.queryLcsDetail(mobile);
				lcsDetailResp.setImage(systemConfigService.getImageUrl(lcsDetailResp.getImage()));
				model.addAttribute("dtl", lcsDetailResp);
				model.addAttribute("torlist",torginfoModelList);
			}
		}
		catch (Exception e){
				LOGGER.error(e.getMessage());
		}

		return "cfplanner/lcsDetail";
	}

	@RequestMapping("/remove/headimage")
	@RequestLogging("删除理财师头像")
	@ResponseBody
	public ResponseResult removeSaleUserHeadImage(String mobile){
		ResponseResult result = new ResponseResult();
		try {
			if(com.linkwee.core.util.StringUtils.isNotBlank(mobile) && lcsListService.removeSaleUserHandImage(mobile)){
				SaleUserInfo saleUserInfo = saleUserInfoService.getSaleUserInfoByMobile(mobile);
				String nameStrlcs = fixphoneNmber(StringUtils.trim( "(" + saleUserInfo.getName() + saleUserInfo.getMobile() + ")" ));
				result.setIsFlag(true);
				String message = "您的头像图片不符合规定,己被删除!";
				boolean messageResult = pushMsgService.pushMessage(AppTypeEnum.CHANNEL.getKey(), SmsTypeEnum.LCSJBSJSUCCESS.getKey(), saleUserInfo.getCustomerId(), nameStrlcs, 0, message, 0, null);
				LOGGER.info("理财师头像不符合规定己删除：{}-------------------{}", message,messageResult==true?"推送成功":"推送失败");

			}
			else{
				result.setIsFlag(false);
			}
		}
		catch (Exception e){
			LOGGER.info(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 退出理财师
	 * @param mobile
	 * @return
	 */
	@RequestMapping("exitLcs")
	@ResponseBody
	@RequestLogging("退出理财师")
	public Object exitLcs(String mobile){		
		ResponseResult result = new ResponseResult();
		try{

			if(StringUtils.isNotBlank(mobile)){
				CfpCancelValideModel cfpCancelValideModel = lcsListService.queryValidCancelCFP(mobile);
				if(cfpCancelValideModel == null || cfpCancelValideModel.getCfp() !=1){
					result.setIsFlag(false);
					result.setMsg("参数错误!");
				}
				else if (cfpCancelValideModel.getCustomeNums()>1){
					result.setIsFlag(false);
					result.setMsg("该账号已产生客户或团队数据，不允许取消理财师身份!");
				}
				else if(cfpCancelValideModel.getParentId()!=1){
					result.setIsFlag(false);
					result.setMsg("该账号无上级理财师，不允许取消理财师身份!");
				}
				else{
					
					//推送消息  要在解绑之前查询相关的信息
					//该理财师相关信息
					SaleUserInfo saleUserInfo = saleUserInfoService.getSaleUserInfoByMobile(mobile);
					//上级理财师相关信息
					SaleUserInfo saleUserInfoTop = saleUserInfoService.queryParentByMobile(mobile);
					
					//退出理财师
					lcsListService.exitLcs(mobile);
					result.setIsFlag(true);
					
					//发送消息
					//上级理财师
					if(StringUtils.isEmpty(saleUserInfo.getName())){
						saleUserInfo.setName("");
					}
					String nameStrlcs = fixphoneNmber(StringUtils.trim( "(" + saleUserInfo.getName() + saleUserInfo.getMobile() + ")" ));
					String lcsContent = String.format("理财师%s已与您解除团队关系",nameStrlcs);
					boolean khjblcsBL = pushMsgService.pushMessage(AppTypeEnum.CHANNEL.getKey(), SmsTypeEnum.LCSJBSJSUCCESS.getKey(), saleUserInfoTop.getCustomerId(), nameStrlcs, 0, lcsContent, 0, null);
					LOGGER.info("理财师下级解绑推送上级消息：{}-------------------{}", lcsContent,khjblcsBL==true?"推送成功":"推送失败");
					//该理财师
					if(StringUtils.isEmpty(saleUserInfoTop.getName())){
						saleUserInfoTop.setName("");
					}
					String nameStrjf = fixphoneNmber(StringUtils.trim( "(" + saleUserInfoTop.getName() + saleUserInfoTop.getMobile() + ")" ));
					String jfContent = String.format("您已与理财师%s解除团队关系", nameStrjf);
					boolean khjbjfBL = pushMsgService.pushMessage(AppTypeEnum.INVESTOR.getKey(), SmsTypeEnum.LCSJBXJSUCCESS.getKey(), saleUserInfo.getCustomerId(), nameStrjf, 0, jfContent, 0, null);
					LOGGER.info("理财师下级解绑推送下级消息：{}-------------------{}", jfContent,khjbjfBL==true?"推送成功":"推送失败");
				}

			}else{
				result.setIsFlag(false);
				result.setMsg("参数错误!");
			}
		}catch(Exception e){
			result.setIsFlag(false);
		}
		return result;
	}
	
	
	/**
	 * 获取理财师客户
	 * @param lcsCustomerRequest
	 * @return
	 */
	@RequestMapping("getLcsCustomerList")
	@ResponseBody
	@RequestLogging("获取理财师客户")
	public Object getLcsCustomerList(LcsCustomerRequest lcsCustomerRequest){
		PaginatorSevReq request = lcsCustomerRequest.toPaginatorSevReq();

		if(StringUtils.isNotBlank(lcsCustomerRequest.getNameOrmobile())){
			request.getQueryConditions().put("nameOrmobile",lcsCustomerRequest.getNameOrmobile());
		}

		if(StringUtils.isNotBlank(lcsCustomerRequest.getLcsNumber())){
			request.getQueryConditions().put("number", lcsCustomerRequest.getLcsNumber());
		}
		if(StringUtils.isNotBlank(lcsCustomerRequest.getStartDate())){
			request.getQueryConditions().put("startDate", DateUtils.parse(lcsCustomerRequest.getStartDate(), DateUtils.FORMAT_SHORT));
		}
		if(StringUtils.isNotBlank(lcsCustomerRequest.getEndDate())){
			request.getQueryConditions().put("endDate",DateUtils.parse(lcsCustomerRequest.getEndDate(), DateUtils.FORMAT_SHORT));
		}
		DataTableReturn dataTableReturn= lcsListService.queryLcsCustomerList(request);
		dataTableReturn.setDraw(0);

		return dataTableReturn;
	}


	
	/**
	 * 获取理财师团队
	 * @param lcsCustomerRequest
	 * @return
	 */
	@RequestMapping("getLcsTeamList")
	@ResponseBody
	@RequestLogging("获取理财师团队")
	public Object getLcsTeamList(LcsCustomerRequest lcsCustomerRequest){
		PaginatorSevReq request = lcsCustomerRequest.toPaginatorSevReq();
		if(StringUtils.isNotBlank(lcsCustomerRequest.getLcsNumber())){
			request.getQueryConditions().put("number", lcsCustomerRequest.getLcsNumber());
		}
		if(StringUtils.isNotBlank(lcsCustomerRequest.getStartDate())){
			request.getQueryConditions().put("startDate", DateUtils.parse(lcsCustomerRequest.getStartDate(), DateUtils.FORMAT_SHORT));
		}
		if(StringUtils.isNotBlank(lcsCustomerRequest.getEndDate())){
			request.getQueryConditions().put("endDate",DateUtils.parse(lcsCustomerRequest.getEndDate(), DateUtils.FORMAT_SHORT));
		}
		PaginatorSevResp<LcsTeamDetailResp> datas= lcsListService.queryLcsTeamList(request);
		return datas;
	}
	
	
	/**
	 * 解除理财师与客户关系
	 * @param customerMobile
	 * @param lcsNumber
	 */
	@RequestMapping("unbindByCustomer")
	@ResponseBody
	@RequestLogging("解除理财师与客户关系")
	public Object unbindByCustomer(String customerMobile,String lcsNumber){
		ResponseResult result = new ResponseResult();
		try{
			if(StringUtils.isNotBlank(customerMobile)&&StringUtils.isNotBlank(lcsNumber)){
				lcsListService.unbindByCustomer(customerMobile, lcsNumber);
				result.setIsFlag(true);
			}else{
				result.setIsFlag(false);
				result.setMsg("参数错误!");
			}
		}catch(Exception e){
			result.setIsFlag(false);
		}
		return result;
		
	}
	
	
	/**
	 * 更换理财师机构
	 * @param lcsNumber
	 * @param department
	 * @return
	 */
	@RequestMapping("replaceLcs")
	@ResponseBody
	@RequestLogging("更换理财师机构")
	public Object replaceLcs(String lcsNumber, String department){
		ResponseResult result = new ResponseResult();
		try{
			if(StringUtils.isNotBlank(lcsNumber)&&StringUtils.isNotBlank(department)){
				
				lcsListService.replaceLcs(lcsNumber, department);
				result.setIsFlag(true);
			}else{
				result.setIsFlag(false);
				result.setMsg("参数错误!");
			}
		}catch(Exception e){
			result.setIsFlag(false);
		}
		return result;
	}
	
	
	/**
	 * 密码重置
	 */
	@RequestMapping("resetpwdPage")
	public String toPwdReset(String mobile,Model model){
		Usercustomerrel usercustomerrel = usercustomerrelService.queryByMobile(mobile);
		model.addAttribute("dtl",usercustomerrel);
		return "cfplanner/pwdReset";
	}
	
	private String getSignKey(String key) {
		return systemConfigService.getValuesByKey(key);
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
	@RequestLogging("密码重置")
	public Object resetPwd(HttpServletRequest reqeust,@RequestParam String mobile,@RequestParam String newPwd) {
		
		if(StringUtils.isBlank(mobile) || StringUtils.isBlank(newPwd)){
			return new ResponseResult(ResponseConstant.FAILURE,"参数错误");
		}
		SaleUserInfo saleUserInfo = saleUserInfoService.getSaleUserInfoByMobile(mobile);
		if(saleUserInfo == null){
			return new ResponseResult(ResponseConstant.FAILURE,"查询客户信息错误");
		}
		ResponseResult result =  new ResponseResult();
		try{
			ResetLoginPwdReq model = new ResetLoginPwdReq();
			model.setLoginName(mobile);
			model.setLoginNewPwd(newPwd);
			model.setAppVersion("1.0");
			model.setSystemType("channel");
			model.setSourceType("PCWeb");
			model.setSign(RequestSignUtils.addSign(model,getSignKey(WebConstants.USER_MD5_SIGN_KEY)));
			cn.xn.user.domain.CommonRlt<cn.xn.user.domain.EmptyObject> changeRlt = p2pPwdService.doResetLoginPwd(model);
			if(changeRlt.getReturnCode() ==0){//消息中心写数据
				int appType= AppTypeEnum.CHANNEL.getKey();//待定
				String userId = saleUserInfo.getCustomerId();
				String content = String.format("亲爱的%s,您的密码信息已于%s由管理员更新,敬请留意", StringUtils.isNotBlank(saleUserInfo.getName())?saleUserInfo.getName():saleUserInfo.getMobile(),DateUtils.getCurrentDate());
				Msg msg= new Msg();
				msg.setAppType(appType);
				msg.setStatus(0);
				msg.setType(1);//待定
				msg.setUserNumber(userId);
				msg.setContent(content);
				msgService.insert(msg);
				result.setIsFlag(true);
			}else{
				result.setIsFlag(false);
				result.setMsg(changeRlt.getReturnMsg());
			}
		}catch(Exception e){
			result.setIsFlag(false);
		}
		return result;
		
		
	}


	/**
	 * 查询理财师变更记录
	 * @param mobile
	 * @Author Libin
	 * @return
	 * @throws Exception
     */
	@RequestMapping("/querycfphistory/{mobile}")
    public ModelAndView queryCFPhistory(@PathVariable(value = "mobile") String mobile) throws Exception{
		ModelAndView modelAndView  = new ModelAndView("cfplanner/cfplanner_history");
		UnconventionalRecord unconventionalRecord  = new UnconventionalRecord();
		unconventionalRecord.setCfpMobile(mobile);
		List<UnconventionalRecord> unconventionalRecordList = lcsListService.queryCfpLevelOptRecord(unconventionalRecord);
		modelAndView.addObject("queryList",unconventionalRecordList);
		return modelAndView;
	}
	
	/**
	 * 查询理财师变更记录
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "getHisrec")
	@ResponseBody
	@RequestLogging("查询理财师变更记录")
	public Object getHisrec(LcsCustomerRequest req) {
		PageResponse<UnconventionalRecord> response = new PageResponse<UnconventionalRecord>();
		try {
			PaginatorSevReq pageRequest = req.toPaginatorSevReq();
			pageRequest.getQueryConditions().put("lcsNumber",req.getLcsNumber());
			pageRequest.getQueryConditions().put("optTypes","7,8,9");//只查询职级日记
			return customerCftRelFixService.queryUnRecList(pageRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	/**
	 * 更改上级界面
	 */
	@RequestMapping("toChangeParent")
	public String toChangeParent(String mobile,Model model){
		SaleUserInfo saleUserInfo = saleUserInfoService.getSaleUserInfoByMobile(mobile);
		SaleUserInfo parent = null;
		String saleUserName = "";
		String parentName = "";
		if(saleUserInfo != null) {
			saleUserName = saleUserInfo.getMobile();
		}
		if(saleUserInfo != null && saleUserInfo.getParentId() != null && !"".equals(saleUserInfo.getParentId())){
			parent = saleUserInfoService.getSaleUserInfoByNumber(saleUserInfo.getParentId());
			if(parent != null ) {
				parentName = parent.getMobile();
			}
		}
		model.addAttribute("saleUserName",saleUserName);
		model.addAttribute("parentName",parentName);
		return "cfplanner/toChangeParent";
	}

	/**
	 * 更改上级
	 * @param reqeust
	 * @param mobile
	 * @param parentMobile
	 * @param changeType
     * @return
     */
	@RequestMapping(value = "changeParent")
	@ResponseBody
	@RequestLogging("更改上级")
	public Object changeParent(HttpServletRequest reqeust,@RequestParam String mobile,@RequestParam String parentMobile,@RequestParam String changeType) {
		if(StringUtils.isBlank(mobile) || StringUtils.isBlank(changeType)){
			return new ResponseResult(ResponseConstant.FAILURE,"参数错误");
		}
		if(StringUtils.isNotBlank(changeType) && "1".equals(changeType)){
			if(StringUtils.isBlank(parentMobile)) {
				return new ResponseResult(ResponseConstant.FAILURE,"新上级理财师号码不正常");
			}
		}
		SaleUserInfo saleUserInfo = saleUserInfoService.getSaleUserInfoByMobile(mobile);
		if(saleUserInfo == null){
			return new ResponseResult(ResponseConstant.FAILURE,"查询客户信息错误");
		}
		SaleUserInfo parent = saleUserInfoService.getSaleUserInfoByMobile(parentMobile);
		if(parent == null){
			return new ResponseResult(ResponseConstant.FAILURE,"更换失败，新上级理财师不存在");
		}
		ResponseResult result =  new ResponseResult();
		try{			
			//推送消息  要在解绑之前查询相关的信息
			//该理财师相关信息
			SaleUserInfo saleUserInfoNew = saleUserInfoService.getSaleUserInfoByMobile(mobile);
			//原先上级理财师相关信息
			SaleUserInfo saleUserInfoTop = saleUserInfoService.queryParentByMobile(mobile);
			//现在绑定理财师相关信息
			SaleUserInfo saleUserInfoTopNew = saleUserInfoService.getSaleUserInfoByMobile(parentMobile);
			
			saleUserInfoService.changeParent(mobile, parentMobile, changeType, saleUserInfo);
			result.setIsFlag(true);
			
			//发送消息
			//上级理财师
			if(StringUtils.isEmpty(saleUserInfoNew.getName())){
				saleUserInfoNew.setName("");
			}
			String nameStrlcs = fixphoneNmber(StringUtils.trim( "(" + saleUserInfoNew.getName() + saleUserInfoNew.getMobile() + ")" ));
			String lcsContent = String.format("理财师%s已与您解除团队关系",nameStrlcs);
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("customUrlKey", SmsTypeEnum.LCSJBSJSUCCESS.getMsg());
			boolean khjblcsBL = pushMsgService.pushMessage(AppTypeEnum.CHANNEL.getKey(), SmsTypeEnum.LCSJBSJSUCCESS.getKey(), saleUserInfoTop.getCustomerId(), nameStrlcs, 0, lcsContent, 0, map1);
			LOGGER.info("理财师更改上级推送原先上级消息：{}-------------------{}", lcsContent,khjblcsBL==true?"推送成功":"推送失败");
			//该理财师
			if(StringUtils.isEmpty(saleUserInfoTop.getName())){
				saleUserInfoTop.setName("");
			}
			String nameStrjf = fixphoneNmber(StringUtils.trim( "(" + saleUserInfoTop.getName() + saleUserInfoTop.getMobile() + ")" ));
			String nameStrjfNew = fixphoneNmber(StringUtils.trim( "(" + saleUserInfoTopNew.getName() + saleUserInfoTopNew.getMobile() + ")" ));
			String jfContent = String.format("您已与理财师%s解除团队关系,加入了%s的团队", nameStrjf,nameStrjfNew);
			String parameterValue = nameStrjf +","+nameStrjfNew;
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("customUrlKey", SmsTypeEnum.LCSJBXJSUCCESS.getMsg());
			boolean khjbjfBL = pushMsgService.pushMessage(AppTypeEnum.CHANNEL.getKey(), SmsTypeEnum.LCSJBXJSUCCESS.getKey(), saleUserInfoNew.getCustomerId(), parameterValue, 0, jfContent, 0, map2);
			LOGGER.info("理财师更改上级推送下级消息：{}-------------------{}", jfContent,khjbjfBL==true?"推送成功":"推送失败");
		}catch(Exception e){
			LOGGER.error("更改上级错误" + e);
			result.setIsFlag(false);
			result.setMsg("系统异常");
		}
		return result;
	}
	
	/**
	 * 禁止登录90天
	 * @param reqeust
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "disabledLogin")
	@ResponseBody
	@RequestLogging("禁止登录90天")
	public Object disabledLogin(HttpServletRequest reqeust,@RequestParam String mobile) {
		if(StringUtils.isBlank(mobile) ){
			return new ResponseResult(ResponseConstant.FAILURE,"参数错误");
		}
		SaleUserInfo saleUserInfo = saleUserInfoService.getSaleUserInfoByMobile(mobile);
		if(saleUserInfo == null){
			return new ResponseResult(ResponseConstant.FAILURE,"用户不存在");
		}
		ResponseResult result =  new ResponseResult();
		try{
			saleUserInfoService.disabledLogin90days(mobile);
			result.setIsFlag(true);
		}catch(Exception e){
			LOGGER.error("禁止登录90天" + e);
			result.setIsFlag(false);
			result.setMsg("系统异常");
		}
		return result;
	}


	/**
	 * 查子级组织机构列表
	 * @param torginfoModel
	 * @return
	 * @throws Exception
     */
	@ResponseBody
	@RequestMapping(value = "/querytorginfonode")
	public List<TorginfoModel> queryTorginfoNodeList(TorginfoModel torginfoModel) throws Exception{
		List<TorginfoModel>  result  = lcsListService.findTorgginNodeListByParentId(torginfoModel);
		return result;
	}

	/**
	 *  通过理财师ID从技术平台查询用户信息与绑卡信息
	 * @param fid
	 * @return
	 * @throws Exception
     */
	@ResponseBody
	@RequestMapping(value = "/findtcuserinfo")
	public Map<String,Object> findCustomerInfoTCById(String fid) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		UserBindCardRlt userBindCardRlt= userInfoTCService.findUserBindCardById(fid);
		if(userBindCardRlt!=null){
			result.put("bankCode",userBindCardRlt.getBankCode());
			result.put("bankName",userBindCardRlt.getBankName());
			if(StringUtils.isNotBlank(userBindCardRlt.getIdentityCard())){
				result.put("certNo",userBindCardRlt.getIdentityCard());
			}
			else{
				result.put("certNo","NOTFOUND");
			}
		}
		else{
			result.put("bankCode","");
			result.put("bankName","NOTFOUND");
			result.put("certNo","NOTFOUND");
		}
		return result;
	}

	public String fixphoneNmber(String msg){
		return msg.substring(0,msg.length()-12)+msg.substring(msg.length()-5);
	}
}
