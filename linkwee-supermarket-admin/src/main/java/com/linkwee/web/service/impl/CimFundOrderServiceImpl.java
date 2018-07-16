package com.linkwee.web.service.impl;

import java.util.List;
import java.lang.Long;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;

import com.linkwee.web.model.CimFundOrder;
import com.linkwee.web.dao.CimFundOrderMapper;
import com.linkwee.web.service.CimFundOrderService;
import com.linkwee.web.service.impl.CimFundOrderServiceImpl;


 /**
 * 
 * @描述：CimFundOrderService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年10月24日 17:36:45
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimFundOrderService")
public class CimFundOrderServiceImpl extends GenericServiceImpl<CimFundOrder, Long> implements CimFundOrderService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimFundOrderServiceImpl.class);
	
	@Resource
	private CimFundOrderMapper cimFundOrderMapper;
	
	@Override
    public GenericDao<CimFundOrder, Long> getDao() {
        return cimFundOrderMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimFundOrder -- 排序和模糊查询 ");
		Page<CimFundOrder> page = new Page<CimFundOrder>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimFundOrder> list = this.cimFundOrderMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<CimFundOrder> selectUpdateOrderList() {
		// TODO Auto-generated method stub
		return cimFundOrderMapper.selectUpdateOrderList();
	}

}
