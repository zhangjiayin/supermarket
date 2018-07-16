package com.linkwee.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.api.request.cim.AllAssessRequest;
import com.linkwee.api.request.cim.OrgMoneyDataRequest;
import com.linkwee.api.response.cim.AllAssessResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CimOrgRiskManageSynthesizeMapper;
import com.linkwee.web.model.CimOrgRiskManageSynthesize;
import com.linkwee.web.model.cim.OrgMoneyDataDetail;
import com.linkwee.web.service.CimOrgRiskFresGradeService;
import com.linkwee.web.service.CimOrgRiskManageSynthesizeService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.DtOrgMoneyInOutDayService;
import com.linkwee.web.service.DtOrgMoneyInOutMonthService;
import com.linkwee.web.service.DtOrgMoneyInOutWeekService;
import com.linkwee.web.service.DtOrgNewMoneyDayService;
import com.linkwee.web.service.DtOrgNewMoneyMonthService;
import com.linkwee.web.service.DtOrgNewMoneyWeekService;
import com.linkwee.web.service.DtOrgSynthesizeDataService;


 /**
 * 
 * @描述：CimOrgRiskManageSynthesizeService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年06月07日 17:06:03
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrgRiskManageSynthesizeService")
public class CimOrgRiskManageSynthesizeServiceImpl extends GenericServiceImpl<CimOrgRiskManageSynthesize, Long> implements CimOrgRiskManageSynthesizeService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgRiskManageSynthesizeServiceImpl.class);
	
	@Resource
	private CimOrgRiskManageSynthesizeMapper cimOrgRiskManageSynthesizeMapper;
	@Resource
	private CimOrginfoService cimOrginfoService;
	@Resource
	private CimOrgRiskFresGradeService cimOrgRiskFresGradeService;
	@Resource
	private DtOrgSynthesizeDataService dtOrgSynthesizeDataService;
	@Resource
	private DtOrgNewMoneyDayService  dtOrgNewMoneyDayService;
	@Resource
	private DtOrgNewMoneyWeekService  dtOrgNewMoneyWeekService;
	@Resource
	private DtOrgNewMoneyMonthService  dtOrgNewMoneyMonthService;
	@Resource
	private DtOrgMoneyInOutDayService dtOrgMoneyInOutDayService;
	@Resource
	private DtOrgMoneyInOutWeekService  dtOrgMoneyInOutWeekService;
	@Resource
	private DtOrgMoneyInOutMonthService  dtOrgMoneyInOutMonthService;
	
	@Override
    public GenericDao<CimOrgRiskManageSynthesize, Long> getDao() {
        return cimOrgRiskManageSynthesizeMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrgRiskManageSynthesize -- 排序和模糊查询 ");
		Page<CimOrgRiskManageSynthesize> page = new Page<CimOrgRiskManageSynthesize>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrgRiskManageSynthesize> list = this.cimOrgRiskManageSynthesizeMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public AllAssessResponse getAllAssess(AllAssessRequest allAssessRequest) {
		AllAssessResponse allAssessResponse = new AllAssessResponse();
		//机构基本信息
		allAssessResponse.setCimOrginfo(cimOrginfoService.selectCimOrginfoByOrgNumber(allAssessRequest.getOrgNo()));
		//机构风控管理综合
		allAssessResponse.setCimOrgRiskManageSynthesize(selectOneByOrgNumber(allAssessRequest.getOrgNo()));
		//机构风控FRES评分列表
		allAssessResponse.setCimOrgRiskFresGradeExtendsList(cimOrgRiskFresGradeService.queryRiskFresGradeExtendsList(allAssessRequest.getOrgNo()));
		//机构综合数据
		allAssessResponse.setDtOrgSynthesizeData(dtOrgSynthesizeDataService.getNewestOrgData(allAssessRequest.getOrgNo()));
		return allAssessResponse;
	}

	@Override
	public CimOrgRiskManageSynthesize selectOneByOrgNumber(String orgNumber) {
		// TODO Auto-generated method stub
		CimOrgRiskManageSynthesize cimOrgRiskManageSynthesize = new CimOrgRiskManageSynthesize();
		cimOrgRiskManageSynthesize.setOrgNumber(orgNumber);
		return cimOrgRiskManageSynthesizeMapper.selectOneByCondition(cimOrgRiskManageSynthesize);
	}

	@Override
	public List<OrgMoneyDataDetail> orgdata(OrgMoneyDataRequest orgMoneyDataRequest) {
		
		List<OrgMoneyDataDetail>  orgMoneyDataDetailList = new ArrayList<OrgMoneyDataDetail>();
		
		if(orgMoneyDataRequest.getQueryType() == 1){//1=成交量 
			if(orgMoneyDataRequest.getTimeType() == 1){// 1=日 
				orgMoneyDataDetailList = dtOrgNewMoneyDayService.queryOrgdata(orgMoneyDataRequest);
			} else if(orgMoneyDataRequest.getTimeType() == 2){// 2=周  
				orgMoneyDataDetailList = dtOrgNewMoneyWeekService.queryOrgdata(orgMoneyDataRequest);
			} else if(orgMoneyDataRequest.getTimeType() == 3){// 3=月
				orgMoneyDataDetailList = dtOrgNewMoneyMonthService.queryOrgdata(orgMoneyDataRequest);
			}
		} else if(orgMoneyDataRequest.getQueryType() == 2){//2=资金净流入
			if(orgMoneyDataRequest.getTimeType() == 1){// 1=日 
				orgMoneyDataDetailList = dtOrgMoneyInOutDayService.queryOrgdata(orgMoneyDataRequest);
			} else if(orgMoneyDataRequest.getTimeType() == 2){// 2=周  
				orgMoneyDataDetailList = dtOrgMoneyInOutWeekService.queryOrgdata(orgMoneyDataRequest);
			} else if(orgMoneyDataRequest.getTimeType() == 3){// 3=月
				orgMoneyDataDetailList = dtOrgMoneyInOutMonthService.queryOrgdata(orgMoneyDataRequest);
			}
		}
		return orgMoneyDataDetailList;
	}

}
