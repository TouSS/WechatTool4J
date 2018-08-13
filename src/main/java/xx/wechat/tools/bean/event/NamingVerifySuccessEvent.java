package xx.wechat.tools.bean.event;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 名称认证成功事件（即命名成功）
 */
public class NamingVerifySuccessEvent extends Event {
    /**
     * 有效期 (整形)，指的是时间戳，将于该时间戳认证过期
     */
    @JSONField(name = "ExpiredTime")
    private Long expiredTime;

    public NamingVerifySuccessEvent() {
        super(Event.EVENT_TYPE_NAMING_VERIFY_SUCCESS);
    }

    public Long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Long expiredTime) {
        this.expiredTime = expiredTime;
    }
}
