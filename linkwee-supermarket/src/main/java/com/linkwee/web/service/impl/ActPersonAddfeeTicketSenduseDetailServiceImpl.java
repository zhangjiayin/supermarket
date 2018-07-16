package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;
import java.lang.Long;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActPersonAddfeeTicketSenduseDetail;
import com.linkwee.web.model.acc.ActPersonAddfeeTicketExtends;
import com.linkwee.web.dao.ActPersonAddfeeTicketSenduseDetailMapper;
import com.linkwee.web.enums.ActicityPersonAddFeeTicketEnum;
import com.linkwee.web.enums.ActicityPersonAddFeeTicketPeriodEnum;
import com.linkwee.web.service.ActPersonAddfeeTicketSenduseDetailService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.impl.ActPersonAddfeeTicketSenduseDetailServiceImpl;


 /**
 * 
 * @描述：ActPersonAddfeeTicketSenduseDetailService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年04月12日 16:02:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actPersonAddfeeTicketSenduseDetailService")
public class ActPersonAddfeeTicketSenduseDetailServiceImpl extends GenericServiceImpl<ActPersonAddfeeTicketSenduseDetail, Long> implements ActPersonAddfeeTicketSenduseDetailService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActPersonAddfeeTicketSenduseDetailServiceImpl.class);
	
	@Resource
	private ActPersonAddfeeTicketSenduseDetailMapper actPersonAddfeeTicketSenduseDetailMapper;
	
	@Autowired
	private SysConfigService sysConfigService;
	
	@Override
    public GenericDao<ActPersonAddfeeTicketSenduseDetail, Long> getDao() {
        return actPersonAddfeeTicketSenduseDetailMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActPersonAddfeeTicketSenduseDetail -- 排序和模糊查询 ");
		Page<ActPersonAddfeeTicketSenduseDetail> page = new Page<ActPersonAddfeeTicketSenduseDetail>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActPersonAddfeeTicketSenduseDetail> list = this.actPersonAddfeeTicketSenduseDetailMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public ActPersonAddfeeTicketExtends queryPersonUseAddfeeTicketByInvestId(String investId) {
		// TODO Auto-generated method stub
		return actPersonAddfeeTicketSenduseDetailMapper.queryPersonUseAddfeeTicketByInvestId(investId);
	}

	@Override
	public void lcsActivityPersonAddFeeTicket(String userId,ActicityPersonAddFeeTicketEnum personAddFeeTicketEnum, ActicityPersonAddFeeTicketPeriodEnum personAddFeeTicketPeriodEnum, String remark) {
		String personAddFeeTicketId = sysConfigService.getValuesByKey(personAddFeeTicketEnum.getValue());
		String period = sysConfigService.getValuesByKey(personAddFeeTicketPeriodEnum.getValue());
		int days = Integer.parseInt(period);
		ActPersonAddfeeTicketSenduseDetail detail = new ActPersonAddfeeTicketSenduseDetail();
        detail.setUserId(userId);
        detail.setTicketId(personAddFeeTicketId);
        detail.setTicketSendId(StringUtils.getUUID());
        detail.setValidBeginTime(new Date());
        detail.setValidEndTime(DateUtils.addDay(new Date(), days));
        detail.setSendTime(new Date());
        detail.setCreateTime(new Date());
        detail.setOperator("system");
        detail.setRemark(remark);
        actPersonAddfeeTicketSenduseDetailMapper.insertSelective(detail);
	}

}
