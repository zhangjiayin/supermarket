package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.mc.SmClassroom;
 /**
 * 
 * @描述： SmClassroomService服务接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2016年11月04日 16:27:24
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmClassroomService extends GenericService<SmClassroom,Long>{

	/**
	 * 查询SmClassroom列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(SmClassroom classroom,DataTable dataTable);
	
	/**
	 * 修改课堂
	 */
	void updateClassroom(SmClassroom smClassroom);
	
	/**
	 * 顶置
	 */
	void overheadClassroom();

}
