package xx.wechat.tools.bean.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 回复视频消息
 */
public class ReplyVideoMessage extends Message {

    /**
     * 视频
     */
    @JSONField(name = "Video")
    private Video video;

    public ReplyVideoMessage() {
        super(Message.MESSAGE_TYPE_VIDEO);
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
