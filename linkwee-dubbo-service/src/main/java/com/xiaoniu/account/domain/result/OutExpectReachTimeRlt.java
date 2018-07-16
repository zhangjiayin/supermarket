/*************************************************************************
 *                  XIAONIU66 CONFIDENTIAL AND PROPRIETARY                                                      
 *
 *                COPYRIGHT (C) XIAONIU66 CORPORATION 2016                                                      
 *    ALL RIGHTS RESERVED BY XIAONIU66 CORPORATION. THIS PROGRAM    
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY   
 * XIAONIU66 CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN       
 * PERMISSION OF XIAONIU66 CORPORATION. USE OF COPYRIGHT NOTICE   
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.                       
 *                  XIAONIU66 CONFIDENTIAL AND PROPRIETARY        
 *************************************************************************/

package com.xiaoniu.account.domain.result;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 预计到站时间提示结果.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/10/11 16:48
 */
public class OutExpectReachTimeRlt implements Serializable {

	//提现申请时间
	private String applyTime;
	//预计银行处理日渐
	private String expectBankDealTime;
	//预计到账时间
	private String expectReachTime;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getExpectBankDealTime() {
		return expectBankDealTime;
	}

	public void setExpectBankDealTime(String expectBankDealTime) {
		this.expectBankDealTime = expectBankDealTime;
	}

	public String getExpectReachTime() {
		return expectReachTime;
	}

	public void setExpectReachTime(String expectReachTime) {
		this.expectReachTime = expectReachTime;
	}
}
