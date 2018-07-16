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
import com.linkwee.core.util.BeanUtil;
import com.linkwee.web.model.SmGrowthHandbook;
import com.linkwee.web.model.SmGrowthHandbookCfplevelRelation;
import com.linkwee.web.model.SmGrowthHandbookClassify;
import com.linkwee.web.dao.SmGrowthHandbookCfplevelRelationMapper;
import com.linkwee.web.dao.SmGrowthHandbookMapper;
import com.linkwee.web.request.HandbookRequest;
import com.linkwee.web.request.NewsRequest;
import com.linkwee.web.response.SmGrowthHandbookResponse;
import com.linkwee.web.service.SmGrowthHandbookCfplevelRelationService;
import com.linkwee.web.service.SmGrowthHandbookService;
import com.linkwee.web.service.NewsService.Error;
import com.linkwee.web.service.impl.SmGrowthHandbookServiceImpl;


 /**
 * 
 * @描述：SmGrowthHandbookService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年07月26日 09:37:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("smGrowthHandbookService")
public class SmGrowthHandbookServiceImpl extends GenericServiceImpl<SmGrowthHandbook, Long> implements SmGrowthHandbookService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmGrowthHandbookServiceImpl.class);
	
	@Resource
	private SmGrowthHandbookMapper smGrowthHandbookMapper;
	
	@Resource
	private SmGrowthHandbookCfplevelRelationMapper smGrowthHandbookCfplevelRelationMapper;
	
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
	public DataTableReturn findHandbookList(HandbookRequest handbookRequest,DataTable dataTable) {
		Page<SmGrowthHandbookResponse> page = new Page<SmGrowthHandbookResponse>(dataTable.getStart() / dataTable.getLength() + 1,dataTable.getLength());
		List<SmGrowthHandbookResponse> newsRequestList = smGrowthHandbookMapper.findHandbookList(handbookRequest,page);
		for(SmGrowthHandbookResponse growthHandbook : newsRequestList){
			SmGrowthHandbookCfplevelRelation temp =  new SmGrowthHandbookCfplevelRelation();
			temp.setGrowthHandbookId(growthHandbook.getId());
			List<SmGrowthHandbookCfplevelRelation> cfplevelRelations = smGrowthHandbookCfplevelRelationMapper.selectByCondition(temp);
			StringBuilder cfpLevelBuilder = new StringBuilder();
			for(int i = 0; i < cfplevelRelations.size(); i++){
				if(i == cfplevelRelations.size() - 1){
					cfpLevelBuilder.append(cfplevelRelations.get(i).getCfpLevelCode());
				}else{
					cfpLevelBuilder.append(cfplevelRelations.get(i).getCfpLevelCode());
					cfpLevelBuilder.append(",");
				}
			}
			growthHandbook.setCfpLevel(cfpLevelBuilder.toString());
		}
		DataTableReturn dataTableReturn =new DataTableReturn();
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		dataTableReturn.setData(newsRequestList);
		return dataTableReturn;
	}

	@Override
	public ReturnCode updateHandbook(HandbookRequest convertHandbook) {
		try {
			SmGrowthHandbook request = new SmGrowthHandbook();
			BeanUtils.copyProperties(convertHandbook, request);
			update(request);
			//更新成长手册对应的职级关系
			smGrowthHandbookCfplevelRelationMapper.deleteByGrowthHandbookId(convertHandbook.getId());
			String[] cfpLevelList = convertHandbook.getCfpLevel().split(",");
			for(int i = 0; i < cfpLevelList.length; i++){
				SmGrowthHandbookCfplevelRelation model = new SmGrowthHandbookCfplevelRelation();
				model.setGrowthHandbookId(convertHandbook.getId());
				model.setCfpLevelCode(cfpLevelList[i]);
				smGrowthHandbookCfplevelRelationMapper.insertSelective(model);
			}
			
			return new SuccessCode();
		} catch (Exception e) {
			LOGGER.error("updateHandbook invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}
	}

	@Override
	public ReturnCode saveHandbook(HandbookRequest convertHandbook) {
		try {
			SmGrowthHandbook request = new SmGrowthHandbook();
			BeanUtils.copyProperties(convertHandbook, request);
			smGrowthHandbookMapper.insertSelective(request);
			
			String[] cfpLevelList = convertHandbook.getCfpLevel().split(",");
			for(int i = 0; i < cfpLevelList.length; i++){
				SmGrowthHandbookCfplevelRelation model = new SmGrowthHandbookCfplevelRelation();
				model.setGrowthHandbookId(request.getId());
				model.setCfpLevelCode(cfpLevelList[i]);
				smGrowthHandbookCfplevelRelationMapper.insertSelective(model);
			}
			
			return new SuccessCode();
		} catch (Exception e) {
			LOGGER.error("saveHandbook invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}
	}

	@Override
	public ReturnCode updateStatus(String id, String status) {
		try {
			SmGrowthHandbook model = new SmGrowthHandbook();
			model.setId(Integer.valueOf(id));
			model.setStatus(Integer.valueOf(status));
			smGrowthHandbookMapper.updateByPrimaryKeySelective(model);
			return new SuccessCode();
		} catch (Exception e) {
			LOGGER.error("updateStatus invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}
	}

	@Override
	public void updateHandbookTypeName(SmGrowthHandbookClassify classify) {
		smGrowthHandbookMapper.updateHandbookTypeName(classify);
	}

	@Override
	public void updateHandbookStatusByType(int parseInt) {
		smGrowthHandbookMapper.updateHandbookStatusByType(parseInt);	
	}

}
