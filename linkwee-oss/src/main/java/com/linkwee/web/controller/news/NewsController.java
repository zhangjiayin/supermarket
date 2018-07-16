package com.linkwee.web.controller.news;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.linkwee.core.base.PaginatorSevReq;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.api.BaseController;
import com.linkwee.web.constant.ConfigurationConstant;
import com.linkwee.web.enums.NewsTypeEnum;
import com.linkwee.web.model.news.News;
import com.linkwee.web.request.NewsRequest;
import com.linkwee.web.response.PageResponse;
import com.linkwee.web.service.NewsService;
import com.linkwee.web.service.SystemConfigService;
import com.linkwee.web.util.HtmlFilterUtil;
import com.linkwee.web.util.ResponseUtil;


@Controller
@RequestMapping("news")
public class NewsController extends BaseController{
	@Resource
	private NewsService newsService; 
	@Resource
	private SystemConfigService systemConfigService; 
	

	@RequestMapping("init")
	public String advListPage(){
		return "news/newsList";
	}
	private  String getImgServerUrl(){
		return systemConfigService.getValuesByKey("img_server_url");
	}

	/**
	 * 资讯列表页
	 * @return
	 * @throws Exception
     */
	@RequestMapping("/list")
	public ModelAndView newsList() throws Exception{
		ModelAndView modelAndView = new ModelAndView("news/news-list");
		modelAndView.addObject("newsTypeList",NewsTypeEnum.values());
		return modelAndView;
	}


	@RequestMapping("/list_ajax")
	@ResponseBody
	public DataTableReturn newsListAjax(NewsRequest newsRequest, DataTable dataTable) throws Exception{
		DataTableReturn dataTableReturn = new DataTableReturn();
		if(newsRequest==null){
			newsRequest = new NewsRequest();
		}
		if(newsRequest.getAppType() == null || newsRequest.getAppType()<=0){
			newsRequest.setAppType(1);
		}
		dataTableReturn = newsService.findNewsList(newsRequest,dataTable);
		return dataTableReturn;
	}
	

	/**
	 * 获取系统参数列表
	 * @param pageRequest
	 * @return
	 */
	@RequestMapping("list_json")
	@ResponseBody
	public Object getAdvList(NewsRequest pageRequest){
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		
		PaginatorSevReq req = pageRequest.toPaginatorSevReq();
		
		if(StringUtils.isNotBlank(pageRequest.getTypeCode())){
			req.getQueryConditions().put("typeCode", pageRequest.getTypeCode().trim());
		}
		
		PageResponse<News> response = new PageResponse<News>();
		try {
			
			PaginatorSevResp<News> newsPageList = newsService.queryNewsList(req);
			response.setTotal(newsPageList.getTotalCount());
			response.setRows(newsPageList.getDatas());
			
		} catch (Exception e) {
			e.printStackTrace();
			logsb.append("|Exception e=").append(e.getMessage());
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return response;
	}
	
	/**
	 * 转编辑页
	 * @return
	 */
	@RequestMapping("toupdate")
	public String toUpdate(@RequestParam String id,Model model){
		if(StringUtils.isNotBlank(id)){
			model.addAttribute("news", newsService.findNewsDtl(id));
			model.addAttribute("actionType","edit");
		}
		return "news/newsDtl";
	}
	
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping("del")
	@ResponseBody
	public Object del(@RequestParam String id ){
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult result = null;
		if(StringUtils.isNotBlank(id)){
			ReturnCode returnCode = newsService.DeleteNews(Integer.parseInt(id));
			if(returnCode.getCode() == 0){
				result = new ResponseResult(true, "删除成功");
				logsb.append("newsService DeleteNews success");
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
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public Object update(@Valid NewsRequest news,BindingResult bindResult){
		if(ResponseUtil.existsParamsError(bindResult)) {
	    	return ResponseUtil.getErrorParams(bindResult);
        }
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult result = null;
			
		ReturnCode returnCode = newsService.updateNews(convertToNews(news));
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
	 * @return
	 */
	@RequestMapping("tosave")
	public ModelAndView toSave(Integer id){
		ModelAndView modelAndView = new ModelAndView("news/newsDtl");
		String imgServerUrl = systemConfigService.getValuesByKey(ConfigurationConstant.IMAGE_SERVER_URL);
		if(null !=id && id>0){
			News news = newsService.findNewsDtl(String.valueOf(id));
			news.setTitle(HtmlFilterUtil.filterHtml(news.getTitle()));
			news.setImg(systemConfigService.getImageUrl(news.getImg()));
			modelAndView.addObject("news",news);
		}
		modelAndView.addObject("img_server",imgServerUrl);
		return modelAndView;
	}
	
	/**
	 * 新增
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public Object save(@Valid NewsRequest news,BindingResult bindResult){
		if(ResponseUtil.existsParamsError(bindResult)) {
	    	return ResponseUtil.getErrorParams(bindResult);
        }
		long start = System.currentTimeMillis();
		if(news.getAppType() == null || news.getAppType()<=0){
			news.setAppType(1);
		}
		StringBuilder logsb = new StringBuilder();
		ResponseResult result = null;
		ReturnCode returnCode =null;
		String message = "添加成功";
		news.setTitle(HtmlFilterUtil.filterHtml(news.getTitle()));
		if(news.getId()!=null && news.getId()>0){
			message = "更新成功";
			returnCode = newsService.updateNews(convertToNews(news));
		}
		else{
			returnCode = newsService.SaveNews(convertToNews(news));
		}
			

		if(returnCode.getCode() == 0){
			result = new ResponseResult(true, message);
			logsb.append("newsService SaveNews success");
			logger.info(logsb.toString());
		}else{
			result = new ResponseResult(false, "操作失败");
		}
	
		long end = System.currentTimeMillis();
		logsb.append("|totaltime=").append(end - start);
		logger.info(logsb.toString());
		return result;
	}
	/**
	 * 设置资讯显示、不显示
	 * @return
	 */
	@RequestMapping("setstatu")
	@ResponseBody
	public Object setStatus(@RequestParam String id,@RequestParam String opType){
		
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult result = null;
		if(StringUtils.isNotBlank(id) && StringUtils.isNotBlank(opType)){
			News ret = new News();
			ret.setId(Integer.parseInt(id));
			ret.setStatus(Integer.parseInt(opType));
			ReturnCode returnCode = newsService.updateNews(ret);
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
	
	private News convertToNews(NewsRequest news){
		News ret = new News();
		if(news!=null){
		ret.setAppType(news.getAppType());
		ret.setTypeCode(news.getTypeCode());
		for(NewsTypeEnum item :NewsTypeEnum.values()){
			if(String.valueOf(item.getKey()).equals(news.getTypeCode())){
				ret.setTypeName(item.getValue());
				break;
			}
		}
		String prefix = "<section style=\"padding:0 20px;\">";
		String endfix = "</section>";
		ret.setCreator(news.getCreator());
		ret.setTitle(news.getTitle());
		ret.setName(news.getName());
		ret.setValidBegin(news.getValidBegin());
			String endString = DateUtils.format(DateUtils.addDay(new Date(),365));
		ret.setValidEnd(endString);
		ret.setShowInx(news.getShowInx());

			if(!news.getContent().startsWith(prefix)){
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(prefix);
				stringBuilder.append(news.getContent());
				stringBuilder.append(endfix);
				news.setContent(stringBuilder.toString());
			}
		ret.setContent(news.getContent());
		ret.setImg(systemConfigService.getImageUrl(news.getImg()));
		ret.setCrtTime(new Date());
		ret.setId(news.getId());
			ret.setSummary(news.getSummary());
		ret.setIsStick(news.getIsStick());
		if(StringUtils.isBlank(news.getContent()) && StringUtils.isNotBlank(news.getLinkUrl())){
			ret.setLinkUrl(news.getLinkUrl());
		}
		}
		return ret;
	}


	@RequestMapping("/save_news")
	public ModelAndView saveNews(News news) throws Exception{
		ModelAndView modelAndView = new ModelAndView("news/news_save");
		return modelAndView;
	}


	/**
	 *
	 * @return
     */
	@RequestMapping("/ueditor_config")
	@ResponseBody
	public Object ueditorConfig(){
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("imageActionName","zimg");
		result.put("imageFieldName","userfile");
		result.put("imageAllowFiles",new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp"});
		return result;
	}
}
