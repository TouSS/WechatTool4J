package xx.wechat.tools.bean.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 图片消息
 */
public class ImageMessage extends Message {
    /**
     * 图片链接（由系统生成）
     */
    @JSONField(name = "PicUrl")
    private String picUrl;
    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    @JSONField(name = "MediaId")
    private String mediaId;

    public ImageMessage() {
        super(Message.MESSAGE_TYPE_IMAGE);
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
