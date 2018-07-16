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

import com.xiaoniu.account.comm.page.PageResult;
import com.xiaoniu.account.domain.CommPropertyReq;
import com.xiaoniu.account.domain.FindCommPropertyReq;
import com.xiaoniu.account.domain.result.CommPropertyRlt;
import com.xiaoniu.account.domain.result.CommonRlt;
import com.xiaoniu.account.domain.result.EmptyObject;

/**
 * 通用属性配置接口.
 *
 * @author lipw
 * @version 1.0
 * @date 2016/10/14 14:59
 */
public interface ICommPropertyConfigService {

	/**
	 * 添加或更新通用属性
	 */
	public CommonRlt<EmptyObject> saveOrUpdateCommProperty(CommPropertyReq req);

	/**
	 * 通用属性分页查询
	 * @param req
	 * @return  分页对象
	 */
	public CommonRlt<PageResult<CommPropertyRlt>> commPropertyListPage(FindCommPropertyReq req);
}

