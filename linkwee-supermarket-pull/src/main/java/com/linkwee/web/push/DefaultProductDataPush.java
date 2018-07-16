package com.linkwee.web.push;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.model.CimProductAddPull;
import com.linkwee.web.model.CimProductUpdatePull;
import com.linkwee.web.model.SysThirdkeyConfigPull;
import com.linkwee.web.push.vo.ResultVOProduct;
import com.linkwee.web.service.CimProductAddPullService;
import com.linkwee.web.service.CimProductUpdatePullService;
import com.linkwee.web.service.SysThirdkeyConfigPullService;
import com.linkwee.xoss.helper.OpenApiHelper;

@Component
public class DefaultProductDataPush implements ProductDataPush {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultProductDataPush.class);
	
	@Resource
	private CimProductAddPullService cimProductAddPullService;
	
	@Resource
	private CimProductUpdatePullService cimProductUpdatePullService;
	
	@Resource
	private SysThirdkeyConfigPullService sysThirdkeyConfigPullService;
	
	@Autowired
	private OpenApiHelper openApiHelper;
	
	@Override
	public void pushProductData() {
		
		/**
		 * 待执行产品新增接口的 产品
		 */
		CimProductAddPull cimProductAddPull = new CimProductAddPull();
		cimProductAddPull.setUpdateStatus(1);//1-待执行添加
		List<CimProductAddPull> cimProductAddPullList = cimProductAddPullService.selectListByCondition(cimProductAddPull);
		List<ResultVOProduct> resultVOListProductAdd = new ArrayList<ResultVOProduct>();
		if(CollectionUtils.isNotEmpty(cimProductAddPullList)){		
			for(CimProductAddPull cimProductAddPullNew : cimProductAddPullList){
				SysThirdkeyConfigPull sysThirdkeyConfigPull = sysThirdkeyConfigPullService.selectSysThirdkeyConfigPullByOrgNumber(cimProductAddPullNew.getOrgNumber());
				ResultVOProduct result = openApiHelper.pushProduct(sysThirdkeyConfigPull, cimProductAddPullNew);
				resultVOListProductAdd.add(result);
			}
		}
		if(CollectionUtils.isNotEmpty(resultVOListProductAdd)){
			try{
				//更新推送记录状态
				cimProductAddPullService.updateProductAddPullStatus(resultVOListProductAdd);
			}catch(Exception e){
				LOGGER.info("更新产品新增推送记录状态异常,ResultVOProduct={}",JSONObject.toJSONString(resultVOListProductAdd));
				e.printStackTrace();
			}
		}
		
		/**
		 * 待执行产品更新接口的 产品
		 */
		CimProductUpdatePull cimProductUpdatePull = new CimProductUpdatePull();
		cimProductUpdatePull.setUpdateStatus(1);//1-待执行更新
		List<CimProductUpdatePull> cimProductUpdatePullList = cimProductUpdatePullService.selectListByCondition(cimProductUpdatePull);
		
		List<ResultVOProduct> resultVOListProductUpdate = new ArrayList<ResultVOProduct>();
		if(CollectionUtils.isNotEmpty(cimProductUpdatePullList)){
			LOGGER.info("###############################################调用领会产品【更新】接口###############################################");
			for(CimProductUpdatePull cimProductUpdatePullNew : cimProductUpdatePullList){
				SysThirdkeyConfigPull sysThirdkeyConfigPull = sysThirdkeyConfigPullService.selectSysThirdkeyConfigPullByOrgNumber(cimProductUpdatePullNew.getOrgNumber());
				ResultVOProduct result = openApiHelper.updateProduct(sysThirdkeyConfigPull, cimProductUpdatePullNew);
				resultVOListProductUpdate.add(result);
			}
		}
		if(CollectionUtils.isNotEmpty(resultVOListProductUpdate)){
			try{
				//若更新成功  则直接删除产品更新表（中转表）中产品记录  否则将错误信息更新到 产品更新表
				for(ResultVOProduct resultVOProduct : resultVOListProductUpdate){
					if(resultVOProduct.getCode() == 0){
						cimProductUpdatePullService.delete(Long.valueOf(resultVOProduct.getBizId()));
					} else {
						CimProductUpdatePull cimProductUpdatePull2 = new CimProductUpdatePull();
						cimProductUpdatePull2.setId(resultVOProduct.getBizId());
						cimProductUpdatePull2.setMessage(resultVOProduct.getMsg());
						cimProductUpdatePull2.setUpdateTime(DateUtils.getNow());
						cimProductUpdatePullService.update(cimProductUpdatePull2);
					}
				}
			}catch(Exception e){
				LOGGER.info("更新产品更新推送记录状态异常,resultVOList={}",JSONObject.toJSONString(resultVOListProductUpdate));
				e.printStackTrace();
			}
		}
	}
}
