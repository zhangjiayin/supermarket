package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.model.fee.FeePay;
import com.linkwee.web.model.fee.StatusSetReq;
import com.linkwee.web.request.FeeRequest;

public interface FeePayService {
	public ReturnCode feeDataImport(List<FeePay> datalist);

	public ReturnCode feePay(List<FeePay> entitylist);

	public DataTableReturn feePayResultQuery(FeeRequest req);

	public ReturnCode statusReset(StatusSetReq req);

	public void updateFeePayStatus(List<FeePay> list);
	
	public List<FeePay> getUnPayList(String month,
			List<String> saleUserNo);

	/**
	 * 
	 * Title: ResultBean Description:佣金发放基础平台返回结果 Company: Copyright (c)
	 * 深圳市前海领会科技有限公司-版权所有
	 * 
	 * @author jinbo.fu
	 * @date 2016年6月23日上午10:04:02
	 */
	public enum ResultBean {
		NO_FOUND_SALE_USER("141013", "没有找到推荐业务员或理财师"), SALE_USER_CUSTOMER_NOT_MATCH(
				"141014", "业务员与客户不匹配"), PRODUCT_IS_NULL("141015", "产品不存在"), GET_CUSTOMERID_FAIL(
				"141016", "查询客户id失败"), CUSTOMER_REG_SELF("141017",
				"用户自主注册，不需要计算佣金"), FAIL_TOO_MANY_TIMES("141018", "失败次数过多，结束处理"), SYSTEM_ERROR(
				"149999", "系统异常");

		ResultBean(String code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		private String code;
		private String desc;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
}
