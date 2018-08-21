package xx.wechat.tools.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import xx.wechat.tools.bean.token.AccessToken;
import xx.wechat.tools.bean.token.JsApiTicket;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 公众号凭证/接入检查等
 */
public class Access {
    /**
     * 获取公众号凭证
     *
     * @param appid  公众号APPID
     * @param secret 公众号密匙
     * @return 凭证
     * @throws WechatException 微信异常
     * @throws HttpException   网络异常
     */
    public static AccessToken getAccessToken(String appid, String secret) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
        AccessToken token = JSON.parseObject(Https.get(url), AccessToken.class);
        token.setApply(System.currentTimeMillis() / 1000);
        return token;
    }

    /**
     * 获取JS调用接口凭证
     *
     * @param accessToken ACCESS_TOKEN
     * @return 临时票据
     * @throws WechatException
     * @throws HttpException
     */
    public static JsApiTicket getJsApiTicket(String accessToken) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";
        JsApiTicket ticket = JSON.parseObject(Https.get(url), JsApiTicket.class);
        ticket.setApply(System.currentTimeMillis() / 1000);
        return ticket;
    }

    /**
     * 验证消息的确来自微信服务器
     *
     * @param token     微信服务器接入时预设的Token
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param signature 填写的随机字符串
     * @return 是否为合法请求
     */
    public static boolean checkSignature(String token, String timestamp, String nonce, String signature) {
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(nonce)) {
            return false;
        }
        String[] tmp = {token, timestamp, nonce};
        Arrays.sort(tmp);
        String tmpStr = "";
        for (String s : tmp) {
            tmpStr += s;
        }
        if (new String(DigestUtils.sha1Hex(tmpStr)).equals(signature)) {
            return true;
        }
        return false;
    }

    /**
     * ticket 签名
     *
     * @param ticket 临时票据
     * @param url    当前网页的URL，不包含#及其后面部分
     * @return 签名
     */
    public static Map<String, String> getJsApiTicketSignature(String ticket, String url) {
        String[] keys = {"jsapi_ticket", "noncestr", "timestamp", "url"};
        Arrays.sort(keys);
        String noncestr = RandomStringUtils.randomAlphanumeric(16);
        String timestamp = String.valueOf(System.currentTimeMillis());
        Map<String, String> data = new HashMap<String, String>() {{
            put("jsapi_ticket", ticket);
            put("noncestr", noncestr);
            put("timestamp", timestamp);
            put("url", url);
        }};
        String str = "";
        String key;
        for (int i = 0; i < keys.length; i++) {
            key = keys[i];
            if (i == 0) {
                str += key + "=" + data.get(key);
            } else {
                str += "&" + key + "=" + data.get(key);
            }
        }
        Map<String, String> signature = new HashMap<>();
        signature.put("timestamp", timestamp);
        signature.put("nonceStr", noncestr);
        signature.put("signature", new String(DigestUtils.sha1Hex(str)));
        return signature;
    }

}
