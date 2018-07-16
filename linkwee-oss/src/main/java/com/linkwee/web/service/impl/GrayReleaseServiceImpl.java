package com.linkwee.web.service.impl;

import java.util.List;
import java.lang.Long;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.GrayReleaseMapper;
import com.linkwee.web.model.GrayRelease;
import com.linkwee.web.service.GrayReleaseService;
import com.linkwee.web.service.impl.GrayReleaseServiceImpl;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年06月27日 15:52:14
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("grayReleaseService")
public class GrayReleaseServiceImpl extends GenericServiceImpl<GrayRelease, Long> implements GrayReleaseService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GrayReleaseServiceImpl.class);
	
	@Resource
	private GrayReleaseMapper grayReleaseMapper;
	
	@Override
    public GenericDao<GrayRelease, Long> getDao() {
        return grayReleaseMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- GrayRelease -- 排序和模糊查询 ");
		Page<GrayRelease> page = new Page<GrayRelease>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<GrayRelease> list = this.grayReleaseMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public GrayRelease selectByMobile(String mobile) {
		return grayReleaseMapper.selectByMobile(mobile);
	}

}
