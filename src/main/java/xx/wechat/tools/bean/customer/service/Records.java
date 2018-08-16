package xx.wechat.tools.bean.customer.service;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 聊天记录
 */
public class Records {
    /**
     * 记录数
     */
    @JSONField(name = "number")
    private Integer number;
    /**
     * 下一条查询ID
     */
    @JSONField(name = "msgid")
    private Long msgId;
    /**
     * 聊天列表
     */
    @JSONField(name = "recordlist")
    private List<Record> recordList;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }
}
