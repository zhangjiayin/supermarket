package com.linkwee.api.response.crm;

import java.io.Serializable;
import java.util.List;

import com.linkwee.web.model.crm.CrmLineUserInfo;
 /**
 * 
 * @描述：V2.1.0_提现记录  实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年07月22日 21:33:01
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class QueryInvitationRecordResponse implements Serializable {
	
	private static final long serialVersionUID = -2979696234831682651L;
	
	public QueryInvitationRecordResponse(){
		
	}
	
	 /**
     *邀请理财师人数
     */
	private String invitNum;
	
	/**
	 * 线下活动邀请记录
	 */
	private List<CrmLineUserInfo> lineUserList;
	
	
	public List<CrmLineUserInfo> getLineUserList() {
		return lineUserList;
	}

	public void setLineUserList(List<CrmLineUserInfo> lineUserList) {
		this.lineUserList = lineUserList;
	}

	public String getInvitNum() {
		return invitNum;
	}

	public void setInvitNum(String invitNum) {
		this.invitNum = invitNum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

