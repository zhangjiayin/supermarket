package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;
import java.lang.Long;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.linkwee.api.request.sm.AddRequestRecordRequest;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.SmCustomerRequestRecord;
import com.linkwee.web.dao.SmCustomerRequestRecordMapper;
import com.linkwee.web.service.SmCustomerRequestRecordService;
import com.linkwee.web.service.impl.SmCustomerRequestRecordServiceImpl;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppResponseUtil;
import com.linkwee.xoss.util.AppUtils;


 /**
 * 
 * @描述：SmCustomerRequestRecordService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年05月23日 09:46:16
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("smCustomerRequestRecordService")
public class SmCustomerRequestRecordServiceImpl extends GenericServiceImpl<SmCustomerRequestRecord, Long> implements SmCustomerRequestRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmCustomerRequestRecordServiceImpl.class);
	
	@Resource
	private SmCustomerRequestRecordMapper smCustomerRequestRecordMapper;
	
	@Override
    public GenericDao<SmCustomerRequestRecord, Long> getDao() {
        return smCustomerRequestRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- SmCustomerRequestRecord -- 排序和模糊查询 ");
		Page<SmCustomerRequestRecord> page = new Page<SmCustomerRequestRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<SmCustomerRequestRecord> list = this.smCustomerRequestRecordMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public BaseResponse addRequestRecord(AppRequestHead appRequestHead,AddRequestRecordRequest addRequestRecordRequest) {
		try {
			SmCustomerRequestRecord smCustomerRequestRecord = new SmCustomerRequestRecord();
			BeanUtils.copyProperties(addRequestRecordRequest, smCustomerRequestRecord);
			if(StringUtils.isBlank(smCustomerRequestRecord.getUserId())){
				String userId = JsonWebTokenHepler.getUserIdByToken(appRequestHead.getToken());//查询userId
				if(!"undefined".equals(userId)){
					smCustomerRequestRecord.setUserId(userId);
				}
			}
			smCustomerRequestRecord.setAppType(AppUtils.getAppType(appRequestHead.getOrgNumber()).getValue());
			smCustomerRequestRecord.setDeviceType(AppUtils.getPlatform(appRequestHead.getOrgNumber()).getValue());
			smCustomerRequestRecord.setCreateDate(new Date());
			
			smCustomerRequestRecordMapper.insertSelective(smCustomerRequestRecord);
		} catch (Exception e) {
			LOGGER.error("添加用户用户请求记录异常", e);
			return AppResponseUtil.getErrorData("-1", "添加用户用户请求记录异常");
		}
		return AppResponseUtil.getSuccessResponse();
	}

}
