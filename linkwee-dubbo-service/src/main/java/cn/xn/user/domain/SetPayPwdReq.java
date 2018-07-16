package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class SetPayPwdReq extends BaseReq {
    
    private static final long serialVersionUID = 5167134225369952941L;
    
    /**
     * 新密码
     */
    @NotNullField
    private String payNewPwd;
    
    /**
     * 用户编码
     */
    @NotNullField
    private String memberNo;
    
    public String getMemberNo() {
        return memberNo;
    }
    
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }
    
    public String getPayNewPwd() {
        return payNewPwd;
    }
    
    public void setPayNewPwd(String payNewPwd) {
        this.payNewPwd = payNewPwd;
    }
    
    @Override
    public String toString() {
        ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return ReflectionToStringBuilder.toStringExclude(this, new String[]{"payNewPwd" });
    }
}
