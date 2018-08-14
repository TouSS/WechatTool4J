package xx.wechat.tools.exception;

/**
 * Http异常
 */
public class MessageControllerNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;


    public MessageControllerNotFoundException() {
        super("Message controller no found.");
    }

    public MessageControllerNotFoundException(String message) {
        super(message);
    }

    public MessageControllerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageControllerNotFoundException(Throwable cause) {
        super(cause);
    }

    protected MessageControllerNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
