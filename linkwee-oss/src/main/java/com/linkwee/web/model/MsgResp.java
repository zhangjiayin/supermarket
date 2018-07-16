package com.linkwee.web.model;


/**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年10月27日 09:41:37
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class MsgResp extends Msg {
	private static final long serialVersionUID = 8411648168714991468L;

	/**
	 * 是否已读 1已读,0未读
	 */
	private Integer read;

	public Integer getRead() {
		return read;
	}

	public void setRead(Integer read) {
		this.read = read;
	}


}
