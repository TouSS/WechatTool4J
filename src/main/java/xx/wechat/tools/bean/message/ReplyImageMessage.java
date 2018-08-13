package xx.wechat.tools.bean.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 回复图片消息
 */
public class ReplyImageMessage extends Message {
    /**
     * 图片
     */
    @JSONField(name = "Image")
    private Image image;

    public ReplyImageMessage() {
        super(Message.MESSAGE_TYPE_IMAGE);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
