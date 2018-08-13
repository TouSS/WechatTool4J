package xx.wechat.tools.handler;

import com.alibaba.fastjson.JSON;
import xx.wechat.tools.bean.AccessToken;
import xx.wechat.tools.bean.QRCode;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;
import xx.wechat.tools.utils.Http;
import xx.wechat.tools.utils.Https;
import xx.wechat.tools.utils.WechatServer;

import java.util.HashMap;

/**
 * 二维码/链接处理方法
 */
public class QRCodeHandler extends Handler {
    /**
     * 临时的整型参数值
     */
    public final static String QR_SCENE = "QR_SCENE";
    /**
     * 临时的字符串参数值
     */
    public final static String QR_STR_SCENE = "QR_STR_SCENE";
    /**
     * 永久的整型参数值
     */
    public final static String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";
    /**
     * 永久的字符串参数值
     */
    public final static String QR_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";

    public QRCodeHandler(AccessToken token) {
        super(token);
    }

    /**
     * 创建二维码
     *
     * @param type  二维码类型, 二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值，QR_LIMIT_SCENE为永久的整型参数值，QR_LIMIT_STR_SCENE为永久的字符串参数值
     * @param scene 二维码场景, 整形|字符
     * @return 二维码
     * @throws WechatException
     * @throws HttpException
     */
    public QRCode createQRCode(String type, Object scene) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/qrcode/create?access_token=" + this.token.getToken();
        QRCode qrCode = JSON.parseObject(Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("action_name", type);
            put("action_info", new HashMap<String, Object>() {{
                if (QR_SCENE.equals(type) || QR_LIMIT_SCENE.equals(type)) {
                    put("scene", new HashMap<String, Integer>() {{
                        put("scene_id", (Integer) scene);
                    }});
                } else {
                    put("scene", new HashMap<String, String>() {{
                        put("scene_str", (String) scene);
                    }});
                }
            }});
        }})), QRCode.class);
        if (QR_SCENE.equals(type) || QR_LIMIT_SCENE.equals(type)) {
            qrCode.setSceneId((Integer) scene);
        } else {
            qrCode.setSceneStr((String) scene);
        }
        return qrCode;
    }

    /**
     * 长链接转短链接接
     *
     * @param longUrl 需要转换的长链接，支持http://、https://、weixin://wxpay 格式的url
     * @return 短链接
     * @throws WechatException
     * @throws HttpException
     */
    public String getShortUrl(String longUrl) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/shorturl?access_token=" + this.token.getToken();
        return JSON.parseObject(Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, String>() {{
            put("action", "long2short");
            put("long_url", longUrl);
        }}))).getString("short_url");
    }
}
