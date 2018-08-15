package xx.wechat.tools.bean.user;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
import java.util.Map;

/**
 * 用于拉取用户列表返回
 */
public class UserBatchInfo {
    /**
     * 关注该公众账号的总用户数
     */
    @JSONField(name = "total")
    private Integer total;
    /**
     * 拉取的OPENID个数，最大值为10000
     */
    @JSONField(name = "count")
    private Integer count;
    /**
     * 列表数据，OPENID的列表
     */
    @JSONField(name = "data")
    private Map<String, List<String>> data;
    /**
     * 拉取列表的最后一个用户的OPENID
     */
    @JSONField(name = "next_openid")
    private String nextOpenid;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Map<String, List<String>> getData() {
        return data;
    }

    public void setData(Map<String, List<String>> data) {
        this.data = data;
    }

    public String getNextOpenid() {
        return nextOpenid;
    }

    public void setNextOpenid(String nextOpenid) {
        this.nextOpenid = nextOpenid;
    }

    public List<String> openids() {
        if (this.data != null) {
            return this.data.get("openid");
        }
        return null;
    }
}
