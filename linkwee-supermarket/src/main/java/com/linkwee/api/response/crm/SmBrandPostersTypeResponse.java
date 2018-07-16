package com.linkwee.api.response.crm;

import java.util.List;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.web.model.crm.SmBrandPostersType;

/**
 * @描述：v4.5.3个人品牌推广
 */
public class SmBrandPostersTypeResponse extends BaseEntity {
	private static final long serialVersionUID = 3405963387464644389L;

	public SmBrandPostersTypeResponse() {

	}
	
	/**
	 * 图片类型
	 */
	private List<SmBrandPostersType> typeList;

	public List<SmBrandPostersType> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<SmBrandPostersType> typeList) {
		this.typeList = typeList;
	}
	
}
