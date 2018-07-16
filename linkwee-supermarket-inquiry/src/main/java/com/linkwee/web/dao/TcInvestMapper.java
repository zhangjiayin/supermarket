package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.web.model.CustomerInvestDetail;
import com.linkwee.web.model.CustomerInvestStatistics;

public interface TcInvestMapper {
	
	/**
	 * 统计用户在机构的投资信息
	 * @param platfrom 机构编码
	 * @param page
	 * @return
	 */
	public List<CustomerInvestStatistics> queryCustomerInvestStatistics(@Param("platfrom")String platfrom,@Param("nameOrMobile")String nameOrMobile,RowBounds page);
	
	/**
	 * 查询用户在机构投资详情
	 * @param platfrom 机构编码
	 * @param userId 用户编号
	 * @param page
	 * @return
	 */
	public List<CustomerInvestDetail> queryCustomerInvestDetail(@Param("platfrom")String platfrom,@Param("userId")String userId,RowBounds page);

}
