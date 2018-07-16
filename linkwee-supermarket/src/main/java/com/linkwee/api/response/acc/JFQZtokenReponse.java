package com.linkwee.api.response.acc;

import java.io.Serializable;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： chenjl
 * 
 * @创建时间：2017年12月21日 14:35:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class JFQZtokenReponse implements Serializable {
	
	private static final long serialVersionUID = -6941427020761839097L;
	
    /**
     *	玖富轻舟token
     */
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}

