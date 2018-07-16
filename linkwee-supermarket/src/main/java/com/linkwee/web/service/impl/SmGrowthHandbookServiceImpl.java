package com.linkwee.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.SmGrowthHandbookMapper;
import com.linkwee.web.model.SmGrowthHandbook;
import com.linkwee.web.service.SmGrowthHandbookService;


 /**
 * 
 * @描述：SmGrowthHandbookService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月25日 14:29:27
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("smGrowthHandbookService")
public class SmGrowthHandbookServiceImpl extends GenericServiceImpl<SmGrowthHandbook, Long> implements SmGrowthHandbookService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmGrowthHandbookServiceImpl.class);
	
	@Resource
	private SmGrowthHandbookMapper smGrowthHandbookMapper;
	
	@Override
    public GenericDao<SmGrowthHandbook, Long> getDao() {
        return smGrowthHandbookMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmGrowthHandbook -- 排序和模糊查询 ");
		Page<SmGrowthHandbook> page = new Page<SmGrowthHandbook>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmGrowthHandbook> list = this.smGrowthHandbookMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public PaginatorResponse<SmGrowthHandbook> classifyList(Page<SmGrowthHandbook> page, Map<String, Object> conditions) {
		PaginatorResponse<SmGrowthHandbook> paginatorResponse = new PaginatorResponse<SmGrowthHandbook>();
		List<SmGrowthHandbook> handbookList = smGrowthHandbookMapper.queryClassifyPageList(conditions,page);
		for(SmGrowthHandbook ghb : handbookList){
			ghb.setContent(null);
		}
		paginatorResponse.setDatas(handbookList);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public List<SmGrowthHandbook> personalCustomizationList(Map<String, Object> conditions) {
		List<SmGrowthHandbook> handbookList = smGrowthHandbookMapper.personalCustomizationList(conditions);
		for(SmGrowthHandbook ghb : handbookList){
			ghb.setContent(null);
		}
		return handbookList;
	}

}
