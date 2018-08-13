package xx.wechat.tools;

import xx.wechat.tools.bean.AccessToken;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;
import xx.wechat.tools.handler.MenuHandler;
import xx.wechat.tools.utils.Access;

/**
 * 微信操作集
 */
public class WechatContext {
    private String appid;
    private String secret;

    private AccessToken token;

    protected WechatContext(String appid, String secret) throws WechatException, HttpException {
        this.appid = appid;
        this.secret = secret;
        refreshToken();
    }

    protected void refreshToken() throws WechatException, HttpException {
        this.token = Access.getAccessToken(this.appid, this.secret);
    }

    protected AccessToken getToken() throws WechatException, HttpException {
        if(this.token == null || this.token.isExpired()) refreshToken();
        return this.token;
    }

    public WechatContext getInstance(String appid, String secret) throws WechatException, HttpException {
        return new WechatContext(appid, secret);
    }

    /**
     * 菜单操作方法
     * @return 菜单处理方法集合
     * @throws WechatException
     * @throws HttpException
     */
    public MenuHandler getMenuHandler() throws WechatException, HttpException {
        return new MenuHandler(getToken());
    }

}
