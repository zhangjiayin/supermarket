package com.linkwee.api.controller.crm;

import java.util.Date;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.linkwee.api.response.AccountBookStatisticResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CrmUserAccountBook;
import com.linkwee.web.service.CrmUserAccountBookService;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.RequestLogging;

 /**
 * 
 * @描述： CrmUserAccountBookController控制器
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月14日 15:31:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Controller
@RequestMapping(value = "/api/accountbook")
@RequestLogging("CrmUserAccountBookController控制器")
public class CrmUserAccountBookController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CrmUserAccountBookController.class);

	@Resource
	private CrmUserAccountBookService crmUserAccountBookService;
	
	/**
	 * 记账本统计
	 * @param head
	 * @return
	 */
	@RequestMapping(value="/statistics/4.5.0")
    @ResponseBody
    @RequestLogging("记账本统计")
	public BaseResponse statistics(AppRequestHead head) {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		AccountBookStatisticResponse resultResp = crmUserAccountBookService.statistics(userId);
    	return AppResponseUtil.getSuccessResponse(resultResp);
	}
	
	/**
	 * 在投列表
	 * @param head
	 * @return
	 */
	@RequestMapping(value="/investing/list/4.5.0")
    @ResponseBody
    @RequestLogging("在投列表")
	public BaseResponse investingList(@Valid PaginatorRequest req,AppRequestHead head) {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		Page<CrmUserAccountBook> page  = new Page<CrmUserAccountBook>(req.getPageIndex(),req.getPageSize());
		PaginatorResponse<CrmUserAccountBook> resultList = crmUserAccountBookService.investingList(page,userId);
    	return AppResponseUtil.getSuccessResponse(resultList);
	}
	
	/**
	 * 记账本详情
	 * @param id
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/investing/detail/4.5.0")
	@RequestLogging("记账本详情")
	@ResponseBody
	public BaseResponse investingDetail(String id,AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		CrmUserAccountBook accountBook = new CrmUserAccountBook();
		accountBook.setId(Integer.parseInt(id));
		accountBook.setUserId(userId);
		accountBook = crmUserAccountBookService.selectOne(accountBook);
		return AppResponseUtil.getSuccessResponse(accountBook);
	}
	
	/**
	 * 记账本编辑
	 * @param accountBook
	 * @param head
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/investing/edit/4.5.0")
	@RequestLogging("记账本编辑")
	@ResponseBody
	public BaseResponse investingEdit(CrmUserAccountBook accountBook,AppRequestHead head) throws Exception {
		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		accountBook.setUserId(userId);
		try {
			if(accountBook.getId() != null){
				accountBook.setUpdateTime(new Date());
				crmUserAccountBookService.update(accountBook);
			}else{
				crmUserAccountBookService.insert(accountBook);
			}
		} catch (Exception e) {
			LOGGER.info("记账本编辑出错={}", JSON.toJSONString(e));
			AppResponseUtil.getErrorBusi(new BaseResponse("10010","参数错误"));
		}
		return AppResponseUtil.getSuccessResponse();
	}
}
