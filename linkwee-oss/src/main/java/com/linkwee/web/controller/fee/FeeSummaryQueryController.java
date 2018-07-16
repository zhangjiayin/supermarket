package com.linkwee.web.controller.fee;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.fee.FeeSummaryEntity;
import com.linkwee.web.request.FeeRequest;
import com.linkwee.web.service.FeeDetailService;
import com.linkwee.web.util.RequestLogging;

/**
 * Title: FeeSummaryQueryController Description:佣金汇总记录 Company: Copyright (c)
 * 深圳市前海领会科技有限公司-版权所有
 * 
 * @author jinbo.fu
 * @date 2016年5月17日上午10:34:00
 */
@Controller
@RequestMapping(value = "/feesummary")
@RequestLogging("佣金汇总记录")
public class FeeSummaryQueryController {
	private static final Logger logger = LoggerFactory
			.getLogger(FeeDetailQueryController.class);
	@Resource
	private FeeDetailService feeDetailService;

	@RequestMapping(value = "init")
	public String init() {
		return "fee/feeSummary-list";
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	@RequestLogging("佣金汇总记录列表")
	public DataTableReturn list(FeeRequest feeRequest) {
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		logsb.append("list|saleuser=").append(feeRequest.getSaleUser());
		Page<FeeSummaryEntity> page = new Page<FeeSummaryEntity>(
				feeRequest.getPageIndex(), feeRequest.getPageSize());
		DataTableReturn dataTableReturn = feeDetailService.queryFeeSummary(
				feeRequest.getSaleUser(), page);
		logger.info("feedetail/list time:"
				+ (System.currentTimeMillis() - start) + "ms");
		return dataTableReturn;
	}
}
