package xx.wechat.tools.bean.event;

import com.alibaba.fastjson.annotation.JSONField;
import xx.wechat.tools.bean.message.Message;

/**
 * 事件消息
 */
public class Event extends Message {

    /**
     * 关注事件/扫描带参数二维码关注事件
     **/
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    /**
     * 取消关注事件
     **/
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    /**
     * 已关注扫描带参数二维码事件
     **/
    public static final String EVENT_TYPE_SCAN = "SCAN";
    /**
     * 上报地理位置事件
     **/
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    /**
     * 点击菜单拉取消息时的事件
     **/
    public static final String EVENT_TYPE_CLICK = "CLICK";
    /**
     * 点击菜单跳转链接时的事件
     **/
    public static final String EVENT_TYPE_VIEW = "VIEW";
    /**
     * 扫码推事件的事件
     **/
    public static final String EVENT_TYPE_SCANCODE_PUSH = "scancode_push";
    /**
     * 扫码推事件且弹出“消息接收中”提示框的事件
     **/
    public static final String EVENT_TYPE_SCANCODE_WAITMSG = "scancode_waitmsg";
    /**
     * 弹出系统拍照发图的事件
     **/
    public static final String EVENT_TYPE_PIC_SYSPHOTO = "pic_sysphoto";
    /**
     * 弹出拍照或者相册发图的事件
     **/
    public static final String EVENT_TYPE_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
    /**
     * 弹出微信相册发图器的事件
     **/
    public static final String EVENT_TYPE_PIC_WEIXIN = "pic_weixin";
    /**
     * 弹出地理位置选择器的事件
     **/
    public static final String EVENT_TYPE_LOCATION_SELECT = "location_select";
    /**
     * 群发结果事件
     */
    public static final String EVENT_TYPE_LOCATION_MASS_SEND_JOB_FINISH = "MASSSENDJOBFINISH";
    /**
     * 资质认证成功（此时立即获得接口权限）
     */
    public static final String EVENT_TYPE_QUALIFICATION_VERIFY_SUCCESS = "qualification_verify_success";
    /**
     * 资质认证失败
     */
    public static final String EVENT_TYPE_QUALIFICATION_VERIFY_FAIL = "qualification_verify_fail";
    /**
     * 名称认证成功（即命名成功）
     */
    public static final String EVENT_TYPE_NAMING_VERIFY_SUCCESS = "naming_verify_success";
    /**
     * 名称认证失败（这时虽然客户端不打勾，但仍有接口权限）
     */
    public static final String EVENT_TYPE_NAMING_VERIFY_FAIL = "naming_verify_fail";
    /**
     * 年审通知
     */
    public static final String EVENT_TYPE_ANNUAL_RENEW = "annual_renew";
    /**
     * 认证过期失效通知审通知
     */
    public static final String EVENT_TYPE_VERIFY_EXPIRED = "verify_expired";

    /**
     * 模板消息发送结果通知
     */
    public static final String EVENT_TYPE_TEMPLATE_SEND_JOB_FINISH = "TEMPLATESENDJOBFINISH";

    /**
     * 事件类型
     */
    @JSONField(name = "Event")
    protected String event;
    /**
     * 事件KEY值
     */
    @JSONField(name = "EventKey")
    protected String eventKey;

    public Event() {
        super(Message.MESSAGE_TYPE_EVENT);
    }

    public Event(String event) {
        super(Message.MESSAGE_TYPE_EVENT);
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    /**
     * 取消关注事件
     */
    public static class Unsubscribe extends Event {
        public Unsubscribe() {
            super(EVENT_TYPE_UNSUBSCRIBE);
        }
    }
}
