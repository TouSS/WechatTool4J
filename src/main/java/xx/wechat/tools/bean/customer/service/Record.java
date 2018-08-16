package xx.wechat.tools.bean.customer.service;

/**
 * 聊天记录
 */
public class Record {
    /**
     * 完整客服帐号，格式为：帐号前缀@公众号微信号
     */
    private String worker;
    /**
     * 用户标识
     */
    private String openid;
    /**
     * 操作码，2002（客服发送信息），2003（客服接收消息）
     */
    private String opercode;
    /**
     * 聊天记录
     */
    private String text;
    /**
     * 操作时间，unix时间戳
     */
    private Long time;

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpercode() {
        return opercode;
    }

    public void setOpercode(String opercode) {
        this.opercode = opercode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
