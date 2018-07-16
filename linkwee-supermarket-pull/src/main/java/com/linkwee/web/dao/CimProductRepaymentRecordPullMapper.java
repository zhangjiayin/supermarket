package com.linkwee.web.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.linkwee.web.model.CimProductRepaymentRecordPull;
import com.linkwee.web.push.vo.ResultVO;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年02月09日 17:21:31
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductRepaymentRecordPullMapper{
	
	
	int inserts(@Param("repaymentRecords")List<CimProductRepaymentRecordPull> repaymentRecords);
	
	List<CimProductRepaymentRecordPull> getRepaymentRecord();
	
	List<String> getRepaymentRecordIds(@Param("repaymentRecords")List<CimProductRepaymentRecordPull> repaymentRecords);
	
	int updateInvestRecordStockAmt(@Param("updates")Map<String, BigDecimal> updates);
	
	int updateRepaymentRecordInStatus(@Param("preStatus")Integer[] preStatus,@Param("afterStatus")Integer afterStatus,@Param("msg")String msg);
	
	int updatePushRepaymentRecordStatus(@Param("results") List<ResultVO> results,@Param("status")Integer status);
}
