package com.linkwee.product.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.product.dao.ProductManageDao;
import com.linkwee.product.service.ProductManageService;
import com.linkwee.web.model.product.ProductDetailResp;
import com.linkwee.web.model.product.ProductManageRep;
import com.linkwee.web.model.product.ProductManageResp;
import com.linkwee.web.model.product.ProductSaleDtlResp;
import com.linkwee.web.model.product.ProductSaleManageResp;

@Service("productManageService")
public class ProductManageServiceImpl implements ProductManageService {
	private static Logger logger = Logger.getLogger(ProductManageServiceImpl.class);

	@Autowired
	private ProductManageDao productManageDao;

	@Override
	public PaginatorSevResp<ProductManageResp> queryProductPage(ProductManageRep productManageRep) {
		logger.info(productManageRep.toString());
		PaginatorSevResp<ProductManageResp> pageResponse= new PaginatorSevResp<ProductManageResp>();
		pageResponse.setPageSize(productManageRep.getRows());
		pageResponse.setPageIndex(productManageRep.getPage());
		pageResponse.setTotalCount(productManageDao.productListPageCount(productManageRep));
		pageResponse.setDatas(productManageDao.productListPage(productManageRep));
		return pageResponse;
	}


	@Override
	public PaginatorSevResp<ProductSaleManageResp> queryProductSalePage(ProductManageRep request) {
		logger.info(request.toString());
		PaginatorSevResp<ProductSaleManageResp> pageResponse= new PaginatorSevResp<ProductSaleManageResp>();
		pageResponse.setPageSize(request.getRows());
		pageResponse.setPageIndex(request.getPage());
		pageResponse.setTotalCount(productManageDao.productSalePageCount(request));
		pageResponse.setDatas(productManageDao.productSaleListPage(request));
		return pageResponse;
	}


	@Override
	public PaginatorSevResp<ProductSaleDtlResp> queryProductSaleDtlPage(ProductManageRep request) {
		logger.info(request.toString());
		PaginatorSevResp<ProductSaleDtlResp> pageResponse= new PaginatorSevResp<ProductSaleDtlResp>();
		pageResponse.setPageSize(request.getRows());
		pageResponse.setPageIndex(request.getPage());
		pageResponse.setTotalCount(productManageDao.proSaleDtlCount(request));
		pageResponse.setDatas(productManageDao.proSaleDtlPageList(request));
		return pageResponse;
	}


	@Override
	public ProductDetailResp findProductDetail(String productId) {
		return productManageDao.findProductDetail(productId);
	}





/*
	*//**
	 * 还款
	 *//*
	@Override
	public ServiceResponse<Boolean> repayment() {
		//查出要还款的投资数据
		logger.debug("定期产品回款开始");
		List<String> investIdList = productDao.queryWaitRepayInvestIdList();
		if(investIdList == null || investIdList.size() <= 0) {
			logger.debug("定期产品回款：没有到期的投资记录");
			return new ServiceResponse<Boolean>(true);
		}
		//进行还款
		for(String investId : investIdList) {
			logger.debug("定期产品回款：investId = " + investId);
			FixedInvestRecord investRecord = fixedInvestRecordDao.getByInvestId(investId);
			if(investRecord.getStatus() == 3) {
				//该笔投资已还款，跳过
				continue;
			}
			
			//生成回款记录
			FixedRepaymentRecord repayReq = new FixedRepaymentRecord();
			repayReq.setInvestRecordId(investId);
			FixedRepaymentRecord repaymentRecord = fixedRepaymentRecordDao.query(repayReq);
			if(repaymentRecord == null || repaymentRecord.getRepaymentId() == null) {
				repaymentRecord = new FixedRepaymentRecord();
				repaymentRecord.setBizTime(new Date());
				repaymentRecord.setRepaymentId(getUUID());
				repaymentRecord.setAccurateProfit(investRecord.getAccurateProfit());
				repaymentRecord.setEndTime(investRecord.getEndTime());
				repaymentRecord.setInvestAmt(investRecord.getInvestAmt());
				repaymentRecord.setInvestRecordId(investRecord.getInvestId());
				repaymentRecord.setProductId(investRecord.getProductId());
				repaymentRecord.setUserId(investRecord.getUserId());
				repaymentRecord.setStatus(Byte.parseByte(RepaymentRecordStatusEnum.PAYWAIT.getKey() + ""));
				fixedRepaymentRecordDao.add(repaymentRecord);
			}
			
			//写回款日志表
			writeProductRepaymentLog(investId, repaymentRecord.getRepaymentId(), repaymentRecord.getUserId(), 0, "申请回款");
			
			//回款请求参数
			ReturnRecordReq req = new ReturnRecordReq();
			req.setPartnerId(configHelper.getSysConfigValue(Constants.ACOUNT_PARTNERID));
			req.setPartnerTradeNo(repaymentRecord.getInvestRecordId());
			req.setUserId(investRecord.getUserId());
			req.setBisName(investRecord.getProductName() + "回款");
			req.setProductCode(investRecord.getProductId());
			req.setProductName(investRecord.getProductName());
			req.setInvestRecordNo(Long.parseLong(investRecord.getInvestRecordNo()));
			req.setReturnAmount(new Double(investRecord.getInvestAmt() * 10000).longValue());
			req.setProfitAmount(new Double(investRecord.getAccurateProfit() * 10000).longValue());
			req.setRemark("领会理财产品回款");
			req.setCharset("UTF-8");
			req.setSign(SignUtils.addSign(req, req.getCharset(), configHelper.getSysConfigValue(Constants.TRANS_MD5_SIGN_KEY)));
			String bizTime2= DateUtils.format(investRecord.getCreateTime(), DateUtils.FORMAT_SHORT_CN)+"日";
			
			//金服  定期产品到期回款
			pushMsgService.pushMSgRepayment(investRecord.getUserId(),bizTime2,investRecord.getProductName(),investRecord.getInvestAmt());
			//调平台回款接口
			CommonRlt<Map<String, Object>> commonRlt =  p2pIReturnRecordSOAService.userReturnRecord(req);
			if (commonRlt == null) {
				logger.debug("定期产品回款：调平台回款接口失败");
				return new ServiceResponse<Boolean>(Error.REPAY_FAIL);
			} else {
				int returnCode = commonRlt.getReturnCode().intValue();
				String retMsg = commonRlt.getReturnMsg();
				logger.debug("定期产品回款：调平台回款接口返回数据 returnCode=" + returnCode + ", retMsg= " + retMsg );
				//写回款日志
				writeProductRepaymentLog(investId, repaymentRecord.getRepaymentId(), repaymentRecord.getUserId(), returnCode, retMsg);
				
				if (returnCode == 0) {
					//交易中心处理成功，记录平台回款流水号
					Map<String, Object> data = commonRlt.getData();
					String returnRecordNo = data.get("returnRecordNo").toString();
					FixedRepaymentRecord record = new FixedRepaymentRecord();
					record.setReturnRecordNo(returnRecordNo);
					record.setStatus(Byte.parseByte(RepaymentRecordStatusEnum.PAYOFF.getKey() + ""));
					record.setId(repaymentRecord.getId());
					fixedRepaymentRecordDao.update(record);
					
					//修改投资记录状态
					FixedInvestRecord inv = new FixedInvestRecord();
					inv.setInvestId(investId);
					inv.setStatus(Byte.parseByte(InvestRecordStatusEnum.RETURN_SUCCESS.getKey() + ""));
					fixedInvestRecordDao.updateByInvestId(inv);
					String bizTime= DateUtils.format(investRecord.getCreateTime(), DateUtils.FORMAT_SHORT_CN)+"日";
					
					//金服  定期产品到期回款
					pushMsgService.pushMSgRepayment(investRecord.getUserId(),bizTime,investRecord.getProductName(),investRecord.getInvestAmt());
					
				} else {
					//交易中心处理失败
					logger.error("定期产品回款：平台回款处理失败 返回错误信息 code :" + returnCode + ", msg : " + retMsg);
				}
			}
		}
		logger.debug("定期产品回款结束");
		return new ServiceResponse<Boolean>(true);
	}
	*/

	 
}
