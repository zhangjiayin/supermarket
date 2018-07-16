package com.linkwee.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.web.request.CfpCommonRequest;
import com.linkwee.web.service.LogService;
import com.linkwee.xoss.util.RequestLogging;

/**
 *日志管理
 * @Author Mignet
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
    	return logService.queryAccountOpLogList(cfpCommonRequest);
    }

    @RequestMapping(value = "/thread",method = RequestMethod.GET)
    public String showThreads() throws Exception{
        return "logs/log-thread";
    }
    
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String callTest() throws Exception{
    	return "logs/log-test";
    }
    
    @RequestMapping(value = "/tail",method = RequestMethod.GET)
    public String showLogger() throws Exception{
    	return "logs/log-tail";
    }

    @RequestMapping("/data")
    @ResponseBody
    public Map<String,Object> showLoggerAjax(int seek) throws Exception{
        Map<String,Object> result = new HashMap<String, Object>();
        seek = seek<=0?5000:seek;
        File f = new File(File.separator+"data"+File.separator+"logs"+File.separator+"lhlc-supermarket-tomcat"+File.separator+"linkwee-supermarket.log");
        if(f.exists()){
            result.put("size",f.length());

            long le = f.length()-seek;
            if(f.length()<=le){
                le = 0;
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(f,"r");
            randomAccessFile.seek(le);
            String content = readLine(randomAccessFile);
            content = new String(content.getBytes("iso-8859-1"),"utf-8");
            result.put("content",content);
            result.put("seek",le);
        }else{
            result.put("content","/data/logs/lhlc-supermarket-tomcat/linkwee-supermarket.log,文件不存在");
            result.put("seek",0);
        }
        return result;
    }
    
    private static String readLine(RandomAccessFile reader) throws IOException {
        StringBuffer sb  = new StringBuffer();
        int ch;
        boolean seenCR = false;
        while((ch=reader.read()) != -1) {
            switch(ch) {
                case '\n':
                     sb.append("<br/>");
                case '\r':
                    seenCR = true;
                    break;
                default:
                    if (seenCR) {
                        sb.append("&nbsp;");
                        seenCR = false;
                    }
                    sb.append((char)ch); // add character, not its ascii value
            }
        }
        return sb.toString();
    }

    public static void main(String [] args)
    {
           Map<String,String> m = System.getenv();
           for ( Iterator<String> it = m.keySet().iterator(); it.hasNext(); )
           {
                  String key = (String ) it.next();
                  String value = (String )  m.get(key);
                  System.out.println(key +":" +value);
           }
           System.out.println( "--------------------------------------" );
           Properties p = System.getProperties();
          
           for ( Iterator<Object> it = p.keySet().iterator(); it.hasNext(); )
           {
                  String key = (String ) it.next();
                  String value = (String )  p.get(key);
                  System.out.println(key +":" +value);
           }
    }
}
