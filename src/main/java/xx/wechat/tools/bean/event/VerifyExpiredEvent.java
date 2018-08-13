package xx.wechat.tools.bean.event;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 认证过期失效通知审通知事件
 */
public class VerifyExpiredEvent extends Event {
    /**
     * 有效期 (整形)，指的是时间戳，将于该时间戳认证过期
     */
    @JSONField(name = "ExpiredTime")
    private Long expiredTime;

    public VerifyExpiredEvent() {
        super(Event.EVENT_TYPE_VERIFY_EXPIRED);
    }

    public Long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Long expiredTime) {
        this.expiredTime = expiredTime;
    }
}
