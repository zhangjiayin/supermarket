package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.linkwee.web.dao.CimProductRepaymentRecordPullMapper;
import com.linkwee.web.model.CimProductRepaymentRecordPull;
import com.linkwee.web.push.vo.ResultVO;
import com.linkwee.web.service.CimProductRepaymentRecordPullService;


 /**
 * 
 * @描述：CimProductRepaymentRecordPullService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年02月09日 17:21:31
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimProductRepaymentRecordPullService")
public class CimProductRepaymentRecordPullServiceImpl  implements CimProductRepaymentRecordPullService{
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(CimProductRepaymentRecordPullServiceImpl.class);
	
	@Resource
	private CimProductRepaymentRecordPullMapper cimProductRepaymentRecordPullMapper;

	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int inserts(List<CimProductRepaymentRecordPull> repaymentRecords) {
		if(CollectionUtils.isEmpty(repaymentRecords))return 0;
	
		List<String> repaymentRecordIds = cimProductRepaymentRecordPullMapper.getRepaymentRecordIds(repaymentRecords);
		
		if(CollectionUtils.isNotEmpty(repaymentRecordIds)){
			Set<String> repaymentRecordIdsSet = Sets.newHashSet(repaymentRecordIds);
			List<CimProductRepaymentRecordPull> repaymentRecordTemps = Lists.newArrayListWithCapacity(repaymentRecords.size() - repaymentRecordIdsSet.size());
			for (CimProductRepaymentRecordPull repaymentRecord : repaymentRecords) {
				if(repaymentRecordIdsSet.contains(repaymentRecord.getRepaymentId()))continue;
				repaymentRecordTemps.add(repaymentRecord);
			}
			if(CollectionUtils.isEmpty(repaymentRecordTemps))return 0;
			repaymentRecords = repaymentRecordTemps;
		}
		int count = cimProductRepaymentRecordPullMapper.inserts(repaymentRecords);
		Map<String, BigDecimal> updates = Maps.newHashMap();
		for (CimProductRepaymentRecordPull repaymentRecord : repaymentRecords) {
			BigDecimal amt = updates.get(repaymentRecord.getInvestId());
			if(amt==null){
				amt = new BigDecimal(0);
				updates.put(repaymentRecord.getInvestId(), amt);
			}
			updates.put(repaymentRecord.getInvestId(), amt.add(repaymentRecord.getRepaymentAmount()));
		}
		cimProductRepaymentRecordPullMapper.updateInvestRecordStockAmt(updates);
		return count;
	}

	@Override
	public List<CimProductRepaymentRecordPull> getRepaymentRecord() {
		return cimProductRepaymentRecordPullMapper.getRepaymentRecord();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int updateRepaymentRecordInStatus(Integer[] preStatus,Integer afterStatus, String msg) {
		return cimProductRepaymentRecordPullMapper.updateRepaymentRecordInStatus(preStatus, afterStatus, msg);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int updatePushRepaymentRecordStatus(List<ResultVO> results,Integer status) {
		return cimProductRepaymentRecordPullMapper.updatePushRepaymentRecordStatus(results, status);
	}
	
	

}
