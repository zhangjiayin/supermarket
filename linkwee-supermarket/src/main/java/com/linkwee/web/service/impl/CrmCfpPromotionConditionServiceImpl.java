package com.linkwee.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CrmCfpPromotionConditionMapper;
import com.linkwee.web.model.CrmCfpPromotionCondition;
import com.linkwee.web.service.CrmCfpPromotionConditionService;


 /**
 * 
 * @描述：CrmCfpPromotionConditionService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月31日 10:34:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmCfpPromotionConditionService")
public class CrmCfpPromotionConditionServiceImpl extends GenericServiceImpl<CrmCfpPromotionCondition, Long> implements CrmCfpPromotionConditionService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmCfpPromotionConditionServiceImpl.class);
	
	@Resource
	private CrmCfpPromotionConditionMapper crmCfpPromotionConditionMapper;
	
	@Override
    public GenericDao<CrmCfpPromotionCondition, Long> getDao() {
        return crmCfpPromotionConditionMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmCfpPromotionCondition -- 排序和模糊查询 ");
		Page<CrmCfpPromotionCondition> page = new Page<CrmCfpPromotionCondition>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmCfpPromotionCondition> list = this.crmCfpPromotionConditionMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public CrmCfpPromotionCondition queryByCode(String code) {
		CrmCfpPromotionCondition con = new CrmCfpPromotionCondition();
		con.setLevelCode(code);
		con = this.selectOne(con);
		return con;
	}

	@Override
	public Map<String, CrmCfpPromotionCondition> queryCrmCfpPromotionCondition() {
		Map<String, CrmCfpPromotionCondition> returnMap = new HashMap<String, CrmCfpPromotionCondition>();
		CrmCfpPromotionCondition crmCfpPromotionCondition = new CrmCfpPromotionCondition();
		List<CrmCfpPromotionCondition> crmCfpPromotionConditionList = crmCfpPromotionConditionMapper.selectByCondition(crmCfpPromotionCondition);
		for (CrmCfpPromotionCondition crmCfpPromotionCondition2 : crmCfpPromotionConditionList) {
			returnMap.put(crmCfpPromotionCondition2.getLevelCode(), crmCfpPromotionCondition2);
		}
		return returnMap;
	}
}
