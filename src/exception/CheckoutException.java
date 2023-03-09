package exception;

public class CheckoutException extends Exception{
    public CheckoutException() {
        super();
    }

    public CheckoutException(String msg) {
        super(msg);
    }

    public CheckoutException(Throwable t) {
        super(t);
    }
}
