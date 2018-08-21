package xx.wechat.tools.bean.token;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 微信JS接口临时票据
 */
public class JsApiTicket {
    /**
     * 临时票据
     */
    @JSONField(name = "ticket")
    private String ticket;
    /**
     * 有效期
     */
    @JSONField(name = "expires_in")
    private Long expires;
    /**
     * 申请时间戳
     */
    @JSONField(name = "apply_unix")
    private Long apply;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

    public Long getApply() {
        return apply;
    }

    public void setApply(Long apply) {
        this.apply = apply;
    }

    /**
     * 是否过期, 提前10分钟更换
     */
    public boolean isExpired() {
        return this.expires - (System.currentTimeMillis() / 1000 - this.apply) < 600;
    }
}
