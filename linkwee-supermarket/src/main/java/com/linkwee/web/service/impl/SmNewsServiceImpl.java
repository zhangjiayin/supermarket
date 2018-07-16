package com.linkwee.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.api.activity.utils.OneYuanDrawUtil;
import com.linkwee.api.request.NewsPageListRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.SmNewsMapper;
import com.linkwee.web.model.SmNews;
import com.linkwee.web.service.SmNewsService;


 /**
 * 
 * @描述：SmNewsService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年07月27日 19:22:57
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("smNewsService")
public class SmNewsServiceImpl extends GenericServiceImpl<SmNews, Long> implements SmNewsService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmNewsServiceImpl.class);
	
	@Resource
	private SmNewsMapper smNewsMapper;
	
	@Override
    public GenericDao<SmNews, Long> getDao() {
        return smNewsMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmNews -- 排序和模糊查询 ");
		Page<SmNews> page = new Page<SmNews>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmNews> list = this.smNewsMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
    
    /**
     * 根据id查询资讯记录
     */
	@Override
	public SmNews findNewsDtl(String fid) {
		long id = Long.parseLong(fid);
		String[] amountArray ="1,3,5,7,9".split(",");
		int index=(int)randomnum(0,amountArray.length-1);
		Integer addAmount = Integer.parseInt(amountArray[index]);
		SmNews model = new SmNews();
		model.setId((int)id);
		model.setReadingAmount(addAmount);
		smNewsMapper.addReadingAmount(model);
		return smNewsMapper.selectByPrimaryKey(id);
	}
	
	// 获取2个值之间的随机数
    private static long randomnum(int smin, int smax){
        int range = smax - smin;
        double rand = Math.random();
        return (smin + Math.round(rand * range));
    }
	
	/**
	 * 查询资讯分页
	 */
	@Override
	public PaginatorResponse<SmNews> queryNewsPageList(NewsPageListRequest newsPageListRequest, Page<SmNews> page) {
		PaginatorResponse<SmNews> paginatorResponse = new PaginatorResponse<SmNews>();
		List<SmNews> newsList = smNewsMapper.queryNewsPageList(newsPageListRequest, page);
		for(SmNews news : newsList){
			news.setContent(null);
		}
		paginatorResponse.setDatas(newsList);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public SmNews queryNewest(Integer appType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appType", appType);
		return smNewsMapper.queryNewest(map);
	}

	@Override
	public List<SmNews> queryTop(NewsPageListRequest newsPageListRequest) {
		return smNewsMapper.queryTop(newsPageListRequest);
	}

}
