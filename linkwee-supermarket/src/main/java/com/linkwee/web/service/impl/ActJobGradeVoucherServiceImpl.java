package com.linkwee.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.ActJobGradeVoucherMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.CfpJobGradeEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.crm.ActJobGradeVoucher;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.service.ActJobGradeVoucherService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.SmMessageQueueService;
import com.linkwee.web.service.SysMsgService;


 /**
 * 
 * @描述：ActJobGradeVoucherService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年10月27日 18:00:28
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actJobGradeVoucherService")
public class ActJobGradeVoucherServiceImpl extends GenericServiceImpl<ActJobGradeVoucher, Long> implements ActJobGradeVoucherService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActJobGradeVoucherServiceImpl.class);
	
	@Resource
	private ActJobGradeVoucherMapper actJobGradeVoucherMapper;
	@Resource
    private CrmInvestorService crmInvestorService;
	@Resource
    private SmMessageQueueService messageQueueService;
	@Resource
	private CrmCfplannerService crmCfplannerService;
	@Resource
	private SysMsgService sysMsgService;
	
	@Override
    public GenericDao<ActJobGradeVoucher, Long> getDao() {
        return actJobGradeVoucherMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActJobGradeVoucher -- 排序和模糊查询 ");
		Page<ActJobGradeVoucher> page = new Page<ActJobGradeVoucher>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActJobGradeVoucher> list = this.actJobGradeVoucherMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public void insertJobGradeVoucher(ActJobGradeVoucher vou,Object... objects) {
		vou.setVoucherId(StringUtils.getUUID());
		vou.setJobGradeWeight(CfpJobGradeEnum.getCfpJobGradeEnumByKey(vou.getJobGrade()).getLevelWeight());
//		CrmInvestor crmIn = crmInvestorService.queryInvestorByUserId(vou.getUserId());
		CrmCfplanner cfpInfo = crmCfplannerService.queryCfplannerByUserId(vou.getUserId());
		if(cfpInfo==null){
			return;
		}
		String thisMonth = new SimpleDateFormat("yyyy-MM").format(new Date());
		String payMonth = new SimpleDateFormat("yyyy-MM").format(vou.getUseTime());
		vou.setStatus(1);
		if(CfpJobGradeEnum.getCfpJobGradeEnumByKey(vou.getJobGrade()).getLevelWeight()<=CfpJobGradeEnum.getCfpJobGradeEnumByKey(cfpInfo.getJobGrade()).getLevelWeight()
				&&thisMonth.equals(payMonth)){
			vou.setStatus(4);//发放的职级没有用户当前的职级大,直接设为已失效
		}
		vou.setMobile(cfpInfo.getMobile());
		vou.setCreateTime(new Date());
		vou.setUpdateTime(new Date());
		actJobGradeVoucherMapper.insertSelective(vou);
		//推送短信
		if(1==vou.getStatus()){
			messageQueueService.sendSingleMessage(cfpInfo.getMobile(), AppTypeEnum.CHANNEL, MsgModuleEnum.JOB_GRADE_VOUCHER,CfpJobGradeEnum.getCfpJobGradeEnumByKey(vou.getJobGrade()).getMsg());  
			String content = String.format(MsgModuleEnum.SEND_JOB_GRADE_VOUCHER.getMsg(), vou.getActivityAttr(),CfpJobGradeEnum.getCfpJobGradeEnumByKey(vou.getJobGrade()).getMsg());
			if(objects != null){
				content = String.format(MsgModuleEnum.SEND_JOB_GRADE_VOUCHER_NEXT_DAY.getMsg(), vou.getActivityAttr(),CfpJobGradeEnum.getCfpJobGradeEnumByKey(vou.getJobGrade()).getMsg());
			}
			SysMsg msg = new SysMsg();
			msg.setContent(content);
			msg.setStatus(0);// 发布
			msg.setUserNumber(vou.getUserId());
			msg.setReadStatus(0);// 未读
			msg.setAppType(1);
			msg.setTypeName("职级体验券");
			sysMsgService.addMsg(msg);
		}
	}

	@Override
	public int queryCanUserJobGradeVoucher(String userId) {
		return actJobGradeVoucherMapper.queryCanUserJobGradeVoucher(userId);
	}

}
