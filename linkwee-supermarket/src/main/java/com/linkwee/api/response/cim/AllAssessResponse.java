package com.linkwee.api.response.cim;

import java.util.List;

import com.linkwee.web.model.CimOrgRiskManageSynthesize;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.DtOrgSynthesizeData;
import com.linkwee.web.model.cim.CimOrgRiskFresGradeExtends;

public class AllAssessResponse {

	/**
	 * 机构基础信息
	 */
	private CimOrginfo cimOrginfo;
	
	/**
	 * 机构风控管理综合
	 */
	private CimOrgRiskManageSynthesize  cimOrgRiskManageSynthesize;
	
	/**
	 * 机构风控FRES评分列表
	 */
	private List<CimOrgRiskFresGradeExtends> cimOrgRiskFresGradeExtendsList;
	
	/**
	 * 机构风控综合数据
	 */
	private DtOrgSynthesizeData  dtOrgSynthesizeData;

	public CimOrginfo getCimOrginfo() {
		return cimOrginfo;
	}

	public void setCimOrginfo(CimOrginfo cimOrginfo) {
		this.cimOrginfo = cimOrginfo;
	}

	public CimOrgRiskManageSynthesize getCimOrgRiskManageSynthesize() {
		return cimOrgRiskManageSynthesize;
	}

	public void setCimOrgRiskManageSynthesize(
			CimOrgRiskManageSynthesize cimOrgRiskManageSynthesize) {
		this.cimOrgRiskManageSynthesize = cimOrgRiskManageSynthesize;
	}

	public List<CimOrgRiskFresGradeExtends> getCimOrgRiskFresGradeExtendsList() {
		return cimOrgRiskFresGradeExtendsList;
	}

	public void setCimOrgRiskFresGradeExtendsList(
			List<CimOrgRiskFresGradeExtends> cimOrgRiskFresGradeExtendsList) {
		this.cimOrgRiskFresGradeExtendsList = cimOrgRiskFresGradeExtendsList;
	}

	public DtOrgSynthesizeData getDtOrgSynthesizeData() {
		return dtOrgSynthesizeData;
	}

	public void setDtOrgSynthesizeData(DtOrgSynthesizeData dtOrgSynthesizeData) {
		this.dtOrgSynthesizeData = dtOrgSynthesizeData;
	}
}
