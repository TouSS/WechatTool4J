package xx.wechat.tools.bean.customer.service;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 公众号客服
 */
public class Account {
    /**
     * 邀请状态-等待
     */
    private final static String INVITE_STATUS_WAITING = "waiting";
    /**
     * 邀请状态-拒绝
     */
    private final static String INVITE_STATUS_REJECTED = "rejected";
    /**
     * 邀请状态-过期
     */
    private final static String INVITE_STATUS_EXPIRED = "expired";

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
     * 客服头像链接
     */
    @JSONField(name = "kf_headimgurl")
    private String headImgUrl;
    /**
     * 如果客服帐号已绑定了客服人员微信号， 则此处显示微信号
     */
    @JSONField(name = "kf_wx")
    private String wx;
    /**
     * 如果客服帐号尚未绑定微信号，但是已经发起了一个绑定邀请， 则此处显示绑定邀请的微信号
     */
    @JSONField(name = "invite_wx")
    private String inviteWx;
    /**
     * 如果客服帐号尚未绑定微信号，但是已经发起过一个绑定邀请， 邀请的过期时间，为unix 时间戳
     */
    @JSONField(name = "invite_expire_time")
    private Long inviteExpireTime;
    /**
     * 邀请的状态，有等待确认“waiting”，被拒绝“rejected”， 过期“expired”
     */
    @JSONField(name = "invite_status")
    private String inviteStatus;
    /**
     * 客服在线状态
     */
    @JSONField(name = "status")
    private Integer status;
    /**
     * 客服当前正在接待的会话数
     */
    @JSONField(name = "accepted_case")
    private Integer acceptedCase;

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

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public String getInviteWx() {
        return inviteWx;
    }

    public void setInviteWx(String inviteWx) {
        this.inviteWx = inviteWx;
    }

    public Long getInviteExpireTime() {
        return inviteExpireTime;
    }

    public void setInviteExpireTime(Long inviteExpireTime) {
        this.inviteExpireTime = inviteExpireTime;
    }

    public String getInviteStatus() {
        return inviteStatus;
    }

    public void setInviteStatus(String inviteStatus) {
        this.inviteStatus = inviteStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAcceptedCase() {
        return acceptedCase;
    }

    public void setAcceptedCase(Integer acceptedCase) {
        this.acceptedCase = acceptedCase;
    }
}
