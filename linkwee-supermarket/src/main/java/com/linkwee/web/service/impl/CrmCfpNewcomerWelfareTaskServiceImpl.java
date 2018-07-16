package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.List;
import java.lang.Long;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.act.redpacket.service.ActRedpacketService;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.CrmCfpNewcomerWelfareTask;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.model.crm.CrmCfpNewcomerTask;
import com.linkwee.web.dao.CrmCfpNewcomerWelfareTaskMapper;
import com.linkwee.web.enums.AppTypeEnum;
import com.linkwee.web.enums.CfpNewcomerTaskEnum;
import com.linkwee.web.enums.SmsTypeEnum;
import com.linkwee.web.service.CrmCfpNewcomerWelfareTaskService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.web.service.impl.CrmCfpNewcomerWelfareTaskServiceImpl;
import com.linkwee.xoss.helper.PushMessageHelper;


 /**
 * 
 * @描述：CrmCfpNewcomerWelfareTaskService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年09月13日 14:46:43
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("crmCfpNewcomerWelfareTaskService")
public class CrmCfpNewcomerWelfareTaskServiceImpl extends GenericServiceImpl<CrmCfpNewcomerWelfareTask, Long> implements CrmCfpNewcomerWelfareTaskService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrmCfpNewcomerWelfareTaskServiceImpl.class);
	
	@Resource
	private CrmCfpNewcomerWelfareTaskMapper crmCfpNewcomerWelfareTaskMapper;
	
	@Resource
	private ActRedpacketService actRedpacketService;
	
	@Resource
	private CrmUserInfoService crmUserInfoService;
	
	@Resource
	private SysConfigService sysConfigService;
	
	@Resource
    private PushMessageHelper pushMessageHelper;
	
	@Override
    public GenericDao<CrmCfpNewcomerWelfareTask, Long> getDao() {
        return crmCfpNewcomerWelfareTaskMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CrmCfpNewcomerWelfareTask -- 排序和模糊查询 ");
		Page<CrmCfpNewcomerWelfareTask> page = new Page<CrmCfpNewcomerWelfareTask>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CrmCfpNewcomerWelfareTask> list = this.crmCfpNewcomerWelfareTaskMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	@Transactional
	public void sendTaskReward(String userId,CfpNewcomerTaskEnum cfpNewcomerTaskEnum) throws Exception {
		CrmCfpNewcomerWelfareTask task = new CrmCfpNewcomerWelfareTask();
		task.setUserId(userId);
		if(cfpNewcomerTaskEnum == CfpNewcomerTaskEnum.CFP_NEWCOMMER_WELFARE_REGISTER){
			task.setRegStatus(0);
		}else if(cfpNewcomerTaskEnum == CfpNewcomerTaskEnum.CFP_NEWCOMMER_WELFARE_BINDCARD){
			task.setBindcardStatus(0);
		}else if(cfpNewcomerTaskEnum == CfpNewcomerTaskEnum.CFP_NEWCOMMER_WELFARE_INVEST){
			task.setInvestStatus(0);
		}else if(cfpNewcomerTaskEnum == CfpNewcomerTaskEnum.CFP_NEWCOMMER_WELFARE_INVITECFP){
			task.setInviteCfpStatus(0);
		}else if(cfpNewcomerTaskEnum == CfpNewcomerTaskEnum.CFP_NEWCOMMER_WELFARE_INVITECFP_INVEST){
			task.setInviteCfpInvestStatus(0);
		}else if(cfpNewcomerTaskEnum == CfpNewcomerTaskEnum.CFP_NEWCOMMER_WELFARE_ALL){
			task.setWelfareAllStatus(0);
		}
		task = this.selectOne(task);
		if(task != null){
			CrmUserInfo userInfo = crmUserInfoService.queryUserInfoByUserId(userId);
			actRedpacketService.sendLcsNewComerWelfareTaskRedPacekt(cfpNewcomerTaskEnum, userInfo);
			
			String switchStr = sysConfigService.getValuesByKey(SysConfigConstant.SEND_SIX_MSG_SWITCH);
			boolean msgSwitch =  StringUtils.isBlank(switchStr) ? false : Boolean.valueOf(switchStr);
			
			if(cfpNewcomerTaskEnum == CfpNewcomerTaskEnum.CFP_NEWCOMMER_WELFARE_REGISTER){
				task.setRegStatus(1);
			}else if(cfpNewcomerTaskEnum == CfpNewcomerTaskEnum.CFP_NEWCOMMER_WELFARE_BINDCARD){
				task.setBindcardStatus(1);
				if(msgSwitch){
					String content = sysConfigService.getValuesByKey(SysConfigConstant.SEND_SIX_BIND_REDPACKET);
					pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, SmsTypeEnum.SEND_SIX_BIND_REDPACKET, userId, "收到红包", content, null, true);	
				}			
			}else if(cfpNewcomerTaskEnum == CfpNewcomerTaskEnum.CFP_NEWCOMMER_WELFARE_INVEST){
				task.setInvestStatus(1);
				if(msgSwitch){
					String content = sysConfigService.getValuesByKey(SysConfigConstant.SEND_SIX_ONCE_INVEST);
					pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, SmsTypeEnum.SEND_SIX_ONCE_INVEST, userId, "收到红包", content, null, true);	
				}
			}else if(cfpNewcomerTaskEnum == CfpNewcomerTaskEnum.CFP_NEWCOMMER_WELFARE_INVITECFP){
				task.setInviteCfpStatus(1);
				if(msgSwitch){
					String content = sysConfigService.getValuesByKey(SysConfigConstant.SEND_SIX_ONCE_INVITATION_CFP);
					pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, SmsTypeEnum.SEND_SIX_ONCE_INVITATION_CFP, userId, "收到红包", content, null, true);	
				}
			}else if(cfpNewcomerTaskEnum == CfpNewcomerTaskEnum.CFP_NEWCOMMER_WELFARE_INVITECFP_INVEST){
				task.setInviteCfpInvestStatus(1);
				if(msgSwitch){
					String content = sysConfigService.getValuesByKey(SysConfigConstant.SEND_SIX_ALL_CFP_INVEST);
					pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, SmsTypeEnum.SEND_SIX_ALL_CFP_INVEST, userId, "收到红包", content, null, true);
				}
			}else if(cfpNewcomerTaskEnum == CfpNewcomerTaskEnum.CFP_NEWCOMMER_WELFARE_ALL){
				task.setWelfareAllStatus(1);
				if(msgSwitch){
					String content = sysConfigService.getValuesByKey(SysConfigConstant.SEND_SIX_END_REDPACKET);
					pushMessageHelper.pushMessage(AppTypeEnum.CHANNEL, SmsTypeEnum.SEND_SIX_END_REDPACKET, userId, "收到红包", content, null, true);	
				}
			}
			task.setLastUpdateTime(new Date());
			update(task);
		}
	}

	@Override
	public boolean isExistUser(String userId) {
		return crmCfpNewcomerWelfareTaskMapper.isExistUser(userId);
	}

}
