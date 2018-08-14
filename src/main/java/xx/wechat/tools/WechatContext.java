package xx.wechat.tools;

import xx.wechat.tools.bean.AccessToken;
import xx.wechat.tools.bean.WebAccessToken;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;
import xx.wechat.tools.context.*;
import xx.wechat.tools.utils.Access;

/**
 * 微信操作集
 */
public class WechatContext {
    private String appid;
    private String secret;

    private AccessToken accessToken;

    public WechatContext(String appid, String secret) throws WechatException, HttpException {
        this.appid = appid;
        this.secret = secret;
        refreshToken();
    }

    protected void refreshToken() throws WechatException, HttpException {
        this.accessToken = Access.getAccessToken(this.appid, this.secret);
    }

    protected synchronized AccessToken getToken() throws WechatException, HttpException {
        if (this.accessToken == null || this.accessToken.isExpired()) refreshToken();
        return this.accessToken;
    }

    /**
     * 菜单操作方法
     *
     * @return 菜单处理方法集合
     * @throws WechatException
     * @throws HttpException
     */
    public MenuContext getMenuContext() throws WechatException, HttpException {
        return new MenuContext(getToken());
    }

    /**
     * 素材操作方法
     *
     * @return 素材处理器
     * @throws WechatException
     * @throws HttpException
     */
    public MediaContext getMediaContext() throws WechatException, HttpException {
        return new MediaContext(getToken());
    }

    /**
     * 用户操作方法
     *
     * @return 用户处理器
     * @throws WechatException
     * @throws HttpException
     */
    public UserContext getUserContext() throws WechatException, HttpException {
        return new UserContext(getToken());
    }

    /**
     * 客服
     *
     * @return 客服处理集合
     * @throws WechatException
     * @throws HttpException
     */
    public CustomerServiceContext getCustomerServiceContext() throws WechatException, HttpException {
        return new CustomerServiceContext(getToken());
    }

    /**
     * 群发
     *
     * @return 群发处理方法集合
     * @throws WechatException
     * @throws HttpException
     */
    public BatchSendContext getBatchSendContext() throws WechatException, HttpException {
        return new BatchSendContext(getToken());
    }

    /**
     * 二维码
     *
     * @return 二维码|链接处理方法
     * @throws WechatException
     * @throws HttpException
     */
    public QRCodeContext getQRCodeContext() throws WechatException, HttpException {
        return new QRCodeContext(getToken());
    }

    /**
     * 网页授权
     *
     * @return 网页授权方法
     * @throws WechatException
     * @throws HttpException
     */
    public WebAccessContext getWebAccessContext() throws WechatException, HttpException {
        return new WebAccessContext(this.appid, this.secret);
    }

    /**
     * 网页授权
     *
     * @param token 网页授权TOKEN
     * @return 网页授权方法
     * @throws WechatException
     * @throws HttpException
     */
    public WebAccessContext getWebAccessContext(WebAccessToken token) throws WechatException, HttpException {
        return new WebAccessContext(token);
    }

}
