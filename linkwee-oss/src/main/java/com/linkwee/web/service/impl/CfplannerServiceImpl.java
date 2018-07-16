package com.linkwee.web.service.impl;

import com.linkwee.core.datatable.ColumnInfo;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.datatable.OrderInfo;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.CfpRelevantProfitDao;
import com.linkwee.web.dao.CfplannerMapper;
import com.linkwee.web.model.CfpCustomerInvertingModel;
import com.linkwee.web.model.Cfplanner;
import com.linkwee.web.response.CfpCustomerProfitListResp;
import com.linkwee.web.response.CfpTeamListResp;
import com.linkwee.web.response.LcsDetailResp;
import com.linkwee.web.response.LcsSalesAndEarningDetailResp;
import com.linkwee.web.service.CfplannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色Service实现类
 *
 * @author Mignet
 * @since 2014年6月10日 下午4:16:33
 */
@Service
public class CfplannerServiceImpl extends GenericServiceImpl<Cfplanner, String> implements CfplannerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CfplannerServiceImpl.class);
	
    @Resource
    private CfplannerMapper cfplannerMapper;

	@Resource
	private CfpRelevantProfitDao cfpRelevantProfitDao;

    @Override
    public GenericDao<Cfplanner, String> getDao() {
        return cfplannerMapper;
    }


	@Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" 排序和模糊查询 ");
		for(OrderInfo o:dt.getOrder()){
			o.setName(ColumnInfo.exChange(dt.getColumns().get(o.getColumn()).getData()));
		}
		List<Cfplanner> list = this.cfplannerMapper.selectBySearchInfo(dt);
		Integer count = this.cfplannerMapper.countBySearchInfo(dt);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(count);
		tableReturn.setRecordsTotal(count);

		return tableReturn;
	}

	/**
	 * 理财师销售与收益列表查询
	 *
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	@Override
	public DataTableReturn findCfpSaleList(String mobile,DataTable dataTable) throws Exception {
		Page<LcsSalesAndEarningDetailResp> page = new Page<LcsSalesAndEarningDetailResp>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<LcsSalesAndEarningDetailResp> lcsSalesAndEarningDetailRespList = cfpRelevantProfitDao.findCfpSaleList(mobile,page);
		DataTableReturn dataTableReturn = new DataTableReturn();
		dataTableReturn.setData(lcsSalesAndEarningDetailRespList);
		dataTableReturn.setDraw(0);
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		return dataTableReturn;
	}

	/**
	 * 查询理财师当前正在投资的客户
	 *
	 * @param mobile
	 * @param dataTable
	 * @return
	 * @throws Exception
	 */
	@Override
	public DataTableReturn findCurrentInvertorList(String mobile,String searchText, DataTable dataTable) throws Exception {
		Page<CfpCustomerInvertingModel> cfpCustomerInvertingModelPage = new Page<CfpCustomerInvertingModel>(dataTable.getStart()/dataTable.getLength()+1,dataTable.getLength());
		List<CfpCustomerInvertingModel> modelList = cfpRelevantProfitDao.findCurrentInvertorList(mobile,searchText,cfpCustomerInvertingModelPage);
		DataTableReturn dataTableReturn = new DataTableReturn();
		dataTableReturn.setRecordsTotal(cfpCustomerInvertingModelPage.getTotalCount());
		dataTableReturn.setRecordsFiltered(cfpCustomerInvertingModelPage.getTotalCount());
		dataTableReturn.setDraw(0);
		dataTableReturn.setData(modelList);
		return dataTableReturn;
	}

	/**
	 * 查询理财师当前正在投资的客户总的金额
	 *
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	@Override
	public Double findCurrentInvertorAmount(String mobile,String searchText) throws Exception {
		if(StringUtils.isBlank(mobile)){
			return  0.00;
		}
		return cfpRelevantProfitDao.findCurrentInvertorAmount(mobile,searchText);
	}

	/**
	 * 查询理财师下一级与二级理财师的信息与推荐收益情况
	 *
	 * @param lcsDetailResp
	 * @param dataTable
	 * @return
	 */
	@Override
	public DataTableReturn queryCfpTeamList(LcsDetailResp lcsDetailResp, DataTable dataTable) {
		DataTableReturn dataTableReturn  = new DataTableReturn();
		if(null == lcsDetailResp || StringUtils.isBlank(lcsDetailResp.getNumber())){
			dataTableReturn.setData(new ArrayList<Object>());
			dataTableReturn.setDraw(0);
			dataTableReturn.setRecordsFiltered(0);
			dataTableReturn.setRecordsTotal(0);
			return  dataTableReturn;
		}
		Page<CfpTeamListResp> page  = new Page<CfpTeamListResp>(dataTable.getStart() / dataTable.getLength() +1,dataTable.getLength());
		List<CfpTeamListResp> cfpTeamListRespList = cfpRelevantProfitDao.queryCfpTeamList(lcsDetailResp,page);
		dataTableReturn.setData(cfpTeamListRespList);
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		return dataTableReturn;
	}

	/**
	 * 查询理财师一级客户投资情况不包括非直属投资客户
	 *
	 * @param detailResp
	 * @param dataTable
	 * @return
	 */
	@Override
	public DataTableReturn queryCfpCustomerProfitList(LcsDetailResp detailResp, DataTable dataTable) {
		DataTableReturn dataTableReturn = new DataTableReturn();
		if(null == detailResp || StringUtils.isBlank(detailResp.getNumber())){
			dataTableReturn.setData(new ArrayList<Object>());
			dataTableReturn.setDraw(0);
			dataTableReturn.setRecordsFiltered(0);
			dataTableReturn.setRecordsTotal(0);
			return  dataTableReturn;
		}
		Page<CfpCustomerProfitListResp> page = new Page<CfpCustomerProfitListResp>(dataTable.getStart() / dataTable.getLength()+1,dataTable.getLength());
		List<CfpCustomerProfitListResp> cfpCustomerProfitListRespList = cfpRelevantProfitDao.queryCfpCustomerProfitList(detailResp,page);
        dataTableReturn.setData(cfpCustomerProfitListRespList);
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		return dataTableReturn;
	}
}
