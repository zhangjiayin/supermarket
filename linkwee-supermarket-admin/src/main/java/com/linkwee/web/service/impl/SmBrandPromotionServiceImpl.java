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
import com.linkwee.web.dao.SmBrandPromotionMapper;
import com.linkwee.web.model.crm.SmBrandPromotion;
import com.linkwee.web.service.SmBrandPromotionService;
import com.linkwee.web.service.SysConfigService;


 /**
 * 
 * @描述：SmBrandPromotionService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年06月28日 18:47:51
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("smBrandPromotionService")
public class SmBrandPromotionServiceImpl extends GenericServiceImpl<SmBrandPromotion, Long> implements SmBrandPromotionService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmBrandPromotionServiceImpl.class);
	
	@Resource
	private SmBrandPromotionMapper smBrandPromotionMapper;
	
	@Resource
	private SysConfigService systemConfigService; 
	
	@Override
    public GenericDao<SmBrandPromotion, Long> getDao() {
        return smBrandPromotionMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmBrandPromotion -- 排序和模糊查询 ");
		Page<SmBrandPromotion> page = new Page<SmBrandPromotion>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmBrandPromotion> list = this.smBrandPromotionMapper.selectBySearchInfo(null,null,null,dt,page);
		for(SmBrandPromotion sm : list){
			sm.setImage(systemConfigService.getImageUrl(sm.getImage()));
		}
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn selectByDatatables(SmBrandPromotion sm, DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmClassroom -- 排序和模糊查询 ");
		Page<SmBrandPromotion> page = new Page<SmBrandPromotion>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmBrandPromotion> list = this.smBrandPromotionMapper.selectBySearchInfo(sm.getUseType(),sm.getAppType(),sm.getTypeValue(),dt,page);
		for(SmBrandPromotion smb:list){
			smb.setImage(systemConfigService.getImageUrl(smb.getImage()));
			smb.setSmallImage(systemConfigService.getImageUrl(smb.getSmallImage()));
		}
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public void overheadSmBrandPromotion() {
		smBrandPromotionMapper.overheadSmBrandPromotion();
	}
	
	@Override
	public void overheadbpSmBrandPromotion() {
		smBrandPromotionMapper.overheadbpSmBrandPromotion();
	}

}
