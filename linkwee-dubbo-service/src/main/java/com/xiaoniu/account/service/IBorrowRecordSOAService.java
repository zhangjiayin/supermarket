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

package com.xiaoniu.account.service;

import com.xiaoniu.account.domain.BorrowRecordReq;
import com.xiaoniu.account.domain.ConfirmBorrowRecordReq;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.RecordRlt;

/**
 * 借款单服务.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/5/31 17:06
 */
public interface IBorrowRecordSOAService {

	/**
	 * 创建借款单
	 * @param req
	 * @return
	 */
	public CommonRlt<RecordRlt> createBorrowRecord(BorrowRecordReq req);

	/**
	 * 确认借款单
	 * @param req
	 * @return
	 */
	public CommonRlt<RecordRlt> confirmBorrowRecord(ConfirmBorrowRecordReq req);
}
