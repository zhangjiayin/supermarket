package com.linkwee.job;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.CfplannerManagerDao;
import com.linkwee.web.dao.CrmCfpLevelRecordMapper;
import com.linkwee.web.dao.CrmCfplannerMapper;
import com.linkwee.web.enums.CfpJobGradeEnum;
import com.linkwee.web.model.CfpLevelNode;

@Component
public class CfpLevelJob extends AbstractSimpleElasticJob{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CfpLevelJob.class);
	
	@Resource
	private CfplannerManagerDao cfplannerManagerDao;
	
	@Resource
	private CrmCfpLevelRecordMapper crmCfpLevelRecordMapper;
	
	@Resource
	private CrmCfplannerMapper crmCfplannerMapper;
	
	@Override
	public void process(JobExecutionMultipleShardingContext shardingContext) {
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>理财师职级计算start");
	    try {
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.MONTH, -1);
	        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
	        String startDate = format.format(calendar.getTime());
	        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	        String endDate = format.format(calendar.getTime());
	        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
	        int month = Integer.parseInt(dft.format(calendar.getTime()));
	    	getCfpLevel(startDate,endDate,month);//计算理财师职级
		} catch (Exception e) {
			LOGGER.error(">>>>>>>>>>>>>>理财师职级计算定时任务异常{}",e);
			e.printStackTrace();
		}		
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>理财师职级计算end");
	}
	
	/**
	 * 计算没有子节点的节点职级
	 * @param cfpWithoutChildrenNodeList
	 */
	private void computeWithoutChildrenNodeLevel(List<CfpLevelNode> cfpWithoutChildrenNodeList,int month){
		for(CfpLevelNode cfpLevelNode : cfpWithoutChildrenNodeList){
			if(cfpLevelNode.getPersonAmount().compareTo(new BigDecimal(2000)) < 0){
				cfpLevelNode.setLevel(CfpJobGradeEnum.TA.getValue());
				cfpLevelNode.setLevelWeight(CfpJobGradeEnum.TA.getLevelWeight());
				
			}else{
				cfpLevelNode.setLevel(CfpJobGradeEnum.SM1.getValue());
				cfpLevelNode.setLevelWeight(CfpJobGradeEnum.SM1.getLevelWeight());
			}
			cfpLevelNode.setMonth(month);
		}
	}
	
	/**
	 * 计算有子节点的节点职级
	 * @param cfpWithChildrenNode
	 */
	private void computeWithChildrenNodeLevel(CfpLevelNode cfpWithChildrenNode){
		
		List<CfpLevelNode> childrenNodes = cfpWithChildrenNode.getChildrens();
		int hasLevelChildrenNum = 0;
		for(CfpLevelNode cfpLevelNode : childrenNodes){
			if(StringUtils.isNotBlank(cfpLevelNode.getLevel())){
				hasLevelChildrenNum++;
			}
		}
		/**
		 * 叶子节点直接返回
		 * 子节点的职级不确定时递归计算子节点的职级
		 * 用子节点的直接计算该节点的职级
		 */
		if(hasLevelChildrenNum == childrenNodes.size() && hasLevelChildrenNum == 0){
			return;
		}else if(hasLevelChildrenNum != childrenNodes.size()){
			for(CfpLevelNode cfpLevelNode : childrenNodes){
				computeWithChildrenNodeLevel(cfpLevelNode);
			}
		}
		for(CfpLevelNode tempNode : childrenNodes){
			if(tempNode.getLevel().equals(CfpJobGradeEnum.TA.getValue())){
				cfpWithChildrenNode.setProbationNum(cfpWithChildrenNode.getProbationNum() + 1);
			}else if(tempNode.getLevel().equals(CfpJobGradeEnum.SM1.getValue())){
				//子节点为顾问
				cfpWithChildrenNode.setAdviserNum(cfpWithChildrenNode.getAdviserNum() + 1);			
			}else if(tempNode.getLevel().equals(CfpJobGradeEnum.SM2.getValue())){
				// 子节点为经理
				cfpWithChildrenNode.setManagerNum(cfpWithChildrenNode.getManagerNum() + 1);			
			}else if(tempNode.getLevel().equals(CfpJobGradeEnum.SM3.getValue())){
				//子节点为总监
				cfpWithChildrenNode.setDirectorNum(cfpWithChildrenNode.getDirectorNum() + 1);
			}
		}
		//计算职级
		//实际有效见习职级子节点数量
		int adviserRealNum = cfpWithChildrenNode.getDirectorNum() + cfpWithChildrenNode.getManagerNum() + cfpWithChildrenNode.getAdviserNum();
		//实际有效经理职级子节点数量
		int managerRealNum = cfpWithChildrenNode.getDirectorNum() + cfpWithChildrenNode.getManagerNum();
		if(managerRealNum >= 4 && cfpWithChildrenNode.getPersonAmount().compareTo(new BigDecimal(50000)) >= 0){
			cfpWithChildrenNode.setLevel(CfpJobGradeEnum.SM3.getValue());
			cfpWithChildrenNode.setLevelWeight(CfpJobGradeEnum.SM3.getLevelWeight());
		}else if(adviserRealNum >= 2 && cfpWithChildrenNode.getPersonAmount().compareTo(new BigDecimal(30000)) >= 0){
			cfpWithChildrenNode.setLevel(CfpJobGradeEnum.SM2.getValue());
			cfpWithChildrenNode.setLevelWeight(CfpJobGradeEnum.SM2.getLevelWeight());
		}else if(cfpWithChildrenNode.getPersonAmount().compareTo(new BigDecimal(2000)) < 0){
			cfpWithChildrenNode.setLevel(CfpJobGradeEnum.TA.getValue());
			cfpWithChildrenNode.setLevelWeight(CfpJobGradeEnum.TA.getLevelWeight());
		}else{
			cfpWithChildrenNode.setLevel(CfpJobGradeEnum.SM1.getValue());
			cfpWithChildrenNode.setLevelWeight(CfpJobGradeEnum.SM1.getLevelWeight());
		}
	}
	
	/**
	 * 将有父节点的节点交给父节点
	 * @param childrenList
	 * @param cfpWithChildrenAndParentMap
	 * @param cfpWithChildrenButParentMap
	 */
	private void setChildrenToParent(List<CfpLevelNode> childrenList,Map<String, CfpLevelNode> cfpWithChildrenAndParentMap,Map<String, CfpLevelNode> cfpWithChildrenButParentMap,int month){
		for(CfpLevelNode cfpLevelNode : childrenList){
			CfpLevelNode tempNode = cfpWithChildrenAndParentMap.get(cfpLevelNode.getParentId());
			if(tempNode == null){
				tempNode = cfpWithChildrenButParentMap.get(cfpLevelNode.getParentId());
			}
			if(tempNode != null){
				//将该节点交给父节点
				tempNode.getChildrens().add(cfpLevelNode);		
			}else {
				LOGGER.error("节点不存在父节点，或者节点不在map中。userId={},parentId={}",cfpLevelNode.getUserId(),cfpLevelNode.getParentId());
			}
			cfpLevelNode.setMonth(month);
		}
	}
	
	private void classifyNodes(List<CfpLevelNode> cfpNodeList,
			List<String> tALevelNodes, List<String> sM1LevelNodes,
			List<String> sM2LevelNodes, List<String> sM3LevelNodes,
			List<CfpLevelNode> allCfpLevelNodes) {
		for(CfpLevelNode cfpLevelNode : cfpNodeList){
			allCfpLevelNodes.add(cfpLevelNode);
			if(cfpLevelNode.getLevel().equals(CfpJobGradeEnum.TA.getValue())){
				tALevelNodes.add(cfpLevelNode.getUserId());
			}else if(cfpLevelNode.getLevel().equals(CfpJobGradeEnum.SM1.getValue())){
				sM1LevelNodes.add(cfpLevelNode.getUserId());
			}else if(cfpLevelNode.getLevel().equals(CfpJobGradeEnum.SM2.getValue())){
				sM2LevelNodes.add(cfpLevelNode.getUserId());
			}else if(cfpLevelNode.getLevel().equals(CfpJobGradeEnum.SM3.getValue())){
				sM3LevelNodes.add(cfpLevelNode.getUserId());
			}
		}
	}
	
	/**
	 * 计算理财师职级
	 */
	public void getCfpLevel(String startDate,String endDate,int month) {
		LOGGER.info("开始取数据----"+ DateUtils.formatDate(DateUtils.FORMAT_FULL_CN, new Date()));
		//无父无子的节点List
		List<CfpLevelNode> cfpWithoutChildrenAndParentList = cfplannerManagerDao.getCfpWithoutChildrenAndParent(startDate,endDate);
		//有父无子的节点List
		List<CfpLevelNode> cfpWithoutChildrenButParentList = cfplannerManagerDao.getCfpWithoutChildrenButParent(startDate,endDate);
		//有父有子的节点List
		List<CfpLevelNode> cfpWithChildrenAndParentList = cfplannerManagerDao.getCfpWithChildrenAndParent(startDate,endDate);
		//无父有子的节点List
		List<CfpLevelNode> cfpWithChildrenButParentList = cfplannerManagerDao.getCfpWithChildrenButParent(startDate,endDate);
		LOGGER.info("结束取数据----"+DateUtils.formatDate(DateUtils.FORMAT_FULL_CN, new Date()));
		//所有的理财师节点
		List<CfpLevelNode> allCfpLevelNodes = new ArrayList<CfpLevelNode>();
		//见习理财师
		List<String> tALevelNodes = new ArrayList<String>();
		//顾问理财师
		List<String> sM1LevelNodes = new ArrayList<String>();
		//经理理财师
		List<String> sM2LevelNodes = new ArrayList<String>();
		//总监理财师
		List<String> sM3LevelNodes = new ArrayList<String>();
		//有父有子的节点Map
		Map<String, CfpLevelNode> cfpWithChildrenAndParentMap = new HashMap<String, CfpLevelNode>();
		//无父有子的节点Map
		Map<String, CfpLevelNode> cfpWithChildrenButParentMap = new HashMap<String, CfpLevelNode>();
		//缓存有子节点的节点
		for(CfpLevelNode cfpLevelNode : cfpWithChildrenAndParentList){
			cfpWithChildrenAndParentMap.put(cfpLevelNode.getUserId(), cfpLevelNode);
		}
		for(CfpLevelNode cfpLevelNode : cfpWithChildrenButParentList){
			cfpWithChildrenButParentMap.put(cfpLevelNode.getUserId(), cfpLevelNode);
		}
		//无父无子的节点只能对应等级一和二
		computeWithoutChildrenNodeLevel(cfpWithoutChildrenAndParentList,month);
		//将有父无子的节点（这种节点只能对应等级一和二）设置到其父节点
		computeWithoutChildrenNodeLevel(cfpWithoutChildrenButParentList,month);
		setChildrenToParent(cfpWithoutChildrenButParentList,cfpWithChildrenAndParentMap,cfpWithChildrenButParentMap,month);
		//将有父有子的节点设置到其父节点
		setChildrenToParent(cfpWithChildrenAndParentList,cfpWithChildrenAndParentMap,cfpWithChildrenButParentMap,month);
		//计算无父有子的节点的职级
		for(CfpLevelNode cfpLevelNode : cfpWithChildrenButParentList){
			computeWithChildrenNodeLevel(cfpLevelNode);
			cfpLevelNode.setMonth(month);
		}
		LOGGER.info("计算完职级，开始分类----"+DateUtils.formatDate(DateUtils.FORMAT_FULL_CN, new Date()));		
		classifyNodes(cfpWithoutChildrenAndParentList,tALevelNodes,sM1LevelNodes,sM2LevelNodes,sM3LevelNodes,allCfpLevelNodes);
		classifyNodes(cfpWithChildrenAndParentList,tALevelNodes,sM1LevelNodes,sM2LevelNodes,sM3LevelNodes,allCfpLevelNodes);
		classifyNodes(cfpWithChildrenButParentList,tALevelNodes,sM1LevelNodes,sM2LevelNodes,sM3LevelNodes,allCfpLevelNodes);
		classifyNodes(cfpWithoutChildrenButParentList,tALevelNodes,sM1LevelNodes,sM2LevelNodes,sM3LevelNodes,allCfpLevelNodes);
		LOGGER.info("计算完职级，开始存储更新到数据库----"+DateUtils.formatDate(DateUtils.FORMAT_FULL_CN, new Date()));

		if(!crmCfpLevelRecordMapper.isExistMonth(month)){
			//设置有效的职级记录为无效
			crmCfpLevelRecordMapper.updateYearpurAmountNow();
			crmCfpLevelRecordMapper.batchInsert(allCfpLevelNodes);
		}
				
		Map<String, Object> updateRequestMap = new HashMap<String, Object>();
		if(tALevelNodes != null && tALevelNodes.size() > 0){
			updateRequestMap.put("jobGrade", CfpJobGradeEnum.TA.getValue());
			updateRequestMap.put("cfpLevelNodes", tALevelNodes);
			crmCfplannerMapper.batchUpdateJobGrade(updateRequestMap);
		}
		if(sM1LevelNodes != null && sM1LevelNodes.size() > 0){
			updateRequestMap.put("jobGrade", CfpJobGradeEnum.SM1.getValue());
			updateRequestMap.put("cfpLevelNodes", sM1LevelNodes);
			crmCfplannerMapper.batchUpdateJobGrade(updateRequestMap);
		}
		if(sM2LevelNodes != null && sM2LevelNodes.size() > 0){
			updateRequestMap.put("jobGrade", CfpJobGradeEnum.SM2.getValue());
			updateRequestMap.put("cfpLevelNodes", sM2LevelNodes);
			crmCfplannerMapper.batchUpdateJobGrade(updateRequestMap);
		}
		if(sM3LevelNodes != null && sM3LevelNodes.size() > 0){
			updateRequestMap.put("jobGrade", CfpJobGradeEnum.SM3.getValue());
			updateRequestMap.put("cfpLevelNodes", sM3LevelNodes);
			crmCfplannerMapper.batchUpdateJobGrade(updateRequestMap);
		}
		
		LOGGER.info("存储更新数据库完成----"+DateUtils.formatDate(DateUtils.FORMAT_FULL_CN, new Date()));
		/*crmCfpLevelRecordMapper.batchInsert(cfpWithoutChildrenAndParentList);
		crmCfpLevelRecordMapper.batchInsert(cfpWithoutChildrenButParentList);
		crmCfpLevelRecordMapper.batchInsert(cfpWithChildrenAndParentList);
		crmCfpLevelRecordMapper.batchInsert(cfpWithChildrenButParentList);*/
		
		/*System.out.println("ok");
		CfpLevelNode xxxCfpLevelNode = cfpWithChildrenButParentMap.get("af55712345fe4a8aa97c349b753d6d5c");
		System.out.println(xxxCfpLevelNode);
		for(CfpLevelNode temp : xxxCfpLevelNode.getChildrens()){
			System.out.println(temp.getUserId());
		}*/
		/*for(CfpLevelNode cfpLevelNode : cfpWithoutChildrenAndParentList){
			if(StringUtils.isBlank(cfpLevelNode.getLevel()))
			LOGGER.info(cfpLevelNode.getLevel() + "----" + cfpLevelNode.getUserId()+ "----" + cfpLevelNode.getAdviserNum()+ "----" + cfpLevelNode.getManagerNum());
		}
		for(CfpLevelNode cfpLevelNode : cfpWithoutChildrenButParentList){
			if(StringUtils.isBlank(cfpLevelNode.getLevel()))
			LOGGER.info(cfpLevelNode.getLevel() + "----" + cfpLevelNode.getUserId()+ "----" + cfpLevelNode.getAdviserNum()+ "----" + cfpLevelNode.getManagerNum());
		}
		for(CfpLevelNode cfpLevelNode : cfpWithChildrenAndParentList){
			if(StringUtils.isBlank(cfpLevelNode.getLevel()))
			LOGGER.info(cfpLevelNode.getLevel() + "----" + cfpLevelNode.getUserId()+ "----" + cfpLevelNode.getAdviserNum()+ "----" + cfpLevelNode.getManagerNum());
		}
		for(CfpLevelNode cfpLevelNode : cfpWithChildrenButParentList){
			if(StringUtils.isBlank(cfpLevelNode.getLevel()))
			LOGGER.info(cfpLevelNode.getLevel() + "----" + cfpLevelNode.getUserId()+ "----" + cfpLevelNode.getAdviserNum()+ "----" + cfpLevelNode.getManagerNum());
		}*/
	}
	
	/*public static void main(String[] args){     
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        String startDate = format.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endDate = format.format(calendar.getTime());
        System.out.println(startDate);
        System.out.println(endDate);
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        System.out.println(Integer.parseInt(dft.format(calendar.getTime())));
        System.out.println(CfpJobGradeEnum.TA.getKey());
        System.out.println(CfpJobGradeEnum.TA.getValue());
        System.out.println(CfpJobGradeEnum.TA.getLevelWeight());
        System.out.println(DateUtils.formatDate(DateUtils.FORMAT_FULL_CN, new Date()));
	}*/
}
