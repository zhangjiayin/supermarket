package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class CheckLoginPwdByMemberNoReq extends BaseReq {
    
    private static final long serialVersionUID = 692855053348901052L;
    
    /**
     * 用户登录密码
     */
    @NotNullField
    private String loginPwd;
    
    /**
     * 用户登录名
     */
    @NotNullField
    private String memberNo;
    
    public String getMemberNo() {
        return memberNo;
    }
    
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
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
