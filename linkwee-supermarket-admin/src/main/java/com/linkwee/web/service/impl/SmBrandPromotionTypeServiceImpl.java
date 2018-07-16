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

import com.linkwee.web.model.crm.SmBrandPromotionType;
import com.linkwee.web.dao.SmBrandPromotionTypeMapper;
import com.linkwee.web.service.SmBrandPromotionTypeService;
import com.linkwee.web.service.impl.SmBrandPromotionTypeServiceImpl;


 /**
 * 
 * @描述：SmBrandPromotionTypeService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年06月28日 16:44:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("smBrandPromotionTypeService")
public class SmBrandPromotionTypeServiceImpl extends GenericServiceImpl<SmBrandPromotionType, Long> implements SmBrandPromotionTypeService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmBrandPromotionTypeServiceImpl.class);
	
	@Resource
	private SmBrandPromotionTypeMapper smBrandPromotionTypeMapper;
	
	@Override
    public GenericDao<SmBrandPromotionType, Long> getDao() {
        return smBrandPromotionTypeMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmBrandPromotionType -- 排序和模糊查询 ");
		Page<SmBrandPromotionType> page = new Page<SmBrandPromotionType>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmBrandPromotionType> list = this.smBrandPromotionTypeMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

}
