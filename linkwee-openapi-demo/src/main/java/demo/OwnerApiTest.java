package demo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.enums.OrgEnum;
import demo.enums.RequestTypeEnums;
import demo.utils.CommonUtils;

/**
 * 
 * @author liqimoon
 *
 */
public class OwnerApiTest {
	
	/**
	 * 整体说明
	 * ********该接口由机构提供**********
	 * 整体请使用junit测试调用，调用前请看下面修改参数说明
	 * 1：需根据不同的机构修改OrgEnum类下面的枚举类型,对应领会分配的ORG_NUMBER(机构编码),ORG_KEY(机构明钥),ORG_SECRET(机构私钥)
	 * 2：需根据不同的请求地址修改OpenApiTest类下面的常量openBaseUrl(请求路径),openOrgEnum(机构枚举,对应1)
	 */
	protected final static Logger LOGGER = LoggerFactory.getLogger(OwnerApiTest.class);
	/**
	 * 请求机构信息  请根据不同的机构进行修改
	 */
	private static OrgEnum orgEnum= OrgEnum.OPEN_LUJINSUO_WEB;
	/**
	 * 请求路径   需根据具体的请求地址作修改
	 */
	private static String orgBaseUrl = orgEnum.getThirdBaseUrL();
	/**
	 * 请求方式   需根据具体的请求方式作修改
	 */
	private static RequestTypeEnums orgRequestType = RequestTypeEnums.POST;
	
	/**
	 * 5.2 查询用户是否已存在接口
	 */
	@Test
	public void testIsExistInPlatform(){
		
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("mobile", "13417322420");
		parameters.put("userId", "11db98256efd4b8f88fa1016e432bd80");
		//发送请求
		CommonUtils.httpRequest(orgEnum,orgRequestType,orgBaseUrl+"/account/isExist",parameters);
	}	
	
	/**
	 * 5.3绑定合作平台账户接口
	 */
	@Test
	public void accountBind(){
		
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("userId", "11db98256efd4b8f88fa1016e432bd80");
		parameters.put("mobile", "13417322420");
		//发送请求
		CommonUtils.httpRequest(orgEnum,orgRequestType,orgBaseUrl+"/account/bind",parameters);
	}
	
	/**
	 * 5.7用户资产余额查询接口
	 */
	@Test
	public void accountAssert(){
		
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("orgAccount", "d64c7defdc7c429698b541b91911c085");
		parameters.put("mobile", "13691957334");
		//发送请求
		CommonUtils.httpRequest(orgEnum,orgRequestType,orgBaseUrl+"/account/assert",parameters);

	}
	
	/**
	 * 5.8 用户中心跳转
	 */
	@Test
	public void personal(){
		
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("orgAccount", "07a159d3d696e961de8abc4620");
		parameters.put("requestFrom", "wap");
		//发送请求
		CommonUtils.httpRequest(orgEnum,orgRequestType,orgBaseUrl+"/oms/personal",parameters);

	}
	
	/**
	 * 5.9 产品跳转
	 */
	@Test
	public void product(){
		
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("thirdProductId", "c6628450-e2f6-4fea-85dc-44001d9573c1");
		parameters.put("txId", "995e2df93fb3451186088158d95f3268");
		parameters.put("orgAccount", "DFH15915473248");
		parameters.put("requestFrom", "wap");
		//发送请求
		CommonUtils.httpRequest(orgEnum,orgRequestType,orgBaseUrl+"/oms/product",parameters);
	}
}
