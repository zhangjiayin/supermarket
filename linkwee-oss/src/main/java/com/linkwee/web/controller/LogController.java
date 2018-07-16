package com.linkwee.web.controller;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.request.CfpCommonRequest;
import com.linkwee.web.service.LogService;
import com.linkwee.web.util.RandomAccessUtil;
import com.linkwee.web.util.RequestLogging;

/**
 *日志管理
 * @Author Libin
 * @Date 2016/6/7
 */
@Controller
@RequestMapping("/log")
@RequestLogging(value = "账户日志管理")
public class LogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogController.class);

    @Resource
    private LogService logService;

    /**
     * 账号操作日志列表显示列表页
     * @return
     */
    @RequestMapping(value = "/account")
    @RequestLogging("账号操作日志列表显示列表页")
    public String accountLog(){
        return "logs/log-account";
    }

    /**
     * 账号操作日志列表数据请求源操作
     * @param cfpCommonRequest
     * @return
     * @throws Exception
     */
    @RequestLogging("账号操作日志列表数据请求源操作")
    @RequestMapping("/account_ajax")
    @ResponseBody
    public DataTableReturn accountLogAjax(@RequestBody CfpCommonRequest cfpCommonRequest) throws Exception{
        DataTableReturn  dataTableReturn = logService.queryAccountOpLogList(cfpCommonRequest);
        return dataTableReturn;
    }

    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public ModelAndView showLogger() throws Exception{
        ModelAndView modelAndView = new ModelAndView("logs/log-tail");
        return modelAndView;
    }

    @RequestMapping("/data")
    @ResponseBody
    public Map<String,Object> showLoggerAjax(int seek) throws Exception{
        Map<String,Object> result = new HashMap<String, Object>();
//        File f = new File("D:\\programfiles\\apache-tomcat-7.0.42\\bin\\logs\\linkwee-oss.log");
        seek = seek<=0?5000:seek;
        File f = new File("logs/linkwee-oss.log");
        if(f.exists()){
            result.put("size",f.length());

            long le = f.length()-seek;
            if(f.length()<=le){
                le = 0;
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(f,"r");
            randomAccessFile.seek(le);
            String content = RandomAccessUtil.readLine(randomAccessFile);
            content = new String(content.getBytes("iso-8859-1"),"utf-8");
            result.put("content",content);
            result.put("seek",le);
        }
        else{
            result.put("error","文件不存在");
        }
        return result;
    }

}
