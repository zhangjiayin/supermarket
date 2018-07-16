package com.linkwee.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CimLeaderFeeMapper;
import com.linkwee.web.dao.CimLeaderTreeMapper;
import com.linkwee.web.dao.CrmCfplannerMapper;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.cim.CimLeaderFee;
import com.linkwee.web.model.crm.CimLeaderTree;
import com.linkwee.web.service.CimLeaderFeeService;
import com.linkwee.web.service.CrmCfplannerService;
import com.linkwee.web.service.CrmInvestorService;
import com.linkwee.web.service.SysConfigService;


 /**
 * 
 * @描述：CimLeaderFeeService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年02月28日 10:46:49
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimLeaderFeeService")//InvestRecordAware
public class CimLeaderFeeServiceImpl extends GenericServiceImpl<CimLeaderFee, Long> implements CimLeaderFeeService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimLeaderFeeServiceImpl.class);
	
	@Resource
	private CimLeaderFeeMapper cimLeaderFeeMapper;
	
	@Resource
	private CrmCfplannerMapper crmCfplannerMapper;
	
	@Resource
	private SysConfigService sysConfigService;
	
	@Resource
	private CrmCfplannerService crmCfplannerService;
	
	@Resource
	private CimLeaderTreeMapper cimLeaderTreeMapper;
	
	@Resource
	private CimLeaderFeeService cimLeaderFeeService;
	
	@Resource
	private CrmInvestorService crmInvestorService;
	
	@Override
    public GenericDao<CimLeaderFee, Long> getDao() {
        return cimLeaderFeeMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimLeaderFee -- 排序和模糊查询 ");
		Page<CimLeaderFee> page = new Page<CimLeaderFee>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimLeaderFee> list = this.cimLeaderFeeMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
    
	/*@Override
	public void investRecordProcess(InvestRecordWrapper inRecord) throws Exception {
		String swicth = sysConfigService.getValuesByKey(SysConfigConstant.LEADER_PROFIT_SWICTH);
		if(swicth!=null&&"ON".equals(swicth)){
			CimLeaderFee ldFee = new CimLeaderFee();
			ldFee.setBizTime(inRecord.getInvestTime());
			ldFee.setCreateTime(new Date());
			ldFee.setEndTime(inRecord.getEndTime());//还款日期
			BigDecimal yearAmount = CalculateTools.yearpurAmountCompute(inRecord.getInvestAmt(), inRecord.getProductDays());
			ldFee.setLeaderProfit(CalculateTools.feeAmountCompute(yearAmount, 1.0));
			ldFee.setInvestId(inRecord.getBizId());
			ldFee.setInvestRecordNo(inRecord.getInvestId());
			SimpleDateFormat simple = new SimpleDateFormat("yyyyMM");
			ldFee.setMonth(simple.format(inRecord.getInvestTime()));//"201702"
			ldFee.setUserId(inRecord.getUserId());
			ldFee.setStartTime(inRecord.getInvestTime());
			ldFee.setInvestAmt(inRecord.getInvestAmt());
			ldFee.setProductId(inRecord.getProductId());
	//		ldFee.setThirdProductId("22015");
			ldFee.setPlatfrom(inRecord.getProductOrgId());
			CrmInvestor invest = crmInvestorService.queryInvestorByUserId(inRecord.getUserId());
			ldFee.setRemark(invest.getUserName()+"("+RandomTextCreator.encrypTion(invest.getMobile())+")购买了 "+inRecord.getOrgName()
			               +inRecord.getProductName()+",金额"+NumberUtils.getFormat(inRecord.getInvestAmt(), "0.00")+"元");//张三 186****6372购买了 五星双季享 深圳华南国际资产收益权2017030902，金额10000.00元
			CrmCfplanner cfplanner1 = crmCfplannerService.queryCfplannerByInvestor(inRecord.getUserId());//当前理财师
			
//			CrmCfplanner cfplanner3 =crmCfplannerService.queryCfplannerByUserId(cfplanner2.getUserId());//上上级理财师
			ldFee.setCfplanner(cfplanner1.getUserId());
			
			//cfp1  设置leader奖励的归属 cfp1是独立的计算rootID,则发给他自己
			CimLeaderTree zjtree = new CimLeaderTree();
			zjtree.setRootId(cfplanner1.getUserId());
			List<CimLeaderTree> zjtreeList = cimLeaderTreeMapper.selectByCondition(zjtree);
			if(zjtreeList!=null&&zjtreeList.size()>0){
				 ldFee.setSonOwner(querySonOwnerId(cfplanner1.getUserId(),zjtreeList.get(0).getRootId()));
				 ldFee.setOwnerId(zjtreeList.get(0).getRootId());//当前理财师 cfp1
				 cimLeaderFeeMapper.insertSelective(ldFee);
				 return;
			}
			
            //cfp2是空的即理财师没有了上级了
			if(cfplanner1.getParentId()==null){return;}
			
			//cfp1是否有上级rootId 先在leader树里面查询是否有上级rootID select *　from tcim_leader_tree  where user_id=#{userId} cfplanner.getUserId()
			CimLeaderTree tree = new CimLeaderTree();
			tree.setUserId(cfplanner1.getUserId());
			tree.setIsCalc(1);
			List<CimLeaderTree> treeList = cimLeaderTreeMapper.selectByCondition(tree);
			//cfp的上面是否有rootID 可能有多个层级 ldFee.setOwnerid()
			if(treeList!=null&&treeList.size()>0){
				ldFee.setOwnerId(treeList.get(0).getRootId());//cfp1归属的rootID 可能是cfp2 cfp3 cfp4...
				
				
//				CrmCfplanner cfplanner2 =crmCfplannerService.queryCfplannerByUserId(cfplanner1.getParentId());
				
				boolean flag = cimLeaderFeeService.isFiveAddOneCondition(cfplanner1.getParentId());
				
				if(flag&&!cfplanner1.getParentId().equals(treeList.get(0).getRootId())){
					//判断cfp2是不是有树，没有建立,砍分枝,该ownerID
					CimLeaderTree zjtree2 = new CimLeaderTree();
					zjtree2.setRootId(cfplanner1.getParentId());
					List<CimLeaderTree> zjtreeList2 = cimLeaderTreeMapper.selectByCondition(zjtree2);
					if(zjtreeList2!=null&&zjtreeList2.size()==0){
						testCreateTree(cfplanner1.getParentId());
						//cp3里面砍掉cp2分枝 update tcim_leader_tree set is_calc=0,is_onlypay=1 where user_id =#{userId} and root_id =#{rootId}
						CimLeaderTree cimLeaderTree = new CimLeaderTree();
						cimLeaderTree.setRootId(treeList.get(0).getRootId());
						cimLeaderTree.setUserId(cfplanner1.getParentId());
						cimLeaderTreeMapper.updateLeaderTreeIsOnelyPay(cimLeaderTree);
						//递归将下级不计入树的计算is_calc=0
						updateIsOnlyPay(treeList.get(0).getRootId(),cfplanner1.getParentId());
						//砍树后更新leader_fee里面的数据
//						cimLeaderFeeMapper.updateLeaderProfitOwnerId(treeList.get(0).getRootId());
						ldFee.setOwnerId(cfplanner1.getParentId());
					}
				}
				ldFee.setSonOwner(querySonOwnerId(cfplanner1.getUserId(),treeList.get(0).getRootId()));
				cimLeaderFeeMapper.insertSelective(ldFee);
				
			}else{
				//直接生成树 则判断是否满足五加一条件
				boolean flag = cimLeaderFeeService.isFiveAddOneCondition(cfplanner1.getParentId());
				if(flag){
					//新建一棵树
					testCreateTree(cfplanner1.getParentId());
					//将新生成的树 ,下面客户购买的leader记录  重新分配owner_id is_pay=0
					cimLeaderFeeMapper.updateLeaderProfitOwnerId(cfplanner1.getParentId());
					ldFee.setSonOwner(querySonOwnerId(cfplanner1.getUserId(),cfplanner1.getParentId()));
					ldFee.setOwnerId(cfplanner1.getParentId());//上级理财师 cfp2
					cimLeaderFeeMapper.insertSelective(ldFee);
				}else{
					cimLeaderFeeMapper.insertSelective(ldFee);
				}
			}
		}
	}*/
	
	
  public String querySonOwnerId(String userId,String ownerId){
		if(ownerId==null){return null;}
		String returnId = null;
		CimLeaderTree tree = new CimLeaderTree();
		tree.setUserId(userId);
		tree.setIsCalc(1);
		tree.setIsOnlypay(0);
		tree.setIsTree(1);
		List<CimLeaderTree> treeList = cimLeaderTreeMapper.selectByCondition(tree);
		if(treeList.size()>0){//有树的情况
			if(treeList.get(0).getIsDirect()==1){
				return treeList.get(0).getUserId();
			}else if(!ownerId.equals(treeList.get(0).getCfplanner())){
				returnId = querySonOwnerId(treeList.get(0).getCfplanner(),ownerId);
			}
		}else{//没有树的情况
			return null;
		}
		return returnId;
	}
	
	@Override
	public void changeLeaderTree(String mobile, String recommendCode) {
		//邀请人
		CrmCfplanner cfp =crmCfplannerService.queryCfplannerByInvestMobile(mobile);//上级理财师
		
		//第一步
		boolean flag = cimLeaderFeeService.isFiveAddOneCondition(cfp.getParentId());
//		if(!flag){return;}
		
		CimLeaderTree tree = new CimLeaderTree();
		tree.setRootId(cfp.getParentId());
		List<CimLeaderTree> treeList = cimLeaderTreeMapper.selectByCondition(tree);
		//自己理财师是树
		if(treeList.size()>0){
			CimLeaderTree ltree = new CimLeaderTree();
			ltree.setRootId(cfp.getParentId());
			ltree.setCfplanner(cfp.getParentId());
			ltree.setUserId(cfp.getUserId());
			ltree.setIsDirect(1);//直接下级理财师
			ltree.setIsOnlypay(0);
			ltree.setIsCalc(1);
			cimLeaderTreeMapper.insertSelective(ltree);
			return;
		}
		
		//第二步  邀请人生成树 前面flag=true
		
		//属于别人的树 判断该树下面是否有新的树
		CimLeaderTree tree1 = new CimLeaderTree();
		tree1.setUserId(cfp.getParentId());
		tree1.setIsCalc(1);
		List<CimLeaderTree> treeList1 = cimLeaderTreeMapper.selectByCondition(tree1);
        //属于别人的树 
		if(treeList1!=null&&treeList1.size()>0){
			if(flag&&!cfp.getParentId().equals(treeList1.get(0).getRootId())){//满足5+1 且是邀请人上级
				testCreateTree(cfp.getParentId());
				//在别人树里面独立出来update tcim_leader_tree set is_calc=0,is_onlypay=1 where user_id =#{userId} and root_id =#{rootId}
				CimLeaderTree cimLeaderTree = new CimLeaderTree();
				cimLeaderTree.setRootId(treeList1.get(0).getRootId());
				cimLeaderTree.setUserId(cfp.getParentId());//?此处验证自己是否已改
				cimLeaderTreeMapper.updateLeaderTreeIsOnelyPay(cimLeaderTree);//在原来树里面标记已独立数据
				//砍树后更新leader_fee里面的数据
//				cimLeaderFeeMapper.updateLeaderProfitOwnerId(treeList.get(0).getRootId());
				//还有一种情况没有做：就是砍掉树之后判断老树是否还成立
			}else{
				CimLeaderTree ldtree = new CimLeaderTree();
				ldtree.setRootId(treeList1.get(0).getRootId());
				ldtree.setCfplanner(cfp.getParentId());
				ldtree.setUserId(cfp.getUserId());
				ldtree.setIsDirect(0);
				ldtree.setIsOnlypay(0);
				ldtree.setIsCalc(1);
				cimLeaderTreeMapper.insertSelective(ldtree);
			}
			
		}else if(flag){
			testCreateTree(cfp.getParentId());
		}
		//5+1成立后，之前的数据owner、sonOwner
	}
	
	//1.被邀请上级理财师
//	CrmCfplanner cfplanner2 =crmCfplannerService.queryCfplannerByUserId(cfplanner1.getUserId());//上级理财师
	
//   boolean flag = cimLeaderFeeService.isFiveAddOneCondition(cfplanner2.getUserId());
	
//	CimLeaderTree tree = new CimLeaderTree();
//	tree.setRootId(cfplanner2.getUserId());
//	List<CimLeaderTree> treeList = cimLeaderTreeMapper.selectByCondition(tree);
	//自己不是树  判断满足条件  直接生成树
//	if(treeList.size()==0&&flag){
	    //生成树
//	    testCreateTree(cfplanner2.getUserId());
//	}
	
	
	//属于别人的树 判断该树下面是否有新的树
	
//	CimLeaderTree tree1 = new CimLeaderTree();
//	tree1.setUserId(cfplanner2.getUserId());
//	List<CimLeaderTree> treeList1 = cimLeaderTreeMapper.selectByCondition(tree);
	
//	CrmCfplanner cfplanner3 =crmCfplannerService.queryCfplannerByUserId(cfplanner2.getUserId());
//	if(cfplanner3==null){return;}
//	
//	if(ttreeList1!=null&&treeList.size()>0){
//		if(flag&&cfplanner3.getUserId().equals(treeList1.get(0).getRootId())){//满足5+1 且是上两级
//			//将下级分枝重新生成树 ldFee.setOwnerid()  
//			testCreateTree(c.getUserId());
//			//cp3里面砍掉cp2分枝 update tcim_leader_tree set is_calc=0,is_onlypay=1 where user_id =#{userId} and root_id =#{rootId}
//			CimLeaderTree cimLeaderTree = new CimLeaderTree();
//			cimLeaderTree.setRootId(treeList.get(0).getRootId());
//			cimLeaderTree.setUserId(c.getUserId());//?此处验证自己是否已改
//			cimLeaderTreeMapper.updateLeaderTreeIsOnelyPay(cimLeaderTree);//在原来树里面标记已独立数据
//			//砍树后更新leader_fee里面的数据
//			cimLeaderFeeMapper.updateLeaderProfitOwnerId(treeList.get(0).getRootId());
//		}
//		
//	}
	/**
	 * 递归将下级数据置is_calc=0  (用户ID下面的数据是否核算到此树0=否1=是)
	 * */
	public void updateIsOnlyPay(String rootId,String userId){
		List<CrmCfplanner>  cfpList = crmCfplannerMapper.queryLowerLevelOne(userId);
		for(CrmCfplanner c : cfpList){
			CimLeaderTree cimLeaderTree = new CimLeaderTree();
			cimLeaderTree.setRootId(rootId);
			cimLeaderTree.setUserId(c.getUserId());
			cimLeaderTree.setIsCalc(0);
			cimLeaderTreeMapper.updateLeaderTreeIsCalc(cimLeaderTree);
			updateIsOnlyPay(rootId,c.getUserId());
		}
	}
	
	
	/**
	 * 重新建立leaderTree
	 * */
   public void testCreateTree(String userId){
		List<CrmCfplanner>  cfpList = crmCfplannerMapper.queryLowerLevelOne(userId);
		
		for(CrmCfplanner c : cfpList){
			boolean flag = cimLeaderFeeService.isFiveAddOneCondition(c.getUserId());
			CimLeaderTree tree = new CimLeaderTree();
			tree.setRootId(userId);
			tree.setCfplanner(userId);
			tree.setUserId(c.getUserId());
			tree.setIsDirect(1);//直接下级理财师
			tree.setIsOnlypay(flag==true?1:0);
			tree.setIsCalc(flag==true?0:1);
			if(flag){
				testCreateTree(c.getUserId());
			}
			cimLeaderTreeMapper.insertSelective(tree);
			queryDirctCfp(c.getUserId(),userId,flag);
		}
    }
  
    /**
	 * 递归生成间接下级理财师
	 * */
	public void queryDirctCfp(String userId,String oldUserId,boolean flag){
		List<CrmCfplanner>  cfpList = crmCfplannerMapper.queryLowerLevelOne(userId);
		for(CrmCfplanner cfp : cfpList){
			if(cfp.getParentId()!=null&&!userId.equals(cfp.getUserId())){
				CimLeaderTree tree = new CimLeaderTree();
				tree.setRootId(oldUserId);
				tree.setCfplanner(userId);
				tree.setUserId(cfp.getUserId());
				tree.setIsDirect(0);//间接下级理财师
				tree.setIsCalc(flag==true?0:1);
				cimLeaderTreeMapper.insertSelective(tree);
				queryDirctCfp(cfp.getUserId(),oldUserId,flag);
			}else{
				continue;
			}
		}
	}

	@Override
	public boolean isFiveAddOneCondition(String userId) {
		//当前理财师下面是否有5个及以上直属理财师
		List<CrmCfplanner>  cfpList = crmCfplannerMapper.queryLowerLevelOne(userId);
		if(cfpList!=null&&cfpList.size()<5){
			return false;
		}
		//其中1个直属理财师年化业绩达到100元
		Double amount = cimLeaderFeeMapper.queryUnderCfpYearInvestAmount(userId);
		if(amount!=null&&amount<100){
			return false;
		}
		return true;
	}

	@Override
	public PaginatorSevResp<CimLeaderFee> querycontribuPageList(Map<String, Object> query, Page<CimLeaderFee> page) {
		PaginatorSevResp<CimLeaderFee> paginatorResponse = new PaginatorSevResp<CimLeaderFee>();
		paginatorResponse.setDatas(cimLeaderFeeMapper.querycontribuPageList(query, page));
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public Double queryMonthProfit(String userId) {
		String month = new SimpleDateFormat("yyyyMM").format(new Date());
		return cimLeaderFeeMapper.queryMonthProfit(userId,month);
	}

	/**
	 * 递归统计间接下级理财师
	 * */
	public Integer queryDirctCfp(String userId){
		Integer numbers = 0;
		List<CrmCfplanner>  cfpList = crmCfplannerMapper.queryLowerLevelOne(userId);
		numbers = cfpList.size();
		for(CrmCfplanner cfp : cfpList){
			if(cfp.getParentId()!=null&&!userId.equals(cfp.getUserId())){
				numbers = numbers + queryDirctCfp(cfp.getUserId());
			}else{
				continue;
			}
		}
		return numbers;
	}
	
	@Override
	public Integer queryIndirectCfpNumbers(String userId) {
		Integer numbers = 0;
		numbers = queryDirctCfp(userId);
		List<CrmCfplanner>  cfpList = crmCfplannerMapper.queryLowerLevelOne(userId);
		numbers = numbers - cfpList.size();//减去直接下级理财师
		return numbers;
	}
	
	/*@Override
	public Integer queryIndirectCfpNumbers(String userId) {
		int numbers = cimLeaderFeeMapper.queryIndirectCfpNumbers(userId);
		return numbers;
	}*/

	@Override
	public boolean haveUnderCfpIndependent(String userId) {
		int inte = cimLeaderFeeMapper.haveUnderCfpIndependent(userId);
		return inte==0?false:true;
	}

	@Override
	public Double queryTotalProfit(String userId) {
		Double totalProfit = 0.0;
		//分两部分：1历史发放奖励   目前暂无数据,可以放到下一个版本做
		
		//2本月累计奖励
		String month = new SimpleDateFormat("yyyyMM").format(new Date());
		totalProfit = totalProfit + cimLeaderFeeMapper.queryMonthProfit(userId,month);
		return totalProfit;
	}

	@Override
	public Double queryContrProfit(String userId) {
		return cimLeaderFeeMapper.queryContrProfit(userId);
	}
	
	@Override
	public String queryLeaderPriftRankListNo1() {
		return cimLeaderFeeMapper.queryLeaderPriftRankListNo1();
	}

}
