package xx.wechat.tools.bean.event;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 图片|相片选择事件/弹出系统拍照发图的事件
 */
public class PhotoSelectEvent extends Event {
    /**
     * 发送的图片信息
     */
    @JSONField(name = "SendPicsInfo")
    private SendPicsInfo sendPicsInfo;

    public PhotoSelectEvent() {
        super(Event.EVENT_TYPE_PIC_SYSPHOTO);
    }

    public SendPicsInfo getSendPicsInfo() {
        return sendPicsInfo;
    }

    public void setSendPicsInfo(SendPicsInfo sendPicsInfo) {
        this.sendPicsInfo = sendPicsInfo;
    }

    /**
     * 弹出拍照或者相册发图的事件
     */
    public static class PhotoOrAlbum extends PhotoSelectEvent {
        public PhotoOrAlbum() {
            this.setEvent(Event.EVENT_TYPE_PIC_PHOTO_OR_ALBUM);
        }
    }

    /**
     * 弹出微信相册发图器的事件
     */
    public static class WeixinAlbum extends PhotoSelectEvent {
        public WeixinAlbum() {
            this.setEvent(Event.EVENT_TYPE_PIC_WEIXIN);
        }
    }
}
