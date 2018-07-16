package com.linkwee.xoss.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.core.util.DateUtils;
import com.linkwee.openApi.enums.RequestTypeEnums;
import com.linkwee.openApi.request.InvestRecordReq;
import com.linkwee.openApi.request.ProductPushRequest;
import com.linkwee.openApi.request.ProductUpdatRequest;
import com.linkwee.openApi.request.RepaymentRecordReq;
import com.linkwee.openApi.response.BaseResponse;
import com.linkwee.openApi.response.ErrorResponse;
import com.linkwee.openApi.utils.OpenApiCommonUtils;
import com.linkwee.openplatform.common.CommonProductDateHandle;
import com.linkwee.openplatform.xyb.XYBProductDateHandle;
import com.linkwee.web.model.CimProductAddPull;
import com.linkwee.web.model.CimProductInvestRecordPull;
import com.linkwee.web.model.CimProductRepaymentRecordPull;
import com.linkwee.web.model.CimProductUpdatePull;
import com.linkwee.web.model.SysThirdkeyConfigPull;
import com.linkwee.web.push.vo.ResultVO;
import com.linkwee.web.push.vo.ResultVOProduct;
import com.linkwee.web.service.CimProductAddPullService;
import com.linkwee.xoss.util.StringUtils;

/**
 * 
 * @author liqimoon
 *
 */
@Component
public class OpenApiHelper{
	
	protected final static Logger LOGGER = LoggerFactory.getLogger(OpenApiHelper.class);
	/**
	 * 请求方式   需根据具体的请求方式作修改
	 */
	private static RequestTypeEnums openRequestType = RequestTypeEnums.POST;
	
	private String linkweeBaseUrL="http://market.toobei.com/rest/openapi";
	
	@Resource
	private ConfigHelper configHelper;
	@Resource
	private CimProductAddPullService cimProductAddPullService;
	@Resource
	private CommonProductDateHandle commonProductDateHandle;
	@Resource
	private XYBProductDateHandle xybProductDateHandle;
	
	@PostConstruct
	public void init(){
		Map<String, String> configMap = configHelper.getValuesByType(0, "LINKWEE");
	    this.linkweeBaseUrL = configMap.get("linkweeBaseUrL");
	}
	
	/**
	 * 产品添加推送接口
	 * @param sysThirdkeyConfigPull	第三方配置
	 * @param cimProductAddPull	待推送的产品
	 * @return
	 */
	public ResultVOProduct pushProduct(SysThirdkeyConfigPull sysThirdkeyConfigPull,CimProductAddPull cimProductAddPull) {
		ResultVOProduct resultVOProduct = null;
		try {	
			ProductPushRequest productPushRequest = new ProductPushRequest();
			BeanUtils.copyProperties(cimProductAddPull, productPushRequest);
			LOGGER.info("########调用领会产品【推送】接口########");
			String result = OpenApiCommonUtils.httpRequest(sysThirdkeyConfigPull,openRequestType,linkweeBaseUrL+"/product/pushProduct",productPushRequest);//发送请求
			resultVOProduct = createResultVOProduct(result,cimProductAddPull.getId());
		} catch (Exception e) {
			resultVOProduct =  new ResultVOProduct(cimProductAddPull.getId(), 1,"产品添加推送到领会异常");
		}
		return resultVOProduct;
	}
	
	/**
	 * 产品更新推送接口
	 * @param sysThirdkeyConfigPull
	 * @param cimProductUpdatePullNew
	 * @return
	 */
	public ResultVOProduct updateProduct(SysThirdkeyConfigPull sysThirdkeyConfigPull,CimProductUpdatePull cimProductUpdatePull) {
		ResultVOProduct resultVOProduct = null;
		try {	
			ProductUpdatRequest productUpdatRequest = new ProductUpdatRequest();
			BeanUtils.copyProperties(cimProductUpdatePull, productUpdatRequest);
			String result = OpenApiCommonUtils.httpRequest(sysThirdkeyConfigPull,openRequestType,linkweeBaseUrL+"/product/updateProduct",productUpdatRequest);//发送请求
			resultVOProduct = createResultVOProduct(result,cimProductUpdatePull.getId());
		} catch (Exception e) {
			resultVOProduct =  new ResultVOProduct(cimProductUpdatePull.getId(), 1,"产品更新推送到领会异常");
		}
		return resultVOProduct;
	}
	
	/**
	 * 投资记录推送接口
	 */
	public ResultVO pushInvestRecord(SysThirdkeyConfigPull thirdkey,CimProductInvestRecordPull investRecordPull){
		ResultVO resultVO = null; 
		try{
			InvestRecordReq investRecordReq =null;
			investRecordReq = new InvestRecordReq();
			investRecordReq.setInvestId(investRecordPull.getInvestId());
			investRecordReq.setUserId(investRecordPull.getUserId());
			String txId= StringUtils.isNotBlank(investRecordPull.getTxId())?investRecordPull.getTxId():"none";
			investRecordReq.setTxId(txId);
			investRecordReq.setInvestStartTime(DateUtils.format(investRecordPull.getInvestStartTime()) );
			investRecordReq.setInvestEndTime(DateUtils.format(investRecordPull.getInvestEndTime()) );
			investRecordReq.setProductId(investRecordPull.getProductId());
			investRecordReq.setInvestAmount(investRecordPull.getInvestAmt());
			investRecordReq.setProfit(investRecordPull.getProfit());
			investRecordReq.setPlatfromId(investRecordPull.getPlatfrom());
			LOGGER.info("投资记录推送接口：investRecordReq={}",JSONObject.toJSONString(investRecordReq));
			String result = OpenApiCommonUtils.httpRequest(thirdkey,openRequestType,linkweeBaseUrL+"/invest/pushInvestRecord",investRecordReq);//发送请求
			
			//处理推送投资记录错误信息
			result = PushInvestRecordResultInterceptor(result,thirdkey,investRecordReq);
			
			resultVO = createResultVO(result,investRecordPull.getInvestId());
		}catch(Exception e){
			LOGGER.error("pushInvestRecord Exception OrgEnum={},repaymentRecordPull={},Exception={}", new Object[]{thirdkey,investRecordPull,e});
			LOGGER.error("pushInvestRecord Exception", e);
			resultVO =  new ResultVO(investRecordPull.getInvestId(), 1,"异常");
		}
		return resultVO;
	}

	/**
	 * 5.6投资回款推送接口
	 */
	public ResultVO pushRepaymentRecord(SysThirdkeyConfigPull thirdkey,CimProductRepaymentRecordPull repaymentRecordPull){
		ResultVO resultVO = null; 
		try{
			RepaymentRecordReq repaymentRecordReq = new RepaymentRecordReq();
			repaymentRecordReq.setUserId(repaymentRecordPull.getUserId());
			repaymentRecordReq.setRepaymentId(repaymentRecordPull.getRepaymentId());
			repaymentRecordReq.setInvestId(repaymentRecordPull.getInvestId());
			repaymentRecordReq.setProductId(repaymentRecordPull.getProductId());
			repaymentRecordReq.setRepaymentAmount(repaymentRecordPull.getRepaymentAmount());
			repaymentRecordReq.setProfit(repaymentRecordPull.getProfit());
			repaymentRecordReq.setRepaymentTime(DateUtils.format(repaymentRecordPull.getRepaymentTime()));
			repaymentRecordReq.setStatus(repaymentRecordPull.getStatus());
			LOGGER.info("投资回款推送接口：repaymentRecordReq={}",JSONObject.toJSONString(repaymentRecordReq));
			String result = OpenApiCommonUtils.httpRequest(thirdkey,openRequestType,linkweeBaseUrL+"/invest/pushRepaymentRecord",repaymentRecordReq);//发送请求
			resultVO = createResultVO(result,repaymentRecordPull.getRepaymentId());
		}catch(Exception e){
			LOGGER.error("pushRepaymentRecord Exception OrgEnum={},repaymentRecordPull={},Exception={}", new Object[]{thirdkey,repaymentRecordPull,e});
			resultVO =  new ResultVO(repaymentRecordPull.getInvestId(), 1,"异常");
		}
		return resultVO;
	}
	
	private static ResultVO createResultVO(String result,String bizId){
		ResultVO resultVO = null; 
		BaseResponse response = JSONObject.parseObject(result, BaseResponse.class);
		if("0".equals(response.getCode())){
			 resultVO = new ResultVO(bizId, 0, response.getMsg());
		}else{
			ErrorResponse 	errorResponse = JSONObject.parseObject(result, ErrorResponse.class);
			List<BaseResponse> responses = errorResponse.getErrors();
			if(CollectionUtils.isEmpty(responses))
			{
				resultVO = new ResultVO(bizId, 1, response.getMsg());
			}else{
				StringBuilder sb = new StringBuilder(errorResponse.getMsg());
				for (BaseResponse baseResponse : responses) {
					sb.append('|');
					sb.append(baseResponse.getMsg().replace("'", ""));
				}
				resultVO = new ResultVO(bizId, 1, sb.toString());
			}
		}
		return resultVO;
	}
	
	private static ResultVOProduct createResultVOProduct(String result,Integer bizId){
		ResultVOProduct resultVOProduct = null; 
		BaseResponse response = JSONObject.parseObject(result, BaseResponse.class);
		if("0".equals(response.getCode())){
			resultVOProduct = new ResultVOProduct(bizId, 0, response.getMsg());
		}else{
			ErrorResponse 	errorResponse = JSONObject.parseObject(result, ErrorResponse.class);
			List<BaseResponse> responses = errorResponse.getErrors();
			if(CollectionUtils.isEmpty(responses))
			{
				resultVOProduct = new ResultVOProduct(bizId, 1, response.getMsg());
			}else{
				StringBuilder sb = new StringBuilder(errorResponse.getMsg());
				for (BaseResponse baseResponse : responses) {
					sb.append('|');
					sb.append(baseResponse.getMsg().replace("'", ""));
				}
				resultVOProduct = new ResultVOProduct(bizId, 1, sb.toString());
			}
		}
		return resultVOProduct;
	}
	
	
	/**
	 * 处理推送投资记录错误信息
	 * @param result
	 * @return
	 */
	private String PushInvestRecordResultInterceptor(String result,SysThirdkeyConfigPull sysThirdkeyConfigPull,InvestRecordReq investRecordReq) {
		
		LOGGER.info("开放平台返回处理推送投资记录错误信息：result={}",result);
		try {
			/**
			 * 若产品不存在,单独拉取产品信息并推送投资记录
			 */		
			if(result.indexOf("无效的平台产品") != -1){
				LOGGER.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				LOGGER.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^处理推送投资记录错误信息,无效的平台产品拉取产品重新补推一下^^^^^^^^^^^^^^^^^^^^^^^^");
				LOGGER.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				String orgNumber = sysThirdkeyConfigPull.getOrgNumber();
				String thirdProductId = investRecordReq.getProductId();
				
				CimProductAddPull cimProductAddPullN = new CimProductAddPull();
				cimProductAddPullN.setThirdProductId(thirdProductId);;
				cimProductAddPullN.setOrgNumber(orgNumber);
				cimProductAddPullN = cimProductAddPullService.selectOne(cimProductAddPullN);
				
				//确实没拉取到产品  重新拉取并添加到产品新增表中
				if(cimProductAddPullN == null){
					CimProductAddPull cimProductAddPullf = null;
					if(orgNumber.equals("OPEN_XINYONGBAO_WEB")){//信用宝单独处理
						cimProductAddPullf = xybProductDateHandle.getProductById(orgNumber,thirdProductId);
					} else {
						cimProductAddPullf = commonProductDateHandle.getProductById(orgNumber,thirdProductId);
					}
					if(cimProductAddPullf != null){
						cimProductAddPullf.setStatus(1);//必需设置成在售才能推送投资记录,待重新拉取产品会更新成售罄[实际其实很可能产品已售罄]
						cimProductAddPullf.setUpdateStatus(1);//1-待执行添加
						cimProductAddPullf.setCreator(orgNumber);
						cimProductAddPullf.setCreateTime(DateUtils.getNow());
						cimProductAddPullf.setRemark("处理推送投资记录添加的产品");
						cimProductAddPullService.insert(cimProductAddPullf);
						
						LOGGER.info("无效的平台产品	再次拉取产品重新补推一下,cimProductAddPullf={}",cimProductAddPullf);
						//推送产品信息
						List<ResultVOProduct> resultVOListProductAdd = new ArrayList<ResultVOProduct>();
						ResultVOProduct resultVOProduct = pushProduct(sysThirdkeyConfigPull, cimProductAddPullf);
						resultVOListProductAdd.add(resultVOProduct);
						if(CollectionUtils.isNotEmpty(resultVOListProductAdd)){
							try{
								//更新推送记录状态
								cimProductAddPullService.updateProductAddPullStatus(resultVOListProductAdd);
							}catch(Throwable e){
								LOGGER.error("更新产品新增推送记录状态异常,ResultVOProduct={}",JSONObject.toJSONString(resultVOListProductAdd),e);
							}
						}
						
						//再次发送请求 推送投资记录信息
						LOGGER.info("无效的平台产品     再次发送请求 推送投资记录信息,investRecordReq={}",investRecordReq);
						result = OpenApiCommonUtils.httpRequest(sysThirdkeyConfigPull,openRequestType,linkweeBaseUrL+"/invest/pushInvestRecord",investRecordReq);
					} else {
						LOGGER.info("根据第三方产品id 拉取产品返回为null orgNumber={},thirdProductId={}",orgNumber,thirdProductId);
					}
				}		
			}		
		} catch (Exception e) {
			LOGGER.error("处理推送投资记录错误信息异常",e);
		}
		return result;
	}	
}
