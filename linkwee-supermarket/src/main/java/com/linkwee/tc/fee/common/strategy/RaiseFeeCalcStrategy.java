package com.linkwee.tc.fee.common.strategy;

import java.rmi.ServerException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.CimProduct;
import com.linkwee.web.model.cim.CimProductInvestRecord;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CimProductInvestRecordService;
import com.linkwee.web.service.CollectAware;
import com.linkwee.web.service.InvestRecordAware;
import com.linkwee.xoss.helper.ThreadpoolService;
/**
 * 募集期 佣金计算策略 募集期内默认不处理，募集成功后同意计算
 * @author ch
 *
 */
@Component
public class RaiseFeeCalcStrategy  implements FeeCalcStrategy,CollectAware{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RaiseFeeCalcStrategy.class);
	
	@Resource
	private CimProductInvestRecordService investRecordService;
	
	@Resource
	private CimOrginfoService orginfoService;
	
	@Resource(name="cimOrgFeeGatherService")
	private InvestRecordAware cimOrgFeeGatherService;
	
	@Resource(name="feeCalcService")
	private InvestRecordAware feeCalcService;
	
	@Autowired
	private List<OrgFeeCalcPatternStrategy> orgFeeCalcPatternStrategies;
	
	private List<InvestRecordAware> investRecordAwares;
	
	@PostConstruct
	private void init(){
		investRecordAwares = Lists.newArrayList(cimOrgFeeGatherService,feeCalcService);
	}
	
	@Override
	public boolean matchCalcStrategy(InvestRecordWrapper investRecordWrapper) {
		return ObjectUtils.equals(investRecordWrapper.getIsFixedDeadline(), 2);
	}
	
	@Override
	public void feeCalc(InvestRecordWrapper investRecordWrapper) throws Exception{
		CimOrginfo orginfo = new CimOrginfo();
		orginfo.setOrgNumber(investRecordWrapper.getProductOrgId());
		orginfo = orginfoService.selectOne(orginfo);
		if(orginfo == null ) throw new ServerException("机构不存在");
		for (OrgFeeCalcPatternStrategy orgFeeCalcPatternStrategy : orgFeeCalcPatternStrategies) {
			if (orgFeeCalcPatternStrategy.matchPattern(orginfo, investRecordWrapper)) {
				orgFeeCalcPatternStrategy.orgFeeCalc(orginfo, investRecordWrapper);
				break;
			}
		}
	}

	@Override
	public void collectProcess(final CimProduct product) {
	
		ThreadpoolService.execute(new Runnable() {
			@Override
			public void run() {
				try{
					LOGGER.info("collectProcess product = {}", product);
					//查询机构是否存在
					CimOrginfo orginfo = new CimOrginfo();
					orginfo.setOrgNumber(product.getOrgNumber());
					orginfo = orginfoService.selectOne(orginfo);
					if(orginfo == null ){
						LOGGER.warn("机构不存在 orgNumber="+product.getOrgNumber());
						return;
					} 
				
					List<InvestRecordWrapper> investRecordWrappers = investRecordService.getInvestRecordByProduct(product);
					if(investRecordWrappers ==null || investRecordWrappers.isEmpty()){
						LOGGER.warn("产品不存在投资记录  ProductId ={}",product.getProductId());
						return;
					}
					
					CimProductInvestRecord productInvestRecord = null;
					try{
						//更新到期日期
						productInvestRecord = new CimProductInvestRecord();
						productInvestRecord.setProductId(product.getProductId());
						Date endDate = DateUtils.addDays(com.linkwee.core.util.DateUtils.parse(com.linkwee.core.util.DateUtils.format(product.getSaleEndTime(), com.linkwee.core.util.DateUtils.FORMAT_SHORT), com.linkwee.core.util.DateUtils.FORMAT_SHORT), product.getDeadLineMinValue()+1);
						productInvestRecord.setEndTime(endDate);
						investRecordService.updateInvestRecordEndTimeByProductId(productInvestRecord);
					}catch(Exception e){
						LOGGER.warn("updateInvestRecordEndTimeByProductId  productInvestRecord = {},exception={}",productInvestRecord,e);
					}
					
					     	
					//按计费模式计算佣金
					for (InvestRecordWrapper investRecordWrapper : investRecordWrappers) {
						
						//不计算实际期限
						//int days = Days.daysBetween(LocalDate.fromDateFields(investRecordWrapper.getInvestTime()), LocalDate.fromDateFields(product.getSaleEndTime())).getDays();
						
						for (InvestRecordAware investRecordAware : investRecordAwares) {
							try{
								investRecordAware.investRecordProcess(investRecordWrapper);
							}catch(Exception e){
								LOGGER.warn("collectProcess exception investRecordAware={},investRecordWrapper={},exception={}", new Object[]{investRecordAware,investRecordWrapper,e});
							}
						}
						
					}
				}catch(Exception e){
					LOGGER.error("collectProcess exception product={}", product,e);
				}
			}
		});
		
	}
}


