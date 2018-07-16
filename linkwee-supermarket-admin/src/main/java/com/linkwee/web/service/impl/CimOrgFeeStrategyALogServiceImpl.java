package com.linkwee.web.service.impl;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CimOrgFeeStrategyALogMapper;
import com.linkwee.web.model.CimOrgFeeStrategyA;
import com.linkwee.web.model.CimOrgFeeStrategyALog;
import com.linkwee.web.service.CimOrgFeeStrategyALogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
*
* @描述：CimOrgFeeStrategyALogService 服务实现类
*
* @创建人： Hxb
*
* @创建时间：2018年06月08日 18:56:39
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
@Service("cimOrgFeeStrategyALogService")
public class CimOrgFeeStrategyALogServiceImpl extends GenericServiceImpl<CimOrgFeeStrategyALog, Long> implements CimOrgFeeStrategyALogService{

   private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgFeeStrategyALogServiceImpl.class);

   @Resource
   private CimOrgFeeStrategyALogMapper cimOrgFeeStrategyALogMapper;

   @Override
   public GenericDao<CimOrgFeeStrategyALog, Long> getDao() {
       return cimOrgFeeStrategyALogMapper;
   }

   @Override
   public DataTableReturn selectByDatatables(DataTable dt) {
       DataTableReturn tableReturn = new DataTableReturn();
       tableReturn.setDraw(dt.getDraw()+1);
       LOGGER.debug(" -- CimOrgFeeStrategyALog -- 排序和模糊查询 ");
       Page<CimOrgFeeStrategyALog> page = new Page<CimOrgFeeStrategyALog>(dt.getStart()/dt.getLength()+1,dt.getLength());
       List<CimOrgFeeStrategyALog> list = this.cimOrgFeeStrategyALogMapper.selectBySearchInfo(dt,page);
       tableReturn.setData(list);
       tableReturn.setRecordsFiltered(page.getTotalCount());
       tableReturn.setRecordsTotal(page.getTotalCount());
       return tableReturn;
   }

    @Override
    public int batchInsert(List<CimOrgFeeStrategyA> strategyASList) {
       return cimOrgFeeStrategyALogMapper.batchInsert(strategyASList);
    }

}
