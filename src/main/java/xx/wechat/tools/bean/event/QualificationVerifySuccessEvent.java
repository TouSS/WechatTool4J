package xx.wechat.tools.bean.event;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 资质认证成功事件（此时立即获得接口权限）
 */
public class QualificationVerifySuccessEvent extends Event {
    /**
     * 有效期 (整形)，指的是时间戳，将于该时间戳认证过期
     */
    @JSONField(name = "ExpiredTime")
    private Long expiredTime;

    public QualificationVerifySuccessEvent() {
        super(Event.EVENT_TYPE_QUALIFICATION_VERIFY_SUCCESS);
    }

    public Long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Long expiredTime) {
        this.expiredTime = expiredTime;
    }
}
