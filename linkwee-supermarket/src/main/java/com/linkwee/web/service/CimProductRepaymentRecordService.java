package com.linkwee.web.service;

import com.linkwee.core.generic.GenericService;
import com.linkwee.openapi.request.RepaymentRecordReq;
import com.linkwee.web.model.cim.CimProductRepaymentRecord;
 /**
 * 
 * @描述： CimProductRepaymentRecordService服务接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年07月25日 17:15:45
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductRepaymentRecordService extends GenericService<CimProductRepaymentRecord,Long>{

	/**
	 * 回款记录
	 * @param recordReq
	 * @param orgNumber
	 * @throws Exception
	 */
	void insertRepaymentRecord(RepaymentRecordReq recordReq,String orgNumber) throws Exception;
}
