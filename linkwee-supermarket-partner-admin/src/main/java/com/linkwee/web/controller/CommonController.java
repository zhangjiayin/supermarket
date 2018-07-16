package com.linkwee.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 公共视图控制器
 * 
 * @author Mignet
 * @since 2014年4月15日 下午4:16:34
 **/
@Controller
public class CommonController {


    /**
     * 首页
     * 
     * @param request
     * @return
     */
    @RequestMapping("index")
    public String index(HttpServletRequest request, Model model) throws Exception{
        return "index";
    }

}