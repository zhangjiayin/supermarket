package com.linkwee.test.api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.linkwee.test.TestSupport;
import com.linkwee.web.model.CimProduct;
import com.linkwee.web.model.cim.CimProductInvestRecord;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.web.service.CimProductInvestRecordService;
import com.linkwee.web.service.CimProductService;
import com.linkwee.web.service.InvestRecordAware;

public class ProductFeeControllerTest  extends TestSupport{

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductFeeControllerTest.class);
	
	@Resource
	private CimProductInvestRecordService investRecordService;
	@Resource
	private CimProductInvestRecordService cimProductInvestRecordService;
	@Resource
	private CimProductService cimProductService;
	
	@Resource(name="cimOrgFeeGatherService")
	private InvestRecordAware cimOrgFeeGatherService;
	
	@Resource(name="feeCalcService")
	private InvestRecordAware feeCalcService;
	
	private List<InvestRecordAware> investRecordAwares;
	
	@PostConstruct
	private void init(){
		investRecordAwares = Lists.newArrayList(cimOrgFeeGatherService,feeCalcService);
	}
	
	/**
	 * 募集产品且募集成功补录佣金数据
	 * @throws Exception
	 */
	@Test
	public void testCalculateProductFee() throws Exception {
		
		start();
		
		//投资记录id
		String investId = "74473a73dc0a4d4695c58f1197272810";//"988258815bc741ceb772a24a9db9e4b1"
		
		//查询对应的投资记录
		CimProductInvestRecord  cimProductInvestRecord  = new CimProductInvestRecord();
		cimProductInvestRecord.setInvestId(investId);
		cimProductInvestRecord = cimProductInvestRecordService.selectOne(cimProductInvestRecord);
		
		//查询投资记录对应的产品
		CimProduct cimProduct =  new CimProduct();
		cimProduct.setProductId(cimProductInvestRecord.getProductId());
		cimProduct = cimProductService.selectOne(cimProduct);
		
		List<InvestRecordWrapper> investRecordWrappers = investRecordService.getInvestRecordByProduct(cimProduct);
		//按计费模式计算佣金
		for (InvestRecordWrapper investRecordWrapper : investRecordWrappers) {
			
			if(investRecordWrapper.getBizId().equals(investId)){			
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
		}
		
		end();
		log();
		TimeUnit.SECONDS.sleep(30);
	}
}
