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

import com.linkwee.web.model.CimOrgMemberinfoA;
import com.linkwee.web.dao.CimOrgMemberinfoAMapper;
import com.linkwee.web.service.CimOrgMemberinfoAService;
import com.linkwee.web.service.impl.CimOrgMemberinfoAServiceImpl;


/**
*
* @描述：CimOrgMemberinfoAService 服务实现类
*
* @创建人： Hxb
*
* @创建时间：2018年06月07日 11:47:15
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
@Service("cimOrgMemberinfoAService")
public class CimOrgMemberinfoAServiceImpl extends GenericServiceImpl<CimOrgMemberinfoA, Long> implements CimOrgMemberinfoAService{

   private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgMemberinfoAServiceImpl.class);

   @Resource
   private CimOrgMemberinfoAMapper cimOrgMemberinfoAMapper;

   @Override
   public GenericDao<CimOrgMemberinfoA, Long> getDao() {
       return cimOrgMemberinfoAMapper;
   }

   @Override
   public DataTableReturn selectByDatatables(DataTable dt) {
       DataTableReturn tableReturn = new DataTableReturn();
       tableReturn.setDraw(dt.getDraw()+1);
       LOGGER.debug(" -- CimOrgMemberinfoA -- 排序和模糊查询 ");
       Page<CimOrgMemberinfoA> page = new Page<CimOrgMemberinfoA>(dt.getStart()/dt.getLength()+1,dt.getLength());
       List<CimOrgMemberinfoA> list = this.cimOrgMemberinfoAMapper.selectBySearchInfo(dt,page);
       tableReturn.setData(list);
       tableReturn.setRecordsFiltered(page.getTotalCount());
       tableReturn.setRecordsTotal(page.getTotalCount());
       return tableReturn;
   }

    @Override
    public void updateBatchTeam(List<CimOrgMemberinfoA> teams) {
        cimOrgMemberinfoAMapper.updateBatchTeam(teams);
    }

    @Override
    public void insertBatch(List<CimOrgMemberinfoA> newTeams) {
        cimOrgMemberinfoAMapper.insertBatch(newTeams);
    }

}
