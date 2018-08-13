package xx.wechat.tools.handler;

import com.alibaba.fastjson.JSON;
import xx.wechat.tools.bean.WebAccessToken;
import xx.wechat.tools.bean.user.User;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;
import xx.wechat.tools.utils.Https;
import xx.wechat.tools.utils.WechatServer;

/**
 * 网页授权处理
 */
public class WebAccessHandler {

    private String appid;
    private String secret;
    private WebAccessToken webAccessToken;

    public WebAccessHandler(String appid, String secret) {
        this.appid = appid;
        this.secret = secret;
    }

    public WebAccessHandler(WebAccessToken token) {
        this.appid = token.getAppid();
        this.webAccessToken = token;
    }

    public WebAccessToken getWebAccessToken() {
        return webAccessToken;
    }

    /**
     * 获取网页授权TOKEN
     *
     * @param code 页面跳转CODE
     * @return TOKEN
     * @throws WechatException
     * @throws HttpException
     */
    public WebAccessToken getWebAccessToken(String code) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/sns/oauth2/access_token?appid=" + this.appid + "&secret=" + this.secret + "&code=" + code + "&grant_type=authorization_code";
        WebAccessToken webAccessToken = JSON.parseObject(Https.get(url), WebAccessToken.class);
        webAccessToken.setApply(System.currentTimeMillis());
        this.webAccessToken = webAccessToken;
        return webAccessToken;
    }

    /**
     * 刷新网页授权TOKEN
     *
     * @param token 网页授权TOKEN
     * @throws WechatException
     * @throws HttpException
     */
    public void refreshWebAccessToken(WebAccessToken token) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/sns/oauth2/refresh_token?appid=" + this.appid + "&refresh_token=" + token.getRefreshToken() + "&grant_type=refresh_token";
        token = JSON.parseObject(Https.get(url), WebAccessToken.class);
        token.setApply(System.currentTimeMillis());
    }

    /**
     * 拉取用户信息(需scope为 snsapi_userinfo)
     *
     * @param token 网页授权TOKEN
     * @param lang  国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
     * @return
     * @throws WechatException
     * @throws HttpException
     */
    public User getUserInfo(WebAccessToken token, String lang) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/sns/userinfo?access_token=" + token.getToken() + "&openid=" + token.getOpenid() + "&lang=" + lang;
        return JSON.parseObject(Https.get(url), User.class);
    }

    /**
     * 检验授权凭证（access_token）是否有效
     *
     * @param token 网页授权TOKEN
     * @return
     */
    public Boolean isValid(WebAccessToken token) {
        String url = "https://" + WechatServer.get() + "/sns/auth?access_token=" + token.getToken() + "&openid=" + token.getOpenid();
        try {
            Https.get("url");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
