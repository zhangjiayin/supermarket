package com.linkwee.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.linkwee.web.response.MenuTreeResp;
import com.linkwee.web.service.MenuSerivce;

/**
 * 公共视图控制器
 * 
 * @author Mignet
 * @since 2014年4月15日 下午4:16:34
 **/
@Controller
public class CommonController {


    @Resource
    private MenuSerivce menuSerivce;

    /**
     * 首页
     * 
     * @param request
     * @return
     */
    @RequestMapping("index")
    public String index(HttpServletRequest request, Model model) throws Exception{
        List<MenuTreeResp> menuTreeRespList = menuSerivce.findMenuTree();
        model.addAttribute("menus",menuTreeRespList);
        return "index";
    }

}