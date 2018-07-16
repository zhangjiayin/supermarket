package com.linkwee.web.service;

import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActCfpDoubleElevenActivity;
 /**
 * 
 * @描述： ActCfpDoubleElevenActivityService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月23日 11:23:07
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActCfpDoubleElevenActivityService extends GenericService<ActCfpDoubleElevenActivity,Long>{

	/**
	 * 是否出单基金
	 * @param userId
	 * @return
	 */
	boolean hasSaleFund(String userId);

	/**
	 * 用户投资
	 * @param userId
	 */
	void invested(String userId) throws Exception;

	/**
	 * 出单保险
	 * @param userId
	 */
	void saleInsurance(String userId);
	
	/**
	 * 出单基金
	 * @param userId
	 */
	public void saleFund(String userId);
}
