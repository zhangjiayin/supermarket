package com.linkwee.api.request.cim;

import com.linkwee.core.base.api.PaginatorRequest;

public class CimThumbsUpRequest extends PaginatorRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**
     *分类id
     */
	private int id;
	
	private String image;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
