package business.exception;

public class LoginException extends Exception{
    public LoginException() {
        super();
    }

    public LoginException(String msg) {
        super(msg);
    }

    public LoginException(Throwable t) {
        super(t);
    }
}
