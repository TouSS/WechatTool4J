package xx.wechat.tools.bean.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 文本消息
 */
public class TextMessage extends Message {
    /**
     * 文本消息内容
     */
    @JSONField(name = "Content")
    private String content;

    public TextMessage() {
        super(Message.MESSAGE_TYPE_TEXT);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
