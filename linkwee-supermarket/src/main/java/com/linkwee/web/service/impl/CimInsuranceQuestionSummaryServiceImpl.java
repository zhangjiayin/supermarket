package com.linkwee.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.api.request.cim.CimInsuranceQuestionSummaryRequest;
import com.linkwee.api.response.insurance.qixin.InsuranceQuestionRecom;
import com.linkwee.core.constant.SysConfigConstant;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.dao.CimInsuranceQuestionSummaryMapper;
import com.linkwee.web.model.cim.CimInsuranceQuestionSummary;
import com.linkwee.web.service.CimInsuranceQuestionSummaryService;
import com.linkwee.web.service.SysConfigService;


 /**
 * 
 * @描述：CimInsuranceQuestionSummaryService 服务实现类
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年12月29日 15:37:10
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimInsuranceQuestionSummaryService")
public class CimInsuranceQuestionSummaryServiceImpl extends GenericServiceImpl<CimInsuranceQuestionSummary, Long> implements CimInsuranceQuestionSummaryService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimInsuranceQuestionSummaryServiceImpl.class);
	
	@Resource
	private CimInsuranceQuestionSummaryMapper cimInsuranceQuestionSummaryMapper;
	@Resource
	private SysConfigService sysConfigService;
	
	@Override
    public GenericDao<CimInsuranceQuestionSummary, Long> getDao() {
        return cimInsuranceQuestionSummaryMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimInsuranceQuestionSummary -- 排序和模糊查询 ");
		Page<CimInsuranceQuestionSummary> page = new Page<CimInsuranceQuestionSummary>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimInsuranceQuestionSummary> list = this.cimInsuranceQuestionSummaryMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public void insertCimInsuranceQuestionSummary(String userId, CimInsuranceQuestionSummaryRequest req) {
		CimInsuranceQuestionSummary model = new CimInsuranceQuestionSummary();
		model.setAge(req.getAge());
		model.setCreateDate(new Date());
		model.setFamilyEnsure(req.getFamilyEnsure());
		model.setFamilyLoan(req.getFamilyLoan());
		model.setFamilyMember(req.getFamilyMember());
		model.setSex(req.getSex());
		model.setUserId(userId);
		model.setYearIncome(req.getYearIncome());
		Integer score = cimInsuranceQuestionSummaryMapper.sumScore(changeSql(req.getFamilyEnsure()),changeSql(req.getFamilyLoan()),changeSql(req.getYearIncome()));
		model.setTotalScore(200+score);
		model.setRiskGrade(sumTotalScore(200+score));
		CimInsuranceQuestionSummary reqSum = new CimInsuranceQuestionSummary();
		reqSum.setUserId(userId);
		CimInsuranceQuestionSummary retSum =cimInsuranceQuestionSummaryMapper.selectOneByCondition(reqSum);
		if(retSum==null){
			cimInsuranceQuestionSummaryMapper.insertSelective(model);
		}else{
			cimInsuranceQuestionSummaryMapper.updateByPrimaryKeySelective(model);
		}
	}
	
	public String changeSql(String sql){
		String strRe = "";
		String[] sqls = sql.split(",");
		for(String str:sqls){
			strRe = strRe+"'"+str+"',";
		}
		strRe = strRe+"''";
		return strRe;
		
	}

	
	public String sumTotalScore(Integer score){
		if(score>=250){
			return "风险较低";
		}else if(score<200){
			return "风险极高";
		}else if(200<=score&&score<230){
			return "风险较高";
		}else if(230<=score&&score<250){
			return "风险适中";
		}
		return null;
	}

	@Override
	public CimInsuranceQuestionSummary queryQquestionResult(String userId) {
		CimInsuranceQuestionSummary req = new CimInsuranceQuestionSummary();
		req.setUserId(userId);
		List<CimInsuranceQuestionSummary> sumList = cimInsuranceQuestionSummaryMapper.selectByCondition(req);
		if(sumList.size()>0&&sumList.get(0).getFamilyMember()!=null){
			String member = sumList.get(0).getFamilyMember();
			List<InsuranceQuestionRecom>  recomList = new ArrayList<InsuranceQuestionRecom>();
			if(member.contains("0")){//宠爱自己
				String kindToMyself = sysConfigService.getValuesByKey(SysConfigConstant.KIND_TO_MYSELF);
				InsuranceQuestionRecom re = new InsuranceQuestionRecom();
				re.setCategoryImage(kindToMyself);
				re.setRecomCategory("5");//重疾险
				recomList.add(re);
				sumList.get(0).setRecomList(recomList);
			}
			if(member.contains("6")||member.contains("7")){//健康成长
				String healthyGrowth = sysConfigService.getValuesByKey(SysConfigConstant.HEALTHY_GROWTH);
				InsuranceQuestionRecom re = new InsuranceQuestionRecom();
				re.setCategoryImage(healthyGrowth);
				re.setRecomCategory("8");//少儿险
				recomList.add(re);
			}
			if(member.contains("2")||member.contains("3")||member.contains("4")||member.contains("5")){//感恩父母
				String thanksgivingParents = sysConfigService.getValuesByKey(SysConfigConstant.THANKSGIVING_PARENTS);
				InsuranceQuestionRecom re = new InsuranceQuestionRecom();
				re.setCategoryImage(thanksgivingParents);
				re.setRecomCategory("9");//老年险
				recomList.add(re);
			}
			if(sumList.get(0).getFamilyMember().contains("1")){//一路有你
				String youAllTheWay = sysConfigService.getValuesByKey(SysConfigConstant.THERE_YOU_ALL_THE_WAY);
				InsuranceQuestionRecom re = new InsuranceQuestionRecom();
				re.setCategoryImage(youAllTheWay);
				re.setRecomCategory("1");//意外险
				recomList.add(re);
			}
			sumList.get(0).setRecomList(recomList);
		}
		return sumList.size()>0?sumList.get(0):null;
	}
}
