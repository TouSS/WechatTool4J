package xx.wechat.tools.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import xx.wechat.tools.bean.AccessToken;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;

import java.util.Arrays;

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
}
