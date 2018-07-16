package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class ModifyLoginPwdReq extends BaseReq {
    
    private static final long serialVersionUID = 6049054914876711744L;
    
    /**
     * 用户编码
     */
    @NotNullField
    private String memberNo;
    
    /**
     * 登录token
     */
    @NotNullField
    private String tokenId;
    
    /**
     * 现有密码
     */
    @NotNullField
    private String loginPwd;
    
    /**
     * 新密码
     */
    @NotNullField
    private String loginNewPwd;
    
    public String getTokenId() {
        return tokenId;
    }
    
    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
    
    public String getLoginPwd() {
        return loginPwd;
    }
    
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }
    
    public String getLoginNewPwd() {
        return loginNewPwd;
    }
    
    public void setLoginNewPwd(String loginNewPwd) {
        this.loginNewPwd = loginNewPwd;
    }
    
    public String getMemberNo() {
        return memberNo;
    }
    
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }
    
    @Override
    public String toString() {
        ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return ReflectionToStringBuilder.toStringExclude(this, new String[]{"loginPwd", "loginNewPwd" });
    }
    
}
