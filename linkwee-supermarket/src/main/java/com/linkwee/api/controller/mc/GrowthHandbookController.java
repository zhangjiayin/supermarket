package com.linkwee.api.controller.mc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.api.request.GrowthHandbookClassifyRequest;
import com.linkwee.api.response.GrowthHandbookClassifyListResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.SmGrowthHandbook;
import com.linkwee.web.model.SmGrowthHandbookClassify;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.SmGrowthHandbookClassifyService;
import com.linkwee.web.service.SmGrowthHandbookService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;
import com.linkwee.xoss.util.RequestLogging;

/**
 * 
 * @描述：成长手册
 *
 * @author hxb
 * @时间 2015年10月16日上午11:06:20
 *
 */
@Controller
@RequestMapping(value = "/api/growthHandbook")
@RequestLogging("成长手册")
public class GrowthHandbookController  {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GrowthHandbookController.class);
	
	@Resource
	private SmGrowthHandbookService growthHandbookService;
	
	@Resource
	private SmGrowthHandbookClassifyService growthHandbookClassifyService;
	
	@Resource
	private CrmCfplannerService cfplannerService;
	
	/**
	 * 成长手册分类
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("成长手册分类")
	@RequestMapping("/classify/4.1.1")
	@ResponseBody
	public BaseResponse classify(AppRequestHead head) throws Exception {		
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		SmGrowthHandbookClassify condition = new SmGrowthHandbookClassify();
		condition.setAppType(appType);
		List<SmGrowthHandbookClassify> growthHandbookClassifyList = growthHandbookClassifyService.classify(condition);	
		return AppResponseUtil.getSuccessResponse(growthHandbookClassifyList);
	}
	
	/**
	 * 成长手册分类列表
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("成长手册分类列表")
	@RequestMapping("/classifyList/4.1.1")
	@ResponseBody
	public BaseResponse classifyList(GrowthHandbookClassifyRequest req,AppRequestHead head) throws Exception {		
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();	
		SmGrowthHandbookClassify ghbc = growthHandbookClassifyService.selectById(Long.valueOf(req.getTypeCode()));
		GrowthHandbookClassifyListResponse resp = new GrowthHandbookClassifyListResponse();
		resp.setBannerImg(ghbc.getImg());
		Page<SmGrowthHandbook> page  = new Page<SmGrowthHandbook>(req.getPageIndex(),req.getPageSize()); //默认每页10条
		Map<String,Object> conditions = new HashMap<String, Object>(); //查询条件
        conditions.put("appType", appType);
        conditions.put("typeCode", req.getTypeCode());       
		PaginatorResponse<SmGrowthHandbook> result = growthHandbookService.classifyList(page, conditions);
		resp.setGrowthHandbookList(result);
		return AppResponseUtil.getSuccessResponse(resp);
	}
	
	/**
	 * 个人定制列表
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("个人定制列表")
	@RequestMapping("/personalCustomization/4.1.1")
	@ResponseBody
	public BaseResponse personalCustomizationList(AppRequestHead head) throws Exception {		
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
				
		Map<String,Object> conditions = new HashMap<String, Object>(); //查询条件
        conditions.put("appType", appType);
        if(userId.equals("undefined") || StringUtils.isBlank(userId)){
        	conditions.put("cfpLevel", "TA"); 
        }else{
        	CrmCfplanner cfplanner = cfplannerService.queryCfplannerByUserId(userId);
            conditions.put("cfpLevel", cfplanner.getJobGrade()); 
        }
                
		List<SmGrowthHandbook> growthHandbookList = growthHandbookService.personalCustomizationList(conditions);
		return AppResponseUtil.getSuccessResponse(growthHandbookList);
	}
	
	/**
	 * 成长手册详情
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("成长手册详情")
	@RequestMapping("/detail/4.1.1")
	@ResponseBody
	public BaseResponse detail(@RequestParam(required=true)String id,AppRequestHead head) throws Exception {		
		SmGrowthHandbook growthHandbook = growthHandbookService.selectById(Long.valueOf(id));
		growthHandbook.setReadingAmount(growthHandbook.getReadingAmount()+1);
		growthHandbookService.update(growthHandbook);
		return AppResponseUtil.getSuccessResponse(growthHandbook);
	}
	
	/**
	 * 成长手册列表
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestLogging("成长手册列表")
	@RequestMapping("/list/4.3.0")
	@ResponseBody
	public BaseResponse list(GrowthHandbookClassifyRequest req,AppRequestHead head) throws Exception {				
		Integer appType = AppUtils.getAppType(head.getOrgNumber()).getKey();		
		SmGrowthHandbook temp = new SmGrowthHandbook();
		temp.setAppType(appType);
		temp.setStatus(1);
		List<SmGrowthHandbook> growthHandbookList = growthHandbookService.selectListByCondition(temp);
		for(SmGrowthHandbook smGrowthHandbook : growthHandbookList){
			smGrowthHandbook.setContent("");
		}
		return AppResponseUtil.getSuccessResponse(growthHandbookList);
	}
}
