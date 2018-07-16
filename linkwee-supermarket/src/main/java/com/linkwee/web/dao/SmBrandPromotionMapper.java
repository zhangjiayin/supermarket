package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.crm.SmBrandPostersType;
import com.linkwee.web.model.crm.SmBrandPromotion;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年06月28日 18:47:51
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface SmBrandPromotionMapper extends GenericDao<SmBrandPromotion,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<SmBrandPromotion> selectBySearchInfo(@Param("useType")String useType,@Param("appType")String appType,@Param("typeValue")String typeValue,@Param("dt")DataTable dt,RowBounds page);
    
	/**
     * 顶置-个人海报
     * */
	void overheadSmBrandPromotion();

	/**
     * 顶置
     * */
	void overheadbpSmBrandPromotion();

	List<SmBrandPromotion> selectAllListByCondition(SmBrandPromotion smBr);

	/**
     * 4.5.3海报类型查询
     * */
	List<SmBrandPostersType> selectAllBrandPostersTypeList();

	/**
     * 4.5.3推广海报
     * */
	List<SmBrandPromotion> selectBrandPostersList(@Param("type")String type);

}
