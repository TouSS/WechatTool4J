package xx.wechat.tools.bean.event;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 模板消息发送结果通知事件
 */
public class TemplateSendJobFinishEvent extends Event {

    /**
     * 发送状态(成功：success，用户拒绝接收：failed:user block，发送失败（非用户拒绝）：failed: system failed)
     */
    @JSONField(name = "Status")
    private String status;

    public TemplateSendJobFinishEvent() {
        super(Event.EVENT_TYPE_TEMPLATE_SEND_JOB_FINISH);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
