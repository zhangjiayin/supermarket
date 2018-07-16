package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.CimProductAddPull;
import com.linkwee.web.push.vo.ResultVOProduct;
 /**
 * 
 * @描述： CimProductAddPullService服务接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年02月09日 17:21:31
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductAddPullService extends GenericService<CimProductAddPull,Long>{

	/**
	 * 更新推送记录的状态
	 * @param resultVOList
	 */
	int updateProductAddPullStatus(List<ResultVOProduct> resultVOList);
	
}
