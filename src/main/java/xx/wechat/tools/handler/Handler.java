package xx.wechat.tools.handler;

import xx.wechat.tools.bean.AccessToken;

/**
 * 处理方法基础类
 */
public class Handler {
    protected AccessToken token;

    public Handler(AccessToken token) {
        this.token = token;
    }

    public Boolean isAvailable() {
        return !token.isExpired();
    }
}
