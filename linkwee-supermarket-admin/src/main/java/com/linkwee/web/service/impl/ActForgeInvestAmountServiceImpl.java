package com.linkwee.web.service.impl;

import java.util.List;
import java.lang.Long;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.base.SuccessCode;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActForgeInvestAmount;
import com.linkwee.web.dao.ActForgeInvestAmountMapper;
import com.linkwee.web.request.NewsRequest;
import com.linkwee.web.service.ActForgeInvestAmountService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.NewsService.Error;
import com.linkwee.web.service.impl.ActForgeInvestAmountServiceImpl;


 /**
 * 
 * @描述：ActForgeInvestAmountService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年08月25日 10:10:09
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actForgeInvestAmountService")
public class ActForgeInvestAmountServiceImpl extends GenericServiceImpl<ActForgeInvestAmount, Long> implements ActForgeInvestAmountService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActForgeInvestAmountServiceImpl.class);
	
	@Resource
	private ActForgeInvestAmountMapper actForgeInvestAmountMapper;
	
	@Resource
	private SysConfigService systemConfigService; 
	
	@Override
    public GenericDao<ActForgeInvestAmount, Long> getDao() {
        return actForgeInvestAmountMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActForgeInvestAmount -- 排序和模糊查询 ");
		Page<ActForgeInvestAmount> page = new Page<ActForgeInvestAmount>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActForgeInvestAmount> list = this.actForgeInvestAmountMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn findForgeinvestamountList(ActForgeInvestAmount actForgeInvestAmount, DataTable dataTable) {
		 Page<ActForgeInvestAmount> page = new Page<ActForgeInvestAmount>(dataTable.getStart() / dataTable.getLength() + 1,dataTable.getLength());
		 List<ActForgeInvestAmount> responseList = actForgeInvestAmountMapper.findForgeinvestamountList(actForgeInvestAmount,page);
		 for(ActForgeInvestAmount response : responseList){
			 response.setHeadImage(systemConfigService.getImageUrl(response.getHeadImage()));
		 }
		 DataTableReturn dataTableReturn =new DataTableReturn();
		 dataTableReturn.setRecordsFiltered(page.getTotalCount());
		 dataTableReturn.setRecordsTotal(page.getTotalCount());
		 dataTableReturn.setData(responseList);
		 return dataTableReturn;
	}

	@Override
	public ReturnCode deleteForgeinvestamountList(int parseInt) {
		try {
			actForgeInvestAmountMapper.deleteByPrimaryKey((long)parseInt);
			return new SuccessCode();
		} catch (Exception e) {
			LOGGER.error("actForgeInvestAmountMapper.deleteByPrimaryKey invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}
	}

	@Override
	public ReturnCode updateActForgeInvestAmount(ActForgeInvestAmount convert) {
		try {
			actForgeInvestAmountMapper.updateByPrimaryKeySelective(convert);
			return new SuccessCode();
		} catch (Exception e) {
			LOGGER.error("actForgeInvestAmountMapper.updateByPrimaryKeySelective invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}
	}

	@Override
	public ReturnCode saveActForgeInvestAmount(ActForgeInvestAmount convert) {
		try {
			actForgeInvestAmountMapper.insertSelective(convert);
			return new SuccessCode();
		} catch (Exception e) {
			LOGGER.error("actForgeInvestAmountMapper.insertSelective invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}
	}

}
