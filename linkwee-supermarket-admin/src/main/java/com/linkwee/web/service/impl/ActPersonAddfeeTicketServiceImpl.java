package com.linkwee.web.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.linkwee.act.personAddfeeTicket.model.AddFeeTicketImportModel;
import com.linkwee.core.Import.PoiImport;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.ActPersonAddfeeTicketMapper;
import com.linkwee.web.model.ActPersonAddfeeTicket;
import com.linkwee.web.model.ActPersonAddfeeTicketSenduseDetail;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.CrmUserInfo;
import com.linkwee.web.request.act.AddFeeTicketRequest;
import com.linkwee.web.response.act.AddfeeTicketListResponse;
import com.linkwee.web.service.ActPersonAddfeeTicketService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CrmUserInfoService;
import com.linkwee.xoss.helper.ThreadpoolService;
import com.linkwee.xoss.rbac.PermissionSign;
import com.linkwee.xoss.util.InterceptUtility;

import org.apache.commons.lang.Validate;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.CountDownLatch;


/**
 * 
 * @描述：ActPersonAddfeeTicketService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年04月12日 16:02:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actPersonAddfeeTicketService")
public class ActPersonAddfeeTicketServiceImpl extends GenericServiceImpl<ActPersonAddfeeTicket, Long> implements ActPersonAddfeeTicketService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActPersonAddfeeTicketServiceImpl.class);
	
	@Resource
	private ActPersonAddfeeTicketMapper actPersonAddfeeTicketMapper;

    @Value("200")
    private int addfee_ticket_insert_numer;

    @Autowired
    private CrmUserInfoService userInfoService;
    
    @Autowired
    private CimOrginfoService cimOrginfoService;

    @Value("200")
    private int addfee_ticket_number_than_use_subsection;
	
	@Override
    public GenericDao<ActPersonAddfeeTicket, Long> getDao() {
        return actPersonAddfeeTicketMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- ActPersonAddfeeTicket -- 排序和模糊查询 ");
		Page<ActPersonAddfeeTicket> page = new Page<ActPersonAddfeeTicket>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ActPersonAddfeeTicket> list = this.actPersonAddfeeTicketMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	 /**
	  * 获取个人加拥券列表
	  *
	  * @param dt
	  * @return
	  */
	 @Override
	 public DataTableReturn getPersonAddfeeTicketList(DataTable dt) {
		 DataTableReturn tableReturn = new DataTableReturn();
		 tableReturn.setDraw(dt.getDraw()+1);
		 Page<AddfeeTicketListResponse> page = new Page<AddfeeTicketListResponse>(dt.getStart()/dt.getLength()+1,dt.getLength());
		 List<AddfeeTicketListResponse> list = actPersonAddfeeTicketMapper.getPersonAddfeeTicketList(page);

		 Subject currentUser = SecurityUtils.getSubject();
		 String addfeeTicketSendPermission = "0";
		 String addfeeTicketEditPermission = "0";
		 if(currentUser.isPermitted(PermissionSign.PERSON_ADD_FEE_TICKET_SEND)) {
			 addfeeTicketSendPermission = "1";
		 }
		 if(currentUser.isPermitted(PermissionSign.PERSON_ADD_FEE_TICKET_EDIT)) {
			 addfeeTicketEditPermission = "1";
		 }
		 for(AddfeeTicketListResponse temp : list){
			 temp.setAddfeeTicketSendPermission(addfeeTicketSendPermission);
			 temp.setAddfeeTicketEditPermission(addfeeTicketEditPermission);
		 }

		 tableReturn.setData(list);
		 tableReturn.setRecordsFiltered(page.getTotalCount());
		 tableReturn.setRecordsTotal(page.getTotalCount());
		 return tableReturn;
	 }

	 /**
	  * 添加加拥券
	  *
	  * @param ticketRequest
	  */
	 @Override
	 public void insertAddFeeTicket(AddFeeTicketRequest ticketRequest) {
		 ActPersonAddfeeTicket ticket = new ActPersonAddfeeTicket();
		 BeanUtils.copyProperties(ticketRequest,ticket);
		 if(ticket.getPlatformLimitOrgNumber() != null && !ticket.getPlatformLimitOrgNumber().equals("all")){
			 CimOrginfo temp = new CimOrginfo();
			 temp.setOrgNumber(ticket.getPlatformLimitOrgNumber());
			 temp = cimOrginfoService.selectOne(temp);
			 ticket.setPlatformLimitOrgName(temp.getName());
		 }
		 ticket.setPlatformLimit(ticketRequest.getPlatformLimit() == 1);
		 ticket.setTicketId(StringUtils.getUUID());
		 ticket.setCreateTime(new Date());
		 insert(ticket);
	 }

	 /**
	  * 编辑加拥券
	  *
	  * @param ticketRequest
	  */
	 @Override
	 public void updateAddFeeTicket(AddFeeTicketRequest ticketRequest) {
		 ActPersonAddfeeTicket ticket = new ActPersonAddfeeTicket();
		 BeanUtils.copyProperties(ticketRequest,ticket);
		 if(ticket.getPlatformLimitOrgNumber() != null && !ticket.getPlatformLimitOrgNumber().equals("all")){
			 CimOrginfo temp = new CimOrginfo();
			 temp.setOrgNumber(ticket.getPlatformLimitOrgNumber());
			 temp = cimOrginfoService.selectOne(temp);
			 ticket.setPlatformLimitOrgName(temp.getName());
		 }
		 ticket.setPlatformLimit(ticketRequest.getPlatformLimit() == 1);
		 ticket.setUpdateTime(new Date());
		 actPersonAddfeeTicketMapper.updateAddFeeTicket(ticket);
	 }

	/**
	 * 发放个人加拥券
	 *
	 * @param file
	 * @param ticketId
	 * @param startDate
	 * @param endDate
	 * @param username
	 * @return
	 */
	@Override
	public Set<String> sendAddFeeTicket(MultipartFile file, final String ticketId, final Date startDate, final Date endDate, final String username, final String remark) throws Exception {
		long s = System.currentTimeMillis();
		LOGGER.debug("start sendAddFeeTicket");
		Validate.notEmpty(ticketId,"加拥券编号不能为空");
		final ActPersonAddfeeTicket addfeeTicket = getAddFeeTicket(ticketId);
		Validate.notNull(addfeeTicket,"加拥券不存在");
		//解析导入模板
		final List<AddFeeTicketImportModel>  addFeeTicketImportModels = getAddFeeTicketImportModels(file);
		Validate.notEmpty(addFeeTicketImportModels,"导入用户为空");
		final String sendId = StringUtils.getUUID();

		final Set<ActPersonAddfeeTicketSenduseDetail> ticketSendDetails = Sets.newConcurrentHashSet();
		final Set<String> msg  = Sets.newConcurrentHashSet();
		List<List<AddFeeTicketImportModel>>  addFeeTicketImportModelss = Lists.newArrayList();
		// 按 addfee_ticket_insert_numer 用户数据  分段
		InterceptUtility.subsection(addFeeTicketImportModels, addFeeTicketImportModelss,addfee_ticket_insert_numer*4);

		final CountDownLatch countDownLatch = new CountDownLatch(addFeeTicketImportModelss.size());
		/**
		 * 分批次 进行多线程创建红包
		 */
		for (final List<AddFeeTicketImportModel> list : addFeeTicketImportModelss) {
			ThreadpoolService.execute(new Runnable() {
				@Override
				public void run() {
					try{
						List<String> sendTicketUserMobiles = Lists.newArrayListWithCapacity(list.size());
						for (AddFeeTicketImportModel addFeeTicketImportModel : list) {
                            sendTicketUserMobiles.add(addFeeTicketImportModel.getMobile());
						}
						//查询本批次发放的所有用户信息 避免单次慢查询
						List<CrmUserInfo> userInfos =  userInfoService.queryUserListByMobileList(sendTicketUserMobiles);
						if(userInfos==null )userInfos =  Lists.newArrayListWithCapacity(1);

						Map<String, CrmUserInfo> userMaps = Maps.newHashMapWithExpectedSize(userInfos.size());
						//映射用户手机号码与用户信息 方便查询用户信息
						for (CrmUserInfo crmUserInfo : userInfos) {
							userMaps.put(crmUserInfo.getMobile(), crmUserInfo);
						}

						for (AddFeeTicketImportModel addFeeTicketImportModel : list) {
							CrmUserInfo crmUserInfo = userMaps.get(addFeeTicketImportModel.getMobile());
							if(crmUserInfo ==null){
								msg.add("用户不存在_"+addFeeTicketImportModel.getMobile());
								continue;
							}
							//创建个人加拥券
                            ticketSendDetails.add(createTicketSendDetail(crmUserInfo, sendId, ticketId, startDate, endDate,username,remark));
						}
					}catch(Exception e){
						LOGGER.warn("创建个人加拥券",e);
					}finally{
						countDownLatch.countDown();
					}
				}
			});
		}
		countDownLatch.await();

		//可发放个人加拥券为空 返回
		if(ticketSendDetails.isEmpty()) return msg;

		//发放总个人加拥券数量
		int size = ticketSendDetails.size();

		List<List<ActPersonAddfeeTicketSenduseDetail>> ticketss = Lists.newArrayList();
		//总个人加拥券数量  addfee_ticket_number_than_use_subsection
		//true : 分批插入  | false :  一次插入
		if(size > addfee_ticket_number_than_use_subsection ){
			InterceptUtility.subsection(new ArrayList<ActPersonAddfeeTicketSenduseDetail>(ticketSendDetails),ticketss,addfee_ticket_insert_numer);
		}else{
            ticketss.add(new ArrayList<ActPersonAddfeeTicketSenduseDetail>(ticketSendDetails));
		}
		//批量插入个人加拥券
        for (List<ActPersonAddfeeTicketSenduseDetail> addfeeTicketList : ticketss) {
            actPersonAddfeeTicketMapper.inserts(addfeeTicketList);
        }

		LOGGER.debug("send addFeeTicket end time = {}",  System.currentTimeMillis()-s);
		//异步发送消息
		/*ThreadpoolService.execute(new Runnable(){
			@Override
			public void run() {
				try{
					callback.sendMsgs(mobileOrUserIds,userIds,callback.getMsgContent(redpacket, sendNum));
				}catch(Exception e){
					LOGGER.warn("sendMsgs exception",e);
				}
			}});*/
		return msg;
	}

    private ActPersonAddfeeTicketSenduseDetail createTicketSendDetail(CrmUserInfo crmUserInfo, String sendId, String ticketId, Date startDate, Date endDate, String operator, String remark) {
        ActPersonAddfeeTicketSenduseDetail detail = new ActPersonAddfeeTicketSenduseDetail();
        detail.setUserId(crmUserInfo.getUserId());
        detail.setTicketId(ticketId);
        detail.setTicketSendId(sendId);
        detail.setValidBeginTime(startDate);
        detail.setValidEndTime(endDate);
        detail.setSendTime(new Date());
        detail.setCreateTime(new Date());
        detail.setOperator(operator);
        detail.setRemark(remark);
        return  detail;
    }

    private List<AddFeeTicketImportModel> getAddFeeTicketImportModels(MultipartFile file) throws Exception {
		InputStream inputStream = file.getInputStream();
		return PoiImport.dataImport(inputStream, AddFeeTicketImportModel.class);
	}

	private ActPersonAddfeeTicket getAddFeeTicket(String ticketId) {
		ActPersonAddfeeTicket ticket = new ActPersonAddfeeTicket();
		ticket.setTicketId(ticketId);
		ticket = selectOne(ticket);
		return ticket;
	}

}
