package com.linkwee.web.response;

import java.util.List;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.openplatform.common.vo.CommonRepaymentRecordVO;
import com.linkwee.web.model.SysThirdkeyConfigPull;

public class RepaymentRecordPullReturn extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SysThirdkeyConfigPull sysThirdkeyConfigPull;
	
	private List<CommonRepaymentRecordVO> commonRepaymentRecordVOList;

	public SysThirdkeyConfigPull getSysThirdkeyConfigPull() {
		return sysThirdkeyConfigPull;
	}

	public void setSysThirdkeyConfigPull(SysThirdkeyConfigPull sysThirdkeyConfigPull) {
		this.sysThirdkeyConfigPull = sysThirdkeyConfigPull;
	}

	public List<CommonRepaymentRecordVO> getCommonRepaymentRecordVOList() {
		return commonRepaymentRecordVOList;
	}

	public void setCommonRepaymentRecordVOList(
			List<CommonRepaymentRecordVO> commonRepaymentRecordVOList) {
		this.commonRepaymentRecordVOList = commonRepaymentRecordVOList;
	}
}
