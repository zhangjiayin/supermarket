package com.linkwee.web.service.impl;

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
import com.linkwee.web.dao.SmNewsMapper;
import com.linkwee.web.model.SmNews;
import com.linkwee.web.service.SmNewsService;


 /**
 * 
 * @描述：SmNewsService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月27日 19:22:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("smNewsService")
public class SmNewsServiceImpl extends GenericServiceImpl<SmNews, Long> implements SmNewsService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmNewsServiceImpl.class);
	
	@Resource
	private SmNewsMapper smNewsMapper;
	
	@Override
    public GenericDao<SmNews, Long> getDao() {
        return smNewsMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmNews -- 排序和模糊查询 ");
		Page<SmNews> page = new Page<SmNews>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmNews> list = this.smNewsMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
    
    /**
     * 根据id查询资讯记录
     */
	@Override
	public SmNews findNewsDtl(String fid) {
		long id = Long.parseLong(fid);
		return smNewsMapper.selectByPrimaryKey(id);
	}


}
