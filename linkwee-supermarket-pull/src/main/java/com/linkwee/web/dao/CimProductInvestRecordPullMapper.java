package com.linkwee.web.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkwee.web.model.CimProductInvestRecordPull;
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
public interface CimProductInvestRecordPullMapper {
	
	int inserts(@Param("investRecords")List<CimProductInvestRecordPull> investRecords);
	
	BigDecimal getStockInvestAmt(@Param("investId") String investId);
	
	List<String> getInvestRecordIds(@Param("investRecords")List<CimProductInvestRecordPull> investRecords);
	
	List<CimProductInvestRecordPull> getInvestRecord();
	
	int updateInvestRecordInStatus(@Param("preStatus")Integer[] preStatus,@Param("afterStatus")Integer afterStatus,@Param("msg")String msg);
	
	int updatePushInvestRecordStatus(@Param("results") List<ResultVO> results,@Param("status")Integer status);
}
