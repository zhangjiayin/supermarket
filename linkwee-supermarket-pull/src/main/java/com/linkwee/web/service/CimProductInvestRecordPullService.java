package com.linkwee.web.service;

import java.math.BigDecimal;
import java.util.List;

import com.linkwee.web.model.CimProductInvestRecordPull;
import com.linkwee.web.push.vo.ResultVO;

 /**
 * 
 * @描述： CimProductInvestRecordPullService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年02月09日 17:21:31
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductInvestRecordPullService {
	
	BigDecimal getStockInvestAmt(String investId);

	int inserts(List<CimProductInvestRecordPull> investRecords);
	
	List<CimProductInvestRecordPull> getInvestRecord();
	
	int updateInvestRecordInStatus(Integer[] preStatus,Integer afterStatus,String msg);
	
	
	int updatePushInvestRecordStatus(List<ResultVO> results,Integer status);
}
