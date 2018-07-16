package com.linkwee.web.service;


import java.util.List;

import com.linkwee.core.base.ErrorCode;
import com.linkwee.core.base.PaginatorSevReq;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.model.Advertisement;
import com.linkwee.web.model.news.AdvertisementListResp;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年10月17日 15:18:02
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface AdvertisementService{
	public static enum Error implements ErrorCode{
		SESSION_EXPIRE(142001, "会话已过期，请重新登录"),
		DB_ERROR(141005, "系统异常");
		Error(int code,String message){
			this.code = code;
			this.message = message;
		}
		private int code = 0;
		private String message = "";
		public int getCode() {
			return code;
		}
		public String getMessage() {
			return message;
		}
	}
	
	public List<Advertisement> list(Advertisement advertisement);
	public PaginatorSevResp<Advertisement> queryAdvList(PaginatorSevReq pageRequest);
	public Advertisement findAdvDtl(String fid);
	public ReturnCode DeleteAdv(Integer fid);
	public ReturnCode SaveAdv(Advertisement adv) ;
	public ReturnCode updateAdv(Advertisement adv);
	public PaginatorSevResp<AdvertisementListResp> queryAdvPageList(PaginatorSevReq pageRequest);
	public DataTableReturn findAdvList(Advertisement pageRequest,DataTable dataTable) throws Exception;
	
}
