package xx.wechat.tools.bean.customer.service;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 未接入会话
 */
public class WaitCase {
    /**
     * 未接入会话数量
     */
    @JSONField(name = "count")
    private int count;
    /**
     * 未接入会话列表，最多返回100条数据，按照来访顺序
     */
    @JSONField(name = "waitcaselist")
    private List<Session> waitCaseList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Session> getWaitCaseList() {
        return waitCaseList;
    }

    public void setWaitCaseList(List<Session> waitCaseList) {
        this.waitCaseList = waitCaseList;
    }
}
