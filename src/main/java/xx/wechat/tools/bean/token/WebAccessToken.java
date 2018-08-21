package xx.wechat.tools.bean.token;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 网页授权access_token
 */
public class WebAccessToken {
    /**
     * APPID
     */
    @JSONField(name = "appid")
    private String appid;
    /**
     * 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     */
    @JSONField(name = "access_token")
    private String token;
    /**
     * 用户刷新access_token, 有效期为30天
     */
    @JSONField(name = "refresh_token")
    private String refreshToken;
    /**
     * 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     */
    @JSONField(name = "openid")
    private String openid;
    /**
     * 用户授权的作用域，使用逗号（,）分隔
     */
    @JSONField(name = "scope")
    private String scope;
    /**
     * access_token接口调用凭证超时时间，单位（秒）
     */
    @JSONField(name = "expires_in")
    private Long expires;
    /**
     * 凭证申请时间戳
     */
    @JSONField(name = "apply_unix")
    private Long apply;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
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
        return this.apply + this.expires - System.currentTimeMillis() / 1000 < 600;
    }

    /**
     * 刷新码是否过期，提前10分钟
     */
    public boolean isRefreshExpired() {
        return this.apply + 30 * 24 * 60 * 60 - System.currentTimeMillis() / 1000 < 600;
    }
}
