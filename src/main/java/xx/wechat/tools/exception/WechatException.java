package xx.wechat.tools.exception;

public class WechatException extends Exception {
    private static final long serialVersionUID = 1L;

    private int code;

    public WechatException() {
        super();
    }

    public WechatException(String message) {
        super(message);
    }

    public WechatException(int code, String message) {
        super(message);
        this.code = code;
    }

    public WechatException(String message, Throwable cause) {
        super(message, cause);
    }

    public WechatException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public WechatException(Throwable cause) {
        super(cause);
    }

    public WechatException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    protected WechatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    protected WechatException(int code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
