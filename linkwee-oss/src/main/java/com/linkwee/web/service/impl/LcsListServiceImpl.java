package com.linkwee.web.service.impl;

import com.linkwee.core.base.PaginatorSevReq;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.ChangeParentRecordDao;
import com.linkwee.web.dao.LcsListDao;
import com.linkwee.web.dao.TorginfoDao;
import com.linkwee.web.dao.UnconventionalRecordDao;
import com.linkwee.web.enums.UnconventionalTypeEnum;
import com.linkwee.web.model.CfpCancelValideModel;
import com.linkwee.web.model.ChangeParentRecord;
import com.linkwee.web.model.TorginfoModel;
import com.linkwee.web.model.UnconventionalRecord;
import com.linkwee.web.response.LcsCustomeDetailResp;
import com.linkwee.web.response.LcsDetailResp;
import com.linkwee.web.response.LcsTeamDetailResp;
import com.linkwee.web.service.LcsListService;
import com.linkwee.web.util.PaginatorUtil;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service("lcsListService")
public class LcsListServiceImpl implements LcsListService {
	
	@Autowired
	private LcsListDao lcsListDao;

	@Resource
	private TorginfoDao torginfoDao;
	
	@Autowired
	private ChangeParentRecordDao changeParentRecordDao;

	@Resource
	private UnconventionalRecordDao unconventionalRecordDao;

	@Override
	public DataTableReturn queryLcsList(PaginatorSevReq request) {
		DataTableReturn dataTableReturn = new DataTableReturn();
		Page<LcsDetailResp> page = new Page<LcsDetailResp>(request.getPageIndex(),request.getPageSize());
		PageRequest req = PaginatorUtil.toPageRequest(request);
		List<LcsDetailResp> list =lcsListDao.queryLcsList(req,page);
//		PageRequest req = PaginatorUtil.toPageRequest(request);
//		int totalCount = lcsListDao.queryLcsListCount(req.getQuery());
//		if(totalCount==0){
//			return PaginatorUtil.getEmptyResp(req);
//		}
//		req.setContainsTotalCount(false);
		dataTableReturn.setDraw(request.getPageIndex()-1);
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		dataTableReturn.setData(list);
		return dataTableReturn;
	}

	@Override
	public LcsDetailResp queryLcsDetail(String mobile) {
//		Validate.isTrue(lcsListDao.isCfp(mobile)>0,"该手机号码用户不是理财师");
		LcsDetailResp resp = lcsListDao.queryLcsDetail(mobile);
		if(resp == null){
			return null;
		}
		Date disabledLoginEndTime = DateUtils.addDay(resp.getDisabledLoginTime(), 90);
		resp.setDisabledLoginEndTime(disabledLoginEndTime);
		//更换上级记录
		List<ChangeParentRecord> changeParentRecordList = queryChangeParentRecordList(resp.getNumber());
		resp.setChangeParentRecordList(changeParentRecordList);
		return resp;
	}

	@Override
	public LcsDetailResp queryLcsInfo(String mobile) {
		if(StringUtils.isNotBlank(mobile)){
			LcsDetailResp resp = lcsListDao.queryLcsDetail(mobile);
			return resp;
		}
		return null;
	}

	/**
	 * 更换上级记录
	 * @param number
	 * @return
	 */
	private List<ChangeParentRecord> queryChangeParentRecordList(String number) {
		ChangeParentRecord record = new ChangeParentRecord();
		record.setNumber(number);
		List<ChangeParentRecord> list = changeParentRecordDao.list(record);
		return list;
	}

	@Override
	public void exitLcs(String mobile) {
		Validate.isTrue(lcsListDao.isCfp(mobile)>0,"该手机号码用户不是理财师");

		// 操作记录
		lcsListDao.operationRecords(mobile,UnconventionalTypeEnum.REJECT.getMessage());
		// 备份记录
//		lcsListDao.bacRecords(mobile);

//		lcsListDao.changeFreedomCustomer(mobile);
		// 更改理财师为上级理财师客户
		lcsListDao.changeFreedomCustomerNew(mobile);

		lcsListDao.deleteSaleUser(mobile);

	}


	@Override
	public PaginatorSevResp<LcsTeamDetailResp> queryLcsTeamList(PaginatorSevReq request) {
		PageRequest req = PaginatorUtil.toPageRequest(request);
		int totalCount = lcsListDao.queryLcsTeamCount(req.getQuery());
		if(totalCount==0){
			return PaginatorUtil.getEmptyResp(req);
		}
		req.setContainsTotalCount(false);
		return PaginatorUtil.getPaginatorSevResp(req,lcsListDao.queryLcsTeamList(req),totalCount);
	}

	@Override
	public void unbindByLcs(String mobile) {
		exitLcs(mobile);	
	}

	@Override
	public DataTableReturn queryLcsCustomerList(PaginatorSevReq request) {
		Page<LcsCustomeDetailResp> page = new Page<LcsCustomeDetailResp>(request.getPageIndex(),request.getPageSize());
		PageRequest req = PaginatorUtil.toPageRequest(request);

		List<LcsCustomeDetailResp> lcsCustomeDetailRespList = lcsListDao.queryLcsCustomerList(req,page);
		DataTableReturn dataTableReturn = new DataTableReturn();
		dataTableReturn.setDraw(request.getPageIndex()-1);
		dataTableReturn.setData(lcsCustomeDetailRespList);
		dataTableReturn.setRecordsTotal(page.getTotalCount());
		dataTableReturn.setRecordsFiltered(page.getTotalCount());
		return dataTableReturn;
	}

	@Override
	public void unbindByCustomer(String customerMobile, String lcsNumber) {
		lcsListDao.unbindByCustomer(customerMobile, lcsNumber);	
	}

	@Override
	public void replaceLcs(String lcsNumber, String department) {
		lcsListDao.unbindByOrganization(lcsNumber,department);
	}

	@Override
	public CfpCancelValideModel queryValidCancelCFP(String mobile) {
		return lcsListDao.queryValidCancelCFP(mobile);
	}

	/**
	 * 获取理财师职级更变记录
	 * @param unconventionalRecord
	 * @return
     */
	@Override
	public List<UnconventionalRecord> queryCfpLevelOptRecord(UnconventionalRecord unconventionalRecord) {
		if(unconventionalRecord == null){
			return new ArrayList<UnconventionalRecord>();
		}
		return unconventionalRecordDao.queryCfpLevelOptRecord(unconventionalRecord);
	}

	/**
	 * 查子级组织机构列表
	 *
	 * @param torginfoModel
	 * @return
	 */
	@Override
	public List<TorginfoModel> findTorgginNodeListByParentId(TorginfoModel torginfoModel) {
		if(torginfoModel == null){
			return new ArrayList<TorginfoModel>();
		}
		return torginfoDao.findTorgginNodeListByParentId(torginfoModel);
	}

	@Override
	public boolean removeSaleUserHandImage(String mobile) throws Exception {
		return lcsListDao.removeSaleUserHeadImage(mobile)>0;
	}

	@Override
	public Map<String, Object> exportLcsList(Map<String, Object> map) {
		Map<String, Object> datas = new LinkedHashMap<String, Object>();
		List<LcsDetailResp> list = lcsListDao.exportLcsList(map);
		datas.put("list", list==null?Collections.emptyList():list);
		datas.put("size", list==null?0l:Long.valueOf(list.size()));
		return datas;
	}

	@Override
	public Map<String, Object> exportLcsTeamList(Map<String, Object> map) {
		Map<String, Object> datas = new LinkedHashMap<String, Object>();
		List<LcsTeamDetailResp> list = lcsListDao.exportLcsTeamList(map);
		datas.put("list", list==null?Collections.emptyList():list);
		datas.put("size", list==null?0l:Long.valueOf(list.size()));
		return datas;
	}

	@Override
	public Map<String, Object> exportLcsCustomerList(Map<String, Object> map) {
		Map<String, Object> datas = new LinkedHashMap<String, Object>();
		List<LcsCustomeDetailResp> list = lcsListDao.exportLcsCustomerList(map);
		datas.put("list", list==null?Collections.emptyList():list);
		datas.put("size", list==null?0l:Long.valueOf(list.size()));
		return datas;
	}

}
