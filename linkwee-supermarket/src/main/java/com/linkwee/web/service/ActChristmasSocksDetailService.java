package com.linkwee.web.service;

import java.util.List;

import com.linkwee.api.request.act.SocksCollectHelpRequest;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActChristmasSocksDetail;
import com.linkwee.web.service.ActChristmasSocksDetailService;
 /**
 * 
 * @描述： ActChristmasSocksDetailService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月20日 13:54:37
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActChristmasSocksDetailService extends GenericService<ActChristmasSocksDetail,Long>{

	/**
	 * 助力记录
	 * @param userId
	 * @return
	 */
	List<ActChristmasSocksDetail> queryHelpRecord(String userId);

	int help(String userId, SocksCollectHelpRequest req) throws Exception;
}
