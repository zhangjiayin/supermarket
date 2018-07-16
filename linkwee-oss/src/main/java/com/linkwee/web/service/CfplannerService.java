package com.linkwee.web.service;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.Cfplanner;
import com.linkwee.web.response.LcsDetailResp;

/**
 *  业务接口
 * 
 * @author Mignet
 * @since 2014年6月10日 下午4:15:01
 **/
public interface CfplannerService extends GenericService<Cfplanner, String> {
    /**

	/**
	 * 查询列表,为data tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 理财师销售与收益列表查询
	 * @param mobile
	 * @return
	 * @throws Exception
     */
	public DataTableReturn findCfpSaleList(String mobile,DataTable dataTable) throws Exception;

	/**
	 * 查询理财师当前正在投资的客户
	 * @param mobile
	 * @param dataTable
	 * @return
	 * @throws Exception
     */
	public DataTableReturn findCurrentInvertorList(String mobile,String searchText,DataTable dataTable) throws Exception;

	/**
	 * 查询理财师当前正在投资的客户总的金额
	 * @param mobile
	 * @return
	 * @throws Exception
     */
	public Double findCurrentInvertorAmount(String mobile,String searchText) throws Exception;


	/**
	 * 查询理财师下一级与二级理财师的信息与推荐收益情况
	 * @param lcsDetailResp
	 * @param dataTable
     * @return
     */
	public DataTableReturn queryCfpTeamList(LcsDetailResp lcsDetailResp,DataTable dataTable);

	/**
	 * 查询理财师一级客户投资情况不包括非直属投资客户
	 * @param detailResp
	 * @param dataTable
     * @return
     */
	public DataTableReturn queryCfpCustomerProfitList(LcsDetailResp detailResp, DataTable dataTable);



}
