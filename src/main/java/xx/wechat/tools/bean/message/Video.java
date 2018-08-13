package xx.wechat.tools.bean.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 消息视频
 */
public class Video {
    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id
     */
    @JSONField(name = "MediaId")
    private String mediaId;
    /**
     * 视频消息的标题
     */
    @JSONField(name = "Title")
    private String title;
    /**
     * 视频消息的描述
     */
    @JSONField(name = "Description")
    private String description;

    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
     */
    @JSONField(name = "ThumbMediaId")
    private String thumbMediaId;

    public Video() {
    }

    public Video(String mediaId, String title, String description, String thumbMediaId) {
        this.mediaId = mediaId;
        this.title = title;
        this.description = description;
        this.thumbMediaId = thumbMediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
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

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
}
