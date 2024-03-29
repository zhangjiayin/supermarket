package demo.response;

import java.util.List;

/**
 * 
 * 描述：分页返回类
 *
 * @创建人： liqi
 *
 */
public class PaginatorResponse<T>{
	
	/**
	 * 页码
	 */
	private int pageIndex;
	/**
	 * 页面大小
	 */
	private int pageSize;
	/**
	 * 总页数
	 */
	private int pageCount;
	/**
	 * 数据总条数
	 */
	private int totalCount;
	/**
	 * 数据
	 */
	private List<T> datas ;
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
}
