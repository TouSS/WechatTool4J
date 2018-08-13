package xx.wechat.tools.bean.event;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 扫码推事件的事件
 */
public class ScanEvent extends Event {
    /**
     * 扫描信息
     */
    @JSONField(name = "ScanCodeInfo")
    private ScanCodeInfo scanCodeInfo;

    public ScanEvent() {
        super(Event.EVENT_TYPE_SCANCODE_PUSH);
    }

    public ScanCodeInfo getScanCodeInfo() {
        return scanCodeInfo;
    }

    public void setScanCodeInfo(ScanCodeInfo scanCodeInfo) {
        this.scanCodeInfo = scanCodeInfo;
    }

    /**
     * 扫码推事件且弹出“消息接收中”提示框的事件
     */
    public static class ScanWaitMsgEvent extends ScanEvent {
        public ScanWaitMsgEvent() {
            this.setEvent(Event.EVENT_TYPE_SCANCODE_WAITMSG);
        }
    }
}
