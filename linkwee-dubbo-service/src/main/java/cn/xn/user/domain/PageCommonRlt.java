package cn.xn.user.domain;


public class PageCommonRlt<T> extends CommonRlt<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1298492507295210421L;

	private Integer total;

	public PageCommonRlt() {
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
