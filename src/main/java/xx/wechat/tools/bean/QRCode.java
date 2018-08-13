package xx.wechat.tools.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 二维码
 */
public class QRCode {
    /**
     * 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码
     */
    @JSONField(name = "ticket")
    private String ticket;
    /**
     * 二维码有效时间，以秒为单位。 最大不超过2592000（即30天）
     */
    @JSONField(name = "expire_seconds")
    private Long expire;
    /**
     * 二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
     */
    @JSONField(name = "url")
    private String url;
    /**
     * 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     */
    @JSONField(name = "scene_id")
    private Integer sceneId;
    /**
     * 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
     */
    @JSONField(name = "scene_str")
    private String sceneStr;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneStr() {
        return sceneStr;
    }

    public void setSceneStr(String sceneStr) {
        this.sceneStr = sceneStr;
    }

    public String getQRCodeUrl() throws UnsupportedEncodingException {
        return "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + URLEncoder.encode(this.ticket, "UTF-8");
    }
}
