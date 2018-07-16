package com.linkwee.web.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.linkwee.core.base.PaginatorSevReq;
import com.linkwee.core.base.PaginatorSevResp;
import com.xiaoniu.mybatis.paginator.domain.PageList;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;
import com.xiaoniu.mybatis.paginator.domain.Paginator;

/**
 * 
 * @描述：分页查询工具
 *
 * @author Bob
 * @时间  2015年8月17日上午11:24:59
 *
 */
public class PaginatorUtil {
	
	/**
	 * 分页组件查询结果转dubbo服务分页出参
	 * @param pageList
	 * @return
	 */
	public static <T>PaginatorSevResp<T> toPaginatorSevResp(PageList<T> pageList) {
		PaginatorSevResp<T> ret = new PaginatorSevResp<T>();
		if(pageList.isEmpty()||StringUtils.isEmpty(pageList.getPaginator())){
			ret.setPageIndex(0);
			ret.setPageSize(0);
			ret.setTotalCount(0);
			ret.setPageCount(0);
			ret.setDatas(pageList);
		}else{
			ret.setPageIndex(pageList.getPaginator().getPage());
			ret.setPageSize(pageList.getPaginator().getLimit());
			ret.setTotalCount(pageList.getPaginator().getTotalCount());
			ret.setPageCount(pageList.getPaginator().getTotalPages());
			ret.setDatas(pageList);
		}
		return ret;
	}
	
	
	/**
	 * 分页组件查询结果转dubbo服务分页出参
	 * @param pageList
	 * @return
	 */
	public static <T>PaginatorSevResp<T> getPaginatorSevResp(PageRequest request,List<T> pageList,int count) {
		PaginatorSevResp<T> ret = new PaginatorSevResp<T>();
		Paginator p = new Paginator(request.getPage(),request.getRows(),count);
		ret.setPageIndex(p.getPage());
		ret.setPageSize(p.getLimit());
		ret.setTotalCount(p.getTotalCount());
		ret.setPageCount(p.getTotalPages());
		ret.setDatas(pageList);
		return ret;
	}
	
	/**
	 * 获取空返回
	 * @param pageList
	 * @return
	 */
	public static <T>PaginatorSevResp<T> getEmptyResp(PageRequest request) {
		PaginatorSevResp<T> ret = new PaginatorSevResp<T>();
		ret.setPageIndex(request.getPage());
		ret.setPageSize(request.getRows());
		ret.setTotalCount(0);
		ret.setPageCount(0);
		ret.setDatas(new ArrayList<T>());
		return ret;
	}
	
	
	/**
	 * dubbo服务分页入参 转分页组件查询参数
	 * @param request
	 * @return
	 */
	public static PageRequest toPageRequest(PaginatorSevReq request){
		PageRequest pageRequest = new PageRequest();
		pageRequest.setPage(request.getPageIndex());
		pageRequest.setRows(request.getPageSize());
		pageRequest.setQuery(request.getQueryConditions());
		return pageRequest;
	}
	
}
