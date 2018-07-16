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
import com.linkwee.web.dao.WxAccessTokenMapper;
import com.linkwee.web.model.WxAccessToken;
import com.linkwee.web.service.WxAccessTokenService;

 /**
 * 
 * @描述：WxAccessTokenService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2017年03月08日 17:14:05
 * 
 * Copyright (c) 深圳米格网络科技有限公司-版权所有
 */
@Service("wxAccessTokenService")
public class WxAccessTokenServiceImpl extends GenericServiceImpl<WxAccessToken, Long> implements WxAccessTokenService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WxAccessTokenServiceImpl.class);
	
	@Resource
	private WxAccessTokenMapper wxAccessTokenMapper;
	
	@Override
    public GenericDao<WxAccessToken, Long> getDao() {
        return wxAccessTokenMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- WxAccessToken -- 排序和模糊查询 ");
		Page<WxAccessToken> page = new Page<WxAccessToken>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<WxAccessToken> list = this.wxAccessTokenMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public WxAccessToken selectNewAccessToken(int appType) {
		// TODO Auto-generated method stub
		return wxAccessTokenMapper.selectNewAccessToken(appType);
	}

}
