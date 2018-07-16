package demo.request;

/**
 * 
 * @描述：api分页请求参数
 *
 * @author liqi
 * @时间  2017年7月31日上午10:15:22
 *
 */
public class PaginatorRequest{

	/**
	 * 第几页
	 */
	private Integer pageIndex =1;
	
	/**
	 * 页面大小
	 */
	private Integer pageSize = 10;

	
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
}
