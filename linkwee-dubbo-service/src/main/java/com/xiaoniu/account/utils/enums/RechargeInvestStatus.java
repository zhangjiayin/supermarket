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

package com.xiaoniu.account.utils.enums;

/**
 * 充值投资状态枚举.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/4/22 14:49
 */
public enum RechargeInvestStatus {

	STATUS_REC_INVEST_YES_LESS_THREE_TIMES		(1, 	"充值已投资提现小于等于3次"),
	STATUS_REC_INVEST_NO						(2, 	"充值未投资"),
	STATUS_REC_INVEST_YES_ABOVE_THREE_TIMES		(3, 	"充值已投资提现大于3次");

	private int type;
	private String desc;

	private RechargeInvestStatus(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public int getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

	public static RechargeInvestStatus getEnum(String code) {
		RechargeInvestStatus[] arr = RechargeInvestStatus.values();
		for (RechargeInvestStatus tmp : arr) {
			if (code.equals(tmp.getType())) {
				return tmp;
			}
		}
		return null;
	}
}
