package com.linkwee.web.service.news.impl;

import com.linkwee.core.base.PaginatorSevReq;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.base.SuccessCode;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.AdvertisementDao;
import com.linkwee.web.model.Advertisement;
import com.linkwee.web.model.news.AdvertisementListResp;
import com.linkwee.web.service.AdvertisementService;
import com.linkwee.web.util.PaginatorUtil;
import com.xiaoniu.mybatis.paginator.domain.PageList;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年10月17日 15:18:02
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
@Service("advertisementService")
public class AdvertisementServiceImpl implements AdvertisementService{
	

	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private AdvertisementDao advertisementDao;
	

	public List<Advertisement> list(Advertisement advertisement){
		return advertisementDao.list(advertisement);
	}
	
	/**
	 * 分页查询
	 */
	public PaginatorSevResp<Advertisement> queryAdvList(PaginatorSevReq pageRequest) {
		PageRequest req = PaginatorUtil.toPageRequest(pageRequest);
		PageList<Advertisement> datas = advertisementDao.query(req);
		return  PaginatorUtil.toPaginatorSevResp(datas);
	}

	@Override
	public Advertisement findAdvDtl(String fid) {
		return advertisementDao.getByPrimaryKey(fid);
	}

	@Override
	public ReturnCode DeleteAdv(Integer fid) {
		
		 try {
			 advertisementDao.deleteByPrimaryKey(fid);
				return new SuccessCode();
			} catch (Exception e) {
				logger.error("advertisementDao.deleteByPrimaryKey invoke error:"+e.getMessage());
				e.printStackTrace();
				return Error.DB_ERROR;
			}
	}

	@Override
	public ReturnCode SaveAdv(Advertisement adv) {
		 try {
			 advertisementDao.add(adv);
			return new SuccessCode();
		} catch (Exception e) {
			logger.error("advertisementDao.add invoke error:"+e.getMessage());
			e.printStackTrace();
			return Error.DB_ERROR;
		}
	}

	@Override
	public ReturnCode updateAdv(Advertisement adv) {
		 
		 try {
			 advertisementDao.update(adv);
				return new SuccessCode();
			} catch (Exception e) {
				logger.error("advertisementDao.update invoke error:"+e.getMessage());
				e.printStackTrace();
				return Error.DB_ERROR;
			}
	}

	@Override
	public PaginatorSevResp<AdvertisementListResp> queryAdvPageList(
			PaginatorSevReq pageRequest) {
		PageRequest req = PaginatorUtil.toPageRequest(pageRequest);
		PageList<Advertisement> datas = advertisementDao.query(req);
		PaginatorSevResp<Advertisement> rlt =  PaginatorUtil.toPaginatorSevResp(datas);
		PaginatorSevResp<AdvertisementListResp> rltResp = new PaginatorSevResp<AdvertisementListResp>();
		List<AdvertisementListResp> respList = new ArrayList<AdvertisementListResp>();
		for(Advertisement adv :rlt.getDatas()){
			AdvertisementListResp resp = new AdvertisementListResp();
			resp.setId(adv.getId());
			resp.setImgUrl(adv.getImgUrl());
			resp.setLinkUrl(adv.getLinkUrl());
			resp.setAppType(adv.getAppType());
			respList.add(resp);
		}
		rltResp.setDatas(respList);
		rltResp.setPageIndex(rlt.getPageIndex());
		rltResp.setPageSize(rlt.getPageSize());
		rltResp.setTotalCount(rlt.getTotalCount());
		rltResp.setPageCount(rlt.getPageCount());
		return rltResp;
	}

	@Override
	public DataTableReturn findAdvList(Advertisement pageRequest,DataTable dataTable) throws Exception{
		 Page<Advertisement> page = new Page<Advertisement>(dataTable.getStart() / dataTable.getLength() + 1,dataTable.getLength());
		 List<Advertisement> newsRequestList = advertisementDao.findAdvList(pageRequest,page);
		 DataTableReturn dataTableReturn =new DataTableReturn();
		 dataTableReturn.setRecordsFiltered(page.getTotalCount());
		 dataTableReturn.setRecordsTotal(page.getTotalCount());
		 dataTableReturn.setData(newsRequestList);
		 return dataTableReturn;
	}

	
}