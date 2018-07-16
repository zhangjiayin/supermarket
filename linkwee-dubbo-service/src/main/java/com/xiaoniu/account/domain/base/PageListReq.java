package com.xiaoniu.account.domain.base;

import com.xiaoniu.account.utils.ReqValidateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 后台查询基础请求参数
 */
public class PageListReq implements Serializable {

	private static final long serialVersionUID = 12178115888236383L;
	
	/** 开始时间 格式：yyyy-MM-dd*/
	private String beginTime;
	
	/** 结束时间 格式：yyyy-MM-dd*/
	private String endTime;
	
	/** 分页当前页 */
	@NotNull
	private Integer currentPage;
	
	/** 分页行数 */
	@NotNull
	private Integer pageSize;
	
	/** 签名编码 */
	@NotNull
	private String charset;
	
	/** 签名 */
	@NotNull
	private String sign;
	
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * 校验日期参数
	 * @return
	 */
	public boolean verifyDateParams(){
		if (StringUtils.isNotEmpty(this.getBeginTime()) && !ReqValidateUtil.isValidDate(
				this.getBeginTime())) {
			return false;
		}
		if (StringUtils.isNotEmpty(this.getEndTime()) && !ReqValidateUtil.isValidDate(this.getEndTime())) {
			return false;
		}
		return true;
	}

	/**
	 * 设置日期参数
	 * @return
	 */
	public void initQueryDateParams(){
		if (StringUtils.isNotEmpty(this.getBeginTime())) {
			this.setBeginTime(this.getBeginTime() + " 00:00:00");
		}
		if (StringUtils.isNotEmpty(this.getEndTime())) {
			this.setEndTime(this.getEndTime() + " 23:59:59");
		}
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
