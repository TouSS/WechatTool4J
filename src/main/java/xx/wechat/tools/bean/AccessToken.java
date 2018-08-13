package xx.wechat.tools.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 微信公众号凭证
 */
public class AccessToken {
    /**
     * 凭证
     */
    @JSONField(name = "access_token")
    private String token;
    /**
     * 凭证有效时间, 单位：秒
     */
    @JSONField(name = "expires_in")
    private Long expires;
    /**
     * 凭证申请时间戳
     */
    @JSONField(name = "apply_timestamp")
    private Long apply;

    public AccessToken() {
    }

    public AccessToken(String token, Long expires) {
        this.token = token;
        this.expires = expires;
        this.apply = System.currentTimeMillis() / 1000;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
     * 凭证是否过期, 提前10分钟更换凭证
     */
    public boolean isExpired() {
        return this.expires - (System.currentTimeMillis() / 1000 - this.apply) < 600;
    }
}
