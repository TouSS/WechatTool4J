package xx.wechat.tools.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 公众号客服
 */
public class CustomerServiceAccount {
    /**
     * 完整客服账号，格式为：账号前缀@公众号微信号
     */
    @JSONField(name = "kf_account")
    private String account;
    /**
     * 客服昵称
     */
    @JSONField(name = "kf_nick")
    private String nick;
    /**
     * 客服工号
     */
    @JSONField(name = "kf_id")
    private String id;
    /**
     * 客服昵称，最长6个汉字或12个英文字符
     */
    @JSONField(name = "nickname")
    private String nickname;
    /**
     * 客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码
     */
    @JSONField(name = "password")
    private String password;

    /**
     * 客服头像链接
     */
    @JSONField(name = "kf_headimgurl")
    private String headImgUrl;

    public CustomerServiceAccount() {
    }

    public CustomerServiceAccount(String account, String nickname, String password) {

        this.account = account;
        this.nick = nick;
        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }
}
