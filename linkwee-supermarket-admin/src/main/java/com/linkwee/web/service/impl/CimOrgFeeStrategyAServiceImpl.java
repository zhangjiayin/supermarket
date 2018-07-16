package com.linkwee.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.lang.Long;
import javax.annotation.Resource;

import com.linkwee.web.model.cim.CimOrgFeeRecord;
import com.linkwee.web.service.CimOrgFeeStrategyALogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;

import com.linkwee.web.model.CimOrgFeeStrategyA;
import com.linkwee.web.dao.CimOrgFeeStrategyAMapper;
import com.linkwee.web.service.CimOrgFeeStrategyAService;
import com.linkwee.web.service.impl.CimOrgFeeStrategyAServiceImpl;


/**
*
* @描述：CimOrgFeeStrategyAService 服务实现类
*
* @创建人： Hxb
*
* @创建时间：2018年06月07日 11:47:15
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
@Service("cimOrgFeeStrategyAService")
public class CimOrgFeeStrategyAServiceImpl extends GenericServiceImpl<CimOrgFeeStrategyA, Long> implements CimOrgFeeStrategyAService{

   private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgFeeStrategyAServiceImpl.class);

   @Resource
   private CimOrgFeeStrategyALogService strategyALogService;

   @Resource
   private CimOrgFeeStrategyAMapper cimOrgFeeStrategyAMapper;

   @Override
   public GenericDao<CimOrgFeeStrategyA, Long> getDao() {
       return cimOrgFeeStrategyAMapper;
   }

   @Override
   public DataTableReturn selectByDatatables(DataTable dt) {
       DataTableReturn tableReturn = new DataTableReturn();
       tableReturn.setDraw(dt.getDraw()+1);
       LOGGER.debug(" -- CimOrgFeeStrategyA -- 排序和模糊查询 ");
       Page<CimOrgFeeStrategyA> page = new Page<CimOrgFeeStrategyA>(dt.getStart()/dt.getLength()+1,dt.getLength());
       List<CimOrgFeeStrategyA> list = this.cimOrgFeeStrategyAMapper.selectBySearchInfo(dt,page);
       tableReturn.setData(list);
       tableReturn.setRecordsFiltered(page.getTotalCount());
       tableReturn.setRecordsTotal(page.getTotalCount());
       return tableReturn;
   }

    @Override
    public List<CimOrgFeeStrategyA> queryOrgFeeStrategy(String orgNumber) {
        CimOrgFeeStrategyA strategyA = new CimOrgFeeStrategyA();
        strategyA.setOrgNumber(orgNumber);
        return selectListByCondition(strategyA);
    }

    @Override
    public void updateBatchFee(List<CimOrgFeeStrategyA> strategyASList,String orgNumber) {
       strategyALogService.batchInsert(strategyASList);
       cimOrgFeeStrategyAMapper.deleteByOrgNumber(orgNumber);
       cimOrgFeeStrategyAMapper.batchInsert(strategyASList);
    }

}
