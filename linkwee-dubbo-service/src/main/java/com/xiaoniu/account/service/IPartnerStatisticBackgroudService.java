package com.xiaoniu.account.service;

import com.xiaoniu.account.comm.page.PageResult;
import com.xiaoniu.account.domain.PartnerInvestReq;
import com.xiaoniu.account.domain.PartnerRegisterReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.PartnerInvestRlt;
import com.xiaoniu.account.domain.result.PartnerRegisterRlt;

/**
 * 后台统计功能
 * @author 颜彩云
 */
public interface IPartnerStatisticBackgroudService {
    /**
     * 注册明细（有包含首投金额及时间）
     * @param req
     * @return
     */
    public CommonRlt<PageResult<PartnerRegisterRlt>> getPartnerRegister(PartnerRegisterReq req);
    
    /**
     * 首投明细（作为注册明细的补充）
     * @param req
     * @return
     */
    public CommonRlt<PageResult<PartnerRegisterRlt>> getFirstInvestDetail(PartnerRegisterReq req);
    
    /**
     * 注册投资统计（有包含首投金额及时间）
     * @param req
     * @return
     */
    public CommonRlt<PageResult<PartnerInvestRlt>> getPartnerInvest(PartnerInvestReq req);
    
    /**
     * 注册当日投资统计（有包含首投金额及时间）
     * @param req
     * @return
     */
    public CommonRlt<PageResult<PartnerInvestRlt>> getPartnerTodayInvest(PartnerInvestReq req);
}
