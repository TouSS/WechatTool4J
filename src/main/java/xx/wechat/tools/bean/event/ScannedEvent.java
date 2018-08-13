package xx.wechat.tools.bean.event;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 被扫码事件
 */
public class ScannedEvent extends Event {
    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    @JSONField(name = "Ticket")
    private String ticket;

    public ScannedEvent() {
        super(Event.EVENT_TYPE_SCAN);
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    /**
     * (扫码)?关注事件
     */
    public static class Subscribe extends ScannedEvent {
        public Subscribe() {
            this.setEvent(Event.EVENT_TYPE_SUBSCRIBE);
        }
    }
}
