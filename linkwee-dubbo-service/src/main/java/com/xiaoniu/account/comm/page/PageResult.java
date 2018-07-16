
package com.xiaoniu.account.comm.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description: 分页结果集
 * @author 周锋恒
 * @date 2015年8月9日
 * @param <T>
 *
 */
public class PageResult<T>  implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 总记录数
	 */
	private int totalRecord;
	

	/**存放结果集*/
	private List<T> result = new ArrayList<T>();

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}
	
}
