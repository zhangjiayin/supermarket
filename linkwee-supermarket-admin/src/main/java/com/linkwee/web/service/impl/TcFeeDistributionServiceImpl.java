package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.linkwee.web.dao.TCFeedetailMapper;
import com.linkwee.web.request.tc.TcFeeDistributionRequest;
import com.linkwee.web.response.tc.FeeTopDetailResponse;
import com.linkwee.web.service.TcFeeDistributionService;

@Service("TcFeeDistributionService")
public class TcFeeDistributionServiceImpl implements TcFeeDistributionService{
	
	@Autowired
	private TCFeedetailMapper feedetailMapper;

	@Override
	public Map<String, Long> getFeeTotalData() {
		return feedetailMapper.getFeeTotalData();
	}

	@Override
	public List<FeeTopDetailResponse> getTop(TcFeeDistributionRequest req) {
	
		String start = StringUtils.join(new Object[]{req.getStart()," 00:00:00"});
		String end = StringUtils.join(new Object[]{req.getEnd()," 23:59:59"});
		return feedetailMapper.getTop(req.getOrgInfo(),start, end);
	}

	@Override
	public List<Map<String, Long>> getFeeDataDetail(TcFeeDistributionRequest req) {
		String start = StringUtils.join(new Object[]{req.getStart()," 00:00:00"});
		String end = StringUtils.join(new Object[]{req.getEnd()," 23:59:59"});
		return feedetailMapper.getFeeDataDetail(req.getType(), req.getOrgInfo(), start, end);
	}

	@Override
	public Map<String, Float> getFeeDistributionRatio(TcFeeDistributionRequest req) {
		String start = StringUtils.join(new Object[]{req.getStart()," 00:00:00"});
		String end = StringUtils.join(new Object[]{req.getEnd()," 23:59:59"});
		Long count = feedetailMapper.getFeeDistributionRatioCount(req.getOrgInfo(), start, end);
		Float total = count.floatValue();
		Map<String, Float> result = Maps.newTreeMap();
		List<Map<String, Object>> datas = feedetailMapper.getFeeDistributionRatio(req.getOrgInfo(), start, end);
		
		for (Map<String, Object> map : datas) {
    		result.put(((BigDecimal)map.get("amt")).setScale(2,BigDecimal.ROUND_DOWN).toString(),new BigDecimal(((Long)map.get("count") )/total * 100f).setScale(2,BigDecimal.ROUND_DOWN).floatValue());
		}
		return result;
	}

	

}
