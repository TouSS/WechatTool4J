package xx.wechat.tools.bean.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 消息图片
 */
public class Image {
    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id
     */
    @JSONField(name = "MediaId")
    private String mediaId;

    public Image() {
    }

    public Image(String mediaId) {

        this.mediaId = mediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
