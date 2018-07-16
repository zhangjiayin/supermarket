package com.linkwee.web.service.impl;

import java.util.List;
import java.lang.Long;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.api.response.AccountBookStatisticResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.CrmUserAccountBook;
import com.linkwee.web.model.crm.GoodTransResp;
import com.linkwee.web.dao.CrmUserAccountBookMapper;
import com.linkwee.web.service.CrmUserAccountBookService;
import com.linkwee.web.service.impl.CrmUserAccountBookServiceImpl;


 /**
 * 
 * @描述：CrmUserAccountBookService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年10月14日 15:31:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmUserAccountBookService")
public class CrmUserAccountBookServiceImpl extends GenericServiceImpl<CrmUserAccountBook, Long> implements CrmUserAccountBookService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmUserAccountBookServiceImpl.class);
	
	@Resource
	private CrmUserAccountBookMapper crmUserAccountBookMapper;
	
	@Override
    public GenericDao<CrmUserAccountBook, Long> getDao() {
        return crmUserAccountBookMapper;
    }

	@Override
	public AccountBookStatisticResponse statistics(String userId) {
		return crmUserAccountBookMapper.statistics(userId);
	}

	@Override
	public PaginatorResponse<CrmUserAccountBook> investingList(Page<CrmUserAccountBook> page, String userId) {
		PaginatorResponse<CrmUserAccountBook> investingListResponse = new PaginatorResponse<CrmUserAccountBook>();
		List<CrmUserAccountBook> investingList = crmUserAccountBookMapper.investingList(userId,page);
		investingListResponse.setDatas(investingList);
		investingListResponse.setValuesByPage(page);
		return investingListResponse;
	}

}
