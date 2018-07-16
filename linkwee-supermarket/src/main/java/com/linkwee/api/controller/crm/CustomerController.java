package com.linkwee.api.controller.crm;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.crm.CustomerDetailRequest;
import com.linkwee.api.request.crm.MycustomersRequest;
import com.linkwee.api.response.crm.CfpCustomerCountResponse;
import com.linkwee.api.response.crm.CustomerDetailResponse;
import com.linkwee.api.response.crm.MycustomersResponse;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.constant.ApiInvokeLogConstant;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.web.model.SysApiInvokeLog;
import com.linkwee.web.model.crm.CfpCustomerCountResp;
import com.linkwee.web.model.crm.CustomerDetailResp;
import com.linkwee.web.model.crm.MycustomersResp;
import com.linkwee.web.model.crm.OrgSimpleResp;
import com.linkwee.web.service.CimProductInvestRecordService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.CustomerService;
import com.linkwee.web.service.SysApiInvokeLogService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.ResponseUtil;

/**
 * 客户
 *
 * @author Mignet
 * @since 2014年5月28日 下午3:54:00
 **/
@Controller
@RequestMapping(value = "/api/customer")
public class CustomerController extends BaseController{
	
    @Resource
    private CrmCfplannerService crmCfplannerService;

    @Resource
    private CrmInvestorService crmInvestorService;
    
    @Resource
    private CrmUserInfoService crmUserInfoService;
    
    @Resource
    private CustomerService customerService;
    
    @Resource
	private SysApiInvokeLogService sysApiInvokeLogService;
    
    @Resource
    private CimProductInvestRecordService investRecordService;
    @Resource
	private SysConfigService sysConfigService;
    
	/**
	 * 客户首页
	 * @param head
	 * @return
	 */
	@RequestMapping("homepage")
	@ResponseBody
	public BaseResponse homePage(AppRequestHead head) {
		CfpCustomerCountResp rlt = customerService.queryCfpCustomerCountResp(JsonWebTokenHepler.getUserIdByToken(head.getToken()));
		return AppResponseUtil.getSuccessResponse(rlt,CfpCustomerCountResponse.class);
	}
	
	/**
	 * 客户列表
	 * @param head
	 * @return
	 */
	@RequestMapping("mycustomers/pageList")
	@ResponseBody
	public BaseResponse customerMycustomers(MycustomersRequest req, AppRequestHead head) {
		LOGGER.info("客户列表, pageRequest={}", JSONObject.toJSONString(req));
		Map<String ,Object> query = new HashMap<String ,Object>();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		query.put("userId", userId);
		if (StringUtils.isNotBlank(req.getName())) {
			query.put("userName", req.getName());
		}
		if (StringUtils.isNotBlank(req.getCustomerType())) {
			query.put("customerType", req.getCustomerType());
		}
		if(req.getSort() != null || req.getOrder() != null){
			query.put("sort",req.getSort());
			query.put("order", req.getOrder());
		}
		SysApiInvokeLog apiInvokeLogCustomer = sysApiInvokeLogService.queryApiInvokeLog(ApiInvokeLogConstant.NAME_CUSTOMER_MYCUSTOMERS_PAGELIST, userId, AppTypeEnum.CHANNEL.getKey());
		Date readCustomerDate = null;
		if(apiInvokeLogCustomer!=null){
			readCustomerDate = apiInvokeLogCustomer.getChgTime();
		}else{
			readCustomerDate = DateUtils.parse("1990-01-01",DateUtils.FORMAT_SHORT);
		}
		query.put("date", readCustomerDate);
		Page<MycustomersResp> page = new Page<MycustomersResp>(req.getPageIndex(), req.getPageSize());
		PaginatorSevResp<MycustomersResp> datas = customerService.queryMycustomerList(query,page);
		sysApiInvokeLogService.updateApiInvokeLog(ApiInvokeLogConstant.NAME_CUSTOMER_MYCUSTOMERS_PAGELIST, userId,AppTypeEnum.CHANNEL.getKey());
		return AppResponseUtil.getSuccessResponse(datas, MycustomersResponse.class);
		
	}
	
	/**
	 * 客户详情
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("detail")
	@ResponseBody
	public BaseResponse customerDetail(@Valid CustomerDetailRequest req,
			BindingResult result, AppRequestHead head) throws Exception {
		if (ResponseUtil.existsParamsError(result)) {
			return ResponseUtil.getErrorParams(result);
		}
		CrmInvestor investor= crmInvestorService.queryInvestorByUserId(req.getUserId());
		if(investor == null ) {
			return AppResponseUtil.getErrorBusi("customerNotExsit","客户不存在");
		}
		CustomerDetailResp rlt = new CustomerDetailResp();
		rlt.setMobile(investor.getMobile());
		rlt.setUserName(investor.getUserName());
		rlt.setHeadImage(investor.getHeadImage());
		rlt.setImportant((int)investor.getIsImportant());
		rlt.setCurrInvestAmt(investRecordService.queryCurrInvestAmount(req.getUserId()));
		rlt.setFeeAmt(customerService.queryFeeAmtByCfpAndInvestor(JsonWebTokenHepler.getUserIdByToken(head.getToken()), req.getUserId()));
		rlt.setTotalInvestAmt(investRecordService.queryCustomerInvestTotalAmount(req.getUserId()));
		rlt.setRegisterDate(investor.getCreateTime());
		rlt.setFirstRcpDate(crmInvestorService.queryFirstRcpDate(req.getUserId()));
		List<OrgSimpleResp> orgList = customerService.queryRegisteredOrgList(req.getUserId());
		if(orgList != null) {
			for(OrgSimpleResp bo : orgList) {
				if(bo != null && bo.getOrgLogo() != null && !"".equals(bo.getOrgLogo())){
					bo.setOrgLogo(sysConfigService.getImageUrl(bo.getOrgLogo()));
				}
			}
			rlt.setRegisteredOrgCount(orgList.size());
			rlt.setRegisteredOrgList(orgList);
		}
		return ResponseUtil.getSuccessResponse(rlt,CustomerDetailResponse.class);
	}
	
	/**
	 * 设置重要客户
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("important/add")
	@ResponseBody
	public BaseResponse addImportant(@Valid CustomerDetailRequest req, BindingResult result, AppRequestHead head) {
		if (ResponseUtil.existsParamsError(result)) {
			return ResponseUtil.getErrorParams(result);
		}
		try {
			CrmInvestor crmInvestor = new CrmInvestor();
			crmInvestor.setUserId(req.getUserId());
			crmInvestor.setIsImportant(new Byte("1"));
			crmInvestorService.updateByUserId(crmInvestor);
		} catch (NumberFormatException e) {
			LOGGER.error("设置重要客户失败 ：" , e );
			return AppResponseUtil.getErrorBusi("error","设置重要客户失败 ："+e.getMessage());
		}
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 移除重要客户
	 * @param req
	 * @param result
	 * @param head
	 * @return
	 */
	@RequestMapping("important/remove")
	@ResponseBody
	public BaseResponse removeImportant(@Valid CustomerDetailRequest req, BindingResult result, AppRequestHead head) {
		if (ResponseUtil.existsParamsError(result)) {
			return ResponseUtil.getErrorParams(result);
		}
		try {
			CrmInvestor crmInvestor = new CrmInvestor();
			crmInvestor.setUserId(req.getUserId());
			crmInvestor.setIsImportant(new Byte("0"));
			crmInvestorService.updateByUserId(crmInvestor);
		} catch (NumberFormatException e) {
			LOGGER.error("移除重要客户失败 ：", e );
			return AppResponseUtil.getErrorBusi("error","移除重要客户失败 ：" + e.getMessage());
		}
		return AppResponseUtil.getSuccessResponse();
	}
	
}
