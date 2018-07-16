package com.linkwee.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.ActJobGradeVoucherMapper;
import com.linkwee.web.dao.CrmCfpLevelRecordMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.CfpJobGradeEnum;
import com.linkwee.web.enums.MsgModuleEnum;
import com.linkwee.web.model.CrmCfpLevelRecord;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.crm.ActJobGradeVoucher;
import com.linkwee.web.model.mc.SysMsg;
import com.linkwee.web.service.ActJobGradeVoucherService;
import com.linkwee.web.service.CrmCfpLevelRecordService;
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
	private CrmCfpLevelRecordService crmCfpLevelRecordService;
	@Resource
	private CrmCfpLevelRecordMapper crmCfpLevelRecordMapper;
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
	public void insertJobGradeVoucher(ActJobGradeVoucher vou) throws Exception {
		
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
	public void insertJobGradeVoucherList(List<ActJobGradeVoucher> gradeList) throws Exception {
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Date useTimeDate = DateUtils.parse(today+" 00:00:00",DateUtils.FORMAT_LONG);
		for(ActJobGradeVoucher grade : gradeList){
			actJobGradeVoucherMapper.insertSelective(grade);
			if(useTimeDate.equals(grade.getUseTime())){//当天发放的职级体验券，当天生效,且职级要大于当前
				if(grade.getJobGradeWeight()>CfpJobGradeEnum.getCfpJobGradeEnumByKey(grade.getCurJobGrade()).getLevelWeight()){
					//将正在使用的职级体验券置为已失效
					actJobGradeVoucherMapper.updateByUserIdJobGradeWeight(grade.getUserId(),CfpJobGradeEnum.getCfpJobGradeEnumByKey(grade.getJobGrade()).getLevelWeight());
					ActJobGradeVoucher updateVou = new ActJobGradeVoucher();
					updateVou.setId(grade.getId());
					updateVou.setStatus(2);
					grade.setStatus(2);
					actJobGradeVoucherMapper.updateByPrimaryKeySelective(updateVou);
					//更新tcrm_cfplanner表调整后的职级
					CrmCfplanner crmCfplanner = new CrmCfplanner();
					crmCfplanner.setUserId(grade.getUserId());
					crmCfplanner.setJobGrade(grade.getJobGrade());
					crmCfplanner.setJobGradeWeight(CfpJobGradeEnum.getCfpJobGradeEnumByKey(grade.getJobGrade()).getLevelWeight());
					crmCfplannerService.updateByUserId(crmCfplanner);
					//更新tcrm_cfp_level_record表，先将原数据status置为0，重新插入最新数据
					crmCfpLevelRecordService.updateLevelRecord(grade.getUserId(),grade.getJobGrade(),"system");
				}		
			}
			
			if(grade.getStatus()==1||grade.getStatus()==2){
				//个人消息中心
				String formmsg = grade.getStatus()==1?MsgModuleEnum.SEND_JOB_GRADE_VOUCHER.getMsg():MsgModuleEnum.JOB_USE_TIME_GRADE_VOUCHER.getMsg();
				String content = String.format(formmsg, grade.getActivityAttr(),CfpJobGradeEnum.getCfpJobGradeEnumByKey(grade.getJobGrade()).getMsg());
				SysMsg msg = new SysMsg();
				msg.setContent(content);
				msg.setStatus(0);// 发布
				msg.setUserNumber(grade.getUserId());
				msg.setReadStatus(0);// 未读
				msg.setAppType(1);
				msg.setTypeName("职级体验券");
				sysMsgService.addMsg(msg);
				messageQueueService.sendSingleMessage(grade.getMobile(), AppTypeEnum.CHANNEL, MsgModuleEnum.JOB_GRADE_VOUCHER,grade.getActivityAttr(),CfpJobGradeEnum.getCfpJobGradeEnumByKey(grade.getJobGrade()).getMsg());  
			}
		}
	}

	@Override
	@Transactional
	public void synActJobGradeVoucher() throws Exception {
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		//更新使用职级体验券
		List<ActJobGradeVoucher> gradeList = actJobGradeVoucherMapper.synActJobGradeVoucher(today);
		for(ActJobGradeVoucher grade:gradeList){
			//将正在使用的职级体验券置为已失效
			actJobGradeVoucherMapper.updateByUserIdJobGradeWeight(grade.getUserId(),CfpJobGradeEnum.getCfpJobGradeEnumByKey(grade.getJobGrade()).getLevelWeight());
			//将未使用的设为已失效(针对下个月先发总监后发经理体验券)
			String month = new SimpleDateFormat("yyyy-MM").format(new Date());
			actJobGradeVoucherMapper.updateByMonthUserIdJobGradeWeight(grade.getUserId(),CfpJobGradeEnum.getCfpJobGradeEnumByKey(grade.getJobGrade()).getLevelWeight(),month);
			ActJobGradeVoucher updateVou = new ActJobGradeVoucher();
			updateVou.setId(grade.getId());
			updateVou.setStatus(2);
			updateVou.setUpdateTime(new Date());
			actJobGradeVoucherMapper.updateByPrimaryKeySelective(updateVou);
			//更新tcrm_cfplanner表调整后的职级
			CrmCfplanner crmCfplanner = new CrmCfplanner();
			crmCfplanner.setUserId(grade.getUserId());
			crmCfplanner.setJobGrade(grade.getJobGrade());
			crmCfplanner.setJobGradeWeight(CfpJobGradeEnum.getCfpJobGradeEnumByKey(grade.getJobGrade()).getLevelWeight());
			crmCfplannerService.updateByUserId(crmCfplanner);
			//更新tcrm_cfp_level_record表，先将原数据status置为0，重新插入最新数据
			crmCfpLevelRecordService.updateLevelRecord(grade.getUserId(),grade.getJobGrade(),"system");
			//个人消息中心
			String content = String.format(MsgModuleEnum.JOB_USE_TIME_GRADE_VOUCHER.getMsg(), grade.getActivityAttr(),CfpJobGradeEnum.getCfpJobGradeEnumByKey(grade.getJobGrade()).getMsg());
			SysMsg msg = new SysMsg();
			msg.setContent(content);
			msg.setStatus(0);// 发布
			msg.setUserNumber(grade.getUserId());
			msg.setReadStatus(0);// 未读
			msg.setAppType(1);
			msg.setTypeName("职级体验券");
			sysMsgService.addMsg(msg);
			
			//发送短信
//			messageQueueService.sendSingleMessage(grade.getMobile(), AppTypeEnum.CHANNEL, MsgModuleEnum.JOB_USE_TIME_GRADE_VOUCHER,grade.getActivityAttr(),CfpJobGradeEnum.getCfpJobGradeEnumByKey(grade.getJobGrade()).getMsg());  
		}
	}
	

	@Override
	public void synActExpiresTimeJobGradeVoucher() {
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		//更新过期职级体验券
		List<ActJobGradeVoucher> jobList = actJobGradeVoucherMapper.synExpirseActJobGradeVoucher(today);
		for(ActJobGradeVoucher grade:jobList){
			CrmCfpLevelRecord crmCfpLevelRecord = new CrmCfpLevelRecord();
			crmCfpLevelRecord.setUserId(grade.getUserId());
			crmCfpLevelRecord.setStatus(1);
			CrmCfpLevelRecord temp = crmCfpLevelRecordMapper.selectOneByCondition(crmCfpLevelRecord);
			//恢复调整前的职级
			CrmCfplanner crmCfplanner = new CrmCfplanner();
			crmCfplanner.setUserId(grade.getUserId());
			crmCfplanner.setJobGrade(temp.getPreLevel());
			crmCfplanner.setJobGradeWeight(CfpJobGradeEnum.getCfpJobGradeEnumByKey(temp.getPreLevel()).getLevelWeight());
			crmCfplannerService.updateByUserId(crmCfplanner);
			crmCfpLevelRecord.setStatus(0);
			crmCfpLevelRecordMapper.updateLevelRecordByUserId(crmCfpLevelRecord);
			temp.setId(null);		
			temp.setCurLevel(temp.getPreLevel());
			temp.setOptType(2);
			temp.setOperator("system");
			temp.setCreateTime(new Date());
			temp.setUpdateTime(new Date());
			temp.setStatus(1);
			temp.setUserId(grade.getUserId());
			temp.setPreLevel(grade.getJobGrade());
			temp.setMonth(Integer.parseInt(new SimpleDateFormat("yyyyMM").format(DateUtils.getLastDayOfLastMonth(new Date()))));
			temp.setCurLevelWeight(CfpJobGradeEnum.getCfpJobGradeEnumByKey(grade.getJobGrade()).getLevelWeight());
			crmCfpLevelRecordMapper.insertSelective(temp);
			ActJobGradeVoucher updateVou = new ActJobGradeVoucher();
			updateVou.setId(grade.getId());
			updateVou.setStatus(3);
			updateVou.setUpdateTime(new Date());
			actJobGradeVoucherMapper.updateByPrimaryKeySelective(updateVou);
		}
	}

}
