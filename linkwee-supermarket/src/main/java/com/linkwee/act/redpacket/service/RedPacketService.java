package com.linkwee.act.redpacket.service;

import java.util.List;

import com.linkwee.act.redpacket.model.ActRedpacketBriefDetailAndRule;
import com.linkwee.act.redpacket.model.ActRedpacketDetail;
import com.linkwee.api.request.act.RedpacketRequest;
import com.linkwee.api.request.act.SendRedPacketRequest;
import com.linkwee.api.request.cim.ProductRedPacketRequest;
import com.linkwee.api.response.act.RedpacketResponse;
import com.linkwee.api.response.cim.ProductDetailResponse;
import com.linkwee.api.response.cim.ProductPageList4Response;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.enums.ActicityRedPacketEnum;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.CrmUserInfo;

public interface RedPacketService {
	
	/**
	 * 查询客户可用红包数量
	 * @param userId
	 * @return
	 */
	Integer queryInvestorRedPacketCount(String userId);
	
	/**
	 * 查询客户红包信息 
	 * @param userId
	 * @param req
	 * @return
	 */
	PaginatorResponse<RedpacketResponse> queryInvestorRedPacket(String userId, RedpacketRequest req); 
	
	/**
	 * 查询理财师可派发红包数量
	 * @param userId
	 * @return
	 */
	Integer queryCfplannerRedPacketCount(String userId);
	
	/**
	 * 查询理财师红包信息
	 * @param userId
	 * @param req
	 * @return
	 */
	PaginatorResponse<RedpacketResponse> queryCfplannerRedPacket(String userId,RedpacketRequest req);
	
	/**
	 * 理财师发放红包
	 * @param userId
	 * @param sendRedPacketRequest
	 * @return
	 */
	BaseResponse sendRedPacket(String userId,SendRedPacketRequest sendRedPacketRequest) throws Exception;
	

	
	/**
	 * 客户注册送红包
	 * @param userInfo
	 */
	 void  customerRegisterRedPacekt(CrmUserInfo userInfo)throws Exception;
	 

	 
	 /**
	  * 理财师奖励红包
	  * @param userInfo
	  * @throws Exception
	  */
	 void lcsActicityRedPacket(String userId,ActicityRedPacketEnum acticityRedPacketEnum)throws Exception;
	 
	 /**
	  * 产品是否有红包标识
	  * @param products
	  * @return
	  */
	 <T extends ProductPageList4Response> void productRedPacketTag(List<T> products,String userId);
	 
	 /**
	  * 平台是否有红包标识
	  * @param products
	  * @return
	  */
	 void patformRedPacketTag(List<CimOrginfo> orgInfo,String userId);
	 
	 /**
	  * 产品红包数量
	  * @param products
	  * @return
	  */
	 int productRedPacketCount(ProductDetailResponse productDetail ,String userId);
	 
	 /**
	  * 产品红包
	  * @param userId
	  * @param req
	  * @param page
	  * @return
	  */
	 List<RedpacketResponse> productRedPacket(String userId,RedpacketRequest req,Page<RedpacketResponse> page);
	 
	 /**
	  * 平台红包数量
	  * @param products
	  * @return
	  */
	 int patformRedPacketCount(String patform,int model, String userId);
	 
	 /**
	  * 平台红包
	  * @param userId
	  * @param req
	  * @param page
	  * @return
	  */
	 List<RedpacketResponse> patformRedPacket(String userId,RedpacketRequest req,Page<RedpacketResponse> page);

	 /**
	  * 查询红包列表4.0
	  * @param redpacketRequest
	  * @return
	  */
	 PaginatorResponse<RedpacketResponse>  queryCfplannerRedPacket4(RedpacketRequest redpacketRequest);
	 
	 /**
	  * 查询红包列表数量4.0
	  * @param redpacketRequest
	  * @return
	  */
	Integer queryRedPacketCount4(RedpacketRequest redpacketRequest);

	/**
	 * 发放红包（4.5.0给理财师或客户）
	 * @param userIdByToken
	 * @param req
	 * @return
	 */
	BaseResponse sendRedPacketToCfp(String userIdByToken, SendRedPacketRequest req)throws Exception;

	/**
	 * 查询红包列表4.5.0
	 * @param userId
	 * @return
	 */
	PaginatorResponse<RedpacketResponse> queryCfplannerRedPacket5(PaginatorRequest request,String userId);

	/**
	 * 可使用红包数量4.5.0
	 * @param userId
	 * @return
	 */
	int queryRedPacketCount5(String userId);

	/**
	 * 用户最新获得的红包
	 * @param userId
	 * @return
	 */
	ActRedpacketDetail queryNewestRedPacket(String userId);

	/**
	 * 发放活动红包
	 * @param cfpUserId
	 * @param doubleEleven11
	 */
	void sendDoubleElevenRedpacket(String userId,ActicityRedPacketEnum acticityRedPacketEnum) throws Exception;

	/**
	 * 红包详情
	 * @param string
	 * @return
	 */
	RedpacketResponse redPacketDetail(String userId,String redpacketId);
	
	/**
	 * 查询当前用户对应产品可使用的红包
	 * @param productRedPacketRequest
	 * @return
	 */
	List<ActRedpacketBriefDetailAndRule> productRedPacket(ProductRedPacketRequest productRedPacketRequest);
}

