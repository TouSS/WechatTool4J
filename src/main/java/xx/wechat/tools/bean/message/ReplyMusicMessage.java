package xx.wechat.tools.bean.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 回复音乐消息
 */
public class ReplyMusicMessage extends Message {
    /**
     * 音乐
     */
    @JSONField(name = "Music")
    private Music music;

    public ReplyMusicMessage() {
        super(Message.MESSAGE_TYPE_MUSIC);
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
