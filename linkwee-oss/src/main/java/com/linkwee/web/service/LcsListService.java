package com.linkwee.web.service;

import java.util.List;
import java.util.Map;

import com.linkwee.core.base.PaginatorSevReq;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.model.CfpCancelValideModel;
import com.linkwee.web.model.TorginfoModel;
import com.linkwee.web.model.UnconventionalRecord;
import com.linkwee.web.response.LcsCustomeDetailResp;
import com.linkwee.web.response.LcsDetailResp;
import com.linkwee.web.response.LcsTeamDetailResp;

/**
 * 
 * @描述：理财师销列表
 *
 * @author ch
 * @时间  2016年4月8日下午5:43:30
 *
 */
public interface LcsListService {
	
	public enum CfgLevel{
		PROBATION("试用期理财师"),

		INTERNSHIP("实习理财师"), JUNIOR("初级理财师"),

		MIDDLE("中级理财师"), HIGH("高级理财师"),

		SENIOR("资深理财师"), SUPER("超级理财师");
		
		private String levelStr;
		
		private CfgLevel(String levelStr){
			this.levelStr = levelStr;
		}
		
		public static String getCfgLevel(String code){
			for (CfgLevel cfgLevel : CfgLevel.values()) {
				if(cfgLevel.name().equalsIgnoreCase(code))return cfgLevel.levelStr;
			}
			return "";
		}
		
	}
	
	
	
	/**
	 * 导出理财师数据
	 * @param map
	 * @return
	 */
	public Map<String, Object> exportLcsList(Map<String, Object> map);
	
	/**
	 * 导出理财师团队
	 * @param map
	 * @return
	 */
	public Map<String, Object> exportLcsTeamList(Map<String, Object> map);
	
	/**
	 * 导出理财师客户
	 * @param map
	 * @return
	 */
	public Map<String, Object> exportLcsCustomerList(Map<String, Object> map);
	
	
	
	
	
	
	
	/**
	 * 查询理财师列表
	 * @param request
	 * @return
	 */
	public DataTableReturn queryLcsList(PaginatorSevReq request);
	
	/**
	 * 查询理财师详情,根据手机号码
	 * @param mobile
	 * @return
	 */
	public LcsDetailResp queryLcsDetail(String mobile);


	/**
	 * 获得理财师信息，不包括上级记录
	 * @param mobile
	 * @return
     */
	public LcsDetailResp queryLcsInfo(String mobile);
	
	
	/**
	 * 退出理财师
	 * @param mobile
	 */
	public void exitLcs(String mobile);
	
	/**
	 * 查询理财师团队列表
	 * @param request
	 * @return
	 */
	public PaginatorSevResp<LcsTeamDetailResp> queryLcsTeamList(PaginatorSevReq request);
	
	/**
	 * 解除理财师绑定
	 * @param lcsNumber
	 */
	public void unbindByLcs(String mobile);
	
	/**
	 * 查询理财师客户列表
	 * @param request
	 */
	public DataTableReturn queryLcsCustomerList(PaginatorSevReq request);
	
	/**
	 * 解除客户与理财师的绑定
	 * @param customMobile
	 * @param lcsNumber
	 */
	public void unbindByCustomer(String customerMobile,String lcsNumber);
	
	/**
	 * 更换理财师组织机构
	 * @param lcsNumber
	 * @param department
	 */
	public void replaceLcs(String lcsNumber,String department);

	public CfpCancelValideModel queryValidCancelCFP(String mobile);


	public List<UnconventionalRecord> queryCfpLevelOptRecord(UnconventionalRecord unconventionalRecord);

	/**
	 * 查子级组织机构列表
	 * @param torginfoModel
	 * @return
     */
	public List<TorginfoModel>  findTorgginNodeListByParentId(TorginfoModel torginfoModel);

	public boolean removeSaleUserHandImage(String mobile) throws Exception;
	
	
	
	
}
