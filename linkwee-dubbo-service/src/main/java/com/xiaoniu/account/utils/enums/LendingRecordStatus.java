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
 * 借款单状态.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/5/23 10:44
 */
public enum LendingRecordStatus {

	STATUS_INIT				("0", "初始状态"),
	STATUS_OK				("1", "放款成功"),
	STATUS_FAIL				("2", "放款失败"),
	;

	private String type;
	private String desc;

	private LendingRecordStatus(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

	public static LendingRecordStatus getEnum(String code) {
		LendingRecordStatus[] arr = LendingRecordStatus.values();
		for (LendingRecordStatus tmp : arr) {
			if (code.equals(tmp.getType())) {
				return tmp;
			}
		}
		return null;
	}
}
