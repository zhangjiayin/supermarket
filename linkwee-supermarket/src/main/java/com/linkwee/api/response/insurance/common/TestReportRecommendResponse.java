package com.linkwee.api.response.insurance.common;

import java.util.List;

public class TestReportRecommendResponse {

	/**
	 * 保险推荐列表
	 */
	private List<ReportRecommendResponse> reportRecommendResponseList;

	public List<ReportRecommendResponse> getReportRecommendResponseList() {
		return reportRecommendResponseList;
	}

	public void setReportRecommendResponseList(
			List<ReportRecommendResponse> reportRecommendResponseList) {
		this.reportRecommendResponseList = reportRecommendResponseList;
	}
}
