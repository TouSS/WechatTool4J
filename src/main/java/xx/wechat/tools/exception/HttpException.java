package xx.wechat.tools.exception;

/**
 * Http异常
 */
public class HttpException extends Exception {
    private static final long serialVersionUID = 1L;

    private int code = 500;

    public HttpException() {
        super();
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(int code, String message) {
        super(message);
        this.code = code;
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public HttpException(Throwable cause) {
        super(cause);
    }

    public HttpException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    protected HttpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    protected HttpException(int code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
