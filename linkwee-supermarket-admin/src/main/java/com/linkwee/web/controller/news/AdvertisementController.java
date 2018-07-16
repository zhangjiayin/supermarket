package com.linkwee.web.controller.news;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.news.SmAdvertisement;
import com.linkwee.web.request.AdvRequest;
import com.linkwee.web.service.AdvertisementService;
import com.linkwee.web.service.SysConfigService;
import com.linkwee.xoss.api.BaseController;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;


@Controller
@RequestMapping("adv")
@RequestLogging("广告管理")
public class AdvertisementController extends BaseController{
	
	@Resource
	private AdvertisementService advertisementService; 
	@Resource
	private SysConfigService systemConfigService; 
	
	@RequestMapping("init")
	public String advListPage(){
		return "news/advList";
	}

	private  String getImgServerUrl(){
		return systemConfigService.getValuesByKey("img_server_url");
	}
	/**
	 * 获取列表
	 * @param lcsListRequest
	 * @return
	 */
/*	@RequestMapping("list")
	@ResponseBody
	public Object getAdvList(AdvRequest pageRequest){
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		PaginatorSevReq req = pageRequest.toPaginatorSevReq();
		
		if(StringUtils.isNotBlank(pageRequest.getPageIndexDesc())){
			req.getQueryConditions().put("pageIndexDesc", pageRequest.getPageIndexDesc().trim());
		}
		
		PageResponse<SmAdvertisement> response = new PageResponse<SmAdvertisement>();
		try {
			
			PaginatorSevResp<SmAdvertisement> sysConfigPageList = advertisementService.queryAdvList(req);
			response.setTotal(sysConfigPageList.getTotalCount());
			response.setRows(sysConfigPageList.getDatas());
			
		} catch (Exception e) {
			e.printStackTrace();
			logsb.append("|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return response;
	}*/
	@RequestMapping("/list_json")
	@ResponseBody
	@RequestLogging("广告列表")
	public DataTableReturn advListAjax(SmAdvertisement pageRequest, DataTable dataTable) throws Exception{
		DataTableReturn dataTableReturn = new DataTableReturn();
		if(pageRequest==null){
			pageRequest = new SmAdvertisement();
		}
		
		dataTableReturn = advertisementService.findAdvList(pageRequest,dataTable);
		@SuppressWarnings("unchecked")
		List<SmAdvertisement> newsRequestList =(List<SmAdvertisement>)dataTableReturn.getData();
		for(SmAdvertisement item:newsRequestList){
			if(StringUtils.isNotBlank(item.getImgUrl()) && !item.getImgUrl().startsWith("http")){
				item.setImgUrl(getImgServerUrl()+item.getImgUrl());
			}
			int temp1 = DateUtils.compareDate(item.getValidBeginDate(),new Date());
			int temp2 = DateUtils.compareDate(new Date(),item.getValidEndDate());
			if(temp1 ==1){
				item.setProcessingStatus("未开始");
				continue;
			}
			if(temp2 == 1){
				item.setProcessingStatus("已结束");
				continue;
			}
			if(temp1 < 1 && temp2 < 1){
				 if(item.getStatus().intValue() ==-1){
					 item.setProcessingStatus("进行中 不显示");
				 }else{
					 item.setProcessingStatus("进行中");
				 }
			}
		}
		return dataTableReturn;
	}
	
	/**
	 * 转编辑页
	 * @param mobile
	 * @return
	 */
	@RequestMapping("toupdate")
	public String toUpdate(@RequestParam String id,Model model){
		if(StringUtils.isNotBlank(id)){
			SmAdvertisement advertisement = advertisementService.findAdvDtl(id);
			advertisement.setImgUrl(systemConfigService.getImageUrl(advertisement.getImgUrl()));
			model.addAttribute("dtl", advertisement);
			model.addAttribute("actionType","edit");
			model.addAttribute("img_server",getImgServerUrl());
		}
		return "news/advDtl";
	}
	
	
	/**
	 * 删除
	 * @param mobile
	 * @return
	 */
	@RequestMapping("del")
	@ResponseBody
	@RequestLogging("删除广告")
	public Object del(@RequestParam String id ){
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult result = null;
		if(StringUtils.isNotBlank(id)){
			ReturnCode returnCode = advertisementService.DeleteAdv(Integer.parseInt(id));
			if(returnCode.getCode() == 0){
				result = new ResponseResult(true, "删除成功");
				logsb.append("advertisementService DeleteAdv success");
				logger.info(logsb.toString());
			}else{
				result = new ResponseResult(false, "删除失败");
				
			}
		}
		long end = System.currentTimeMillis();
		logsb.append("|totaltime=").append(end - start);
		logger.info(logsb.toString());
		return result;
	}
	
	/**
	 * 修改
	 * @param mobile
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	@RequestLogging("编辑广告")
	public Object update(@Valid AdvRequest adv,BindingResult bindResult){
		if(ResponseUtil.existsParamsError(bindResult)) {
	    	return ResponseUtil.getErrorParams(bindResult);
        }
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult result = null;
		
		if(adv.getPageLocation().equals("product_opening")){
			boolean hasDuplicateProductOpening = advertisementService.hasDuplicateProductOpening(adv.getValidBeginDate(),adv.getValidEndDate(),String.valueOf(adv.getId()));
			if(hasDuplicateProductOpening){
				result = new ResponseResult(false, "更新失败,更新的首页弹窗起止时间与已存在的重叠！");
				return result;
			}
		}
			
		ReturnCode returnCode = advertisementService.updateAdv(convertToAdvRequest(adv));
		if(returnCode.getCode() == 0){
			result = new ResponseResult(true, "编辑成功");
			logsb.append("advertisementService updateAdv success");
			logger.info(logsb.toString());
		}else{
			result = new ResponseResult(false, "编辑失败");
			
		}
		long end = System.currentTimeMillis();
		logsb.append("|totaltime=").append(end - start);
		logger.info(logsb.toString());
		return result;
	}
	/**
	 * 转新增页
	 * @param mobile
	 * @return
	 */
	@RequestMapping("tosave")
	public String toSave(Model model){
		model.addAttribute("img_server",getImgServerUrl());
		return "news/advDtl";
	}
	
	/**
	 * 新增
	 * @param mobile
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	@RequestLogging("新增广告")
	public Object save(@Valid AdvRequest adv,BindingResult bindResult){
		if(ResponseUtil.existsParamsError(bindResult)) {
	    	return ResponseUtil.getErrorParams(bindResult);
        }
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult result = null;
		if(adv.getPageLocation().equals("product_opening")){
			boolean hasDuplicateProductOpening = advertisementService.hasDuplicateProductOpening(adv.getValidBeginDate(),adv.getValidEndDate(),null);
			if(hasDuplicateProductOpening){
				result = new ResponseResult(false, "添加失败,添加的首页弹窗起止时间与已存在的重叠！");
				return result;
			}
		}
		ReturnCode returnCode = advertisementService.SaveAdv(convertToAdvRequest(adv));
		if(returnCode.getCode() == 0){
			result = new ResponseResult(true, "添加成功");
			logsb.append("advertisementService SaveAdv success");
			logger.info(logsb.toString());
		}else{
			result = new ResponseResult(false, "添加失败");
			
		}
	
		long end = System.currentTimeMillis();
		logsb.append("|totaltime=").append(end - start);
		logger.info(logsb.toString());
		return result;
	}
/*	@RequestMapping("upload")
	public Object uploadPic(ByteArray file){
		
		System.out.println(file);
		return null;
	}*/
	/**
	 * 设置广告显示、不显示
	 * @param adv
	 * @param bindResult
	 * @return
	 */
	@RequestMapping("setstatu")
	@ResponseBody
	public Object setStatus(@RequestParam String id,@RequestParam String opType){
		
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult result = null;
		if(StringUtils.isNotBlank(id) && StringUtils.isNotBlank(opType)){
			SmAdvertisement ret = new SmAdvertisement();
			ret.setId(Integer.parseInt(id));
			ret.setStatus(Integer.parseInt(opType));
			ReturnCode returnCode = advertisementService.updateAdv(ret);
			if(returnCode.getCode() == 0){
				result = new ResponseResult(true, "设置成功");
				logsb.append("advertisementService updateAdv success");
				logger.info(logsb.toString());
			}else{
				result = new ResponseResult(false, "设置失败");
				
			}
		}
			
		
		long end = System.currentTimeMillis();
		logsb.append("|totaltime=").append(end - start);
		logger.info(logsb.toString());
		return result;
	}
	
	private SmAdvertisement convertToAdvRequest(AdvRequest adv){
		SmAdvertisement ret = new SmAdvertisement();
		if(adv!=null){
			if(adv.getId()!=null){
				ret.setId(adv.getId());
			}
			ret.setPageIndex(adv.getPageLocation());
			ret.setPageIndexDesc(adv.getPageIndexDesc());
			if(StringUtils.isNotBlank(adv.getImgUrl()) && adv.getImgUrl().startsWith("http")){
				ret.setImgUrl(adv.getImgUrl());
			}else{
				ret.setImgUrl(getImgServerUrl()+adv.getImgUrl());
			}
				
			//ret.setImgUrl(getImgServerUrl()+adv.getImgUrl());
			//ret.setImgUrl(adv.getImgUrl());
			ret.setLinkUrl(adv.getLinkUrl());
			ret.setShowIndex(adv.getShowIndex());
			ret.setStatus(adv.getStatus());
			ret.setAppType(adv.getAppType());
			ret.setValidBeginDate(adv.getValidBeginDate());
			ret.setValidEndDate(adv.getValidEndDate());
			ret.setShareDesc(adv.getShareDesc());
			
			/*if(StringUtils.isNotBlank(adv.getShareIcon()) && adv.getShareIcon().startsWith("http:")){
				ret.setShareIcon(adv.getShareIcon());
			}else{
				ret.setShareIcon(getImgServerUrl()+adv.getShareIcon());
			}*/
			ret.setShareIcon(adv.getShareIcon());
			ret.setShareLink(adv.getShareLink());
			ret.setShareTitle(adv.getShareTitle());
		}
		return ret;
		
	}
	
	
	

}
