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
 * 还款状态.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/5/24 11:14
 */
public enum RepaymentBillStatus {

	STATUS_INIT				("0", "初始状态"),
	STATUS_FREEZE			("1", "还款冻结成功"),
	STATUS_PROCESSING		("2", "还款中"),
	STATUS_OK				("3", "还款成功"),
	STATUS_FAIL				("4", "还款失败"),
	;

	private String type;
	private String desc;

	private RepaymentBillStatus(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

	public static RepaymentBillStatus getEnum(String code) {
		RepaymentBillStatus[] arr = RepaymentBillStatus.values();
		for (RepaymentBillStatus tmp : arr) {
			if (code.equals(tmp.getType())) {
				return tmp;
			}
		}
		return null;
	}
}
