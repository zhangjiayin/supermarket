package com.linkwee.web.service.impl;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CrmOrgAcctRelAMapper;
import com.linkwee.web.model.CrmOrgAcctRelA;
import com.linkwee.web.service.CrmOrgAcctRelAService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
*
* @描述：CrmOrgAcctRelAService 服务实现类
*
* @创建人： Hxb
*
* @创建时间：2018年06月11日 15:28:30
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
@Service("crmOrgAcctRelAService")
public class CrmOrgAcctRelAServiceImpl extends GenericServiceImpl<CrmOrgAcctRelA, Long> implements CrmOrgAcctRelAService{

   private static final Logger LOGGER = LoggerFactory.getLogger(CrmOrgAcctRelAServiceImpl.class);

   @Resource
   private CrmOrgAcctRelAMapper crmOrgAcctRelAMapper;

   @Override
   public GenericDao<CrmOrgAcctRelA, Long> getDao() {
       return crmOrgAcctRelAMapper;
   }

   @Override
   public DataTableReturn selectByDatatables(DataTable dt) {
       DataTableReturn tableReturn = new DataTableReturn();
       tableReturn.setDraw(dt.getDraw()+1);
       LOGGER.debug(" -- CrmOrgAcctRelA -- 排序和模糊查询 ");
       Page<CrmOrgAcctRelA> page = new Page<CrmOrgAcctRelA>(dt.getStart()/dt.getLength()+1,dt.getLength());
       List<CrmOrgAcctRelA> list = this.crmOrgAcctRelAMapper.selectBySearchInfo(dt,page);
       tableReturn.setData(list);
       tableReturn.setRecordsFiltered(page.getTotalCount());
       tableReturn.setRecordsTotal(page.getTotalCount());
       return tableReturn;
   }

    @Override
    public boolean isBindOrgAcct(String userId, String platFromNumber) {
        if(crmOrgAcctRelAMapper.queryIsBindOrgAcct(userId, platFromNumber) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String queryThirdOrgAccountByUserId(String userId, String orgNo) {
        return crmOrgAcctRelAMapper.queryThirdOrgAccountByUserId(userId,orgNo);
    }

}
