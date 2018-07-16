package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CimProductInfoCateMapper;
import com.linkwee.web.model.CimProductInfoCate;
import com.linkwee.web.service.CimProductInfoCateService;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： liqi
 * 
 * @创建时间：2016年07月14日 18:23:34
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimProductInfoCateService")
public class CimProductInfoCateServiceImpl extends GenericServiceImpl<CimProductInfoCate, Long> implements CimProductInfoCateService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimProductInfoCateServiceImpl.class);
	
	@Resource
	private CimProductInfoCateMapper cimProductInfoCateMapper;
	
	@Override
    public GenericDao<CimProductInfoCate, Long> getDao() {
        return cimProductInfoCateMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimProductInfoCate -- 排序和模糊查询 ");
		Page<CimProductInfoCate> page = new Page<CimProductInfoCate>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimProductInfoCate> list = this.cimProductInfoCateMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public void addProductCate(String productId, Integer cateId,Integer sort) {
    	CimProductInfoCate cimProductInfoCate = new CimProductInfoCate();
    	cimProductInfoCate.setProductId(productId);
    	cimProductInfoCate.setCateId(cateId);
    	cimProductInfoCate = cimProductInfoCateMapper.selectOneByCondition(cimProductInfoCate);
		if(null == cimProductInfoCate){
	    	CimProductInfoCate cimProductInfoCateNew = new CimProductInfoCate();
	    	cimProductInfoCateNew.setProductId(productId);
	    	cimProductInfoCateNew.setCateId(cateId);
	    	cimProductInfoCateNew.setSort(sort);
	    	cimProductInfoCateNew.setDescription("添加产品分类");
	    	cimProductInfoCateNew.setUpdateTime(new Date());
	    	cimProductInfoCateMapper.insertSelective(cimProductInfoCateNew);
		} else if(null != cimProductInfoCate && sort != cimProductInfoCate.getSort()){
			cimProductInfoCate.setSort(sort);
			cimProductInfoCate.setDescription("更新产品分类");
			cimProductInfoCate.setUpdateTime(new Date());
			cimProductInfoCateMapper.updateByPrimaryKeySelective(cimProductInfoCate);
		} 
	}

	@Override
	public void deleProductCate(String productId, Integer cateId) {
		//如果cateId为null  则删除所有产品分类
		if(null == cateId){
			cimProductInfoCateMapper.deleteAllByProductId(productId);
		} else {		
			CimProductInfoCate cimProductInfoCate = new CimProductInfoCate();
			cimProductInfoCate.setProductId(productId);
			cimProductInfoCate.setCateId(cateId);
			cimProductInfoCate = cimProductInfoCateMapper.selectOneByCondition(cimProductInfoCate);
			if(null != cimProductInfoCate){
				cimProductInfoCateMapper.deleteByPrimaryKey((long)cimProductInfoCate.getId());
			}
		}
	}

	@Override
	public List<CimProductInfoCate> selectCateListByCondition(CimProductInfoCate cimProductInfoCate) {
		// TODO Auto-generated method stub
		return cimProductInfoCateMapper.selectCateListByCondition(cimProductInfoCate);
	}

	@Override
	public void insertBatch(List<CimProductInfoCate> newCimProductInfoCateList) {
		// TODO Auto-generated method stub
		cimProductInfoCateMapper.insertBatch(newCimProductInfoCateList);
	}

}
