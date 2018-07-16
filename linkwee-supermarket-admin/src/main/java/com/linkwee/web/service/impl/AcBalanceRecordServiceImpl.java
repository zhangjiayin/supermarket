package com.linkwee.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.tc.fee.model.TCFee;
import com.linkwee.web.dao.AcAccountBindMapper;
import com.linkwee.web.dao.AcBalanceRecordMapper;
import com.linkwee.web.dao.TCFeeMapper;
import com.linkwee.web.model.acc.AcAccountBind;
import com.linkwee.web.model.acc.AcBalanceRecord;
import com.linkwee.web.service.AcBalanceRecordService;


 /**
 * 
 * @描述：AcBalanceRecordService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月22日 21:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("acBalanceRecordService")
public class AcBalanceRecordServiceImpl extends GenericServiceImpl<AcBalanceRecord, Long> implements AcBalanceRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AcBalanceRecordServiceImpl.class);
	
	@Resource
	private AcBalanceRecordMapper acBalanceRecordMapper;
	
	@Resource
	private AcAccountBindMapper accountbindMapper;
	
    @Resource
    private TCFeeMapper tcFeeMapper;
	
	@Override
    public GenericDao<AcBalanceRecord, Long> getDao() {
        return acBalanceRecordMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- AcBalanceRecord -- 排序和模糊查询 ");
		Page<AcBalanceRecord> page = new Page<AcBalanceRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<AcBalanceRecord> list = this.acBalanceRecordMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public PaginatorResponse<AcBalanceRecord> queryMyAccountDetails(Page<AcBalanceRecord> page,
			Map<String, Object> conditions) {
		PaginatorResponse<AcBalanceRecord> accountListResponse = new PaginatorResponse<AcBalanceRecord>();
		List<AcBalanceRecord> accountList = acBalanceRecordMapper.queryMyAccountDetails(page,conditions);
		accountListResponse.setDatas(accountList);
		accountListResponse.setValuesByPage(page);
		return accountListResponse;
	}
	
	@Override
	public PaginatorResponse<AcBalanceRecord> queryMyAccountDetails2(Page<AcBalanceRecord> page,
			Map<String, Object> conditions) {
		PaginatorResponse<AcBalanceRecord> accountListResponse = new PaginatorResponse<AcBalanceRecord>();
		List<AcBalanceRecord> accountList = acBalanceRecordMapper.queryMyAccountDetails2(page,conditions);
		accountListResponse.setDatas(accountList);
		accountListResponse.setValuesByPage(page);
		return accountListResponse;
	}

	@Override
	@Transactional
	public void grantProfit(List<AcBalanceRecord> balanList) {
		
		for(AcBalanceRecord ac:balanList){
			//插入交易明细
			acBalanceRecordMapper.insertSelective(ac);
		    //添加发放金额
			accountbindMapper.acGrantProfit(ac.getTransAmount().toString(),ac.getUserId());
			
		}
		
	}

	@Override
	public DataTableReturn selectGrantByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- AcBalanceRecord -- 排序和模糊查询 ");
		Page<AcBalanceRecord> page = new Page<AcBalanceRecord>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<AcBalanceRecord> list = this.acBalanceRecordMapper.selectGrantBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public List<AcBalanceRecord> checkSameSerialNumber(String serialNumber) {
		return acBalanceRecordMapper.checkSameSerialNumber(serialNumber);
	}

	@Override
	@Transactional
	public void payLeaderProfit() {
		//1.先查询出1006类型数据
		TCFee tcFee = new TCFee();
		tcFee.setFeeType("1006");
		List<TCFee> tcList = tcFeeMapper.selectByFeeTypeAndCreateTime();
		//发放Leader团队津贴
		for(TCFee tc: tcList){
			if(tc.getFeeAmount().compareTo(BigDecimal.ZERO)==1){//大于0
				AcBalanceRecord acBR = new AcBalanceRecord();
				acBR.setOrderId(tc.getBizId());
				AcBalanceRecord haveRecord = acBalanceRecordMapper.selectOneByCondition(acBR);
				if(haveRecord==null){//避免重复发放
					LOGGER.debug(">>>>>>>>>>>Leader团队津贴充值starting>>>>>>>>>");
					//先更改账号金额
					AcAccountBind bind =  accountbindMapper.selectAccountByUserId(tc.getProfitCfplannerId());
					BigDecimal totalAmount = new BigDecimal(bind.getTotalAmount());
					bind.setTotalAmount(totalAmount.add(tc.getFeeAmount()).toString());
					accountbindMapper.updateByPrimaryKeySelective(bind);
					//再插入银行流水记录
					AcBalanceRecord record = new AcBalanceRecord();
					String serialNumber = StringUtils.getUUID();
					record.setTransAmount(tc.getFeeAmount());
					record.setOrderId(tc.getBizId());
					record.setUserId(tc.getProfitCfplannerId());
					record.setBankCardId(bind.getBankCardId());
					record.setCreateTime(new Date());
					record.setTransDate(new Date());
					record.setUserType(1);
					record.setTransType(15);
					record.setRemark(tc.getRemark());
					record.setSerialNumber(serialNumber);
					acBalanceRecordMapper.insertSelective(record);
					LOGGER.debug(">>>>>>>>>>>Leader团队津贴充值完成>>>>>>>>>");
				}
			}
		}
		
	}
	

}
