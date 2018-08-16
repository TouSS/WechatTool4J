package xx.wechat.tools.bean.customer.service;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 客服会话
 */
public class Session {
    /**
     * 粉丝的openid
     */
    @JSONField(name = "openid")
    private String openid;
    /**
     * 完整客服帐号，格式为：帐号前缀@公众号微信号
     */
    @JSONField(name = "kf_account")
    private String account;
    /**
     * 会话接入的时间
     */
    @JSONField(name = "createtime")
    private Long createTime;
    /**
     * 粉丝的最后一条消息的时间
     */
    @JSONField(name = "latest_time")
    private Long latestTime;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
