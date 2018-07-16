package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class LoginReq extends BaseReq {
    
    private static final long serialVersionUID = -6078038213683153569L;
    
    /**
     * 登录密码
     */
    @NotNullField
    private String loginPwd;
    
    /**
     * 用户编码
     */
    @NotNullField
    private String loginName;
    
    public String getLoginName() {
        return loginName;
    }
    
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    
    public String getLoginPwd() {
        return loginPwd;
    }
    
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }
    
    @Override
    public String toString() {
        ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return ReflectionToStringBuilder.toStringExclude(this, "loginPwd");
    }
    
}
