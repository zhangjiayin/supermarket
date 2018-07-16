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
 * 还款类型枚举.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/8/30 13:48
 */
public enum RepaymentTypeEnum {

	FULL_BALANCE				(1, 	"全额余额还款"),
	WITHHOLDING_BALANCE			(2, 	"部分余额与代扣"),
	;


	private Integer type;

	private String desc;

	private RepaymentTypeEnum(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public Integer getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

	public static RepaymentTypeEnum getEnum(Integer type) {
		RepaymentTypeEnum[] arr = RepaymentTypeEnum.values();
		for (RepaymentTypeEnum tmp : arr) {
			if (type == tmp.getType()) {
				return tmp;
			}
		}
		return null;
	}

}
