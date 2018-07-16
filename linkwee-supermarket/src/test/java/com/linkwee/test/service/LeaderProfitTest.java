package com.linkwee.test.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;
import com.linkwee.test.TestSupport;
import com.linkwee.web.dao.CimLeaderFeeMapper;
import com.linkwee.web.dao.CimLeaderTreeMapper;
import com.linkwee.web.dao.CrmCfplannerMapper;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.web.model.crm.CimLeaderTree;
import com.linkwee.web.service.CimLeaderFeeService;
import com.linkwee.web.service.CimLeaderTreeService;

public class LeaderProfitTest extends TestSupport {

    @Resource
    private CimLeaderFeeService cimLeaderFeeService;
    
	@Resource
	private CrmCfplannerMapper crmCfplannerMapper;
	
	@Resource
	private CimLeaderFeeMapper cimLeaderFeeMapper;
	
	@Resource
	private CimLeaderTreeMapper cimLeaderTreeMapper;
	
	@Resource
	private CimLeaderTreeService cimLeaderTreeService;

    @Test//判断是否符合5+1 leader奖励条件
    public void testIsFiveAddOneCondition() throws NoSuchAlgorithmException, InvalidKeySpecException {
    	CrmCfplanner cfp = new CrmCfplanner();
    	List<CrmCfplanner>  cfList = crmCfplannerMapper.selectByCondition(cfp);
    	Integer five = 0;
    	Integer cfInt = 0;
    	for(CrmCfplanner cf:cfList){
    	//当前理财师下面是否有5个及以上直属理财师
    			List<CrmCfplanner>  cfpList = crmCfplannerMapper.queryLowerLevelOne(cf.getUserId());
    			if(cfpList!=null&&cfpList.size()<5){
//    				return false;
    				System.out.println("小于5人"+cfpList.size());
    				five++;
    				continue;
    			}
    			//其中1个直属理财师年化业绩达到1000元
    			Double amount = cimLeaderFeeMapper.queryUnderCfpYearInvestAmount(cf.getUserId());
    			if(amount!=null&&amount<1000){
    				System.out.println("小于一千"+amount);
    				continue;
    			}
    			cfInt++;
    			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@userId:"+cf.getUserId()+"  手机号码:"+cf.getMobile()
    			                   +" 投资额"+amount);
//    			testCreateTree(cf.getUserId());
    	}
//    	System.out.println("符合五人"+five+"          总数"+cfInt);//符合五人17961          总数22   符合五人17961          总数34
    	
    }
    
    @Test//生成结构树String userId
    public void testCreateTreess() throws NoSuchAlgorithmException, InvalidKeySpecException {
    	String  userId = "17f3cf77163a4c71a11f7415157f4a5a";
    	testCreateTree(userId);
    }
    
    
//    @Test//生成结构树String userId
    public void testCreateTree(String userId) throws NoSuchAlgorithmException, InvalidKeySpecException {
//    	String  userId = "4d1bb49f921e48f3918675fdc615f0ee";
//    	String  userId = "72f63c97449146c996a94aee6abb73e8";
//    	String  userId = "822b71784d6f497cb891626fac538a14";
//    	String  userId = "17f3cf77163a4c71a11f7415157f4a5a";
    	//当前理财师下面是否有5个及以上直属理财师
		List<CrmCfplanner>  cfpList = crmCfplannerMapper.queryLowerLevelOne(userId);
		
		if(cfpList!=null&&cfpList.size()<5){
//    				return false;
			System.out.println("小于5人"+cfpList.size());
		}
		//其中1个直属理财师年化业绩达到1000元
		Double amount = cimLeaderFeeMapper.queryUnderCfpYearInvestAmount(userId);
		if(amount!=null&&amount<1000){
			System.out.println("小于一千"+amount);
		}
		
		for(CrmCfplanner c : cfpList){
			//直属下级是否满足五加一条件
			boolean flag = cimLeaderFeeService.isFiveAddOneCondition(c.getUserId());
			CimLeaderTree tree = new CimLeaderTree();
			tree.setRootId(userId);
			tree.setCfplanner(userId);
			
			tree.setUserId(c.getUserId());
			tree.setIsDirect(1);//直接下级理财师
			tree.setIsOnlypay(flag==true?1:0);
			tree.setIsCalc(flag==true?0:1);
			if(flag){
				System.out.println("##################下级也满足独立条件userId="+c.getUserId());
//				testCreateTree(c.getUserId());
			}
			cimLeaderTreeMapper.insertSelective(tree);
			queryDirctCfp(c.getUserId(),userId,flag);
		}
    }
    
    /**
	 * 递归统计间接下级理财师
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
	
//	public String queryIndirectCfpNumbers(String userId) {
//		Integer numbers = 0;
//		numbers = queryDirctCfp(userId);
//		List<CrmCfplanner>  cfpList = crmCfplannerMapper.queryLowerLevelOne(userId);
//		numbers = numbers - cfpList.size();//减去直接下级理财师
//		return numbers+"";
//	}
}
