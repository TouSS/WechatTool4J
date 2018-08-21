package xx.wechat.tools.context;

import xx.wechat.tools.bean.token.AccessToken;

/**
 * 处理方法基础类
 */
public class PartContext {
    protected AccessToken token;

    public PartContext(AccessToken token) {
        this.token = token;
    }

    public Boolean isAvailable() {
        return !token.isExpired();
    }
}
