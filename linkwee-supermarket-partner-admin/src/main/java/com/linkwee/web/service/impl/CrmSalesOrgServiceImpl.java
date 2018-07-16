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
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.CrmSalesOrgMapper;
import com.linkwee.web.model.CrmSalesOrg;
import com.linkwee.web.request.CustomerStatisticalRequest;
import com.linkwee.web.request.LcsStatisticalRequest;
import com.linkwee.web.request.TeamStatisticalRequest;
import com.linkwee.web.response.CustomerStatisticalListResponse;
import com.linkwee.web.response.LcsStatisticalListResponse;
import com.linkwee.web.response.LcsStatisticalResponse;
import com.linkwee.web.response.TeamStatisticalListResponse;
import com.linkwee.web.response.TeamStatisticalResponse;
import com.linkwee.web.service.CrmSalesOrgService;


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
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(CrmSalesOrgServiceImpl.class);
	
	@Resource
	private CrmSalesOrgMapper salesOrgMapper;
	
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
		TeamStatisticalResponse response = salesOrgMapper.getTeamStatistical(salesOrgId, req);
		if(response==null ) response =  new TeamStatisticalResponse();
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
	
/*	public static void main(String[] args) {
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd"); 
	 	LocalDate start=new LocalDate(DateTime.parse("2016-11-05", format));    
        LocalDate end=new LocalDate(DateTime.parse("2016-11-04", format));  
        Days days = Days.daysBetween(start, end);
        System.out.println(days.getDays());
	}*/

	
    


}
