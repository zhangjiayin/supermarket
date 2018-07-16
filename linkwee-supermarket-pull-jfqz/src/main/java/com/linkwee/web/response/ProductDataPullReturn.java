package com.linkwee.web.response;

import java.util.List;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.web.model.CimProductAddPull;
import com.linkwee.web.model.SysThirdkeyConfigPull;

public class ProductDataPullReturn extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SysThirdkeyConfigPull sysThirdkeyConfigPull;
	
	private List<CimProductAddPull> cimProductAddPullList;

	public SysThirdkeyConfigPull getSysThirdkeyConfigPull() {
		return sysThirdkeyConfigPull;
	}

	public void setSysThirdkeyConfigPull(SysThirdkeyConfigPull sysThirdkeyConfigPull) {
		this.sysThirdkeyConfigPull = sysThirdkeyConfigPull;
	}

	public List<CimProductAddPull> getCimProductAddPullList() {
		return cimProductAddPullList;
	}

	public void setCimProductAddPullList(
			List<CimProductAddPull> cimProductAddPullList) {
		this.cimProductAddPullList = cimProductAddPullList;
	}
}
