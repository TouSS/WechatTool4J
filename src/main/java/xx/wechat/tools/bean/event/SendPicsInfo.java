package xx.wechat.tools.bean.event;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Map;

/**
 * 发送的图片信息
 */
public class SendPicsInfo {
    /**
     * 发送的图片数量
     */
    @JSONField(name = "Count")
    private Integer count;
    /**
     * 图片列表
     */
    @JSONField(name = "PicList")
    private Map<String, Object> picList;

    public SendPicsInfo() {
    }

    public SendPicsInfo(Integer count, Map<String, Object> picList) {
        this.count = count;
        this.picList = picList;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Map<String, Object> getPicList() {
        return picList;
    }

    public void setPicList(Map<String, Object> picList) {
        this.picList = picList;
    }
}
