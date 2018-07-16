package com.linkwee.web.dao;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.ActPersonAddfeeTicket;
import com.linkwee.web.model.ActPersonAddfeeTicketSenduseDetail;
import com.linkwee.web.response.act.AddfeeTicketListResponse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： liqimoon
 * 
 * @创建时间：2018年04月12日 17:24:11
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActPersonAddfeeTicketMapper extends GenericDao<ActPersonAddfeeTicket,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActPersonAddfeeTicket> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

     /**
      * 个人加拥券列表
      * @param page
      * @return
      */
	List<AddfeeTicketListResponse> getPersonAddfeeTicketList(RowBounds page);

     /**
      * 更新个人加拥券
      * @param ticket
      */
	void updateAddFeeTicket(ActPersonAddfeeTicket ticket);

     /**
      * 批量发放个人加拥券
      * @param addfeeTicketList
      */
	void inserts(@Param("addfeeTickets")List<ActPersonAddfeeTicketSenduseDetail> addfeeTicketList);
 }
