package xx.wechat.tools.bean.event;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 年审通知事件
 */
public class AnnualRenewEvent extends Event {
    /**
     * 有效期 (整形)，指的是时间戳，将于该时间戳认证过期
     */
    @JSONField(name = "ExpiredTime")
    private Long expiredTime;

    public AnnualRenewEvent() {
        super(Event.EVENT_TYPE_ANNUAL_RENEW);
    }

    public Long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Long expiredTime) {
        this.expiredTime = expiredTime;
    }
}
