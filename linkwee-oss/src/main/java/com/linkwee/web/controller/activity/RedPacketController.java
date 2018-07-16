package com.linkwee.web.controller.activity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.activity.model.RedpacketCal;
import com.linkwee.activity.model.Redpaper;
import com.linkwee.activity.service.RedpacketService;
import com.linkwee.activity.service.UseRedPacketRuleService;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.product.service.ProductInfoService;
import com.linkwee.web.interceptors.DateConvertEditor;
import com.linkwee.web.model.User;
import com.linkwee.web.model.product.ProductInfoResp;
import com.linkwee.web.model.product.SimpleProductReq;
import com.linkwee.web.rbac.PermissionSign;
import com.linkwee.web.request.EveryDayRedPacketCalRequest;
import com.linkwee.web.request.RedPacketInfoRequest;
import com.linkwee.web.request.SendRedPacketRequest;
import com.linkwee.web.util.RequestLogging;

@Controller
@RequestMapping(value = "/redpaper")
@RequestLogging("红包管理")
public class RedPacketController{
	
	private static final Logger logger = LoggerFactory.getLogger(RedPacketController.class);
	
	
	@Autowired
	private RedpacketService redpacketService;
	
	@Autowired
	private ProductInfoService productInfoService;
	
	@Autowired
	private UseRedPacketRuleService useRedPacketRuleService;
	
	private static ExecutorService poolThreadExecutor = Executors.newFixedThreadPool(20);
	
	/**
	 * 转换器
	 *
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	
	 /**
	  * 初始化红包列表
     * 基于角色 比如拥有OPERATION_MANAGER角色，才可以查看列表.
     */
    @RequestMapping(value="/initRedpaperList",method=RequestMethod.GET)
    @RequiresPermissions(value = PermissionSign.ACT_LIST_ALL)
    public String initRedpaperList(Model model) {
    	return "activity/redpaper-list";
    }

    /**
     * 红包列表
     * @return
     */
    @RequestMapping(value="/redpaperList", method=RequestMethod.POST)
    @ResponseBody
    @RequestLogging("红包列表")
	public DataTableReturn queryRedpaperList(@RequestParam String dt_json) {
    	logger.info("queryRedpaperList dt_json={}", dt_json);
    	DataTable dataTable = JsonUtils.fromJsonToObject(dt_json, DataTable.class);
    	logger.info("queryRedpaperList DataTable={}", dataTable);
		//dataTable.initOrders();
		DataTableReturn tableReturn = redpacketService.getRedpacketList(dataTable);
		return tableReturn;
	}
	
	@RequestMapping(value="/sendRedpaper", method=RequestMethod.POST)
	@ResponseBody
	@RequestLogging("正式发送红包")
	public Object sendRedpaper(final SendRedPacketRequest request,HttpSession session){
		User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
		request.setLoginUserName(user.getUsername()); //设置当前发红包的操作用户
		logger.info("sendRedpaper request={}", request);
		try {
			String[] lcsLevels = null ;
			List<String> levels = null;
			if(StringUtils.isNotBlank(request.getLcsLevel())){
				lcsLevels = request.getLcsLevel().substring(0, request.getLcsLevel().length()-1).split(",");
				levels = new LinkedList<String>();
				for(String level : lcsLevels){
					levels.add(level);
				}
			}
			request.setCfpLevels(levels);
			//最高发5个红包
			if( request.getSendNums() > 5){
				return new ResponseResult(true,"发送红包不能超过5个！");
			}
			if(request.getSendNums() != null && request.getSendNums() > 0){
				final CountDownLatch countDownLatch = new CountDownLatch(request.getSendNums());
				for(int i = 0;i<request.getSendNums(); i++){
					poolThreadExecutor.execute(new Runnable() {
						@Override
						public void run() {
							try {
								redpacketService.insertBatchRedpacket(request);
							} catch (Exception e) {
								logger.error("正式发送红包失败 request={}",request, e.getMessage());
							}finally{
								countDownLatch.countDown();
							}
						}
					});
				}
				countDownLatch.await();
				
			}
			return new ResponseResult(true,"正式发送红包成功");
		} catch (Exception e) {
			logger.error("正式发送红包失败", e);
			return new ResponseResult(false,"正式发送红包失败"+e.getMessage());
		}
		
		
	}
	
	@RequestMapping(value="/sendWhiteListUserRedpaper", method=RequestMethod.POST)
	@ResponseBody
	@RequestLogging("发送白名单用户红包")
	public Object sendWhiteListUserRedpaper(SendRedPacketRequest request,HttpSession session){
		User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
		request.setLoginUserName(user.getUsername()); //设置当前发红包的操作用户
		logger.info("sendWhiteListUserRedpaper request={}", request);
		try {
			if(request.getSendNums() != null && request.getSendNums() > 0){
				
				for(int i = 0;i<request.getSendNums(); i++){
					
					redpacketService.insertBatchWhiteListUser(request);
				}
				
			}
			return new ResponseResult(true,"发送白名单用户红包成功");
		} catch (Exception e) {
			logger.error("发送白名单用户红包失败", e);
			return new ResponseResult(false,"发送白名单用户红包失败"+e.getMessage());
		}
	}
	
	@RequestMapping("save")
	@ResponseBody
	@RequestLogging("添加红包信息")
	public Object save(@Valid RedPacketInfoRequest req,HttpSession session,BindingResult bindResult){
		logger.info("save request={}", req);
		try {
			User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
			redpacketService.insertRedpacket(req,user.getUsername());
			return new ResponseResult(true,"添加红包信息成功");
		}catch(IllegalArgumentException e){
			return new ResponseResult(false,e.getMessage());
		} catch (Exception e) {
			logger.error("save redPacket exception", e);
		}
		return new ResponseResult(false,"添加红包信息失败");
	}
	
    @RequestMapping(value="/update",method=RequestMethod.POST)
    @ResponseBody
    public Object update(@Valid RedPacketInfoRequest req,HttpSession session){
    	try {
 
    		if(redpacketService.isSendRedpaper(req.getActivityId(),req.getRedpacketTypeId())){
    			return new ResponseResult(false,"红包已经发放,无法更新");
    		}
    		User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
    		redpacketService.updateRedpacket(req,req.getActivityId(),req.getRedpacketTypeId(),user.getUsername());
			return new ResponseResult(true,"更新红包信息成功");
		}catch(IllegalArgumentException e){
			return new ResponseResult(false,e.getMessage());
		} catch (Exception e) {
			logger.error("update redPacket exception", e);
		}
		return new ResponseResult(false,"更新红包信息失败");
    	
    }
	
	
	
	 @RequestMapping(value="/queryBindingRedpaperProductListPage",method=RequestMethod.GET)
	 public String queryBindingRedpaperProductListPage() {
	    	return "activity/redpaper-product-list";
	 }
	
	 /**
     * 红包列表
     * @return
     */
    @RequestMapping(value="/queryBindingRedpaperProductList", method=RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查询绑定红包产品列表")
	public DataTableReturn queryBindingRedpaperProductList(@RequestParam String  dt_json) {
    	logger.info("queryBindingRedpaperProductList dt_json={}", dt_json);
    	SimpleProductReq dataTable = JsonUtils.fromJsonToObject(dt_json, SimpleProductReq.class);
    	logger.info("queryBindingRedpaperProductList DataTable={}", dataTable);
		DataTableReturn tableReturn = productInfoService.queryDataTableProductList(dataTable);
		return tableReturn;
	}
    
    
    /**
	  * 初始化红包每日统计列表
    * 基于角色 比如拥有OPERATION_MANAGER角色，才可以查看列表.
    */
   @RequestMapping(value="/initRedpaperEveryDayList",method=RequestMethod.GET)
   @RequiresPermissions(value = PermissionSign.ACT_LIST_ALL)
   public String redpaperEveryDayList(Model model) {
   	return "activity/redpaper-everyday-list";
   }
   
    /**
     * 红包每日统计列表 
     * @return
     */
    @RequestMapping(value="/redpaperEveryDayList", method=RequestMethod.POST)
    @ResponseBody
    @RequestLogging("红包每日统计列表")
	public DataTableReturn queryRedpaperEveryDayList(@RequestParam String dt_json) {
    	logger.info("queryRedpaperEveryDayList dt_json={}", dt_json);
    	EveryDayRedPacketCalRequest everyDayRedPacketCal = JsonUtils.fromJsonToObject(dt_json, EveryDayRedPacketCalRequest.class);
    	if(everyDayRedPacketCal.getDate() == null){
    		everyDayRedPacketCal.setDate(new Date());
    	}
    	logger.info("queryRedpaperEveryDayList DataTable={}", everyDayRedPacketCal);
		//dataTable.initOrders();
		DataTableReturn tableReturn = redpacketService.getRedpaperEveryDayList(everyDayRedPacketCal);
		return tableReturn;
	}
    
    /**
 	  * 红包详情
     * 
     */
    @RequestMapping(value="/queryRedpaperInfo",method=RequestMethod.GET)
    @RequestLogging("红包详情及统计")
    public String queryRedpaperInfo(SendRedPacketRequest request,Model model) {
    	logger.info("queryRedpaperInfo request={}", request);
    	try {
			RedpacketCal rcal = redpacketService.queryRedpaperCal(request);
			Redpaper redpaperInfo = redpacketService.queryRedpaperInfo(request);
			RedPacketInfoRequest redpaperUserInfo = new RedPacketInfoRequest();
			useRedPacketRuleService.getRedPacketUseRule(redpaperUserInfo, request.getActivityId());
			String[] productIds = null;
			if(StringUtils.isNotBlank(redpaperUserInfo.getPids())){
			   productIds = StringUtils.split(redpaperUserInfo.getPids(), ',');
			   List<ProductInfoResp> ps = new LinkedList<ProductInfoResp>();
			   ProductInfoResp productInfo = null;
				   for (String productId : productIds) {
					productInfo = productInfoService.getByProductId(productId);
					Validate.notNull(productInfo, "编号为: "+productId+" 的产品不存在 ");
					ps.add(productInfo);
				}
				   model.addAttribute("ps", ps);   
			}
			
			model.addAttribute("rcal", rcal);
			model.addAttribute("redpaperInfo", redpaperInfo);
			model.addAttribute("redpaperUserInfo", redpaperUserInfo);
		} catch (Exception e) {
			logger.error("红包详情及统计 exception", e);
			model.addAttribute("rcal", null);
			model.addAttribute("redpaperInfo", null);
			model.addAttribute("redpaperUserInfo", null);
		}
    	return "activity/redpaper-info";
    }
    
    /**
     * 获取红包编辑页面
     * @param activityId
     * @param redpacketTypeId
     * @return
     */
    @RequestMapping(value="/getRedpacketEditPage",method=RequestMethod.GET)
    public String getRedpacketEditPage(String activityId,String redpacketTypeId,Model model){
    	boolean isSendRedpaper = false;
    	try {
    		isSendRedpaper = redpacketService.isSendRedpaper(activityId, redpacketTypeId);
    		RedPacketInfoRequest redpacketInfo =  new RedPacketInfoRequest();
			redpacketService.queryRedpaperInfo(redpacketInfo,activityId,redpacketTypeId);
			model.addAttribute("redpaperInfo", redpacketInfo);
		} catch (Exception e) {
			logger.error("getRedpacketEditPage exception", e);
			model.addAttribute("redpaperInfo", null);
		}
    	return (isSendRedpaper ? "activity/redpaper-edit":"activity/no-send-redpaper-edit");
    }
    
    
    @RequestMapping(value="/getNoSendRedpaperBindingProdcutPage",method=RequestMethod.GET)
    public String getNoSendRedpaperBindingProdcutPage(Model model){
    	return "activity/no-send-redpaper-edit-product-list";
    }
    
    @RequestMapping(value="/getBindingProdcutPage",method=RequestMethod.GET)
    public String getBindingRedpaperProdcutPage(Model model){
    	return "activity/redpaper-edit-product-list";
    }
    
    
    

    
    @RequestMapping(value="/updateRedPacket",method=RequestMethod.POST)
    @ResponseBody
    public Object updateRedPacket(String activityId,String redpacketTypeId,String pids){
    	try {
    		redpacketService.updateRedpaperRule(activityId, redpacketTypeId, pids);
			return new ResponseResult(true,"更新红包信息成功");
		} catch (Exception e) {
			logger.error("updateRedPacket redPacket exception", e);
		}
		return new ResponseResult(false,"更新红包信息失败");
    	
    }
	
}
