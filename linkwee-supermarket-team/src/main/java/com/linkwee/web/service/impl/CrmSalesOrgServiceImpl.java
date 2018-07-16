package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.CrmSalesOrgMapper;
import com.linkwee.web.model.CrmSalesOrg;
import com.linkwee.web.request.CustomerStatisticalRequest;
import com.linkwee.web.request.DataStatisticsRequest;
import com.linkwee.web.request.LcsDetailRequest;
import com.linkwee.web.request.LcsFouseRequest;
import com.linkwee.web.request.LcsSaleRequest;
import com.linkwee.web.request.LcsSearchRequest;
import com.linkwee.web.request.LcsStatisticalRequest;
import com.linkwee.web.request.PlatformSaleRequest;
import com.linkwee.web.request.TeamSaleRequest;
import com.linkwee.web.request.TeamStatisticalRequest;
import com.linkwee.web.response.CustomerStatisticalListResponse;
import com.linkwee.web.response.LcsDetailResponse;
import com.linkwee.web.response.LcsSaleInfoResponse;
import com.linkwee.web.response.LcsSaleListResponse;
import com.linkwee.web.response.LcsSearchInfoResponse;
import com.linkwee.web.response.LcsStatisticalListResponse;
import com.linkwee.web.response.LcsStatisticalResponse;
import com.linkwee.web.response.LevelSaleResponse;
import com.linkwee.web.response.PlatformSaleResponse;
import com.linkwee.web.response.TeamSaleRecordResponse;
import com.linkwee.web.response.TeamStatisticalListResponse;
import com.linkwee.web.response.TeamStatisticalResponse;
import com.linkwee.web.response.TotalStatisticResponse;
import com.linkwee.web.response.UnRepaymentTotalResponse;
import com.linkwee.web.service.CrmSalesOrgService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.util.AppResponseUtil;


 /**
 * 
 * @描述：CrmSalesOrgService 服务实现类
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年11月07日 11:41:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmSalesOrgService")
public class CrmSalesOrgServiceImpl extends GenericServiceImpl<CrmSalesOrg, Long> implements CrmSalesOrgService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmSalesOrgServiceImpl.class);
	
	@Resource
	private CrmSalesOrgMapper salesOrgMapper;
	@Resource
	private SysConfigService sysConfigService;
	
	@Override
    public GenericDao<CrmSalesOrg, Long> getDao() {
        return salesOrgMapper;
    }

	@Override
	public LcsStatisticalResponse getLcsStatistical(String salesOrgId) {
		return salesOrgMapper.getLcsStatistical(salesOrgId);
	}
	
	@Override
	public DataTableReturn getLcsStatisticalList(String salesOrgId,LcsStatisticalRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(req.getDraw()+1);
		Page<LcsStatisticalListResponse> page = new Page<LcsStatisticalListResponse>(req.getStart()/req.getLength()+1,req.getLength());
		List<LcsStatisticalListResponse> list = salesOrgMapper.getLcsStatisticalList(salesOrgId, req.getNameormobile(), req.getCity(), page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn getCustomerStatisticalList(String salesOrgId,CustomerStatisticalRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(req.getDraw()+1);
		Page<CustomerStatisticalListResponse> page = new Page<CustomerStatisticalListResponse>(req.getStart()/req.getLength()+1,req.getLength());
		List<CustomerStatisticalListResponse> list = salesOrgMapper.getCustomerStatisticalList(salesOrgId, req.getMobile(), req.getNameOrMobile(), page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public TeamStatisticalResponse getTeamStatistical(String salesOrgId,TeamStatisticalRequest req) {
		if( StringUtils.isBlank(req.getStartDate()) && StringUtils.isBlank(req.getEndDate())){
			DateTime  now = 	 DateTime.now();
			String month = now.toString("yyyy-MM");
			req.setStartDate(month + "-01");
			req.setEndDate(now.toString("yyyy-MM-dd"));
		}
		List<TeamStatisticalResponse> list = salesOrgMapper.getTeamStatistical(salesOrgId, req);
		TeamStatisticalResponse response =  new TeamStatisticalResponse();
		//多条数据，对结果进行整合
		int investCount = 0;
		int lcsCount = 0;
		double totalAmt =  0;
		double totalYearpurAmt=  0;
		double totalFeeAmt=  0;
		for(TeamStatisticalResponse d:list){
			investCount += d.getInvestCount();
			lcsCount += d.getLcsCount();
			totalAmt += d.getBBTotalAmt();
			totalYearpurAmt += d.getBBTotalYearpurAmt();
			totalFeeAmt += d.getBBTotalFeeAmt();
		}
		if(!list.isEmpty()){
			response.setInvestCount(investCount);
			response.setLcsCount(lcsCount);
			response.setTotalAmt(new BigDecimal(totalAmt));
			response.setTotalYearpurAmt(new BigDecimal(totalYearpurAmt));
			response.setTotalFeeAmt(new BigDecimal(totalFeeAmt));
		}
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd"); 
	 	LocalDate start=new LocalDate(DateTime.parse(req.getStartDate(), format));    
        LocalDate end=new LocalDate(DateTime.parse(req.getEndDate(), format));  
        int days = Days.daysBetween(start, end).getDays();
        days = days + 1;
        if(days< 0) return response;
        //计算存量
		Map<String, BigDecimal> nh = salesOrgMapper.getStockYearpurAmt(salesOrgId, req);
		//时间段总年化
		BigDecimal znh = nh.get("znh");
		//固定期产品(不需要过了锁定期后每日佣金计算的产品) 总年化
		BigDecimal gdnh = nh.get("gdnh");
		////固定期产品(不需要过了锁定期后每日佣金计算的产品) 总投资额
		BigDecimal gdinvest = nh.get("gdinvest");
		//存量年化 
		response.setStockYearpurAmt(znh);
		//总年化 - 固定年化 = 每日计算所产总年化
		BigDecimal jsnh = znh.subtract(gdnh);
		if(jsnh.compareTo(BigDecimal.ZERO)==0){
			response.setStockAmt(gdinvest);
		}else{
			//存量总投资额 = 每日计算所产总投资额 +  固定期产品总投资额
			//每日计算所产总投资额 = 每日计算所产总年化 * 360 / 时间段天数
			BigDecimal stockAmt =(jsnh.multiply(new BigDecimal(360)).divide(new BigDecimal(days), 4, BigDecimal.ROUND_DOWN) .add(gdinvest));
			//stockAmt = stockAmt .add(gdinvest);
			response.setStockAmt(stockAmt);
		}
		return response;
	}

	@Override
	public DataTableReturn getTeamStatisticalList(String salesOrgId,TeamStatisticalRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(req.getDraw()+1);
		Page<TeamStatisticalListResponse> page = new Page<TeamStatisticalListResponse>(req.getStart()/req.getLength()+1,req.getLength());
		List<TeamStatisticalListResponse> list = salesOrgMapper.getTeamStatisticalList(salesOrgId, req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<Map<String, String>> getPlatfroms() {
		return salesOrgMapper.getPlatfroms();
	}
	
	@Override
	public BigDecimal getInvestmentStatisticsTotal(String salesOrgId,String platfrom,String startTime, String endTime) {
		return salesOrgMapper.getInvestmentStatisticsTotal(salesOrgId,platfrom, startTime, endTime);
	}

	@Override
	public List<Map<String, BigDecimal>> getInvestmentStatisticsList(String salesOrgId,
			String platfrom, String startTime, String endTime) {
		return salesOrgMapper.getInvestmentStatisticsList(salesOrgId,platfrom, startTime, endTime);
	}

	@Override
	public TotalStatisticResponse queryTotalStatisticData(DataStatisticsRequest request) {
		return salesOrgMapper.queryTotalStatisticData(request);
	}

	@Override
	public DataTableReturn getPlatformSale(PlatformSaleRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(req.getDraw()+1);
		Page<PlatformSaleResponse> page = new Page<PlatformSaleResponse>(req.getStart()/req.getLength()+1,req.getLength());
		List<PlatformSaleResponse> list = salesOrgMapper.getPlatformSale(req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn getLevelSale(PlatformSaleRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(req.getDraw()+1);
		Page<LevelSaleResponse> page = new Page<LevelSaleResponse>(req.getStart()/req.getLength()+1,req.getLength());
		List<LevelSaleResponse> list = salesOrgMapper.getLevelSale(req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn lcsTeamSaleRecordList(TeamSaleRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(req.getDraw()+1);
		Page<TeamSaleRecordResponse> page = new Page<TeamSaleRecordResponse>(req.getStart()/req.getLength()+1,req.getLength());
		List<TeamSaleRecordResponse> list = salesOrgMapper.lcsTeamSaleRecordList(req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public int getTeamMaxDepth(String salesOrgId) {
		return salesOrgMapper.getTeamMaxDepth(salesOrgId);
	}

	@Override
	public LcsDetailResponse lcsDetail(LcsDetailRequest request) {
		return salesOrgMapper.lcsDetail(request);
	}

	@Override
	public LcsSaleInfoResponse lcsSaleInfo(LcsSaleRequest request) {
		CrmSalesOrg salesOrg = new CrmSalesOrg();
		String userId = salesOrgMapper.queryLcsUserId(request.getLcsMobile());
		request.setLcsUserId(userId);
		String teamSql = generateTeamSql(request);
		request.setTeamSql(teamSql);
		return salesOrgMapper.lcsSaleInfo(request);
	}
	
	@Override
	public String generateTeamSql(LcsSaleRequest request) {
		//合伙人团队层级
		int n = Integer.parseInt(sysConfigService.getValuesByKey("partner_team_depth"));

        StringBuilder sql = new StringBuilder();
        sql.append("select p1.mobile,p1.user_id ");
        for( int i =1;i<n; i++){
        	sql.append(",p"+i+".parent_id as parent_id"+i);
        }
        sql.append(", case ");
        for( int i =1;i<n; i++){
        	sql.append(" when p"+i+".parent_id ='"+ request.getLcsUserId()+"' THEN "+i);
        }
        sql.append(" END AS depth ");
        sql.append(" from   tcrm_cfplanner p1");
        for( int i =1+1;i<n; i++){
        	sql.append(" left join   tcrm_cfplanner p"+i+" on p"+i+".user_id = p"+(i-1)+".parent_id ");
        }
        sql.append(" where '"  + request.getLcsUserId() +"' in (p1.parent_id ");
        for( int i =1+1;i<n; i++){
        	sql.append(" ,p"+i+".parent_id");
        }
        sql.append(" ) ");
        if(request.getStartTime() != null && !request.getStartTime().equals("")){
        	sql.append("AND DATE_FORMAT(p1.create_time ,'%Y-%m-%d') >= DATE_FORMAT('"+DateUtils.format(request.getStartTime(), DateUtils.FORMAT_LONG)+"','%Y-%m-%d')");
        }
        if(request.getEndTime() != null && !request.getEndTime().equals("")){
        	sql.append("AND DATE_FORMAT(p1.create_time ,'%Y-%m-%d') <= DATE_FORMAT('"+DateUtils.format(request.getEndTime(), DateUtils.FORMAT_LONG)+"','%Y-%m-%d')");
        }
        sql.append(" order  by "+(n+1));
        for( int i =n;i>2; i--){
        	sql.append(" ,"+i+"");
        }
        return sql.toString();
	}

	@Override
	public DataTableReturn lcsSaleList(LcsSaleRequest request) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(request.getDraw()+1);
		Page<LcsSaleListResponse> page = new Page<LcsSaleListResponse>(request.getStart()/request.getLength()+1,request.getLength());
		List<LcsSaleListResponse> list = salesOrgMapper.lcsSaleList(request, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn lcsSearchInfoList(LcsSearchRequest request) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(request.getDraw()+1);
		Page<LcsSearchInfoResponse> page = new Page<LcsSearchInfoResponse>(request.getStart()/request.getLength()+1,request.getLength());
		List<LcsSearchInfoResponse> list = salesOrgMapper.lcsSearchInfoList(request, page);
		LcsSaleRequest saleRequest = new LcsSaleRequest();
		for(LcsSearchInfoResponse temp : list){
			saleRequest.setLcsUserId(temp.getUserId());
			String teamSql = generateTeamSql(saleRequest);
			saleRequest.setTeamSql(teamSql);
			int teamNumber = salesOrgMapper.lcsTeamNumber(saleRequest);
			temp.setTeamNumber(teamNumber);
		}
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public BaseResponse lcsSearchRecord(LcsSearchRequest request) {
		if(request.getMobileOrName() != null && !request.getMobileOrName().equals("")){
			salesOrgMapper.lcsSearchRecord(request);
		}		
		return AppResponseUtil.getSuccessResponse();
	}

	@Override
	public BaseResponse lcsSearchHistory(LcsSearchRequest request) {
		List<String> resultList = salesOrgMapper.searchHistory(request.getSalesOrgId());
		return AppResponseUtil.getSuccessResponse(resultList);
	}

	@Override
	public BaseResponse lcsFouseRecord(LcsFouseRequest request) {
		String userId = salesOrgMapper.queryLcsUserId(request.getCfpMobile());
		request.setCfplanner(userId);
		if(request.getStatus() == 1){
			int hasFoused = salesOrgMapper.hasFoused(request);
			if(hasFoused > 0){
				salesOrgMapper.updateLcsFouseStatus(request);
			}else{
				salesOrgMapper.insertFouseStatus(request);
			}
		}else{
			salesOrgMapper.updateLcsFouseStatus(request);
		}
		return AppResponseUtil.getSuccessResponse();
	}

	@Override
	public DataTableReturn lcsFouseList(LcsSearchRequest request) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(request.getDraw()+1);
		Page<LcsSearchInfoResponse> page = new Page<LcsSearchInfoResponse>(request.getStart()/request.getLength()+1,request.getLength());
		List<LcsSearchInfoResponse> list = salesOrgMapper.lcsFouseList(request, page);
		LcsSaleRequest saleRequest = new LcsSaleRequest();
		for(LcsSearchInfoResponse temp : list){
			saleRequest.setLcsUserId(temp.getUserId());
			String teamSql = generateTeamSql(saleRequest);
			saleRequest.setTeamSql(teamSql);
			int teamNumber = salesOrgMapper.lcsTeamNumber(saleRequest);
			temp.setTeamNumber(teamNumber);
		}
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn lcsUnSaleList(LcsSearchRequest request) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(request.getDraw()+1);
		Page<LcsSearchInfoResponse> page = new Page<LcsSearchInfoResponse>(request.getStart()/request.getLength()+1,request.getLength());
		List<LcsSearchInfoResponse> list = salesOrgMapper.lcsUnSaleList(request, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public DataTableReturn lcsFlowDeadLineUnRepaymentList(TeamSaleRequest req) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(req.getDraw()+1);
		Page<TeamSaleRecordResponse> page = new Page<TeamSaleRecordResponse>(req.getStart()/req.getLength()+1,req.getLength());
		List<TeamSaleRecordResponse> list = salesOrgMapper.lcsFlowDeadLineUnRepaymentList(req, page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public UnRepaymentTotalResponse unRepaymentListTotal(TeamSaleRequest req) {
		UnRepaymentTotalResponse response = salesOrgMapper.unRepaymentListTotal(req);
		return response;
	}

}
