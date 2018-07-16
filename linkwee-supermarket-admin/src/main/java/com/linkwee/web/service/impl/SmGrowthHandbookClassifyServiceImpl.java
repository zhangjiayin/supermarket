package com.linkwee.web.service.impl;

import java.util.List;
import java.lang.Long;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.base.SuccessCode;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.SmGrowthHandbook;
import com.linkwee.web.model.SmGrowthHandbookCfplevelRelation;
import com.linkwee.web.model.SmGrowthHandbookClassify;
import com.linkwee.web.dao.SmGrowthHandbookClassifyMapper;
import com.linkwee.web.response.SmGrowthHandbookResponse;
import com.linkwee.web.service.SmGrowthHandbookClassifyService;
import com.linkwee.web.service.NewsService.Error;
import com.linkwee.web.service.SmGrowthHandbookService;
import com.linkwee.web.service.impl.SmGrowthHandbookClassifyServiceImpl;


 /**
 * 
 * @描述：SmGrowthHandbookClassifyService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月26日 09:37:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("smGrowthHandbookClassifyService")
public class SmGrowthHandbookClassifyServiceImpl extends GenericServiceImpl<SmGrowthHandbookClassify, Long> implements SmGrowthHandbookClassifyService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmGrowthHandbookClassifyServiceImpl.class);
	
	@Resource
	private SmGrowthHandbookClassifyMapper smGrowthHandbookClassifyMapper;
	
	@Resource
	private SmGrowthHandbookService smGrowthHandbookService;
	
	@Override
    public GenericDao<SmGrowthHandbookClassify, Long> getDao() {
        return smGrowthHandbookClassifyMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmGrowthHandbookClassify -- 排序和模糊查询 ");
		Page<SmGrowthHandbookClassify> page = new Page<SmGrowthHandbookClassify>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmGrowthHandbookClassify> list = this.smGrowthHandbookClassifyMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn findhandbookClassifyList(SmGrowthHandbookClassify request, DataTable dataTable) {
		Page<SmGrowthHandbookClassify> page = new Page<SmGrowthHandbookClassify>(dataTable.getStart() / dataTable.getLength() + 1,dataTable.getLength());
		List<SmGrowthHandbookClassify> resultList = smGrowthHandbookClassifyMapper.findclassifyList(request,page);
		DataTableReturn dataTableReturn =new DataTableReturn();
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		dataTableReturn.setData(resultList);
		return dataTableReturn;
	}

	@Override
	public ReturnCode updateHandbookClassify(SmGrowthHandbookClassify classify) {
		try {
			update(classify);
			smGrowthHandbookService.updateHandbookTypeName(classify);
			return new SuccessCode();
		} catch (Exception e) {
			LOGGER.error("updateHandbookClassify invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}
	}

	@Override
	public ReturnCode saveHandbookClassify(SmGrowthHandbookClassify classify) {
		try {
			insert(classify);
			return new SuccessCode();
		} catch (Exception e) {
			LOGGER.error("saveHandbookClassify invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}
	}

	@Override
	public ReturnCode deleteHandbookClassify(int parseInt) {
		try {
			smGrowthHandbookClassifyMapper.deleteByPrimaryKey(Long.valueOf(parseInt));
			smGrowthHandbookService.updateHandbookStatusByType(parseInt);
			return new SuccessCode();
		} catch (Exception e) {
			LOGGER.error("deleteHandbookClassify invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}
	}

}
