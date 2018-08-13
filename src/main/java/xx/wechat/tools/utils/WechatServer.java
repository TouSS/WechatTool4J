package xx.wechat.tools.utils;

/**
 * 公众平台接口域名
 */
public class WechatServer {
    /** 通用域名 **/
    public static final String SERVER_DEFAULT = "api.weixin.qq.com";
    /** 上海域名 **/
    public static final String SERVER_SH = "sh.api.weixin.qq.com";
    /** 深圳域名 **/
    public static final String SERVER_SZ = "sz.api.weixin.qq.com";
    /** 香港域名 **/
    public static final String SERVER_HK = "hk.api.weixin.qq.com";


    public static String get() {
        return SERVER_DEFAULT;
    }
}
