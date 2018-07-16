package com.linkwee.web.controller.fee;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.fee.FeeDetail;
import com.linkwee.web.request.FeeRequest;
import com.linkwee.web.service.FeeDetailService;
import com.linkwee.web.util.RequestLogging;

/**
 * Title: FeeDetailQueryController Description: 佣金记录 Company: Copyright (c)
 * 深圳市前海领会科技有限公司-版权所有
 * 
 * @author jinbo.fu
 * @date 2016年5月17日上午10:33:30
 */
@Controller
@RequestMapping(value = "/feedetail")
@RequestLogging("佣金记录")
public class FeeDetailQueryController {

	private static final Logger logger = LoggerFactory
			.getLogger(FeeDetailQueryController.class);

	@Resource
	private FeeDetailService feeDetailService;

	@RequestMapping(value = "/init")
	public String init() {
		return "fee/feeDetail-list";
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	@RequestLogging("佣金记录列表")
	public DataTableReturn list(FeeRequest request) {
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		logsb.append("list|saleuser=").append(request.getSaleUser());
		Page<FeeDetail> page = new Page<FeeDetail>(request.getPageIndex(),
				request.getPageSize());
		DataTableReturn dataTableReturn = feeDetailService.queryFeeDetail(
				request.getSaleUser(), page);
		logger.info("feedetail/list time:"
				+ (System.currentTimeMillis() - start) + "ms");
		return dataTableReturn;
	}
}