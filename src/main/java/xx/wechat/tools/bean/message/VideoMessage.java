package xx.wechat.tools.bean.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 视频消息/小视频消息
 */
public class VideoMessage extends Message {
    /**
     * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    @JSONField(name = "MediaId")
    private String mediaId;
    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    @JSONField(name = "ThumbMediaId")
    private String thumbMediaId;

    public VideoMessage() {
        super(Message.MESSAGE_TYPE_VIDEO);
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    /**
     * 小视频消息
     */
    public static class Short extends VideoMessage {
        public Short() {
            this.setMsgType(Message.MESSAGE_TYPE_SHORT_VIDEO);
        }
    }
}
