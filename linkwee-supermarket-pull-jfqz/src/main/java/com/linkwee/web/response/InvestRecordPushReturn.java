package com.linkwee.web.response;

import java.util.List;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.openplatform.common.vo.CommonInvestRecordVO;
import com.linkwee.web.model.SysThirdkeyConfigPull;

public class InvestRecordPushReturn extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SysThirdkeyConfigPull sysThirdkeyConfigPull;
	
	private List<CommonInvestRecordVO> commonInvestRecordVOList;

	public SysThirdkeyConfigPull getSysThirdkeyConfigPull() {
		return sysThirdkeyConfigPull;
	}

	public void setSysThirdkeyConfigPull(SysThirdkeyConfigPull sysThirdkeyConfigPull) {
		this.sysThirdkeyConfigPull = sysThirdkeyConfigPull;
	}

	public List<CommonInvestRecordVO> getCommonInvestRecordVOList() {
		return commonInvestRecordVOList;
	}

	public void setCommonInvestRecordVOList(
			List<CommonInvestRecordVO> commonInvestRecordVOList) {
		this.commonInvestRecordVOList = commonInvestRecordVOList;
	}
}
