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
import com.linkwee.web.dao.SmBrandPostersMapper;
import com.linkwee.web.model.SmBrandPosters;
import com.linkwee.web.response.acc.SmBrandPosterResponse;
import com.linkwee.web.service.SmBrandPostersService;
import com.linkwee.web.service.SysConfigService;


 /**
 * 
 * @描述：SmBrandPostersService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2018年01月06日 15:13:33
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("smBrandPostersService")
public class SmBrandPostersServiceImpl extends GenericServiceImpl<SmBrandPosters, Long> implements SmBrandPostersService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmBrandPostersServiceImpl.class);
	
	@Resource
	private SmBrandPostersMapper smBrandPostersMapper;
	@Resource
	private SysConfigService systemConfigService; 
	
	@Override
    public GenericDao<SmBrandPosters, Long> getDao() {
        return smBrandPostersMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmBrandPosters -- 排序和模糊查询 ");
		Page<SmBrandPosters> page = new Page<SmBrandPosters>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmBrandPosters> list = this.smBrandPostersMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn findBrandPosters(SmBrandPosters pageRequest, DataTable dataTable) {
		 Page<SmBrandPosters> page = new Page<SmBrandPosters>(dataTable.getStart() / dataTable.getLength() + 1,dataTable.getLength());
		 List<SmBrandPosters> newsRequestList = smBrandPostersMapper.findBrandPosters(pageRequest,page);
		 DataTableReturn dataTableReturn =new DataTableReturn();
		 dataTableReturn.setRecordsFiltered(page.getTotalCount());
		 dataTableReturn.setRecordsTotal(page.getTotalCount());
		 dataTableReturn.setData(newsRequestList);
		 return dataTableReturn;
	}

	@Override
	public DataTableReturn selectByDatatables(SmBrandPosters sm, DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmClassroom -- 排序和模糊查询 ");
		Page<SmBrandPosters> page = new Page<SmBrandPosters>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmBrandPosters> list = this.smBrandPostersMapper.selectBySearchInfo(sm.getTypeValue(),dt,page);
		for(SmBrandPosters smb:list){
			smb.setImage(systemConfigService.getImageUrl(smb.getImage()));
			smb.setSmallImage(systemConfigService.getImageUrl(smb.getSmallImage()));
		}
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public void overheadSmBrandPosters(String typeValue) {
		smBrandPostersMapper.overheadSmBrandPosters(typeValue);
	}

	@Override
	public List<SmBrandPosterResponse> selectBrandPosterList() {
		return smBrandPostersMapper.selectBrandPosterList();
	}

}
