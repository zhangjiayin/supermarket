
package com.xiaoniu.account.comm.page;


/**
 * 分页结果集，除结果集外，还可以附带一个对象
 * @author 颜彩云
 *
 * @param <K>
 */
public class PageResultExt<K>  extends PageResult<K>{

	private static final long serialVersionUID = 1L;

	private Object extData;

	public Object getExtData() {
		return extData;
	}

	public void setExtData(Object extData) {
		this.extData = extData;
	}
	
}
