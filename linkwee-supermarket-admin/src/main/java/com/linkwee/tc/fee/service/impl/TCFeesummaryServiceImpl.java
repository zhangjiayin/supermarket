package com.linkwee.tc.fee.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.tc.fee.model.TCFeeSummary;
import com.linkwee.tc.fee.service.TCFeesummaryService;
import com.linkwee.web.dao.TCFeesummaryMapper;


 /**
 * 
 * @描述：CimFeesummaryService 服务实现类
 * 
 * @创建人： ch
 * 
 * @创建时间：2017年04月19日 10:44:58
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("TCFeesummaryService")
public class TCFeesummaryServiceImpl extends GenericServiceImpl<TCFeeSummary, Long> implements TCFeesummaryService{
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(TCFeesummaryServiceImpl.class);
	
	@Resource
	private TCFeesummaryMapper feesummaryMapper;
	
	@Override
    public GenericDao<TCFeeSummary, Long> getDao() {
        return feesummaryMapper;
    }

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int updateFeesummaryTypeBybizId(String bizId, String operator) {
		return feesummaryMapper.updateFeesummaryTypeBybizId(bizId, operator);
	}
    


}
