package com.linkwee.web.service;

import java.util.List;

import com.linkwee.web.model.CimProductRepaymentRecordPull;
import com.linkwee.web.push.vo.ResultVO;
 /**
 * 
 * @描述： CimProductRepaymentRecordPullService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年02月09日 17:21:31
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductRepaymentRecordPullService {


	int inserts(List<CimProductRepaymentRecordPull> repaymentRecords);
	
	List<CimProductRepaymentRecordPull> getRepaymentRecord();
	
	int updateRepaymentRecordInStatus(Integer[] preStatus,Integer afterStatus,String msg);
	
	
	int updatePushRepaymentRecordStatus(List<ResultVO> results,Integer status);
}
