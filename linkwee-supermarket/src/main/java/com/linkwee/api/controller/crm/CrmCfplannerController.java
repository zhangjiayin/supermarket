package com.linkwee.api.controller.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.api.request.crm.CfplannerDataRequest;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.RequestLogging;

@Controller
@RequestLogging("猎财顾问")
@RequestMapping("/api/crm/crmCfplanner")
public class CrmCfplannerController extends BaseController {

	@Autowired
	private CrmCfplannerService crmCfplannerService;
	
	/**
	 * 猎财顾问数据
	 * @param appRequestHead
	 * @return
	 */
    @RequestMapping(value="/cfplannerData")
    @ResponseBody
    @RequestLogging("猎财顾问数据")
	public BaseResponse cfplannerData(AppRequestHead head,CfplannerDataRequest cfplannerDataRequest) {
    	String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());//查询userId
		if(StringUtils.isNotBlank(userId) && !"undefined".equals(userId)){
			cfplannerDataRequest.setUserId(userId);
			return AppResponseUtil.getSuccessResponse(crmCfplannerService.queryCfplannerData(cfplannerDataRequest));
		} else {
			return AppResponseUtil.getBaseResponse("100002", "token不能为空");
		}
	}
}
