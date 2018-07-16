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

package com.xiaoniu.account.utils;

import java.util.Date;

/**
 * 请求参数验证工具类.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/9/18 15:36
 */
public class ReqValidateUtil {

	/**
	 * 验证字符串是不是日期格式
	 * @param str
	 * @return
	 */
	public static boolean isValidDate(String str) {
		Date date = DateUtils.parse(str, DateUtils.FORMAT_SHORT);
		return date == null ? false : true;
	}
}
