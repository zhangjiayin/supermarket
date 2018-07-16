package com.linkwee.api.controller.act;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.api.response.act.AddFeeCouponResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.web.service.ActAddFeeCouponService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.RequestLogging;

@Controller
@RequestMapping("api/addFeeCoupon")
@RequestLogging("加拥券")
public class AddFeeCouponController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddFeeCouponController.class);
	
	@Resource
	private ActAddFeeCouponService addFeeCouponService;
	
	@RequestMapping("pageList/4.5.0")
	@ResponseBody
	@RequestLogging("查询加拥券列表4.5.0")
	public BaseResponse pageList(PaginatorRequest request, AppRequestHead head){
		try{
			String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
			if(AppUtils.isChannelApp(head.getOrgNumber())){//理财师端
				PaginatorResponse<AddFeeCouponResponse> responseList = addFeeCouponService.pageList(request,userId);
				return AppResponseUtil.getSuccessResponse(responseList);
			}
			return AppResponseUtil.getErrorBusi("100002","请使用正确的app类型");
		}
		catch(Exception e){
			LOGGER.error("addFeeCoupon pageList exception", e);
		}
		return new BaseResponse(-1,"查询失败，请联系客服");
	}
}
