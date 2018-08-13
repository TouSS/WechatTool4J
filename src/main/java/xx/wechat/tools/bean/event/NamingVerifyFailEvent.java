package xx.wechat.tools.bean.event;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 名称认证失败事件（这时虽然客户端不打勾，但仍有接口权限）
 */
public class NamingVerifyFailEvent extends Event {
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

    public NamingVerifyFailEvent() {
        super(Event.EVENT_TYPE_NAMING_VERIFY_FAIL);
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
