package xx.wechat.tools.bean.event;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 弹出地理位置选择器的事件
 */
public class LocationSelectEvent extends Event {

    /**
     * 发送的位置信息
     */
    @JSONField(name = "SendLocationInfo")
    private SendLocationInfo sendLocationInfo;

    public LocationSelectEvent() {
        super(Event.EVENT_TYPE_LOCATION_SELECT);
    }

    public SendLocationInfo getSendLocationInfo() {
        return sendLocationInfo;
    }

    public void setSendLocationInfo(SendLocationInfo sendLocationInfo) {
        this.sendLocationInfo = sendLocationInfo;
    }
}
