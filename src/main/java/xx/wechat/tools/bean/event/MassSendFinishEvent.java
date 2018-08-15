package xx.wechat.tools.bean.event;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 群发结束事件
 */
public class MassSendFinishEvent extends Event {
    /**
     * 群发的结构，为“send success”或“send fail”或“err(num)”。但send success时，也有可能因用户拒收公众号的消息、系统错误等原因造成少量用户接收失败。
     * err(num)是审核失败的具体原因，可能的情况如下：
     * err(10001), //涉嫌广告
     * err(20001), //涉嫌政治
     * err(20004), //涉嫌社会
     * err(20002), //涉嫌色情
     * err(20006), //涉嫌违法犯罪
     * err(20008), //涉嫌欺诈
     * err(20013), //涉嫌版权
     * err(22000), //涉嫌互推(互相宣传)
     * err(21000), //涉嫌其他
     * err(30001) // 原创校验出现系统错误且用户选择了被判为转载就不群发
     * err(30002) // 原创校验被判定为不能群发
     * err(30003) // 原创校验被判定为转载文且用户选择了被判为转载就不群发
     */
    @JSONField(name = "Status")
    private String status;
    /**
     * tag_id下粉丝数；或者openid_list中的粉丝数
     */
    @JSONField(name = "TotalCount")
    private Integer totalCount;
    /**
     * 过滤（过滤是指特定地区、性别的过滤、用户设置拒收的过滤，用户接收已超4条的过滤）后，准备发送的粉丝数，原则上，filterCount = sentCount + errorCount
     */
    @JSONField(name = "FilterCount")
    private Integer filterCount;
    /**
     * 发送成功的粉丝数
     */
    @JSONField(name = "SentCount")
    private Integer sentCount;
    /**
     * 发送失败的粉丝数
     */
    @JSONField(name = "ErrorCount")
    private Integer errorCount;
    /**
     * 各个单图文校验结果
     */
    @JSONField(name = "CopyrightCheckResult")
    private Map<String, Object> copyrightCheckResult;

    public MassSendFinishEvent() {
        super(Event.EVENT_TYPE_LOCATION_MASS_SEND_JOB_FINISH);
        this.copyrightCheckResult = new HashMap<>();
        this.copyrightCheckResult.put("Count", 0);
        this.copyrightCheckResult.put("ResultList", new HashMap<String, List<ArticleCheckResult>>(){{
            put("item", new ArrayList<ArticleCheckResult>());
        }});
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getFilterCount() {
        return filterCount;
    }

    public void setFilterCount(Integer filterCount) {
        this.filterCount = filterCount;
    }

    public Integer getSentCount() {
        return sentCount;
    }

    public void setSentCount(Integer sentCount) {
        this.sentCount = sentCount;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public Map<String, Object> getCopyrightCheckResult() {
        return copyrightCheckResult;
    }

    public void setCopyrightCheckResult(Map<String, Object> copyrightCheckResult) {
        this.copyrightCheckResult = copyrightCheckResult;
    }

    public Integer articleCheckResultCount() {
        if (this.copyrightCheckResult != null && this.copyrightCheckResult.containsKey("Count")) {
            return (Integer) this.copyrightCheckResult.get("Count");
        }
        return 0;
    }

    public Integer checkState() {
        if (this.copyrightCheckResult != null && this.copyrightCheckResult.containsKey("CheckState")) {
            return (Integer) this.copyrightCheckResult.get("CheckState");
        }
        return 0;
    }

    public List<ArticleCheckResult> articleCheckResultList() {
        if (this.copyrightCheckResult != null && this.copyrightCheckResult.containsKey("ResultList")) {
            return ((Map<String, List<ArticleCheckResult>>) this.copyrightCheckResult.get("ResultList")).get("item");
        }
        return null;
    }
}
