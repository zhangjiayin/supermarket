package com.linkwee.web.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.api.request.crm.GetWelfareRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CrmCfplannerMapper;
import com.linkwee.web.dao.CrmLineUserInfoMapper;
import com.linkwee.web.model.crm.CrmLineUserInfo;
import com.linkwee.web.service.CrmLineUserInfoService;


 /**
 * 
 * @描述：CrmLineUserInfoService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年05月19日 19:36:07
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmLineUserInfoService")
public class CrmLineUserInfoServiceImpl extends GenericServiceImpl<CrmLineUserInfo, Long> implements CrmLineUserInfoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmLineUserInfoServiceImpl.class);
	
	@Resource
	private CrmLineUserInfoMapper crmLineUserInfoMapper;
	
	@Resource
	private CrmCfplannerMapper crmCfplannerMapper;
	
	@Override
    public GenericDao<CrmLineUserInfo, Long> getDao() {
        return crmLineUserInfoMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmLineUserInfo -- 排序和模糊查询 ");
		Page<CrmLineUserInfo> page = new Page<CrmLineUserInfo>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmLineUserInfo> list = this.crmLineUserInfoMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	@Transactional
	public void insetWelfare(GetWelfareRequest req) throws UnsupportedEncodingException {
		//更新理财师邀请微信用户的数量
		crmCfplannerMapper.updateInvitNumber(req.getCfpUserId());
		//保存数据
		CrmLineUserInfo info = new CrmLineUserInfo();
		info.setCfpMobile(req.getCfpMobile());
		info.setCfpName(req.getCfpName());
		info.setCfpUserId(req.getCfpUserId());
		info.setHeadImgUrl(req.getHeadImgUrl());
		info.setMobile(req.getMobile());
		info.setNickName(URLEncoder.encode(req.getNickName(), "UTF-8"));
		info.setOpenId(req.getOpenId());
		crmLineUserInfoMapper.insertSelective(info);
	}

	@Override
	public PaginatorResponse<CrmLineUserInfo> queryInvitationRecord(Page<CrmLineUserInfo> page,
			Map<String, Object> conditions) {
		PaginatorResponse<CrmLineUserInfo> lineUserResponse = new PaginatorResponse<CrmLineUserInfo>();
		List<CrmLineUserInfo> lineUserList = crmLineUserInfoMapper.queryInvitationRecord(page,conditions);
		for(CrmLineUserInfo lineUser : lineUserList){
			lineUser.setInvitNum(lineUserList.size());
		}
		lineUserResponse.setDatas(lineUserList);
		lineUserResponse.setValuesByPage(page);
		return lineUserResponse;
	}

}
