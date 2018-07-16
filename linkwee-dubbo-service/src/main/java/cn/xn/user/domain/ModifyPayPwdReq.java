package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ModifyPayPwdReq extends BaseReq {
    
    private static final long serialVersionUID = -7566331981460093868L;
    
    /**
     * 现有支付密码
     */
    private String payPwd;
    
    /**
     * 新的支付密码
     */
    private String payNewPwd;
    
    public String getPayPwd() {
        return payPwd;
    }
    
    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
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
        return ReflectionToStringBuilder.toStringExclude(this, new String[]{"payPwd", "payNewPwd" });
    }
    
}
