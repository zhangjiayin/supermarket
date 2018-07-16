package com.linkwee.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CimOrgFeeGatherMapper;
import com.linkwee.web.model.CimOrgFeeGather;
import com.linkwee.web.model.vo.OrgSaleFeeData;
import com.linkwee.web.service.CimOrgFeeGatherService;
import com.linkwee.xoss.util.BigDecimalUtil;


 /**
 * 
 * @描述：CimOrgFeedetailService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月28日 17:06:53
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrgFeeGatherService")
public class CimOrgFeeGatherServiceImpl extends GenericServiceImpl<CimOrgFeeGather, Long> implements CimOrgFeeGatherService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgFeeGatherServiceImpl.class);
	
	@Resource
	private CimOrgFeeGatherMapper cimOrgFeedetailMapper;
	
	@Override
    public GenericDao<CimOrgFeeGather, Long> getDao() {
        return cimOrgFeedetailMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrgFeedetail -- 排序和模糊查询 ");
		Page<CimOrgFeeGather> page = new Page<CimOrgFeeGather>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrgFeeGather> list = this.cimOrgFeedetailMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

    @Override
	 public DataTableReturn queryOrgSaleFee(Map<String,Object> params, DataTable dataTable) throws Exception {
		 Page<OrgSaleFeeData> page = new Page<OrgSaleFeeData>(dataTable.getStart() / dataTable.getLength() + 1,dataTable.getLength());
		 List<OrgSaleFeeData> feeList = cimOrgFeedetailMapper.queryOrgSaleFee(page, params);
		 for(OrgSaleFeeData item :feeList){
			 if(item.getDaySaleAmount()!=null){
				 item.setDaySaleAmount(BigDecimalUtil.divide(item.getDaySaleAmount(), 10000));
				 item.setAvgInvest(BigDecimalUtil.divide(item.getDaySaleAmount(), item.getInvestPersonAmount()));
			 }
			 if(item.getDaySaleForYearAmount()!=null){
				 item.setDaySaleForYearAmount(BigDecimalUtil.divide(item.getDaySaleForYearAmount(), 10000));
			 }
			 if(item.getNewInvestAmount()!=null){
				 item.setNewInvestAmount(BigDecimalUtil.divide(item.getNewInvestAmount(), 10000));
			 }
			 
		 }
		 DataTableReturn dataTableReturn =new DataTableReturn();
		 dataTableReturn.setRecordsFiltered(page.getTotalCount());
		 dataTableReturn.setRecordsTotal(page.getTotalCount());
		 dataTableReturn.setData(feeList);
		 return dataTableReturn;
	 }

}
