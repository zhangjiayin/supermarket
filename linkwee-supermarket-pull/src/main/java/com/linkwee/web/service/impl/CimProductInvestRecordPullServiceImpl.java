package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.linkwee.web.dao.CimProductInvestRecordPullMapper;
import com.linkwee.web.model.CimProductInvestRecordPull;
import com.linkwee.web.push.vo.ResultVO;
import com.linkwee.web.service.CimProductInvestRecordPullService;


 /**
 * 
 * @描述：CimProductInvestRecordPullService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年02月09日 17:21:31
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimProductInvestRecordPullService")
public class CimProductInvestRecordPullServiceImpl implements CimProductInvestRecordPullService{
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(CimProductInvestRecordPullServiceImpl.class);
	
	@Resource
	private CimProductInvestRecordPullMapper cimProductInvestRecordPullMapper;

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int inserts(List<CimProductInvestRecordPull> investRecords) {
		if(CollectionUtils.isEmpty(investRecords))return 0;
		List<String> investRecordIds = cimProductInvestRecordPullMapper.getInvestRecordIds(investRecords);
		if(CollectionUtils.isNotEmpty(investRecordIds)){
			Set<String> investRecordIdsSet = Sets.newHashSet(investRecordIds);
			List<CimProductInvestRecordPull> investRecordTemps = Lists.newArrayListWithCapacity(investRecords.size() - investRecordIds.size());
			for (CimProductInvestRecordPull investRecord : investRecords) {
				if(investRecordIdsSet.contains(investRecord.getInvestId()))continue;
				investRecordTemps.add(investRecord);
			}
			if(CollectionUtils.isEmpty(investRecordTemps)) return 0;
			investRecords = investRecordTemps;
		}
		return cimProductInvestRecordPullMapper.inserts(investRecords);
	}

	@Override
	public List<CimProductInvestRecordPull> getInvestRecord() {
		return cimProductInvestRecordPullMapper.getInvestRecord();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int updateInvestRecordInStatus(Integer[] preStatus,Integer afterStatus,String msg) {
		return cimProductInvestRecordPullMapper.updateInvestRecordInStatus(preStatus, afterStatus, msg);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int updatePushInvestRecordStatus(List<ResultVO> results,Integer status) {
		return cimProductInvestRecordPullMapper.updatePushInvestRecordStatus(results, status);
	}

	@Override
	public BigDecimal getStockInvestAmt(String investId) {
		return cimProductInvestRecordPullMapper.getStockInvestAmt(investId);
	}

    
 

}
