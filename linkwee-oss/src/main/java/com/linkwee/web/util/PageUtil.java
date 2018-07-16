package com.linkwee.web.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * 项目名称：宝箱活动
 * 
 * 类名称：PageUtil
 * 
 * 类描述： 分页数据
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年8月13日 下午2:37:57
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
public class PageUtil<T>  implements Serializable {


	private static final long serialVersionUID = -2579226512967490994L;
	
	private List<T> datas = new ArrayList<T>(); // 数据
	private Integer totalCount; // 总条数
	private Integer pageIndex = 1;// 当前页数
	private Integer pageSize = 200; //每页显示的条数
	private Integer pageCount;// 总页数
	
	
	


	public PageUtil() {
		super();
	}

	public PageUtil(T t) {
	}
	
	
	
	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
		if (null == totalCount || null == pageSize) {
			this.pageCount = new Integer(1);
		} else {
			if (totalCount % pageSize == 0) {
				//计算总页数
				this.pageCount =  (totalCount / pageSize);
			} else {
				this.pageCount =  (totalCount / pageSize) + 1;
			}
		}
	}

	

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public String toString() {
		return "PageUtil [datas=" + datas + ", totalCount=" + totalCount
				+ ", pageIndex=" + pageIndex + ", pageSize=" + pageSize
				+ ", pageCount=" + pageCount + "]";
	}
	
	

	
}
