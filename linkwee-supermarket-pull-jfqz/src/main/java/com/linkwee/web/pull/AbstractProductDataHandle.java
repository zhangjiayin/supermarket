package com.linkwee.web.pull;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.linkwee.core.util.DateUtils;
import com.linkwee.web.model.CimProductAddPull;
import com.linkwee.web.model.CimProductUpdatePull;
import com.linkwee.web.response.ProductDataPullReturn;
import com.linkwee.web.service.CimProductAddPullService;
import com.linkwee.web.service.CimProductUpdatePullService;



public abstract class AbstractProductDataHandle implements ProductDataPull{

	@Resource
	private CimProductAddPullService cimProductAddPullService;
	
	@Resource
	private CimProductUpdatePullService cimProductUpdatePullService;
	
	/**
	 *  拉取产品 当前在售
	 * @param orgNumber  机构编码   为null时查询所有机构
	 * @param startTime  开始时间   为null时为当前时间减1天
	 * @param endTime    结束时间  为null时为当前时间
	 * @return
	 */
	public abstract List<ProductDataPullReturn> getProductCurrentSale(String orgNumber,String startTime,String endTime);
	
	/**
	 * 根据第三方产品id 拉取产品 无论产品当前是否在售
	 * @param thirdProductId
	 * @return
	 */
	public abstract CimProductAddPull getProductById(String orgNumber,String thirdProductId);
	
	@Override
	public void pullProductCurrentSale(String orgNumber,String startTime,String endTime){
		handleProductData(getProductCurrentSale(orgNumber,startTime,endTime));
	}
	
	@Override
	public CimProductAddPull pullProductById(String orgNumber,String thirdProductId){
		return getProductById(orgNumber,thirdProductId);
	}

	/**
	 * 拉取到产品后  后续处理  数据保存到数据库表
	 * @param orgEnum
	 * @param cimProductAddPullList
	 */
	private void handleProductData(List<ProductDataPullReturn> productDataPullReturnList) {
		
		if(CollectionUtils.isNotEmpty(productDataPullReturnList)){
			
			String dateNow = DateUtils.getNow();
			
			for(ProductDataPullReturn productDataPullReturn : productDataPullReturnList){
				
				if(productDataPullReturn.getSysThirdkeyConfigPull() != null){
					
					String orgNumber = productDataPullReturn.getSysThirdkeyConfigPull().getOrgNumber();
					List<CimProductAddPull> cimProductAddPullList = productDataPullReturn.getCimProductAddPullList();
					
					List<CimProductAddPull> cimProductAddPullListNew = new ArrayList<CimProductAddPull>();
					
					/**
					 * 查询产品表中还是在售状态的产品列表B
					 * 将第三方返回的在售产品列表A和B进行比较
					 * 若该产品在A和B中都存在  则将该产品记录插入产品更新表
					 * 若该产品在B存在，在A不存在，则将B中该产品更新成2-售罄,产品更新表插入该产品更新成售罄记录
					 * 若该产品是非募集的，则直接插入，若是募集期，则调用第三方接口判断该产品的募集结束时间
					 * 该产品在B不存在，在A存在。该产品是新增产品，添加到产品添加表
					 */
					//1+3
					CimProductAddPull cimProductAddPullNew = new CimProductAddPull();
					cimProductAddPullNew.setStatus(1);//1-在售|2-售罄|3-募集失败
					cimProductAddPullNew.setOrgNumber(orgNumber);
					cimProductAddPullListNew = cimProductAddPullService.selectListByCondition(cimProductAddPullNew);
					
					/**
					 * 待添加的产品和待更新的产品
					 * 若该产品在A和B中都存在  则将该产品记录插入产品更新表
					 * 该产品在B不存在，在A存在。该产品是新增产品，添加到产品添加表
					 */
					for(CimProductAddPull cimProductAddPullf:cimProductAddPullList){
						
						cimProductAddPullf.setOrgNumber(orgNumber);//设置产品对应的机构
						
						boolean ifExist = false;
						CimProductAddPull cimProductAddPull1 = new CimProductAddPull();
						for(CimProductAddPull cimProductAddPullNewf:cimProductAddPullListNew){
							if(cimProductAddPullf.getThirdProductId().equals(cimProductAddPullNewf.getThirdProductId())){
								ifExist = true;
								cimProductAddPull1 = cimProductAddPullNewf;
								break;
							}
						}
						
						/**
						 * 待更新的产品    
						 * 若存在该产品为1-在售状态  则将该数据插入产品更新表
						 * 若存在该产品为2-售罄 3-募集失败   则将该数据插入产品更新表  并更新产品添加表产品状态为2-售罄 3-募集失败
						 */
						if(ifExist) {
							
							//产品更新表
							CimProductUpdatePull cimProductUpdatePullforInsert = new CimProductUpdatePull();
							BeanUtils.copyProperties(cimProductAddPullf, cimProductUpdatePullforInsert);
							cimProductUpdatePullforInsert.setUpdateStatus(1);
							cimProductUpdatePullforInsert.setCreator(orgNumber);
							cimProductUpdatePullforInsert.setCreateTime(dateNow);
							cimProductUpdatePullService.insert(cimProductUpdatePullforInsert);
							
							//更新产品新增表
							if(cimProductAddPullf.getStatus() == 2 || cimProductAddPullf.getStatus() == 3){
								cimProductAddPull1.setStatus(cimProductAddPullf.getStatus());
								cimProductAddPull1.setUpdater(orgNumber);
								cimProductAddPull1.setUpdateTime(DateUtils.getNow());
								cimProductAddPull1.setBuyedTotalMoney(cimProductAddPullf.getBuyedTotalMoney());
								cimProductAddPull1.setBuyedTotalPeople(cimProductAddPullf.getBuyedTotalPeople());
								cimProductAddPull1.setSaleEndTime(cimProductAddPullf.getSaleEndTime());
								cimProductAddPull1.setRemark("已售罄的产品或募集失败-更新产品新增表为售罄");
								cimProductAddPullService.update(cimProductAddPull1);
							}
							/**
							 * 待添加的产品
							 * 判断该产品是否已经存在（售罄，募集失败），若是则丢弃，否则插入产品增加表
							 */
						} else {
							CimProductAddPull cimProductAddPullN = new CimProductAddPull();
							cimProductAddPullN.setThirdProductId(cimProductAddPullf.getThirdProductId());;
							cimProductAddPullN.setOrgNumber(orgNumber);
							cimProductAddPullN = cimProductAddPullService.selectOne(cimProductAddPullN);
							
							/**
							 * 只有在售状态     产品执行添加状态为1-待执行添加
							 */
							if(cimProductAddPullN == null){
								if(cimProductAddPullf.getStatus() == 1){		
									cimProductAddPullf.setUpdateStatus(1);
								} else {
									cimProductAddPullf.setUpdateStatus(0);
								}
								cimProductAddPullf.setCreator(orgNumber);
								cimProductAddPullf.setCreateTime(dateNow);
								cimProductAddPullf.setRemark("添加的产品");
								cimProductAddPullService.insert(cimProductAddPullf);
							}
						}
					}
					/**
					 * 已售罄的产品处理
					 * 若该产品在B存在，在A不存在，则将B中该产品更新成2-售罄,产品更新表插入该产品更新成售罄记录,同时更新产品添加表为售罄
					 * 若该产品是非募集的，则直接插入，若是募集期，则调用第三方接口判断该产品的募集结束时间
					 */
					for(CimProductAddPull cimProductAddPullNewf:cimProductAddPullListNew){
						boolean isExist = false;
						for(CimProductAddPull cimProductAddPullf:cimProductAddPullList){
							if(cimProductAddPullf.getThirdProductId().equals(cimProductAddPullNewf.getThirdProductId())){
								isExist =true;
								break;
							}
						}
						/**
						 * "已售罄"的产品处理
						 * 拉取系统在售产品不在获取产品列表里面   单独查询id进行更新
						 */
						if(!isExist){
							cimProductAddPullNewf.setStatus(2);//产品状态(1-在售|2-售罄|3-募集失败)
							cimProductAddPullNewf.setSaleEndTime(cimProductAddPullNewf.getSaleEndTime());	
							cimProductAddPullNewf.setBuyedTotalMoney(cimProductAddPullNewf.getBuyedTotalMoney());
							cimProductAddPullNewf.setBuyedTotalPeople(cimProductAddPullNewf.getBuyedTotalPeople());
							//产品更新表
							CimProductUpdatePull cimProductUpdatePullforInsertf = new CimProductUpdatePull();
							BeanUtils.copyProperties(cimProductAddPullNewf, cimProductUpdatePullforInsertf);
							cimProductUpdatePullforInsertf.setUpdateStatus(1);
							cimProductUpdatePullforInsertf.setCreator(orgNumber);
							cimProductUpdatePullforInsertf.setOrgNumber(orgNumber);
							cimProductUpdatePullforInsertf.setCreateTime(dateNow);
							cimProductUpdatePullforInsertf.setId(null);
							cimProductUpdatePullService.insert(cimProductUpdatePullforInsertf);
							//产品新增表
							cimProductAddPullNewf.setUpdater(orgNumber);
							cimProductAddPullNewf.setUpdateTime(dateNow);
							cimProductAddPullNewf.setRemark("已售罄的产品-更新产品新增表为售罄");
							cimProductAddPullService.update(cimProductAddPullNewf);
						}
					}
				}	
			}
		}
	}
	
	
	
}
