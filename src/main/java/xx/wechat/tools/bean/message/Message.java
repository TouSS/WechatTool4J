package xx.wechat.tools.bean.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 公众号消息
 */
public class Message {
    /** 文本消息 **/
    public final static String MESSAGE_TYPE_TEXT = "text";
    /** 图片消息 **/
    public final static String MESSAGE_TYPE_IMAGE = "image";
    /** 语音消息 **/
    public final static String MESSAGE_TYPE_VOICE = "voice";
    /** 视频消息 **/
    public final static String MESSAGE_TYPE_VIDEO = "video";
    /** 视频消息（群发消息） **/
    public final static String MESSAGE_TYPE_MPVIDEO = "mpvideo";
    /** 短视频消息 **/
    public final static String MESSAGE_TYPE_SHORT_VIDEO = "shortvideo";
    /** 地理位置消息 **/
    public final static String MESSAGE_TYPE_LOCATION = "location";
    /** 链接消息 **/
    public final static String MESSAGE_TYPE_LINK = "link";
    /** 音乐消息 **/
    public final static String MESSAGE_TYPE_MUSIC = "music";
    /** 图文消息 **/
    public final static String MESSAGE_TYPE_NEWS = "news";
    /** 图文消息(群发消息) **/
    public final static String MESSAGE_TYPE_MPNEWS = "mpnews";
    /** 卡券消息 **/
    public final static String MESSAGE_TYPE_WXCARD = "wxcard";
    /** 事件消息 **/
    public final static String MESSAGE_TYPE_EVENT = "event";

    /**
     * 开发者微信号
     */
    @JSONField(name = "ToUserName")
    protected String toUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    @JSONField(name = "FromUserName")
    protected String fromUserName;
    /**
     * 消息创建时间 （整型）
     */
    @JSONField(name = "CreateTime")
    protected Long createTime;
    /**
     * 消息类型
     */
    @JSONField(name = "MsgType")
    protected String msgType;
    /**
     * 消息id，64位整型
     */
    @JSONField(name = "MsgId")
    protected Long msgId;

    public Message() {
    }

    public Message(String msgType) {
        this.msgType = msgType;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }
}
