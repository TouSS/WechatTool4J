package xx.wechat.tools.bean.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 语音消息
 */
public class VoiceMessage extends Message {
    /**
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    @JSONField(name = "MediaId")
    private String mediaId;
    /**
     * 语音格式，如amr，speex等
     */
    @JSONField(name = "Format")
    private String format;
    /**
     * 语音识别结果，UTF8编码
     */
    @JSONField(name = "Recognition")
    private String recognition;

    public VoiceMessage() {
        super(Message.MESSAGE_TYPE_VOICE);
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }
}
