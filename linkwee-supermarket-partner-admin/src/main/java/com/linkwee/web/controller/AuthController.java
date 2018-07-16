package com.linkwee.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkwee.core.result.Result;
import com.linkwee.web.model.CrmSalesOrg;
import com.linkwee.web.request.LoginRequest;
import com.linkwee.web.service.CrmSalesOrgService;
import com.linkwee.xoss.util.RequestLogging;

/**
 * 权限控制器
 * 
 * @author Mignet
 * @since 2014年5月28日 下午3:54:00
 **/
@Controller  
@RequestMapping(value="auth")  
@RequestLogging("权限控制器")
public class AuthController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	
    @Resource
    private CrmSalesOrgService userService; 

    /**
     * 机构用户登录
     * 
     * @param user
     * @param result
     * @return
     */
    @RequestLogging("用户登录")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@Validated LoginRequest user, BindingResult result, Model model, HttpServletRequest request) {
    	AuthenticationToken token =new UsernamePasswordToken(user.getOrgAccount(),user.getOrgPassword());
    	Subject currentUser = SecurityUtils.getSubject();
		// 已登陆 则跳到首页
		if (currentUser.isAuthenticated()) {
			LOGGER.debug("已登陆 则跳到首页");
			return "redirect:/";
		}
		if (result.hasErrors()) {
			model.addAttribute("error", "参数错误！");
			LOGGER.debug("参数错误");
			return "login";
		}
        try {
            // 身份验证
            currentUser.login(token);
        }catch (AuthenticationException e) {
            // 身份验证失败
            model.addAttribute("error", e.getMessage());
            return "login";
        }catch (Exception e) {
            // 身份验证失败
            model.addAttribute("error", "登录失败,请重试!");
            return "login";
        }
        CrmSalesOrg salesOrg = new CrmSalesOrg();
       
        salesOrg.setManagerAccount(user.getOrgAccount());
        salesOrg.setPassword(user.getOrgPassword());
        // 验证成功在Session中保存用户信息
        final CrmSalesOrg authUserInfo = userService.selectOne(salesOrg);
        Session session = currentUser.getSession(true);
        session.setAttribute("userInfo", authUserInfo);
        //通常这个时候会给出当前用户所属角色的菜单
        return "redirect:/";
    }
    
    /**
     * 
     * @return
     */
    @RequestMapping(value="toresetpwd",   method=RequestMethod.GET)  
    public String toResetPwd(){
    	return "sys/reset-pwd";
    }
    
    /**
     * 用户修改密码
     */
    @RequestMapping(value="resetpwd",   method=RequestMethod.POST)  
    @ResponseBody
    @RequestLogging("用户修改密码")
    public Result resetPwd(@RequestParam("password")String password,@RequestParam("new_password")String new_password) {
    	Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	CrmSalesOrg u = (CrmSalesOrg)session.getAttribute("userInfo");
    	if(!u.getPassword().equals(password)){
    		return new Result(false,401,"原密码不匹配！");
    	}
    	u.setPassword(new_password);
    	int i = userService.update(u);
    	if(i==1){
    		//session.setAttribute("userInfo", u); 退出登录
    		session.removeAttribute("userInfo");
    		currentUser.logout();
    		return new Result(true,"密码修改成功!");
    	}else{
    		return new Result(false,500,"密码修改失败");
    	}
    }

    /**
     * 用户登出
     * 
     * @param session
     * @return
     */
    @RequestLogging("用户登出")
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
    	Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
        session.removeAttribute("userInfo");
        // 登出操作
        currentUser.logout();
        return "login";
    }

}
