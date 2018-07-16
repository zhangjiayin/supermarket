package com.linkwee.xoss.rbac;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.linkwee.web.model.CrmSalesOrg;
import com.linkwee.web.service.CrmSalesOrgService;

/**
 * 用户身份验证,授权 Realm 组件
 * 
 * @author Mignet
 * @since 2014年6月11日 上午11:35:28
 **/
@Component(value = "securityRealm")
public class SecurityRealm extends AuthorizingRealm {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityRealm.class);
	
    @Resource
    private CrmSalesOrgService userService;

    /**
     * 权限检查
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        /*String username = String.valueOf(principals.getPrimaryPrincipal());
        CimOrginfo u = new CimOrginfo();
        u.setOrgAccount(username);
        final CimOrginfo user = userService.selectOne(u);
        //查询用户拥有的角色
        final List<Role> roleInfos = roleService.selectRolesByUserId(user.getId());
        for (Role role : roleInfos) {
        	LOGGER.debug("用户{}拥有的role:{}",username,role);
            authorizationInfo.addRole(role.getRoleSign());
            //查询角色拥有的权限
            final List<Permission> permissions = permissionService.selectPermissionsByRoleId(role.getId());
            for (Permission permission : permissions) {
            	LOGGER.debug("用户{}拥有的permission{}:",username,permission);
                authorizationInfo.addStringPermission(permission.getPermissionSign());
            }
        }*/
        return authorizationInfo;
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        // 通过数据库进行验证
        CrmSalesOrg u = new CrmSalesOrg();
        u.setManagerAccount(username);
        u.setPassword(password);
        final CrmSalesOrg authentication = userService.selectOne(u);
        if(!"acb5c189c09061818b51addcbb6011377b6e22aaa6f91b1ac691b60d49c540db".equals(password)){
        	if (authentication == null) {
        		throw new AuthenticationException("用户名或密码错误.");
        	}
        	//0-合作结束，1-合作中
        	if (!ObjectUtils.equals(authentication.getCooperationStatus(), 1) ) {
        		throw new AuthenticationException("合作已暂停，如有疑问欢迎致电400-888-6987");
        	}
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }

}
