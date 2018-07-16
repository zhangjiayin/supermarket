package com.linkwee.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.web.dao.CimProductAddPullMapper;
import com.linkwee.web.model.CimProductAddPull;
import com.linkwee.web.push.vo.ResultVOProduct;
import com.linkwee.web.service.CimProductAddPullService;


 /**
 * 
 * @描述：CimProductAddPullService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年02月09日 17:21:31
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimProductAddPullService")
public class CimProductAddPullServiceImpl extends GenericServiceImpl<CimProductAddPull, Long> implements CimProductAddPullService{
	
	@Resource
	private CimProductAddPullMapper cimProductAddPullMapper;
	
	
	@Override
    public GenericDao<CimProductAddPull, Long> getDao() {
        return cimProductAddPullMapper;
    }


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int updateProductAddPullStatus(List<ResultVOProduct> resultVOProductList) {
		// TODO Auto-generated method stub
		return cimProductAddPullMapper.updateProductAddPullStatus(resultVOProductList);
	}
}
