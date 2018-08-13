package xx.wechat.tools.bean.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 链接消息
 */
public class LinkMessage extends Message{
    /**
     * 消息标题
     */
    @JSONField(name = "Title")
    private String title;
    /**
     * 消息描述
     */
    @JSONField(name = "Description")
    private String description;
    /**
     * 消息链接
     */
    @JSONField(name = "Url")
    private String url;

    public LinkMessage() {
        super(Message.MESSAGE_TYPE_LINK);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
