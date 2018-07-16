package com.linkwee.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.linkwee.xoss.util.RequestLogging;

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

    @RequestLogging("查看机构日志")
    @RequestMapping(value = {"","/"},method = RequestMethod.GET)
    public ModelAndView showLogger() throws Exception{
        ModelAndView modelAndView = new ModelAndView("logs/log-tail");
        return modelAndView;
    }

    @RequestMapping("/data")
    @ResponseBody
    public Map<String,Object> showLoggerAjax(int seek) throws Exception{
        Map<String,Object> result = new HashMap<String, Object>();
        seek = seek<=0?5000:seek;
        File f = new File(File.separator+"data"+File.separator+"logs"+File.separator+"linkwee-supermarket-inquiry.log");
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
        }
        else{
            result.put("error","文件不存在");
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

}
