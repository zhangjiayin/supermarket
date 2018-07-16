package com.linkwee.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.request.cim.OrginfoaDetailRequest;
import com.linkwee.api.request.cim.OrginfoaListRequest;
import com.linkwee.api.request.cim.OrginfoaPageListRequest;
import com.linkwee.api.response.cim.OrginfoaDetailResponse;
import com.linkwee.api.response.cim.OrginfoaPageListResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.SignUtils;
import com.linkwee.web.dao.CimOrgInfoAMapper;
import com.linkwee.web.enums.PlatformEnum;
import com.linkwee.web.model.CimOrgInfoA;
import com.linkwee.web.model.CimOrgUrl;
import com.linkwee.web.model.SysThirdkeyConfig;
import com.linkwee.web.request.orgInfo.OrgUrlSkipParameterRequest;
import com.linkwee.web.service.*;
import com.linkwee.xoss.api.AppRequestHead;
import com.linkwee.xoss.helper.JsonWebTokenHepler;
import com.linkwee.xoss.util.AppUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * @描述：CimOrgInfoAService 服务实现类
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年05月09日 16:41:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("cimOrgInfoAService")
public class CimOrgInfoAServiceImpl extends GenericServiceImpl<CimOrgInfoA, Long> implements CimOrgInfoAService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgInfoAServiceImpl.class);
	
	@Resource
	private CimOrgInfoAMapper cimOrgInfoAMapper;
	@Resource
	private CimOrgInvestStrategyAService cimOrgInvestStrategyAService;
	@Resource
	private ActivityListService activityListService;
	@Resource
	private CimOrgPictureService cimOrgPictureService;  //机构图片配置服务
	@Resource
	private CimOrgMemberinfoAService orgMemberinfoAService; // A专区团队成员信息
	@Resource
	private SysThirdkeyConfigService sysThirdkeyConfigService;
	@Resource
	private CrmOrgAcctRelAService orgAcctRelAService;
	
	@Override
    public GenericDao<CimOrgInfoA, Long> getDao() {
        return cimOrgInfoAMapper;
    }
    
    @Override
	public DataTableReturn selectByDatatables(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		LOGGER.debug(" -- CimOrgInfoA -- 排序和模糊查询 ");
		Page<CimOrgInfoA> page = new Page<CimOrgInfoA>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<CimOrgInfoA> list = this.cimOrgInfoAMapper.selectBySearchInfo(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}

	@Override
	public PaginatorResponse<OrginfoaPageListResponse> queryPageList(OrginfoaPageListRequest orginfoaPageListRequest) {
		PaginatorResponse<OrginfoaPageListResponse> paginatorResponse = new PaginatorResponse<OrginfoaPageListResponse>();
		Page<OrginfoaPageListResponse> page = new Page<OrginfoaPageListResponse>(orginfoaPageListRequest.getPageIndex(), orginfoaPageListRequest.getPageSize());
		//List<OrginfoaPageListResponse> resultList = cimOrgInfoAMapper.queryPageList(orginfoaPageListRequest,page);
		List<String> pageListOrgNumber = cimOrgInfoAMapper.queryPageListOrgNumber(orginfoaPageListRequest,page);
		List<OrginfoaPageListResponse> resultList = cimOrgInfoAMapper.queryPageListWithFee(pageListOrgNumber);
		paginatorResponse.setDatas(resultList);
		paginatorResponse.setValuesByPage(page);
		return paginatorResponse;
	}

	@Override
	public OrginfoaDetailResponse queryOrginfoaDetail(OrginfoaDetailRequest orginfoaDetailRequest) {
		// TODO Auto-generated method stub
		OrginfoaDetailResponse orginfoaDetailResponse = cimOrgInfoAMapper.queryOrginfoaDetailGray(orginfoaDetailRequest);
		if(orginfoaDetailResponse != null){
            orginfoaDetailResponse.setCimOrgInvestStrategyAExtendsList(cimOrgInvestStrategyAService.queryCimOrgInvestStrategyAExtendsList(orginfoaDetailRequest.getOrgCode()));
            orginfoaDetailResponse.setActivityLists(activityListService.queryOrginfoaActivityList(orginfoaDetailRequest.getOrgCode()));
            orginfoaDetailResponse.setOrgEnvironmentList(cimOrgPictureService.queryOrgPictureList(orginfoaDetailRequest.getOrgCode(),2,1));
            orginfoaDetailResponse.setOrgPapersList(cimOrgPictureService.queryOrgPictureList(orginfoaDetailRequest.getOrgCode(),1,1));
            orginfoaDetailResponse.setTeamInfos(orgMemberinfoAService.queryOrgTeamInfos(orginfoaDetailRequest.getOrgCode()));
        }
		return orginfoaDetailResponse;
	}

	@Override
	public OrginfoaDetailResponse queryOrginfoaDetail(String orgCode) {
		OrginfoaDetailResponse orginfoaDetailResponse = cimOrgInfoAMapper.queryOrginfoaDetail(orgCode);
		orginfoaDetailResponse.setCimOrgInvestStrategyAExtendsList(cimOrgInvestStrategyAService.queryCimOrgInvestStrategyAExtendsList(orgCode));
		orginfoaDetailResponse.setActivityLists(activityListService.queryOrginfoaActivityList(orgCode));
		return orginfoaDetailResponse;
	}

	@Override
	public CimOrgInfoA queryOrginfoa(String orgNumber) {
		// TODO Auto-generated method stub
		CimOrgInfoA cimOrgInfoA = new CimOrgInfoA();
		cimOrgInfoA.setOrgNumber(orgNumber);
		return cimOrgInfoAMapper.selectOneByCondition(cimOrgInfoA);
	}

	@Override
	public List<CimOrgInfoA> queryOrginfoaList(OrginfoaListRequest orginfoaListRequest) {
		// TODO Auto-generated method stub
		return cimOrgInfoAMapper.queryOrginfoaList(orginfoaListRequest);
	}

    @Override
    public Map<String, String> getOrgUrlSkipParameter(OrgUrlSkipParameterRequest orgUrlSkipParameterRequest, AppRequestHead head) {
		//查询第三方api接口配置
		SysThirdkeyConfig sysThirdkeyConfig = new SysThirdkeyConfig();
		sysThirdkeyConfig.setOrgNumber(orgUrlSkipParameterRequest.getOrgNo());
		sysThirdkeyConfig = sysThirdkeyConfigService.selectOne(sysThirdkeyConfig);

		String userId = JsonWebTokenHepler.getUserIdByToken(head.getToken());
		String thirdOrgAccount = orgAcctRelAService.queryThirdOrgAccountByUserId(userId,orgUrlSkipParameterRequest.getOrgNo());//通过userid和机构编码 查询用户的 第三方机构用户账号

		Map<String,String> paramsMap = new HashMap<String,String>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		paramsMap.put("orgNumber", sysThirdkeyConfig.getOrgNumber());//机构编码
		paramsMap.put("orgKey",sysThirdkeyConfig.getOrgKey());//机构公钥
		paramsMap.put("timestamp",simpleDateFormat.format(new Date()));
		paramsMap.put("orgAccount",thirdOrgAccount);//第三方机构用户账号
		//判断是否PC端
		if(PlatformEnum.WEB == AppUtils.getPlatform(head.getOrgNumber())){
			paramsMap.put("requestFrom","web");//PC端
		}else{
			paramsMap.put("requestFrom","wap");//移动端
		}

		String sign = SignUtils.sign(paramsMap,sysThirdkeyConfig.getOrgSecret());//机构私钥
		paramsMap.put("sign", sign); //生成签名

		return paramsMap;
    }

}
