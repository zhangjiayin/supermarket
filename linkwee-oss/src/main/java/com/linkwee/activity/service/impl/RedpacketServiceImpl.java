package com.linkwee.activity.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.linkwee.activity.dao.ActivityMapper;
import com.linkwee.activity.dao.ActivityRedpacketMapper;
import com.linkwee.activity.dao.RedpacketAccountMapper;
import com.linkwee.activity.dao.RedpacketMapper;
import com.linkwee.activity.dao.RedpacketTypeMapper;
import com.linkwee.activity.model.Activity;
import com.linkwee.activity.model.ActivityRedpacket;
import com.linkwee.activity.model.ExcelModel;
import com.linkwee.activity.model.GenerateRedPacketRule;
import com.linkwee.activity.model.GenerateRuleContext;
import com.linkwee.activity.model.GenerateRuleDetail;
import com.linkwee.activity.model.Product;
import com.linkwee.activity.model.ProductUseCondition;
import com.linkwee.activity.model.Redpacket;
import com.linkwee.activity.model.RedpacketAccount;
import com.linkwee.activity.model.RedpacketCal;
import com.linkwee.activity.model.RedpacketRule;
import com.linkwee.activity.model.RedpacketType;
import com.linkwee.activity.model.Redpaper;
import com.linkwee.activity.model.UseRedPacketRule;
import com.linkwee.activity.model.UseRuleContext;
import com.linkwee.activity.model.UseRuleDetail;
import com.linkwee.activity.service.ActivityRedpacketService;
import com.linkwee.activity.service.ActivityService;
import com.linkwee.activity.service.GenerateRedPacketRuleService;
import com.linkwee.activity.service.RedpacketService;
import com.linkwee.activity.service.RedpacketTypeService;
import com.linkwee.activity.service.UseRedPacketRuleService;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.GrayReleaseMapper;
import com.linkwee.web.model.GrayRelease;
import com.linkwee.web.model.InvestorUserInfo;
import com.linkwee.web.model.SaleUserInfo;
import com.linkwee.web.request.RedPacketInfoRequest;
import com.linkwee.web.request.SendRedPacketRequest;
import com.linkwee.web.service.InvestorUserInfoService;
import com.linkwee.web.service.SaleUserInfoService;
import com.linkwee.web.util.GenerateNumberUtils;
import com.linkwee.web.util.MoneyUtil;
import com.linkwee.web.util.PageUtil;

@Service
public class RedpacketServiceImpl extends GenericServiceImpl<Redpacket, Long> implements RedpacketService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RedpacketServiceImpl.class);
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private RedpacketTypeService redpacketTypeService;
	
	@Autowired
	private ActivityRedpacketService activityRedpacketService;
	
	@Autowired
	private GenerateRedPacketRuleService generateRedPacketRuleService;
	
	@Autowired
	private UseRedPacketRuleService useRedPacketRuleService;
	
	@Autowired
	private RedpacketMapper redpacketMapper;
	
	@Autowired
	private RedpacketAccountMapper redpacketAccountMapper;
	
	@Autowired
	private ActivityMapper ActivityMapper;
	
	@Autowired
	private RedpacketTypeMapper redpacketTypeMapper;
	
	@Autowired
	private ActivityRedpacketMapper ActivityRedpacketMapper;
	
	@Autowired
	private InvestorUserInfoService investorUserInfoService;
	
	@Autowired
	private SaleUserInfoService saleUserInfoService;
	
	@Autowired
	private GrayReleaseMapper grayReleaseMapper; //白名单 list
	
	@Override
	public GenericDao<Redpacket, Long> getDao() {
		return redpacketMapper;
	}


	@Override
	public void insertRedpacket(RedPacketInfoRequest req,String operator) throws Exception {
		try{
			
			Validate.notNull(req.getValidDate(),"红包有效期不能为空");
			//添加新活动
			Date date= new Date();
			Validate.isTrue(req.getValidDate().compareTo(date)==1,"有效时间必须大于当前时间");
			
			Activity activity = activityService.insertActivity(req.getName(),date,req.getValidDate());
			
			/**
			 * 红包信息
			 */
			Validate.notNull(req.getMoney(),"红包金额不能为空");
			Validate.isTrue(req.getMoney()>0d,"红包金额必须大于0");
			//红包类型
			RedpacketType redpacketType = redpacketTypeService.insertRedpacketType(req.getMoney(),req.getName(), date,operator);
			
			//红包描述
			activityRedpacketService.insertActivityRedpacket(activity.getFid(), redpacketType.getFid(), req.getName(), req.getRemark(), date);
			
			/**
			 * 红包生成规则配置
			 */
			boolean  generateRule =  generateRedPacketRuleService.insertActivityGenerateRule(new GenerateRuleContext(activity, redpacketType, req.getRtype(),req.getValidDate(),date));
			Validate.isTrue(generateRule,"添加红包生成规则信息异常");
			
			/**
			 * 红包使用规则
			 */
			boolean  useRule =  useRedPacketRuleService.insertUseRedPacketRule(new UseRuleContext(req, activity, redpacketType, date));
			Validate.isTrue(useRule,"添加红包使用规则信息异常");
		
		}catch(Exception e){
			LOGGER.error("insertRedpacket exception redPacketInfoRequest={},exception={}", req,e);
			throw e;
		}
		
	}
	
	@Override
	public void updateRedpacket(RedPacketInfoRequest req,String activityId,String redpacketTypeId,String operator) throws Exception {
		try{
			Validate.notNull(req.getValidDate(),"红包有效期不能为空");
			//添加新活动
			Date date= new Date();
			Validate.isTrue(req.getValidDate().compareTo(date)==1,"有效时间必须大于当前时间");
			Activity activity = activityService.getActivity(activityId);
			Validate.notNull(activity,"红包不存在");
			Validate.isTrue(activityService.updateActivity(activityId, req.getName(), req.getValidDate(), date),"更新红包失败");
			RedpacketType redpacketType = redpacketTypeService.getRedPaperTypeById(redpacketTypeId);
			Validate.notNull(redpacketType,"红包不存在");
			Validate.isTrue(redpacketTypeService.updateRedpacketType(redpacketTypeId, req.getMoney(), req.getName(), date, operator),"更新红包失败");
			Validate.isTrue(activityRedpacketService.updateActivityRedpacket(activityId, redpacketTypeId,  req.getName(), req.getRemark(), date),"更新红包失败");
			Validate.isTrue(generateRedPacketRuleService.deleteActivityGenerateRule(activityId),"更新红包失败");
			boolean  generateRule = generateRedPacketRuleService.insertActivityGenerateRule(new GenerateRuleContext(activity, redpacketType, req.getRtype(),req.getValidDate(),date));
			Validate.isTrue(generateRule,"更新红包失败");
			Validate.isTrue(useRedPacketRuleService.deleteUseRedPacketRule(activityId),"更新红包失败");
			boolean  useRule =  useRedPacketRuleService.insertUseRedPacketRule(new UseRuleContext(req, activity, redpacketType, date));
			Validate.isTrue(useRule,"更新红包失败");
		}catch(Exception e){
			LOGGER.error("insertRedpacket exception redPacketInfoRequest={},activityId={},redpacketTypeId={},operator={},exception={}",new Object[]{req,activityId,redpacketTypeId,operator,e} );
			throw e;
		}
	}


	@Override
	public DataTableReturn getRedpacketList(DataTable dt){
		
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		Page<Redpaper> page = new Page<Redpaper>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<Redpaper> list = redpacketMapper.getRedpacketList(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());

		return tableReturn;
	}
	
	
	
	@Override
	public void insertBatchRedpacket(SendRedPacketRequest request) throws Exception{
		try{
			batchSend(request);//正式发送
		}catch(Exception exception){
			LOGGER.error("正式发放红包出现异常!", exception);
			throw exception;
		}
	}
	
	
	private void batchSend(SendRedPacketRequest request){
		
		 Activity activity = ActivityMapper.getActivity(request.getActivityId());
		 Validate.notNull(activity,"红包不存在");
		 
		 String activityName = activity.getName();
		 
		
		 
		 RedpacketType redPaperType = redpacketTypeMapper.getRedPaperTypeById(request.getRedPacketTypeId());
		 Validate.notNull(redPaperType,"红包不存在");
		 
		 ActivityRedpacket activityRedPaper = ActivityRedpacketMapper.getActivityRedPaperByActivityAndRedPaperTypeId(request.getActivityId(), redPaperType.getFid());
	     Validate.notNull(activityRedPaper,"红包描述不存在");
			
		 GenerateRedPacketRule generateRedPacketRule = generateRedPacketRuleService.getGenerateRedPacketRuleByActivityId(request.getActivityId());
		 Validate.notNull(generateRedPacketRule,"红包规则不存在");
		 

		 //当前日期
		 Date date = Calendar.getInstance().getTime();
		 //[{"max":50000,"min":10000,"redPaperType":[{"busType":1,"redpaperMoney":80,"redpaperTypeId":"c5072453fe824a84a0fcc1cf794e9065"}]},{"max":100000,"min":50000,"redPaperType":[{"busType":1,"redpaperMoney":80,"redpaperTypeId":"c5072453fe824a84a0fcc1cf794e9065"},{"busType":1,"redpaperMoney":400,"redpaperTypeId":"85c64dba0b9d4c2f9cf3155d84059d3e"}]},{"max":1.0E7,"min":100000,"redPaperType":[{"busType":1,"redpaperMoney":80,"redpaperTypeId":"c5072453fe824a84a0fcc1cf794e9065"},{"busType":1,"redpaperMoney":400,"redpaperTypeId":"85c64dba0b9d4c2f9cf3155d84059d3e"},{"busType":1,"redpaperMoney":820,"redpaperTypeId":"56d970a70a714c4d8b2058d9f8e9827d"}]}]
		 String redPaperRuleJson = generateRedPacketRule.getRedPaperRuleJson();
		 GenerateRuleDetail generateRuleDetail = JSON.parseObject(redPaperRuleJson, GenerateRuleDetail.class);
		 RedpacketRule curRule = null;
		 List<RedpacketRule> redpacketRuleList = generateRuleDetail.getRedPaperType();
		 for(RedpacketRule redpacketRule : redpacketRuleList){
			 if(redpacketRule.getRedpaperTypeId().equals(request.getRedPacketTypeId())){
				 curRule=redpacketRule;
			 }
			 
		 }
		 Validate.notNull(curRule,"红包类型不存在");

		 //红包规则的红包有效期
		 Date expDate = generateRedPacketRule.getDay()==null?curRule.getValidDate():DateUtils.addDays(date, generateRedPacketRule.getDay());
		 
		 
		 //Double activitySendMoney = redPaperAccount.getSendMoney();//已派发金额
		 
		 //Double activityMoney = redPaperAccount.getMoney(); //活动总金额
		 
		 Double totalMoney = 0d;
		 
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("cfpLevelList", request.getCfpLevels()); //理财师级别
		 int lcsCounts = saleUserInfoService.getAllSaleCounts(map);
		 PageUtil<SaleUserInfo> page = new PageUtil<SaleUserInfo>();
		 page.setTotalCount(lcsCounts); //设置总条数 并计算出总页数  默认100条/页
			
		 Double currentSendMoney = redPaperType.getMoney(); //红包金额
		//按页进行循环 批量插入 每次插入200条
		for(int i = 1;i <= page.getPageCount();i++){
			/*
			int count = i*page.getPageSize();
			if(count>=2000){
				if(count % 2000 == 0){
					expDate = DateUtils.addMinutes(expDate, -2); //2分钟前的时间
				}
			}
			*/
			List<Redpacket> redPapers  = new LinkedList<Redpacket>();
			LOGGER.info("发红包: 200条/批次  执行到: 第"+ i +"批次");
					
			map.put("startPageIndex", (i - 1) * page.getPageSize());
			map.put("pageSize", page.getPageSize());
			
			//查询所有理财师信息
			List<SaleUserInfo> allSaleInfo = saleUserInfoService.getAllSaleInfo(map); 
			int userSize = allSaleInfo.size() > 0?allSaleInfo.size():0;
			if(userSize == 0){
				return;
			}			
			
			for(SaleUserInfo saleUserInfo : allSaleInfo){
				
				Validate.isTrue(StringUtils.isNotBlank(saleUserInfo.getMobile()), "理财师手机不能为空!");
				Validate.notNull(currentSendMoney,"红包金额不能为空");
				
				Validate.isTrue(currentSendMoney>0, "红包金额必须大于0!");
				
				//检查各个红包账户的派发金额是否会超过总金额，如果是则不派发红包
				//无限金额,则跳过
			/*	if(!ObjectUtils.equals(redPaperAccount.getMoney(), 0d)){
					
					activitySendMoney =MoneyUtil.add(activitySendMoney, currentSendMoney);
					boolean isContinue = Operation.GE.compare(activityMoney, activitySendMoney);
					Validate.isTrue(isContinue, "兑换红包金额超过限定总金额,兑换失败!");
					
				}*/
				
				
				totalMoney =MoneyUtil.add(totalMoney, currentSendMoney);
				
				Redpacket redPaper = new Redpacket();
				redPaper.setFid(GenerateNumberUtils.generateKey());
				redPaper.setInitDate(date);
				redPaper.setUpdateDate(date);
				redPaper.setStatus(1); //未派发
				redPaper.setRedpaperMoney(currentSendMoney); //红包金额
				redPaper.setBusType(curRule.getBusType());
				redPaper.setExpireDate(expDate);
				
				redPaper.setActivityId(request.getActivityId());
				redPaper.setActivityName(activityName);
				
				redPaper.setRedPaperType(redPaperType.getFid());
				redPaper.setShowName(activityRedPaper.getShowName());
				redPaper.setUseRemark(activityRedPaper.getUseRemark());
				

				//设置理财师信息
				redPaper.setSaleUserId(saleUserInfo.getNumber());
				redPaper.setSaleUserName(saleUserInfo.getName());
				redPaper.setSaleUserMobile(saleUserInfo.getMobile());
				redPaper.setOperator(request.getLoginUserName()); //发放红包操作人
				
				redPapers.add(redPaper);
									
			}
			
			//发红包
			try {
				redpacketMapper.insertBatchRedpacket(redPapers); //批量生成红包 每次插入100条
			 	//写日志 存插入成功的数据
			} catch (Exception e) {
				LOGGER.error("发放红包执行到第"+i+"批次失败" , e);
				//失败 存插入失败的数据
				redpacketMapper.insertBatchSendFailRedpacket(redPapers);
			}
				
		}
		RedpacketAccount redPaperAccount =  redpacketAccountMapper.getSingleSendMoney(request.getActivityId()); //查询单个活动已派发金额 
		Validate.notNull(redPaperAccount,"红包不存在");
		redpacketAccountMapper.updateSendMoney(totalMoney,request.getActivityId()); //更新已派发金额
	}
	
	/**
	 * 发送白名单用户
	 * @author yalin 
	 * @date 2016年6月28日 上午11:24:30  
	 * @param request 
	 */
	private void whiteListbatchSend(SendRedPacketRequest request) {
		
		 Activity activity = ActivityMapper.getActivity(request.getActivityId());
		 Validate.notNull(activity,"红包不存在");
		 
		 String activityName = activity.getName();
		 
		 RedpacketAccount redPaperAccount =  redpacketAccountMapper.getSingleSendMoney(request.getActivityId()); //查询单个活动已派发金额 
		 Validate.notNull(redPaperAccount,"红包不存在");
		 
		 RedpacketType redPaperType = redpacketTypeMapper.getRedPaperTypeById(request.getRedPacketTypeId());
		 Validate.notNull(redPaperType,"红包不存在");
		 
		 ActivityRedpacket activityRedPaper = ActivityRedpacketMapper.getActivityRedPaperByActivityAndRedPaperTypeId(request.getActivityId(), redPaperType.getFid());
	     Validate.notNull(activityRedPaper,"红包描述不存在");
			
		 GenerateRedPacketRule generateRedPacketRule = generateRedPacketRuleService.getGenerateRedPacketRuleByActivityId(request.getActivityId());
		 Validate.notNull(generateRedPacketRule,"红包规则不存在");
		 

		 //当前日期
		 Date date = Calendar.getInstance().getTime();
		 //[{"max":50000,"min":10000,"redPaperType":[{"busType":1,"redpaperMoney":80,"redpaperTypeId":"c5072453fe824a84a0fcc1cf794e9065"}]},{"max":100000,"min":50000,"redPaperType":[{"busType":1,"redpaperMoney":80,"redpaperTypeId":"c5072453fe824a84a0fcc1cf794e9065"},{"busType":1,"redpaperMoney":400,"redpaperTypeId":"85c64dba0b9d4c2f9cf3155d84059d3e"}]},{"max":1.0E7,"min":100000,"redPaperType":[{"busType":1,"redpaperMoney":80,"redpaperTypeId":"c5072453fe824a84a0fcc1cf794e9065"},{"busType":1,"redpaperMoney":400,"redpaperTypeId":"85c64dba0b9d4c2f9cf3155d84059d3e"},{"busType":1,"redpaperMoney":820,"redpaperTypeId":"56d970a70a714c4d8b2058d9f8e9827d"}]}]
		 String redPaperRuleJson = generateRedPacketRule.getRedPaperRuleJson();
		 GenerateRuleDetail generateRuleDetail = JSON.parseObject(redPaperRuleJson, GenerateRuleDetail.class);
		 RedpacketRule curRule = null;
		 List<RedpacketRule> redpacketRuleList = generateRuleDetail.getRedPaperType();
		 for(RedpacketRule redpacketRule : redpacketRuleList){
			 if(redpacketRule.getRedpaperTypeId().equals(request.getRedPacketTypeId())){
				 curRule=redpacketRule;
			 }
			 
		 }
		 Validate.notNull(curRule,"红包类型不存在");

		 //红包规则的红包有效期
		 Date expDate = generateRedPacketRule.getDay()==null?curRule.getValidDate():DateUtils.addDays(date, generateRedPacketRule.getDay());
		 
		 
		 
		 Double totalMoney = 0d;
			
		 Double currentSendMoney = redPaperType.getMoney(); //红包金额
	     List<Redpacket> redPapers  = new LinkedList<Redpacket>();
					
			//查询白名单里所有理财师信息
			List<GrayRelease> grayReleaseList = grayReleaseMapper.queryRedpaperWhiteListUser(); 
					
			for(GrayRelease grayRelease : grayReleaseList){
				
				Validate.isTrue(StringUtils.isNotBlank(grayRelease.getMobile()), "白名单理财师手机不能为空!");
				Validate.notNull(currentSendMoney,"红包金额不能为空");
				
				Validate.isTrue(currentSendMoney>0, "红包金额必须大于0!");
				
				totalMoney =MoneyUtil.add(totalMoney, currentSendMoney);
				
				Redpacket redPaper = new Redpacket();
				redPaper.setFid(GenerateNumberUtils.generateKey());
				redPaper.setInitDate(date);
				redPaper.setUpdateDate(date);
				redPaper.setStatus(1); //未派发
				redPaper.setRedpaperMoney(currentSendMoney); //红包金额
				redPaper.setBusType(curRule.getBusType());
				redPaper.setExpireDate(expDate);
				
				redPaper.setActivityId(request.getActivityId());
				redPaper.setActivityName(activityName);
				
				redPaper.setRedPaperType(redPaperType.getFid());
				redPaper.setShowName(activityRedPaper.getShowName());
				redPaper.setUseRemark(activityRedPaper.getUseRemark());
				

				//设置理财师信息
				redPaper.setSaleUserId(grayRelease.getUserId());
				redPaper.setSaleUserName(grayRelease.getUserName());
				redPaper.setSaleUserMobile(grayRelease.getMobile());
				redPaper.setOperator(request.getLoginUserName()); //发放红包操作人
				
				redPapers.add(redPaper);
									
			}
			
			//发红包
			try {
				if(grayReleaseList.size() > 0){
					redpacketMapper.insertBatchRedpacket(redPapers); //批量发送红包
					redpacketAccountMapper.updateSendMoney(totalMoney,request.getActivityId()); //更新已派发金额
				}
			 	//写日志 存插入成功的数据
			} catch (Exception e) {
				LOGGER.error("发放白名单红包失败" , e);
				redpacketMapper.insertBatchSendFailRedpacket(redPapers);
				//失败 存插入失败的数据
			}
				
		
	}
	
	public void insertBatchWhiteListUser(SendRedPacketRequest request) throws Exception{
		try{
			whiteListbatchSend(request);//白名单用户发送
		}catch(Exception exception){
			LOGGER.error("发放白名单红包出现异常!", exception);
			throw exception;
		}
	}


	@Override
	public DataTableReturn getRedpaperEveryDayList(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		Page<RedpacketCal> page = new Page<RedpacketCal>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<RedpacketCal> list = redpacketMapper.getRedpaperEveryDayList(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		
		return tableReturn;
	}


	@Override
	public Redpaper queryRedpaperInfo(SendRedPacketRequest request) {
		return redpacketMapper.queryRedpaperInfo(request);
	}


	@Override
	public List<ProductUseCondition> queryProductUseCondition(SendRedPacketRequest request) {
		return redpacketMapper.queryProductUseCondition(request);
	}


	@Override
	public RedpacketCal queryRedpaperCal(SendRedPacketRequest request) {
		return redpacketMapper.queryRedpaperCal(request);
	}
	@Override
	public ProductUseCondition queryAllUseRuleByActivityId(SendRedPacketRequest request){
		 ProductUseCondition pc = new ProductUseCondition();
		 List<Product> pcs = new ArrayList<Product>();
		 List<UseRedPacketRule> redpacketUseRule = useRedPacketRuleService.queryAllUseRuleByActivityId(request.getActivityId());
		 //投资金额限制 投资用户限制(是否首次投资) 投资产品限制(按期限或者编号)
		 if(redpacketUseRule.size() > 0){
			 for(UseRedPacketRule urr:redpacketUseRule){
				 //json转换
				 List<UseRuleDetail> redpacketUserCondition = JSON.parseArray(urr.getRedPaperRuleJson(), UseRuleDetail.class);
				// List<UseRuleDetail> redpacketUserCondition = useRuleDetail.getRedpacketUserCondition();
				 for(UseRuleDetail ur:redpacketUserCondition){
					//投资用户限制 0= 不限制|1=首次投资用户
					 if(ur.getInvestUser() == 1){
						 pc.setIsFirstInvest(1);
					 }else{
						 pc.setIsFirstInvest(0);
					 }
					 
					 //投资金额限制
					 if(ur.getMin() == 0 || ur.getMax() == 0){
						 
					 }
				 }
				 //不限产品
				 if(urr.getUseCondition() == 1000){
					 pc.setProductRemark("产品不限");
					 //按产品编号
				 }else if(urr.getUseCondition() == 1001){
					 Product p  =  new Product();
					 p.setProductId(urr.getProductId());
					 p.setProductName(urr.getProductName());
					 pcs.add(p);
					 //按产品期限
				 }else if(urr.getUseCondition() == 1002 || urr.getUseCondition() == 1003){
					 Product p  =  new Product();
					 p.setProductId(urr.getProductId());
					 p.setProductName(urr.getProductName());
					 p.setProductDeadLine(urr.getProductDeadline());
					 pcs.add(p);
				 }
			 }
		 }
		 return pc;
	}


	@Override
	public void queryRedpaperInfo(RedPacketInfoRequest redpacketInfo,String activityId, String redpacketTypeId) throws Exception {
		
		try{
			RedpacketType redpacketType = redpacketTypeMapper.getRedPaperTypeById(redpacketTypeId);
			Validate.notNull(redpacketType, redpacketTypeId + "红包类型不存在");
			ActivityRedpacket activityRedpacket = activityRedpacketService.getActivityRedPaperByActivityAndRedPaperTypeId(activityId, redpacketTypeId);
			Validate.notNull(activityRedpacket,activityId +  "红包活动描述不存在");
			
			redpacketInfo.setName(activityRedpacket.getShowName());
			redpacketInfo.setRemark(activityRedpacket.getUseRemark());
			redpacketInfo.setMoney(redpacketType.getMoney());
			
			GenerateRedPacketRule generateRedPacketRule =generateRedPacketRuleService.getGenerateRedPacketRuleByActivityId(activityId);
			Validate.notNull(generateRedPacketRule, activityId + "活动规则不存在");
			String generateJson = generateRedPacketRule.getRedPaperRuleJson();
			Validate.isTrue(StringUtils.isNotBlank(generateJson),activityId + " 活动生成规则不存在");
			GenerateRuleDetail generateRuleDetail = JSON.parseObject(generateJson, GenerateRuleDetail.class);
			Validate.notNull(generateRuleDetail,activityId +  "活动 规则json"+generateJson+"不存在");
			List<RedpacketRule> redpacketRules = generateRuleDetail.getRedPaperType();
			Validate.notEmpty(redpacketRules,activityId +  "活动, 规则"+generateJson+", redpacketRules不存在");
			for (RedpacketRule redpacketRule : generateRuleDetail.getRedPaperType()) {
				if(ObjectUtils.equals(redpacketRule.getRedpaperTypeId(), redpacketTypeId)){
					redpacketInfo.setValidDate(redpacketRule.getValidDate());
					break;
				}
			}
			useRedPacketRuleService.getRedPacketUseRule(redpacketInfo, activityId);
		}catch(Exception e){
			LOGGER.error("queryRedpaperInfo exception ,activityId={},redpacketTypeId={}", new Object[]{activityId,redpacketTypeId,e});
			throw e;
		}
	}


	@Override
	public void updateRedpaperRule(String activityId, String redpaperTypeId,String pids) throws Exception {
		useRedPacketRuleService.updateUseRedPacketRule(activityId, pids);
	}
	
	
	public String insertImportSend(SendRedPacketRequest request,List<ExcelModel> datas){
		
		if(datas==null||datas.isEmpty()){
			 return "发放理财师用户不能为空";
		 }
		 Activity activity = ActivityMapper.getActivity(request.getActivityId());
		 Validate.notNull(activity,"红包不存在");
		 
		 String activityName = activity.getName();
		 
		 
		 RedpacketType redPaperType = redpacketTypeMapper.getRedPaperTypeById(request.getRedPacketTypeId());
		 Validate.notNull(redPaperType,"红包不存在");
		 
		 ActivityRedpacket activityRedPaper = ActivityRedpacketMapper.getActivityRedPaperByActivityAndRedPaperTypeId(request.getActivityId(), redPaperType.getFid());
	     Validate.notNull(activityRedPaper,"红包描述不存在");
			
		 GenerateRedPacketRule generateRedPacketRule = generateRedPacketRuleService.getGenerateRedPacketRuleByActivityId(request.getActivityId());
		 Validate.notNull(generateRedPacketRule,"红包规则不存在");
		 

		 //当前日期
		 Date date = Calendar.getInstance().getTime();
		 //[{"max":50000,"min":10000,"redPaperType":[{"busType":1,"redpaperMoney":80,"redpaperTypeId":"c5072453fe824a84a0fcc1cf794e9065"}]},{"max":100000,"min":50000,"redPaperType":[{"busType":1,"redpaperMoney":80,"redpaperTypeId":"c5072453fe824a84a0fcc1cf794e9065"},{"busType":1,"redpaperMoney":400,"redpaperTypeId":"85c64dba0b9d4c2f9cf3155d84059d3e"}]},{"max":1.0E7,"min":100000,"redPaperType":[{"busType":1,"redpaperMoney":80,"redpaperTypeId":"c5072453fe824a84a0fcc1cf794e9065"},{"busType":1,"redpaperMoney":400,"redpaperTypeId":"85c64dba0b9d4c2f9cf3155d84059d3e"},{"busType":1,"redpaperMoney":820,"redpaperTypeId":"56d970a70a714c4d8b2058d9f8e9827d"}]}]
		 String redPaperRuleJson = generateRedPacketRule.getRedPaperRuleJson();
		 GenerateRuleDetail generateRuleDetail = JSON.parseObject(redPaperRuleJson, GenerateRuleDetail.class);
		 RedpacketRule curRule = null;
		 List<RedpacketRule> redpacketRuleList = generateRuleDetail.getRedPaperType();
		 for(RedpacketRule redpacketRule : redpacketRuleList){
			 if(redpacketRule.getRedpaperTypeId().equals(request.getRedPacketTypeId())){
				 curRule=redpacketRule;
			 }
			 
		 }
		 Validate.notNull(curRule,"红包不存在");

		 //红包规则的红包有效期
		 Date expDate = generateRedPacketRule.getDay()==null?curRule.getValidDate():DateUtils.addDays(date, generateRedPacketRule.getDay());
		 
		 Double totalMoney = 0d;
		
		 
		 int userCounts = datas.size();
		 int pageSize=200;
		 int count=0;
		 if (userCounts % 200 == 0) {
			count =  (userCounts / pageSize);
		} else {
			count =  (userCounts / pageSize) + 1;
		}
		Double currentSendMoney = redPaperType.getMoney(); //红包金额

		Validate.notNull(currentSendMoney,"红包金额不能为空");
		Validate.isTrue(currentSendMoney>0, "红包金额必须大于0!");
		
		StringBuilder sb = new StringBuilder();
		Redpacket redPaper=null; 
		try{
			for (int i = 0; i < count; i++) {
				LOGGER.info("导入发放理财师红包: 200条/批次  执行到: 第"+ i +"批次");
				int from = i*pageSize;
				int to = from+pageSize;
				if(to >= datas.size()){
					to = datas.size();
					
				}
				if(from >= to){
					from = to;
				}
				if(from >=400){
					if(from % 400 == 0){
						expDate = DateUtils.addMinutes(expDate, -2); //2分钟前的时间
					}
				}
				
				List<ExcelModel> users = datas.subList(from,to);
				List<Redpacket> redPapers  = new LinkedList<Redpacket>();
				List<Redpacket> errorPapers  = new LinkedList<Redpacket>();
				for (ExcelModel model : users) {
					try{
						//查询是否存在此理财师用户
						SaleUserInfo saleUserInfo = saleUserInfoService.getSaleUserInfoByMobile(model.getMobile());
						if(saleUserInfo == null){
							redPaper = new Redpacket();
							redPaper.setFid(GenerateNumberUtils.generateKey());
							redPaper.setInitDate(date);
							redPaper.setUpdateDate(date);
							redPaper.setStatus(1); //未派发
							redPaper.setRedpaperMoney(currentSendMoney); //红包金额
							redPaper.setBusType(curRule.getBusType());
							redPaper.setExpireDate(expDate);
							
							redPaper.setActivityId(request.getActivityId());
							redPaper.setActivityName(activityName);
							
							redPaper.setRedPaperType(redPaperType.getFid());
							redPaper.setShowName(activityRedPaper.getShowName());
							redPaper.setUseRemark(activityRedPaper.getUseRemark()+" Excel导入失败");
							
							redPaper.setUserMobile(model.getMobile());
							redPaper.setOperator(request.getLoginUserName()); //发放红包操作人
							errorPapers.add(redPaper);
							sb.append(" "+model.getMobile()+"理财师用户不存在\n");
						}
						else
						{
						
							totalMoney =MoneyUtil.add(totalMoney, currentSendMoney);
							
							redPaper = new Redpacket();
							redPaper.setFid(GenerateNumberUtils.generateKey());
							redPaper.setInitDate(date);
							redPaper.setUpdateDate(date);
							redPaper.setStatus(1); //未派发
							redPaper.setRedpaperMoney(currentSendMoney); //红包金额
							redPaper.setBusType(curRule.getBusType());
							redPaper.setExpireDate(expDate);
							
							redPaper.setActivityId(request.getActivityId());
							redPaper.setActivityName(activityName);
							
							redPaper.setRedPaperType(redPaperType.getFid());
							redPaper.setShowName(activityRedPaper.getShowName());
							redPaper.setUseRemark(activityRedPaper.getUseRemark());
							
		
							//设置理财师用户信息
							redPaper.setUserId(saleUserInfo.getNumber());
							redPaper.setUserName(saleUserInfo.getName());
							redPaper.setUserMobile(saleUserInfo.getMobile());
							redPaper.setOperator(request.getLoginUserName()); //发放红包操作人
							redPapers.add(redPaper);
						}
						
					}catch(Exception e){
						sb.append(" "+model.getMobile()+"理财师用户红包发放失败");
						continue;
					}
				}
				users=null;
				//发红包
				try {
					if(!redPapers.isEmpty()){
						redpacketMapper.insertBatchRedpacket(redPapers); //批量生成红包 每次插入200条
					}
					if(!errorPapers.isEmpty()){
						redpacketMapper.insertBatchSendFailRedpacket(errorPapers); //插入不是理财师的错误记录
					}
				 	//写日志 存插入成功的数据
				} catch (Exception e) {
					LOGGER.error("导入理财师用户发放红包执行到第"+i+"批次失败" , e);
				}
			}
		}catch(Exception e){
			LOGGER.error("导入理财师用户发放红包失败" , e);
		}
		RedpacketAccount redPaperAccount =  redpacketAccountMapper.getSingleSendMoney(request.getActivityId()); //查询单个活动已派发金额 
		Validate.notNull(redPaperAccount,"红包不存在");
		redpacketAccountMapper.updateSendMoney(totalMoney,request.getActivityId()); //更新已派发金额
		return sb.toString();
		
		
	}


	@Override
	public boolean isSendRedpaper(String activityId, String redpaperTypeId) {
		System.out.println(activityId);
		return redpacketMapper.isSendRedpaper(activityId, redpaperTypeId)>0;
	}


	

	
	public String insertImportSendInvester(SendRedPacketRequest request,List<ExcelModel> datas){
		 if(datas==null||datas.isEmpty()){
			 return "发放金服用户不能为空";
		 }
		 Activity activity = ActivityMapper.getActivity(request.getActivityId());
		 Validate.notNull(activity,"红包不存在");
		 
		 String activityName = activity.getName();
		 
		 
		 RedpacketType redPaperType = redpacketTypeMapper.getRedPaperTypeById(request.getRedPacketTypeId());
		 Validate.notNull(redPaperType,"红包不存在");
		 
		 ActivityRedpacket activityRedPaper = ActivityRedpacketMapper.getActivityRedPaperByActivityAndRedPaperTypeId(request.getActivityId(), redPaperType.getFid());
	     Validate.notNull(activityRedPaper,"红包描述不存在");
			
		 GenerateRedPacketRule generateRedPacketRule = generateRedPacketRuleService.getGenerateRedPacketRuleByActivityId(request.getActivityId());
		 Validate.notNull(generateRedPacketRule,"红包规则不存在");
		 

		 //当前日期
		 Date date = Calendar.getInstance().getTime();
		 //[{"max":50000,"min":10000,"redPaperType":[{"busType":1,"redpaperMoney":80,"redpaperTypeId":"c5072453fe824a84a0fcc1cf794e9065"}]},{"max":100000,"min":50000,"redPaperType":[{"busType":1,"redpaperMoney":80,"redpaperTypeId":"c5072453fe824a84a0fcc1cf794e9065"},{"busType":1,"redpaperMoney":400,"redpaperTypeId":"85c64dba0b9d4c2f9cf3155d84059d3e"}]},{"max":1.0E7,"min":100000,"redPaperType":[{"busType":1,"redpaperMoney":80,"redpaperTypeId":"c5072453fe824a84a0fcc1cf794e9065"},{"busType":1,"redpaperMoney":400,"redpaperTypeId":"85c64dba0b9d4c2f9cf3155d84059d3e"},{"busType":1,"redpaperMoney":820,"redpaperTypeId":"56d970a70a714c4d8b2058d9f8e9827d"}]}]
		 String redPaperRuleJson = generateRedPacketRule.getRedPaperRuleJson();
		 GenerateRuleDetail generateRuleDetail = JSON.parseObject(redPaperRuleJson, GenerateRuleDetail.class);
		 RedpacketRule curRule = null;
		 List<RedpacketRule> redpacketRuleList = generateRuleDetail.getRedPaperType();
		 for(RedpacketRule redpacketRule : redpacketRuleList){
			 if(redpacketRule.getRedpaperTypeId().equals(request.getRedPacketTypeId())){
				 curRule=redpacketRule;
			 }
			 
		 }
		 Validate.notNull(curRule,"红包不存在");

		 //红包规则的红包有效期
		 Date expDate = generateRedPacketRule.getDay()==null?curRule.getValidDate():DateUtils.addDays(date, generateRedPacketRule.getDay());
		 
		 Double totalMoney = 0d;
		
		 
		 int userCounts = datas.size();
		 int pageSize=200;
		 int count=0;
		 if (userCounts % 200 == 0) {
			count =  (userCounts / pageSize);
		} else {
			count =  (userCounts / pageSize) + 1;
		}
		Double currentSendMoney = redPaperType.getMoney(); //红包金额

		Validate.notNull(currentSendMoney,"红包金额不能为空");
		Validate.isTrue(currentSendMoney>0, "红包金额必须大于0!");
		
		StringBuilder sb = new StringBuilder();
		Redpacket redPaper=null; 
		try{
			for (int i = 0; i < count; i++) {
				LOGGER.info("导入发放金服红包: 200条/批次  执行到: 第"+ i +"批次");
				int from = i*pageSize;
				int to = from+ pageSize;
				if(to >= datas.size()){
					to = datas.size();
					
				}
				if(from >= to){
					from = to;
				}
				if(from >=400 ){
					if(from % 400 == 0){
						expDate = DateUtils.addMinutes(expDate, -2); //2分钟前的时间
					}
				}
				
				List<ExcelModel> users = datas.subList(from,to);
				List<Redpacket> redPapers  = new LinkedList<Redpacket>();
				List<Redpacket> errorPapers  = new LinkedList<Redpacket>();
				for (ExcelModel model : users) {
					try{
						//查询是否存在此金服用户
						InvestorUserInfo investor = investorUserInfoService.queryInvestorUserInfoByMobile(model.getMobile());
						if(investor == null){
							redPaper = new Redpacket();
							redPaper.setFid(GenerateNumberUtils.generateKey());
							redPaper.setInitDate(date);
							redPaper.setUpdateDate(date);
							redPaper.setStatus(1); //未派发
							redPaper.setRedpaperMoney(currentSendMoney); //红包金额
							redPaper.setBusType(curRule.getBusType());
							redPaper.setExpireDate(expDate);
							
							redPaper.setActivityId(request.getActivityId());
							redPaper.setActivityName(activityName);
							
							redPaper.setRedPaperType(redPaperType.getFid());
							redPaper.setShowName(activityRedPaper.getShowName());
							redPaper.setUseRemark(activityRedPaper.getUseRemark()+" Excel导入失败");
							
							redPaper.setUserMobile(model.getMobile());
							redPaper.setOperator(request.getLoginUserName()); //发放红包操作人
							errorPapers.add(redPaper);
							sb.append(" "+model.getMobile()+"金服用户不存在\n");
						}
						else
						{
						
							totalMoney =MoneyUtil.add(totalMoney, currentSendMoney);
							
							redPaper = new Redpacket();
							redPaper.setFid(GenerateNumberUtils.generateKey());
							redPaper.setInitDate(date);
							redPaper.setUpdateDate(date);
							redPaper.setStatus(2); //未兑换
							redPaper.setRedpaperMoney(currentSendMoney); //红包金额
							redPaper.setBusType(curRule.getBusType());
							redPaper.setExpireDate(expDate);
							
							redPaper.setActivityId(request.getActivityId());
							redPaper.setActivityName(activityName);
							
							redPaper.setRedPaperType(redPaperType.getFid());
							redPaper.setShowName(activityRedPaper.getShowName());
							redPaper.setUseRemark(activityRedPaper.getUseRemark());
							
		
							//设置用户信息
							redPaper.setUserId(investor.getUserId());
							redPaper.setUserName(investor.getUserName());
							redPaper.setUserMobile(investor.getMobile());
							redPaper.setOperator(request.getLoginUserName()); //发放红包操作人
							redPapers.add(redPaper);
						}
						
					}catch(Exception e){
						sb.append(" "+model.getMobile()+"金服用户红包发放失败");
						continue;
					}
				}
				users=null;
				//发红包
				try {
					if(!redPapers.isEmpty()){
						redpacketMapper.insertBatchRedpacket(redPapers); //批量生成红包 每次插入200条
					}
					if(!errorPapers.isEmpty()){
						redpacketMapper.insertBatchSendFailRedpacket(errorPapers); //插入不是理财师的错误记录
					}
				 	//写日志 存插入成功的数据
				} catch (Exception e) {
					LOGGER.error("导入金服用户发放红包执行到第"+i+"批次失败" , e);
				}
			}
		}catch(Exception e){
			LOGGER.error("导入金服用户发放红包失败" , e);
		}
		RedpacketAccount redPaperAccount =  redpacketAccountMapper.getSingleSendMoney(request.getActivityId()); //查询单个活动已派发金额 
		Validate.notNull(redPaperAccount,"红包不存在");
		redpacketAccountMapper.updateSendMoney(totalMoney,request.getActivityId()); //更新已派发金额
		return sb.toString();
		 
		 
	}
	
}
