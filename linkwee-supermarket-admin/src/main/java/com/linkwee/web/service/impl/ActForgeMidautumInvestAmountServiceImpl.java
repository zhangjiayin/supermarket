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
import com.linkwee.web.model.ActForgeMidautumInvestAmount;
import com.linkwee.web.dao.ActForgeMidautumInvestAmountMapper;
import com.linkwee.web.service.ActForgeMidautumInvestAmountService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.NewsService.Error;
import com.linkwee.web.service.impl.ActForgeMidautumInvestAmountServiceImpl;


 /**
 * 
 * @描述：ActForgeMidautumInvestAmountService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年09月20日 17:25:13
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actForgeMidautumInvestAmountService")
public class ActForgeMidautumInvestAmountServiceImpl extends GenericServiceImpl<ActForgeMidautumInvestAmount, Long> implements ActForgeMidautumInvestAmountService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActForgeMidautumInvestAmountServiceImpl.class);
	
	@Resource
	private ActForgeMidautumInvestAmountMapper actForgeMidautumInvestAmountMapper;
	
	@Resource
	private SysConfigService systemConfigService; 
	
	@Override
    public GenericDao<ActForgeMidautumInvestAmount, Long> getDao() {
        return actForgeMidautumInvestAmountMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActForgeMidautumInvestAmount -- 排序和模糊查询 ");
		Page<ActForgeMidautumInvestAmount> page = new Page<ActForgeMidautumInvestAmount>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActForgeMidautumInvestAmount> list = this.actForgeMidautumInvestAmountMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
    
    @Override
	public DataTableReturn findForgeinvestamountList(ActForgeMidautumInvestAmount actForgeMidautumInvestAmount, DataTable dataTable) {
		 Page<ActForgeMidautumInvestAmount> page = new Page<ActForgeMidautumInvestAmount>(dataTable.getStart() / dataTable.getLength() + 1,dataTable.getLength());
		 List<ActForgeMidautumInvestAmount> responseList = actForgeMidautumInvestAmountMapper.findForgeinvestamountList(actForgeMidautumInvestAmount,page);
		 for(ActForgeMidautumInvestAmount response : responseList){
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
			actForgeMidautumInvestAmountMapper.deleteByPrimaryKey((long)parseInt);
			return new SuccessCode();
		} catch (Exception e) {
			LOGGER.error("actForgeMidautumInvestAmountMapper.deleteByPrimaryKey invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}
	}

	@Override
	public ReturnCode updateActForgeInvestAmount(ActForgeMidautumInvestAmount convert) {
		try {
			actForgeMidautumInvestAmountMapper.updateByPrimaryKeySelective(convert);
			return new SuccessCode();
		} catch (Exception e) {
			LOGGER.error("actForgeMidautumInvestAmountMapper.updateByPrimaryKeySelective invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}
	}

	@Override
	public ReturnCode saveActForgeInvestAmount(ActForgeMidautumInvestAmount convert) {
		try {
			actForgeMidautumInvestAmountMapper.insertSelective(convert);
			return new SuccessCode();
		} catch (Exception e) {
			LOGGER.error("actForgeMidautumInvestAmountMapper.insertSelective invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}
	}

}
