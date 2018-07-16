package com.linkwee.web.controller.cfplanner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.util.DateUtils;
import com.linkwee.web.service.LcsDataViewService;
import com.linkwee.web.util.RequestLogging;

@RequestMapping("lcsDataView")
@Controller
@RequestLogging("理财师数据概览")
public class LcsDataViewContrller {
	
	@Resource
	private LcsDataViewService lcsDataViewServicel;
	
	@RequestMapping("dataViewPage")
	@RequestLogging("数据概览页面")
	public String initPage(Model model){
		model.addAttribute("data",lcsDataViewServicel.getLcsDateStaticCount());
		String start = DateUtils.format(DateUtils.subDay(new Date(), 7),"yyyy-MM-dd");
		String end = DateUtils.format(DateUtils.subDay(new Date(), 1),"yyyy-MM-dd");
		model.addAttribute("start",start);
		model.addAttribute("end",end);
		return "cfplanner/lcsDataView";
	}
	
	@RequestMapping("getLcsDataView")
	@ResponseBody
	@RequestLogging("查阅折线图报表")
	public Object getLcsDataView(String start,String end){
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(start)){
			map.put("startDate", DateUtils.parse(start,DateUtils.FORMAT_SHORT));
		}else{
			map.put("startDate", DateUtils.subDay(new Date(), 7));
		}
		if(StringUtils.isNotBlank(end)){
			map.put("endDate", DateUtils.parse(end,DateUtils.FORMAT_SHORT));
		}else{
			map.put("endDate", DateUtils.subDay(new Date(), 1));
		}
		map.put("data", lcsDataViewServicel.getLcsDataStatic(map));
		return map;
	}
}
