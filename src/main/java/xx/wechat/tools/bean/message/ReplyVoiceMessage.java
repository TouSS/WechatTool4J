package xx.wechat.tools.bean.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 回复语音消息
 */
public class ReplyVoiceMessage extends Message {
    /**
     * 语音
     */
    @JSONField(name = "Voice")
    private Voice voice;

    public ReplyVoiceMessage() {
        super(Message.MESSAGE_TYPE_VOICE);
    }

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }
}
