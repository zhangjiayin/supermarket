package com.linkwee.api.response.crm;

import java.io.Serializable;

import com.linkwee.web.model.CrmCfpNewcomerWelfareTask;
import com.linkwee.xoss.util.WebUtil;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月08日 16:32:41
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CrmCfpNewcomerWelfareTaskResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -930282392036260651L;

	public CrmCfpNewcomerWelfareTaskResponse() {
		super();
	}
	
	public CrmCfpNewcomerWelfareTaskResponse(CrmCfpNewcomerWelfareTask obj) {
		WebUtil.initObj(this, obj);
	}

	/**
     *注册完成状态： 0未完成，1已完成
     */
	private Integer regStatus;
	
    /**
     *绑卡完成状态： 0未完成，1已完成
     */
	private Integer bindcardStatus;
	
    /**
     *自投完成状态： 0未完成，1已完成
     */
	private Integer investStatus;
	
    /**
     *邀请理财师完成状态： 0未完成，1已完成
     */
	private Integer inviteCfpStatus;
	
    /**
     *发红包完成状态： 0未完成，1已完成
     */
	private Integer inviteCfpInvestStatus;
	
    /**
     *终极大奖完成状态： 0未完成，1已完成
     */
	private Integer welfareAllStatus;
	
	/**
	 * 下一个状态
	 */
	private Integer nextStatus;

	public Integer getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(Integer regStatus) {
		this.regStatus = regStatus;
	}

	public Integer getBindcardStatus() {
		return bindcardStatus;
	}

	public void setBindcardStatus(Integer bindcardStatus) {
		this.bindcardStatus = bindcardStatus;
	}

	public Integer getInvestStatus() {
		return investStatus;
	}

	public void setInvestStatus(Integer investStatus) {
		this.investStatus = investStatus;
	}

	public Integer getInviteCfpStatus() {
		return inviteCfpStatus;
	}

	public void setInviteCfpStatus(Integer inviteCfpStatus) {
		this.inviteCfpStatus = inviteCfpStatus;
	}

	public Integer getInviteCfpInvestStatus() {
		return inviteCfpInvestStatus;
	}

	public void setInviteCfpInvestStatus(Integer inviteCfpInvestStatus) {
		this.inviteCfpInvestStatus = inviteCfpInvestStatus;
	}

	public Integer getWelfareAllStatus() {
		return welfareAllStatus;
	}

	public void setWelfareAllStatus(Integer welfareAllStatus) {
		this.welfareAllStatus = welfareAllStatus;
	}

	public Integer getNextStatus() {
		if(regStatus == 1){
			if(bindcardStatus == 1){
				if(investStatus == 1){
					if(inviteCfpStatus == 1){
						if(inviteCfpInvestStatus == 1){
							if(welfareAllStatus == 1){
								nextStatus = 7;
							}else{
								nextStatus = 6;
							}
						}else{
							nextStatus = 5;
						}
					}else{
						nextStatus = 4;
					}
				}else{
					nextStatus = 3;
				}
			}else{
				nextStatus = 2;
			}
		}else{
			nextStatus = 1;
		}
		return nextStatus;
	}

	public void setNextStatus(Integer nextStatus) {
		this.nextStatus = nextStatus;
	}
}

