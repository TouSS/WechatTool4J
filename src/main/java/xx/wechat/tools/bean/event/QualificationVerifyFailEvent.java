package xx.wechat.tools.bean.event;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 资质认证失败事件
 */
public class QualificationVerifyFailEvent extends Event {
    /**
     * 失败发生时间 (整形)，时间戳
     */
    @JSONField(name = "FailTime")
    private Long failTime;
    /**
     * 认证失败的原因
     */
    @JSONField(name = "FailReason")
    private String failReason;

    public QualificationVerifyFailEvent() {
        super(Event.EVENT_TYPE_QUALIFICATION_VERIFY_FAIL);
    }

    public Long getFailTime() {
        return failTime;
    }

    public void setFailTime(Long failTime) {
        this.failTime = failTime;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
}
