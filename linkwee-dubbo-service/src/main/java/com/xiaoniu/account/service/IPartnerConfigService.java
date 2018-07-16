package com.xiaoniu.account.service;

import java.util.List;

import com.xiaoniu.account.domain.FindPartnerConfigReq;
import com.xiaoniu.account.domain.PartnerConfigReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.EmptyObject;
import com.xiaoniu.account.domain.result.PartnerConRlt;

/**
 * 
 * @author 周锋恒
 * 
 * @Date 2016年1月8日
 */
public interface IPartnerConfigService {

	/**
	 * 添加业务配置
	 */
	public CommonRlt<EmptyObject> addPartnerConfig(PartnerConfigReq req);

	/**
	 * 更新业务配置
	 */
	public CommonRlt<EmptyObject> updatePartnerConfig(PartnerConfigReq req);

	/**
	 * 
	 * @param req
	 * @return
	 */
	public CommonRlt<PartnerConRlt> findByPartnerId(FindPartnerConfigReq req);

	/**
	 * 查询业务配置列表
	 * 
	 * @return
	 */
	public CommonRlt<List<PartnerConRlt>> queryAll();

}
