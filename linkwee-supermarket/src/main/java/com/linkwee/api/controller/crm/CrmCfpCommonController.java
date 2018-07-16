package com.linkwee.api.controller.crm;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.api.request.crm.CustomerDetailRequest;
import com.linkwee.api.response.crm.FeeCalBaseDataResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.web.model.CrmCfpLevel;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CrmCfpLevelService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;

@Controller
@RequestLogging("理财师公用")
@RequestMapping("/api/crm/cfpcommon")
public class CrmCfpCommonController {

	@Autowired
	private CrmCfpLevelService  crmCfpLevelService;
	@Autowired
	private CimOrginfoService cimOrginfoService;
	@Autowired
	private CrmCfplannerService crmCfplannerService;
	
	/**
	 * 佣金计算器基本数据
	 * @param appRequestHead
	 * @return
	 */
    @RequestMapping(value="/feeCalBaseData")
    @ResponseBody
    @RequestLogging("佣金计算器基本数据")
	public BaseResponse feeCalLevelType() {
    	FeeCalBaseDataResponse feeCalBaseDataResponse = new FeeCalBaseDataResponse();
    	CrmCfpLevel crmCfpLevel = new CrmCfpLevel();
    	
    	List<CrmCfpLevel> crmCfpLevelList = crmCfpLevelService.selectListByCondition(crmCfpLevel);
    	feeCalBaseDataResponse.setCrmCfpLevelList(crmCfpLevelList);
    	
    	List<String> feeTypeList = cimOrginfoService.selectFeeCalFeeType();
    	feeCalBaseDataResponse.setFeeTypeList(feeTypeList);
    	return AppResponseUtil.getSuccessResponse(feeCalBaseDataResponse);
	}
    
    /**
	 * 4.5.0 理财师添加关注
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
			CrmCfplanner crmCfplanner = new CrmCfplanner();
			crmCfplanner.setUserId(req.getUserId());
			crmCfplanner.setIsImportant(new Byte("1"));
			crmCfplannerService.updateByUserId(crmCfplanner);
		} catch (NumberFormatException e) {
			return AppResponseUtil.getErrorBusi("error","添加关注失败 ："+e.getMessage());
		}
		return AppResponseUtil.getSuccessResponse();
	}
	
	/**
	 * 4.5.0 理财师取消关注 
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
			CrmCfplanner crmCfplanner = new CrmCfplanner();
			crmCfplanner.setUserId(req.getUserId());
			crmCfplanner.setIsImportant(new Byte("0"));
			crmCfplannerService.updateByUserId(crmCfplanner);
		} catch (NumberFormatException e) {
			return AppResponseUtil.getErrorBusi("error","取消关注 ：" + e.getMessage());
		}
		return AppResponseUtil.getSuccessResponse();
	}
}
