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
import com.linkwee.web.model.CimOrgRiskFresGrade;
import com.linkwee.web.model.cim.CimOrgRiskFresGradeExtends;
import com.linkwee.web.dao.CimOrgRiskFresGradeMapper;
import com.linkwee.web.service.CimOrgRiskFresGradeService;
import com.linkwee.web.service.impl.CimOrgRiskFresGradeServiceImpl;


 /**
 * 
 * @描述：CimOrgRiskFresGradeService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月07日 17:06:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrgRiskFresGradeService")
public class CimOrgRiskFresGradeServiceImpl extends GenericServiceImpl<CimOrgRiskFresGrade, Long> implements CimOrgRiskFresGradeService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgRiskFresGradeServiceImpl.class);
	
	@Resource
	private CimOrgRiskFresGradeMapper cimOrgRiskFresGradeMapper;
	
	@Override
    public GenericDao<CimOrgRiskFresGrade, Long> getDao() {
        return cimOrgRiskFresGradeMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrgRiskFresGrade -- 排序和模糊查询 ");
		Page<CimOrgRiskFresGrade> page = new Page<CimOrgRiskFresGrade>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrgRiskFresGrade> list = this.cimOrgRiskFresGradeMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CimOrgRiskFresGradeExtends> queryRiskFresGradeExtendsList(String orgNumber) {
		// TODO Auto-generated method stub
		return cimOrgRiskFresGradeMapper.queryRiskFresGradeExtendsList(orgNumber);
	}

}
