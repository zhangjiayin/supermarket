package com.xiaoniu.account.domain.result;

import com.xiaoniu.account.utils.enums.ResultMsgEnum;


/**
 * 
 * @Description: 通用返回对象
 * @author 周锋恒
 * @date 2015年7月24日
 *
 */
public class CommonRltExt<T> extends CommonRlt<T> {

	private static final long serialVersionUID = -8646861797545039280L;
	
	private Integer total;
	
	private T rows;
	
	public CommonRltExt(){}
	
	public CommonRltExt(ResultMsgEnum errorMsgEnum){
		super(errorMsgEnum);
	}
	
	
	public CommonRltExt(Integer total, T rows) {
		this.total = total;
		this.rows = rows;
	}


	public Integer getTotal() {
		return total;
	}


	public void setTotal(Integer total) {
		this.total = total;
	}


	public Object getRows() {
		return rows;
	}


	public void setRows(T rows) {
		this.rows = rows;
	}
	
	
}
