package com.linkwee.test.job;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linkwee.test.TestSupport;
import com.linkwee.web.service.AcWithdrawApplyService;
import com.linkwee.web.service.WeiXinMsgService;

public class WeiXinMsgServiceTest extends TestSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeiXinMsgServiceTest.class);
	
	@Resource
	private WeiXinMsgService weiXinMsgService;
	
	@Resource
	private AcWithdrawApplyService acWithdrawApplyService;
	
	@Test
	public void process() {
		System.out.println("-----------------WeiXinMsgService-------------");
		 try {
			 weiXinMsgService.updateWeiXinAccToken();
				
			weiXinMsgService.updateWeiXinAccTokenLieCai();
			} catch (Exception e) {
				LOGGER.error(">>>>>>>>>>>>>>更新微信accToken{}",e);
			}
		
//		try {
//	    	acWithdrawApplyService.queryWithdrawforJob();//批量支付接口
//		} catch (Exception e) {
//			LOGGER.error(">>>>>>>>>>>>>>同步批量提现记录接口定时任务异常{}",e);
//			e.printStackTrace();
//		}
		
	}
	
//    @RequestMapping(value="importRewardData")
//    @ResponseBody
//    @RequestLogging("录入奖励数据")
//	public Object importRewardData(HttpServletRequest request,HttpSession session) {   
//    	try {
//    		User user = (User) session.getAttribute("userInfo"); 
//    		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
//    		String rewardName = multipartRequest.getParameter("rewardName");
//    		String profitType = multipartRequest.getParameter("profitType");
//    		String rewardTime = multipartRequest.getParameter("rewardTime");
//    		Date rewardTimeDate = DateUtils.parse(rewardTime,DateUtils.FORMAT_SHORT);
//            MultipartFile file  =  multipartRequest.getFile("file");
//            Set<String> msg = null;
//            InputStream inputStream = file.getInputStream();
//            List<RewardDataImport>  cfpList = PoiImport.dataImport(inputStream, RewardDataImport.class);
//            if(cfpList == null || cfpList.size() == 0) {
//            	return new ResponseResult(false,"导入失败，数据为空");
//            }
//			String date = DateUtils.format(new Date(), "yyyyMMdd");
////			String batch = rewardName + date;
////			List<AcOfflineRewardDraft> list = new ArrayList<AcOfflineRewardDraft>();
////			CrmUserInfo userInfo = null;
//			
//		    String redpacket_id = "6018060ba7e94e3c8077d291bc198729";
//		    String sendId = "d9b75943b13e4b4a899c3c3c6619f844";//StringUtils.getUUID();
//		    String name = "游戏红包";
//		    String remark = "30天期以上,10000元起";
//		    String expires_date = "2017-07-20 00:00:00";
//			System.out.println("===================================================>>>>>>>>>>>>>");
//			System.out.println("===================================================>>>>>>>>>>>>>");
//			System.out.println("===================================================>>>>>>>>>>>>>");
//            for(RewardDataImport rewardDataImport : cfpList) {
//            	
//            	String mobile = rewardDataImport.getMobile();
//				if(mobile == null || mobile.length() != 11){
//					return new ResponseResult(false,"【 " + mobile + " 】手机号码长度不对");
//				}
////				userInfo = crmUserInfoService.selectCrmUserInfoByMobile(rewardDataImport.getMobile());
//				CrmCfplanner refCfplanner = crmCfplannerService.queryCfplannerByMobile(rewardDataImport.getMobile());
//				if(refCfplanner == null){
//					return new ResponseResult(false,"【 " + mobile + " 】用户不存在");
//				}
//				String profit = rewardDataImport.getAmount();
//				
//				    
//	    	String redpacket_detail_id = StringUtils.getUUID();
//	    	String  cfplanner_id =refCfplanner.getUserId();
//	    	String  cfplanner_name =refCfplanner.getUserName();
//	    	String  cfplanner_mobile =refCfplanner.getMobile();
//	    	String  money = profit;
//	    	
//	    	String sql ="	INSERT INTO tact_redpacket_detail ( redpacket_id, send_id, redpacket_detail_id, name, money,"
//	    			
//	        		+ " remark, type, status,expires_date, cfplanner_id, cfplanner_name,"
//	        		
//	        		+ " cfplanner_mobile, create_time, update_time ) "
//	        		
//	        		+ "VALUES ( '%s', '%s', '%s', '%s', '%s',"
//	        		
//	        		+ " '%s', '1', '1', '%s', '%s', '%s',"
//	        		
//	        		+ " '%s', '%s', '%s' );";
//	    	String time = DateUtils.format(new Date(),DateUtils.FORMAT_LONG);
//	    	System.out.println(String.format(sql,
//	    			redpacket_id, sendId,redpacket_detail_id,name,money,
//	    			remark,expires_date,cfplanner_id,cfplanner_name,
//	    			cfplanner_mobile,time,time));
//	    	
//            }
//            System.out.println("===================================================>>>>>>>>>>>>>");
//            System.out.println("===================================================>>>>>>>>>>>>>");
//            System.out.println("===================================================>>>>>>>>>>>>>");
//    		return new ResponseResult(true, "导入成功", msg);
//		}catch (ServiceException e) {
////			logger.error("import raward data exception : {}", e.getMessage());
//			return new ResponseResult(false, e.getMessage());
//		} catch (Exception e) {
////			logger.error("import raward data exception : {}", e.getMessage());
//		}
//    	return new ResponseResult(false, "导入失败");
//	}
}
